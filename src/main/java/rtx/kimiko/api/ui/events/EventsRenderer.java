/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.events;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.funtime.FunTimeEvent;
import rtx.kimiko.api.events.funtime.FunTimeEventsClient;
import rtx.kimiko.api.events.funtime.FunTimeEventsSnapshot;
import rtx.kimiko.api.events.funtime.FunTimeMine;
import rtx.kimiko.api.events.funtime.FunTimeMinesSnapshot;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.CardAppear;
import rtx.kimiko.api.ui.ScrollBar;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.events.EventsRenderer;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Animation;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 \u009d\u00012\u00020\u0001:\u000e\u009e\u0001\u009f\u0001\u00a0\u0001\u00a1\u0001\u00a2\u0001\u00a3\u0001\u009d\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\u0003J\r\u0010\n\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u0003J\u0015\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\b\u00a2\u0006\u0004\b\u000e\u0010\u0003J\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0006J\r\u0010\u0019\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0019\u0010\u0017J\r\u0010\u001a\u001a\u00020\b\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u0017\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ/\u0010#\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b#\u0010$J/\u0010#\u001a\u00020\b2\u0006\u0010 \u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b#\u0010&J=\u0010.\u001a\u00020\b2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015\u00a2\u0006\u0004\b.\u0010/J?\u00100\u001a\u00020\b2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b0\u0010/J\r\u00101\u001a\u00020\b\u00a2\u0006\u0004\b1\u0010\u0003J\r\u00102\u001a\u00020\b\u00a2\u0006\u0004\b2\u0010\u0003J\r\u00103\u001a\u00020\u0004\u00a2\u0006\u0004\b3\u0010\u0006J\u0017\u00104\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b4\u00105J/\u0010:\u001a\u00020\b2\u0006\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b:\u0010;J'\u0010?\u001a\u00020\u00152\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b?\u0010@J\u0017\u0010A\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bA\u0010BJ7\u0010D\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bD\u0010EJO\u0010G\u001a\u00020\b2\u0006\u0010(\u001a\u00020'2\u0006\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00152\u0006\u0010F\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bG\u0010HJO\u0010I\u001a\u00020\b2\u0006\u0010(\u001a\u00020'2\u0006\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00152\u0006\u0010F\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bI\u0010HJ?\u0010J\u001a\u00020\u00152\u0006\u0010(\u001a\u00020'2\u0006\u00106\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bJ\u0010KJI\u0010O\u001a\u00020\b2\u0006\u0010M\u001a\u00020L2\b\u0010N\u001a\u0004\u0018\u00010%2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bO\u0010PJ'\u0010R\u001a\u00020\u00152\u0006\u0010=\u001a\u00020<2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010Q\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bR\u0010SJ\u0017\u0010V\u001a\u00020\b2\u0006\u0010U\u001a\u00020TH\u0002\u00a2\u0006\u0004\bV\u0010WJ\u0017\u0010Y\u001a\u00020\b2\u0006\u0010U\u001a\u00020XH\u0002\u00a2\u0006\u0004\bY\u0010ZJ\u001d\u0010]\u001a\u00020\u00042\u0006\u0010[\u001a\u00020\u00152\u0006\u0010\\\u001a\u00020\u0015\u00a2\u0006\u0004\b]\u0010^J\u0017\u0010a\u001a\u00020\b2\u0006\u0010`\u001a\u00020_H\u0002\u00a2\u0006\u0004\ba\u0010bJ\u001d\u0010f\u001a\u00020\b2\u0006\u0010d\u001a\u00020c2\u0006\u0010e\u001a\u00020\u0015\u00a2\u0006\u0004\bf\u0010gJQ\u0010j\u001a\u00020\u00152\u0006\u0010(\u001a\u00020'2\b\u0010h\u001a\u0004\u0018\u00010<2\u0006\u0010i\u001a\u00020<2\u0006\u00106\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bj\u0010kJI\u0010n\u001a\u00020\b2\u0006\u0010m\u001a\u00020l2\b\u0010N\u001a\u0004\u0018\u00010\u001f2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bn\u0010oJ7\u0010p\u001a\u00020\b2\u0006\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bp\u0010EJ\u001d\u0010s\u001a\u00020\u00042\u0006\u0010q\u001a\u00020\u00152\u0006\u0010r\u001a\u00020\u0015\u00a2\u0006\u0004\bs\u0010^J\r\u0010t\u001a\u00020\b\u00a2\u0006\u0004\bt\u0010\u0003J\r\u0010u\u001a\u00020\u0004\u00a2\u0006\u0004\bu\u0010\u0006R\u001a\u0010x\u001a\b\u0012\u0004\u0012\u00020w0v8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010yR \u0010{\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u001b0z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010|R \u0010}\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u001f0z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b}\u0010|R \u0010~\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00150z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b~\u0010|R\u0017\u0010\u0080\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0018\u0010\u0083\u0001\u001a\u00030\u0082\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0017\u0010f\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bf\u0010\u0085\u0001R\u0019\u0010\u0086\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0085\u0001R\u0019\u0010\u0087\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0085\u0001R\u0019\u0010\u0088\u0001\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0019\u0010\u008a\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0019\u0010\u008c\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u0085\u0001R\u0019\u0010\u008d\u0001\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u0089\u0001R\u0019\u0010\u008e\u0001\u001a\u00020_8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0019\u0010\u0090\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0085\u0001R\u0019\u0010\u0091\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u0085\u0001R\u0017\u0010[\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b[\u0010\u0085\u0001R\u0017\u0010\\\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\\\u0010\u0085\u0001R\u0019\u0010\u0092\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u008b\u0001R\u0017\u0010\u0093\u0001\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0017\u0010\u0095\u0001\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0094\u0001R\u001d\u0010\u0097\u0001\u001a\t\u0012\u0005\u0012\u00030\u0096\u00010v8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010yR\"\u0010\u0098\u0001\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020%0z8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0098\u0001\u0010|R\u001b\u0010\u0099\u0001\u001a\u0004\u0018\u00010_8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u008f\u0001R\u0019\u0010\u009a\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0085\u0001R\u0019\u0010\u009b\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0085\u0001R\u0019\u0010\u009c\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0085\u0001\u00a8\u0006\u00a4\u0001"}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer;", "", "<init>", "()V", "", "isTransitioning", "()Z", "isFadeOutDone", "", "beginFadeOut", "finishTransition", "value", "setAppearComposite", "(Z)V", "resetCardBlur", "", "cardBlurRects", "()[F", "", "cardBlurCount", "()I", "", "cardBlurMaxPhase", "()F", "hasAppearWork", "currentScroll", "open", "", "now", "withinInitialWindow", "(J)Z", "Lrtx/kimiko/api/ui/events/EventsRenderer$AnimEntry;", "entry", "instant", "order", "seedAppear", "(Lrtx/kimiko/api/ui/events/EventsRenderer$AnimEntry;JZI)V", "Lrtx/kimiko/api/ui/events/EventsRenderer$MineAnim;", "(Lrtx/kimiko/api/ui/events/EventsRenderer$MineAnim;JZI)V", "Lnet/minecraft/DrawContext;", "graphics", "x", "y", "w", "alpha", "dt", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "renderContent", "showEvents", "showMines", "isMines", "updateTabTransition", "(F)V", "areaX", "areaY", "areaW", "areaH", "updateListState", "(FFFF)V", "", "key", "hovered", "hoverProgress", "(Ljava/lang/String;ZF)F", "cardBottomRadius", "(F)F", "hoverT", "drawCardBase", "(FFFFF)V", "baseX", "renderEvents", "(Lnet/minecraft/DrawContext;FFFFFFF)V", "renderMines", "renderMinesList", "(Lnet/minecraft/DrawContext;FFFFF)F", "Lrtx/kimiko/api/events/funtime/FunTimeMine;", "mine", "anim", "renderMineRow", "(Lrtx/kimiko/api/events/funtime/FunTimeMine;Lrtx/kimiko/api/ui/events/EventsRenderer$MineAnim;FFFFF)V", "settled", "detailEase", "(Ljava/lang/String;JZ)F", "Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;", "snapshot", "updateMineAnimStates", "(Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;)V", "Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;", "updateAnimStates", "(Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;)V", "mouseX", "mouseY", "click", "(FF)Z", "Lrtx/kimiko/api/ui/events/EventsRenderer$Tab;", "next", "setTab", "(Lrtx/kimiko/api/ui/events/EventsRenderer$Tab;)V", "", "amount", "viewH", "scroll", "(DF)V", "title", "sectionKey", "renderSection", "(Lnet/minecraft/DrawContext;Ljava/lang/String;Ljava/lang/String;FFFFF)F", "Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "event", "renderEvent", "(Lrtx/kimiko/api/events/funtime/FunTimeEvent;Lrtx/kimiko/api/ui/events/EventsRenderer$AnimEntry;FFFFF)V", "renderScrollBar", "mx", "my", "scrollbarGrab", "scrollbarRelease", "scrollbarDragging", "", "Lrtx/kimiko/api/ui/events/EventsRenderer$EventButton;", "buttons", "Ljava/util/List;", "", "detailAppearMs", "Ljava/util/Map;", "animEntries", "hoverAnims", "Lrtx/kimiko/api/ui/CardAppear;", "appear", "Lrtx/kimiko/api/ui/CardAppear;", "Lrtx/kimiko/api/ui/ScrollBar;", "scrollBar", "Lrtx/kimiko/api/ui/ScrollBar;", "F", "scrollTarget", "contentH", "startMs", "J", "transitioning", "Z", "fadeOutTime", "openMs", "tab", "Lrtx/kimiko/api/ui/events/EventsRenderer$Tab;", "listTop", "listBottom", "mouseInList", "eventsTabRect", "[F", "minesTabRect", "Lrtx/kimiko/api/ui/events/EventsRenderer$MineRow;", "mineRows", "mineAnims", "pendingTab", "tabFade", "eventsTabT", "minesTabT", "Companion", "EventStyle", "EventButton", "MineRow", "Tab", "AnimEntry", "MineAnim", "rtx.kimiko:kimiko"})
public final class EventsRenderer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<EventButton> buttons = new ArrayList();
    @NotNull
    private final Map<String, Long> detailAppearMs = new HashMap();
    @NotNull
    private final Map<String, AnimEntry> animEntries = new LinkedHashMap();
    @NotNull
    private final Map<String, Float> hoverAnims = new HashMap();
    @NotNull
    private final CardAppear appear = new CardAppear();
    @NotNull
    private final ScrollBar scrollBar = new ScrollBar();
    private float scroll;
    private float scrollTarget;
    private float contentH;
    private long startMs = System.currentTimeMillis();
    private boolean transitioning;
    private float fadeOutTime;
    private long openMs = System.currentTimeMillis();
    @NotNull
    private Tab tab = Tab.EVENTS;
    private float listTop;
    private float listBottom;
    private float mouseX;
    private float mouseY;
    private boolean mouseInList;
    @NotNull
    private final float[] eventsTabRect = new float[4];
    @NotNull
    private final float[] minesTabRect = new float[4];
    @NotNull
    private final List<MineRow> mineRows = new ArrayList();
    @NotNull
    private final Map<String, MineAnim> mineAnims = new LinkedHashMap();
    @Nullable
    private Tab pendingTab;
    private float tabFade = 1.0f;
    private float eventsTabT = 1.0f;
    private float minesTabT;
    private static final float ROW_H = 31.0f;
    private static final float GAP = 5.0f;
    private static final float SIDE_PAD = 5.0f;
    private static final float CARD_RADIUS = 6.0f;
    private static final float PANEL_RADIUS = 12.0f;
    private static final float CORNER_BLEND = 16.0f;
    private static final float CONTENT_Y_OFFSET = 5.0f;
    private static final float CONTENT_HEIGHT = 280.0f;
    private static final float EVENT_ICON_SIZE = 14.5f;
    @NotNull
    private static final String ICON_AIR_DROP = "a";
    @NotNull
    private static final String ICON_ALTAR = "b";
    @NotNull
    private static final String ICON_CLOVER = "c";
    @NotNull
    private static final String ICON_COMING_SOON = "d";
    @NotNull
    private static final String ICON_FLAME = "e";
    @NotNull
    private static final String ICON_LIGHTHOUSE = "f";
    @NotNull
    private static final String ICON_METEOR = "g";
    @NotNull
    private static final String ICON_MOUNTAIN = "h";
    @NotNull
    private static final String ICON_PICKAXE = "i";
    @NotNull
    private static final String ICON_TREASURE = "j";
    private static final float DETAIL_ANIM_MS = 400.0f;
    private static final float FADE_OUT_DURATION = 0.15f;
    private static final int ANIM_MS = 280;
    private static final long APPEAR_STAGGER_MS = 50L;
    private static final long SHIMMER_CYCLE_MS = 4400L;
    private static final long INITIAL_WINDOW_MS = 250L;
    private static final long LINGER_MS = 10000L;
    private static final float REORDER_RATE = 8.5f;
    private static final float TAB_BAR_H = 18.0f;
    private static final float BOTTOM_PAD = 18.0f;
    private static final float TAB_FADE_RATE = 16.0f;
    private static final float TAB_BTN_RATE = 14.0f;

    public final boolean isTransitioning() {
        return this.transitioning;
    }

    public final boolean isFadeOutDone() {
        return this.transitioning && this.fadeOutTime <= 0.0f;
    }

    public final void beginFadeOut() {
        this.fadeOutTime = 0.15f;
        this.transitioning = true;
    }

    public final void finishTransition() {
        this.transitioning = false;
        this.fadeOutTime = 0.0f;
    }

    public final void setAppearComposite(boolean value) {
        this.appear.setComposite(value);
    }

    public final void resetCardBlur() {
        this.appear.resetRects();
    }

    @NotNull
    public final float[] cardBlurRects() {
        return this.appear.rects();
    }

    public final int cardBlurCount() {
        return this.appear.count();
    }

    public final float cardBlurMaxPhase() {
        return this.appear.maxPhase();
    }

    private final boolean hasAppearWork() {
        float p;
        if (this.appear.hasWork()) {
            return true;
        }
        for (AnimEntry animEntry : this.animEntries.values()) {
            p = animEntry.progress();
            if (!(p > 0.001f) || !(p < 0.999f)) continue;
            return true;
        }
        for (MineAnim mineAnim : this.mineAnims.values()) {
            p = mineAnim.progress();
            if (!(p > 0.001f) || !(p < 0.999f)) continue;
            return true;
        }
        return false;
    }

    public final float currentScroll() {
        return this.scroll;
    }

    public final void open() {
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
        this.openMs = this.startMs = System.currentTimeMillis();
        this.animEntries.clear();
        this.mineAnims.clear();
        this.hoverAnims.clear();
        this.appear.restart(false);
    }

    private final boolean withinInitialWindow(long now) {
        return now - this.openMs < 250L;
    }

    private final void seedAppear(AnimEntry entry, long now, boolean instant, int order) {
        if (instant) {
            entry.getAnim().counter.setTime(now - (long)280 - 1L);
        } else {
            entry.getAnim().counter.setTime(now + (long)order * 50L);
        }
    }

    private final void seedAppear(MineAnim entry, long now, boolean instant, int order) {
        if (instant) {
            entry.getAnim().counter.setTime(now - (long)280 - 1L);
        } else {
            long capped = (long)Math.min(order, 12) * 50L;
            entry.getAnim().counter.setTime(now + capped);
        }
    }

    public final void render(@NotNull DrawContext graphics, float x, float y, float w, float alpha, float dt) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        float factor = 1.0f - (float)Math.exp(-dt * 14.0f);
        this.scroll += (this.scrollTarget - this.scroll) * factor;
        if (Math.abs(this.scrollTarget - this.scroll) < 0.05f) {
            this.scroll = this.scrollTarget;
        }
        if (this.transitioning) {
            if (this.fadeOutTime > 0.0f) {
                this.fadeOutTime -= dt;
                float fadeAlpha = alpha * Math.max(0.0f, this.fadeOutTime / 0.15f);
                this.renderContent(graphics, x, y, w, fadeAlpha, dt);
            }
            return;
        }
        this.renderContent(graphics, x, y, w, alpha, dt);
    }

    private final void renderContent(DrawContext graphics, float x, float y, float w, float alpha, float dt) {
        float areaX = x + 117.0f;
        float areaY = y + 5.0f;
        float areaW = w - 122.0f;
        float areaH = CONTENT_HEIGHT;
        this.updateTabTransition(dt);
        float listY = areaY;
        float listH = areaH;
        if (this.appear.composite() && this.hasAppearWork()) {
            Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
            GuiRenderState rs = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            rs.createNewRootLayer();
            rs.applyBlur();
            UI.Companion.markCardStratum();
        }
        float listAlpha = alpha * EventsRenderer.Companion.smooth01(this.tabFade);
        if (this.tab == Tab.MINES) {
            this.renderMines(graphics, areaX, listY, areaW, listH, listAlpha, dt, x);
        } else {
            this.renderEvents(graphics, areaX, listY, areaW, listH, listAlpha, dt, x);
        }
        this.appear.frameDone();
    }

    public final void showEvents() {
        this.setTab(Tab.EVENTS);
    }

    public final void showMines() {
        this.setTab(Tab.MINES);
    }

    public final boolean isMines() {
        Tab tab = this.pendingTab;
        if (tab == null) {
            tab = this.tab;
        }
        return tab == Tab.MINES;
    }

    private final void updateTabTransition(float dt) {
        Tab tab;
        float fadeTarget = this.pendingTab != null ? 0.0f : 1.0f;
        this.tabFade += (fadeTarget - this.tabFade) * (1.0f - (float)Math.exp(-dt * 16.0f));
        if (this.pendingTab != null && this.tabFade <= 0.04f) {
            Tab tab2 = this.pendingTab;
            Intrinsics.checkNotNull((Object)((Object)tab2));
            this.tab = tab2;
            this.pendingTab = null;
            this.scroll = 0.0f;
            this.scrollTarget = 0.0f;
            for (AnimEntry animEntry : this.animEntries.values()) {
                animEntry.setRenderYInit(false);
            }
            for (MineAnim mineAnim : this.mineAnims.values()) {
                mineAnim.setRenderYInit(false);
            }
            this.hoverAnims.clear();
            this.appear.restart(true);
            this.tabFade = 0.0f;
        }
        if ((tab = this.pendingTab) == null) {
            tab = this.tab;
        }
        Tab shown = tab;
        float f = 1.0f - (float)Math.exp(-dt * 14.0f);
        this.eventsTabT += ((shown == Tab.EVENTS ? 1.0f : 0.0f) - this.eventsTabT) * f;
        this.minesTabT += ((shown == Tab.MINES ? 1.0f : 0.0f) - this.minesTabT) * f;
    }

    private final void updateListState(float areaX, float areaY, float areaW, float areaH) {
        this.listTop = areaY;
        this.listBottom = areaY + areaH;
        this.mouseX = Position.Companion.mouseX();
        this.mouseY = Position.Companion.mouseY();
        this.mouseInList = UI.Companion.isOpen() && this.mouseX >= areaX && this.mouseX <= areaX + areaW && this.mouseY >= areaY && this.mouseY <= areaY + areaH;
    }

    private final float hoverProgress(String key, boolean hovered, float dt) {
        float rate = UI.Companion.isOpen() ? 1.0f - (float)Math.exp(-dt * 16.0f) : 0.0f;
        float t = ((Number)this.hoverAnims.getOrDefault(key, Float.valueOf(0.0f))).floatValue();
        t += ((hovered ? 1.0f : 0.0f) - t) * rate;
        this.hoverAnims.put(key, Float.valueOf(t));
        return t;
    }

    private final float cardBottomRadius(float y) {
        float bottom = y + 31.0f;
        if (bottom <= this.listBottom - 16.0f) {
            return 6.0f;
        }
        float t = RangesKt.coerceIn((float)((bottom - (this.listBottom - 16.0f)) / 16.0f), (float)0.0f, (float)1.0f);
        return 6.0f + 6.0f * t;
    }

    private final void drawCardBase(float x, float y, float w, float alpha, float hoverT) {
        float rbr = this.cardBottomRadius(y);
        RectUtil.drawGlassCard(x, y, w, 31.0f, 6.0f, 6.0f, rbr, 6.0f, alpha, hoverT);
        int fillTop = EventsRenderer.Companion.rgba(255, 255, 255, (7.0f + 5.0f * hoverT) * alpha);
        int fillBottom = EventsRenderer.Companion.rgba(255, 255, 255, 1.5f * alpha);
        Render2D.rect(x, y, w, 31.0f, 6.0f, 6.0f, rbr, 6.0f, fillTop, fillTop, fillBottom, fillBottom);
        int outTop = EventsRenderer.Companion.rgba(255, 255, 255, (30.0f + 22.0f * hoverT) * alpha);
        int outBottom = EventsRenderer.Companion.rgba(255, 255, 255, (10.0f + 8.0f * hoverT) * alpha);
        Render2D.outline(x, y, w, 31.0f, 6.0f, 6.0f, rbr, 6.0f, 0.6f, outTop, outTop, outBottom, outBottom);
    }

    private final void renderEvents(DrawContext graphics, float areaX, float areaY, float areaW, float areaH, float alpha, float dt, float baseX) {
        FunTimeEventsSnapshot snapshot = FunTimeEventsClient.INSTANCE.snapshot();
        this.buttons.clear();
        this.updateAnimStates(snapshot);
        this.updateListState(areaX, areaY, areaW, areaH);
        Render2D.pushScissor(graphics, areaX, areaY, areaW, areaH);
        RoundedScissor.push(graphics, areaX, areaY, areaW, areaH, 0.0f, 12.0f, 12.0f, 0.0f);
        float cursorY = areaY + 5.0f - this.scroll;
        cursorY = this.renderSection(graphics, "", "current", areaX, cursorY, areaW, alpha, dt);
        if (snapshot.current().isEmpty() && this.animEntries.isEmpty()) {
            String message = snapshot.online() ? I18n.tr("Событий пока нет") : EventsRenderer.Companion.offlineMessage(snapshot.error());
            float textW = Fonts.MEDIUM.width(message, 6.5f);
            Fonts.MEDIUM.draw(message, areaX + (areaW - textW) * 0.5f, areaY + areaH * 0.5f, 6.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)115 * alpha));
        }
        this.contentH = Math.max(0.0f, cursorY - (areaY + 5.0f - this.scroll));
        float maxScroll = Math.max(0.0f, this.contentH - areaH + 18.0f);
        this.scrollTarget = RangesKt.coerceIn((float)this.scrollTarget, (float)0.0f, (float)maxScroll);
        this.scroll = RangesKt.coerceIn((float)this.scroll, (float)0.0f, (float)maxScroll);
        RoundedScissor.pop();
        Render2D.popScissor(graphics);
        this.renderScrollBar(areaX, areaY, areaW, areaH, alpha);
    }

    private final void renderMines(DrawContext graphics, float areaX, float areaY, float areaW, float areaH, float alpha, float dt, float baseX) {
        FunTimeMinesSnapshot snapshot = FunTimeEventsClient.INSTANCE.minesSnapshot();
        this.mineRows.clear();
        this.updateMineAnimStates(snapshot);
        this.updateListState(areaX, areaY, areaW, areaH);
        Render2D.pushScissor(graphics, areaX, areaY, areaW, areaH);
        RoundedScissor.push(graphics, areaX, areaY, areaW, areaH, 0.0f, 12.0f, 12.0f, 0.0f);
        float startY = areaY + 5.0f - this.scroll;
        float cursorY = this.renderMinesList(graphics, areaX, startY, areaW, alpha, dt);
        if (snapshot.mines().isEmpty() && this.mineAnims.isEmpty()) {
            String message = snapshot.online() ? I18n.tr("Редких шахт сейчас нет") : EventsRenderer.Companion.offlineMessage(snapshot.error());
            float textW = Fonts.MEDIUM.width(message, 6.5f);
            Fonts.MEDIUM.draw(message, areaX + (areaW - textW) * 0.5f, areaY + areaH * 0.5f, 6.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)115 * alpha));
        }
        this.contentH = Math.max(0.0f, cursorY - startY);
        float maxScroll = Math.max(0.0f, this.contentH - areaH + 18.0f);
        this.scrollTarget = RangesKt.coerceIn((float)this.scrollTarget, (float)0.0f, (float)maxScroll);
        this.scroll = RangesKt.coerceIn((float)this.scroll, (float)0.0f, (float)maxScroll);
        RoundedScissor.pop();
        Render2D.popScissor(graphics);
        this.renderScrollBar(areaX, areaY, areaW, areaH, alpha);
    }

    private final float renderMinesList(DrawContext graphics, float areaX, float y, float areaW, float alpha, float dt) {
        ArrayList<MineAnim> rows = new ArrayList<MineAnim>();
        for (MineAnim entry : this.mineAnims.values()) {
            if (entry.progress() <= 0.001f) continue;
            rows.add(entry);
        }
        Comparator<MineAnim> comparator = Comparator
                .comparingInt((MineAnim e) -> Companion.anarchyOf(e.getMine()))
                .thenComparing(e -> e.getMine().mineName());
        rows.sort(comparator);
        float reorderFactor = 1.0f - (float)Math.exp(-dt * 8.5f);
        float targetY = y;
        int order = 0;
        Iterator iterator = rows.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            boolean growing;
            Object e = iterator2.next();
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            MineAnim entry = (MineAnim)e;
            float p = entry.progress();
            float slotH = 31.0f * p;
            float gap = 5.0f * p;
            boolean bl = growing = p < 0.999f;
            if (!entry.getRenderYInit()) {
                entry.setRenderY(targetY);
                entry.setRenderYInit(true);
            } else {
                entry.setRenderY(entry.getRenderY() + (targetY - entry.getRenderY()) * reorderFactor);
                if (Math.abs(targetY - entry.getRenderY()) < 0.05f) {
                    entry.setRenderY(targetY);
                }
            }
            float cardX = areaX + 5.0f;
            float cardW = areaW - 10.0f;
            float progress = this.appear.progress(order++);
            if (progress < 0.001f) {
                targetY += slotH + gap;
                continue;
            }
            float settle = CardAppear.Companion.settle(progress);
            float presence = settle * settle;
            boolean appearing = progress < 0.999f;
            boolean compositeCard = this.appear.composite() && appearing;
            float rowY = entry.getRenderY() + (1.0f - settle) * 6.0f;
            float rowAlpha = alpha * p * (!appearing || compositeCard ? 1.0f : presence);
            if (rowY + slotH < this.listTop - 6.0f || rowY > this.listBottom + 6.0f) {
                targetY += slotH + gap;
                continue;
            }
            if (this.appear.composite()) {
                if (appearing) {
                    this.appear.push(cardX, rowY, cardW, slotH, presence, CardAppear.Companion.blurPhase(progress));
                } else if (growing) {
                    this.appear.push(cardX, rowY, cardW, slotH, p, CardAppear.Companion.blurPhase(p));
                }
            }
            if (growing) {
                Render2D.pushScissor(graphics, areaX, rowY, areaW, slotH);
            }
            if (appearing) {
                float scale = 0.85f + 0.15f * settle;
                float ox = cardX + cardW * 0.5f;
                float oy = rowY + 15.5f;
                graphics.getMatrices().pushMatrix();
                graphics.getMatrices().translate(ox, oy);
                graphics.getMatrices().scale(scale, scale);
                graphics.getMatrices().translate(-ox, -oy);
            }
            boolean hovered = this.mouseInList && !entry.getLeaving() && this.mouseX >= cardX && this.mouseX <= cardX + cardW && this.mouseY >= rowY && this.mouseY <= rowY + slotH;
            float hoverT = this.hoverProgress(EventsRenderer.Companion.mineKey(entry.getMine()), hovered, dt);
            this.renderMineRow(entry.getMine(), entry, cardX, rowY, cardW, rowAlpha, hoverT);
            if (appearing) {
                graphics.getMatrices().popMatrix();
            }
            if (growing) {
                Render2D.popScissor(graphics);
            }
            targetY += slotH + gap;
        }
        return targetY;
    }

    private final void renderMineRow(FunTimeMine mine, MineAnim anim, float x, float y, float w, float alpha, float hoverT) {
        String string;
        long l;
        boolean departed;
        int[] rc = Companion.rarityColor(mine.rarity());
        this.drawCardBase(x, y, w, alpha, hoverT);
        float buttonX = x + (float)4;
        float buttonY = y + (float)5;
        float buttonW = 31.0f;
        float buttonH = 21.0f;
        int[] dark = Companion.darker(rc);
        int cTL = EventsRenderer.Companion.rgba(rc[0], rc[1], rc[2], (float)175 * alpha);
        int cBR = EventsRenderer.Companion.rgba(dark[0], dark[1], dark[2], (float)175 * alpha);
        int cMid = EventsRenderer.Companion.mixColor(cTL, cBR, 0.5f);
        Render2D.rect(buttonX, buttonY, buttonW, buttonH, 4.0f, cTL, cMid, cBR, cMid);
        float[] iconBounds = Fonts.EVENT_ICONS.msdfBounds(ICON_PICKAXE, 14.5f);
        Fonts.EVENT_ICONS.msdf(ICON_PICKAXE, buttonX + buttonW * 0.5f - (iconBounds[0] + iconBounds[2]) * 0.5f, buttonY + buttonH * 0.5f - (iconBounds[1] + iconBounds[3]) * 0.5f, 14.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)235 * alpha));
        float textX = buttonX + buttonW + (float)7;
        int anarchy = Companion.anarchyOf(mine);
        String anTag = "/an" + anarchy;
        float anTagW = Fonts.SEMIBOLD.width(anTag, 5.0f);
        Fonts.SEMIBOLD.draw(anTag, x + w - anTagW - (float)6, y + (float)6, 5.0f, ClientAccent.accentBrightAt((float)200 * alpha, x + w - anTagW - (float)6, y + (float)6));
        boolean bl = departed = anim != null && (anim.getLeaving() || anim.getDepartedAtMs() != 0L);
        if (departed) {
            MineAnim mineAnim = anim;
            Intrinsics.checkNotNull((Object)mineAnim);
            l = mineAnim.effectiveRemaining();
        } else {
            l = FunTimeEventsClient.INSTANCE.remainingSeconds(mine);
        }
        long remaining = l;
        String timer = remaining > 0L ? Companion.formatTime((int)remaining) : I18n.tr("Обновляется");
        float timerW = Fonts.MEDIUM.width(timer, 5.0f);
        Fonts.MEDIUM.draw(timer, x + w - timerW - (float)6, y + (float)17, 5.0f, EventsRenderer.Companion.rgba(255, 255, 255, (float)120 * alpha));
        Object detail = I18n.tr(mine.rarity());
        if (!StringsKt.isBlank((CharSequence)mine.nextRarity()) && !StringsKt.equals((String)mine.nextRarity(), (String)mine.rarity(), (boolean)true)) {
            detail = (String)detail + "  \u2192  " + I18n.tr(mine.nextRarity());
        }
        if (StringsKt.isBlank((CharSequence)mine.serverRuName())) {
            Object[] objectArray = new Object[]{anarchy};
            string = I18n.tr("Анархия-%d", objectArray);
        } else {
            string = I18n.tr(mine.serverRuName());
        }
        String serverName = string;
        Fonts.MEDIUM.draw(serverName, textX, y + (float)5, 6.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)225 * alpha));
        Fonts.MEDIUM.draw((String)detail, textX, y + (float)17, 5.0f, EventsRenderer.Companion.rgba(rc[0], rc[1], rc[2], (float)200 * alpha));
        if (anim == null || !anim.getLeaving()) {
            this.mineRows.add(new MineRow(x, y, w, 31.0f, anarchy));
        }
    }

    private final float detailEase(String key, long now, boolean settled) {
        if (settled) {
            this.detailAppearMs.put(key, now - 400L - 1L);
            return 1.0f;
        }
        this.detailAppearMs.putIfAbsent(key, now);
        Long l = this.detailAppearMs.get(key);
        Intrinsics.checkNotNull((Object)l);
        float t = RangesKt.coerceIn((float)((float)(now - ((Number)l).longValue()) / 400.0f), (float)0.0f, (float)1.0f);
        return t * t * (3.0f - 2.0f * t);
    }

    private final void updateMineAnimStates(FunTimeMinesSnapshot snapshot) {
        MineAnim entry;
        HashSet<String> seen = new HashSet<String>();
        long now = System.currentTimeMillis();
        boolean firstBatch = this.mineAnims.isEmpty();
        boolean instant = firstBatch || this.withinInitialWindow(now);
        int newIndex = 0;
        for (FunTimeMine entry2 : snapshot.mines()) {
            String k = EventsRenderer.Companion.mineKey(entry2);
            seen.add(k);
            entry = this.mineAnims.get(k);
            if (entry == null) {
                entry = new MineAnim(entry2);
                this.seedAppear(entry, now, instant, newIndex++);
                this.mineAnims.put(k, entry);
                continue;
            }
            entry.markPresent(entry2);
        }
        if (firstBatch && !this.mineAnims.isEmpty() && !this.withinInitialWindow(now)) {
            this.appear.restart(true);
        }
        for (Map.Entry entry2 : this.mineAnims.entrySet()) {
            String key = (String)entry2.getKey();
            entry = (MineAnim)entry2.getValue();
            if (seen.contains(key) || entry.getLeaving()) continue;
            if (entry.getDepartedAtMs() == 0L) {
                entry.setDepartedAtMs(now);
                continue;
            }
            if (now - entry.getDepartedAtMs() < 10000L) continue;
            entry.markLeaving();
        }
        this.mineAnims.values().removeIf(MineAnim::isGone);
    }

    private final void updateAnimStates(FunTimeEventsSnapshot snapshot) {
        AnimEntry entry;
        String k;
        HashSet<String> seen = new HashSet<String>();
        long now = System.currentTimeMillis();
        boolean firstBatch = this.animEntries.isEmpty();
        boolean instant = firstBatch || this.withinInitialWindow(now);
        int newIndex = 0;
        for (FunTimeEvent entry2 : snapshot.current()) {
            k = EventsRenderer.Companion.key(entry2);
            seen.add(k);
            entry = this.animEntries.get(k);
            if (entry == null) {
                entry = new AnimEntry(entry2, "current");
                this.seedAppear(entry, now, instant, newIndex++);
                this.animEntries.put(k, entry);
                continue;
            }
            entry.markPresent(entry2, "current");
        }
        for (FunTimeEvent funTimeEvent : snapshot.nearest()) {
            k = EventsRenderer.Companion.key(funTimeEvent);
            seen.add(k);
            entry = this.animEntries.get(k);
            if (entry == null) {
                entry = new AnimEntry(funTimeEvent, "nearest");
                this.seedAppear(entry, now, instant, newIndex++);
                this.animEntries.put(k, entry);
                continue;
            }
            entry.markPresent(funTimeEvent, "nearest");
        }
        if (firstBatch && !this.animEntries.isEmpty() && !this.withinInitialWindow(now)) {
            this.appear.restart(true);
        }
        for (Map.Entry entry2 : this.animEntries.entrySet()) {
            String key = (String)entry2.getKey();
            entry = (AnimEntry)entry2.getValue();
            if (seen.contains(key) || entry.getLeaving()) continue;
            if (entry.getDepartedAtMs() == 0L) {
                entry.setDepartedAtMs(now);
                continue;
            }
            if (now - entry.getDepartedAtMs() < 10000L) continue;
            entry.markLeaving();
        }
        this.animEntries.values().removeIf(AnimEntry::isGone);
    }

    public final boolean click(float mouseX, float mouseY) {
        if (EventsRenderer.Companion.hit(this.eventsTabRect, mouseX, mouseY)) {
            this.setTab(Tab.EVENTS);
            return true;
        }
        if (EventsRenderer.Companion.hit(this.minesTabRect, mouseX, mouseY)) {
            this.setTab(Tab.MINES);
            return true;
        }
        if (this.tab == Tab.MINES) {
            for (MineRow row : this.mineRows) {
                if (!row.contains(mouseX, mouseY)) continue;
                EventsRenderer.Companion.joinAnarchy(row.getAnarchy());
                return true;
            }
            return false;
        }
        for (EventButton button : this.buttons) {
            if (!button.contains(mouseX, mouseY)) continue;
            EventsRenderer.Companion.joinAnarchy(button.getAnarchy());
            return true;
        }
        return false;
    }

    private final void setTab(Tab next) {
        Tab target;
        Tab tab = this.pendingTab;
        if (tab == null) {
            tab = this.tab;
        }
        if (next == (target = tab)) {
            return;
        }
        this.pendingTab = next;
    }

    public final void scroll(double amount, float viewH) {
        float max = Math.max(0.0f, this.contentH - viewH + 18.0f);
        this.scrollTarget = RangesKt.coerceIn((float)(this.scrollTarget - (float)amount * 16.0f), (float)0.0f, (float)max);
    }

    private final float renderSection(DrawContext graphics, String title, String sectionKey, float areaX, float y, float areaW, float alpha, float dt) {
        float cursorY = y;
        CharSequence charSequence = title;
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
            Fonts.SEMIBOLD.draw(title, areaX + (float)6, cursorY, 6.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)155 * alpha));
            cursorY += (float)11;
        }
        ArrayList<AnimEntry> rows = new ArrayList<AnimEntry>();
        for (AnimEntry entry : this.animEntries.values()) {
            if (!Intrinsics.areEqual((Object)sectionKey, (Object)entry.getSection()) || entry.progress() <= 0.001f) continue;
            rows.add(entry);
        }
        Comparator<AnimEntry> comparator = Comparator
                .comparingInt((AnimEntry e) -> Companion.access$displayRemaining(Companion, e))
                .thenComparingInt(e -> e.getEvent().anarchy())
                .thenComparing(e -> e.getEvent().name());
        rows.sort(comparator);
        float reorderFactor = 1.0f - (float)Math.exp(-dt * 8.5f);
        float targetY = cursorY;
        int order = 0;
        Iterator iterator = rows.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            boolean growing;
            Object e = iterator2.next();
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            AnimEntry entry = (AnimEntry)e;
            float p = entry.progress();
            float slotH = 31.0f * p;
            float gap = 5.0f * p;
            boolean bl = growing = p < 0.999f;
            if (!entry.getRenderYInit()) {
                entry.setRenderY(targetY);
                entry.setRenderYInit(true);
            } else {
                entry.setRenderY(entry.getRenderY() + (targetY - entry.getRenderY()) * reorderFactor);
                if (Math.abs(targetY - entry.getRenderY()) < 0.05f) {
                    entry.setRenderY(targetY);
                }
            }
            float cardX = areaX + 5.0f;
            float cardW = areaW - 10.0f;
            float progress = this.appear.progress(order++);
            if (progress < 0.001f) {
                targetY += slotH + gap;
                continue;
            }
            float settle = CardAppear.Companion.settle(progress);
            float presence = settle * settle;
            boolean appearing = progress < 0.999f;
            boolean compositeCard = this.appear.composite() && appearing;
            float rowY = entry.getRenderY() + (1.0f - settle) * 6.0f;
            float rowAlpha = alpha * p * (!appearing || compositeCard ? 1.0f : presence);
            if (rowY + slotH < this.listTop - 6.0f || rowY > this.listBottom + 6.0f) {
                targetY += slotH + gap;
                continue;
            }
            if (this.appear.composite()) {
                if (appearing) {
                    this.appear.push(cardX, rowY, cardW, slotH, presence, CardAppear.Companion.blurPhase(progress));
                } else if (growing) {
                    this.appear.push(cardX, rowY, cardW, slotH, p, CardAppear.Companion.blurPhase(p));
                }
            }
            if (growing) {
                Render2D.pushScissor(graphics, areaX, rowY, areaW, slotH);
            }
            if (appearing) {
                float scale = 0.85f + 0.15f * settle;
                float ox = cardX + cardW * 0.5f;
                float oy = rowY + 15.5f;
                graphics.getMatrices().pushMatrix();
                graphics.getMatrices().translate(ox, oy);
                graphics.getMatrices().scale(scale, scale);
                graphics.getMatrices().translate(-ox, -oy);
            }
            boolean hovered = this.mouseInList && !entry.getLeaving() && this.mouseX >= cardX && this.mouseX <= cardX + cardW && this.mouseY >= rowY && this.mouseY <= rowY + slotH;
            float hoverT = this.hoverProgress(EventsRenderer.Companion.key(entry.getEvent()), hovered, dt);
            this.renderEvent(entry.getEvent(), entry, cardX, rowY, cardW, rowAlpha, hoverT);
            if (appearing) {
                graphics.getMatrices().popMatrix();
            }
            if (growing) {
                Render2D.popScissor(graphics);
            }
            targetY += slotH + gap;
        }
        if (rows.isEmpty()) {
            Fonts.MEDIUM.draw("No data", areaX + (float)8, cursorY + (float)3, 5.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)70 * alpha));
            return cursorY + (float)14;
        }
        return targetY;
    }

    private final void renderEvent(FunTimeEvent event, AnimEntry anim, float x, float y, float w, float alpha, float hoverT) {
        EventStyle style = Companion.classify(event);
        this.drawCardBase(x, y, w, alpha, hoverT);
        float buttonX = x + (float)4;
        float buttonY = y + (float)5;
        float buttonW = 31.0f;
        float buttonH = 21.0f;
        int cTL = EventsRenderer.Companion.rgba(style.r1(), style.g1(), style.b1(), (float)175 * alpha);
        int cBR = EventsRenderer.Companion.rgba(style.r2(), style.g2(), style.b2(), (float)175 * alpha);
        int cMid = EventsRenderer.Companion.mixColor(cTL, cBR, 0.5f);
        Render2D.rect(buttonX, buttonY, buttonW, buttonH, 4.0f, cTL, cMid, cBR, cMid);
        float[] iconBounds = Fonts.EVENT_ICONS.msdfBounds(style.icon(), 14.5f);
        Fonts.EVENT_ICONS.msdf(style.icon(), buttonX + buttonW * 0.5f - (iconBounds[0] + iconBounds[2]) * 0.5f, buttonY + buttonH * 0.5f - (iconBounds[1] + iconBounds[3]) * 0.5f, 14.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)235 * alpha));
        float textX = buttonX + buttonW + (float)7;
        String anTag = "/an" + event.anarchy();
        float anTagW = Fonts.SEMIBOLD.width(anTag, 5.0f);
        Fonts.SEMIBOLD.draw(anTag, x + w - anTagW - (float)6, y + (float)6, 5.0f, ClientAccent.accentBrightAt((float)200 * alpha, x + w - anTagW - (float)6, y + (float)6));
        String timer = I18n.tr("Идёт");
        float timerW = Fonts.MEDIUM.width(timer, 5.0f);
        Fonts.MEDIUM.draw(timer, x + w - timerW - (float)6, y + (float)17, 5.0f, EventsRenderer.Companion.rgba(255, 255, 255, (float)120 * alpha));
        String detail = Companion.eventRarityLabel(event.rarity());
        boolean hasDetail = !StringsKt.isBlank((CharSequence)detail);
        float centerY = y + 15.5f - 3.5f;
        float topY = y + (float)5;
        long now = System.currentTimeMillis();
        if (hasDetail) {
            boolean settled = anim == null || !anim.getDrawnOnce() || anim.getHadDetail();
            float ease = this.detailEase(EventsRenderer.Companion.key(event), now, settled);
            float nameY = centerY + (topY - centerY) * ease;
            Fonts.MEDIUM.draw(event.name(), textX, nameY, 6.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)225 * alpha));
            float detailAlpha = (float)120 * alpha * ease;
            Fonts.MEDIUM.draw(detail, textX, y + (float)17, 5.0f, EventsRenderer.Companion.rgba(255, 255, 255, detailAlpha));
            float raw = (float)((now - this.startMs) % 4400L) / 4400.0f;
            float shimmerProgress = -0.5f + raw * 2.0f;
            Fonts.MEDIUM.shimmer(detail, textX, y + (float)17, 5.0f, EventsRenderer.Companion.rgba(255, 255, 255, (float)200 * alpha * ease), shimmerProgress, 0.52f, 0.9f, alpha * ease);
        } else {
            this.detailAppearMs.remove(EventsRenderer.Companion.key(event));
            Fonts.MEDIUM.draw(event.name(), textX, centerY, 6.5f, EventsRenderer.Companion.rgba(255, 255, 255, (float)225 * alpha));
        }
        if (anim != null) {
            anim.setDrawnOnce(true);
            anim.setHadDetail(hasDetail);
        }
        if (anim == null || !anim.getLeaving()) {
            this.buttons.add(new EventButton(x, y, w, 31.0f, event.anarchy()));
        }
    }

    private final void renderScrollBar(float areaX, float areaY, float areaW, float areaH, float alpha) {
        float trackX = areaX + areaW - 2.5f;
        float effR = RenderHelper.effectiveCornerRadius(12.0f, areaW, CONTENT_HEIGHT);
        float cornerInset = Math.max(0.0f, RenderHelper.cornerEdgeInset(effR, 1.0f) + 1.5f - 3.0f);
        float trackY = areaY + 3.0f + cornerInset;
        float trackH = areaH - 6.0f - cornerInset * 2.0f;
        float newScroll = this.scrollBar.render(trackX, trackY, trackH, areaH, this.contentH + 18.0f, this.scroll, alpha);
        if (this.scrollBar.isDragging()) {
            this.scroll = newScroll;
            this.scrollTarget = newScroll;
        }
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

    private static final int renderMinesList$lambda$0(MineAnim e) {
        Intrinsics.checkNotNullParameter((Object)e, (String)ICON_FLAME);
        return Companion.anarchyOf(e.getMine());
    }

    private static final int renderMinesList$lambda$1(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).intValue();
    }

    private static final String renderMinesList$lambda$2(MineAnim e) {
        Intrinsics.checkNotNullParameter((Object)e, (String)ICON_FLAME);
        return e.getMine().mineName();
    }

    private static final String renderMinesList$lambda$3(Function1 $tmp0, Object p0) {
        return (String)$tmp0.invoke(p0);
    }

    private static final boolean updateMineAnimStates$lambda$0(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean updateAnimStates$lambda$0(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final int renderSection$lambda$0(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).intValue();
    }

    private static final int renderSection$lambda$1(AnimEntry e) {
        Intrinsics.checkNotNullParameter((Object)e, (String)ICON_FLAME);
        return e.getEvent().anarchy();
    }

    private static final int renderSection$lambda$2(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).intValue();
    }

    private static final String renderSection$lambda$3(AnimEntry e) {
        Intrinsics.checkNotNullParameter((Object)e, (String)ICON_FLAME);
        return e.getEvent().name();
    }

    private static final String renderSection$lambda$4(Function1 $tmp0, Object p0) {
        return (String)$tmp0.invoke(p0);
    }

    @JvmStatic
    @NotNull
    public static final int[] darker(@NotNull int[] color) {
        return Companion.darker(color);
    }

    @JvmStatic
    public static final int anarchyOf(@NotNull FunTimeMine mine) {
        return Companion.anarchyOf(mine);
    }

    @JvmStatic
    @NotNull
    public static final int[] rarityColor(@Nullable String rarity) {
        return Companion.rarityColor(rarity);
    }

    @JvmStatic
    @NotNull
    public static final String eventRarityLabel(@Nullable String rare) {
        return Companion.eventRarityLabel(rare);
    }

    @JvmStatic
    @NotNull
    public static final String formatTime(int totalSeconds) {
        return Companion.formatTime(totalSeconds);
    }

    @JvmStatic
    @NotNull
    public static final EventStyle classify(@NotNull FunTimeEvent event) {
        return Companion.classify(event);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u001d\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\u0007J\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0017\u0010 \u001a\u00020\u001f8\u0006\u00a2\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\"\u0010$\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0014\"\u0004\b'\u0010(R\"\u0010*\u001a\u00020)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00100\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u0010\u000e\"\u0004\b3\u00104R\"\u00105\u001a\u00020)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u0010+\u001a\u0004\b6\u0010-\"\u0004\b7\u0010/R\"\u00108\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010\u0011\"\u0004\b;\u0010<R\"\u0010=\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b=\u0010%\u001a\u0004\b>\u0010\u0014\"\u0004\b?\u0010(R\"\u0010@\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b@\u0010%\u001a\u0004\bA\u0010\u0014\"\u0004\bB\u0010(R\"\u0010C\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bC\u0010%\u001a\u0004\bD\u0010\u0014\"\u0004\bE\u0010(\u00a8\u0006F"}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer$AnimEntry;", "", "Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "event", "", "section", "<init>", "(Lrtx/kimiko/api/events/funtime/FunTimeEvent;Ljava/lang/String;)V", "", "markLeaving", "()V", "markPresent", "", "effectiveRemaining", "()I", "", "progress", "()F", "", "isGone", "()Z", "Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "getEvent", "()Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "setEvent", "(Lrtx/kimiko/api/events/funtime/FunTimeEvent;)V", "Ljava/lang/String;", "getSection", "()Ljava/lang/String;", "setSection", "(Ljava/lang/String;)V", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "getAnim", "()Lrtx/kimiko/utils/animations/Decelerate;", "leaving", "Z", "getLeaving", "setLeaving", "(Z)V", "", "departedAtMs", "J", "getDepartedAtMs", "()J", "setDepartedAtMs", "(J)V", "lastRemaining", "I", "getLastRemaining", "setLastRemaining", "(I)V", "lastSeenMs", "getLastSeenMs", "setLastSeenMs", "renderY", "F", "getRenderY", "setRenderY", "(F)V", "renderYInit", "getRenderYInit", "setRenderYInit", "drawnOnce", "getDrawnOnce", "setDrawnOnce", "hadDetail", "getHadDetail", "setHadDetail", "rtx.kimiko:kimiko"})
    private static final class AnimEntry {
        @NotNull
        private FunTimeEvent event;
        @NotNull
        private String section;
        @NotNull
        private final Decelerate anim;
        private boolean leaving;
        private long departedAtMs;
        private int lastRemaining;
        private long lastSeenMs;
        private float renderY;
        private boolean renderYInit;
        private boolean drawnOnce;
        private boolean hadDetail;

        public AnimEntry(@NotNull FunTimeEvent event, @NotNull String section) {
            Intrinsics.checkNotNullParameter((Object)event, (String)"event");
            Intrinsics.checkNotNullParameter((Object)section, (String)"section");
            this.event = event;
            this.section = section;
            Animation animation = new Decelerate().setMs(280).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            this.anim = (Decelerate)animation;
            this.lastRemaining = FunTimeEventsClient.INSTANCE.remainingSeconds(this.event);
            this.lastSeenMs = System.currentTimeMillis();
            this.anim.setDirection(Direction.FORWARDS);
        }

        @NotNull
        public final FunTimeEvent getEvent() {
            return this.event;
        }

        public final void setEvent(@NotNull FunTimeEvent funTimeEvent) {
            Intrinsics.checkNotNullParameter((Object)funTimeEvent, (String)"<set-?>");
            this.event = funTimeEvent;
        }

        @NotNull
        public final String getSection() {
            return this.section;
        }

        public final void setSection(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.section = string;
        }

        @NotNull
        public final Decelerate getAnim() {
            return this.anim;
        }

        public final boolean getLeaving() {
            return this.leaving;
        }

        public final void setLeaving(boolean bl) {
            this.leaving = bl;
        }

        public final long getDepartedAtMs() {
            return this.departedAtMs;
        }

        public final void setDepartedAtMs(long l) {
            this.departedAtMs = l;
        }

        public final int getLastRemaining() {
            return this.lastRemaining;
        }

        public final void setLastRemaining(int n) {
            this.lastRemaining = n;
        }

        public final long getLastSeenMs() {
            return this.lastSeenMs;
        }

        public final void setLastSeenMs(long l) {
            this.lastSeenMs = l;
        }

        public final float getRenderY() {
            return this.renderY;
        }

        public final void setRenderY(float f) {
            this.renderY = f;
        }

        public final boolean getRenderYInit() {
            return this.renderYInit;
        }

        public final void setRenderYInit(boolean bl) {
            this.renderYInit = bl;
        }

        public final boolean getDrawnOnce() {
            return this.drawnOnce;
        }

        public final void setDrawnOnce(boolean bl) {
            this.drawnOnce = bl;
        }

        public final boolean getHadDetail() {
            return this.hadDetail;
        }

        public final void setHadDetail(boolean bl) {
            this.hadDetail = bl;
        }

        public final void markLeaving() {
            if (!this.leaving) {
                this.leaving = true;
                this.anim.setDirection(Direction.BACKWARDS);
            }
        }

        public final void markPresent(@NotNull FunTimeEvent event, @NotNull String section) {
            Intrinsics.checkNotNullParameter((Object)event, (String)"event");
            Intrinsics.checkNotNullParameter((Object)section, (String)"section");
            this.event = event;
            this.section = section;
            this.lastRemaining = FunTimeEventsClient.INSTANCE.remainingSeconds(event);
            this.lastSeenMs = System.currentTimeMillis();
            this.departedAtMs = 0L;
            if (this.leaving) {
                this.leaving = false;
                this.anim.setDirection(Direction.FORWARDS);
            }
        }

        public final int effectiveRemaining() {
            long elapsed = (System.currentTimeMillis() - this.lastSeenMs) / 1000L;
            return Math.max(0, this.lastRemaining - (int)elapsed);
        }

        public final float progress() {
            Double d = this.anim.getOutput();
            return RangesKt.coerceIn((float)((float)(d != null ? d : 0.0)), (float)0.0f, (float)1.0f);
        }

        public final boolean isGone() {
            return this.leaving && this.anim.isFinished(Direction.BACKWARDS);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b+\n\u0002\u0010\t\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0010\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0019\u0010\"\u001a\u00020\u000e2\b\u0010!\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b\"\u0010\u0014J\u0017\u0010#\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b%\u0010&J'\u0010-\u001a\u00020,2\u0006\u0010(\u001a\u00020'2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u00101\u001a\u0002002\u0006\u0010/\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b1\u00102J\u0019\u00104\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b4\u0010\u0014J'\u00108\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u000b2\u0006\u00107\u001a\u00020)H\u0002\u00a2\u0006\u0004\b8\u00109J/\u0010>\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020)H\u0002\u00a2\u0006\u0004\b>\u0010?J\u0017\u0010A\u001a\u00020)2\u0006\u0010@\u001a\u00020)H\u0002\u00a2\u0006\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010DR\u0014\u0010F\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010DR\u0014\u0010G\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010DR\u0014\u0010H\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010DR\u0014\u0010I\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010DR\u0014\u0010J\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010DR\u0014\u0010K\u001a\u00020)8\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\bK\u0010DR\u0014\u0010L\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010DR\u0014\u0010M\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010NR\u0014\u0010P\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010NR\u0014\u0010Q\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010NR\u0014\u0010R\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010NR\u0014\u0010S\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010NR\u0014\u0010T\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u0010NR\u0014\u0010U\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010NR\u0014\u0010V\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010NR\u0014\u0010W\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010NR\u0014\u0010X\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010DR\u0014\u0010Y\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010DR\u0014\u0010Z\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010_\u001a\u00020\\8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b_\u0010^R\u0014\u0010`\u001a\u00020\\8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b`\u0010^R\u0014\u0010a\u001a\u00020\\8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010^R\u0014\u0010b\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bb\u0010DR\u0014\u0010c\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bc\u0010DR\u0014\u0010d\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010DR\u0014\u0010e\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\be\u0010DR\u0014\u0010f\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010D\u00a8\u0006g"}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer.Companion;", "", "<init>", "()V", "", "color", "Lkotlin/jvm/JvmStatic;", "darker", "([I)[I", "Lrtx/kimiko/api/events/funtime/FunTimeMine;", "mine", "", "anarchyOf", "(Lrtx/kimiko/api/events/funtime/FunTimeMine;)I", "", "rarity", "rarityColor", "(Ljava/lang/String;)[I", "rare", "eventRarityLabel", "(Ljava/lang/String;)Ljava/lang/String;", "totalSeconds", "formatTime", "(I)Ljava/lang/String;", "Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "event", "Lrtx/kimiko/api/ui/events/EventsRenderer$EventStyle;", "classify", "(Lrtx/kimiko/api/events/funtime/FunTimeEvent;)Lrtx/kimiko/api/ui/events/EventsRenderer$EventStyle;", "Lrtx/kimiko/api/ui/events/EventsRenderer$AnimEntry;", "entry", "displayRemaining", "(Lrtx/kimiko/api/ui/events/EventsRenderer$AnimEntry;)I", "error", "offlineMessage", "key", "(Lrtx/kimiko/api/events/funtime/FunTimeEvent;)Ljava/lang/String;", "mineKey", "(Lrtx/kimiko/api/events/funtime/FunTimeMine;)Ljava/lang/String;", "", "rect", "", "mouseX", "mouseY", "", "hit", "([FFF)Z", "anarchy", "", "joinAnarchy", "(I)V", "value", "lower", "a", "b", "t", "mixColor", "(IIF)I", "red", "green", "blue", "alpha", "rgba", "(IIIF)I", "tIn", "smooth01", "(F)F", "ROW_H", "F", "GAP", "SIDE_PAD", "CARD_RADIUS", "PANEL_RADIUS", "CORNER_BLEND", "CONTENT_Y_OFFSET", "CONTENT_HEIGHT", "EVENT_ICON_SIZE", "ICON_AIR_DROP", "Ljava/lang/String;", "ICON_ALTAR", "ICON_CLOVER", "ICON_COMING_SOON", "ICON_FLAME", "ICON_LIGHTHOUSE", "ICON_METEOR", "ICON_MOUNTAIN", "ICON_PICKAXE", "ICON_TREASURE", "DETAIL_ANIM_MS", "FADE_OUT_DURATION", "ANIM_MS", "I", "", "APPEAR_STAGGER_MS", "J", "SHIMMER_CYCLE_MS", "INITIAL_WINDOW_MS", "LINGER_MS", "REORDER_RATE", "TAB_BAR_H", "BOTTOM_PAD", "TAB_FADE_RATE", "TAB_BTN_RATE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final int[] darker(@NotNull int[] color) {
            Intrinsics.checkNotNullParameter((Object)color, (String)"color");
            int[] nArray = new int[]{MathKt.roundToInt((float)((float)color[0] * 0.42f)), MathKt.roundToInt((float)((float)color[1] * 0.42f)), MathKt.roundToInt((float)((float)color[2] * 0.42f))};
            return nArray;
        }

        @JvmStatic
        public final int anarchyOf(@NotNull FunTimeMine mine) {
            int n;
            Intrinsics.checkNotNullParameter((Object)mine, (String)"mine");
            String id = mine.serverId();
            StringBuilder digits = new StringBuilder();
            int n2 = ((CharSequence)id).length();
            for (int i = 0; i < n2; ++i) {
                char c = id.charAt(i);
                boolean bl = '0' <= c ? c < ':' : false;
                if (!bl) continue;
                digits.append(c);
            }
            try {
                int n3;
                if (((CharSequence)digits).length() == 0) {
                    n3 = 0;
                } else {
                    String string = digits.toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    n3 = Integer.parseInt(string);
                }
                n = n3;
            }
            catch (NumberFormatException ignored) {
                n = 0;
            }
            return n;
        }

        @JvmStatic
        @NotNull
        public final int[] rarityColor(@Nullable String rarity) {
            String r;
            String string;
            block8: {
                block7: {
                    string = rarity;
                    if (string == null) break block7;
                    String string2 = string;
                    Locale locale = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                    String string3 = string2.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
                    string = string3;
                    if (string3 != null) break block8;
                }
                string = "";
            }
            if (String.valueOf((r = string)).contains("мифическ")) {
                int[] nArray = new int[]{170, 90, 230};
                return nArray;
            }
            if (String.valueOf(r).contains("легендарн")) {
                int[] nArray = new int[]{235, 190, 70};
                return nArray;
            }
            if (String.valueOf(r).contains("эпическ")) {
                int[] nArray = new int[]{150, 90, 220};
                return nArray;
            }
            if (String.valueOf(r).contains("редк")) {
                int[] nArray = new int[]{70, 130, 235};
                return nArray;
            }
            int[] nArray = new int[]{150, 150, 165};
            return nArray;
        }

        @JvmStatic
        @NotNull
        public final String eventRarityLabel(@Nullable String rare) {
            if (rare == null) {
                return "";
            }
            String string = rare;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toUpperCase(...)");
            return switch (string2) {
                case "MYTHICAL" -> I18n.tr("Мифический");
                case "LEGENDARY" -> I18n.tr("Легендарный");
                case "EPIC" -> I18n.tr("Эпический");
                case "RARE" -> I18n.tr("Редкий");
                case "NORMAL" -> I18n.tr("Обычный");
                default -> "";
            };
        }

        @JvmStatic
        @NotNull
        public final String formatTime(int totalSeconds) {
            if (totalSeconds <= 0) {
                return I18n.tr("Идёт");
            }
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            Object[] objectArray = new Object[]{minutes, seconds};
            return I18n.tr("%dмин. %dсек.", objectArray);
        }

        @JvmStatic
        @NotNull
        public final EventStyle classify(@NotNull FunTimeEvent event) {
            String status;
            String string;
            block46: {
                block45: {
                    Intrinsics.checkNotNullParameter((Object)event, (String)"event");
                    string = event.status();
                    if (string == null) break block45;
                    String string2 = string;
                    Locale locale = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                    String string3 = string2.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toUpperCase(...)");
                    string = string3;
                    if (string3 != null) break block46;
                }
                string = "";
            }
            switch (status = string) {
                case "MINE": {
                    return new EventStyle(EventsRenderer.ICON_PICKAXE, 140, 120, 80, 100, 85, 55);
                }
                case "SNOWQUARRY": {
                    return new EventStyle(EventsRenderer.ICON_CLOVER, 60, 160, 90, 40, 120, 60);
                }
                case "SHIP": {
                    return new EventStyle(EventsRenderer.ICON_TREASURE, 70, 130, 200, 40, 90, 150);
                }
                case "JAYCOB": {
                    return new EventStyle(EventsRenderer.ICON_ALTAR, 150, 110, 60, 110, 80, 40);
                }
                case "AIRDROP": {
                    return new EventStyle(EventsRenderer.ICON_AIR_DROP, 80, 140, 100, 50, 100, 70);
                }
                case "METEOR_RAIN": 
                case "METEOR": {
                    return new EventStyle(EventsRenderer.ICON_METEOR, 100, 100, 110, 50, 50, 60);
                }
                case "BEACON": {
                    return new EventStyle(EventsRenderer.ICON_LIGHTHOUSE, 100, 40, 160, 170, 90, 230);
                }
                case "VOLCANO": {
                    return new EventStyle(EventsRenderer.ICON_MOUNTAIN, 200, 50, 30, 240, 140, 40);
                }
            }
            String text = this.lower(event.name());
            if (String.valueOf(text).contains("вулкан")) {
                return new EventStyle(EventsRenderer.ICON_MOUNTAIN, 200, 50, 30, 240, 140, 40);
            }
            if (String.valueOf(text).contains("маяк")) {
                return new EventStyle(EventsRenderer.ICON_LIGHTHOUSE, 100, 40, 160, 170, 90, 230);
            }
            if (String.valueOf(text).contains("метеор")) {
                return new EventStyle(EventsRenderer.ICON_METEOR, 100, 100, 110, 50, 50, 60);
            }
            if (String.valueOf(text).contains("адск") || String.valueOf(text).contains("резн")) {
                return new EventStyle(EventsRenderer.ICON_FLAME, 160, 30, 30, 100, 15, 20);
            }
            if (String.valueOf(text).contains("гейзер")) {
                return new EventStyle(EventsRenderer.ICON_CLOVER, 40, 90, 200, 80, 180, 240);
            }
            if (String.valueOf(text).contains("сундук")) {
                return new EventStyle(EventsRenderer.ICON_ALTAR, 130, 130, 140, 180, 180, 190);
            }
            if (String.valueOf(text).contains("шахт") || String.valueOf(text).contains("mine")) {
                return new EventStyle(EventsRenderer.ICON_PICKAXE, 140, 120, 80, 100, 85, 55);
            }
            if (String.valueOf(text).contains("аир") || String.valueOf(text).contains("air") || String.valueOf(text).contains("дроп")) {
                return new EventStyle(EventsRenderer.ICON_AIR_DROP, 80, 140, 100, 50, 100, 70);
            }
            if (String.valueOf(text).contains("клевер") || String.valueOf(text).contains("удач")) {
                return new EventStyle(EventsRenderer.ICON_CLOVER, 60, 160, 80, 40, 120, 55);
            }
            if (String.valueOf(text).contains("клад") || String.valueOf(text).contains("казн")) {
                return new EventStyle(EventsRenderer.ICON_TREASURE, 180, 160, 60, 140, 120, 40);
            }
            if (String.valueOf(text).contains("алтар")) {
                return new EventStyle(EventsRenderer.ICON_ALTAR, 130, 130, 140, 180, 180, 190);
            }
            return new EventStyle(EventsRenderer.ICON_COMING_SOON, 140, 140, 150, 180, 180, 190);
        }

        private final int displayRemaining(AnimEntry entry) {
            if (entry.getLeaving() || entry.getDepartedAtMs() != 0L) {
                return entry.effectiveRemaining();
            }
            return FunTimeEventsClient.INSTANCE.remainingSeconds(entry.getEvent());
        }

        private final String offlineMessage(String error) {
            if (error == null || StringsKt.isBlank(error)) {
                return I18n.tr("Нет связи с ВДС");
            }
            return I18n.tr("Ошибка: %s", new Object[]{I18n.tr(error)});
        }

        private final String key(FunTimeEvent event) {
            return event.anarchy() + "|" + event.name();
        }

        private final String mineKey(FunTimeMine mine) {
            return mine.serverId() + "|" + mine.mineName();
        }

        private final boolean hit(float[] rect, float mouseX, float mouseY) {
            return mouseX >= rect[0] && mouseX <= rect[0] + rect[2] && mouseY >= rect[1] && mouseY <= rect[1] + rect[3];
        }

        private final void joinAnarchy(int anarchy) {
            MinecraftClient minecraft;
            block5: {
                block4: {
                    if (anarchy <= 0) {
                        return;
                    }
                    MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
                    Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
                    minecraft = minecraftClient2;
                    if (minecraft.player == null) break block4;
                    ClientPlayerEntity clientPlayerEntity2 = minecraft.player;
                    Intrinsics.checkNotNull((Object)clientPlayerEntity2);
                    if (clientPlayerEntity2.networkHandler != null) break block5;
                }
                return;
            }
            ClientPlayerEntity clientPlayerEntity3 = minecraft.player;
            Intrinsics.checkNotNull((Object)clientPlayerEntity3);
            clientPlayerEntity3.networkHandler.sendChatCommand("an" + anarchy);
        }

        private final String lower(String value) {
            String string;
            block3: {
                block2: {
                    string = value;
                    if (string == null) break block2;
                    String string2 = string;
                    Locale locale = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                    String string3 = string2.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
                    string = string3;
                    if (string3 != null) break block3;
                }
                string = "";
            }
            return string;
        }

        private final int mixColor(int a, int b, float t) {
            int aA = a >> 24 & 0xFF;
            int aR = a >> 16 & 0xFF;
            int aG = a >> 8 & 0xFF;
            int aB = a & 0xFF;
            int bA = b >> 24 & 0xFF;
            int bR = b >> 16 & 0xFF;
            int bG = b >> 8 & 0xFF;
            int bB = b & 0xFF;
            return (int)((float)aA + (float)(bA - aA) * t) << 24 | (int)((float)aR + (float)(bR - aR) * t) << 16 | (int)((float)aG + (float)(bG - aG) * t) << 8 | (int)((float)aB + (float)(bB - aB) * t);
        }

        private final int rgba(int red, int green, int blue, float alpha) {
            int a = RangesKt.coerceIn((int)MathKt.roundToInt((float)alpha), (int)0, (int)255);
            if (a == 0) {
                return 0;
            }
            return new Color(red, green, blue, a).getRGB();
        }

        private final float smooth01(float tIn) {
            float t = RangesKt.coerceIn((float)tIn, (float)0.0f, (float)1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        public static final /* synthetic */ int access$displayRemaining(Companion $this, AnimEntry entry) {
            return $this.displayRemaining(entry);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0011J\u0010\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016JB\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001a\u001a\u00020\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001c\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u0016J\u0011\u0010\u001e\u001a\u00020\u001dH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\"\u0010\u0011R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b#\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b$\u0010\u0011R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010%\u001a\u0004\b&\u0010\u0016\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer$EventButton;", "", "", "x", "y", "w", "h", "", "anarchy", "<init>", "(FFFFI)V", "mouseX", "mouseY", "", "contains", "(FF)Z", "component1", "()F", "component2", "component3", "component4", "component5", "()I", "copy", "(FFFFI)Lrtx/kimiko/api/ui/events/EventsRenderer$EventButton;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getW", "getH", "I", "getAnarchy", "rtx.kimiko:kimiko"})
    private static final class EventButton {
        private final float x;
        private final float y;
        private final float w;
        private final float h;
        private final int anarchy;

        public EventButton(float x, float y, float w, float h, int anarchy) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.anarchy = anarchy;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final float getW() {
            return this.w;
        }

        public final float getH() {
            return this.h;
        }

        public final int getAnarchy() {
            return this.anarchy;
        }

        public final boolean contains(float mouseX, float mouseY) {
            return mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h;
        }

        public final float component1() {
            return this.x;
        }

        public final float component2() {
            return this.y;
        }

        public final float component3() {
            return this.w;
        }

        public final float component4() {
            return this.h;
        }

        public final int component5() {
            return this.anarchy;
        }

        @NotNull
        public final EventButton copy(float x, float y, float w, float h, int anarchy) {
            return new EventButton(x, y, w, h, anarchy);
        }

        public static /* synthetic */ EventButton copy$default(EventButton eventButton, float f, float f2, float f3, float f4, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                f = eventButton.x;
            }
            if ((n2 & 2) != 0) {
                f2 = eventButton.y;
            }
            if ((n2 & 4) != 0) {
                f3 = eventButton.w;
            }
            if ((n2 & 8) != 0) {
                f4 = eventButton.h;
            }
            if ((n2 & 0x10) != 0) {
                n = eventButton.anarchy;
            }
            return eventButton.copy(f, f2, f3, f4, n);
        }

        @NotNull
        public String toString() {
            return "EventButton(x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.h + ", anarchy=" + this.anarchy + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.w);
            result = result * 31 + Float.hashCode(this.h);
            result = result * 31 + Integer.hashCode(this.anarchy);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EventButton)) {
                return false;
            }
            EventButton eventButton = (EventButton)other;
            if (Float.compare(this.x, eventButton.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, eventButton.y) != 0) {
                return false;
            }
            if (Float.compare(this.w, eventButton.w) != 0) {
                return false;
            }
            if (Float.compare(this.h, eventButton.h) != 0) {
                return false;
            }
            return this.anarchy == eventButton.anarchy;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0010JV\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001c\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u0010J\u0011\u0010\u001d\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u000eR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b\u0003\u0010\u000eR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\u0005\u0010\u0010R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\u0006\u0010\u0010R%\u0010\u0007\u001a\u00020\u00048\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\u0007\u0010\u0010R%\u0010\b\u001a\u00020\u00048\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\b\u0010\u0010R%\u0010\t\u001a\u00020\u00048\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\t\u0010\u0010R%\u0010\n\u001a\u00020\u00048\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b\n\u0010\u0010\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer$EventStyle;", "", "", "icon", "", "r1", "g1", "b1", "r2", "g2", "b2", "<init>", "(Ljava/lang/String;IIIIII)V", "component1", "()Ljava/lang/String;", "component2", "()I", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;IIIIII)Lrtx/kimiko/api/ui/events/EventsRenderer$EventStyle;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "I", "rtx.kimiko:kimiko"})
    public static final class EventStyle {
        @NotNull
        private final String icon;
        private final int r1;
        private final int g1;
        private final int b1;
        private final int r2;
        private final int g2;
        private final int b2;

        public EventStyle(@NotNull String icon, int r1, int g1, int b1, int r2, int g2, int b2) {
            Intrinsics.checkNotNullParameter((Object)icon, (String)"icon");
            this.icon = icon;
            this.r1 = r1;
            this.g1 = g1;
            this.b1 = b1;
            this.r2 = r2;
            this.g2 = g2;
            this.b2 = b2;
        }

        @JvmName(name="icon")
        @NotNull
        public final String icon() {
            return this.icon;
        }

        @JvmName(name="r1")
        public final int r1() {
            return this.r1;
        }

        @JvmName(name="g1")
        public final int g1() {
            return this.g1;
        }

        @JvmName(name="b1")
        public final int b1() {
            return this.b1;
        }

        @JvmName(name="r2")
        public final int r2() {
            return this.r2;
        }

        @JvmName(name="g2")
        public final int g2() {
            return this.g2;
        }

        @JvmName(name="b2")
        public final int b2() {
            return this.b2;
        }

        @NotNull
        public final String component1() {
            return this.icon;
        }

        public final int component2() {
            return this.r1;
        }

        public final int component3() {
            return this.g1;
        }

        public final int component4() {
            return this.b1;
        }

        public final int component5() {
            return this.r2;
        }

        public final int component6() {
            return this.g2;
        }

        public final int component7() {
            return this.b2;
        }

        @NotNull
        public final EventStyle copy(@NotNull String icon, int r1, int g1, int b1, int r2, int g2, int b2) {
            Intrinsics.checkNotNullParameter((Object)icon, (String)"icon");
            return new EventStyle(icon, r1, g1, b1, r2, g2, b2);
        }

        public static /* synthetic */ EventStyle copy$default(EventStyle eventStyle, String string, int n, int n2, int n3, int n4, int n5, int n6, int n7, Object object) {
            if ((n7 & 1) != 0) {
                string = eventStyle.icon;
            }
            if ((n7 & 2) != 0) {
                n = eventStyle.r1;
            }
            if ((n7 & 4) != 0) {
                n2 = eventStyle.g1;
            }
            if ((n7 & 8) != 0) {
                n3 = eventStyle.b1;
            }
            if ((n7 & 0x10) != 0) {
                n4 = eventStyle.r2;
            }
            if ((n7 & 0x20) != 0) {
                n5 = eventStyle.g2;
            }
            if ((n7 & 0x40) != 0) {
                n6 = eventStyle.b2;
            }
            return eventStyle.copy(string, n, n2, n3, n4, n5, n6);
        }

        @NotNull
        public String toString() {
            return "EventStyle(icon=" + this.icon + ", r1=" + this.r1 + ", g1=" + this.g1 + ", b1=" + this.b1 + ", r2=" + this.r2 + ", g2=" + this.g2 + ", b2=" + this.b2 + ")";
        }

        public int hashCode() {
            int result = this.icon.hashCode();
            result = result * 31 + Integer.hashCode(this.r1);
            result = result * 31 + Integer.hashCode(this.g1);
            result = result * 31 + Integer.hashCode(this.b1);
            result = result * 31 + Integer.hashCode(this.r2);
            result = result * 31 + Integer.hashCode(this.g2);
            result = result * 31 + Integer.hashCode(this.b2);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EventStyle)) {
                return false;
            }
            EventStyle eventStyle = (EventStyle)other;
            if (!Intrinsics.areEqual((Object)this.icon, (Object)eventStyle.icon)) {
                return false;
            }
            if (this.r1 != eventStyle.r1) {
                return false;
            }
            if (this.g1 != eventStyle.g1) {
                return false;
            }
            if (this.b1 != eventStyle.b1) {
                return false;
            }
            if (this.r2 != eventStyle.r2) {
                return false;
            }
            if (this.g2 != eventStyle.g2) {
                return false;
            }
            return this.b2 == eventStyle.b2;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0005J\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0005R\u0017\u0010\u0018\u001a\u00020\u00178\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010\f\"\u0004\b$\u0010%R\"\u0010&\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b&\u0010\"\u001a\u0004\b'\u0010\f\"\u0004\b(\u0010%R\"\u0010)\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010\"\u001a\u0004\b*\u0010\f\"\u0004\b+\u0010%R\"\u0010,\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010\u000f\"\u0004\b/\u00100R\"\u00101\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b1\u0010\u001d\u001a\u0004\b2\u0010\u0012\"\u0004\b3\u0010 \u00a8\u00064"}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer$MineAnim;", "", "Lrtx/kimiko/api/events/funtime/FunTimeMine;", "mine", "<init>", "(Lrtx/kimiko/api/events/funtime/FunTimeMine;)V", "", "markLeaving", "()V", "markPresent", "", "effectiveRemaining", "()J", "", "progress", "()F", "", "isGone", "()Z", "Lrtx/kimiko/api/events/funtime/FunTimeMine;", "getMine", "()Lrtx/kimiko/api/events/funtime/FunTimeMine;", "setMine", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "getAnim", "()Lrtx/kimiko/utils/animations/Decelerate;", "leaving", "Z", "getLeaving", "setLeaving", "(Z)V", "departedAtMs", "J", "getDepartedAtMs", "setDepartedAtMs", "(J)V", "lastRemaining", "getLastRemaining", "setLastRemaining", "lastSeenMs", "getLastSeenMs", "setLastSeenMs", "renderY", "F", "getRenderY", "setRenderY", "(F)V", "renderYInit", "getRenderYInit", "setRenderYInit", "rtx.kimiko:kimiko"})
    private static final class MineAnim {
        @NotNull
        private FunTimeMine mine;
        @NotNull
        private final Decelerate anim;
        private boolean leaving;
        private long departedAtMs;
        private long lastRemaining;
        private long lastSeenMs;
        private float renderY;
        private boolean renderYInit;

        public MineAnim(@NotNull FunTimeMine mine) {
            Intrinsics.checkNotNullParameter((Object)mine, (String)"mine");
            this.mine = mine;
            Animation animation = new Decelerate().setMs(280).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            this.anim = (Decelerate)animation;
            this.lastRemaining = FunTimeEventsClient.INSTANCE.remainingSeconds(this.mine);
            this.lastSeenMs = System.currentTimeMillis();
            this.anim.setDirection(Direction.FORWARDS);
        }

        @NotNull
        public final FunTimeMine getMine() {
            return this.mine;
        }

        public final void setMine(@NotNull FunTimeMine funTimeMine) {
            Intrinsics.checkNotNullParameter((Object)funTimeMine, (String)"<set-?>");
            this.mine = funTimeMine;
        }

        @NotNull
        public final Decelerate getAnim() {
            return this.anim;
        }

        public final boolean getLeaving() {
            return this.leaving;
        }

        public final void setLeaving(boolean bl) {
            this.leaving = bl;
        }

        public final long getDepartedAtMs() {
            return this.departedAtMs;
        }

        public final void setDepartedAtMs(long l) {
            this.departedAtMs = l;
        }

        public final long getLastRemaining() {
            return this.lastRemaining;
        }

        public final void setLastRemaining(long l) {
            this.lastRemaining = l;
        }

        public final long getLastSeenMs() {
            return this.lastSeenMs;
        }

        public final void setLastSeenMs(long l) {
            this.lastSeenMs = l;
        }

        public final float getRenderY() {
            return this.renderY;
        }

        public final void setRenderY(float f) {
            this.renderY = f;
        }

        public final boolean getRenderYInit() {
            return this.renderYInit;
        }

        public final void setRenderYInit(boolean bl) {
            this.renderYInit = bl;
        }

        public final void markLeaving() {
            if (!this.leaving) {
                this.leaving = true;
                this.anim.setDirection(Direction.BACKWARDS);
            }
        }

        public final void markPresent(@NotNull FunTimeMine mine) {
            Intrinsics.checkNotNullParameter((Object)mine, (String)"mine");
            this.mine = mine;
            this.lastRemaining = FunTimeEventsClient.INSTANCE.remainingSeconds(mine);
            this.lastSeenMs = System.currentTimeMillis();
            this.departedAtMs = 0L;
            if (this.leaving) {
                this.leaving = false;
                this.anim.setDirection(Direction.FORWARDS);
            }
        }

        public final long effectiveRemaining() {
            long elapsed = (System.currentTimeMillis() - this.lastSeenMs) / 1000L;
            return Math.max(0L, this.lastRemaining - elapsed);
        }

        public final float progress() {
            Double d = this.anim.getOutput();
            return RangesKt.coerceIn((float)((float)(d != null ? d : 0.0)), (float)0.0f, (float)1.0f);
        }

        public final boolean isGone() {
            return this.leaving && this.anim.isFinished(Direction.BACKWARDS);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0011J\u0010\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016JB\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001a\u001a\u00020\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001c\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u0016J\u0011\u0010\u001e\u001a\u00020\u001dH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\"\u0010\u0011R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b#\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b$\u0010\u0011R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010%\u001a\u0004\b&\u0010\u0016\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer$MineRow;", "", "", "x", "y", "w", "h", "", "anarchy", "<init>", "(FFFFI)V", "mouseX", "mouseY", "", "contains", "(FF)Z", "component1", "()F", "component2", "component3", "component4", "component5", "()I", "copy", "(FFFFI)Lrtx/kimiko/api/ui/events/EventsRenderer$MineRow;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getW", "getH", "I", "getAnarchy", "rtx.kimiko:kimiko"})
    private static final class MineRow {
        private final float x;
        private final float y;
        private final float w;
        private final float h;
        private final int anarchy;

        public MineRow(float x, float y, float w, float h, int anarchy) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.anarchy = anarchy;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final float getW() {
            return this.w;
        }

        public final float getH() {
            return this.h;
        }

        public final int getAnarchy() {
            return this.anarchy;
        }

        public final boolean contains(float mouseX, float mouseY) {
            return mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h;
        }

        public final float component1() {
            return this.x;
        }

        public final float component2() {
            return this.y;
        }

        public final float component3() {
            return this.w;
        }

        public final float component4() {
            return this.h;
        }

        public final int component5() {
            return this.anarchy;
        }

        @NotNull
        public final MineRow copy(float x, float y, float w, float h, int anarchy) {
            return new MineRow(x, y, w, h, anarchy);
        }

        public static /* synthetic */ MineRow copy$default(MineRow mineRow, float f, float f2, float f3, float f4, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                f = mineRow.x;
            }
            if ((n2 & 2) != 0) {
                f2 = mineRow.y;
            }
            if ((n2 & 4) != 0) {
                f3 = mineRow.w;
            }
            if ((n2 & 8) != 0) {
                f4 = mineRow.h;
            }
            if ((n2 & 0x10) != 0) {
                n = mineRow.anarchy;
            }
            return mineRow.copy(f, f2, f3, f4, n);
        }

        @NotNull
        public String toString() {
            return "MineRow(x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.h + ", anarchy=" + this.anarchy + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.w);
            result = result * 31 + Float.hashCode(this.h);
            result = result * 31 + Integer.hashCode(this.anarchy);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MineRow)) {
                return false;
            }
            MineRow mineRow = (MineRow)other;
            if (Float.compare(this.x, mineRow.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, mineRow.y) != 0) {
                return false;
            }
            if (Float.compare(this.w, mineRow.w) != 0) {
                return false;
            }
            if (Float.compare(this.h, mineRow.h) != 0) {
                return false;
            }
            return this.anarchy == mineRow.anarchy;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/ui/events/EventsRenderer$Tab;", "", "<init>", "(Ljava/lang/String;I)V", "EVENTS", "MINES", "rtx.kimiko:kimiko"})
    private static enum Tab {
        EVENTS,
        MINES;
@NotNull
        public static EnumEntries<Tab> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

