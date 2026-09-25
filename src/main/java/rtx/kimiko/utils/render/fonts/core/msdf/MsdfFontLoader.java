/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  it.unimi.dsi.fastutil.longs.Long2FloatMap
 *  it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap
 *  kotlin.Metadata
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.ResourceTexture
 *  net.minecraft.client.texture.ReloadableTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.longs.Long2FloatMap;
import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.ReloadableTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfAtlasPack;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFont;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfGlyph;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u001c\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\n0\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFontLoader;", "", "<init>", "()V", "", "basePath", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "Lkotlin/jvm/JvmStatic;", "load", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "rtx.kimiko:kimiko"})
public final class MsdfFontLoader {
    @NotNull
    public static final MsdfFontLoader INSTANCE = new MsdfFontLoader();
    private static final Logger LOGGER = LoggerFactory.getLogger((String)"Kimiko/MSDF");

    private MsdfFontLoader() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @Nullable
    public static final MsdfFont load(@Nullable String basePath) {
        if (basePath == null) {
            return null;
        }
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)(basePath + ".json"));
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        Identifier jsonId = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)(basePath + ".png"));
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        Identifier pngId = identifier3;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Identifier textureId = null;
        textureId = pngId;
        MsdfAtlasPack.Placement placement = null;
        if (MsdfAtlasPack.contains(basePath)) {
            placement = MsdfAtlasPack.placement(basePath);
            Identifier packed = MsdfAtlasPack.texture();
            if (placement != null && packed != null) {
                textureId = packed;
            } else {
                placement = null;
            }
        }
        if (placement == null) {
            try {
                mc.getTextureManager().registerTexture(pngId, (ReloadableTexture)new ResourceTexture(pngId));
            }
            catch (Throwable ex) {
                LOGGER.warn("[MSDF] Failed to register atlas texture {}", (Object)pngId, (Object)ex);
                return null;
            }
        }
        Optional optional = mc.getResourceManager().getResource(jsonId);
        Intrinsics.checkNotNullExpressionValue((Object)optional, (String)"getResource(...)");
        Optional resource = optional;
        if (resource.isEmpty()) {
            LOGGER.warn("[MSDF] Layout JSON not found: {}", (Object)jsonId);
            return null;
        }
        try {
            Closeable closeable = ((Resource)resource.get()).getReader();
            Throwable throwable = null;
            try {
                BufferedReader reader = (BufferedReader)closeable;
                boolean bl = false;
                JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
                JsonObject atlas = root.getAsJsonObject("atlas");
                int atlasW = atlas.get("width").getAsInt();
                int atlasH = atlas.get("height").getAsInt();
                JsonObject metrics = root.getAsJsonObject("metrics");
                float lineHeight = metrics.get("lineHeight").getAsFloat();
                float ascender = metrics.get("ascender").getAsFloat();
                float descender = metrics.get("descender").getAsFloat();
                HashMap glyphs = new HashMap(512);
                Iterator iterator = root.getAsJsonArray("glyphs").iterator();
                Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
                Iterator iterator2 = iterator;
                while (iterator2.hasNext()) {
                    float advance;
                    JsonElement element = (JsonElement)iterator2.next();
                    JsonObject g = element.getAsJsonObject();
                    int unicode = g.get("unicode").getAsInt();
                    float f = advance = g.has("advance") ? g.get("advance").getAsFloat() : 0.0f;
                    if (!g.has("planeBounds") || !g.has("atlasBounds")) {
                        ((Map)glyphs).put(unicode, MsdfGlyph.Companion.nonDrawable(advance));
                        continue;
                    }
                    JsonObject plane = g.getAsJsonObject("planeBounds");
                    JsonObject ab = g.getAsJsonObject("atlasBounds");
                    float planeLeft = plane.get("left").getAsFloat();
                    float planeBottom = plane.get("bottom").getAsFloat();
                    float planeRight = plane.get("right").getAsFloat();
                    float planeTop = plane.get("top").getAsFloat();
                    float abLeft = ab.get("left").getAsFloat();
                    float abBottom = ab.get("bottom").getAsFloat();
                    float abRight = ab.get("right").getAsFloat();
                    float abTop = ab.get("top").getAsFloat();
                    float u0 = abLeft / (float)atlasW;
                    float u1 = abRight / (float)atlasW;
                    float v0 = ((float)atlasH - abTop) / (float)atlasH;
                    float v1 = ((float)atlasH - abBottom) / (float)atlasH;
                    if (placement != null) {
                        float packedW = placement.packedWidth();
                        float packedH = placement.packedHeight();
                        float scaleU = (float)placement.sourceWidth() / packedW;
                        float scaleV = (float)placement.sourceHeight() / packedH;
                        float offsetU = (float)placement.offsetX() / packedW;
                        float offsetV = (float)placement.offsetY() / packedH;
                        u0 = offsetU + u0 * scaleU;
                        u1 = offsetU + u1 * scaleU;
                        v0 = offsetV + v0 * scaleV;
                        v1 = offsetV + v1 * scaleV;
                    }
                    ((Map)glyphs).put(unicode, new MsdfGlyph(true, advance, planeLeft, planeTop, planeRight, planeBottom, u0, v0, u1, v1));
                }
                Long2FloatOpenHashMap kerning = new Long2FloatOpenHashMap();
                if (root.has("kerning")) {
                    JsonArray pairs = root.getAsJsonArray("kerning");
                    Iterator iterator3 = pairs.iterator();
                    Intrinsics.checkNotNullExpressionValue((Object)iterator3, (String)"iterator(...)");
                    Iterator iterator4 = iterator3;
                    while (iterator4.hasNext()) {
                        JsonElement element = (JsonElement)iterator4.next();
                        JsonObject k = element.getAsJsonObject();
                        int left = k.get("unicode1").getAsInt();
                        int right = k.get("unicode2").getAsInt();
                        float advance = k.get("advance").getAsFloat();
                        if (advance == 0.0f) continue;
                        kerning.put((long)left << 32 | (long)right & 0xFFFFFFFFL, advance);
                    }
                }
                Object[] objectArray = new Object[]{basePath, glyphs.size(), atlasW, atlasH};
                LOGGER.info("[MSDF] Loaded font {} ({} glyphs, {}x{} atlas)", objectArray);
                MsdfFont msdfFont = new MsdfFont(textureId, atlasW, atlasH, lineHeight, ascender, descender, glyphs, (Long2FloatMap)kerning);
                return msdfFont;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
        }
        catch (Throwable ex) {
            LOGGER.warn("[MSDF] Failed to parse layout {}", (Object)jsonId, (Object)ex);
            return null;
        }
    }
}

