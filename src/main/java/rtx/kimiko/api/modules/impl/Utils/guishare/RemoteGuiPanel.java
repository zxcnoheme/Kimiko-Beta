/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareChatState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareCloseState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiSharePopupRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareRemoteState;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiPanelRenderer;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteTheme;
import rtx.kimiko.api.ui.window.GuiShatterAnimation;
import rtx.kimiko.utils.animations.Animation;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\u0018\u0000 \u00b0\u00012\u00020\u0001:\u0002\u00b0\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\t\u00a2\u0006\u0004\b\f\u0010\u000bJ\r\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0005J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001b\u0010\u0016J\r\u0010\u001c\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001c\u0010\u0016J\r\u0010\u001d\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001d\u0010\u0016J\r\u0010\u001e\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001e\u0010\u0016J\r\u0010 \u001a\u00020\u001f\u00a2\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0014\u00a2\u0006\u0004\b\"\u0010\u0016J\u001f\u0010&\u001a\u00020\r2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b(\u0010\u0005J\u0017\u0010+\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b-\u0010\u0005J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010.J\r\u00100\u001a\u00020/\u00a2\u0006\u0004\b0\u00101J\u0013\u00104\u001a\b\u0012\u0004\u0012\u00020302\u00a2\u0006\u0004\b4\u00105J\r\u00106\u001a\u00020\t\u00a2\u0006\u0004\b6\u0010\u000bJ\r\u00107\u001a\u00020\u0014\u00a2\u0006\u0004\b7\u0010\u0016J\r\u00108\u001a\u00020\u0014\u00a2\u0006\u0004\b8\u0010\u0016J\r\u00109\u001a\u00020\u0014\u00a2\u0006\u0004\b9\u0010\u0016J\r\u0010:\u001a\u00020\u0014\u00a2\u0006\u0004\b:\u0010\u0016J\r\u0010;\u001a\u00020\u0014\u00a2\u0006\u0004\b;\u0010\u0016J\r\u0010<\u001a\u00020\u0014\u00a2\u0006\u0004\b<\u0010\u0016J\r\u0010=\u001a\u00020\u0014\u00a2\u0006\u0004\b=\u0010\u0016J\r\u0010>\u001a\u00020\u0014\u00a2\u0006\u0004\b>\u0010\u0016J\r\u0010?\u001a\u00020\u0014\u00a2\u0006\u0004\b?\u0010\u0016J\r\u0010@\u001a\u00020\u0014\u00a2\u0006\u0004\b@\u0010\u0016J\r\u0010A\u001a\u00020\u0014\u00a2\u0006\u0004\bA\u0010\u0016J\r\u0010B\u001a\u00020\u0014\u00a2\u0006\u0004\bB\u0010\u0016J\u000f\u0010D\u001a\u0004\u0018\u00010C\u00a2\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u0004\u0018\u00010C\u00a2\u0006\u0004\bF\u0010EJ\r\u0010G\u001a\u00020\u0014\u00a2\u0006\u0004\bG\u0010\u0016J\u0015\u0010I\u001a\u00020\u00142\u0006\u0010H\u001a\u00020C\u00a2\u0006\u0004\bI\u0010JJ\r\u0010K\u001a\u00020\u0014\u00a2\u0006\u0004\bK\u0010\u0016J\r\u0010L\u001a\u00020\u0014\u00a2\u0006\u0004\bL\u0010\u0016J\r\u0010M\u001a\u00020\u0014\u00a2\u0006\u0004\bM\u0010\u0016J\r\u0010N\u001a\u00020\u0014\u00a2\u0006\u0004\bN\u0010\u0016J\r\u0010O\u001a\u00020\u0014\u00a2\u0006\u0004\bO\u0010\u0016J\r\u0010P\u001a\u00020\u0014\u00a2\u0006\u0004\bP\u0010\u0016J\u0015\u0010S\u001a\u00020\u00142\u0006\u0010R\u001a\u00020Q\u00a2\u0006\u0004\bS\u0010TJ\r\u0010U\u001a\u000203\u00a2\u0006\u0004\bU\u0010VJ\r\u0010W\u001a\u00020\u0014\u00a2\u0006\u0004\bW\u0010\u0016J\r\u0010X\u001a\u00020\u0014\u00a2\u0006\u0004\bX\u0010\u0016J\r\u0010Y\u001a\u00020\t\u00a2\u0006\u0004\bY\u0010\u000bJ\r\u0010Z\u001a\u00020\u0014\u00a2\u0006\u0004\bZ\u0010\u0016J\r\u0010[\u001a\u00020\u0014\u00a2\u0006\u0004\b[\u0010\u0016J\r\u0010\\\u001a\u00020Q\u00a2\u0006\u0004\b\\\u0010]J\r\u0010^\u001a\u00020\u0014\u00a2\u0006\u0004\b^\u0010\u0016J\r\u0010_\u001a\u00020\u0014\u00a2\u0006\u0004\b_\u0010\u0016J\r\u0010`\u001a\u00020\u0014\u00a2\u0006\u0004\b`\u0010\u0016J\u0013\u0010c\u001a\b\u0012\u0004\u0012\u00020b0a\u00a2\u0006\u0004\bc\u0010dJ\u0015\u0010f\u001a\u00020\u00142\u0006\u0010e\u001a\u00020Q\u00a2\u0006\u0004\bf\u0010TJ\r\u0010g\u001a\u00020\r\u00a2\u0006\u0004\bg\u0010\u000fJ\r\u0010h\u001a\u00020\r\u00a2\u0006\u0004\bh\u0010\u000fJ\u001f\u0010k\u001a\u00020\r2\u0006\u0010i\u001a\u00020\u00022\u0006\u0010j\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bk\u0010lJ\u0017\u0010m\u001a\u00020\r2\u0006\u0010i\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\bm\u0010\u0005J\u0019\u0010o\u001a\u00020\r2\b\u0010n\u001a\u0004\u0018\u00010CH\u0002\u00a2\u0006\u0004\bo\u0010pJ!\u0010r\u001a\u00020\r2\b\u0010q\u001a\u0004\u0018\u00010C2\u0006\u0010i\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\br\u0010sJ\u000f\u0010t\u001a\u00020\rH\u0002\u00a2\u0006\u0004\bt\u0010\u000fJ\u0017\u0010v\u001a\u00020u2\u0006\u0010H\u001a\u00020CH\u0002\u00a2\u0006\u0004\bv\u0010wJ\u0017\u0010y\u001a\u00020\u00142\u0006\u0010x\u001a\u00020#H\u0002\u00a2\u0006\u0004\by\u0010zR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010{R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010|R\u001c\u00104\u001a\b\u0012\u0004\u0012\u000203028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u0010}R\u0016\u0010~\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010\u007fR\u0018\u0010\u0080\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010\u007fR\u0019\u0010\u0081\u0001\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0019\u0010\u0083\u0001\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0082\u0001R\u0017\u0010:\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b:\u0010\u0084\u0001R\u0017\u0010;\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b;\u0010\u0084\u0001R\u0017\u0010<\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b<\u0010\u0084\u0001R\u0017\u0010=\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b=\u0010\u0084\u0001R\u0017\u0010>\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b>\u0010\u0084\u0001R\u0017\u0010@\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b@\u0010\u0084\u0001R\u0017\u0010?\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b?\u0010\u0084\u0001R\u0017\u0010A\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bA\u0010\u0084\u0001R\u0017\u0010B\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bB\u0010\u0084\u0001R\u0019\u0010\u0085\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0084\u0001R\u0019\u0010\u0086\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0084\u0001R\u0018\u0010\u0087\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010\u007fR\u0019\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bD\u0010\u0088\u0001R\u0019\u0010F\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bF\u0010\u0088\u0001R\u0018\u0010\u0089\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010\u007fR\u0017\u0010G\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bG\u0010\u0084\u0001R$\u0010\u008b\u0001\u001a\u000f\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020u0\u008a\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0017\u0010\u008d\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R\u0017\u0010\u008f\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u008e\u0001R\u0017\u0010\u0090\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u008e\u0001R\u0017\u0010\u0091\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u008e\u0001R\u0017\u0010N\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bN\u0010\u0084\u0001R\u0017\u0010O\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bO\u0010\u0084\u0001R\u0015\u0010S\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\bS\u0010\u0092\u0001R5\u0010\u0095\u0001\u001a \u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020#0\u0093\u0001j\u000f\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020#`\u0094\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0019\u0010\u0097\u0001\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0082\u0001R\u0018\u0010\u0098\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0098\u0001\u0010\u007fR\u0017\u0010\u0099\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u008e\u0001R\u0017\u0010\u009a\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u008e\u0001R\u0017\u0010\u009b\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u008e\u0001R\u0017\u0010\u009c\u0001\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u008e\u0001R\u0017\u0010\\\u001a\u00020Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\\\u0010\u009d\u0001R\u0017\u0010Z\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bZ\u0010\u0084\u0001R\u0017\u0010U\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bU\u0010\u009e\u0001R\u0017\u0010_\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b_\u0010\u0084\u0001R\u0017\u0010`\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b`\u0010\u0084\u0001R\u001d\u0010c\u001a\b\u0012\u0004\u0012\u00020b0a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bc\u0010\u009f\u0001R\u0017\u0010\u00a0\u0001\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u00a1\u0001R\u0018\u0010\u00a2\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a2\u0001\u0010\u007fR\u0019\u0010\u00a3\u0001\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u0082\u0001R\u0018\u0010\u00a4\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a4\u0001\u0010\u007fR\u0019\u0010\u00a5\u0001\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u0082\u0001R\u0018\u0010\u00a6\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a6\u0001\u0010\u007fR\u0018\u0010\u00a7\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a7\u0001\u0010\u007fR\u0019\u0010\u00a8\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u00a9\u0001R\u0019\u0010\u00aa\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00aa\u0001\u0010\u0084\u0001R\u0019\u0010«\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u0084\u0001R\u0019\u0010\u00ac\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u0084\u0001R\u0019\u0010\u00ad\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u0084\u0001R\u0017\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0012\u0010\u00a9\u0001R\u0017\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0015\u0010\u0084\u0001R\u0017\u0010\u0017\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0017\u0010\u0084\u0001R\u0013\u0010\u00ae\u0001\u001a\u00020\t8F\u00a2\u0006\u0007\u001a\u0005\b\u00ae\u0001\u0010\u000bR\u0013\u0010\u00af\u0001\u001a\u00020\t8F\u00a2\u0006\u0007\u001a\u0005\b\u00af\u0001\u0010\u000bR\u0017\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b \u0010\u0092\u0001\u00a8\u0006\u00b1\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;", "", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "state", "<init>", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;)V", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "shatterState", "()Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "", "shatterActive", "()Z", "closeActive", "", "markClosed", "()V", "acceptCloseState", "Lnet/minecraft/Vec3d;", "resolvedAnchor", "()Lnet/minecraft/Vec3d;", "", "resolvedYaw", "()F", "resolvedPitch", "liveAnchor", "resolveAnchor", "(Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "closeOpenness", "displayShatterProgress", "closeAnimationScale", "closeAlpha", "", "shatterRect", "()[F", "shatterProgressForRender", "", "seed", "rect", "beginShatterWith", "(J[F)V", "updateShatter", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "close", "resolveShatterRect", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;)[F", "applyState", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteTheme;", "theme", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteTheme;", "", "", "enabledSet", "()Ljava/util/Set;", "isOpen", "contentAlpha", "scale", "backdropScale", "smoothScroll", "smoothMouseX", "smoothMouseY", "smoothPopupScroll", "smoothThemesScroll", "smoothConfigsScroll", "smoothEventsScroll", "smoothYaw", "smoothPitch", "Lrtx/kimiko/api/modules/Category;", "contentCategory", "()Lrtx/kimiko/api/modules/Category;", "targetCategory", "categoryT", "category", "categoryAnimT", "(Lrtx/kimiko/api/modules/Category;)F", "modulesHeaderT", "eventsHeaderT", "clientHeaderT", "themesRowT", "configsHeaderT", "placeholderT", "", "index", "eventsSubT", "(I)F", "popupDisplayed", "()Ljava/lang/String;", "popupT", "messengerT", "messengerVisible", "smoothMessengerScroll", "chatSelectionT", "headerModalDisplayed", "()I", "headerModalT", "popupHoldX", "popupHoldY", "", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "popupRows", "()Ljava/util/List;", "row", "rowAppear", "markAppearFrameDone", "updateSmoothing", "current", "dt", "updateContentAnims", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;F)V", "updateChatAnims", "target", "applyTarget", "(Lrtx/kimiko/api/modules/Category;)V", "next", "swapContent", "(Lrtx/kimiko/api/modules/Category;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;)V", "restartAppear", "Lrtx/kimiko/utils/animations/Decelerate;", "categoryAnim", "(Lrtx/kimiko/api/modules/Category;)Lrtx/kimiko/utils/animations/Decelerate;", "durationMs", "animProgress", "(J)F", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteTheme;", "Ljava/util/Set;", "open", "Z", "everOpened", "animStartNanos", "J", "lastFrameNanos", "F", "displayOpenness", "displayShatter", "smoothingPrimed", "Lrtx/kimiko/api/modules/Category;", "targetPrimed", "Ljava/util/EnumMap;", "categoryAnims", "Ljava/util/EnumMap;", "modulesHeaderAnim", "Lrtx/kimiko/utils/animations/Decelerate;", "eventsHeaderAnim", "clientHeaderAnim", "placeholderAnim", "[F", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "rowAppearStart", "Ljava/util/HashMap;", "appearBaseMs", "appearInitialFrame", "popupAnim", "messengerAnim", "chatSelectionAnim", "headerModalAnim", "I", "Ljava/lang/String;", "Ljava/util/List;", "shatter", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "shatterPrimed", "shatterSeedTried", "shatterSeedTriedSet", "closeStateSampleNanos", "closeStateLive", "closeAnchorPrimed", "frozenAnchor", "Lnet/minecraft/Vec3d;", "frozenYaw", "frozenPitch", "reverseStartOpenness", "prevOpenness", "isRenderable", "isFinished", "Companion", "rtx.kimiko:kimiko"})
public final class RemoteGuiPanel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private GuiShareRemoteState state;
    @NotNull
    private final RemoteTheme theme;
    @NotNull
    private Set<String> enabledSet;
    private boolean open;
    private boolean everOpened;
    private long animStartNanos;
    private long lastFrameNanos;
    private float smoothScroll;
    private float smoothMouseX;
    private float smoothMouseY;
    private float smoothPopupScroll;
    private float smoothThemesScroll;
    private float smoothEventsScroll;
    private float smoothConfigsScroll;
    private float smoothYaw;
    private float smoothPitch;
    private float displayOpenness;
    private float displayShatter;
    private boolean smoothingPrimed;
    @Nullable
    private Category contentCategory;
    @Nullable
    private Category targetCategory;
    private boolean targetPrimed;
    private float categoryT;
    @NotNull
    private final EnumMap<Category, Decelerate> categoryAnims;
    @NotNull
    private final Decelerate modulesHeaderAnim;
    @NotNull
    private final Decelerate eventsHeaderAnim;
    @NotNull
    private final Decelerate clientHeaderAnim;
    @NotNull
    private final Decelerate placeholderAnim;
    private float themesRowT;
    private float configsHeaderT;
    @NotNull
    private final float[] eventsSubT;
    @NotNull
    private final HashMap<Integer, Long> rowAppearStart;
    private long appearBaseMs;
    private boolean appearInitialFrame;
    @NotNull
    private final Decelerate popupAnim;
    @NotNull
    private final Decelerate messengerAnim;
    @NotNull
    private final Decelerate chatSelectionAnim;
    @NotNull
    private final Decelerate headerModalAnim;
    private int headerModalDisplayed;
    private float smoothMessengerScroll;
    @NotNull
    private String popupDisplayed;
    private float popupHoldX;
    private float popupHoldY;
    @NotNull
    private List<GuiSharePopupRow> popupRows;
    @NotNull
    private final GuiShatterAnimation.State shatter;
    private boolean shatterPrimed;
    private long shatterSeedTried;
    private boolean shatterSeedTriedSet;
    private long closeStateSampleNanos;
    private boolean closeStateLive;
    private boolean closeAnchorPrimed;
    @NotNull
    private Vec3d frozenAnchor;
    private float frozenYaw;
    private float frozenPitch;
    private float reverseStartOpenness;
    private float prevOpenness;
    @NotNull
    private Vec3d resolvedAnchor;
    private float resolvedYaw;
    private float resolvedPitch;
    @NotNull
    private float[] shatterRect;
    private static final long OPEN_MS = 380L;
    private static final long CLOSE_MS = 620L;
    private static final float CATEGORY_FADE_SEC = 0.15f;
    private static final float ROW_FADE_MS = 380.0f;
    private static final long CLOSE_STATE_TIMEOUT_NANOS = 1500000000L;
    @NotNull
    private static final float[] FALLBACK_RECT;

    public RemoteGuiPanel(@NotNull GuiShareRemoteState state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        this.state = state;
        this.theme = new RemoteTheme();
        this.enabledSet = SetsKt.emptySet();
        this.displayOpenness = 1.0f;
        this.categoryT = 1.0f;
        this.categoryAnims = new EnumMap(Category.class);
        this.modulesHeaderAnim = RemoteGuiPanel.Companion.createAnim(200);
        this.eventsHeaderAnim = RemoteGuiPanel.Companion.createAnim(200);
        this.clientHeaderAnim = RemoteGuiPanel.Companion.createAnim(200);
        this.placeholderAnim = RemoteGuiPanel.Companion.createAnim(200);
        this.themesRowT = 1.0f;
        this.eventsSubT = new float[2];
        this.rowAppearStart = new HashMap();
        this.popupAnim = RemoteGuiPanel.Companion.createAnim(220);
        this.messengerAnim = RemoteGuiPanel.Companion.createAnim(220);
        this.chatSelectionAnim = RemoteGuiPanel.Companion.createAnim(220);
        this.headerModalAnim = RemoteGuiPanel.Companion.createAnim(220);
        this.headerModalDisplayed = -1;
        this.popupDisplayed = "";
        this.popupRows = CollectionsKt.emptyList();
        this.shatter = GuiShatterAnimation.create();
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.frozenAnchor = vec3d2;
        this.reverseStartOpenness = -1.0f;
        this.prevOpenness = 1.0f;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        this.resolvedAnchor = vec3d3;
        this.acceptCloseState(state);
        this.theme.apply(state.theme);
        this.enabledSet = new HashSet(state.enabledModules);
        this.lastFrameNanos = System.nanoTime();
        this.placeholderAnim.setDirection(Direction.FORWARDS);
        this.shatterRect = FALLBACK_RECT;
    }

    @NotNull
    public final GuiShatterAnimation.State shatterState() {
        return this.shatter;
    }

    public final boolean shatterActive() {
        if (!this.shatterPrimed || !this.shatter.isActive()) {
            return false;
        }
        return this.closeActive() || !this.open && this.animProgress(620L) < 1.0f;
    }

    public final boolean closeActive() {
        return this.closeStateLive && System.nanoTime() - this.closeStateSampleNanos <= 1500000000L;
    }

    public final void markClosed() {
        this.open = false;
    }

    public final boolean isRenderable() {
        return this.open || this.contentAlpha() > 0.01f || this.closeActive();
    }

    public final boolean isFinished() {
        if (this.shatterActive() || this.closeActive()) {
            return false;
        }
        return !this.open && this.contentAlpha() <= 0.01f;
    }

    private final void acceptCloseState(GuiShareRemoteState state) {
        this.closeStateLive = state != null && state.closeState.active;
        this.closeStateSampleNanos = this.closeStateLive ? System.nanoTime() : 0L;
    }

    @NotNull
    public final Vec3d resolvedAnchor() {
        return this.resolvedAnchor;
    }

    public final float resolvedYaw() {
        return this.resolvedYaw;
    }

    public final float resolvedPitch() {
        return this.resolvedPitch;
    }

    @NotNull
    public final Vec3d resolveAnchor(@NotNull Vec3d liveAnchor) {
        Intrinsics.checkNotNullParameter((Object)liveAnchor, (String)"liveAnchor");
        float liveYaw = this.smoothYaw();
        float livePitch = this.smoothPitch();
        if (!this.closeActive()) {
            this.closeAnchorPrimed = false;
            this.reverseStartOpenness = -1.0f;
            this.prevOpenness = 1.0f;
            this.resolvedAnchor = liveAnchor;
            this.resolvedYaw = liveYaw;
            this.resolvedPitch = livePitch;
            return liveAnchor;
        }
        float openness = this.closeOpenness();
        if (!this.closeAnchorPrimed) {
            this.closeAnchorPrimed = true;
            this.frozenAnchor = liveAnchor;
            this.frozenYaw = liveYaw;
            this.frozenPitch = livePitch;
            this.prevOpenness = openness;
            this.reverseStartOpenness = -1.0f;
        }
        if (openness > this.prevOpenness + 1.0E-4f && this.reverseStartOpenness < 0.0f) {
            this.reverseStartOpenness = this.prevOpenness;
        }
        if (this.reverseStartOpenness >= 0.0f && openness < this.prevOpenness - 0.001f) {
            this.frozenAnchor = this.resolvedAnchor;
            this.frozenYaw = this.resolvedYaw;
            this.frozenPitch = this.resolvedPitch;
            this.reverseStartOpenness = -1.0f;
        }
        this.prevOpenness = openness;
        if (this.reverseStartOpenness >= 0.0f) {
            float span = Math.max(1.0E-4f, 1.0f - this.reverseStartOpenness);
            float blend = RemoteGuiPanel.Companion.clamp01((openness - this.reverseStartOpenness) / span);
            Vec3d vec3d2 = this.frozenAnchor.lerp(liveAnchor, (double)blend);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"lerp(...)");
            this.resolvedAnchor = vec3d2;
            this.resolvedYaw = this.frozenYaw + RemoteGuiPanel.Companion.wrapDegrees(liveYaw - this.frozenYaw) * blend;
            this.resolvedPitch = this.frozenPitch + (livePitch - this.frozenPitch) * blend;
        } else {
            this.resolvedAnchor = this.frozenAnchor;
            this.resolvedYaw = this.frozenYaw;
            this.resolvedPitch = this.frozenPitch;
        }
        return this.resolvedAnchor;
    }

    public final float closeOpenness() {
        return this.displayOpenness;
    }

    public final float displayShatterProgress() {
        return this.displayShatter;
    }

    public final float closeAnimationScale() {
        float t = 1.0f - this.closeOpenness();
        float backIn = 2.70158f * t * t * t - 1.70158f * t * t;
        return 1.0f + -0.3f * backIn;
    }

    public final float closeAlpha() {
        float t = this.closeActive() ? 1.0f - this.closeOpenness() : RemoteGuiPanel.Companion.clamp01(this.animProgress(620L));
        return RemoteGuiPanel.Companion.clamp01(1.0f - t * t * t);
    }

    @NotNull
    public final float[] shatterRect() {
        return this.shatterRect;
    }

    public final float shatterProgressForRender() {
        return this.closeActive() ? this.displayShatter : RemoteGuiPanel.Companion.clamp01(this.animProgress(620L));
    }

    private final void beginShatterWith(long seed, float[] rect) {
        this.shatterRect = rect;
        this.shatterSeedTried = seed;
        this.shatterSeedTriedSet = true;
        this.shatter.begin(rect[0], rect[1], rect[2], rect[3], rect[4], rect[5], rect[6], seed);
        this.shatterPrimed = this.shatter.isActive();
    }

    private final void updateShatter(GuiShareRemoteState state) {
        long seed;
        boolean closingLocally;
        GuiShareCloseState close = state.closeState;
        boolean liveClose = this.closeActive();
        boolean bl = closingLocally = !this.open && this.everOpened && this.animProgress(620L) < 1.0f;
        if (!liveClose && !closingLocally) {
            if (this.shatterPrimed) {
                this.shatter.cancel();
            }
            this.shatterPrimed = false;
            this.shatterSeedTriedSet = false;
            return;
        }
        long l = seed = liveClose ? close.seed : 0L;
        if (!this.shatterSeedTriedSet || this.shatterSeedTried != seed) {
            float[] rect = this.resolveShatterRect(close);
            this.beginShatterWith(seed, rect);
        }
    }

    private final float[] resolveShatterRect(GuiShareCloseState close) {
        if (close.rectW > 10.0f && close.rectH > 10.0f) {
            float[] fArray = new float[]{close.rectX, close.rectY, close.rectW, close.rectH, close.rectPad, close.screenW, close.screenH};
            return fArray;
        }
        return FALLBACK_RECT;
    }

    public final void applyState(@NotNull GuiShareRemoteState state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        String previousTheme = this.state.selectedTheme;
        this.state = state;
        this.acceptCloseState(state);
        this.theme.apply(state.theme);
        this.enabledSet = new HashSet(state.enabledModules);
        if (state.open != this.open) {
            this.open = state.open;
            this.animStartNanos = System.nanoTime();
            if (this.open) {
                this.everOpened = true;
            }
        }
        if (!Intrinsics.areEqual((Object)previousTheme, (Object)state.selectedTheme)) {
            this.smoothThemesScroll = state.themesScroll;
        }
    }

    @NotNull
    public final GuiShareRemoteState state() {
        return this.state;
    }

    @NotNull
    public final RemoteTheme theme() {
        return this.theme;
    }

    @NotNull
    public final Set<String> enabledSet() {
        return this.enabledSet;
    }

    public final boolean isOpen() {
        return this.open;
    }

    public final float contentAlpha() {
        if (this.shatterActive()) {
            return 1.0f;
        }
        if (this.closeActive()) {
            return this.closeAlpha();
        }
        if (this.open) {
            return RemoteGuiPanel.Companion.clamp01(this.animProgress(380L) * 1.6f);
        }
        return RemoteGuiPanel.Companion.clamp01(1.0f - this.animProgress(620L) * 1.35f);
    }

    public final float scale() {
        if (this.closeActive()) {
            return this.closeAnimationScale();
        }
        if (this.open) {
            float progress = this.animProgress(380L);
            float quintOut = 1.0f - (float)Math.pow(1.0f - progress, 5.0);
            return 1.25f - 0.25f * quintOut;
        }
        return Math.max(0.001f, RemoteGuiPanel.Companion.easeOutBack(1.0f - this.animProgress(620L)));
    }

    public final float backdropScale() {
        if (!this.open) {
            return 1.0f;
        }
        return RemoteGuiPanel.Companion.clamp01(1.0f - this.animProgress(620L) * 1.35f);
    }

    public final float smoothScroll() {
        return this.smoothScroll;
    }

    public final float smoothMouseX() {
        return this.smoothMouseX;
    }

    public final float smoothMouseY() {
        return this.smoothMouseY;
    }

    public final float smoothPopupScroll() {
        return this.smoothPopupScroll;
    }

    public final float smoothThemesScroll() {
        return this.smoothThemesScroll;
    }

    public final float smoothConfigsScroll() {
        return this.smoothConfigsScroll;
    }

    public final float smoothEventsScroll() {
        return this.smoothEventsScroll;
    }

    public final float smoothYaw() {
        return this.smoothYaw;
    }

    public final float smoothPitch() {
        return this.smoothPitch;
    }

    @Nullable
    public final Category contentCategory() {
        return this.contentCategory;
    }

    @Nullable
    public final Category targetCategory() {
        return this.targetCategory;
    }

    public final float categoryT() {
        return this.categoryT;
    }

    public final float categoryAnimT(@NotNull Category category) {
        Intrinsics.checkNotNullParameter((Object)((Object)category), (String)"category");
        Double d = this.categoryAnim(category).getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final float modulesHeaderT() {
        Double d = this.modulesHeaderAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final float eventsHeaderT() {
        Double d = this.eventsHeaderAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final float clientHeaderT() {
        Double d = this.clientHeaderAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final float themesRowT() {
        return this.themesRowT;
    }

    public final float configsHeaderT() {
        return this.configsHeaderT;
    }

    public final float placeholderT() {
        Double d = this.placeholderAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final float eventsSubT(int index) {
        return this.eventsSubT[index];
    }

    @NotNull
    public final String popupDisplayed() {
        return this.popupDisplayed;
    }

    public final float popupT() {
        Double d = this.popupAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final float messengerT() {
        Double d = this.messengerAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final boolean messengerVisible() {
        return this.messengerT() > 0.01f;
    }

    public final float smoothMessengerScroll() {
        return this.smoothMessengerScroll;
    }

    public final float chatSelectionT() {
        Double d = this.chatSelectionAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final int headerModalDisplayed() {
        return this.headerModalDisplayed;
    }

    public final float headerModalT() {
        Double d = this.headerModalAnim.getOutput();
        return d != null ? (float)d.doubleValue() : 0.0f;
    }

    public final float popupHoldX() {
        return this.popupHoldX;
    }

    public final float popupHoldY() {
        return this.popupHoldY;
    }

    @NotNull
    public final List<GuiSharePopupRow> popupRows() {
        return this.popupRows;
    }

    public final float rowAppear(int row) {
        Long start = this.rowAppearStart.get(row);
        if (start == null) {
            start = this.appearInitialFrame ? Long.valueOf(this.appearBaseMs) : Long.valueOf(System.currentTimeMillis());
            ((Map)this.rowAppearStart).put(row, start);
        }
        float p = RemoteGuiPanel.Companion.clamp01((float)(System.currentTimeMillis() - start) / 380.0f);
        return 1.0f - (1.0f - p) * (1.0f - p);
    }

    public final void markAppearFrameDone() {
        this.appearInitialFrame = false;
    }

    public final void updateSmoothing() {
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastFrameNanos) / 1.0E9f);
        this.lastFrameNanos = now;
        GuiShareRemoteState current = this.state;
        boolean liveClose = this.closeActive();
        if (!this.smoothingPrimed) {
            this.smoothingPrimed = true;
            this.smoothScroll = current.listScroll;
            this.smoothMouseX = current.mouseX;
            this.smoothMouseY = current.mouseY;
            this.smoothPopupScroll = current.popupScroll;
            this.smoothThemesScroll = current.themesScroll;
            this.smoothEventsScroll = current.eventsScroll;
            this.smoothConfigsScroll = current.configsScroll;
            this.smoothMessengerScroll = RemoteGuiPanel.Companion.chatScrollOf(current);
            this.smoothYaw = current.yaw;
            this.smoothPitch = current.pitch;
            this.displayOpenness = liveClose ? RemoteGuiPanel.Companion.clamp01(current.closeState.openness) : 1.0f;
            this.displayShatter = liveClose ? RemoteGuiPanel.Companion.clamp01(current.closeState.shatterProgress) : 0.0f;
        } else {
            float rate = 1.0f - (float)Math.exp(-dt * 14.0f);
            this.smoothScroll += (current.listScroll - this.smoothScroll) * rate;
            this.smoothMouseX += (current.mouseX - this.smoothMouseX) * rate;
            this.smoothMouseY += (current.mouseY - this.smoothMouseY) * rate;
            this.smoothPopupScroll += (current.popupScroll - this.smoothPopupScroll) * rate;
            this.smoothThemesScroll += (current.themesScroll - this.smoothThemesScroll) * rate;
            this.smoothEventsScroll += (current.eventsScroll - this.smoothEventsScroll) * rate;
            this.smoothConfigsScroll += (current.configsScroll - this.smoothConfigsScroll) * rate;
            float chatTarget = RemoteGuiPanel.Companion.chatScrollOf(current);
            float chatDelta = chatTarget - this.smoothMessengerScroll;
            float guiScale = Render2DCoordinateSpace.designGuiScale();
            if (guiScale > 0.0f && Math.abs(chatDelta) * guiScale < 1.0f) {
                this.smoothMessengerScroll = chatTarget;
            } else {
                float chatRate = 1.0f - (float)Math.exp(-dt * 18.0f);
                this.smoothMessengerScroll += chatDelta * chatRate;
            }
            float rotationRate = 1.0f - (float)Math.exp(-dt * 16.0f);
            this.smoothYaw += RemoteGuiPanel.Companion.wrapDegrees(current.yaw - this.smoothYaw) * rotationRate;
            this.smoothPitch += (current.pitch - this.smoothPitch) * rotationRate;
            GuiShareCloseState close = current.closeState;
            float targetOpenness = liveClose ? RemoteGuiPanel.Companion.clamp01(close.openness) : 1.0f;
            float targetShatter = liveClose ? RemoteGuiPanel.Companion.clamp01(close.shatterProgress) : 0.0f;
            float closeRate = 1.0f - (float)Math.exp(-dt * 13.0f);
            this.displayOpenness += (targetOpenness - this.displayOpenness) * closeRate;
            this.displayShatter += (targetShatter - this.displayShatter) * closeRate;
        }
        this.updateShatter(current);
        this.updateContentAnims(current, dt);
    }

    private final void updateContentAnims(GuiShareRemoteState current, float dt) {
        String popup;
        Category target = RemoteGuiPanelRenderer.resolveCategory(current.category);
        float themesTarget = this.theme.isThemeMode() ? 1.0f : 0.0f;
        this.themesRowT += (themesTarget - this.themesRowT) * (1.0f - (float)Math.exp(-dt * 10.0f));
        float subRate = 1.0f - (float)Math.exp(-dt * 14.0f);
        int n = this.eventsSubT.length;
        for (int i = 0; i < n; ++i) {
            float subTarget = target == Category.EVENTS && current.eventsSub == i ? 1.0f : 0.0f;
            float[] fArray = this.eventsSubT;
            int n2 = i;
            fArray[n2] = fArray[n2] + (subTarget - this.eventsSubT[i]) * subRate;
        }
        if (!this.targetPrimed || target != this.targetCategory) {
            this.applyTarget(target);
        }
        if (target == this.contentCategory) {
            if (this.contentCategory != null && this.categoryT < 1.0f) {
                this.categoryT = Math.min(1.0f, this.categoryT + dt / 0.15f);
            }
        } else if (this.contentCategory == null) {
            this.swapContent(target, current);
            this.categoryT = 0.0f;
        } else {
            this.categoryT = Math.max(0.0f, this.categoryT - dt / 0.15f);
            if (this.categoryT <= 0.0f) {
                this.categoryT = 0.0f;
                this.swapContent(target, current);
            }
        }
        if (((CharSequence)(popup = current.popupModule)).length() > 0) {
            if (!Intrinsics.areEqual((Object)popup, (Object)this.popupDisplayed)) {
                this.popupDisplayed = popup;
                this.popupRows = current.popupRows;
                this.popupAnim.setDirection(Direction.FORWARDS);
                this.popupAnim.counter.resetCounter();
            } else if (!((Collection)current.popupRows).isEmpty()) {
                this.popupRows = current.popupRows;
            }
            this.popupHoldX = current.popupX;
            this.popupHoldY = current.popupY;
        } else if (((CharSequence)this.popupDisplayed).length() > 0) {
            if (this.popupAnim.getDirection() == Direction.FORWARDS) {
                this.popupAnim.setDirection(Direction.BACKWARDS);
                this.popupAnim.counter.resetCounter();
            }
            Double d = this.popupAnim.getOutput();
            float f = d != null ? (float)d.doubleValue() : 0.0f;
            if (f <= 0.01f) {
                this.popupDisplayed = "";
                this.popupRows = CollectionsKt.emptyList();
            }
        }
        float configsTarget = this.contentCategory == Category.CONFIGS ? 1.0f : 0.0f;
        this.configsHeaderT += (configsTarget - this.configsHeaderT) * (1.0f - (float)Math.exp(-dt * 12.0f));
        if (Math.abs(configsTarget - this.configsHeaderT) < 0.002f) {
            this.configsHeaderT = configsTarget;
        }
        this.updateChatAnims(current);
    }

    private final void updateChatAnims(GuiShareRemoteState current) {
        int modal;
        Direction selectionTarget;
        Direction messengerTarget;
        GuiShareChatState guiShareChatState = current.chat;
        if (guiShareChatState == null) {
            guiShareChatState = GuiShareChatState.HIDDEN;
        }
        GuiShareChatState chat = guiShareChatState;
        Direction direction = messengerTarget = chat.messengerOpen ? Direction.FORWARDS : Direction.BACKWARDS;
        if (this.messengerAnim.getDirection() != messengerTarget) {
            this.messengerAnim.setDirection(messengerTarget);
            this.messengerAnim.counter.resetCounter();
        }
        Direction direction2 = selectionTarget = chat.selectedCount > 0 ? Direction.FORWARDS : Direction.BACKWARDS;
        if (this.chatSelectionAnim.getDirection() != selectionTarget) {
            this.chatSelectionAnim.setDirection(selectionTarget);
            this.chatSelectionAnim.counter.resetCounter();
        }
        if ((modal = chat.headerModal) >= 0) {
            if (modal != this.headerModalDisplayed) {
                this.headerModalDisplayed = modal;
                this.headerModalAnim.setDirection(Direction.FORWARDS);
                this.headerModalAnim.counter.resetCounter();
            }
        } else if (this.headerModalDisplayed >= 0) {
            if (this.headerModalAnim.getDirection() == Direction.FORWARDS) {
                this.headerModalAnim.setDirection(Direction.BACKWARDS);
                this.headerModalAnim.counter.resetCounter();
            }
            Double d = this.headerModalAnim.getOutput();
            float f = d != null ? (float)d.doubleValue() : 0.0f;
            if (f <= 0.01f) {
                this.headerModalDisplayed = -1;
            }
        }
    }

    private final void applyTarget(Category target) {
        this.targetPrimed = true;
        this.targetCategory = target;
        for (Category category : Category.values()) {
            this.categoryAnim(category).setDirection(category == target ? Direction.FORWARDS : Direction.BACKWARDS);
        }
        this.modulesHeaderAnim.setDirection(RemoteGuiPanelRenderer.INSTANCE.isMainCategory(target) || target == Category.CONFIGS ? Direction.FORWARDS : Direction.BACKWARDS);
        this.eventsHeaderAnim.setDirection(target == Category.EVENTS ? Direction.FORWARDS : Direction.BACKWARDS);
        this.clientHeaderAnim.setDirection(RemoteGuiPanelRenderer.INSTANCE.isOtherCategory(target) ? Direction.FORWARDS : Direction.BACKWARDS);
        if (target == null) {
            this.placeholderAnim.setDirection(Direction.FORWARDS);
            this.placeholderAnim.counter.resetCounter();
        } else {
            this.placeholderAnim.setDirection(Direction.BACKWARDS);
        }
    }

    private final void swapContent(Category next, GuiShareRemoteState current) {
        boolean headerSwap;
        Category previous = this.contentCategory;
        this.contentCategory = next;
        boolean bl = headerSwap = previous == Category.CONFIGS && RemoteGuiPanelRenderer.INSTANCE.isMainCategory(next) || RemoteGuiPanelRenderer.INSTANCE.isMainCategory(previous) && next == Category.CONFIGS;
        if (!headerSwap) {
            this.configsHeaderT = next == Category.CONFIGS ? 1.0f : 0.0f;
        }
        this.smoothScroll = current.listScroll;
        this.smoothThemesScroll = current.themesScroll;
        this.smoothConfigsScroll = current.configsScroll;
        if (next != null) {
            this.restartAppear();
        } else {
            this.placeholderAnim.setDirection(Direction.FORWARDS);
            this.placeholderAnim.counter.resetCounter();
        }
    }

    private final void restartAppear() {
        this.rowAppearStart.clear();
        this.appearBaseMs = System.currentTimeMillis();
        this.appearInitialFrame = true;
    }

    private final Decelerate categoryAnim(Category category) {
        return this.categoryAnims.computeIfAbsent(category, k -> RemoteGuiPanel.Companion.createAnim(200));
    }

    private final float animProgress(long durationMs) {
        float elapsed = (float)(System.nanoTime() - this.animStartNanos) / 1000000.0f;
        return RemoteGuiPanel.Companion.clamp01(elapsed / (float)durationMs);
    }


    static {
        float[] fArray = new float[]{530.0f, 250.0f, 860.0f, 580.0f, 58.0f, 1920.0f, 1080.0f};
        FALLBACK_RECT = fArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0012\u0010\fJ\u0017\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0013\u0010\fR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0016R\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel.Companion;", "", "<init>", "()V", "", "ms", "Lrtx/kimiko/utils/animations/Decelerate;", "createAnim", "(I)Lrtx/kimiko/utils/animations/Decelerate;", "", "value", "easeOutBack", "(F)F", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "state", "chatScrollOf", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;)F", "degrees", "wrapDegrees", "clamp01", "", "OPEN_MS", "J", "CLOSE_MS", "CATEGORY_FADE_SEC", "F", "ROW_FADE_MS", "CLOSE_STATE_TIMEOUT_NANOS", "", "FALLBACK_RECT", "[F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final Decelerate createAnim(int ms) {
            Animation animation = new Decelerate().setMs((long)ms).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            Decelerate anim = (Decelerate)animation;
            anim.setDirection(Direction.BACKWARDS);
            anim.counter.setTime(System.currentTimeMillis() - (long)10000);
            return anim;
        }

        private final float easeOutBack(float value) {
            float overshoot = 1.70158f;
            float shifted = this.clamp01(value) - 1.0f;
            return shifted * shifted * ((overshoot + 1.0f) * shifted + overshoot) + 1.0f;
        }

        private final float chatScrollOf(GuiShareRemoteState state) {
            GuiShareChatState chat;
            GuiShareChatState guiShareChatState = chat = state.chat;
            return guiShareChatState != null ? guiShareChatState.messengerScroll : 0.0f;
        }

        private final float wrapDegrees(float degrees) {
            float value = degrees % 360.0f;
            if (value >= 180.0f) {
                value -= 360.0f;
            }
            if (value < -180.0f) {
                value += 360.0f;
            }
            return value;
        }

        private final float clamp01(float value) {
            return value < 0.0f ? 0.0f : Math.min(1.0f, value);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

