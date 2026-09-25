/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  net.minecraft.util.math.MathHelper
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
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.math.MathHelper;
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
import org.joml.Vector3f;
import org.joml.Vector3fc;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00e4\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0014\u0099\u0001\u009a\u0001\u009b\u0001\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001\u00a0\u0001\u00a1\u0001\u00a2\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u0003J#\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u000f\u0010\u0010J;\u0010\u0018\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\rH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0018\u0010\u0019J)\u0010 \u001a\u00020\u001f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b$\u0010%J\u001f\u0010'\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b'\u0010(J[\u00101\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010.\u001a\u00020\u001f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u001a2\u0006\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b1\u00102J7\u00106\u001a\u00020\b2\u0006\u0010+\u001a\u00020*2\u0006\u00104\u001a\u0002032\u0006\u00106\u001a\u0002052\u0006\u00108\u001a\u0002072\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b6\u00109J/\u0010?\u001a\u00020<2\u0006\u0010;\u001a\u00020:2\u0006\u0010.\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b?\u0010@J-\u0010E\u001a\u00020<2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010D\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\rH\u0002\u00a2\u0006\u0004\bE\u0010FJ-\u0010G\u001a\u00020<2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010D\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\rH\u0002\u00a2\u0006\u0004\bG\u0010FJE\u0010L\u001a\u00020<2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010H\u001a\u00020\u00042\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020:0I2\u0006\u0010.\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020<H\u0002\u00a2\u0006\u0004\bL\u0010MJ'\u0010Q\u001a\u00020<2\u0006\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bQ\u0010RJ'\u0010S\u001a\u00020<2\u0006\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bS\u0010RJ7\u0010X\u001a\u00020<2\u0006\u0010T\u001a\u00020<2\u0006\u0010U\u001a\u00020<2\u0006\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u00020<2\u0006\u0010P\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bX\u0010YJ\u0011\u0010[\u001a\u0004\u0018\u00010ZH\u0002\u00a2\u0006\u0004\b[\u0010\\J\u0011\u0010]\u001a\u0004\u0018\u00010ZH\u0002\u00a2\u0006\u0004\b]\u0010\\JA\u0010d\u001a\u0004\u0018\u00010,2\u0006\u0010_\u001a\u00020^2\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020a0`2\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020,0`H\u0002\u00a2\u0006\u0004\bd\u0010eJ/\u0010j\u001a\u00020\b2\u0006\u0010f\u001a\u00020a2\u0006\u0010g\u001a\u00020,2\u0006\u0010h\u001a\u00020\"2\u0006\u0010i\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bj\u0010kJ\u001f\u0010o\u001a\u00020\b2\u0006\u0010m\u001a\u00020l2\u0006\u0010n\u001a\u00020JH\u0002\u00a2\u0006\u0004\bo\u0010pJ\u0017\u0010q\u001a\u00020<2\u0006\u0010#\u001a\u00020<H\u0002\u00a2\u0006\u0004\bq\u0010rJ\u0017\u0010s\u001a\u00020<2\u0006\u0010#\u001a\u00020<H\u0002\u00a2\u0006\u0004\bs\u0010rJ\u001f\u0010v\u001a\u00020\"2\u0006\u0010t\u001a\u00020a2\u0006\u0010u\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bv\u0010wJ\u001f\u0010x\u001a\u00020\u00042\u0006\u0010t\u001a\u00020a2\u0006\u0010u\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bx\u0010yJ\u0017\u0010{\u001a\u00020<2\u0006\u0010z\u001a\u00020lH\u0002\u00a2\u0006\u0004\b{\u0010|R\u0014\u0010}\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010~R\u001d\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0016\u0010\u0082\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010~R\u0016\u0010\u0083\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010~R\u0018\u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0018\u0010\u0087\u0001\u001a\u00030\u0084\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0086\u0001R\u0017\u0010\u0088\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0017\u0010\u008a\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u0089\u0001R\u0017\u0010\u008b\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u0089\u0001R\u0017\u0010\u008c\u0001\u001a\u00020\u001f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0017\u0010\u008e\u0001\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0017\u0010\u0090\u0001\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u008f\u0001R%\u0010\u0093\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0005\u0012\u00030\u0092\u00010\u0091\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u001b\u0010\u0095\u0001\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0019\u0010\u0097\u0001\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001\u00a8\u0006\u00a3\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer;", "", "<init>", "()V", "", "previous", "pickLoop", "(Ljava/lang/String;)Ljava/lang/String;", "", "Lkotlin/jvm/JvmStatic;", "reset", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "", "active", "shouldRender", "(Lnet/minecraft/AbstractClientPlayerEntity;Z)Z", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "packedLight", "helmet", "render", "(Lnet/minecraft/AbstractClientPlayerEntity;Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;IZ)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Animation;", "animation", "", "now", "startedAt", "", "animationTime", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Animation;JJ)D", "", "value", "smoothstep", "(F)F", "then", "secondsSince", "(JJ)D", "stack", "Lnet/minecraft/VertexConsumer;", "buffer", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Node;", "node", "time", "previousTime", "blend", "renderNode", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Node;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Animation;DLrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Animation;DFI)V", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;", "vertex", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedFace;", "face", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedFace;I)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Channel;", "channel", "", "fallback", "loop", "sample", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Channel;D[FZ)[F", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Key;", "keys", "index", "neighbourBefore", "(Ljava/util/List;IZ)[F", "neighbourAfter", "bone", "Ljava/util/function/Function;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BoneChannels;", "pick", "boneChannel", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Animation;Ljava/lang/String;Ljava/util/function/Function;D[F)[F", "from", "to", "t", "lerpAngles", "([F[FF)[F", "lerp", "p0", "p1", "p2", "p3", "catmullRom", "([F[F[F[FF)[F", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Model;", "getOrLoadModel", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Model;", "loadModel", "Lcom/google/gson/JsonElement;", "element", "", "Lcom/google/gson/JsonObject;", "groupsByUuid", "meshHolders", "buildNode", "(Lcom/google/gson/JsonElement;Ljava/util/Map;Ljava/util/Map;)Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Node;", "mesh", "holder", "uvWidth", "uvHeight", "bakeMesh", "(Lcom/google/gson/JsonObject;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Node;FF)V", "Lcom/google/gson/JsonArray;", "keyframes", "channels", "parseKeyframes", "(Lcom/google/gson/JsonArray;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BoneChannels;)V", "convertRotation", "([F)[F", "convertPosition", "objectObj", "key", "readFloat", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)F", "readString", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "array", "vector", "(Lcom/google/gson/JsonArray;)[F", "LOOP_IDLE", "Ljava/lang/String;", "", "LOOPS", "[Ljava/lang/String;", "SPAWN", "DISPERSE", "Lnet/minecraft/Identifier;", "MODEL", "Lnet/minecraft/Identifier;", "TEXTURE", "Y_LIFT_PIXELS", "F", "HELMET_LIFT_PIXELS", "LIFT_SMOOTHING", "BLEND_SECONDS", "D", "ZERO", "[F", "ONE", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Playback;", "playbacks", "Ljava/util/Map;", "model", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Model;", "loadAttempted", "Z", "Phase", "Playback", "Model", "Node", "BakedVertex", "BakedFace", "Animation", "BoneChannels", "Channel", "Key", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nGoldenHaloRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GoldenHaloRenderer.kt\nrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,655:1\n1#2:656\n*E\n"})
public final class GoldenHaloRenderer {
    @NotNull
    public static final GoldenHaloRenderer INSTANCE = new GoldenHaloRenderer();
    @NotNull
    private static final String LOOP_IDLE = "idle";
    @NotNull
    private static final String[] LOOPS;
    @NotNull
    private static final String SPAWN = "spawn";
    @NotNull
    private static final String DISPERSE = "disperse";
    @NotNull
    private static final Identifier MODEL;
    @NotNull
    private static final Identifier TEXTURE;
    private static final float Y_LIFT_PIXELS = -16.5f;
    private static final float HELMET_LIFT_PIXELS = 3.0f;
    private static final float LIFT_SMOOTHING = 8.0f;
    private static final double BLEND_SECONDS = 0.6;
    @NotNull
    private static final float[] ZERO;
    @NotNull
    private static final float[] ONE;
    @NotNull
    private static final Map<AbstractClientPlayerEntity, Playback> playbacks;
    @Nullable
    private static volatile Model model;
    private static boolean loadAttempted;

    private GoldenHaloRenderer() {
    }

    private final String pickLoop(String previous) {
        if (previous == null) {
            return LOOPS[ThreadLocalRandom.current().nextInt(LOOPS.length)];
        }
        String next = previous;
        for (int attempt = 0; attempt < 4 && Intrinsics.areEqual((Object)next, (Object)previous); ++attempt) {
            next = LOOPS[ThreadLocalRandom.current().nextInt(LOOPS.length)];
        }
        return next;
    }

    @JvmStatic
    public static final void reset() {
        playbacks.clear();
    }

    @JvmStatic
    public static final boolean shouldRender(@NotNull AbstractClientPlayerEntity player, boolean active) {
        Animation disperse;
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        long now = System.nanoTime();
        Playback playback = playbacks.get(player);
        if (active) {
            if (playback == null) {
                playback = new Playback();
                playbacks.put(player, playback);
            } else if (playback.getPhase() == Phase.DISPERSE) {
                playback.enter(Phase.SPAWN, now);
            }
            return true;
        }
        if (playback == null) {
            return false;
        }
        if (playback.getPhase() != Phase.DISPERSE) {
            playback.enter(Phase.DISPERSE, now);
            return true;
        }
        Model loadedModel = INSTANCE.getOrLoadModel();
        double disperseLength = 1.25;
        if (loadedModel != null && (disperse = loadedModel.getAnimations().get(DISPERSE)) != null) {
            disperseLength = disperse.getLength();
        }
        if (INSTANCE.secondsSince(now, playback.getPhaseStartedAt()) >= disperseLength) {
            playbacks.remove(player);
            return false;
        }
        return true;
    }

    @JvmStatic
    public static final void render(@NotNull AbstractClientPlayerEntity player, @NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue collector, int packedLight, boolean helmet) {
        float blend;
        Animation animation;
        Animation animation2;
        Animation active;
        Animation spawn;
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        Model loadedModel = INSTANCE.getOrLoadModel();
        if (loadedModel == null || loadedModel.getRoots().isEmpty()) {
            return;
        }
        long now = System.nanoTime();
        Playback playback = playbacks.get(player);
        if (playback == null) {
            playback = new Playback();
            playbacks.put(player, playback);
        }
        float delta = (float)Math.min(0.1, INSTANCE.secondsSince(now, playback.getLastUpdatedAt()));
        playback.setLastUpdatedAt(now);
        float targetLift = helmet ? 3.0f : 0.0f;
        Playback playback2 = playback;
        playback2.setHelmetLift(playback2.getHelmetLift() + (targetLift - playback.getHelmetLift()) * (1.0f - (float)Math.exp(-8.0f * delta)));
        float extraLiftPixels = playback.getHelmetLift();
        if (playback.getPhase() == Phase.SPAWN && ((spawn = loadedModel.getAnimations().get(SPAWN)) == null || INSTANCE.secondsSince(now, playback.getPhaseStartedAt()) >= spawn.getLength())) {
            playback.enter(Phase.LOOP, now);
        }
        if (playback.getPhase() == Phase.LOOP && (active = loadedModel.getAnimations().get(playback.getLoop())) != null && active.getLength() > 0.0 && INSTANCE.secondsSince(now, playback.getLoopStartedAt()) >= active.getLength()) {
            playback.nextLoop(now);
        }
        if ((animation2 = loadedModel.getAnimations().get(playback.currentAnimation())) == null && playback.getPhase() == Phase.LOOP) {
            animation2 = loadedModel.getAnimations().get(LOOP_IDLE);
        }
        double time = INSTANCE.animationTime(animation2, now, playback.currentStartedAt());
        String string = playback.getPreviousAnimation();
        if (string != null) {
            String it = string;
            boolean bl = false;
            animation = loadedModel.getAnimations().get(it);
        } else {
            animation = null;
        }
        Animation previous = animation;
        double previousTime = INSTANCE.animationTime(previous, playback.getPreviousFrozenAt(), playback.getPreviousStartedAt());
        float f = blend = previous == null ? 1.0f : INSTANCE.smoothstep((float)Math.min(1.0, INSTANCE.secondsSince(now, playback.getTransitionStartedAt()) / 0.6));
        if (blend >= 1.0f) {
            playback.setPreviousAnimation(null);
            previous = null;
        }
        Animation finalAnimation = animation2;
        double finalTime = time;
        Animation finalPrevious = previous;
        double finalPreviousTime = previousTime;
        float finalBlend = blend;
        collector.submitCustom(poseStack, RenderLayers.entityCutoutNoCull((Identifier)TEXTURE), (arg_0, arg_1) -> GoldenHaloRenderer.render$lambda$1(extraLiftPixels, loadedModel, finalAnimation, finalTime, finalPrevious, finalPreviousTime, finalBlend, packedLight, arg_0, arg_1));
    }

    private final double animationTime(Animation animation, long now, long startedAt) {
        if (animation == null || animation.getLength() <= 0.0) {
            return 0.0;
        }
        double elapsed = this.secondsSince(now, startedAt);
        return animation.getLoop() ? elapsed % animation.getLength() : Math.min(elapsed, animation.getLength());
    }

    private final float smoothstep(float value) {
        float clamped = Math.max(0.0f, Math.min(1.0f, value));
        return clamped * clamped * (3.0f - 2.0f * clamped);
    }

    private final double secondsSince(long now, long then) {
        return Math.max(0.0, (double)(now - then) / 1.0E9);
    }

    private final void renderNode(MatrixStack stack, VertexConsumer buffer, Node node, Animation animation, double time, Animation previous, double previousTime, float blend, int packedLight) {
        stack.push();
        float[] animPos = this.boneChannel(animation, node.getName(), GoldenHaloRenderer::renderNode$lambda$0, time, ZERO);
        float[] animRot = this.boneChannel(animation, node.getName(), GoldenHaloRenderer::renderNode$lambda$1, time, ZERO);
        float[] animScale = this.boneChannel(animation, node.getName(), GoldenHaloRenderer::renderNode$lambda$2, time, ONE);
        if (previous != null && blend < 1.0f) {
            animPos = this.lerp(this.boneChannel(previous, node.getName(), GoldenHaloRenderer::renderNode$lambda$3, previousTime, ZERO), animPos, blend);
            animRot = this.lerpAngles(this.boneChannel(previous, node.getName(), GoldenHaloRenderer::renderNode$lambda$4, previousTime, ZERO), animRot, blend);
            animScale = this.lerp(this.boneChannel(previous, node.getName(), GoldenHaloRenderer::renderNode$lambda$5, previousTime, ONE), animScale, blend);
        }
        float px = node.getPivot()[0];
        float py = node.getPivot()[1];
        float pz = node.getPivot()[2];
        stack.translate(px + animPos[0], py + animPos[1], pz + animPos[2]);
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(node.getRotation()[2] + animRot[2]));
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(node.getRotation()[0] + animRot[0]));
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(node.getRotation()[1] + animRot[1]));
        stack.scale(animScale[0], animScale[1], animScale[2]);
        stack.translate(-px, -py, -pz);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        for (BakedFace face : node.getFaces()) {
            this.vertex(buffer, pose, face.getA(), face, packedLight);
            this.vertex(buffer, pose, face.getB(), face, packedLight);
            this.vertex(buffer, pose, face.getC(), face, packedLight);
            this.vertex(buffer, pose, face.getD(), face, packedLight);
        }
        for (Node child : node.getChildren()) {
            this.renderNode(stack, buffer, child, animation, time, previous, previousTime, blend, packedLight);
        }
        stack.pop();
    }

    private final void vertex(VertexConsumer buffer, MatrixStack.Entry pose, BakedVertex vertex, BakedFace face, int packedLight) {
        buffer.vertex(pose, vertex.getX(), vertex.getY(), vertex.getZ()).color(-1).texture(vertex.getU(), vertex.getV()).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(pose, face.getNx(), face.getNy(), face.getNz());
    }

    private final float[] sample(Channel channel, double time, float[] fallback, boolean loop) {
        float t;
        int index;
        List<Key> keys = channel.getKeys();
        if (keys.isEmpty()) {
            return fallback;
        }
        if (keys.size() == 1 || time <= keys.get(0).getTime()) {
            return keys.get(0).getValue();
        }
        Key last = keys.get(keys.size() - 1);
        if (time >= last.getTime()) {
            return last.getValue();
        }
        for (index = 0; index < keys.size() - 2 && keys.get(index + 1).getTime() <= time; ++index) {
        }
        Key from = keys.get(index);
        Key to = keys.get(index + 1);
        double span = to.getTime() - from.getTime();
        float f = t = span <= 0.0 ? 0.0f : (float)((time - from.getTime()) / span);
        if (from.getSmooth() || to.getSmooth()) {
            return this.catmullRom(this.neighbourBefore(keys, index, loop), from.getValue(), to.getValue(), this.neighbourAfter(keys, index, loop), t);
        }
        return this.lerp(from.getValue(), to.getValue(), t);
    }

    private final float[] neighbourBefore(List<Key> keys, int index, boolean loop) {
        if (index > 0) {
            return keys.get(index - 1).getValue();
        }
        return loop && keys.size() >= 3 ? keys.get(keys.size() - 2).getValue() : keys.get(0).getValue();
    }

    private final float[] neighbourAfter(List<Key> keys, int index, boolean loop) {
        int after = index + 2;
        if (after <= keys.size() - 1) {
            return keys.get(after).getValue();
        }
        return loop && keys.size() >= 3 ? keys.get(1).getValue() : keys.get(keys.size() - 1).getValue();
    }

    private final float[] boneChannel(Animation animation, String bone, Function<BoneChannels, Channel> pick, double time, float[] fallback) {
        if (animation == null || animation.getBones() == null) {
            return fallback;
        }
        BoneChannels channels = animation.getBones().get(bone);
        if (channels == null) {
            return fallback;
        }
        Channel channel = pick.apply(channels);
        Intrinsics.checkNotNullExpressionValue((Object)channel, (String)"apply(...)");
        return this.sample(channel, time, fallback, animation.getLoop());
    }

    private final float[] lerpAngles(float[] from, float[] to, float t) {
        float[] fArray = new float[]{from[0] + MathHelper.wrapDegrees((float)(to[0] - from[0])) * t, from[1] + MathHelper.wrapDegrees((float)(to[1] - from[1])) * t, from[2] + MathHelper.wrapDegrees((float)(to[2] - from[2])) * t};
        return fArray;
    }

    private final float[] lerp(float[] from, float[] to, float t) {
        float[] fArray = new float[]{from[0] + (to[0] - from[0]) * t, from[1] + (to[1] - from[1]) * t, from[2] + (to[2] - from[2]) * t};
        return fArray;
    }

    private final float[] catmullRom(float[] p0, float[] p1, float[] p2, float[] p3, float t) {
        float[] result = new float[3];
        float t2 = t * t;
        float t3 = t2 * t;
        for (int axis = 0; axis < 3; ++axis) {
            result[axis] = 0.5f * (2.0f * p1[axis] + (-p0[axis] + p2[axis]) * t + (2.0f * p0[axis] - 5.0f * p1[axis] + 4.0f * p2[axis] - p3[axis]) * t2 + (-p0[axis] + 3.0f * p1[axis] - 3.0f * p2[axis] + p3[axis]) * t3);
        }
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final Model getOrLoadModel() {
        Model loaded = model;
        if (loaded != null || loadAttempted) {
            return loaded;
        }
        GoldenHaloRenderer goldenHaloRenderer = this;
        synchronized (goldenHaloRenderer) {
            boolean bl = false;
            if (model == null && !loadAttempted) {
                loadAttempted = true;
                model = INSTANCE.loadModel();
            }
            Model model = GoldenHaloRenderer.model;
            return model;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final Model loadModel() {
        try {
            Object element;
            Object element2;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            Optional optional = minecraft.getResourceManager().getResource(MODEL);
            Intrinsics.checkNotNullExpressionValue((Object)optional, (String)"getResource(...)");
            Optional resource = optional;
            if (resource.isEmpty()) {
                return null;
            }
            JsonObject root = null;
            Closeable closeable = ((Resource)resource.get()).getInputStream();
            Throwable throwable = null;
            try {
                InputStream stream = (InputStream)closeable;
                boolean bl = false;
                JsonObject jsonObject = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                root = jsonObject;
// stream = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                throwable = bl;
                throw bl;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
            JsonObject resolution = root.getAsJsonObject("resolution");
            float uvWidth = resolution.get("width").getAsFloat();
            float uvHeight = resolution.get("height").getAsFloat();
            HashMap<String, Node> meshHolders = new HashMap<String, Node>();
            for (JsonElement el : root.getAsJsonArray("elements")) {
                JsonObject mesh = el.getAsJsonObject();
                if (!Intrinsics.areEqual((Object)"mesh", (Object)this.readString(mesh, "type"))) continue;
                Node holder = new Node(this.readString(mesh, "name"), ZERO, ZERO);
                this.bakeMesh(mesh, holder, uvWidth, uvHeight);
                meshHolders.put(this.readString(mesh, "uuid"), holder);
            }
            HashMap<String, JsonObject> groupsByUuid = new HashMap<String, JsonObject>();
            for (JsonElement el : root.getAsJsonArray("groups")) {
                JsonObject group = el.getAsJsonObject();
                groupsByUuid.put(this.readString(group, "uuid"), group);
            }
            ArrayList<Node> roots = new ArrayList<Node>();
            for (JsonElement el : root.getAsJsonArray("outliner")) {
                Node node = this.buildNode(el, groupsByUuid, meshHolders);
                if (node == null) continue;
                roots.add(node);
            }
            HashMap animations = new HashMap();
            Iterator iterator5 = root.getAsJsonArray("animations").iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator5, (String)"iterator(...)");
            Iterator iterator6 = iterator5;
            while (iterator6.hasNext()) {
                JsonElement element4 = (JsonElement)iterator6.next();
                JsonObject animationJson = element4.getAsJsonObject();
                double d = animationJson.get("length").getAsDouble();
                Intrinsics.checkNotNull((Object)animationJson);
                Animation animation = new Animation(d, Intrinsics.areEqual((Object)"loop", (Object)this.readString(animationJson, "loop")));
                JsonObject animators = animationJson.getAsJsonObject("animators");
                if (animators != null) {
                    for (String uuid : animators.keySet()) {
                        JsonObject animator = animators.getAsJsonObject(uuid);
                        if (!animator.has("keyframes")) continue;
                        BoneChannels channels = new BoneChannels();
                        JsonArray jsonArray = animator.getAsJsonArray("keyframes");
                        Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
                        this.parseKeyframes(jsonArray, channels);
                        channels.getPosition().sort();
                        channels.getRotation().sort();
                        channels.getScale().sort();
                        Map<String, BoneChannels> map = animation.getBones();
                        Intrinsics.checkNotNull((Object)animator);
                        map.put(this.readString(animator, "name"), channels);
                    }
                }
                ((Map)animations).put(this.readString(animationJson, "name"), animation);
            }
            return new Model((List<Node>)roots, animations);
        }
        catch (Exception ignored) {
            return null;
        }
    }

    private final Node buildNode(JsonElement element, Map<String, JsonObject> groupsByUuid, Map<String, Node> meshHolders) {
        float[] fArray;
        if (element.isJsonPrimitive()) {
            return meshHolders.get(element.getAsString());
        }
        if (!element.isJsonObject()) {
            return null;
        }
        JsonObject outlinerNode = element.getAsJsonObject();
        Intrinsics.checkNotNull((Object)outlinerNode);
        String uuid = this.readString(outlinerNode, "uuid");
        JsonObject group = groupsByUuid.get(uuid);
        float[] pivot;
        if (group != null && group.has("origin")) {
            JsonArray jsonArray = group.getAsJsonArray("origin");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            pivot = this.vector(jsonArray);
        } else {
            pivot = (float[])ZERO.clone();
        }
        if (group != null && group.has("rotation")) {
            JsonArray jsonArray = group.getAsJsonArray("rotation");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            fArray = this.convertRotation(this.vector(jsonArray));
        } else {
            fArray = (float[])ZERO.clone();
        }
        float[] rotation = fArray;
        JsonObject jsonObject = group;
        String name = jsonObject != null ? this.readString(jsonObject, "name") : uuid;
        Node node = new Node(name, pivot, rotation);
        if (outlinerNode.has("children")) {
            Iterator iterator = outlinerNode.getAsJsonArray("children").iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                JsonElement child = (JsonElement)iterator2.next();
                Intrinsics.checkNotNull((Object)child);
                Node childNode = this.buildNode(child, groupsByUuid, meshHolders);
                if (childNode == null) continue;
                node.getChildren().add(childNode);
            }
        }
        return node;
    }

    private final void bakeMesh(JsonObject mesh, Node holder, float uvWidth, float uvHeight) {
        float[] fArray;
        if (mesh.has("origin")) {
            JsonArray jsonArray = mesh.getAsJsonArray("origin");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            fArray = this.vector(jsonArray);
        } else {
            fArray = (float[])ZERO.clone();
        }
        float[] origin = fArray;
        HashMap positions = new HashMap();
        JsonObject vertices = mesh.getAsJsonObject("vertices");
        for (String key : vertices.keySet()) {
            JsonArray jsonArray = vertices.getAsJsonArray(key);
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            float[] relative = this.vector(jsonArray);
            Map map = positions;
            float[] fArray2 = new float[]{origin[0] + relative[0], origin[1] + relative[1], origin[2] + relative[2]};
            map.put(key, fArray2);
        }
        JsonObject faces = mesh.getAsJsonObject("faces");
        for (String faceKey : faces.keySet()) {
            JsonObject face = faces.getAsJsonObject(faceKey);
            JsonArray order = face.getAsJsonArray("vertices");
            if (order.size() < 3) continue;
            JsonObject uv = face.getAsJsonObject("uv");
            BakedVertex[] baked = new BakedVertex[4];
            for (int i = 0; i < 4; ++i) {
                String vertexKey = order.get(Math.min(i, order.size() - 1)).getAsString();
                float[] position = (float[])positions.get(vertexKey);
                JsonArray vertexUv = uv.getAsJsonArray(vertexKey);
                if (position == null || vertexUv == null) {
                    baked = null;
                    break;
                }
                baked[i] = new BakedVertex(position[0], position[1], position[2], vertexUv.get(0).getAsFloat() / uvWidth, vertexUv.get(1).getAsFloat() / uvHeight);
            }
            if (baked == null || baked[0] == null || baked[1] == null || baked[2] == null || baked[3] == null) continue;
            BakedVertex b0 = baked[0];
            BakedVertex b1 = baked[1];
            BakedVertex b2 = baked[2];
            BakedVertex b3 = baked[3];
            Vector3f edge1 = new Vector3f(b1.getX() - b0.getX(), b1.getY() - b0.getY(), b1.getZ() - b0.getZ());
            Vector3f edge2 = new Vector3f(b2.getX() - b0.getX(), b2.getY() - b0.getY(), b2.getZ() - b0.getZ());
            Vector3f normal = edge1.cross((Vector3fc)edge2);
            Vector3f vector3f = normal.lengthSquared() < 1.0E-8f ? normal.set(0.0f, 1.0f, 0.0f) : normal.normalize();
            holder.getFaces().add(new BakedFace(b0, b1, b2, b3, normal.x, normal.y, normal.z));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void parseKeyframes(JsonArray keyframes, BoneChannels channels) {
        Iterator iterator = keyframes.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (true) {
            block8: {
                boolean smooth;
                double time;
                float[] value;
                block9: {
                    if (!iterator2.hasNext()) {
                        return;
                    }
                    JsonElement element = (JsonElement)iterator2.next();
                    JsonObject keyframe = element.getAsJsonObject();
                    Intrinsics.checkNotNull((Object)keyframe);
                    String channelName = this.readString(keyframe, "channel");
                    JsonArray dataPoints = keyframe.getAsJsonArray("data_points");
                    if (dataPoints == null || dataPoints.isEmpty()) continue;
                    JsonObject dataPoint = dataPoints.get(0).getAsJsonObject();
                    float[] fArray = new float[3];
                    Intrinsics.checkNotNull((Object)dataPoint);
                    fArray[0] = this.readFloat(dataPoint, "x");
                    fArray[1] = this.readFloat(dataPoint, "y");
                    fArray[2] = this.readFloat(dataPoint, "z");
                    value = fArray;
                    time = keyframe.get("time").getAsDouble();
                    smooth = Intrinsics.areEqual((Object)"catmullrom", (Object)this.readString(keyframe, "interpolation"));
                    String string = channelName;
                    switch (string.hashCode()) {
                        case -40300674: {
                            if (string.equals("rotation")) break;
                            break block8;
                        }
                        case 109250890: {
                            if (string.equals("scale")) break block9;
                            break block8;
                        }
                        case 0x2C929929: {
                            if (string.equals("position")) {
                                channels.getPosition().getKeys().add(new Key(time, this.convertPosition(value), smooth));
                            }
                            break block8;
                        }
                    }
                    channels.getRotation().getKeys().add(new Key(time, this.convertRotation(value), smooth));
                    continue;
                }
                channels.getScale().getKeys().add(new Key(time, value, smooth));
            }
        }
    }

    private final float[] convertRotation(float[] value) {
        float[] fArray = new float[]{-value[0], -value[1], value[2]};
        return fArray;
    }

    private final float[] convertPosition(float[] value) {
        float[] fArray = new float[]{-value[0], value[1], value[2]};
        return fArray;
    }

    private final float readFloat(JsonObject objectObj, String key) {
        float f;
        if (!objectObj.has(key)) {
            return 0.0f;
        }
        try {
            String string = objectObj.get(key).getAsString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
            f = Float.parseFloat(string);
        }
        catch (NumberFormatException ignored) {
            f = 0.0f;
        }
        return f;
    }

    private final String readString(JsonObject objectObj, String key) {
        String string;
        if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
            String string2 = objectObj.get(key).getAsString();
            string = string2;
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
        } else {
            string = "";
        }
        return string;
    }

    private final float[] vector(JsonArray array) {
        float[] fArray = new float[]{array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat()};
        return fArray;
    }

    private static final void render$lambda$1(float $extraLiftPixels, Model $loadedModel, Animation $finalAnimation, double $finalTime, Animation $finalPrevious, double $finalPreviousTime, float $finalBlend, int $packedLight, MatrixStack.Entry pose, VertexConsumer buffer) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)buffer, (String)"buffer");
        MatrixStack local = new MatrixStack();
        local.peek().getPositionMatrix().set((Matrix4fc)pose.getPositionMatrix());
        local.peek().getNormalMatrix().set((Matrix3fc)pose.getNormalMatrix());
        local.scale(0.0625f, -0.0625f, 0.0625f);
        local.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        local.translate(0.0f, -16.5f + $extraLiftPixels, 0.0f);
        for (Node root : $loadedModel.getRoots()) {
            INSTANCE.renderNode(local, buffer, root, $finalAnimation, $finalTime, $finalPrevious, $finalPreviousTime, $finalBlend, $packedLight);
        }
    }

    private static final Channel renderNode$lambda$0(BoneChannels c) {
        Intrinsics.checkNotNullParameter((Object)c, (String)"c");
        return c.getPosition();
    }

    private static final Channel renderNode$lambda$1(BoneChannels c) {
        Intrinsics.checkNotNullParameter((Object)c, (String)"c");
        return c.getRotation();
    }

    private static final Channel renderNode$lambda$2(BoneChannels c) {
        Intrinsics.checkNotNullParameter((Object)c, (String)"c");
        return c.getScale();
    }

    private static final Channel renderNode$lambda$3(BoneChannels c) {
        Intrinsics.checkNotNullParameter((Object)c, (String)"c");
        return c.getPosition();
    }

    private static final Channel renderNode$lambda$4(BoneChannels c) {
        Intrinsics.checkNotNullParameter((Object)c, (String)"c");
        return c.getRotation();
    }

    private static final Channel renderNode$lambda$5(BoneChannels c) {
        Intrinsics.checkNotNullParameter((Object)c, (String)"c");
        return c.getScale();
    }

    static {
        LOOPS = new String[]{LOOP_IDLE, "orbit", "pulse"};
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/halo/golden_shard_halo.bbmodel");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        MODEL = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/halo/golden_shard_halo.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier3;
        ZERO = new float[]{0.0f, 0.0f, 0.0f};
        ONE = new float[]{1.0f, 1.0f, 1.0f};
        playbacks = new WeakHashMap();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\rR#\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e8\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Animation;", "", "", "length", "", "loop", "<init>", "(DZ)V", "D", "getLength", "()D", "Z", "getLoop", "()Z", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BoneChannels;", "bones", "Ljava/util/Map;", "getBones", "()Ljava/util/Map;", "rtx.kimiko:kimiko"})
    private static final class Animation {
        private final double length;
        private final boolean loop;
        @NotNull
        private final Map<String, BoneChannels> bones;

        public Animation(double length, boolean loop) {
            this.length = length;
            this.loop = loop;
            this.bones = new HashMap();
        }

        public final double getLength() {
            return this.length;
        }

        public final boolean getLoop() {
            return this.loop;
        }

        @NotNull
        public final Map<String, BoneChannels> getBones() {
            return this.bones;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0013JV\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010 \u001a\u00020\u001fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b$\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b&\u0010\u000eR\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b(\u0010\u0013R\u0017\u0010\t\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b)\u0010\u0013R\u0017\u0010\n\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\n\u0010'\u001a\u0004\b*\u0010\u0013\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedFace;", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;", "a", "b", "c", "d", "", "nx", "ny", "nz", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;FFF)V", "component1", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;", "component2", "component3", "component4", "component5", "()F", "component6", "component7", "copy", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;FFF)Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedFace;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;", "getA", "getB", "getC", "getD", "F", "getNx", "getNy", "getNz", "rtx.kimiko:kimiko"})
    private static final class BakedFace {
        @NotNull
        private final BakedVertex a;
        @NotNull
        private final BakedVertex b;
        @NotNull
        private final BakedVertex c;
        @NotNull
        private final BakedVertex d;
        private final float nx;
        private final float ny;
        private final float nz;

        public BakedFace(@NotNull BakedVertex a, @NotNull BakedVertex b, @NotNull BakedVertex c, @NotNull BakedVertex d, float nx, float ny, float nz) {
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
        public final BakedVertex getA() {
            return this.a;
        }

        @NotNull
        public final BakedVertex getB() {
            return this.b;
        }

        @NotNull
        public final BakedVertex getC() {
            return this.c;
        }

        @NotNull
        public final BakedVertex getD() {
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
        public final BakedVertex component1() {
            return this.a;
        }

        @NotNull
        public final BakedVertex component2() {
            return this.b;
        }

        @NotNull
        public final BakedVertex component3() {
            return this.c;
        }

        @NotNull
        public final BakedVertex component4() {
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
        public final BakedFace copy(@NotNull BakedVertex a, @NotNull BakedVertex b, @NotNull BakedVertex c, @NotNull BakedVertex d, float nx, float ny, float nz) {
            Intrinsics.checkNotNullParameter((Object)a, (String)"a");
            Intrinsics.checkNotNullParameter((Object)b, (String)"b");
            Intrinsics.checkNotNullParameter((Object)c, (String)"c");
            Intrinsics.checkNotNullParameter((Object)d, (String)"d");
            return new BakedFace(a, b, c, d, nx, ny, nz);
        }

        public static /* synthetic */ BakedFace copy$default(BakedFace bakedFace, BakedVertex bakedVertex, BakedVertex bakedVertex2, BakedVertex bakedVertex3, BakedVertex bakedVertex4, float f, float f2, float f3, int n, Object object) {
            if ((n & 1) != 0) {
                bakedVertex = bakedFace.a;
            }
            if ((n & 2) != 0) {
                bakedVertex2 = bakedFace.b;
            }
            if ((n & 4) != 0) {
                bakedVertex3 = bakedFace.c;
            }
            if ((n & 8) != 0) {
                bakedVertex4 = bakedFace.d;
            }
            if ((n & 0x10) != 0) {
                f = bakedFace.nx;
            }
            if ((n & 0x20) != 0) {
                f2 = bakedFace.ny;
            }
            if ((n & 0x40) != 0) {
                f3 = bakedFace.nz;
            }
            return bakedFace.copy(bakedVertex, bakedVertex2, bakedVertex3, bakedVertex4, f, f2, f3);
        }

        @NotNull
        public String toString() {
            return "BakedFace(a=" + this.a + ", b=" + this.b + ", c=" + this.c + ", d=" + this.d + ", nx=" + this.nx + ", ny=" + this.ny + ", nz=" + this.nz + ")";
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
            if (!(other instanceof BakedFace)) {
                return false;
            }
            BakedFace bakedFace = (BakedFace)other;
            if (!Intrinsics.areEqual((Object)this.a, (Object)bakedFace.a)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.b, (Object)bakedFace.b)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.c, (Object)bakedFace.c)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.d, (Object)bakedFace.d)) {
                return false;
            }
            if (Float.compare(this.nx, bakedFace.nx) != 0) {
                return false;
            }
            if (Float.compare(this.ny, bakedFace.ny) != 0) {
                return false;
            }
            return Float.compare(this.nz, bakedFace.nz) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000bJB\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001e\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001f\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b \u0010\u000bR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b!\u0010\u000b\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;", "", "", "x", "y", "z", "u", "v", "<init>", "(FFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "copy", "(FFFFF)Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedVertex;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getZ", "getU", "getV", "rtx.kimiko:kimiko"})
    private static final class BakedVertex {
        private final float x;
        private final float y;
        private final float z;
        private final float u;
        private final float v;

        public BakedVertex(float x, float y, float z, float u, float v) {
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
        public final BakedVertex copy(float x, float y, float z, float u, float v) {
            return new BakedVertex(x, y, z, u, v);
        }

        public static /* synthetic */ BakedVertex copy$default(BakedVertex bakedVertex, float f, float f2, float f3, float f4, float f5, int n, Object object) {
            if ((n & 1) != 0) {
                f = bakedVertex.x;
            }
            if ((n & 2) != 0) {
                f2 = bakedVertex.y;
            }
            if ((n & 4) != 0) {
                f3 = bakedVertex.z;
            }
            if ((n & 8) != 0) {
                f4 = bakedVertex.u;
            }
            if ((n & 0x10) != 0) {
                f5 = bakedVertex.v;
            }
            return bakedVertex.copy(f, f2, f3, f4, f5);
        }

        @NotNull
        public String toString() {
            return "BakedVertex(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", u=" + this.u + ", v=" + this.v + ")";
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
            if (!(other instanceof BakedVertex)) {
                return false;
            }
            BakedVertex bakedVertex = (BakedVertex)other;
            if (Float.compare(this.x, bakedVertex.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, bakedVertex.y) != 0) {
                return false;
            }
            if (Float.compare(this.z, bakedVertex.z) != 0) {
                return false;
            }
            if (Float.compare(this.u, bakedVertex.u) != 0) {
                return false;
            }
            return Float.compare(this.v, bakedVertex.v) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BoneChannels;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Channel;", "position", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Channel;", "getPosition", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Channel;", "rotation", "getRotation", "scale", "getScale", "rtx.kimiko:kimiko"})
    private static final class BoneChannels {
        @NotNull
        private final Channel position = new Channel();
        @NotNull
        private final Channel rotation = new Channel();
        @NotNull
        private final Channel scale = new Channel();

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
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Channel;", "", "<init>", "()V", "", "sort", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Key;", "keys", "Ljava/util/List;", "getKeys", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
    private static final class Channel {
        @NotNull
        private final List<Key> keys = new ArrayList();

        @NotNull
        public final List<Key> getKeys() {
            return this.keys;
        }

        public final void sort() {
            this.keys.sort(Comparator.comparingDouble(Key::getTime));
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0013\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b \u0010\u000f\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Key;", "", "", "time", "", "value", "", "smooth", "<init>", "(D[FZ)V", "component1", "()D", "component2", "()[F", "component3", "()Z", "copy", "(D[FZ)Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Key;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "D", "getTime", "[F", "getValue", "Z", "getSmooth", "rtx.kimiko:kimiko"})
    private static final class Key {
        private final double time;
        @NotNull
        private final float[] value;
        private final boolean smooth;

        public Key(double time, @NotNull float[] value, boolean smooth) {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.time = time;
            this.value = value;
            this.smooth = smooth;
        }

        public final double getTime() {
            return this.time;
        }

        @NotNull
        public final float[] getValue() {
            return this.value;
        }

        public final boolean getSmooth() {
            return this.smooth;
        }

        public final double component1() {
            return this.time;
        }

        @NotNull
        public final float[] component2() {
            return this.value;
        }

        public final boolean component3() {
            return this.smooth;
        }

        @NotNull
        public final Key copy(double time, @NotNull float[] value, boolean smooth) {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            return new Key(time, value, smooth);
        }

        public static /* synthetic */ Key copy$default(Key key, double d, float[] fArray, boolean bl, int n, Object object) {
            if ((n & 1) != 0) {
                d = key.time;
            }
            if ((n & 2) != 0) {
                fArray = key.value;
            }
            if ((n & 4) != 0) {
                bl = key.smooth;
            }
            return key.copy(d, fArray, bl);
        }

        @NotNull
        public String toString() {
            return "Key(time=" + this.time + ", value=" + Arrays.toString(this.value) + ", smooth=" + this.smooth + ")";
        }

        public int hashCode() {
            int result = Double.hashCode(this.time);
            result = result * 31 + Arrays.hashCode(this.value);
            result = result * 31 + Boolean.hashCode(this.smooth);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }
            Key key = (Key)other;
            if (Double.compare(this.time, key.time) != 0) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.value, (Object)key.value)) {
                return false;
            }
            return this.smooth == key.smooth;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Model;", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Node;", "roots", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Animation;", "animations", "<init>", "(Ljava/util/List;Ljava/util/Map;)V", "Ljava/util/List;", "getRoots", "()Ljava/util/List;", "Ljava/util/Map;", "getAnimations", "()Ljava/util/Map;", "rtx.kimiko:kimiko"})
    private static final class Model {
        @NotNull
        private final List<Node> roots;
        @NotNull
        private final Map<String, Animation> animations;

        public Model(@NotNull List<Node> roots, @NotNull Map<String, Animation> animations) {
            Intrinsics.checkNotNullParameter(roots, (String)"roots");
            Intrinsics.checkNotNullParameter(animations, (String)"animations");
            this.roots = roots;
            this.animations = animations;
        }

        @NotNull
        public final List<Node> getRoots() {
            return this.roots;
        }

        @NotNull
        public final Map<String, Animation> getAnimations() {
            return this.animations;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\u000f\u0010\u000eR\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Node;", "", "", "name", "", "pivot", "rotation", "<init>", "(Ljava/lang/String;[F[F)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "[F", "getPivot", "()[F", "getRotation", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$BakedFace;", "faces", "Ljava/util/List;", "getFaces", "()Ljava/util/List;", "children", "getChildren", "rtx.kimiko:kimiko"})
    private static final class Node {
        @NotNull
        private final String name;
        @NotNull
        private final float[] pivot;
        @NotNull
        private final float[] rotation;
        @NotNull
        private final List<BakedFace> faces;
        @NotNull
        private final List<Node> children;

        public Node(@NotNull String name, @NotNull float[] pivot, @NotNull float[] rotation) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)pivot, (String)"pivot");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            this.name = name;
            this.pivot = pivot;
            this.rotation = rotation;
            this.faces = new ArrayList();
            this.children = new ArrayList();
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final float[] getPivot() {
            return this.pivot;
        }

        @NotNull
        public final float[] getRotation() {
            return this.rotation;
        }

        @NotNull
        public final List<BakedFace> getFaces() {
            return this.faces;
        }

        @NotNull
        public final List<Node> getChildren() {
            return this.children;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Phase;", "", "<init>", "(Ljava/lang/String;I)V", "SPAWN", "LOOP", "DISPERSE", "rtx.kimiko:kimiko"})
    private static enum Phase {
        SPAWN,
        LOOP,
        DISPERSE;
@NotNull
        public static EnumEntries<Phase> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\u0007\n\u0002\b\u0013\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\fR\"\u0010 \u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010$R\"\u0010%\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010\u001d\u001a\u0004\b&\u0010\u0015\"\u0004\b'\u0010\fR\"\u0010(\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010\u001d\u001a\u0004\b)\u0010\u0015\"\u0004\b*\u0010\fR\"\u0010,\u001a\u00020+8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u00102\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b2\u0010!\u001a\u0004\b3\u0010\u0013\"\u0004\b4\u0010$R\"\u00105\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u0010\u001d\u001a\u0004\b6\u0010\u0015\"\u0004\b7\u0010\fR\"\u00108\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b8\u0010\u001d\u001a\u0004\b9\u0010\u0015\"\u0004\b:\u0010\fR\"\u0010;\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b;\u0010\u001d\u001a\u0004\b<\u0010\u0015\"\u0004\b=\u0010\f\u00a8\u0006>"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Playback;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Phase;", "next", "", "now", "", "enter", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Phase;J)V", "nextLoop", "(J)V", "", "from", "fromStartedAt", "beginTransition", "(Ljava/lang/String;JJ)V", "currentAnimation", "()Ljava/lang/String;", "currentStartedAt", "()J", "phase", "Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Phase;", "getPhase", "()Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Phase;", "setPhase", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/GoldenHaloRenderer$Phase;)V", "phaseStartedAt", "J", "getPhaseStartedAt", "setPhaseStartedAt", "loop", "Ljava/lang/String;", "getLoop", "setLoop", "(Ljava/lang/String;)V", "loopStartedAt", "getLoopStartedAt", "setLoopStartedAt", "lastUpdatedAt", "getLastUpdatedAt", "setLastUpdatedAt", "", "helmetLift", "F", "getHelmetLift", "()F", "setHelmetLift", "(F)V", "previousAnimation", "getPreviousAnimation", "setPreviousAnimation", "previousStartedAt", "getPreviousStartedAt", "setPreviousStartedAt", "previousFrozenAt", "getPreviousFrozenAt", "setPreviousFrozenAt", "transitionStartedAt", "getTransitionStartedAt", "setTransitionStartedAt", "rtx.kimiko:kimiko"})
    private static final class Playback {
        @NotNull
        private Phase phase = Phase.SPAWN;
        private long phaseStartedAt = System.nanoTime();
        @NotNull
        private String loop = INSTANCE.pickLoop(null);
        private long loopStartedAt = this.phaseStartedAt;
        private long lastUpdatedAt = this.phaseStartedAt;
        private float helmetLift;
        @Nullable
        private String previousAnimation;
        private long previousStartedAt;
        private long previousFrozenAt;
        private long transitionStartedAt;

        @NotNull
        public final Phase getPhase() {
            return this.phase;
        }

        public final void setPhase(@NotNull Phase phase) {
            Intrinsics.checkNotNullParameter((Object)((Object)phase), (String)"<set-?>");
            this.phase = phase;
        }

        public final long getPhaseStartedAt() {
            return this.phaseStartedAt;
        }

        public final void setPhaseStartedAt(long l) {
            this.phaseStartedAt = l;
        }

        @NotNull
        public final String getLoop() {
            return this.loop;
        }

        public final void setLoop(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.loop = string;
        }

        public final long getLoopStartedAt() {
            return this.loopStartedAt;
        }

        public final void setLoopStartedAt(long l) {
            this.loopStartedAt = l;
        }

        public final long getLastUpdatedAt() {
            return this.lastUpdatedAt;
        }

        public final void setLastUpdatedAt(long l) {
            this.lastUpdatedAt = l;
        }

        public final float getHelmetLift() {
            return this.helmetLift;
        }

        public final void setHelmetLift(float f) {
            this.helmetLift = f;
        }

        @Nullable
        public final String getPreviousAnimation() {
            return this.previousAnimation;
        }

        public final void setPreviousAnimation(@Nullable String string) {
            this.previousAnimation = string;
        }

        public final long getPreviousStartedAt() {
            return this.previousStartedAt;
        }

        public final void setPreviousStartedAt(long l) {
            this.previousStartedAt = l;
        }

        public final long getPreviousFrozenAt() {
            return this.previousFrozenAt;
        }

        public final void setPreviousFrozenAt(long l) {
            this.previousFrozenAt = l;
        }

        public final long getTransitionStartedAt() {
            return this.transitionStartedAt;
        }

        public final void setTransitionStartedAt(long l) {
            this.transitionStartedAt = l;
        }

        public final void enter(@NotNull Phase next, long now) {
            Intrinsics.checkNotNullParameter((Object)((Object)next), (String)"next");
            this.beginTransition(this.currentAnimation(), this.currentStartedAt(), now);
            this.phase = next;
            this.phaseStartedAt = now;
            if (next == Phase.LOOP) {
                this.loop = INSTANCE.pickLoop(null);
                this.loopStartedAt = now;
            }
        }

        public final void nextLoop(long now) {
            this.beginTransition(this.loop, this.loopStartedAt, now);
            this.loop = INSTANCE.pickLoop(this.loop);
            this.loopStartedAt = now;
        }

        private final void beginTransition(String from, long fromStartedAt, long now) {
            this.previousAnimation = from;
            this.previousStartedAt = fromStartedAt;
            this.previousFrozenAt = now;
            this.transitionStartedAt = now;
        }

        @NotNull
        public final String currentAnimation() {
            return switch (WhenMappings.$EnumSwitchMapping$0[this.phase.ordinal()]) {
                case 1 -> GoldenHaloRenderer.SPAWN;
                case 2 -> GoldenHaloRenderer.DISPERSE;
                case 3 -> this.loop;
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        public final long currentStartedAt() {
            return this.phase == Phase.LOOP ? this.loopStartedAt : this.phaseStartedAt;
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[Phase.values().length];
                try {
                    nArray[Phase.SPAWN.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Phase.DISPERSE.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Phase.LOOP.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }
}

