/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 */
package rtx.kimiko.api.modules.impl.Visuals.trails;

import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.impl.Visuals.trails.TrailEchoCapture;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b,\n\u0002\u0010\u0015\n\u0002\b&\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0004\u00b4\u0001\u00b5\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J+\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012JW\u0010!\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b!\u0010\"JW\u0010*\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b*\u0010+JW\u00103\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b3\u00104J_\u0010;\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u00172\u0006\u00106\u001a\u00020\t2\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b;\u0010<J'\u0010@\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00172\u0006\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b@\u0010AJW\u0010J\u001a\u00020\u00172\u0006\u0010C\u001a\u00020B2\u0006\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u00172\u0006\u0010D\u001a\u00020\t2\u0006\u0010E\u001a\u00020\t2\u0006\u0010F\u001a\u00020\t2\u0006\u0010G\u001a\u00020\t2\u0006\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bJ\u0010KJO\u0010S\u001a\u00020\u00042\u0006\u0010C\u001a\u00020B2\u0006\u0010L\u001a\u00020\u00172\u0006\u0010M\u001a\u00020\t2\u0006\u0010N\u001a\u00020\t2\u0006\u0010O\u001a\u00020\u00172\u0006\u0010P\u001a\u00020\u00172\u0006\u0010Q\u001a\u00020\u00172\u0006\u0010R\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bS\u0010TJ7\u0010Y\u001a\u00020\u00042\u0006\u0010C\u001a\u00020B2\u0006\u0010U\u001a\u00020\t2\u0006\u0010V\u001a\u00020\t2\u0006\u0010W\u001a\u00020\t2\u0006\u0010X\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bY\u0010ZJ'\u0010\\\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010[\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\\\u0010]J/\u0010`\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020\u001d2\u0006\u0010W\u001a\u00020\u001d2\u0006\u0010_\u001a\u00020^H\u0002\u00a2\u0006\u0004\b`\u0010aJ/\u0010f\u001a\u00020\u00172\u0006\u0010b\u001a\u00020\u00172\u0006\u0010d\u001a\u00020c2\u0006\u0010e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bf\u0010gJ/\u0010l\u001a\u00020\u00172\u0006\u0010h\u001a\u00020\t2\u0006\u0010i\u001a\u00020\t2\u0006\u0010j\u001a\u00020\t2\u0006\u0010k\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bl\u0010mJ\u001f\u0010p\u001a\u00020\t2\u0006\u0010n\u001a\u00020\u00192\u0006\u0010o\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bp\u0010qR\u0014\u0010r\u001a\u00020\u00178\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0014\u0010t\u001a\u00020\u00178\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bt\u0010sR\u0014\u0010u\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bu\u0010sR\u0014\u0010v\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010sR\u0014\u0010w\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0014\u0010y\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\by\u0010xR\u0014\u0010z\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bz\u0010{R\u0014\u0010|\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010{R\u0014\u0010}\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010{R\u0014\u0010~\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b~\u0010{R\u0014\u0010\u007f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u007f\u0010{R\u0016\u0010\u0080\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010{R\u0016\u0010\u0081\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010{R\u0016\u0010\u0082\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010{R\u0017\u0010\u0083\u0001\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0016\u0010\u0085\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010{R\u0016\u0010\u0086\u0001\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010sR\u0016\u0010\u0087\u0001\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010sR\u0016\u0010\u0088\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0088\u0001\u0010{R\u0016\u0010\u0089\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010{R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0014\u0010\u008a\u0001R\u0017\u0010\u008b\u0001\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0017\u0010\u008d\u0001\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008c\u0001R\u0017\u0010\u008e\u0001\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008c\u0001R\u0017\u0010\u008f\u0001\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u008c\u0001R\u0018\u0010\u0091\u0001\u001a\u00030\u0090\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u0092\u0001R\u0017\u0010\u0093\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0017\u0010\u0095\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0094\u0001R\u0017\u0010\u0096\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0094\u0001R\u0017\u0010\u0097\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0094\u0001R\u0017\u0010\u0098\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0094\u0001R\u0017\u0010\u0099\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0094\u0001R\u0017\u0010\u009a\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0094\u0001R\u0017\u0010\u009b\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0094\u0001R\u0017\u0010\u009c\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0094\u0001R\u0017\u0010\u009d\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u0094\u0001R\u0017\u0010\u009e\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u0094\u0001R\u0017\u0010\u009f\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u0094\u0001R\u0017\u0010\u00a0\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u0094\u0001R\u0017\u0010\u00a1\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u0094\u0001R\u0017\u0010\u00a2\u0001\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u0094\u0001R\u0018\u0010\u00a3\u0001\u001a\u00030\u0090\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u0092\u0001R\u0018\u0010\u00a4\u0001\u001a\u00030\u0090\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u0092\u0001R\u0018\u0010\u00a5\u0001\u001a\u00030\u0090\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u0092\u0001R\u0018\u0010\u00a6\u0001\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a6\u0001\u0010sR\u0018\u0010\u00a7\u0001\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a7\u0001\u0010sR\u0018\u0010\u00a8\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a8\u0001\u0010{R\u0018\u0010\u00a9\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a9\u0001\u0010{R\u0018\u0010\u00aa\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00aa\u0001\u0010{R\u0018\u0010«\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b«\u0001\u0010{R\u0018\u0010\u00ac\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ac\u0001\u0010{R\u0018\u0010\u00ad\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ad\u0001\u0010{R\u0018\u0010\u00ae\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ae\u0001\u0010{R\u0018\u0010\u00af\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00af\u0001\u0010{R\u0018\u0010\u00b0\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b0\u0001\u0010{R\u0018\u0010\u00b1\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b1\u0001\u0010{R\u0018\u0010\u00b2\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b2\u0001\u0010{R\u0018\u0010\u00b3\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b3\u0001\u0010{\u00a8\u0006\u00b6\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "clear", "Lnet/minecraft/LivingEntity;", "entity", "", "partialTick", "Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;", "options", "update", "(Lnet/minecraft/LivingEntity;FLrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "render", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;)V", "Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;", "trail", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;", "box", "", "boxIndex", "", "now", "", "push", "", "originX", "originY", "originZ", "sampleBox", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;ILrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;JZDDD)V", "point", "px", "py", "pz", "worldX", "worldY", "worldZ", "feed", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;IFFFZDDD)V", "mask", "depth", "camX", "camY", "camZ", "opacity", "fadeMs", "steps", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;JIJDDDFF)I", "cap", "spawn", "live", "liveX", "liveY", "liveZ", "strip", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;IIIFZFFF)I", "count", "from", "to", "sides", "(III)V", "Lnet/minecraft/VertexConsumer;", "consumer", "hr", "hg", "hb", "tr", "tg", "tb", "ribbon", "(Lnet/minecraft/VertexConsumer;IIFFFFFF)I", "k", "halfA", "halfB", "coreA", "coreB", "edgeA", "edgeB", "span", "(Lnet/minecraft/VertexConsumer;IFFIIII)V", "x", "y", "z", "color", "emit", "(Lnet/minecraft/VertexConsumer;FFFI)V", "lifetime", "prune", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;JI)Z", "Lorg/joml/Vector3f;", "dest", "curl", "(DDDLorg/joml/Vector3f;)V", "texture", "", "uvs", "quad", "faceKey", "(I[FII)I", "red", "green", "blue", "alpha", "pack", "(FFFF)I", "key", "salt", "hash01", "(JI)F", "MIN_SAMPLES", "I", "MAX_SAMPLES", "GRID", "MAX_POINTS", "MIN_INTERVAL", "J", "FRESH_WINDOW", "SPAWN_FADE", "F", "PIXELS", "SURFACE_OFFSET", "FADE_IN_PUSHES", "HALO_WIDTH", "HALO_ALPHA", "ALPHA_SCALE", "FULL_SPEED", "POWER_RISE_MS", "D", "POWER_CUTOFF", "STRIP", "VERTEX_BUDGET", "MIN_ALPHA", "GRADIENT_SPAN", "Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;", "NORMAL", "Lorg/joml/Vector3f;", "EDGE_U", "EDGE_V", "SWIRL", "", "STEP_SLOT", "[I", "STEP_X", "[F", "STEP_Y", "STEP_Z", "STEP_AGE", "STEP_POWER", "STEP_FADE", "PX", "PY", "PZ", "AT", "AA", "AW", "SX", "SY", "SZ", "CORE", "HALO", "EDGE", "hotLo", "hotHi", "m00", "m01", "m02", "m10", "m11", "m12", "m20", "m21", "m22", "m30", "m31", "m32", "Options", "Trail", "rtx.kimiko:kimiko"})
public final class EnergyTrail {
    @NotNull
    public static final EnergyTrail INSTANCE = new EnergyTrail();
    public static final int MIN_SAMPLES = 6;
    public static final int MAX_SAMPLES = 40;
    private static final int GRID = 24;
    private static final int MAX_POINTS = 3000;
    private static final long MIN_INTERVAL = 8L;
    private static final long FRESH_WINDOW = 80L;
    private static final float SPAWN_FADE = 220.0f;
    private static final float PIXELS = 16.0f;
    private static final float SURFACE_OFFSET = 0.02f;
    private static final float FADE_IN_PUSHES = 2.0f;
    private static final float HALO_WIDTH = 2.5f;
    private static final float HALO_ALPHA = 0.45f;
    private static final float ALPHA_SCALE = 0.75f;
    private static final float FULL_SPEED = 0.25f;
    private static final double POWER_RISE_MS = 190.0;
    private static final float POWER_CUTOFF = 0.01f;
    private static final int STRIP = 42;
    private static final int VERTEX_BUDGET = 250000;
    private static final float MIN_ALPHA = 0.002f;
    private static final float GRADIENT_SPAN = 0.4f;
    @Nullable
    private static Trail trail;
    @NotNull
    private static final Vector3f NORMAL;
    @NotNull
    private static final Vector3f EDGE_U;
    @NotNull
    private static final Vector3f EDGE_V;
    @NotNull
    private static final Vector3f SWIRL;
    @NotNull
    private static final int[] STEP_SLOT;
    @NotNull
    private static final float[] STEP_X;
    @NotNull
    private static final float[] STEP_Y;
    @NotNull
    private static final float[] STEP_Z;
    @NotNull
    private static final float[] STEP_AGE;
    @NotNull
    private static final float[] STEP_POWER;
    @NotNull
    private static final float[] STEP_FADE;
    @NotNull
    private static final float[] PX;
    @NotNull
    private static final float[] PY;
    @NotNull
    private static final float[] PZ;
    @NotNull
    private static final float[] AT;
    @NotNull
    private static final float[] AA;
    @NotNull
    private static final float[] AW;
    @NotNull
    private static final float[] SX;
    @NotNull
    private static final float[] SY;
    @NotNull
    private static final float[] SZ;
    @NotNull
    private static final int[] CORE;
    @NotNull
    private static final int[] HALO;
    @NotNull
    private static final int[] EDGE;
    private static int hotLo;
    private static int hotHi;
    private static float m00;
    private static float m01;
    private static float m02;
    private static float m10;
    private static float m11;
    private static float m12;
    private static float m20;
    private static float m21;
    private static float m22;
    private static float m30;
    private static float m31;
    private static float m32;

    private EnergyTrail() {
    }

    @JvmStatic
    public static final void clear() {
        trail = null;
    }

    @JvmStatic
    public static final void update(@NotNull LivingEntity entity, float partialTick, @NotNull Options options) {
        boolean push;
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        Intrinsics.checkNotNullParameter((Object)options, (String)"options");
        int samples = MathHelper.clamp((int)options.getSamples(), (int)6, (int)40);
        Trail current = trail;
        if (current == null || current.getCap() != samples || !(current.getSpacing() == options.getSpacing())) {
            trail = current = new Trail(samples, options.getSpacing());
        }
        long now = System.currentTimeMillis();
        current.setLastSeen(now);
        double originX = MathHelper.lerp((double)partialTick, (double)entity.lastRenderX, (double)entity.getX());
        double originY = MathHelper.lerp((double)partialTick, (double)entity.lastRenderY, (double)entity.getY());
        double originZ = MathHelper.lerp((double)partialTick, (double)entity.lastRenderZ, (double)entity.getZ());
        double moveX = entity.getX() - entity.lastRenderX;
        double moveZ = entity.getZ() - entity.lastRenderZ;
        float speed = (float)Math.sqrt(moveX * moveX + moveZ * moveZ);
        float target = MathHelper.clamp((float)((speed - options.getMinSpeed()) / 0.25f), (float)0.0f, (float)1.0f);
        long elapsed = current.getLastPower() == 0L ? 0L : MathHelper.clamp((long)(now - current.getLastPower()), (long)0L, (long)250L);
        current.setLastPower(now);
        float rate = 1.0f - (float)Math.exp((double)(-elapsed) / 190.0);
        Trail trail = current;
        trail.setSmoothPower(trail.getSmoothPower() + (target - current.getSmoothPower()) * rate);
        float intensity = current.getSmoothPower();
        current.setLivePower(intensity);
        if (intensity <= 0.01f) {
            return;
        }
        current.setLiveAnchorX(originX);
        current.setLiveAnchorY(originY);
        current.setLiveAnchorZ(originZ);
        List<TrailEchoCapture.EchoBox> boxes = TrailEchoCapture.capture(entity, partialTick, true);
        if (boxes.isEmpty()) {
            return;
        }
        long interval = Math.max(8L, (long)(options.getLifetime() / samples));
        boolean bl = push = now - current.getLastPush() >= interval;
        if (push) {
            current.openSlot(now, options.getLifetime(), intensity, originX, originY, originZ);
        }
        Arrays.fill(current.getFaceLive(), 0, current.getFaceCount(), false);
        int index = 0;
        for (TrailEchoCapture.EchoBox box : boxes) {
            INSTANCE.sampleBox(current, box, index++, options, now, push, originX, originY, originZ);
        }
    }

    @JvmStatic
    public static final void render(@NotNull WorldRenderEvent event, @NotNull Options options) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        Intrinsics.checkNotNullParameter((Object)options, (String)"options");
        Trail trail = EnergyTrail.trail;
        if (trail == null) {
            return;
        }
        Trail current = trail;
        long now = System.currentTimeMillis();
        if (!INSTANCE.prune(current, now, options.getLifetime())) {
            EnergyTrail.trail = null;
            return;
        }
        Camera camera2 = event.getCamera();
        if (camera2 == null) {
            return;
        }
        Camera camera = camera2;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        VertexConsumerProvider.Immediate immediate2 = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        RenderLayer type = ClientPipelines.trailEnergy(options.getGlow(), options.getThroughWalls());
        VertexConsumer vertexConsumer2 = provider.getBuffer(type);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        Matrix4f matrix4f = event.getStack().peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"pose(...)");
        Matrix4f matrix = matrix4f;
        m00 = matrix.m00();
        m01 = matrix.m01();
        m02 = matrix.m02();
        m10 = matrix.m10();
        m11 = matrix.m11();
        m12 = matrix.m12();
        m20 = matrix.m20();
        m21 = matrix.m21();
        m22 = matrix.m22();
        m30 = matrix.m30();
        m31 = matrix.m31();
        m32 = matrix.m32();
        float hr = (float)(options.getHead() >>> 16 & 0xFF) / 255.0f;
        float hg = (float)(options.getHead() >>> 8 & 0xFF) / 255.0f;
        float hb = (float)(options.getHead() & 0xFF) / 255.0f;
        float tr = (float)(options.getTail() >>> 16 & 0xFF) / 255.0f;
        float tg = (float)(options.getTail() >>> 8 & 0xFF) / 255.0f;
        float tb = (float)(options.getTail() & 0xFF) / 255.0f;
        int cap = current.getCap();
        int depth = Math.min(cap, current.getPushes());
        int written = 0;
        boolean drew = false;
        long interval = Math.max(8L, (long)(options.getLifetime() / MathHelper.clamp((int)options.getSamples(), (int)6, (int)40)));
        float fadeMs = Math.max(1.0f, (float)interval * 2.0f);
        boolean fresh = now - current.getLastSeen() <= 80L && current.getLivePower() > 0.0f;
        float liveX = (float)(current.getLiveAnchorX() - cam.x);
        float liveY = (float)(current.getLiveAnchorY() - cam.y);
        float liveZ = (float)(current.getLiveAnchorZ() - cam.z);
        int n = current.getFaceCount();
        for (int face = 0; face < n; ++face) {
            int steps;
            boolean live;
            long mask = current.getFaceMask()[face];
            boolean bl = live = fresh && current.getFaceLive()[face];
            if (mask == 0L && !live) continue;
            int n2 = steps = mask == 0L ? 0 : INSTANCE.steps(current, mask, depth, now, cam.x, cam.y, cam.z, options.getOpacity(), fadeMs);
            if (steps == 0 && !live) continue;
            float spawn = MathHelper.clamp((float)((float)(now - current.getFaceBorn()[face]) / 220.0f), (float)0.0f, (float)1.0f);
            int base = current.getFaceBase()[face];
            int count = current.getFaceU()[face] * current.getFaceV()[face];
            int n3 = base + count;
            for (int point = base; point < n3 && written < 250000; ++point) {
                int hi;
                int n4 = INSTANCE.strip(current, options, cap, point, steps, spawn, live, liveX, liveY, liveZ);
                if (n4 < 2 || hotHi < 0) continue;
                int lo = hotLo > 0 ? hotLo - 1 : 0;
                int n5 = hi = hotHi + 1 < n4 ? hotHi + 1 : n4 - 1;
                if (hi <= lo) continue;
                INSTANCE.sides(n4, lo, hi);
                int added = INSTANCE.ribbon(consumer, lo, hi, hr, hg, hb, tr, tg, tb);
                written += added;
                if (added <= 0) continue;
                drew = true;
            }
            if (written >= 250000) break;
        }
        if (drew) {
            provider.draw(type);
        }
    }

    private final void sampleBox(Trail trail, TrailEchoCapture.EchoBox box, int boxIndex, Options options, long now, boolean push, double originX, double originY, double originZ) {
        float[] verts = box.getVerts();
        float[] uvs = box.getUvs();
        int texture = box.getTexture().hashCode();
        int n = box.getQuadCount();
        for (int quad = 0; quad < n; ++quad) {
            int o = quad * 12;
            float ax = verts[o];
            float ay = verts[o + 1];
            float az = verts[o + 2];
            float ux = verts[o + 3] - ax;
            float uy = verts[o + 4] - ay;
            float uz = verts[o + 5] - az;
            float vx = verts[o + 9] - ax;
            float vy = verts[o + 10] - ay;
            float vz = verts[o + 11] - az;
            float lengthU = (float)Math.sqrt(ux * ux + uy * uy + uz * uz);
            float lengthV = (float)Math.sqrt(vx * vx + vy * vy + vz * vz);
            if (lengthU < 1.0E-4f || lengthV < 1.0E-4f) continue;
            NORMAL.set(uy * vz - uz * vy, uz * vx - ux * vz, ux * vy - uy * vx);
            if (NORMAL.lengthSquared() < 1.0E-9f) continue;
            NORMAL.normalize();
            float faceX = ax + (ux + vx) * 0.5f;
            float faceY = ay + (uy + vy) * 0.5f;
            float faceZ = az + (uz + vz) * 0.5f;
            if (EnergyTrail.NORMAL.x * faceX + EnergyTrail.NORMAL.y * faceY + EnergyTrail.NORMAL.z * faceZ < 0.0f) {
                NORMAL.mul(-1.0f);
            }
            int stepsU = MathHelper.clamp((int)Math.round(lengthU * 16.0f / options.getSpacing()), (int)1, (int)24);
            int stepsV = MathHelper.clamp((int)Math.round(lengthV * 16.0f / options.getSpacing()), (int)1, (int)24);
            int key = this.faceKey(texture, uvs, quad, boxIndex);
            int face = trail.openFace(key, stepsU, stepsV, now);
            if (face < 0) continue;
            trail.getFaceLive()[face] = true;
            if (push) {
                trail.getFaceMask()[face] = trail.getFaceMask()[face] | 1L << trail.getWrite();
                trail.getFacePushed()[face] = now;
            }
            EDGE_U.set(ux, uy, uz);
            EDGE_V.set(vx, vy, vz);
            int base = trail.getFaceBase()[face];
            for (int i = 0; i < stepsU; ++i) {
                float u = ((float)i + 0.5f) / (float)stepsU;
                for (int j = 0; j < stepsV; ++j) {
                    float v = ((float)j + 0.5f) / (float)stepsV;
                    float px = box.getCx() + ax + EnergyTrail.EDGE_U.x * u + EnergyTrail.EDGE_V.x * v + EnergyTrail.NORMAL.x * 0.02f;
                    float py = box.getCy() + ay + EnergyTrail.EDGE_U.y * u + EnergyTrail.EDGE_V.y * v + EnergyTrail.NORMAL.y * 0.02f;
                    float pz = box.getCz() + az + EnergyTrail.EDGE_U.z * u + EnergyTrail.EDGE_V.z * v + EnergyTrail.NORMAL.z * 0.02f;
                    this.feed(trail, base + i * stepsV + j, px, py, pz, push, originX + (double)px, originY + (double)py, originZ + (double)pz);
                }
            }
        }
    }

    private final void feed(Trail trail, int point, float px, float py, float pz, boolean push, double worldX, double worldY, double worldZ) {
        trail.getLiveX()[point] = px;
        trail.getLiveY()[point] = py;
        trail.getLiveZ()[point] = pz;
        if (!push) {
            return;
        }
        this.curl(worldX, worldY, worldZ, SWIRL);
        SWIRL.mul(0.9f).fma(0.65f, (Vector3fc)NORMAL);
        if (SWIRL.lengthSquared() > 1.0E-6f) {
            SWIRL.normalize();
        }
        int index = point * trail.getCap() + trail.getWrite();
        trail.getHx()[index] = px;
        trail.getHy()[index] = py;
        trail.getHz()[index] = pz;
        trail.getDx()[index] = EnergyTrail.SWIRL.x;
        trail.getDy()[index] = EnergyTrail.SWIRL.y;
        trail.getDz()[index] = EnergyTrail.SWIRL.z;
    }

    private final int steps(Trail trail, long mask, int depth, long now, double camX, double camY, double camZ, float opacity, float fadeMs) {
        int count = 0;
        for (int k = 0; k < depth; ++k) {
            int slot = trail.getWrite() - k;
            if (slot < 0) {
                slot += trail.getCap();
            }
            if ((mask & 1L << slot) == 0L) continue;
            EnergyTrail.STEP_SLOT[count] = slot;
            EnergyTrail.STEP_X[count] = (float)(trail.getPushX()[slot] - camX);
            EnergyTrail.STEP_Y[count] = (float)(trail.getPushY()[slot] - camY);
            EnergyTrail.STEP_Z[count] = (float)(trail.getPushZ()[slot] - camZ);
            float lived = now - trail.getPushTime()[slot];
            EnergyTrail.STEP_AGE[count] = lived / trail.getPushDuration()[slot];
            EnergyTrail.STEP_POWER[count] = opacity * 0.75f * trail.getPushIntensity()[slot];
            float fade = MathHelper.clamp((float)(lived / fadeMs), (float)0.0f, (float)1.0f);
            EnergyTrail.STEP_FADE[count] = fade * fade * (3.0f - 2.0f * fade);
            ++count;
        }
        return count;
    }

    private final int strip(Trail trail, Options options, int cap, int point, int steps, float spawn, boolean live, float liveX, float liveY, float liveZ) {
        float age;
        float invLife = trail.getLifeInv()[point];
        float bright = trail.getBrightScale()[point] * spawn;
        float width = options.getWidth() * trail.getWidthScale()[point];
        int cursor = point * cap;
        int n = 0;
        hotLo = -1;
        hotHi = -1;
        if (live) {
            EnergyTrail.PX[0] = liveX + trail.getLiveX()[point];
            EnergyTrail.PY[0] = liveY + trail.getLiveY()[point];
            EnergyTrail.PZ[0] = liveZ + trail.getLiveZ()[point];
            EnergyTrail.AT[0] = 0.0f;
            EnergyTrail.AA[0] = 0.0f;
            EnergyTrail.AW[0] = width;
            n = 1;
        }
        for (int k = 0; k < steps && n < 42 && !((age = STEP_AGE[k] * invLife) >= 1.0f); ++k) {
            float ddz;
            float ddy;
            float ddx;
            int source = cursor + STEP_SLOT[k];
            float travel = options.getDrift() * age * MathHelper.sqrt((float)age);
            float rest = 1.0f - age;
            float grow = STEP_FADE[k];
            float x = STEP_X[k] + trail.getHx()[source] + trail.getDx()[source] * travel;
            float y = STEP_Y[k] + trail.getHy()[source] + trail.getDy()[source] * travel + options.getRise() * age;
            float z = STEP_Z[k] + trail.getHz()[source] + trail.getDz()[source] * travel;
            if (n > 0 && (ddx = x - PX[n - 1]) * ddx + (ddy = y - PY[n - 1]) * ddy + (ddz = z - PZ[n - 1]) * ddz < 1.0E-8f) continue;
            float alpha = STEP_POWER[k] * bright * grow * rest * rest;
            EnergyTrail.PX[n] = x;
            EnergyTrail.PY[n] = y;
            EnergyTrail.PZ[n] = z;
            EnergyTrail.AT[n] = age;
            EnergyTrail.AA[n] = alpha;
            EnergyTrail.AW[n] = width * (1.0f - options.getShrink() * age);
            if (alpha > 0.002f) {
                if (hotLo < 0) {
                    hotLo = n;
                }
                hotHi = n;
            }
            ++n;
        }
        return n;
    }

    private final void sides(int count, int from, int to) {
        int k = from;
        if (k <= to) {
            while (true) {
                float sz;
                float sy;
                float sx;
                float side;
                float tz;
                float ty;
                int prev;
                int next;
                float tx;
                float tangent;
                if ((tangent = (float)Math.sqrt((tx = PX[next = k + 1 < count ? k + 1 : count - 1] - PX[prev = k > 0 ? k - 1 : 0]) * tx + (ty = PY[next] - PY[prev]) * ty + (tz = PZ[next] - PZ[prev]) * tz)) < 1.0E-6f) {
                    tx = 0.0f;
                    ty = 1.0f;
                    tz = 0.0f;
                    tangent = 1.0f;
                }
                tx /= tangent;
                ty /= tangent;
                tz /= tangent;
                float vx = PX[k];
                float vy = PY[k];
                float vz = PZ[k];
                float view = (float)Math.sqrt(vx * vx + vy * vy + vz * vz);
                if (view < 1.0E-4f) {
                    vx = 0.0f;
                    vy = 0.0f;
                    vz = 1.0f;
                    view = 1.0f;
                }
                if ((side = (float)Math.sqrt((sx = ty * (vz /= view) - tz * (vy /= view)) * sx + (sy = tz * (vx /= view) - tx * vz) * sy + (sz = tx * vy - ty * vx) * sz)) < 1.0E-5f) {
                    sx = ty;
                    sy = -tx;
                    sz = 0.0f;
                    side = (float)Math.sqrt(sx * sx + sy * sy);
                    if (side < 1.0E-5f) {
                        sx = 1.0f;
                        sy = 0.0f;
                        sz = 0.0f;
                        side = 1.0f;
                    }
                }
                EnergyTrail.SX[k] = sx / side;
                EnergyTrail.SY[k] = sy / side;
                EnergyTrail.SZ[k] = sz / side;
                if (k == to) break;
                ++k;
            }
        }
    }

    private final int ribbon(VertexConsumer consumer, int from, int to, float hr, float hg, float hb, float tr, float tg, float tb) {
        int k = from;
        if (k <= to) {
            while (true) {
                int core;
                float age = MathHelper.clamp((float)(AT[k] / 0.4f), (float)0.0f, (float)1.0f);
                float red = hr + (tr - hr) * age;
                float green = hg + (tg - hg) * age;
                float blue = hb + (tb - hb) * age;
                EnergyTrail.CORE[k] = core = this.pack(red, green, blue, AA[k]);
                EnergyTrail.HALO[k] = this.pack(red, green, blue, AA[k] * 0.45f);
                EnergyTrail.EDGE[k] = core & 0xFFFFFF;
                if (k == to) break;
                ++k;
            }
        }
        int written = 0;
        for (int k2 = from; k2 < to; ++k2) {
            if (AA[k2] <= 0.002f && AA[k2 + 1] <= 0.002f) continue;
            if ((HALO[k2] | HALO[k2 + 1]) >>> 24 != 0) {
                this.span(consumer, k2, AW[k2] * 2.5f * 0.5f, AW[k2 + 1] * 2.5f * 0.5f, HALO[k2], HALO[k2 + 1], EDGE[k2], EDGE[k2 + 1]);
                written += 8;
            }
            if ((CORE[k2] | CORE[k2 + 1]) >>> 24 == 0) continue;
            this.span(consumer, k2, AW[k2] * 0.5f, AW[k2 + 1] * 0.5f, CORE[k2], CORE[k2 + 1], EDGE[k2], EDGE[k2 + 1]);
            written += 8;
        }
        return written;
    }

    private final void span(VertexConsumer consumer, int k, float halfA, float halfB, int coreA, int coreB, int edgeA, int edgeB) {
        float axL = PX[k] - SX[k] * halfA;
        float ayL = PY[k] - SY[k] * halfA;
        float azL = PZ[k] - SZ[k] * halfA;
        float axR = PX[k] + SX[k] * halfA;
        float ayR = PY[k] + SY[k] * halfA;
        float azR = PZ[k] + SZ[k] * halfA;
        float bxL = PX[k + 1] - SX[k + 1] * halfB;
        float byL = PY[k + 1] - SY[k + 1] * halfB;
        float bzL = PZ[k + 1] - SZ[k + 1] * halfB;
        float bxR = PX[k + 1] + SX[k + 1] * halfB;
        float byR = PY[k + 1] + SY[k + 1] * halfB;
        float bzR = PZ[k + 1] + SZ[k + 1] * halfB;
        this.emit(consumer, axL, ayL, azL, edgeA);
        this.emit(consumer, PX[k], PY[k], PZ[k], coreA);
        this.emit(consumer, PX[k + 1], PY[k + 1], PZ[k + 1], coreB);
        this.emit(consumer, bxL, byL, bzL, edgeB);
        this.emit(consumer, PX[k], PY[k], PZ[k], coreA);
        this.emit(consumer, axR, ayR, azR, edgeA);
        this.emit(consumer, bxR, byR, bzR, edgeB);
        this.emit(consumer, PX[k + 1], PY[k + 1], PZ[k + 1], coreB);
    }

    private final void emit(VertexConsumer consumer, float x, float y, float z, int color) {
        consumer.vertex(m00 * x + (m10 * y + (m20 * z + m30)), m01 * x + (m11 * y + (m21 * z + m31)), m02 * x + (m12 * y + (m22 * z + m32))).color(color);
    }

    private final boolean prune(Trail trail, long now, int lifetime) {
        if (now - trail.getLastSeen() > (long)lifetime + 1000L) {
            return false;
        }
        boolean fresh = now - trail.getLastSeen() <= 80L;
        boolean alive = false;
        int n = trail.getFaceCount();
        for (int face = 0; face < n; ++face) {
            if (trail.getFaceBorn()[face] < 0L) continue;
            if (trail.getFaceLive()[face] && fresh) {
                alive = true;
                continue;
            }
            if (trail.getFaceMask()[face] == 0L || now - trail.getFacePushed()[face] > (long)lifetime) {
                trail.getFaceBorn()[face] = -1L;
                trail.getFaceMask()[face] = 0L;
                continue;
            }
            alive = true;
        }
        return alive || fresh;
    }

    private final void curl(double x, double y, double z, Vector3f dest) {
        double fx = x * 0.85;
        double fy = y * 0.85;
        double fz = z * 0.85;
        dest.set((float)((double)MathHelper.sin((double)(fy * 1.7 + fz * 0.9)) + 0.5 * (double)MathHelper.cos((double)(fz * 2.3 - fx * 1.1))), (float)((double)MathHelper.sin((double)(fz * 1.3 + fx * 1.9)) + 0.5 * (double)MathHelper.cos((double)(fx * 2.1 - fy * 0.7))), (float)((double)MathHelper.sin((double)(fx * 1.1 + fy * 2.1)) + 0.5 * (double)MathHelper.cos((double)(fy * 1.9 - fz * 1.3))));
        if (dest.lengthSquared() > 1.0E-6f) {
            dest.normalize();
        }
    }

    private final int faceKey(int texture, float[] uvs, int quad, int boxIndex) {
        int o = quad * 8;
        int key = texture * 31 + boxIndex * 8 + quad;
        key = key * 31 + Math.round(uvs[o] * 4096.0f);
        key = key * 31 + Math.round(uvs[o + 1] * 4096.0f);
        key = key * 31 + Math.round(uvs[o + 2] * 4096.0f);
        key = key * 31 + Math.round(uvs[o + 3] * 4096.0f);
        key = key * 31 + Math.round(uvs[o + 6] * 4096.0f);
        key = key * 31 + Math.round(uvs[o + 7] * 4096.0f);
        return key;
    }

    private final int pack(float red, float green, float blue, float alpha) {
        int a = MathHelper.clamp((int)Math.round(alpha * 255.0f), (int)0, (int)255);
        int r = MathHelper.clamp((int)Math.round(red * 255.0f), (int)0, (int)255);
        int g = MathHelper.clamp((int)Math.round(green * 255.0f), (int)0, (int)255);
        int b = MathHelper.clamp((int)Math.round(blue * 255.0f), (int)0, (int)255);
        return a << 24 | r << 16 | g << 8 | b;
    }

    private final float hash01(long key, int salt) {
        long h = key * -7046029254386353131L + (long)salt * 7146057691288625177L;
        h ^= h >>> 33;
        h *= -9143259669804075067L;
        h ^= h >>> 33;
        return (float)(h >>> 40) / 1.6777216E7f;
    }

    static {
        NORMAL = new Vector3f();
        EDGE_U = new Vector3f();
        EDGE_V = new Vector3f();
        SWIRL = new Vector3f();
        STEP_SLOT = new int[40];
        STEP_X = new float[40];
        STEP_Y = new float[40];
        STEP_Z = new float[40];
        STEP_AGE = new float[40];
        STEP_POWER = new float[40];
        STEP_FADE = new float[40];
        PX = new float[42];
        PY = new float[42];
        PZ = new float[42];
        AT = new float[42];
        AA = new float[42];
        AW = new float[42];
        SX = new float[42];
        SY = new float[42];
        SZ = new float[42];
        CORE = new int[42];
        HALO = new int[42];
        EDGE = new int[42];
        hotLo = -1;
        hotHi = -1;
        m00 = 1.0f;
        m11 = 1.0f;
        m22 = 1.0f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001a\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0014\u001a\u0004\b\u001b\u0010\u0016R\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0014\u001a\u0004\b\u001c\u0010\u0016R\u0017\u0010\t\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u001d\u0010\u0016R\u0017\u0010\n\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0014\u001a\u0004\b\u001e\u0010\u0016R\u0017\u0010\u000b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0014\u001a\u0004\b\u001f\u0010\u0016R\u0017\u0010\f\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u0014\u001a\u0004\b \u0010\u0016R\u0017\u0010\u000e\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u000f\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010!\u001a\u0004\b$\u0010#R\u0017\u0010\u0010\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0017\u001a\u0004\b%\u0010\u0019R\u0017\u0010\u0011\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0017\u001a\u0004\b&\u0010\u0019\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;", "", "", "spacing", "", "lifetime", "samples", "width", "shrink", "drift", "rise", "minSpeed", "opacity", "", "glow", "throughWalls", "head", "tail", "<init>", "(FIIFFFFFFZZII)V", "F", "getSpacing", "()F", "I", "getLifetime", "()I", "getSamples", "getWidth", "getShrink", "getDrift", "getRise", "getMinSpeed", "getOpacity", "Z", "getGlow", "()Z", "getThroughWalls", "getHead", "getTail", "rtx.kimiko:kimiko"})
    public static final class Options {
        private final float spacing;
        private final int lifetime;
        private final int samples;
        private final float width;
        private final float shrink;
        private final float drift;
        private final float rise;
        private final float minSpeed;
        private final float opacity;
        private final boolean glow;
        private final boolean throughWalls;
        private final int head;
        private final int tail;

        public Options(float spacing, int lifetime, int samples, float width, float shrink, float drift, float rise, float minSpeed, float opacity, boolean glow, boolean throughWalls, int head, int tail) {
            this.spacing = spacing;
            this.lifetime = lifetime;
            this.samples = samples;
            this.width = width;
            this.shrink = shrink;
            this.drift = drift;
            this.rise = rise;
            this.minSpeed = minSpeed;
            this.opacity = opacity;
            this.glow = glow;
            this.throughWalls = throughWalls;
            this.head = head;
            this.tail = tail;
        }

        public final float getSpacing() {
            return this.spacing;
        }

        public final int getLifetime() {
            return this.lifetime;
        }

        public final int getSamples() {
            return this.samples;
        }

        public final float getWidth() {
            return this.width;
        }

        public final float getShrink() {
            return this.shrink;
        }

        public final float getDrift() {
            return this.drift;
        }

        public final float getRise() {
            return this.rise;
        }

        public final float getMinSpeed() {
            return this.minSpeed;
        }

        public final float getOpacity() {
            return this.opacity;
        }

        public final boolean getGlow() {
            return this.glow;
        }

        public final boolean getThroughWalls() {
            return this.throughWalls;
        }

        public final int getHead() {
            return this.head;
        }

        public final int getTail() {
            return this.tail;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0010\u0013\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0013\n\u0002\u0010\u0018\n\u0002\bE\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J=\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u0011\u0010\u0012J-\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\"\u001a\u00020!8\u0006\u00a2\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010'\u001a\u00020&8\u0006\u00a2\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\u00020&8\u0006\u00a2\u0006\f\n\u0004\b+\u0010(\u001a\u0004\b,\u0010*R\u0017\u0010.\u001a\u00020-8\u0006\u00a2\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0017\u00102\u001a\u00020-8\u0006\u00a2\u0006\f\n\u0004\b2\u0010/\u001a\u0004\b3\u00101R\u0017\u00104\u001a\u00020-8\u0006\u00a2\u0006\f\n\u0004\b4\u0010/\u001a\u0004\b5\u00101R\"\u00106\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u0010\u001b\u001a\u0004\b7\u0010\u001d\"\u0004\b8\u0010\u001aR\"\u00109\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010\u001b\u001a\u0004\b:\u0010\u001d\"\u0004\b;\u0010\u001aR\"\u0010<\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010B\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010=\u001a\u0004\bC\u0010?\"\u0004\bD\u0010AR\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR\"\u0010H\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010\u001b\u001a\u0004\bI\u0010\u001d\"\u0004\bJ\u0010\u001aR\"\u0010L\u001a\u00020K8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\"\u0010S\u001a\u00020R8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\"\u0010Y\u001a\u00020R8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bY\u0010T\u001a\u0004\bZ\u0010V\"\u0004\b[\u0010XR\"\u0010\\\u001a\u00020!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\\\u0010#\u001a\u0004\b]\u0010%\"\u0004\b^\u0010_R\"\u0010`\u001a\u00020!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b`\u0010#\u001a\u0004\ba\u0010%\"\u0004\bb\u0010_R\"\u0010c\u001a\u00020!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bc\u0010#\u001a\u0004\bd\u0010%\"\u0004\be\u0010_R\"\u0010g\u001a\u00020f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\"\u0010m\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\"\u0010s\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bs\u0010n\u001a\u0004\bt\u0010p\"\u0004\bu\u0010rR\"\u0010v\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bv\u0010n\u001a\u0004\bw\u0010p\"\u0004\bx\u0010rR\"\u0010y\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\by\u0010\u001e\u001a\u0004\bz\u0010 \"\u0004\b{\u0010|R\"\u0010}\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b}\u0010\u001e\u001a\u0004\b~\u0010 \"\u0004\b\u007f\u0010|R&\u0010\u0080\u0001\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0080\u0001\u0010=\u001a\u0005\b\u0081\u0001\u0010?\"\u0005\b\u0082\u0001\u0010AR&\u0010\u0083\u0001\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0083\u0001\u0010\u001b\u001a\u0005\b\u0084\u0001\u0010\u001d\"\u0005\b\u0085\u0001\u0010\u001aR'\u0010\u0086\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u0086\u0001\u0010(\u001a\u0005\b\u0087\u0001\u0010*\"\u0006\b\u0088\u0001\u0010\u0089\u0001R'\u0010\u008a\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u008a\u0001\u0010(\u001a\u0005\b\u008b\u0001\u0010*\"\u0006\b\u008c\u0001\u0010\u0089\u0001R'\u0010\u008d\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u008d\u0001\u0010(\u001a\u0005\b\u008e\u0001\u0010*\"\u0006\b\u008f\u0001\u0010\u0089\u0001R'\u0010\u0090\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u0090\u0001\u0010(\u001a\u0005\b\u0091\u0001\u0010*\"\u0006\b\u0092\u0001\u0010\u0089\u0001R'\u0010\u0093\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u0093\u0001\u0010(\u001a\u0005\b\u0094\u0001\u0010*\"\u0006\b\u0095\u0001\u0010\u0089\u0001R'\u0010\u0096\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u0096\u0001\u0010(\u001a\u0005\b\u0097\u0001\u0010*\"\u0006\b\u0098\u0001\u0010\u0089\u0001R'\u0010\u0099\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u0099\u0001\u0010(\u001a\u0005\b\u009a\u0001\u0010*\"\u0006\b\u009b\u0001\u0010\u0089\u0001R'\u0010\u009c\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u009c\u0001\u0010(\u001a\u0005\b\u009d\u0001\u0010*\"\u0006\b\u009e\u0001\u0010\u0089\u0001R'\u0010\u009f\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u009f\u0001\u0010(\u001a\u0005\b\u00a0\u0001\u0010*\"\u0006\b\u00a1\u0001\u0010\u0089\u0001R'\u0010\u00a2\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u00a2\u0001\u0010(\u001a\u0005\b\u00a3\u0001\u0010*\"\u0006\b\u00a4\u0001\u0010\u0089\u0001R'\u0010\u00a5\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u00a5\u0001\u0010(\u001a\u0005\b\u00a6\u0001\u0010*\"\u0006\b\u00a7\u0001\u0010\u0089\u0001R'\u0010\u00a8\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\b\u00a8\u0001\u0010(\u001a\u0005\b\u00a9\u0001\u0010*\"\u0006\b\u00aa\u0001\u0010\u0089\u0001\u00a8\u0006«\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Trail;", "", "", "cap", "", "spacing", "<init>", "(IF)V", "", "now", "duration", "intensity", "", "x", "y", "z", "", "openSlot", "(JFFDDD)V", "key", "stepsU", "stepsV", "openFace", "(IIIJ)I", "size", "growPoints", "(I)V", "I", "getCap", "()I", "F", "getSpacing", "()F", "", "pushTime", "[J", "getPushTime", "()[J", "", "pushDuration", "[F", "getPushDuration", "()[F", "pushIntensity", "getPushIntensity", "", "pushX", "[D", "getPushX", "()[D", "pushY", "getPushY", "pushZ", "getPushZ", "write", "getWrite", "setWrite", "pushes", "getPushes", "setPushes", "lastPush", "J", "getLastPush", "()J", "setLastPush", "(J)V", "lastSeen", "getLastSeen", "setLastSeen", "Lit/unimi/dsi/fastutil/ints/Int2IntOpenHashMap;", "faceIndex", "Lit/unimi/dsi/fastutil/ints/Int2IntOpenHashMap;", "faceCount", "getFaceCount", "setFaceCount", "", "faceBase", "[I", "getFaceBase", "()[I", "setFaceBase", "([I)V", "", "faceU", "[B", "getFaceU", "()[B", "setFaceU", "([B)V", "faceV", "getFaceV", "setFaceV", "faceBorn", "getFaceBorn", "setFaceBorn", "([J)V", "facePushed", "getFacePushed", "setFacePushed", "faceMask", "getFaceMask", "setFaceMask", "", "faceLive", "[Z", "getFaceLive", "()[Z", "setFaceLive", "([Z)V", "liveAnchorX", "D", "getLiveAnchorX", "()D", "setLiveAnchorX", "(D)V", "liveAnchorY", "getLiveAnchorY", "setLiveAnchorY", "liveAnchorZ", "getLiveAnchorZ", "setLiveAnchorZ", "livePower", "getLivePower", "setLivePower", "(F)V", "smoothPower", "getSmoothPower", "setSmoothPower", "lastPower", "getLastPower", "setLastPower", "pointCount", "getPointCount", "setPointCount", "lifeInv", "getLifeInv", "setLifeInv", "([F)V", "widthScale", "getWidthScale", "setWidthScale", "brightScale", "getBrightScale", "setBrightScale", "liveX", "getLiveX", "setLiveX", "liveY", "getLiveY", "setLiveY", "liveZ", "getLiveZ", "setLiveZ", "hx", "getHx", "setHx", "hy", "getHy", "setHy", "hz", "getHz", "setHz", "dx", "getDx", "setDx", "dy", "getDy", "setDy", "dz", "getDz", "setDz", "rtx.kimiko:kimiko"})
    private static final class Trail {
        private final int cap;
        private final float spacing;
        @NotNull
        private final long[] pushTime;
        @NotNull
        private final float[] pushDuration;
        @NotNull
        private final float[] pushIntensity;
        @NotNull
        private final double[] pushX;
        @NotNull
        private final double[] pushY;
        @NotNull
        private final double[] pushZ;
        private int write;
        private int pushes;
        private long lastPush;
        private long lastSeen;
        @NotNull
        private final Int2IntOpenHashMap faceIndex;
        private int faceCount;
        @NotNull
        private int[] faceBase;
        @NotNull
        private byte[] faceU;
        @NotNull
        private byte[] faceV;
        @NotNull
        private long[] faceBorn;
        @NotNull
        private long[] facePushed;
        @NotNull
        private long[] faceMask;
        @NotNull
        private boolean[] faceLive;
        private double liveAnchorX;
        private double liveAnchorY;
        private double liveAnchorZ;
        private float livePower;
        private float smoothPower;
        private long lastPower;
        private int pointCount;
        @NotNull
        private float[] lifeInv;
        @NotNull
        private float[] widthScale;
        @NotNull
        private float[] brightScale;
        @NotNull
        private float[] liveX;
        @NotNull
        private float[] liveY;
        @NotNull
        private float[] liveZ;
        @NotNull
        private float[] hx;
        @NotNull
        private float[] hy;
        @NotNull
        private float[] hz;
        @NotNull
        private float[] dx;
        @NotNull
        private float[] dy;
        @NotNull
        private float[] dz;

        public Trail(int cap, float spacing) {
            this.cap = cap;
            this.spacing = spacing;
            this.pushTime = new long[this.cap];
            this.pushDuration = new float[this.cap];
            this.pushIntensity = new float[this.cap];
            this.pushX = new double[this.cap];
            this.pushY = new double[this.cap];
            this.pushZ = new double[this.cap];
            this.write = -1;
            this.faceIndex = new Int2IntOpenHashMap();
            this.faceBase = new int[16];
            this.faceU = new byte[16];
            this.faceV = new byte[16];
            this.faceBorn = new long[16];
            this.facePushed = new long[16];
            this.faceMask = new long[16];
            this.faceLive = new boolean[16];
            this.lifeInv = new float[0];
            this.widthScale = new float[0];
            this.brightScale = new float[0];
            this.liveX = new float[0];
            this.liveY = new float[0];
            this.liveZ = new float[0];
            this.hx = new float[0];
            this.hy = new float[0];
            this.hz = new float[0];
            this.dx = new float[0];
            this.dy = new float[0];
            this.dz = new float[0];
            this.faceIndex.defaultReturnValue(-1);
        }

        public final int getCap() {
            return this.cap;
        }

        public final float getSpacing() {
            return this.spacing;
        }

        @NotNull
        public final long[] getPushTime() {
            return this.pushTime;
        }

        @NotNull
        public final float[] getPushDuration() {
            return this.pushDuration;
        }

        @NotNull
        public final float[] getPushIntensity() {
            return this.pushIntensity;
        }

        @NotNull
        public final double[] getPushX() {
            return this.pushX;
        }

        @NotNull
        public final double[] getPushY() {
            return this.pushY;
        }

        @NotNull
        public final double[] getPushZ() {
            return this.pushZ;
        }

        public final int getWrite() {
            return this.write;
        }

        public final void setWrite(int n) {
            this.write = n;
        }

        public final int getPushes() {
            return this.pushes;
        }

        public final void setPushes(int n) {
            this.pushes = n;
        }

        public final long getLastPush() {
            return this.lastPush;
        }

        public final void setLastPush(long l) {
            this.lastPush = l;
        }

        public final long getLastSeen() {
            return this.lastSeen;
        }

        public final void setLastSeen(long l) {
            this.lastSeen = l;
        }

        public final int getFaceCount() {
            return this.faceCount;
        }

        public final void setFaceCount(int n) {
            this.faceCount = n;
        }

        @NotNull
        public final int[] getFaceBase() {
            return this.faceBase;
        }

        public final void setFaceBase(@NotNull int[] nArray) {
            Intrinsics.checkNotNullParameter((Object)nArray, (String)"<set-?>");
            this.faceBase = nArray;
        }

        @NotNull
        public final byte[] getFaceU() {
            return this.faceU;
        }

        public final void setFaceU(@NotNull byte[] byArray) {
            Intrinsics.checkNotNullParameter((Object)byArray, (String)"<set-?>");
            this.faceU = byArray;
        }

        @NotNull
        public final byte[] getFaceV() {
            return this.faceV;
        }

        public final void setFaceV(@NotNull byte[] byArray) {
            Intrinsics.checkNotNullParameter((Object)byArray, (String)"<set-?>");
            this.faceV = byArray;
        }

        @NotNull
        public final long[] getFaceBorn() {
            return this.faceBorn;
        }

        public final void setFaceBorn(@NotNull long[] lArray) {
            Intrinsics.checkNotNullParameter((Object)lArray, (String)"<set-?>");
            this.faceBorn = lArray;
        }

        @NotNull
        public final long[] getFacePushed() {
            return this.facePushed;
        }

        public final void setFacePushed(@NotNull long[] lArray) {
            Intrinsics.checkNotNullParameter((Object)lArray, (String)"<set-?>");
            this.facePushed = lArray;
        }

        @NotNull
        public final long[] getFaceMask() {
            return this.faceMask;
        }

        public final void setFaceMask(@NotNull long[] lArray) {
            Intrinsics.checkNotNullParameter((Object)lArray, (String)"<set-?>");
            this.faceMask = lArray;
        }

        @NotNull
        public final boolean[] getFaceLive() {
            return this.faceLive;
        }

        public final void setFaceLive(@NotNull boolean[] blArray) {
            Intrinsics.checkNotNullParameter((Object)blArray, (String)"<set-?>");
            this.faceLive = blArray;
        }

        public final double getLiveAnchorX() {
            return this.liveAnchorX;
        }

        public final void setLiveAnchorX(double d) {
            this.liveAnchorX = d;
        }

        public final double getLiveAnchorY() {
            return this.liveAnchorY;
        }

        public final void setLiveAnchorY(double d) {
            this.liveAnchorY = d;
        }

        public final double getLiveAnchorZ() {
            return this.liveAnchorZ;
        }

        public final void setLiveAnchorZ(double d) {
            this.liveAnchorZ = d;
        }

        public final float getLivePower() {
            return this.livePower;
        }

        public final void setLivePower(float f) {
            this.livePower = f;
        }

        public final float getSmoothPower() {
            return this.smoothPower;
        }

        public final void setSmoothPower(float f) {
            this.smoothPower = f;
        }

        public final long getLastPower() {
            return this.lastPower;
        }

        public final void setLastPower(long l) {
            this.lastPower = l;
        }

        public final int getPointCount() {
            return this.pointCount;
        }

        public final void setPointCount(int n) {
            this.pointCount = n;
        }

        @NotNull
        public final float[] getLifeInv() {
            return this.lifeInv;
        }

        public final void setLifeInv(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.lifeInv = fArray;
        }

        @NotNull
        public final float[] getWidthScale() {
            return this.widthScale;
        }

        public final void setWidthScale(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.widthScale = fArray;
        }

        @NotNull
        public final float[] getBrightScale() {
            return this.brightScale;
        }

        public final void setBrightScale(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.brightScale = fArray;
        }

        @NotNull
        public final float[] getLiveX() {
            return this.liveX;
        }

        public final void setLiveX(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.liveX = fArray;
        }

        @NotNull
        public final float[] getLiveY() {
            return this.liveY;
        }

        public final void setLiveY(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.liveY = fArray;
        }

        @NotNull
        public final float[] getLiveZ() {
            return this.liveZ;
        }

        public final void setLiveZ(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.liveZ = fArray;
        }

        @NotNull
        public final float[] getHx() {
            return this.hx;
        }

        public final void setHx(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.hx = fArray;
        }

        @NotNull
        public final float[] getHy() {
            return this.hy;
        }

        public final void setHy(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.hy = fArray;
        }

        @NotNull
        public final float[] getHz() {
            return this.hz;
        }

        public final void setHz(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.hz = fArray;
        }

        @NotNull
        public final float[] getDx() {
            return this.dx;
        }

        public final void setDx(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.dx = fArray;
        }

        @NotNull
        public final float[] getDy() {
            return this.dy;
        }

        public final void setDy(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.dy = fArray;
        }

        @NotNull
        public final float[] getDz() {
            return this.dz;
        }

        public final void setDz(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.dz = fArray;
        }

        public final void openSlot(long now, float duration, float intensity, double x, double y, double z) {
            this.write = this.write + 1 >= this.cap ? 0 : this.write + 1;
            int n = this.pushes;
            this.pushes = n + 1;
            this.lastPush = now;
            this.pushTime[this.write] = now;
            this.pushDuration[this.write] = Math.max(1.0f, duration);
            this.pushIntensity[this.write] = intensity;
            this.pushX[this.write] = x;
            this.pushY[this.write] = y;
            this.pushZ[this.write] = z;
            long clear = 1L << this.write ^ 0xFFFFFFFFFFFFFFFFL;
            int n2 = this.faceCount;
            for (int face = 0; face < n2; ++face) {
                this.faceMask[face] = this.faceMask[face] & clear;
            }
        }

        public final int openFace(int key, int stepsU, int stepsV, long now) {
            int existing = this.faceIndex.get(key);
            if (existing >= 0) {
                if (this.faceU[existing] != stepsU || this.faceV[existing] != stepsV) {
                    return -1;
                }
                if (this.faceBorn[existing] < 0L) {
                    this.faceBorn[existing] = now;
                    this.facePushed[existing] = now;
                    this.faceMask[existing] = 0L;
                }
                return existing;
            }
            int need = stepsU * stepsV;
            if (this.pointCount + need > 3000) {
                return -1;
            }
            if (this.faceCount == this.faceBase.length) {
                int size = this.faceCount * 2;
                int[] nArray = Arrays.copyOf(this.faceBase, size);
                Intrinsics.checkNotNullExpressionValue((Object)nArray, (String)"copyOf(...)");
                this.faceBase = nArray;
                byte[] byArray = Arrays.copyOf(this.faceU, size);
                Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"copyOf(...)");
                this.faceU = byArray;
                byte[] byArray2 = Arrays.copyOf(this.faceV, size);
                Intrinsics.checkNotNullExpressionValue((Object)byArray2, (String)"copyOf(...)");
                this.faceV = byArray2;
                long[] lArray = Arrays.copyOf(this.faceBorn, size);
                Intrinsics.checkNotNullExpressionValue((Object)lArray, (String)"copyOf(...)");
                this.faceBorn = lArray;
                long[] lArray2 = Arrays.copyOf(this.facePushed, size);
                Intrinsics.checkNotNullExpressionValue((Object)lArray2, (String)"copyOf(...)");
                this.facePushed = lArray2;
                long[] lArray3 = Arrays.copyOf(this.faceMask, size);
                Intrinsics.checkNotNullExpressionValue((Object)lArray3, (String)"copyOf(...)");
                this.faceMask = lArray3;
                boolean[] blArray = Arrays.copyOf(this.faceLive, size);
                Intrinsics.checkNotNullExpressionValue((Object)blArray, (String)"copyOf(...)");
                this.faceLive = blArray;
            }
            this.growPoints(this.pointCount + need);
            int n = this.faceCount;
            this.faceCount = n + 1;
            int index = n;
            this.faceBase[index] = this.pointCount;
            this.faceU[index] = (byte)stepsU;
            this.faceV[index] = (byte)stepsV;
            this.faceBorn[index] = now;
            this.facePushed[index] = now;
            this.faceMask[index] = 0L;
            this.faceIndex.put(key, index);
            for (int i = 0; i < stepsU; ++i) {
                for (int j = 0; j < stepsV; ++j) {
                    int point = this.pointCount + i * stepsV + j;
                    long hash = (long)key << 16 | (long)i << 8 | (long)j;
                    this.lifeInv[point] = 1.0f / (0.55f + 0.45f * INSTANCE.hash01(hash, 1));
                    this.widthScale[point] = 0.55f + 0.9f * INSTANCE.hash01(hash, 2);
                    this.brightScale[point] = 0.6f + 0.4f * INSTANCE.hash01(hash, 3);
                }
            }
            this.pointCount += need;
            return index;
        }

        private final void growPoints(int size) {
            if (this.lifeInv.length >= size) {
                return;
            }
            int room = Math.min(3000, Math.max(size, Math.max(64, this.lifeInv.length * 2)));
            float[] fArray = Arrays.copyOf(this.lifeInv, room);
            Intrinsics.checkNotNullExpressionValue((Object)fArray, (String)"copyOf(...)");
            this.lifeInv = fArray;
            float[] fArray2 = Arrays.copyOf(this.widthScale, room);
            Intrinsics.checkNotNullExpressionValue((Object)fArray2, (String)"copyOf(...)");
            this.widthScale = fArray2;
            float[] fArray3 = Arrays.copyOf(this.brightScale, room);
            Intrinsics.checkNotNullExpressionValue((Object)fArray3, (String)"copyOf(...)");
            this.brightScale = fArray3;
            float[] fArray4 = Arrays.copyOf(this.liveX, room);
            Intrinsics.checkNotNullExpressionValue((Object)fArray4, (String)"copyOf(...)");
            this.liveX = fArray4;
            float[] fArray5 = Arrays.copyOf(this.liveY, room);
            Intrinsics.checkNotNullExpressionValue((Object)fArray5, (String)"copyOf(...)");
            this.liveY = fArray5;
            float[] fArray6 = Arrays.copyOf(this.liveZ, room);
            Intrinsics.checkNotNullExpressionValue((Object)fArray6, (String)"copyOf(...)");
            this.liveZ = fArray6;
            int slots = room * this.cap;
            float[] fArray7 = Arrays.copyOf(this.hx, slots);
            Intrinsics.checkNotNullExpressionValue((Object)fArray7, (String)"copyOf(...)");
            this.hx = fArray7;
            float[] fArray8 = Arrays.copyOf(this.hy, slots);
            Intrinsics.checkNotNullExpressionValue((Object)fArray8, (String)"copyOf(...)");
            this.hy = fArray8;
            float[] fArray9 = Arrays.copyOf(this.hz, slots);
            Intrinsics.checkNotNullExpressionValue((Object)fArray9, (String)"copyOf(...)");
            this.hz = fArray9;
            float[] fArray10 = Arrays.copyOf(this.dx, slots);
            Intrinsics.checkNotNullExpressionValue((Object)fArray10, (String)"copyOf(...)");
            this.dx = fArray10;
            float[] fArray11 = Arrays.copyOf(this.dy, slots);
            Intrinsics.checkNotNullExpressionValue((Object)fArray11, (String)"copyOf(...)");
            this.dy = fArray11;
            float[] fArray12 = Arrays.copyOf(this.dz, slots);
            Intrinsics.checkNotNullExpressionValue((Object)fArray12, (String)"copyOf(...)");
            this.dz = fArray12;
        }
    }
}

