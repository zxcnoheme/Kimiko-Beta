/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.render2d.ClientSplits;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.arcrect.BuiltArcRect;
import rtx.kimiko.utils.render.render2d.glass.BuiltGlass;
import rtx.kimiko.utils.render.render2d.glow.BuiltGlow;
import rtx.kimiko.utils.render.render2d.radialglass.BuiltRadialGlass;
import rtx.kimiko.utils.render.render2d.shape.BuiltShape;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0010\u0014\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003Jk\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u0011H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0003J7\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ=\u0010 \u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b \u0010!JE\u0010 \u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b \u0010\"JM\u0010 \u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b \u0010#J3\u0010 \u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b \u0010$J;\u0010 \u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b \u0010%JC\u0010 \u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b \u0010&JK\u0010 \u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b \u0010(JC\u0010)\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b)\u0010&JK\u0010*\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b*\u0010(JC\u0010+\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b+\u0010&JC\u0010,\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b,\u0010&J+\u0010.\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b.\u0010/Jc\u00105\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b5\u00106JC\u00107\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b7\u0010&JW\u0010:\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b:\u0010;Jk\u0010A\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bA\u0010BJs\u0010H\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bH\u0010IJK\u0010O\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bO\u0010(JS\u0010O\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bO\u0010QJS\u0010V\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u00042\u0006\u0010S\u001a\u00020\u00042\u0006\u0010T\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bV\u0010QJc\u0010V\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u00042\u0006\u0010S\u001a\u00020\u00042\u0006\u0010T\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\n2\u0006\u0010X\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bV\u0010YJs\u0010_\u001a\u00020\u00112\u0006\u0010Z\u001a\u00020\u00042\u0006\u0010[\u001a\u00020\u00042\u0006\u0010\\\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010^\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b_\u0010`J\u0093\u0001\u0010m\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010b\u001a\u00020a2\u0006\u0010c\u001a\u00020a2\u0006\u0010d\u001a\u00020\n2\u0006\u0010e\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u001b2\u0006\u0010h\u001a\u00020\u001b2\b\b\u0002\u0010i\u001a\u00020\u00042\b\b\u0002\u0010j\u001a\u00020\u00042\b\b\u0002\u0010k\u001a\u00020\n2\b\b\u0002\u0010l\u001a\u00020\nH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bm\u0010nJM\u0010q\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\b\u0010o\u001a\u0004\u0018\u00010a2\u0006\u0010p\u001a\u00020\nH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\bq\u0010rJa\u0010x\u001a\u00020\u00112\b\u0010t\u001a\u0004\u0018\u00010s2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010u\u001a\u00020\n2\u0006\u0010v\u001a\u00020\n2\u0006\u0010w\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bx\u0010yJi\u0010z\u001a\u00020\u00112\b\u0010t\u001a\u0004\u0018\u00010s2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010u\u001a\u00020\n2\u0006\u0010v\u001a\u00020\n2\u0006\u0010w\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bz\u0010{J\u0097\u0001\u0010|\u001a\u00020\u00112\b\u0010t\u001a\u0004\u0018\u00010s2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010u\u001a\u00020\n2\u0006\u0010v\u001a\u00020\n2\u0006\u0010w\u001a\u00020\u00042\b\u0010o\u001a\u0004\u0018\u00010a2\u0006\u0010p\u001a\u00020\n2\u0006\u0010g\u001a\u00020\u001b2\u0006\u0010h\u001a\u00020\u001b2\b\b\u0002\u0010i\u001a\u00020\u00042\b\b\u0002\u0010j\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b|\u0010}J\u0018\u0010\u007f\u001a\u00020\u00042\u0006\u0010~\u001a\u00020\u0004H\u0002\u00a2\u0006\u0005\b\u007f\u0010\u0080\u0001J\u0016\u0010\u0081\u0001\u001a\u00020\u0004H\u0007b\u0002\b\u0012\u00a2\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001b\u0010\u0084\u0001\u001a\u00020\u00042\u0007\u0010\u0083\u0001\u001a\u00020\u001bH\u0002\u00a2\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u001b\u0010\u0087\u0001\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00020\u001bH\u0002\u00a2\u0006\u0006\b\u0087\u0001\u0010\u0085\u0001J\u001b\u0010\u0088\u0001\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00020\u001bH\u0002\u00a2\u0006\u0006\b\u0088\u0001\u0010\u0085\u0001J\u001a\u0010\u0089\u0001\u001a\u00020\u00042\u0006\u0010~\u001a\u00020\u0004H\u0002\u00a2\u0006\u0006\b\u0089\u0001\u0010\u0080\u0001R\u0017\u0010\u008a\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0017\u0010\u008c\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u008b\u0001R\u0017\u0010\u008d\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008b\u0001R\u0017\u0010\u008e\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008b\u0001R\u0017\u0010\u008f\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u008b\u0001R\u0017\u0010\u0090\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u008b\u0001R\u0017\u0010\u0091\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u008b\u0001R\u0017\u0010\u0092\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u008b\u0001R\u0017\u0010\u0093\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u008b\u0001R\u0017\u0010\u0094\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u008b\u0001R\u0017\u0010\u0095\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u008b\u0001R\u0017\u0010\u0096\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u008b\u0001R\u0017\u0010\u0097\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u008b\u0001R\u0017\u0010\u0098\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u008b\u0001R\u0017\u0010\u0099\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u008b\u0001R\u0017\u0010\u009a\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u008b\u0001R\u0017\u0010\u009b\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u008b\u0001R\u0017\u0010\u009c\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u008b\u0001R\u0017\u0010\u009d\u0001\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0018\u0010\u00a0\u0001\u001a\u00030\u009f\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u00a1\u0001R\u0018\u0010\u00a2\u0001\u001a\u00030\u009f\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u00a1\u0001R\u0018\u0010\u00a4\u0001\u001a\u00030\u00a3\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u00a5\u0001R\u0018\u0010\u00a6\u0001\u001a\u00030\u00a3\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u00a5\u0001R\u0018\u0010\u00a7\u0001\u001a\u00030\u00a3\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a7\u0001\u0010\u00a5\u0001R\u0018\u0010\u00a8\u0001\u001a\u00030\u00a3\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u00a5\u0001R\u0018\u0010\u00a9\u0001\u001a\u00030\u00a3\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a9\u0001\u0010\u00a5\u0001R\u0018\u0010\u00aa\u0001\u001a\u00030\u00a3\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00aa\u0001\u0010\u00a5\u0001R\u0018\u0010\u00ac\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u00ad\u0001R\u0018\u0010\u00af\u0001\u001a\u00030\u00ae\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00af\u0001\u0010\u00b0\u0001R\u0018\u0010\u00b1\u0001\u001a\u00030\u00ae\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00b0\u0001R\u0018\u0010\u00b2\u0001\u001a\u00030\u00ae\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00b0\u0001R\u0019\u0010\u00b3\u0001\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00b4\u0001R\u0019\u0010\u00b5\u0001\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b5\u0001\u0010\u00b4\u0001R\u0019\u0010\u00b6\u0001\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u00b4\u0001R\u0017\u0010w\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bw\u0010\u008b\u0001R\u001a\u0010\u00b7\u0001\u001a\u00030\u009f\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00a1\u0001R\u0019\u0010\u00b8\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b8\u0001\u0010\u009e\u0001R\u0019\u0010\u00b9\u0001\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b9\u0001\u0010\u00b4\u0001R\u0019\u0010\u00ba\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ba\u0001\u0010\u008b\u0001R\u0019\u0010»\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b»\u0001\u0010\u008b\u0001R\u0019\u0010\u00bc\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bc\u0001\u0010\u008b\u0001R\u0019\u0010\u00bd\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bd\u0001\u0010\u008b\u0001R\u0019\u0010\u00be\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00be\u0001\u0010\u008b\u0001R\u0019\u0010\u00bf\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bf\u0001\u0010\u009e\u0001R\u0019\u0010\u00c0\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c0\u0001\u0010\u008b\u0001R\u0019\u0010\u00c1\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c1\u0001\u0010\u008b\u0001R\u0019\u0010\u00c2\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c2\u0001\u0010\u008b\u0001R\u0019\u0010\u00c3\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c3\u0001\u0010\u008b\u0001R\u0019\u0010\u00c4\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c4\u0001\u0010\u008b\u0001\u00a8\u0006\u00c5\u0001"}, d2={"Lrtx/kimiko/utils/render/others/RectUtil;", "", "<init>", "()V", "", "x", "y", "width", "height", "radius", "", "splitIndex", "childX", "childY", "childW", "childH", "childRadius", "", "Lkotlin/jvm/JvmStatic;", "armSplitOverride", "(FFFFFIFFFFF)V", "clearSplitOverride", "panelX", "panelY", "panelW", "panelH", "alpha", "", "consumeSplitOverride", "(FFFFF)Z", "Lnet/minecraft/DrawContext;", "graphics", "drawClientRect", "(Lnet/minecraft/DrawContext;FFFF)V", "(Lnet/minecraft/DrawContext;FFFFF)V", "(Lnet/minecraft/DrawContext;FFFFFF)V", "(FFFF)V", "(FFFFF)V", "(FFFFFF)V", "radiusBonus", "(FFFFFFF)V", "drawClientRectNoGlow", "drawClientRectFixedRadius", "drawClientRectFixedRadiusNoGlow", "drawClientWindow", "requestedRadius", "clientWindowRadius", "(FFF)F", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "lift", "drawGlassCard", "(FFFFFFFFFF)V", "drawClientGlowOnly", "withGlow", "fixedRadius", "drawClientRectImpl", "(FFFFFFFZZ)V", "tailCenterX", "tailHalfWidth", "tailHeight", "tailRound", "blend", "drawClientRectWithTail", "(FFFFFFFFFFF)V", "bubbleCenterX", "bubbleHalfWidth", "bubbleHalfHeight", "bubbleRadius", "bubbleRise", "drawClientRectBubble", "(FFFFFFFFFFFF)V", "centerX", "centerY", "thickness", "startDegrees", "sweepDegrees", "drawClientArc", "feather", "(FFFFFFFF)V", "innerRadius", "outerRadius", "midAngleDegrees", "corner", "drawClientSector", "highlightColor", "highlight", "(FFFFFFFFIF)V", "aabbX", "aabbY", "aabbWidth", "aabbHeight", "cut", "drawClientRectSplit", "(FFFFFFIZFFFF)V", "", "rowWidths", "rowHeights", "rowCountIn", "padTop", "padBottom", "leftAligned", "bottomAnchored", "waveFreq", "wavePhase", "waveColorA", "waveColorB", "drawClientShape", "(FF[F[FIFFFFZZFFII)V", "spans", "spanCount", "drawClientGlowSpans", "(FFFFF[FI)V", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "module", "primaryColor", "secondaryColor", "colorOffset", "drawGlow", "(Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;FFFFFFIIF)V", "drawGlowSplit", "(Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;FFFFFFIIFI)V", "drawGlowWithSpans", "(Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;FFFFFFIIF[FIZZFF)V", "value", "clamp", "(F)F", "clientColorOffset", "()F", "movementEnabled", "updateColorOffset", "(Z)F", "enabled", "updateSecondColorBlend", "updateGlowBlend", "normalizeOffset", "DEFAULT_RADIUS", "F", "ROW_JOIN_OVERLAP", "DEFAULT_BLUR_RADIUS", "DEFAULT_BACKDROP_OPACITY", "DEFAULT_GLASS_OPACITY", "DEFAULT_FILL_OPACITY", "DEFAULT_EDGE_STRENGTH", "DEFAULT_EDGE_SHARPNESS", "DEFAULT_REFRACTION", "DEFAULT_LIQUID_CURVE", "TAIL_EDGE_PAD", "CARD_GLASS_OPACITY", "CARD_GLASS_LIFT", "CARD_FILL_OPACITY", "CARD_FILL_LIFT", "CARD_BLUR_SCALE", "CARD_EDGE_BOOST", "CARD_REFRACTION_SCALE", "DEFAULT_COLOR", "I", "", "COLOR_MOVEMENT_PERIOD_MS", "J", "MAX_COLOR_FRAME_DELTA_MS", "", "COLOR_MOVEMENT_ACCEL_SECONDS", "D", "COLOR_MOVEMENT_DECEL_SECONDS", "SECOND_COLOR_FADE_IN_SECONDS", "SECOND_COLOR_FADE_OUT_SECONDS", "GLOW_FADE_IN_SECONDS", "GLOW_FADE_OUT_SECONDS", "Lrtx/kimiko/utils/animations/Easing;", "COLOR_EASING", "Lrtx/kimiko/utils/animations/Easing;", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "colorMovementSpeed", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "secondColorBlend", "glowBlend", "colorMovementTarget", "Z", "secondColorTarget", "glowTarget", "lastColorMovementMs", "lastSecondColor", "splitOverrideArmed", "splitOverrideX", "splitOverrideY", "splitOverrideW", "splitOverrideH", "splitOverrideRadius", "splitOverrideIndex", "splitOverrideChildX", "splitOverrideChildY", "splitOverrideChildW", "splitOverrideChildH", "splitOverrideChildRadius", "rtx.kimiko:kimiko"})
public final class RectUtil {
    @NotNull
    public static final RectUtil INSTANCE = new RectUtil();
    private static final float DEFAULT_RADIUS = 7.0f;
    private static final float ROW_JOIN_OVERLAP = 0.75f;
    private static final float DEFAULT_BLUR_RADIUS = 18.0f;
    private static final float DEFAULT_BACKDROP_OPACITY = 1.0f;
    private static final float DEFAULT_GLASS_OPACITY = 1.0f;
    private static final float DEFAULT_FILL_OPACITY = 1.0f;
    private static final float DEFAULT_EDGE_STRENGTH = 0.18f;
    private static final float DEFAULT_EDGE_SHARPNESS = 55.0f;
    private static final float DEFAULT_REFRACTION = 0.3f;
    private static final float DEFAULT_LIQUID_CURVE = 0.5f;
    private static final float TAIL_EDGE_PAD = 2.0f;
    private static final float CARD_GLASS_OPACITY = 0.34f;
    private static final float CARD_GLASS_LIFT = 0.16f;
    private static final float CARD_FILL_OPACITY = 0.46f;
    private static final float CARD_FILL_LIFT = 0.14f;
    private static final float CARD_BLUR_SCALE = 0.42f;
    private static final float CARD_EDGE_BOOST = 1.35f;
    private static final float CARD_REFRACTION_SCALE = 0.55f;
    private static final int DEFAULT_COLOR = -857872385;
    private static final long COLOR_MOVEMENT_PERIOD_MS = 2400L;
    private static final long MAX_COLOR_FRAME_DELTA_MS = 100L;
    private static final double COLOR_MOVEMENT_ACCEL_SECONDS = 0.45;
    private static final double COLOR_MOVEMENT_DECEL_SECONDS = 0.7;
    private static final double SECOND_COLOR_FADE_IN_SECONDS = 0.3;
    private static final double SECOND_COLOR_FADE_OUT_SECONDS = 0.42;
    private static final double GLOW_FADE_IN_SECONDS = 0.5;
    private static final double GLOW_FADE_OUT_SECONDS = 0.5;
    @NotNull
    private static final Easing COLOR_EASING = RectUtil::COLOR_EASING$lambda$0;
    @NotNull
    private static final SmoothAnimation colorMovementSpeed = new SmoothAnimation();
    @NotNull
    private static final SmoothAnimation secondColorBlend = new SmoothAnimation();
    @NotNull
    private static final SmoothAnimation glowBlend = new SmoothAnimation();
    private static boolean colorMovementTarget;
    private static boolean secondColorTarget;
    private static boolean glowTarget;
    private static float colorOffset;
    private static long lastColorMovementMs;
    private static int lastSecondColor;
    private static boolean splitOverrideArmed;
    private static float splitOverrideX;
    private static float splitOverrideY;
    private static float splitOverrideW;
    private static float splitOverrideH;
    private static float splitOverrideRadius;
    private static int splitOverrideIndex;
    private static float splitOverrideChildX;
    private static float splitOverrideChildY;
    private static float splitOverrideChildW;
    private static float splitOverrideChildH;
    private static float splitOverrideChildRadius;

    private RectUtil() {
    }

    @JvmStatic
    public static final void armSplitOverride(float x, float y, float width, float height, float radius, int splitIndex, float childX, float childY, float childW, float childH, float childRadius) {
        splitOverrideArmed = splitIndex > 0;
        splitOverrideX = x;
        splitOverrideY = y;
        splitOverrideW = width;
        splitOverrideH = height;
        splitOverrideRadius = radius;
        splitOverrideIndex = splitIndex;
        splitOverrideChildX = childX;
        splitOverrideChildY = childY;
        splitOverrideChildW = childW;
        splitOverrideChildH = childH;
        splitOverrideChildRadius = childRadius;
    }

    @JvmStatic
    public static final void clearSplitOverride() {
        splitOverrideArmed = false;
        splitOverrideIndex = 0;
    }

    private final boolean consumeSplitOverride(float panelX, float panelY, float panelW, float panelH, float alpha) {
        if (!splitOverrideArmed) {
            return false;
        }
        splitOverrideArmed = false;
        RectUtil.drawClientRectSplit(splitOverrideX, splitOverrideY, splitOverrideW, splitOverrideH, splitOverrideRadius, alpha, splitOverrideIndex, false, panelX, panelY, panelW, panelH);
        return true;
    }

    @JvmStatic
    public static final void drawClientRect(@Nullable DrawContext graphics, float x, float y, float width, float height) {
        RectUtil.drawClientRect(graphics, x, y, width, height, 7.0f, 1.0f);
    }

    @JvmStatic
    public static final void drawClientRect(@Nullable DrawContext graphics, float x, float y, float width, float height, float alpha) {
        RectUtil.drawClientRect(graphics, x, y, width, height, 7.0f, alpha);
    }

    @JvmStatic
    public static final void drawClientRect(@Nullable DrawContext graphics, float x, float y, float width, float height, float radius, float alpha) {
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, width, height, radius, alpha);
        Render2D.flush();
    }

    @JvmStatic
    public static final void drawClientRect(float x, float y, float width, float height) {
        RectUtil.drawClientRect(x, y, width, height, 7.0f, 1.0f);
    }

    @JvmStatic
    public static final void drawClientRect(float x, float y, float width, float height, float alpha) {
        RectUtil.drawClientRect(x, y, width, height, 7.0f, alpha);
    }

    @JvmStatic
    public static final void drawClientRect(float x, float y, float width, float height, float radius, float alpha) {
        RectUtil.drawClientRect(x, y, width, height, radius, alpha, 0.0f);
    }

    @JvmStatic
    public static final void drawClientRect(float x, float y, float width, float height, float radius, float alpha, float radiusBonus) {
        INSTANCE.drawClientRectImpl(x, y, width, height, radius, alpha, radiusBonus, true, false);
    }

    @JvmStatic
    public static final void drawClientRectNoGlow(float x, float y, float width, float height, float radius, float alpha) {
        INSTANCE.drawClientRectImpl(x, y, width, height, radius, alpha, 0.0f, false, false);
    }

    @JvmStatic
    public static final void drawClientRectFixedRadius(float x, float y, float width, float height, float radius, float alpha, float radiusBonus) {
        INSTANCE.drawClientRectImpl(x, y, width, height, radius, alpha, radiusBonus, true, true);
    }

    @JvmStatic
    public static final void drawClientRectFixedRadiusNoGlow(float x, float y, float width, float height, float radius, float alpha) {
        INSTANCE.drawClientRectImpl(x, y, width, height, radius, alpha, 0.0f, false, true);
    }

    @JvmStatic
    public static final void drawClientWindow(float x, float y, float width, float height, float radius, float alpha) {
        if (width <= 0.0f || height <= 0.0f || alpha <= 0.0f) {
            return;
        }
        INSTANCE.drawClientRectImpl(x, y, width, height, radius, INSTANCE.clamp(alpha), 0.0f, true, false);
    }

    @JvmStatic
    public static final float clientWindowRadius(float requestedRadius, float width, float height) {
        InterfaceModule module;
        InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
        float configuredRadius = interfaceModule == null ? requestedRadius : interfaceModule.rectCornerRadius.getFloat();
        return Math.max(0.0f, Math.min(configuredRadius, Math.min(width, height) * 0.5f));
    }

    @JvmStatic
    public static final void drawGlassCard(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float alpha, float lift) {
        int n;
        boolean secondColorEnabled;
        if (width <= 0.0f || height <= 0.0f || alpha <= 0.0f) {
            return;
        }
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float a = INSTANCE.clamp(alpha);
        float l = Math.max(0.0f, Math.min(1.0f, lift));
        float sampleX = x + width * 0.5f;
        float sampleY = y + height * 0.5f;
        InterfaceModule interfaceModule = module;
        int primaryColor = interfaceModule == null ? -857872385 : interfaceModule.clientPrimaryColorAt(sampleX, sampleY);
        boolean bl = secondColorEnabled = module != null && module.usesSecondClientColor();
        if (secondColorEnabled) {
            InterfaceModule interfaceModule2 = module;
            Intrinsics.checkNotNull((Object)interfaceModule2);
            lastSecondColor = interfaceModule2.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        if (secondColorEnabled) {
            InterfaceModule interfaceModule3 = module;
            Intrinsics.checkNotNull((Object)interfaceModule3);
            n = interfaceModule3.clientSecondaryColorAt(sampleX, sampleY);
        } else {
            n = lastSecondColor;
        }
        int secondFade = n;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module != null && module.clientColorMovement());
        InterfaceModule interfaceModule4 = module;
        float baseBlur = interfaceModule4 == null ? 18.0f : interfaceModule4.rectBackdropBlur.getFloat();
        InterfaceModule interfaceModule5 = module;
        float edgeStrength = (interfaceModule5 == null ? 0.18f : interfaceModule5.rectEdgeStrength.getFloat()) * 1.35f;
        InterfaceModule interfaceModule6 = module;
        float edgeSharpness = interfaceModule6 == null ? 55.0f : interfaceModule6.rectEdgeSharpness.getFloat();
        InterfaceModule interfaceModule7 = module;
        float refraction = (interfaceModule7 == null ? 0.3f : interfaceModule7.rectRefractionStrength.getFloat()) * 0.55f;
        float maxR = Math.min(width, height) * 0.5f;
        BuiltGlass glass = new BuiltGlass(x, y, width, height, Math.min(maxR, radiusTopLeft), Math.min(maxR, radiusTopRight), Math.min(maxR, radiusBottomRight), Math.min(maxR, radiusBottomLeft), primaryColor, a * (0.34f + 0.16f * l), edgeSharpness, primaryColor, a * (0.46f + 0.14f * l), true, edgeStrength, refraction, 0.5f, 0.0f).withBlurRadius(baseBlur * 0.42f).withSecondColor(secondaryColor, animatedColorOffset);
        Render2D.glass(glass);
    }

    @JvmStatic
    public static final void drawClientGlowOnly(float x, float y, float width, float height, float radius, float alpha) {
        if (width <= 0.0f || height <= 0.0f || alpha <= 0.0f) {
            return;
        }
        InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
        if (interfaceModule == null) {
            return;
        }
        InterfaceModule module = interfaceModule;
        float a = INSTANCE.clamp(alpha);
        float sampleX = x + width * 0.5f;
        float sampleY = y + height * 0.5f;
        int primaryColor = module.clientPrimaryColorAt(sampleX, sampleY);
        boolean secondColorEnabled = module.usesSecondClientColor();
        if (secondColorEnabled) {
            lastSecondColor = module.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        int secondFade = secondColorEnabled ? module.clientSecondaryColorAt(sampleX, sampleY) : lastSecondColor;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module.clientColorMovement());
        INSTANCE.drawGlow(module, x, y, width, height, radius, a, primaryColor, secondaryColor, animatedColorOffset);
    }

    private final void drawClientRectImpl(float x, float y, float width, float height, float radius, float alpha, float radiusBonus, boolean withGlow, boolean fixedRadius) {
        if (width <= 0.0f || height <= 0.0f || alpha <= 0.0f) {
            return;
        }
        if (this.consumeSplitOverride(x, y, width, height, this.clamp(alpha))) {
            return;
        }
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float a = this.clamp(alpha);
        float configuredRadius = ((fixedRadius || module == null) ? radius : module.rectCornerRadius.getFloat()) + radiusBonus;
        float r = Math.max(0.0f, Math.min(configuredRadius, Math.min(width, height) * 0.5f));
        float backdropBlur = module == null ? 18.0f : module.rectBackdropBlur.getFloat();
        float backdropOpacity = 1.0f;
        float glassOpacity = 1.0f;
        float fillOpacity = 1.0f;
        float sampleX = x + width * 0.5f;
        float sampleY = y + height * 0.5f;
        int primaryColor = module == null ? -857872385 : module.clientPrimaryColorAt(sampleX, sampleY);
        boolean secondColorEnabled = module != null && module.usesSecondClientColor();
        if (secondColorEnabled && module != null) {
            RectUtil.lastSecondColor = module.clientSecondaryColor();
        }
        float secondColorProgress = this.updateSecondColorBlend(secondColorEnabled);
        int secondFade = (secondColorEnabled && module != null) ? module.clientSecondaryColorAt(sampleX, sampleY) : RectUtil.lastSecondColor;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = this.updateColorOffset(module != null && module.clientColorMovement());
        float edgeStrength = module == null ? 0.18f : module.rectEdgeStrength.getFloat();
        float edgeSharpness = module == null ? 55.0f : module.rectEdgeSharpness.getFloat();
        float refraction = module == null ? 0.3f : module.rectRefractionStrength.getFloat();
        float liquidCurve = 0.5f;
        if (withGlow) {
            this.drawGlow(module, x, y, width, height, r, a, primaryColor, secondaryColor, animatedColorOffset);
        }
        BuiltGlass glass = new BuiltGlass(x, y, width, height, r, r, r, r, primaryColor, a * glassOpacity, edgeSharpness, primaryColor, fillOpacity * backdropOpacity, true, edgeStrength, refraction, liquidCurve, 0.0f).withBlurRadius(backdropBlur).withSecondColor(secondaryColor, animatedColorOffset);
        Render2D.glass(glass);
    }

    @JvmStatic
    public static final void drawClientRectWithTail(float x, float y, float width, float height, float radius, float alpha, float tailCenterX, float tailHalfWidth, float tailHeight, float tailRound, float blend) {
        int n;
        boolean secondColorEnabled;
        if (width <= 0.0f || height <= 0.0f || alpha <= 0.0f) {
            return;
        }
        int split = ClientSplits.addTail(width * 0.5f, height * 0.5f, width * 0.5f, height * 0.5f, tailCenterX - x - tailHalfWidth, height, tailHalfWidth, tailHeight, tailRound, blend);
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float a = INSTANCE.clamp(alpha);
        float r = Math.max(0.0f, Math.min(radius, Math.min(width, height) * 0.5f));
        float sampleX = x + width * 0.5f;
        float sampleY = y + height * 0.5f;
        InterfaceModule interfaceModule = module;
        int primaryColor = interfaceModule == null ? -857872385 : interfaceModule.clientPrimaryColorAt(sampleX, sampleY);
        boolean bl = secondColorEnabled = module != null && module.usesSecondClientColor();
        if (secondColorEnabled) {
            InterfaceModule interfaceModule2 = module;
            Intrinsics.checkNotNull((Object)interfaceModule2);
            lastSecondColor = interfaceModule2.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        if (secondColorEnabled) {
            InterfaceModule interfaceModule3 = module;
            Intrinsics.checkNotNull((Object)interfaceModule3);
            n = interfaceModule3.clientSecondaryColorAt(sampleX, sampleY);
        } else {
            n = lastSecondColor;
        }
        int secondFade = n;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module != null && module.clientColorMovement());
        InterfaceModule interfaceModule4 = module;
        InterfaceModule interfaceModule5 = module;
        InterfaceModule interfaceModule6 = module;
        InterfaceModule interfaceModule7 = module;
        BuiltGlass glass = new BuiltGlass(x, y, width, height + tailHeight + 2.0f, r, r, r, r, primaryColor, a * 1.0f, interfaceModule4 == null ? 55.0f : interfaceModule4.rectEdgeSharpness.getFloat(), primaryColor, 1.0f, true, interfaceModule5 == null ? 0.18f : interfaceModule5.rectEdgeStrength.getFloat(), interfaceModule6 == null ? 0.3f : interfaceModule6.rectRefractionStrength.getFloat(), 0.5f, 0.0f).withBlurRadius(interfaceModule7 == null ? 18.0f : interfaceModule7.rectBackdropBlur.getFloat()).withSecondColor(secondaryColor, animatedColorOffset).withSplitIndex(split);
        Render2D.glass(glass);
    }

    @JvmStatic
    public static final void drawClientRectBubble(float x, float y, float width, float height, float radius, float alpha, float bubbleCenterX, float bubbleHalfWidth, float bubbleHalfHeight, float bubbleRadius, float bubbleRise, float blend) {
        int n;
        boolean secondColorEnabled;
        if (width <= 0.0f || height <= 0.0f || alpha <= 0.0f) {
            return;
        }
        if (bubbleHalfWidth <= 0.0f || bubbleHalfHeight <= 0.0f || bubbleRise <= 0.01f) {
            RectUtil.drawClientRect(x, y, width, height, radius, alpha);
            return;
        }
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float a = INSTANCE.clamp(alpha);
        InterfaceModule interfaceModule = module;
        float configuredRadius = interfaceModule == null ? radius : interfaceModule.rectCornerRadius.getFloat();
        float r = Math.max(0.0f, Math.min(configuredRadius, Math.min(width, height) * 0.5f));
        float bubbleCenterY = y + bubbleHalfHeight - bubbleRise;
        float pad = blend + 2.0f;
        float left = Math.min(x, bubbleCenterX - bubbleHalfWidth) - pad;
        float top = Math.min(y, bubbleCenterY - bubbleHalfHeight) - pad;
        float right = Math.max(x + width, bubbleCenterX + bubbleHalfWidth) + pad;
        float bottom = Math.max(y + height, bubbleCenterY + bubbleHalfHeight) + pad;
        float aabbW = right - left;
        float aabbH = bottom - top;
        int split = ClientSplits.setBubble(x + width * 0.5f - left, y + height * 0.5f - top, width * 0.5f, height * 0.5f, bubbleCenterX - left, bubbleCenterY - top, bubbleHalfWidth, bubbleHalfHeight, Math.min(bubbleRadius, Math.min(bubbleHalfWidth, bubbleHalfHeight)), blend);
        InterfaceModule interfaceModule2 = module;
        float backdropBlur = interfaceModule2 == null ? 18.0f : interfaceModule2.rectBackdropBlur.getFloat();
        float sampleX = x + width * 0.5f;
        float sampleY = y + height * 0.5f;
        InterfaceModule interfaceModule3 = module;
        int primaryColor = interfaceModule3 == null ? -857872385 : interfaceModule3.clientPrimaryColorAt(sampleX, sampleY);
        boolean bl = secondColorEnabled = module != null && module.usesSecondClientColor();
        if (secondColorEnabled) {
            InterfaceModule interfaceModule4 = module;
            Intrinsics.checkNotNull((Object)interfaceModule4);
            lastSecondColor = interfaceModule4.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        if (secondColorEnabled) {
            InterfaceModule interfaceModule5 = module;
            Intrinsics.checkNotNull((Object)interfaceModule5);
            n = interfaceModule5.clientSecondaryColorAt(sampleX, sampleY);
        } else {
            n = lastSecondColor;
        }
        int secondFade = n;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module != null && module.clientColorMovement());
        InterfaceModule interfaceModule6 = module;
        float edgeStrength = interfaceModule6 == null ? 0.18f : interfaceModule6.rectEdgeStrength.getFloat();
        InterfaceModule interfaceModule7 = module;
        float edgeSharpness = interfaceModule7 == null ? 55.0f : interfaceModule7.rectEdgeSharpness.getFloat();
        InterfaceModule interfaceModule8 = module;
        float refraction = interfaceModule8 == null ? 0.3f : interfaceModule8.rectRefractionStrength.getFloat();
        INSTANCE.drawGlowSplit(module, left, top, aabbW, aabbH, r, a, primaryColor, secondaryColor, animatedColorOffset, split);
        BuiltGlass glass = new BuiltGlass(left, top, aabbW, aabbH, r, r, r, r, primaryColor, a * 1.0f, edgeSharpness, primaryColor, 1.0f, true, edgeStrength, refraction, 0.5f, 0.0f).withBlurRadius(backdropBlur).withSecondColor(secondaryColor, animatedColorOffset).withSplitIndex(split);
        Render2D.glass(glass);
    }

    @JvmStatic
    public static final void drawClientArc(float centerX, float centerY, float radius, float thickness, float startDegrees, float sweepDegrees, float alpha) {
        RectUtil.drawClientArc(centerX, centerY, radius, thickness, startDegrees, sweepDegrees, alpha, 1.0f);
    }

    @JvmStatic
    public static final void drawClientArc(float centerX, float centerY, float radius, float thickness, float startDegrees, float sweepDegrees, float alpha, float feather) {
        float a = INSTANCE.clamp(alpha);
        if (a <= 0.0f || sweepDegrees <= 0.05f || radius <= 0.0f || thickness <= 0.01f) {
            return;
        }
        float alpha255 = a * 255.0f;
        Render2D.arcRect(new BuiltArcRect(centerX, centerY, radius, thickness, startDegrees, sweepDegrees, 0).withColors(ClientAccent.gradientColorAt(0.0f, alpha255, centerX, centerY), ClientAccent.gradientColorAt(0.6667f, alpha255, centerX, centerY), ClientAccent.gradientColorAt(0.6667f, alpha255, centerX, centerY), ClientAccent.gradientColorAt(0.0f, alpha255, centerX, centerY)).withFeather(feather));
    }

    @JvmStatic
    public static final void drawClientSector(float centerX, float centerY, float innerRadius, float outerRadius, float midAngleDegrees, float sweepDegrees, float corner, float alpha) {
        RectUtil.drawClientSector(centerX, centerY, innerRadius, outerRadius, midAngleDegrees, sweepDegrees, corner, alpha, 0, 0.0f);
    }

    @JvmStatic
    public static final void drawClientSector(float centerX, float centerY, float innerRadius, float outerRadius, float midAngleDegrees, float sweepDegrees, float corner, float alpha, int highlightColor, float highlight) {
        int n;
        boolean secondColorEnabled;
        if (outerRadius <= innerRadius || sweepDegrees <= 0.0f || alpha <= 0.0f) {
            return;
        }
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float a = INSTANCE.clamp(alpha);
        InterfaceModule interfaceModule = module;
        float backdropBlur = interfaceModule == null ? 18.0f : interfaceModule.rectBackdropBlur.getFloat();
        InterfaceModule interfaceModule2 = module;
        int primaryColor = interfaceModule2 == null ? -857872385 : interfaceModule2.clientPrimaryColorAt(centerX, centerY);
        boolean bl = secondColorEnabled = module != null && module.usesSecondClientColor();
        if (secondColorEnabled) {
            InterfaceModule interfaceModule3 = module;
            Intrinsics.checkNotNull((Object)interfaceModule3);
            lastSecondColor = interfaceModule3.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        if (secondColorEnabled) {
            InterfaceModule interfaceModule4 = module;
            Intrinsics.checkNotNull((Object)interfaceModule4);
            n = interfaceModule4.clientSecondaryColorAt(centerX, centerY);
        } else {
            n = lastSecondColor;
        }
        int secondFade = n;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module != null && module.clientColorMovement());
        InterfaceModule interfaceModule5 = module;
        float edgeStrength = interfaceModule5 == null ? 0.18f : interfaceModule5.rectEdgeStrength.getFloat();
        InterfaceModule interfaceModule6 = module;
        float edgeSharpness = interfaceModule6 == null ? 55.0f : interfaceModule6.rectEdgeSharpness.getFloat();
        InterfaceModule interfaceModule7 = module;
        float refraction = interfaceModule7 == null ? 0.3f : interfaceModule7.rectRefractionStrength.getFloat();
        Render2D.radialGlass(new BuiltRadialGlass(centerX, centerY, innerRadius, outerRadius, (float)Math.toRadians(midAngleDegrees), (float)Math.toRadians(sweepDegrees) * 0.5f, corner, 1.0f, primaryColor, secondaryColor, animatedColorOffset, a * 1.0f, edgeSharpness, primaryColor, 1.0f, true, edgeStrength, refraction, backdropBlur, highlightColor, highlight, 0.0f));
    }

    @JvmStatic
    public static final void drawClientRectSplit(float aabbX, float aabbY, float aabbWidth, float aabbHeight, float radius, float alpha, int splitIndex, boolean cut, float panelX, float panelY, float panelW, float panelH) {
        int n;
        boolean secondColorEnabled;
        if (aabbWidth <= 0.0f || aabbHeight <= 0.0f || alpha <= 0.0f || splitIndex <= 0) {
            return;
        }
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float a = INSTANCE.clamp(alpha);
        float r = Math.max(0.0f, radius);
        InterfaceModule interfaceModule = module;
        float backdropBlur = interfaceModule == null ? 18.0f : interfaceModule.rectBackdropBlur.getFloat();
        float sampleX = aabbX + aabbWidth * 0.5f;
        float sampleY = aabbY + aabbHeight * 0.5f;
        InterfaceModule interfaceModule2 = module;
        int primaryColor = interfaceModule2 == null ? -857872385 : interfaceModule2.clientPrimaryColorAt(sampleX, sampleY);
        boolean bl = secondColorEnabled = module != null && module.usesSecondClientColor();
        if (secondColorEnabled) {
            InterfaceModule interfaceModule3 = module;
            Intrinsics.checkNotNull((Object)interfaceModule3);
            lastSecondColor = interfaceModule3.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        if (secondColorEnabled) {
            InterfaceModule interfaceModule4 = module;
            Intrinsics.checkNotNull((Object)interfaceModule4);
            n = interfaceModule4.clientSecondaryColorAt(sampleX, sampleY);
        } else {
            n = lastSecondColor;
        }
        int secondFade = n;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module != null && module.clientColorMovement());
        InterfaceModule interfaceModule5 = module;
        float edgeStrength = interfaceModule5 == null ? 0.18f : interfaceModule5.rectEdgeStrength.getFloat();
        InterfaceModule interfaceModule6 = module;
        float edgeSharpness = interfaceModule6 == null ? 55.0f : interfaceModule6.rectEdgeSharpness.getFloat();
        InterfaceModule interfaceModule7 = module;
        float refraction = interfaceModule7 == null ? 0.3f : interfaceModule7.rectRefractionStrength.getFloat();
        INSTANCE.drawGlow(module, panelX, panelY, panelW, panelH, r, a, primaryColor, secondaryColor, animatedColorOffset);
        if (splitOverrideChildW > 1.0f && splitOverrideChildH > 1.0f) {
            INSTANCE.drawGlow(module, splitOverrideChildX, splitOverrideChildY, splitOverrideChildW, splitOverrideChildH, splitOverrideChildRadius, a, primaryColor, secondaryColor, animatedColorOffset);
        }
        BuiltGlass glass = new BuiltGlass(aabbX, aabbY, aabbWidth, aabbHeight, r, r, r, r, primaryColor, a * 1.0f, edgeSharpness, primaryColor, 1.0f, true, edgeStrength, refraction, 0.5f, 0.0f).withBlurRadius(backdropBlur).withSecondColor(secondaryColor, animatedColorOffset).withSplitIndex(cut ? -splitIndex : splitIndex);
        Render2D.glass(glass);
    }

    @JvmStatic
    public static final void drawClientShape(float x, float y, @NotNull float[] rowWidths, @NotNull float[] rowHeights, int rowCountIn, float padTop, float padBottom, float radius, float alpha, boolean leftAligned, boolean bottomAnchored, float waveFreq, float wavePhase, int waveColorA, int waveColorB) {
        float foldedPhase;
        int n;
        boolean secondColorEnabled;
        Intrinsics.checkNotNullParameter((Object)rowWidths, (String)"rowWidths");
        Intrinsics.checkNotNullParameter((Object)rowHeights, (String)"rowHeights");
        int rowCount = rowCountIn;
        if (rowCount <= 0 || alpha <= 0.0f) {
            return;
        }
        boolean split = false;
        float splitX = 0.0f;
        float splitY = 0.0f;
        float splitW = 0.0f;
        float splitH = 0.0f;
        int splitIdx = 0;
        float budX = 0.0f;
        float budY = 0.0f;
        float budW = 0.0f;
        float budH = 0.0f;
        float budRadius = 0.0f;
        if (splitOverrideArmed) {
            splitOverrideArmed = false;
            split = true;
            splitX = splitOverrideX;
            splitY = splitOverrideY;
            splitW = splitOverrideW;
            splitH = splitOverrideH;
            splitIdx = splitOverrideIndex;
            budX = splitOverrideChildX;
            budY = splitOverrideChildY;
            budW = splitOverrideChildW;
            budH = splitOverrideChildH;
            budRadius = splitOverrideChildRadius;
        }
        float width = 0.0f;
        float rowsHeight = 0.0f;
        int n2 = rowCount;
        for (int i = 0; i < n2; ++i) {
            width = Math.max(width, rowWidths[i]);
            rowsHeight += rowHeights[i];
        }
        float height = rowsHeight + padTop + padBottom;
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float a = INSTANCE.clamp(alpha);
        InterfaceModule interfaceModule = module;
        float configuredRadius = interfaceModule == null ? radius : interfaceModule.rectCornerRadius.getFloat();
        float r = Math.max(0.0f, Math.min(configuredRadius, Math.min(width, height) * 0.5f));
        InterfaceModule interfaceModule2 = module;
        float backdropBlur = interfaceModule2 == null ? 18.0f : interfaceModule2.rectBackdropBlur.getFloat();
        float backdropOpacity = 1.0f;
        float glassOpacity = 1.0f;
        float fillOpacity = 1.0f;
        float sampleX = x + width * 0.5f;
        float sampleY = y + height * 0.5f;
        InterfaceModule interfaceModule3 = module;
        int primaryColor = interfaceModule3 == null ? -857872385 : interfaceModule3.clientPrimaryColorAt(sampleX, sampleY);
        boolean bl = secondColorEnabled = module != null && module.usesSecondClientColor();
        if (secondColorEnabled) {
            InterfaceModule interfaceModule4 = module;
            Intrinsics.checkNotNull((Object)interfaceModule4);
            lastSecondColor = interfaceModule4.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        if (secondColorEnabled) {
            InterfaceModule interfaceModule5 = module;
            Intrinsics.checkNotNull((Object)interfaceModule5);
            n = interfaceModule5.clientSecondaryColorAt(sampleX, sampleY);
        } else {
            n = lastSecondColor;
        }
        int secondFade = n;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module != null && module.clientColorMovement());
        InterfaceModule interfaceModule6 = module;
        float edgeStrength = interfaceModule6 == null ? 0.18f : interfaceModule6.rectEdgeStrength.getFloat();
        InterfaceModule interfaceModule7 = module;
        float edgeSharpness = interfaceModule7 == null ? 55.0f : interfaceModule7.rectEdgeSharpness.getFloat();
        InterfaceModule interfaceModule8 = module;
        float refraction = interfaceModule8 == null ? 0.3f : interfaceModule8.rectRefractionStrength.getFloat();
        float liquidCurve = 0.5f;
        boolean rowWave = waveFreq > 0.0f;
        float f = foldedPhase = rowWave ? wavePhase - y * waveFreq : 0.0f;
        if (rowWave) {
            int plateAlpha = primaryColor & 0xFF000000;
            primaryColor = plateAlpha | waveColorA & 0xFFFFFF;
            secondaryColor = plateAlpha | waveColorB & 0xFFFFFF;
        }
        float[] rowTop = new float[rowCount];
        float[] rowBottom = new float[rowCount];
        float cursorY = padTop;
        int n3 = rowCount;
        for (int i = 0; i < n3; ++i) {
            rowTop[i] = i == 0 ? 0.0f : cursorY;
            rowBottom[i] = i == rowCount - 1 ? height : (cursorY += rowHeights[i]);
        }
        float[] mergedLeft = new float[rowCount];
        float[] mergedRight = new float[rowCount];
        float[] mergedTop = new float[rowCount];
        float[] mergedBottom = new float[rowCount];
        int mergedCount = 0;
        int i = 0;
        while (i < rowCount) {
            int j = i;
            while (j + 1 < rowCount && Math.abs(rowWidths[j + 1] - rowWidths[i]) < 0.01f) {
                ++j;
            }
            float left = leftAligned ? 0.0f : width - rowWidths[i];
            float right = leftAligned ? rowWidths[i] : width;
            mergedLeft[mergedCount] = left;
            mergedRight[mergedCount] = right;
            mergedTop[mergedCount] = rowTop[i];
            mergedBottom[mergedCount] = rowBottom[j];
            ++mergedCount;
            i = j + 1;
        }
        float innerRadius = Math.min(r, rowsHeight / (float)Math.max(mergedCount, 1) * 0.5f);
        float topCornerGuard = mergedTop[0] + innerRadius;
        float bottomCornerGuard = mergedBottom[mergedCount - 1] - innerRadius;
        float[] spans = new float[mergedCount * 4];
        int n4 = mergedCount;
        for (int m = 0; m < n4; ++m) {
            float neighbourW;
            float top = mergedTop[m];
            float bottom = mergedBottom[m];
            float thisW = mergedRight[m] - mergedLeft[m];
            if (m > 0) {
                neighbourW = mergedRight[m - 1] - mergedLeft[m - 1];
                top -= neighbourW >= thisW - 0.01f ? Math.max(mergedBottom[m - 1] - mergedTop[m - 1], 0.75f) : 0.75f;
                top = Math.max(top, Math.min(topCornerGuard, mergedBottom[m - 1]));
            }
            if (m < mergedCount - 1) {
                neighbourW = mergedRight[m + 1] - mergedLeft[m + 1];
                bottom += neighbourW >= thisW - 0.01f ? Math.max(mergedBottom[m + 1] - mergedTop[m + 1], 0.75f) : 0.75f;
                bottom = Math.min(bottom, Math.max(bottomCornerGuard, mergedTop[m + 1]));
            }
            spans[m * 4] = mergedLeft[m];
            spans[m * 4 + 1] = mergedRight[m];
            spans[m * 4 + 2] = top;
            spans[m * 4 + 3] = bottom;
        }
        rowCount = mergedCount;
        INSTANCE.drawGlowWithSpans(module, x, y, width, height, innerRadius, a, primaryColor, secondaryColor, animatedColorOffset, spans, rowCount, leftAligned, bottomAnchored, rowWave ? waveFreq : 0.0f, foldedPhase);
        if (split && budW > 1.0f && budH > 1.0f) {
            INSTANCE.drawGlow(module, budX, budY, budW, budH, budRadius, a, primaryColor, secondaryColor, animatedColorOffset);
        }
        float shapeX = x;
        float shapeY = y;
        float shapeW = width;
        float shapeH = height;
        if (split) {
            shapeX = splitX;
            shapeY = splitY;
            shapeW = splitW;
            shapeH = splitH;
            float offX = x - splitX;
            float offY = y - splitY;
            float[] shifted = new float[spans.length];
            int n5 = rowCount;
            for (int s = 0; s < n5; ++s) {
                shifted[s * 4] = spans[s * 4] + offX;
                shifted[s * 4 + 1] = spans[s * 4 + 1] + offX;
                shifted[s * 4 + 2] = spans[s * 4 + 2] + offY;
                shifted[s * 4 + 3] = spans[s * 4 + 3] + offY;
            }
            spans = shifted;
        }
        BuiltShape shape = new BuiltShape(shapeX, shapeY, shapeW, shapeH, r, primaryColor, a * glassOpacity, edgeSharpness, primaryColor, fillOpacity * backdropOpacity, true, edgeStrength, refraction, liquidCurve, 0.0f).withSpans(spans, rowCount, innerRadius).withBlurRadius(backdropBlur).withSecondColor(secondaryColor, animatedColorOffset).withAlignment(leftAligned, bottomAnchored).withSplitIndex(split ? splitIdx : 0).withRowWave(rowWave ? waveFreq : 0.0f, foldedPhase);
        Render2D.shape(shape);
    }

    public static /* synthetic */ void drawClientShape$default(float f, float f2, float[] fArray, float[] fArray2, int n, float f3, float f4, float f5, float f6, boolean bl, boolean bl2, float f7, float f8, int n2, int n3, int n4, Object object) {
        if ((n4 & 0x800) != 0) {
            f7 = 0.0f;
        }
        if ((n4 & 0x1000) != 0) {
            f8 = 0.0f;
        }
        if ((n4 & 0x2000) != 0) {
            n2 = 0;
        }
        if ((n4 & 0x4000) != 0) {
            n3 = 0;
        }
        RectUtil.drawClientShape(f, f2, fArray, fArray2, n, f3, f4, f5, f6, bl, bl2, f7, f8, n2, n3);
    }

    @JvmStatic
    public static final void drawClientGlowSpans(float x, float y, float width, float height, float alpha, @Nullable float[] spans, int spanCount) {
        if (spanCount <= 0 || alpha <= 0.0f) {
            return;
        }
        InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
        if (interfaceModule == null) {
            return;
        }
        InterfaceModule module = interfaceModule;
        float glowProgress = INSTANCE.updateGlowBlend(module.rectGlow.getValue());
        if (glowProgress <= 0.001f) {
            return;
        }
        float intensity = module.rectGlowIntensity.getFloat();
        float glowRadius = module.rectGlowRadius.getFloat();
        if (intensity <= 0.0f || glowRadius <= 0.0f) {
            return;
        }
        float sampleX = x + width * 0.5f;
        float sampleY = y + height * 0.5f;
        int primaryColor = module.clientPrimaryColorAt(sampleX, sampleY);
        boolean secondColorEnabled = module.usesSecondClientColor();
        if (secondColorEnabled) {
            lastSecondColor = module.clientSecondaryColor();
        }
        float secondColorProgress = INSTANCE.updateSecondColorBlend(secondColorEnabled);
        int secondFade = secondColorEnabled ? module.clientSecondaryColorAt(sampleX, sampleY) : lastSecondColor;
        int secondaryColor = ColorEngine.lerpColor(primaryColor, secondFade, secondColorProgress);
        float animatedColorOffset = INSTANCE.updateColorOffset(module.clientColorMovement());
        float configuredRadius = module.rectCornerRadius.getFloat();
        float[] fArray = new float[]{configuredRadius, configuredRadius, configuredRadius, configuredRadius};
        float[] radii = fArray;
        BuiltGlow glow = new BuiltGlow(x, y, width, height, radii, primaryColor, intensity, glowRadius, INSTANCE.clamp(alpha) * glowProgress).withSecondColor(secondaryColor, animatedColorOffset).withSpans(spans, spanCount).withBoxesMode();
        Render2D.glow(glow);
    }

    private final void drawGlow(InterfaceModule module, float x, float y, float width, float height, float radius, float alpha, int primaryColor, int secondaryColor, float colorOffset) {
        RectUtil.drawGlowWithSpans$default(this, module, x, y, width, height, radius, alpha, primaryColor, secondaryColor, colorOffset, null, 0, true, false, 0.0f, 0.0f, 49152, null);
    }

    private final void drawGlowSplit(InterfaceModule module, float x, float y, float width, float height, float radius, float alpha, int primaryColor, int secondaryColor, float colorOffset, int splitIndex) {
        if (module == null || splitIndex == 0) {
            return;
        }
        float glowProgress = this.updateGlowBlend(module.rectGlow.getValue());
        if (glowProgress <= 0.001f) {
            return;
        }
        float intensity = module.rectGlowIntensity.getFloat();
        float glowRadius = module.rectGlowRadius.getFloat();
        if (intensity <= 0.0f || glowRadius <= 0.0f) {
            return;
        }
        float[] fArray = new float[]{radius, radius, radius, radius};
        float[] radii = fArray;
        BuiltGlow glow = new BuiltGlow(x, y, width, height, radii, primaryColor, intensity, glowRadius, alpha * glowProgress).withSecondColor(secondaryColor, colorOffset).withSplitIndex(splitIndex);
        Render2D.glow(glow);
    }

    private final void drawGlowWithSpans(InterfaceModule module, float x, float y, float width, float height, float radius, float alpha, int primaryColor, int secondaryColor, float colorOffset, float[] spans, int spanCount, boolean leftAligned, boolean bottomAnchored, float waveFreq, float wavePhase) {
        if (module == null) {
            return;
        }
        float glowProgress = this.updateGlowBlend(module.rectGlow.getValue());
        if (glowProgress <= 0.001f) {
            return;
        }
        float intensity = module.rectGlowIntensity.getFloat();
        float glowRadius = module.rectGlowRadius.getFloat();
        if (intensity <= 0.0f || glowRadius <= 0.0f) {
            return;
        }
        float[] fArray = new float[]{radius, radius, radius, radius};
        float[] radii = fArray;
        BuiltGlow glow = new BuiltGlow(x, y, width, height, radii, primaryColor, intensity, glowRadius, alpha * glowProgress).withSecondColor(secondaryColor, colorOffset);
        if (spans != null && spanCount > 0) {
            glow = glow.withSpans(spans, spanCount).withAlignment(leftAligned, bottomAnchored);
        }
        glow = glow.withRowWave(waveFreq, wavePhase);
        Render2D.glow(glow);
    }

    static /* synthetic */ void drawGlowWithSpans$default(RectUtil rectUtil, InterfaceModule interfaceModule, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, float f7, float[] fArray, int n3, boolean bl, boolean bl2, float f8, float f9, int n4, Object object) {
        if ((n4 & 0x4000) != 0) {
            f8 = 0.0f;
        }
        if ((n4 & 0x8000) != 0) {
            f9 = 0.0f;
        }
        rectUtil.drawGlowWithSpans(interfaceModule, f, f2, f3, f4, f5, f6, n, n2, f7, fArray, n3, bl, bl2, f8, f9);
    }

    private final float clamp(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }

    @JvmStatic
    public static final float clientColorOffset() {
        return colorOffset;
    }

    private final float updateColorOffset(boolean movementEnabled) {
        long now = System.currentTimeMillis();
        if (movementEnabled != colorMovementTarget) {
            colorMovementSpeed.run(movementEnabled ? 1.0 : 0.0, movementEnabled ? 0.45 : 0.7, COLOR_EASING, false);
            colorMovementTarget = movementEnabled;
        }
        colorMovementSpeed.update();
        if (lastColorMovementMs == 0L) {
            lastColorMovementMs = now;
            return colorOffset;
        }
        long elapsedMs = Math.min(Math.max(0L, now - lastColorMovementMs), 100L);
        lastColorMovementMs = now;
        float speed = this.clamp(colorMovementSpeed.get());
        colorOffset = this.normalizeOffset(colorOffset + (float)elapsedMs * speed / 2400.0f);
        return colorOffset;
    }

    private final float updateSecondColorBlend(boolean enabled) {
        if (enabled != secondColorTarget) {
            secondColorBlend.run(enabled ? 1.0 : 0.0, enabled ? 0.3 : 0.42, COLOR_EASING, false);
            secondColorTarget = enabled;
        }
        secondColorBlend.update();
        return this.clamp(secondColorBlend.get());
    }

    private final float updateGlowBlend(boolean enabled) {
        if (enabled != glowTarget) {
            glowBlend.run(enabled ? 1.0 : 0.0, enabled ? 0.5 : 0.5, COLOR_EASING, false);
            glowTarget = enabled;
        }
        glowBlend.update();
        return this.clamp(glowBlend.get());
    }

    private final float normalizeOffset(float value) {
        if (!(Math.abs(value) <= Float.MAX_VALUE)) {
            return 0.0f;
        }
        return value - (float)Math.floor(value);
    }

    private static final double COLOR_EASING$lambda$0(double value) {
        return value * value * (3.0 - 2.0 * value);
    }

    static {
        lastSecondColor = -857872385;
    }
}

