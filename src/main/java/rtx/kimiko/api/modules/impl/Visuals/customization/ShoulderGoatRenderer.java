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
 *  kotlin.collections.MapsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
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
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals.customization;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
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
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00be\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010$\n\u0002\b\u0018\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0014\u0080\u0001\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001\u0085\u0001\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJY\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ/\u0010!\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b!\u0010\"JU\u0010,\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010$\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u00182\n\u0010+\u001a\u00020*\"\u00020\u0018H\u0002\u00a2\u0006\u0004\b,\u0010-J_\u00101\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010$\u001a\u00020#2\u0006\u0010+\u001a\u00020*2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b1\u00102J\u001f\u00105\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u00104\u001a\u000203H\u0002\u00a2\u0006\u0004\b5\u00106J)\u0010<\u001a\u00020;2\b\u00107\u001a\u0004\u0018\u00010\u00162\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b<\u0010=J\u0017\u0010@\u001a\u00020?2\u0006\u0010>\u001a\u000208H\u0002\u00a2\u0006\u0004\b@\u0010AJ\u001f\u0010B\u001a\u0002082\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bB\u0010CJ\u0017\u0010D\u001a\u00020\f2\u0006\u0010:\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bD\u0010EJ\u0017\u0010F\u001a\u00020?2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bF\u0010GJ\u001f\u0010I\u001a\u00020\f2\u0006\u0010H\u001a\u0002082\u0006\u0010:\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bI\u0010JJ\u0013\u0010K\u001a\u00020\fH\u0007b\u0002\b\r\u00a2\u0006\u0004\bK\u0010\u0003J\u000f\u0010L\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bL\u0010\u0003J\u001d\u0010O\u001a\b\u0012\u0004\u0012\u00020\u001f0N2\u0006\u0010\u0011\u001a\u00020MH\u0002\u00a2\u0006\u0004\bO\u0010PJ\u0019\u0010T\u001a\u00020S2\b\u0010R\u001a\u0004\u0018\u00010QH\u0002\u00a2\u0006\u0004\bT\u0010UJ!\u0010Y\u001a\u0002032\b\u0010W\u001a\u0004\u0018\u00010V2\u0006\u0010X\u001a\u000203H\u0002\u00a2\u0006\u0004\bY\u0010ZJ\u0017\u0010\\\u001a\u0002032\u0006\u0010[\u001a\u000203H\u0002\u00a2\u0006\u0004\b\\\u0010]J\u0017\u0010`\u001a\u00020M2\u0006\u0010_\u001a\u00020^H\u0002\u00a2\u0006\u0004\b`\u0010aR\u0014\u0010b\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010d\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010cR\u0014\u0010e\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010cR\u0014\u0010f\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0014\u0010h\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010gR\u0014\u0010i\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0014\u0010k\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bk\u0010jR\u0014\u0010l\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010jR\u0016\u0010m\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0016\u0010o\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010nR\u001c\u0010p\u001a\b\u0012\u0004\u0012\u00020\u00100N8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010qR\"\u0010s\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00160r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010tR\u0016\u0010u\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0018\u0010w\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0018\u0010y\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010xR\u0016\u0010z\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010jR\u0016\u0010{\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010jR\u0016\u0010|\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010jR\u0016\u0010}\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010jR\u0016\u0010~\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010jR\u0016\u0010\u007f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010n\u00a8\u0006\u008a\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer;", "", "<init>", "()V", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "packedLight", "", "Lkotlin/jvm/JvmStatic;", "render", "(Lnet/minecraft/AbstractClientPlayerEntity;Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;I)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Bone;", "bone", "matrices", "Lnet/minecraft/VertexConsumer;", "consumer", "light", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Animation;", "previous", "", "previousTime", "current", "currentTime", "blend", "renderBone", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Bone;Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;ILrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Animation;FLrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Animation;FF)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Cube;", "cube", "renderCube", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Cube;Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;I)V", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$TextureFace;", "face", "normalX", "normalY", "normalZ", "", "vertices", "drawFace", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$TextureFace;IFFF[F)V", "offset", "u", "v", "addVertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;[FIFFIFFF)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "rotation", "rotate", "(Lnet/minecraft/MatrixStack;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)V", "animation", "", "boneName", "time", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;", "sample", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Animation;Ljava/lang/String;F)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;", "name", "", "isEffectBone", "(Ljava/lang/String;)Z", "selectAnimation", "(Lnet/minecraft/AbstractClientPlayerEntity;F)Ljava/lang/String;", "postponeKiss", "(F)V", "isMoving", "(Lnet/minecraft/AbstractClientPlayerEntity;)Z", "requested", "updateState", "(Ljava/lang/String;F)V", "reset", "load", "Lcom/google/gson/JsonObject;", "", "parseCubes", "(Lcom/google/gson/JsonObject;)Ljava/util/List;", "Lcom/google/gson/JsonElement;", "element", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "parseChannel", "(Lcom/google/gson/JsonElement;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "Lcom/google/gson/JsonArray;", "array", "fallback", "vector", "(Lcom/google/gson/JsonArray;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "point", "modelPoint", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "Lnet/minecraft/Identifier;", "id", "readJson", "(Lnet/minecraft/Identifier;)Lcom/google/gson/JsonObject;", "GEOMETRY_ID", "Lnet/minecraft/Identifier;", "ANIMATIONS_ID", "TEXTURE", "ZERO", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "ONE", "TRANSITION_DURATION", "F", "SCALE", "ANCHOR_X", "textureWidth", "I", "textureHeight", "roots", "Ljava/util/List;", "", "animations", "Ljava/util/Map;", "loaded", "Z", "previousAnimation", "Ljava/lang/String;", "currentAnimation", "previousStartedAt", "currentStartedAt", "nextKissAt", "kissEndsAt", "lastTime", "kissIndex", "Face", "TextureFace", "Cube", "Bone", "Keyframe", "Channel", "BoneTrack", "Animation", "Transform", "Vec3", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nShoulderGoatRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ShoulderGoatRenderer.kt\nrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,536:1\n1174#2,2:537\n*S KotlinDebug\n*F\n+ 1 ShoulderGoatRenderer.kt\nrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer\n*L\n422#1:537,2\n*E\n"})
public final class ShoulderGoatRenderer {
    @NotNull
    public static final ShoulderGoatRenderer INSTANCE = new ShoulderGoatRenderer();
    @NotNull
    private static final Identifier GEOMETRY_ID;
    @NotNull
    private static final Identifier ANIMATIONS_ID;
    @NotNull
    private static final Identifier TEXTURE;
    @NotNull
    private static final Vec3 ZERO;
    @NotNull
    private static final Vec3 ONE;
    private static final float TRANSITION_DURATION = 0.22f;
    private static final float SCALE = 1.28f;
    private static final float ANCHOR_X = -0.38375f;
    private static int textureWidth;
    private static int textureHeight;
    @NotNull
    private static List<Bone> roots;
    @NotNull
    private static Map<String, Animation> animations;
    private static boolean loaded;
    @Nullable
    private static String previousAnimation;
    @Nullable
    private static String currentAnimation;
    private static float previousStartedAt;
    private static float currentStartedAt;
    private static float nextKissAt;
    private static float kissEndsAt;
    private static float lastTime;
    private static int kissIndex;

    private ShoulderGoatRenderer() {
    }

    @JvmStatic
    public static final void render(@NotNull AbstractClientPlayerEntity player, @NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue collector, int packedLight) {
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        INSTANCE.load();
        if (roots.isEmpty()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        float partialTick = mc.getRenderTickCounter().getTickProgress(false);
        float time = ((float)player.age + partialTick) / 20.0f;
        INSTANCE.updateState(INSTANCE.selectAnimation(player, time), time);
        Animation previous = animations.get(previousAnimation);
        Animation animation = animations.get(currentAnimation);
        if (animation == null) {
            return;
        }
        Animation current = animation;
        float previousTime = Math.max(0.0f, time - previousStartedAt);
        float currentTime = Math.max(0.0f, time - currentStartedAt);
        float blend = Math.max(0.0f, Math.min(1.0f, (time - currentStartedAt) / 0.22f));
        poseStack.push();
        poseStack.translate(-0.025f, 0.018f, 0.01f);
        poseStack.translate(-0.38375f, 0.0f, 0.0f);
        poseStack.scale(1.28f, 1.28f, 1.28f);
        poseStack.translate(0.38375f, 0.0f, 0.0f);
        collector.submitCustom(poseStack, RenderLayers.entityCutout((Identifier)TEXTURE), (arg_0, arg_1) -> ShoulderGoatRenderer.render$lambda$0(packedLight, previous, previousTime, current, currentTime, blend, arg_0, arg_1));
        poseStack.pop();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderBone(Bone bone, MatrixStack matrices, VertexConsumer consumer, int light, Animation previous, float previousTime, Animation current, float currentTime, float blend) {
        Transform newTransform;
        Transform oldTransform = this.sample(previous, bone.getName(), previousTime);
        Transform transform = Transform.Companion.lerp(oldTransform, newTransform = this.sample(current, bone.getName(), currentTime), blend);
        if (Math.abs(transform.getScale().getX()) < 0.001f && Math.abs(transform.getScale().getY()) < 0.001f && Math.abs(transform.getScale().getZ()) < 0.001f) {
            return;
        }
        matrices.push();
        try {
            matrices.translate(transform.getPosition().getX() / 16.0f, -transform.getPosition().getY() / 16.0f, transform.getPosition().getZ() / 16.0f);
            Vec3 pivot = this.modelPoint(bone.getPivot());
            matrices.translate(pivot.getX(), pivot.getY(), pivot.getZ());
            this.rotate(matrices, bone.getRotation().add(transform.getRotation()));
            matrices.scale(transform.getScale().getX(), transform.getScale().getY(), transform.getScale().getZ());
            matrices.translate(-pivot.getX(), -pivot.getY(), -pivot.getZ());
            for (Cube cube : bone.getCubes()) {
                this.renderCube(cube, matrices, consumer, light);
            }
            for (Bone child : bone.getChildren()) {
                this.renderBone(child, matrices, consumer, light, previous, previousTime, current, currentTime, blend);
            }
        }
        finally {
            matrices.pop();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderCube(Cube cube, MatrixStack matrices, VertexConsumer consumer, int light) {
        matrices.push();
        try {
            if (!cube.getRotation().isZero()) {
                Vec3 pivot = this.modelPoint(cube.getPivot());
                matrices.translate(pivot.getX(), pivot.getY(), pivot.getZ());
                this.rotate(matrices, cube.getRotation());
                matrices.translate(-pivot.getX(), -pivot.getY(), -pivot.getZ());
            }
            float x1 = cube.getOrigin().getX() / 16.0f;
            float x2 = (cube.getOrigin().getX() + cube.getSize().getX()) / 16.0f;
            float y1 = (24.0f - cube.getOrigin().getY() - cube.getSize().getY()) / 16.0f;
            float y2 = (24.0f - cube.getOrigin().getY()) / 16.0f;
            float z1 = cube.getOrigin().getZ() / 16.0f;
            float z2 = (cube.getOrigin().getZ() + cube.getSize().getZ()) / 16.0f;
            MatrixStack.Entry entry2 = matrices.peek();
            Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
            MatrixStack.Entry pose = entry2;
            float[] fArray = new float[]{x1, y2, z1, x2, y2, z1, x2, y1, z1, x1, y1, z1};
            this.drawFace(consumer, pose, cube.getFaces().get((Object)Face.NORTH), light, 0.0f, 0.0f, -1.0f, fArray);
            fArray = new float[]{x2, y2, z2, x1, y2, z2, x1, y1, z2, x2, y1, z2};
            this.drawFace(consumer, pose, cube.getFaces().get((Object)Face.SOUTH), light, 0.0f, 0.0f, 1.0f, fArray);
            fArray = new float[]{x1, y2, z2, x1, y2, z1, x1, y1, z1, x1, y1, z2};
            this.drawFace(consumer, pose, cube.getFaces().get((Object)Face.WEST), light, -1.0f, 0.0f, 0.0f, fArray);
            fArray = new float[]{x2, y2, z1, x2, y2, z2, x2, y1, z2, x2, y1, z1};
            this.drawFace(consumer, pose, cube.getFaces().get((Object)Face.EAST), light, 1.0f, 0.0f, 0.0f, fArray);
            fArray = new float[]{x1, y1, z1, x2, y1, z1, x2, y1, z2, x1, y1, z2};
            this.drawFace(consumer, pose, cube.getFaces().get((Object)Face.UP), light, 0.0f, -1.0f, 0.0f, fArray);
            fArray = new float[]{x1, y2, z2, x2, y2, z2, x2, y2, z1, x1, y2, z1};
            this.drawFace(consumer, pose, cube.getFaces().get((Object)Face.DOWN), light, 0.0f, 1.0f, 0.0f, fArray);
        }
        finally {
            matrices.pop();
        }
    }

    private final void drawFace(VertexConsumer consumer, MatrixStack.Entry pose, TextureFace face, int light, float normalX, float normalY, float normalZ, float ... vertices) {
        if (face == null) {
            return;
        }
        float u1 = face.getU() / (float)textureWidth;
        float v1 = face.getV() / (float)textureHeight;
        float u2 = (face.getU() + face.getWidth()) / (float)textureWidth;
        float v2 = (face.getV() + face.getHeight()) / (float)textureHeight;
        this.addVertex(consumer, pose, vertices, 0, u1, v2, light, normalX, normalY, normalZ);
        this.addVertex(consumer, pose, vertices, 3, u2, v2, light, normalX, normalY, normalZ);
        this.addVertex(consumer, pose, vertices, 6, u2, v1, light, normalX, normalY, normalZ);
        this.addVertex(consumer, pose, vertices, 9, u1, v1, light, normalX, normalY, normalZ);
    }

    private final void addVertex(VertexConsumer consumer, MatrixStack.Entry pose, float[] vertices, int offset, float u, float v, int light, float normalX, float normalY, float normalZ) {
        consumer.vertex(pose, vertices[offset], vertices[offset + 1], vertices[offset + 2]).color(-1).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(pose, normalX, normalY, normalZ);
    }

    private final void rotate(MatrixStack matrices, Vec3 rotation) {
        if (!(rotation.getZ() == 0.0f)) {
            matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(-rotation.getZ()));
        }
        if (!(rotation.getY() == 0.0f)) {
            matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(rotation.getY()));
        }
        if (!(rotation.getX() == 0.0f)) {
            matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-rotation.getX()));
        }
    }

    private final Transform sample(Animation animation, String boneName, float time) {
        Vec3 defaultScale;
        Vec3 vec3 = defaultScale = this.isEffectBone(boneName) ? ZERO : ONE;
        if (animation == null) {
            return new Transform(ZERO, ZERO, defaultScale);
        }
        float sampledTime = animation.time(time);
        BoneTrack boneTrack = animation.getTracks().get(boneName);
        if (boneTrack == null) {
            return new Transform(ZERO, ZERO, defaultScale);
        }
        BoneTrack track = boneTrack;
        return new Transform(track.getPosition().sample(sampledTime, ZERO), track.getRotation().sample(sampledTime, ZERO), track.getScale().sample(sampledTime, defaultScale));
    }

    private final boolean isEffectBone(String name) {
        return String.valueOf(name).startsWith("kiss_heart") || String.valueOf(name).startsWith("run_stop_dust") || String.valueOf(name).startsWith("fly_wisp") || String.valueOf(name).startsWith("jump_star") || String.valueOf(name).startsWith("victory_star") || String.valueOf(name).startsWith("victory_confetti") || String.valueOf(name).startsWith("sleep_z");
    }

    private final String selectAnimation(AbstractClientPlayerEntity player, float time) {
        if (lastTime >= 0.0f && time < lastTime) {
            nextKissAt = -1.0f;
            kissEndsAt = 0.0f;
        }
        lastTime = time;
        if (player.isGliding() || player.getAbilities().flying) {
            this.postponeKiss(time);
            return "fly";
        }
        if (player.isInSneakingPose()) {
            this.postponeKiss(time);
            return "sneak";
        }
        if (!player.isOnGround()) {
            this.postponeKiss(time);
            return "jump";
        }
        if (this.isMoving(player)) {
            this.postponeKiss(time);
            return "run";
        }
        if (time < kissEndsAt) {
            return "kiss";
        }
        if (nextKissAt < 0.0f) {
            nextKissAt = time + 3.8f;
        }
        if (time >= nextKissAt) {
            kissEndsAt = time + 2.6f;
            int n = kissIndex;
            kissIndex = n + 1;
            nextKissAt = kissEndsAt + 10.5f + (float)(n % 3) * 1.8f;
            return "kiss";
        }
        return "pet_life";
    }

    private final void postponeKiss(float time) {
        kissEndsAt = 0.0f;
        nextKissAt = time + 4.5f;
    }

    private final boolean isMoving(AbstractClientPlayerEntity player) {
        double x = player.getVelocity().x;
        double z = player.getVelocity().z;
        return x * x + z * z > 9.0E-4;
    }

    private final void updateState(String requested, float time) {
        if (currentAnimation == null || time < currentStartedAt) {
            previousAnimation = requested;
            currentAnimation = requested;
            previousStartedAt = time;
            currentStartedAt = time;
        } else if (!Intrinsics.areEqual((Object)currentAnimation, (Object)requested)) {
            previousAnimation = currentAnimation;
            previousStartedAt = currentStartedAt;
            currentAnimation = requested;
            currentStartedAt = time;
        }
    }

    @JvmStatic
    public static final void reset() {
        previousAnimation = null;
        currentAnimation = null;
        previousStartedAt = 0.0f;
        currentStartedAt = 0.0f;
        nextKissAt = -1.0f;
        kissEndsAt = 0.0f;
        lastTime = -1.0f;
        kissIndex = 0;
    }

    private final void load() {
        if (loaded) {
            return;
        }
        loaded = true;
        try {
            JsonObject geometryRoot = this.readJson(GEOMETRY_ID);
            JsonObject geometry = geometryRoot.getAsJsonArray("minecraft:geometry").get(0).getAsJsonObject();
            JsonObject description = geometry.getAsJsonObject("description");
            textureWidth = description.get("texture_width").getAsInt();
            textureHeight = description.get("texture_height").getAsInt();
            LinkedHashMap bones = new LinkedHashMap();
            Iterator iterator = geometry.getAsJsonArray("bones").iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                JsonElement element = (JsonElement)iterator2.next();
                JsonObject source = element.getAsJsonObject();
                String name = source.get("name").getAsString();
                Map map = bones;
                Intrinsics.checkNotNull((Object)name);
                String string = source.has("parent") ? source.get("parent").getAsString() : null;
                Vec3 vec3 = this.vector(source.getAsJsonArray("pivot"), ZERO);
                Vec3 vec32 = this.vector(source.getAsJsonArray("rotation"), ZERO);
                Intrinsics.checkNotNull((Object)source);
                map.put(name, new Bone(name, string, vec3, vec32, this.parseCubes(source)));
            }
            ArrayList<Bone> parsedRoots = new ArrayList<Bone>();
            for (Object e : bones.values()) {
                Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
                Bone bone = (Bone)e;
                Bone parent = bone.getParentName() == null ? null : (Bone)bones.get(bone.getParentName());
                boolean bl = parent == null ? parsedRoots.add(bone) : parent.getChildren().add(bone);
            }
            roots = CollectionsKt.toList((Iterable)parsedRoots);
            JsonObject animationRoot = this.readJson(ANIMATIONS_ID).getAsJsonObject("animations");
            LinkedHashMap parsedAnimations = new LinkedHashMap();
            for (Map.Entry entry : animationRoot.entrySet()) {
                Intrinsics.checkNotNull((Object)entry);
                String fullName = (String)entry.getKey();
                JsonElement value = (JsonElement)entry.getValue();
                Intrinsics.checkNotNull((Object)fullName);
                String name = fullName.substring(fullName.lastIndexOf('.') + 1);
                JsonObject source = value.getAsJsonObject();
                LinkedHashMap tracks = new LinkedHashMap();
                if (source.has("bones")) {
                    for (Map.Entry entry2 : source.getAsJsonObject("bones").entrySet()) {
                        Intrinsics.checkNotNull((Object)entry2);
                        String trackKey = (String)entry2.getKey();
                        JsonElement trackValue = (JsonElement)entry2.getValue();
                        JsonObject track = trackValue.getAsJsonObject();
                        ((Map)tracks).put(trackKey, new BoneTrack(this.parseChannel(track.get("position")), this.parseChannel(track.get("rotation")), this.parseChannel(track.get("scale"))));
                    }
                }
                ((Map)parsedAnimations).put(name, new Animation(source.has("loop") && source.get("loop").isJsonPrimitive() && source.get("loop").getAsJsonPrimitive().isBoolean() && source.get("loop").getAsBoolean(), source.has("animation_length") ? source.get("animation_length").getAsFloat() : 0.0f, tracks));
            }
            animations = MapsKt.toMap((Map)parsedAnimations);
        }
        catch (Exception exception) {
            roots = CollectionsKt.emptyList();
            animations = MapsKt.emptyMap();
        }
    }

    private final List<Cube> parseCubes(JsonObject bone) {
        if (!bone.has("cubes")) {
            return CollectionsKt.emptyList();
        }
        ArrayList<Cube> cubes = new ArrayList<Cube>();
        Vec3 bonePivot = this.vector(bone.getAsJsonArray("pivot"), ZERO);
        Iterator iterator = bone.getAsJsonArray("cubes").iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonElement element = (JsonElement)iterator2.next();
            JsonObject source = element.getAsJsonObject();
            EnumMap faces = new EnumMap(Face.class);
            JsonObject uv = source.getAsJsonObject("uv");
            if (uv != null) {
                for (Face face : Face.getEntries()) {
                    if (!uv.has(face.getKey())) continue;
                    JsonObject faceSource = uv.getAsJsonObject(face.getKey());
                    JsonArray position = faceSource.getAsJsonArray("uv");
                    JsonArray size = faceSource.getAsJsonArray("uv_size");
                    ((Map)faces).put(face, new TextureFace(position.get(0).getAsFloat(), position.get(1).getAsFloat(), size.get(0).getAsFloat(), size.get(1).getAsFloat()));
                }
            }
            cubes.add(new Cube(this.vector(source.getAsJsonArray("origin"), ZERO), this.vector(source.getAsJsonArray("size"), ZERO), this.vector(source.getAsJsonArray("pivot"), bonePivot), this.vector(source.getAsJsonArray("rotation"), ZERO), faces));
        }
        return CollectionsKt.toList((Iterable)cubes);
    }

    private final Channel parseChannel(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return Channel.Companion.getEMPTY();
        }
        if (element.isJsonArray()) {
            return new Channel(List.of(new Keyframe(0.0f, this.vector(element.getAsJsonArray(), ZERO))));
        }
        ArrayList<Keyframe> frames = new ArrayList<Keyframe>();
        for (Map.Entry entry : element.getAsJsonObject().entrySet()) {
            Intrinsics.checkNotNull((Object)entry);
            String key = (String)entry.getKey();
            JsonElement value = (JsonElement)entry.getValue();
            JsonArray array = null;
            if (value.isJsonArray()) {
                array = value.getAsJsonArray();
            } else if (value.isJsonObject() && value.getAsJsonObject().has("post") && value.getAsJsonObject().get("post").isJsonArray()) {
                array = value.getAsJsonObject().getAsJsonArray("post");
            }
            if (array == null) continue;
            try {
                Intrinsics.checkNotNull((Object)key);
                frames.add(new Keyframe(Float.parseFloat(key), this.vector(array, ZERO)));
            }
            catch (NumberFormatException numberFormatException) {}
        }
        frames.sort(Comparator.comparingDouble(Keyframe::getTime));
        return new Channel(frames);
    }

    private final Vec3 vector(JsonArray array, Vec3 fallback) {
        if (array == null || array.size() < 3) {
            return fallback;
        }
        return new Vec3(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
    }

    private final Vec3 modelPoint(Vec3 point) {
        return new Vec3(point.getX() / 16.0f, (24.0f - point.getY()) / 16.0f, point.getZ() / 16.0f);
    }

    private final JsonObject readJson(Identifier id) {
        try {
            Resource resource = (Resource)MinecraftClient.getInstance().getResourceManager().getResource(id).orElseThrow();
            try (InputStream stream = resource.getInputStream();
                 InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                return JsonParser.parseReader((Reader)reader).getAsJsonObject();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final void render$lambda$0(int $packedLight, Animation $previous, float $previousTime, Animation $current, float $currentTime, float $blend, MatrixStack.Entry pose, VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        MatrixStack matrices = new MatrixStack();
        matrices.multiplyPositionMatrix((Matrix4fc)pose.getPositionMatrix());
        for (Bone root : roots) {
            INSTANCE.renderBone(root, matrices, consumer, $packedLight, $previous, $previousTime, $current, $currentTime, $blend);
        }
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/goat/shoulder_goat_pet.geo.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        GEOMETRY_ID = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/goat/shoulder_goat_pet.animation.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        ANIMATIONS_ID = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/goat/shoulder_goat_pet.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier4;
        ZERO = new Vec3(0.0f, 0.0f, 0.0f);
        ONE = new Vec3(1.0f, 1.0f, 1.0f);
        textureWidth = 64;
        textureHeight = 64;
        roots = CollectionsKt.emptyList();
        animations = MapsKt.emptyMap();
        nextKissAt = -1.0f;
        lastTime = -1.0f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J:\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010\u0012R#\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0006\u00a2\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010\u0014\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Animation;", "", "", "loop", "", "length", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$BoneTrack;", "tracks", "<init>", "(ZFLjava/util/Map;)V", "value", "time", "(F)F", "component1", "()Z", "component2", "()F", "component3", "()Ljava/util/Map;", "copy", "(ZFLjava/util/Map;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Animation;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Z", "getLoop", "F", "getLength", "Ljava/util/Map;", "getTracks", "rtx.kimiko:kimiko"})
    private static final class Animation {
        private final boolean loop;
        private final float length;
        @NotNull
        private final Map<String, BoneTrack> tracks;

        public Animation(boolean loop, float length, @NotNull Map<String, BoneTrack> tracks) {
            Intrinsics.checkNotNullParameter(tracks, (String)"tracks");
            this.loop = loop;
            this.length = length;
            this.tracks = tracks;
        }

        public final boolean getLoop() {
            return this.loop;
        }

        public final float getLength() {
            return this.length;
        }

        @NotNull
        public final Map<String, BoneTrack> getTracks() {
            return this.tracks;
        }

        public final float time(float value) {
            if (this.length <= 0.0f) {
                return 0.0f;
            }
            if (!this.loop) {
                return Math.max(0.0f, Math.min(this.length, value));
            }
            float wrapped = value % this.length;
            return wrapped < 0.0f ? wrapped + this.length : wrapped;
        }

        public final boolean component1() {
            return this.loop;
        }

        public final float component2() {
            return this.length;
        }

        @NotNull
        public final Map<String, BoneTrack> component3() {
            return this.tracks;
        }

        @NotNull
        public final Animation copy(boolean loop, float length, @NotNull Map<String, BoneTrack> tracks) {
            Intrinsics.checkNotNullParameter(tracks, (String)"tracks");
            return new Animation(loop, length, tracks);
        }

        public static /* synthetic */ Animation copy$default(Animation animation, boolean bl, float f, Map map, int n, Object object) {
            if ((n & 1) != 0) {
                bl = animation.loop;
            }
            if ((n & 2) != 0) {
                f = animation.length;
            }
            if ((n & 4) != 0) {
                map = animation.tracks;
            }
            return animation.copy(bl, f, map);
        }

        @NotNull
        public String toString() {
            return "Animation(loop=" + this.loop + ", length=" + this.length + ", tracks=" + this.tracks + ")";
        }

        public int hashCode() {
            int result = Boolean.hashCode(this.loop);
            result = result * 31 + Float.hashCode(this.length);
            result = result * 31 + ((Object)this.tracks).hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Animation)) {
                return false;
            }
            Animation animation = (Animation)other;
            if (this.loop != animation.loop) {
                return false;
            }
            if (Float.compare(this.length, animation.length) != 0) {
                return false;
            }
            return Intrinsics.areEqual(this.tracks, animation.tracks);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010!\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0007\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u00188\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Bone;", "", "", "name", "parentName", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "pivot", "rotation", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Cube;", "cubes", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Ljava/util/List;)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "getParentName", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "getPivot", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "getRotation", "Ljava/util/List;", "getCubes", "()Ljava/util/List;", "", "children", "getChildren", "rtx.kimiko:kimiko"})
    private static final class Bone {
        @NotNull
        private final String name;
        @Nullable
        private final String parentName;
        @NotNull
        private final Vec3 pivot;
        @NotNull
        private final Vec3 rotation;
        @NotNull
        private final List<Cube> cubes;
        @NotNull
        private final List<Bone> children;

        public Bone(@NotNull String name, @Nullable String parentName, @NotNull Vec3 pivot, @NotNull Vec3 rotation, @NotNull List<Cube> cubes) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)pivot, (String)"pivot");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter(cubes, (String)"cubes");
            this.name = name;
            this.parentName = parentName;
            this.pivot = pivot;
            this.rotation = rotation;
            this.cubes = cubes;
            this.children = new ArrayList();
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final String getParentName() {
            return this.parentName;
        }

        @NotNull
        public final Vec3 getPivot() {
            return this.pivot;
        }

        @NotNull
        public final Vec3 getRotation() {
            return this.rotation;
        }

        @NotNull
        public final List<Cube> getCubes() {
            return this.cubes;
        }

        @NotNull
        public final List<Bone> getChildren() {
            return this.children;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\tJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\tJ.\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\t\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$BoneTrack;", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "position", "rotation", "scale", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;)V", "component1", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "component2", "component3", "copy", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$BoneTrack;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "getPosition", "getRotation", "getScale", "rtx.kimiko:kimiko"})
    private static final class BoneTrack {
        @NotNull
        private final Channel position;
        @NotNull
        private final Channel rotation;
        @NotNull
        private final Channel scale;

        public BoneTrack(@NotNull Channel position, @NotNull Channel rotation, @NotNull Channel scale) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter((Object)scale, (String)"scale");
            this.position = position;
            this.rotation = rotation;
            this.scale = scale;
        }

        @NotNull
        public final Channel getPosition() {
            return this.position;
        }

        @NotNull
        public final Channel getRotation() {
            return this.rotation;
        }

        @NotNull
        public final Channel getScale() {
            return this.scale;
        }

        @NotNull
        public final Channel component1() {
            return this.position;
        }

        @NotNull
        public final Channel component2() {
            return this.rotation;
        }

        @NotNull
        public final Channel component3() {
            return this.scale;
        }

        @NotNull
        public final BoneTrack copy(@NotNull Channel position, @NotNull Channel rotation, @NotNull Channel scale) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter((Object)scale, (String)"scale");
            return new BoneTrack(position, rotation, scale);
        }

        public static /* synthetic */ BoneTrack copy$default(BoneTrack boneTrack, Channel channel, Channel channel2, Channel channel3, int n, Object object) {
            if ((n & 1) != 0) {
                channel = boneTrack.position;
            }
            if ((n & 2) != 0) {
                channel2 = boneTrack.rotation;
            }
            if ((n & 4) != 0) {
                channel3 = boneTrack.scale;
            }
            return boneTrack.copy(channel, channel2, channel3);
        }

        @NotNull
        public String toString() {
            return "BoneTrack(position=" + this.position + ", rotation=" + this.rotation + ", scale=" + this.scale + ")";
        }

        public int hashCode() {
            int result = this.position.hashCode();
            result = result * 31 + this.rotation.hashCode();
            result = result * 31 + this.scale.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BoneTrack)) {
                return false;
            }
            BoneTrack boneTrack = (BoneTrack)other;
            if (!Intrinsics.areEqual((Object)this.position, (Object)boneTrack.position)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.rotation, (Object)boneTrack.rotation)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.scale, (Object)boneTrack.scale);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ \u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u000e\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Keyframe;", "frames", "<init>", "(Ljava/util/List;)V", "", "time", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "fallback", "sample", "(FLrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "component1", "()Ljava/util/List;", "copy", "(Ljava/util/List;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "getFrames", "Companion", "rtx.kimiko:kimiko"})
    private static final class Channel {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final List<Keyframe> frames;
        @NotNull
        private static final Channel EMPTY = new Channel(CollectionsKt.emptyList());

        public Channel(@NotNull List<Keyframe> frames) {
            Intrinsics.checkNotNullParameter(frames, (String)"frames");
            this.frames = frames;
        }

        @NotNull
        public final List<Keyframe> getFrames() {
            return this.frames;
        }

        @NotNull
        public final Vec3 sample(float time, @NotNull Vec3 fallback) {
            Intrinsics.checkNotNullParameter((Object)fallback, (String)"fallback");
            if (this.frames.isEmpty()) {
                return fallback;
            }
            if (time <= ((Keyframe)CollectionsKt.first(this.frames)).getTime()) {
                return ((Keyframe)CollectionsKt.first(this.frames)).getValue();
            }
            if (time >= ((Keyframe)CollectionsKt.last(this.frames)).getTime()) {
                return ((Keyframe)CollectionsKt.last(this.frames)).getValue();
            }
            int n = this.frames.size();
            for (int index = 1; index < n; ++index) {
                Keyframe right = this.frames.get(index);
                if (time > right.getTime()) continue;
                Keyframe left = this.frames.get(index - 1);
                float progress = (time - left.getTime()) / Math.max(1.0E-4f, right.getTime() - left.getTime());
                return Vec3.Companion.lerp(left.getValue(), right.getValue(), progress);
            }
            return ((Keyframe)CollectionsKt.last(this.frames)).getValue();
        }

        @NotNull
        public final List<Keyframe> component1() {
            return this.frames;
        }

        @NotNull
        public final Channel copy(@NotNull List<Keyframe> frames) {
            Intrinsics.checkNotNullParameter(frames, (String)"frames");
            return new Channel(frames);
        }

        public static /* synthetic */ Channel copy$default(Channel channel, List list, int n, Object object) {
            if ((n & 1) != 0) {
                list = channel.frames;
            }
            return channel.copy(list);
        }

        @NotNull
        public String toString() {
            return "Channel(frames=" + this.frames + ")";
        }

        public int hashCode() {
            return ((Object)this.frames).hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Channel)) {
                return false;
            }
            Channel channel = (Channel)other;
            return Intrinsics.areEqual(this.frames, channel.frames);
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "EMPTY", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "getEMPTY", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Channel;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final Channel getEMPTY() {
                return EMPTY;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013JN\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001e\u001a\u00020\u001dH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b#\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b$\u0010\u000eR#\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00078\u0006\u00a2\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b&\u0010\u0013\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Cube;", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "origin", "size", "pivot", "rotation", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Face;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$TextureFace;", "faces", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Ljava/util/Map;)V", "component1", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "component2", "component3", "component4", "component5", "()Ljava/util/Map;", "copy", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Ljava/util/Map;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Cube;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "getOrigin", "getSize", "getPivot", "getRotation", "Ljava/util/Map;", "getFaces", "rtx.kimiko:kimiko"})
    private static final class Cube {
        @NotNull
        private final Vec3 origin;
        @NotNull
        private final Vec3 size;
        @NotNull
        private final Vec3 pivot;
        @NotNull
        private final Vec3 rotation;
        @NotNull
        private final Map<Face, TextureFace> faces;

        public Cube(@NotNull Vec3 origin, @NotNull Vec3 size, @NotNull Vec3 pivot, @NotNull Vec3 rotation, @NotNull Map<Face, TextureFace> faces) {
            Intrinsics.checkNotNullParameter((Object)origin, (String)"origin");
            Intrinsics.checkNotNullParameter((Object)size, (String)"size");
            Intrinsics.checkNotNullParameter((Object)pivot, (String)"pivot");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter(faces, (String)"faces");
            this.origin = origin;
            this.size = size;
            this.pivot = pivot;
            this.rotation = rotation;
            this.faces = faces;
        }

        @NotNull
        public final Vec3 getOrigin() {
            return this.origin;
        }

        @NotNull
        public final Vec3 getSize() {
            return this.size;
        }

        @NotNull
        public final Vec3 getPivot() {
            return this.pivot;
        }

        @NotNull
        public final Vec3 getRotation() {
            return this.rotation;
        }

        @NotNull
        public final Map<Face, TextureFace> getFaces() {
            return this.faces;
        }

        @NotNull
        public final Vec3 component1() {
            return this.origin;
        }

        @NotNull
        public final Vec3 component2() {
            return this.size;
        }

        @NotNull
        public final Vec3 component3() {
            return this.pivot;
        }

        @NotNull
        public final Vec3 component4() {
            return this.rotation;
        }

        @NotNull
        public final Map<Face, TextureFace> component5() {
            return this.faces;
        }

        @NotNull
        public final Cube copy(@NotNull Vec3 origin, @NotNull Vec3 size, @NotNull Vec3 pivot, @NotNull Vec3 rotation, @NotNull Map<Face, TextureFace> faces) {
            Intrinsics.checkNotNullParameter((Object)origin, (String)"origin");
            Intrinsics.checkNotNullParameter((Object)size, (String)"size");
            Intrinsics.checkNotNullParameter((Object)pivot, (String)"pivot");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter(faces, (String)"faces");
            return new Cube(origin, size, pivot, rotation, faces);
        }

        public static /* synthetic */ Cube copy$default(Cube cube, Vec3 vec3, Vec3 vec32, Vec3 vec33, Vec3 vec34, Map map, int n, Object object) {
            if ((n & 1) != 0) {
                vec3 = cube.origin;
            }
            if ((n & 2) != 0) {
                vec32 = cube.size;
            }
            if ((n & 4) != 0) {
                vec33 = cube.pivot;
            }
            if ((n & 8) != 0) {
                vec34 = cube.rotation;
            }
            if ((n & 0x10) != 0) {
                map = cube.faces;
            }
            return cube.copy(vec3, vec32, vec33, vec34, map);
        }

        @NotNull
        public String toString() {
            return "Cube(origin=" + this.origin + ", size=" + this.size + ", pivot=" + this.pivot + ", rotation=" + this.rotation + ", faces=" + this.faces + ")";
        }

        public int hashCode() {
            int result = this.origin.hashCode();
            result = result * 31 + this.size.hashCode();
            result = result * 31 + this.pivot.hashCode();
            result = result * 31 + this.rotation.hashCode();
            result = result * 31 + ((Object)this.faces).hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Cube)) {
                return false;
            }
            Cube cube = (Cube)other;
            if (!Intrinsics.areEqual((Object)this.origin, (Object)cube.origin)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.size, (Object)cube.size)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.pivot, (Object)cube.pivot)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.rotation, (Object)cube.rotation)) {
                return false;
            }
            return Intrinsics.areEqual(this.faces, cube.faces);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\r\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Face;", "", "", "key", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "NORTH", "EAST", "SOUTH", "WEST", "UP", "DOWN", "rtx.kimiko:kimiko"})
    private static enum Face {
        NORTH("north"),
        EAST("east"),
        SOUTH("south"),
        WEST("west"),
        UP("up"),
        DOWN("down");
@NotNull
        private final String key;
        
        
        
        
        
        
        
        private Face(String key) {
            this.key = key;
        }

        @NotNull
        public final String getKey() {
            return this.key;
        }

        

        

        @NotNull
        public static EnumEntries<Face> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Keyframe;", "", "", "time", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "value", "<init>", "(FLrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)V", "component1", "()F", "component2", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "copy", "(FLrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Keyframe;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getTime", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "getValue", "rtx.kimiko:kimiko"})
    private static final class Keyframe {
        private final float time;
        @NotNull
        private final Vec3 value;

        public Keyframe(float time, @NotNull Vec3 value) {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.time = time;
            this.value = value;
        }

        public final float getTime() {
            return this.time;
        }

        @NotNull
        public final Vec3 getValue() {
            return this.value;
        }

        public final float component1() {
            return this.time;
        }

        @NotNull
        public final Vec3 component2() {
            return this.value;
        }

        @NotNull
        public final Keyframe copy(float time, @NotNull Vec3 value) {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            return new Keyframe(time, value);
        }

        public static /* synthetic */ Keyframe copy$default(Keyframe keyframe, float f, Vec3 vec3, int n, Object object) {
            if ((n & 1) != 0) {
                f = keyframe.time;
            }
            if ((n & 2) != 0) {
                vec3 = keyframe.value;
            }
            return keyframe.copy(f, vec3);
        }

        @NotNull
        public String toString() {
            return "Keyframe(time=" + this.time + ", value=" + this.value + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.time);
            result = result * 31 + this.value.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Keyframe)) {
                return false;
            }
            Keyframe keyframe = (Keyframe)other;
            if (Float.compare(this.time, keyframe.time) != 0) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.value, (Object)keyframe.value);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\nJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\nJ8\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u00020\u0014H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0018\u001a\u00020\u0017H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001c\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001d\u0010\nR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001e\u0010\n\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$TextureFace;", "", "", "u", "v", "width", "height", "<init>", "(FFFF)V", "component1", "()F", "component2", "component3", "component4", "copy", "(FFFF)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$TextureFace;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getU", "getV", "getWidth", "getHeight", "rtx.kimiko:kimiko"})
    private static final class TextureFace {
        private final float u;
        private final float v;
        private final float width;
        private final float height;

        public TextureFace(float u, float v, float width, float height) {
            this.u = u;
            this.v = v;
            this.width = width;
            this.height = height;
        }

        public final float getU() {
            return this.u;
        }

        public final float getV() {
            return this.v;
        }

        public final float getWidth() {
            return this.width;
        }

        public final float getHeight() {
            return this.height;
        }

        public final float component1() {
            return this.u;
        }

        public final float component2() {
            return this.v;
        }

        public final float component3() {
            return this.width;
        }

        public final float component4() {
            return this.height;
        }

        @NotNull
        public final TextureFace copy(float u, float v, float width, float height) {
            return new TextureFace(u, v, width, height);
        }

        public static /* synthetic */ TextureFace copy$default(TextureFace textureFace, float f, float f2, float f3, float f4, int n, Object object) {
            if ((n & 1) != 0) {
                f = textureFace.u;
            }
            if ((n & 2) != 0) {
                f2 = textureFace.v;
            }
            if ((n & 4) != 0) {
                f3 = textureFace.width;
            }
            if ((n & 8) != 0) {
                f4 = textureFace.height;
            }
            return textureFace.copy(f, f2, f3, f4);
        }

        @NotNull
        public String toString() {
            return "TextureFace(u=" + this.u + ", v=" + this.v + ", width=" + this.width + ", height=" + this.height + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.u);
            result = result * 31 + Float.hashCode(this.v);
            result = result * 31 + Float.hashCode(this.width);
            result = result * 31 + Float.hashCode(this.height);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TextureFace)) {
                return false;
            }
            TextureFace textureFace = (TextureFace)other;
            if (Float.compare(this.u, textureFace.u) != 0) {
                return false;
            }
            if (Float.compare(this.v, textureFace.v) != 0) {
                return false;
            }
            if (Float.compare(this.width, textureFace.width) != 0) {
                return false;
            }
            return Float.compare(this.height, textureFace.height) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\tJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\tJ.\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\t\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "position", "rotation", "scale", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)V", "component1", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "component2", "component3", "copy", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "getPosition", "getRotation", "getScale", "Companion", "rtx.kimiko:kimiko"})
    private static final class Transform {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final Vec3 position;
        @NotNull
        private final Vec3 rotation;
        @NotNull
        private final Vec3 scale;

        public Transform(@NotNull Vec3 position, @NotNull Vec3 rotation, @NotNull Vec3 scale) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter((Object)scale, (String)"scale");
            this.position = position;
            this.rotation = rotation;
            this.scale = scale;
        }

        @NotNull
        public final Vec3 getPosition() {
            return this.position;
        }

        @NotNull
        public final Vec3 getRotation() {
            return this.rotation;
        }

        @NotNull
        public final Vec3 getScale() {
            return this.scale;
        }

        @NotNull
        public final Vec3 component1() {
            return this.position;
        }

        @NotNull
        public final Vec3 component2() {
            return this.rotation;
        }

        @NotNull
        public final Vec3 component3() {
            return this.scale;
        }

        @NotNull
        public final Transform copy(@NotNull Vec3 position, @NotNull Vec3 rotation, @NotNull Vec3 scale) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter((Object)scale, (String)"scale");
            return new Transform(position, rotation, scale);
        }

        public static /* synthetic */ Transform copy$default(Transform transform, Vec3 vec3, Vec3 vec32, Vec3 vec33, int n, Object object) {
            if ((n & 1) != 0) {
                vec3 = transform.position;
            }
            if ((n & 2) != 0) {
                vec32 = transform.rotation;
            }
            if ((n & 4) != 0) {
                vec33 = transform.scale;
            }
            return transform.copy(vec3, vec32, vec33);
        }

        @NotNull
        public String toString() {
            return "Transform(position=" + this.position + ", rotation=" + this.rotation + ", scale=" + this.scale + ")";
        }

        public int hashCode() {
            int result = this.position.hashCode();
            result = result * 31 + this.rotation.hashCode();
            result = result * 31 + this.scale.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Transform)) {
                return false;
            }
            Transform transform = (Transform)other;
            if (!Intrinsics.areEqual((Object)this.position, (Object)transform.position)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.rotation, (Object)transform.rotation)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.scale, (Object)transform.scale);
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;", "first", "second", "", "progress", "lerp", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;F)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Transform;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final Transform lerp(@NotNull Transform first, @NotNull Transform second, float progress) {
                Intrinsics.checkNotNullParameter((Object)first, (String)"first");
                Intrinsics.checkNotNullParameter((Object)second, (String)"second");
                return new Transform(Vec3.Companion.lerp(first.getPosition(), second.getPosition(), progress), Vec3.Companion.lerp(first.getRotation(), second.getRotation(), progress), Vec3.Companion.lerp(first.getScale(), second.getScale(), progress));
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\b\u0018\u0000  2\u00020\u0001:\u0001 B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000fJ.\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001f\u0010\u000f\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "", "", "x", "y", "z", "<init>", "(FFF)V", "other", "add", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "", "isZero", "()Z", "component1", "()F", "component2", "component3", "copy", "(FFF)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getZ", "Companion", "rtx.kimiko:kimiko"})
    private static final class Vec3 {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final float x;
        private final float y;
        private final float z;

        public Vec3(float x, float y, float z) {
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

        @NotNull
        public final Vec3 add(@NotNull Vec3 other) {
            Intrinsics.checkNotNullParameter((Object)other, (String)"other");
            return new Vec3(this.x + other.x, this.y + other.y, this.z + other.z);
        }

        public final boolean isZero() {
            return this.x == 0.0f && this.y == 0.0f && this.z == 0.0f;
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
        public final Vec3 copy(float x, float y, float z) {
            return new Vec3(x, y, z);
        }

        public static /* synthetic */ Vec3 copy$default(Vec3 vec3, float f, float f2, float f3, int n, Object object) {
            if ((n & 1) != 0) {
                f = vec3.x;
            }
            if ((n & 2) != 0) {
                f2 = vec3.y;
            }
            if ((n & 4) != 0) {
                f3 = vec3.z;
            }
            return vec3.copy(f, f2, f3);
        }

        @NotNull
        public String toString() {
            return "Vec3(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
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
            if (!(other instanceof Vec3)) {
                return false;
            }
            Vec3 vec3 = (Vec3)other;
            if (Float.compare(this.x, vec3.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, vec3.y) != 0) {
                return false;
            }
            return Float.compare(this.z, vec3.z) == 0;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "first", "second", "", "progress", "lerp", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;F)Lrtx/kimiko/api/modules/impl/Visuals/customization/ShoulderGoatRenderer$Vec3;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final Vec3 lerp(@NotNull Vec3 first, @NotNull Vec3 second, float progress) {
                Intrinsics.checkNotNullParameter((Object)first, (String)"first");
                Intrinsics.checkNotNullParameter((Object)second, (String)"second");
                return new Vec3(first.getX() + (second.getX() - first.getX()) * progress, first.getY() + (second.getY() - first.getY()) * progress, first.getZ() + (second.getZ() - first.getZ()) * progress);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

