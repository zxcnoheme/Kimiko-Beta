/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3fc
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals.customization;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3fc;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0017\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0006OPQRSTB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J3\u0010\u000f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J5\u0010\u0014\u001a\u00020\r2\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015JC\u0010!\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\"JY\u0010+\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%2\b\u0010(\u001a\u0004\u0018\u00010'2\u0006\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b+\u0010,J7\u0010/\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010$\u001a\u00020#2\u0006\u0010.\u001a\u00020-2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00103R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R \u0010:\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020'098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010?\u001a\u00020>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010A\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010DR\u0016\u0010F\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010DR\u0016\u0010G\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010DR\u0016\u0010H\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010DR\u0016\u0010I\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010DR\u0016\u0010J\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010DR\u0016\u0010K\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010DR\u0016\u0010L\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010DR\u0016\u0010M\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010DR\u0016\u0010N\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010D\u00a8\u0006U"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "loadModel", "Lcom/google/gson/JsonObject;", "boneObj", "", "channel", "Ljava/util/TreeMap;", "", "", "dest", "parseAnimVector", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/util/TreeMap;)V", "keyframes", "time", "defaultValue", "interpolate", "(Ljava/util/TreeMap;D[F)[F", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "submitNodeCollector", "", "lightCoords", "", "isFallFlying", "", "walkSpeed", "verticalSpeed", "render", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;IZFD)V", "Lnet/minecraft/VertexConsumer;", "buffer", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Bone;", "bone", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$AnimationData;", "activeAnim", "animTime", "vShift", "renderBone", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Bone;Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$AnimationData;DFIZF)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Cube;", "cube", "drawCube", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Cube;FI)V", "Lnet/minecraft/Identifier;", "GEOMETRY_ID", "Lnet/minecraft/Identifier;", "ANIMATIONS_ID", "TEXTURE", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Geometry;", "geometry", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Geometry;", "", "animations", "Ljava/util/Map;", "loaded", "Z", "", "lastTimeMs", "J", "phase", "D", "currentSpeed", "F", "currentAmp", "currentWaveDelay", "currentRootYaw", "currentMidYaw", "currentTipYaw", "currentRootPitch", "currentMidPitch", "currentTipPitch", "currentRootOffsetX", "currentRootOffsetZ", "Geometry", "Bone", "Cube", "FaceUV", "AnimationData", "BoneAnim", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nRoyalWingsRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoyalWingsRenderer.kt\nrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,529:1\n1#2:530\n*E\n"})
public final class RoyalWingsRenderer {
    @NotNull
    public static final RoyalWingsRenderer INSTANCE = new RoyalWingsRenderer();
    @NotNull
    private static final Identifier GEOMETRY_ID;
    @NotNull
    private static final Identifier ANIMATIONS_ID;
    @NotNull
    private static final Identifier TEXTURE;
    @NotNull
    private static final Geometry geometry;
    @NotNull
    private static final Map<String, AnimationData> animations;
    private static boolean loaded;
    private static long lastTimeMs;
    private static double phase;
    private static float currentSpeed;
    private static float currentAmp;
    private static float currentWaveDelay;
    private static float currentRootYaw;
    private static float currentMidYaw;
    private static float currentTipYaw;
    private static float currentRootPitch;
    private static float currentMidPitch;
    private static float currentTipPitch;
    private static float currentRootOffsetX;
    private static float currentRootOffsetZ;

    private RoyalWingsRenderer() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void loadModel() {
        if (loaded) {
            return;
        }
        try {
            Throwable throwable;
            Object object;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            Optional optional = mc.getResourceManager().getResource(GEOMETRY_ID);
            Intrinsics.checkNotNullExpressionValue((Object)optional, (String)"getResource(...)");
            Optional resGeo = optional;
            if (resGeo.isPresent()) {
                Closeable closeable = ((Resource)resGeo.get()).getInputStream();
                object = null;
                try {
                    InputStream stream = (InputStream)closeable;
                    boolean bl = false;
                    JsonObject json = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                    JsonArray geoms = json.getAsJsonArray("minecraft:geometry");
                    JsonObject geom = geoms.get(0).getAsJsonObject();
                    JsonArray bonesArray = geom.getAsJsonArray("bones");
                    HashMap<String, Bone> tempBones = new HashMap<>();
                    Iterator iterator = bonesArray.iterator();
                    Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
                    Iterator iterator2 = iterator;
                    while (iterator2.hasNext()) {
                        JsonElement bEl = (JsonElement)iterator2.next();
                        JsonObject bObj = bEl.getAsJsonObject();
                        Bone bone = new Bone();
                        bone.name = bObj.get("name").getAsString();
                        if (bObj.has("parent")) {
                            bone.parent = bObj.get("parent").getAsString();
                        }
                        JsonArray pivotArr = bObj.getAsJsonArray("pivot");
                        float[] fArray = new float[]{pivotArr.get(0).getAsFloat(), pivotArr.get(1).getAsFloat(), pivotArr.get(2).getAsFloat()};
                        bone.pivot = fArray;
                        if (bObj.has("cubes")) {
                            JsonArray cubesArr = bObj.getAsJsonArray("cubes");
                            for (JsonElement cEl : cubesArr) {
                                JsonObject cObj = cEl.getAsJsonObject();
                                Cube cube = new Cube();
                                JsonArray orgArr = cObj.getAsJsonArray("origin");
                                float[] fArray2 = new float[]{orgArr.get(0).getAsFloat(), orgArr.get(1).getAsFloat(), orgArr.get(2).getAsFloat()};
                                cube.origin = fArray2;
                                JsonArray szArr = cObj.getAsJsonArray("size");
                                float[] fArray3 = new float[]{szArr.get(0).getAsFloat(), szArr.get(1).getAsFloat(), szArr.get(2).getAsFloat()};
                                cube.size = fArray3;
                                if (cObj.has("pivot")) {
                                    JsonArray cp = cObj.getAsJsonArray("pivot");
                                    cube.pivot = new float[]{cp.get(0).getAsFloat(), cp.get(1).getAsFloat(), cp.get(2).getAsFloat()};
                                }
                                if (cObj.has("rotation")) {
                                    JsonArray cr = cObj.getAsJsonArray("rotation");
                                    cube.rotation = new float[]{cr.get(0).getAsFloat(), cr.get(1).getAsFloat(), cr.get(2).getAsFloat()};
                                }
                                if (cObj.has("uv")) {
                                    JsonObject uvObj = cObj.getAsJsonObject("uv");
                                    for (String faceName : uvObj.keySet()) {
                                        JsonObject faceJson = uvObj.getAsJsonObject(faceName);
                                        FaceUV fUV = new FaceUV();
                                        JsonArray uvArr = faceJson.getAsJsonArray("uv");
                                        fUV.uv = new float[]{uvArr.get(0).getAsFloat(), uvArr.get(1).getAsFloat()};
                                        JsonArray sizeArr = faceJson.getAsJsonArray("uv_size");
                                        fUV.uvSize = new float[]{sizeArr.get(0).getAsFloat(), sizeArr.get(1).getAsFloat()};
                                        cube.faces.put(faceName, fUV);
                                    }
                                }
                                bone.cubes.add(cube);
                            }
                        }
                        if (bone.name != null) {
                            tempBones.put(bone.name, bone);
                        }
                    }
                    for (Bone bone : tempBones.values()) {
                        if (bone.parent != null && tempBones.containsKey(bone.parent)) {
                            Bone parentBone = tempBones.get(bone.parent);
                            if (parentBone != null) {
                                parentBone.children.add(bone);
                            }
                        } else {
                            geometry.rootBones.add(bone);
                        }
                        if (bone.name != null) {
                            geometry.bones.put(bone.name, bone);
                        }
                    }
// throwable = Unit.INSTANCE;
                }
                catch (Throwable bl) {
                    object = bl;
                    throw bl;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)closeable, (Throwable)object);
                }
            }
            Optional optional2 = mc.getResourceManager().getResource(ANIMATIONS_ID);
            Intrinsics.checkNotNullExpressionValue((Object)optional2, (String)"getResource(...)");
            Optional resAnim = optional2;
            if (resAnim.isPresent()) {
                object = ((Resource)resAnim.get()).getInputStream();
                throwable = null;
                try {
                    InputStream stream = (InputStream)object;
                    boolean bl = false;
                    JsonObject json = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                    JsonObject animsObj = json.getAsJsonObject("animations");
                    for (String animName : animsObj.keySet()) {
                        JsonObject animObj = animsObj.getAsJsonObject(animName);
                        AnimationData anim = new AnimationData();
                        anim.name = animName;
                        anim.length = animObj.get("animation_length").getAsDouble();
                        anim.loop = animObj.has("loop") && animObj.get("loop").getAsBoolean();
                        JsonObject bonesObj = animObj.getAsJsonObject("bones");
                        for (String boneName : bonesObj.keySet()) {
                            JsonObject boneAnimObj = bonesObj.getAsJsonObject(boneName);
                            BoneAnim boneAnim = new BoneAnim();
                            Intrinsics.checkNotNull((Object)boneAnimObj);
                            INSTANCE.parseAnimVector(boneAnimObj, "position", boneAnim.position);
                            INSTANCE.parseAnimVector(boneAnimObj, "rotation", boneAnim.rotation);
                            INSTANCE.parseAnimVector(boneAnimObj, "scale", boneAnim.scale);
                            anim.boneAnims.put(boneName, boneAnim);
                        }
                        animations.put(animName, anim);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)object, (Throwable)throwable);
                }
            }
            loaded = true;
        }
        catch (Exception e) {
            loaded = true;
        }
    }

    private final void parseAnimVector(JsonObject boneObj, String channel, TreeMap<Double, float[]> dest) {
        if (!boneObj.has(channel)) {
            return;
        }
        JsonElement el = boneObj.get(channel);
        if (el.isJsonArray()) {
            JsonArray arr = el.getAsJsonArray();
            float[] fArray = new float[]{arr.get(0).getAsFloat(), arr.get(1).getAsFloat(), arr.get(2).getAsFloat()};
            float[] value = fArray;
            ((Map)dest).put(0.0, value);
        } else if (el.isJsonObject()) {
            JsonObject obj = el.getAsJsonObject();
            for (String key : obj.keySet()) {
                try {
                    Intrinsics.checkNotNull((Object)key);
                    double t = Double.parseDouble(key);
                    JsonElement valEl = obj.get(key);
                    if (!valEl.isJsonArray()) continue;
                    JsonArray arr = valEl.getAsJsonArray();
                    float[] fArray = new float[]{arr.get(0).getAsFloat(), arr.get(1).getAsFloat(), arr.get(2).getAsFloat()};
                    float[] value = fArray;
                    ((Map)dest).put(t, value);
                }
                catch (NumberFormatException numberFormatException) {}
            }
        }
    }

    private final float[] interpolate(TreeMap<Double, float[]> keyframes, double time, float[] defaultValue) {
        if (keyframes == null || keyframes.isEmpty()) {
            return defaultValue;
        }
        Double t1 = keyframes.floorKey(time);
        Double t2 = keyframes.ceilingKey(time);
        if (t1 == null && t2 == null) {
            return defaultValue;
        }
        if (t1 == null) {
            float[] fArray = keyframes.get(t2);
            Intrinsics.checkNotNull((Object)fArray);
            return fArray;
        }
        if (t2 == null) {
            float[] fArray = keyframes.get(t1);
            Intrinsics.checkNotNull((Object)fArray);
            return fArray;
        }
        if (t1.doubleValue() == t2.doubleValue()) {
            float[] fArray = keyframes.get(t1);
            Intrinsics.checkNotNull((Object)fArray);
            return fArray;
        }
        float[] fArray = keyframes.get(t1);
        Intrinsics.checkNotNull((Object)fArray);
        float[] v1 = fArray;
        float[] fArray2 = keyframes.get(t2);
        Intrinsics.checkNotNull((Object)fArray2);
        float[] v2 = fArray2;
        float pct = (float)((time - t1) / (t2 - t1));
        float[] res = new float[]{v1[0] + (v2[0] - v1[0]) * pct, v1[1] + (v2[1] - v1[1]) * pct, v1[2] + (v2[2] - v1[2]) * pct};
        return res;
    }

    @JvmStatic
    public static final void render(@NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue submitNodeCollector, int lightCoords, boolean isFallFlying, float walkSpeed, double verticalSpeed) {
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)submitNodeCollector, (String)"submitNodeCollector");
        RoyalWingsRenderer.loadModel();
        if (RoyalWingsRenderer.geometry.rootBones.isEmpty()) {
            return;
        }
        String animName = isFallFlying ? "animation.royal_mechanical_wings.fly" : (walkSpeed > 0.05f ? "animation.royal_mechanical_wings.run" : "animation.royal_mechanical_wings.idle");
        AnimationData activeAnim = animations.get(animName);
        double animTime = 0.0;
        if (activeAnim != null && activeAnim.length > 0.0) {
            animTime = (double)(System.currentTimeMillis() % (long)(activeAnim.length * 1000.0)) / 1000.0;
        }
        long timeMs = System.currentTimeMillis();
        int frameIndex = (int)(timeMs / 50L % 80L);
        float vShift = (float)frameIndex * 64.0f;
        long now = System.currentTimeMillis();
        if (lastTimeMs == 0L) {
            lastTimeMs = now;
        }
        float dt = (float)(now - lastTimeMs) / 1000.0f;
        lastTimeMs = now;
        if (dt > 0.1f) {
            dt = 0.1f;
        }
        float targetSpeed = 0.0f;
        float targetAmp = 0.0f;
        float targetWaveDelay = 0.0f;
        float targetRootYaw = 0.0f;
        float targetMidYaw = 0.0f;
        float targetTipYaw = 0.0f;
        float targetRootPitch = 0.0f;
        float targetMidPitch = 0.0f;
        float targetTipPitch = 0.0f;
        float targetRootOffsetX = 0.0f;
        float targetRootOffsetZ = 0.0f;
        if (isFallFlying) {
            targetRootOffsetX = 1.6f;
            targetRootOffsetZ = -1.0f;
            if (verticalSpeed > 0.02) {
                targetSpeed = 2.8f;
                targetAmp = 30.0f;
                targetWaveDelay = 0.5f;
                targetRootPitch = 15.0f;
            } else if (verticalSpeed < -0.28) {
                targetSpeed = 1.8f;
                targetAmp = 3.0f;
                targetWaveDelay = 0.2f;
                targetRootYaw = -25.0f;
                targetMidYaw = -38.0f;
                targetTipYaw = -20.0f;
                targetRootPitch = -10.0f;
            } else {
                targetSpeed = 2.2f;
                targetAmp = 0.0f;
                targetWaveDelay = 0.35f;
                double glidePhase = phase % 7.0;
                if (glidePhase < 2.0) {
                    float it = targetSpeed;
                    boolean bl = false;
                }
                float localAmp = 0.0f;
                float localSpeed = 2.2f;
                if (glidePhase < 2.0) {
                    localAmp = 18.0f;
                    localSpeed = 2.4f;
                }
                targetRootYaw = -10.0f;
                targetMidYaw = -15.0f;
            }
        } else if (walkSpeed > 0.05f) {
            targetSpeed = 5.5f;
            targetAmp = 14.0f;
            targetWaveDelay = 0.35f;
        } else {
            targetSpeed = 2.0f;
            targetAmp = 7.0f;
            targetWaveDelay = 0.3f;
        }
        currentSpeed += (targetSpeed - currentSpeed) * dt * 3.5f;
        currentAmp += (targetAmp - currentAmp) * dt * 3.5f;
        currentWaveDelay += (targetWaveDelay - currentWaveDelay) * dt * 3.5f;
        currentRootYaw += (targetRootYaw - currentRootYaw) * dt * 3.0f;
        currentMidYaw += (targetMidYaw - currentMidYaw) * dt * 3.0f;
        currentTipYaw += (targetTipYaw - currentTipYaw) * dt * 3.0f;
        currentRootPitch += (targetRootPitch - currentRootPitch) * dt * 3.0f;
        currentMidPitch += (targetMidPitch - currentMidPitch) * dt * 3.0f;
        currentTipPitch += (targetTipPitch - currentTipPitch) * dt * 3.0f;
        currentRootOffsetX += (targetRootOffsetX - currentRootOffsetX) * dt * 3.0f;
        currentRootOffsetZ += (targetRootOffsetZ - currentRootOffsetZ) * dt * 3.0f;
        phase += (double)(currentSpeed * dt);
        double finalAnimTime = animTime;
        submitNodeCollector.submitCustom(poseStack, RenderLayers.entityCutout((Identifier)TEXTURE), (arg_0, arg_1) -> RoyalWingsRenderer.render$lambda$1(activeAnim, finalAnimTime, vShift, lightCoords, isFallFlying, walkSpeed, arg_0, arg_1));
    }

    private final void renderBone(MatrixStack poseStack, VertexConsumer buffer, Bone bone, AnimationData activeAnim, double animTime, float vShift, int lightCoords, boolean isFallFlying, float walkSpeed) {
        float[] animRot;
        float[] animPos;
        float pz;
        float py;
        float px;
        block9: {
            block12: {
                block11: {
                    float yawRad;
                    float pitch;
                    float yaw;
                    float roll;
                    float windShake;
                    float sideSign;
                    block10: {
                        block8: {
                            poseStack.push();
                            Intrinsics.checkNotNull((Object)bone.pivot);
                            px = bone.pivot[0];
                            Intrinsics.checkNotNull((Object)bone.pivot);
                            py = bone.pivot[1];
                            Intrinsics.checkNotNull((Object)bone.pivot);
                            pz = bone.pivot[2];
                            poseStack.translate(px, py, pz);
                            BoneAnim boneAnim = activeAnim != null && activeAnim.boneAnims != null ? activeAnim.boneAnims.get(bone.name) : null;
                            BoneAnim boneAnim2 = boneAnim;
                            float[] fArray = new float[]{0.0f, 0.0f, 0.0f};
                            animPos = this.interpolate(boneAnim2 != null ? boneAnim2.position : null, animTime, fArray);
                            BoneAnim boneAnim3 = boneAnim;
                            float[] fArray2 = new float[]{0.0f, 0.0f, 0.0f};
                            animRot = this.interpolate(boneAnim3 != null ? boneAnim3.rotation : null, animTime, fArray2);
                            if (Intrinsics.areEqual((Object)"wings_root", (Object)bone.name) || Intrinsics.areEqual((Object)"bb_main", (Object)bone.name)) {
                                animPos[0] = 0.0f;
                                animPos[1] = 0.0f;
                                animPos[2] = 0.0f;
                            }
                            String string = bone.name;
                            Intrinsics.checkNotNull((Object)string);
                            boolean isLeft = String.valueOf(string).startsWith("left_");
                            sideSign = isLeft ? 1.0f : -1.0f;
                            windShake = 0.0f;
                            if (isFallFlying && currentAmp < 1.0f) {
                                windShake = (float)(Math.sin(phase * 2.5) * 1.5);
                            }
                            String string2 = bone.name;
                            Intrinsics.checkNotNull((Object)string2);
                            if (!String.valueOf(string2).endsWith("_wing_root")) break block8;
                            animPos[0] = sideSign * currentRootOffsetX;
                            animPos[1] = 0.0f;
                            animPos[2] = currentRootOffsetZ;
                            roll = (float)(Math.sin(phase) * (double)currentAmp) + windShake;
                            yaw = (float)(Math.cos(phase) * (double)(currentAmp * 0.45f));
                            pitch = (float)(Math.sin(phase + 0.3) * (double)(currentAmp * 0.25f));
                            animRot[2] = sideSign * roll;
                            animRot[1] = sideSign * (currentRootYaw + yaw);
                            animRot[0] = currentRootPitch + pitch;
                            break block9;
                        }
                        String string = bone.name;
                        Intrinsics.checkNotNull((Object)string);
                        if (!String.valueOf(string).endsWith("_wing_mid")) break block10;
                        roll = (float)(Math.sin(phase - (double)currentWaveDelay) * (double)currentAmp * (double)0.85f) + windShake * 0.8f;
                        yaw = (float)(Math.cos(phase - (double)currentWaveDelay) * (double)(currentAmp * 0.6f));
                        pitch = (float)(Math.sin(phase - (double)currentWaveDelay + 0.2) * (double)(currentAmp * 0.2f));
                        animRot[2] = sideSign * roll;
                        animRot[1] = sideSign * (currentMidYaw + yaw);
                        animRot[0] = currentMidPitch + pitch;
                        yawRad = (float)Math.toRadians(animRot[1]);
                        animPos[0] = sideSign * (Math.abs(yawRad) * 2.2f);
                        animPos[1] = 0.0f;
                        animPos[2] = -(Math.abs(yawRad) * 1.5f);
                        break block9;
                    }
                    String string = bone.name;
                    Intrinsics.checkNotNull((Object)string);
                    if (!String.valueOf(string).endsWith("_wing_tip")) break block11;
                    roll = (float)(Math.sin(phase - (double)(2.0f * currentWaveDelay)) * (double)currentAmp * (double)1.2f) + windShake * 0.6f;
                    yaw = (float)(Math.cos(phase - (double)(2.0f * currentWaveDelay)) * (double)(currentAmp * 0.8f));
                    pitch = (float)(Math.sin(phase - (double)(2.0f * currentWaveDelay) + 0.4) * (double)(currentAmp * 0.35f));
                    animRot[2] = sideSign * roll;
                    animRot[1] = sideSign * (currentTipYaw + yaw);
                    animRot[0] = currentTipPitch + pitch;
                    yawRad = (float)Math.toRadians(animRot[1]);
                    animPos[0] = sideSign * (Math.abs(yawRad) * 2.4f);
                    animPos[1] = 0.0f;
                    animPos[2] = -(Math.abs(yawRad) * 1.5f);
                    break block9;
                }
                String string = bone.name;
                Intrinsics.checkNotNull((Object)string);
                if (String.valueOf(string).contains("crystals_")) break block12;
                String string3 = bone.name;
                Intrinsics.checkNotNull((Object)string3);
                if (!String.valueOf(string3).contains("feathers")) break block9;
            }
            animPos[0] = 0.0f;
            animPos[1] = 0.0f;
            animPos[2] = 0.0f;
            animRot[0] = 0.0f;
            animRot[1] = 0.0f;
            animRot[2] = 0.0f;
        }
        poseStack.translate(animPos[0], animPos[1], animPos[2]);
        poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(animRot[2]));
        poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(animRot[0]));
        poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(animRot[1]));
        poseStack.translate(-px, -py, -pz);
        for (Cube cube : bone.cubes) {
            poseStack.push();
            if (cube.pivot != null && cube.rotation != null) {
                Intrinsics.checkNotNull((Object)cube.pivot);
                float cpx = cube.pivot[0];
                Intrinsics.checkNotNull((Object)cube.pivot);
                float cpy = cube.pivot[1];
                Intrinsics.checkNotNull((Object)cube.pivot);
                float cpz = cube.pivot[2];
                poseStack.translate(cpx, cpy, cpz);
                Intrinsics.checkNotNull((Object)cube.rotation);
                poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(cube.rotation[2]));
                Intrinsics.checkNotNull((Object)cube.rotation);
                poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(cube.rotation[0]));
                Intrinsics.checkNotNull((Object)cube.rotation);
                poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(cube.rotation[1]));
                poseStack.translate(-cpx, -cpy, -cpz);
            }
            this.drawCube(poseStack, buffer, cube, vShift, lightCoords);
            poseStack.pop();
        }
        for (Bone child : bone.children) {
            this.renderBone(poseStack, buffer, child, activeAnim, animTime, vShift, lightCoords, isFallFlying, walkSpeed);
        }
        poseStack.pop();
    }

    private final void drawCube(MatrixStack poseStack, VertexConsumer buffer, Cube cube, float vShift, int lightCoords) {
        Intrinsics.checkNotNull((Object)cube.origin);
        float x1 = cube.origin[0];
        Intrinsics.checkNotNull((Object)cube.origin);
        float y1 = cube.origin[1];
        Intrinsics.checkNotNull((Object)cube.origin);
        float z1 = cube.origin[2];
        Intrinsics.checkNotNull((Object)cube.size);
        float x2 = x1 + cube.size[0];
        Intrinsics.checkNotNull((Object)cube.size);
        float y2 = y1 + cube.size[1];
        Intrinsics.checkNotNull((Object)cube.size);
        float z2 = z1 + cube.size[2];
        MatrixStack.Entry entry2 = poseStack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        for (Map.Entry<String, FaceUV> entry : cube.faces.entrySet()) {
            String faceName = entry.getKey();
            FaceUV uv = entry.getValue();
            Intrinsics.checkNotNull((Object)uv.uv);
            float u1 = uv.uv[0] / 64.0f;
            Intrinsics.checkNotNull((Object)uv.uv);
            float v1 = (uv.uv[1] + vShift) / 5120.0f;
            Intrinsics.checkNotNull((Object)uv.uv);
            float f = uv.uv[0];
            Intrinsics.checkNotNull((Object)uv.uvSize);
            float u2 = (f + uv.uvSize[0]) / 64.0f;
            Intrinsics.checkNotNull((Object)uv.uv);
            float f2 = uv.uv[1];
            Intrinsics.checkNotNull((Object)uv.uvSize);
            float v2 = (f2 + uv.uvSize[1] + vShift) / 5120.0f;
            switch (faceName) {
                case "north": {
                    buffer.vertex(pose, x2, y2, z1).color(-1).texture(u2, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, -1.0f);
                    buffer.vertex(pose, x1, y2, z1).color(-1).texture(u1, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, -1.0f);
                    buffer.vertex(pose, x1, y1, z1).color(-1).texture(u1, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, -1.0f);
                    buffer.vertex(pose, x2, y1, z1).color(-1).texture(u2, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, -1.0f);
                    break;
                }
                case "south": {
                    buffer.vertex(pose, x1, y2, z2).color(-1).texture(u2, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, 1.0f);
                    buffer.vertex(pose, x2, y2, z2).color(-1).texture(u1, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, 1.0f);
                    buffer.vertex(pose, x2, y1, z2).color(-1).texture(u1, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, 1.0f);
                    buffer.vertex(pose, x1, y1, z2).color(-1).texture(u2, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 0.0f, 1.0f);
                    break;
                }
                case "east": {
                    buffer.vertex(pose, x1, y2, z1).color(-1).texture(u2, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, -1.0f, 0.0f, 0.0f);
                    buffer.vertex(pose, x1, y2, z2).color(-1).texture(u1, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, -1.0f, 0.0f, 0.0f);
                    buffer.vertex(pose, x1, y1, z2).color(-1).texture(u1, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, -1.0f, 0.0f, 0.0f);
                    buffer.vertex(pose, x1, y1, z1).color(-1).texture(u2, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, -1.0f, 0.0f, 0.0f);
                    break;
                }
                case "west": {
                    buffer.vertex(pose, x2, y2, z2).color(-1).texture(u2, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 1.0f, 0.0f, 0.0f);
                    buffer.vertex(pose, x2, y2, z1).color(-1).texture(u1, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 1.0f, 0.0f, 0.0f);
                    buffer.vertex(pose, x2, y1, z1).color(-1).texture(u1, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 1.0f, 0.0f, 0.0f);
                    buffer.vertex(pose, x2, y1, z2).color(-1).texture(u2, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 1.0f, 0.0f, 0.0f);
                    break;
                }
                case "up": {
                    buffer.vertex(pose, x2, y2, z2).color(-1).texture(u2, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 1.0f, 0.0f);
                    buffer.vertex(pose, x1, y2, z2).color(-1).texture(u1, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 1.0f, 0.0f);
                    buffer.vertex(pose, x1, y2, z1).color(-1).texture(u1, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 1.0f, 0.0f);
                    buffer.vertex(pose, x2, y2, z1).color(-1).texture(u2, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case "down": {
                    buffer.vertex(pose, x2, y1, z1).color(-1).texture(u2, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, -1.0f, 0.0f);
                    buffer.vertex(pose, x1, y1, z1).color(-1).texture(u1, v1).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, -1.0f, 0.0f);
                    buffer.vertex(pose, x1, y1, z2).color(-1).texture(u1, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, -1.0f, 0.0f);
                    buffer.vertex(pose, x2, y1, z2).color(-1).texture(u2, v2).overlay(OverlayTexture.DEFAULT_UV).light(lightCoords).normal(pose, 0.0f, -1.0f, 0.0f);
                }
            }
        }
    }

    private static final void render$lambda$1(AnimationData $activeAnim, double $finalAnimTime, float $vShift, int $lightCoords, boolean $isFallFlying, float $walkSpeed, MatrixStack.Entry pose, VertexConsumer buffer) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)buffer, (String)"buffer");
        MatrixStack localStack = new MatrixStack();
        localStack.peek().getPositionMatrix().set((Matrix4fc)pose.getPositionMatrix());
        localStack.peek().getNormalMatrix().set((Matrix3fc)pose.getNormalMatrix());
        localStack.scale(0.0625f, -0.0625f, 0.0625f);
        localStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        float xOffset = 0.0f;
        float yOffset = -17.0f;
        float zOffset = -4.3f;
        localStack.translate(xOffset, yOffset, zOffset);
        float scaleFactor = 0.74f;
        localStack.scale(scaleFactor, scaleFactor, scaleFactor);
        for (Bone root : RoyalWingsRenderer.geometry.rootBones) {
            INSTANCE.renderBone(localStack, buffer, root, $activeAnim, $finalAnimTime, $vShift, $lightCoords, $isFallFlying, $walkSpeed);
        }
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/wings/royal_mechanical_wings.geo.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        GEOMETRY_ID = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/wings/royal_mechanical_wings.animation.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        ANIMATIONS_ID = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/wings/royal_mechanical_wings.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier4;
        geometry = new Geometry();
        animations = new HashMap();
        currentSpeed = 2.0f;
        currentAmp = 7.0f;
        currentWaveDelay = 0.3f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001b\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u001b\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR'\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$AnimationData;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "name", "Ljava/lang/String;", "", "length", "D", "", "loop", "Z", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$BoneAnim;", "boneAnims", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
    public static final class AnimationData {
        @JvmField
        @Nullable
        public String name;
        @JvmField
        public double length;
        @JvmField
        public boolean loop;
        @JvmField
        @NotNull
        public Map<String, BoneAnim> boneAnims = new HashMap();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007R\u001d\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000f\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Bone;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "name", "Ljava/lang/String;", "parent", "", "pivot", "[F", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Cube;", "cubes", "Ljava/util/List;", "children", "rtx.kimiko:kimiko"})
    public static final class Bone {
        @JvmField
        @Nullable
        public String name;
        @JvmField
        @Nullable
        public String parent;
        @JvmField
        @Nullable
        public float[] pivot;
        @JvmField
        @NotNull
        public List<Cube> cubes = new ArrayList();
        @JvmField
        @NotNull
        public List<Bone> children = new ArrayList();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R'\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\t\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$BoneAnim;", "", "<init>", "()V", "Ljava/util/TreeMap;", "", "", "Lkotlin/jvm/JvmField;", "position", "Ljava/util/TreeMap;", "rotation", "scale", "rtx.kimiko:kimiko"})
    public static final class BoneAnim {
        @JvmField
        @NotNull
        public TreeMap<Double, float[]> position = new TreeMap();
        @JvmField
        @NotNull
        public TreeMap<Double, float[]> rotation = new TreeMap();
        @JvmField
        @NotNull
        public TreeMap<Double, float[]> scale = new TreeMap();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007R\u001d\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0007R\u001d\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0007R'\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Cube;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "origin", "[F", "size", "pivot", "rotation", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$FaceUV;", "faces", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
    public static final class Cube {
        @JvmField
        @Nullable
        public float[] origin;
        @JvmField
        @Nullable
        public float[] size;
        @JvmField
        @Nullable
        public float[] pivot;
        @JvmField
        @Nullable
        public float[] rotation;
        @JvmField
        @NotNull
        public Map<String, FaceUV> faces = new HashMap();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$FaceUV;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "uv", "[F", "uvSize", "rtx.kimiko:kimiko"})
    public static final class FaceUV {
        @JvmField
        @Nullable
        public float[] uv;
        @JvmField
        @Nullable
        public float[] uvSize;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R'\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\n8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Geometry;", "", "<init>", "()V", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/RoyalWingsRenderer$Bone;", "Lkotlin/jvm/JvmField;", "bones", "Ljava/util/Map;", "", "rootBones", "Ljava/util/List;", "rtx.kimiko:kimiko"})
    public static final class Geometry {
        @JvmField
        @NotNull
        public Map<String, Bone> bones = new HashMap();
        @JvmField
        @NotNull
        public List<Bone> rootBones = new ArrayList();
    }
}

