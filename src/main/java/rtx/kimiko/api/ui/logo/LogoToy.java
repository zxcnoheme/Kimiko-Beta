/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.logo;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.ui.UiScale;
import rtx.kimiko.api.ui.logo.LogoTrail;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.image.ImageRenderer;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b>\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b;\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u000f\u0010\u0010\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0013\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0013J+\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ3\u0010 \u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010\u0003J\u0013\u0010#\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b#\u0010\u0003J#\u0010&\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b(\u0010)J\u001f\u0010*\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b*\u0010'J\u0017\u0010+\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b+\u0010)J\u0017\u0010,\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b,\u0010)J\u0017\u0010.\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b.\u0010/J\u001f\u00102\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b2\u00103J\u001f\u00104\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b4\u00103J;\u00106\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u0015H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b6\u00107J#\u00108\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u0015H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b8\u00109JG\u0010<\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b<\u0010=J'\u0010@\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u00152\u0006\u0010?\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b@\u0010AJ7\u0010F\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010B\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u00152\u0006\u0010E\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bF\u00107J\u001f\u0010G\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bG\u0010HJ\u001f\u0010I\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bI\u0010HJ\u000f\u0010J\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u0017\u0010M\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bM\u0010NJ'\u0010Q\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u00152\u0006\u0010O\u001a\u00020\u00152\u0006\u0010P\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bQ\u0010RJ\u0017\u0010T\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bT\u0010NJ\u0017\u0010U\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bU\u0010NJ\u0017\u0010V\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bV\u0010NJ/\u0010Z\u001a\u00020\u001d2\u0006\u0010W\u001a\u00020\u001d2\u0006\u0010X\u001a\u00020\u001d2\u0006\u0010Y\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010_\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0014\u0010a\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010`R\u0014\u0010b\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bb\u0010`R\u0014\u0010c\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bc\u0010`R\u0014\u0010d\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010f\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010eR\u0014\u0010g\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010eR\u0014\u0010h\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010eR\u0014\u0010i\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bi\u0010eR\u0014\u0010j\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010eR\u0014\u0010k\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bk\u0010eR\u0014\u0010l\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010eR\u0014\u0010m\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bm\u0010eR\u0014\u0010n\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bn\u0010eR\u0014\u0010o\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bo\u0010eR\u0014\u0010q\u001a\u00020p8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0014\u0010s\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bs\u0010eR\u0014\u0010t\u001a\u00020\\8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bt\u0010^R\u0014\u0010u\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bu\u0010eR\u0014\u0010v\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010eR\u0014\u0010w\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bw\u0010eR\u0014\u0010x\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bx\u0010eR\u0014\u0010y\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\by\u0010eR\u0014\u0010z\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bz\u0010eR\u0014\u0010{\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b{\u0010eR\u0014\u0010|\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010eR\u0014\u0010}\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010`R\u0014\u0010~\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b~\u0010`R\u0014\u0010\u007f\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u007f\u0010`R\u0016\u0010\u0080\u0001\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010eR\u0016\u0010\u0081\u0001\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010eR\u0016\u0010\u0082\u0001\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010eR\u0018\u0010\u0083\u0001\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010`R\u0018\u0010\u0084\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010eR\u0018\u0010\u0085\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010eR\u0018\u0010\u0086\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010eR\u0018\u0010\u0087\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010eR\u0018\u0010\u0088\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0088\u0001\u0010eR\u0018\u0010\u0089\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010eR\u0018\u0010\u008a\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008a\u0001\u0010eR\u0018\u0010\u008b\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008b\u0001\u0010eR\u0018\u0010\u008c\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008c\u0001\u0010eR\u0018\u0010\u008d\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008d\u0001\u0010eR\u0019\u0010\u008e\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0018\u0010\u0090\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0090\u0001\u0010eR\u0018\u0010\u0091\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0091\u0001\u0010eR\u0018\u0010\u0092\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0092\u0001\u0010eR\u0018\u0010\u0093\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0093\u0001\u0010eR\u0018\u0010\u0094\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0094\u0001\u0010eR\u0018\u0010\u0095\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0095\u0001\u0010eR\u0018\u0010\u0096\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0096\u0001\u0010eR\u0018\u0010\u0097\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010eR\u0018\u0010\u0098\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0098\u0001\u0010eR\u0018\u0010\u0099\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0099\u0001\u0010eR\u0018\u0010\u009a\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009a\u0001\u0010eR\u0018\u0010\u009b\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009b\u0001\u0010eR\u0018\u0010\u009c\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009c\u0001\u0010eR\u0018\u0010\u009d\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009d\u0001\u0010eR\u0018\u0010\u009e\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009e\u0001\u0010eR\u0018\u0010\u009f\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009f\u0001\u0010eR\u0018\u0010\u00a0\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a0\u0001\u0010eR\u0018\u0010\u00a1\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a1\u0001\u0010eR\u0018\u0010\u00a2\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a2\u0001\u0010eR\u0018\u0010\u00a3\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a3\u0001\u0010eR\u0018\u0010\u00a4\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a4\u0001\u0010eR\u0018\u0010\u00a5\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a5\u0001\u0010eR\u0018\u0010\u00a6\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a6\u0001\u0010eR\u0018\u0010\u00a7\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a7\u0001\u0010eR\u0018\u0010\u00a8\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a8\u0001\u0010eR\u0018\u0010\u00a9\u0001\u001a\u00020p8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a9\u0001\u0010rR\u0018\u0010\u00aa\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00aa\u0001\u0010eR\u0019\u0010«\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u008f\u0001R\u0018\u0010\u00ad\u0001\u001a\u00030\u00ac\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u00ae\u0001R\u0017\u0010\u00af\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00af\u0001\u0010\u00b0\u0001R\u0017\u0010\u00b1\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00b0\u0001R\u0017\u0010\u00b2\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00b0\u0001R\u0017\u0010\u00b3\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00b0\u0001R\u0017\u0010\u00b4\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00b0\u0001R\u0017\u0010\u00b5\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b5\u0001\u0010\u00b0\u0001R\u0017\u0010\u00b6\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u00b0\u0001R\u0017\u0010\u00b7\u0001\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00b0\u0001R\u0018\u0010\u00b8\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b8\u0001\u0010eR\u0019\u0010\u00b9\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b9\u0001\u0010\u008f\u0001R\u0018\u0010\u00ba\u0001\u001a\u00020p8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ba\u0001\u0010r\u00a8\u0006»\u0001"}, d2={"Lrtx/kimiko/api/ui/logo/LogoToy;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "dockInstant", "", "keepRectPixels", "()[F", "beginShatter", "Lnet/minecraft/DrawContext;", "graphics", "renderOverlay", "(Lnet/minecraft/DrawContext;)V", "reopen", "sanitize", "", "isDetached", "()Z", "busy", "", "drawX", "drawY", "size", "setSocket", "(FFF)V", "mouseX", "mouseY", "", "button", "doubleClick", "grab", "(FFIZ)Z", "release", "returnHome", "dt", "interactive", "update", "(FZ)V", "updateDocked", "(F)V", "updateHeld", "updateFree", "updateReturning", "bounce", "clampInside", "(Z)V", "normalX", "normalY", "hitWall", "(FF)V", "beginHold", "alpha", "renderSocket", "(Lnet/minecraft/DrawContext;FFFF)V", "render", "(Lnet/minecraft/DrawContext;F)V", "boxW", "boxH", "renderShards", "(Lnet/minecraft/DrawContext;FFFFFF)V", "extent", "alpha255", "drawGlow", "(Lnet/minecraft/DrawContext;FF)V", "pivotX", "pivotY", "factor", "degrees", "pushToyTransform", "hitSocket", "(FF)Z", "hitFlying", "radius", "()F", "value", "wrapSpin", "(F)F", "low", "high", "clamp", "(FFF)F", "t", "easeInOutCubic", "easeOutCubic", "easeInCubic", "r", "g", "b", "rgba", "(IIIF)I", "", "GLYPH", "Ljava/lang/String;", "STATE_DOCKED", "I", "STATE_HELD", "STATE_FREE", "STATE_RETURNING", "FLY_SCALE", "F", "GRAVITY", "AIR_DRAG", "RESTITUTION", "MAX_THROW", "FOLLOW_RATE", "SNAP_DIST", "RETURN_TIME", "INSERT_TIME", "SQUASH_STIFFNESS", "SQUASH_DAMPING", "", "BOUNCE_SOUND_GAP_MS", "J", "EDGE_MARGIN", "GLOW_TEXTURE", "GLOW_FRACTION", "GLOW_ALPHA", "SPIN_DAMPING", "GROUND_FRICTION", "ROLL_PER_UNIT", "SETTLE_SPEED", "GROUND_EPSILON", "REST_FALL_SPEED", "SHARD_COLS", "SHARD_ROWS", "SHARDS", "SHATTER_TIME", "GATHER_TIME", "SHARD_SPREAD", "state", "socketX", "socketY", "socketSize", "socketCenterX", "socketCenterY", "boundsLeft", "boundsTop", "boundsRight", "boundsBottom", "boundsSize", "socketReady", "Z", "x", "y", "prevX", "prevY", "vx", "vy", "grabDX", "grabDY", "heldSpinBase", "spin", "spinVel", "scale", "squash", "squashVel", "squashAxisX", "squashAxisY", "detachT", "insertT", "hoverT", "returnT", "returnFromX", "returnFromY", "returnFromSpin", "returnBowX", "returnBowY", "lastBounceMs", "floorY", "resting", "Lrtx/kimiko/api/ui/logo/LogoTrail;", "trail", "Lrtx/kimiko/api/ui/logo/LogoTrail;", "shardX", "[F", "shardY", "shardW", "shardH", "shardDirX", "shardDirY", "shardReach", "shardDelay", "shatterT", "shattering", "overlayNs", "rtx.kimiko:kimiko"})
public final class LogoToy {
    @NotNull
    public static final LogoToy INSTANCE;
    @NotNull
    private static final String GLYPH = "x";
    private static final int STATE_DOCKED = 0;
    private static final int STATE_HELD = 1;
    private static final int STATE_FREE = 2;
    private static final int STATE_RETURNING = 3;
    private static final float FLY_SCALE = 4.6f;
    private static final float GRAVITY = 1000.0f;
    private static final float AIR_DRAG = 0.55f;
    private static final float RESTITUTION = 0.62f;
    private static final float MAX_THROW = 1500.0f;
    private static final float FOLLOW_RATE = 26.0f;
    private static final float SNAP_DIST = 40.0f;
    private static final float RETURN_TIME = 0.44f;
    private static final float INSERT_TIME = 0.42f;
    private static final float SQUASH_STIFFNESS = 420.0f;
    private static final float SQUASH_DAMPING = 9.0f;
    private static final long BOUNCE_SOUND_GAP_MS = 110L;
    private static final float EDGE_MARGIN = 4.0f;
    @NotNull
    private static final String GLOW_TEXTURE = "logo_glow.png";
    private static final float GLOW_FRACTION = 0.32f;
    private static final float GLOW_ALPHA = 200.0f;
    private static final float SPIN_DAMPING = 1.6f;
    private static final float GROUND_FRICTION = 3.6f;
    private static final float ROLL_PER_UNIT = 1.8f;
    private static final float SETTLE_SPEED = 30.0f;
    private static final float GROUND_EPSILON = 2.5f;
    private static final float REST_FALL_SPEED = 45.0f;
    private static final int SHARD_COLS = 4;
    private static final int SHARD_ROWS = 4;
    private static final int SHARDS = 16;
    private static final float SHATTER_TIME = 0.42f;
    private static final float GATHER_TIME = 0.3f;
    private static final float SHARD_SPREAD = 0.78f;
    private static int state;
    private static float socketX;
    private static float socketY;
    private static float socketSize;
    private static float socketCenterX;
    private static float socketCenterY;
    private static float boundsLeft;
    private static float boundsTop;
    private static float boundsRight;
    private static float boundsBottom;
    private static float boundsSize;
    private static boolean socketReady;
    private static float x;
    private static float y;
    private static float prevX;
    private static float prevY;
    private static float vx;
    private static float vy;
    private static float grabDX;
    private static float grabDY;
    private static float heldSpinBase;
    private static float spin;
    private static float spinVel;
    private static float scale;
    private static float squash;
    private static float squashVel;
    private static float squashAxisX;
    private static float squashAxisY;
    private static float detachT;
    private static float insertT;
    private static float hoverT;
    private static float returnT;
    private static float returnFromX;
    private static float returnFromY;
    private static float returnFromSpin;
    private static float returnBowX;
    private static float returnBowY;
    private static long lastBounceMs;
    private static float floorY;
    private static boolean resting;
    @NotNull
    private static final LogoTrail trail;
    @NotNull
    private static final float[] shardX;
    @NotNull
    private static final float[] shardY;
    @NotNull
    private static final float[] shardW;
    @NotNull
    private static final float[] shardH;
    @NotNull
    private static final float[] shardDirX;
    @NotNull
    private static final float[] shardDirY;
    @NotNull
    private static final float[] shardReach;
    @NotNull
    private static final float[] shardDelay;
    private static float shatterT;
    private static boolean shattering;
    private static long overlayNs;

    private LogoToy() {
    }

    @JvmStatic
    public static final void dockInstant() {
        state = 0;
        x = socketCenterX;
        y = socketCenterY;
        prevX = x;
        prevY = y;
        vx = 0.0f;
        vy = 0.0f;
        spin = 0.0f;
        spinVel = 0.0f;
        scale = 1.0f;
        squash = 0.0f;
        squashVel = 0.0f;
        detachT = 0.0f;
        insertT = 0.0f;
        hoverT = 0.0f;
        returnT = 0.0f;
        shattering = false;
        shatterT = 0.0f;
        resting = false;
        trail.clear();
    }

    @JvmStatic
    @Nullable
    public static final float[] keepRectPixels() {
        if (!socketReady || detachT <= 0.01f) {
            return null;
        }
        float half = socketSize * 4.6f * 2.4f + 20.0f;
        float left = Render2DCoordinateSpace.pixelX(x - half);
        float top = Render2DCoordinateSpace.pixelY(y - half);
        float right = Render2DCoordinateSpace.pixelX(x + half);
        float bottom = Render2DCoordinateSpace.pixelY(y + half);
        if (right - left <= 1.0f || bottom - top <= 1.0f) {
            return null;
        }
        float[] fArray = new float[]{left, top, right - left, bottom - top};
        return fArray;
    }

    @JvmStatic
    public static final void beginShatter() {
        if (state == 0 || shattering) {
            return;
        }
        if (state == 1) {
            LogoToy.release();
        }
        if (state == 0) {
            return;
        }
        shattering = true;
        overlayNs = 0L;
        if (shatterT <= 0.0f) {
            trail.clear();
            trail.burst(x, y, 22, 240.0f, 0.0f, 0.0f, (float)Math.PI * 2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void renderOverlay(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (!socketReady || !LogoToy.busy() || !shattering && !(shatterT > 0.0f) || minecraft.currentScreen != null || minecraft.world == null || minecraft.options.hudHidden) {
            overlayNs = 0L;
            return;
        }
        if (shattering && shatterT >= 1.0f && trail.idle()) {
            overlayNs = 0L;
            return;
        }
        long now = System.nanoTime();
        float step = overlayNs == 0L ? 0.0f : Math.min(0.05f, Math.max(0.0f, (float)(now - overlayNs) / 1.0E9f));
        overlayNs = now;
        LogoToy.update(step, false);
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            Render2D.beginFrame(graphics);
            LogoToy.render(graphics, 1.0f);
            Render2D.flush();
            GuiLayerBlurRenderer.markPanelEnd(graphics);
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    @JvmStatic
    public static final void reopen() {
        shattering = false;
        if (!socketReady) {
            return;
        }
        INSTANCE.sanitize();
        if (state != 0) {
            resting = false;
            INSTANCE.clampInside(false);
        }
    }

    private final void sanitize() {
        if (!(Math.abs(x) <= Float.MAX_VALUE) || !(Math.abs(y) <= Float.MAX_VALUE)) {
            x = socketCenterX;
            y = socketCenterY;
            prevX = x;
            prevY = y;
        }
        if (!(Math.abs(vx) <= Float.MAX_VALUE) || !(Math.abs(vy) <= Float.MAX_VALUE)) {
            vx = 0.0f;
            vy = 0.0f;
        }
        if (!(Math.abs(spin) <= Float.MAX_VALUE) || !(Math.abs(spinVel) <= Float.MAX_VALUE)) {
            spin = 0.0f;
            spinVel = 0.0f;
        }
        if (!(Math.abs(scale) <= Float.MAX_VALUE) || scale <= 0.0f) {
            scale = 1.0f;
        }
    }

    @JvmStatic
    public static final boolean isDetached() {
        return state != 0;
    }

    @JvmStatic
    public static final boolean busy() {
        return state != 0 || insertT > 0.004f || !trail.idle();
    }

    @JvmStatic
    public static final void setSocket(float drawX, float drawY, float size) {
        socketX = drawX;
        socketY = drawY;
        if (Math.abs(size - boundsSize) > 0.001f) {
            boundsSize = size;
            float[] bounds = Fonts.KIMIKO.msdfBounds(GLYPH, size);
            if (bounds.length >= 4 && bounds[2] - bounds[0] > 0.0f && bounds[3] - bounds[1] > 0.0f) {
                boundsLeft = bounds[0];
                boundsTop = bounds[1];
                boundsRight = bounds[2];
                boundsBottom = bounds[3];
            } else {
                float width = Fonts.KIMIKO.msdfWidth(GLYPH, size);
                boundsLeft = 0.0f;
                boundsTop = 0.0f;
                boundsRight = width > 0.0f ? width : size;
                boundsBottom = size;
            }
        }
        socketSize = size;
        socketCenterX = drawX + (boundsLeft + boundsRight) * 0.5f;
        socketCenterY = drawY + (boundsTop + boundsBottom) * 0.5f;
        if (!socketReady) {
            socketReady = true;
            x = socketCenterX;
            y = socketCenterY;
            prevX = x;
            prevY = y;
        }
        if (state == 0) {
            x = socketCenterX;
            y = socketCenterY;
        }
    }

    @JvmStatic
    public static final boolean grab(float mouseX, float mouseY, int button, boolean doubleClick) {
        if (!socketReady) {
            return false;
        }
        if (button == 1) {
            if (state != 0 && INSTANCE.hitFlying(mouseX, mouseY)) {
                LogoToy.returnHome();
                return true;
            }
            return false;
        }
        if (button != 0) {
            return false;
        }
        if (state == 0) {
            if (insertT > 0.05f || !INSTANCE.hitSocket(mouseX, mouseY)) {
                return false;
            }
            INSTANCE.beginHold(mouseX, mouseY);
            trail.burst(x, y, 10, 120.0f, 0.0f, 0.0f, (float)Math.PI * 2);
            Sounds.play("logo_pull");
            return true;
        }
        if (!INSTANCE.hitFlying(mouseX, mouseY)) {
            return false;
        }
        if (doubleClick) {
            LogoToy.returnHome();
            return true;
        }
        INSTANCE.beginHold(mouseX, mouseY);
        return true;
    }

    @JvmStatic
    public static final void release() {
        if (state != 1) {
            return;
        }
        float dx = socketCenterX - x;
        float dy = socketCenterY - y;
        if ((float)Math.sqrt(dx * dx + dy * dy) <= 40.0f) {
            LogoToy.returnHome();
            return;
        }
        state = 2;
        resting = false;
        float speed = (float)Math.sqrt(vx * vx + vy * vy);
        if (speed > 1500.0f) {
            float k = 1500.0f / speed;
            vx *= k;
            vy *= k;
        }
        spinVel = -vx * 1.35f;
    }

    @JvmStatic
    public static final void returnHome() {
        if (state == 3 || state == 0) {
            return;
        }
        state = 3;
        resting = false;
        returnT = 0.0f;
        returnFromX = x;
        returnFromY = y;
        returnFromSpin = INSTANCE.wrapSpin(spin);
        float dx = socketCenterX - x;
        float dy = socketCenterY - y;
        float dist = (float)Math.sqrt(dx * dx + dy * dy);
        if (dist > 0.001f) {
            float bow = Math.min(38.0f, dist * 0.22f);
            returnBowX = -dy / dist * bow;
            returnBowY = -Math.abs(dx / dist) * bow - bow * 0.35f;
        } else {
            returnBowX = 0.0f;
            returnBowY = 0.0f;
        }
    }

    @JvmStatic
    public static final void update(float dt, boolean interactive) {
        if (!socketReady) {
            return;
        }
        float step = Math.min(0.05f, Math.max(0.0f, dt));
        if (step <= 0.0f) {
            return;
        }
        INSTANCE.sanitize();
        if (shattering) {
            shatterT = Math.min(1.0f, shatterT + step / 0.42f);
        } else if (shatterT > 0.0f) {
            shatterT = Math.max(0.0f, shatterT - step / 0.3f);
        }
        if (shatterT > 0.0f) {
            hoverT += (0.0f - hoverT) * (1.0f - (float)Math.exp(-step * 10.0f));
            prevX = x;
            prevY = y;
            trail.update(step);
            return;
        }
        prevX = x;
        prevY = y;
        switch (state) {
            case 1: {
                INSTANCE.updateHeld(step, interactive);
                break;
            }
            case 2: {
                INSTANCE.updateFree(step);
                break;
            }
            case 3: {
                INSTANCE.updateReturning(step);
                break;
            }
            default: {
                INSTANCE.updateDocked(step);
            }
        }
        float detachTarget = state == 0 ? 0.0f : 1.0f;
        detachT += (detachTarget - detachT) * (1.0f - (float)Math.exp(-step * 13.0f));
        if (Math.abs(detachTarget - detachT) < 0.002f) {
            detachT = detachTarget;
        }
        if (insertT > 0.0f) {
            insertT = Math.max(0.0f, insertT - step / 0.42f);
        }
        squashVel -= squash * 420.0f * step;
        if (Math.abs(squash += (squashVel *= (float)Math.exp(-step * 9.0f)) * step) < 0.001f && Math.abs(squashVel) < 0.01f) {
            squash = 0.0f;
            squashVel = 0.0f;
        }
        if (state != 0) {
            float speed = (float)Math.sqrt(vx * vx + vy * vy);
            trail.emit(prevX, prevY, x, y, vx, vy, Math.min(1.0f, speed / 260.0f));
        }
        trail.update(step);
    }

    private final void updateDocked(float dt) {
        x = socketCenterX;
        y = socketCenterY;
        spin += (0.0f - spin) * (1.0f - (float)Math.exp(-dt * 14.0f));
        float hoverTarget = insertT <= 0.05f && this.hitSocket(Position.Companion.mouseX(), Position.Companion.mouseY()) ? 1.0f : 0.0f;
        hoverT += (hoverTarget - hoverT) * (1.0f - (float)Math.exp(-dt * 12.0f));
        scale += (1.0f - scale) * (1.0f - (float)Math.exp(-dt * 12.0f));
    }

    private final void updateHeld(float dt, boolean interactive) {
        if (!interactive) {
            LogoToy.release();
            return;
        }
        hoverT += (1.0f - hoverT) * (1.0f - (float)Math.exp(-dt * 12.0f));
        float targetX = Position.Companion.mouseX() + grabDX;
        float targetY = Position.Companion.mouseY() + grabDY;
        float k = 1.0f - (float)Math.exp(-dt * 26.0f);
        float nextX = x + (targetX - x) * k;
        float nextY = y + (targetY - y) * k;
        vx = (nextX - x) / dt;
        vy = (nextY - y) / dt;
        x = nextX;
        y = nextY;
        this.clampInside(false);
        float tilt = heldSpinBase + this.clamp(-vx * 0.055f, -34.0f, 34.0f);
        spin += (tilt - spin) * (1.0f - (float)Math.exp(-dt * 9.0f));
        scale += (1.14f - scale) * (1.0f - (float)Math.exp(-dt * 11.0f));
    }

    private final void updateFree(float dt) {
        boolean grounded;
        hoverT += (0.0f - hoverT) * (1.0f - (float)Math.exp(-dt * 10.0f));
        if (!resting) {
            vy += 1000.0f * dt;
            float drag = (float)Math.exp(-dt * 0.55f);
            vx *= drag;
            vy *= drag;
        }
        x += vx * dt;
        scale += (1.0f - scale) * (1.0f - (float)Math.exp(-dt * 10.0f));
        this.clampInside(true);
        boolean bl = grounded = (y += vy * dt) >= floorY - 2.5f;
        if (!grounded) {
            resting = false;
        } else if (!resting && Math.abs(vy) <= Math.max(45.0f, 1000.0f * dt * 2.2f)) {
            resting = true;
            vy = 0.0f;
            y = floorY;
        }
        if (grounded) {
            if (Math.abs(vx *= (float)Math.exp(-dt * 3.6f)) < 4.0f) {
                vx = 0.0f;
            }
            if (Math.abs(vx) < 30.0f) {
                if (!(spinVel == 0.0f)) {
                    if (Math.abs(spinVel *= (float)Math.exp(-dt * 12.0f)) < 90.0f) {
                        spinVel = 0.0f;
                    }
                } else {
                    float upright = (float)Math.round(spin / 360.0f) * 360.0f;
                    if (Math.abs(upright - (spin += (upright - spin) * (1.0f - (float)Math.exp(-dt * 8.0f)))) < 0.25f) {
                        spin = upright;
                    }
                }
            } else {
                float roll = vx * 1.8f;
                spinVel += (roll - spinVel) * (1.0f - (float)Math.exp(-dt * 10.0f));
            }
        } else {
            spinVel *= (float)Math.exp(-dt * 1.6f);
        }
        spin += spinVel * dt;
    }

    private final void updateReturning(float dt) {
        hoverT += (0.0f - hoverT) * (1.0f - (float)Math.exp(-dt * 10.0f));
        returnT = Math.min(1.0f, returnT + dt / 0.44f);
        float e = this.easeInOutCubic(returnT);
        float bow = (float)Math.sin(returnT * (float)Math.PI);
        x = returnFromX + (socketCenterX - returnFromX) * e + returnBowX * bow;
        y = returnFromY + (socketCenterY - returnFromY) * e + returnBowY * bow;
        spin = returnFromSpin * (1.0f - this.easeOutCubic(returnT));
        scale = 1.0f + 0.24f * bow - 0.2f * this.easeInCubic(returnT);
        vx = (x - prevX) / dt;
        vy = (y - prevY) / dt;
        if (returnT >= 1.0f) {
            state = 0;
            x = socketCenterX;
            y = socketCenterY;
            vx = 0.0f;
            vy = 0.0f;
            spin = 0.0f;
            spinVel = 0.0f;
            scale = 1.0f;
            insertT = 1.0f;
            squash = 0.5f;
            squashVel = 0.0f;
            squashAxisX = 0.0f;
            squashAxisY = 1.0f;
            trail.burst(socketCenterX, socketCenterY, 18, 190.0f, 0.0f, 0.0f, (float)Math.PI * 2);
            Sounds.play("logo_insert");
        }
    }

    private final void clampInside(boolean bounce) {
        float maxY;
        float zoom = Math.max(0.01f, Render2DCoordinateSpace.uiZoom());
        float centerX = Position.Companion.screenWidth() * 0.5f;
        float centerY = Position.Companion.screenHeight() * 0.5f;
        float halfW = centerX / zoom;
        float halfH = centerY / zoom;
        float radius = this.radius();
        float minX = centerX - halfW + 4.0f + radius;
        float maxX = centerX + halfW - 4.0f - radius;
        float minY = centerY - halfH + 4.0f + radius;
        floorY = maxY = centerY + halfH - 4.0f - radius;
        if (x < minX) {
            x = minX;
            if (bounce) {
                this.hitWall(1.0f, 0.0f);
            }
        } else if (x > maxX) {
            x = maxX;
            if (bounce) {
                this.hitWall(-1.0f, 0.0f);
            }
        }
        if (y < minY) {
            y = minY;
            if (bounce) {
                this.hitWall(0.0f, 1.0f);
            }
        } else if (y > maxY) {
            y = maxY;
            if (bounce) {
                this.hitWall(0.0f, -1.0f);
            }
        }
    }

    private final void hitWall(float normalX, float normalY) {
        float impact = 0.0f;
        if (!(normalX == 0.0f)) {
            impact = Math.abs(vx);
            vx = -vx * 0.62f;
            spinVel += vy * 1.1f * normalX;
        } else {
            impact = Math.abs(vy);
            vy = -vy * 0.62f;
            spinVel += vx * 1.1f * -normalY;
        }
        if (impact < 22.0f) {
            if (normalY < 0.0f && Math.abs(vy) < 26.0f) {
                vy = 0.0f;
            }
            return;
        }
        float strength = Math.min(0.65f, impact / 900.0f + 0.12f);
        squashAxisX = normalX;
        squashAxisY = normalY;
        squash = strength;
        squashVel = 0.0f;
        trail.burst(x - normalX * this.radius() * 0.7f, y - normalY * this.radius() * 0.7f, (int)((float)4 + strength * 16.0f), 120.0f + impact * 0.35f, normalX, normalY, 2.1f);
        long now = System.currentTimeMillis();
        if (now - lastBounceMs >= 110L && impact > 120.0f) {
            lastBounceMs = now;
            Sounds.play("logo_bounce");
        }
    }

    private final void beginHold(float mouseX, float mouseY) {
        state = 1;
        resting = false;
        grabDX = x - mouseX;
        grabDY = y - mouseY;
        heldSpinBase = spin;
        vx = 0.0f;
        vy = 0.0f;
        spinVel = 0.0f;
        squash = 0.18f;
        squashVel = 0.0f;
        squashAxisX = 0.0f;
        squashAxisY = 1.0f;
    }

    @JvmStatic
    public static final void renderSocket(@NotNull DrawContext graphics, float drawX, float drawY, float size, float alpha) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        LogoToy.setSocket(drawX, drawY, size);
        if (alpha <= 0.004f) {
            return;
        }
        float hole = detachT;
        if (hole > 0.004f) {
            Fonts.KIMIKO.msdf(GLYPH, drawX + 0.5f, drawY + 0.5f, size, INSTANCE.rgba(255, 255, 255, 30.0f * alpha * hole));
            Fonts.KIMIKO.msdf(GLYPH, drawX, drawY, size, INSTANCE.rgba(104, 108, 122, 145.0f * alpha * hole));
        }
        if (hole < 0.996f) {
            float docked = 1.0f - hole;
            float lift = 1.0f + 0.09f * hoverT;
            INSTANCE.pushToyTransform(graphics, socketCenterX, socketCenterY, scale * lift, spin * docked);
            AccentGradient.msdfIcon(Fonts.KIMIKO, GLYPH, drawX, drawY, size, (225.0f + 30.0f * hoverT) * alpha * docked, 0.1f);
            graphics.getMatrices().popMatrix();
        }
        if (insertT > 0.004f) {
            float t = insertT;
            float ring = size * (0.42f + (1.0f - t) * 1.35f);
            Render2D.circleOutline(socketCenterX, socketCenterY, ring, 0.85f, ClientAccent.accentBrightAt(200.0f * alpha * t * t, socketCenterX, socketCenterY));
            Fonts.KIMIKO.msdf(GLYPH, drawX, drawY, size, INSTANCE.rgba(255, 255, 255, 190.0f * alpha * t * t));
        }
    }

    @JvmStatic
    public static final void render(@NotNull DrawContext graphics, float alpha) {
        float effective;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        float f = effective = shatterT > 0.002f ? 1.0f : alpha;
        if (effective <= 0.004f) {
            return;
        }
        trail.render(effective);
        float visible = detachT;
        if (visible <= 0.01f) {
            return;
        }
        float shrink = 0.21739131f;
        float size = socketSize * 4.6f * (shrink + (1.0f - shrink) * visible);
        float[] bounds = Fonts.KIMIKO.msdfBounds(GLYPH, size);
        float cx = 0.0f;
        float cy = 0.0f;
        float boxW = 0.0f;
        float boxH = 0.0f;
        if (bounds.length >= 4 && bounds[2] - bounds[0] > 0.0f) {
            cx = x - (bounds[0] + bounds[2]) * 0.5f;
            cy = y - (bounds[1] + bounds[3]) * 0.5f;
            boxW = bounds[2] - bounds[0];
            boxH = bounds[3] - bounds[1];
        } else {
            cx = x - size * 0.5f;
            cy = y - size * 0.5f;
            boxW = size;
            boxH = size;
        }
        if (shatterT > 0.002f) {
            INSTANCE.renderShards(graphics, cx, cy, size, boxW, boxH, visible);
            return;
        }
        float a = effective * visible;
        float heat = 1.0f + 0.55f * Math.min(1.0f, (float)Math.sqrt(vx * vx + vy * vy) / 700.0f);
        INSTANCE.drawGlow(graphics, Math.max(boxW, boxH), 200.0f * a * heat);
        INSTANCE.pushToyTransform(graphics, x, y, scale, spin);
        AccentGradient.msdfIcon(Fonts.KIMIKO, GLYPH, cx, cy, size, 255.0f * a, 0.1f);
        graphics.getMatrices().popMatrix();
    }

    private final void renderShards(DrawContext graphics, float drawX, float drawY, float size, float boxW, float boxH, float alpha) {
        float field = Math.max(boxW, boxH) * 1.6f;
        float originX = x - field * 0.5f;
        float originY = y - field * 0.5f;
        float whole = 1.0f - shatterT;
        if (whole > 0.02f) {
            this.drawGlow(graphics, Math.max(boxW, boxH) * (1.0f + 0.3f * shatterT), 200.0f * alpha * whole * whole);
        }
        for (int i = 0; i < 16; ++i) {
            float delay = shardDelay[i];
            float t = this.clamp((shatterT - delay) / Math.max(0.1f, 1.0f - delay), 0.0f, 1.0f);
            float eased = this.easeOutCubic(t);
            float fade = 1.0f - eased * eased;
            float shardAlpha = alpha * fade;
            if (shardAlpha <= 0.005f) continue;
            float reach = eased * field * 0.78f * shardReach[i];
            float dx = shardDirX[i] * reach;
            float dy = shardDirY[i] * reach + eased * eased * field * 0.2f;
            Render2D.pushScissor(graphics, originX + shardX[i] * field + dx, originY + shardY[i] * field + dy, shardW[i] * field, shardH[i] * field);
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(dx, dy);
            this.pushToyTransform(graphics, x, y, scale, spin);
            AccentGradient.msdfIcon(Fonts.KIMIKO, GLYPH, drawX, drawY, size, 255.0f * shardAlpha, 0.1f);
            graphics.getMatrices().popMatrix();
            graphics.getMatrices().popMatrix();
            Render2D.popScissor(graphics);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void drawGlow(DrawContext graphics, float extent, float alpha255) {
        if (alpha255 <= 1.0f) {
            return;
        }
        float glowSize = extent / 0.32f;
        this.pushToyTransform(graphics, x, y, scale, spin);
        ImageRenderer.Companion.setAdditive(true);
        try {
            Render2D.image(GLOW_TEXTURE, x - glowSize * 0.5f, y - glowSize * 0.5f, glowSize, glowSize, 0.0f, ClientAccent.accentBrightAt(alpha255, x, y));
        }
        finally {
            ImageRenderer.Companion.setAdditive(false);
        }
        graphics.getMatrices().popMatrix();
    }

    private final void pushToyTransform(DrawContext graphics, float pivotX, float pivotY, float factor, float degrees) {
        float squashAngle = (float)Math.atan2(squashAxisY, squashAxisX);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(pivotX, pivotY);
        if (Math.abs(squash) > 0.001f) {
            graphics.getMatrices().rotate(squashAngle);
            graphics.getMatrices().scale(1.0f - squash * 0.5f, 1.0f + squash * 0.3f);
            graphics.getMatrices().rotate(-squashAngle);
        }
        graphics.getMatrices().rotate((float)Math.toRadians(degrees));
        graphics.getMatrices().scale(factor);
        graphics.getMatrices().translate(-pivotX, -pivotY);
    }

    private final boolean hitSocket(float mouseX, float mouseY) {
        float pad = 3.0f;
        float left = socketX + boundsLeft - pad;
        float top = socketY + boundsTop - pad;
        float right = socketX + boundsRight + pad;
        float bottom = socketY + boundsBottom + pad;
        return mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom;
    }

    private final boolean hitFlying(float mouseX, float mouseY) {
        float dx = mouseX - x;
        float dy = mouseY - y;
        float r = this.radius() + 3.0f;
        return dx * dx + dy * dy <= r * r;
    }

    private final float radius() {
        float w = (boundsRight - boundsLeft) * 4.6f;
        float h = (boundsBottom - boundsTop) * 4.6f;
        float base = Math.max(w, h) * 0.5f;
        return base > 0.5f ? base : 8.0f;
    }

    private final float wrapSpin(float value) {
        float v = value % 360.0f;
        if (v > 180.0f) {
            v -= 360.0f;
        }
        if (v < -180.0f) {
            v += 360.0f;
        }
        return v;
    }

    private final float clamp(float value, float low, float high) {
        return value < low ? low : (value > high ? high : value);
    }

    private final float easeInOutCubic(float t) {
        return t < 0.5f ? 4.0f * t * t * t : 1.0f - (-2.0f * t + 2.0f) * (-2.0f * t + 2.0f) * (-2.0f * t + 2.0f) * 0.5f;
    }

    private final float easeOutCubic(float t) {
        float p = 1.0f - t;
        return 1.0f - p * p * p;
    }

    private final float easeInCubic(float t) {
        return t * t * t;
    }

    private final int rgba(int r, int g, int b, float alpha255) {
        int a = Math.round(this.clamp(alpha255, 0.0f, 255.0f));
        if (a <= 0) {
            return 0;
        }
        return a << 24 | r << 16 | g << 8 | b;
    }

    static {
        float base;
        int i;
        INSTANCE = new LogoToy();
        boundsSize = -1.0f;
        scale = 1.0f;
        squashAxisY = 1.0f;
        trail = new LogoTrail();
        shardX = new float[16];
        shardY = new float[16];
        shardW = new float[16];
        shardH = new float[16];
        shardDirX = new float[16];
        shardDirY = new float[16];
        shardReach = new float[16];
        shardDelay = new float[16];
        Random rng = new Random(12648430L);
        float[] cutX = new float[5];
        float[] cutY = new float[5];
        for (i = 0; i < 5; ++i) {
            base = (float)i / (float)4;
            cutX[i] = switch (i) {
                case 0, 4 -> base;
                default -> base + (rng.nextFloat() - 0.5f) * 0.24f;
            };
        }
        for (i = 0; i < 5; ++i) {
            base = (float)i / (float)4;
            cutY[i] = switch (i) {
                case 0, 4 -> base;
                default -> base + (rng.nextFloat() - 0.5f) * 0.24f;
            };
        }
        int index = 0;
        for (int row = 0; row < 4; ++row) {
            for (int col = 0; col < 4; ++col) {
                LogoToy.shardX[index] = cutX[col];
                LogoToy.shardY[index] = cutY[row];
                LogoToy.shardW[index] = cutX[col + 1] - cutX[col];
                LogoToy.shardH[index] = cutY[row + 1] - cutY[row];
                float ox = shardX[index] + shardW[index] * 0.5f - 0.5f;
                float oy = shardY[index] + shardH[index] * 0.5f - 0.5f;
                float len = (float)Math.sqrt(ox * ox + oy * oy);
                if (len > 1.0E-4f) {
                    LogoToy.shardDirX[index] = ox / len;
                    LogoToy.shardDirY[index] = oy / len;
                } else {
                    LogoToy.shardDirX[index] = 0.0f;
                    LogoToy.shardDirY[index] = -1.0f;
                }
                LogoToy.shardReach[index] = 0.45f + len * 1.4f + rng.nextFloat() * 0.3f;
                LogoToy.shardDelay[index] = rng.nextFloat() * 0.22f;
                ++index;
            }
        }
    }
}

