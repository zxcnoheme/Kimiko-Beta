/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.util.Util
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.OverlayTexture
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.customization;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.util.Util;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.OverlayTexture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0003LMNB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ+\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0014\u0010\u0015JO\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u001cJ\u0015\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0015\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002\u00a2\u0006\u0004\b!\u0010 JE\u0010*\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0\"2\u0006\u0010\u001f\u001a\u00020$2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b*\u0010+Ju\u00102\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0\"2\u0006\u0010\u001f\u001a\u00020$2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u00020%2\u0006\u00100\u001a\u00020%2\u0006\u00101\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b2\u00103J\u0017\u00106\u001a\u00020%2\u0006\u00105\u001a\u000204H\u0002\u00a2\u0006\u0004\b6\u00107J\u0017\u0010;\u001a\u00020:2\u0006\u00109\u001a\u000208H\u0002\u00a2\u0006\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010I\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010>R\u0014\u0010J\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010@R\u001e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010K\u00a8\u0006O"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer;", "", "<init>", "()V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "packedLight", "", "yOffset", "", "Lkotlin/jvm/JvmStatic;", "submit", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;IF)V", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lnet/minecraft/VertexConsumer;", "consumer", "render", "(Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/VertexConsumer;I)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;", "vertex", "nx", "ny", "nz", "frameOffset", "(Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;FFFIF)V", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Face;", "faces", "()Ljava/util/List;", "load", "", "result", "Lcom/google/gson/JsonObject;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;", "from", "to", "textureWidth", "textureHeight", "addCube", "(Ljava/util/List;Lcom/google/gson/JsonObject;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;FF)V", "", "name", "a", "b", "c", "d", "addFace", "(Ljava/util/List;Lcom/google/gson/JsonObject;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;FFFFF)V", "Lcom/google/gson/JsonArray;", "array", "vec", "(Lcom/google/gson/JsonArray;)Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;", "Lnet/minecraft/Identifier;", "id", "Ljava/io/BufferedReader;", "reader", "(Lnet/minecraft/Identifier;)Ljava/io/BufferedReader;", "SCALE", "F", "TEXTURE", "Lnet/minecraft/Identifier;", "Lnet/minecraft/RenderLayer;", "RENDER_TYPE", "Lnet/minecraft/RenderLayer;", "FRAME_COUNT", "I", "", "FRAME_DURATION_MS", "J", "FRAME_V_SCALE", "MODEL", "Ljava/util/List;", "Vec", "Vertex", "Face", "rtx.kimiko:kimiko"})
public final class CrownRenderer {
    @NotNull
    public static final CrownRenderer INSTANCE = new CrownRenderer();
    private static final float SCALE = 0.028f;
    @NotNull
    private static final Identifier TEXTURE;
    @NotNull
    private static final RenderLayer RENDER_TYPE;
    private static final int FRAME_COUNT = 80;
    private static final long FRAME_DURATION_MS = 50L;
    private static final float FRAME_V_SCALE = 0.0125f;
    @NotNull
    private static final Identifier MODEL;
    @Nullable
    private static volatile List<Face> faces;

    private CrownRenderer() {
    }

    @JvmStatic
    public static final void submit(@NotNull MatrixStack stack, @NotNull OrderedRenderCommandQueue collector, int packedLight, float yOffset) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        stack.push();
        stack.translate(0.0f, yOffset, 0.0f);
        stack.scale(0.028f, 0.028f, 0.028f);
        collector.submitCustom(stack, RENDER_TYPE, (arg_0, arg_1) -> CrownRenderer.submit$lambda$0(packedLight, arg_0, arg_1));
        stack.pop();
    }

    @JvmStatic
    public static final void render(@NotNull MatrixStack.Entry pose, @NotNull VertexConsumer consumer, int packedLight) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        float frameOffset = (float)(Util.getMeasuringTimeMs() / 50L % (long)80) * 0.0125f;
        for (Face face : INSTANCE.faces()) {
            INSTANCE.vertex(pose, consumer, face.getA(), face.getNx(), face.getNy(), face.getNz(), packedLight, frameOffset);
            INSTANCE.vertex(pose, consumer, face.getB(), face.getNx(), face.getNy(), face.getNz(), packedLight, frameOffset);
            INSTANCE.vertex(pose, consumer, face.getC(), face.getNx(), face.getNy(), face.getNz(), packedLight, frameOffset);
            INSTANCE.vertex(pose, consumer, face.getD(), face.getNx(), face.getNy(), face.getNz(), packedLight, frameOffset);
        }
    }

    private final void vertex(MatrixStack.Entry pose, VertexConsumer consumer, Vertex vertex, float nx, float ny, float nz, int packedLight, float frameOffset) {
        consumer.vertex(pose, vertex.getX(), vertex.getY(), vertex.getZ()).color(-1).texture(vertex.getU(), frameOffset + vertex.getV() * 0.0125f).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(pose, nx, ny, nz);
    }

    private final List<Face> faces() {
        List<Face> cached = faces;
        if (cached != null) {
            return cached;
        }
        faces = cached = this.load();
        return cached;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final List<Face> load() {
        try {
            Closeable closeable = this.reader(MODEL);
            Throwable throwable = null;
            try {
                BufferedReader reader = (BufferedReader)closeable;
                boolean bl = false;
                JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
                JsonObject resolution = root.getAsJsonObject("resolution");
                float textureWidth = resolution.get("width").getAsFloat();
                float textureHeight = resolution.get("height").getAsFloat();
                JsonArray elements = root.getAsJsonArray("elements");
                ArrayList result = new ArrayList(elements.size() * 6);
                Iterator iterator = elements.iterator();
                Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
                Iterator iterator2 = iterator;
                while (iterator2.hasNext()) {
                    JsonElement element = (JsonElement)iterator2.next();
                    JsonObject cube = element.getAsJsonObject();
                    JsonArray jsonArray = cube.getAsJsonArray("from");
                    Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
                    Vec from = INSTANCE.vec(jsonArray);
                    JsonArray jsonArray2 = cube.getAsJsonArray("to");
                    Intrinsics.checkNotNullExpressionValue((Object)jsonArray2, (String)"getAsJsonArray(...)");
                    Vec to = INSTANCE.vec(jsonArray2);
                    List list = result;
                    JsonObject jsonObject = cube.getAsJsonObject("faces");
                    Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                    INSTANCE.addCube(list, jsonObject, from, to, textureWidth, textureHeight);
                }
                int expectedFaces = elements.size() * 6;
                if (result.size() != expectedFaces) {
                    throw new IllegalStateException("Expected " + expectedFaces + " crown faces, got " + result.size());
                }
                List<Face> list = List.copyOf(result);
                Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
                List<Face> list2 = list;
                return list2;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
        }
        catch (Exception exception) {
            return CollectionsKt.emptyList();
        }
    }

    private final void addCube(List<Face> result, JsonObject faces, Vec from, Vec to, float textureWidth, float textureHeight) {
        float x0 = Math.min(from.getX(), to.getX());
        float y0 = Math.min(from.getY(), to.getY());
        float z0 = Math.min(from.getZ(), to.getZ());
        float x1 = Math.max(from.getX(), to.getX());
        float y1 = Math.max(from.getY(), to.getY());
        float z1 = Math.max(from.getZ(), to.getZ());
        Vec v000 = new Vec(x0, y0, z0);
        Vec v100 = new Vec(x1, y0, z0);
        Vec v110 = new Vec(x1, y1, z0);
        Vec v010 = new Vec(x0, y1, z0);
        Vec v001 = new Vec(x0, y0, z1);
        Vec v101 = new Vec(x1, y0, z1);
        Vec v111 = new Vec(x1, y1, z1);
        Vec v011 = new Vec(x0, y1, z1);
        this.addFace(result, faces, "down", v101, v001, v000, v100, 0.0f, -1.0f, 0.0f, textureWidth, textureHeight);
        this.addFace(result, faces, "up", v110, v010, v011, v111, 0.0f, 1.0f, 0.0f, textureWidth, textureHeight);
        this.addFace(result, faces, "west", v000, v001, v011, v010, -1.0f, 0.0f, 0.0f, textureWidth, textureHeight);
        this.addFace(result, faces, "north", v100, v000, v010, v110, 0.0f, 0.0f, -1.0f, textureWidth, textureHeight);
        this.addFace(result, faces, "east", v101, v100, v110, v111, 1.0f, 0.0f, 0.0f, textureWidth, textureHeight);
        this.addFace(result, faces, "south", v001, v101, v111, v011, 0.0f, 0.0f, 1.0f, textureWidth, textureHeight);
    }

    private final void addFace(List<Face> result, JsonObject faces, String name, Vec a, Vec b, Vec c, Vec d, float nx, float ny, float nz, float textureWidth, float textureHeight) {
        JsonObject jsonObject = faces.getAsJsonObject(name);
        if (jsonObject == null) {
            return;
        }
        JsonObject face = jsonObject;
        if (!face.has("texture")) {
            return;
        }
        JsonArray uv = face.getAsJsonArray("uv");
        float u0 = uv.get(0).getAsFloat() / textureWidth;
        float v0 = uv.get(1).getAsFloat() / textureHeight;
        float u1 = uv.get(2).getAsFloat() / textureWidth;
        float v1 = uv.get(3).getAsFloat() / textureHeight;
        result.add(new Face(new Vertex(a, u1, v0), new Vertex(b, u0, v0), new Vertex(c, u0, v1), new Vertex(d, u1, v1), nx, ny, nz));
    }

    private final Vec vec(JsonArray array) {
        return new Vec(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
    }

    private final BufferedReader reader(Identifier id) {
        try {
            Resource resource = (Resource)MinecraftClient.getInstance().getResourceManager().getResource(id).orElseThrow();
            return new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final void submit$lambda$0(int $packedLight, MatrixStack.Entry pose, VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        CrownRenderer.render(pose, consumer, $packedLight);
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/crown/royal_square_crown.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier2;
        RenderLayer renderLayer2 = RenderLayers.entitySolid((Identifier)TEXTURE);
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"entitySolid(...)");
        RENDER_TYPE = renderLayer2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/crown/royal_square_crown.bbmodel");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        MODEL = identifier3;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0013JV\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010 \u001a\u00020\u001fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b$\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b&\u0010\u000eR\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b(\u0010\u0013R\u0017\u0010\t\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b)\u0010\u0013R\u0017\u0010\n\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\n\u0010'\u001a\u0004\b*\u0010\u0013\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Face;", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;", "a", "b", "c", "d", "", "nx", "ny", "nz", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;FFF)V", "component1", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;", "component2", "component3", "component4", "component5", "()F", "component6", "component7", "copy", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;FFF)Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Face;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;", "getA", "getB", "getC", "getD", "F", "getNx", "getNy", "getNz", "rtx.kimiko:kimiko"})
    private static final class Face {
        @NotNull
        private final Vertex a;
        @NotNull
        private final Vertex b;
        @NotNull
        private final Vertex c;
        @NotNull
        private final Vertex d;
        private final float nx;
        private final float ny;
        private final float nz;

        public Face(@NotNull Vertex a, @NotNull Vertex b, @NotNull Vertex c, @NotNull Vertex d, float nx, float ny, float nz) {
            Intrinsics.checkNotNullParameter((Object)a, (String)"a");
            Intrinsics.checkNotNullParameter((Object)b, (String)"b");
            Intrinsics.checkNotNullParameter((Object)c, (String)"c");
            Intrinsics.checkNotNullParameter((Object)d, (String)"d");
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.nx = nx;
            this.ny = ny;
            this.nz = nz;
        }

        @NotNull
        public final Vertex getA() {
            return this.a;
        }

        @NotNull
        public final Vertex getB() {
            return this.b;
        }

        @NotNull
        public final Vertex getC() {
            return this.c;
        }

        @NotNull
        public final Vertex getD() {
            return this.d;
        }

        public final float getNx() {
            return this.nx;
        }

        public final float getNy() {
            return this.ny;
        }

        public final float getNz() {
            return this.nz;
        }

        @NotNull
        public final Vertex component1() {
            return this.a;
        }

        @NotNull
        public final Vertex component2() {
            return this.b;
        }

        @NotNull
        public final Vertex component3() {
            return this.c;
        }

        @NotNull
        public final Vertex component4() {
            return this.d;
        }

        public final float component5() {
            return this.nx;
        }

        public final float component6() {
            return this.ny;
        }

        public final float component7() {
            return this.nz;
        }

        @NotNull
        public final Face copy(@NotNull Vertex a, @NotNull Vertex b, @NotNull Vertex c, @NotNull Vertex d, float nx, float ny, float nz) {
            Intrinsics.checkNotNullParameter((Object)a, (String)"a");
            Intrinsics.checkNotNullParameter((Object)b, (String)"b");
            Intrinsics.checkNotNullParameter((Object)c, (String)"c");
            Intrinsics.checkNotNullParameter((Object)d, (String)"d");
            return new Face(a, b, c, d, nx, ny, nz);
        }

        public static /* synthetic */ Face copy$default(Face face, Vertex vertex, Vertex vertex2, Vertex vertex3, Vertex vertex4, float f, float f2, float f3, int n, Object object) {
            if ((n & 1) != 0) {
                vertex = face.a;
            }
            if ((n & 2) != 0) {
                vertex2 = face.b;
            }
            if ((n & 4) != 0) {
                vertex3 = face.c;
            }
            if ((n & 8) != 0) {
                vertex4 = face.d;
            }
            if ((n & 0x10) != 0) {
                f = face.nx;
            }
            if ((n & 0x20) != 0) {
                f2 = face.ny;
            }
            if ((n & 0x40) != 0) {
                f3 = face.nz;
            }
            return face.copy(vertex, vertex2, vertex3, vertex4, f, f2, f3);
        }

        @NotNull
        public String toString() {
            return "Face(a=" + this.a + ", b=" + this.b + ", c=" + this.c + ", d=" + this.d + ", nx=" + this.nx + ", ny=" + this.ny + ", nz=" + this.nz + ")";
        }

        public int hashCode() {
            int result = this.a.hashCode();
            result = result * 31 + this.b.hashCode();
            result = result * 31 + this.c.hashCode();
            result = result * 31 + this.d.hashCode();
            result = result * 31 + Float.hashCode(this.nx);
            result = result * 31 + Float.hashCode(this.ny);
            result = result * 31 + Float.hashCode(this.nz);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Face)) {
                return false;
            }
            Face face = (Face)other;
            if (!Intrinsics.areEqual((Object)this.a, (Object)face.a)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.b, (Object)face.b)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.c, (Object)face.c)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.d, (Object)face.d)) {
                return false;
            }
            if (Float.compare(this.nx, face.nx) != 0) {
                return false;
            }
            if (Float.compare(this.ny, face.ny) != 0) {
                return false;
            }
            return Float.compare(this.nz, face.nz) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\tJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\tJ.\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\t\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;", "", "", "x", "y", "z", "<init>", "(FFF)V", "component1", "()F", "component2", "component3", "copy", "(FFF)Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getZ", "rtx.kimiko:kimiko"})
    private static final class Vec {
        private final float x;
        private final float y;
        private final float z;

        public Vec(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final float getZ() {
            return this.z;
        }

        public final float component1() {
            return this.x;
        }

        public final float component2() {
            return this.y;
        }

        public final float component3() {
            return this.z;
        }

        @NotNull
        public final Vec copy(float x, float y, float z) {
            return new Vec(x, y, z);
        }

        public static /* synthetic */ Vec copy$default(Vec vec, float f, float f2, float f3, int n, Object object) {
            if ((n & 1) != 0) {
                f = vec.x;
            }
            if ((n & 2) != 0) {
                f2 = vec.y;
            }
            if ((n & 4) != 0) {
                f3 = vec.z;
            }
            return vec.copy(f, f2, f3);
        }

        @NotNull
        public String toString() {
            return "Vec(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.z);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Vec)) {
                return false;
            }
            Vec vec = (Vec)other;
            if (Float.compare(this.x, vec.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, vec.y) != 0) {
                return false;
            }
            return Float.compare(this.z, vec.z) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tB!\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000eJB\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b!\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b#\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b$\u0010\u000e\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;", "", "", "x", "y", "z", "u", "v", "<init>", "(FFFFF)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;", "position", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vec;FF)V", "component1", "()F", "component2", "component3", "component4", "component5", "copy", "(FFFFF)Lrtx/kimiko/api/modules/impl/Visuals/customization/CrownRenderer$Vertex;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getZ", "getU", "getV", "rtx.kimiko:kimiko"})
    private static final class Vertex {
        private final float x;
        private final float y;
        private final float z;
        private final float u;
        private final float v;

        public Vertex(float x, float y, float z, float u, float v) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.u = u;
            this.v = v;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final float getZ() {
            return this.z;
        }

        public final float getU() {
            return this.u;
        }

        public final float getV() {
            return this.v;
        }

        public Vertex(@NotNull Vec position, float u, float v) {
            this(position.getX(), position.getY(), position.getZ(), u, v);
        }

        public final float component1() {
            return this.x;
        }

        public final float component2() {
            return this.y;
        }

        public final float component3() {
            return this.z;
        }

        public final float component4() {
            return this.u;
        }

        public final float component5() {
            return this.v;
        }

        @NotNull
        public final Vertex copy(float x, float y, float z, float u, float v) {
            return new Vertex(x, y, z, u, v);
        }

        public static /* synthetic */ Vertex copy$default(Vertex vertex, float f, float f2, float f3, float f4, float f5, int n, Object object) {
            if ((n & 1) != 0) {
                f = vertex.x;
            }
            if ((n & 2) != 0) {
                f2 = vertex.y;
            }
            if ((n & 4) != 0) {
                f3 = vertex.z;
            }
            if ((n & 8) != 0) {
                f4 = vertex.u;
            }
            if ((n & 0x10) != 0) {
                f5 = vertex.v;
            }
            return vertex.copy(f, f2, f3, f4, f5);
        }

        @NotNull
        public String toString() {
            return "Vertex(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", u=" + this.u + ", v=" + this.v + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.z);
            result = result * 31 + Float.hashCode(this.u);
            result = result * 31 + Float.hashCode(this.v);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Vertex)) {
                return false;
            }
            Vertex vertex = (Vertex)other;
            if (Float.compare(this.x, vertex.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, vertex.y) != 0) {
                return false;
            }
            if (Float.compare(this.z, vertex.z) != 0) {
                return false;
            }
            if (Float.compare(this.u, vertex.u) != 0) {
                return false;
            }
            return Float.compare(this.v, vertex.v) == 0;
        }
    }
}

