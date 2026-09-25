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
 *  kotlin.jvm.functions.Function1
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
 *  net.minecraft.client.network.AbstractClientPlayerEntity
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
import java.util.WeakHashMap;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
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
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3fc;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0007defghijB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J5\u0010\n\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u000e\u001a\u00020\fH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u0003J3\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015JK\u0010#\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 H\u0007b\u0002\b\r\u00a2\u0006\u0004\b#\u0010$J)\u0010*\u001a\u00020\u00052\b\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'H\u0002\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020 2\u0006\u0010,\u001a\u00020 H\u0002\u00a2\u0006\u0004\b-\u0010.J'\u00102\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020 H\u0002\u00a2\u0006\u0004\b2\u00103J{\u0010B\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u00105\u001a\u0002042\u0006\u00107\u001a\u0002062\b\u00108\u001a\u0004\u0018\u00010%2\u0006\u00109\u001a\u00020\u00052\b\u0010:\u001a\u0004\u0018\u00010%2\u0006\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020 2\u000e\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0=2\u0006\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\bB\u0010CJ/\u0010F\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u00105\u001a\u0002042\u0006\u0010E\u001a\u00020D2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010JR\u0014\u0010L\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010JR\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR \u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020%0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010RR \u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020S0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010RR\u0014\u0010U\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010VR\u0014\u0010X\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010VR\u0014\u0010Y\u001a\u00020 8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0014\u0010[\u001a\u00020\u00058\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010]\u001a\u00020\u00058\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010\\R\u0014\u0010^\u001a\u00020\u00058\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b^\u0010\\R\u001a\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00110=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00110=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010`R\u0016\u0010b\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010c\u00a8\u0006k"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer;", "", "<init>", "()V", "Ljava/util/TreeMap;", "", "", "keyframes", "time", "defaultValue", "interpolate", "(Ljava/util/TreeMap;D[F)[F", "", "Lkotlin/jvm/JvmStatic;", "loadModel", "Lcom/google/gson/JsonObject;", "boneObj", "", "channel", "dest", "parseAnimVector", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/util/TreeMap;)V", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "submitNodeCollector", "", "lightCoords", "", "isFlying", "", "walkSpeed", "attackTime", "render", "(Lnet/minecraft/AbstractClientPlayerEntity;Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;IZFF)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$AnimationData;", "animation", "", "now", "startedAt", "animationTime", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$AnimationData;JJ)D", "value", "smoothstep", "(F)F", "from", "to", "progress", "blend", "([F[FF)[F", "Lnet/minecraft/VertexConsumer;", "buffer", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Bone;", "bone", "baseAnimation", "baseTime", "previousBaseAnimation", "previousBaseTime", "baseBlend", "", "attackAnimations", "", "attackTimes", "attackBlends", "renderBone", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Bone;Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$AnimationData;DLrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$AnimationData;DF[Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$AnimationData;[D[FI)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Cube;", "cube", "drawCube", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Cube;I)V", "Lnet/minecraft/Identifier;", "GEOMETRY_ID", "Lnet/minecraft/Identifier;", "ANIMATIONS_ID", "TEXTURE", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Geometry;", "geometry", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Geometry;", "", "animations", "Ljava/util/Map;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$PlaybackState;", "playbackStates", "IDLE", "Ljava/lang/String;", "RUN", "FLY", "MODEL_SCALE", "F", "BASE_BLEND_SECONDS", "D", "ATTACK_BLEND_IN_SECONDS", "ATTACK_BLEND_OUT_SECONDS", "ATTACKS", "[Ljava/lang/String;", "ATTACK_BONE_PREFIXES", "loaded", "Z", "PlaybackState", "Geometry", "Bone", "Cube", "FaceUV", "AnimationData", "BoneAnim", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nKanekiKaguneRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KanekiKaguneRenderer.kt\nrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,509:1\n1#2:510\n*E\n"})
public final class KanekiKaguneRenderer {
    @NotNull
    public static final KanekiKaguneRenderer INSTANCE = new KanekiKaguneRenderer();
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
    @NotNull
    private static final Map<AbstractClientPlayerEntity, PlaybackState> playbackStates;
    @NotNull
    private static final String IDLE = "animation.kaneki_kagune.idle";
    @NotNull
    private static final String RUN = "animation.kaneki_kagune.run";
    @NotNull
    private static final String FLY = "animation.kaneki_kagune.fly";
    private static final float MODEL_SCALE = 1.65f;
    private static final double BASE_BLEND_SECONDS = 0.18;
    private static final double ATTACK_BLEND_IN_SECONDS = 0.08;
    private static final double ATTACK_BLEND_OUT_SECONDS = 0.14;
    @NotNull
    private static final String[] ATTACKS;
    @NotNull
    private static final String[] ATTACK_BONE_PREFIXES;
    private static boolean loaded;

    private KanekiKaguneRenderer() {
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

    @JvmStatic
    public static final void render(@NotNull AbstractClientPlayerEntity player, @NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue submitNodeCollector, int lightCoords, boolean isFlying, float walkSpeed, float attackTime) {
        AnimationData animationData;
        String desiredBaseAnimation;
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)submitNodeCollector, (String)"submitNodeCollector");
        KanekiKaguneRenderer.loadModel();
        if (KanekiKaguneRenderer.geometry.rootBones.isEmpty()) {
            return;
        }
        long now = System.nanoTime();
        PlaybackState state = playbackStates.computeIfAbsent(player, it -> new PlaybackState());
        desiredBaseAnimation = isFlying ? FLY : (walkSpeed > 0.05f ? RUN : IDLE);
        if (!Intrinsics.areEqual((Object)desiredBaseAnimation, (Object)state.getBaseAnimation())) {
            state.setPreviousBaseAnimation(state.getBaseAnimation());
            state.setPreviousBaseStartedAt(state.getBaseStartedAt());
            state.setBaseAnimation(desiredBaseAnimation);
            state.setBaseStartedAt(now);
            state.setBaseTransitionStartedAt(now);
        }
        boolean attackStarted = attackTime > 0.001f && (state.getPreviousAttackTime() <= 0.001f || attackTime + 0.2f < state.getPreviousAttackTime());
        state.setPreviousAttackTime(attackTime);
        int[] availableAttacks = new int[ATTACKS.length];
        int availableAttackCount = 0;
        int n = ATTACKS.length;
        for (int i = 0; i < n; ++i) {
            AnimationData animation = animations.get(ATTACKS[i]);
            if (state.getAttackStartedAt()[i] != 0L && (animation == null || (double)(now - state.getAttackStartedAt()[i]) / 1.0E9 >= animation.length)) {
                state.getAttackStartedAt()[i] = 0L;
            }
            if (state.getAttackStartedAt()[i] != 0L) continue;
            availableAttacks[availableAttackCount++] = i;
        }
        if (attackStarted && availableAttackCount > 0) {
            int slot = availableAttacks[ThreadLocalRandom.current().nextInt(availableAttackCount)];
            state.getAttackStartedAt()[slot] = now;
        }
        AnimationData baseAnimation = animations.get(state.getBaseAnimation());
        double baseTime = INSTANCE.animationTime(baseAnimation, now, state.getBaseStartedAt());
        String string2 = state.getPreviousBaseAnimation();
        if (string2 != null) {
            String it = string2;
            boolean bl = false;
            animationData = animations.get(it);
        } else {
            animationData = null;
        }
        AnimationData previousBaseAnimation = animationData;
        double previousBaseTime = INSTANCE.animationTime(previousBaseAnimation, now, state.getPreviousBaseStartedAt());
        float baseBlend = 1.0f;
        if (previousBaseAnimation != null && (baseBlend = INSTANCE.smoothstep((float)Math.min(1.0, (double)(now - state.getBaseTransitionStartedAt()) / 1.0E9 / 0.18))) >= 1.0f) {
            state.setPreviousBaseAnimation(null);
            previousBaseAnimation = null;
        }
        AnimationData[] attackAnimations = new AnimationData[ATTACKS.length];
        double[] activeAttackTimes = new double[ATTACKS.length];
        float[] attackBlends = new float[ATTACKS.length];
        int n2 = ATTACKS.length;
        for (int i = 0; i < n2; ++i) {
            AnimationData animation;
            if (state.getAttackStartedAt()[i] == 0L || (animation = animations.get(ATTACKS[i])) == null) continue;
            double elapsed = (double)(now - state.getAttackStartedAt()[i]) / 1.0E9;
            double fadeIn = Math.min(1.0, elapsed / 0.08);
            double fadeOut = Math.min(1.0, (animation.length - elapsed) / 0.14);
            attackAnimations[i] = animation;
            activeAttackTimes[i] = INSTANCE.animationTime(animation, now, state.getAttackStartedAt()[i]);
            attackBlends[i] = INSTANCE.smoothstep((float)Math.min(fadeIn, fadeOut));
        }
        AnimationData finalBaseAnimation = baseAnimation;
        AnimationData finalPreviousBaseAnimation = previousBaseAnimation;
        double finalBaseTime = baseTime;
        double finalPreviousBaseTime = previousBaseTime;
        float finalBaseBlend = baseBlend;
        submitNodeCollector.submitCustom(poseStack, RenderLayers.entityCutout((Identifier)TEXTURE), (arg_0, arg_1) -> KanekiKaguneRenderer.render$lambda$3(finalBaseAnimation, finalBaseTime, finalPreviousBaseAnimation, finalPreviousBaseTime, finalBaseBlend, attackAnimations, activeAttackTimes, attackBlends, lightCoords, arg_0, arg_1));
    }

    private final double animationTime(AnimationData animation, long now, long startedAt) {
        if (animation == null || animation.length <= 0.0) {
            return 0.0;
        }
        double elapsed = Math.max(0.0, (double)(now - startedAt) / 1.0E9);
        return animation.loop ? elapsed % animation.length : Math.min(elapsed, animation.length);
    }

    private final float smoothstep(float value) {
        float clamped = Math.max(0.0f, Math.min(1.0f, value));
        return clamped * clamped * (3.0f - 2.0f * clamped);
    }

    private final float[] blend(float[] from, float[] to, float progress) {
        float[] fArray = new float[]{from[0] + (to[0] - from[0]) * progress, from[1] + (to[1] - from[1]) * progress, from[2] + (to[2] - from[2]) * progress};
        return fArray;
    }

    private final void renderBone(MatrixStack poseStack, VertexConsumer buffer, Bone bone, AnimationData baseAnimation, double baseTime, AnimationData previousBaseAnimation, double previousBaseTime, float baseBlend, AnimationData[] attackAnimations, double[] attackTimes, float[] attackBlends, int lightCoords) {
        poseStack.push();
        Intrinsics.checkNotNull((Object)bone.pivot);
        float px = bone.pivot[0];
        Intrinsics.checkNotNull((Object)bone.pivot);
        float py = bone.pivot[1];
        Intrinsics.checkNotNull((Object)bone.pivot);
        float pz = bone.pivot[2];
        poseStack.translate(px, py, pz);
        BoneAnim baseBone = baseAnimation != null && baseAnimation.boneAnims != null ? baseAnimation.boneAnims.get(bone.name) : null;
        float[] animPos = this.interpolate(baseBone != null ? baseBone.position : null, baseTime, new float[]{0.0f, 0.0f, 0.0f});
        float[] animRot = this.interpolate(baseBone != null ? baseBone.rotation : null, baseTime, new float[]{0.0f, 0.0f, 0.0f});
        BoneAnim previousBaseBone = previousBaseAnimation != null && previousBaseAnimation.boneAnims != null ? previousBaseAnimation.boneAnims.get(bone.name) : null;
        if (previousBaseBone != null && baseBlend < 1.0f) {
            float[] previousPos = this.interpolate(previousBaseBone.position, previousBaseTime, new float[]{0.0f, 0.0f, 0.0f});
            float[] previousRot = this.interpolate(previousBaseBone.rotation, previousBaseTime, new float[]{0.0f, 0.0f, 0.0f});
            animPos = this.blend(previousPos, animPos, baseBlend);
            animRot = this.blend(previousRot, animRot, baseBlend);
        }
        int attackLen = attackAnimations.length;
        for (int i = 0; i < attackLen; ++i) {
            if (attackAnimations[i] == null || attackBlends[i] <= 0.0f) continue;
            if (bone.name == null || !bone.name.startsWith(ATTACK_BONE_PREFIXES[i])) continue;
            AnimationData animationData = attackAnimations[i];
            BoneAnim attackBone = animationData.boneAnims.get(bone.name);
            if (attackBone == null) continue;
            if (!attackBone.position.isEmpty()) {
                animPos = this.blend(animPos, this.interpolate(attackBone.position, attackTimes[i], animPos), attackBlends[i]);
            }
            if (!attackBone.rotation.isEmpty()) {
                animRot = this.blend(animRot, this.interpolate(attackBone.rotation, attackTimes[i], animRot), attackBlends[i]);
            }
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
            this.drawCube(poseStack, buffer, cube, lightCoords);
            poseStack.pop();
        }
        for (Bone child : bone.children) {
            this.renderBone(poseStack, buffer, child, baseAnimation, baseTime, previousBaseAnimation, previousBaseTime, baseBlend, attackAnimations, attackTimes, attackBlends, lightCoords);
        }
        poseStack.pop();
    }

    private final void drawCube(MatrixStack poseStack, VertexConsumer buffer, Cube cube, int lightCoords) {
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
            float v1 = uv.uv[1] / 64.0f;
            Intrinsics.checkNotNull((Object)uv.uv);
            float f = uv.uv[0];
            Intrinsics.checkNotNull((Object)uv.uvSize);
            float u2 = (f + uv.uvSize[0]) / 64.0f;
            Intrinsics.checkNotNull((Object)uv.uv);
            float f2 = uv.uv[1];
            Intrinsics.checkNotNull((Object)uv.uvSize);
            float v2 = (f2 + uv.uvSize[1]) / 64.0f;
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


    private static final void render$lambda$3(AnimationData $finalBaseAnimation, double $finalBaseTime, AnimationData $finalPreviousBaseAnimation, double $finalPreviousBaseTime, float $finalBaseBlend, AnimationData[] $attackAnimations, double[] $activeAttackTimes, float[] $attackBlends, int $lightCoords, MatrixStack.Entry pose, VertexConsumer buffer) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)buffer, (String)"buffer");
        MatrixStack localStack = new MatrixStack();
        localStack.peek().getPositionMatrix().set((Matrix4fc)pose.getPositionMatrix());
        localStack.peek().getNormalMatrix().set((Matrix3fc)pose.getNormalMatrix());
        localStack.scale(0.0625f, -0.0625f, 0.0625f);
        localStack.translate(0.0f, -6.5f, 1.9f);
        localStack.scale(1.65f, 1.65f, 1.65f);
        localStack.translate(0.0f, -16.5f, -2.3f);
        for (Bone root : KanekiKaguneRenderer.geometry.rootBones) {
            INSTANCE.renderBone(localStack, buffer, root, $finalBaseAnimation, $finalBaseTime, $finalPreviousBaseAnimation, $finalPreviousBaseTime, $finalBaseBlend, $attackAnimations, $activeAttackTimes, $attackBlends, $lightCoords);
        }
    }

    public static final /* synthetic */ String[] access$getATTACKS$p() {
        return ATTACKS;
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/kagune/kaneki_kagune.geo.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        GEOMETRY_ID = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/kagune/kaneki_kagune.animation.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        ANIMATIONS_ID = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/kagune/kaneki_kagune.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier4;
        geometry = new Geometry();
        animations = new HashMap();
        playbackStates = new WeakHashMap();
        String[] stringArray = new String[]{"animation.kaneki_kagune.attack", "animation.kaneki_kagune.attack_upper_right", "animation.kaneki_kagune.attack_lower_left", "animation.kaneki_kagune.attack_lower_right"};
        ATTACKS = stringArray;
        stringArray = new String[]{"upper_left_", "upper_right_", "lower_left_", "lower_right_"};
        ATTACK_BONE_PREFIXES = stringArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001b\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u001b\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR'\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$AnimationData;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "name", "Ljava/lang/String;", "", "length", "D", "", "loop", "Z", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$BoneAnim;", "boneAnims", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007R\u001d\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000f\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Bone;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "name", "Ljava/lang/String;", "parent", "", "pivot", "[F", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Cube;", "cubes", "Ljava/util/List;", "children", "rtx.kimiko:kimiko"})
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R'\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\t\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$BoneAnim;", "", "<init>", "()V", "Ljava/util/TreeMap;", "", "", "Lkotlin/jvm/JvmField;", "position", "Ljava/util/TreeMap;", "rotation", "scale", "rtx.kimiko:kimiko"})
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007R\u001d\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0007R\u001d\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0007R'\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Cube;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "origin", "[F", "size", "pivot", "rotation", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$FaceUV;", "faces", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$FaceUV;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "uv", "[F", "uvSize", "rtx.kimiko:kimiko"})
    public static final class FaceUV {
        @JvmField
        @Nullable
        public float[] uv;
        @JvmField
        @Nullable
        public float[] uvSize;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R'\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\n8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Geometry;", "", "<init>", "()V", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$Bone;", "Lkotlin/jvm/JvmField;", "bones", "Ljava/util/Map;", "", "rootBones", "Ljava/util/List;", "rtx.kimiko:kimiko"})
    public static final class Geometry {
        @JvmField
        @NotNull
        public Map<String, Bone> bones = new HashMap();
        @JvmField
        @NotNull
        public List<Bone> rootBones = new ArrayList();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0006\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\nR\"\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\"\u0010\u0018\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u0017\u0010\u001c\u001a\u00020\u001b8\u0006\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\"\u0010!\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/KanekiKaguneRenderer$PlaybackState;", "", "<init>", "()V", "", "baseAnimation", "Ljava/lang/String;", "getBaseAnimation", "()Ljava/lang/String;", "setBaseAnimation", "(Ljava/lang/String;)V", "", "baseStartedAt", "J", "getBaseStartedAt", "()J", "setBaseStartedAt", "(J)V", "previousBaseAnimation", "getPreviousBaseAnimation", "setPreviousBaseAnimation", "previousBaseStartedAt", "getPreviousBaseStartedAt", "setPreviousBaseStartedAt", "baseTransitionStartedAt", "getBaseTransitionStartedAt", "setBaseTransitionStartedAt", "", "attackStartedAt", "[J", "getAttackStartedAt", "()[J", "", "previousAttackTime", "F", "getPreviousAttackTime", "()F", "setPreviousAttackTime", "(F)V", "rtx.kimiko:kimiko"})
    private static final class PlaybackState {
        @NotNull
        private String baseAnimation = "animation.kaneki_kagune.idle";
        private long baseStartedAt = System.nanoTime();
        @Nullable
        private String previousBaseAnimation;
        private long previousBaseStartedAt;
        private long baseTransitionStartedAt;
        @NotNull
        private final long[] attackStartedAt = new long[KanekiKaguneRenderer.access$getATTACKS$p().length];
        private float previousAttackTime;

        @NotNull
        public final String getBaseAnimation() {
            return this.baseAnimation;
        }

        public final void setBaseAnimation(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.baseAnimation = string;
        }

        public final long getBaseStartedAt() {
            return this.baseStartedAt;
        }

        public final void setBaseStartedAt(long l) {
            this.baseStartedAt = l;
        }

        @Nullable
        public final String getPreviousBaseAnimation() {
            return this.previousBaseAnimation;
        }

        public final void setPreviousBaseAnimation(@Nullable String string) {
            this.previousBaseAnimation = string;
        }

        public final long getPreviousBaseStartedAt() {
            return this.previousBaseStartedAt;
        }

        public final void setPreviousBaseStartedAt(long l) {
            this.previousBaseStartedAt = l;
        }

        public final long getBaseTransitionStartedAt() {
            return this.baseTransitionStartedAt;
        }

        public final void setBaseTransitionStartedAt(long l) {
            this.baseTransitionStartedAt = l;
        }

        @NotNull
        public final long[] getAttackStartedAt() {
            return this.attackStartedAt;
        }

        public final float getPreviousAttackTime() {
            return this.previousAttackTime;
        }

        public final void setPreviousAttackTime(float f) {
            this.previousAttackTime = f;
        }
    }
}

