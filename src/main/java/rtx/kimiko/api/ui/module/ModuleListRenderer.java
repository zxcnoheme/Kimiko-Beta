/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRestrictions;
import rtx.kimiko.api.ui.ClientLanguage;
import rtx.kimiko.api.ui.ScrollBar;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Animation;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.BuiltRectangle;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010!\n\u0002\b\u0005\u0018\u0000 \u009e\u00012\u00020\u0001:\u0004\u009f\u0001\u009e\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\nJ\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u000e2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ-\u0010\u001f\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J%\u0010!\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0004\u00a2\u0006\u0004\b#\u0010\u0006J?\u0010(\u001a\u0004\u0018\u00010'2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010+\u001a\u00020\u00192\b\u0010*\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b+\u0010,J\r\u0010-\u001a\u00020\u0019\u00a2\u0006\u0004\b-\u0010\u0003J\r\u0010.\u001a\u00020\u0019\u00a2\u0006\u0004\b.\u0010\u0003J\r\u0010/\u001a\u00020\u0019\u00a2\u0006\u0004\b/\u0010\u0003J\u0017\u00101\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b4\u00105J\u001d\u00109\u001a\u00020\u00192\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u00020\u0004\u00a2\u0006\u0004\b9\u0010:J\u0015\u0010<\u001a\u00020\u00192\u0006\u0010;\u001a\u00020\u0014\u00a2\u0006\u0004\b<\u0010=JS\u0010F\u001a\u00020\u00192\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0004\bF\u0010GJ[\u0010F\u001a\u00020\u00192\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0004\bF\u0010IJ\u001d\u0010L\u001a\u00020\b2\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004\u00a2\u0006\u0004\bL\u0010MJ\r\u0010N\u001a\u00020\u0019\u00a2\u0006\u0004\bN\u0010\u0003J\r\u0010O\u001a\u00020\b\u00a2\u0006\u0004\bO\u0010\nJ}\u0010T\u001a\u00020\u00192\u0006\u0010?\u001a\u00020>2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010S\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bT\u0010UJ\u000f\u0010V\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bV\u0010\nJ\u0015\u0010X\u001a\u00020\u00192\u0006\u0010W\u001a\u00020\b\u00a2\u0006\u0004\bX\u0010YJ\r\u0010Z\u001a\u00020\u0019\u00a2\u0006\u0004\bZ\u0010\u0003J\r\u0010[\u001a\u00020'\u00a2\u0006\u0004\b[\u0010\\J\r\u0010]\u001a\u00020\u001d\u00a2\u0006\u0004\b]\u0010^J\r\u0010_\u001a\u00020\u0004\u00a2\u0006\u0004\b_\u0010\u0006J\u000f\u0010a\u001a\u00020`H\u0002\u00a2\u0006\u0004\ba\u0010bJ\u0017\u0010c\u001a\u00020\u00192\u0006\u0010?\u001a\u00020>H\u0002\u00a2\u0006\u0004\bc\u0010dJ\u0017\u0010e\u001a\u00020\u00192\u0006\u0010?\u001a\u00020>H\u0002\u00a2\u0006\u0004\be\u0010dJ?\u0010j\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\u00042\u0006\u0010i\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bj\u0010kJ\r\u0010l\u001a\u00020\u0019\u00a2\u0006\u0004\bl\u0010\u0003J=\u0010m\u001a\u00020\b2\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\u0004\bm\u0010nR&\u0010p\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010qR \u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020s0r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010uR \u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00040r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bv\u0010uR \u0010w\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00040r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010uR \u0010x\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00040r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010uR&\u0010y\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000e0r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010uR \u0010z\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00040r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bz\u0010uR\u0016\u0010{\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010~R\u0016\u00109\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010|R\u0016\u0010\u007f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010|R\u0018\u0010\u0080\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010|R\u001b\u0010\u0081\u0001\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\"\u0010\u0083\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020s0r8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010uR\u0019\u0010\u0084\u0001\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001a\u0010\u0087\u0001\u001a\u00030\u0086\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0019\u0010\u0089\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0019\u0010\u008e\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008a\u0001R\u0019\u0010\u008f\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u008a\u0001R\u001f\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0092\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0092\u0001\u0010|R\u001a\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0093\u0001\u0010~R\u001a\u0010\u0094\u0001\u001a\u00030\u0086\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0088\u0001R\u0019\u0010\u0095\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u008a\u0001R\u0018\u0010\u0096\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0096\u0001\u0010|R\u0015\u0010[\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b[\u0010\u0097\u0001R\u0017\u0010]\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b]\u0010\u0085\u0001R\u0016\u0010_\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010|R\u0019\u0010\u0098\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u008a\u0001R\u0018\u0010\u0099\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0099\u0001\u0010|R\u0019\u0010\u009a\u0001\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0085\u0001R\u001e\u0010\u009c\u0001\u001a\t\u0012\u0004\u0012\u00020`0\u009b\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0091\u0001R\u0019\u0010\u009d\u0001\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u0085\u0001\u00a8\u0006\u00a0\u0001"}, d2={"Lrtx/kimiko/api/ui/module/ModuleListRenderer;", "", "<init>", "()V", "", "getContentH", "()F", "getScroll", "", "isTransitioning", "()Z", "isFadeOutDone", "Lrtx/kimiko/api/modules/Category;", "cat", "", "Lrtx/kimiko/api/modules/Module;", "getModules", "(Lrtx/kimiko/api/modules/Category;)Ljava/util/List;", "mod", "colW", "", "descLines", "(Lrtx/kimiko/api/modules/Module;F)Ljava/util/List;", "baseCardHeight", "(Lrtx/kimiko/api/modules/Module;F)F", "", "invalidateOnWidth", "(F)V", "modules", "", "idx", "cardTopInColumn", "(Ljava/util/List;IF)F", "totalContentH", "(Ljava/util/List;F)F", "currentScroll", "listX", "listY", "listW", "", "cardRect", "(Ljava/util/List;IFFF)[F", "from", "beginFadeOut", "(Lrtx/kimiko/api/modules/Category;)V", "prepareQuickAppear", "finishTransition", "replayAppear", "rows", "startRowAnims", "(I)V", "row", "rowAppear", "(I)F", "", "amount", "viewH", "scroll", "(DF)V", "name", "focusModule", "(Ljava/lang/String;)V", "Lnet/minecraft/DrawContext;", "g", "x", "y", "w", "alpha", "dt", "selected", "render", "(Lnet/minecraft/DrawContext;FFFFFLrtx/kimiko/api/modules/Category;Ljava/util/List;)V", "slideAlpha", "(Lnet/minecraft/DrawContext;FFFFFFLrtx/kimiko/api/modules/Category;Ljava/util/List;)V", "mx", "my", "scrollbarGrab", "(FF)Z", "scrollbarRelease", "scrollbarDragging", "c1x", "c2x", "scrollAreaH", "animateAppear", "renderCards", "(Lnet/minecraft/DrawContext;FFFFFFFFFFLjava/util/List;Z)V", "hasUnfinishedAppear", "value", "setAppearComposite", "(Z)V", "resetCardBlur", "cardBlurRects", "()[F", "cardBlurCount", "()I", "cardBlurMaxPhase", "Lrtx/kimiko/api/ui/module/ModuleListRenderer$CardText;", "nextCardText", "()Lrtx/kimiko/api/ui/module/ModuleListRenderer$CardText;", "drawCardShapes", "(Lnet/minecraft/DrawContext;)V", "drawCardText", "cx", "cy", "ma", "enableT", "renderDesc", "(Lrtx/kimiko/api/modules/Module;FFFFF)V", "warmup", "hitSettingsIcon", "(FFFFFLrtx/kimiko/api/modules/Module;)Z", "Ljava/util/EnumMap;", "moduleCache", "Ljava/util/EnumMap;", "", "Lrtx/kimiko/utils/animations/Decelerate;", "enableAnims", "Ljava/util/Map;", "hoverAnims", "gearHoverAnims", "gearOpenAnims", "descLinesCache", "baseHeightCache", "cachedColumnWidth", "F", "cachedLanguage", "Ljava/lang/String;", "scrollTarget", "contentH", "lastRendered", "Lrtx/kimiko/api/modules/Category;", "rowAppearAnims", "appearFadeMs", "I", "", "appearBaseMs", "J", "appearInitialFrame", "Z", "Lrtx/kimiko/api/ui/ScrollBar;", "scrollBar", "Lrtx/kimiko/api/ui/ScrollBar;", "transitioning", "quickAppear", "fadingOut", "Ljava/util/List;", "fadeOutTime", "focusName", "focusUntilMs", "focusScrollPending", "focusDimT", "[F", "appearComposite", "topBlurT", "lastModulesSig", "", "cardTexts", "cardTextCount", "Companion", "CardText", "rtx.kimiko:kimiko"})
public final class ModuleListRenderer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final EnumMap<Category, List<Module>> moduleCache = new EnumMap(Category.class);
    @NotNull
    private final Map<String, Decelerate> enableAnims = new HashMap();
    @NotNull
    private final Map<String, Float> hoverAnims = new HashMap();
    @NotNull
    private final Map<String, Float> gearHoverAnims = new HashMap();
    @NotNull
    private final Map<String, Float> gearOpenAnims = new HashMap();
    @NotNull
    private final Map<Module, List<String>> descLinesCache = new IdentityHashMap();
    @NotNull
    private final Map<Module, Float> baseHeightCache = new IdentityHashMap();
    private float cachedColumnWidth = Float.NaN;
    @NotNull
    private String cachedLanguage = ClientLanguage.code();
    private float scroll;
    private float scrollTarget;
    private float contentH;
    @Nullable
    private Category lastRendered;
    @NotNull
    private final Map<Integer, Decelerate> rowAppearAnims = new HashMap();
    private int appearFadeMs = 320;
    private long appearBaseMs;
    private boolean appearInitialFrame;
    @NotNull
    private final ScrollBar scrollBar = new ScrollBar();
    private boolean transitioning;
    private boolean quickAppear;
    @NotNull
    private List<? extends Module> fadingOut = CollectionsKt.emptyList();
    private float fadeOutTime;
    @Nullable
    private String focusName;
    private long focusUntilMs;
    private boolean focusScrollPending;
    private float focusDimT;
    @NotNull
    private final float[] cardBlurRects = new float[384];
    private int cardBlurCount;
    private float cardBlurMaxPhase;
    private boolean appearComposite;
    private float topBlurT;
    private int lastModulesSig;
    @NotNull
    private final List<CardText> cardTexts = new ArrayList();
    private int cardTextCount;
    private static final float FADE_OUT_DURATION = 0.15f;
    private static final int ROW_FADE_MS = 380;
    private static final int QUICK_ROW_FADE_MS = 320;
    private static final float SLIDE_PX = 6.0f;
    private static final float CONTENT_Y_OFFSET = 5.0f;
    @JvmField
    public static final float HEADER_OFFSET = 30.0f;
    private static final float CARD_RADIUS = 6.0f;
    private static final float TOP_BLUR_SCROLL = 10.0f;
    private static final float TOP_FADE = 26.0f;
    private static final float CARD_GAP = 5.0f;
    private static final float CARD_PAD = 5.0f;
    private static final float PAD_X = 8.0f;
    private static final float NAME_SIZE = 7.5f;
    private static final float NAME_TOP = 6.0f;
    private static final float TITLE_CY = 10.75f;
    private static final float DESC_SIZE = 6.0f;
    private static final float DESC_LINE_H = 7.0f;
    private static final float DESC_TOP = 19.0f;
    private static final float DESC_RIGHT_PAD = 4.0f;
    private static final float DESC_BOTTOM_PAD = 7.0f;
    private static final float NO_DESC_H = 22.0f;
    private static final float TOGGLE_RIGHT_PAD = 8.0f;
    private static final float GEAR_GAP = 7.0f;
    private static final float GEAR_SIZE = 7.0f;
    @NotNull
    private static final String GEAR_GLYPH = "f";
    private static final float CHECK_SIZE = 8.5f;
    private static final float CHECK_SCALE = 0.425f;
    private static final float CHECK_RADIUS = 2.9750001f;
    private static final float CHECK_RING1_INSET = 1.2750001f;
    private static final float CHECK_RING1_RADIUS = 2.5500002f;
    private static final float CHECK_RING2_INSET = 2.5500002f;
    private static final float CHECK_RING2_RADIUS = 2.125f;
    private static final float CHECK_DOT_INSET = 2.125f;
    private static final float CHECK_RING_THICKNESS = RangesKt.coerceAtLeast((float)0.5f, (float)0.425f);
    private static final float BADGE_H = 10.0f;
    public static final int MAX_BLUR_CARDS = 64;
    @NotNull
    private static final EnumMap<Category, String> ICON_CACHE = new EnumMap(Category.class);

    public final float getContentH() {
        return this.contentH;
    }

    public final float getScroll() {
        return this.scroll;
    }

    public final boolean isTransitioning() {
        return this.transitioning;
    }

    public final boolean isFadeOutDone() {
        return this.transitioning && this.fadeOutTime <= 0.0f;
    }

    @NotNull
    public final List<Module> getModules(@Nullable Category cat) {
        if (cat == null) {
            return CollectionsKt.emptyList();
        }
        List<Module> all = this.moduleCache.computeIfAbsent(cat, c -> ModuleManager.Companion.get().getByCategory(c));
        EnumSet<Server> here = ServerRestrictions.current();
        ArrayList<Module> visible = new ArrayList<Module>(all.size());
        for (Module m : all) {
            if (m.isHiddenInList() || ServerRestrictions.isHiddenBy(m, here)) continue;
            visible.add(m);
        }
        return Companion.sortPinnedFirst(visible);
    }

    private final List<String> descLines(Module mod, float colW) {
        List<String> cached;
        this.invalidateOnWidth(colW);
        List<String> list = cached = this.descLinesCache.get(mod);
        if (list != null) {
            return list;
        }
        String raw = mod.getDisplayDescription();
        ArrayList<String> lines = new ArrayList<String>();
        if (((CharSequence)raw).length() > 0) {
            float maxW = colW - 16.0f - 4.0f;
            StringBuilder line = new StringBuilder();
            for (String word : raw.split(" ")) {
                String test;
                String string = test = ((CharSequence)line).length() > 0 ? line + " " + word : word;
                if (Fonts.MEDIUM.width(test, 6.0f) > maxW && ((CharSequence)line).length() > 0) {
                    lines.add(line.toString());
                    line = new StringBuilder(word);
                    continue;
                }
                if (((CharSequence)line).length() > 0) {
                    line.append(' ');
                }
                line.append(word);
            }
            if (((CharSequence)line).length() > 0) {
                lines.add(line.toString());
            }
        }
        this.descLinesCache.put(mod, lines);
        return lines;
    }

    public final float baseCardHeight(@NotNull Module mod, float colW) {
        Intrinsics.checkNotNullParameter((Object)mod, (String)"mod");
        this.invalidateOnWidth(colW);
        Float cached = this.baseHeightCache.get(mod);
        if (cached != null) {
            return cached.floatValue();
        }
        List<String> lines = this.descLines(mod, colW);
        float h = lines.isEmpty() ? 22.0f : 19.0f + (float)lines.size() * 7.0f + 7.0f;
        this.baseHeightCache.put(mod, Float.valueOf(h));
        return h;
    }

    private final void invalidateOnWidth(float colW) {
        float width = (int)colW;
        String language = ClientLanguage.code();
        if (this.cachedColumnWidth == width && Intrinsics.areEqual((Object)this.cachedLanguage, (Object)language)) {
            return;
        }
        this.cachedColumnWidth = width;
        this.cachedLanguage = language;
        this.descLinesCache.clear();
        this.baseHeightCache.clear();
    }

    private final float cardTopInColumn(List<? extends Module> modules, int idx, float colW) {
        float y = 0.0f;
        for (int i = idx % 2; i < idx; i += 2) {
            y += this.baseCardHeight(modules.get(i), colW) + 5.0f;
        }
        return y;
    }

    private final float totalContentH(List<? extends Module> modules, float colW) {
        float col0 = 0.0f;
        float col1 = 0.0f;
        int n = ((Collection)modules).size();
        for (int i = 0; i < n; ++i) {
            float advance = this.baseCardHeight(modules.get(i), colW) + 5.0f;
            if ((i & 1) == 0) {
                col0 += advance;
                continue;
            }
            col1 += advance;
        }
        float tallest = Math.max(col0, col1);
        if (tallest > 0.0f) {
            tallest -= 5.0f;
        }
        return 10.0f + tallest;
    }

    public final float currentScroll() {
        return this.scroll;
    }

    @Nullable
    public final float[] cardRect(@Nullable List<? extends Module> modules, int idx, float listX, float listY, float listW) {
        if (modules == null || idx < 0 || idx >= modules.size()) {
            return null;
        }
        float colW = ModuleListRenderer.Companion.colW(listW);
        float c1x = listX + 5.0f;
        float c2x = c1x + colW + 5.0f;
        float cx = idx % 2 == 0 ? c1x : c2x;
        float cy = listY + 5.0f + this.cardTopInColumn(modules, idx, colW) - this.scroll;
        float cardH = this.baseCardHeight(modules.get(idx), colW);
        float[] fArray = new float[]{cx, cy, colW, cardH};
        return fArray;
    }

    public final void beginFadeOut(@Nullable Category from) {
        if (from == null) {
            return;
        }
        this.fadingOut = this.getModules(from);
        this.fadeOutTime = 0.15f;
        this.transitioning = true;
    }

    public final void prepareQuickAppear() {
        this.quickAppear = true;
    }

    public final void finishTransition() {
        this.transitioning = false;
        this.fadingOut = CollectionsKt.emptyList();
        this.fadeOutTime = 0.0f;
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
        this.lastRendered = null;
    }

    public final void replayAppear() {
        this.lastRendered = null;
    }

    private final void startRowAnims(int rows) {
        this.rowAppearAnims.clear();
        this.appearFadeMs = this.quickAppear ? 320 : 380;
        this.quickAppear = false;
        this.appearBaseMs = System.currentTimeMillis();
        this.appearInitialFrame = true;
    }

    private final float rowAppear(int row) {
        Decelerate d = this.rowAppearAnims.get(row);
        if (d == null) {
            long start = this.appearInitialFrame ? this.appearBaseMs : System.currentTimeMillis();
            Animation animation = new Decelerate().setMs(this.appearFadeMs).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            d = (Decelerate)animation;
            d.counter.setTime(start);
            this.rowAppearAnims.put(row, d);
        }
        Double d2 = d.getOutput();
        float v = (float)(d2 != null ? d2 : 0.0);
        return RangesKt.coerceIn((float)v, (float)0.0f, (float)1.0f);
    }

    public final void scroll(double amount, float viewH) {
        float max = Math.max(0.0f, this.contentH - viewH);
        this.scrollTarget = RangesKt.coerceIn((float)(this.scrollTarget - (float)amount * 16.0f), (float)0.0f, (float)max);
    }

    public final void focusModule(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        this.focusName = name;
        this.focusUntilMs = System.currentTimeMillis() + 5000L;
        this.focusScrollPending = true;
    }

    public final void render(@NotNull DrawContext g, float x, float y, float w, float alpha, float dt, @NotNull Category selected, @NotNull List<? extends Module> modules) {
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        Intrinsics.checkNotNullParameter((Object)((Object)selected), (String)"selected");
        Intrinsics.checkNotNullParameter(modules, (String)"modules");
        this.render(g, x, y, w, alpha, alpha, dt, selected, modules);
    }

    public final void render(@NotNull DrawContext g, float x, float y, float w, float alpha, float slideAlpha, float dt, @NotNull Category selected, @NotNull List<? extends Module> modules) {
        boolean focusActive;
        int n;
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        Intrinsics.checkNotNullParameter((Object)((Object)selected), (String)"selected");
        Intrinsics.checkNotNullParameter(modules, (String)"modules");
        float listX = x + 117.0f;
        float listY = y + 5.0f + HEADER_OFFSET;
        float listW = w - 122.0f;
        float colW = ModuleListRenderer.Companion.colW(listW);
        float c1x = listX + 5.0f;
        float c2x = c1x + colW + 5.0f;
        float scrollAreaH = 280.0f - HEADER_OFFSET;
        float factor = 1.0f - (float)Math.exp(-dt * 14.0f);
        this.scroll += (this.scrollTarget - this.scroll) * factor;
        if (Math.abs(this.scrollTarget - this.scroll) < 0.05f) {
            this.scroll = this.scrollTarget;
        }
        if (this.transitioning) {
            if (this.fadeOutTime > 0.0f) {
                this.fadeOutTime -= dt;
                float fadeAlpha = alpha * Math.max(0.0f, this.fadeOutTime / 0.15f);
                this.renderCards(g, listX, listY, listW, colW, c1x, c2x, scrollAreaH, fadeAlpha, slideAlpha, dt, this.fadingOut, false);
            }
            return;
        }
        this.contentH = this.totalContentH(modules, colW);
        float maxScroll = Math.max(0.0f, this.contentH - scrollAreaH);
        this.scrollTarget = RangesKt.coerceIn((float)this.scrollTarget, (float)0.0f, (float)maxScroll);
        this.topBlurT = maxScroll <= 0.5f ? 0.0f : RangesKt.coerceIn((float)(this.scroll / 10.0f), (float)0.0f, (float)1.0f);
        int modulesSig = ModuleListRenderer.Companion.modulesSignature(modules);
        boolean appearWork = this.appearComposite && (selected != this.lastRendered || modulesSig != this.lastModulesSig || this.scrollBar.isDragging() || Math.abs(this.scrollTarget - this.scroll) > 0.05f || this.topBlurT > 0.01f || this.hasUnfinishedAppear());
        this.lastModulesSig = modulesSig;
        if (selected != this.lastRendered) {
            this.lastRendered = selected;
            this.startRowAnims(ModuleListRenderer.Companion.rowCount(modules.size()));
        }
        if (this.focusName != null) {
            String string = this.focusName;
            Intrinsics.checkNotNull((Object)string);
            n = ModuleListRenderer.Companion.indexOfModule(modules, string);
        } else {
            n = -1;
        }
        int focusIdx = n;
        boolean bl = focusActive = focusIdx >= 0 && System.currentTimeMillis() < this.focusUntilMs;
        if (this.focusScrollPending && focusIdx >= 0) {
            float top = 5.0f + this.cardTopInColumn(modules, focusIdx, colW);
            float cardH = this.baseCardHeight(modules.get(focusIdx), colW);
            this.scrollTarget = RangesKt.coerceIn((float)(top - (scrollAreaH - cardH) * 0.5f), (float)0.0f, (float)maxScroll);
            this.focusScrollPending = false;
        }
        this.focusDimT += ((focusActive ? 1.0f : 0.0f) - this.focusDimT) * (1.0f - (float)Math.exp(-dt * 8.0f));
        if (!focusActive && this.focusDimT < 0.01f && !this.focusScrollPending) {
            this.focusName = null;
        }
        float trackX = x + w - 7.5f;
        float effR = RenderHelper.effectiveCornerRadius(12.0f, listW, 280.0f);
        float botExtra = Math.max(0.0f, RenderHelper.cornerEdgeInset(effR, 1.0f) + 1.5f - 3.0f);
        float trackY = listY + (float)3;
        float trackH = scrollAreaH - (float)6 - botExtra;
        float newScroll = this.scrollBar.render(trackX, trackY, trackH, scrollAreaH, this.contentH, this.scroll, alpha);
        if (this.scrollBar.isDragging()) {
            this.scroll = newScroll;
            this.scrollTarget = newScroll;
        }
        if (appearWork) {
            GuiRenderState rs = ((GuiGraphicsExtractorAccessor)g).kimiko$getGuiRenderState();
            rs.createNewRootLayer();
            rs.applyBlur();
            UI.Companion.markCardStratum();
        }
        this.renderCards(g, listX, listY, listW, colW, c1x, c2x, scrollAreaH, alpha, slideAlpha, dt, modules, true);
        this.appearInitialFrame = false;
    }

    public final boolean scrollbarGrab(float mx, float my) {
        return this.scrollBar.tryGrab(mx, my);
    }

    public final void scrollbarRelease() {
        this.scrollBar.release();
    }

    public final boolean scrollbarDragging() {
        return this.scrollBar.isDragging();
    }

    private final void renderCards(DrawContext g, float listX, float listY, float listW, float colW, float c1x, float c2x, float scrollAreaH, float alpha, float slideAlpha, float dt, List<? extends Module> modules, boolean animateAppear) {
        float fadeBand = 26.0f * this.topBlurT;
        Render2D.pushScissor(g, listX, listY - (float)4, listW, scrollAreaH + (float)4);
        RoundedScissor.push(g, listX, listY - (float)4, listW, scrollAreaH + (float)4, 0.0f, 0.0f, 12.0f, 0.0f, fadeBand);
        float slideOff = (1.0f - slideAlpha) * (float)8;
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        boolean interactive = UI.Companion.isOpen();
        boolean mouseInList = interactive && mx >= listX && mx <= listX + listW && my >= listY && my <= listY + scrollAreaH && !UI.Companion.isSettingsPopupVisible();
        float hoverFactor = interactive ? 1.0f - (float)Math.exp(-dt * 16.0f) : 0.0f;
        EnumSet<Server> here = ServerRestrictions.current();
        int n = ((Collection)modules).size();
        for (int idx = 0; idx < n; ++idx) {
            Decelerate ea;
            float contBot;
            float bot;
            float ma;
            float rowAppear;
            Module mod = modules.get(idx);
            boolean restricted = ServerRestrictions.isBlockedBy(mod, here);
            int col = idx % 2;
            int row = idx / 2;
            float cx = col == 0 ? c1x : c2x;
            float cardH = this.baseCardHeight(mod, colW);
            float cy = listY + 5.0f + this.cardTopInColumn(modules, idx, colW) - this.scroll + slideOff;
            boolean hovered = mouseInList && mx >= cx && mx <= cx + colW && my >= cy && my <= cy + cardH;
            float hoverT = ((Number)this.hoverAnims.getOrDefault(mod.getName(), Float.valueOf(0.0f))).floatValue();
            hoverT += ((hovered ? 1.0f : 0.0f) - hoverT) * hoverFactor;
            this.hoverAnims.put(mod.getName(), Float.valueOf(hoverT));
            if (cy + cardH < listY - (float)5 || cy > listY + scrollAreaH + (float)5) continue;
            float f = rowAppear = animateAppear ? this.rowAppear(row) : 1.0f;
            if (rowAppear < 0.001f) continue;
            float mods = alpha;
            if (this.focusDimT > 0.01f && !Intrinsics.areEqual((Object)mod.getName(), (Object)this.focusName)) {
                mods *= 1.0f - 0.75f * this.focusDimT;
            }
            if (restricted) {
                mods *= 0.35f;
            }
            float settleT = Math.min(1.0f, rowAppear / 0.6f);
            float presence = settleT * settleT;
            float fadeT = rowAppear < 0.6f ? 0.0f : (rowAppear - 0.6f) / 0.4f;
            float blurPhase = 1.0f - fadeT * fadeT * (3.0f - 2.0f * fadeT);
            float renderCy = cy + (1.0f - settleT) * 6.0f;
            boolean cardAppearing = rowAppear < 0.999f;
            boolean compositeCard = this.appearComposite && cardAppearing;
            CardText text = this.nextCardText();
            float cardAlpha = ma = mods * (!cardAppearing || compositeCard ? 1.0f : presence);
            if (cardAppearing) {
                float cardScale = 0.85f + 0.15f * settleT;
                if (compositeCard && this.cardBlurCount < 64) {
                    float blurTop = Math.max(renderCy, listY);
                    float blurBottom = Math.min(renderCy + cardH, listY + scrollAreaH);
                    if (blurBottom - blurTop > 0.5f) {
                        int off = this.cardBlurCount * 6;
                        this.cardBlurRects[off] = cx;
                        this.cardBlurRects[off + 1] = blurTop;
                        this.cardBlurRects[off + 2] = colW;
                        this.cardBlurRects[off + 3] = blurBottom - blurTop;
                        this.cardBlurRects[off + 4] = presence;
                        this.cardBlurRects[off + 5] = blurPhase;
                        int n2 = this.cardBlurCount;
                        this.cardBlurCount = n2 + 1;
                        this.cardBlurMaxPhase = Math.max(this.cardBlurMaxPhase, blurPhase);
                    }
                }
                float ox = cx + colW * 0.5f;
                float oy = renderCy + cardH * 0.5f;
                text.setAppearing(true);
                text.setOriginX(ox);
                text.setOriginY(oy);
                text.setScale(cardScale);
            }
            float rbr = 6.0f;
            float rtr = 6.0f;
            if (col == 1 && (bot = renderCy + cardH) > (contBot = listY + scrollAreaH) - (float)16) {
                float t = RangesKt.coerceIn((float)((bot - (contBot - (float)16)) / 16.0f), (float)0.0f, (float)1.0f);
                rbr = 6.0f + 6.0f * t;
            }
            ea = this.enableAnims.computeIfAbsent(mod.getName(), k -> Companion.createAnim(220));
            ea.setDirection(mod.isEnabled() ? Direction.FORWARDS : Direction.BACKWARDS);
            Double d = ea.getOutput();
            float enableT = (float)(d != null ? d : 0.0);
            float cardLift = Math.max(hoverT, enableT * 0.65f);
            float cardSampleX = cx + colW * 0.5f;
            float cardSampleY = renderCy + cardH * 0.5f;
            text.setCardHeight(cardH);
            text.setRadiusTopRight(rtr);
            text.setRadiusBottomRight(rbr);
            text.setCardAlpha(cardAlpha);
            text.setCardLift(cardLift);
            text.setFillTop(ModuleListRenderer.Companion.rgba(255, 255, 255, (7.0f + 5.0f * hoverT) * cardAlpha));
            text.setFillBottom(ModuleListRenderer.Companion.rgba(255, 255, 255, 1.5f * cardAlpha));
            text.setHasAccent(enableT > 0.01f);
            if (text.getHasAccent()) {
                text.setAccentA(ClientAccent.gradientAAt(22.0f * enableT * cardAlpha, cardSampleX, cardSampleY));
                text.setAccentB(ClientAccent.gradientBAt(22.0f * enableT * cardAlpha, cardSampleX, cardSampleY));
            }
            float topAlpha = (30.0f + 22.0f * hoverT) * cardAlpha + (78.0f * cardAlpha - (30.0f + 22.0f * hoverT) * cardAlpha) * enableT;
            float bottomAlpha = (10.0f + 8.0f * hoverT) * cardAlpha + (52.0f * cardAlpha - (10.0f + 8.0f * hoverT) * cardAlpha) * enableT;
            text.setOutlineTop(ModuleListRenderer.Companion.rgba(255, 255, 255, topAlpha));
            text.setOutlineBottom(ModuleListRenderer.Companion.rgba(255, 255, 255, bottomAlpha));
            text.setOutlineStrength(enableT);
            KeyBind bind = mod.getBind();
            boolean voiceBind = mod.getBindType() == Module.BindType.VOICE;
            float badgeSlot = 0.0f;
            if (voiceBind || bind != null && bind.isBound()) {
                String string;
                if (voiceBind) {
                    string = mod.hasVoiceBind() ? I18n.tr("ГОЛОС") : I18n.tr("ЗАПИШИ");
                } else {
                    KeyBind keyBind = bind;
                    Intrinsics.checkNotNull((Object)keyBind);
                    string = ModuleListRenderer.Companion.badgeLabel(keyBind);
                }
                String badgeText = string;
                float badgeW = Math.max(10.0f, Fonts.MEDIUM.width(badgeText, 5.5f) + 6.0f);
                float badgeX = cx + 8.0f;
                float badgeY = renderCy + 10.75f - 5.0f;
                text.setBadgeX(badgeX);
                text.setBadgeY(badgeY);
                text.setBadgeWidth(badgeW);
                text.setBadgeFill(ModuleListRenderer.Companion.rgba(255, 255, 255, 16.0f * ma));
                text.setBadgeOutline(ModuleListRenderer.Companion.rgba(255, 255, 255, 30.0f * ma));
                float tw = Fonts.MEDIUM.width(badgeText, 5.5f);
                text.setBadgeText(badgeText);
                text.setBadgeTextX(badgeX + (badgeW - tw) * 0.5f);
                text.setBadgeTextY(badgeY + 1.75f);
                text.setBadgeColor(ModuleListRenderer.Companion.rgba(255, 255, 255, 205.0f * ma));
                badgeSlot = badgeW + 5.0f;
            }
            float nameX = cx + 8.0f + badgeSlot;
            text.setModule(mod);
            text.setNameX(nameX);
            text.setNameY(renderCy + 6.0f);
            text.setNameColor(ModuleListRenderer.Companion.rgba(255, 255, 255, (160.0f + 95.0f * enableT) * ma));
            text.setCardX(cx);
            text.setCardY(renderCy);
            text.setCardWidth(colW);
            text.setAlpha(ma);
            text.setEnableT(enableT);
            boolean pinned = Companion.isPinned(mod);
            if (!pinned) {
                float toggleX = cx + colW - 8.5f - 8.0f;
                float toggleY = renderCy + 10.75f - 4.25f;
                boolean checkHover = hovered && mx >= toggleX - 2.0f && mx <= toggleX + 8.5f + 2.0f && my >= toggleY - 2.0f && my <= toggleY + 8.5f + 2.0f;
                text.setHasCheck(true);
                text.setCheckX(toggleX);
                text.setCheckY(toggleY);
                text.setCheckEnableT(enableT);
                text.setCheckHover(checkHover);
            }
            if (!(!((Collection)mod.getSettings().all()).isEmpty())) continue;
            boolean exp = UI.Companion.isSettingsOpenFor(mod.getName());
            float gw = Fonts.KIMIKO.msdfWidth(GEAR_GLYPH, 7.0f);
            float gearX = Companion.settingsIconX(cx, colW, mod);
            float gearY = renderCy + 10.75f - 3.5f;
            boolean gearHover = hovered && mx >= gearX - 3.0f && mx <= gearX + gw + 3.0f && my >= renderCy + 4.0f && my <= renderCy + 17.5f;
            float gearT = ((Number)this.gearHoverAnims.getOrDefault(mod.getName(), Float.valueOf(0.0f))).floatValue();
            gearT += ((gearHover ? 1.0f : 0.0f) - gearT) * hoverFactor;
            this.gearHoverAnims.put(mod.getName(), Float.valueOf(gearT));
            float openT = ((Number)this.gearOpenAnims.getOrDefault(mod.getName(), Float.valueOf(0.0f))).floatValue();
            if (interactive) {
                openT += ((exp ? 1.0f : 0.0f) - openT) * (1.0f - (float)Math.exp(-dt * 12.0f));
            }
            this.gearOpenAnims.put(mod.getName(), Float.valueOf(openT));
            int gearClr = ColorEngine.lerpColor(ModuleListRenderer.Companion.rgba(255, 255, 255, (120.0f + 105.0f * gearT) * ma), ClientAccent.accentSoftAt(235.0f * ma, gearX, gearY), openT);
            text.setHasGear(true);
            text.setGearX(gearX);
            text.setGearY(gearY);
            text.setGearColor(gearClr);
        }
        this.drawCardShapes(g);
        this.drawCardText(g);
        RoundedScissor.pop();
        Render2D.popScissor(g);
    }

    private final boolean hasUnfinishedAppear() {
        for (Decelerate d : this.rowAppearAnims.values()) {
            Double d2 = d.getOutput();
            double d3 = d2 != null ? d2 : 0.0;
            float v = (float)d3;
            if (!(v < 0.999f)) continue;
            return true;
        }
        return false;
    }

    public final void setAppearComposite(boolean value) {
        this.appearComposite = value;
    }

    public final void resetCardBlur() {
        this.cardBlurCount = 0;
        this.cardBlurMaxPhase = 0.0f;
    }

    @NotNull
    public final float[] cardBlurRects() {
        return this.cardBlurRects;
    }

    public final int cardBlurCount() {
        return this.cardBlurCount;
    }

    public final float cardBlurMaxPhase() {
        return this.cardBlurMaxPhase;
    }

    private final CardText nextCardText() {
        if (this.cardTextCount == this.cardTexts.size()) {
            this.cardTexts.add(new CardText());
        }
        int n = this.cardTextCount;
        this.cardTextCount = n + 1;
        CardText entry = this.cardTexts.get(n);
        entry.reset();
        return entry;
    }

    private final void drawCardShapes(DrawContext g) {
        CardText entry;
        int index;
        int n = this.cardTextCount;
        for (index = 0; index < n; ++index) {
            entry = this.cardTexts.get(index);
            if (entry.getModule() == null) continue;
            ModuleListRenderer.Companion.pushCardPose(g, entry);
            RectUtil.drawGlassCard(entry.getCardX(), entry.getCardY(), entry.getCardWidth(), entry.getCardHeight(), 6.0f, entry.getRadiusTopRight(), entry.getRadiusBottomRight(), 6.0f, entry.getCardAlpha(), entry.getCardLift());
            ModuleListRenderer.Companion.popCardPose(g, entry);
        }
        n = this.cardTextCount;
        for (index = 0; index < n; ++index) {
            entry = this.cardTexts.get(index);
            if (entry.getModule() == null) continue;
            ModuleListRenderer.Companion.pushCardPose(g, entry);
            Render2D.rect(entry.getCardX(), entry.getCardY(), entry.getCardWidth(), entry.getCardHeight(), 6.0f, entry.getRadiusTopRight(), entry.getRadiusBottomRight(), 6.0f, entry.getFillTop(), entry.getFillTop(), entry.getFillBottom(), entry.getFillBottom());
            if (entry.getHasAccent()) {
                Render2D.rect(entry.getCardX(), entry.getCardY(), entry.getCardWidth(), entry.getCardHeight(), 6.0f, entry.getRadiusTopRight(), entry.getRadiusBottomRight(), 6.0f, entry.getAccentA(), entry.getAccentB(), entry.getAccentB(), entry.getAccentA());
            }
            ModuleListRenderer.Companion.popCardPose(g, entry);
        }
        n = this.cardTextCount;
        for (index = 0; index < n; ++index) {
            entry = this.cardTexts.get(index);
            if (entry.getModule() == null) continue;
            ModuleListRenderer.Companion.pushCardPose(g, entry);
            Render2D.outlineClient(entry.getCardX(), entry.getCardY(), entry.getCardWidth(), entry.getCardHeight(), 6.0f, entry.getRadiusTopRight(), entry.getRadiusBottomRight(), 6.0f, 0.6f, entry.getOutlineTop(), entry.getOutlineTop(), entry.getOutlineBottom(), entry.getOutlineBottom(), entry.getOutlineStrength());
            ModuleListRenderer.Companion.popCardPose(g, entry);
        }
        n = this.cardTextCount;
        for (index = 0; index < n; ++index) {
            entry = this.cardTexts.get(index);
            if (entry.getModule() == null || entry.getBadgeText() == null) continue;
            ModuleListRenderer.Companion.pushCardPose(g, entry);
            Render2D.rect(entry.getBadgeX(), entry.getBadgeY(), entry.getBadgeWidth(), 10.0f, 3.0f, entry.getBadgeFill());
            Render2D.outline(entry.getBadgeX(), entry.getBadgeY(), entry.getBadgeWidth(), 10.0f, 3.0f, 0.5f, entry.getBadgeOutline());
            ModuleListRenderer.Companion.popCardPose(g, entry);
        }
        n = this.cardTextCount;
        for (index = 0; index < n; ++index) {
            entry = this.cardTexts.get(index);
            if (entry.getModule() == null || !entry.getHasCheck()) continue;
            ModuleListRenderer.Companion.pushCardPose(g, entry);
            ModuleListRenderer.Companion.drawModuleCheck(entry.getCheckX(), entry.getCheckY(), entry.getCheckEnableT(), entry.getAlpha(), entry.getCheckHover());
            ModuleListRenderer.Companion.popCardPose(g, entry);
        }
    }

    private final void drawCardText(DrawContext g) {
        int n = this.cardTextCount;
        for (int index = 0; index < n; ++index) {
            CardText entry = this.cardTexts.get(index);
            Module mod = entry.getModule();
            if (mod == null) continue;
            ModuleListRenderer.Companion.pushCardPose(g, entry);
            if (entry.getBadgeText() != null) {
                Fonts.MEDIUM.draw(entry.getBadgeText(), entry.getBadgeTextX(), entry.getBadgeTextY(), 5.5f, entry.getBadgeColor());
            }
            Fonts.MEDIUM.draw(mod.getDisplayName(), entry.getNameX(), entry.getNameY(), 7.5f, entry.getNameColor());
            this.renderDesc(mod, entry.getCardX(), entry.getCardY(), entry.getCardWidth(), entry.getAlpha(), entry.getEnableT());
            if (entry.getHasGear()) {
                Fonts.KIMIKO.msdf(GEAR_GLYPH, entry.getGearX(), entry.getGearY(), 7.0f, entry.getGearColor());
            }
            ModuleListRenderer.Companion.popCardPose(g, entry);
        }
        this.cardTextCount = 0;
    }

    private final void renderDesc(Module mod, float cx, float cy, float colW, float ma, float enableT) {
        List<String> lines = this.descLines(mod, colW);
        if (lines.isEmpty()) {
            return;
        }
        int clr = ModuleListRenderer.Companion.rgba(255, 255, 255, (78.0f + 27.0f * enableT) * ma);
        float ly = cy + 19.0f;
        for (String line : lines) {
            Fonts.MEDIUM.draw(line, cx + 8.0f, ly, 6.0f, clr);
            ly += 7.0f;
        }
    }

    public final void warmup() {
        int faint = 0x1FFFFFF;
        float ox = -500.0f;
        float oy = -500.0f;
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,:;!?-_()[]{}<>/\\|@#$%^&*+=\"'`~АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        Fonts.MEDIUM.draw(charset, ox, oy, 6.0f, faint);
        for (Category cat : Category.getEntries()) {
            for (Module mod : this.getModules(cat)) {
                this.enableAnims.computeIfAbsent(mod.getName(), k -> Companion.createAnim(220));
                Fonts.KIMIKO.msdf(ICON_CACHE.get((Object)cat), ox, oy, 6.5f, faint);
                Fonts.MEDIUM.draw(mod.getDisplayName(), ox, oy, 7.5f, faint);
                Fonts.MEDIUM.shimmer(mod.getDisplayName(), ox, oy, 7.5f, faint, 0.5f, 0.35f, 0.5f, 0.5f);
                String d = mod.getDisplayDescription();
                Fonts.MEDIUM.draw(d, ox, oy, 6.0f, faint);
                Fonts.MEDIUM.width(mod.getDisplayName(), 7.5f);
                Fonts.MEDIUM.width(d, 6.0f);
            }
        }
        Fonts.KIMIKO.msdf(GEAR_GLYPH, ox, oy, 7.0f, faint);
        Render2D.rect(ox, oy, 30.0f, 30.0f, 4.0f, 4.0f, 4.0f, 4.0f, faint);
        Render2D.outline(ox, oy, 30.0f, 30.0f, 4.0f, 4.0f, 4.0f, 4.0f, 0.7f, 0, 26447859, 0, 26447859);
    }

    public final boolean hitSettingsIcon(float mx, float my, float cx, float cy, float colW, @NotNull Module mod) {
        Intrinsics.checkNotNullParameter((Object)mod, (String)"mod");
        if (mod.getSettings().all().isEmpty()) {
            return false;
        }
        float gw = Fonts.KIMIKO.msdfWidth(GEAR_GLYPH, 7.0f);
        float gearX = Companion.settingsIconX(cx, colW, mod);
        return mx >= gearX - 3.5f && mx <= gearX + gw + 3.5f && my >= cy + 3.5f && my <= cy + 18.0f;
    }

    private static final List getModules$lambda$0(Category c) {
        ModuleManager moduleManager = ModuleManager.Companion.get();
        Intrinsics.checkNotNull((Object)((Object)c));
        return moduleManager.getByCategory(c);
    }

    private static final List getModules$lambda$1(Function1 $tmp0, Object p0) {
        return (List)$tmp0.invoke(p0);
    }

    private static final Decelerate renderCards$lambda$0(String it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return ModuleListRenderer.Companion.createAnim(220);
    }

    private static final Decelerate renderCards$lambda$1(Function1 $tmp0, Object p0) {
        return (Decelerate)$tmp0.invoke(p0);
    }

    private static final Decelerate warmup$lambda$0(String it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return ModuleListRenderer.Companion.createAnim(220);
    }

    private static final Decelerate warmup$lambda$1(Function1 $tmp0, Object p0) {
        return (Decelerate)$tmp0.invoke(p0);
    }

    @JvmStatic
    public static final int pinnedOrder(@NotNull Module module) {
        return Companion.pinnedOrder(module);
    }

    @JvmStatic
    public static final boolean isPinned(@NotNull Module module) {
        return Companion.isPinned(module);
    }

    @JvmStatic
    @NotNull
    public static final List<Module> sortPinnedFirst(@NotNull List<? extends Module> modules) {
        return Companion.sortPinnedFirst(modules);
    }

    @JvmStatic
    public static final float settingsIconX(float cx, float colW, @NotNull Module mod) {
        return Companion.settingsIconX(cx, colW, mod);
    }

    @JvmStatic
    private static final int modulesSignature(List<? extends Module> modules) {
        return ModuleListRenderer.Companion.modulesSignature(modules);
    }

    static {
        for (Category c : Category.getEntries()) {
            ((Map)ICON_CACHE).put(c, String.valueOf(ModuleListRenderer.Companion.iconChar(c)));
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\ba\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003R$\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\"\u0010\u0017\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\"\u0010\u001a\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u000f\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\"\u0010\u001d\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u000f\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R\"\u0010 \u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010\u000f\u001a\u0004\b!\u0010\u0011\"\u0004\b\"\u0010\u0013R\"\u0010#\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010\u000f\u001a\u0004\b$\u0010\u0011\"\u0004\b%\u0010\u0013R\"\u0010'\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010.\u001a\u0004\u0018\u00010-8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\"\u00104\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b4\u0010\u000f\u001a\u0004\b5\u0010\u0011\"\u0004\b6\u0010\u0013R\"\u00107\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b7\u0010\u000f\u001a\u0004\b8\u0010\u0011\"\u0004\b9\u0010\u0013R\"\u0010:\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b:\u0010(\u001a\u0004\b;\u0010*\"\u0004\b<\u0010,R\"\u0010>\u001a\u00020=8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\"\u0010D\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bD\u0010\u000f\u001a\u0004\bE\u0010\u0011\"\u0004\bF\u0010\u0013R\"\u0010G\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bG\u0010\u000f\u001a\u0004\bH\u0010\u0011\"\u0004\bI\u0010\u0013R\"\u0010J\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bJ\u0010(\u001a\u0004\bK\u0010*\"\u0004\bL\u0010,R\"\u0010M\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bM\u0010\u000f\u001a\u0004\bN\u0010\u0011\"\u0004\bO\u0010\u0013R\"\u0010P\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bP\u0010\u000f\u001a\u0004\bQ\u0010\u0011\"\u0004\bR\u0010\u0013R\"\u0010S\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bS\u0010\u000f\u001a\u0004\bT\u0010\u0011\"\u0004\bU\u0010\u0013R\"\u0010V\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bV\u0010\u000f\u001a\u0004\bW\u0010\u0011\"\u0004\bX\u0010\u0013R\"\u0010Y\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bY\u0010\u000f\u001a\u0004\bZ\u0010\u0011\"\u0004\b[\u0010\u0013R\"\u0010\\\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\\\u0010(\u001a\u0004\b]\u0010*\"\u0004\b^\u0010,R\"\u0010_\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b_\u0010(\u001a\u0004\b`\u0010*\"\u0004\ba\u0010,R\"\u0010b\u001a\u00020=8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bb\u0010?\u001a\u0004\bc\u0010A\"\u0004\bd\u0010CR\"\u0010e\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\be\u0010(\u001a\u0004\bf\u0010*\"\u0004\bg\u0010,R\"\u0010h\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bh\u0010(\u001a\u0004\bi\u0010*\"\u0004\bj\u0010,R\"\u0010k\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bk\u0010(\u001a\u0004\bl\u0010*\"\u0004\bm\u0010,R\"\u0010n\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bn\u0010(\u001a\u0004\bo\u0010*\"\u0004\bp\u0010,R\"\u0010q\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bq\u0010\u000f\u001a\u0004\br\u0010\u0011\"\u0004\bs\u0010\u0013R\"\u0010t\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bt\u0010\u000f\u001a\u0004\bu\u0010\u0011\"\u0004\bv\u0010\u0013R\"\u0010w\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bw\u0010\u000f\u001a\u0004\bx\u0010\u0011\"\u0004\by\u0010\u0013R\"\u0010z\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bz\u0010\u000f\u001a\u0004\b{\u0010\u0011\"\u0004\b|\u0010\u0013R\"\u0010}\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b}\u0010(\u001a\u0004\b~\u0010*\"\u0004\b\u007f\u0010,R&\u0010\u0080\u0001\u001a\u00020&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0080\u0001\u0010(\u001a\u0005\b\u0081\u0001\u0010*\"\u0005\b\u0082\u0001\u0010,R&\u0010\u0083\u0001\u001a\u00020=8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0083\u0001\u0010?\u001a\u0005\b\u0084\u0001\u0010A\"\u0005\b\u0085\u0001\u0010CR&\u0010\u0086\u0001\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0086\u0001\u0010\u000f\u001a\u0005\b\u0087\u0001\u0010\u0011\"\u0005\b\u0088\u0001\u0010\u0013R&\u0010\u0089\u0001\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0089\u0001\u0010\u000f\u001a\u0005\b\u008a\u0001\u0010\u0011\"\u0005\b\u008b\u0001\u0010\u0013R&\u0010\u008c\u0001\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u008c\u0001\u0010\u000f\u001a\u0005\b\u008d\u0001\u0010\u0011\"\u0005\b\u008e\u0001\u0010\u0013R&\u0010\u008f\u0001\u001a\u00020=8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u008f\u0001\u0010?\u001a\u0005\b\u0090\u0001\u0010A\"\u0005\b\u0091\u0001\u0010CR&\u0010\u0092\u0001\u001a\u00020=8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0092\u0001\u0010?\u001a\u0005\b\u0093\u0001\u0010A\"\u0005\b\u0094\u0001\u0010CR&\u0010\u0095\u0001\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0095\u0001\u0010\u000f\u001a\u0005\b\u0096\u0001\u0010\u0011\"\u0005\b\u0097\u0001\u0010\u0013R&\u0010\u0098\u0001\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u0098\u0001\u0010\u000f\u001a\u0005\b\u0099\u0001\u0010\u0011\"\u0005\b\u009a\u0001\u0010\u0013R&\u0010\u009b\u0001\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0015\n\u0005\b\u009b\u0001\u0010\u000f\u001a\u0005\b\u009c\u0001\u0010\u0011\"\u0005\b\u009d\u0001\u0010\u0013\u00a8\u0006\u009e\u0001"}, d2={"Lrtx/kimiko/api/ui/module/ModuleListRenderer$CardText;", "", "<init>", "()V", "", "reset", "Lrtx/kimiko/api/modules/Module;", "module", "Lrtx/kimiko/api/modules/Module;", "getModule", "()Lrtx/kimiko/api/modules/Module;", "setModule", "(Lrtx/kimiko/api/modules/Module;)V", "", "cardX", "F", "getCardX", "()F", "setCardX", "(F)V", "cardY", "getCardY", "setCardY", "cardWidth", "getCardWidth", "setCardWidth", "alpha", "getAlpha", "setAlpha", "enableT", "getEnableT", "setEnableT", "nameX", "getNameX", "setNameX", "nameY", "getNameY", "setNameY", "", "nameColor", "I", "getNameColor", "()I", "setNameColor", "(I)V", "", "badgeText", "Ljava/lang/String;", "getBadgeText", "()Ljava/lang/String;", "setBadgeText", "(Ljava/lang/String;)V", "badgeTextX", "getBadgeTextX", "setBadgeTextX", "badgeTextY", "getBadgeTextY", "setBadgeTextY", "badgeColor", "getBadgeColor", "setBadgeColor", "", "hasGear", "Z", "getHasGear", "()Z", "setHasGear", "(Z)V", "gearX", "getGearX", "setGearX", "gearY", "getGearY", "setGearY", "gearColor", "getGearColor", "setGearColor", "cardHeight", "getCardHeight", "setCardHeight", "radiusTopRight", "getRadiusTopRight", "setRadiusTopRight", "radiusBottomRight", "getRadiusBottomRight", "setRadiusBottomRight", "cardAlpha", "getCardAlpha", "setCardAlpha", "cardLift", "getCardLift", "setCardLift", "fillTop", "getFillTop", "setFillTop", "fillBottom", "getFillBottom", "setFillBottom", "hasAccent", "getHasAccent", "setHasAccent", "accentA", "getAccentA", "setAccentA", "accentB", "getAccentB", "setAccentB", "outlineTop", "getOutlineTop", "setOutlineTop", "outlineBottom", "getOutlineBottom", "setOutlineBottom", "outlineStrength", "getOutlineStrength", "setOutlineStrength", "badgeX", "getBadgeX", "setBadgeX", "badgeY", "getBadgeY", "setBadgeY", "badgeWidth", "getBadgeWidth", "setBadgeWidth", "badgeFill", "getBadgeFill", "setBadgeFill", "badgeOutline", "getBadgeOutline", "setBadgeOutline", "hasCheck", "getHasCheck", "setHasCheck", "checkX", "getCheckX", "setCheckX", "checkY", "getCheckY", "setCheckY", "checkEnableT", "getCheckEnableT", "setCheckEnableT", "checkHover", "getCheckHover", "setCheckHover", "appearing", "getAppearing", "setAppearing", "originX", "getOriginX", "setOriginX", "originY", "getOriginY", "setOriginY", "scale", "getScale", "setScale", "rtx.kimiko:kimiko"})
    private static final class CardText {
        @Nullable
        private Module module;
        private float cardX;
        private float cardY;
        private float cardWidth;
        private float alpha;
        private float enableT;
        private float nameX;
        private float nameY;
        private int nameColor;
        @Nullable
        private String badgeText;
        private float badgeTextX;
        private float badgeTextY;
        private int badgeColor;
        private boolean hasGear;
        private float gearX;
        private float gearY;
        private int gearColor;
        private float cardHeight;
        private float radiusTopRight;
        private float radiusBottomRight;
        private float cardAlpha;
        private float cardLift;
        private int fillTop;
        private int fillBottom;
        private boolean hasAccent;
        private int accentA;
        private int accentB;
        private int outlineTop;
        private int outlineBottom;
        private float outlineStrength;
        private float badgeX;
        private float badgeY;
        private float badgeWidth;
        private int badgeFill;
        private int badgeOutline;
        private boolean hasCheck;
        private float checkX;
        private float checkY;
        private float checkEnableT;
        private boolean checkHover;
        private boolean appearing;
        private float originX;
        private float originY;
        private float scale = 1.0f;

        @Nullable
        public final Module getModule() {
            return this.module;
        }

        public final void setModule(@Nullable Module module) {
            this.module = module;
        }

        public final float getCardX() {
            return this.cardX;
        }

        public final void setCardX(float f) {
            this.cardX = f;
        }

        public final float getCardY() {
            return this.cardY;
        }

        public final void setCardY(float f) {
            this.cardY = f;
        }

        public final float getCardWidth() {
            return this.cardWidth;
        }

        public final void setCardWidth(float f) {
            this.cardWidth = f;
        }

        public final float getAlpha() {
            return this.alpha;
        }

        public final void setAlpha(float f) {
            this.alpha = f;
        }

        public final float getEnableT() {
            return this.enableT;
        }

        public final void setEnableT(float f) {
            this.enableT = f;
        }

        public final float getNameX() {
            return this.nameX;
        }

        public final void setNameX(float f) {
            this.nameX = f;
        }

        public final float getNameY() {
            return this.nameY;
        }

        public final void setNameY(float f) {
            this.nameY = f;
        }

        public final int getNameColor() {
            return this.nameColor;
        }

        public final void setNameColor(int n) {
            this.nameColor = n;
        }

        @Nullable
        public final String getBadgeText() {
            return this.badgeText;
        }

        public final void setBadgeText(@Nullable String string) {
            this.badgeText = string;
        }

        public final float getBadgeTextX() {
            return this.badgeTextX;
        }

        public final void setBadgeTextX(float f) {
            this.badgeTextX = f;
        }

        public final float getBadgeTextY() {
            return this.badgeTextY;
        }

        public final void setBadgeTextY(float f) {
            this.badgeTextY = f;
        }

        public final int getBadgeColor() {
            return this.badgeColor;
        }

        public final void setBadgeColor(int n) {
            this.badgeColor = n;
        }

        public final boolean getHasGear() {
            return this.hasGear;
        }

        public final void setHasGear(boolean bl) {
            this.hasGear = bl;
        }

        public final float getGearX() {
            return this.gearX;
        }

        public final void setGearX(float f) {
            this.gearX = f;
        }

        public final float getGearY() {
            return this.gearY;
        }

        public final void setGearY(float f) {
            this.gearY = f;
        }

        public final int getGearColor() {
            return this.gearColor;
        }

        public final void setGearColor(int n) {
            this.gearColor = n;
        }

        public final float getCardHeight() {
            return this.cardHeight;
        }

        public final void setCardHeight(float f) {
            this.cardHeight = f;
        }

        public final float getRadiusTopRight() {
            return this.radiusTopRight;
        }

        public final void setRadiusTopRight(float f) {
            this.radiusTopRight = f;
        }

        public final float getRadiusBottomRight() {
            return this.radiusBottomRight;
        }

        public final void setRadiusBottomRight(float f) {
            this.radiusBottomRight = f;
        }

        public final float getCardAlpha() {
            return this.cardAlpha;
        }

        public final void setCardAlpha(float f) {
            this.cardAlpha = f;
        }

        public final float getCardLift() {
            return this.cardLift;
        }

        public final void setCardLift(float f) {
            this.cardLift = f;
        }

        public final int getFillTop() {
            return this.fillTop;
        }

        public final void setFillTop(int n) {
            this.fillTop = n;
        }

        public final int getFillBottom() {
            return this.fillBottom;
        }

        public final void setFillBottom(int n) {
            this.fillBottom = n;
        }

        public final boolean getHasAccent() {
            return this.hasAccent;
        }

        public final void setHasAccent(boolean bl) {
            this.hasAccent = bl;
        }

        public final int getAccentA() {
            return this.accentA;
        }

        public final void setAccentA(int n) {
            this.accentA = n;
        }

        public final int getAccentB() {
            return this.accentB;
        }

        public final void setAccentB(int n) {
            this.accentB = n;
        }

        public final int getOutlineTop() {
            return this.outlineTop;
        }

        public final void setOutlineTop(int n) {
            this.outlineTop = n;
        }

        public final int getOutlineBottom() {
            return this.outlineBottom;
        }

        public final void setOutlineBottom(int n) {
            this.outlineBottom = n;
        }

        public final float getOutlineStrength() {
            return this.outlineStrength;
        }

        public final void setOutlineStrength(float f) {
            this.outlineStrength = f;
        }

        public final float getBadgeX() {
            return this.badgeX;
        }

        public final void setBadgeX(float f) {
            this.badgeX = f;
        }

        public final float getBadgeY() {
            return this.badgeY;
        }

        public final void setBadgeY(float f) {
            this.badgeY = f;
        }

        public final float getBadgeWidth() {
            return this.badgeWidth;
        }

        public final void setBadgeWidth(float f) {
            this.badgeWidth = f;
        }

        public final int getBadgeFill() {
            return this.badgeFill;
        }

        public final void setBadgeFill(int n) {
            this.badgeFill = n;
        }

        public final int getBadgeOutline() {
            return this.badgeOutline;
        }

        public final void setBadgeOutline(int n) {
            this.badgeOutline = n;
        }

        public final boolean getHasCheck() {
            return this.hasCheck;
        }

        public final void setHasCheck(boolean bl) {
            this.hasCheck = bl;
        }

        public final float getCheckX() {
            return this.checkX;
        }

        public final void setCheckX(float f) {
            this.checkX = f;
        }

        public final float getCheckY() {
            return this.checkY;
        }

        public final void setCheckY(float f) {
            this.checkY = f;
        }

        public final float getCheckEnableT() {
            return this.checkEnableT;
        }

        public final void setCheckEnableT(float f) {
            this.checkEnableT = f;
        }

        public final boolean getCheckHover() {
            return this.checkHover;
        }

        public final void setCheckHover(boolean bl) {
            this.checkHover = bl;
        }

        public final boolean getAppearing() {
            return this.appearing;
        }

        public final void setAppearing(boolean bl) {
            this.appearing = bl;
        }

        public final float getOriginX() {
            return this.originX;
        }

        public final void setOriginX(float f) {
            this.originX = f;
        }

        public final float getOriginY() {
            return this.originY;
        }

        public final void setOriginY(float f) {
            this.originY = f;
        }

        public final float getScale() {
            return this.scale;
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        public final void reset() {
            this.module = null;
            this.badgeText = null;
            this.hasGear = false;
            this.hasAccent = false;
            this.hasCheck = false;
            this.appearing = false;
            this.scale = 1.0f;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\rH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J+\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\rH\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u001dJ%\u0010 \u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b \u0010!J\u001f\u0010'\u001a\u00020&2\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b'\u0010(J\u001f\u0010)\u001a\u00020&2\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b)\u0010(J\u0017\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b,\u0010-J\u0017\u00101\u001a\u0002002\u0006\u0010/\u001a\u00020.H\u0002\u00a2\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u00020\u001e2\u0006\u00104\u001a\u000203H\u0002\u00a2\u0006\u0004\b5\u00106J7\u0010<\u001a\u00020&2\u0006\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00112\u0006\u0010;\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b<\u0010=J\u001f\u0010@\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b@\u0010AJ'\u0010E\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010D\u001a\u00020CH\u0002\u00a2\u0006\u0004\bE\u0010FJ/\u0010J\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u0017\u0010M\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010Q\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010RR\u0014\u0010T\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u0010PR\u0014\u0010U\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010PR\u0019\u0010W\u001a\u00020\u00118\u0006X\u0087D\u0092\u0002\u0002\bV\u00a2\u0006\u0006\n\u0004\bW\u0010PR\u0014\u0010X\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010PR\u0014\u0010Y\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010PR\u0014\u0010Z\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010PR\u0014\u0010[\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b[\u0010PR\u0014\u0010\\\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\\\u0010PR\u0014\u0010]\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010PR\u0014\u0010^\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b^\u0010PR\u0014\u0010_\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b_\u0010PR\u0014\u0010`\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b`\u0010PR\u0014\u0010a\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010PR\u0014\u0010b\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bb\u0010PR\u0014\u0010c\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bc\u0010PR\u0014\u0010d\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010PR\u0014\u0010e\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\be\u0010PR\u0014\u0010f\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010PR\u0014\u0010g\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010PR\u0014\u0010h\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010PR\u0014\u0010i\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bi\u0010PR\u0014\u0010j\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u0014\u0010l\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010PR\u0014\u0010m\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bm\u0010PR\u0014\u0010n\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bn\u0010PR\u0014\u0010o\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bo\u0010PR\u0014\u0010p\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bp\u0010PR\u0014\u0010q\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bq\u0010PR\u0014\u0010r\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\br\u0010PR\u0014\u0010s\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bs\u0010PR\u0014\u0010t\u001a\u00020\u00118\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010PR\u0014\u0010u\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bu\u0010PR\u0014\u0010v\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bv\u0010RR \u0010x\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u001e0w8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010y\u00a8\u0006z"}, d2={"Lrtx/kimiko/api/ui/module/ModuleListRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/Module;", "module", "", "Lkotlin/jvm/JvmStatic;", "pinnedOrder", "(Lrtx/kimiko/api/modules/Module;)I", "", "isPinned", "(Lrtx/kimiko/api/modules/Module;)Z", "", "modules", "sortPinnedFirst", "(Ljava/util/List;)Ljava/util/List;", "", "cx", "colW", "mod", "settingsIconX", "(FFLrtx/kimiko/api/modules/Module;)F", "modulesSignature", "(Ljava/util/List;)I", "moduleCount", "rowCount", "(I)I", "listW", "(F)F", "", "name", "indexOfModule", "(Ljava/util/List;Ljava/lang/String;)I", "Lnet/minecraft/DrawContext;", "g", "Lrtx/kimiko/api/ui/module/ModuleListRenderer$CardText;", "entry", "", "pushCardPose", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/ui/module/ModuleListRenderer$CardText;)V", "popCardPose", "ms", "Lrtx/kimiko/utils/animations/Decelerate;", "createAnim", "(I)Lrtx/kimiko/utils/animations/Decelerate;", "Lrtx/kimiko/api/modules/Category;", "c", "", "iconChar", "(Lrtx/kimiko/api/modules/Category;)C", "Lrtx/kimiko/utils/key/KeyBind;", "bind", "badgeLabel", "(Lrtx/kimiko/utils/key/KeyBind;)Ljava/lang/String;", "px", "py", "t", "alpha", "hover", "drawModuleCheck", "(FFFFZ)V", "index", "alpha255", "checkGradient", "(IF)I", "speed", "", "palette", "checkPaletteFade", "(II[I)I", "r", "b", "a", "rgba", "(IIIF)I", "v", "clamp255", "(F)I", "FADE_OUT_DURATION", "F", "ROW_FADE_MS", "I", "QUICK_ROW_FADE_MS", "SLIDE_PX", "CONTENT_Y_OFFSET", "Lkotlin/jvm/JvmField;", "HEADER_OFFSET", "CARD_RADIUS", "TOP_BLUR_SCROLL", "TOP_FADE", "CARD_GAP", "CARD_PAD", "PAD_X", "NAME_SIZE", "NAME_TOP", "TITLE_CY", "DESC_SIZE", "DESC_LINE_H", "DESC_TOP", "DESC_RIGHT_PAD", "DESC_BOTTOM_PAD", "NO_DESC_H", "TOGGLE_RIGHT_PAD", "GEAR_GAP", "GEAR_SIZE", "GEAR_GLYPH", "Ljava/lang/String;", "CHECK_SIZE", "CHECK_SCALE", "CHECK_RADIUS", "CHECK_RING1_INSET", "CHECK_RING1_RADIUS", "CHECK_RING2_INSET", "CHECK_RING2_RADIUS", "CHECK_DOT_INSET", "CHECK_RING_THICKNESS", "BADGE_H", "MAX_BLUR_CARDS", "Ljava/util/EnumMap;", "ICON_CACHE", "Ljava/util/EnumMap;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final int pinnedOrder(@NotNull Module module) {
            Intrinsics.checkNotNullParameter((Object)module, (String)"module");
            if (module instanceof ClickGui) {
                return 0;
            }
            if (module instanceof InterfaceModule) {
                return 1;
            }
            return -1;
        }

        @JvmStatic
        public final boolean isPinned(@NotNull Module module) {
            Intrinsics.checkNotNullParameter((Object)module, (String)"module");
            return this.pinnedOrder(module) >= 0;
        }

        @JvmStatic
        @NotNull
        public final List<Module> sortPinnedFirst(@NotNull List<? extends Module> modules) {
            Intrinsics.checkNotNullParameter(modules, (String)"modules");
            boolean anyPinned = false;
            for (Module m : modules) {
                if (!this.isPinned(m)) continue;
                anyPinned = true;
                break;
            }
            if (!anyPinned) {
                return new ArrayList<>(modules);
            }
            ArrayList<Module> pinned = new ArrayList<>(2);
            ArrayList<Module> rest = new ArrayList<>(modules.size());
            for (Module m : modules) {
                if (this.isPinned(m)) {
                    pinned.add(m);
                } else {
                    rest.add(m);
                }
            }
            pinned.sort(Comparator.comparingInt(this::pinnedOrder));
            ArrayList<Module> out = new ArrayList<>(modules.size());
            out.addAll(pinned);
            out.addAll(rest);
            return out;
        }

        @JvmStatic
        public final float settingsIconX(float cx, float colW, @NotNull Module mod) {
            Intrinsics.checkNotNullParameter((Object)mod, (String)"mod");
            float gw = Fonts.KIMIKO.msdfWidth(ModuleListRenderer.GEAR_GLYPH, 7.0f);
            if (this.isPinned(mod)) {
                return cx + colW - 8.0f - 4.25f - gw * 0.5f;
            }
            return cx + colW - 8.5f - 8.0f - 7.0f - gw;
        }

        @JvmStatic
        private final int modulesSignature(List<? extends Module> modules) {
            int sig = modules.size();
            if (!((Collection)modules).isEmpty()) {
                sig = sig * 31 + modules.get(0).getName().hashCode();
                sig = sig * 31 + modules.get(modules.size() - 1).getName().hashCode();
            }
            return sig;
        }

        private final int rowCount(int moduleCount) {
            return (moduleCount + 1) / 2;
        }

        private final float colW(float listW) {
            return (listW - 5.0f - 10.0f) * 0.5f;
        }

        private final int indexOfModule(List<? extends Module> modules, String name) {
            int n = ((Collection)modules).size();
            for (int i = 0; i < n; ++i) {
                if (!Intrinsics.areEqual((Object)modules.get(i).getName(), (Object)name)) continue;
                return i;
            }
            return -1;
        }

        private final void pushCardPose(DrawContext g, CardText entry) {
            if (!entry.getAppearing()) {
                return;
            }
            g.getMatrices().pushMatrix();
            g.getMatrices().translate(entry.getOriginX(), entry.getOriginY());
            g.getMatrices().scale(entry.getScale(), entry.getScale());
            g.getMatrices().translate(-entry.getOriginX(), -entry.getOriginY());
        }

        private final void popCardPose(DrawContext g, CardText entry) {
            if (entry.getAppearing()) {
                g.getMatrices().popMatrix();
            }
        }

        private final Decelerate createAnim(int ms) {
            Animation animation = new Decelerate().setMs(ms).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            Decelerate d = (Decelerate)animation;
            d.setDirection(Direction.BACKWARDS);
            d.counter.setTime(System.currentTimeMillis() - (long)10000);
            return d;
        }

        private final char iconChar(Category c) {
            return switch (WhenMappings.$EnumSwitchMapping$0[c.ordinal()]) {
                case 1 -> 'p';
                case 2 -> 'j';
                case 3 -> 'r';
                case 4 -> 'i';
                case 5 -> 'w';
                case 6 -> 'B';
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        private final String badgeLabel(KeyBind bind) {
            String n = bind.getDisplayName();
            return ((CharSequence)n).length() == 0 ? "?" : n;
        }

        private final void drawModuleCheck(float px, float py, float t, float alpha, boolean hover) {
            float cx = px + 4.25f;
            float cy = py + 4.25f;
            float off = 1.0f - t;
            float hoverT = hover ? 1.0f : 0.0f;
            float chipTop = (13.0f + 7.0f * hoverT) * off * alpha;
            float chipBottom = 3.0f * off * alpha;
            Render2D.rect(px, py, 8.5f, 8.5f, 2.9750001f, this.rgba(255, 255, 255, chipTop), this.rgba(255, 255, 255, chipTop), this.rgba(255, 255, 255, chipBottom), this.rgba(255, 255, 255, chipBottom));
            if (t < 0.996f) {
                float ringAlpha = alpha * off;
                float i1 = 1.2750001f;
                Render2D.outlineClient$default(px + i1, py + i1, 8.5f - i1 * 2.0f, 8.5f - i1 * 2.0f, 2.5500002f, CHECK_RING_THICKNESS, ClientAccent.accentAt((hover ? 70.0f : 28.0f) * ringAlpha, cx, cy), 0.0f, 128, null);
                float i2 = 2.5500002f;
                Render2D.outlineClient$default(px + i2, py + i2, 8.5f - i2 * 2.0f, 8.5f - i2 * 2.0f, 2.125f, CHECK_RING_THICKNESS, ClientAccent.accentAt((hover ? 40.0f : 16.0f) * ringAlpha, cx, cy), 0.0f, 128, null);
            }
            if (t > 0.004f) {
                float fillAlpha = 255.0f * t * alpha;
                Render2D.rect(new BuiltRectangle(px - 0.25f, py - 0.25f, 9.0f, 9.0f, 2.9750001f, 2.9750001f, 2.9750001f, 2.9750001f, this.checkGradient(0, fillAlpha), this.checkGradient(90, fillAlpha), this.checkGradient(180, fillAlpha), this.checkGradient(270, fillAlpha), 0.35f));
                float d = 2.125f;
                float dot = 8.5f - d * 2.0f;
                Render2D.rect(px + d, py + d, dot, dot, dot * 0.5f, this.rgba(255, 255, 255, 255.0f * t * alpha));
            }
            int rimTop = ColorEngine.lerpColor(this.rgba(255, 255, 255, (34.0f + 22.0f * hoverT) * alpha), this.checkGradient(0, 120.0f * alpha), t);
            int rimBottom = ColorEngine.lerpColor(this.rgba(255, 255, 255, (12.0f + 8.0f * hoverT) * alpha), this.checkGradient(180, 70.0f * alpha), t);
            Render2D.outline(px, py, 8.5f, 8.5f, 2.9750001f, 0.6f, rimTop, rimTop, rimBottom, rimBottom);
        }

        private final int checkGradient(int index, float alpha255) {
            int alpha = this.clamp255(alpha255);
            if (alpha <= 0) {
                return 0;
            }
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.withAlpha(this.checkPaletteFade(8, index, palette), alpha);
            }
            int angle = (int)((System.currentTimeMillis() / 8L + (long)index) % 360L);
            float t = angle >= 180 ? (float)(360 - angle) / 180.0f : (float)angle / 180.0f;
            return ClientAccent.gradientColor(t, alpha);
        }

        private final int checkPaletteFade(int speed, int index, int[] palette) {
            int n = palette.length;
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            float f = (float)angle / 360.0f * (float)n;
            int i = (int)f % n;
            int j = (i + 1) % n;
            int a = palette[i] | 0xFF000000;
            int b = palette[j] | 0xFF000000;
            return ColorEngine.lerpColor(a, b, f - (float)Math.floor(f)) | 0xFF000000;
        }

        private final int rgba(int r, int g, int b, float a) {
            int alpha = this.clamp255(a);
            if (alpha <= 0) {
                return 0;
            }
            return new Color(r, g, b, alpha).getRGB();
        }

        private final int clamp255(float v) {
            return RangesKt.coerceIn((int)((int)v), (int)0, (int)255);
        }

        private static final int sortPinnedFirst$lambda$0(Function1 $tmp0, Object p0) {
            return ((Number)$tmp0.invoke(p0)).intValue();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[Category.values().length];
                try {
                    nArray[Category.VISUALS.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.DISPLAY.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.UTILS.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.EVENTS.ordinal()] = 4;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.CONFIGS.ordinal()] = 5;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.THEMES.ordinal()] = 6;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }
}

