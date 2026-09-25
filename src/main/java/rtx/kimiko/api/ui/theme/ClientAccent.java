/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.theme;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.Theme;
import rtx.kimiko.api.ui.theme.ThemeManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0099\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\tJ\u001b\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\tJ\u001b\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\tJ\u001b\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\tJ\u0013\u0010\u0010\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J3\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J+\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J+\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0019\u0010\u0018J+\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001a\u0010\u0018J+\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001b\u0010\u0018J+\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u0018J+\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001d\u0010\u0018J+\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001e\u0010\u0018J#\u0010 \u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b \u0010!J+\u0010%\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b%\u0010&J\u0013\u0010(\u001a\u00020'H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b(\u0010)J\u0013\u0010*\u001a\u00020'H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b*\u0010)J\u000f\u0010+\u001a\u00020'H\u0002\u00a2\u0006\u0004\b+\u0010)J\u000f\u0010,\u001a\u00020'H\u0002\u00a2\u0006\u0004\b,\u0010)J\u001f\u00100\u001a\u00020/2\u0006\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020'H\u0002\u00a2\u0006\u0004\b0\u00101J!\u00103\u001a\u00020\u00062\b\u0010-\u001a\u0004\u0018\u00010'2\u0006\u00102\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b3\u00104J\u0013\u00105\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b5\u0010\u0011J#\u00106\u001a\u00020'2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b6\u00107J#\u00108\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b8\u00109J3\u0010:\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b:\u0010;J'\u0010<\u001a\u00020\u00062\u0006\u0010-\u001a\u00020'2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b<\u0010=J'\u0010@\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b@\u0010&J\u001f\u0010A\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bA\u0010!J\u000f\u0010B\u001a\u00020/H\u0002\u00a2\u0006\u0004\bB\u0010\u0003J\u001f\u0010C\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bC\u0010!J\u0013\u0010E\u001a\u00020DH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bE\u0010FJ\u0013\u0010G\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bG\u0010HJ\u000f\u0010I\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bI\u0010HJ\u000f\u0010J\u001a\u00020/H\u0002\u00a2\u0006\u0004\bJ\u0010\u0003J\u001f\u0010M\u001a\u00020/2\u0006\u0010K\u001a\u00020'2\u0006\u0010L\u001a\u00020DH\u0002\u00a2\u0006\u0004\bM\u0010NJ\u000f\u0010O\u001a\u00020'H\u0002\u00a2\u0006\u0004\bO\u0010)J+\u0010S\u001a\u00020'2\u0006\u0010P\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u00062\u0006\u0010R\u001a\u00020DH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bS\u0010TJ\u0013\u0010U\u001a\u00020/H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bU\u0010\u0003J\u0013\u0010V\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bV\u0010HJ#\u0010X\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bX\u00109J\u000f\u0010Y\u001a\u00020'H\u0002\u00a2\u0006\u0004\bY\u0010)J\u0017\u0010[\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b[\u0010\\J\u000f\u0010]\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b]\u0010HJ\u000f\u0010_\u001a\u00020^H\u0002\u00a2\u0006\u0004\b_\u0010`J\u0017\u0010b\u001a\u00020^2\u0006\u0010a\u001a\u00020'H\u0002\u00a2\u0006\u0004\bb\u0010cJ/\u0010g\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bg\u0010\u0016J'\u0010h\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bh\u0010iJ\u0017\u0010j\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bj\u0010\\R\u0014\u0010l\u001a\u00020k8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010n\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0016\u0010q\u001a\u00020p8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0016\u0010s\u001a\u00020D8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010tR\u0016\u0010u\u001a\u00020^8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0016\u0010w\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020p8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010rR\u0016\u0010z\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010{R\u0016\u0010|\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010mR\u0016\u0010}\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010oR\u0016\u0010~\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010oR\u0016\u0010\u007f\u001a\u00020D8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010tR\u0016\u0010\u0080\u0001\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010oR\u0016\u0010\u0081\u0001\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010xR \u0010\u0083\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010'0\u0082\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0016\u0010\u0085\u0001\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010xR\u001a\u0010\u0086\u0001\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010xR\u0018\u0010\u0087\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010oR\u0018\u0010\u0088\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0088\u0001\u0010oR\u0018\u0010\u0089\u0001\u001a\u00020D8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010tR\u0018\u0010\u008a\u0001\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008a\u0001\u0010mR\u0018\u0010\u008b\u0001\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008b\u0001\u0010mR\u001a\u0010\u008c\u0001\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008c\u0001\u0010xR\u0016\u0010\u008d\u0001\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008d\u0001\u0010xR\u0018\u0010\u008e\u0001\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008e\u0001\u0010mR\u0018\u0010\u008f\u0001\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008f\u0001\u0010mR\u0016\u0010\u0090\u0001\u001a\u00020k8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0090\u0001\u0010mR\u0014\u0010Y\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010xR\u001a\u0010\u0092\u0001\u001a\u00030\u0091\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0018\u0010\u0094\u0001\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0094\u0001\u0010mR\u0018\u0010\u0095\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0095\u0001\u0010{R\u0018\u0010\u0096\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0096\u0001\u0010{R\u0018\u0010\u0097\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010{R\u0018\u0010\u0098\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0098\u0001\u0010{\u00a8\u0006\u009a\u0001"}, d2={"Lrtx/kimiko/api/ui/theme/ClientAccent;", "", "<init>", "()V", "", "alpha255", "", "Lkotlin/jvm/JvmStatic;", "accent", "(F)I", "accentBright", "accentSoft", "accentFill", "toggleOn", "gradientA", "gradientB", "accentOpaque", "()I", "index", "screenX", "screenY", "shadeAt", "(IFFF)I", "accentAt", "(FFF)I", "accentBrightAt", "accentSoftAt", "accentFillAt", "toggleOnAt", "gradientAAt", "gradientBAt", "rgb", "rgba", "(IF)I", "from", "to", "t", "mix", "(IIF)I", "", "blendedClientPalette", "()[I", "currentPalette", "computeClientPalette", "desiredPalette", "palette", "out", "", "resamplePalette", "([I[I)V", "pos", "samplePalette", "([IF)I", "gradientStops", "currentPaletteAt", "(FF)[I", "gradientColor", "(FF)I", "gradientColorAt", "(FFFF)I", "sampleGradient", "([IFF)I", "a", "b", "mixRgb", "shade", "computeFrameShades", "themeShade", "", "isModeTransitioning", "()Z", "closedFactor", "()F", "currentClosed", "refreshState", "desired", "intoRainbow", "beginTransition", "([IZ)V", "sampleThemeShades", "primaryArgb", "secondaryArgb", "useSecond", "shadesFromCustom", "(IIZ)[I", "beginFrame", "rainbowBaseHue", "hueOffset", "rainbowFlow", "rainbowShades", "v", "wrap", "(F)F", "progress", "", "blendedShades", "()[F", "shades", "unpack", "([I)[F", "satMul", "valMul", "valAdd", "lighten", "darken", "(IFF)I", "clamp01", "", "SWITCH_MS", "J", "SHADE_COUNT", "I", "Lrtx/kimiko/api/ui/theme/ClientAccent$State;", "state", "Lrtx/kimiko/api/ui/theme/ClientAccent$State;", "targetIsRainbow", "Z", "fromShades", "[F", "targetShades", "[I", "transitionTarget", "fromClosed", "F", "switchStart", "lastCustomPrimary", "lastCustomSecondary", "lastCustomSecondEnabled", "PALETTE_K", "displayedPaletteSamples", "", "transitionPalettes", "[[I", "fallbackPalette", "fromPaletteSamples", "fromPaletteCount", "lastDisplayedCount", "hasDisplayedPalette", "frameId", "paletteFrameId", "framePalette", "frameShades", "shadesFrameId", "refreshedFrameId", "RAINBOW_CYCLE_MS", "", "huePhase", "D", "hueLast", "frameHue", "shadesHue", "shadesSpread", "shadesSaturation", "State", "rtx.kimiko:kimiko"})
public final class ClientAccent {
    @NotNull
    public static final ClientAccent INSTANCE = new ClientAccent();
    private static final long SWITCH_MS = 500L;
    private static final int SHADE_COUNT = 7;
    @NotNull
    private static State state = State.THEMES;
    private static boolean targetIsRainbow;
    @NotNull
    private static float[] fromShades;
    @NotNull
    private static int[] targetShades;
    @NotNull
    private static State transitionTarget;
    private static float fromClosed;
    private static long switchStart;
    private static int lastCustomPrimary;
    private static int lastCustomSecondary;
    private static boolean lastCustomSecondEnabled;
    private static final int PALETTE_K = 6;
    @NotNull
    private static final int[] displayedPaletteSamples;
    @NotNull
    private static final int[][] transitionPalettes;
    @NotNull
    private static final int[] fallbackPalette;
    @Nullable
    private static int[] fromPaletteSamples;
    private static int fromPaletteCount;
    private static int lastDisplayedCount;
    private static boolean hasDisplayedPalette;
    private static long frameId;
    private static long paletteFrameId;
    @Nullable
    private static int[] framePalette;
    @NotNull
    private static final int[] frameShades;
    private static long shadesFrameId;
    private static long refreshedFrameId;
    private static final long RAINBOW_CYCLE_MS = 6000L;
    @NotNull
    private static final int[] rainbowShades;
    private static double huePhase;
    private static long hueLast;
    private static float frameHue;
    private static float shadesHue;
    private static float shadesSpread;
    private static float shadesSaturation;

    private ClientAccent() {
    }

    @JvmStatic
    public static final int accent(float alpha255) {
        return INSTANCE.shade(0, alpha255);
    }

    @JvmStatic
    public static final int accentBright(float alpha255) {
        return INSTANCE.shade(1, alpha255);
    }

    @JvmStatic
    public static final int accentSoft(float alpha255) {
        return INSTANCE.shade(2, alpha255);
    }

    @JvmStatic
    public static final int accentFill(float alpha255) {
        return INSTANCE.shade(3, alpha255);
    }

    @JvmStatic
    public static final int toggleOn(float alpha255) {
        return INSTANCE.shade(4, alpha255);
    }

    @JvmStatic
    public static final int gradientA(float alpha255) {
        return INSTANCE.shade(5, alpha255);
    }

    @JvmStatic
    public static final int gradientB(float alpha255) {
        return INSTANCE.shade(6, alpha255);
    }

    @JvmStatic
    public static final int accentOpaque() {
        return 0xFF000000 | INSTANCE.shade(0, 255.0f) & 0xFFFFFF;
    }

    @JvmStatic
    public static final int shadeAt(int index, float alpha255, float screenX, float screenY) {
        INSTANCE.refreshState();
        if (state == State.THEMES) {
            return ThemeManager.shadeAt(index, alpha255, screenX, screenY);
        }
        return INSTANCE.shade(index, alpha255);
    }

    @JvmStatic
    public static final int accentAt(float alpha255, float screenX, float screenY) {
        return ClientAccent.shadeAt(0, alpha255, screenX, screenY);
    }

    @JvmStatic
    public static final int accentBrightAt(float alpha255, float screenX, float screenY) {
        return ClientAccent.shadeAt(1, alpha255, screenX, screenY);
    }

    @JvmStatic
    public static final int accentSoftAt(float alpha255, float screenX, float screenY) {
        return ClientAccent.shadeAt(2, alpha255, screenX, screenY);
    }

    @JvmStatic
    public static final int accentFillAt(float alpha255, float screenX, float screenY) {
        return ClientAccent.shadeAt(3, alpha255, screenX, screenY);
    }

    @JvmStatic
    public static final int toggleOnAt(float alpha255, float screenX, float screenY) {
        return ClientAccent.shadeAt(4, alpha255, screenX, screenY);
    }

    @JvmStatic
    public static final int gradientAAt(float alpha255, float screenX, float screenY) {
        return ClientAccent.shadeAt(5, alpha255, screenX, screenY);
    }

    @JvmStatic
    public static final int gradientBAt(float alpha255, float screenX, float screenY) {
        return ClientAccent.shadeAt(6, alpha255, screenX, screenY);
    }

    @JvmStatic
    public static final int rgba(int rgb, float alpha255) {
        return ThemeManager.rgba(rgb, alpha255);
    }

    @JvmStatic
    public static final int mix(int from, int to, float t) {
        return ThemeManager.mix(from, to, t);
    }

    @JvmStatic
    @NotNull
    public static final int[] blendedClientPalette() {
        int[] cached = framePalette;
        if (paletteFrameId == frameId && cached != null) {
            return cached;
        }
        INSTANCE.refreshState();
        int[] result = INSTANCE.computeClientPalette();
        INSTANCE.resamplePalette(result, displayedPaletteSamples);
        hasDisplayedPalette = true;
        lastDisplayedCount = Math.max(1, result.length);
        framePalette = result;
        paletteFrameId = frameId;
        return result;
    }

    @JvmStatic
    @NotNull
    public static final int[] currentPalette() {
        int[] palette = ClientAccent.blendedClientPalette();
        if (palette.length == 0) {
            ClientAccent.fallbackPalette[0] = ClientAccent.accent(255.0f) & 0xFFFFFF;
            return fallbackPalette;
        }
        return palette;
    }

    private final int[] computeClientPalette() {
        float p;
        int[] desired = this.desiredPalette();
        int[] from = fromPaletteSamples;
        if (state == State.TRANSITION && from != null && (p = this.progress()) < 1.0f) {
            int n = Math.max(Math.max(1, fromPaletteCount), desired.length);
            int[] out = transitionPalettes[n];
            if (out == null) {
                out = new int[n];
                ClientAccent.transitionPalettes[n] = out;
            }
            for (int i = 0; i < n; ++i) {
                float pos = n <= 1 ? 0.0f : (float)i / (float)(n - 1);
                out[i] = this.mixRgb(this.samplePalette(from, pos), this.samplePalette(desired, pos), p);
            }
            return out;
        }
        return desired;
    }

    private final int[] desiredPalette() {
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        int[] palette = module != null ? module.clientPalette() : null;
        if (palette == null || palette.length == 0) {
            ClientAccent.fallbackPalette[0] = ClientAccent.accent(255.0f) & 0xFFFFFF;
            return fallbackPalette;
        }
        return palette;
    }

    private final void resamplePalette(int[] palette, int[] out) {
        int n = out.length;
        for (int i = 0; i < n; ++i) {
            float pos = out.length <= 1 ? 0.0f : (float)i / (float)(out.length - 1);
            out[i] = this.samplePalette(palette, pos);
        }
    }

    private final int samplePalette(int[] palette, float pos) {
        if (palette == null || palette.length == 0) {
            return 0;
        }
        if (palette.length == 1) {
            return palette[0] & 0xFFFFFF;
        }
        float f = this.clamp01(pos) * (float)(palette.length - 1);
        int i = (int)f;
        if (i > palette.length - 2) {
            i = palette.length - 2;
        }
        return this.mixRgb(palette[i], palette[i + 1], f - (float)i);
    }

    @JvmStatic
    public static final int gradientStops() {
        return Math.max(1, ClientAccent.currentPalette().length);
    }

    @JvmStatic
    @NotNull
    public static final int[] currentPaletteAt(float screenX, float screenY) {
        int[] settled = ClientAccent.currentPalette();
        if (state != State.THEMES) {
            return settled;
        }
        int[] positional = ThemeManager.blendedPaletteAt(screenX, screenY);
        return positional == null || positional.length == 0 ? settled : positional;
    }

    @JvmStatic
    public static final int gradientColor(float t, float alpha255) {
        return INSTANCE.sampleGradient(ClientAccent.currentPalette(), t, alpha255);
    }

    @JvmStatic
    public static final int gradientColorAt(float t, float alpha255, float screenX, float screenY) {
        return INSTANCE.sampleGradient(ClientAccent.currentPaletteAt(screenX, screenY), t, alpha255);
    }

    private final int sampleGradient(int[] palette, float t, float alpha255) {
        if (palette.length <= 1) {
            return ClientAccent.rgba(palette[0] & 0xFFFFFF, alpha255);
        }
        float f = this.clamp01(t) * (float)(palette.length - 1);
        int i = (int)Math.floor(f);
        if (i > palette.length - 2) {
            i = palette.length - 2;
        }
        return ClientAccent.rgba(this.mixRgb(palette[i], palette[i + 1], f - (float)i), alpha255);
    }

    private final int mixRgb(int a, int b, float t) {
        float tt = this.clamp01(t);
        int ar = a >> 16 & 0xFF;
        int ag = a >> 8 & 0xFF;
        int ab = a & 0xFF;
        int br = b >> 16 & 0xFF;
        int bg = b >> 8 & 0xFF;
        int bb = b & 0xFF;
        int r = MathKt.roundToInt((float)((float)ar + (float)(br - ar) * tt));
        int g = MathKt.roundToInt((float)((float)ag + (float)(bg - ag) * tt));
        int bl = MathKt.roundToInt((float)((float)ab + (float)(bb - ab) * tt));
        return r << 16 | g << 8 | bl;
    }

    private final int shade(int index, float alpha255) {
        this.refreshState();
        if (state == State.THEMES) {
            return this.themeShade(index, alpha255);
        }
        int a = Math.max(0, Math.min(255, MathKt.roundToInt((float)alpha255)));
        if (a <= 0) {
            return 0;
        }
        if (shadesFrameId != frameId) {
            this.computeFrameShades();
            shadesFrameId = frameId;
        }
        return a << 24 | frameShades[index];
    }

    private final void computeFrameShades() {
        int[] target;
        if (state == State.RAINBOW) {
            int[] shades = this.rainbowShades();
            for (int i = 0; i < 7; ++i) {
                ClientAccent.frameShades[i] = shades[i] & 0xFFFFFF;
            }
            return;
        }
        float t = this.progress();
        int[] nArray = target = targetIsRainbow ? this.rainbowShades() : targetShades;
        if (t >= 1.0f) {
            for (int i = 0; i < 7; ++i) {
                ClientAccent.frameShades[i] = target[i] & 0xFFFFFF;
            }
            return;
        }
        for (int i = 0; i < 7; ++i) {
            int base = i * 3;
            int tc = target[i];
            int r = MathKt.roundToInt((float)(fromShades[base] + ((float)(tc >>> 16 & 0xFF) - fromShades[base]) * t));
            int g = MathKt.roundToInt((float)(fromShades[base + 1] + ((float)(tc >>> 8 & 0xFF) - fromShades[base + 1]) * t));
            int b = MathKt.roundToInt((float)(fromShades[base + 2] + ((float)(tc & 0xFF) - fromShades[base + 2]) * t));
            ClientAccent.frameShades[i] = r << 16 | g << 8 | b;
        }
    }

    private final int themeShade(int index, float alpha255) {
        return switch (index) {
            case 0 -> ThemeManager.accent(alpha255);
            case 1 -> ThemeManager.accentBright(alpha255);
            case 2 -> ThemeManager.accentSoft(alpha255);
            case 3 -> ThemeManager.accentFill(alpha255);
            case 4 -> ThemeManager.toggleOn(alpha255);
            case 5 -> ThemeManager.gradientA(alpha255);
            default -> ThemeManager.gradientB(alpha255);
        };
    }

    @JvmStatic
    public static final boolean isModeTransitioning() {
        INSTANCE.refreshState();
        return state == State.TRANSITION && INSTANCE.progress() < 1.0f;
    }

    @JvmStatic
    public static final float closedFactor() {
        INSTANCE.refreshState();
        return INSTANCE.currentClosed();
    }

    private final float currentClosed() {
        if (state == State.RAINBOW) {
            return 1.0f;
        }
        if (state != State.TRANSITION) {
            return 0.0f;
        }
        float target = transitionTarget == State.RAINBOW ? 1.0f : 0.0f;
        return fromClosed + (target - fromClosed) * this.progress();
    }

    private final void refreshState() {
        boolean rainbow;
        if (refreshedFrameId == frameId) {
            return;
        }
        refreshedFrameId = frameId;
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        boolean themes = module == null || module.isThemeClientColor();
        boolean bl = rainbow = module != null && module.isRainbowClientColor();
        State desiredMode = themes ? State.THEMES : (rainbow ? State.RAINBOW : State.CUSTOM);
        int[] desiredShades = null;
        boolean intoRainbow = false;
        boolean customColorChanged = false;
        if (desiredMode == State.THEMES) {
            desiredShades = this.sampleThemeShades();
        } else if (desiredMode == State.RAINBOW) {
            desiredShades = this.rainbowShades();
            intoRainbow = true;
        } else {
            InterfaceModule interfaceModule = module;
            Intrinsics.checkNotNull((Object)interfaceModule);
            int primary = interfaceModule.rectColor.getColor();
            int secondary = module.rectSecondColor.getColor();
            boolean second = module.rectUseSecondColor.getValue();
            desiredShades = ClientAccent.shadesFromCustom(primary, secondary, second);
            customColorChanged = primary != lastCustomPrimary || secondary != lastCustomSecondary || second != lastCustomSecondEnabled;
            lastCustomPrimary = primary;
            lastCustomSecondary = secondary;
            lastCustomSecondEnabled = second;
        }
        if (state == desiredMode) {
            if (desiredMode == State.CUSTOM && customColorChanged) {
                this.beginTransition(desiredShades, false);
                transitionTarget = State.CUSTOM;
            }
            return;
        }
        if (state == State.TRANSITION && transitionTarget == desiredMode) {
            targetShades = desiredShades;
            targetIsRainbow = intoRainbow;
            if (this.progress() >= 1.0f) {
                state = desiredMode;
            }
            return;
        }
        this.beginTransition(desiredShades, intoRainbow);
        transitionTarget = desiredMode;
    }

    private final void beginTransition(int[] desired, boolean intoRainbow) {
        fromShades = this.blendedShades();
        fromClosed = this.currentClosed();
        if (hasDisplayedPalette) {
            fromPaletteSamples = (int[])displayedPaletteSamples.clone();
        } else {
            int[] samples = new int[6];
            this.resamplePalette(this.desiredPalette(), samples);
            fromPaletteSamples = samples;
        }
        fromPaletteCount = lastDisplayedCount;
        targetShades = desired;
        targetIsRainbow = intoRainbow;
        switchStart = System.currentTimeMillis();
        state = State.TRANSITION;
    }

    private final int[] sampleThemeShades() {
        int[] nArray = new int[]{ThemeManager.accent(255.0f) & 0xFFFFFF, ThemeManager.accentBright(255.0f) & 0xFFFFFF, ThemeManager.accentSoft(255.0f) & 0xFFFFFF, ThemeManager.accentFill(255.0f) & 0xFFFFFF, ThemeManager.toggleOn(255.0f) & 0xFFFFFF, ThemeManager.gradientA(255.0f) & 0xFFFFFF, ThemeManager.gradientB(255.0f) & 0xFFFFFF};
        return nArray;
    }

    @JvmStatic
    @NotNull
    public static final int[] shadesFromCustom(int primaryArgb, int secondaryArgb, boolean useSecond) {
        int primary = primaryArgb & 0xFFFFFF;
        int secondary = useSecond ? secondaryArgb & 0xFFFFFF : primary;
        int[] nArray = new int[]{primary, INSTANCE.lighten(primary, 0.65f, 1.15f, 0.1f), INSTANCE.lighten(primary, 0.45f, 1.25f, 0.15f), INSTANCE.darken(primary, 1.05f, 0.78f), INSTANCE.darken(primary, 1.1f, 0.55f), primary, secondary};
        return nArray;
    }

    @JvmStatic
    public static final void beginFrame() {
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float speed = module == null ? 1.0f : Math.max(0.1f, module.rainbowSpeed.getFloat());
        long now = System.currentTimeMillis();
        if (hueLast != 0L) {
            long dt = now - hueLast;
            if (dt < 0L) {
                dt = 0L;
            } else if (dt > 200L) {
                dt = 200L;
            }
            huePhase += (double)((float)dt * speed) / 6000.0;
            huePhase -= Math.floor(huePhase);
        }
        hueLast = now;
        frameHue = (float)huePhase;
        long l = frameId;
        frameId = l + 1L;
        paletteFrameId = Long.MIN_VALUE;
    }

    @JvmStatic
    public static final float rainbowBaseHue() {
        return frameHue;
    }

    @JvmStatic
    public static final int rainbowFlow(float hueOffset, float alpha255) {
        InterfaceModule module;
        InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
        float sat = interfaceModule == null ? 0.85f : INSTANCE.clamp01(interfaceModule.rainbowSaturation.getFloat());
        float hue = INSTANCE.wrap(frameHue + hueOffset);
        return ClientAccent.rgba(Color.HSBtoRGB(hue, sat, 1.0f) & 0xFFFFFF, alpha255);
    }

    private final int[] rainbowShades() {
        InterfaceModule module;
        InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
        float spread = interfaceModule == null ? 0.18f : interfaceModule.rainbowSpread.getFloat();
        InterfaceModule interfaceModule2 = module;
        float sat = interfaceModule2 == null ? 0.85f : interfaceModule2.rainbowSaturation.getFloat();
        float hue = frameHue;
        if (hue == shadesHue && spread == shadesSpread && sat == shadesSaturation) {
            return rainbowShades;
        }
        int primary = Color.HSBtoRGB(hue, sat, 1.0f) & 0xFFFFFF;
        int gradientB = Color.HSBtoRGB(this.wrap(hue + spread), sat, 1.0f) & 0xFFFFFF;
        ClientAccent.rainbowShades[0] = primary;
        ClientAccent.rainbowShades[1] = Color.HSBtoRGB(hue, this.clamp01(sat * 0.7f), 1.0f) & 0xFFFFFF;
        ClientAccent.rainbowShades[2] = Color.HSBtoRGB(hue, this.clamp01(sat * 0.5f), 1.0f) & 0xFFFFFF;
        ClientAccent.rainbowShades[3] = Color.HSBtoRGB(hue, sat, 0.8f) & 0xFFFFFF;
        ClientAccent.rainbowShades[4] = Color.HSBtoRGB(hue, sat, 0.6f) & 0xFFFFFF;
        ClientAccent.rainbowShades[5] = primary;
        ClientAccent.rainbowShades[6] = gradientB;
        shadesHue = hue;
        shadesSpread = spread;
        shadesSaturation = sat;
        return rainbowShades;
    }

    private final float wrap(float v) {
        return v - (float)Math.floor(v);
    }

    private final float progress() {
        float t = (float)(System.currentTimeMillis() - switchStart) / 500.0f;
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t >= 1.0f) {
            return 1.0f;
        }
        return t * t * (3.0f - 2.0f * t);
    }

    private final float[] blendedShades() {
        if (state == State.THEMES) {
            return this.unpack(this.sampleThemeShades());
        }
        float t = this.progress();
        int[] live = targetIsRainbow ? this.rainbowShades() : targetShades;
        float[] result = new float[21];
        for (int i = 0; i < 7; ++i) {
            int base = i * 3;
            int target = live[i];
            result[base] = fromShades[base] + ((float)(target >>> 16 & 0xFF) - fromShades[base]) * t;
            result[base + 1] = fromShades[base + 1] + ((float)(target >>> 8 & 0xFF) - fromShades[base + 1]) * t;
            result[base + 2] = fromShades[base + 2] + ((float)(target & 0xFF) - fromShades[base + 2]) * t;
        }
        return result;
    }

    private final float[] unpack(int[] shades) {
        float[] result = new float[21];
        for (int i = 0; i < 7; ++i) {
            result[i * 3] = shades[i] >>> 16 & 0xFF;
            result[i * 3 + 1] = shades[i] >>> 8 & 0xFF;
            result[i * 3 + 2] = shades[i] & 0xFF;
        }
        return result;
    }

    private final int lighten(int rgb, float satMul, float valMul, float valAdd) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb & 0xFF, hsb);
        return Color.HSBtoRGB(hsb[0], this.clamp01(hsb[1] * satMul), this.clamp01(hsb[2] * valMul + valAdd)) & 0xFFFFFF;
    }

    private final int darken(int rgb, float satMul, float valMul) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb & 0xFF, hsb);
        return Color.HSBtoRGB(hsb[0], this.clamp01(hsb[1] * satMul), hsb[2] * valMul) & 0xFFFFFF;
    }

    private final float clamp01(float v) {
        return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
    }

    static {
        fromShades = INSTANCE.unpack(Theme.KIMIKO.shades());
        targetShades = Theme.KIMIKO.shades();
        transitionTarget = State.THEMES;
        switchStart = System.currentTimeMillis() - (long)10000;
        displayedPaletteSamples = new int[6];
        transitionPalettes = new int[10][];
        int[] nArray = new int[]{0xFFFFFF};
        fallbackPalette = nArray;
        fromPaletteCount = 1;
        lastDisplayedCount = 1;
        paletteFrameId = Long.MIN_VALUE;
        frameShades = new int[7];
        shadesFrameId = Long.MIN_VALUE;
        refreshedFrameId = Long.MIN_VALUE;
        rainbowShades = new int[7];
        shadesHue = Float.NaN;
        shadesSpread = Float.NaN;
        shadesSaturation = Float.NaN;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/ui/theme/ClientAccent$State;", "", "<init>", "(Ljava/lang/String;I)V", "THEMES", "CUSTOM", "RAINBOW", "TRANSITION", "rtx.kimiko:kimiko"})
    private static enum State {
        THEMES,
        CUSTOM,
        RAINBOW,
        TRANSITION;
@NotNull
        public static EnumEntries<State> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

