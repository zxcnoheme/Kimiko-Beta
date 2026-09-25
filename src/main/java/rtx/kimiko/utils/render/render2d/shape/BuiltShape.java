/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.shape;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.shape.ShapeRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b?\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 w2\u00020\u0001:\u0001wB\u00fd\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\t\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\t\u0012\u0006\u0010\u001d\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\u0006\u0010\u001f\u001a\u00020\u0002\u0012\u0006\u0010 \u001a\u00020\t\u0012\b\b\u0002\u0010!\u001a\u00020\u0002\u0012\b\b\u0002\u0010\"\u001a\u00020\u0002\u0012\b\b\u0002\u0010#\u001a\u00020\u0015\u00a2\u0006\u0004\b$\u0010%B\u0081\u0001\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010&\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\t\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u00a2\u0006\u0004\b$\u0010'J\u001d\u0010*\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u0002\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010,\u00a2\u0006\u0004\b/\u00100J\r\u00101\u001a\u00020\u0015\u00a2\u0006\u0004\b1\u00102J%\u00106\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\u0002\u00a2\u0006\u0004\b6\u00107J\u001d\u0010:\u001a\u00020\u00002\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u0015\u00a2\u0006\u0004\b:\u0010;J\u0015\u0010<\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\t\u00a2\u0006\u0004\b<\u0010=J\u001d\u0010@\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0015\u0010B\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0002\u00a2\u0006\u0004\bB\u0010CJ\u001d\u0010F\u001a\u00020\u00002\u0006\u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u0002\u00a2\u0006\u0004\bF\u0010+J\u0010\u0010G\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bG\u0010HJ\u0010\u0010I\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bI\u0010HJ\u0010\u0010J\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bJ\u0010HJ\u0010\u0010K\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bK\u0010HJ\u0010\u0010L\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bP\u0010HJ\u0010\u0010Q\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bQ\u0010HJ\u0010\u0010R\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bR\u0010HJ\u0010\u0010S\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bS\u0010HJ\u0010\u0010T\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bT\u0010HJ\u0010\u0010U\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\bU\u0010OJ\u0010\u0010V\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bV\u0010HJ\u0010\u0010W\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bW\u0010HJ\u0010\u0010X\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\bX\u0010OJ\u0010\u0010Y\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bY\u0010HJ\u0010\u0010Z\u001a\u00020\u0015H\u00c6\u0003\u00a2\u0006\u0004\bZ\u00102J\u0010\u0010[\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b[\u0010HJ\u0010\u0010\\\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\\\u0010HJ\u0010\u0010]\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b]\u0010HJ\u0010\u0010^\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b^\u0010HJ\u0010\u0010_\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b_\u0010HJ\u0010\u0010`\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b`\u0010OJ\u0010\u0010a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\ba\u0010HJ\u0010\u0010b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bb\u0010HJ\u0010\u0010c\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bc\u0010HJ\u0010\u0010d\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\bd\u0010OJ\u0010\u0010e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\be\u0010HJ\u0010\u0010f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bf\u0010HJ\u0010\u0010g\u001a\u00020\u0015H\u00c6\u0003\u00a2\u0006\u0004\bg\u00102J\u00bc\u0002\u0010h\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020\u00022\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020\u00022\b\b\u0002\u0010#\u001a\u00020\u0015H\u00c6\u0001\u00a2\u0006\u0004\bh\u0010iJ\u001b\u0010k\u001a\u00020\u00152\b\u0010j\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bk\u0010lJ\u0011\u0010m\u001a\u00020\tH\u00d6\u0081\u0004\u00a2\u0006\u0004\bm\u0010OJ\u0011\u0010o\u001a\u00020nH\u00d6\u0081\u0004\u00a2\u0006\u0004\bo\u0010pR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010s\u001a\u0004\b\u0003\u0010HR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010s\u001a\u0004\b\u0004\u0010HR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010s\u001a\u0004\b\u0005\u0010HR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010s\u001a\u0004\b\u0006\u0010HR%\u0010\b\u001a\u00020\u00078\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010t\u001a\u0004\b\b\u0010MR%\u0010\n\u001a\u00020\t8\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010u\u001a\u0004\b\n\u0010OR%\u0010\u000b\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010s\u001a\u0004\b\u000b\u0010HR%\u0010\f\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010s\u001a\u0004\b\f\u0010HR%\u0010\r\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010s\u001a\u0004\b\r\u0010HR%\u0010\u000e\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010s\u001a\u0004\b\u000e\u0010HR%\u0010\u000f\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010s\u001a\u0004\b\u000f\u0010HR%\u0010\u0010\u001a\u00020\t8\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010u\u001a\u0004\b\u0010\u0010OR%\u0010\u0011\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010s\u001a\u0004\b\u0011\u0010HR%\u0010\u0012\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010s\u001a\u0004\b\u0012\u0010HR%\u0010\u0013\u001a\u00020\t8\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010u\u001a\u0004\b\u0013\u0010OR%\u0010\u0014\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010s\u001a\u0004\b\u0014\u0010HR%\u0010\u0016\u001a\u00020\u00158\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010v\u001a\u0004\b\u0016\u00102R%\u0010\u0017\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010s\u001a\u0004\b\u0017\u0010HR%\u0010\u0018\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010s\u001a\u0004\b\u0018\u0010HR%\u0010\u0019\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010s\u001a\u0004\b\u0019\u0010HR%\u0010\u001a\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010s\u001a\u0004\b\u001a\u0010HR%\u0010\u001b\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u001b\u00a2\u0006\f\n\u0004\b\u001b\u0010s\u001a\u0004\b\u001b\u0010HR%\u0010\u001c\u001a\u00020\t8\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u001c\u00a2\u0006\f\n\u0004\b\u001c\u0010u\u001a\u0004\b\u001c\u0010OR%\u0010\u001d\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u001d\u00a2\u0006\f\n\u0004\b\u001d\u0010s\u001a\u0004\b\u001d\u0010HR%\u0010\u001e\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u001e\u00a2\u0006\f\n\u0004\b\u001e\u0010s\u001a\u0004\b\u001e\u0010HR%\u0010\u001f\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\u001f\u00a2\u0006\f\n\u0004\b\u001f\u0010s\u001a\u0004\b\u001f\u0010HR%\u0010 \u001a\u00020\t8\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b( \u00a2\u0006\f\n\u0004\b \u0010u\u001a\u0004\b \u0010OR%\u0010!\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(!\u00a2\u0006\f\n\u0004\b!\u0010s\u001a\u0004\b!\u0010HR%\u0010\"\u001a\u00020\u00028\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(\"\u00a2\u0006\f\n\u0004\b\"\u0010s\u001a\u0004\b\"\u0010HR%\u0010#\u001a\u00020\u00158\u0007z\f\bq\u0012\b\br\u0012\u0004\b\b(#\u00a2\u0006\f\n\u0004\b#\u0010v\u001a\u0004\b#\u00102\u00a8\u0006x"}, d2={"Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "", "", "x", "y", "width", "height", "", "spans", "", "spanCount", "innerRadius", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "color", "globalAlpha", "fresnelPower", "fresnelColor", "baseAlpha", "", "fresnelInvert", "fresnelMix", "distortStrength", "squirt", "z", "blurRadius", "secondColor", "colorOffset", "leftAligned", "bottomAnchored", "splitIndex", "waveFreq", "wavePhase", "waveEnabled", "<init>", "(FFFF[FIFFFFFIFFIFZFFFFFIFFFIFFZ)V", "radius", "(FFFFFIFFIFZFFFF)V", "freq", "phase", "withRowWave", "(FF)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "visible", "()Z", "spanData", "count", "innerRad", "withSpans", "([FIF)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "isLeftAligned", "isBottomAnchored", "withAlignment", "(ZZ)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "withSplitIndex", "(I)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "second", "offset", "withSecondColor", "(IF)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "withBlurRadius", "(F)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "spread", "saturation", "withRainbow", "component1", "()F", "component2", "component3", "component4", "component5", "()[F", "component6", "()I", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "copy", "(FFFF[FIFFFFFIFFIFZFFFFFIFFFIFFZ)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "[F", "I", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltShape {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    @NotNull
    private final float[] spans;
    private final int spanCount;
    private final float innerRadius;
    private final float radiusTopLeft;
    private final float radiusTopRight;
    private final float radiusBottomRight;
    private final float radiusBottomLeft;
    private final int color;
    private final float globalAlpha;
    private final float fresnelPower;
    private final int fresnelColor;
    private final float baseAlpha;
    private final boolean fresnelInvert;
    private final float fresnelMix;
    private final float distortStrength;
    private final float squirt;
    private final float z;
    private final float blurRadius;
    private final int secondColor;
    private final float colorOffset;
    private final float leftAligned;
    private final float bottomAnchored;
    private final int splitIndex;
    private final float waveFreq;
    private final float wavePhase;
    private final boolean waveEnabled;
    private static final float DEFAULT_BLUR_RADIUS = 30.0f;
    @NotNull
    private static final float[] NO_SPANS = new float[0];

    public BuiltShape(float x, float y, float width, float height, @NotNull float[] spans, int spanCount, float innerRadius, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z, float blurRadius, int secondColor, float colorOffset, float leftAligned, float bottomAnchored, int splitIndex, float waveFreq, float wavePhase, boolean waveEnabled) {
        Intrinsics.checkNotNullParameter((Object)spans, (String)"spans");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.spans = spans;
        this.spanCount = spanCount;
        this.innerRadius = innerRadius;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.color = color;
        this.globalAlpha = globalAlpha;
        this.fresnelPower = fresnelPower;
        this.fresnelColor = fresnelColor;
        this.baseAlpha = baseAlpha;
        this.fresnelInvert = fresnelInvert;
        this.fresnelMix = fresnelMix;
        this.distortStrength = distortStrength;
        this.squirt = squirt;
        this.z = z;
        this.blurRadius = blurRadius;
        this.secondColor = secondColor;
        this.colorOffset = colorOffset;
        this.leftAligned = leftAligned;
        this.bottomAnchored = bottomAnchored;
        this.splitIndex = splitIndex;
        this.waveFreq = waveFreq;
        this.wavePhase = wavePhase;
        this.waveEnabled = waveEnabled;
    }

    public /* synthetic */ BuiltShape(float f, float f2, float f3, float f4, float[] fArray, int n, float f5, float f6, float f7, float f8, float f9, int n2, float f10, float f11, int n3, float f12, boolean bl, float f13, float f14, float f15, float f16, float f17, int n4, float f18, float f19, float f20, int n5, float f21, float f22, boolean bl2, int n6, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, fArray, n, f5, f6, f7, f8, f9, n2, f10, f11, n3, f12, bl, f13, f14, f15, f16, f17, n4, f18, f19, f20, n5, (n6 & 0x8000000) != 0 ? 0.0f : f21, (n6 & 0x10000000) != 0 ? 0.0f : f22, (n6 & 0x20000000) == 0 && bl2);
    }

    @JvmName(name="x")
    public final float x() {
        return this.x;
    }

    @JvmName(name="y")
    public final float y() {
        return this.y;
    }

    @JvmName(name="width")
    public final float width() {
        return this.width;
    }

    @JvmName(name="height")
    public final float height() {
        return this.height;
    }

    @JvmName(name="spans")
    @NotNull
    public final float[] spans() {
        return this.spans;
    }

    @JvmName(name="spanCount")
    public final int spanCount() {
        return this.spanCount;
    }

    @JvmName(name="innerRadius")
    public final float innerRadius() {
        return this.innerRadius;
    }

    @JvmName(name="radiusTopLeft")
    public final float radiusTopLeft() {
        return this.radiusTopLeft;
    }

    @JvmName(name="radiusTopRight")
    public final float radiusTopRight() {
        return this.radiusTopRight;
    }

    @JvmName(name="radiusBottomRight")
    public final float radiusBottomRight() {
        return this.radiusBottomRight;
    }

    @JvmName(name="radiusBottomLeft")
    public final float radiusBottomLeft() {
        return this.radiusBottomLeft;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    @JvmName(name="globalAlpha")
    public final float globalAlpha() {
        return this.globalAlpha;
    }

    @JvmName(name="fresnelPower")
    public final float fresnelPower() {
        return this.fresnelPower;
    }

    @JvmName(name="fresnelColor")
    public final int fresnelColor() {
        return this.fresnelColor;
    }

    @JvmName(name="baseAlpha")
    public final float baseAlpha() {
        return this.baseAlpha;
    }

    @JvmName(name="fresnelInvert")
    public final boolean fresnelInvert() {
        return this.fresnelInvert;
    }

    @JvmName(name="fresnelMix")
    public final float fresnelMix() {
        return this.fresnelMix;
    }

    @JvmName(name="distortStrength")
    public final float distortStrength() {
        return this.distortStrength;
    }

    @JvmName(name="squirt")
    public final float squirt() {
        return this.squirt;
    }

    @JvmName(name="z")
    public final float z() {
        return this.z;
    }

    @JvmName(name="blurRadius")
    public final float blurRadius() {
        return this.blurRadius;
    }

    @JvmName(name="secondColor")
    public final int secondColor() {
        return this.secondColor;
    }

    @JvmName(name="colorOffset")
    public final float colorOffset() {
        return this.colorOffset;
    }

    @JvmName(name="leftAligned")
    public final float leftAligned() {
        return this.leftAligned;
    }

    @JvmName(name="bottomAnchored")
    public final float bottomAnchored() {
        return this.bottomAnchored;
    }

    @JvmName(name="splitIndex")
    public final int splitIndex() {
        return this.splitIndex;
    }

    @JvmName(name="waveFreq")
    public final float waveFreq() {
        return this.waveFreq;
    }

    @JvmName(name="wavePhase")
    public final float wavePhase() {
        return this.wavePhase;
    }

    @JvmName(name="waveEnabled")
    public final boolean waveEnabled() {
        return this.waveEnabled;
    }

    @NotNull
    public final BuiltShape withRowWave(float freq, float phase) {
        return BuiltShape.copy$default(this, 0.0f, 0.0f, 0.0f, 0.0f, null, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0.0f, false, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0.0f, 0, freq, phase, freq > 0.0f, 0x7FFFFFF, null);
    }

    public BuiltShape(float x, float y, float width, float height, float radius, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z) {
        this(x, y, width, height, NO_SPANS, 0, 0.0f, radius, radius, radius, radius, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, z, 30.0f, color, 0.0f, 1.0f, 0.0f, 0, 0.0f, 0.0f, false, 0x38000000, null);
    }

    public final void render(@Nullable DrawContext graphics) {
        ShapeRenderer.Companion.getInstance().draw(graphics, this);
    }

    public final boolean visible() {
        return this.width > 0.0f && this.height > 0.0f && this.globalAlpha > 0.0f && (this.baseAlpha > 0.0f || this.fresnelColor >>> 24 != 0 || this.color >>> 24 != 0);
    }

    @NotNull
    public final BuiltShape withSpans(@NotNull float[] spanData, int count, float innerRad) {
        Intrinsics.checkNotNullParameter((Object)spanData, (String)"spanData");
        return new BuiltShape(this.x, this.y, this.width, this.height, spanData, count, innerRad, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, this.blurRadius, this.secondColor, this.colorOffset, this.leftAligned, this.bottomAnchored, this.splitIndex, 0.0f, 0.0f, false, 0x38000000, null);
    }

    @NotNull
    public final BuiltShape withAlignment(boolean isLeftAligned, boolean isBottomAnchored) {
        return new BuiltShape(this.x, this.y, this.width, this.height, this.spans, this.spanCount, this.innerRadius, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, this.blurRadius, this.secondColor, this.colorOffset, isLeftAligned ? 1.0f : 0.0f, isBottomAnchored ? 1.0f : 0.0f, this.splitIndex, 0.0f, 0.0f, false, 0x38000000, null);
    }

    @NotNull
    public final BuiltShape withSplitIndex(int splitIndex) {
        return new BuiltShape(this.x, this.y, this.width, this.height, this.spans, this.spanCount, this.innerRadius, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, this.blurRadius, this.secondColor, this.colorOffset, this.leftAligned, this.bottomAnchored, splitIndex, 0.0f, 0.0f, false, 0x38000000, null);
    }

    @NotNull
    public final BuiltShape withSecondColor(int second, float offset) {
        return new BuiltShape(this.x, this.y, this.width, this.height, this.spans, this.spanCount, this.innerRadius, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, this.blurRadius, second, offset, this.leftAligned, this.bottomAnchored, this.splitIndex, 0.0f, 0.0f, false, 0x38000000, null);
    }

    @NotNull
    public final BuiltShape withBlurRadius(float radius) {
        return new BuiltShape(this.x, this.y, this.width, this.height, this.spans, this.spanCount, this.innerRadius, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, radius, this.secondColor, this.colorOffset, this.leftAligned, this.bottomAnchored, this.splitIndex, 0.0f, 0.0f, false, 0x38000000, null);
    }

    @NotNull
    public final BuiltShape withRainbow(float spread, float saturation) {
        int sr = Math.max(0, Math.min(255, Math.round(spread * 255.0f)));
        int sg = Math.max(0, Math.min(255, Math.round(saturation * 255.0f)));
        int packed = 0xFF000000 | sr << 16 | sg << 8;
        return new BuiltShape(this.x, this.y, this.width, this.height, this.spans, this.spanCount, this.innerRadius, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, 1.0f, this.blurRadius, packed, this.colorOffset, this.leftAligned, this.bottomAnchored, this.splitIndex, 0.0f, 0.0f, false, 0x38000000, null);
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.width;
    }

    public final float component4() {
        return this.height;
    }

    @NotNull
    public final float[] component5() {
        return this.spans;
    }

    public final int component6() {
        return this.spanCount;
    }

    public final float component7() {
        return this.innerRadius;
    }

    public final float component8() {
        return this.radiusTopLeft;
    }

    public final float component9() {
        return this.radiusTopRight;
    }

    public final float component10() {
        return this.radiusBottomRight;
    }

    public final float component11() {
        return this.radiusBottomLeft;
    }

    public final int component12() {
        return this.color;
    }

    public final float component13() {
        return this.globalAlpha;
    }

    public final float component14() {
        return this.fresnelPower;
    }

    public final int component15() {
        return this.fresnelColor;
    }

    public final float component16() {
        return this.baseAlpha;
    }

    public final boolean component17() {
        return this.fresnelInvert;
    }

    public final float component18() {
        return this.fresnelMix;
    }

    public final float component19() {
        return this.distortStrength;
    }

    public final float component20() {
        return this.squirt;
    }

    public final float component21() {
        return this.z;
    }

    public final float component22() {
        return this.blurRadius;
    }

    public final int component23() {
        return this.secondColor;
    }

    public final float component24() {
        return this.colorOffset;
    }

    public final float component25() {
        return this.leftAligned;
    }

    public final float component26() {
        return this.bottomAnchored;
    }

    public final int component27() {
        return this.splitIndex;
    }

    public final float component28() {
        return this.waveFreq;
    }

    public final float component29() {
        return this.wavePhase;
    }

    public final boolean component30() {
        return this.waveEnabled;
    }

    @NotNull
    public final BuiltShape copy(float x, float y, float width, float height, @NotNull float[] spans, int spanCount, float innerRadius, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z, float blurRadius, int secondColor, float colorOffset, float leftAligned, float bottomAnchored, int splitIndex, float waveFreq, float wavePhase, boolean waveEnabled) {
        Intrinsics.checkNotNullParameter((Object)spans, (String)"spans");
        return new BuiltShape(x, y, width, height, spans, spanCount, innerRadius, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, z, blurRadius, secondColor, colorOffset, leftAligned, bottomAnchored, splitIndex, waveFreq, wavePhase, waveEnabled);
    }

    public static /* synthetic */ BuiltShape copy$default(BuiltShape builtShape, float f, float f2, float f3, float f4, float[] fArray, int n, float f5, float f6, float f7, float f8, float f9, int n2, float f10, float f11, int n3, float f12, boolean bl, float f13, float f14, float f15, float f16, float f17, int n4, float f18, float f19, float f20, int n5, float f21, float f22, boolean bl2, int n6, Object object) {
        if ((n6 & 1) != 0) {
            f = builtShape.x;
        }
        if ((n6 & 2) != 0) {
            f2 = builtShape.y;
        }
        if ((n6 & 4) != 0) {
            f3 = builtShape.width;
        }
        if ((n6 & 8) != 0) {
            f4 = builtShape.height;
        }
        if ((n6 & 0x10) != 0) {
            fArray = builtShape.spans;
        }
        if ((n6 & 0x20) != 0) {
            n = builtShape.spanCount;
        }
        if ((n6 & 0x40) != 0) {
            f5 = builtShape.innerRadius;
        }
        if ((n6 & 0x80) != 0) {
            f6 = builtShape.radiusTopLeft;
        }
        if ((n6 & 0x100) != 0) {
            f7 = builtShape.radiusTopRight;
        }
        if ((n6 & 0x200) != 0) {
            f8 = builtShape.radiusBottomRight;
        }
        if ((n6 & 0x400) != 0) {
            f9 = builtShape.radiusBottomLeft;
        }
        if ((n6 & 0x800) != 0) {
            n2 = builtShape.color;
        }
        if ((n6 & 0x1000) != 0) {
            f10 = builtShape.globalAlpha;
        }
        if ((n6 & 0x2000) != 0) {
            f11 = builtShape.fresnelPower;
        }
        if ((n6 & 0x4000) != 0) {
            n3 = builtShape.fresnelColor;
        }
        if ((n6 & 0x8000) != 0) {
            f12 = builtShape.baseAlpha;
        }
        if ((n6 & 0x10000) != 0) {
            bl = builtShape.fresnelInvert;
        }
        if ((n6 & 0x20000) != 0) {
            f13 = builtShape.fresnelMix;
        }
        if ((n6 & 0x40000) != 0) {
            f14 = builtShape.distortStrength;
        }
        if ((n6 & 0x80000) != 0) {
            f15 = builtShape.squirt;
        }
        if ((n6 & 0x100000) != 0) {
            f16 = builtShape.z;
        }
        if ((n6 & 0x200000) != 0) {
            f17 = builtShape.blurRadius;
        }
        if ((n6 & 0x400000) != 0) {
            n4 = builtShape.secondColor;
        }
        if ((n6 & 0x800000) != 0) {
            f18 = builtShape.colorOffset;
        }
        if ((n6 & 0x1000000) != 0) {
            f19 = builtShape.leftAligned;
        }
        if ((n6 & 0x2000000) != 0) {
            f20 = builtShape.bottomAnchored;
        }
        if ((n6 & 0x4000000) != 0) {
            n5 = builtShape.splitIndex;
        }
        if ((n6 & 0x8000000) != 0) {
            f21 = builtShape.waveFreq;
        }
        if ((n6 & 0x10000000) != 0) {
            f22 = builtShape.wavePhase;
        }
        if ((n6 & 0x20000000) != 0) {
            bl2 = builtShape.waveEnabled;
        }
        return builtShape.copy(f, f2, f3, f4, fArray, n, f5, f6, f7, f8, f9, n2, f10, f11, n3, f12, bl, f13, f14, f15, f16, f17, n4, f18, f19, f20, n5, f21, f22, bl2);
    }

    @NotNull
    public String toString() {
        return "BuiltShape(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", spans=" + Arrays.toString(this.spans) + ", spanCount=" + this.spanCount + ", innerRadius=" + this.innerRadius + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", color=" + this.color + ", globalAlpha=" + this.globalAlpha + ", fresnelPower=" + this.fresnelPower + ", fresnelColor=" + this.fresnelColor + ", baseAlpha=" + this.baseAlpha + ", fresnelInvert=" + this.fresnelInvert + ", fresnelMix=" + this.fresnelMix + ", distortStrength=" + this.distortStrength + ", squirt=" + this.squirt + ", z=" + this.z + ", blurRadius=" + this.blurRadius + ", secondColor=" + this.secondColor + ", colorOffset=" + this.colorOffset + ", leftAligned=" + this.leftAligned + ", bottomAnchored=" + this.bottomAnchored + ", splitIndex=" + this.splitIndex + ", waveFreq=" + this.waveFreq + ", wavePhase=" + this.wavePhase + ", waveEnabled=" + this.waveEnabled + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.height);
        result = result * 31 + Arrays.hashCode(this.spans);
        result = result * 31 + Integer.hashCode(this.spanCount);
        result = result * 31 + Float.hashCode(this.innerRadius);
        result = result * 31 + Float.hashCode(this.radiusTopLeft);
        result = result * 31 + Float.hashCode(this.radiusTopRight);
        result = result * 31 + Float.hashCode(this.radiusBottomRight);
        result = result * 31 + Float.hashCode(this.radiusBottomLeft);
        result = result * 31 + Integer.hashCode(this.color);
        result = result * 31 + Float.hashCode(this.globalAlpha);
        result = result * 31 + Float.hashCode(this.fresnelPower);
        result = result * 31 + Integer.hashCode(this.fresnelColor);
        result = result * 31 + Float.hashCode(this.baseAlpha);
        result = result * 31 + Boolean.hashCode(this.fresnelInvert);
        result = result * 31 + Float.hashCode(this.fresnelMix);
        result = result * 31 + Float.hashCode(this.distortStrength);
        result = result * 31 + Float.hashCode(this.squirt);
        result = result * 31 + Float.hashCode(this.z);
        result = result * 31 + Float.hashCode(this.blurRadius);
        result = result * 31 + Integer.hashCode(this.secondColor);
        result = result * 31 + Float.hashCode(this.colorOffset);
        result = result * 31 + Float.hashCode(this.leftAligned);
        result = result * 31 + Float.hashCode(this.bottomAnchored);
        result = result * 31 + Integer.hashCode(this.splitIndex);
        result = result * 31 + Float.hashCode(this.waveFreq);
        result = result * 31 + Float.hashCode(this.wavePhase);
        result = result * 31 + Boolean.hashCode(this.waveEnabled);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltShape)) {
            return false;
        }
        BuiltShape builtShape = (BuiltShape)other;
        if (Float.compare(this.x, builtShape.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtShape.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtShape.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtShape.height) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.spans, (Object)builtShape.spans)) {
            return false;
        }
        if (this.spanCount != builtShape.spanCount) {
            return false;
        }
        if (Float.compare(this.innerRadius, builtShape.innerRadius) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtShape.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtShape.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtShape.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtShape.radiusBottomLeft) != 0) {
            return false;
        }
        if (this.color != builtShape.color) {
            return false;
        }
        if (Float.compare(this.globalAlpha, builtShape.globalAlpha) != 0) {
            return false;
        }
        if (Float.compare(this.fresnelPower, builtShape.fresnelPower) != 0) {
            return false;
        }
        if (this.fresnelColor != builtShape.fresnelColor) {
            return false;
        }
        if (Float.compare(this.baseAlpha, builtShape.baseAlpha) != 0) {
            return false;
        }
        if (this.fresnelInvert != builtShape.fresnelInvert) {
            return false;
        }
        if (Float.compare(this.fresnelMix, builtShape.fresnelMix) != 0) {
            return false;
        }
        if (Float.compare(this.distortStrength, builtShape.distortStrength) != 0) {
            return false;
        }
        if (Float.compare(this.squirt, builtShape.squirt) != 0) {
            return false;
        }
        if (Float.compare(this.z, builtShape.z) != 0) {
            return false;
        }
        if (Float.compare(this.blurRadius, builtShape.blurRadius) != 0) {
            return false;
        }
        if (this.secondColor != builtShape.secondColor) {
            return false;
        }
        if (Float.compare(this.colorOffset, builtShape.colorOffset) != 0) {
            return false;
        }
        if (Float.compare(this.leftAligned, builtShape.leftAligned) != 0) {
            return false;
        }
        if (Float.compare(this.bottomAnchored, builtShape.bottomAnchored) != 0) {
            return false;
        }
        if (this.splitIndex != builtShape.splitIndex) {
            return false;
        }
        if (Float.compare(this.waveFreq, builtShape.waveFreq) != 0) {
            return false;
        }
        if (Float.compare(this.wavePhase, builtShape.wavePhase) != 0) {
            return false;
        }
        return this.waveEnabled == builtShape.waveEnabled;
    }

    @JvmStatic
    @NotNull
    public static final BuiltShape downwardTriangle(float x, float y, float width, float height, int color) {
        return Companion.downwardTriangle(x, y, width, height, color);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J;\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/utils/render/render2d/shape/BuiltShape.Companion;", "", "<init>", "()V", "", "x", "y", "width", "height", "", "color", "Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "Lkotlin/jvm/JvmStatic;", "downwardTriangle", "(FFFFI)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "DEFAULT_BLUR_RADIUS", "F", "", "NO_SPANS", "[F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final BuiltShape downwardTriangle(float x, float y, float width, float height, int color) {
            return new BuiltShape(x, y, width, height, NO_SPANS, -1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, color, 1.0f, 1.0f, color, 1.0f, false, 1.0f, 0.0f, 1.0f, 0.0f, 0.1f, color, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, false, 0x38000000, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

