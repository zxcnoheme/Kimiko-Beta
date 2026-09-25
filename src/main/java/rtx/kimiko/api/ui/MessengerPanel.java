/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.input.CharInput
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.messenger.MessengerClient;
import rtx.kimiko.api.chat.prefix.ChatPrefixes;
import rtx.kimiko.api.chat.voice.VoiceNote;
import rtx.kimiko.api.chat.voice.VoiceNotePlayer;
import rtx.kimiko.api.chat.voice.VoiceNoteRecorder;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteAvatarCache;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.ui.messenger.ChatPrefixBadge;
import rtx.kimiko.api.ui.messenger.UserActionsPopup;
import rtx.kimiko.api.ui.messenger.VoiceBubble;
import rtx.kimiko.api.ui.module.DiscordAvatar;
import rtx.kimiko.api.ui.module.SearchField;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0011\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b:\u0018\u0000 \u00f6\u00012\u00020\u0001:\b\u00f7\u0001\u00f8\u0001\u00f9\u0001\u00f6\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\u0006J\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0003J\r\u0010\u0011\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0003J\r\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0003J\r\u0010\u0013\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0003J'\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u001f\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u0003J\u001d\u0010!\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\t\u00a2\u0006\u0004\b#\u0010\u000bJ\u001f\u0010'\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010$2\u0006\u0010&\u001a\u00020\f\u00a2\u0006\u0004\b'\u0010(JU\u00103\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\t\u00a2\u0006\u0004\b3\u00104JG\u0010:\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\t2\u0006\u00106\u001a\u00020\t2\u0006\u00107\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00109\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b:\u0010;J/\u0010>\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b>\u0010?J\u0015\u0010B\u001a\b\u0012\u0004\u0012\u00020A0@H\u0002\u00a2\u0006\u0004\bB\u0010CJ/\u0010D\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00109\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bD\u0010EJ\u0017\u0010F\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bF\u0010GJ\u0017\u0010I\u001a\u00020\t2\u0006\u0010H\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bI\u0010JJG\u0010L\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010K\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00109\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bL\u0010;JG\u0010M\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010K\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00109\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bM\u0010;J7\u0010O\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\t2\u0006\u0010N\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00109\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bO\u0010PJ7\u0010Q\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\t2\u0006\u0010N\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00109\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bQ\u0010PJG\u0010Y\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\t2\u0006\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020\t2\u0006\u0010V\u001a\u00020U2\u0006\u0010W\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u0010X\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bY\u0010ZJG\u0010\\\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00109\u001a\u00020\t2\u0006\u0010[\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\\\u0010;J'\u0010]\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u00108\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b]\u0010^J!\u0010`\u001a\u0004\u0018\u00010_2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0002\u00a2\u0006\u0004\b`\u0010aJ'\u0010c\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t2\u0006\u0010b\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bc\u0010dJ\u000f\u0010e\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\be\u0010\u0003J\u000f\u0010f\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bf\u0010\u0003J%\u0010j\u001a\u00020\u00042\f\u0010h\u001a\b\u0012\u0004\u0012\u00020g0@2\u0006\u0010i\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bj\u0010kJ\u001f\u0010n\u001a\u00020\t2\u0006\u0010l\u001a\u00020g2\u0006\u0010m\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bn\u0010oJ\u0017\u0010p\u001a\u00020\t2\u0006\u0010l\u001a\u00020gH\u0002\u00a2\u0006\u0004\bp\u0010qJ?\u0010r\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020g2\u0006\u0010m\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010K\u001a\u00020\t2\u0006\u00108\u001a\u00020\tH\u0002\u00a2\u0006\u0004\br\u0010sJA\u0010v\u001a\u00020\u000f2\b\u0010t\u001a\u0004\u0018\u00010g2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010K\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u0010u\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bv\u0010wJ?\u0010y\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020g2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010K\u001a\u00020\t2\u0006\u0010x\u001a\u00020\t2\u0006\u00108\u001a\u00020\tH\u0002\u00a2\u0006\u0004\by\u0010zJ/\u0010|\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u0010{\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b|\u0010}J/\u0010~\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020g2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u00108\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b~\u0010\u007fJ:\u0010\u0080\u0001\u001a\u00020\u000f2\u0006\u0010l\u001a\u00020g2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u00108\u001a\u00020\t2\u0006\u0010u\u001a\u00020\u0004H\u0002\u00a2\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J9\u0010\u0083\u0001\u001a\u00020\u000f2\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020U0@2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010=\u001a\u00020\fH\u0002\u00a2\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J)\u0010\u0086\u0001\u001a\u00020\u000f2\u0007\u0010\u0085\u0001\u001a\u00020\t2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020g0@H\u0002\u00a2\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J'\u0010\u0088\u0001\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t2\u0006\u0010b\u001a\u00020\f\u00a2\u0006\u0005\b\u0088\u0001\u0010dJ%\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0002\u00a2\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001J\u001a\u0010\u008c\u0001\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u0015H\u0002\u00a2\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J\u0011\u0010\u008e\u0001\u001a\u00020\u000fH\u0002\u00a2\u0006\u0005\b\u008e\u0001\u0010\u0003J\u001a\u0010\u008f\u0001\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u0015H\u0002\u00a2\u0006\u0006\b\u008f\u0001\u0010\u008d\u0001J\u0011\u0010\u0090\u0001\u001a\u00020\u000fH\u0002\u00a2\u0006\u0005\b\u0090\u0001\u0010\u0003J\u0018\u0010\u0091\u0001\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020\f\u00a2\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J\u001a\u0010\u0095\u0001\u001a\u00020\u000f2\b\u0010\u0094\u0001\u001a\u00030\u0093\u0001\u00a2\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J\u001a\u0010\u0099\u0001\u001a\u00020\u00042\b\u0010\u0098\u0001\u001a\u00030\u0097\u0001\u00a2\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u001a\u0010\u009c\u0001\u001a\u00020\u00042\b\u0010\u0098\u0001\u001a\u00030\u009b\u0001\u00a2\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J\u0011\u0010\u009e\u0001\u001a\u00020\u000fH\u0002\u00a2\u0006\u0005\b\u009e\u0001\u0010\u0003R\u0018\u0010\u00a0\u0001\u001a\u00030\u009f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u00a1\u0001R\u0018\u0010\u00a3\u0001\u001a\u00030\u00a2\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u00a4\u0001R\u0018\u0010\u00a6\u0001\u001a\u00030\u00a5\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u00a7\u0001R\u001e\u0010\u00a9\u0001\u001a\t\u0012\u0004\u0012\u00020_0\u00a8\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a9\u0001\u0010\u00aa\u0001R\u001e\u0010«\u0001\u001a\t\u0012\u0004\u0012\u00020_0\u00a8\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00aa\u0001R\u0017\u0010\u00ac\u0001\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u00ad\u0001R\u0019\u0010\u00ae\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00af\u0001R\u0019\u0010\u00b0\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u00b1\u0001R\u0019\u0010\u00b2\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00b1\u0001R\u0019\u0010\u00b3\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00af\u0001R\u0019\u0010\u00b4\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00b1\u0001R\u0019\u0010\u00b5\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b5\u0001\u0010\u00b1\u0001R\u0019\u0010\u00b6\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u00b1\u0001R\u0019\u0010\u00b7\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00b1\u0001R\u0019\u0010\u00b8\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b8\u0001\u0010\u00b1\u0001R\u0019\u0010\u00b9\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b9\u0001\u0010\u00b1\u0001R*\u0010»\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0@0\u00ba\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b»\u0001\u0010\u00bc\u0001R$\u0010\u00bd\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020U0\u00ba\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00bd\u0001\u0010\u00bc\u0001R$\u0010\u00be\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00ba\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00be\u0001\u0010\u00bc\u0001R\u001f\u0010\u00bf\u0001\u001a\n\u0012\u0005\u0012\u00030\u0089\u00010\u00a8\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00bf\u0001\u0010\u00aa\u0001R\u001e\u0010\u00c1\u0001\u001a\t\u0012\u0004\u0012\u00020\u00150\u00c0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00c1\u0001\u0010\u00c2\u0001R\u0019\u0010\u00c3\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c3\u0001\u0010\u00b1\u0001R\u0019\u0010\u00c4\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c4\u0001\u0010\u00af\u0001R\u0019\u0010\u00c5\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c5\u0001\u0010\u00b1\u0001R\u0019\u0010\u00c6\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c6\u0001\u0010\u00af\u0001R\u0019\u0010\u00c7\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c7\u0001\u0010\u00b1\u0001R\u0019\u0010\u00c8\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c8\u0001\u0010\u00b1\u0001R\u0019\u0010\u00c9\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c9\u0001\u0010\u00b1\u0001R\u0019\u0010\u00ca\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ca\u0001\u0010\u00b1\u0001R\u0019\u0010\u0095\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u00b1\u0001R\u0019\u0010\u00cb\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cb\u0001\u0010\u00b1\u0001R\u0019\u0010\u00cc\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cc\u0001\u0010\u00cd\u0001R\u0019\u0010\u00ce\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ce\u0001\u0010\u00af\u0001R\u0019\u0010\u00cf\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cf\u0001\u0010\u00cd\u0001R\u0019\u0010\u00d0\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d0\u0001\u0010\u00b1\u0001R\u0019\u0010\u00d1\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d1\u0001\u0010\u00b1\u0001R\u0019\u0010\u00d2\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d2\u0001\u0010\u00af\u0001R\u0019\u0010\u00d3\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d3\u0001\u0010\u00b1\u0001R\u0019\u0010\u00d4\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d4\u0001\u0010\u00b1\u0001R\u0019\u0010\u00d5\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d5\u0001\u0010\u00cd\u0001R\u0019\u0010\u00d6\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d6\u0001\u0010\u00b1\u0001R\u0019\u0010\u00d7\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d7\u0001\u0010\u00b1\u0001R\u0019\u0010\u00d8\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d8\u0001\u0010\u00b1\u0001R\u0019\u0010\u00d9\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d9\u0001\u0010\u00b1\u0001R\u0018\u0010\u00da\u0001\u001a\u00030\u009f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00da\u0001\u0010\u00a1\u0001R\u0018\u0010\u00db\u0001\u001a\u00030\u009f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00db\u0001\u0010\u00a1\u0001R\u0017\u0010\u00dc\u0001\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00dc\u0001\u0010\u00ad\u0001R\u0017\u0010\u00dd\u0001\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00dd\u0001\u0010\u00ad\u0001R$\u0010\u00de\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\t0\u00ba\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00de\u0001\u0010\u00bc\u0001R\u0019\u0010\u00df\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00df\u0001\u0010\u00b1\u0001R\u0019\u0010\u00e0\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e0\u0001\u0010\u00b1\u0001R\u0019\u0010\u00e1\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e1\u0001\u0010\u00b1\u0001R\u0019\u0010\u00e2\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e2\u0001\u0010\u00b1\u0001R\u0019\u0010\u00e3\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e3\u0001\u0010\u00af\u0001R\u0019\u0010\u00e4\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e4\u0001\u0010\u00af\u0001R\u0019\u0010\u00e5\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e5\u0001\u0010\u00cd\u0001R\u0019\u0010\u00e6\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e6\u0001\u0010\u00b1\u0001R\u0019\u0010\u00e7\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e7\u0001\u0010\u00b1\u0001R\u0019\u0010\u00e8\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e8\u0001\u0010\u00b1\u0001R\u0019\u0010\u00e9\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e9\u0001\u0010\u00b1\u0001R\u0019\u0010\u00ea\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ea\u0001\u0010\u00b1\u0001R\u0019\u0010\u00eb\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00eb\u0001\u0010\u00cd\u0001R\u0019\u0010\u00ec\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ec\u0001\u0010\u00b1\u0001R\u0019\u0010\u00ed\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ed\u0001\u0010\u00b1\u0001R\u0019\u0010\u00ee\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ee\u0001\u0010\u00b1\u0001R\u0019\u0010\u00ef\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ef\u0001\u0010\u00b1\u0001R\u0019\u0010\u00f0\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f0\u0001\u0010\u00b1\u0001R\u0019\u0010\u00f1\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f1\u0001\u0010\u00b1\u0001R\u0019\u0010\u00f2\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f2\u0001\u0010\u00b1\u0001R\u0019\u0010\u00f3\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f3\u0001\u0010\u00b1\u0001R\u0019\u0010\u00f4\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f4\u0001\u0010\u00cd\u0001R\u0019\u0010\u00f5\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00f5\u0001\u0010\u00cd\u0001\u00a8\u0006\u00fa\u0001"}, d2={"Lrtx/kimiko/api/ui/MessengerPanel;", "", "<init>", "()V", "", "isOpen", "()Z", "isVisible", "isTyping", "", "shareScroll", "()F", "", "shareSelectedCount", "()I", "", "toggle", "close", "closeSilent", "detachForGuiClose", "resetInteraction", "", "msgId", "x", "y", "openMenu", "(JFF)V", "closeMenu", "openConfirm", "(FF)V", "closeConfirm", "mx", "my", "contains", "(FF)Z", "blurPhase", "", "dst", "offset", "writeBlurRect", "([FI)Z", "Lnet/minecraft/DrawContext;", "graphics", "panelX", "panelY", "panelW", "panelH", "alpha", "mouseX", "mouseY", "dt", "render", "(Lnet/minecraft/DrawContext;FFFFFFFF)V", "inputX", "inputY", "inputW", "a", "rate", "renderMic", "(FFFFFFF)V", "size", "color", "drawMic", "(FFFI)V", "", "Lrtx/kimiko/api/ui/MessengerPanel$SelfRow;", "selfRows", "()Ljava/util/List;", "renderSelfMenu", "(FFFF)V", "updateSelectionAnim", "(F)V", "id", "selectionT", "(J)F", "w", "renderEditBar", "renderReplyBar", "t", "renderMenu", "(FFFFF)V", "renderConfirm", "rx", "ry", "rw", "", "label", "hover", "danger", "drawRowLabel", "(FFFLjava/lang/String;FFZ)V", "panelRadius", "renderHeader", "drawEmptyChip", "(FFF)F", "Lrtx/kimiko/api/ui/MessengerPanel$SpotRect;", "nickAt", "(FF)Lrtx/kimiko/api/ui/MessengerPanel$SpotRect;", "button", "handleSelfMenuClick", "(FFI)Z", "startKeyEntry", "stopKeyEntry", "Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;", "msgs", "i", "isGrouped", "(Ljava/util/List;I)Z", "msg", "grouped", "blockHeight", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;Z)F", "bubbleHeight", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;)F", "renderMessage", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;ZFFFF)V", "quoted", "mine", "drawQuote", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;FFFFZ)V", "h", "drawReplyHighlight", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;FFFFF)V", "alignRight", "drawEditedMark", "(FFFZ)V", "renderAvatar", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;FFF)V", "drawVoice", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;FFFZ)V", "lines", "drawLines", "(Ljava/util/List;FFI)V", "listW", "ensureWrapCache", "(FLjava/util/List;)V", "mouseClicked", "Lrtx/kimiko/api/ui/MessengerPanel$HitRect;", "hitAt", "(FF)Lrtx/kimiko/api/ui/MessengerPanel$HitRect;", "startEditing", "(J)V", "cancelEditing", "startReply", "cancelReply", "mouseReleased", "(I)V", "", "vertical", "scroll", "(D)V", "Lnet/minecraft/KeyInput;", "event", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "Lnet/minecraft/CharInput;", "charTyped", "(Lnet/minecraft/CharInput;)Z", "submitInput", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "Lrtx/kimiko/api/ui/module/SearchField;", "input", "Lrtx/kimiko/api/ui/module/SearchField;", "Lrtx/kimiko/api/ui/messenger/UserActionsPopup;", "userPopup", "Lrtx/kimiko/api/ui/messenger/UserActionsPopup;", "", "nickRects", "Ljava/util/List;", "voiceRects", "selfHover", "[F", "selfMenuOpen", "Z", "selfMenuX", "F", "selfMenuY", "keyEntry", "micX", "micY", "micHover", "selfChipX", "selfChipY", "selfChipW", "", "wrapCache", "Ljava/util/Map;", "wrapText", "appearAt", "hitRects", "", "selected", "Ljava/util/Set;", "wrapCacheWidth", "appearPrimed", "lastContentH", "open", "px", "py", "pw", "ph", "scrollVisual", "seenVersion", "J", "menuOpen", "menuMsgId", "menuX", "menuY", "confirmOpen", "confirmX", "confirmY", "editingId", "deleteBtnX", "deleteBtnY", "deleteBtnW", "deleteBtnH", "menuAnim", "confirmAnim", "menuHover", "confirmHover", "selAnim", "deleteBtnT", "deleteBtnHover", "editBarT", "editCancelHover", "dragSelecting", "dragAdd", "dragArmId", "dragStartX", "dragStartY", "editCancelX", "editCancelY", "editCancelS", "replyId", "replyBarT", "replyCancelHover", "replyCancelX", "replyCancelY", "replyCancelS", "sendX", "sendY", "sendHover", "lastClickId", "lastClickMs", "Companion", "HitRect", "SpotRect", "SelfRow", "rtx.kimiko:kimiko"})
public final class MessengerPanel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Decelerate anim = new Decelerate();
    @NotNull
    private final SearchField input = new SearchField().placeholder("Сообщение...").icon("").rightPadding(27.0f);
    @NotNull
    private final UserActionsPopup userPopup = new UserActionsPopup();
    @NotNull
    private final List<SpotRect> nickRects = new ArrayList();
    @NotNull
    private final List<SpotRect> voiceRects = new ArrayList();
    @NotNull
    private final float[] selfHover = new float[20];
    private boolean selfMenuOpen;
    private float selfMenuX;
    private float selfMenuY;
    private boolean keyEntry;
    private float micX;
    private float micY;
    private float micHover;
    private float selfChipX;
    private float selfChipY;
    private float selfChipW;
    @NotNull
    private final Map<Long, List<String>> wrapCache = new HashMap();
    @NotNull
    private final Map<Long, String> wrapText = new HashMap();
    @NotNull
    private final Map<Long, Long> appearAt = new HashMap();
    @NotNull
    private final List<HitRect> hitRects = new ArrayList();
    @NotNull
    private final Set<Long> selected = new LinkedHashSet();
    private float wrapCacheWidth = -1.0f;
    private boolean appearPrimed;
    private float lastContentH;
    private boolean open;
    private float px;
    private float py;
    private float pw;
    private float ph;
    private float scroll;
    private float scrollVisual;
    private long seenVersion = -1L;
    private boolean menuOpen;
    private long menuMsgId;
    private float menuX;
    private float menuY;
    private boolean confirmOpen;
    private float confirmX;
    private float confirmY;
    private long editingId = -1L;
    private float deleteBtnX;
    private float deleteBtnY;
    private float deleteBtnW;
    private float deleteBtnH;
    @NotNull
    private final Decelerate menuAnim = new Decelerate();
    @NotNull
    private final Decelerate confirmAnim = new Decelerate();
    @NotNull
    private final float[] menuHover = new float[2];
    @NotNull
    private final float[] confirmHover = new float[2];
    @NotNull
    private final Map<Long, Float> selAnim = new HashMap();
    private float deleteBtnT;
    private float deleteBtnHover;
    private float editBarT;
    private float editCancelHover;
    private boolean dragSelecting;
    private boolean dragAdd;
    private long dragArmId = -1L;
    private float dragStartX;
    private float dragStartY;
    private float editCancelX;
    private float editCancelY;
    private float editCancelS;
    private long replyId = -1L;
    private float replyBarT;
    private float replyCancelHover;
    private float replyCancelX;
    private float replyCancelY;
    private float replyCancelS;
    private float sendX;
    private float sendY;
    private float sendHover;
    private long lastClickId = -1L;
    private long lastClickMs;
    private static final float WIDTH = 158.0f;
    private static final float GAP_FROM_PANEL = 8.0f;
    private static final float RADIUS = 12.0f;
    private static final float PAD = 7.0f;
    private static final float HEADER_H = 22.0f;
    private static final float INPUT_H = 13.0f;
    private static final float AVATAR = 11.0f;
    private static final float MSG_SIZE = 5.6f;
    private static final float NICK_SIZE = 5.6f;
    private static final float TIME_SIZE = 4.6f;
    private static final float BUBBLE_PAD_X = 4.5f;
    private static final float BUBBLE_PAD_Y = 3.5f;
    private static final float LINE_H = 7.8f;
    private static final long GROUP_WINDOW_MS = 90000L;
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");
    @NotNull
    private static final List<String> EMPTY_LINES = List.of("");
    private static final float MIC_SIZE = 7.0f;
    private static final float SELF_MENU_W = 76.0f;
    private static final int SELF_PREFIX = 0;
    private static final int SELF_AUTOPLAY = 1;
    private static final int SELF_KEY = 2;
    @NotNull
    private static final String MARK = "\u2713";
    private static final float MENU_W = 66.0f;
    private static final float MENU_ROW_H = 12.0f;
    private static final float CONFIRM_W = 88.0f;
    private static final float EDIT_BAR_H = 12.0f;
    private static final float REPLY_BAR_H = 17.0f;
    private static final float QUOTE_H = 11.5f;
    private static final float QUOTE_GAP = 2.5f;
    private static final float QUOTE_BAR_W = 1.4f;
    private static final float QUOTE_TEXT_X = 4.4f;
    private static final float QUOTE_SIZE = 4.8f;
    private static final long DOUBLE_CLICK_MS = 330L;
    private static final float EDITED_H = 5.5f;
    private static final float PAD_POPUP = 6.0f;
    private static final float GAP_POPUP = 4.0f;
    private static final float CONFIRM_TITLE_H = 13.0f;
    private static final float ROW_RADIUS = 3.0f;
    private static final float ROW_TEXT_SIZE = 6.0f;
    @NotNull
    private static final String ICON_TRASH = "A";
    @NotNull
    private static final String CHAT_GLYPH = "a";
    @NotNull
    private static final String SEND_GLYPH = "A";
    private static final float SEND_SIZE = 7.0f;

    public MessengerPanel() {
        this.anim.setMs(220);
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - (long)10000);
        MessengerPanel.Companion.snapClosed(this.menuAnim, 180);
        MessengerPanel.Companion.snapClosed(this.confirmAnim, 180);
    }

    public final boolean isOpen() {
        return this.open;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isVisible() {
        if (this.open) return true;
        Double d = this.anim.getOutput();
        double d2 = d != null ? d : 0.0;
        if (!((float)d2 > 0.01f)) return false;
        return true;
    }

    public final boolean isTyping() {
        return this.open && this.input.isTyping();
    }

    public final float shareScroll() {
        return this.scrollVisual;
    }

    public final int shareSelectedCount() {
        return this.selected.size();
    }

    public final void toggle() {
        if (this.open) {
            this.close();
        } else {
            this.open = true;
            MessengerClient.INSTANCE.start();
            MessengerClient.INSTANCE.setChatOpen(true);
            this.anim.setDirection(Direction.FORWARDS);
            this.anim.counter.resetCounter();
            Sounds.play("module_settings_open");
        }
    }

    public final void close() {
        if (!this.open) {
            return;
        }
        this.open = false;
        MessengerClient.INSTANCE.setChatOpen(false);
        this.resetInteraction();
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.resetCounter();
    }

    public final void closeSilent() {
        this.open = false;
        MessengerClient.INSTANCE.setChatOpen(false);
        this.resetInteraction();
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - (long)10000);
    }

    public final void detachForGuiClose() {
        if (!this.open) {
            return;
        }
        MessengerClient.INSTANCE.setChatOpen(false);
        this.resetInteraction();
    }

    private final void resetInteraction() {
        this.appearPrimed = false;
        this.userPopup.close();
        this.selfMenuOpen = false;
        this.stopKeyEntry();
        VoiceNoteRecorder.cancel();
        VoiceNotePlayer.stop();
        this.menuOpen = false;
        this.confirmOpen = false;
        this.editingId = -1L;
        this.selected.clear();
        this.selAnim.clear();
        this.dragSelecting = false;
        this.dragArmId = -1L;
        this.deleteBtnT = 0.0f;
        this.editBarT = 0.0f;
        this.replyId = -1L;
        this.replyBarT = 0.0f;
        this.replyCancelS = 0.0f;
        this.lastClickId = -1L;
        MessengerPanel.Companion.snapClosed(this.menuAnim, 180);
        MessengerPanel.Companion.snapClosed(this.confirmAnim, 180);
        this.input.setText("");
        this.input.blur();
    }

    private final void openMenu(long msgId, float x, float y) {
        this.menuMsgId = msgId;
        this.menuX = x;
        this.menuY = y;
        this.menuOpen = true;
        this.menuHover[0] = 0.0f;
        this.menuHover[1] = 0.0f;
        this.menuAnim.setDirection(Direction.FORWARDS);
        this.menuAnim.counter.resetCounter();
        Sounds.play("module_settings_open");
    }

    private final void closeMenu() {
        if (!this.menuOpen) {
            return;
        }
        this.menuOpen = false;
        this.menuAnim.setDirection(Direction.BACKWARDS);
        this.menuAnim.counter.resetCounter();
    }

    private final void openConfirm(float x, float y) {
        this.confirmX = x;
        this.confirmY = y;
        this.confirmOpen = true;
        this.confirmHover[0] = 0.0f;
        this.confirmHover[1] = 0.0f;
        this.confirmAnim.setDirection(Direction.FORWARDS);
        this.confirmAnim.counter.resetCounter();
        Sounds.play("module_settings_open");
    }

    private final void closeConfirm() {
        if (!this.confirmOpen) {
            return;
        }
        this.confirmOpen = false;
        this.confirmAnim.setDirection(Direction.BACKWARDS);
        this.confirmAnim.counter.resetCounter();
    }

    public final boolean contains(float mx, float my) {
        return this.isVisible() && mx >= this.px && mx <= this.px + this.pw && my >= this.py && my <= this.py + this.ph;
    }

    public final float blurPhase() {
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            return 0.0f;
        }
        return Math.max(0.0f, Math.min(1.0f, 1.0f - t));
    }

    public final boolean writeBlurRect(@Nullable float[] dst, int offset) {
        if (dst == null || offset + 6 > dst.length) {
            return false;
        }
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            return false;
        }
        dst[offset] = this.px;
        dst[offset + 1] = this.py + (1.0f - t) * 4.0f;
        dst[offset + 2] = this.pw;
        dst[offset + 3] = this.ph;
        dst[offset + 4] = 1.0f;
        dst[offset + 5] = Math.max(0.0f, Math.min(1.0f, 1.0f - t));
        return true;
    }

    public final void render(@NotNull DrawContext graphics, float panelX, float panelY, float panelW, float panelH, float alpha, float mouseX, float mouseY, float dt) {
        HitRect hit;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f && !this.open) {
            return;
        }
        if (this.open) {
            MessengerClient.INSTANCE.setChatOpen(true);
        }
        float a = t * alpha;
        this.pw = 158.0f;
        this.ph = panelH;
        this.px = panelX + panelW + 8.0f;
        this.py = panelY;
        float drawX = this.px;
        float drawY = this.py + (1.0f - t) * 4.0f;
        float panelRadius = RenderHelper.effectiveCornerRadius(12.0f, this.pw, this.ph);
        RectUtil.drawClientRectFixedRadius(drawX, drawY, this.pw, this.ph, panelRadius, a, 0.0f);
        Render2D.outline(drawX, drawY, this.pw, this.ph, panelRadius, 0.6f, ColorEngine.multAlpha(0x18FFFFFF, a));
        float rate = 1.0f - (float)Math.exp(-dt * 14.0f);
        this.editBarT += ((this.editingId != -1L ? 1.0f : 0.0f) - this.editBarT) * rate;
        this.replyBarT += ((this.replyId != -1L ? 1.0f : 0.0f) - this.replyBarT) * rate;
        this.deleteBtnT += ((this.selected.isEmpty() ? 0.0f : 1.0f) - this.deleteBtnT) * rate;
        this.updateSelectionAnim(rate);
        this.renderHeader(drawX, drawY, a, mouseX, mouseY, rate, panelRadius);
        float listX = drawX + 7.0f;
        float listW = this.pw - 14.0f;
        float listTop = drawY + 22.0f;
        float listBottom = drawY + this.ph - 7.0f - 13.0f - 5.0f - 15.0f * this.editBarT - 20.0f * this.replyBarT;
        float listH = listBottom - listTop;
        List<MessengerClient.Message> msgs = MessengerClient.INSTANCE.snapshot();
        this.ensureWrapCache(listW, msgs);
        float contentH = 0.0f;
        float[] heights = new float[msgs.size()];
        int n = ((Collection)msgs).size();
        for (int i = 0; i < n; ++i) {
            boolean grouped = this.isGrouped(msgs, i);
            heights[i] = this.blockHeight(msgs.get(i), grouped);
            contentH += heights[i];
        }
        float maxScroll = Math.max(0.0f, contentH - listH);
        long ver = MessengerClient.INSTANCE.version();
        if (ver != this.seenVersion) {
            if (this.scroll <= 12.0f) {
                this.scroll = 0.0f;
            }
            this.seenVersion = ver;
        }
        if (this.appearPrimed && contentH > this.lastContentH && this.lastContentH > 0.0f && this.scroll <= 12.0f) {
            this.scrollVisual += contentH - this.lastContentH;
        }
        this.lastContentH = contentH;
        this.scroll = Math.min(this.scroll, maxScroll);
        this.scrollVisual += (this.scroll - this.scrollVisual) * Math.min(1.0f, dt * 9.0f);
        long nowMs = System.currentTimeMillis();
        this.hitRects.clear();
        this.nickRects.clear();
        this.voiceRects.clear();
        Render2D.pushScissor(graphics, drawX, listTop, this.pw, listH + (float)5);
        float cursorY = MessengerPanel.Companion.snapPx(listBottom - contentH + this.scrollVisual);
        int n2 = ((Collection)msgs).size();
        for (int i = 0; i < n2; ++i) {
            float blockH = heights[i];
            if (cursorY + blockH >= listTop - 20.0f && cursorY <= listBottom + 20.0f) {
                float ap;
                MessengerClient.Message msg = msgs.get(i);
                this.hitRects.add(new HitRect(msg.id(), listX, cursorY, listW, blockH - 4.0f, msg.mine()));
                long bornAt = ((Number)this.appearAt.getOrDefault(msg.id(), 0L)).longValue();
                float f = ap = bornAt == 0L ? 1.0f : MessengerPanel.Companion.ease(Math.min((float)(nowMs - bornAt) / 240.0f, 1.0f));
                if (ap > 0.01f) {
                    this.renderMessage(msg, this.isGrouped(msgs, i), listX, cursorY + (1.0f - ap) * 6.0f, listW, a * ap);
                }
            }
            cursorY += blockH;
        }
        if (msgs.isEmpty()) {
            String empty = MessengerClient.INSTANCE.isConnected() ? I18n.tr("Пока пусто...") : I18n.tr("Подключение...");
            float ew = Fonts.MEDIUM.width(empty, 6.0f);
            Fonts.MEDIUM.draw(empty, drawX + (this.pw - ew) * 0.5f, listTop + listH * 0.5f - 3.0f, 6.0f, ColorEngine.multAlpha(-1, 0.35f * a));
        }
        Render2D.popScissor(graphics);
        if (this.dragArmId != -1L && !this.dragSelecting && Math.abs(mouseX - this.dragStartX) + Math.abs(mouseY - this.dragStartY) > 3.0f) {
            this.dragSelecting = true;
            this.dragAdd = true;
            this.selected.add(this.dragArmId);
        }
        if (this.dragSelecting && (hit = this.hitAt(mouseX, mouseY)) != null && hit.getMine()) {
            boolean bl = this.dragAdd ? this.selected.add(hit.getId()) : this.selected.remove(hit.getId());
        }
        float barY = listBottom + 4.0f;
        if (this.editBarT > 0.01f) {
            this.renderEditBar(drawX + 7.0f, barY, this.pw - 14.0f, a * this.editBarT, mouseX, mouseY, rate);
            barY += 15.0f * this.editBarT;
        } else {
            this.editCancelS = 0.0f;
        }
        if (this.replyBarT > 0.01f) {
            this.renderReplyBar(drawX + 7.0f, barY, this.pw - 14.0f, a * this.replyBarT, mouseX, mouseY, rate);
        } else {
            this.replyCancelS = 0.0f;
        }
        float inputX = drawX + 7.0f;
        float inputY = drawY + this.ph - 7.0f - 13.0f;
        float inputW = this.pw - 14.0f;
        this.input.render(graphics, inputX, inputY, inputW, 13.0f, a, mouseX, mouseY, dt);
        this.sendX = inputX + inputW - 7.0f - 5.0f;
        this.sendY = inputY + 3.0f + 0.2f;
        boolean sendHovered = mouseX >= this.sendX - 2.0f && mouseX <= this.sendX + 7.0f + 2.0f && mouseY >= this.sendY - 2.0f && mouseY <= this.sendY + 7.0f + 2.0f;
        this.sendHover += ((sendHovered ? 1.0f : 0.0f) - this.sendHover) * rate;
        int sendColor = ColorEngine.lerpColor(ColorEngine.multAlpha(-1, 0.48f * a), ClientAccent.accentSoftAt(235.0f * a, this.sendX, this.sendY), this.sendHover);
        Fonts.SEND.msdf("A", this.sendX, this.sendY, 7.0f, sendColor);
        this.renderMic(inputX, inputY, inputW, a, mouseX, mouseY, rate);
        if (this.userPopup.isVisible()) {
            this.userPopup.clampTo(drawX, drawY, this.pw, this.ph);
            this.userPopup.render(a, mouseX, mouseY, rate);
        }
        if (this.selfMenuOpen) {
            this.renderSelfMenu(a, mouseX, mouseY, rate);
        }
        Double d2 = this.menuAnim.getOutput();
        float menuT = (float)(d2 != null ? d2 : 0.0);
        if (menuT > 0.01f) {
            this.renderMenu(a, menuT, mouseX, mouseY, rate);
        }
        Double d3 = this.confirmAnim.getOutput();
        float confirmT = (float)(d3 != null ? d3 : 0.0);
        if (confirmT > 0.01f) {
            this.renderConfirm(a, confirmT, mouseX, mouseY, rate);
        }
    }

    private final void renderMic(float inputX, float inputY, float inputW, float a, float mouseX, float mouseY, float rate) {
        this.micX = this.sendX - 7.0f - 6.0f;
        this.micY = inputY + 3.0f;
        boolean recording = VoiceNoteRecorder.isRecording();
        if (recording) {
            float overlayW = this.micX - inputX - 4.0f;
            RenderHelper.drawPanelBg(inputX, inputY, overlayW, 13.0f, 6.5f, a);
            float midY = inputY + 6.5f;
            float pulse = 0.5f + 0.5f * (float)Math.sin((double)System.currentTimeMillis() / 140.0);
            Render2D.circle(inputX + 6.0f, midY, 2.1f, ColorEngine.multAlpha(-44462, (0.45f + 0.55f * pulse) * a));
            int elapsed = (int)VoiceNoteRecorder.elapsedMs();
            String timer = VoiceNote.formatDuration(elapsed);
            Fonts.MEDIUM.draw(timer, inputX + 11.0f, midY - 2.6f, 5.2f, ColorEngine.multAlpha(-1, 0.9f * a));
            float barX = inputX + 11.0f + Fonts.MEDIUM.width(timer, 5.2f) + 5.0f;
            float barW = Math.max(6.0f, inputX + overlayW - 7.0f - barX);
            Render2D.rect(barX, midY - 1.1f, barW, 2.2f, 1.1f, ColorEngine.multAlpha(0x30FFFFFF, a));
            Render2D.rect(barX, midY - 1.1f, barW * Math.min(1.0f, (float)elapsed / (float)60000), 2.2f, 1.1f, ColorEngine.multAlpha(-44462, 0.85f * a));
            Render2D.rect(barX, midY + 1.6f, barW * Math.min(1.0f, VoiceNoteRecorder.level() * 1.7f), 1.2f, 0.6f, ColorEngine.multAlpha(ClientAccent.accentAt(255.0f, barX, midY), 0.75f * a));
        }
        boolean hovered = mouseX >= this.micX - 2.0f && mouseX <= this.micX + 7.0f + 2.0f && mouseY >= this.micY - 2.0f && mouseY <= this.micY + 7.0f + 2.0f;
        this.micHover += ((hovered ? 1.0f : 0.0f) - this.micHover) * rate;
        int micColor = recording ? ColorEngine.multAlpha(-44462, a) : ColorEngine.lerpColor(ColorEngine.multAlpha(-1, 0.48f * a), ClientAccent.accentSoftAt(235.0f * a, this.micX, this.micY), this.micHover);
        this.drawMic(this.micX, this.micY, 7.0f, micColor);
    }

    private final void drawMic(float x, float y, float size, int color) {
        float capsuleW = size * 0.44f;
        float capsuleH = size * 0.58f;
        float cx = x + size * 0.5f;
        Render2D.rect(cx - capsuleW * 0.5f, y, capsuleW, capsuleH, capsuleW * 0.5f, color);
        Render2D.rect(cx - capsuleW * 0.72f, y + capsuleH * 0.55f, 0.8f, capsuleH * 0.42f, 0.4f, color);
        Render2D.rect(cx + capsuleW * 0.72f - 0.8f, y + capsuleH * 0.55f, 0.8f, capsuleH * 0.42f, 0.4f, color);
        Render2D.rect(cx - capsuleW * 0.72f, y + capsuleH * 0.94f, capsuleW * 1.44f, 0.8f, 0.4f, color);
        Render2D.rect(cx - 0.4f, y + capsuleH * 0.97f, 0.8f, size * 0.2f, 0.4f, color);
        Render2D.rect(cx - capsuleW * 0.6f, y + size - 0.8f, capsuleW * 1.2f, 0.8f, 0.4f, color);
    }

    private final List<SelfRow> selfRows() {
        MessengerClient.SelfState state = MessengerClient.INSTANCE.state();
        ArrayList<SelfRow> list = new ArrayList<SelfRow>();
        list.add(new SelfRow(I18n.tr("Без префикса"), "", 0));
        for (String id : state.prefixes()) {
            ChatPrefixes.Entry entry = ChatPrefixes.byId(id);
            if (entry == null) continue;
            list.add(new SelfRow(entry.label(), id, 0));
        }
        list.add(new SelfRow(VoiceNotePlayer.autoPlay() ? I18n.tr("Автовоспроизведение: вкл") : I18n.tr("Автовоспроизведение: выкл"), null, 1));
        if (state.needsKey() || !Intrinsics.areEqual((Object)state.role(), (Object)"none")) {
            list.add(new SelfRow(I18n.tr("Ключ модерации"), null, 2));
        }
        return list;
    }

    private final void renderSelfMenu(float alpha, float mouseX, float mouseY, float rate) {
        List<SelfRow> rows = this.selfRows();
        float w = 76.0f;
        float h = 12.0f + (float)rows.size() * 16.0f - 4.0f;
        this.selfMenuX = Math.min(this.selfMenuX, this.px + this.pw - w - 3.0f);
        this.selfMenuY = Math.min(this.selfMenuY, this.py + this.ph - h - 3.0f);
        RenderHelper.drawDropBackground(this.selfMenuX, this.selfMenuY, w, h, alpha);
        MessengerClient.SelfState state = MessengerClient.INSTANCE.state();
        int n = ((Collection)rows).size();
        for (int i = 0; i < n && i < this.selfHover.length; ++i) {
            ChatPrefixes.Entry entry;
            SelfRow row = rows.get(i);
            float rx = this.selfMenuX + 6.0f;
            float ry = this.selfMenuY + 6.0f + (float)i * 16.0f;
            float rw = w - 12.0f;
            boolean hovered = this.selfMenuOpen && mouseX >= rx && mouseX <= rx + rw && mouseY >= ry && mouseY <= ry + 12.0f;
            float[] fArray = this.selfHover;
            int n2 = i;
            fArray[n2] = fArray[n2] + ((hovered ? 1.0f : 0.0f) - this.selfHover[i]) * rate;
            RenderHelper.drawPanelBg(rx, ry, rw, 12.0f, 3.0f, alpha);
            if (this.selfHover[i] > 0.01f) {
                Render2D.rect(rx, ry, rw, 12.0f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(210.0f * this.selfHover[i], rx + rw * 0.5f, ry + 6.0f), alpha));
            }
            ChatPrefixes.Entry entry2 = entry = row.getAction() == 0 ? ChatPrefixes.byId(row.getPrefixId()) : null;
            if (entry != null) {
                ChatPrefixBadge.draw(entry, rx + 5.0f, ry + 2.5f, alpha);
            } else {
                Fonts.MEDIUM.draw(row.getLabel(), rx + 5.0f, ry + 2.5f, 6.0f, ColorEngine.lerpColor(ColorEngine.multAlpha(-1, 0.65f * alpha), ColorEngine.multAlpha(-15856114, alpha), this.selfHover[i]));
            }
            if (row.getAction() != 0 || !Intrinsics.areEqual((Object)row.getPrefixId(), (Object)state.prefix())) continue;
            float markW = Fonts.MEDIUM.width(MARK, 5.0f);
            Fonts.MEDIUM.draw(MARK, rx + rw - markW - 5.0f, ry + 3.0f, 5.0f, ColorEngine.multAlpha(-11019894, 0.95f * alpha));
        }
    }

    private final void updateSelectionAnim(float rate) {
        Iterator<Long> iterator = this.selected.iterator();
        while (iterator.hasNext()) {
            long id = ((Number)iterator.next()).longValue();
            this.selAnim.putIfAbsent(id, Float.valueOf(0.0f));
        }
        Iterator<Map.Entry<Long, Float>> it = this.selAnim.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, Float> entry = it.next();
            float target = this.selected.contains(entry.getKey()) ? 1.0f : 0.0f;
            float value = ((Number)entry.getValue()).floatValue() + (target - ((Number)entry.getValue()).floatValue()) * rate;
            if (target == 0.0f && value < 0.01f) {
                it.remove();
                continue;
            }
            entry.setValue(Float.valueOf(value));
        }
    }

    private final float selectionT(long id) {
        Float value;
        Float f = value = this.selAnim.get(id);
        return f != null ? f.floatValue() : 0.0f;
    }

    private final void renderEditBar(float x, float y, float w, float a, float mouseX, float mouseY, float rate) {
        RenderHelper.drawPanelBg(x, y, w, 12.0f, 3.0f, a);
        Fonts.MEDIUM.draw(I18n.tr("Изменение сообщения"), x + 5.0f, y + 2.5f, 6.0f, ClientAccent.accentSoftAt(220.0f * a, x + 5.0f, y + 6.0f));
        this.editCancelS = 12.0f;
        this.editCancelX = x + w - this.editCancelS;
        this.editCancelY = y;
        boolean hovered = mouseX >= this.editCancelX && mouseX <= this.editCancelX + this.editCancelS && mouseY >= this.editCancelY && mouseY <= this.editCancelY + this.editCancelS;
        this.editCancelHover += ((hovered ? 1.0f : 0.0f) - this.editCancelHover) * rate;
        if (this.editCancelHover > 0.01f) {
            Render2D.rect(this.editCancelX, this.editCancelY, this.editCancelS, this.editCancelS, 3.0f, ColorEngine.multAlpha(0x2EFFFFFF, this.editCancelHover * a));
        }
        String cancel = "\u00d7";
        float cw = Fonts.MEDIUM.width(cancel, 8.0f);
        Fonts.MEDIUM.draw(cancel, this.editCancelX + (this.editCancelS - cw) * 0.5f, this.editCancelY + 1.5f, 8.0f, ColorEngine.multAlpha(-1, (0.55f + 0.4f * this.editCancelHover) * a));
    }

    private final void renderReplyBar(float x, float y, float w, float a, float mouseX, float mouseY, float rate) {
        RenderHelper.drawPanelBg(x, y, w, 17.0f, 3.0f, a);
        this.replyCancelS = 17.0f;
        this.replyCancelX = x + w - this.replyCancelS;
        this.replyCancelY = y;
        boolean hovered = this.replyId != -1L && mouseX >= this.replyCancelX && mouseX <= this.replyCancelX + this.replyCancelS && mouseY >= this.replyCancelY && mouseY <= this.replyCancelY + this.replyCancelS;
        this.replyCancelHover += ((hovered ? 1.0f : 0.0f) - this.replyCancelHover) * rate;
        MessengerClient.Message target = MessengerClient.INSTANCE.byId(this.replyId);
        float barX = x + 4.0f;
        float barY = y + 3.0f;
        float barH = 11.0f;
        Render2D.rect(barX, barY, 1.4f, barH, 0.7f, ColorEngine.multAlpha(ClientAccent.accentAt(255.0f, barX, barY + barH * 0.5f), a));
        float textX = barX + 4.4f;
        float textW = this.replyCancelX - textX - 2.0f;
        Object[] objectArray = new Object[1];
        MessengerClient.Message message = target;
        objectArray[0] = message == null ? I18n.tr("сообщению") : message.user();
        String name = I18n.tr("Ответ %s", objectArray);
        MessengerClient.Message message2 = target;
        String preview = message2 == null ? I18n.tr("недоступно") : String.valueOf(message2.text()).replace((char)'\n', (char)' ');
        Fonts.MEDIUM.draw(MessengerPanel.Companion.clip(name, 4.8f, textW), textX, y + 3.2f, 4.8f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(230.0f, textX, y + 5.0f), a));
        Fonts.MEDIUM.draw(MessengerPanel.Companion.clip(preview, 4.8f, textW), textX, y + 9.4f, 4.8f, ColorEngine.multAlpha(-1, 0.5f * a));
        if (this.replyCancelHover > 0.01f) {
            Render2D.rect(this.replyCancelX, this.replyCancelY, this.replyCancelS, this.replyCancelS, 3.0f, ColorEngine.multAlpha(0x2EFFFFFF, this.replyCancelHover * a));
        }
        String cancel = "\u00d7";
        float cw = Fonts.MEDIUM.width(cancel, 8.0f);
        Fonts.MEDIUM.draw(cancel, this.replyCancelX + (this.replyCancelS - cw) * 0.5f, this.replyCancelY + 4.0f, 8.0f, ColorEngine.multAlpha(-1, (0.55f + 0.4f * this.replyCancelHover) * a));
    }

    private final void renderMenu(float alpha, float t, float mouseX, float mouseY, float rate) {
        float a = alpha * t;
        float drawY = this.menuY + (1.0f - t) * 4.0f;
        float mh = 40.0f;
        RenderHelper.drawDropBackground(this.menuX, drawY, 66.0f, mh, a);
        String[] stringArray = new String[]{I18n.tr("Выбрать"), I18n.tr("Изменить")};
        String[] labels = stringArray;
        for (int i = 0; i < 2; ++i) {
            float ry = drawY + 6.0f + (float)i * 16.0f;
            float rx = this.menuX + 6.0f;
            float rw = 54.0f;
            boolean hovered = this.menuOpen && mouseX >= rx && mouseX <= rx + rw && mouseY >= ry && mouseY <= ry + 12.0f;
            float[] fArray = this.menuHover;
            int n = i;
            fArray[n] = fArray[n] + ((hovered ? 1.0f : 0.0f) - this.menuHover[i]) * rate;
            float hover = this.menuHover[i];
            RenderHelper.drawPanelBg(rx, ry, rw, 12.0f, 3.0f, a);
            if (hover > 0.01f) {
                Render2D.rect(rx, ry, rw, 12.0f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(210.0f * hover, rx + rw * 0.5f, ry + 6.0f), a));
            }
            this.drawRowLabel(rx, ry, rw, labels[i], hover, a, false);
        }
    }

    private final void renderConfirm(float alpha, float t, float mouseX, float mouseY, float rate) {
        float a = alpha * t;
        float drawY = this.confirmY + (1.0f - t) * 4.0f;
        float ch = 53.0f;
        RenderHelper.drawDropBackground(this.confirmX, drawY, 88.0f, ch, a);
        Object[] objectArray = new Object[]{Math.max(1, this.selected.size())};
        String title = I18n.tr("Удалить %d?", objectArray);
        float iconS = 6.0f;
        float iconGap = 3.0f;
        float tw = Fonts.MEDIUM.width(title, 6.0f);
        float titleX = this.confirmX + (88.0f - (iconS + iconGap + tw)) * 0.5f;
        int titleColor = ColorEngine.multAlpha(-1, 0.85f * a);
        MessengerPanel.Companion.drawIconCentered("A", titleX, drawY + 6.0f + (7.0f - iconS) * 0.5f, iconS, iconS, ColorEngine.multAlpha(-2070424, 0.9f * a));
        Fonts.MEDIUM.draw(title, titleX + iconS + iconGap, drawY + 6.0f, 6.0f, titleColor);
        Render2D.rect(this.confirmX + 6.0f, drawY + 6.0f + 9.0f, 76.0f, 0.6f, 0.0f, ColorEngine.multAlpha(0x22FFFFFF, a));
        String[] stringArray = new String[]{I18n.tr("Для себя"), I18n.tr("Для всех")};
        String[] labels = stringArray;
        for (int i = 0; i < 2; ++i) {
            float ry = drawY + 6.0f + 13.0f + (float)i * 16.0f;
            float rx = this.confirmX + 6.0f;
            float rw = 76.0f;
            boolean hovered = this.confirmOpen && mouseX >= rx && mouseX <= rx + rw && mouseY >= ry && mouseY <= ry + 12.0f;
            float[] fArray = this.confirmHover;
            int n = i;
            fArray[n] = fArray[n] + ((hovered ? 1.0f : 0.0f) - this.confirmHover[i]) * rate;
            float hover = this.confirmHover[i];
            RenderHelper.drawPanelBg(rx, ry, rw, 12.0f, 3.0f, a);
            if (i == 1) {
                Render2D.rect(rx, ry, rw, 12.0f, 3.0f, ColorEngine.multAlpha(-4702144, (0.15f + 0.6f * hover) * a));
            } else if (hover > 0.01f) {
                Render2D.rect(rx, ry, rw, 12.0f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(210.0f * hover, rx + rw * 0.5f, ry + 6.0f), a));
            }
            this.drawRowLabel(rx, ry, rw, labels[i], hover, a, i == 1);
        }
    }

    private final void drawRowLabel(float rx, float ry, float rw, String label, float hover, float a, boolean danger) {
        int base = danger ? ColorEngine.multAlpha(-2070424, 0.8f * a) : ColorEngine.multAlpha(-1, 0.65f * a);
        int active = danger ? ColorEngine.multAlpha(-1, a) : ColorEngine.multAlpha(-15856114, a);
        int color = ColorEngine.lerpColor(base, active, hover);
        float lw = Fonts.MEDIUM.width(label, 6.0f);
        Fonts.MEDIUM.draw(label, rx + (rw - lw) * 0.5f, ry + 2.5f, 6.0f, color);
    }

    private final void renderHeader(float x, float y, float a, float mouseX, float mouseY, float rate, float panelRadius) {
        float inset = 1.5f;
        RenderHelper.drawPanelBg(x + inset, y + inset, this.pw - inset * 2.0f, 22.0f - inset, Math.max(0.0f, panelRadius - inset), Math.max(0.0f, panelRadius - inset), 0.0f, 0.0f, a);
        float titleIconSize = 7.0f;
        float titleIconX = x + 7.0f + 1.0f;
        float titleIconY = y + 8.5f;
        Fonts.KIMIKO.msdf(CHAT_GLYPH, titleIconX, titleIconY, titleIconSize, ColorEngine.multAlpha(-1, 0.82f * a));
        String titleText = I18n.tr("Чат");
        Fonts.MEDIUM.draw(titleText, titleIconX + titleIconSize + 3.0f, y + 8.5f, 7.0f, ColorEngine.multAlpha(-1, 0.92f * a));
        MessengerClient.SelfState state = MessengerClient.INSTANCE.state();
        this.selfChipX = titleIconX + titleIconSize + 3.0f + Fonts.MEDIUM.width(titleText, 7.0f) + 5.0f;
        this.selfChipY = y + 8.0f;
        ChatPrefixes.Entry selfEntry = ChatPrefixBadge.resolve(state.prefix(), state.role());
        this.selfChipW = selfEntry != null ? ChatPrefixBadge.draw(selfEntry, this.selfChipX, this.selfChipY, a) : this.drawEmptyChip(this.selfChipX, this.selfChipY, a);
        float statusA = a * (1.0f - this.deleteBtnT);
        if (statusA > 0.01f) {
            String string;
            boolean connected = MessengerClient.INSTANCE.isConnected();
            if (connected) {
                Object[] objectArray = new Object[]{MessengerClient.INSTANCE.online()};
                string = I18n.tr("%d онлайн", objectArray);
            } else {
                string = I18n.tr("оффлайн");
            }
            String status = string;
            float sw = Fonts.MEDIUM.width(status, 5.0f);
            float dotR = 2.2f;
            float sx = x + this.pw - 7.0f - sw;
            int dotColor = connected ? ColorEngine.multAlpha(-11019894, statusA) : ColorEngine.multAlpha(-6598576, statusA);
            Render2D.rect(sx - dotR * 2.0f - 4.0f, y + 12.5f - dotR, dotR * 2.0f, dotR * 2.0f, dotR, dotColor);
            Fonts.MEDIUM.draw(status, sx, y + 9.5f, 5.0f, ColorEngine.multAlpha(-1, 0.55f * statusA));
        }
        if (this.deleteBtnT > 0.01f) {
            float btnA = a * this.deleteBtnT;
            Object[] sw = new Object[]{Math.max(1, this.selected.size())};
            String label = I18n.tr("Удалить (%d)", sw);
            float lw = Fonts.MEDIUM.width(label, 6.0f);
            float iconS = 6.0f;
            float iconGap = 3.0f;
            this.deleteBtnW = 6.0f + iconS + iconGap + lw + 6.0f;
            this.deleteBtnH = 12.0f;
            this.deleteBtnX = x + this.pw - 7.0f - this.deleteBtnW;
            this.deleteBtnY = y + 7.0f + (1.0f - this.deleteBtnT) * 3.0f;
            boolean hovered = !((Collection)this.selected).isEmpty() && mouseX >= this.deleteBtnX && mouseX <= this.deleteBtnX + this.deleteBtnW && mouseY >= this.deleteBtnY && mouseY <= this.deleteBtnY + this.deleteBtnH;
            this.deleteBtnHover += ((hovered ? 1.0f : 0.0f) - this.deleteBtnHover) * rate;
            RenderHelper.drawPanelBg(this.deleteBtnX, this.deleteBtnY, this.deleteBtnW, this.deleteBtnH, 3.0f, btnA);
            Render2D.rect(this.deleteBtnX, this.deleteBtnY, this.deleteBtnW, this.deleteBtnH, 3.0f, ColorEngine.multAlpha(-4702144, (0.15f + 0.6f * this.deleteBtnHover) * btnA));
            int textColor = ColorEngine.lerpColor(ColorEngine.multAlpha(-2070424, 0.9f * btnA), ColorEngine.multAlpha(-1, btnA), this.deleteBtnHover);
            MessengerPanel.Companion.drawIconCentered("A", this.deleteBtnX + 6.0f, this.deleteBtnY + (this.deleteBtnH - iconS) * 0.5f, iconS, iconS, textColor);
            Fonts.MEDIUM.draw(label, this.deleteBtnX + 6.0f + iconS + iconGap, this.deleteBtnY + (this.deleteBtnH - 7.0f) * 0.5f, 6.0f, textColor);
        } else {
            this.deleteBtnW = 0.0f;
        }
    }

    private final float drawEmptyChip(float x, float y, float a) {
        float w = 11.0f;
        Render2D.rect(x, y, w, 7.0f, 2.52f, ColorEngine.multAlpha(0x1AFFFFFF, a));
        Render2D.outline(x, y, w, 7.0f, 2.52f, 0.5f, ColorEngine.multAlpha(0x33FFFFFF, a));
        String plus = "+";
        float pw2 = Fonts.MEDIUM.width(plus, 5.0f);
        Fonts.MEDIUM.draw(plus, x + (w - pw2) * 0.5f, y + 1.0f - 0.3f, 5.0f, ColorEngine.multAlpha(-1, 0.5f * a));
        return w;
    }

    private final SpotRect nickAt(float mx, float my) {
        for (SpotRect rect : this.nickRects) {
            if (!(mx >= rect.getX()) || !(mx <= rect.getX() + rect.getW()) || !(my >= rect.getY()) || !(my <= rect.getY() + rect.getH())) continue;
            return rect;
        }
        return null;
    }

    private final boolean handleSelfMenuClick(float mx, float my, int button) {
        if (button != 0) {
            return false;
        }
        List<SelfRow> rows = this.selfRows();
        float w = 76.0f;
        int n = ((Collection)rows).size();
        for (int i = 0; i < n; ++i) {
            float rx = this.selfMenuX + 6.0f;
            float ry = this.selfMenuY + 6.0f + (float)i * 16.0f;
            float rw = w - 12.0f;
            if (mx < rx || mx > rx + rw || my < ry || my > ry + 12.0f) continue;
            this.selfMenuOpen = false;
            switch (rows.get(i).getAction()) {
                case 1: {
                    VoiceNotePlayer.setAutoPlay(!VoiceNotePlayer.autoPlay());
                    break;
                }
                case 2: {
                    this.startKeyEntry();
                    break;
                }
                default: {
                    String string = rows.get(i).getPrefixId();
                    if (string == null) {
                        string = "";
                    }
                    MessengerClient.INSTANCE.choosePrefix(string, (ok, message) -> {
                        if (!Boolean.TRUE.equals(ok)) {
                            Notifications.push(I18n.tr("Ошибка"), message, 2400L);
                        }
                        return Unit.INSTANCE;
                    });
                }
            }
            return true;
        }
        return false;
    }

    private final void startKeyEntry() {
        this.keyEntry = true;
        this.cancelEditing();
        this.cancelReply();
        this.input.placeholder(I18n.tr("Ключ модерации"));
        this.input.setText("");
        this.input.focus();
    }

    private final void stopKeyEntry() {
        this.keyEntry = false;
        this.input.placeholder(I18n.tr("Сообщение..."));
        this.input.setText("");
    }

    private final boolean isGrouped(List<MessengerClient.Message> msgs, int i) {
        if (i <= 0) {
            return false;
        }
        MessengerClient.Message cur = msgs.get(i);
        MessengerClient.Message prev = msgs.get(i - 1);
        return StringsKt.equals((String)cur.user(), (String)prev.user(), (boolean)true) && cur.ts() - prev.ts() <= 90000L;
    }

    private final float blockHeight(MessengerClient.Message msg, boolean grouped) {
        float head = msg.mine() || grouped ? 0.0f : 14.0f;
        float edited = msg.edited() ? 5.5f : 0.0f;
        return head + this.bubbleHeight(msg) + edited + 4.0f;
    }

    private final float bubbleHeight(MessengerClient.Message msg) {
        if (msg.voice()) {
            return 20.0f + MessengerPanel.Companion.quoteExtra(msg);
        }
        List<String> lines = this.wrapCache.getOrDefault(msg.id(), List.of(msg.text()));
        return (float)lines.size() * 7.8f - 2.2f + 2.24f + 7.0f + MessengerPanel.Companion.quoteExtra(msg);
    }

    private final void renderMessage(MessengerClient.Message msg, boolean grouped, float x, float y, float w, float a) {
        List<String> lines = this.wrapCache.getOrDefault(msg.id(), List.of(msg.text()));
        float textW = 0.0f;
        if (msg.voice()) {
            textW = VoiceBubble.contentWidth(msg);
        } else {
            for (String line : lines) {
                textW = Math.max(textW, Fonts.MEDIUM.width(line, 5.6f));
            }
        }
        float maxBubbleW = w * 0.86f;
        float quoteH = MessengerPanel.Companion.quoteExtra(msg);
        MessengerClient.Message quoted = msg.replyTo() > 0L ? MessengerClient.INSTANCE.byId(msg.replyTo()) : null;
        float contentW = textW;
        if (quoteH > 0.0f) {
            float qw = 4.4f + Math.max(Fonts.MEDIUM.width(MessengerPanel.Companion.quoteName(quoted), 4.8f), Fonts.MEDIUM.width(MessengerPanel.Companion.quoteText(quoted), 4.8f));
            contentW = Math.max(contentW, Math.min(qw, maxBubbleW - 9.0f));
        }
        float bubbleW = Math.min(maxBubbleW, contentW + 9.0f);
        float bubbleH = this.bubbleHeight(msg);
        String time = TIME_FMT.format(Instant.ofEpochMilli(msg.ts()).atZone(ZoneId.systemDefault()));
        if (msg.mine()) {
            float bx = x + w - bubbleW;
            int bg = ColorEngine.multAlpha(ClientAccent.accentSoftAt(105.0f, bx + bubbleW * 0.5f, y + bubbleH * 0.5f), a);
            Render2D.rect(bx, y, bubbleW, bubbleH, 4.0f, bg);
            float selT = this.selectionT(msg.id());
            if (selT > 0.01f) {
                Render2D.rect(bx, y, bubbleW, bubbleH, 4.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(60.0f * selT, bx, y), a));
                int outlineColor = ColorEngine.lerpColor(-1776412, ClientAccent.accentAt(255.0f, bx, y), 0.2f);
                Render2D.outline(bx - 1.0f, y - 1.0f, bubbleW + 2.0f, bubbleH + 2.0f, 5.0f, 0.8f, ColorEngine.multAlpha(outlineColor, 0.9019608f * selT * a));
            }
            this.drawReplyHighlight(msg, bx, y, bubbleW, bubbleH, a);
            if (quoteH > 0.0f) {
                this.drawQuote(quoted, bx + 4.5f, y + 3.5f, bubbleW - 9.0f, a, true);
            }
            if (msg.voice()) {
                this.drawVoice(msg, bx + 4.5f, y + 3.5f + quoteH, a, true);
            } else {
                this.drawLines(lines, bx + 4.5f, y + 3.5f + quoteH, ColorEngine.multAlpha(-1, 0.92f * a));
            }
            float tw = Fonts.MEDIUM.width(time, 4.6f);
            Fonts.MEDIUM.draw(time, bx - tw - 3.0f, y + bubbleH - 4.6f - 1.5f, 4.6f, ColorEngine.multAlpha(-1, 0.38f * a));
            if (msg.edited()) {
                this.drawEditedMark(bx + bubbleW, y + bubbleH + 1.0f, a, true);
            }
            return;
        }
        float bubbleX = x + 11.0f + 4.0f;
        float bubbleY = y;
        if (!grouped) {
            this.renderAvatar(msg, x, y, a);
            ChatPrefixes.Entry badge = ChatPrefixBadge.resolve(msg.prefix(), msg.role());
            float nickX = bubbleX;
            if (badge != null) {
                nickX += ChatPrefixBadge.draw(badge, bubbleX, y + 0.4f, a) + 3.0f;
            }
            Fonts.MEDIUM.draw(msg.user(), nickX, y + 1.5f, 5.6f, ColorEngine.multAlpha(-1, 0.85f * a));
            float nickW = Fonts.MEDIUM.width(msg.user(), 5.6f);
            Fonts.MEDIUM.draw(time, nickX + nickW + 4.0f, y + 2.6f, 4.6f, ColorEngine.multAlpha(-1, 0.38f * a));
            this.nickRects.add(new SpotRect(msg.id(), x, y - 1.0f, nickX - bubbleX + nickW + 11.0f + 4.0f, 13.0f));
            bubbleY = y + 11.0f + 3.0f;
        }
        bubbleW = Math.min(x + w - bubbleX, bubbleW);
        Render2D.rect(bubbleX, bubbleY, bubbleW, bubbleH, 4.0f, ColorEngine.multAlpha(0x28000000, a));
        Render2D.outline(bubbleX, bubbleY, bubbleW, bubbleH, 4.0f, 0.6f, ColorEngine.multAlpha(0x14FFFFFF, a));
        this.drawReplyHighlight(msg, bubbleX, bubbleY, bubbleW, bubbleH, a);
        if (quoteH > 0.0f) {
            this.drawQuote(quoted, bubbleX + 4.5f, bubbleY + 3.5f, bubbleW - 9.0f, a, false);
        }
        if (msg.voice()) {
            this.drawVoice(msg, bubbleX + 4.5f, bubbleY + 3.5f + quoteH, a, false);
        } else {
            this.drawLines(lines, bubbleX + 4.5f, bubbleY + 3.5f + quoteH, ColorEngine.multAlpha(-1, 0.88f * a));
        }
        if (grouped) {
            float tw = Fonts.MEDIUM.width(time, 4.6f);
            Fonts.MEDIUM.draw(time, bubbleX + bubbleW + 3.0f, bubbleY + bubbleH - 4.6f - 1.5f, 4.6f, ColorEngine.multAlpha(-1, 0.3f * a));
        }
        if (msg.edited()) {
            this.drawEditedMark(bubbleX + 1.0f, bubbleY + bubbleH + 1.0f, a, false);
        }
    }

    private final void drawQuote(MessengerClient.Message quoted, float x, float y, float w, float a, boolean mine) {
        int barColor = mine ? ColorEngine.multAlpha(-1, 0.75f * a) : ColorEngine.multAlpha(ClientAccent.accentAt(255.0f, x, y + 5.75f), a);
        Render2D.rect(x, y, 1.4f, 11.5f, 0.7f, barColor);
        float textX = x + 4.4f;
        float textW = w - 4.4f;
        int nameColor = mine ? ColorEngine.multAlpha(-1, 0.85f * a) : ColorEngine.multAlpha(ClientAccent.accentSoftAt(230.0f, textX, y), a);
        Fonts.MEDIUM.draw(MessengerPanel.Companion.clip(MessengerPanel.Companion.quoteName(quoted), 4.8f, textW), textX, y, 4.8f, nameColor);
        Fonts.MEDIUM.draw(MessengerPanel.Companion.clip(MessengerPanel.Companion.quoteText(quoted), 4.8f, textW), textX, y + 6.0f, 4.8f, ColorEngine.multAlpha(-1, 0.5f * a));
    }

    private final void drawReplyHighlight(MessengerClient.Message msg, float x, float y, float w, float h, float a) {
        if (this.replyId != msg.id() || this.replyBarT <= 0.01f) {
            return;
        }
        Render2D.rect(x, y, w, h, 4.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(55.0f * this.replyBarT, x, y), a));
        Render2D.outline(x - 1.0f, y - 1.0f, w + 2.0f, h + 2.0f, 5.0f, 0.8f, ColorEngine.multAlpha(ClientAccent.accentAt(255.0f, x, y), 0.85f * this.replyBarT * a));
    }

    private final void drawEditedMark(float x, float y, float a, boolean alignRight) {
        String mark = I18n.tr("Изменено");
        float size = 4.2f;
        float mw = Fonts.MEDIUM.width(mark, size);
        float mx = alignRight ? x - mw : x;
        Fonts.MEDIUM.draw(mark, mx, y, size, ColorEngine.multAlpha(-1, 0.34f * a), -6.0f, mx + mw * 0.5f, y + size * 0.5f);
    }

    private final void renderAvatar(MessengerClient.Message msg, float x, float y, float a) {
        String string;
        String texture = null;
        if (msg.mine()) {
            texture = DiscordAvatar.texture();
        } else if (msg.uid() > 0) {
            texture = RemoteAvatarCache.texture("https://kimiko.tech/api/account/avatar/" + msg.uid());
        }
        if (texture != null) {
            int white = ColorEngine.multAlpha(-1, a);
            Render2D.image(texture, x, y, 11.0f, 11.0f, 5.5f, white);
            return;
        }
        int bg = ColorEngine.multAlpha(ClientAccent.accentSoftAt(70.0f, x + 5.5f, y + 5.5f), a);
        Render2D.rect(x, y, 11.0f, 11.0f, 5.5f, bg);
        if (((CharSequence)msg.user()).length() == 0) {
            string = "?";
        } else {
            String string2 = msg.user().substring(0, 1);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
            String string3 = string2.toUpperCase(Locale.ROOT);
            string = string3;
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toUpperCase(...)");
        }
        String letter = string;
        float lw = Fonts.MEDIUM.width(letter, 5.5f);
        Fonts.MEDIUM.draw(letter, x + (11.0f - lw) * 0.5f, y + 2.75f - 0.5f, 5.5f, ColorEngine.multAlpha(-1, 0.85f * a));
    }

    private final void drawVoice(MessengerClient.Message msg, float x, float y, float a, boolean mine) {
        VoiceBubble.render(msg, x, y, a, mine);
        this.voiceRects.add(new SpotRect(msg.id(), x, y, 12.0f, 13.0f));
    }

    private final void drawLines(List<String> lines, float x, float y, int color) {
        int n = ((Collection)lines).size();
        for (int i = 0; i < n; ++i) {
            Fonts.MEDIUM.draw(lines.get(i), x, y + (float)i * 7.8f, 5.6f, color);
        }
    }

    private final void ensureWrapCache(float listW, List<MessengerClient.Message> msgs) {
        float wrapW = listW * 0.86f - 9.0f;
        if (Math.abs(wrapW - this.wrapCacheWidth) > 0.5f) {
            this.wrapCache.clear();
            this.wrapCacheWidth = wrapW;
        }
        long now = System.currentTimeMillis();
        for (MessengerClient.Message msg : msgs) {
            String text = msg.text();
            if (msg.voice()) {
                this.wrapCache.put(msg.id(), EMPTY_LINES);
                this.wrapText.put(msg.id(), text);
                if (this.appearAt.containsKey(msg.id())) continue;
                this.appearAt.put(msg.id(), this.appearPrimed ? now : 0L);
                continue;
            }
            if (!this.wrapCache.containsKey(msg.id()) || !Intrinsics.areEqual((Object)text, (Object)this.wrapText.get(msg.id()))) {
                this.wrapCache.put(msg.id(), MessengerPanel.Companion.wrap(text, wrapW));
                this.wrapText.put(msg.id(), text);
            }
            if (this.appearAt.containsKey(msg.id())) continue;
            this.appearAt.put(msg.id(), this.appearPrimed ? now : 0L);
        }
        this.appearPrimed = true;
        if (this.wrapCache.size() > msgs.size() + 64) {
            Set<Long> msgIds = new HashSet<Long>();
            for (MessengerClient.Message m : msgs) {
                msgIds.add(m.id());
            }
            this.wrapCache.keySet().removeIf(id -> !msgIds.contains(id));
            this.wrapText.keySet().retainAll((Collection)this.wrapCache.keySet());
            this.appearAt.keySet().retainAll((Collection)this.wrapCache.keySet());
        }
    }

    public final boolean mouseClicked(float mx, float my, int button) {
        HitRect hit;
        if (!this.open) {
            return false;
        }
        if (this.userPopup.isOpen()) {
            if (this.userPopup.mouseClicked(mx, my, button)) {
                return true;
            }
            return this.contains(mx, my);
        }
        if (this.selfMenuOpen) {
            if (this.handleSelfMenuClick(mx, my, button)) {
                return true;
            }
            this.selfMenuOpen = false;
            return this.contains(mx, my);
        }
        if (this.menuOpen) {
            float menuH = 40.0f;
            if (button == 0 && mx >= this.menuX && mx <= this.menuX + 66.0f && my >= this.menuY && my <= this.menuY + menuH) {
                int row = (int)((my - this.menuY - 6.0f) / 16.0f);
                this.closeMenu();
                switch (row) {
                    case 0: {
                        this.selected.add(this.menuMsgId);
                        break;
                    }
                    case 1: {
                        this.startEditing(this.menuMsgId);
                    }
                }
                return true;
            }
            this.closeMenu();
            return this.contains(mx, my);
        }
        if (this.confirmOpen) {
            float rowsTop = this.confirmY + 6.0f + 13.0f;
            if (button == 0 && mx >= this.confirmX && mx <= this.confirmX + 88.0f && my >= rowsTop && my <= rowsTop + 24.0f + 4.0f) {
                int row = (int)((my - rowsTop) / 16.0f);
                this.closeConfirm();
                List ids = new ArrayList(this.selected);
                this.selected.clear();
                if (row == 0) {
                    MessengerClient.INSTANCE.deleteLocal(ids);
                } else {
                    MessengerClient.INSTANCE.deleteForAll(ids);
                }
                return true;
            }
            this.closeConfirm();
            return this.contains(mx, my);
        }
        if (button == 0 && this.selfChipW > 0.0f && mx >= this.selfChipX - 2.0f && mx <= this.selfChipX + this.selfChipW + 2.0f && my >= this.selfChipY - 2.0f && my <= this.selfChipY + 7.0f + 2.0f) {
            this.selfMenuOpen = true;
            this.selfMenuX = this.selfChipX;
            this.selfMenuY = this.selfChipY + 7.0f + 3.0f;
            Arrays.fill(this.selfHover, 0.0f);
            Sounds.play("module_settings_open");
            return true;
        }
        if (mx >= this.micX - 2.0f && mx <= this.micX + 7.0f + 2.0f && my >= this.micY - 2.0f && my <= this.micY + 7.0f + 2.0f) {
            switch (button) {
                case 1: {
                    VoiceNoteRecorder.cancel();
                    break;
                }
                case 0: {
                    if (VoiceNoteRecorder.isRecording()) {
                        VoiceNoteRecorder.finish();
                        this.scroll = 0.0f;
                        break;
                    }
                    VoiceNoteRecorder.start();
                }
            }
            return true;
        }
        if (button == 0) {
            for (SpotRect rect : this.voiceRects) {
                if (!(mx >= rect.getX()) || !(mx <= rect.getX() + rect.getW()) || !(my >= rect.getY()) || !(my <= rect.getY() + rect.getH())) continue;
                MessengerClient.Message voiceMsg = MessengerClient.INSTANCE.byId(rect.getId());
                if (voiceMsg != null && voiceMsg.voice()) {
                    VoiceNotePlayer.toggle(voiceMsg.voiceId(), voiceMsg.user());
                }
                return true;
            }
        }
        if (MessengerClient.INSTANCE.isStaff()) {
            MessengerClient.Message subject;
            SpotRect spot;
            SpotRect spotRect;
            if (button == 0) {
                spotRect = this.nickAt(mx, my);
            } else {
                HitRect hitRect = this.hitAt(mx, my);
                if (hitRect != null) {
                    HitRect hit2 = hitRect;
                    boolean bl = false;
                    spotRect = hit2.getMine() ? null : new SpotRect(hit2.getId(), hit2.getX(), hit2.getY(), hit2.getW(), hit2.getH());
                } else {
                    spotRect = null;
                }
            }
            SpotRect spotRect2 = spot = spotRect;
            MessengerClient.Message message = subject = spotRect2 == null ? null : MessengerClient.INSTANCE.byId(spotRect2.getId());
            if (subject != null && !subject.mine()) {
                this.userPopup.open(subject, MessengerClient.INSTANCE.role(), mx, my);
                return true;
            }
        }
        if (button == 0 && this.replyId != -1L && this.replyCancelS > 0.0f && mx >= this.replyCancelX && mx <= this.replyCancelX + this.replyCancelS && my >= this.replyCancelY && my <= this.replyCancelY + this.replyCancelS) {
            this.cancelReply();
            return true;
        }
        if (button == 1) {
            hit = this.hitAt(mx, my);
            if (hit != null && hit.getMine()) {
                float menuH = 40.0f;
                this.openMenu(hit.getId(), Math.min(mx, this.px + this.pw - 66.0f - 3.0f), Math.min(my, this.py + this.ph - menuH - 3.0f));
                return true;
            }
            return this.contains(mx, my);
        }
        if (!((Collection)this.selected).isEmpty() && this.deleteBtnW > 0.0f && mx >= this.deleteBtnX && mx <= this.deleteBtnX + this.deleteBtnW && my >= this.deleteBtnY && my <= this.deleteBtnY + this.deleteBtnH) {
            this.openConfirm(Math.min(this.deleteBtnX, this.px + this.pw - 88.0f - 3.0f), this.deleteBtnY + this.deleteBtnH + 3.0f);
            return true;
        }
        if (!((Collection)this.selected).isEmpty() && (hit = this.hitAt(mx, my)) != null && hit.getMine()) {
            if (!this.selected.remove(hit.getId())) {
                this.selected.add(hit.getId());
                this.dragAdd = true;
            } else {
                this.dragAdd = false;
            }
            this.dragSelecting = true;
            return true;
        }
        if (button == 0) {
            hit = this.hitAt(mx, my);
            if (hit != null) {
                long now = System.currentTimeMillis();
                if (hit.getId() == this.lastClickId && now - this.lastClickMs <= 330L) {
                    this.lastClickId = -1L;
                    this.dragArmId = -1L;
                    this.startReply(hit.getId());
                    return true;
                }
                this.lastClickId = hit.getId();
                this.lastClickMs = now;
            }
            if (hit != null && hit.getMine()) {
                this.dragArmId = hit.getId();
                this.dragStartX = mx;
                this.dragStartY = my;
            }
        }
        if (this.editingId != -1L && this.editCancelS > 0.0f && mx >= this.editCancelX && mx <= this.editCancelX + this.editCancelS && my >= this.editCancelY && my <= this.editCancelY + this.editCancelS) {
            this.cancelEditing();
            return true;
        }
        if (button == 0 && mx >= this.sendX - 2.0f && mx <= this.sendX + 7.0f + 2.0f && my >= this.sendY - 2.0f && my <= this.sendY + 7.0f + 2.0f) {
            this.submitInput();
            return true;
        }
        if (this.input.mouseClicked(mx, my, button)) {
            return true;
        }
        return this.contains(mx, my);
    }

    private final HitRect hitAt(float mx, float my) {
        for (HitRect rect : this.hitRects) {
            if (!(mx >= rect.getX()) || !(mx <= rect.getX() + rect.getW()) || !(my >= rect.getY()) || !(my <= rect.getY() + rect.getH())) continue;
            return rect;
        }
        return null;
    }

    private final void startEditing(long id) {
        for (MessengerClient.Message msg : MessengerClient.INSTANCE.snapshot()) {
            if (msg.id() != id || !msg.mine()) continue;
            this.cancelReply();
            this.editingId = id;
            this.input.setText(msg.text());
            this.input.focus();
            return;
        }
    }

    private final void cancelEditing() {
        this.editingId = -1L;
        this.input.setText("");
        this.input.blur();
    }

    private final void startReply(long id) {
        MessengerClient.Message message = MessengerClient.INSTANCE.byId(id);
        if (message == null) {
            return;
        }
        MessengerClient.Message target = message;
        if (this.editingId != -1L) {
            this.editingId = -1L;
            this.input.setText("");
        }
        this.replyId = id;
        this.replyCancelHover = 0.0f;
        this.input.focus();
        Sounds.play("module_settings_open");
    }

    private final void cancelReply() {
        this.replyId = -1L;
        this.replyCancelHover = 0.0f;
    }

    public final void mouseReleased(int button) {
        if (button == 0) {
            this.dragSelecting = false;
            this.dragArmId = -1L;
        }
        this.input.mouseReleased(button);
    }

    public final void scroll(double vertical) {
        this.scroll += (float)vertical * 14.0f;
        if (this.scroll < 0.0f) {
            this.scroll = 0.0f;
        }
    }

    public final boolean keyPressed(@NotNull KeyInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.isTyping()) {
            return false;
        }
        if (event.key() == 256 && this.keyEntry) {
            this.stopKeyEntry();
            this.input.blur();
            return true;
        }
        if (event.key() == 256 && this.editingId != -1L) {
            this.cancelEditing();
            return true;
        }
        if (event.key() == 256 && this.replyId != -1L) {
            this.cancelReply();
            return true;
        }
        if (event.key() == 257 || event.key() == 335) {
            this.submitInput();
            return true;
        }
        return this.input.keyPressed(event);
    }

    public final boolean charTyped(@NotNull CharInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        return this.isTyping() && this.input.charTyped(event);
    }

    private final void submitInput() {
        String text = ((Object)StringsKt.trim((CharSequence)this.input.getText())).toString();
        if (this.keyEntry) {
            this.stopKeyEntry();
            this.input.blur();
            if (((CharSequence)text).length() > 0) {
                MessengerClient.INSTANCE.authStaff(text, (ok, message) -> {
                    boolean success = Boolean.TRUE.equals(ok);
                    Notifications.push(success ? I18n.tr("Модерация") : I18n.tr("Ошибка"), success ? I18n.tr("Ключ принят") : message, 2600L);
                    return Unit.INSTANCE;
                });
            }
            return;
        }
        if (this.editingId != -1L) {
            if (((CharSequence)text).length() > 0) {
                MessengerClient.INSTANCE.edit(this.editingId, text);
            }
            this.cancelEditing();
            Sounds.play("search_typing");
            return;
        }
        if (((CharSequence)text).length() == 0) {
            return;
        }
        if (this.replyId != -1L) {
            MessengerClient.INSTANCE.sendReply(this.replyId, text);
            this.cancelReply();
        } else {
            MessengerClient.INSTANCE.send(text);
        }
        this.input.setText("");
        this.scroll = 0.0f;
        Sounds.play("search_typing");
    }

    private static final Unit handleSelfMenuClick$lambda$0(boolean ok, String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        if (!ok) {
            Notifications.push(I18n.tr("Ошибка"), message, 2400L);
        }
        return Unit.INSTANCE;
    }

    private static final boolean ensureWrapCache$lambda$0(List<?> $msgs, Long id) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        for (Object item : $msgs) {
            MessengerClient.Message msg = (MessengerClient.Message) item;
            Long l = id;
            if (l == null || msg.id() != l.longValue()) continue;
            return false;
        }
        return true;
    }

    private static final boolean ensureWrapCache$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final Unit submitInput$lambda$0(boolean ok, String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        Notifications.push(ok ? I18n.tr("Модерация") : I18n.tr("Ошибка"), ok ? I18n.tr("Ключ принят") : message, 2600L);
        return Unit.INSTANCE;
    }

    @JvmStatic
    public static final float shatterExtraWidth() {
        return Companion.shatterExtraWidth();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b#\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ?\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J)\u0010\u001a\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010 \u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002\u00a2\u0006\u0004\b \u0010\u001fJ\u0017\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b'\u0010&J%\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0(2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010,R\u0014\u0010.\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010,R\u0014\u0010/\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010,R\u0014\u00100\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010,R\u0014\u00101\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010,R\u0014\u00102\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010,R\u0014\u00103\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u0010,R\u0014\u00104\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010,R\u0014\u00105\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010,R\u0014\u00106\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010,R\u0014\u00107\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010,R\u0014\u00108\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u0010,R\u0014\u0010:\u001a\u0002098\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u001c\u0010>\u001a\n =*\u0004\u0018\u00010<0<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u001a\u0010@\u001a\b\u0012\u0004\u0012\u00020\u000f0(8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010,R\u0014\u0010C\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010,R\u0014\u0010D\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010ER\u0014\u0010G\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010ER\u0014\u0010H\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010,R\u0014\u0010K\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010,R\u0014\u0010L\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010,R\u0014\u0010M\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010,R\u0014\u0010N\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010,R\u0014\u0010O\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010,R\u0014\u0010P\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010,R\u0014\u0010Q\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010,R\u0014\u0010R\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010,R\u0014\u0010S\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010,R\u0014\u0010T\u001a\u0002098\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u0010;R\u0014\u0010U\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010,R\u0014\u0010V\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010,R\u0014\u0010W\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010,R\u0014\u0010X\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010,R\u0014\u0010Y\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010,R\u0014\u0010Z\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010,R\u0014\u0010[\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b[\u0010IR\u0014\u0010\\\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\\\u0010IR\u0014\u0010]\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010IR\u0014\u0010^\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b^\u0010,\u00a8\u0006_"}, d2={"Lrtx/kimiko/api/ui/MessengerPanel.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "shatterExtraWidth", "()F", "Lrtx/kimiko/utils/animations/Decelerate;", "target", "", "ms", "", "snapClosed", "(Lrtx/kimiko/utils/animations/Decelerate;I)V", "", "glyph", "x", "y", "box", "size", "color", "drawIconCentered", "(Ljava/lang/String;FFFFI)V", "text", "maxW", "clip", "(Ljava/lang/String;FF)Ljava/lang/String;", "Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;", "quoted", "quoteName", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;)Ljava/lang/String;", "quoteText", "msg", "quoteExtra", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;)F", "value", "snapPx", "(F)F", "ease", "", "wrap", "(Ljava/lang/String;F)Ljava/util/List;", "WIDTH", "F", "GAP_FROM_PANEL", "RADIUS", "PAD", "HEADER_H", "INPUT_H", "AVATAR", "MSG_SIZE", "NICK_SIZE", "TIME_SIZE", "BUBBLE_PAD_X", "BUBBLE_PAD_Y", "LINE_H", "", "GROUP_WINDOW_MS", "J", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "TIME_FMT", "Ljava/time/format/DateTimeFormatter;", "EMPTY_LINES", "Ljava/util/List;", "MIC_SIZE", "SELF_MENU_W", "SELF_PREFIX", "I", "SELF_AUTOPLAY", "SELF_KEY", "MARK", "Ljava/lang/String;", "MENU_W", "MENU_ROW_H", "CONFIRM_W", "EDIT_BAR_H", "REPLY_BAR_H", "QUOTE_H", "QUOTE_GAP", "QUOTE_BAR_W", "QUOTE_TEXT_X", "QUOTE_SIZE", "DOUBLE_CLICK_MS", "EDITED_H", "PAD_POPUP", "GAP_POPUP", "CONFIRM_TITLE_H", "ROW_RADIUS", "ROW_TEXT_SIZE", "ICON_TRASH", "CHAT_GLYPH", "SEND_GLYPH", "SEND_SIZE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final float shatterExtraWidth() {
            return 166.0f;
        }

        private final void snapClosed(Decelerate target, int ms) {
            target.setMs(ms);
            target.setValue(1.0);
            target.setDirection(Direction.BACKWARDS);
            target.counter.setTime(System.currentTimeMillis() - (long)10000);
        }

        private final void drawIconCentered(String glyph, float x, float y, float box, float size, int color) {
            float[] b = Fonts.KIMIKO.msdfBounds(glyph, size);
            if (b == null || b.length < 4) {
                return;
            }
            Fonts.KIMIKO.msdf(glyph, x + (box - (b[2] - b[0])) * 0.5f - b[0], y + (box - (b[3] - b[1])) * 0.5f - b[1], size, color);
        }

        private final String clip(String text, float size, float maxW) {
            int cut;
            CharSequence charSequence = text;
            if (charSequence == null || charSequence.length() == 0 || maxW <= 0.0f) {
                return "";
            }
            if (Fonts.MEDIUM.width(text, size) <= maxW) {
                return text;
            }
            for (cut = text.length(); cut > 1; --cut) {
                String string = text.substring(0, cut);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                if (!(Fonts.MEDIUM.width(string + "…", size) > maxW)) break;
            }
            String string = text.substring(0, cut);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            return string + "…";
        }

        private final String quoteName(MessengerClient.Message quoted) {
            if (quoted == null || quoted.user() == null) {
                return I18n.tr("Сообщение");
            }
            return quoted.user();
        }

        private final String quoteText(MessengerClient.Message quoted) {
            if (quoted == null || quoted.text() == null) {
                return I18n.tr("недоступно");
            }
            return quoted.text().replace('\n', ' ');
        }

        private final float quoteExtra(MessengerClient.Message msg) {
            return msg.replyTo() > 0L ? 14.0f : 0.0f;
        }

        private final float snapPx(float value) {
            float px = Render2DCoordinateSpace.pixelScale();
            if (px <= 0.0f || !(Math.abs(value) <= Float.MAX_VALUE)) {
                return value;
            }
            return (float)MathKt.roundToInt((float)(value * px)) / px;
        }

        private final float ease(float x) {
            float t = RangesKt.coerceIn((float)x, (float)0.0f, (float)1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        private final List<String> wrap(String text, float maxW) {
            List<String> lines = new ArrayList<String>();
            StringBuilder line = new StringBuilder();
            String[] parts = String.valueOf(text).split(" ");
            for (String word : parts) {
                while (Fonts.MEDIUM.width(word, 5.6f) > maxW && word.length() > 1) {
                    int cut;
                    for (cut = word.length() - 1; cut > 1; --cut) {
                        String sub = word.substring(0, cut);
                        if (!(Fonts.MEDIUM.width(sub, 5.6f) > maxW)) break;
                    }
                    String head = word.substring(0, cut);
                    if (line.length() > 0) {
                        lines.add(line.toString());
                        line.setLength(0);
                    }
                    lines.add(head);
                    word = word.substring(cut);
                }
                String candidate = line.length() == 0 ? word : line + " " + word;
                if (Fonts.MEDIUM.width(candidate, 5.6f) > maxW && line.length() > 0) {
                    lines.add(line.toString());
                    line.setLength(0);
                    line.append(word);
                    continue;
                }
                line.setLength(0);
                line.append(candidate);
            }
            if (line.length() > 0) {
                lines.add(line.toString());
            }
            if (lines.isEmpty()) {
                lines.add("");
            }
            return lines;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015JL\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001f\u001a\u00020\u001eH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b$\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010#\u001a\u0004\b%\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b&\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b'\u0010\u0010R\u0017\u0010\n\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\n\u0010(\u001a\u0004\b)\u0010\u0015\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/ui/MessengerPanel$HitRect;", "", "", "id", "", "x", "y", "w", "h", "", "mine", "<init>", "(JFFFFZ)V", "component1", "()J", "component2", "()F", "component3", "component4", "component5", "component6", "()Z", "copy", "(JFFFFZ)Lrtx/kimiko/api/ui/MessengerPanel$HitRect;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "getId", "F", "getX", "getY", "getW", "getH", "Z", "getMine", "rtx.kimiko:kimiko"})
    private static final class HitRect {
        private final long id;
        private final float x;
        private final float y;
        private final float w;
        private final float h;
        private final boolean mine;

        public HitRect(long id, float x, float y, float w, float h, boolean mine) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.mine = mine;
        }

        public final long getId() {
            return this.id;
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

        public final boolean getMine() {
            return this.mine;
        }

        public final long component1() {
            return this.id;
        }

        public final float component2() {
            return this.x;
        }

        public final float component3() {
            return this.y;
        }

        public final float component4() {
            return this.w;
        }

        public final float component5() {
            return this.h;
        }

        public final boolean component6() {
            return this.mine;
        }

        @NotNull
        public final HitRect copy(long id, float x, float y, float w, float h, boolean mine) {
            return new HitRect(id, x, y, w, h, mine);
        }

        public static /* synthetic */ HitRect copy$default(HitRect hitRect, long l, float f, float f2, float f3, float f4, boolean bl, int n, Object object) {
            if ((n & 1) != 0) {
                l = hitRect.id;
            }
            if ((n & 2) != 0) {
                f = hitRect.x;
            }
            if ((n & 4) != 0) {
                f2 = hitRect.y;
            }
            if ((n & 8) != 0) {
                f3 = hitRect.w;
            }
            if ((n & 0x10) != 0) {
                f4 = hitRect.h;
            }
            if ((n & 0x20) != 0) {
                bl = hitRect.mine;
            }
            return hitRect.copy(l, f, f2, f3, f4, bl);
        }

        @NotNull
        public String toString() {
            return "HitRect(id=" + this.id + ", x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.h + ", mine=" + this.mine + ")";
        }

        public int hashCode() {
            int result = Long.hashCode(this.id);
            result = result * 31 + Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.w);
            result = result * 31 + Float.hashCode(this.h);
            result = result * 31 + Boolean.hashCode(this.mine);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HitRect)) {
                return false;
            }
            HitRect hitRect = (HitRect)other;
            if (this.id != hitRect.id) {
                return false;
            }
            if (Float.compare(this.x, hitRect.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, hitRect.y) != 0) {
                return false;
            }
            if (Float.compare(this.w, hitRect.w) != 0) {
                return false;
            }
            if (Float.compare(this.h, hitRect.h) != 0) {
                return false;
            }
            return this.mine == hitRect.mine;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/ui/MessengerPanel$SelfRow;", "", "", "label", "prefixId", "", "action", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "Ljava/lang/String;", "getLabel", "()Ljava/lang/String;", "getPrefixId", "I", "getAction", "()I", "rtx.kimiko:kimiko"})
    private static final class SelfRow {
        @NotNull
        private final String label;
        @Nullable
        private final String prefixId;
        private final int action;

        public SelfRow(@NotNull String label, @Nullable String prefixId, int action) {
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            this.label = label;
            this.prefixId = prefixId;
            this.action = action;
        }

        @NotNull
        public final String getLabel() {
            return this.label;
        }

        @Nullable
        public final String getPrefixId() {
            return this.prefixId;
        }

        public final int getAction() {
            return this.action;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJB\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b#\u0010\u000eR\u0017\u0010\b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b$\u0010\u000e\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/ui/MessengerPanel$SpotRect;", "", "", "id", "", "x", "y", "w", "h", "<init>", "(JFFFF)V", "component1", "()J", "component2", "()F", "component3", "component4", "component5", "copy", "(JFFFF)Lrtx/kimiko/api/ui/MessengerPanel$SpotRect;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "getId", "F", "getX", "getY", "getW", "getH", "rtx.kimiko:kimiko"})
    private static final class SpotRect {
        private final long id;
        private final float x;
        private final float y;
        private final float w;
        private final float h;

        public SpotRect(long id, float x, float y, float w, float h) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public final long getId() {
            return this.id;
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

        public final long component1() {
            return this.id;
        }

        public final float component2() {
            return this.x;
        }

        public final float component3() {
            return this.y;
        }

        public final float component4() {
            return this.w;
        }

        public final float component5() {
            return this.h;
        }

        @NotNull
        public final SpotRect copy(long id, float x, float y, float w, float h) {
            return new SpotRect(id, x, y, w, h);
        }

        public static /* synthetic */ SpotRect copy$default(SpotRect spotRect, long l, float f, float f2, float f3, float f4, int n, Object object) {
            if ((n & 1) != 0) {
                l = spotRect.id;
            }
            if ((n & 2) != 0) {
                f = spotRect.x;
            }
            if ((n & 4) != 0) {
                f2 = spotRect.y;
            }
            if ((n & 8) != 0) {
                f3 = spotRect.w;
            }
            if ((n & 0x10) != 0) {
                f4 = spotRect.h;
            }
            return spotRect.copy(l, f, f2, f3, f4);
        }

        @NotNull
        public String toString() {
            return "SpotRect(id=" + this.id + ", x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.h + ")";
        }

        public int hashCode() {
            int result = Long.hashCode(this.id);
            result = result * 31 + Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.w);
            result = result * 31 + Float.hashCode(this.h);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SpotRect)) {
                return false;
            }
            SpotRect spotRect = (SpotRect)other;
            if (this.id != spotRect.id) {
                return false;
            }
            if (Float.compare(this.x, spotRect.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, spotRect.y) != 0) {
                return false;
            }
            if (Float.compare(this.w, spotRect.w) != 0) {
                return false;
            }
            return Float.compare(this.h, spotRect.h) == 0;
        }
    }
}

