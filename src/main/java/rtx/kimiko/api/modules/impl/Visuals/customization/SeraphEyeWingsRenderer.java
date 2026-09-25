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
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ce\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0011\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0010\u00ab\u0001\u00ac\u0001\u00ad\u0001\u00ae\u0001\u00af\u0001\u00b0\u0001\u00b1\u0001\u00b2\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JK\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J7\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ)\u0010#\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b#\u0010$J\u001f\u0010&\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b)\u0010*J}\u00109\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u00062\u0006\u0010-\u001a\u00020,2\u0006\u0010/\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u00010\u001f2\u0006\u00101\u001a\u00020\"2\b\u00102\u001a\u0004\u0018\u00010\u001f2\u0006\u00103\u001a\u00020\"2\u0006\u00104\u001a\u00020\u000e2\b\u00105\u001a\u0004\u0018\u00010\u001f2\u0006\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020\"2\u0006\u00108\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b9\u0010:J/\u0010@\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\u001b2\u0006\u0010>\u001a\u00020\"2\u0006\u0010?\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0017\u0010C\u001a\u00020\n2\u0006\u0010B\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bC\u0010DJ'\u0010E\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bE\u0010FJ)\u0010H\u001a\u00020;2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010=\u001a\u00020\u001b2\u0006\u0010G\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bH\u0010IJ'\u0010L\u001a\u00020;2\u0006\u0010J\u001a\u00020;2\u0006\u0010L\u001a\u00020K2\u0006\u0010G\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bL\u0010MJ'\u00104\u001a\u00020;2\u0006\u0010N\u001a\u00020;2\u0006\u0010O\u001a\u00020;2\u0006\u0010P\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b4\u0010QJ'\u00104\u001a\u00020R2\u0006\u0010N\u001a\u00020R2\u0006\u0010O\u001a\u00020R2\u0006\u0010P\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b4\u0010SJ5\u0010W\u001a\u00020R2\u0014\u0010U\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020R\u0018\u00010T2\u0006\u0010G\u001a\u00020\"2\u0006\u0010V\u001a\u00020RH\u0002\u00a2\u0006\u0004\bW\u0010XJ\u000f\u0010Y\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bY\u0010\u0003J\u0017\u0010\\\u001a\u00020\u00112\u0006\u0010[\u001a\u00020ZH\u0002\u00a2\u0006\u0004\b\\\u0010]J\u0017\u0010`\u001a\u00020_2\u0006\u0010^\u001a\u00020ZH\u0002\u00a2\u0006\u0004\b`\u0010aJ\u0017\u0010b\u001a\u00020\u00112\u0006\u0010[\u001a\u00020ZH\u0002\u00a2\u0006\u0004\bb\u0010]J3\u0010e\u001a\u00020\u00112\u0006\u0010/\u001a\u00020Z2\u0006\u0010c\u001a\u00020\u001b2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020R0TH\u0002\u00a2\u0006\u0004\be\u0010fJ\u0017\u0010i\u001a\u00020R2\u0006\u0010h\u001a\u00020gH\u0002\u00a2\u0006\u0004\bi\u0010jJ\u0017\u0010k\u001a\u00020R2\u0006\u0010h\u001a\u00020gH\u0002\u00a2\u0006\u0004\bk\u0010jJ/\u0010m\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u00062\u0006\u0010-\u001a\u00020,2\u0006\u0010l\u001a\u00020_2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bm\u0010nJ\u00ac\u0001\u0010\u0082\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020,2\u0006\u0010p\u001a\u00020o2\u0006\u0010r\u001a\u00020q2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010s\u001a\u00020\u000e2\u0006\u0010t\u001a\u00020\u000e2\u0006\u0010u\u001a\u00020\u000e2\u0006\u0010v\u001a\u00020\u000e2\u0006\u0010w\u001a\u00020\u000e2\u0006\u0010x\u001a\u00020\u000e2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\u000e2\u0006\u0010{\u001a\u00020\u000e2\u0006\u0010|\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020\u000e2\u0006\u0010~\u001a\u00020\u000e2\u0006\u0010\u007f\u001a\u00020\u000e2\u0007\u0010\u0080\u0001\u001a\u00020\u000e2\u0007\u0010\u0081\u0001\u001a\u00020\u000eH\u0002\u00a2\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001Jq\u0010\u0089\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020,2\u0006\u0010p\u001a\u00020o2\u0007\u0010\u0084\u0001\u001a\u00020\u000e2\u0007\u0010\u0085\u0001\u001a\u00020\u000e2\u0007\u0010\u0086\u0001\u001a\u00020\u000e2\u0007\u0010\u0087\u0001\u001a\u00020\u000e2\u0007\u0010\u0088\u0001\u001a\u00020\u000e2\u0006\u0010\u007f\u001a\u00020\u000e2\u0007\u0010\u0080\u0001\u001a\u00020\u000e2\u0007\u0010\u0081\u0001\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0017\u0010\u008b\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0017\u0010\u008d\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008c\u0001R\u0017\u0010\u008e\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008c\u0001R\u0017\u0010\u008f\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u008c\u0001R\u0017\u0010\u0090\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u008c\u0001R\u0017\u0010\u0091\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u008c\u0001R\u0017\u0010\u0092\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u008c\u0001R\u0017\u0010\u0093\u0001\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u008c\u0001R\u0017\u0010\u0094\u0001\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0017\u0010\u0096\u0001\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0095\u0001R\u0017\u0010\u0097\u0001\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0095\u0001R\u0017\u0010\u0098\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001R\u0018\u0010\u009b\u0001\u001a\u00030\u009a\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0018\u0010\u009d\u0001\u001a\u00030\u009a\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u009c\u0001R\u0018\u0010\u009e\u0001\u001a\u00030\u009a\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009c\u0001R\u0018\u0010\u00a0\u0001\u001a\u00030\u009f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u00a1\u0001R$\u0010\u00a3\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001f0\u00a2\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u00a4\u0001R$\u0010\u00a5\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00150\u00a2\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u00a4\u0001R\u0019\u0010\u00a6\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u00a7\u0001R\u0017\u0010\u00a8\u0001\u001a\u00020R8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u00a9\u0001R\u0017\u0010\u00aa\u0001\u001a\u00020R8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00aa\u0001\u0010\u00a9\u0001\u00a8\u0006\u00b3\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer;", "", "<init>", "()V", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "light", "", "flying", "", "walkSpeed", "attackTime", "", "Lkotlin/jvm/JvmStatic;", "render", "(Lnet/minecraft/AbstractClientPlayerEntity;Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;IZFF)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$PlaybackState;", "state", "", "now", "updateState", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$PlaybackState;JZFF)V", "", "next", "switchAnimation", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$PlaybackState;Ljava/lang/String;J)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$AnimationData;", "animation", "startedAt", "", "animationTime", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$AnimationData;JJ)D", "then", "secondsSince", "(JJ)D", "value", "smoothstep", "(F)F", "stack", "Lnet/minecraft/VertexConsumer;", "buffer", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Bone;", "bone", "current", "currentTime", "previous", "previousTime", "blend", "eye", "eyeTime", "flapPhase", "flapAmplitude", "renderBone", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Bone;Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$AnimationData;DLrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$AnimationData;DFLrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$AnimationData;DDFI)V", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "transform", "boneName", "phase", "amplitude", "applyFlap", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;Ljava/lang/String;DF)Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "hash", "mix32", "(I)I", "applyIndependentEyeAnimation", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;Ljava/lang/String;D)Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "time", "sample", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$AnimationData;Ljava/lang/String;D)Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "base", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$BoneAnimation;", "overlay", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$BoneAnimation;D)Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "from", "to", "amount", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;F)Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "", "([F[FF)[F", "Ljava/util/TreeMap;", "keyframes", "fallback", "interpolate", "(Ljava/util/TreeMap;D[F)[F", "loadModel", "Lcom/google/gson/JsonObject;", "json", "parseGeometry", "(Lcom/google/gson/JsonObject;)V", "obj", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Cube;", "parseCube", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Cube;", "parseAnimations", "name", "output", "parseChannel", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/util/TreeMap;)V", "Lcom/google/gson/JsonArray;", "array", "vector", "(Lcom/google/gson/JsonArray;)[F", "pair", "cube", "drawCube", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Cube;I)V", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$FaceUv;", "uv", "ax", "ay", "az", "bx", "by", "bz", "cx", "cy", "cz", "dx", "dy", "dz", "nx", "ny", "nz", "quad", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$FaceUv;IFFFFFFFFFFFFFFF)V", "x", "y", "z", "u", "v", "vertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFFFFI)V", "PREFIX", "Ljava/lang/String;", "SPAWN", "IDLE", "RUN", "RUN_STOP", "FLY", "ATTACK", "EYE_LIFE", "TEXTURE_WIDTH", "F", "TEXTURE_HEIGHT", "MODEL_SCALE", "BLEND_SECONDS", "D", "Lnet/minecraft/Identifier;", "GEOMETRY_ID", "Lnet/minecraft/Identifier;", "ANIMATIONS_ID", "TEXTURE", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Geometry;", "geometry", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Geometry;", "", "animations", "Ljava/util/Map;", "playbackStates", "loaded", "Z", "ZERO", "[F", "ONE", "Geometry", "Bone", "Cube", "FaceUv", "AnimationData", "BoneAnimation", "PlaybackState", "Transform", "rtx.kimiko:kimiko"})
public final class SeraphEyeWingsRenderer {
    @NotNull
    public static final SeraphEyeWingsRenderer INSTANCE = new SeraphEyeWingsRenderer();
    @NotNull
    private static final String PREFIX = "animation.seraph_eye_wings.";
    @NotNull
    private static final String SPAWN = "animation.seraph_eye_wings.spawn";
    @NotNull
    private static final String IDLE = "animation.seraph_eye_wings.idle";
    @NotNull
    private static final String RUN = "animation.seraph_eye_wings.run";
    @NotNull
    private static final String RUN_STOP = "animation.seraph_eye_wings.run_stop";
    @NotNull
    private static final String FLY = "animation.seraph_eye_wings.fly";
    @NotNull
    private static final String ATTACK = "animation.seraph_eye_wings.attack";
    @NotNull
    private static final String EYE_LIFE = "animation.seraph_eye_wings.eye_life";
    private static final float TEXTURE_WIDTH = 128.0f;
    private static final float TEXTURE_HEIGHT = 128.0f;
    private static final float MODEL_SCALE = 0.62f;
    private static final double BLEND_SECONDS = 0.24;
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
    private static boolean loaded;
    @NotNull
    private static final float[] ZERO;
    @NotNull
    private static final float[] ONE;

    private SeraphEyeWingsRenderer() {
    }

    @JvmStatic
    public static final void render(@NotNull AbstractClientPlayerEntity player, @NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue collector, int light, boolean flying, float walkSpeed, float attackTime) {
        float blend;
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        INSTANCE.loadModel();
        if (geometry.getRootBones().isEmpty()) {
            return;
        }
        long now = System.nanoTime();
        PlaybackState state = playbackStates.computeIfAbsent(player, it -> new PlaybackState(now));
        INSTANCE.updateState(state, now, flying, walkSpeed, attackTime);
        AnimationData current = animations.get(state.getAnimation());
        AnimationData previous = animations.get(state.getPreviousAnimation());
        AnimationData eye = animations.get(EYE_LIFE);
        double currentTime = INSTANCE.animationTime(current, now, state.getAnimationStartedAt());
        double previousTime = INSTANCE.animationTime(previous, now, state.getPreviousStartedAt());
        double eyeTime = INSTANCE.animationTime(eye, now, state.getCreatedAt());
        float f = blend = previous == null ? 1.0f : INSTANCE.smoothstep((float)Math.min(1.0, INSTANCE.secondsSince(now, state.getTransitionStartedAt()) / 0.24));
        if (blend >= 1.0f) {
            state.setPreviousAnimation(null);
            previous = null;
        }
        AnimationData finalPrevious = previous;
        float finalBlend = blend;
        collector.submitCustom(poseStack, RenderLayers.entityCutout((Identifier)TEXTURE), (arg_0, arg_1) -> SeraphEyeWingsRenderer.render$lambda$2(current, currentTime, finalPrevious, previousTime, finalBlend, eye, eyeTime, state, light, arg_0, arg_1));
    }

    private final void updateState(PlaybackState state, long now, boolean flying, float walkSpeed, float attackTime) {
        boolean finished;
        float targetSpeed;
        float dt = (float)Math.min(0.05, this.secondsSince(now, state.getLastUpdatedAt()));
        state.setLastUpdatedAt(now);
        targetSpeed = flying ? 2.4f : (walkSpeed > 0.05f ? 5.5f : 2.0f);
        float targetAmplitude = flying ? 18.0f : (walkSpeed > 0.05f ? 12.0f : 6.0f);
        float smoothing = Math.min(1.0f, dt * 4.0f);
        state.setFlapSpeed(state.getFlapSpeed() + (targetSpeed - state.getFlapSpeed()) * smoothing);
        state.setFlapAmplitude(state.getFlapAmplitude() + (targetAmplitude - state.getFlapAmplitude()) * smoothing);
        state.setFlapPhase(state.getFlapPhase() + (double)(state.getFlapSpeed() * dt));
        String desired = flying ? FLY : (walkSpeed > 0.05f ? RUN : IDLE);
        boolean attackStarted = attackTime > 0.001f && (state.getPreviousAttackTime() <= 0.001f || attackTime + 0.2f < state.getPreviousAttackTime());
        state.setPreviousAttackTime(attackTime);
        AnimationData active = animations.get(state.getAnimation());
        boolean bl = finished = active == null || !active.getLoop() && this.secondsSince(now, state.getAnimationStartedAt()) >= active.getLength();
        if (attackStarted && !Intrinsics.areEqual((Object)SPAWN, (Object)state.getAnimation())) {
            this.switchAnimation(state, ATTACK, now);
        } else if (Intrinsics.areEqual((Object)SPAWN, (Object)state.getAnimation()) || Intrinsics.areEqual((Object)ATTACK, (Object)state.getAnimation()) || Intrinsics.areEqual((Object)RUN_STOP, (Object)state.getAnimation())) {
            if (finished || Intrinsics.areEqual((Object)RUN_STOP, (Object)state.getAnimation()) && !Intrinsics.areEqual((Object)IDLE, (Object)desired)) {
                this.switchAnimation(state, desired, now);
            }
        } else if (Intrinsics.areEqual((Object)RUN, (Object)state.getAnimation()) && Intrinsics.areEqual((Object)IDLE, (Object)desired)) {
            this.switchAnimation(state, RUN_STOP, now);
        } else if (!Intrinsics.areEqual((Object)state.getAnimation(), (Object)desired)) {
            this.switchAnimation(state, desired, now);
        }
    }

    private final void switchAnimation(PlaybackState state, String next, long now) {
        if (Intrinsics.areEqual((Object)next, (Object)state.getAnimation())) {
            return;
        }
        state.setPreviousAnimation(state.getAnimation());
        state.setPreviousStartedAt(state.getAnimationStartedAt());
        state.setAnimation(next);
        state.setAnimationStartedAt(now);
        state.setTransitionStartedAt(now);
    }

    private final double animationTime(AnimationData animation, long now, long startedAt) {
        if (animation == null || animation.getLength() <= 0.0) {
            return 0.0;
        }
        double elapsed = this.secondsSince(now, startedAt);
        return animation.getLoop() ? elapsed % animation.getLength() : Math.min(elapsed, animation.getLength());
    }

    private final double secondsSince(long now, long then) {
        return Math.max(0.0, (double)(now - then) / 1.0E9);
    }

    private final float smoothstep(float value) {
        float clamped = Math.max(0.0f, Math.min(1.0f, value));
        return clamped * clamped * (3.0f - 2.0f * clamped);
    }

    private final void renderBone(MatrixStack stack, VertexConsumer buffer, Bone bone, AnimationData current, double currentTime, AnimationData previous, double previousTime, float blend, AnimationData eye, double eyeTime, double flapPhase, float flapAmplitude, int light) {
        BoneAnimation eyeBone;
        Object object;
        stack.push();
        String string = bone.getName();
        Intrinsics.checkNotNull((Object)string);
        Transform transform = this.sample(current, string, currentTime);
        if (previous != null && blend < 1.0f) {
            String string2 = bone.getName();
            Intrinsics.checkNotNull((Object)string2);
            transform = this.blend(this.sample(previous, string2, previousTime), transform, blend);
        }
        eyeBone = (eye != null && eye.getBones() != null) ? eye.getBones().get(bone.getName()) : null;
        if (eyeBone != null) {
            String string3 = bone.getName();
            Intrinsics.checkNotNull((Object)string3);
            if (!String.valueOf(string3).startsWith("eye_")) {
                String string4 = bone.getName();
                Intrinsics.checkNotNull((Object)string4);
                if (!String.valueOf(string4).startsWith("iris_")) {
                    transform = this.overlay(transform, eyeBone, eyeTime);
                }
            }
        }
        String string5 = bone.getName();
        Intrinsics.checkNotNull((Object)string5);
        transform = this.applyIndependentEyeAnimation(transform, string5, eyeTime);
        String string6 = bone.getName();
        Intrinsics.checkNotNull((Object)string6);
        transform = this.applyFlap(transform, string6, flapPhase, flapAmplitude);
        float[] fArray = bone.getPivot();
        Intrinsics.checkNotNull((Object)fArray);
        float px = fArray[0];
        float[] fArray2 = bone.getPivot();
        Intrinsics.checkNotNull((Object)fArray2);
        float py = fArray2[1];
        float[] fArray3 = bone.getPivot();
        Intrinsics.checkNotNull((Object)fArray3);
        float pz = fArray3[2];
        stack.translate(px + transform.getPosition()[0], py + transform.getPosition()[1], pz + transform.getPosition()[2]);
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(bone.getRotation()[2] + transform.getRotation()[2]));
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(bone.getRotation()[0] + transform.getRotation()[0]));
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(bone.getRotation()[1] + transform.getRotation()[1]));
        stack.scale(transform.getScale()[0], transform.getScale()[1], transform.getScale()[2]);
        stack.translate(-px, -py, -pz);
        for (Cube cube : bone.getCubes()) {
            stack.push();
            if (cube.getPivot() != null && cube.getRotation() != null) {
                float[] fArray4 = cube.getPivot();
                Intrinsics.checkNotNull((Object)fArray4);
                float f = fArray4[0];
                float[] fArray5 = cube.getPivot();
                Intrinsics.checkNotNull((Object)fArray5);
                float f2 = fArray5[1];
                float[] fArray6 = cube.getPivot();
                Intrinsics.checkNotNull((Object)fArray6);
                stack.translate(f, f2, fArray6[2]);
                float[] fArray7 = cube.getRotation();
                Intrinsics.checkNotNull((Object)fArray7);
                stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(fArray7[2]));
                float[] fArray8 = cube.getRotation();
                Intrinsics.checkNotNull((Object)fArray8);
                stack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(fArray8[0]));
                float[] fArray9 = cube.getRotation();
                Intrinsics.checkNotNull((Object)fArray9);
                stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(fArray9[1]));
                float[] fArray10 = cube.getPivot();
                Intrinsics.checkNotNull((Object)fArray10);
                float f3 = -fArray10[0];
                float[] fArray11 = cube.getPivot();
                Intrinsics.checkNotNull((Object)fArray11);
                float f4 = -fArray11[1];
                float[] fArray12 = cube.getPivot();
                Intrinsics.checkNotNull((Object)fArray12);
                stack.translate(f3, f4, -fArray12[2]);
            }
            this.drawCube(stack, buffer, cube, light);
            stack.pop();
        }
        for (Bone child : bone.getChildren()) {
            this.renderBone(stack, buffer, child, current, currentTime, previous, previousTime, blend, eye, eyeTime, flapPhase, flapAmplitude, light);
        }
        stack.pop();
    }

    private final Transform applyFlap(Transform transform, String boneName, double phase, float amplitude) {
        return transform;
    }

    private final int mix32(int hash) {
        int h = hash;
        h ^= h >>> 16;
        h *= -2048144789;
        h ^= h >>> 13;
        h *= -1028477387;
        h ^= h >>> 16;
        return h;
    }

    private final Transform applyIndependentEyeAnimation(Transform transform, String boneName, double eyeTime) {
        if (String.valueOf(boneName).startsWith("iris_")) {
            int h = this.mix32(boneName.hashCode());
            float seed1 = (float)(Math.abs(h) % 10000) / 10000.0f;
            float seed2 = (float)(Math.abs(this.mix32(h ^ 0x3C6EF372)) % 10000) / 10000.0f;
            double blinkInterval = 2.5 + (double)seed1 * 5.0;
            double blinkPhase = (double)seed2 * 100.0;
            double blinkCycleTime = (eyeTime + blinkPhase) % blinkInterval;
            float yScale = 1.0f;
            if (blinkCycleTime < 0.14) {
                float progress = (float)(blinkCycleTime / 0.14);
                float blinkFactor = (float)Math.sin((double)progress * Math.PI);
                yScale = Math.max(0.05f, 1.0f - blinkFactor * 0.95f);
            }
            double gazeInterval = 1.3 + (double)seed1 * 2.4;
            double gazeTime = eyeTime + (double)seed2 * 50.0;
            long gazeIndex = (long)(gazeTime / gazeInterval);
            double stepProgress = gazeTime % gazeInterval / gazeInterval;
            float transition = this.smoothstep((float)Math.min(1.0, stepProgress * 6.0));
            int h1 = this.mix32((int)(gazeIndex ^ (long)h));
            int h2 = this.mix32((int)(gazeIndex + 1L ^ (long)h));
            float x1 = ((float)(Math.abs(h1) % 1000) / 1000.0f - 0.5f) * 0.8f;
            float y1 = ((float)(Math.abs(this.mix32(h1 ^ 0x77777777)) % 1000) / 1000.0f - 0.5f) * 0.5f;
            float x2 = ((float)(Math.abs(h2) % 1000) / 1000.0f - 0.5f) * 0.8f;
            float y2 = ((float)(Math.abs(this.mix32(h2 ^ 0x77777777)) % 1000) / 1000.0f - 0.5f) * 0.5f;
            float gazeX = x1 + (x2 - x1) * transition;
            float gazeY = y1 + (y2 - y1) * transition;
            float[] position = (float[])transform.getPosition().clone();
            position[0] = position[0] + gazeX;
            position[1] = position[1] + gazeY;
            float[] rotation = (float[])transform.getRotation().clone();
            rotation[1] = rotation[1] + gazeX * 6.0f;
            rotation[0] = rotation[0] + gazeY * 6.0f;
            float[] scale = (float[])transform.getScale().clone();
            scale[1] = scale[1] * yScale;
            return new Transform(position, rotation, scale);
        }
        return transform;
    }

    private final Transform sample(AnimationData animation, String boneName, double time) {
        BoneAnimation bone = (animation != null && animation.getBones() != null) ? animation.getBones().get(boneName) : null;
        return new Transform(this.interpolate(bone != null ? bone.getPosition() : null, time, ZERO), this.interpolate(bone != null ? bone.getRotation() : null, time, ZERO), this.interpolate(bone != null ? bone.getScale() : null, time, ONE));
    }

    private final Transform overlay(Transform base, BoneAnimation overlay, double time) {
        float[] position = overlay.getPosition().isEmpty() ? base.getPosition() : this.interpolate(overlay.getPosition(), time, base.getPosition());
        float[] rotation = overlay.getRotation().isEmpty() ? base.getRotation() : this.interpolate(overlay.getRotation(), time, base.getRotation());
        float[] scale = overlay.getScale().isEmpty() ? base.getScale() : this.interpolate(overlay.getScale(), time, base.getScale());
        return new Transform(position, rotation, scale);
    }

    private final Transform blend(Transform from, Transform to, float amount) {
        return new Transform(this.blend(from.getPosition(), to.getPosition(), amount), this.blend(from.getRotation(), to.getRotation(), amount), this.blend(from.getScale(), to.getScale(), amount));
    }

    private final float[] blend(float[] from, float[] to, float amount) {
        float[] fArray = new float[]{from[0] + (to[0] - from[0]) * amount, from[1] + (to[1] - from[1]) * amount, from[2] + (to[2] - from[2]) * amount};
        return fArray;
    }

    private final float[] interpolate(TreeMap<Double, float[]> keyframes, double time, float[] fallback) {
        if (keyframes == null || keyframes.isEmpty()) {
            return fallback;
        }
        Map.Entry<Double, float[]> before = keyframes.floorEntry(time);
        Map.Entry<Double, float[]> after = keyframes.ceilingEntry(time);
        if (before == null) {
            Map.Entry<Double, float[]> entry = after;
            Intrinsics.checkNotNull(entry);
            float[] fArray = entry.getValue();
            Intrinsics.checkNotNullExpressionValue((Object)fArray, (String)"<get-value>(...)");
            return fArray;
        }
        if (after == null || Intrinsics.areEqual((Double)before.getKey(), (Double)after.getKey())) {
            float[] fArray = before.getValue();
            Intrinsics.checkNotNullExpressionValue((Object)fArray, (String)"<get-value>(...)");
            return fArray;
        }
        Double d = before.getKey();
        Intrinsics.checkNotNullExpressionValue((Object)d, (String)"<get-key>(...)");
        double d2 = time - ((Number)d).doubleValue();
        double d3 = ((Number)after.getKey()).doubleValue();
        Double d4 = before.getKey();
        Intrinsics.checkNotNullExpressionValue((Object)d4, (String)"<get-key>(...)");
        float amount = (float)(d2 / (d3 - ((Number)d4).doubleValue()));
        float[] fArray = before.getValue();
        Intrinsics.checkNotNullExpressionValue((Object)fArray, (String)"<get-value>(...)");
        float[] fArray2 = after.getValue();
        Intrinsics.checkNotNullExpressionValue((Object)fArray2, (String)"<get-value>(...)");
        return this.blend(fArray, fArray2, amount);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void loadModel() {
        block15: {
            if (loaded) {
                return;
            }
            loaded = true;
            try {
                Throwable throwable;
                Object object;
                MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
                Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
                MinecraftClient minecraft = minecraftClient2;
                Optional optional = minecraft.getResourceManager().getResource(GEOMETRY_ID);
                Intrinsics.checkNotNullExpressionValue((Object)optional, (String)"getResource(...)");
                Optional geometryResource = optional;
                if (geometryResource.isPresent()) {
                    Closeable closeable = ((Resource)geometryResource.get()).getInputStream();
                    object = null;
                    try {
                        InputStream stream = (InputStream)closeable;
                        boolean bl = false;
                        JsonObject jsonObject = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                        Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                        INSTANCE.parseGeometry(jsonObject);
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
                Optional optional2 = minecraft.getResourceManager().getResource(ANIMATIONS_ID);
                Intrinsics.checkNotNullExpressionValue((Object)optional2, (String)"getResource(...)");
                Optional animationResource = optional2;
                if (!animationResource.isPresent()) break block15;
                object = ((Resource)animationResource.get()).getInputStream();
                throwable = null;
                try {
                    InputStream stream = (InputStream)object;
                    boolean bl = false;
                    JsonObject jsonObject = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                    Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                    INSTANCE.parseAnimations(jsonObject);
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
            catch (Exception ignored) {
                geometry.getRootBones().clear();
                animations.clear();
            }
        }
    }

    private final void parseGeometry(JsonObject json) {
        JsonObject model = json.getAsJsonArray("minecraft:geometry").get(0).getAsJsonObject();
        HashMap bones = new HashMap();
        Iterator iterator = model.getAsJsonArray("bones").iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonElement element = (JsonElement)iterator2.next();
            JsonObject obj = element.getAsJsonObject();
            Bone bone = new Bone();
            bone.setName(obj.get("name").getAsString());
            bone.setParent(obj.has("parent") ? obj.get("parent").getAsString() : null);
            JsonArray jsonArray = obj.getAsJsonArray("pivot");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            bone.setPivot(this.vector(jsonArray));
            if (obj.has("rotation")) {
                JsonArray jsonArray2 = obj.getAsJsonArray("rotation");
                Intrinsics.checkNotNullExpressionValue((Object)jsonArray2, (String)"getAsJsonArray(...)");
                bone.setRotation(this.vector(jsonArray2));
            }
            if (obj.has("cubes")) {
                Iterator iterator3 = (Iterator) (obj.getAsJsonArray("cubes").iterator());
                while (iterator3.hasNext()) {
                    JsonElement cubeElement = (JsonElement)iterator3.next();
                    List<Cube> list = bone.getCubes();
                    JsonObject jsonObject = cubeElement.getAsJsonObject();
                    Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                    list.add(this.parseCube(jsonObject));
                }
            }
            Map map = bones;
            String string = bone.getName();
            Intrinsics.checkNotNull((Object)string);
            map.put(string, bone);
        }
        for (Object v : bones.values()) {
            Intrinsics.checkNotNullExpressionValue(v, (String)"next(...)");
            Bone bone = (Bone)v;
            Bone parent = (Bone)((Map)bones).get(bone.getParent());
            boolean bl = parent == null ? geometry.getRootBones().add(bone) : parent.getChildren().add(bone);
        }
    }

    private final Cube parseCube(JsonObject obj) {
        Cube cube = new Cube();
        JsonArray jsonArray = obj.getAsJsonArray("origin");
        Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
        cube.setOrigin(this.vector(jsonArray));
        JsonArray jsonArray2 = obj.getAsJsonArray("size");
        Intrinsics.checkNotNullExpressionValue((Object)jsonArray2, (String)"getAsJsonArray(...)");
        cube.setSize(this.vector(jsonArray2));
        if (obj.has("pivot")) {
            JsonArray jsonArray3 = obj.getAsJsonArray("pivot");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray3, (String)"getAsJsonArray(...)");
            cube.setPivot(this.vector(jsonArray3));
        }
        if (obj.has("rotation")) {
            JsonArray jsonArray4 = obj.getAsJsonArray("rotation");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray4, (String)"getAsJsonArray(...)");
            cube.setRotation(this.vector(jsonArray4));
        }
        JsonObject uv = obj.getAsJsonObject("uv");
        for (String faceName : uv.keySet()) {
            JsonObject faceObject = uv.getAsJsonObject(faceName);
            FaceUv face = new FaceUv();
            JsonArray jsonArray5 = faceObject.getAsJsonArray("uv");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray5, (String)"getAsJsonArray(...)");
            face.setUv(this.pair(jsonArray5));
            JsonArray jsonArray6 = faceObject.getAsJsonArray("uv_size");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray6, (String)"getAsJsonArray(...)");
            face.setSize(this.pair(jsonArray6));
            cube.getFaces().put(faceName, face);
        }
        return cube;
    }

    private final void parseAnimations(JsonObject json) {
        JsonObject source = json.getAsJsonObject("animations");
        for (String name : source.keySet()) {
            JsonObject obj = source.getAsJsonObject(name);
            AnimationData animation = new AnimationData();
            animation.setLength(obj.get("animation_length").getAsDouble());
            animation.setLoop(obj.has("loop") && obj.get("loop").getAsBoolean());
            JsonObject bones = obj.getAsJsonObject("bones");
            for (String boneName : bones.keySet()) {
                JsonObject boneObject = bones.getAsJsonObject(boneName);
                BoneAnimation bone = new BoneAnimation();
                Intrinsics.checkNotNull((Object)boneObject);
                this.parseChannel(boneObject, "position", bone.getPosition());
                this.parseChannel(boneObject, "rotation", bone.getRotation());
                this.parseChannel(boneObject, "scale", bone.getScale());
                animation.getBones().put(boneName, bone);
            }
            animations.put(name, animation);
        }
    }

    private final void parseChannel(JsonObject bone, String name, TreeMap<Double, float[]> output) {
        if (!bone.has(name)) {
            return;
        }
        JsonElement channel = bone.get(name);
        if (channel.isJsonArray()) {
            Map map = output;
            Double d = 0.0;
            JsonArray jsonArray = channel.getAsJsonArray();
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            float[] fArray = this.vector(jsonArray);
            map.put(d, fArray);
            return;
        }
        for (String time : channel.getAsJsonObject().keySet()) {
            JsonElement value = channel.getAsJsonObject().get(time);
            if (!value.isJsonArray()) continue;
            Map map = output;
            Intrinsics.checkNotNull((Object)time);
            Double d = Double.parseDouble(time);
            JsonArray jsonArray = value.getAsJsonArray();
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            float[] fArray = this.vector(jsonArray);
            map.put(d, fArray);
        }
    }

    private final float[] vector(JsonArray array) {
        float[] fArray = new float[]{array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat()};
        return fArray;
    }

    private final float[] pair(JsonArray array) {
        float[] fArray = new float[]{array.get(0).getAsFloat(), array.get(1).getAsFloat()};
        return fArray;
    }

    private final void drawCube(MatrixStack stack, VertexConsumer buffer, Cube cube, int light) {
        float[] fArray = cube.getOrigin();
        Intrinsics.checkNotNull((Object)fArray);
        float x1 = fArray[0];
        float[] fArray2 = cube.getOrigin();
        Intrinsics.checkNotNull((Object)fArray2);
        float y1 = fArray2[1];
        float[] fArray3 = cube.getOrigin();
        Intrinsics.checkNotNull((Object)fArray3);
        float z1 = fArray3[2];
        float[] fArray4 = cube.getSize();
        Intrinsics.checkNotNull((Object)fArray4);
        float x2 = x1 + fArray4[0];
        float[] fArray5 = cube.getSize();
        Intrinsics.checkNotNull((Object)fArray5);
        float y2 = y1 + fArray5[1];
        float[] fArray6 = cube.getSize();
        Intrinsics.checkNotNull((Object)fArray6);
        float z2 = z1 + fArray6[2];
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        for (Map.Entry<String, FaceUv> entry : cube.getFaces().entrySet()) {
            String key = entry.getKey();
            FaceUv uv = entry.getValue();
            switch (key) {
                case "north": {
                    this.quad(buffer, pose, uv, light, x2, y2, z1, x1, y2, z1, x1, y1, z1, x2, y1, z1, 0.0f, 0.0f, -1.0f);
                    break;
                }
                case "south": {
                    this.quad(buffer, pose, uv, light, x1, y2, z2, x2, y2, z2, x2, y1, z2, x1, y1, z2, 0.0f, 0.0f, 1.0f);
                    break;
                }
                case "east": {
                    this.quad(buffer, pose, uv, light, x1, y2, z1, x1, y2, z2, x1, y1, z2, x1, y1, z1, -1.0f, 0.0f, 0.0f);
                    break;
                }
                case "west": {
                    this.quad(buffer, pose, uv, light, x2, y2, z2, x2, y2, z1, x2, y1, z1, x2, y1, z2, 1.0f, 0.0f, 0.0f);
                    break;
                }
                case "up": {
                    this.quad(buffer, pose, uv, light, x2, y2, z2, x1, y2, z2, x1, y2, z1, x2, y2, z1, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case "down": {
                    this.quad(buffer, pose, uv, light, x2, y1, z1, x1, y1, z1, x1, y1, z2, x2, y1, z2, 0.0f, -1.0f, 0.0f);
                }
            }
        }
    }

    private final void quad(VertexConsumer buffer, MatrixStack.Entry pose, FaceUv uv, int light, float ax, float ay, float az, float bx, float by, float bz, float cx, float cy, float cz, float dx, float dy, float dz, float nx, float ny, float nz) {
        float[] fArray = uv.getUv();
        Intrinsics.checkNotNull((Object)fArray);
        float u1 = fArray[0] / 128.0f;
        float[] fArray2 = uv.getUv();
        Intrinsics.checkNotNull((Object)fArray2);
        float v1 = fArray2[1] / 128.0f;
        float[] fArray3 = uv.getUv();
        Intrinsics.checkNotNull((Object)fArray3);
        float f = fArray3[0];
        float[] fArray4 = uv.getSize();
        Intrinsics.checkNotNull((Object)fArray4);
        float u2 = (f + fArray4[0]) / 128.0f;
        float[] fArray5 = uv.getUv();
        Intrinsics.checkNotNull((Object)fArray5);
        float f2 = fArray5[1];
        float[] fArray6 = uv.getSize();
        Intrinsics.checkNotNull((Object)fArray6);
        float v2 = (f2 + fArray6[1]) / 128.0f;
        this.vertex(buffer, pose, ax, ay, az, u2, v1, nx, ny, nz, light);
        this.vertex(buffer, pose, bx, by, bz, u1, v1, nx, ny, nz, light);
        this.vertex(buffer, pose, cx, cy, cz, u1, v2, nx, ny, nz, light);
        this.vertex(buffer, pose, dx, dy, dz, u2, v2, nx, ny, nz, light);
    }

    private final void vertex(VertexConsumer buffer, MatrixStack.Entry pose, float x, float y, float z, float u, float v, float nx, float ny, float nz, int light) {
        buffer.vertex(pose, x, y, z).color(-1).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(pose, nx, ny, nz);
    }

    private static final PlaybackState render$lambda$0(long $now, AbstractClientPlayerEntity it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return new PlaybackState($now);
    }

    private static final PlaybackState render$lambda$1(Function1 $tmp0, Object p0) {
        return (PlaybackState)$tmp0.invoke(p0);
    }

    private static final void render$lambda$2(AnimationData $current, double $currentTime, AnimationData $finalPrevious, double $previousTime, float $finalBlend, AnimationData $eye, double $eyeTime, PlaybackState $state, int $light, MatrixStack.Entry pose, VertexConsumer buffer) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)buffer, (String)"buffer");
        MatrixStack local = new MatrixStack();
        local.peek().getPositionMatrix().set((Matrix4fc)pose.getPositionMatrix());
        local.peek().getNormalMatrix().set((Matrix3fc)pose.getNormalMatrix());
        local.scale(0.0625f, -0.0625f, 0.0625f);
        local.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        local.translate(0.0f, -16.0f, -4.3f);
        local.scale(0.62f, 0.62f, 0.62f);
        for (Bone root : geometry.getRootBones()) {
            INSTANCE.renderBone(local, buffer, root, $current, $currentTime, $finalPrevious, $previousTime, $finalBlend, $eye, $eyeTime, $state.getFlapPhase(), $state.getFlapAmplitude(), $light);
        }
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/wings/seraph_eye_wings.geo.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        GEOMETRY_ID = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/wings/seraph_eye_wings.animation.json");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        ANIMATIONS_ID = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/wings/seraph_eye_wings.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier4;
        geometry = new Geometry();
        animations = new HashMap();
        playbackStates = new WeakHashMap();
        float[] fArray = new float[]{0.0f, 0.0f, 0.0f};
        ZERO = fArray;
        fArray = new float[]{1.0f, 1.0f, 1.0f};
        ONE = fArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R#\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00128\u0006\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$AnimationData;", "", "<init>", "()V", "", "length", "D", "getLength", "()D", "setLength", "(D)V", "", "loop", "Z", "getLoop", "()Z", "setLoop", "(Z)V", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$BoneAnimation;", "bones", "Ljava/util/Map;", "getBones", "()Ljava/util/Map;", "rtx.kimiko:kimiko"})
    private static final class AnimationData {
        private double length;
        private boolean loop;
        @NotNull
        private final Map<String, BoneAnimation> bones = new HashMap();

        public final double getLength() {
            return this.length;
        }

        public final void setLength(double d) {
            this.length = d;
        }

        public final boolean getLoop() {
            return this.loop;
        }

        public final void setLoop(boolean bl) {
            this.loop = bl;
        }

        @NotNull
        public final Map<String, BoneAnimation> getBones() {
            return this.bones;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188\u0006\u00a2\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00000\u00188\u0006\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001d\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Bone;", "", "<init>", "()V", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "parent", "getParent", "setParent", "", "pivot", "[F", "getPivot", "()[F", "setPivot", "([F)V", "rotation", "getRotation", "setRotation", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Cube;", "cubes", "Ljava/util/List;", "getCubes", "()Ljava/util/List;", "children", "getChildren", "rtx.kimiko:kimiko"})
    private static final class Bone {
        @Nullable
        private String name;
        @Nullable
        private String parent;
        @Nullable
        private float[] pivot;
        @NotNull
        private float[] rotation;
        @NotNull
        private final List<Cube> cubes;
        @NotNull
        private final List<Bone> children;

        public Bone() {
            float[] fArray = new float[]{0.0f, 0.0f, 0.0f};
            this.rotation = fArray;
            this.cubes = new ArrayList();
            this.children = new ArrayList();
        }

        @Nullable
        public final String getName() {
            return this.name;
        }

        public final void setName(@Nullable String string) {
            this.name = string;
        }

        @Nullable
        public final String getParent() {
            return this.parent;
        }

        public final void setParent(@Nullable String string) {
            this.parent = string;
        }

        @Nullable
        public final float[] getPivot() {
            return this.pivot;
        }

        public final void setPivot(@Nullable float[] fArray) {
            this.pivot = fArray;
        }

        @NotNull
        public final float[] getRotation() {
            return this.rotation;
        }

        public final void setRotation(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.rotation = fArray;
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0014\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR#\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\nR#\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$BoneAnimation;", "", "<init>", "()V", "Ljava/util/TreeMap;", "", "", "position", "Ljava/util/TreeMap;", "getPosition", "()Ljava/util/TreeMap;", "rotation", "getRotation", "scale", "getScale", "rtx.kimiko:kimiko"})
    private static final class BoneAnimation {
        @NotNull
        private final TreeMap<Double, float[]> position = new TreeMap();
        @NotNull
        private final TreeMap<Double, float[]> rotation = new TreeMap();
        @NotNull
        private final TreeMap<Double, float[]> scale = new TreeMap();

        @NotNull
        public final TreeMap<Double, float[]> getPosition() {
            return this.position;
        }

        @NotNull
        public final TreeMap<Double, float[]> getRotation() {
            return this.rotation;
        }

        @NotNull
        public final TreeMap<Double, float[]> getScale() {
            return this.scale;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u000f\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR$\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR$\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR#\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00148\u0006\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Cube;", "", "<init>", "()V", "", "origin", "[F", "getOrigin", "()[F", "setOrigin", "([F)V", "size", "getSize", "setSize", "pivot", "getPivot", "setPivot", "rotation", "getRotation", "setRotation", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$FaceUv;", "faces", "Ljava/util/Map;", "getFaces", "()Ljava/util/Map;", "rtx.kimiko:kimiko"})
    private static final class Cube {
        @Nullable
        private float[] origin;
        @Nullable
        private float[] size;
        @Nullable
        private float[] pivot;
        @Nullable
        private float[] rotation;
        @NotNull
        private final Map<String, FaceUv> faces = new HashMap();

        @Nullable
        public final float[] getOrigin() {
            return this.origin;
        }

        public final void setOrigin(@Nullable float[] fArray) {
            this.origin = fArray;
        }

        @Nullable
        public final float[] getSize() {
            return this.size;
        }

        public final void setSize(@Nullable float[] fArray) {
            this.size = fArray;
        }

        @Nullable
        public final float[] getPivot() {
            return this.pivot;
        }

        public final void setPivot(@Nullable float[] fArray) {
            this.pivot = fArray;
        }

        @Nullable
        public final float[] getRotation() {
            return this.rotation;
        }

        public final void setRotation(@Nullable float[] fArray) {
            this.rotation = fArray;
        }

        @NotNull
        public final Map<String, FaceUv> getFaces() {
            return this.faces;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$FaceUv;", "", "<init>", "()V", "", "uv", "[F", "getUv", "()[F", "setUv", "([F)V", "size", "getSize", "setSize", "rtx.kimiko:kimiko"})
    private static final class FaceUv {
        @Nullable
        private float[] uv;
        @Nullable
        private float[] size;

        @Nullable
        public final float[] getUv() {
            return this.uv;
        }

        public final void setUv(@Nullable float[] fArray) {
            this.uv = fArray;
        }

        @Nullable
        public final float[] getSize() {
            return this.size;
        }

        public final void setSize(@Nullable float[] fArray) {
            this.size = fArray;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Geometry;", "", "<init>", "()V", "", "Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Bone;", "rootBones", "Ljava/util/List;", "getRootBones", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
    private static final class Geometry {
        @NotNull
        private final List<Bone> rootBones = new ArrayList();

        @NotNull
        public final List<Bone> getRootBones() {
            return this.rootBones;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\"\u0010\u0014\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0007\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\u0005R\"\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0007\u001a\u0004\b\u0018\u0010\t\"\u0004\b\u0019\u0010\u0005R\"\u0010\u001a\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u0007\u001a\u0004\b\u001b\u0010\t\"\u0004\b\u001c\u0010\u0005R\"\u0010\u001d\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0007\u001a\u0004\b\u001e\u0010\t\"\u0004\b\u001f\u0010\u0005R\"\u0010!\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010(\u001a\u00020'8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\"\u0010.\u001a\u00020'8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010)\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\"\u00101\u001a\u00020'8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b1\u0010)\u001a\u0004\b2\u0010+\"\u0004\b3\u0010-\u00a8\u00064"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$PlaybackState;", "", "", "now", "<init>", "(J)V", "createdAt", "J", "getCreatedAt", "()J", "", "animation", "Ljava/lang/String;", "getAnimation", "()Ljava/lang/String;", "setAnimation", "(Ljava/lang/String;)V", "previousAnimation", "getPreviousAnimation", "setPreviousAnimation", "animationStartedAt", "getAnimationStartedAt", "setAnimationStartedAt", "previousStartedAt", "getPreviousStartedAt", "setPreviousStartedAt", "transitionStartedAt", "getTransitionStartedAt", "setTransitionStartedAt", "lastUpdatedAt", "getLastUpdatedAt", "setLastUpdatedAt", "", "flapPhase", "D", "getFlapPhase", "()D", "setFlapPhase", "(D)V", "", "flapSpeed", "F", "getFlapSpeed", "()F", "setFlapSpeed", "(F)V", "flapAmplitude", "getFlapAmplitude", "setFlapAmplitude", "previousAttackTime", "getPreviousAttackTime", "setPreviousAttackTime", "rtx.kimiko:kimiko"})
    private static final class PlaybackState {
        private final long createdAt;
        @NotNull
        private String animation;
        @Nullable
        private String previousAnimation;
        private long animationStartedAt;
        private long previousStartedAt;
        private long transitionStartedAt;
        private long lastUpdatedAt;
        private double flapPhase;
        private float flapSpeed;
        private float flapAmplitude;
        private float previousAttackTime;

        public PlaybackState(long now) {
            this.createdAt = now;
            this.animation = SeraphEyeWingsRenderer.IDLE;
            this.animationStartedAt = now;
            this.transitionStartedAt = now;
            this.lastUpdatedAt = now;
            this.flapSpeed = 2.0f;
            this.flapAmplitude = 6.0f;
        }

        public final long getCreatedAt() {
            return this.createdAt;
        }

        @NotNull
        public final String getAnimation() {
            return this.animation;
        }

        public final void setAnimation(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.animation = string;
        }

        @Nullable
        public final String getPreviousAnimation() {
            return this.previousAnimation;
        }

        public final void setPreviousAnimation(@Nullable String string) {
            this.previousAnimation = string;
        }

        public final long getAnimationStartedAt() {
            return this.animationStartedAt;
        }

        public final void setAnimationStartedAt(long l) {
            this.animationStartedAt = l;
        }

        public final long getPreviousStartedAt() {
            return this.previousStartedAt;
        }

        public final void setPreviousStartedAt(long l) {
            this.previousStartedAt = l;
        }

        public final long getTransitionStartedAt() {
            return this.transitionStartedAt;
        }

        public final void setTransitionStartedAt(long l) {
            this.transitionStartedAt = l;
        }

        public final long getLastUpdatedAt() {
            return this.lastUpdatedAt;
        }

        public final void setLastUpdatedAt(long l) {
            this.lastUpdatedAt = l;
        }

        public final double getFlapPhase() {
            return this.flapPhase;
        }

        public final void setFlapPhase(double d) {
            this.flapPhase = d;
        }

        public final float getFlapSpeed() {
            return this.flapSpeed;
        }

        public final void setFlapSpeed(float f) {
            this.flapSpeed = f;
        }

        public final float getFlapAmplitude() {
            return this.flapAmplitude;
        }

        public final void setFlapAmplitude(float f) {
            this.flapAmplitude = f;
        }

        public final float getPreviousAttackTime() {
            return this.previousAttackTime;
        }

        public final void setPreviousAttackTime(float f) {
            this.previousAttackTime = f;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\tJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\tJ.\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\t\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "", "", "position", "rotation", "scale", "<init>", "([F[F[F)V", "component1", "()[F", "component2", "component3", "copy", "([F[F[F)Lrtx/kimiko/api/modules/impl/Visuals/customization/SeraphEyeWingsRenderer$Transform;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "[F", "getPosition", "getRotation", "getScale", "rtx.kimiko:kimiko"})
    private static final class Transform {
        @NotNull
        private final float[] position;
        @NotNull
        private final float[] rotation;
        @NotNull
        private final float[] scale;

        public Transform(@NotNull float[] position, @NotNull float[] rotation, @NotNull float[] scale) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter((Object)scale, (String)"scale");
            this.position = position;
            this.rotation = rotation;
            this.scale = scale;
        }

        @NotNull
        public final float[] getPosition() {
            return this.position;
        }

        @NotNull
        public final float[] getRotation() {
            return this.rotation;
        }

        @NotNull
        public final float[] getScale() {
            return this.scale;
        }

        @NotNull
        public final float[] component1() {
            return this.position;
        }

        @NotNull
        public final float[] component2() {
            return this.rotation;
        }

        @NotNull
        public final float[] component3() {
            return this.scale;
        }

        @NotNull
        public final Transform copy(@NotNull float[] position, @NotNull float[] rotation, @NotNull float[] scale) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            Intrinsics.checkNotNullParameter((Object)scale, (String)"scale");
            return new Transform(position, rotation, scale);
        }

        public static /* synthetic */ Transform copy$default(Transform transform, float[] fArray, float[] fArray2, float[] fArray3, int n, Object object) {
            if ((n & 1) != 0) {
                fArray = transform.position;
            }
            if ((n & 2) != 0) {
                fArray2 = transform.rotation;
            }
            if ((n & 4) != 0) {
                fArray3 = transform.scale;
            }
            return transform.copy(fArray, fArray2, fArray3);
        }

        @NotNull
        public String toString() {
            return "Transform(position=" + Arrays.toString(this.position) + ", rotation=" + Arrays.toString(this.rotation) + ", scale=" + Arrays.toString(this.scale) + ")";
        }

        public int hashCode() {
            int result = Arrays.hashCode(this.position);
            result = result * 31 + Arrays.hashCode(this.rotation);
            result = result * 31 + Arrays.hashCode(this.scale);
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
    }
}

