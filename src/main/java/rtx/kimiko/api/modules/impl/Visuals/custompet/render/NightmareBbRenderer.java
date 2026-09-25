/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.RenderSetup
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.world.World
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  net.minecraft.sound.SoundEvent
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3fc
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.render;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.World;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3fc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0080\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0018\u00bd\u0001\u00be\u0001\u00bf\u0001\u00c0\u0001\u00c1\u0001\u00c2\u0001\u00c3\u0001\u00c4\u0001\u00c5\u0001\u00c6\u0001\u00c7\u0001\u00c8\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0007\u0010\u0003J\u001d\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ[\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00132\u0006\u0010\u0018\u001a\u00020\u00172\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ=\u0010%\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020'H\u0002\u00a2\u0006\u0004\b*\u0010+JG\u0010.\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2\u0006\u0010(\u001a\u00020'2\u0006\u0010-\u001a\u00020,H\u0002\u00a2\u0006\u0004\b.\u0010/J?\u00103\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u001f2\u0006\u00102\u001a\u0002012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010-\u001a\u00020,2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b3\u00104J/\u00108\u001a\u00020\u00042\u0006\u00102\u001a\u0002012\u0006\u0010\u001d\u001a\u0002052\u0006\u00107\u001a\u0002062\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b8\u00109J/\u0010@\u001a\u00020\u00042\u0006\u0010;\u001a\u00020:2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010=\u001a\u00020<2\u0006\u0010?\u001a\u00020>H\u0002\u00a2\u0006\u0004\b@\u0010AJ1\u0010B\u001a\u00020\u00042\u0006\u0010;\u001a\u00020:2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bB\u0010CJ\u0019\u0010F\u001a\u0004\u0018\u00010E2\u0006\u0010D\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bF\u0010GJ)\u0010I\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010=\u001a\u00020<2\u0006\u0010H\u001a\u00020<H\u0002\u00a2\u0006\u0004\bI\u0010JJ\u001f\u0010L\u001a\u00020\u00112\u0006\u0010=\u001a\u00020<2\u0006\u0010K\u001a\u00020<H\u0002\u00a2\u0006\u0004\bL\u0010MJ\u0017\u0010O\u001a\u00020\u00172\u0006\u0010N\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bO\u0010PJ)\u0010R\u001a\u00020\u00152\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010Q\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bR\u0010SJ'\u0010\u0018\u001a\u00020\u00152\u0006\u0010T\u001a\u00020\u00152\u0006\u0010U\u001a\u00020\u00152\u0006\u0010V\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0018\u0010WJ1\u0010]\u001a\u00020Z2\b\u0010Y\u001a\u0004\u0018\u00010X2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010[\u001a\u00020Z2\u0006\u0010\\\u001a\u00020,H\u0002\u00a2\u0006\u0004\b]\u0010^J-\u0010c\u001a\u00020Z2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020`0_2\u0006\u0010b\u001a\u00020#2\u0006\u0010\\\u001a\u00020,H\u0002\u00a2\u0006\u0004\bc\u0010dJ-\u0010e\u001a\u00020Z2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020`0_2\u0006\u0010b\u001a\u00020#2\u0006\u0010\\\u001a\u00020,H\u0002\u00a2\u0006\u0004\be\u0010dJ'\u0010f\u001a\u00020Z2\u0006\u0010T\u001a\u00020Z2\u0006\u0010U\u001a\u00020Z2\u0006\u0010V\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bf\u0010gJ7\u0010m\u001a\u00020Z2\u0006\u0010h\u001a\u00020Z2\u0006\u0010i\u001a\u00020Z2\u0006\u0010j\u001a\u00020Z2\u0006\u0010k\u001a\u00020Z2\u0006\u0010l\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bm\u0010nJ\u0011\u0010o\u001a\u0004\u0018\u00010>H\u0002\u00a2\u0006\u0004\bo\u0010pJ\u0011\u0010q\u001a\u0004\u0018\u00010>H\u0002\u00a2\u0006\u0004\bq\u0010pJA\u0010w\u001a\u0004\u0018\u00010\r2\u0006\u0010s\u001a\u00020r2\u0012\u0010u\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020t0\u00132\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0013H\u0002\u00a2\u0006\u0004\bw\u0010xJC\u0010~\u001a\u00020\u00042\u0006\u0010y\u001a\u00020t2\u0006\u0010z\u001a\u00020\r2\u0006\u0010{\u001a\u00020\u00172\u0006\u0010|\u001a\u00020\u00172\u0012\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020,0\u0013H\u0002\u00a2\u0006\u0004\b~\u0010\u007fJ-\u0010\u0085\u0001\u001a\u00020\u00042\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u000f\u0010\u0084\u0001\u001a\n\u0012\u0005\u0012\u00030\u0083\u00010\u0082\u0001H\u0002\u00a2\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J&\u0010\u0089\u0001\u001a\u00020\u00042\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\b\u0010\u0088\u0001\u001a\u00030\u0087\u0001H\u0002\u00a2\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u001a\u0010\u008b\u0001\u001a\u00020Z2\u0006\u0010N\u001a\u00020ZH\u0002\u00a2\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\u001a\u0010\u008d\u0001\u001a\u00020Z2\u0006\u0010N\u001a\u00020ZH\u0002\u00a2\u0006\u0006\b\u008d\u0001\u0010\u008c\u0001J\u001a\u0010\u008e\u0001\u001a\u00020Z2\u0006\u0010N\u001a\u00020ZH\u0002\u00a2\u0006\u0006\b\u008e\u0001\u0010\u008c\u0001J#\u0010\u0090\u0001\u001a\u00020\u00172\u0007\u0010\u008f\u0001\u001a\u00020t2\u0006\u0010D\u001a\u00020\u0014H\u0002\u00a2\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J#\u0010\u0092\u0001\u001a\u00020\u00142\u0007\u0010\u008f\u0001\u001a\u00020t2\u0006\u0010D\u001a\u00020\u0014H\u0002\u00a2\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u001c\u0010\u0095\u0001\u001a\u00020Z2\b\u0010\u0094\u0001\u001a\u00030\u0080\u0001H\u0002\u00a2\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0017\u0010\u0097\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0017\u0010\u0099\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0098\u0001R\u0017\u0010\u009a\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0098\u0001R\u0017\u0010\u009b\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0098\u0001R\u0017\u0010\u009c\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0098\u0001R\u0017\u0010\u009d\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u0098\u0001R\u0017\u0010\u009e\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u0098\u0001R\u0017\u0010\u009f\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u0098\u0001R\u0017\u0010\u00a0\u0001\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u00a1\u0001R\u0017\u0010\u00a2\u0001\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u00a1\u0001R\u0017\u0010\u00a3\u0001\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u00a1\u0001R\u0017\u0010\u00a4\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u0098\u0001R\u0017\u0010\u00a5\u0001\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u0098\u0001R\u0017\u0010\u00a6\u0001\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u00a7\u0001R\u0017\u0010\u00a8\u0001\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u00a9\u0001R\u0017\u0010\u00aa\u0001\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00aa\u0001\u0010\u00a9\u0001R\u0017\u0010«\u0001\u001a\u00020<8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00ac\u0001R\u0017\u0010\u00ad\u0001\u001a\u00020<8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u00ac\u0001R\u0017\u0010\u00ae\u0001\u001a\u00020Z8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00af\u0001R\u0017\u0010\u00b0\u0001\u001a\u00020Z8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u00af\u0001R\u0018\u0010\u00b2\u0001\u001a\u00030\u00b1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00b3\u0001R#\u0010\u00b4\u0001\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020)0\u00198\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00b5\u0001R#\u0010\u00b6\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u00138\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u00b5\u0001R#\u0010\u00b7\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020E0\u00198\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00b5\u0001R#\u0010\u00b8\u0001\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020:0\u00198\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b8\u0001\u0010\u00b5\u0001R\u0018\u0010\u00ba\u0001\u001a\u00030\u00b9\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ba\u0001\u0010»\u0001R\u0019\u0010o\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bo\u0010\u00bc\u0001\u00a8\u0006\u00c9\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "preload", "reset", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "pet", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Pose;", "capture", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;)Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Pose;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Node;", "node", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Animation;", "animation", "", "time", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;", "blendFrom", "", "blend", "", "out", "resolveTransforms", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Node;Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Animation;DLjava/util/Map;FLjava/util/Map;)V", "pose", "bodyRot", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "light", "render", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Pose;FLnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;I)V", "Lnet/minecraft/Identifier;", "texture", "Lnet/minecraft/RenderLayer;", "unlitCutout", "(Lnet/minecraft/Identifier;)Lnet/minecraft/RenderLayer;", "", "eyePass", "submitBatches", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Pose;FLnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;ILnet/minecraft/Identifier;Z)V", "stack", "Lnet/minecraft/VertexConsumer;", "buffer", "renderNode", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Node;ZLrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Pose;I)V", "Lnet/minecraft/MatrixStack$Entry;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Batch;", "batch", "drawBatch", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Batch;I)V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Playback;", "state", "", "now", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;", "loaded", "updateState", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Playback;Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;JLrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;)V", "emitSounds", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Playback;Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Animation;D)V", "key", "Lnet/minecraft/SoundEvent;", "resolveSound", "(Ljava/lang/String;)Lnet/minecraft/SoundEvent;", "startedAt", "animationTime", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Animation;JJ)D", "then", "secondsSince", "(JJ)D", "value", "smoothstep", "(F)F", "boneName", "sample", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Animation;Ljava/lang/String;D)Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;", "from", "to", "amount", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;F)Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Channel;", "channel", "", "fallback", "loop", "sampleChannel", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Channel;D[FZ)[F", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Key;", "keys", "index", "neighbourBefore", "(Ljava/util/List;IZ)[F", "neighbourAfter", "lerp", "([F[FF)[F", "p0", "p1", "p2", "p3", "t", "catmullRom", "([F[F[F[FF)[F", "model", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;", "load", "Lcom/google/gson/JsonElement;", "element", "Lcom/google/gson/JsonObject;", "groupsByUuid", "meshHolders", "buildNode", "(Lcom/google/gson/JsonElement;Ljava/util/Map;Ljava/util/Map;)Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Node;", "mesh", "holder", "uvWidth", "uvHeight", "eyeTextures", "bakeMesh", "(Lcom/google/gson/JsonObject;Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Node;FFLjava/util/Map;)V", "Lcom/google/gson/JsonArray;", "keyframes", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$SoundCue;", "sounds", "parseSoundCues", "(Lcom/google/gson/JsonArray;Ljava/util/List;)V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$BoneChannels;", "channels", "parseKeyframes", "(Lcom/google/gson/JsonArray;Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$BoneChannels;)V", "convertRotation", "([F)[F", "convertPosition", "convertPivot", "obj", "readFloat", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)F", "readString", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "array", "vector", "(Lcom/google/gson/JsonArray;)[F", "IDLE", "Ljava/lang/String;", "WALK", "RUN", "JUMP", "SWIM", "FLY", "TALK", "LAUGH", "MODEL", "Lnet/minecraft/Identifier;", "BODY_TEXTURE", "EYE_TEXTURE", "BODY_TEXTURE_ID", "EYE_TEXTURE_ID", "MODEL_SCALE", "F", "BLEND_SECONDS", "D", "RUN_ANIMATION_SPEED", "IDLE_VOICE_MIN_MS", "J", "IDLE_VOICE_MAX_MS", "ZERO", "[F", "ONE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "UNLIT_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "UNLIT_RENDER_TYPES", "Ljava/util/Map;", "SOUND_CUES", "RESOLVED_SOUNDS", "playbackStates", "Ljava/util/concurrent/atomic/AtomicBoolean;", "loadStarted", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;", "Model", "Node", "Batch", "BatchBuilder", "Animation", "SoundCue", "BoneChannels", "Channel", "Key", "Transform", "Pose", "Playback", "rtx.kimiko:kimiko"})
public final class NightmareBbRenderer {
    @NotNull
    public static final NightmareBbRenderer INSTANCE = new NightmareBbRenderer();
    @NotNull
    private static final String IDLE = "idle";
    @NotNull
    private static final String WALK = "walk";
    @NotNull
    private static final String RUN = "run";
    @NotNull
    private static final String JUMP = "jump";
    @NotNull
    private static final String SWIM = "swim";
    @NotNull
    private static final String FLY = "fly";
    @NotNull
    private static final String TALK = "talk";
    @NotNull
    private static final String LAUGH = "laugh";
    @NotNull
    private static final Identifier MODEL;
    @NotNull
    private static final Identifier BODY_TEXTURE;
    @NotNull
    private static final Identifier EYE_TEXTURE;
    @NotNull
    private static final String BODY_TEXTURE_ID = "0";
    @NotNull
    private static final String EYE_TEXTURE_ID = "1";
    private static final float MODEL_SCALE = 0.58f;
    private static final double BLEND_SECONDS = 0.18;
    private static final double RUN_ANIMATION_SPEED = 1.35;
    private static final long IDLE_VOICE_MIN_MS = 26000L;
    private static final long IDLE_VOICE_MAX_MS = 62000L;
    @NotNull
    private static final float[] ZERO;
    @NotNull
    private static final float[] ONE;
    @NotNull
    private static final RenderPipeline UNLIT_PIPELINE;
    @NotNull
    private static final Map<Identifier, RenderLayer> UNLIT_RENDER_TYPES;
    @NotNull
    private static final Map<String, String> SOUND_CUES;
    @NotNull
    private static final Map<String, SoundEvent> RESOLVED_SOUNDS;
    @NotNull
    private static final Map<CustomPetEntity, Playback> playbackStates;
    @NotNull
    private static final AtomicBoolean loadStarted;
    @Nullable
    private static volatile Model model;

    private NightmareBbRenderer() {
    }

    @JvmStatic
    public static final void preload() {
        INSTANCE.model();
    }

    @JvmStatic
    public static final void reset() {
        playbackStates.clear();
    }

    @JvmStatic
    @Nullable
    public static final Pose capture(@NotNull CustomPetEntity pet) {
        Intrinsics.checkNotNullParameter((Object)((Object)pet), (String)"pet");
        Model model = INSTANCE.model();
        if (model == null) {
            return null;
        }
        Model loaded = model;
        if (loaded.getRoots().isEmpty()) {
            return null;
        }
        long now = System.nanoTime();
        Playback playback = playbackStates.computeIfAbsent(pet, it -> new Playback(now));
        Intrinsics.checkNotNullExpressionValue((Object)playback, (String)"computeIfAbsent(...)");
        Playback state = playback;
        INSTANCE.updateState(state, pet, now, loaded);
        Animation current = loaded.getAnimations().get(state.getAnimation());
        double currentTime = INSTANCE.animationTime(current, now, state.getAnimationStartedAt());
        INSTANCE.emitSounds(state, pet, current, currentTime);
        state.setPreviousTime(currentTime);
        float blend = state.getBlendFrom() == null ? 1.0f : INSTANCE.smoothstep((float)Math.min(1.0, INSTANCE.secondsSince(now, state.getTransitionStartedAt()) / 0.18));
        HashMap transforms = new HashMap();
        for (Node root : loaded.getRoots()) {
            INSTANCE.resolveTransforms(root, current, currentTime, state.getBlendFrom(), blend, transforms);
        }
        if (blend >= 1.0f) {
            state.setBlendFrom(null);
        }
        state.getRendered().clear();
        state.getRendered().putAll(transforms);
        return new Pose(loaded, transforms);
    }

    private final void resolveTransforms(Node node, Animation animation, double time, Map<String, Transform> blendFrom, float blend, Map<String, Transform> out) {
        Transform from;
        Transform transform = this.sample(animation, node.getName(), time);
        if (blendFrom != null && blend < 1.0f && (from = blendFrom.get(node.getName())) != null) {
            transform = this.blend(from, transform, blend);
        }
        out.put(node.getName(), transform);
        for (Node child : node.getChildren()) {
            this.resolveTransforms(child, animation, time, blendFrom, blend, out);
        }
    }

    @JvmStatic
    public static final void render(@Nullable Pose pose, float bodyRot, @NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue collector, int light) {
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        if (pose == null) {
            return;
        }
        INSTANCE.submitBatches(pose, bodyRot, poseStack, collector, light, BODY_TEXTURE, false);
        INSTANCE.submitBatches(pose, bodyRot, poseStack, collector, light, EYE_TEXTURE, true);
    }

    private final RenderLayer unlitCutout(Identifier texture) {
        RenderLayer renderLayer2 = UNLIT_RENDER_TYPES.computeIfAbsent(texture, tex -> unlitCutout$lambda$0(tex));
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"computeIfAbsent(...)");
        return renderLayer2;
    }

    private final void submitBatches(Pose pose, float bodyRot, MatrixStack poseStack, OrderedRenderCommandQueue collector, int light, Identifier texture, boolean eyePass) {
        collector.submitCustom(poseStack, this.unlitCutout(texture), (arg_0, arg_1) -> NightmareBbRenderer.submitBatches$lambda$0(bodyRot, pose, eyePass, light, arg_0, arg_1));
    }

    private final void renderNode(MatrixStack stack, VertexConsumer buffer, Node node, boolean eyePass, Pose pose, int light) {
        stack.push();
        Transform transform = pose.getTransforms().get(node.getName());
        if (transform == null) {
            transform = new Transform(ZERO, ZERO, ONE);
        }
        float px = node.getPivot()[0];
        float py = node.getPivot()[1];
        float pz = node.getPivot()[2];
        stack.translate(px + transform.getPosition()[0], py + transform.getPosition()[1], pz + transform.getPosition()[2]);
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(node.getRotation()[2] + transform.getRotation()[2]));
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(node.getRotation()[0] + transform.getRotation()[0]));
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(node.getRotation()[1] + transform.getRotation()[1]));
        stack.scale(transform.getScale()[0], transform.getScale()[1], transform.getScale()[2]);
        stack.translate(-px, -py, -pz);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry current = entry2;
        for (Batch batch : node.getBatches()) {
            if (batch.getEyeTexture() != eyePass) continue;
            this.drawBatch(buffer, current, batch, light);
        }
        for (Node child : node.getChildren()) {
            this.renderNode(stack, buffer, child, eyePass, pose, light);
        }
        stack.pop();
    }

    private final void drawBatch(VertexConsumer buffer, MatrixStack.Entry pose, Batch batch, int light) {
        float[] positions = batch.getPositions();
        float[] uvs = batch.getUvs();
        Matrix4f matrix4f = pose.getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"pose(...)");
        Matrix4f matrix = matrix4f;
        Vector3f scratchPosition = new Vector3f();
        int n = batch.getFaceCount();
        for (int face = 0; face < n; ++face) {
            int p = face * 12;
            int t = face * 8;
            for (int corner = 0; corner < 4; ++corner) {
                int pi = p + corner * 3;
                int ti = t + corner * 2;
                matrix.transformPosition(positions[pi], positions[pi + 1], positions[pi + 2], scratchPosition);
                buffer.vertex(scratchPosition.x, scratchPosition.y, scratchPosition.z).color(-1).texture(uvs[ti], uvs[ti + 1]).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(0.0f, 1.0f, 0.0f);
            }
        }
    }

    private final void updateState(Playback state, CustomPetEntity pet, long now, Model loaded) {
        boolean voiceActive;
        String desired = pet.isTouchingWater() ? SWIM : (pet.isAirborneMode() ? FLY : (!pet.isOnGround() ? JUMP : (!pet.isPetMoving() ? IDLE : (pet.getCurrentAnimationSpeed() >= 1.35 ? RUN : WALK))));
        boolean bl = voiceActive = Intrinsics.areEqual((Object)TALK, (Object)state.getAnimation()) || Intrinsics.areEqual((Object)LAUGH, (Object)state.getAnimation());
        if (voiceActive) {
            boolean finished;
            Animation playing = loaded.getAnimations().get(state.getAnimation());
            boolean bl2 = finished = playing == null || this.secondsSince(now, state.getAnimationStartedAt()) >= playing.getLength();
            if (!finished && Intrinsics.areEqual((Object)IDLE, (Object)desired)) {
                return;
            }
            if (!finished) {
                state.setNextVoiceAt(now + state.nextVoiceDelay());
            }
        } else if (Intrinsics.areEqual((Object)IDLE, (Object)desired) && now >= state.getNextVoiceAt()) {
            desired = state.randomWindow(0L, 2L) == 0L ? TALK : LAUGH;
            state.setNextVoiceAt(now + state.nextVoiceDelay());
        }
        if (Intrinsics.areEqual((Object)desired, (Object)state.getAnimation())) {
            return;
        }
        if (!loaded.getAnimations().containsKey(desired)) {
            return;
        }
        state.setBlendFrom((Map<String, Transform>)new HashMap<String, Transform>(state.getRendered()));
        state.setAnimation(desired);
        state.setAnimationStartedAt(now);
        state.setTransitionStartedAt(now);
        state.setPreviousTime(0.0);
    }

    private final void emitSounds(Playback state, CustomPetEntity pet, Animation animation, double time) {
        if (animation == null || animation.getSounds().isEmpty()) {
            return;
        }
        World world2 = pet.getEntityWorld();
        if (world2 == null) {
            return;
        }
        World level = world2;
        double from = state.getPreviousTime();
        boolean wrapped = time < from;
        for (SoundCue cue : animation.getSounds()) {
            boolean crossed = wrapped ? cue.getTime() > from || cue.getTime() <= time : cue.getTime() > from && cue.getTime() <= time;
            if (!crossed) continue;
            SoundEvent sound = this.resolveSound(cue.getKey());
            if (sound == null) continue;
            level.playSoundClient(pet.getX(), pet.getY(), pet.getZ(), sound, pet.getSoundCategory(), 0.7f, 0.96f + pet.getRandom().nextFloat() * 0.08f, false);
        }
    }

    private final SoundEvent resolveSound(String key) {
        String string = SOUND_CUES.get(key);
        if (string == null) {
            return null;
        }
        String path = string;
        SoundEvent sound = RESOLVED_SOUNDS.get(path);
        if (sound == null) {
            try {
                sound = SoundEvent.of((Identifier)Identifier.of((String)Kimiko.Companion.namespace(), (String)path));
                RESOLVED_SOUNDS.put(path, sound);
            }
            catch (Throwable throwable) {
                return null;
            }
        }
        return sound;
    }

    private final double animationTime(Animation animation, long now, long startedAt) {
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

    private final Transform sample(Animation animation, String boneName, double time) {
        Object object = animation;
        BoneChannels channels = animation != null && animation.getBones() != null ? animation.getBones().get(boneName) : null;
        boolean loop = animation != null && animation.getLoop();
        BoneChannels boneChannels = channels;
        BoneChannels boneChannels2 = channels;
        BoneChannels boneChannels3 = channels;
        return new Transform(this.sampleChannel(boneChannels != null ? boneChannels.getPosition() : null, time, ZERO, loop), this.sampleChannel(boneChannels2 != null ? boneChannels2.getRotation() : null, time, ZERO, loop), this.sampleChannel(boneChannels3 != null ? boneChannels3.getScale() : null, time, ONE, loop));
    }

    private final Transform blend(Transform from, Transform to, float amount) {
        return new Transform(this.lerp(from.getPosition(), to.getPosition(), amount), this.lerp(from.getRotation(), to.getRotation(), amount), this.lerp(from.getScale(), to.getScale(), amount));
    }

    private final float[] sampleChannel(Channel channel, double time, float[] fallback, boolean loop) {
        float amount;
        int index;
        if (channel == null || channel.getKeys().isEmpty()) {
            return fallback;
        }
        List<Key> keys = channel.getKeys();
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
        float f = amount = span <= 0.0 ? 0.0f : (float)((time - from.getTime()) / span);
        if (from.getSmooth() || to.getSmooth()) {
            return this.catmullRom(this.neighbourBefore(keys, index, loop), from.getValue(), to.getValue(), this.neighbourAfter(keys, index, loop), amount);
        }
        return this.lerp(from.getValue(), to.getValue(), amount);
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

    private final float[] lerp(float[] from, float[] to, float amount) {
        float[] fArray = new float[]{from[0] + (to[0] - from[0]) * amount, from[1] + (to[1] - from[1]) * amount, from[2] + (to[2] - from[2]) * amount};
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

    private final Model model() {
        Model loaded;
        Model model = loaded = NightmareBbRenderer.model;
        if (model != null) {
            return model;
        }
        if (loadStarted.compareAndSet(false, true)) {
            Thread loader = new Thread(NightmareBbRenderer::model$lambda$0, "Kimiko-NightmareBB-Load");
            loader.setDaemon(true);
            loader.start();
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final Model load() {
        try {
            Object element;
            Object element2;
            Object element3;
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
            Closeable closeable = new BufferedInputStream(((Resource)resource.get()).getInputStream(), 65536);
            Throwable throwable = null;
            try {
                BufferedInputStream stream = (BufferedInputStream)closeable;
                boolean bl = false;
                JsonObject jsonObject = JsonParser.parseReader((Reader)new InputStreamReader((InputStream)stream, StandardCharsets.UTF_8)).getAsJsonObject();
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
            HashMap<String, Boolean> eyeTextures = new HashMap<>();
            if (root.has("textures") && root.get("textures").isJsonArray()) {
                for (JsonElement el : root.getAsJsonArray("textures")) {
                    JsonObject texture = el.getAsJsonObject();
                    String id = this.readString(texture, "id");
                    if (Intrinsics.areEqual((Object)EYE_TEXTURE_ID, (Object)id)) {
                        eyeTextures.put(id, true);
                    } else if (Intrinsics.areEqual((Object)BODY_TEXTURE_ID, (Object)id)) {
                        eyeTextures.put(id, false);
                    }
                }
            }
            HashMap<String, Node> meshHolders = new HashMap<>();
            if (root.has("elements") && root.get("elements").isJsonArray()) {
                for (JsonElement el : root.getAsJsonArray("elements")) {
                    JsonObject mesh = el.getAsJsonObject();
                    if (!Intrinsics.areEqual((Object)"mesh", (Object)this.readString(mesh, "type"))) continue;
                    Node holder = new Node(this.readString(mesh, "name"), ZERO, ZERO);
                    this.bakeMesh(mesh, holder, uvWidth, uvHeight, eyeTextures);
                    meshHolders.put(this.readString(mesh, "uuid"), holder);
                }
            }
            HashMap<String, JsonObject> groupsByUuid = new HashMap<>();
            if (root.has("groups") && root.get("groups").isJsonArray()) {
                for (JsonElement el : root.getAsJsonArray("groups")) {
                    JsonObject group = el.getAsJsonObject();
                    groupsByUuid.put(this.readString(group, "uuid"), group);
                }
            }
            ArrayList<Node> roots = new ArrayList<Node>();
            if (root.has("outliner") && root.get("outliner").isJsonArray()) {
                for (JsonElement element4 : root.getAsJsonArray("outliner")) {
                    Node node = this.buildNode(element4, groupsByUuid, meshHolders);
                    if (node == null) continue;
                    roots.add(node);
                }
            }
            HashMap animations = new HashMap();
            Iterator iterator6 = root.getAsJsonArray("animations").iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator6, (String)"iterator(...)");
            Iterator iterator7 = iterator6;
            while (iterator7.hasNext()) {
                JsonElement element5 = (JsonElement)iterator7.next();
                JsonObject animationJson = element5.getAsJsonObject();
                double d = animationJson.get("length").getAsDouble();
                Intrinsics.checkNotNull((Object)animationJson);
                Animation animation = new Animation(d, Intrinsics.areEqual((Object)"loop", (Object)this.readString(animationJson, "loop")));
                JsonObject animators = animationJson.getAsJsonObject("animators");
                if (animators != null) {
                    for (String uuid : animators.keySet()) {
                        JsonObject animator = animators.getAsJsonObject(uuid);
                        if (!animator.has("keyframes")) continue;
                        JsonArray keyframes = animator.getAsJsonArray("keyframes");
                        Intrinsics.checkNotNull((Object)animator);
                        if (Intrinsics.areEqual((Object)"effect", (Object)this.readString(animator, "type"))) {
                            Intrinsics.checkNotNull((Object)keyframes);
                            this.parseSoundCues(keyframes, animation.getSounds());
                            continue;
                        }
                        BoneChannels channels = new BoneChannels();
                        Intrinsics.checkNotNull((Object)keyframes);
                        this.parseKeyframes(keyframes, channels);
                        channels.getPosition().sort();
                        channels.getRotation().sort();
                        channels.getScale().sort();
                        animation.getBones().put(this.readString(animator, "name"), channels);
                    }
                }
                List<SoundCue> list = animation.getSounds();
                Comparator<SoundCue> comparator = Comparator.comparingDouble(SoundCue::getTime);
                Intrinsics.checkNotNullExpressionValue(comparator, (String)"comparingDouble(...)");
                CollectionsKt.sortWith(list, comparator);
                ((Map)animations).put(this.readString(animationJson, "name"), animation);
            }
            return new Model((List<Node>)roots, animations);
        }
        catch (Exception exception) {
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
        float[] pivot = (group != null && group.has("origin")) ?
            this.convertPivot(this.vector(group.getAsJsonArray("origin"))) :
            (float[])ZERO.clone();
        float[] rotation = (group != null && group.has("rotation")) ?
            this.convertRotation(this.vector(group.getAsJsonArray("rotation"))) :
            (float[])ZERO.clone();
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

    private final void bakeMesh(JsonObject mesh, Node holder, float uvWidth, float uvHeight, Map<String, Boolean> eyeTextures) {
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
            float[] fArray2 = new float[]{origin[0] + relative[0], origin[1] + relative[1], -(origin[2] + relative[2])};
            map.put(key, fArray2);
        }
        BatchBuilder bodyBuilder = new BatchBuilder();
        BatchBuilder eyeBuilder = new BatchBuilder();
        JsonObject faces = mesh.getAsJsonObject("faces");
        block1: for (String faceKey : faces.keySet()) {
            JsonObject face = faces.getAsJsonObject(faceKey);
            JsonArray order = face.getAsJsonArray("vertices");
            if (order.size() < 3) continue;
            JsonObject uv = face.getAsJsonObject("uv");
            Intrinsics.checkNotNull((Object)face);
            boolean eye = Intrinsics.areEqual((Object)eyeTextures.get(this.readString(face, "texture")), (Object)true);
            BatchBuilder builder = eye ? eyeBuilder : bodyBuilder;
            for (int fan = 0; fan + 3 <= order.size() || fan == 0; ++fan) {
                int[] corners = order.size() <= 4 ?
                    new int[]{0, 1, 2, Math.min(3, order.size() - 1)} :
                    new int[]{0, fan + 1, fan + 2, fan + 2};
                if (order.size() > 4 && fan + 2 > order.size() - 1) continue block1;
                float[] facePositions = new float[12];
                float[] faceUvs = new float[8];
                boolean valid = true;
                for (int corner = 0; corner < 4; ++corner) {
                    JsonArray vertexUv;
                    String vertexKey = order.get(corners[corner]).getAsString();
                    float[] position = (float[])positions.get(vertexKey);
                    JsonObject jsonObject = uv;
                    Object object = vertexUv = jsonObject != null ? jsonObject.getAsJsonArray(vertexKey) : null;
                    if (position == null || vertexUv == null) {
                        valid = false;
                        break;
                    }
                    facePositions[corner * 3] = position[0];
                    facePositions[corner * 3 + 1] = position[1];
                    facePositions[corner * 3 + 2] = position[2];
                    faceUvs[corner * 2] = vertexUv.get(0).getAsFloat() / uvWidth;
                    faceUvs[corner * 2 + 1] = vertexUv.get(1).getAsFloat() / uvHeight;
                }
                if (!valid) continue block1;
                builder.getPositions().add(facePositions);
                builder.getUvs().add(faceUvs);
                if (order.size() <= 4) continue block1;
            }
        }
        if (!((Collection)bodyBuilder.getPositions()).isEmpty()) {
            holder.getBatches().add(bodyBuilder.build(false));
        }
        if (!((Collection)eyeBuilder.getPositions()).isEmpty()) {
            holder.getBatches().add(eyeBuilder.build(true));
        }
    }

    private final void parseSoundCues(JsonArray keyframes, List<SoundCue> sounds) {
        Iterator iterator = keyframes.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonArray dataPoints;
            JsonElement element = (JsonElement)iterator2.next();
            JsonObject keyframe = element.getAsJsonObject();
            Intrinsics.checkNotNull((Object)keyframe);
            if (!Intrinsics.areEqual((Object)"sound", (Object)this.readString(keyframe, "channel")) || (dataPoints = keyframe.getAsJsonArray("data_points")) == null || dataPoints.isEmpty()) continue;
            JsonObject jsonObject = dataPoints.get(0).getAsJsonObject();
            Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
            String effect = this.readString(jsonObject, "effect");
            if (!SOUND_CUES.containsKey(effect)) continue;
            sounds.add(new SoundCue(keyframe.get("time").getAsDouble(), effect));
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
        float[] fArray = new float[]{value[0], value[1], value[2]};
        return fArray;
    }

    private final float[] convertPosition(float[] value) {
        float[] fArray = new float[]{-value[0], value[1], -value[2]};
        return fArray;
    }

    private final float[] convertPivot(float[] value) {
        float[] fArray = new float[]{value[0], value[1], -value[2]};
        return fArray;
    }

    private final float readFloat(JsonObject obj, String key) {
        float f;
        if (!obj.has(key)) {
            return 0.0f;
        }
        try {
            String string = obj.get(key).getAsString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
            f = Float.parseFloat(string);
        }
        catch (NumberFormatException exception) {
            f = 0.0f;
        }
        return f;
    }

    private final String readString(JsonObject obj, String key) {
        String string;
        if (obj.has(key) && obj.get(key).isJsonPrimitive()) {
            String string2 = obj.get(key).getAsString();
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

    private static final Playback capture$lambda$0(long $now, CustomPetEntity it) {
        Intrinsics.checkNotNullParameter((Object)((Object)it), (String)"it");
        return new Playback($now);
    }

    private static final Playback capture$lambda$1(Function1 $tmp0, Object p0) {
        return (Playback)$tmp0.invoke(p0);
    }

    private static final RenderLayer unlitCutout$lambda$0(Identifier tex) {
        Intrinsics.checkNotNullParameter((Object)tex, (String)"tex");
        return RenderLayer.of((String)"kimiko_pet_unlit_cutout", (RenderSetup)RenderSetup.builder((RenderPipeline)UNLIT_PIPELINE).texture("Sampler0", tex).useLightmap().useOverlay().build());
    }

    private static final RenderLayer unlitCutout$lambda$1(Function1 $tmp0, Object p0) {
        return (RenderLayer)$tmp0.invoke(p0);
    }

    private static final void submitBatches$lambda$0(float $bodyRot, Pose $pose, boolean $eyePass, int $light, MatrixStack.Entry parent, VertexConsumer buffer) {
        Intrinsics.checkNotNullParameter((Object)parent, (String)"parent");
        Intrinsics.checkNotNullParameter((Object)buffer, (String)"buffer");
        MatrixStack local = new MatrixStack();
        local.peek().getPositionMatrix().set((Matrix4fc)parent.getPositionMatrix());
        local.peek().getNormalMatrix().set((Matrix3fc)parent.getNormalMatrix());
        local.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-$bodyRot));
        local.scale(0.03625f, 0.03625f, 0.03625f);
        for (Node root : $pose.getModel().getRoots()) {
            INSTANCE.renderNode(local, buffer, root, $eyePass, $pose, $light);
        }
    }

    private static final void model$lambda$0() {
        Model parsed;
        model = parsed = INSTANCE.load();
        if (parsed == null) {
            loadStarted.set(false);
        }
    }

    private static final double load$lambda$1(SoundCue it) {
        return it.getTime();
    }

    private static final double load$lambda$2(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).doubleValue();
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"models/pet/nightmare_bb.bbmodel");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        MODEL = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/pet/nightmare_bb_body.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        BODY_TEXTURE = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/pet/nightmare_bb_eye.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        EYE_TEXTURE = identifier4;
        ZERO = new float[]{0.0f, 0.0f, 0.0f};
        ONE = new float[]{1.0f, 1.0f, 1.0f};
        RenderPipeline.Snippet[] snippetArray = new RenderPipeline.Snippet[]{RenderPipelines.ENTITY_SNIPPET};
        RenderPipeline renderPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])snippetArray).withLocation(Identifier.of((String)Kimiko.Companion.namespace(), (String)"pipeline/pet_unlit_cutout")).withShaderDefine("ALPHA_CUTOUT", 0.1f).withShaderDefine("NO_CARDINAL_LIGHTING").withSampler("Sampler0").withCull(false).build());
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"register(...)");
        UNLIT_PIPELINE = renderPipeline;
        UNLIT_RENDER_TYPES = new ConcurrentHashMap();
        SOUND_CUES = MapsKt.mapOf(new Pair[]{
            TuplesKt.to((Object)"nightmare_bb_hi", (Object)"entity.nightmare_bb.hi"),
            TuplesKt.to((Object)"nightmare_bb_hello", (Object)"entity.nightmare_bb.hello"),
            TuplesKt.to((Object)"nightmare_bb_laugh", (Object)"entity.nightmare_bb.laugh")
        });
        RESOLVED_SOUNDS = new HashMap();
        playbackStates = new WeakHashMap();
        loadStarted = new AtomicBoolean();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\rR#\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e8\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0006\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Animation;", "", "", "length", "", "loop", "<init>", "(DZ)V", "D", "getLength", "()D", "Z", "getLoop", "()Z", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$BoneChannels;", "bones", "Ljava/util/Map;", "getBones", "()Ljava/util/Map;", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$SoundCue;", "sounds", "Ljava/util/List;", "getSounds", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
    public static final class Animation {
        private final double length;
        private final boolean loop;
        @NotNull
        private final Map<String, BoneChannels> bones;
        @NotNull
        private final List<SoundCue> sounds;

        public Animation(double length, boolean loop) {
            this.length = length;
            this.loop = loop;
            this.bones = new HashMap();
            this.sounds = new ArrayList();
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

        @NotNull
        public final List<SoundCue> getSounds() {
            return this.sounds;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u000e\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Batch;", "", "", "eyeTexture", "", "positions", "uvs", "", "faceCount", "<init>", "(Z[F[FI)V", "Z", "getEyeTexture", "()Z", "[F", "getPositions", "()[F", "getUvs", "I", "getFaceCount", "()I", "rtx.kimiko:kimiko"})
    public static final class Batch {
        private final boolean eyeTexture;
        @NotNull
        private final float[] positions;
        @NotNull
        private final float[] uvs;
        private final int faceCount;

        public Batch(boolean eyeTexture, @NotNull float[] positions, @NotNull float[] uvs, int faceCount) {
            Intrinsics.checkNotNullParameter((Object)positions, (String)"positions");
            Intrinsics.checkNotNullParameter((Object)uvs, (String)"uvs");
            this.eyeTexture = eyeTexture;
            this.positions = positions;
            this.uvs = uvs;
            this.faceCount = faceCount;
        }

        public final boolean getEyeTexture() {
            return this.eyeTexture;
        }

        @NotNull
        public final float[] getPositions() {
            return this.positions;
        }

        @NotNull
        public final float[] getUvs() {
            return this.uvs;
        }

        public final int getFaceCount() {
            return this.faceCount;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0014\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$BatchBuilder;", "", "<init>", "()V", "", "eyeTexture", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Batch;", "build", "(Z)Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Batch;", "", "", "positions", "Ljava/util/List;", "getPositions", "()Ljava/util/List;", "uvs", "getUvs", "rtx.kimiko:kimiko"})
    private static final class BatchBuilder {
        @NotNull
        private final List<float[]> positions = new ArrayList();
        @NotNull
        private final List<float[]> uvs = new ArrayList();

        @NotNull
        public final List<float[]> getPositions() {
            return this.positions;
        }

        @NotNull
        public final List<float[]> getUvs() {
            return this.uvs;
        }

        @NotNull
        public final Batch build(boolean eyeTexture) {
            int faces = this.positions.size();
            float[] flatPositions = new float[faces * 12];
            float[] flatUvs = new float[faces * 8];
            for (int i = 0; i < faces; ++i) {
                System.arraycopy(this.positions.get(i), 0, flatPositions, i * 12, 12);
                System.arraycopy(this.uvs.get(i), 0, flatUvs, i * 8, 8);
            }
            return new Batch(eyeTexture, flatPositions, flatUvs, faces);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$BoneChannels;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Channel;", "position", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Channel;", "getPosition", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Channel;", "rotation", "getRotation", "scale", "getScale", "rtx.kimiko:kimiko"})
    public static final class BoneChannels {
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Channel;", "", "<init>", "()V", "", "sort", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Key;", "keys", "Ljava/util/List;", "getKeys", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
    public static final class Channel {
        @NotNull
        private final List<Key> keys = new ArrayList();

        @NotNull
        public final List<Key> getKeys() {
            return this.keys;
        }

        public final void sort() {
            Comparator<Key> comparator = Comparator.comparingDouble(Key::getTime);
            Intrinsics.checkNotNullExpressionValue(comparator, (String)"comparingDouble(...)");
            CollectionsKt.sortWith(this.keys, comparator);
        }

        private static final double sort$lambda$0(Key it) {
            return it.getTime();
        }

        private static final double sort$lambda$1(Function1 $tmp0, Object p0) {
            return ((Number)$tmp0.invoke(p0)).doubleValue();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Key;", "", "", "time", "", "value", "", "smooth", "<init>", "(D[FZ)V", "D", "getTime", "()D", "[F", "getValue", "()[F", "Z", "getSmooth", "()Z", "rtx.kimiko:kimiko"})
    public static final class Key {
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
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Node;", "roots", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Animation;", "animations", "<init>", "(Ljava/util/List;Ljava/util/Map;)V", "Ljava/util/List;", "getRoots", "()Ljava/util/List;", "Ljava/util/Map;", "getAnimations", "()Ljava/util/Map;", "rtx.kimiko:kimiko"})
    public static final class Model {
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\u000f\u0010\u000eR\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Node;", "", "", "name", "", "pivot", "rotation", "<init>", "(Ljava/lang/String;[F[F)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "[F", "getPivot", "()[F", "getRotation", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Batch;", "batches", "Ljava/util/List;", "getBatches", "()Ljava/util/List;", "children", "getChildren", "rtx.kimiko:kimiko"})
    public static final class Node {
        @NotNull
        private final String name;
        @NotNull
        private final float[] pivot;
        @NotNull
        private final float[] rotation;
        @NotNull
        private final List<Batch> batches;
        @NotNull
        private final List<Node> children;

        public Node(@NotNull String name, @NotNull float[] pivot, @NotNull float[] rotation) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)pivot, (String)"pivot");
            Intrinsics.checkNotNullParameter((Object)rotation, (String)"rotation");
            this.name = name;
            this.pivot = pivot;
            this.rotation = rotation;
            this.batches = new ArrayList();
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
        public final List<Batch> getBatches() {
            return this.batches;
        }

        @NotNull
        public final List<Node> getChildren() {
            return this.children;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\b\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\u0005R\"\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\u0005R\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R#\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\"0!8\u0006\u00a2\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R0\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\"\u0018\u00010!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010$\u001a\u0004\b(\u0010&\"\u0004\b)\u0010*R\"\u0010+\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010\u0014\u001a\u0004\b,\u0010\u000b\"\u0004\b-\u0010\u0005R\"\u0010.\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010\u0014\u001a\u0004\b/\u0010\u000b\"\u0004\b0\u0010\u0005\u00a8\u00061"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Playback;", "", "", "now", "<init>", "(J)V", "min", "max", "randomWindow", "(JJ)J", "nextVoiceDelay", "()J", "", "animation", "Ljava/lang/String;", "getAnimation", "()Ljava/lang/String;", "setAnimation", "(Ljava/lang/String;)V", "animationStartedAt", "J", "getAnimationStartedAt", "setAnimationStartedAt", "transitionStartedAt", "getTransitionStartedAt", "setTransitionStartedAt", "", "previousTime", "D", "getPreviousTime", "()D", "setPreviousTime", "(D)V", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;", "rendered", "Ljava/util/Map;", "getRendered", "()Ljava/util/Map;", "blendFrom", "getBlendFrom", "setBlendFrom", "(Ljava/util/Map;)V", "nextVoiceAt", "getNextVoiceAt", "setNextVoiceAt", "randomSeed", "getRandomSeed", "setRandomSeed", "rtx.kimiko:kimiko"})
    private static final class Playback {
        @NotNull
        private String animation = "idle";
        private long animationStartedAt;
        private long transitionStartedAt;
        private double previousTime;
        @NotNull
        private final Map<String, Transform> rendered;
        @Nullable
        private Map<String, Transform> blendFrom;
        private long nextVoiceAt;
        private long randomSeed;

        public Playback(long now) {
            this.animationStartedAt = now;
            this.transitionStartedAt = now;
            this.rendered = new HashMap();
            this.randomSeed = now;
            this.nextVoiceAt = now + this.nextVoiceDelay();
        }

        @NotNull
        public final String getAnimation() {
            return this.animation;
        }

        public final void setAnimation(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.animation = string;
        }

        public final long getAnimationStartedAt() {
            return this.animationStartedAt;
        }

        public final void setAnimationStartedAt(long l) {
            this.animationStartedAt = l;
        }

        public final long getTransitionStartedAt() {
            return this.transitionStartedAt;
        }

        public final void setTransitionStartedAt(long l) {
            this.transitionStartedAt = l;
        }

        public final double getPreviousTime() {
            return this.previousTime;
        }

        public final void setPreviousTime(double d) {
            this.previousTime = d;
        }

        @NotNull
        public final Map<String, Transform> getRendered() {
            return this.rendered;
        }

        @Nullable
        public final Map<String, Transform> getBlendFrom() {
            return this.blendFrom;
        }

        public final void setBlendFrom(@Nullable Map<String, Transform> map) {
            this.blendFrom = map;
        }

        public final long getNextVoiceAt() {
            return this.nextVoiceAt;
        }

        public final void setNextVoiceAt(long l) {
            this.nextVoiceAt = l;
        }

        public final long getRandomSeed() {
            return this.randomSeed;
        }

        public final void setRandomSeed(long l) {
            this.randomSeed = l;
        }

        public final long randomWindow(long min, long max) {
            this.randomSeed = this.randomSeed * 6364136223846793005L + 1442695040888963407L;
            long span = Math.max(1L, max - min);
            return min + Math.floorMod(this.randomSeed >>> 17, span);
        }

        public final long nextVoiceDelay() {
            return this.randomWindow(26000L, 62000L) * 1000000L;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Pose;", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;", "model", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;", "transforms", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;Ljava/util/Map;)V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;", "getModel", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Model;", "Ljava/util/Map;", "getTransforms", "()Ljava/util/Map;", "rtx.kimiko:kimiko"})
    public static final class Pose {
        @NotNull
        private final Model model;
        @NotNull
        private final Map<String, Transform> transforms;

        public Pose(@NotNull Model model, @NotNull Map<String, Transform> transforms) {
            Intrinsics.checkNotNullParameter((Object)model, (String)"model");
            Intrinsics.checkNotNullParameter(transforms, (String)"transforms");
            this.model = model;
            this.transforms = transforms;
        }

        @NotNull
        public final Model getModel() {
            return this.model;
        }

        @NotNull
        public final Map<String, Transform> getTransforms() {
            return this.transforms;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$SoundCue;", "", "", "time", "", "key", "<init>", "(DLjava/lang/String;)V", "D", "getTime", "()D", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class SoundCue {
        private final double time;
        @NotNull
        private final String key;

        public SoundCue(double time, @NotNull String key) {
            Intrinsics.checkNotNullParameter((Object)key, (String)"key");
            this.time = time;
            this.key = key;
        }

        public final double getTime() {
            return this.time;
        }

        @NotNull
        public final String getKey() {
            return this.key;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\b\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\b\u001a\u0004\b\f\u0010\n\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Transform;", "", "", "position", "rotation", "scale", "<init>", "([F[F[F)V", "[F", "getPosition", "()[F", "getRotation", "getScale", "rtx.kimiko:kimiko"})
    public static final class Transform {
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
    }
}

