/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.glass;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.glass.GlassRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b0\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u0000 a2\u00020\u0001:\u0001aB\u00d3\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u000b\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u000b\u0012\u0006\u0010\u001b\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fB\u00b9\u0001\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u000b\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001e\u0010 B\u00b1\u0001\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u000b\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010!B\u0099\u0001\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\"B\u0083\u0001\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\u0010$\u001a\u0004\u0018\u00010#\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010%J\u0017\u0010)\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0011\u00a2\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0002\u00a2\u0006\u0004\b-\u0010.J\u0015\u0010/\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000b\u00a2\u0006\u0004\b/\u00100J\u0015\u00101\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u000b\u00a2\u0006\u0004\b1\u00100J\u001d\u00102\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0002\u00a2\u0006\u0004\b2\u00103J\u001d\u00106\u001a\u00020\u00002\u0006\u00104\u001a\u00020\u00022\u0006\u00105\u001a\u00020\u0002\u00a2\u0006\u0004\b6\u00107J\u0010\u00108\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b8\u00109J\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u00109J\u0010\u0010;\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b;\u00109J\u0010\u0010<\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b<\u00109J\u0010\u0010=\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b=\u00109J\u0010\u0010>\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b>\u00109J\u0010\u0010?\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b?\u00109J\u0010\u0010@\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b@\u00109J\u0010\u0010A\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bA\u0010BJ\u0010\u0010C\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bC\u00109J\u0010\u0010D\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bD\u00109J\u0010\u0010E\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bE\u0010BJ\u0010\u0010F\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bF\u00109J\u0010\u0010G\u001a\u00020\u0011H\u00c6\u0003\u00a2\u0006\u0004\bG\u0010,J\u0010\u0010H\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bH\u00109J\u0010\u0010I\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bI\u00109J\u0010\u0010J\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bJ\u00109J\u0010\u0010K\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bK\u00109J\u0010\u0010L\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bL\u00109J\u0010\u0010M\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bM\u0010BJ\u0010\u0010N\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bN\u00109J\u0010\u0010O\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bO\u0010BJ\u0010\u0010P\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bP\u0010BJ\u0010\u0010Q\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bQ\u00109J\u0010\u0010R\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bR\u00109J\u008a\u0002\u0010S\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\bS\u0010TJ\u001b\u0010V\u001a\u00020\u00112\b\u0010U\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bV\u0010WJ\u0011\u0010X\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\bX\u0010BJ\u0011\u0010Z\u001a\u00020YH\u00d6\u0081\u0004\u00a2\u0006\u0004\bZ\u0010[R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010^\u001a\u0004\b\u0003\u00109R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010^\u001a\u0004\b\u0004\u00109R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010^\u001a\u0004\b\u0005\u00109R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010^\u001a\u0004\b\u0006\u00109R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010^\u001a\u0004\b\u0007\u00109R%\u0010\b\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010^\u001a\u0004\b\b\u00109R%\u0010\t\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010^\u001a\u0004\b\t\u00109R%\u0010\n\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010^\u001a\u0004\b\n\u00109R%\u0010\f\u001a\u00020\u000b8\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010_\u001a\u0004\b\f\u0010BR%\u0010\r\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010^\u001a\u0004\b\r\u00109R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010^\u001a\u0004\b\u000e\u00109R%\u0010\u000f\u001a\u00020\u000b8\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010_\u001a\u0004\b\u000f\u0010BR%\u0010\u0010\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010^\u001a\u0004\b\u0010\u00109R%\u0010\u0012\u001a\u00020\u00118\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010`\u001a\u0004\b\u0012\u0010,R%\u0010\u0013\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010^\u001a\u0004\b\u0013\u00109R%\u0010\u0014\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010^\u001a\u0004\b\u0014\u00109R%\u0010\u0015\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010^\u001a\u0004\b\u0015\u00109R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010^\u001a\u0004\b\u0016\u00109R%\u0010\u0017\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010^\u001a\u0004\b\u0017\u00109R%\u0010\u0018\u001a\u00020\u000b8\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010_\u001a\u0004\b\u0018\u0010BR%\u0010\u0019\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010^\u001a\u0004\b\u0019\u00109R%\u0010\u001a\u001a\u00020\u000b8\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010_\u001a\u0004\b\u001a\u0010BR%\u0010\u001b\u001a\u00020\u000b8\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u001b\u00a2\u0006\f\n\u0004\b\u001b\u0010_\u001a\u0004\b\u001b\u0010BR%\u0010\u001c\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u001c\u00a2\u0006\f\n\u0004\b\u001c\u0010^\u001a\u0004\b\u001c\u00109R%\u0010\u001d\u001a\u00020\u00028\u0007z\f\b\\\u0012\b\b]\u0012\u0004\b\b(\u001d\u00a2\u0006\f\n\u0004\b\u001d\u0010^\u001a\u0004\b\u001d\u00109\u00a8\u0006b"}, d2={"Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "", "color", "globalAlpha", "fresnelPower", "fresnelColor", "baseAlpha", "", "fresnelInvert", "fresnelMix", "distortStrength", "squirt", "z", "blurRadius", "secondColor", "colorOffset", "splitIndex", "paletteSlot", "scissorFade", "scissorTop", "<init>", "(FFFFFFFFIFFIFZFFFFFIFIIFF)V", "(FFFFFFFFIFFIFZFFFFFIFI)V", "(FFFFFFFFIFFIFZFFFFFIF)V", "(FFFFFFFFIFFIFZFFFF)V", "", "radius", "(FFFF[FIFFIFZFFFF)V", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "visible", "()Z", "withBlurRadius", "(F)Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "withSplitIndex", "(I)Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "withPaletteSlot", "withSecondColor", "(IF)Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "spread", "saturation", "withRainbow", "(FF)Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "copy", "(FFFFFFFFIFFIFZFFFFFIFIIFF)Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltGlass {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float x;
    private final float y;
    private final float width;
    private final float height;
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
    private final int splitIndex;
    private final int paletteSlot;
    private final float scissorFade;
    private final float scissorTop;
    private static final float DEFAULT_BLUR_RADIUS = 30.0f;

    public BuiltGlass(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z, float blurRadius, int secondColor, float colorOffset, int splitIndex, int paletteSlot, float scissorFade, float scissorTop) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        this.splitIndex = splitIndex;
        this.paletteSlot = paletteSlot;
        this.scissorFade = scissorFade;
        this.scissorTop = scissorTop;
    }

    public /* synthetic */ BuiltGlass(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, float f9, float f10, int n2, float f11, boolean bl, float f12, float f13, float f14, float f15, float f16, int n3, float f17, int n4, int n5, float f18, float f19, int n6, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, f7, f8, n, f9, f10, n2, f11, bl, f12, f13, f14, f15, f16, n3, f17, n4, n5, (n6 & 0x800000) != 0 ? 0.0f : f18, (n6 & 0x1000000) != 0 ? 0.0f : f19);
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

    @JvmName(name="splitIndex")
    public final int splitIndex() {
        return this.splitIndex;
    }

    @JvmName(name="paletteSlot")
    public final int paletteSlot() {
        return this.paletteSlot;
    }

    @JvmName(name="scissorFade")
    public final float scissorFade() {
        return this.scissorFade;
    }

    @JvmName(name="scissorTop")
    public final float scissorTop() {
        return this.scissorTop;
    }

    public BuiltGlass(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z, float blurRadius, int secondColor, float colorOffset, int splitIndex) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, z, blurRadius, secondColor, colorOffset, splitIndex, 0, 0.0f, 0.0f, 0x1800000, null);
    }

    public BuiltGlass(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z, float blurRadius, int secondColor, float colorOffset) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, z, blurRadius, secondColor, colorOffset, 0, 0, 0.0f, 0.0f, 0x1800000, null);
    }

    public BuiltGlass(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, z, 30.0f, color, 0.0f);
    }

    public BuiltGlass(float x, float y, float width, float height, @Nullable float[] radius, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z) {
        this(x, y, width, height, BuiltGlass.Companion.radiusValue(radius, 0), BuiltGlass.Companion.radiusValue(radius, 1), BuiltGlass.Companion.radiusValue(radius, 2), BuiltGlass.Companion.radiusValue(radius, 3), color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, z);
    }

    public final void render(@Nullable DrawContext graphics) {
        GlassRenderer.Companion.getInstance().draw(graphics, this);
    }

    public final boolean visible() {
        return this.width > 0.0f && this.height > 0.0f && this.globalAlpha > 0.0f && (this.baseAlpha > 0.0f || this.fresnelColor >>> 24 != 0 || this.color >>> 24 != 0);
    }

    @NotNull
    public final BuiltGlass withBlurRadius(float blurRadius) {
        return new BuiltGlass(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, blurRadius, this.secondColor, this.colorOffset, this.splitIndex, this.paletteSlot, 0.0f, 0.0f, 0x1800000, null);
    }

    @NotNull
    public final BuiltGlass withSplitIndex(int splitIndex) {
        return new BuiltGlass(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, this.blurRadius, this.secondColor, this.colorOffset, splitIndex, this.paletteSlot, 0.0f, 0.0f, 0x1800000, null);
    }

    @NotNull
    public final BuiltGlass withPaletteSlot(int paletteSlot) {
        return new BuiltGlass(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, this.blurRadius, this.secondColor, this.colorOffset, this.splitIndex, paletteSlot, 0.0f, 0.0f, 0x1800000, null);
    }

    @NotNull
    public final BuiltGlass withSecondColor(int secondColor, float colorOffset) {
        return new BuiltGlass(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, this.z, this.blurRadius, secondColor, colorOffset, this.splitIndex, this.paletteSlot, 0.0f, 0.0f, 0x1800000, null);
    }

    @NotNull
    public final BuiltGlass withRainbow(float spread, float saturation) {
        int sr = Math.max(0, Math.min(255, Math.round(spread * 255.0f)));
        int sg = Math.max(0, Math.min(255, Math.round(saturation * 255.0f)));
        int packed = 0xFF000000 | sr << 16 | sg << 8;
        return new BuiltGlass(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.squirt, 1.0f, this.blurRadius, packed, this.colorOffset, this.splitIndex, this.paletteSlot, 0.0f, 0.0f, 0x1800000, null);
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

    public final float component5() {
        return this.radiusTopLeft;
    }

    public final float component6() {
        return this.radiusTopRight;
    }

    public final float component7() {
        return this.radiusBottomRight;
    }

    public final float component8() {
        return this.radiusBottomLeft;
    }

    public final int component9() {
        return this.color;
    }

    public final float component10() {
        return this.globalAlpha;
    }

    public final float component11() {
        return this.fresnelPower;
    }

    public final int component12() {
        return this.fresnelColor;
    }

    public final float component13() {
        return this.baseAlpha;
    }

    public final boolean component14() {
        return this.fresnelInvert;
    }

    public final float component15() {
        return this.fresnelMix;
    }

    public final float component16() {
        return this.distortStrength;
    }

    public final float component17() {
        return this.squirt;
    }

    public final float component18() {
        return this.z;
    }

    public final float component19() {
        return this.blurRadius;
    }

    public final int component20() {
        return this.secondColor;
    }

    public final float component21() {
        return this.colorOffset;
    }

    public final int component22() {
        return this.splitIndex;
    }

    public final int component23() {
        return this.paletteSlot;
    }

    public final float component24() {
        return this.scissorFade;
    }

    public final float component25() {
        return this.scissorTop;
    }

    @NotNull
    public final BuiltGlass copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z, float blurRadius, int secondColor, float colorOffset, int splitIndex, int paletteSlot, float scissorFade, float scissorTop) {
        return new BuiltGlass(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, z, blurRadius, secondColor, colorOffset, splitIndex, paletteSlot, scissorFade, scissorTop);
    }

    public static /* synthetic */ BuiltGlass copy$default(BuiltGlass builtGlass, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, float f9, float f10, int n2, float f11, boolean bl, float f12, float f13, float f14, float f15, float f16, int n3, float f17, int n4, int n5, float f18, float f19, int n6, Object object) {
        if ((n6 & 1) != 0) {
            f = builtGlass.x;
        }
        if ((n6 & 2) != 0) {
            f2 = builtGlass.y;
        }
        if ((n6 & 4) != 0) {
            f3 = builtGlass.width;
        }
        if ((n6 & 8) != 0) {
            f4 = builtGlass.height;
        }
        if ((n6 & 0x10) != 0) {
            f5 = builtGlass.radiusTopLeft;
        }
        if ((n6 & 0x20) != 0) {
            f6 = builtGlass.radiusTopRight;
        }
        if ((n6 & 0x40) != 0) {
            f7 = builtGlass.radiusBottomRight;
        }
        if ((n6 & 0x80) != 0) {
            f8 = builtGlass.radiusBottomLeft;
        }
        if ((n6 & 0x100) != 0) {
            n = builtGlass.color;
        }
        if ((n6 & 0x200) != 0) {
            f9 = builtGlass.globalAlpha;
        }
        if ((n6 & 0x400) != 0) {
            f10 = builtGlass.fresnelPower;
        }
        if ((n6 & 0x800) != 0) {
            n2 = builtGlass.fresnelColor;
        }
        if ((n6 & 0x1000) != 0) {
            f11 = builtGlass.baseAlpha;
        }
        if ((n6 & 0x2000) != 0) {
            bl = builtGlass.fresnelInvert;
        }
        if ((n6 & 0x4000) != 0) {
            f12 = builtGlass.fresnelMix;
        }
        if ((n6 & 0x8000) != 0) {
            f13 = builtGlass.distortStrength;
        }
        if ((n6 & 0x10000) != 0) {
            f14 = builtGlass.squirt;
        }
        if ((n6 & 0x20000) != 0) {
            f15 = builtGlass.z;
        }
        if ((n6 & 0x40000) != 0) {
            f16 = builtGlass.blurRadius;
        }
        if ((n6 & 0x80000) != 0) {
            n3 = builtGlass.secondColor;
        }
        if ((n6 & 0x100000) != 0) {
            f17 = builtGlass.colorOffset;
        }
        if ((n6 & 0x200000) != 0) {
            n4 = builtGlass.splitIndex;
        }
        if ((n6 & 0x400000) != 0) {
            n5 = builtGlass.paletteSlot;
        }
        if ((n6 & 0x800000) != 0) {
            f18 = builtGlass.scissorFade;
        }
        if ((n6 & 0x1000000) != 0) {
            f19 = builtGlass.scissorTop;
        }
        return builtGlass.copy(f, f2, f3, f4, f5, f6, f7, f8, n, f9, f10, n2, f11, bl, f12, f13, f14, f15, f16, n3, f17, n4, n5, f18, f19);
    }

    @NotNull
    public String toString() {
        return "BuiltGlass(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", color=" + this.color + ", globalAlpha=" + this.globalAlpha + ", fresnelPower=" + this.fresnelPower + ", fresnelColor=" + this.fresnelColor + ", baseAlpha=" + this.baseAlpha + ", fresnelInvert=" + this.fresnelInvert + ", fresnelMix=" + this.fresnelMix + ", distortStrength=" + this.distortStrength + ", squirt=" + this.squirt + ", z=" + this.z + ", blurRadius=" + this.blurRadius + ", secondColor=" + this.secondColor + ", colorOffset=" + this.colorOffset + ", splitIndex=" + this.splitIndex + ", paletteSlot=" + this.paletteSlot + ", scissorFade=" + this.scissorFade + ", scissorTop=" + this.scissorTop + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.height);
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
        result = result * 31 + Integer.hashCode(this.splitIndex);
        result = result * 31 + Integer.hashCode(this.paletteSlot);
        result = result * 31 + Float.hashCode(this.scissorFade);
        result = result * 31 + Float.hashCode(this.scissorTop);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltGlass)) {
            return false;
        }
        BuiltGlass builtGlass = (BuiltGlass)other;
        if (Float.compare(this.x, builtGlass.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtGlass.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtGlass.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtGlass.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtGlass.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtGlass.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtGlass.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtGlass.radiusBottomLeft) != 0) {
            return false;
        }
        if (this.color != builtGlass.color) {
            return false;
        }
        if (Float.compare(this.globalAlpha, builtGlass.globalAlpha) != 0) {
            return false;
        }
        if (Float.compare(this.fresnelPower, builtGlass.fresnelPower) != 0) {
            return false;
        }
        if (this.fresnelColor != builtGlass.fresnelColor) {
            return false;
        }
        if (Float.compare(this.baseAlpha, builtGlass.baseAlpha) != 0) {
            return false;
        }
        if (this.fresnelInvert != builtGlass.fresnelInvert) {
            return false;
        }
        if (Float.compare(this.fresnelMix, builtGlass.fresnelMix) != 0) {
            return false;
        }
        if (Float.compare(this.distortStrength, builtGlass.distortStrength) != 0) {
            return false;
        }
        if (Float.compare(this.squirt, builtGlass.squirt) != 0) {
            return false;
        }
        if (Float.compare(this.z, builtGlass.z) != 0) {
            return false;
        }
        if (Float.compare(this.blurRadius, builtGlass.blurRadius) != 0) {
            return false;
        }
        if (this.secondColor != builtGlass.secondColor) {
            return false;
        }
        if (Float.compare(this.colorOffset, builtGlass.colorOffset) != 0) {
            return false;
        }
        if (this.splitIndex != builtGlass.splitIndex) {
            return false;
        }
        if (this.paletteSlot != builtGlass.paletteSlot) {
            return false;
        }
        if (Float.compare(this.scissorFade, builtGlass.scissorFade) != 0) {
            return false;
        }
        return Float.compare(this.scissorTop, builtGlass.scissorTop) == 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass.Companion;", "", "<init>", "()V", "", "radius", "", "index", "", "radiusValue", "([FI)F", "DEFAULT_BLUR_RADIUS", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float radiusValue(float[] radius, int index) {
            if (radius == null || index < 0 || index >= radius.length) {
                return 0.0f;
            }
            return radius[index];
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

