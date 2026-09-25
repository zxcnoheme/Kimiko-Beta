/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.input.CharInput
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.util.Util
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.configs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.util.Util;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigLibrary;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.ui.CardAppear;
import rtx.kimiko.api.ui.ClientLanguage;
import rtx.kimiko.api.ui.ScrollBar;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.configs.RemoteAvatars;
import rtx.kimiko.api.ui.module.DiscordAvatar;
import rtx.kimiko.api.ui.module.ModuleListRenderer;
import rtx.kimiko.api.ui.module.SearchField;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\bA\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0017\u0018\u0000 \u00b5\u00012\u00020\u0001:\u0006\u00b6\u0001\u00b7\u0001\u00b5\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u0003J\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0003J\r\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0017\u0010\u0013J=\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0006\u00a2\u0006\u0004\b#\u0010\u0016J%\u0010$\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011\u00a2\u0006\u0004\b$\u0010%J\u0017\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\b(\u0010)J-\u00100\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u00112\u0006\u0010,\u001a\u00020+2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-H\u0002\u00a2\u0006\u0004\b0\u00101J-\u00103\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u00102\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011\u00a2\u0006\u0004\b3\u00104J\u000f\u00105\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b5\u0010\u0003J/\u00107\u001a\u00020\u00042\u0006\u00106\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b7\u00108JO\u0010<\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010;\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b<\u0010=J_\u0010A\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00112\u0006\u0010>\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010;\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010?\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bA\u0010BJ/\u0010C\u001a\u00020\u00042\u0006\u0010,\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bC\u00108Jo\u0010M\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010D\u001a\u00020.2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010F\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010K\u001a\u00020\u00112\u0006\u0010'\u001a\u00020&2\u0006\u0010L\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bM\u0010NJ'\u0010O\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bO\u0010PJ_\u0010R\u001a\u00020\u00042\u0006\u0010D\u001a\u00020.2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010F\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010Q\u001a\u00020\u00112\u0006\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\bR\u0010SJ7\u0010U\u001a\u00020\u00042\u0006\u0010D\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010T\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bU\u0010VJ/\u0010Y\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\u00112\u0006\u0010X\u001a\u00020\u00112\u0006\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\bY\u0010ZJ7\u0010[\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\u00112\u0006\u0010X\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b[\u0010\\J\u001d\u0010]\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u0011\u00a2\u0006\u0004\b]\u0010^J\r\u0010_\u001a\u00020\u0004\u00a2\u0006\u0004\b_\u0010\u0003J\u000f\u0010`\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b`\u0010\u0013J7\u0010d\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u00106\u001a\u00020+2\u0006\u0010a\u001a\u00020\u00112\u0006\u0010b\u001a\u00020\u00112\u0006\u0010c\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bd\u0010eJ'\u0010f\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bf\u0010%J%\u0010h\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010g\u001a\u00020\u000e\u00a2\u0006\u0004\bh\u0010iJ'\u0010k\u001a\u00020\u00042\u0006\u0010D\u001a\u00020.2\u0006\u0010j\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\bk\u0010lJ\u001d\u0010m\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u0011\u00a2\u0006\u0004\bm\u0010^J'\u0010n\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020\u00112\u0006\u0010g\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bn\u0010iJ\u000f\u0010o\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bo\u0010\u0003J+\u0010t\u001a\u00020\u00042\u0006\u0010q\u001a\u00020p2\b\u0010r\u001a\u0004\u0018\u00010.2\b\u0010s\u001a\u0004\u0018\u00010+H\u0002\u00a2\u0006\u0004\bt\u0010uJ\u000f\u0010v\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bv\u0010\u0016J\u0017\u0010x\u001a\u00020+2\u0006\u0010w\u001a\u00020pH\u0002\u00a2\u0006\u0004\bx\u0010yJ\u000f\u0010z\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bz\u0010\u0003J\u0015\u0010}\u001a\u00020\u00062\u0006\u0010|\u001a\u00020{\u00a2\u0006\u0004\b}\u0010~J\u0018\u0010\u0080\u0001\u001a\u00020\u00062\u0006\u0010|\u001a\u00020\u007f\u00a2\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J\u0018\u0010\u0082\u0001\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u000e\u00a2\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001J#\u0010\u0087\u0001\u001a\u00020\u00042\b\u0010\u0085\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0086\u0001\u001a\u00020\u0011\u00a2\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0018\u0010\u008a\u0001\u001a\u00030\u0089\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0018\u0010\u008d\u0001\u001a\u00030\u008c\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R$\u0010\u0090\u0001\u001a\u000f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00110\u008f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0093\u0001\u001a\u00030\u0092\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u001f\u0010\u0097\u0001\u001a\n\u0012\u0005\u0012\u00030\u0096\u00010\u0095\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001R\u001f\u0010\u0099\u0001\u001a\n\u0012\u0005\u0012\u00030\u0096\u00010\u0095\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0098\u0001R$\u0010\u009a\u0001\u001a\u000f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00110\u008f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0091\u0001R$\u0010\u009b\u0001\u001a\u000f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00110\u008f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0091\u0001R\u0019\u0010\u009c\u0001\u001a\u00020p8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0019\u0010\u009e\u0001\u001a\u00020p8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009d\u0001R\u001b\u0010\u009f\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u00a0\u0001R\u001a\u0010\u00a2\u0001\u001a\u00030\u00a1\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u00a3\u0001R\u0019\u0010\u0087\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u00a4\u0001R\u0019\u0010\u00a5\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u00a4\u0001R\u0019\u0010\u00a6\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u00a4\u0001R\u001a\u0010\u00a7\u0001\u001a\u00030\u00a1\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a7\u0001\u0010\u00a3\u0001R\u001b\u0010\u00a8\u0001\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u00a9\u0001R\u0019\u0010\u00aa\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00aa\u0001\u0010\u00a4\u0001R\u0019\u0010«\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00a4\u0001R\u0019\u0010\u00ac\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u00a4\u0001R\u0019\u0010\u00ad\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u00a4\u0001R\u001b\u0010\u00ae\u0001\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00a9\u0001R\u0019\u0010\u00af\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00af\u0001\u0010\u00a4\u0001R\u0019\u0010\u00b0\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u00a4\u0001R\u001b\u0010\u00b1\u0001\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00a9\u0001R\u0019\u0010\u00b2\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00a4\u0001R\u0019\u0010\u00b3\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00a4\u0001R\u0019\u0010\u00b4\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00a4\u0001\u00a8\u0006\u00b8\u0001"}, d2={"Lrtx/kimiko/api/ui/configs/ConfigsRenderer;", "", "<init>", "()V", "", "open", "", "value", "setAppearComposite", "(Z)V", "resetCardBlur", "", "cardBlurRects", "()[F", "", "cardBlurCount", "()I", "", "cardBlurMaxPhase", "()F", "finishTransition", "isModalOpen", "()Z", "currentScroll", "Lnet/minecraft/DrawContext;", "g", "x", "y", "w", "alpha", "dt", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "updateTooltip", "(F)V", "hasOverlay", "renderOverlay", "(Lnet/minecraft/DrawContext;FF)V", "Lrtx/kimiko/api/config/ConfigLibrary;", "library", "rebuildRows", "(Lrtx/kimiko/api/config/ConfigLibrary;)V", "cursorIn", "", "title", "", "Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "entries", "addSection", "(FLjava/lang/String;Ljava/util/List;)F", "dy", "renderHeaderDetails", "(Lnet/minecraft/DrawContext;FFF)V", "openConfigsFolder", "text", "headerTooltip", "(Ljava/lang/String;FFF)V", "h", "glyph", "hovered", "drawIconButton", "(FFFFLjava/lang/String;FZF)V", "label", "enabled", "accent", "drawPill", "(FFFFLjava/lang/String;FZFZZ)V", "renderSection", "entry", "cx", "cy", "cw", "inList", "mx", "my", "hoverFactor", "row", "renderCard", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/config/ConfigLibrary$Entry;FFFFZFFFLrtx/kimiko/api/config/ConfigLibrary;I)V", "renderBadge", "(FFF)V", "hoverT", "renderActions", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;FFFFFZFFLrtx/kimiko/api/config/ConfigLibrary;)V", "size", "renderAvatar", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;FFFF)V", "areaY", "areaH", "renderEmpty", "(FFFLrtx/kimiko/api/config/ConfigLibrary;)V", "renderScrollBar", "(FFFFF)V", "scrollbarGrab", "(FF)Z", "scrollbarRelease", "modalProgress", "centerX", "anchorY", "alphaIn", "renderTooltip", "(Lnet/minecraft/DrawContext;Ljava/lang/String;FFF)V", "renderModal", "button", "click", "(FFI)Z", "action", "onAction", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;ILrtx/kimiko/api/config/ConfigLibrary;)V", "rightClick", "clickModal", "confirmModal", "Lrtx/kimiko/api/ui/configs/ConfigsRenderer$Modal;", "type", "target", "initial", "openModal", "(Lrtx/kimiko/api/ui/configs/ConfigsRenderer$Modal;Lrtx/kimiko/api/config/ConfigLibrary$Entry;Ljava/lang/String;)V", "isConfirm", "shown", "confirmLabel", "(Lrtx/kimiko/api/ui/configs/ConfigsRenderer$Modal;)Ljava/lang/String;", "closeModal", "Lnet/minecraft/KeyInput;", "event", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "Lnet/minecraft/CharInput;", "charTyped", "(Lnet/minecraft/CharInput;)Z", "mouseReleased", "(I)V", "", "amount", "viewH", "scroll", "(DF)V", "Lrtx/kimiko/api/ui/module/SearchField;", "input", "Lrtx/kimiko/api/ui/module/SearchField;", "Lrtx/kimiko/api/ui/ScrollBar;", "scrollBar", "Lrtx/kimiko/api/ui/ScrollBar;", "", "hoverAnims", "Ljava/util/Map;", "Lrtx/kimiko/api/ui/CardAppear;", "appear", "Lrtx/kimiko/api/ui/CardAppear;", "", "Lrtx/kimiko/api/ui/configs/ConfigsRenderer$Row;", "rows", "Ljava/util/List;", "exiting", "rowY", "exitT", "modal", "Lrtx/kimiko/api/ui/configs/ConfigsRenderer$Modal;", "shownModal", "modalTarget", "Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "", "modalStartMs", "J", "F", "scrollTarget", "contentH", "knownVersion", "silentExitKey", "Ljava/lang/String;", "listX", "listY", "listW", "listH", "tooltip", "tooltipX", "tooltipY", "shownTooltip", "tooltipT", "tooltipDrawX", "tooltipDrawY", "Companion", "Modal", "Row", "rtx.kimiko:kimiko"})
public final class ConfigsRenderer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SearchField input = new SearchField();
    @NotNull
    private final ScrollBar scrollBar = new ScrollBar();
    @NotNull
    private final Map<String, Float> hoverAnims = new HashMap();
    @NotNull
    private final CardAppear appear = new CardAppear();
    @NotNull
    private final List<Row> rows = new ArrayList();
    @NotNull
    private final List<Row> exiting = new ArrayList();
    @NotNull
    private final Map<String, Float> rowY = new HashMap();
    @NotNull
    private final Map<String, Float> exitT = new HashMap();
    @NotNull
    private Modal modal = Modal.NONE;
    @NotNull
    private Modal shownModal = Modal.NONE;
    @Nullable
    private ConfigLibrary.Entry modalTarget;
    private long modalStartMs;
    private float scroll;
    private float scrollTarget;
    private float contentH;
    private long knownVersion = -1L;
    @Nullable
    private String silentExitKey;
    private float listX;
    private float listY;
    private float listW;
    private float listH;
    @Nullable
    private String tooltip;
    private float tooltipX;
    private float tooltipY;
    @Nullable
    private String shownTooltip;
    private float tooltipT;
    private float tooltipDrawX;
    private float tooltipDrawY;
    private static final float CONTENT_Y_OFFSET = 5.0f;
    private static final float HEADER_H = ModuleListRenderer.HEADER_OFFSET;
    private static final float PAD = 4.0f;
    private static final float CARD_H = 34.0f;
    private static final float CARD_GAP = 4.0f;
    private static final float SECTION_H = 13.0f;
    private static final float SECTION_GAP = 8.0f;
    private static final float TOP_PAD = 2.0f;
    private static final float ICON_BOX = 15.0f;
    private static final float ICON_GAP = 2.0f;
    private static final float MODAL_RADIUS = 8.0f;
    private static final float MODAL_OPEN_SCALE = 1.25f;
    private static final float MODAL_CLOSE_SCALE = 0.75f;
    private static final long MODAL_OPEN_MS = 450L;
    private static final long MODAL_CLOSE_MS = 350L;
    private static final float AVATAR_SIZE = 18.0f;
    private static final float BADGE_SIZE = 10.0f;
    private static final float CARD_EXIT_RATE = 9.0f;
    private static final float CARD_RADIUS = 6.0f;
    private static final float OVERLAY_RADIUS_BONUS = 1.5f;
    private static final float CORNER_BLEND = 16.0f;
    private static final float TOOLTIP_TAIL = 6.0f;
    private static final float TOOLTIP_TAIL_DROP = 5.0f;
    private static final float TOOLTIP_TAIL_ROUND = 1.5f;
    private static final float TOOLTIP_BLEND = 3.0f;
    private static final float TOOLTIP_OPEN_RATE = 25.0f;
    private static final float TOOLTIP_CLOSE_RATE = 35.0f;
    private static final float TOOLTIP_SWAP_POINT = 0.3f;
    private static final float TOOLTIP_RADIUS = 5.0f;
    private static final float CREATE_W = 48.0f;
    private static final float IMPORT_W = 46.0f;
    private static final float RESET_W = 52.0f;
    private static final float FOLDER_W = 15.0f;
    private static final float ACTION_RIGHT_PAD = 5.0f;
    @NotNull
    private static final String[] ACTION_ICONS;
    @NotNull
    private static final float[] ACTION_ICON_SIZE;
    @NotNull
    private static final float[] ACTION_ICON_X;
    @NotNull
    private static final float[] ACTION_ICON_Y;
    @NotNull
    private static final float[] ACTION_BOX_X;
    @NotNull
    private static final float[] ACTION_BOX_Y;
    @NotNull
    private static final String ICON_CLOUD = "e";
    @NotNull
    private static final String ICON_TITLE = "w";
    private static final DateTimeFormatter DATE_FORMAT;

    public final void open() {
        ConfigLibrary.Companion.get().open();
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
        this.appear.restart(false);
        this.knownVersion = -1L;
        this.closeModal();
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

    public final void finishTransition() {
    }

    public final boolean isModalOpen() {
        return this.modal != Modal.NONE;
    }

    public final float currentScroll() {
        return this.scroll;
    }

    public final void render(@NotNull DrawContext g, float x, float y, float w, float alpha, float dt) {
        String key;
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        this.listX = x + 117.0f;
        this.listY = y + 5.0f;
        this.listW = w - 122.0f;
        this.listH = 280.0f;
        ConfigLibrary library = ConfigLibrary.Companion.get();
        if (library.version() != this.knownVersion) {
            this.knownVersion = library.version();
            this.rebuildRows(library);
        }
        float factor = 1.0f - (float)Math.exp(-dt * 14.0f);
        this.scroll += (this.scrollTarget - this.scroll) * factor;
        if (Math.abs(this.scrollTarget - this.scroll) < 0.05f) {
            this.scroll = this.scrollTarget;
        }
        if (this.modal == Modal.NONE && this.shownModal != Modal.NONE && this.modalProgress() >= 1.0f) {
            this.shownModal = Modal.NONE;
            this.modalTarget = null;
            this.input.setText("");
        }
        this.tooltip = null;
        if (this.appear.composite() && this.appear.hasWork()) {
            GuiRenderState rs = ((GuiGraphicsExtractorAccessor)g).kimiko$getGuiRenderState();
            rs.createNewRootLayer();
            rs.applyBlur();
            UI.Companion.markCardStratum();
        }
        float areaY = this.listY + HEADER_H;
        float areaH = this.listH - HEADER_H;
        Render2D.pushScissor(g, this.listX, areaY - (float)4, this.listW, areaH + (float)4);
        RoundedScissor.push(g, this.listX, areaY - (float)4, this.listW - (float)4, areaH + (float)4, 0.0f, 0.0f, 5.0f, 0.0f);
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        boolean interactive = UI.Companion.isOpen() && this.modal == Modal.NONE;
        boolean inList = interactive && mx >= this.listX && mx <= this.listX + this.listW && my >= areaY && my <= areaY + areaH;
        float hoverFactor = interactive ? 1.0f - (float)Math.exp(-dt * 16.0f) : 0.0f;
        float reflow = 1.0f - (float)Math.exp(-dt * 16.0f);
        int cardIndex = 0;
        for (Row row : this.rows) {
            float ry = 0.0f;
            if (row.getHeader() || row.getEntry() == null) {
                ry = areaY + 2.0f + row.getY() - this.scroll;
            } else {
                ConfigLibrary.Entry entry = row.getEntry();
                Intrinsics.checkNotNull((Object)entry);
                key = entry.key();
                float cur = ((Number)this.rowY.getOrDefault(key, Float.valueOf(row.getY()))).floatValue();
                cur += (row.getY() - cur) * reflow;
                if (Math.abs(row.getY() - cur) < 0.05f) {
                    cur = row.getY();
                }
                this.rowY.put(key, Float.valueOf(cur));
                ry = areaY + 2.0f + cur - this.scroll;
            }
            if (ry + row.getH() < areaY - 6.0f || ry > areaY + areaH + 6.0f) continue;
            if (row.getHeader()) {
                String string = row.getSection();
                if (string == null) {
                    string = "";
                }
                this.renderSection(string, this.listX + 4.0f + 2.0f, ry, alpha);
                continue;
            }
            ConfigLibrary.Entry entry = row.getEntry();
            Intrinsics.checkNotNull((Object)entry);
            this.renderCard(g, entry, this.listX + 4.0f, ry, this.listW - 8.0f, alpha, inList, mx, my, hoverFactor, library, cardIndex++);
        }
        int n = ((Collection)this.exiting).size() + -1;
        if (0 <= n) {
            do {
                int i = n--;
                Row row = this.exiting.get(i);
                ConfigLibrary.Entry entry = row.getEntry();
                Intrinsics.checkNotNull((Object)entry);
                key = entry.key();
                float t = ((Number)this.exitT.getOrDefault(key, Float.valueOf(0.0f))).floatValue() - dt * 9.0f;
                if (t <= 0.0f) {
                    this.exiting.remove(i);
                    this.exitT.remove(key);
                    this.rowY.remove(key);
                    continue;
                }
                this.exitT.put(key, Float.valueOf(t));
                float ry = areaY + 2.0f + row.getY() - this.scroll;
                if (ry + row.getH() < areaY - 6.0f || ry > areaY + areaH + 6.0f) continue;
                ConfigLibrary.Entry entry2 = row.getEntry();
                Intrinsics.checkNotNull((Object)entry2);
                this.renderCard(g, entry2, this.listX + 4.0f, ry, this.listW - 8.0f, alpha * ConfigsRenderer.Companion.cubicOut(t), false, -1.0f, -1.0f, 0.0f, library, -1);
            } while (0 <= n);
        }
        if (this.rows.isEmpty() && this.exiting.isEmpty()) {
            this.renderEmpty(alpha, areaY, areaH, library);
        }
        RoundedScissor.pop();
        Render2D.popScissor(g);
        float maxScroll = Math.max(0.0f, this.contentH - (areaH - 4.0f));
        this.scrollTarget = RangesKt.coerceIn((float)this.scrollTarget, (float)0.0f, (float)maxScroll);
        this.scroll = RangesKt.coerceIn((float)this.scroll, (float)0.0f, (float)maxScroll);
        this.renderScrollBar(x, w, areaY, areaH, alpha);
        this.appear.frameDone();
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void updateTooltip(float dt) {
        boolean swapping = this.tooltip != null && this.shownTooltip != null && !Intrinsics.areEqual((Object)this.shownTooltip, (Object)this.tooltip);
        boolean visible = this.tooltip != null && !swapping;
        float rate = 1.0f - (float)Math.exp(-dt * (visible ? 25.0f : 35.0f));
        if (this.shownTooltip == null && this.tooltip != null) {
            this.shownTooltip = this.tooltip;
            this.tooltipDrawX = this.tooltipX;
            this.tooltipDrawY = this.tooltipY;
            visible = true;
        }
        this.tooltipT += ((visible ? 1.0f : 0.0f) - this.tooltipT) * rate;
        if (!visible) {
            float f = swapping ? 0.3f : 0.005f;
            if (this.tooltipT < f) {
                this.shownTooltip = this.tooltip;
                if (this.shownTooltip == null) {
                    this.tooltipT = 0.0f;
                    return;
                }
                this.tooltipDrawX = this.tooltipX;
                this.tooltipDrawY = this.tooltipY;
                return;
            }
        }
        if (!visible) return;
        this.tooltipDrawX += (this.tooltipX - this.tooltipDrawX) * rate;
        this.tooltipDrawY += (this.tooltipY - this.tooltipDrawY) * rate;
    }

    public final boolean hasOverlay() {
        return this.shownModal != Modal.NONE || this.tooltipT > 0.004f || this.tooltip != null;
    }

    public final void renderOverlay(@NotNull DrawContext g, float alpha, float dt) {
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        this.updateTooltip(dt);
        if (this.shownModal != Modal.NONE) {
            this.renderModal(g, alpha, dt);
        }
        if (this.tooltipT > 0.004f && this.shownTooltip != null) {
            String string = this.shownTooltip;
            Intrinsics.checkNotNull((Object)string);
            this.renderTooltip(g, string, this.tooltipDrawX, this.tooltipDrawY, alpha);
        }
    }

    private final void rebuildRows(ConfigLibrary library) {
        Map<String, Row> previous = new HashMap<String, Row>();
        for (Row row : this.rows) {
            if (row.getHeader() || row.getEntry() == null) continue;
            ConfigLibrary.Entry entry = row.getEntry();
            Intrinsics.checkNotNull((Object)entry);
            previous.put(entry.key(), row);
        }
        this.rows.clear();
        float cursor = 0.0f;
        cursor = this.addSection(cursor, I18n.tr("Мои конфиги"), library.localEntries());
        cursor = this.addSection(cursor, I18n.tr("В облаке"), library.remoteEntries());
        cursor = this.addSection(cursor, I18n.tr("Купленные"), library.premiumEntries());
        this.contentH = Math.max(0.0f, cursor);
        for (Row row : this.rows) {
            if (row.getHeader() || row.getEntry() == null) continue;
            ConfigLibrary.Entry entry = row.getEntry();
            Intrinsics.checkNotNull((Object)entry);
            previous.remove(entry.key());
        }
        for (Row gone : previous.values()) {
            ConfigLibrary.Entry entry = gone.getEntry();
            Intrinsics.checkNotNull((Object)entry);
            String key = entry.key();
            if (Intrinsics.areEqual((Object)key, (Object)this.silentExitKey)) {
                this.silentExitKey = null;
                this.rowY.remove(key);
                continue;
            }
            gone.setY(((Number)this.rowY.getOrDefault(key, Float.valueOf(gone.getY()))).floatValue());
            this.exiting.add(gone);
            this.exitT.put(key, Float.valueOf(1.0f));
        }
    }

    private final float addSection(float cursorIn, String title, List<ConfigLibrary.Entry> entries) {
        if (entries.isEmpty()) {
            return cursorIn;
        }
        float cursor = cursorIn;
        if (cursor > 0.0f) {
            cursor += 8.0f;
        }
        Row header = new Row();
        header.setHeader(true);
        header.setSection(title);
        header.setY(cursor);
        header.setH(13.0f);
        this.rows.add(header);
        cursor += 13.0f;
        for (ConfigLibrary.Entry entry : entries) {
            Row row = new Row();
            row.setEntry(entry);
            row.setY(cursor);
            row.setH(34.0f);
            this.rows.add(row);
            cursor += 38.0f;
        }
        return cursor - 4.0f;
    }

    public final void renderHeaderDetails(@NotNull DrawContext g, float alpha, float dy, float dt) {
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        if (alpha <= 0.004f) {
            return;
        }
        float hx = this.listX;
        float hy = this.listY + dy;
        float hw = this.listW;
        float hh = HEADER_H - 4.0f;
        float cy = hy + hh * 0.5f;
        float iconSize = 8.0f;
        float iconW = Fonts.KIMIKO.msdfWidth(ICON_TITLE, iconSize);
        AccentGradient.msdfIcon(Fonts.KIMIKO, ICON_TITLE, hx + 7.0f, cy - iconSize * 0.5f + 1.0f, iconSize, 225.0f * alpha, 0.5f);
        Fonts.MEDIUM.draw(I18n.tr("Конфигурации"), hx + 7.0f + iconW + 6.0f, cy - 3.5f, 7.0f, ConfigsRenderer.Companion.col(255, 255, 255, (float)235 * alpha));
        ConfigLibrary library = ConfigLibrary.Companion.get();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        boolean interactive = UI.Companion.isOpen() && this.modal == Modal.NONE;
        float bh = 13.0f;
        float by = cy - bh * 0.5f;
        float resetX = hx + hw - 7.0f - 52.0f;
        float importX = resetX - 4.0f - 46.0f;
        float createX = importX - 4.0f - 48.0f;
        float folderX = createX - 4.0f - 15.0f;
        boolean blocked = library.savingBlocked();
        this.drawIconButton(folderX, by, 15.0f, bh, ICON_TITLE, alpha, interactive && ConfigsRenderer.Companion.hit(mx, my, folderX, by, 15.0f, bh), dt);
        this.drawPill(createX, by, 48.0f, bh, I18n.tr("Создать"), alpha, interactive && ConfigsRenderer.Companion.hit(mx, my, createX, by, 48.0f, bh), dt, !blocked, true);
        this.drawPill(importX, by, 46.0f, bh, I18n.tr("По коду"), alpha, interactive && ConfigsRenderer.Companion.hit(mx, my, importX, by, 46.0f, bh), dt, true, false);
        this.drawPill(resetX, by, 52.0f, bh, I18n.tr("Сбросить"), alpha, interactive && ConfigsRenderer.Companion.hit(mx, my, resetX, by, 52.0f, bh), dt, true, false);
        if (interactive) {
            if (ConfigsRenderer.Companion.hit(mx, my, folderX, by, 15.0f, bh)) {
                this.headerTooltip(I18n.tr("Открыть папку с конфигами"), folderX, 15.0f, by);
            } else if (ConfigsRenderer.Companion.hit(mx, my, createX, by, 48.0f, bh)) {
                this.headerTooltip(blocked ? I18n.tr("Активен купленный конфиг") : I18n.tr("Сохранить текущие настройки как новый конфиг"), createX, 48.0f, by);
            } else if (ConfigsRenderer.Companion.hit(mx, my, importX, by, 46.0f, bh)) {
                this.headerTooltip(I18n.tr("Загрузить чужой конфиг по коду"), importX, 46.0f, by);
            } else if (ConfigsRenderer.Companion.hit(mx, my, resetX, by, 52.0f, bh)) {
                this.headerTooltip(blocked ? I18n.tr("Выйти и сбросить настройки") : I18n.tr("Сбросить всё до заводских"), resetX, 52.0f, by);
            }
        }
    }

    private final void openConfigsFolder() {
        try {
            Path dir = ConfigManager.Companion.profilesDirectory();
            Files.createDirectories(dir, new FileAttribute[0]);
            Util.getOperatingSystem().open(dir);
        }
        catch (Throwable throwable) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Не удалось открыть папку"), 2000L);
        }
    }

    private final void headerTooltip(String text, float x, float w, float y) {
        this.tooltip = text;
        this.tooltipX = x + w * 0.5f;
        this.tooltipY = y - 2.0f;
    }

    private final void drawIconButton(float x, float y, float w, float h, String glyph, float alpha, boolean hovered, float dt) {
        String id = "pill:icon:" + glyph;
        float hoverT = ((Number)this.hoverAnims.getOrDefault(id, Float.valueOf(0.0f))).floatValue();
        hoverT += ((hovered ? 1.0f : 0.0f) - hoverT) * (1.0f - (float)Math.exp(-dt * 16.0f));
        this.hoverAnims.put(id, Float.valueOf(hoverT));
        RenderHelper.drawPanelBg(x, y, w, h, 3.0f, alpha * (1.0f + 0.5f * hoverT));
        float size = 7.0f;
        float gw = Fonts.KIMIKO.msdfWidth(glyph, size);
        Fonts.KIMIKO.msdf(glyph, x + (w - gw) * 0.5f, y + (h - size) * 0.5f + 0.5f, size, ConfigsRenderer.Companion.col(255, 255, 255, ((float)170 + (float)70 * hoverT) * alpha));
    }

    private final void drawPill(float x, float y, float w, float h, String label, float alpha, boolean hovered, float dt, boolean enabled, boolean accent) {
        String id = "pill:" + label;
        float hoverT = ((Number)this.hoverAnims.getOrDefault(id, Float.valueOf(0.0f))).floatValue();
        hoverT += ((hovered && enabled ? 1.0f : 0.0f) - hoverT) * (1.0f - (float)Math.exp(-dt * 16.0f));
        this.hoverAnims.put(id, Float.valueOf(hoverT));
        float ea = enabled ? 1.0f : 0.5f;
        RenderHelper.drawPanelBg(x, y, w, h, 3.0f, alpha * (1.0f + 0.5f * hoverT));
        float size = 6.0f;
        float tw = Fonts.MEDIUM.width(label, size);
        float tx = x + (w - tw) * 0.5f;
        float ty = y + (h - 7.0f) * 0.5f;
        if (accent) {
            Fonts.MEDIUM.draw(label, tx, ty, size, ClientAccent.accentSoftAt(((float)200 + (float)55 * hoverT) * alpha * ea, x + w * 0.5f, y + h * 0.5f));
        } else {
            Fonts.MEDIUM.draw(label, tx, ty, size, ConfigsRenderer.Companion.col(255, 255, 255, ((float)170 + (float)60 * hoverT) * alpha * ea));
        }
    }

    private final void renderSection(String title, float x, float y, float alpha) {
        float size = 5.5f;
        Fonts.MEDIUM.draw(title, x, y + (13.0f - size) * 0.5f, size, ConfigsRenderer.Companion.col(255, 255, 255, (float)115 * alpha));
    }

    private final void renderCard(DrawContext g, ConfigLibrary.Entry entry, float cx, float cy, float cw, float alpha, boolean inList, float mx, float my, float hoverFactor, ConfigLibrary library, int row) {
        float nameLimit;
        String entryName;
        float nameW;
        float radius;
        float progress;
        String key = entry.key();
        boolean hovered = inList && ConfigsRenderer.Companion.hit(mx, my, cx, cy, cw, 34.0f);
        float hoverT = ((Number)this.hoverAnims.getOrDefault(key, Float.valueOf(0.0f))).floatValue();
        hoverT += ((hovered ? 1.0f : 0.0f) - hoverT) * hoverFactor;
        this.hoverAnims.put(key, Float.valueOf(hoverT));
        float f = progress = row < 0 ? 1.0f : this.appear.progress(row);
        if (progress < 0.001f) {
            return;
        }
        float settle = CardAppear.Companion.settle(progress);
        float presence = settle * settle;
        float phase = CardAppear.Companion.blurPhase(progress);
        float renderCy = cy + (1.0f - settle) * 6.0f;
        boolean appearing = progress < 0.999f;
        boolean compositeCard = this.appear.composite() && appearing;
        float ma = alpha * (!appearing || compositeCard ? 1.0f : presence);
        if (appearing) {
            if (compositeCard) {
                this.appear.push(cx, renderCy, cw, 34.0f, presence, phase);
            }
            float cardScale = 0.85f + 0.15f * settle;
            float ox = cx + cw * 0.5f;
            float oy = renderCy + 17.0f;
            g.getMatrices().pushMatrix();
            g.getMatrices().translate(ox, oy);
            g.getMatrices().scale(cardScale, cardScale);
            g.getMatrices().translate(-ox, -oy);
        }
        boolean active = Intrinsics.areEqual((Object)library.activeKey(), (Object)key);
        boolean premium = entry.premium();
        boolean readOnly = entry.kind() != ConfigLibrary.Kind.LOCAL;
        float rbr = radius = 6.0f;
        float contentBottom = this.listY + this.listH;
        if (renderCy + 34.0f > contentBottom - 16.0f) {
            float t = RangesKt.coerceIn((float)((renderCy + 34.0f - (contentBottom - 16.0f)) / 16.0f), (float)0.0f, (float)1.0f);
            rbr = radius + (12.0f - radius) * t;
        }
        float overlayRadius = radius + 1.5f;
        float overlayRbr = rbr + 1.5f;
        float activeT = ((Number)this.hoverAnims.getOrDefault(key + ":active", Float.valueOf(active ? 1.0f : 0.0f))).floatValue();
        this.hoverAnims.put(key + ":active", Float.valueOf(activeT += ((active ? 1.0f : 0.0f) - activeT) * (hoverFactor > 0.0f ? hoverFactor : 1.0f)));
        float tint = Math.max(activeT, premium ? 0.5f : 0.0f);
        float lift = Math.max(hoverT, activeT * 0.65f);
        RectUtil.drawGlassCard(cx, renderCy, cw, 34.0f, radius, radius, rbr, radius, ma, lift);
        Render2D.rect(cx, renderCy, cw, 34.0f, overlayRadius, overlayRadius, overlayRbr, overlayRadius, ThemeManager.rgba(0xFFFFFF, (7.0f + 5.0f * hoverT) * ma), ThemeManager.rgba(0xFFFFFF, (7.0f + 5.0f * hoverT) * ma), ThemeManager.rgba(0xFFFFFF, 1.5f * ma), ThemeManager.rgba(0xFFFFFF, 1.5f * ma));
        float sampleX = cx + cw * 0.5f;
        float sampleY = renderCy + 17.0f;
        if (tint > 0.01f) {
            int ga = ClientAccent.gradientAAt(22.0f * tint * ma, sampleX, sampleY);
            int gb = ClientAccent.gradientBAt(22.0f * tint * ma, sampleX, sampleY);
            Render2D.rect(cx, renderCy, cw, 34.0f, overlayRadius, overlayRadius, overlayRbr, overlayRadius, ga, gb, gb, ga);
        }
        int idleTop = ThemeManager.rgba(0xFFFFFF, (30.0f + 22.0f * hoverT) * ma);
        int idleBottom = ThemeManager.rgba(0xFFFFFF, (10.0f + 8.0f * hoverT) * ma);
        int accentTop = ClientAccent.gradientAAt(78.0f * ma, sampleX, sampleY);
        int accentBottom = ClientAccent.gradientBAt(52.0f * ma, sampleX, sampleY);
        int outTop = ColorEngine.lerpColor(idleTop, accentTop, tint);
        int outBottom = ColorEngine.lerpColor(idleBottom, accentBottom, tint);
        Render2D.outline(cx, renderCy, cw, 34.0f, overlayRadius, overlayRadius, overlayRbr, overlayRadius, 0.6f, outTop, outTop, outBottom, outBottom);
        float avatarX = cx + 8.0f;
        float avatarY = renderCy + 8.0f;
        this.renderAvatar(entry, avatarX, avatarY, 18.0f, ma);
        float textX = avatarX + 18.0f + 7.0f;
        float nameSize = 7.0f;
        float nameY = renderCy + 7.5f;
        int nameColor = premium ? ClientAccent.accentBrightAt(((float)235 + (float)20 * hoverT) * ma, textX, nameY) : ConfigsRenderer.Companion.col(255, 255, 255, ((float)215 + (float)35 * hoverT) * ma);
        boolean cloud = entry.cloud();
        String string = entry.name();
        if (string == null) {
            string = "";
        }
        if ((nameW = Fonts.MEDIUM.width(entryName = string, nameSize)) > (nameLimit = cx + cw - (readOnly ? 12.0f : 59.0f) - textX - (cloud ? 15.0f : 0.0f))) {
            RenderHelper.drawScrollingText(entryName, textX, nameY, nameLimit, nameSize, nameColor);
            nameW = nameLimit;
        } else {
            Fonts.MEDIUM.draw(entryName, textX, nameY, nameSize, nameColor);
        }
        if (cloud) {
            this.renderBadge(textX + nameW + 5.0f, nameY - 1.0f, ma);
        }
        String subtitleText = ConfigsRenderer.Companion.subtitle(entry);
        float subLimit = cx + cw - (readOnly ? 12.0f : 59.0f) - textX;
        RenderHelper.drawScrollingText(subtitleText, textX, renderCy + 19.0f, subLimit, 5.5f, ConfigsRenderer.Companion.col(255, 255, 255, (float)108 * ma));
        if (!readOnly) {
            this.renderActions(entry, cx, renderCy, cw, ma, hoverT, inList, mx, my, library);
        }
        if (appearing) {
            g.getMatrices().popMatrix();
        }
    }

    private final void renderBadge(float x, float y, float alpha) {
        AccentGradient.fillHorizontal(x, y, 10.0f, 10.0f, 3.0f, (float)62 * alpha);
        float iconSize = 5.5f;
        float iconW = Fonts.KIMIKO.msdfWidth(ICON_CLOUD, iconSize);
        AccentGradient.msdfIcon(Fonts.KIMIKO, ICON_CLOUD, x + (10.0f - iconW) * 0.5f, y + (10.0f - iconSize) * 0.5f, iconSize, 225.0f * alpha, 0.5f);
    }

    private final void renderActions(ConfigLibrary.Entry entry, float cx, float cy, float cw, float alpha, float hoverT, boolean inList, float mx, float my, ConfigLibrary library) {
        String[] stringArray = new String[3];
        String string = stringArray[0] = library.savingBlocked() ? I18n.tr("Сохранение запрещено") : I18n.tr("Сохранить текущие настройки");
        stringArray[1] = entry.published() ? (Intrinsics.areEqual((Object)library.activeKey(), (Object)entry.key()) ? I18n.tr("Обновить на сервере (текущие настройки)") : I18n.tr("Обновить на сервере")) : I18n.tr("Поделиться (код)");
        stringArray[2] = I18n.tr("Удалить");
        String[] hints = stringArray;
        float bx = cx + cw - 5.0f - 15.0f;
        float by = cy + 9.5f;
        int n = ACTION_ICONS.length + -1;
        if (0 <= n) {
            do {
                int i;
                boolean enabled = (i = n--) != 0 || !library.savingBlocked();
                boolean over = inList && ConfigsRenderer.Companion.hit(mx, my, bx + ConfigsRenderer.ACTION_BOX_X[i], by + ConfigsRenderer.ACTION_BOX_Y[i], 15.0f, 15.0f);
                String id = entry.key() + ":act" + i;
                float t = ((Number)this.hoverAnims.getOrDefault(id, Float.valueOf(0.0f))).floatValue();
                t += ((over ? 1.0f : 0.0f) - t) * 0.5f;
                this.hoverAnims.put(id, Float.valueOf(t));
                float ea = enabled ? 1.0f : 0.5f;
                float boxAlpha = ((float)20 + (float)40 * t) * alpha * (0.5f + 0.5f * hoverT);
                Render2D.rect(bx + ACTION_BOX_X[i], by + ACTION_BOX_Y[i], 15.0f, 15.0f, 3.5f, ThemeManager.rgba(0xFFFFFF, boxAlpha));
                String glyph = ACTION_ICONS[i];
                float glyphSize = ACTION_ICON_SIZE[i];
                float gw = Fonts.KIMIKO.msdfWidth(glyph, glyphSize);
                float gx = bx + ACTION_BOX_X[i] + (15.0f - gw) * 0.5f + ACTION_ICON_X[i];
                float gy = by + ACTION_BOX_Y[i] + (15.0f - glyphSize) * 0.5f + ACTION_ICON_Y[i];
                float glyphAlpha = ((float)185 + (float)70 * t) * alpha * ea;
                if (i == 1 && entry.published()) {
                    AccentGradient.msdfIcon(Fonts.KIMIKO, glyph, gx, gy, glyphSize, glyphAlpha, 0.5f);
                } else {
                    Fonts.KIMIKO.msdf(glyph, gx, gy, glyphSize, ConfigsRenderer.Companion.col(255, 255, 255, glyphAlpha));
                }
                if (over) {
                    this.tooltip = hints[i];
                    this.tooltipX = bx + ACTION_BOX_X[i] + 7.5f;
                    this.tooltipY = by + ACTION_BOX_Y[i] - 2.0f;
                }
                bx -= 17.0f;
            } while (0 <= n);
        }
    }

    private final void renderAvatar(ConfigLibrary.Entry entry, float x, float y, float size, float alpha) {
        String url;
        CharSequence charSequence;
        String texture = entry.premium() ? ((charSequence = (CharSequence)(url = entry.ownerAvatar())) == null || StringsKt.isBlank((CharSequence)charSequence) ? null : RemoteAvatars.texture(url)) : DiscordAvatar.texture();
        float radius = size * 0.5f;
        if (texture != null && Render2D.imageReady(texture)) {
            int white = RangesKt.coerceIn((int)MathKt.roundToInt((float)(alpha * 255.0f)), (int)0, (int)255) << 24 | 0xFFFFFF;
            Render2D.image(texture, x, y, size, size, radius, white);
            return;
        }
        Render2D.rect(x, y, size, size, radius, ThemeManager.rgba(0xFFFFFF, (float)18 * alpha));
        String initial = ConfigsRenderer.Companion.initialOf(entry.ownerName());
        float letterSize = 6.5f;
        float lw = Fonts.MEDIUM.width(initial, letterSize);
        Fonts.MEDIUM.draw(initial, x + (size - lw) * 0.5f, y + (size - letterSize) * 0.5f, letterSize, ConfigsRenderer.Companion.col(255, 255, 255, (float)150 * alpha));
    }

    private final void renderEmpty(float alpha, float areaY, float areaH, ConfigLibrary library) {
        String message = library.remoteLoading() ? I18n.tr("Загрузка...") : I18n.tr("Конфигов пока что нету");
        float size = 6.5f;
        float tw = Fonts.MEDIUM.width(message, size);
        Fonts.MEDIUM.draw(message, this.listX + (this.listW - tw) * 0.5f, areaY + areaH * 0.5f - 3.0f, size, ConfigsRenderer.Companion.col(255, 255, 255, (float)105 * alpha));
    }

    private final void renderScrollBar(float x, float w, float areaY, float areaH, float alpha) {
        float trackX = x + w - 7.5f;
        float effR = RenderHelper.effectiveCornerRadius(12.0f, this.listW, 280.0f);
        float botExtra = Math.max(0.0f, RenderHelper.cornerEdgeInset(effR, 1.0f) + 1.5f - 3.0f);
        float trackY = areaY + 3.0f;
        float trackH = areaH - 6.0f - botExtra;
        float viewH = areaH - 4.0f;
        float newScroll = this.scrollBar.render(trackX, trackY, trackH, viewH, this.contentH, this.scroll, alpha);
        if (this.scrollBar.isDragging()) {
            this.scroll = newScroll;
            this.scrollTarget = newScroll;
        }
    }

    public final boolean scrollbarGrab(float mx, float my) {
        return this.modal == Modal.NONE && this.scrollBar.tryGrab(mx, my);
    }

    public final void scrollbarRelease() {
        this.scrollBar.release();
    }

    private final float modalProgress() {
        long duration = this.modal == Modal.NONE ? 350L : 450L;
        return RangesKt.coerceIn((float)((float)(System.currentTimeMillis() - this.modalStartMs) / (float)duration), (float)0.0f, (float)1.0f);
    }

    private final void renderTooltip(DrawContext g, String text, float centerX, float anchorY, float alphaIn) {
        float app = RangesKt.coerceIn((float)this.tooltipT, (float)0.0f, (float)1.0f);
        float scale = 0.5f + 0.5f * ConfigsRenderer.Companion.easeOutBack(app);
        float alpha = alphaIn * app;
        float size = 5.5f * scale;
        float tw = Fonts.MEDIUM.width(text, size);
        float bw = tw + 13.0f * scale;
        float bh = 14.0f * scale;
        float drop = 5.0f * scale;
        float bx = centerX - bw * 0.5f;
        float by = anchorY - drop - bh;
        RectUtil.drawClientRectWithTail(bx, by, bw, bh, 5.0f * scale, alpha, centerX, 6.0f * scale, drop, 1.5f * scale, 3.0f * scale);
        Fonts.MEDIUM.draw(text, bx + (bw - tw) * 0.5f, by + (bh - size) * 0.5f, size, ConfigsRenderer.Companion.col(255, 255, 255, (float)235 * alpha));
    }

    private final void renderModal(DrawContext g, float alpha, float dt) {
        Modal shown = this.shownModal;
        if (shown == Modal.NONE) {
            return;
        }
        boolean confirm = shown == Modal.CONFIRM_DELETE || shown == Modal.CONFIRM_UNLOCK;
        boolean closing = this.modal == Modal.NONE;
        float bw = 196.0f;
        float bh = confirm ? 74.0f : 86.0f;
        float bx = this.listX + (this.listW - bw) * 0.5f;
        float by = this.listY + (this.listH - bh) * 0.5f;
        float p = this.modalProgress();
        float s = closing ? 1.0f - 0.25f * ConfigsRenderer.Companion.cubicIn(p) : 1.25f - 0.25f * ConfigsRenderer.Companion.quintOut(p);
        float ma = alpha * (closing ? 1.0f - ConfigsRenderer.Companion.cubicIn(p) : ConfigsRenderer.Companion.cubicOut(p));
        float gw = bw * s;
        float gh = bh * s;
        float gx = bx + (bw - gw) * 0.5f;
        float gy = by + (bh - gh) * 0.5f;
        RectUtil.drawClientRectFixedRadius(gx, gy, gw, gh, 8.0f * s, ma, 0.0f);
        Render2D.outline(gx, gy, gw, gh, 8.0f * s, 0.6f, ThemeManager.rgba(0xFFFFFF, 24.0f * ma));
        String title = switch (WhenMappings.$EnumSwitchMapping$0[shown.ordinal()]) {
            case 1 -> I18n.tr("Новый конфиг");
            case 2 -> I18n.tr("Переименовать");
            case 3 -> I18n.tr("Импорт по коду");
            case 4 -> I18n.tr("Удалить конфиг?");
            case 5 -> I18n.tr("Сбросить настройки?");
            default -> "";
        };
        Fonts.MEDIUM.draw(title, gx + 12.0f * s, gy + 12.0f * s, 7.0f * s, ConfigsRenderer.Companion.col(255, 255, 255, (float)235 * ma));
        if (confirm) {
            String name = (this.modalTarget != null && this.modalTarget.name() != null) ? this.modalTarget.name() : "";
            String line = (shown == Modal.CONFIRM_DELETE)
                ? I18n.tr("«%s» будет удалён безвозвратно.", new Object[]{name})
                : I18n.tr("Все модули и бинды вернутся к заводским.");
            RenderHelper.drawScrollingText(line, gx + 12.0f * s, gy + 30.0f * s, gw - 24.0f * s, 5.5f * s, ConfigsRenderer.Companion.col(255, 255, 255, (float)150 * ma));
        } else {
            this.input.render(g, gx + 12.0f * s, gy + 32.0f * s, gw - 24.0f * s, 15.0f * s, ma, Position.Companion.mouseX(), Position.Companion.mouseY(), dt);
        }
        float btnH = 15.0f * s;
        float btnW = 62.0f * s;
        float btnY = gy + gh - btnH - 12.0f * s;
        float okX = gx + gw - 12.0f * s - btnW;
        float cancelX = okX - 6.0f * s - btnW;
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        this.drawPill(cancelX, btnY, btnW, btnH, I18n.tr("Отмена"), ma, !closing && ConfigsRenderer.Companion.hit(mx, my, cancelX, btnY, btnW, btnH), dt, true, false);
        this.drawPill(okX, btnY, btnW, btnH, this.confirmLabel(shown), ma, !closing && ConfigsRenderer.Companion.hit(mx, my, okX, btnY, btnW, btnH), dt, true, true);
    }

    public final boolean click(float mx, float my, int button) {
        if (this.modal != Modal.NONE) {
            return this.clickModal(mx, my, button);
        }
        if (button != 0) {
            return false;
        }
        ConfigLibrary library = ConfigLibrary.Companion.get();
        float resetX = this.listX + this.listW - 7.0f - 52.0f;
        float importX = resetX - 4.0f - 46.0f;
        float createX = importX - 4.0f - 48.0f;
        float folderX = createX - 4.0f - 15.0f;
        float cy = this.listY + (HEADER_H - 4.0f) * 0.5f;
        float bh = 13.0f;
        float by = cy - bh * 0.5f;
        if (ConfigsRenderer.Companion.hit(mx, my, folderX, by, 15.0f, bh)) {
            this.openConfigsFolder();
            return true;
        }
        if (ConfigsRenderer.Companion.hit(mx, my, createX, by, 48.0f, bh)) {
            if (library.savingBlocked()) {
                Notifications.push(I18n.tr("Конфиги"), I18n.tr("Активен купленный конфиг — сохранение запрещено"), 2400L);
                return true;
            }
            this.openModal(Modal.CREATE, null, ConfigManager.Companion.nextProfileName());
            return true;
        }
        if (ConfigsRenderer.Companion.hit(mx, my, importX, by, 46.0f, bh)) {
            this.openModal(Modal.IMPORT, null, "");
            return true;
        }
        if (ConfigsRenderer.Companion.hit(mx, my, resetX, by, 52.0f, bh)) {
            this.openModal(Modal.CONFIRM_UNLOCK, null, "");
            return true;
        }
        float areaY = this.listY + HEADER_H;
        float areaH = this.listH - HEADER_H;
        if (mx < this.listX || mx > this.listX + this.listW || my < areaY || my > areaY + areaH) {
            return false;
        }
        for (Row row : this.rows) {
            float cw;
            float ry;
            float cx;
            if (row.getHeader() || row.getEntry() == null || !ConfigsRenderer.Companion.hit(mx, my, cx = this.listX + 4.0f, ry = areaY + 2.0f + row.getY() - this.scroll, cw = this.listW - 8.0f, 34.0f)) continue;
            float bx = cx + cw - 7.0f - 15.0f;
            float iy = ry + 9.5f;
            ConfigLibrary.Entry entry = row.getEntry();
            Intrinsics.checkNotNull((Object)entry);
            if (entry.kind() == ConfigLibrary.Kind.LOCAL) {
                for (int i = 2; -1 < i; --i) {
                    if (ConfigsRenderer.Companion.hit(mx, my, bx, iy, 15.0f, 15.0f)) {
                        ConfigLibrary.Entry entry2 = row.getEntry();
                        Intrinsics.checkNotNull((Object)entry2);
                        this.onAction(entry2, i, library);
                        return true;
                    }
                    bx -= 17.0f;
                }
            }
            ConfigLibrary.Entry entry3 = row.getEntry();
            Intrinsics.checkNotNull((Object)entry3);
            if (entry3.kind() == ConfigLibrary.Kind.REMOTE) {
                ConfigLibrary.Entry entry4 = row.getEntry();
                Intrinsics.checkNotNull((Object)entry4);
                this.silentExitKey = entry4.key();
                ConfigLibrary.Entry entry5 = row.getEntry();
                Intrinsics.checkNotNull((Object)entry5);
                library.download(entry5);
                Sounds.play("select_category");
                return true;
            }
            ConfigLibrary.Entry entry6 = row.getEntry();
            Intrinsics.checkNotNull((Object)entry6);
            library.apply(entry6);
            Sounds.play("select_category");
            return true;
        }
        return false;
    }

    private final void onAction(ConfigLibrary.Entry entry, int action, ConfigLibrary library) {
        switch (action) {
            case 0: {
                library.saveInto(entry);
                break;
            }
            case 1: {
                if (entry.published()) {
                    library.publish(entry);
                    library.copyCode(entry);
                    break;
                }
                library.publish(entry);
                break;
            }
            case 2: {
                this.openModal(Modal.CONFIRM_DELETE, entry, "");
            }
        }
    }

    public final boolean rightClick(float mx, float my) {
        if (this.modal != Modal.NONE) {
            return true;
        }
        float areaY = this.listY + HEADER_H;
        for (Row row : this.rows) {
            float ry;
            if (row.getHeader() || row.getEntry() == null) continue;
            ConfigLibrary.Entry entry = row.getEntry();
            Intrinsics.checkNotNull((Object)entry);
            if (entry.kind() != ConfigLibrary.Kind.LOCAL || !ConfigsRenderer.Companion.hit(mx, my, this.listX + 4.0f, ry = areaY + 2.0f + row.getY() - this.scroll, this.listW - 8.0f, 34.0f)) continue;
            ConfigLibrary.Entry entry2 = row.getEntry();
            ConfigLibrary.Entry entry3 = row.getEntry();
            Intrinsics.checkNotNull((Object)entry3);
            this.openModal(Modal.RENAME, entry2, entry3.name());
            return true;
        }
        return false;
    }

    private final boolean clickModal(float mx, float my, int button) {
        if (button != 0) {
            return true;
        }
        float bw = 196.0f;
        float bh = this.isConfirm() ? 74.0f : 86.0f;
        float bx = this.listX + (this.listW - bw) * 0.5f;
        float by = this.listY + (this.listH - bh) * 0.5f;
        float btnH = 15.0f;
        float btnY = by + bh - btnH - 12.0f;
        float btnW = 62.0f;
        float okX = bx + bw - 12.0f - btnW;
        float cancelX = okX - 6.0f - btnW;
        if (ConfigsRenderer.Companion.hit(mx, my, okX, btnY, btnW, btnH)) {
            this.confirmModal();
            return true;
        }
        if (ConfigsRenderer.Companion.hit(mx, my, cancelX, btnY, btnW, btnH)) {
            this.closeModal();
            return true;
        }
        if (!this.isConfirm()) {
            this.input.mouseClicked(mx, my, button);
        }
        if (!ConfigsRenderer.Companion.hit(mx, my, bx, by, bw, bh)) {
            this.closeModal();
        }
        return true;
    }

    private final void confirmModal() {
        ConfigLibrary library = ConfigLibrary.Companion.get();
        switch (WhenMappings.$EnumSwitchMapping$0[this.modal.ordinal()]) {
            case 1: {
                library.createFromCurrent(this.input.getText());
                break;
            }
            case 2: {
                library.rename(this.modalTarget, this.input.getText());
                break;
            }
            case 3: {
                library.importByCode(this.input.getText());
                break;
            }
            case 4: {
                library.delete(this.modalTarget);
                break;
            }
            case 5: {
                ConfigManager.Companion.resetToFactory();
                library.reloadLocal();
            }
        }
        this.closeModal();
    }

    private final void openModal(Modal type, ConfigLibrary.Entry target, String initial) {
        this.modal = type;
        this.shownModal = type;
        this.modalStartMs = System.currentTimeMillis();
        this.modalTarget = target;
        String string = initial;
        if (string == null) {
            string = "";
        }
        this.input.setText(string);
        this.input.placeholder(type == Modal.IMPORT ? "Код конфига" : "Название");
        this.input.icon(type == Modal.IMPORT ? ACTION_ICONS[1] : ICON_TITLE);
        if (this.isConfirm()) {
            this.input.blur();
        } else {
            this.input.focus();
        }
    }

    private final boolean isConfirm() {
        return this.modal == Modal.CONFIRM_DELETE || this.modal == Modal.CONFIRM_UNLOCK;
    }

    private final String confirmLabel(Modal shown) {
        return switch (WhenMappings.$EnumSwitchMapping$0[shown.ordinal()]) {
            case 4 -> I18n.tr("Удалить");
            case 5 -> I18n.tr("Сбросить");
            default -> I18n.tr("Готово");
        };
    }

    private final void closeModal() {
        if (this.modal != Modal.NONE) {
            this.modalStartMs = System.currentTimeMillis();
        }
        this.modal = Modal.NONE;
        this.input.blur();
    }

    public final boolean keyPressed(@NotNull KeyInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.modal == Modal.NONE) {
            return false;
        }
        if (event.key() == 256) {
            this.closeModal();
            return true;
        }
        if (event.key() == 257 || event.key() == 335) {
            this.confirmModal();
            return true;
        }
        if (!this.isConfirm() && this.input.keyPressed(event)) {
            return true;
        }
        return true;
    }

    public final boolean charTyped(@NotNull CharInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.modal == Modal.NONE || this.isConfirm()) {
            return false;
        }
        this.input.charTyped(event);
        return true;
    }

    public final void mouseReleased(int button) {
        this.scrollBar.release();
        this.input.mouseReleased(button);
    }

    public final void scroll(double amount, float viewH) {
        float max = Math.max(0.0f, this.contentH - (viewH - HEADER_H - 4.0f));
        this.scrollTarget = RangesKt.coerceIn((float)(this.scrollTarget - (float)amount * 20.0f), (float)0.0f, (float)max);
    }

    static {
        ACTION_ICONS = new String[]{"k", "g", "A"};
        ACTION_ICON_SIZE = new float[]{6.5f, 6.5f, 7.0f};
        ACTION_ICON_X = new float[]{0.0f, 0.5f, 1.0f};
        ACTION_ICON_Y = new float[]{0.5f, 0.0f, 0.0f};
        ACTION_BOX_X = new float[]{0.0f, 0.0f, 0.0f};
        ACTION_BOX_Y = new float[]{0.0f, 0.0f, 0.0f};
        DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b,\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\u0007J\u0019\u0010\r\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ?\u0010\"\u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\"\u0010#J/\u0010(\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010+R\u0014\u0010/\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010+R\u0014\u00100\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010+R\u0014\u00101\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010+R\u0014\u00102\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010+R\u0014\u00103\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u0010+R\u0014\u00104\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010+R\u0014\u00105\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010+R\u0014\u00106\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010+R\u0014\u00107\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010+R\u0014\u00108\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010+R\u0014\u0010<\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010+R\u0014\u0010=\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010+R\u0014\u0010>\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010+R\u0014\u0010?\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010+R\u0014\u0010@\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010+R\u0014\u0010A\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010+R\u0014\u0010B\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010+R\u0014\u0010C\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010+R\u0014\u0010D\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010+R\u0014\u0010E\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010+R\u0014\u0010F\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010+R\u0014\u0010G\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010+R\u0014\u0010H\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010+R\u0014\u0010I\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010+R\u0014\u0010J\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010+R\u0014\u0010K\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010+R\u0014\u0010L\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010+R\u0014\u0010M\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010+R\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020\u000b0N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010SR\u0014\u0010U\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010SR\u0014\u0010V\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010SR\u0014\u0010W\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010SR\u0014\u0010X\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010Z\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010YR\u001c\u0010]\u001a\n \\*\u0004\u0018\u00010[0[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010^\u00a8\u0006_"}, d2={"Lrtx/kimiko/api/ui/configs/ConfigsRenderer.Companion;", "", "<init>", "()V", "", "t", "easeOutBack", "(F)F", "quintOut", "cubicOut", "cubicIn", "", "name", "initialOf", "(Ljava/lang/String;)Ljava/lang/String;", "Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "entry", "subtitle", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;)Ljava/lang/String;", "", "timestamp", "date", "(J)Ljava/lang/String;", "", "count", "plural", "(I)Ljava/lang/String;", "mx", "my", "x", "y", "w", "h", "", "hit", "(FFFFFF)Z", "r", "g", "b", "a", "col", "(IIIF)I", "CONTENT_Y_OFFSET", "F", "HEADER_H", "PAD", "CARD_H", "CARD_GAP", "SECTION_H", "SECTION_GAP", "TOP_PAD", "ICON_BOX", "ICON_GAP", "MODAL_RADIUS", "MODAL_OPEN_SCALE", "MODAL_CLOSE_SCALE", "MODAL_OPEN_MS", "J", "MODAL_CLOSE_MS", "AVATAR_SIZE", "BADGE_SIZE", "CARD_EXIT_RATE", "CARD_RADIUS", "OVERLAY_RADIUS_BONUS", "CORNER_BLEND", "TOOLTIP_TAIL", "TOOLTIP_TAIL_DROP", "TOOLTIP_TAIL_ROUND", "TOOLTIP_BLEND", "TOOLTIP_OPEN_RATE", "TOOLTIP_CLOSE_RATE", "TOOLTIP_SWAP_POINT", "TOOLTIP_RADIUS", "CREATE_W", "IMPORT_W", "RESET_W", "FOLDER_W", "ACTION_RIGHT_PAD", "", "ACTION_ICONS", "[Ljava/lang/String;", "", "ACTION_ICON_SIZE", "[F", "ACTION_ICON_X", "ACTION_ICON_Y", "ACTION_BOX_X", "ACTION_BOX_Y", "ICON_CLOUD", "Ljava/lang/String;", "ICON_TITLE", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "DATE_FORMAT", "Ljava/time/format/DateTimeFormatter;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float easeOutBack(float t) {
            float u = t - 1.0f;
            return 1.0f + 3.0f * u * u * u + 2.0f * u * u;
        }

        private final float quintOut(float t) {
            float u = 1.0f - RangesKt.coerceIn((float)t, (float)0.0f, (float)1.0f);
            return 1.0f - u * u * u * u * u;
        }

        private final float cubicOut(float t) {
            float u = 1.0f - RangesKt.coerceIn((float)t, (float)0.0f, (float)1.0f);
            return 1.0f - u * u * u;
        }

        private final float cubicIn(float t) {
            float u = RangesKt.coerceIn((float)t, (float)0.0f, (float)1.0f);
            return u * u * u;
        }

        private final String initialOf(String name) {
            CharSequence charSequence = name;
            if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
                return "?";
            }
            String string = name.substring(0, 1);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            charSequence = string;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = ((String)charSequence).toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toUpperCase(...)");
            return string2;
        }

        private final String subtitle(ConfigLibrary.Entry entry) {
            StringBuilder builder = new StringBuilder();
            if (entry.premium()) {
                Object[] objectArray = new Object[]{entry.ownerName()};
                builder.append(I18n.tr("от %s", objectArray)).append(" \u00b7 ");
            }
            builder.append(this.date(entry.updatedAt()));
            builder.append(" \u00b7 ").append(entry.modules()).append(' ').append(this.plural(entry.modules()));
            String string = builder.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            return string;
        }

        private final String date(long timestamp) {
            String string;
            if (timestamp <= 0L) {
                return I18n.tr("не сохранён");
            }
            try {
                string = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).format(DATE_FORMAT);
                Intrinsics.checkNotNull((Object)string);
            }
            catch (Exception ex) {
                string = "—";
            }
            return string;
        }

        private final String plural(int count) {
            boolean slavic;
            String code = ClientLanguage.code();
            boolean bl = slavic = Intrinsics.areEqual((Object)code, (Object)"ru") || Intrinsics.areEqual((Object)code, (Object)"uk") || Intrinsics.areEqual((Object)code, (Object)"pl");
            if (!slavic) {
                return count == 1 ? I18n.tr("модуль") : I18n.tr("модулей");
            }
            int mod100 = count % 100;
            int mod10 = count % 10;
            boolean bl2 = 11 <= mod100 ? mod100 < 15 : false;
            if (bl2) {
                return I18n.tr("модулей");
            }
            if (mod10 == 1) {
                return I18n.tr("модуль");
            }
            boolean bl3 = 2 <= mod10 ? mod10 < 5 : false;
            if (bl3) {
                return I18n.tr("модуля");
            }
            return I18n.tr("модулей");
        }

        private final boolean hit(float mx, float my, float x, float y, float w, float h) {
            return mx >= x && mx <= x + w && my >= y && my <= y + h;
        }

        private final int col(int r, int g, int b, float a) {
            int alpha = RangesKt.coerceIn((int)MathKt.roundToInt((float)a), (int)0, (int)255);
            if (alpha <= 0) {
                return 0;
            }
            return alpha << 24 | r << 16 | g << 8 | b;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/ui/configs/ConfigsRenderer$Modal;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "CREATE", "RENAME", "IMPORT", "CONFIRM_DELETE", "CONFIRM_UNLOCK", "rtx.kimiko:kimiko"})
    private static enum Modal {
        NONE,
        CREATE,
        RENAME,
        IMPORT,
        CONFIRM_DELETE,
        CONFIRM_UNLOCK;
@NotNull
        public static EnumEntries<Modal> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\"\u0010\u001d\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/ui/configs/ConfigsRenderer$Row;", "", "<init>", "()V", "Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "entry", "Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "getEntry", "()Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "setEntry", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;)V", "", "section", "Ljava/lang/String;", "getSection", "()Ljava/lang/String;", "setSection", "(Ljava/lang/String;)V", "", "y", "F", "getY", "()F", "setY", "(F)V", "h", "getH", "setH", "", "header", "Z", "getHeader", "()Z", "setHeader", "(Z)V", "rtx.kimiko:kimiko"})
    private static final class Row {
        @Nullable
        private ConfigLibrary.Entry entry;
        @Nullable
        private String section;
        private float y;
        private float h;
        private boolean header;

        @Nullable
        public final ConfigLibrary.Entry getEntry() {
            return this.entry;
        }

        public final void setEntry(@Nullable ConfigLibrary.Entry entry) {
            this.entry = entry;
        }

        @Nullable
        public final String getSection() {
            return this.section;
        }

        public final void setSection(@Nullable String string) {
            this.section = string;
        }

        public final float getY() {
            return this.y;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public final float getH() {
            return this.h;
        }

        public final void setH(float f) {
            this.h = f;
        }

        public final boolean getHeader() {
            return this.header;
        }

        public final void setHeader(boolean bl) {
            this.header = bl;
        }
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[Modal.values().length];
            try {
                nArray[Modal.CREATE.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Modal.RENAME.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Modal.IMPORT.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Modal.CONFIRM_DELETE.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Modal.CONFIRM_UNLOCK.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

