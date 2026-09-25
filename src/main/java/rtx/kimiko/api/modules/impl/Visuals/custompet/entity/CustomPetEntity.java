/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityType
 *  net.minecraft.entity.MovementType
 *  net.minecraft.world.BlockView
 *  net.minecraft.world.World
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos.Mutable
 *  net.minecraft.util.math.Direction.Axis
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.shape.VoxelShape
 *  net.minecraft.block.BlockState
 *  net.minecraft.util.Identifier
 *  net.minecraft.sound.SoundEvent
 *  net.minecraft.sound.SoundEvents
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.block.piston.PistonBehavior
 *  net.minecraft.world.RaycastContext
 *  net.minecraft.world.RaycastContext.FluidHandling
 *  net.minecraft.world.RaycastContext.ShapeType
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.registry.entry.RegistryEntry.Reference
 *  net.minecraft.entity.passive.FrogEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  software.bernie.geckolib.animatable.GeoAnimatable
 *  software.bernie.geckolib.animatable.GeoEntity
 *  software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
 *  software.bernie.geckolib.animatable.manager.AnimatableManager$ControllerRegistrar
 *  software.bernie.geckolib.animation.AnimationController
 *  software.bernie.geckolib.animation.RawAnimation
 *  software.bernie.geckolib.animation.object.PlayState
 *  software.bernie.geckolib.animation.state.AnimationTest
 *  software.bernie.geckolib.animation.state.KeyFrameEvent
 *  software.bernie.geckolib.cache.animation.keyframeevent.SoundKeyframeData
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.entity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.passive.FrogEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetVariant;
import rtx.kimiko.api.modules.impl.Visuals.custompet.sync.CustomPetRemoteState;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.animation.state.AnimationTest;
import software.bernie.geckolib.animation.state.KeyFrameEvent;
import software.bernie.geckolib.cache.animation.keyframeevent.SoundKeyframeData;
import software.bernie.geckolib.util.GeckoLibUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\bp\b\u0016\u0018\u0000 \u0091\u00022\u00020\u00012\u00020\u0002:\b\u0091\u0002\u0092\u0002\u0093\u0002\u0094\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006J=\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\t\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001a\u0010\u0019J\r\u0010\u001b\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001b\u0010\u0019J\r\u0010\u001c\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001c\u0010\u0019J\r\u0010\u001d\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014\u00a2\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014\u00a2\u0006\u0004\b\"\u0010!J\u0015\u0010#\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u0014\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b'\u0010\u001eJ\u000f\u0010(\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b(\u0010\u001eJ\r\u0010)\u001a\u00020\u000b\u00a2\u0006\u0004\b)\u0010\u0019J\u0017\u0010,\u001a\u00020\u00102\b\u0010+\u001a\u0004\u0018\u00010*\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010/\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020*H\u0002\u00a2\u0006\u0004\b/\u00100J\r\u00101\u001a\u00020\u000b\u00a2\u0006\u0004\b1\u0010\u0019J\u0017\u00103\u001a\u00020\u00102\b\u0010+\u001a\u0004\u0018\u000102\u00a2\u0006\u0004\b3\u00104J\u0017\u00105\u001a\u00020\u000b2\u0006\u0010.\u001a\u000202H\u0002\u00a2\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u0004\u0018\u000102\u00a2\u0006\u0004\b7\u00108J\u001d\u0010:\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u0014\u00a2\u0006\u0004\b:\u0010;J-\u0010@\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020\t2\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\t\u00a2\u0006\u0004\b@\u0010AJ\u0015\u0010C\u001a\u00020\u00102\u0006\u0010B\u001a\u00020\u000b\u00a2\u0006\u0004\bC\u0010DJ\u0017\u0010G\u001a\u00020\u00102\b\u0010F\u001a\u0004\u0018\u00010E\u00a2\u0006\u0004\bG\u0010HJ\u0017\u0010I\u001a\u00020\u00102\b\u0010F\u001a\u0004\u0018\u00010E\u00a2\u0006\u0004\bI\u0010HJ\r\u0010J\u001a\u00020\u0010\u00a2\u0006\u0004\bJ\u0010\u001eJ\u000f\u0010K\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\bK\u0010\u001eJ\u000f\u0010M\u001a\u00020LH\u0016\u00a2\u0006\u0004\bM\u0010NJ\u000f\u0010O\u001a\u00020LH\u0016\u00a2\u0006\u0004\bO\u0010NJ\u000f\u0010P\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\bP\u0010&J\u0017\u0010R\u001a\u00020\u000b2\u0006\u0010Q\u001a\u00020\tH\u0016\u00a2\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\bT\u0010\u0019J\u000f\u0010U\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\bU\u0010\u0019J\u000f\u0010V\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\bV\u0010\u0019J\u0019\u0010Y\u001a\u00020\u000b2\b\u0010X\u001a\u0004\u0018\u00010WH\u0016\u00a2\u0006\u0004\bY\u0010ZJ\u0017\u0010[\u001a\u00020\u000b2\u0006\u0010X\u001a\u00020WH\u0016\u00a2\u0006\u0004\b[\u0010ZJ\u0017\u0010\\\u001a\u00020\u00102\u0006\u0010X\u001a\u00020WH\u0016\u00a2\u0006\u0004\b\\\u0010]J'\u0010\\\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\t2\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\\\u0010^J\u0017\u0010\\\u001a\u00020\u00102\u0006\u0010_\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\\\u0010`J\u000f\u0010b\u001a\u00020aH\u0016\u00a2\u0006\u0004\bb\u0010cJ\u000f\u0010d\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bd\u0010\u001eJ\u000f\u0010e\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\be\u0010\u001eJ\u000f\u0010f\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bf\u0010\u001eJ\u000f\u0010g\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\bg\u0010\u0019J\u000f\u0010h\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\bh\u0010\u0019J\u000f\u0010i\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bi\u0010\u001eJ\u000f\u0010j\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bj\u0010\u001eJ\u000f\u0010k\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bk\u0010\u001eJ\u000f\u0010l\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bl\u0010\u001eJ\u000f\u0010m\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bm\u0010\u001eJ\u000f\u0010n\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\bn\u0010\u0019J\u0019\u0010q\u001a\u00020\u00102\b\u0010p\u001a\u0004\u0018\u00010oH\u0002\u00a2\u0006\u0004\bq\u0010rJ\u001f\u0010u\u001a\u00020\u00102\u0006\u0010s\u001a\u00020\u000b2\u0006\u0010t\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bu\u0010vJ\u0017\u0010x\u001a\u00020\t2\u0006\u0010w\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bx\u0010yJ\u0017\u0010|\u001a\u00020\u00102\u0006\u0010{\u001a\u00020zH\u0016\u00a2\u0006\u0004\b|\u0010}J!\u0010\u0081\u0001\u001a\u00030\u0080\u00012\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u00000~H\u0002\u00a2\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J!\u0010\u0083\u0001\u001a\u00030\u0080\u00012\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u00000~H\u0002\u00a2\u0006\u0006\b\u0083\u0001\u0010\u0082\u0001J\u0011\u0010\u0084\u0001\u001a\u00020\u000bH\u0002\u00a2\u0006\u0005\b\u0084\u0001\u0010\u0019J\u0011\u0010\u0085\u0001\u001a\u00020\u000bH\u0002\u00a2\u0006\u0005\b\u0085\u0001\u0010\u0019J\u0019\u0010\u0086\u0001\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0016\u00a2\u0006\u0005\b\u0086\u0001\u0010!J\u0013\u0010\u0088\u0001\u001a\u00030\u0087\u0001H\u0016\u00a2\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0018\u0010\u008a\u0001\u001a\u00030\u0087\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0017\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\b\u0010\u008c\u0001R\u0017\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\n\u0010\u008d\u0001R\u0017\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\f\u0010\u008e\u0001R\u0017\u0010\r\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\r\u0010\u008e\u0001R\u0017\u0010\u000e\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u000e\u0010\u008e\u0001R*\u0010\u0090\u0001\u001a\u00030\u008f\u00018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0018\n\u0006\b\u0090\u0001\u0010\u0091\u0001\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001\"\u0006\b\u0094\u0001\u0010\u0095\u0001R'\u0010\u0096\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u0096\u0001\u0010\u008e\u0001\u001a\u0005\b\u0096\u0001\u0010\u0019\"\u0005\b\u0097\u0001\u0010DR'\u0010\u0098\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u0098\u0001\u0010\u008e\u0001\u001a\u0005\b\u0098\u0001\u0010\u0019\"\u0005\b\u0099\u0001\u0010DR&\u0010\u009a\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0006\b\u009a\u0001\u0010\u008e\u0001\u001a\u0005\b\u009a\u0001\u0010\u0019\"\u0004\b@\u0010DR(\u0010\u009b\u0001\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u009b\u0001\u0010\u008c\u0001\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0005\b\u009e\u0001\u0010`R'\u0010\u009f\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u009f\u0001\u0010\u008e\u0001\u001a\u0005\b\u009f\u0001\u0010\u0019\"\u0005\b\u00a0\u0001\u0010DR'\u0010\u00a1\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00a1\u0001\u0010\u008e\u0001\u001a\u0005\b\u00a1\u0001\u0010\u0019\"\u0005\b\u00a2\u0001\u0010DR'\u0010\u00a3\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00a3\u0001\u0010\u008e\u0001\u001a\u0005\b\u00a3\u0001\u0010\u0019\"\u0005\b\u00a4\u0001\u0010DR*\u0010\u00a6\u0001\u001a\u00030\u00a5\u00018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0018\n\u0006\b\u00a6\u0001\u0010\u00a7\u0001\u001a\u0006\b\u00a8\u0001\u0010\u00a9\u0001\"\u0006\b\u00aa\u0001\u0010«\u0001R0\u0010\u00ad\u0001\u001a\u00020\u000b2\u0007\u0010\u00ac\u0001\u001a\u00020\u000b8\u0006@FX\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00ad\u0001\u0010\u008e\u0001\u001a\u0005\b\u00ad\u0001\u0010\u0019\"\u0005\b\u00ae\u0001\u0010DR0\u0010\u00b0\u0001\u001a\u00020\u000b2\u0007\u0010\u00af\u0001\u001a\u00020\u000b8\u0006@FX\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00b0\u0001\u0010\u008e\u0001\u001a\u0005\b\u00b1\u0001\u0010\u0019\"\u0005\b\u00b2\u0001\u0010DR(\u0010\u00b3\u0001\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00b3\u0001\u0010\u00b4\u0001\u001a\u0005\b\u00b5\u0001\u0010N\"\u0006\b\u00b6\u0001\u0010\u00b7\u0001R(\u0010\u00b8\u0001\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00b8\u0001\u0010\u00b4\u0001\u001a\u0005\b\u00b9\u0001\u0010N\"\u0006\b\u00ba\u0001\u0010\u00b7\u0001R'\u0010»\u0001\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b»\u0001\u0010\u00bc\u0001\u001a\u0004\b \u0010&\"\u0006\b\u00bd\u0001\u0010\u00be\u0001R(\u0010\u00bf\u0001\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00bf\u0001\u0010\u00bc\u0001\u001a\u0005\b\u00c0\u0001\u0010&\"\u0006\b\u00c1\u0001\u0010\u00be\u0001R'\u0010\u00c2\u0001\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00c2\u0001\u0010\u00bc\u0001\u001a\u0004\b\"\u0010&\"\u0006\b\u00c3\u0001\u0010\u00be\u0001R(\u0010\u00c4\u0001\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00c4\u0001\u0010\u00bc\u0001\u001a\u0005\b\u00c5\u0001\u0010&\"\u0006\b\u00c6\u0001\u0010\u00be\u0001R(\u0010\u00c7\u0001\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00c7\u0001\u0010\u008d\u0001\u001a\u0005\b#\u0010\u00c8\u0001\"\u0006\b\u00c9\u0001\u0010\u00ca\u0001R)\u0010\u00cb\u0001\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0018\n\u0006\b\u00cb\u0001\u0010\u008d\u0001\u001a\u0006\b\u00cc\u0001\u0010\u00c8\u0001\"\u0006\b\u00cd\u0001\u0010\u00ca\u0001R*\u0010\u00ce\u0001\u001a\u0004\u0018\u00010*8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00ce\u0001\u0010\u00cf\u0001\u001a\u0006\b\u00d0\u0001\u0010\u00d1\u0001\"\u0005\b\u00d2\u0001\u0010-R(\u0010\u00d3\u0001\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00d3\u0001\u0010\u00b4\u0001\u001a\u0005\b\u00d4\u0001\u0010N\"\u0006\b\u00d5\u0001\u0010\u00b7\u0001R'\u0010\u00d6\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00d6\u0001\u0010\u008e\u0001\u001a\u0005\b\u00d7\u0001\u0010\u0019\"\u0005\b\u00d8\u0001\u0010DR'\u0010\u00d9\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00d9\u0001\u0010\u008e\u0001\u001a\u0005\b\u00da\u0001\u0010\u0019\"\u0005\b\u00db\u0001\u0010DR(\u0010\u00dc\u0001\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00dc\u0001\u0010\u00b4\u0001\u001a\u0005\b\u00dd\u0001\u0010N\"\u0006\b\u00de\u0001\u0010\u00b7\u0001R(\u0010\u00df\u0001\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00df\u0001\u0010\u00b4\u0001\u001a\u0005\b\u00e0\u0001\u0010N\"\u0006\b\u00e1\u0001\u0010\u00b7\u0001R)\u0010\u00e2\u0001\u001a\u0004\u0018\u0001028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00e2\u0001\u0010\u00e3\u0001\u001a\u0005\b\u00e4\u0001\u00108\"\u0005\b\u00e5\u0001\u00104R(\u0010\u00e6\u0001\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00e6\u0001\u0010\u00b4\u0001\u001a\u0005\b\u00e7\u0001\u0010N\"\u0006\b\u00e8\u0001\u0010\u00b7\u0001R'\u0010\u00e9\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00e9\u0001\u0010\u008e\u0001\u001a\u0005\b\u00ea\u0001\u0010\u0019\"\u0005\b\u00eb\u0001\u0010DR(\u0010\u00ec\u0001\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0006\b\u00ec\u0001\u0010\u00b4\u0001\u001a\u0005\b\u00ed\u0001\u0010N\"\u0006\b\u00ee\u0001\u0010\u00b7\u0001R'\u0010\u00ef\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00ef\u0001\u0010\u008e\u0001\u001a\u0005\b\u00ef\u0001\u0010\u0019\"\u0005\b\u00f0\u0001\u0010DR'\u0010\u00f1\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00f1\u0001\u0010\u008e\u0001\u001a\u0005\b\u00f1\u0001\u0010\u0019\"\u0005\b\u00f2\u0001\u0010DR'\u0010\u00f3\u0001\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0006\b\u00f3\u0001\u0010\u008e\u0001\u001a\u0005\b\u00f3\u0001\u0010\u0019\"\u0005\b\u00f4\u0001\u0010DR\u0019\u0010\u00f5\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f5\u0001\u0010\u00b4\u0001R\u0019\u0010\u00f6\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f6\u0001\u0010\u00b4\u0001R\u0019\u0010\u00f7\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f7\u0001\u0010\u00b4\u0001R\u0019\u0010\u00f8\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f8\u0001\u0010\u00b4\u0001R\u0019\u0010\u00f9\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f9\u0001\u0010\u00b4\u0001R\u0019\u0010\u00fa\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fa\u0001\u0010\u00b4\u0001R\u0019\u0010\u00fb\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fb\u0001\u0010\u00b4\u0001R\u0019\u0010\u00fc\u0001\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fc\u0001\u0010\u00b4\u0001R\u0019\u0010\u00fd\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fd\u0001\u0010\u00bc\u0001R\u0019\u0010\u00fe\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fe\u0001\u0010\u00bc\u0001R\u0019\u0010\u00ff\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ff\u0001\u0010\u00bc\u0001R\u0019\u0010\u0080\u0002\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0080\u0002\u0010\u00bc\u0001R\u0017\u0010\u000f\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u000f\u0010\u008d\u0001R)\u0010\u0081\u0002\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0018\n\u0006\b\u0081\u0002\u0010\u008d\u0001\u001a\u0006\b\u0082\u0002\u0010\u00c8\u0001\"\u0006\b\u0083\u0002\u0010\u00ca\u0001R\u0019\u0010\u0084\u0002\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0002\u0010\u008d\u0001R\u0019\u0010\u0085\u0002\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0002\u0010\u008c\u0001R\u0019\u0010\u0086\u0002\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0002\u0010\u008d\u0001R\u0019\u0010\u0087\u0002\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0002\u0010\u008d\u0001R\u0019\u0010\u0088\u0002\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0002\u0010\u008c\u0001R\u0019\u0010\u0089\u0002\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0089\u0002\u0010\u008e\u0001R\u0019\u0010\u008a\u0002\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008a\u0002\u0010\u008e\u0001R\u0019\u0010\u008b\u0002\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0002\u0010\u008c\u0001R\u0019\u0010\u008c\u0002\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008c\u0002\u0010\u00bc\u0001R\u0019\u0010\u008d\u0002\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0002\u0010\u008e\u0001R\u0019\u0010\u008e\u0002\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0002\u0010\u008e\u0001R\u0019\u0010\u008f\u0002\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0002\u0010\u008e\u0001R\u0019\u0010\u0090\u0002\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0002\u0010\u008d\u0001\u00a8\u0006\u0095\u0002"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "Lnet/minecraft/FrogEntity;", "Lsoftware/bernie/geckolib/animatable/GeoEntity;", "Lnet/minecraft/World;", "level", "<init>", "(Lnet/minecraft/World;)V", "Lnet/minecraft/Vec3d;", "desiredPosition", "", "desiredSpeed", "", "desiredMoving", "desiredUmbrella", "desiredAirborne", "animationSpeed", "", "setBehavior", "(Lnet/minecraft/Vec3d;DZZZD)V", "pos", "", "yaw", "snapTo", "(Lnet/minecraft/Vec3d;F)V", "shouldUseUmbrella", "()Z", "isMovementBlocked", "isUfoTakingOff", "hasUfoTrick", "triggerUfoSpin", "()V", "partialTick", "getUfoBeamLevel", "(F)F", "getUfoLandBlend", "getUfoGroundDrop", "(F)D", "ufoBeamTarget", "()F", "tickUfoState", "tickUfoGround", "hasGoatAction", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$GoatAction;", "action", "triggerGoatAction", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$GoatAction;)V", "next", "canInterruptGoatAction", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$GoatAction;)Z", "hasChekushkaAction", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$ChekushkaAction;", "triggerChekushkaAction", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$ChekushkaAction;)V", "canInterruptChekushkaAction", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$ChekushkaAction;)Z", "currentChekushkaAction", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$ChekushkaAction;", "weight", "overrideLookYaw", "(FF)V", "active", "x", "y", "z", "setChekushkaCombat", "(ZDDD)V", "sleeping", "setGoatPose", "(Z)V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetRemoteState;", "state", "applyNetworkState", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetRemoteState;)V", "applyRemoteState", "releaseRemoteControl", "tick", "", "getMaxHeadXRot", "()I", "getMaxHeadYRot", "maxUpStep", "distance", "shouldRenderAtSqrDistance", "(D)Z", "isPickable", "isAttackable", "isPushable", "Lnet/minecraft/Entity;", "entity", "canBeCollidedWith", "(Lnet/minecraft/Entity;)Z", "canCollideWith", "push", "(Lnet/minecraft/Entity;)V", "(DDD)V", "movement", "(Lnet/minecraft/Vec3d;)V", "Lnet/minecraft/PistonBehavior;", "getPistonPushReaction", "()Lnet/minecraft/PistonBehavior;", "tickChekushkaAction", "tickGoatAction", "tickGoatState", "goatInJumpState", "chekushkaInJumpState", "tickCombatStance", "tickNetworkState", "tickCustomMovement", "tickAirMovement", "snapToGroundIfClose", "emitsAudioCues", "", "cue", "handleGoatSoundCue", "(Ljava/lang/String;)V", "movingNow", "horizontalDeltaSqr", "tickClientAudio", "(ZD)V", "value", "square", "(D)D", "Lsoftware/bernie/geckolib/animatable/manager/AnimatableManager$ControllerRegistrar;", "controllers", "registerControllers", "(Lsoftware/bernie/geckolib/animatable/manager/AnimatableManager$ControllerRegistrar;)V", "Lsoftware/bernie/geckolib/animation/state/AnimationTest;", "event", "Lsoftware/bernie/geckolib/animation/object/PlayState;", "predicate", "(Lsoftware/bernie/geckolib/animation/state/AnimationTest;)Lsoftware/bernie/geckolib/animation/object/PlayState;", "lifePredicate", "goatLifeOverlayActive", "chekushkaLifeOverlayActive", "getYRot", "Lsoftware/bernie/geckolib/animatable/instance/AnimatableInstanceCache;", "getAnimatableInstanceCache", "()Lsoftware/bernie/geckolib/animatable/instance/AnimatableInstanceCache;", "cache", "Lsoftware/bernie/geckolib/animatable/instance/AnimatableInstanceCache;", "Lnet/minecraft/Vec3d;", "D", "Z", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "petVariant", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "getPetVariant", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "setPetVariant", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;)V", "isOwl", "setOwl", "isChekushka", "setChekushka", "isChekushkaCombat", "chekushkaCombatTarget", "getChekushkaCombatTarget", "()Lnet/minecraft/Vec3d;", "setChekushkaCombatTarget", "isGoat", "setGoat", "isNightmareBb", "setNightmareBb", "isUfo", "setUfo", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$UfoBeamMode;", "ufoBeamMode", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$UfoBeamMode;", "getUfoBeamMode", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$UfoBeamMode;", "setUfoBeamMode", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$UfoBeamMode;)V", "grounded", "isUfoGrounded", "setUfoGrounded", "boost", "ufoBoost", "getUfoBoost", "setUfoBoost", "ufoSpinTicks", "I", "getUfoSpinTicks", "setUfoSpinTicks", "(I)V", "ufoTakeoffTicks", "getUfoTakeoffTicks", "setUfoTakeoffTicks", "ufoBeamLevel", "F", "setUfoBeamLevel", "(F)V", "ufoBeamLevelPrev", "getUfoBeamLevelPrev", "setUfoBeamLevelPrev", "ufoLandBlend", "setUfoLandBlend", "ufoLandBlendPrev", "getUfoLandBlendPrev", "setUfoLandBlendPrev", "ufoGroundDrop", "()D", "setUfoGroundDrop", "(D)V", "ufoGroundDropPrev", "getUfoGroundDropPrev", "setUfoGroundDropPrev", "goatAction", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$GoatAction;", "getGoatAction", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$GoatAction;", "setGoatAction", "goatActionTicksLeft", "getGoatActionTicksLeft", "setGoatActionTicksLeft", "goatSitting", "getGoatSitting", "setGoatSitting", "goatSleeping", "getGoatSleeping", "setGoatSleeping", "goatIdleTicks", "getGoatIdleTicks", "setGoatIdleTicks", "goatRunTicks", "getGoatRunTicks", "setGoatRunTicks", "chekushkaAction", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$ChekushkaAction;", "getChekushkaAction", "setChekushkaAction", "chekushkaActionTicksLeft", "getChekushkaActionTicksLeft", "setChekushkaActionTicksLeft", "chekushkaBored", "getChekushkaBored", "setChekushkaBored", "robotType", "getRobotType", "setRobotType", "isPetMoving", "setPetMoving", "isUsingUmbrella", "setUsingUmbrella", "isAirborneMode", "setAirborneMode", "movingTicks", "ambientSoundCooldown", "stepSoundCooldown", "jumpCooldown", "blockedTicks", "pauseTicks", "sideStepDir", "sideStepTicks", "targetYaw", "smoothedYaw", "lookOverrideYaw", "lookOverrideWeight", "currentAnimationSpeed", "getCurrentAnimationSpeed", "setCurrentAnimationSpeed", "currentGroundSpeed", "lastMoveDirection", "verticalVelocity", "lastHorizontalDistance", "airMotion", "networkControlled", "hasNetworkAnchor", "networkPosition", "networkYaw", "networkMoving", "networkUmbrella", "networkAirborne", "networkAnimationSpeed", "Companion", "ChekushkaAction", "GoatAction", "UfoBeamMode", "rtx.kimiko:kimiko"})
public class CustomPetEntity
extends FrogEntity
implements GeoEntity {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final AnimatableInstanceCache cache;
    @NotNull
    private Vec3d desiredPosition;
    private double desiredSpeed;
    private boolean desiredMoving;
    private boolean desiredUmbrella;
    private boolean desiredAirborne;
    @NotNull
    private CustomPetVariant petVariant;
    private boolean isOwl;
    private boolean isChekushka;
    private boolean isChekushkaCombat;
    @NotNull
    private Vec3d chekushkaCombatTarget;
    private boolean isGoat;
    private boolean isNightmareBb;
    private boolean isUfo;
    @NotNull
    private UfoBeamMode ufoBeamMode;
    private boolean isUfoGrounded;
    private boolean ufoBoost;
    private int ufoSpinTicks;
    private int ufoTakeoffTicks;
    private float ufoBeamLevel;
    private float ufoBeamLevelPrev;
    private float ufoLandBlend;
    private float ufoLandBlendPrev;
    private double ufoGroundDrop;
    private double ufoGroundDropPrev;
    @Nullable
    private GoatAction goatAction;
    private int goatActionTicksLeft;
    private boolean goatSitting;
    private boolean goatSleeping;
    private int goatIdleTicks;
    private int goatRunTicks;
    @Nullable
    private ChekushkaAction chekushkaAction;
    private int chekushkaActionTicksLeft;
    private boolean chekushkaBored;
    private int robotType;
    private boolean isPetMoving;
    private boolean isUsingUmbrella;
    private boolean isAirborneMode;
    private int movingTicks;
    private int ambientSoundCooldown;
    private int stepSoundCooldown;
    private int jumpCooldown;
    private int blockedTicks;
    private int pauseTicks;
    private int sideStepDir;
    private int sideStepTicks;
    private float targetYaw;
    private float smoothedYaw;
    private float lookOverrideYaw;
    private float lookOverrideWeight;
    private double animationSpeed;
    private double currentAnimationSpeed;
    private double currentGroundSpeed;
    @NotNull
    private Vec3d lastMoveDirection;
    private double verticalVelocity;
    private double lastHorizontalDistance;
    @NotNull
    private Vec3d airMotion;
    private boolean networkControlled;
    private boolean hasNetworkAnchor;
    @NotNull
    private Vec3d networkPosition;
    private float networkYaw;
    private boolean networkMoving;
    private boolean networkUmbrella;
    private boolean networkAirborne;
    private double networkAnimationSpeed;
    @NotNull
    private static final AtomicInteger NEXT_ENTITY_ID = new AtomicInteger(-20000);
    private static final float GROUND_FULL_SPEED_ANGLE = 6.0f;
    private static final float GROUND_MIN_SPEED_ANGLE = 84.0f;
    private static final float AIR_START_MOVE_ANGLE = 9.0f;
    private static final double MOVEMENT_ANIMATION_THRESHOLD_SQR = 2.5E-5;
    private static final double MOVEMENT_VERTICAL_THRESHOLD = 0.003;
    @Nullable
    private static SoundEvent ambientSound;
    @Nullable
    private static SoundEvent stepSound;
    private static boolean soundsResolved;
    @NotNull
    private static final RawAnimation IDLE;
    @NotNull
    private static final RawAnimation WALK;
    @NotNull
    private static final RawAnimation RAIN_IDLE;
    @NotNull
    private static final RawAnimation RAIN_WALK;
    @NotNull
    private static final RawAnimation HAT_IDLE;
    @NotNull
    private static final RawAnimation HAT_WALK;
    @NotNull
    private static final RawAnimation FISHERMAN_IDLE;
    @NotNull
    private static final RawAnimation FISHERMAN_WALK;
    @NotNull
    private static final RawAnimation AIR_IDLE;
    @NotNull
    private static final RawAnimation AIR_WALK;
    @NotNull
    private static final RawAnimation ROBOT_IDLE;
    @NotNull
    private static final RawAnimation ROBOT_FLY;
    @NotNull
    private static final RawAnimation OWL_IDLE;
    @NotNull
    private static final RawAnimation OWL_WALK;
    @NotNull
    private static final RawAnimation OWL_RUN;
    @NotNull
    private static final RawAnimation OWL_FLY;
    private static final double OWL_RUN_ANIMATION_SPEED = 1.4;
    private static final double CHEKUSHKA_RUN_ANIMATION_SPEED = 1.35;
    @NotNull
    private static final RawAnimation CHEKUSHKA_JUMP;
    @NotNull
    private static final RawAnimation CHEKUSHKA_IDLE;
    @NotNull
    private static final RawAnimation CHEKUSHKA_WALK;
    @NotNull
    private static final RawAnimation CHEKUSHKA_RUN;
    @NotNull
    private static final RawAnimation CHEKUSHKA_SWIM;
    @NotNull
    private static final RawAnimation CHEKUSHKA_FLY;
    @NotNull
    private static final RawAnimation CHEKUSHKA_BORED;
    @NotNull
    private static final RawAnimation CHEKUSHKA_LIFE;
    @NotNull
    private static final RawAnimation CHEKUSHKA_SHOOT_LOOP;
    private static final double GOAT_RUN_ANIMATION_SPEED = 1.35;
    private static final int GOAT_SIT_IDLE_TICKS = 160;
    private static final int GOAT_SIT_DURATION_TICKS = 200;
    private static final int GOAT_RUN_STOP_MIN_TICKS = 8;
    @NotNull
    private static final RawAnimation UFO_IDLE;
    @NotNull
    private static final RawAnimation UFO_FLY;
    @NotNull
    private static final RawAnimation UFO_BEAM;
    @NotNull
    private static final RawAnimation UFO_ABDUCT;
    @NotNull
    private static final RawAnimation UFO_SCAN;
    @NotNull
    private static final RawAnimation UFO_BOOST;
    @NotNull
    private static final RawAnimation UFO_SPIN;
    @NotNull
    private static final RawAnimation UFO_LAND;
    @NotNull
    private static final RawAnimation UFO_TAKEOFF;
    private static final int UFO_SPIN_TICKS = 27;
    private static final int UFO_TAKEOFF_TICKS = 31;
    private static final float UFO_BEAM_EASE = 0.11f;
    private static final float UFO_LAND_EASE = 0.085f;
    private static final double UFO_GROUND_EASE = 0.24;
    private static final double UFO_GROUND_MAX = 14.0;
    @NotNull
    private static final RawAnimation GOAT_IDLE;
    @NotNull
    private static final RawAnimation GOAT_WALK;
    @NotNull
    private static final RawAnimation GOAT_RUN;
    @NotNull
    private static final RawAnimation GOAT_SWIM;
    @NotNull
    private static final RawAnimation GOAT_FLY;
    @NotNull
    private static final RawAnimation GOAT_SIT;
    @NotNull
    private static final RawAnimation GOAT_SLEEP;
    @NotNull
    private static final RawAnimation GOAT_GRAZE;
    @NotNull
    private static final RawAnimation GOAT_LIFE;

    public CustomPetEntity(@NotNull World level) {
        super(EntityType.FROG, level);
        AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache((GeoAnimatable)((GeoAnimatable)this));
        Intrinsics.checkNotNullExpressionValue((Object)animatableInstanceCache, (String)"createInstanceCache(...)");
        this.cache = animatableInstanceCache;
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.desiredPosition = vec3d2;
        this.petVariant = CustomPetVariant.NITWIT;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        this.chekushkaCombatTarget = vec3d3;
        this.ufoBeamMode = UfoBeamMode.OFF;
        this.ufoGroundDrop = 0.6;
        this.ufoGroundDropPrev = 0.6;
        this.ambientSoundCooldown = 70;
        this.animationSpeed = 1.0;
        this.currentAnimationSpeed = 1.0;
        Vec3d vec3d4 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"ZERO");
        this.lastMoveDirection = vec3d4;
        this.lastHorizontalDistance = Double.MAX_VALUE;
        Vec3d vec3d5 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"ZERO");
        this.airMotion = vec3d5;
        Vec3d vec3d6 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"ZERO");
        this.networkPosition = vec3d6;
        this.networkAnimationSpeed = 1.0;
        this.noClip = false;
        this.setNoGravity(false);
        this.setAiDisabled(true);
        this.setId(NEXT_ENTITY_ID.getAndDecrement());
        this.uuid = UUID.randomUUID();
        this.setVelocity(Vec3d.ZERO);
        this.targetYaw = this.getYaw();
        this.smoothedYaw = this.getYaw();
        this.ambientSoundCooldown = 70;
        this.verticalVelocity = 0.0;
    }

    @NotNull
    public final CustomPetVariant getPetVariant() {
        return this.petVariant;
    }

    public final void setPetVariant(@NotNull CustomPetVariant customPetVariant) {
        Intrinsics.checkNotNullParameter((Object)((Object)customPetVariant), (String)"<set-?>");
        this.petVariant = customPetVariant;
    }

    public final boolean isOwl() {
        return this.isOwl;
    }

    public final void setOwl(boolean bl) {
        this.isOwl = bl;
    }

    public final boolean isChekushka() {
        return this.isChekushka;
    }

    public final void setChekushka(boolean bl) {
        this.isChekushka = bl;
    }

    public final boolean isChekushkaCombat() {
        return this.isChekushkaCombat;
    }

    public final void setChekushkaCombat(boolean bl) {
        this.isChekushkaCombat = bl;
    }

    @NotNull
    public final Vec3d getChekushkaCombatTarget() {
        return this.chekushkaCombatTarget;
    }

    public final void setChekushkaCombatTarget(@NotNull Vec3d vec3d2) {
        Intrinsics.checkNotNullParameter((Object)vec3d2, (String)"<set-?>");
        this.chekushkaCombatTarget = vec3d2;
    }

    public final boolean isGoat() {
        return this.isGoat;
    }

    public final void setGoat(boolean bl) {
        this.isGoat = bl;
    }

    public final boolean isNightmareBb() {
        return this.isNightmareBb;
    }

    public final void setNightmareBb(boolean bl) {
        this.isNightmareBb = bl;
    }

    public final boolean isUfo() {
        return this.isUfo;
    }

    public final void setUfo(boolean bl) {
        this.isUfo = bl;
    }

    @NotNull
    public final UfoBeamMode getUfoBeamMode() {
        return this.ufoBeamMode;
    }

    public final void setUfoBeamMode(@NotNull UfoBeamMode ufoBeamMode) {
        Intrinsics.checkNotNullParameter((Object)((Object)ufoBeamMode), (String)"<set-?>");
        this.ufoBeamMode = ufoBeamMode;
    }

    public final boolean isUfoGrounded() {
        return this.isUfoGrounded;
    }

    public final void setUfoGrounded(boolean grounded) {
        if (this.isUfoGrounded == grounded) {
            return;
        }
        this.isUfoGrounded = grounded;
        int n = this.ufoTakeoffTicks = grounded ? 0 : 31;
        if (grounded) {
            this.ufoSpinTicks = 0;
            this.setUfoBoost(false);
        }
    }

    public final boolean getUfoBoost() {
        return this.ufoBoost;
    }

    public final void setUfoBoost(boolean boost) {
        this.ufoBoost = boost && !this.isUfoGrounded && this.ufoTakeoffTicks <= 0;
    }

    public final int getUfoSpinTicks() {
        return this.ufoSpinTicks;
    }

    public final void setUfoSpinTicks(int n) {
        this.ufoSpinTicks = n;
    }

    public final int getUfoTakeoffTicks() {
        return this.ufoTakeoffTicks;
    }

    public final void setUfoTakeoffTicks(int n) {
        this.ufoTakeoffTicks = n;
    }

    public final float getUfoBeamLevel() {
        return this.ufoBeamLevel;
    }

    public final void setUfoBeamLevel(float f) {
        this.ufoBeamLevel = f;
    }

    public final float getUfoBeamLevelPrev() {
        return this.ufoBeamLevelPrev;
    }

    public final void setUfoBeamLevelPrev(float f) {
        this.ufoBeamLevelPrev = f;
    }

    public final float getUfoLandBlend() {
        return this.ufoLandBlend;
    }

    public final void setUfoLandBlend(float f) {
        this.ufoLandBlend = f;
    }

    public final float getUfoLandBlendPrev() {
        return this.ufoLandBlendPrev;
    }

    public final void setUfoLandBlendPrev(float f) {
        this.ufoLandBlendPrev = f;
    }

    public final double getUfoGroundDrop() {
        return this.ufoGroundDrop;
    }

    public final void setUfoGroundDrop(double d) {
        this.ufoGroundDrop = d;
    }

    public final double getUfoGroundDropPrev() {
        return this.ufoGroundDropPrev;
    }

    public final void setUfoGroundDropPrev(double d) {
        this.ufoGroundDropPrev = d;
    }

    @Nullable
    public final GoatAction getGoatAction() {
        return this.goatAction;
    }

    public final void setGoatAction(@Nullable GoatAction goatAction) {
        this.goatAction = goatAction;
    }

    public final int getGoatActionTicksLeft() {
        return this.goatActionTicksLeft;
    }

    public final void setGoatActionTicksLeft(int n) {
        this.goatActionTicksLeft = n;
    }

    public final boolean getGoatSitting() {
        return this.goatSitting;
    }

    public final void setGoatSitting(boolean bl) {
        this.goatSitting = bl;
    }

    public final boolean getGoatSleeping() {
        return this.goatSleeping;
    }

    public final void setGoatSleeping(boolean bl) {
        this.goatSleeping = bl;
    }

    public final int getGoatIdleTicks() {
        return this.goatIdleTicks;
    }

    public final void setGoatIdleTicks(int n) {
        this.goatIdleTicks = n;
    }

    public final int getGoatRunTicks() {
        return this.goatRunTicks;
    }

    public final void setGoatRunTicks(int n) {
        this.goatRunTicks = n;
    }

    @Nullable
    public final ChekushkaAction getChekushkaAction() {
        return this.chekushkaAction;
    }

    public final void setChekushkaAction(@Nullable ChekushkaAction chekushkaAction) {
        this.chekushkaAction = chekushkaAction;
    }

    public final int getChekushkaActionTicksLeft() {
        return this.chekushkaActionTicksLeft;
    }

    public final void setChekushkaActionTicksLeft(int n) {
        this.chekushkaActionTicksLeft = n;
    }

    public final boolean getChekushkaBored() {
        return this.chekushkaBored;
    }

    public final void setChekushkaBored(boolean bl) {
        this.chekushkaBored = bl;
    }

    public final int getRobotType() {
        return this.robotType;
    }

    public final void setRobotType(int n) {
        this.robotType = n;
    }

    public final boolean isPetMoving() {
        return this.isPetMoving;
    }

    public final void setPetMoving(boolean bl) {
        this.isPetMoving = bl;
    }

    public final boolean isUsingUmbrella() {
        return this.isUsingUmbrella;
    }

    public final void setUsingUmbrella(boolean bl) {
        this.isUsingUmbrella = bl;
    }

    public final boolean isAirborneMode() {
        return this.isAirborneMode;
    }

    public final void setAirborneMode(boolean bl) {
        this.isAirborneMode = bl;
    }

    public final double getCurrentAnimationSpeed() {
        return this.currentAnimationSpeed;
    }

    public final void setCurrentAnimationSpeed(double d) {
        this.currentAnimationSpeed = d;
    }

    public final void setBehavior(@NotNull Vec3d desiredPosition, double desiredSpeed, boolean desiredMoving, boolean desiredUmbrella, boolean desiredAirborne, double animationSpeed) {
        Intrinsics.checkNotNullParameter((Object)desiredPosition, (String)"desiredPosition");
        if (desiredPosition.squaredDistanceTo(this.desiredPosition) > 0.2) {
            this.blockedTicks = 0;
            this.pauseTicks = 0;
            this.lastHorizontalDistance = Double.MAX_VALUE;
        }
        this.desiredPosition = desiredPosition;
        this.desiredSpeed = desiredSpeed;
        this.desiredMoving = desiredMoving;
        this.desiredUmbrella = desiredUmbrella;
        this.desiredAirborne = desiredAirborne;
        this.animationSpeed = animationSpeed;
    }

    public final void snapTo(@NotNull Vec3d pos, float yaw) {
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        this.setPosition(pos.x, pos.y, pos.z);
        this.lastX = pos.x;
        this.lastY = pos.y;
        this.lastZ = pos.z;
        this.lastYaw = yaw;
        this.lastPitch = 0.0f;
        this.isPetMoving = false;
        this.isUsingUmbrella = false;
        this.isAirborneMode = false;
        this.movingTicks = 0;
        this.stepSoundCooldown = 0;
        this.ambientSoundCooldown = 40;
        this.jumpCooldown = 0;
        this.blockedTicks = 0;
        this.pauseTicks = 0;
        this.sideStepTicks = 0;
        this.targetYaw = yaw;
        this.smoothedYaw = yaw;
        this.animationSpeed = 1.0;
        this.currentAnimationSpeed = 1.0;
        this.currentGroundSpeed = 0.0;
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.lastMoveDirection = vec3d2;
        this.verticalVelocity = 0.0;
        this.lastHorizontalDistance = Double.MAX_VALUE;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        this.airMotion = vec3d3;
        this.goatIdleTicks = 0;
        this.goatRunTicks = 0;
        this.goatSitting = false;
        this.ufoBeamMode = UfoBeamMode.OFF;
        this.setUfoGrounded(false);
        this.setUfoBoost(false);
        this.ufoSpinTicks = 0;
        this.ufoTakeoffTicks = 0;
        this.ufoBeamLevel = 0.0f;
        this.ufoBeamLevelPrev = 0.0f;
        this.ufoLandBlend = 0.0f;
        this.ufoLandBlendPrev = 0.0f;
        this.ufoGroundDrop = 0.6;
        this.ufoGroundDropPrev = 0.6;
        this.setVelocity(Vec3d.ZERO);
        this.setYaw(yaw);
        this.headYaw = yaw;
        this.bodyYaw = yaw;
        this.networkPosition = pos;
        this.networkYaw = yaw;
        this.hasNetworkAnchor = false;
    }

    public final boolean shouldUseUmbrella() {
        return this.isUsingUmbrella;
    }

    public final boolean isMovementBlocked() {
        return this.pauseTicks > 0 || this.blockedTicks >= 3;
    }

    public final boolean isUfoTakingOff() {
        return this.ufoTakeoffTicks > 0;
    }

    public final boolean hasUfoTrick() {
        return this.ufoSpinTicks > 0;
    }

    public final void triggerUfoSpin() {
        if (!this.isUfo || this.isUfoGrounded || this.ufoTakeoffTicks > 0) {
            return;
        }
        this.ufoSpinTicks = 27;
    }

    public final float getUfoBeamLevel(float partialTick) {
        return MathHelper.lerp((float)partialTick, (float)this.ufoBeamLevelPrev, (float)this.ufoBeamLevel);
    }

    public final float getUfoLandBlend(float partialTick) {
        return MathHelper.lerp((float)partialTick, (float)this.ufoLandBlendPrev, (float)this.ufoLandBlend);
    }

    public final double getUfoGroundDrop(float partialTick) {
        return MathHelper.lerp((double)partialTick, (double)this.ufoGroundDropPrev, (double)this.ufoGroundDrop);
    }

    private final float ufoBeamTarget() {
        if (this.ufoBeamMode == UfoBeamMode.OFF || this.isUfoGrounded || this.ufoTakeoffTicks > 0 || this.ufoSpinTicks > 0 || this.ufoBoost) {
            return 0.0f;
        }
        return switch (WhenMappings.$EnumSwitchMapping$0[this.ufoBeamMode.ordinal()]) {
            case 1 -> 1.0f;
            case 2 -> 0.72f;
            default -> 0.88f;
        };
    }

    private final void tickUfoState() {
        int n;
        if (!this.isUfo) {
            return;
        }
        if (this.ufoSpinTicks > 0) {
            n = this.ufoSpinTicks;
            this.ufoSpinTicks = n + -1;
        }
        if (this.ufoTakeoffTicks > 0) {
            n = this.ufoTakeoffTicks;
            this.ufoTakeoffTicks = n + -1;
        }
        float target = this.ufoBeamTarget();
        this.ufoBeamLevelPrev = this.ufoBeamLevel;
        this.ufoBeamLevel += (target - this.ufoBeamLevel) * 0.11f;
        if (Math.abs(target - this.ufoBeamLevel) < 0.002f) {
            this.ufoBeamLevel = target;
        }
        float landTarget = this.isUfoGrounded ? 1.0f : 0.0f;
        this.ufoLandBlendPrev = this.ufoLandBlend;
        this.ufoLandBlend += (landTarget - this.ufoLandBlend) * 0.085f;
        if (Math.abs(landTarget - this.ufoLandBlend) < 0.002f) {
            this.ufoLandBlend = landTarget;
        }
        this.tickUfoGround();
    }

    private final void tickUfoGround() {
        this.ufoGroundDropPrev = this.ufoGroundDrop;
        World world2 = this.getEntityWorld();
        if (world2 == null) {
            return;
        }
        World level = world2;
        if (this.ufoBeamLevel < 0.01f && this.ufoBeamMode == UfoBeamMode.OFF) {
            return;
        }
        double measured = 14.0;
        try {
            Vec3d from = new Vec3d(this.getX(), this.getY() + 0.35, this.getZ());
            Vec3d to = new Vec3d(from.x, from.y - 14.0, from.z);
            BlockHitResult blockHitResult2 = level.raycast(new RaycastContext(from, to, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.ANY, (Entity)this));
            Intrinsics.checkNotNullExpressionValue((Object)blockHitResult2, (String)"clip(...)");
            BlockHitResult hit = blockHitResult2;
            if (hit.getType() != HitResult.Type.MISS) {
                measured = this.getY() - hit.getPos().y;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        measured = MathHelper.clamp((double)measured, (double)0.0, (double)14.0);
        if (this.ufoBeamLevel < 0.02f) {
            this.ufoGroundDrop = measured;
            this.ufoGroundDropPrev = measured;
            return;
        }
        this.ufoGroundDrop += (measured - this.ufoGroundDrop) * 0.24;
    }

    public final boolean hasGoatAction() {
        return this.goatAction != null;
    }

    public final void triggerGoatAction(@Nullable GoatAction action) {
        if (action == null || !this.isGoat || !this.canInterruptGoatAction(action)) {
            return;
        }
        this.goatAction = action;
        this.goatActionTicksLeft = action.getDurationTicks();
    }

    private final boolean canInterruptGoatAction(GoatAction next) {
        GoatAction goatAction = this.goatAction;
        if (goatAction == null) {
            return true;
        }
        GoatAction current = goatAction;
        return switch (WhenMappings.$EnumSwitchMapping$1[current.ordinal()]) {
            case 1 -> false;
            case 2, 3, 4, 5 -> {
                if (next == GoatAction.CELEBRATE || next == GoatAction.HURT) {
                    yield true;
                }
                yield false;
            }
            case 6, 7 -> true;
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    public final boolean hasChekushkaAction() {
        return this.chekushkaAction != null;
    }

    public final void triggerChekushkaAction(@Nullable ChekushkaAction action) {
        if (action == null || !this.isChekushka || !this.canInterruptChekushkaAction(action)) {
            return;
        }
        this.chekushkaAction = action;
        this.chekushkaActionTicksLeft = action.getDurationTicks();
        this.chekushkaBored = false;
    }

    private final boolean canInterruptChekushkaAction(ChekushkaAction next) {
        ChekushkaAction chekushkaAction = this.chekushkaAction;
        if (chekushkaAction == null) {
            return true;
        }
        ChekushkaAction current = chekushkaAction;
        return switch (WhenMappings.$EnumSwitchMapping$2[current.ordinal()]) {
            case 1 -> {
                if (next == ChekushkaAction.CELEBRATE || next == ChekushkaAction.HURT) {
                    yield true;
                }
                yield false;
            }
            case 2 -> {
                if (next == ChekushkaAction.HURT) {
                    yield true;
                }
                yield false;
            }
            case 3 -> {
                if (next == ChekushkaAction.HURT || next == ChekushkaAction.CELEBRATE) {
                    yield true;
                }
                yield false;
            }
            case 4 -> {
                if (next == ChekushkaAction.HURT || next == ChekushkaAction.SHOOT) {
                    yield true;
                }
                yield false;
            }
            case 5 -> {
                if (next == ChekushkaAction.CELEBRATE) {
                    yield true;
                }
                yield false;
            }
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    @Nullable
    public final ChekushkaAction currentChekushkaAction() {
        return this.chekushkaAction;
    }

    public final void overrideLookYaw(float yaw, float weight) {
        this.lookOverrideYaw = yaw;
        this.lookOverrideWeight = MathHelper.clamp((float)weight, (float)0.0f, (float)1.0f);
    }

    public final void setChekushkaCombat(boolean active, double x, double y, double z) {
        boolean next;
        boolean bl = next = active && this.isChekushka;
        if (next) {
            this.chekushkaCombatTarget = new Vec3d(x, y, z);
        } else if (this.isChekushkaCombat) {
            this.lastHorizontalDistance = Double.MAX_VALUE;
            this.blockedTicks = 0;
            this.pauseTicks = 0;
        }
        this.isChekushkaCombat = next;
    }

    public final void setGoatPose(boolean sleeping) {
        this.goatSleeping = sleeping;
    }

    public final void applyNetworkState(@Nullable CustomPetRemoteState state) {
        if (state == null) {
            this.networkControlled = false;
            this.hasNetworkAnchor = false;
            return;
        }
        this.networkControlled = true;
        this.petVariant = state.variant();
        this.robotType = state.robotType();
        this.networkPosition = state.position();
        this.networkYaw = state.yaw();
        this.networkMoving = state.moving();
        this.networkUmbrella = state.umbrella();
        this.networkAirborne = state.airborne();
        this.networkAnimationSpeed = state.animationSpeed();
    }

    public final void applyRemoteState(@Nullable CustomPetRemoteState state) {
        this.applyNetworkState(state);
    }

    public final void releaseRemoteControl() {
        this.networkControlled = false;
        this.hasNetworkAnchor = false;
    }

    public void tick() {
        this.tickChekushkaAction();
        this.tickGoatAction();
        if (this.networkControlled) {
            super.tick();
            this.tickNetworkState();
            this.tickGoatState();
            this.tickUfoState();
            return;
        }
        this.setNoGravity(this.desiredAirborne);
        super.tick();
        this.tickCustomMovement();
        this.tickGoatState();
        this.tickUfoState();
    }

    public int getMaxLookPitchChange() {
        return 0;
    }

    public int getMaxHeadRotation() {
        return 0;
    }

    public float getStepHeight() {
        return this.isAirborneMode ? 0.0f : 1.0f;
    }

    public boolean shouldRender(double distance) {
        return true;
    }

    public boolean canHit() {
        return false;
    }

    public boolean isAttackable() {
        return false;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean isCollidable(@Nullable Entity entity) {
        return false;
    }

    public boolean collidesWith(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        return false;
    }

    public void pushAwayFrom(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
    }

    public void addVelocity(double x, double y, double z) {
    }

    public void addVelocity(@NotNull Vec3d movement) {
        Intrinsics.checkNotNullParameter((Object)movement, (String)"movement");
    }

    @NotNull
    public PistonBehavior getPistonBehavior() {
        return PistonBehavior.IGNORE;
    }

    private final void tickChekushkaAction() {
        if (this.chekushkaAction == null) {
            return;
        }
        this.chekushkaActionTicksLeft += -1;
        if (this.chekushkaActionTicksLeft <= 0) {
            this.chekushkaAction = null;
        }
    }

    private final void tickGoatAction() {
        if (this.goatAction == null) {
            return;
        }
        this.goatActionTicksLeft += -1;
        if (this.goatActionTicksLeft <= 0) {
            this.goatAction = null;
        }
    }

    private final void tickGoatState() {
        boolean resting;
        if (!this.isGoat) {
            return;
        }
        boolean grounded = this.isOnGround() && !this.isTouchingWater() && !this.isAirborneMode;
        boolean bl = resting = grounded && !this.isPetMoving && this.goatAction == null && !this.goatSleeping;
        if (resting) {
            int n = this.goatIdleTicks;
            this.goatIdleTicks = n + 1;
            if (this.goatIdleTicks >= 360) {
                this.goatIdleTicks = 0;
            }
        } else {
            this.goatIdleTicks = 0;
        }
        boolean bl2 = this.goatSitting = this.goatIdleTicks >= 160;
        if (this.isPetMoving && grounded && this.currentAnimationSpeed >= 1.35) {
            this.goatRunTicks = Math.min(this.goatRunTicks + 1, 8);
            return;
        }
        if (this.goatRunTicks >= 8 && grounded && !this.isPetMoving) {
            this.triggerGoatAction(GoatAction.RUN_STOP);
        }
        this.goatRunTicks = 0;
    }

    private final boolean goatInJumpState() {
        return !this.isOnGround() && !this.isTouchingWater() && !this.isAirborneMode;
    }

    private final boolean chekushkaInJumpState() {
        return !this.isOnGround() && !this.isTouchingWater() && !this.isAirborneMode;
    }

    private final void tickCombatStance() {
        this.isPetMoving = false;
        this.movingTicks = 0;
        this.currentGroundSpeed = 0.0;
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.airMotion = vec3d2;
        this.currentAnimationSpeed += (1.0 - this.currentAnimationSpeed) * 0.28;
        if (this.isOnGround()) {
            this.verticalVelocity = 0.0;
        } else {
            this.verticalVelocity = Math.max(this.verticalVelocity - 0.08, -0.36);
            this.move(MovementType.SELF, new Vec3d(0.0, this.verticalVelocity, 0.0));
        }
        this.setVelocity(Vec3d.ZERO);
        double dx = this.chekushkaCombatTarget.x - this.getX();
        double dz = this.chekushkaCombatTarget.z - this.getZ();
        if (dx * dx + dz * dz > 1.0E-6) {
            this.targetYaw = (float)Math.toDegrees(MathHelper.atan2((double)dz, (double)dx)) - 90.0f;
            this.smoothedYaw = MathHelper.stepUnwrappedAngleTowards((float)this.smoothedYaw, (float)this.targetYaw, (float)22.0f);
        }
        this.lastYaw = this.getYaw();
        this.setYaw(this.smoothedYaw);
        this.setHeadYaw(this.smoothedYaw);
        this.setBodyYaw(this.smoothedYaw);
    }

    private final void tickNetworkState() {
        this.movingTicks = 0;
        this.jumpCooldown = 0;
        this.blockedTicks = 0;
        this.pauseTicks = 0;
        this.currentGroundSpeed = 0.0;
        this.verticalVelocity = 0.0;
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.airMotion = vec3d2;
        this.lastHorizontalDistance = Double.MAX_VALUE;
        this.isAirborneMode = this.networkAirborne;
        this.isUsingUmbrella = this.networkUmbrella && !this.networkAirborne && !this.petVariant.isRobot();
        this.setNoGravity(true);
        this.setVelocity(Vec3d.ZERO);
        double prevX = this.getX();
        double prevY = this.getY();
        double prevZ = this.getZ();
        double dx = this.networkPosition.x - prevX;
        double dy = this.networkPosition.y - prevY;
        double dz = this.networkPosition.z - prevZ;
        double distSqr = dx * dx + dy * dy + dz * dz;
        double newX = 0.0;
        double newY = 0.0;
        double newZ = 0.0;
        if (!this.hasNetworkAnchor || distSqr > 36.0) {
            newX = this.networkPosition.x;
            newY = this.networkPosition.y;
            newZ = this.networkPosition.z;
            this.lastX = newX;
            this.lastY = newY;
            this.lastZ = newZ;
            this.hasNetworkAnchor = true;
        } else {
            double lerp = 0.35;
            newX = prevX + dx * lerp;
            newY = prevY + dy * lerp;
            newZ = prevZ + dz * lerp;
            this.lastX = prevX;
            this.lastY = prevY;
            this.lastZ = prevZ;
        }
        this.setPosition(newX, newY, newZ);
        double movedHorizSqr = (newX - this.lastX) * (newX - this.lastX) + (newZ - this.lastZ) * (newZ - this.lastZ);
        boolean interpMoving = movedHorizSqr > 2.5E-5 || Math.abs(newY - this.lastY) > 0.003;
        this.isPetMoving = this.networkMoving && interpMoving;
        this.currentAnimationSpeed = this.animationSpeed = this.isPetMoving ? this.networkAnimationSpeed : 1.0;
        this.lastYaw = this.getYaw();
        this.targetYaw = this.networkYaw;
        this.smoothedYaw = MathHelper.stepUnwrappedAngleTowards((float)this.getYaw(), (float)this.networkYaw, (float)18.0f);
        this.setYaw(this.smoothedYaw);
        this.setHeadYaw(this.smoothedYaw);
        this.setBodyYaw(this.smoothedYaw);
    }

    private final void tickCustomMovement() {
        boolean madeProgress;
        boolean actualMoving;
        boolean keepMoving;
        boolean wantsToMove;
        int n;
        boolean wasAirborne = this.isAirborneMode;
        this.isAirborneMode = this.desiredAirborne;
        this.isUsingUmbrella = this.desiredUmbrella && !this.isAirborneMode && !this.petVariant.isRobot();
        this.setNoGravity(this.isAirborneMode);
        if (!(this.isAirborneMode || !wasAirborne && this.desiredMoving)) {
            this.snapToGroundIfClose();
        }
        if (this.jumpCooldown > 0) {
            n = this.jumpCooldown;
            this.jumpCooldown = n + -1;
        }
        if (this.pauseTicks > 0) {
            n = this.pauseTicks;
            this.pauseTicks = n + -1;
            this.isPetMoving = false;
            this.movingTicks = 0;
            this.currentGroundSpeed = 0.0;
            this.verticalVelocity = 0.0;
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
            this.airMotion = vec3d2;
            this.setVelocity(Vec3d.ZERO);
            this.currentAnimationSpeed += (1.0 - this.currentAnimationSpeed) * 0.28;
            this.lastYaw = this.smoothedYaw;
            this.setYaw(this.smoothedYaw);
            this.setHeadYaw(this.smoothedYaw);
            this.setBodyYaw(this.smoothedYaw);
            this.tickClientAudio(false, 0.0);
            return;
        }
        if (this.isAirborneMode) {
            this.tickAirMovement();
            return;
        }
        if (this.isChekushkaCombat) {
            this.tickCombatStance();
            this.tickClientAudio(false, 0.0);
            return;
        }
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        this.airMotion = vec3d3;
        Vec3d vec3d4 = this.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"position(...)");
        Vec3d current = vec3d4;
        Vec3d vec3d5 = this.desiredPosition.subtract(current);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"subtract(...)");
        Vec3d toTarget = vec3d5;
        Vec3d horizontal = new Vec3d(toTarget.x, 0.0, toTarget.z);
        double horizontalDistance = horizontal.horizontalLength();
        boolean bl = wantsToMove = this.desiredMoving && horizontalDistance > 0.045;
        if (wantsToMove) {
            float desiredYaw = (float)Math.toDegrees(MathHelper.atan2((double)horizontal.z, (double)horizontal.x)) - 90.0f;
            float yawApproach = (float)RangesKt.coerceIn((double)(12.0 + horizontalDistance * 2.4 + this.desiredSpeed * 80.0), (double)12.0, (double)32.0);
            this.targetYaw = MathHelper.stepUnwrappedAngleTowards((float)this.targetYaw, (float)desiredYaw, (float)yawApproach);
        }
        float turnSpeed = wantsToMove ? (float)RangesKt.coerceIn((double)(11.0 + horizontalDistance * 1.8 + this.desiredSpeed * 86.0), (double)12.0, (double)30.0) : 9.0f;
        this.smoothedYaw = MathHelper.stepUnwrappedAngleTowards((float)this.smoothedYaw, (float)this.targetYaw, (float)turnSpeed);
        float remainingYawDifference = Math.abs(MathHelper.wrapDegrees((float)(this.targetYaw - this.smoothedYaw)));
        double turnPenalty = wantsToMove ? MathHelper.clamp((double)(1.0 - Math.max(0.0, (double)(remainingYawDifference - 6.0f)) / (double)78.0f), (double)0.18, (double)1.0) : 0.0;
        double slowdownFactor = wantsToMove ? MathHelper.clamp((double)((horizontalDistance - 0.04) / 1.6), (double)0.34, (double)1.0) : 0.0;
        double targetGroundSpeed = wantsToMove ? Math.min(horizontalDistance, this.desiredSpeed * slowdownFactor * turnPenalty) : 0.0;
        double groundAcceleration = wantsToMove ? RangesKt.coerceIn((double)(0.18 + this.desiredSpeed * 0.95 + horizontalDistance * 0.06), (double)0.18, (double)0.52) : 0.24;
        this.currentGroundSpeed += (targetGroundSpeed - this.currentGroundSpeed) * groundAcceleration;
        if (Math.abs(this.currentGroundSpeed) < 1.0E-4) {
            this.currentGroundSpeed = 0.0;
        }
        boolean bl2 = keepMoving = this.currentGroundSpeed > 0.012;
        this.isPetMoving = wantsToMove ? keepMoving || horizontalDistance > 0.18 : keepMoving;
        Vec3d vec3d6 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"ZERO");
        Vec3d horizontalMove = vec3d6;
        if (this.currentGroundSpeed > 1.0E-5) {
            if (wantsToMove && horizontalDistance > 1.0E-5) {
                Vec3d moveDirection;
                Vec3d vec3d7 = horizontal.normalize();
                Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"normalize(...)");
                this.lastMoveDirection = moveDirection = vec3d7;
                Vec3d vec3d8 = moveDirection.multiply(Math.min(horizontalDistance, this.currentGroundSpeed));
                Intrinsics.checkNotNullExpressionValue((Object)vec3d8, (String)"scale(...)");
                horizontalMove = vec3d8;
            } else if (this.lastMoveDirection.lengthSquared() > 1.0E-6) {
                Vec3d vec3d9 = this.lastMoveDirection.multiply(this.currentGroundSpeed);
                Intrinsics.checkNotNullExpressionValue((Object)vec3d9, (String)"scale(...)");
                horizontalMove = vec3d9;
            }
        }
        if (this.horizontalCollision && wantsToMove && this.blockedTicks >= 1 && horizontalDistance > 0.25 && this.currentGroundSpeed > 0.02) {
            if (this.sideStepTicks <= 0) {
                this.sideStepDir = this.random.nextBoolean() ? 1 : -1;
                this.sideStepTicks = 12;
            }
            Vec3d vec3d10 = horizontal.lengthSquared() > 1.0E-6 ? horizontal.normalize() : new Vec3d(0.0, 0.0, 1.0);
            Intrinsics.checkNotNull((Object)vec3d10);
            Vec3d dir = vec3d10;
            Vec3d perpendicular = new Vec3d(-dir.z * (double)this.sideStepDir, 0.0, dir.x * (double)this.sideStepDir);
            Vec3d vec3d11 = dir.multiply(0.45).add(perpendicular.multiply(0.9));
            Intrinsics.checkNotNullExpressionValue((Object)vec3d11, (String)"add(...)");
            Vec3d blended = vec3d11;
            if (blended.lengthSquared() > 1.0E-6) {
                double stepLength = Math.max(this.currentGroundSpeed, this.desiredSpeed * 0.7);
                Vec3d vec3d12 = blended.normalize().multiply(Math.min(horizontalDistance, stepLength));
                Intrinsics.checkNotNullExpressionValue((Object)vec3d12, (String)"scale(...)");
                horizontalMove = vec3d12;
            }
        }
        if (this.sideStepTicks > 0) {
            int dir = this.sideStepTicks;
            this.sideStepTicks = dir + -1;
        }
        double yDiff = this.desiredPosition.y - this.getY();
        if (this.isOnGround()) {
            if (yDiff > 1.15 && horizontalDistance <= 1.6 && wantsToMove && this.currentGroundSpeed > 0.035 && this.jumpCooldown <= 0 && this.blockedTicks < 3) {
                this.verticalVelocity = 0.42;
                this.jumpCooldown = 11;
            } else {
                this.verticalVelocity = yDiff < -0.35 ? Math.max(-0.16, yDiff) : 0.0;
            }
        } else {
            this.verticalVelocity = Math.max(this.verticalVelocity - 0.08, -0.36);
        }
        Vec3d moveVec = new Vec3d(horizontalMove.x, this.verticalVelocity, horizontalMove.z);
        this.move(MovementType.SELF, moveVec);
        Vec3d vec3d13 = this.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d13, (String)"position(...)");
        Vec3d movedTo = vec3d13;
        double actualHorizontalDeltaSqr = this.square(movedTo.x - current.x) + this.square(movedTo.z - current.z);
        boolean bl3 = actualMoving = actualHorizontalDeltaSqr > 2.5E-5 || Math.abs(movedTo.y - current.y) > 0.003;
        if (this.isOnGround() && this.verticalVelocity < 0.0) {
            this.verticalVelocity = 0.0;
        }
        if (this.isOnGround() && this.verticalVelocity <= 0.0 && this.desiredPosition.y <= this.getY() && Math.abs(this.desiredPosition.y - this.getY()) < 0.18) {
            this.setPosition(this.getX(), this.desiredPosition.y, this.getZ());
        }
        this.isPetMoving = (wantsToMove || this.currentGroundSpeed > 0.012) && actualMoving;
        Vec3d vec3d14 = this.desiredPosition.subtract(this.getEntityPos());
        Intrinsics.checkNotNullExpressionValue((Object)vec3d14, (String)"subtract(...)");
        Vec3d remaining = vec3d14;
        double remainingHorizontalDistance = Math.hypot(remaining.x, remaining.z);
        boolean bl4 = madeProgress = remainingHorizontalDistance + 0.025 < this.lastHorizontalDistance;
        if (this.currentGroundSpeed > 0.02 && this.horizontalCollision && this.isOnGround()) {
            int n2 = this.blockedTicks;
            this.blockedTicks = n2 + 1;
        } else if (!wantsToMove || madeProgress || remainingHorizontalDistance < 0.8) {
            this.blockedTicks = 0;
        }
        if (this.blockedTicks >= 7) {
            this.pauseTicks = 8;
            this.blockedTicks = 0;
            this.sideStepTicks = 0;
            this.isPetMoving = false;
            this.movingTicks = 0;
            this.currentGroundSpeed = 0.0;
            this.verticalVelocity = 0.0;
        }
        this.lastHorizontalDistance = wantsToMove ? remainingHorizontalDistance : Double.MAX_VALUE;
        double animationTarget = this.isPetMoving ? Math.max(1.0, this.animationSpeed) : 1.0;
        this.currentAnimationSpeed += (animationTarget - this.currentAnimationSpeed) * (this.isPetMoving ? 0.22 : 0.18);
        this.setVelocity(Vec3d.ZERO);
        this.lastYaw = this.smoothedYaw;
        this.setYaw(this.smoothedYaw);
        this.setHeadYaw(this.smoothedYaw);
        this.setBodyYaw(this.smoothedYaw);
        this.tickClientAudio(this.isPetMoving, horizontalMove.horizontalLengthSquared());
    }

    private final void tickAirMovement() {
        Vec3d vec3d2;
        boolean idleVerticalHover;
        boolean wantsToMove;
        Vec3d vec3d3 = this.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"position(...)");
        Vec3d current = vec3d3;
        Vec3d vec3d4 = this.desiredPosition.subtract(current);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"subtract(...)");
        Vec3d toTarget = vec3d4;
        Vec3d horizontal = new Vec3d(toTarget.x, 0.0, toTarget.z);
        double horizontalDistance = horizontal.horizontalLength();
        double totalDistance = toTarget.length();
        double verticalDistance = Math.abs(toTarget.y);
        boolean bl = wantsToMove = this.desiredMoving && totalDistance > 0.02;
        if (horizontalDistance > 1.0E-4) {
            float desiredYaw = (float)Math.toDegrees(MathHelper.atan2((double)horizontal.z, (double)horizontal.x)) - 90.0f;
            this.targetYaw = MathHelper.stepUnwrappedAngleTowards((float)this.targetYaw, (float)desiredYaw, (float)10.5f);
        }
        if (this.lookOverrideWeight > 0.001f) {
            float stillness = wantsToMove ? 0.15f : 1.0f;
            float pull = 4.5f * this.lookOverrideWeight * stillness;
            this.targetYaw = MathHelper.stepUnwrappedAngleTowards((float)this.targetYaw, (float)this.lookOverrideYaw, (float)pull);
        }
        this.smoothedYaw = MathHelper.stepUnwrappedAngleTowards((float)this.smoothedYaw, (float)this.targetYaw, (float)(wantsToMove ? 14.0f : 6.0f));
        float remainingYawDifference = Math.abs(MathHelper.wrapDegrees((float)(this.targetYaw - this.smoothedYaw)));
        boolean canAdvance = wantsToMove && (remainingYawDifference <= 9.0f || totalDistance <= 0.35);
        this.movingTicks = 0;
        this.jumpCooldown = 0;
        this.blockedTicks = 0;
        this.pauseTicks = 0;
        this.verticalVelocity = 0.0;
        boolean insideHoverDeadzone = horizontalDistance <= 0.16 && verticalDistance <= 0.012;
        boolean insideSettleZone = horizontalDistance <= 0.42 && verticalDistance <= 0.09;
        Vec3d vec3d5 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"ZERO");
        Vec3d desiredStep = vec3d5;
        if (canAdvance && totalDistance > 1.0E-5 && !insideHoverDeadzone) {
            double slowdown = MathHelper.clamp((double)((totalDistance - 0.2) / 1.6), (double)0.08, (double)1.0);
            double followStep = RangesKt.coerceIn((double)(this.desiredSpeed * slowdown), (double)0.012, (double)6.5);
            Vec3d vec3d6 = toTarget.normalize().multiply(Math.min(totalDistance, followStep));
            Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"scale(...)");
            desiredStep = vec3d6;
        } else if (!this.desiredMoving && verticalDistance > 0.001) {
            double verticalStep = MathHelper.clamp((double)(verticalDistance * 0.38), (double)0.0025, (double)0.018);
            desiredStep = new Vec3d(0.0, Math.copySign(Math.min(verticalDistance, verticalStep), toTarget.y), 0.0);
        }
        double blend = RangesKt.coerceIn((double)(0.12 + totalDistance * 0.06 + this.desiredSpeed * 0.028), (double)0.12, (double)0.38);
        Vec3d vec3d7 = this.airMotion.lerp(desiredStep, blend);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"lerp(...)");
        this.airMotion = vec3d7;
        if (!this.desiredMoving) {
            this.airMotion = new Vec3d(this.airMotion.x * 0.4, this.airMotion.y, this.airMotion.z * 0.4);
        }
        double damping = insideHoverDeadzone ? 0.18 : (insideSettleZone ? 0.42 : (totalDistance < 1.5 ? 0.78 : 0.9));
        boolean bl2 = idleVerticalHover = !this.desiredMoving && verticalDistance > 0.001;
        if (!(canAdvance && !(desiredStep.lengthSquared() < 1.0E-6) || idleVerticalHover)) {
            Vec3d vec3d8 = this.airMotion.multiply(damping);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d8, (String)"scale(...)");
            this.airMotion = vec3d8;
        } else if (idleVerticalHover) {
            this.airMotion = new Vec3d(this.airMotion.x, this.airMotion.y * 0.96, this.airMotion.z);
        }
        if (insideHoverDeadzone && verticalDistance <= 0.0015 || this.airMotion.lengthSquared() < 4.0E-5) {
            Vec3d vec3d9 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d9, (String)"ZERO");
            this.airMotion = vec3d9;
        }
        if (this.airMotion.lengthSquared() > 0.0) {
            vec3d2 = this.airMotion.length() > totalDistance ? toTarget : this.airMotion;
        } else {
            Vec3d vec3d10 = Vec3d.ZERO;
            Intrinsics.checkNotNull((Object)vec3d10);
            vec3d2 = vec3d10;
        }
        Vec3d moveVec = vec3d2;
        this.move(MovementType.SELF, moveVec);
        if (this.horizontalCollision && this.desiredMoving) {
            this.move(MovementType.SELF, new Vec3d(0.0, 0.12, 0.0));
        }
        Vec3d vec3d11 = this.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d11, (String)"position(...)");
        Vec3d movedTo = vec3d11;
        double actualHorizontalDeltaSqr = this.square(movedTo.x - current.x) + this.square(movedTo.z - current.z);
        this.isPetMoving = wantsToMove && (actualHorizontalDeltaSqr > 2.5E-5 || Math.abs(movedTo.y - current.y) > 0.003);
        this.lastHorizontalDistance = wantsToMove ? horizontalDistance : Double.MAX_VALUE;
        double animationTarget = this.isPetMoving ? Math.max(1.0, this.animationSpeed) : 1.0;
        this.currentAnimationSpeed += (animationTarget - this.currentAnimationSpeed) * (this.isPetMoving ? 0.2 : 0.16);
        this.setVelocity(Vec3d.ZERO);
        this.lastYaw = this.smoothedYaw;
        this.setYaw(this.smoothedYaw);
        this.setHeadYaw(this.smoothedYaw);
        this.setBodyYaw(this.smoothedYaw);
        this.tickClientAudio(false, 0.0);
    }

    private final void snapToGroundIfClose() {
        World world2 = this.getEntityWorld();
        if (world2 == null) {
            return;
        }
        World level = world2;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        int blockX = MathHelper.floor((double)x);
        int blockZ = MathHelper.floor((double)z);
        int baseY = MathHelper.floor((double)(y + 0.2));
        BlockPos.Mutable mutablePos = new BlockPos.Mutable(blockX, baseY + 1, blockZ);
        int n = baseY - 4;
        int blockY = baseY + 1;
        if (n <= blockY) {
            while (true) {
                mutablePos.set(blockX, blockY, blockZ);
                BlockState state = level.getBlockState((BlockPos)mutablePos);
                VoxelShape shape = state.getCollisionShape((BlockView)level, (BlockPos)mutablePos);
                double groundY;
                double drop;
                if (!shape.isEmpty() && (drop = y - (groundY = (double)blockY + shape.getMax(Direction.Axis.Y))) >= -0.08 && drop <= 0.72) {
                    this.setPosition(x, groundY, z);
                    this.lastX = x;
                    this.lastY = groundY;
                    this.lastZ = z;
                    this.verticalVelocity = 0.0;
                    this.onLanding();
                    this.setVelocity(Vec3d.ZERO);
                    return;
                }
                if (blockY == n) break;
                --blockY;
            }
        }
    }

    private final boolean emitsAudioCues() {
        return !this.isOwl && !this.isChekushka && !this.isGoat && !this.isNightmareBb && !this.isUfo;
    }

    private final void handleGoatSoundCue(String cue) {
        SoundEvent soundEvent2;
        World world2 = this.getEntityWorld();
        if (world2 == null) {
            return;
        }
        World level = world2;
        if (cue == null) {
            return;
        }
        switch (cue) {
            case "bigear_goat.ambient": {
                SoundEvent soundEvent3 = SoundEvents.ENTITY_GOAT_AMBIENT;
                soundEvent2 = soundEvent3;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent3, (String)"GOAT_AMBIENT");
                break;
            }
            case "bigear_goat.scream": {
                SoundEvent soundEvent4 = SoundEvents.ENTITY_GOAT_SCREAMING_AMBIENT;
                soundEvent2 = soundEvent4;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent4, (String)"GOAT_SCREAMING_AMBIENT");
                break;
            }
            case "bigear_goat.hurt": {
                SoundEvent soundEvent5 = SoundEvents.ENTITY_GOAT_HURT;
                soundEvent2 = soundEvent5;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent5, (String)"GOAT_HURT");
                break;
            }
            case "bigear_goat.scream_hurt": {
                SoundEvent soundEvent6 = SoundEvents.ENTITY_GOAT_SCREAMING_HURT;
                soundEvent2 = soundEvent6;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent6, (String)"GOAT_SCREAMING_HURT");
                break;
            }
            case "bigear_goat.death": {
                SoundEvent soundEvent7 = SoundEvents.ENTITY_GOAT_DEATH;
                soundEvent2 = soundEvent7;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent7, (String)"GOAT_DEATH");
                break;
            }
            case "bigear_goat.eat": {
                SoundEvent soundEvent8 = SoundEvents.ENTITY_GOAT_EAT;
                soundEvent2 = soundEvent8;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent8, (String)"GOAT_EAT");
                break;
            }
            case "bigear_goat.step": {
                SoundEvent soundEvent9 = SoundEvents.ENTITY_GOAT_STEP;
                soundEvent2 = soundEvent9;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent9, (String)"GOAT_STEP");
                break;
            }
            case "bigear_goat.jump": {
                SoundEvent soundEvent10 = SoundEvents.ENTITY_GOAT_LONG_JUMP;
                soundEvent2 = soundEvent10;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent10, (String)"GOAT_LONG_JUMP");
                break;
            }
            case "bigear_goat.prepare_ram": {
                SoundEvent soundEvent11 = SoundEvents.ENTITY_GOAT_SCREAMING_PREPARE_RAM;
                soundEvent2 = soundEvent11;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent11, (String)"GOAT_SCREAMING_PREPARE_RAM");
                break;
            }
            case "bigear_goat.ram_impact": {
                SoundEvent soundEvent12 = SoundEvents.ENTITY_GOAT_RAM_IMPACT;
                soundEvent2 = soundEvent12;
                Intrinsics.checkNotNullExpressionValue((Object)soundEvent12, (String)"GOAT_RAM_IMPACT");
                break;
            }
            case "bigear_goat.horn": {
                Object object = ((RegistryEntry.Reference)SoundEvents.GOAT_HORN_SOUNDS.get(0)).value();
                Intrinsics.checkNotNullExpressionValue((Object)object, (String)"value(...)");
                soundEvent2 = (SoundEvent)object;
                break;
            }
            default: {
                return;
            }
        }
        SoundEvent sound = soundEvent2;
        float volume = switch (cue) {
            case "bigear_goat.step" -> 0.35f;
            case "bigear_goat.horn" -> 0.4f;
            case "bigear_goat.scream", "bigear_goat.scream_hurt" -> 0.5f;
            default -> 0.6f;
        };
        float pitch = 0.96f + this.random.nextFloat() * 0.08f;
        level.playSoundClient(this.getX(), this.getY(), this.getZ(), sound, this.getSoundCategory(), volume, pitch, false);
    }

    private final void tickClientAudio(boolean movingNow, double horizontalDeltaSqr) {
        int n;
        World world2 = this.getEntityWorld();
        if (world2 == null) {
            return;
        }
        World level = world2;
        if (!this.emitsAudioCues()) {
            return;
        }
        if (this.petVariant.isRobot()) {
            return;
        }
        if (this.ambientSoundCooldown > 0) {
            n = this.ambientSoundCooldown;
            this.ambientSoundCooldown = n + -1;
        }
        if (this.stepSoundCooldown > 0) {
            n = this.stepSoundCooldown;
            this.stepSoundCooldown = n + -1;
        }
        if (movingNow && horizontalDeltaSqr > 4.0E-4 && this.isOnGround() && this.stepSoundCooldown <= 0) {
            SoundEvent sound = CustomPetEntity.Companion.stepSound();
            if (sound != null) {
                level.playSoundClient(this.getX(), this.getY(), this.getZ(), sound, this.getSoundCategory(), 0.55f, 0.94f + this.random.nextFloat() * 0.12f, false);
            }
            this.stepSoundCooldown = 8 + this.random.nextInt(4);
        }
        if (!movingNow && this.ambientSoundCooldown <= 0) {
            SoundEvent sound = CustomPetEntity.Companion.ambientSound();
            if (sound != null) {
                level.playSoundClient(this.getX(), this.getY(), this.getZ(), sound, this.getSoundCategory(), 0.75f, 0.94f + this.random.nextFloat() * 0.14f, false);
            }
            this.ambientSoundCooldown = 120 + this.random.nextInt(100);
        }
    }

    private final double square(double value) {
        return value * value;
    }

    public void registerControllers(@NotNull AnimatableManager.ControllerRegistrar controllers) {
        Intrinsics.checkNotNullParameter((Object)controllers, (String)"controllers");
        controllers.add(new AnimationController("controller", 5, arg_0 -> CustomPetEntity.registerControllers$lambda$0(this, arg_0)).setSoundKeyframeHandler(arg_0 -> CustomPetEntity.registerControllers$lambda$1(this, arg_0)));
        controllers.add(new AnimationController("life", 5, arg_0 -> CustomPetEntity.registerControllers$lambda$2(this, arg_0)).additiveAnimations());
    }

    private final PlayState predicate(AnimationTest<CustomPetEntity> event) {
        RawAnimation rawAnimation;
        if (this.isNightmareBb) {
            return PlayState.STOP;
        }
        AnimationController animationController = event.controller();
        Intrinsics.checkNotNullExpressionValue((Object)animationController, (String)"controller(...)");
        AnimationController controller = animationController;
        controller.setAnimationSpeed(this.currentAnimationSpeed);
        RawAnimation animation = null;
        if (this.isUfo) {
            RawAnimation rawAnimation2;
            controller.setAnimationSpeed(1.0);
            if (this.isUfoGrounded) {
                rawAnimation2 = UFO_LAND;
            } else if (this.ufoTakeoffTicks > 0) {
                rawAnimation2 = UFO_TAKEOFF;
            } else if (this.ufoSpinTicks > 0) {
                rawAnimation2 = UFO_SPIN;
            } else if (this.ufoBoost) {
                rawAnimation2 = UFO_BOOST;
            } else if (this.ufoBeamMode == UfoBeamMode.ABDUCT) {
                rawAnimation2 = UFO_ABDUCT;
            } else if (this.ufoBeamMode == UfoBeamMode.SCAN) {
                rawAnimation2 = UFO_SCAN;
            } else if (this.ufoBeamMode == UfoBeamMode.BEAM) {
                rawAnimation2 = UFO_BEAM;
            } else if (this.isPetMoving) {
                controller.setAnimationSpeed(RangesKt.coerceIn((double)this.currentAnimationSpeed, (double)1.0, (double)2.2));
                rawAnimation2 = UFO_FLY;
            } else {
                rawAnimation2 = UFO_IDLE;
            }
            animation = rawAnimation2;
            controller.setAnimation(animation);
            return PlayState.CONTINUE;
        }
        if (this.isGoat) {
            RawAnimation rawAnimation3;
            GoatAction action = this.goatAction;
            if (action != null) {
                controller.setAnimationSpeed(1.0);
                controller.setAnimation(action.getAnimation());
                return PlayState.CONTINUE;
            }
            if (this.isTouchingWater()) {
                rawAnimation3 = GOAT_SWIM;
            } else if (this.isAirborneMode) {
                rawAnimation3 = GOAT_FLY;
            } else if (this.goatInJumpState()) {
                controller.setAnimationSpeed(1.0);
                rawAnimation3 = GoatAction.JUMP.getAnimation();
            } else if (this.goatSleeping) {
                controller.setAnimationSpeed(1.0);
                rawAnimation3 = GOAT_SLEEP;
            } else if (this.goatSitting && !this.isPetMoving) {
                controller.setAnimationSpeed(1.0);
                rawAnimation3 = GOAT_SIT;
            } else if (!this.isPetMoving) {
                controller.setAnimationSpeed(1.0);
                rawAnimation3 = this.age % 340 > 260 ? GOAT_GRAZE : GOAT_IDLE;
            } else {
                rawAnimation3 = this.currentAnimationSpeed >= 1.35 ? GOAT_RUN : GOAT_WALK;
            }
            RawAnimation goatAnimation = rawAnimation3;
            controller.setAnimation(goatAnimation);
            return PlayState.CONTINUE;
        }
        if (this.isChekushka) {
            RawAnimation rawAnimation4;
            ChekushkaAction action = this.chekushkaAction;
            if (action != null && action != ChekushkaAction.SHOOT) {
                controller.setAnimationSpeed(1.0);
                controller.setAnimation(action.getAnimation());
                return PlayState.CONTINUE;
            }
            if (this.isChekushkaCombat) {
                controller.setAnimationSpeed(1.0);
                controller.setAnimation(CHEKUSHKA_SHOOT_LOOP);
                return PlayState.CONTINUE;
            }
            if (action != null) {
                controller.setAnimationSpeed(1.0);
                controller.setAnimation(action.getAnimation());
                return PlayState.CONTINUE;
            }
            if (this.isTouchingWater()) {
                rawAnimation4 = CHEKUSHKA_SWIM;
            } else if (this.isAirborneMode) {
                rawAnimation4 = CHEKUSHKA_FLY;
            } else if (this.chekushkaInJumpState()) {
                controller.setAnimationSpeed(1.0);
                rawAnimation4 = CHEKUSHKA_JUMP;
            } else if (!this.isPetMoving) {
                boolean boredWindow;
                controller.setAnimationSpeed(1.0);
                boolean bl = boredWindow = this.age % 420 > 320;
                rawAnimation4 = this.chekushkaBored || boredWindow ? CHEKUSHKA_BORED : CHEKUSHKA_IDLE;
            } else {
                rawAnimation4 = this.currentAnimationSpeed >= 1.35 ? CHEKUSHKA_RUN : CHEKUSHKA_WALK;
            }
            RawAnimation chekushkaAnimation = rawAnimation4;
            controller.setAnimation(chekushkaAnimation);
            return PlayState.CONTINUE;
        }
        if (this.isOwl) {
            RawAnimation owlAnimation = this.isAirborneMode ? OWL_FLY : (!this.isPetMoving ? OWL_IDLE : (this.currentAnimationSpeed >= 1.4 ? OWL_RUN : OWL_WALK));
            controller.setAnimation(owlAnimation);
            return PlayState.CONTINUE;
        }
        if (this.petVariant.isRobot()) {
            rawAnimation = this.isPetMoving || this.isAirborneMode ? ROBOT_FLY : ROBOT_IDLE;
        } else if (this.isAirborneMode) {
            rawAnimation = AIR_IDLE;
        } else {
            switch (WhenMappings.$EnumSwitchMapping$3[this.petVariant.ordinal()]) {
                case 1: {
                    if (this.isUsingUmbrella) {
                        if (this.isPetMoving) {
                            rawAnimation = FISHERMAN_WALK;
                            break;
                        }
                        rawAnimation = FISHERMAN_IDLE;
                        break;
                    }
                    if (this.isPetMoving) {
                        rawAnimation = AIR_WALK;
                        break;
                    }
                    rawAnimation = AIR_IDLE;
                    break;
                }
                case 2: 
                case 3: {
                    if (this.isUsingUmbrella) {
                        if (this.isPetMoving) {
                            rawAnimation = HAT_WALK;
                            break;
                        }
                        rawAnimation = HAT_IDLE;
                        break;
                    }
                    if (this.isPetMoving) {
                        rawAnimation = WALK;
                        break;
                    }
                    rawAnimation = IDLE;
                    break;
                }
                case 4: 
                case 5: 
                case 6: {
                    if (this.isUsingUmbrella) {
                        if (this.isPetMoving) {
                            rawAnimation = RAIN_WALK;
                            break;
                        }
                        rawAnimation = RAIN_IDLE;
                        break;
                    }
                    if (this.isPetMoving) {
                        rawAnimation = WALK;
                        break;
                    }
                    rawAnimation = IDLE;
                    break;
                }
                case 7: {
                    if (this.isPetMoving) {
                        rawAnimation = ROBOT_FLY;
                        break;
                    }
                    rawAnimation = ROBOT_IDLE;
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
        animation = rawAnimation;
        controller.setAnimation(animation);
        return PlayState.CONTINUE;
    }

    private final PlayState lifePredicate(AnimationTest<CustomPetEntity> event) {
        RawAnimation rawAnimation;
        if (this.isChekushka && this.chekushkaLifeOverlayActive()) {
            rawAnimation = CHEKUSHKA_LIFE;
        } else if (this.isGoat && this.goatLifeOverlayActive()) {
            rawAnimation = GOAT_LIFE;
        } else {
            return PlayState.STOP;
        }
        RawAnimation overlay = rawAnimation;
        AnimationController animationController = event.controller();
        Intrinsics.checkNotNullExpressionValue((Object)animationController, (String)"controller(...)");
        AnimationController controller = animationController;
        controller.setAnimationSpeed(1.0);
        controller.setAnimation(overlay);
        return PlayState.CONTINUE;
    }

    private final boolean goatLifeOverlayActive() {
        if (this.goatSleeping) {
            return false;
        }
        GoatAction goatAction = this.goatAction;
        if (goatAction == null) {
            return true;
        }
        GoatAction action = goatAction;
        return switch (WhenMappings.$EnumSwitchMapping$1[action.ordinal()]) {
            case 1, 2 -> false;
            case 3, 4, 5, 6, 7 -> true;
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    private final boolean chekushkaLifeOverlayActive() {
        ChekushkaAction chekushkaAction = this.chekushkaAction;
        if (chekushkaAction == null) {
            return !this.chekushkaInJumpState();
        }
        ChekushkaAction action = chekushkaAction;
        return action == ChekushkaAction.LOVE || action == ChekushkaAction.SIP;
    }

    public float getLerpedYaw(float partialTick) {
        return MathHelper.lerpAngleDegrees((float)partialTick, (float)this.lastYaw, (float)this.getYaw());
    }

    @NotNull
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private static final PlayState registerControllers$lambda$0(CustomPetEntity this$0, AnimationTest event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        return this$0.predicate((AnimationTest<CustomPetEntity>)event);
    }

    private static final void registerControllers$lambda$1(CustomPetEntity this$0, KeyFrameEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        this$0.handleGoatSoundCue(((SoundKeyframeData)event.keyframeData()).getSound());
    }

    private static final PlayState registerControllers$lambda$2(CustomPetEntity this$0, AnimationTest event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        return this$0.lifePredicate((AnimationTest<CustomPetEntity>)event);
    }

    static {
        RawAnimation rawAnimation = RawAnimation.begin().thenPlay("idle");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation, (String)"thenPlay(...)");
        IDLE = rawAnimation;
        RawAnimation rawAnimation2 = RawAnimation.begin().thenPlay("walk");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation2, (String)"thenPlay(...)");
        WALK = rawAnimation2;
        RawAnimation rawAnimation3 = RawAnimation.begin().thenPlay("idle_holding_1");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation3, (String)"thenPlay(...)");
        RAIN_IDLE = rawAnimation3;
        RawAnimation rawAnimation4 = RawAnimation.begin().thenPlay("walk_holding_1");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation4, (String)"thenPlay(...)");
        RAIN_WALK = rawAnimation4;
        RawAnimation rawAnimation5 = RawAnimation.begin().thenPlay("idle_holding_hat");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation5, (String)"thenPlay(...)");
        HAT_IDLE = rawAnimation5;
        RawAnimation rawAnimation6 = RawAnimation.begin().thenPlay("walk_holding_hat");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation6, (String)"thenPlay(...)");
        HAT_WALK = rawAnimation6;
        RawAnimation rawAnimation7 = RawAnimation.begin().thenPlay("idle_holding_fisherman");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation7, (String)"thenPlay(...)");
        FISHERMAN_IDLE = rawAnimation7;
        RawAnimation rawAnimation8 = RawAnimation.begin().thenPlay("walk_holding_fisherman");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation8, (String)"thenPlay(...)");
        FISHERMAN_WALK = rawAnimation8;
        RawAnimation rawAnimation9 = RawAnimation.begin().thenPlay("idle_holding_2");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation9, (String)"thenPlay(...)");
        AIR_IDLE = rawAnimation9;
        RawAnimation rawAnimation10 = RawAnimation.begin().thenPlay("walk_holding_2");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation10, (String)"thenPlay(...)");
        AIR_WALK = rawAnimation10;
        RawAnimation rawAnimation11 = RawAnimation.begin().thenPlay("robot_idle");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation11, (String)"thenPlay(...)");
        ROBOT_IDLE = rawAnimation11;
        RawAnimation rawAnimation12 = RawAnimation.begin().thenPlay("robot_fly");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation12, (String)"thenPlay(...)");
        ROBOT_FLY = rawAnimation12;
        RawAnimation rawAnimation13 = RawAnimation.begin().thenPlay("animation.owl_jump_rope.skip_idle");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation13, (String)"thenPlay(...)");
        OWL_IDLE = rawAnimation13;
        RawAnimation rawAnimation14 = RawAnimation.begin().thenPlay("animation.owl_jump_rope.walk_skip");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation14, (String)"thenPlay(...)");
        OWL_WALK = rawAnimation14;
        RawAnimation rawAnimation15 = RawAnimation.begin().thenPlay("animation.owl_jump_rope.run_skip");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation15, (String)"thenPlay(...)");
        OWL_RUN = rawAnimation15;
        RawAnimation rawAnimation16 = RawAnimation.begin().thenPlay("animation.owl_jump_rope.fly");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation16, (String)"thenPlay(...)");
        OWL_FLY = rawAnimation16;
        RawAnimation rawAnimation17 = RawAnimation.begin().thenPlayAndHold("animation.chekushka_pet.jump");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation17, (String)"thenPlayAndHold(...)");
        CHEKUSHKA_JUMP = rawAnimation17;
        RawAnimation rawAnimation18 = RawAnimation.begin().thenPlay("animation.chekushka_pet.idle");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation18, (String)"thenPlay(...)");
        CHEKUSHKA_IDLE = rawAnimation18;
        RawAnimation rawAnimation19 = RawAnimation.begin().thenPlay("animation.chekushka_pet.walk");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation19, (String)"thenPlay(...)");
        CHEKUSHKA_WALK = rawAnimation19;
        RawAnimation rawAnimation20 = RawAnimation.begin().thenPlay("animation.chekushka_pet.run");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation20, (String)"thenPlay(...)");
        CHEKUSHKA_RUN = rawAnimation20;
        RawAnimation rawAnimation21 = RawAnimation.begin().thenPlay("animation.chekushka_pet.swim");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation21, (String)"thenPlay(...)");
        CHEKUSHKA_SWIM = rawAnimation21;
        RawAnimation rawAnimation22 = RawAnimation.begin().thenPlay("animation.chekushka_pet.fly");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation22, (String)"thenPlay(...)");
        CHEKUSHKA_FLY = rawAnimation22;
        RawAnimation rawAnimation23 = RawAnimation.begin().thenPlay("animation.chekushka_pet.bored");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation23, (String)"thenPlay(...)");
        CHEKUSHKA_BORED = rawAnimation23;
        RawAnimation rawAnimation24 = RawAnimation.begin().thenPlay("animation.chekushka_pet.pet_life");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation24, (String)"thenPlay(...)");
        CHEKUSHKA_LIFE = rawAnimation24;
        RawAnimation rawAnimation25 = RawAnimation.begin().thenLoop("animation.chekushka_pet.shoot");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation25, (String)"thenLoop(...)");
        CHEKUSHKA_SHOOT_LOOP = rawAnimation25;
        RawAnimation rawAnimation26 = RawAnimation.begin().thenPlay("animation.ufo_pet.idle");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation26, (String)"thenPlay(...)");
        UFO_IDLE = rawAnimation26;
        RawAnimation rawAnimation27 = RawAnimation.begin().thenPlay("animation.ufo_pet.fly");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation27, (String)"thenPlay(...)");
        UFO_FLY = rawAnimation27;
        RawAnimation rawAnimation28 = RawAnimation.begin().thenPlay("animation.ufo_pet.beam");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation28, (String)"thenPlay(...)");
        UFO_BEAM = rawAnimation28;
        RawAnimation rawAnimation29 = RawAnimation.begin().thenPlay("animation.ufo_pet.abduct");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation29, (String)"thenPlay(...)");
        UFO_ABDUCT = rawAnimation29;
        RawAnimation rawAnimation30 = RawAnimation.begin().thenPlay("animation.ufo_pet.scan");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation30, (String)"thenPlay(...)");
        UFO_SCAN = rawAnimation30;
        RawAnimation rawAnimation31 = RawAnimation.begin().thenPlay("animation.ufo_pet.boost");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation31, (String)"thenPlay(...)");
        UFO_BOOST = rawAnimation31;
        RawAnimation rawAnimation32 = RawAnimation.begin().thenPlayAndHold("animation.ufo_pet.spin");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation32, (String)"thenPlayAndHold(...)");
        UFO_SPIN = rawAnimation32;
        RawAnimation rawAnimation33 = RawAnimation.begin().thenPlayAndHold("animation.ufo_pet.land");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation33, (String)"thenPlayAndHold(...)");
        UFO_LAND = rawAnimation33;
        RawAnimation rawAnimation34 = RawAnimation.begin().thenPlayAndHold("animation.ufo_pet.takeoff");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation34, (String)"thenPlayAndHold(...)");
        UFO_TAKEOFF = rawAnimation34;
        RawAnimation rawAnimation35 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.idle");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation35, (String)"thenPlay(...)");
        GOAT_IDLE = rawAnimation35;
        RawAnimation rawAnimation36 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.walk");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation36, (String)"thenPlay(...)");
        GOAT_WALK = rawAnimation36;
        RawAnimation rawAnimation37 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.run");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation37, (String)"thenPlay(...)");
        GOAT_RUN = rawAnimation37;
        RawAnimation rawAnimation38 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.swim");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation38, (String)"thenPlay(...)");
        GOAT_SWIM = rawAnimation38;
        RawAnimation rawAnimation39 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.fly");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation39, (String)"thenPlay(...)");
        GOAT_FLY = rawAnimation39;
        RawAnimation rawAnimation40 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.sit");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation40, (String)"thenPlay(...)");
        GOAT_SIT = rawAnimation40;
        RawAnimation rawAnimation41 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.sleep");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation41, (String)"thenPlay(...)");
        GOAT_SLEEP = rawAnimation41;
        RawAnimation rawAnimation42 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.graze");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation42, (String)"thenPlay(...)");
        GOAT_GRAZE = rawAnimation42;
        RawAnimation rawAnimation43 = RawAnimation.begin().thenPlay("animation.bigear_goat_pet.pet_life");
        Intrinsics.checkNotNullExpressionValue((Object)rawAnimation43, (String)"thenPlay(...)");
        GOAT_LIFE = rawAnimation43;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$ChekushkaAction;", "", "", "name", "", "seconds", "<init>", "(Ljava/lang/String;ILjava/lang/String;D)V", "Lsoftware/bernie/geckolib/animation/RawAnimation;", "animation", "Lsoftware/bernie/geckolib/animation/RawAnimation;", "getAnimation", "()Lsoftware/bernie/geckolib/animation/RawAnimation;", "", "durationTicks", "I", "getDurationTicks", "()I", "LOVE", "SHOOT", "CELEBRATE", "HURT", "SIP", "rtx.kimiko:kimiko"})
    public static enum ChekushkaAction {
        LOVE("animation.chekushka_pet.love", 3.0),
        SHOOT("animation.chekushka_pet.shoot", 1.75),
        CELEBRATE("animation.chekushka_pet.celebrate", 3.4),
        HURT("animation.chekushka_pet.hurt", 0.55),
        SIP("animation.chekushka_pet.sip", 3.6);
@NotNull
        private final RawAnimation animation;
        private final int durationTicks;
        
        
        
        
        
        
        private ChekushkaAction(String name, double seconds) {
            RawAnimation rawAnimation = RawAnimation.begin().thenPlay(name);
            Intrinsics.checkNotNullExpressionValue((Object)rawAnimation, (String)"thenPlay(...)");
            this.animation = rawAnimation;
            this.durationTicks = (int)Math.ceil(seconds * 20.0) + 3;
        }

        @NotNull
        public final RawAnimation getAnimation() {
            return this.animation;
        }

        public final int getDurationTicks() {
            return this.durationTicks;
        }

        

        

        @NotNull
        public static EnumEntries<ChekushkaAction> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u001d\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\bR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0016R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001cR\u0014\u0010 \u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010\u001cR\u0014\u0010!\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\u001cR\u0014\u0010\"\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001cR\u0014\u0010#\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\u001cR\u0014\u0010$\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010\u001cR\u0014\u0010%\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010\u001cR\u0014\u0010&\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010\u001cR\u0014\u0010'\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010\u001cR\u0014\u0010(\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010\u001cR\u0014\u0010)\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010\u001cR\u0014\u0010*\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010\u001cR\u0014\u0010+\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010\u001cR\u0014\u0010,\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010\u0014R\u0014\u0010-\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010\u0014R\u0014\u0010.\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010\u001cR\u0014\u0010/\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010\u001cR\u0014\u00100\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010\u001cR\u0014\u00101\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u0010\u001cR\u0014\u00102\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u0010\u001cR\u0014\u00103\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u0010\u001cR\u0014\u00104\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u0010\u001cR\u0014\u00105\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u0010\u001cR\u0014\u00106\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u0010\u001cR\u0014\u00107\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010\u0014R\u0014\u00109\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010;\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010:R\u0014\u0010<\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010:R\u0014\u0010=\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010\u001cR\u0014\u0010>\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010\u001cR\u0014\u0010?\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010\u001cR\u0014\u0010@\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010\u001cR\u0014\u0010A\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010\u001cR\u0014\u0010B\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010\u001cR\u0014\u0010C\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010\u001cR\u0014\u0010D\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010\u001cR\u0014\u0010E\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010\u001cR\u0014\u0010F\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010:R\u0014\u0010G\u001a\u0002088\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010:R\u0014\u0010H\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010\u000fR\u0014\u0010I\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010\u000fR\u0014\u0010J\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010\u0014R\u0014\u0010K\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010\u0014R\u0014\u0010L\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010\u001cR\u0014\u0010M\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010\u001cR\u0014\u0010N\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010\u001cR\u0014\u0010O\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010\u001cR\u0014\u0010P\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010\u001cR\u0014\u0010Q\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010\u001cR\u0014\u0010R\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010\u001cR\u0014\u0010S\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010\u001cR\u0014\u0010T\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010\u001c\u00a8\u0006U"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity.Companion;", "", "<init>", "()V", "", "resolveSounds", "Lnet/minecraft/SoundEvent;", "ambientSound", "()Lnet/minecraft/SoundEvent;", "stepSound", "Ljava/util/concurrent/atomic/AtomicInteger;", "NEXT_ENTITY_ID", "Ljava/util/concurrent/atomic/AtomicInteger;", "", "GROUND_FULL_SPEED_ANGLE", "F", "GROUND_MIN_SPEED_ANGLE", "AIR_START_MOVE_ANGLE", "", "MOVEMENT_ANIMATION_THRESHOLD_SQR", "D", "MOVEMENT_VERTICAL_THRESHOLD", "Lnet/minecraft/SoundEvent;", "", "soundsResolved", "Z", "Lsoftware/bernie/geckolib/animation/RawAnimation;", "IDLE", "Lsoftware/bernie/geckolib/animation/RawAnimation;", "WALK", "RAIN_IDLE", "RAIN_WALK", "HAT_IDLE", "HAT_WALK", "FISHERMAN_IDLE", "FISHERMAN_WALK", "AIR_IDLE", "AIR_WALK", "ROBOT_IDLE", "ROBOT_FLY", "OWL_IDLE", "OWL_WALK", "OWL_RUN", "OWL_FLY", "OWL_RUN_ANIMATION_SPEED", "CHEKUSHKA_RUN_ANIMATION_SPEED", "CHEKUSHKA_JUMP", "CHEKUSHKA_IDLE", "CHEKUSHKA_WALK", "CHEKUSHKA_RUN", "CHEKUSHKA_SWIM", "CHEKUSHKA_FLY", "CHEKUSHKA_BORED", "CHEKUSHKA_LIFE", "CHEKUSHKA_SHOOT_LOOP", "GOAT_RUN_ANIMATION_SPEED", "", "GOAT_SIT_IDLE_TICKS", "I", "GOAT_SIT_DURATION_TICKS", "GOAT_RUN_STOP_MIN_TICKS", "UFO_IDLE", "UFO_FLY", "UFO_BEAM", "UFO_ABDUCT", "UFO_SCAN", "UFO_BOOST", "UFO_SPIN", "UFO_LAND", "UFO_TAKEOFF", "UFO_SPIN_TICKS", "UFO_TAKEOFF_TICKS", "UFO_BEAM_EASE", "UFO_LAND_EASE", "UFO_GROUND_EASE", "UFO_GROUND_MAX", "GOAT_IDLE", "GOAT_WALK", "GOAT_RUN", "GOAT_SWIM", "GOAT_FLY", "GOAT_SIT", "GOAT_SLEEP", "GOAT_GRAZE", "GOAT_LIFE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final void resolveSounds() {
            if (soundsResolved) {
                return;
            }
            soundsResolved = true;
            try {
                ambientSound = SoundEvent.of((Identifier)Identifier.of((String)"kimiko", (String)"entity.ribbit.ambient"));
                stepSound = SoundEvent.of((Identifier)Identifier.of((String)"kimiko", (String)"entity.ribbit.step"));
            }
            catch (Throwable throwable) {
                ambientSound = null;
                stepSound = null;
            }
        }

        private final SoundEvent ambientSound() {
            this.resolveSounds();
            return ambientSound;
        }

        private final SoundEvent stepSound() {
            this.resolveSounds();
            return stepSound;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$GoatAction;", "", "", "name", "", "seconds", "<init>", "(Ljava/lang/String;ILjava/lang/String;D)V", "Lsoftware/bernie/geckolib/animation/RawAnimation;", "animation", "Lsoftware/bernie/geckolib/animation/RawAnimation;", "getAnimation", "()Lsoftware/bernie/geckolib/animation/RawAnimation;", "", "durationTicks", "I", "getDurationTicks", "()I", "JUMP", "RUN_STOP", "HEADBUTT", "SCREAM", "LOVE", "HURT", "CELEBRATE", "rtx.kimiko:kimiko"})
    public static enum GoatAction {
        JUMP("animation.bigear_goat_pet.jump", 1.0),
        RUN_STOP("animation.bigear_goat_pet.run_stop", 0.8),
        HEADBUTT("animation.bigear_goat_pet.headbutt", 0.9),
        SCREAM("animation.bigear_goat_pet.scream", 1.8),
        LOVE("animation.bigear_goat_pet.love", 2.7),
        HURT("animation.bigear_goat_pet.hurt", 0.55),
        CELEBRATE("animation.bigear_goat_pet.celebrate", 3.2);
@NotNull
        private final RawAnimation animation;
        private final int durationTicks;
        
        
        
        
        
        
        
        
        private GoatAction(String name, double seconds) {
            RawAnimation rawAnimation = RawAnimation.begin().thenPlayAndHold(name);
            Intrinsics.checkNotNullExpressionValue((Object)rawAnimation, (String)"thenPlayAndHold(...)");
            this.animation = rawAnimation;
            this.durationTicks = (int)Math.ceil(seconds * 20.0) + 3;
        }

        @NotNull
        public final RawAnimation getAnimation() {
            return this.animation;
        }

        public final int getDurationTicks() {
            return this.durationTicks;
        }

        

        

        @NotNull
        public static EnumEntries<GoatAction> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity$UfoBeamMode;", "", "<init>", "(Ljava/lang/String;I)V", "OFF", "BEAM", "ABDUCT", "SCAN", "rtx.kimiko:kimiko"})
    public static enum UfoBeamMode {
        OFF,
        BEAM,
        ABDUCT,
        SCAN;
@NotNull
        public static EnumEntries<UfoBeamMode> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] nArray = new int[UfoBeamMode.values().length];
            try {
                nArray[UfoBeamMode.ABDUCT.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[UfoBeamMode.SCAN.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
            nArray = new int[GoatAction.values().length];
            try {
                nArray[GoatAction.CELEBRATE.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[GoatAction.HURT.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[GoatAction.SCREAM.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[GoatAction.LOVE.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[GoatAction.HEADBUTT.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[GoatAction.JUMP.ordinal()] = 6;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[GoatAction.RUN_STOP.ordinal()] = 7;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$1 = nArray;
            nArray = new int[ChekushkaAction.values().length];
            try {
                nArray[ChekushkaAction.SHOOT.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ChekushkaAction.CELEBRATE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ChekushkaAction.LOVE.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ChekushkaAction.SIP.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ChekushkaAction.HURT.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$2 = nArray;
            nArray = new int[CustomPetVariant.values().length];
            try {
                nArray[CustomPetVariant.FISHERMAN.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[CustomPetVariant.GARDENER.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[CustomPetVariant.SORCERER.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[CustomPetVariant.DEFAULT.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[CustomPetVariant.NITWIT.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[CustomPetVariant.MERCHANT.ordinal()] = 6;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[CustomPetVariant.ROBOT.ordinal()] = 7;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$3 = nArray;
        }
    }
}

