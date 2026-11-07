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
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.input.CharInput
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.client.gui.Click
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.api.ui;

import fun.shape.profile.Profile;
import fun.shape.profile.Role;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.gui.Click;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.IMinecraft;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareBindPopup;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareChatState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareCloseState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareConfigRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareLocalSnapshot;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiSharePopupRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareThemeState;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRestrictions;
import rtx.kimiko.api.ui.BaseScreen;
import rtx.kimiko.api.ui.BindPopup;
import rtx.kimiko.api.ui.HeaderButtons;
import rtx.kimiko.api.ui.MessengerPanel;
import rtx.kimiko.api.ui.SettingsPopup;
import rtx.kimiko.api.ui.UiScale;
import rtx.kimiko.api.ui.configs.ConfigsRenderer;
import rtx.kimiko.api.ui.events.EventsRenderer;
import rtx.kimiko.api.ui.logo.LogoToy;
import rtx.kimiko.api.ui.module.DiscordAvatar;
import rtx.kimiko.api.ui.module.ModuleListRenderer;
import rtx.kimiko.api.ui.module.SearchField;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.settings.impl.BindSetting;
import rtx.kimiko.api.ui.settings.impl.TextSetting;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.api.ui.theme.ThemesRenderer;
import rtx.kimiko.api.ui.window.GuiShatterAnimation;
import rtx.kimiko.api.ui.window.MainWindow;
import rtx.kimiko.api.ui.window.PanelDrag;
import rtx.kimiko.api.ui.window.WorldGuiCloseAnimation;
import rtx.kimiko.utils.animations.Animation;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.animations.GuiMotionAnimation;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiCapture;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.modules.post.guimotionblur.GuiMotionBlurRenderer;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;
import rtx.kimiko.utils.render.render2d.gif.GifRenderer;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ee\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u0000 \u00b7\u00012\u00020\u00012\u00020\u0002:\u0002\u00b7\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\b\u0010\u0004J\u000f\u0010\t\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\u0004J\u000f\u0010\n\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\n\u0010\u0004J\u000f\u0010\u000b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0004J\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001dJ\u0017\u0010 \u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b$\u0010%J\u0019\u0010'\u001a\u00020\u00052\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0004\b'\u0010\u001dJ\u0017\u0010(\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b(\u0010!J/\u0010.\u001a\u00020\u00052\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020\u000fH\u0014\u00a2\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u000fH\u0014\u00a2\u0006\u0004\b0\u0010\u0011J\u0017\u00101\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b1\u0010!J\u000f\u00102\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b2\u0010\u0004J\u0017\u00103\u001a\u00020\u00052\u0006\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\b3\u00104J7\u00109\u001a\u00020\u00052\u0006\u0010*\u001a\u00020)2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b;\u0010\u000eJ\u001f\u0010?\u001a\b\u0012\u0004\u0012\u00020>0=2\b\u0010<\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0004\b?\u0010@JQ\u0010B\u001a\u00020\u00052\u0006\u0010*\u001a\u00020)2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000f2\b\u0010<\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bB\u0010CJ7\u0010G\u001a\u00020\u00052\u0006\u0010*\u001a\u00020)2\u0006\u0010D\u001a\u00020\u000f2\u0006\u0010E\u001a\u00020\u000f2\u0006\u0010F\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bG\u0010:J/\u0010H\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bH\u0010IJ7\u0010K\u001a\u00020\u00052\u0006\u0010*\u001a\u00020)2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bK\u0010:J\u001f\u0010O\u001a\u00020\f2\u0006\u0010M\u001a\u00020L2\u0006\u0010N\u001a\u00020\fH\u0016\u00a2\u0006\u0004\bO\u0010PJ\u001f\u0010Q\u001a\u00020\f2\u0006\u0010M\u001a\u00020L2\u0006\u0010N\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bQ\u0010PJ1\u0010T\u001a\u0004\u0018\u00010\u00172\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u000f2\u0006\u0010S\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bT\u0010UJ/\u0010V\u001a\u00020\"2\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u000f2\u0006\u0010S\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bV\u0010WJ\u0017\u0010X\u001a\u00020\f2\u0006\u0010M\u001a\u00020LH\u0016\u00a2\u0006\u0004\bX\u0010YJ\u0017\u0010Z\u001a\u00020\f2\u0006\u0010M\u001a\u00020LH\u0002\u00a2\u0006\u0004\bZ\u0010YJ/\u0010^\u001a\u00020\f2\u0006\u0010+\u001a\u00020[2\u0006\u0010,\u001a\u00020[2\u0006\u0010\\\u001a\u00020[2\u0006\u0010]\u001a\u00020[H\u0016\u00a2\u0006\u0004\b^\u0010_J/\u0010`\u001a\u00020\f2\u0006\u0010+\u001a\u00020[2\u0006\u0010,\u001a\u00020[2\u0006\u0010\\\u001a\u00020[2\u0006\u0010]\u001a\u00020[H\u0002\u00a2\u0006\u0004\b`\u0010_J\u001b\u0010c\u001a\u00020\u00052\u0006\u0010M\u001a\u00020aH\u0003b\u0002\bb\u00a2\u0006\u0004\bc\u0010dJ\u0017\u0010e\u001a\u00020\u00052\u0006\u0010M\u001a\u00020aH\u0002\u00a2\u0006\u0004\be\u0010dJ\u001f\u0010f\u001a\u00020\f2\u0006\u0010R\u001a\u00020\u000f2\u0006\u0010S\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bf\u0010gJ\u0011\u0010h\u001a\u0004\u0018\u00010>H\u0002\u00a2\u0006\u0004\bh\u0010iJ\u0017\u0010k\u001a\u00020\f2\u0006\u0010M\u001a\u00020jH\u0016\u00a2\u0006\u0004\bk\u0010lJ\u0017\u0010m\u001a\u00020\f2\u0006\u0010M\u001a\u00020jH\u0002\u00a2\u0006\u0004\bm\u0010lJ\u0017\u0010o\u001a\u00020\f2\u0006\u0010M\u001a\u00020nH\u0016\u00a2\u0006\u0004\bo\u0010pJ\u0017\u0010q\u001a\u00020\f2\u0006\u0010M\u001a\u00020nH\u0002\u00a2\u0006\u0004\bq\u0010pJ\u000f\u0010r\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\br\u0010\u0004J'\u0010s\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bs\u0010tR\u0018\u0010u\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0018\u0010w\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010vR\u0016\u0010y\u001a\u00020x8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010{\u001a\u00020x8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010zR \u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00190|8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b}\u0010~R\u0015\u0010\u007f\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0017\u0010\u0081\u0001\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0081\u0001\u0010\u0080\u0001R\u0017\u0010\u0082\u0001\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0080\u0001R\u0017\u0010\u0083\u0001\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0080\u0001R\u0019\u0010\u0084\u0001\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0017\u0010\u0086\u0001\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0019\u0010\u0088\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0019\u0010\u008a\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0019\u0010\u008c\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u008b\u0001R\u0019\u0010\u008d\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008b\u0001R\u0019\u0010\u008e\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008b\u0001R\u0018\u0010\u0090\u0001\u001a\u00030\u008f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0093\u0001\u001a\u00030\u0092\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0018\u0010\u0096\u0001\u001a\u00030\u0095\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0018\u0010\u0099\u0001\u001a\u00030\u0098\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R\u0018\u0010\u009c\u0001\u001a\u00030\u009b\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0018\u0010\u009f\u0001\u001a\u00030\u009e\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u00a0\u0001R\u0018\u0010\u00a2\u0001\u001a\u00030\u00a1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u00a3\u0001R\u0018\u0010\u00a5\u0001\u001a\u00030\u00a4\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u00a6\u0001R\u0018\u0010\u00a8\u0001\u001a\u00030\u00a7\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u00a9\u0001R\u0018\u0010«\u0001\u001a\u00030\u00aa\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00ac\u0001R\u001a\u0010\u00ae\u0001\u001a\u00030\u00ad\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00af\u0001R\u0017\u0010\u00b0\u0001\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u0080\u0001R\u0019\u0010\u00b1\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u008b\u0001R\u0019\u0010\u00b2\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u008b\u0001R\u0019\u0010\u00b3\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u008b\u0001R\u0019\u0010\u00b4\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u008b\u0001R\u0019\u0010\u00b5\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b5\u0001\u0010\u008b\u0001R\u0019\u0010\u00b6\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u008b\u0001\u00a8\u0006\u00b8\u0001"}, d2={"Lrtx/kimiko/api/ui/UI;", "Lrtx/kimiko/api/ui/BaseScreen;", "Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture$Source;", "<init>", "()V", "", "warmupRender", "init", "repositionElements", "onClose", "removed", "releaseAllDrags", "", "captureActive", "()Z", "", "captureScale", "()F", "captureBlurRadius", "shatterProgress", "", "shatterKeepRect", "()[F", "Lrtx/kimiko/api/modules/Category;", "category", "Lrtx/kimiko/utils/animations/Decelerate;", "getCategoryAnim", "(Lrtx/kimiko/api/modules/Category;)Lrtx/kimiko/utils/animations/Decelerate;", "profileSelectCategory", "(Lrtx/kimiko/api/modules/Category;)V", "selectCategory", "dt", "updateEventsSubAnim", "(F)V", "", "sub", "selectEventsSub", "(I)V", "next", "swapContentCategory", "updateCategoryCrossfade", "Lnet/minecraft/DrawContext;", "graphics", "mouseX", "mouseY", "partialTick", "renderScreen", "(Lnet/minecraft/DrawContext;IIF)V", "uiZoom", "updatePanelDrag", "updateCameraParallax", "renderPanel", "(Lnet/minecraft/DrawContext;)V", "x", "y", "w", "alpha", "renderNoCategoryPlaceholder", "(Lnet/minecraft/DrawContext;FFFF)V", "isModuleView", "cat", "", "Lrtx/kimiko/api/modules/Module;", "filteredModules", "(Lrtx/kimiko/api/modules/Category;)Ljava/util/List;", "dy", "renderModuleHeader", "(Lnet/minecraft/DrawContext;FFFFLrtx/kimiko/api/modules/Category;FF)V", "ax", "ay", "size", "renderDiscordAvatar", "renderNoResults", "(FFFF)V", "h", "renderCategoryPanel", "Lnet/minecraft/Click;", "event", "doubleClick", "mouseClicked", "(Lnet/minecraft/Click;Z)Z", "mouseClickedScaled", "mx", "my", "categoryButtonAt", "(FFFF)Lrtx/kimiko/api/modules/Category;", "eventsSubAt", "(FFFF)I", "mouseReleased", "(Lnet/minecraft/Click;)Z", "mouseReleasedScaled", "", "horizontal", "vertical", "mouseScrolled", "(DDDD)Z", "mouseScrolledScaled", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "Lrtx/kimiko/api/events/EventHandler;", "onRawMouse", "(Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;)V", "onRawMouseScaled", "canGrabPanel", "(FF)Z", "moduleAtCursor", "()Lrtx/kimiko/api/modules/Module;", "Lnet/minecraft/KeyInput;", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "keyPressedScaled", "Lnet/minecraft/CharInput;", "charTyped", "(Lnet/minecraft/CharInput;)Z", "charTypedScaled", "stagePopupBlur", "stageCardBlur", "(FFF)V", "targetCategory", "Lrtx/kimiko/api/modules/Category;", "contentCategory", "", "oldSubText", "Ljava/lang/String;", "newSubText", "", "categoryAnims", "Ljava/util/Map;", "subTextAnim", "Lrtx/kimiko/utils/animations/Decelerate;", "modulesHeaderAnim", "eventsHeaderAnim", "clientHeaderAnim", "eventsSub", "I", "eventsSubT", "[F", "subTextAnimDone", "Z", "categoryT", "F", "themesRowT", "configsHeaderT", "headerVisibleT", "Lrtx/kimiko/api/ui/module/ModuleListRenderer;", "moduleList", "Lrtx/kimiko/api/ui/module/ModuleListRenderer;", "Lrtx/kimiko/api/ui/module/SearchField;", "search", "Lrtx/kimiko/api/ui/module/SearchField;", "Lrtx/kimiko/api/ui/HeaderButtons;", "headerButtons", "Lrtx/kimiko/api/ui/HeaderButtons;", "Lrtx/kimiko/api/ui/MessengerPanel;", "messenger", "Lrtx/kimiko/api/ui/MessengerPanel;", "Lrtx/kimiko/api/ui/BindPopup;", "bindPopup", "Lrtx/kimiko/api/ui/BindPopup;", "Lrtx/kimiko/api/ui/SettingsPopup;", "settingsPopup", "Lrtx/kimiko/api/ui/SettingsPopup;", "Lrtx/kimiko/api/ui/theme/ThemesRenderer;", "themesRenderer", "Lrtx/kimiko/api/ui/theme/ThemesRenderer;", "Lrtx/kimiko/api/ui/events/EventsRenderer;", "eventsRenderer", "Lrtx/kimiko/api/ui/events/EventsRenderer;", "Lrtx/kimiko/api/ui/configs/ConfigsRenderer;", "configsRenderer", "Lrtx/kimiko/api/ui/configs/ConfigsRenderer;", "Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "screenAnim", "Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "", "lastNs", "J", "placeholderAnim", "parallaxX", "parallaxY", "lastCameraYaw", "lastCameraPitch", "lastPanX", "lastPanY", "Companion", "rtx.kimiko:kimiko"})
public final class UI
extends BaseScreen
implements GuiCapture.Source {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private Category targetCategory;
    @Nullable
    private Category contentCategory;
    @NotNull
    private String oldSubText;
    @NotNull
    private String newSubText;
    @NotNull
    private final Map<Category, Decelerate> categoryAnims;
    @NotNull
    private final Decelerate subTextAnim;
    @NotNull
    private final Decelerate modulesHeaderAnim;
    @NotNull
    private final Decelerate eventsHeaderAnim;
    @NotNull
    private final Decelerate clientHeaderAnim;
    private int eventsSub;
    @NotNull
    private final float[] eventsSubT;
    private boolean subTextAnimDone;
    private float categoryT;
    private float themesRowT;
    private float configsHeaderT;
    private float headerVisibleT;
    @NotNull
    private final ModuleListRenderer moduleList;
    @NotNull
    private final SearchField search;
    @NotNull
    private final HeaderButtons headerButtons;
    @NotNull
    private final MessengerPanel messenger;
    @NotNull
    private final BindPopup bindPopup;
    @NotNull
    private final SettingsPopup settingsPopup;
    @NotNull
    private final ThemesRenderer themesRenderer;
    @NotNull
    private final rtx.kimiko.api.ui.cosmetics.CosmeticsRenderer cosmeticsRenderer;
    @NotNull
    private final EventsRenderer eventsRenderer;
    @NotNull
    private final ConfigsRenderer configsRenderer;
    @NotNull
    private final GuiMotionAnimation screenAnim;
    private long lastNs;
    @NotNull
    private final Decelerate placeholderAnim;
    private float parallaxX;
    private float parallaxY;
    private float lastCameraYaw;
    private float lastCameraPitch;
    private float lastPanX;
    private float lastPanY;
    @NotNull
    private static final String[] EVENT_SUBS;
    @NotNull
    private static final String[] EVENT_SUB_ICONS;
    @JvmField
    @NotNull
    public static final UI INSTANCE;
    public static final float PANEL_W = 430.0f;
    public static final float PANEL_H = 290.0f;
    public static final float SIDEBAR_W = 110.0f;
    public static final float CONTENT_X_OFF = 117.0f;
    public static final float CONTENT_INSET = 122.0f;
    public static final float CONTENT_HEIGHT = 280.0f;
    private static final float CONTENT_Y_OFFSET = 5.0f;
    @NotNull
    private static final Category[] MAIN_CATEGORIES;
    @NotNull
    private static final Category[] OTHER_CATEGORIES;
    private static final float CAT_COL_TOP = 34.0f;
    private static final float CAT_HEADER_H = 20.0f;
    private static final float CAT_SUB_GAP = 4.0f;
    private static final float CAT_SUB_ROW_H = 18.0f;
    private static final float CAT_EVENTS_GAP = 3.0f;
    private static final float CATEGORY_FADE_SEC = 0.15f;
    @Nullable
    private static Screen pendingAfterClose;
    private static boolean motionBlurPending;
    private static float motionBlurOpacity;
    private static float motionBlurRadius;
    private static float motionBlurScale;
    private static float motionBlurOriginX;
    private static float motionBlurOriginY;
    private static int motionBlurX;
    private static int motionBlurY;
    private static int motionBlurW;
    private static int motionBlurH;
    private static int motionBlurSrcX;
    private static int motionBlurSrcY;
    private static int motionBlurSrcW;
    private static int motionBlurSrcH;
    @NotNull
    private static final float[] motionBlurMask;
    private static final float CARD_BLUR_MAX_RADIUS = 22.0f;
    private static boolean cardStratumMarked;
    private static boolean cardBlurPending;
    @NotNull
    private static final float[] cardBlurMask;
    private static int cardBlurMaskCount;
    private static boolean cardCaptureStaged;
    private static boolean cardBlurCaptured;
    private static boolean panelSplitMarked;
    private static boolean vanillaBlurRequested;
    private static boolean popupStratumMarked;
    private static final float POPUP_BLUR_GUI_RADIUS = 9.0f;
    @NotNull
    private static final float[] popupBlurMask;
    private static int popupBlurMaskCount;
    private static boolean popupLayerBlurWanted;
    private static boolean popupBlurWanted;
    private static boolean popupBlurStaged;
    private static boolean popupBlurCaptured;
    private static boolean popupBlurPending;
    private static float popupBlurRadius;
    private static int popupBlurX;
    private static int popupBlurY;
    private static int popupBlurW;
    private static int popupBlurH;
    private static float popupBlurOriginX;
    private static float popupBlurOriginY;
    private static float guiCaptureScale;
    private static float guiCaptureBlurMainPx;
    @NotNull
    private static final String RU_LAYOUT = "йцукенгшщзхъфывапролджэячсмитьбюё";
    @NotNull
    private static final String EN_LAYOUT = "qwertyuiop[]asdfghjkl;'zxcvbnm,.`";

    private UI() {
        super((Text)Text.literal("UI"));
        this.oldSubText = "";
        this.newSubText = "";
        this.categoryAnims = new EnumMap(Category.class);
        this.subTextAnim = UI.Companion.createAnim(300);
        this.modulesHeaderAnim = UI.Companion.createAnim(200);
        this.eventsHeaderAnim = UI.Companion.createAnim(200);
        this.clientHeaderAnim = UI.Companion.createAnim(200);
        this.eventsSubT = new float[EVENT_SUBS.length];
        this.subTextAnimDone = true;
        this.categoryT = 1.0f;
        this.themesRowT = 1.0f;
        this.moduleList = new ModuleListRenderer();
        this.search = new SearchField();
        this.headerButtons = new HeaderButtons();
        this.messenger = new MessengerPanel();
        this.bindPopup = new BindPopup();
        this.settingsPopup = new SettingsPopup();
        this.themesRenderer = new ThemesRenderer();
        this.cosmeticsRenderer = new rtx.kimiko.api.ui.cosmetics.CosmeticsRenderer();
        this.eventsRenderer = new EventsRenderer();
        this.configsRenderer = new ConfigsRenderer();
        this.screenAnim = new GuiMotionAnimation();
        this.lastNs = System.nanoTime();
        this.placeholderAnim = UI.Companion.createAnim(200);
        this.lastCameraYaw = Float.NaN;
        this.placeholderAnim.setDirection(Direction.FORWARDS);
        EventBus.Companion.get().subscribe(this);
    }

    public final void warmupRender() {
        this.moduleList.warmup();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void init() {
        pendingAfterClose = null;
        BaseScreen.Companion.dropClosingOverlay();
        GuiCapture.bind(this);
        if (this.screenAnim.isClosing()) {
            WorldGuiCloseAnimation.reverse();
            GuiShatterAnimation.gather(WorldGuiCloseAnimation.isReversing() ? WorldGuiCloseAnimation.remainingNanos() : 0L);
            this.screenAnim.resumeOpening();
        } else {
            WorldGuiCloseAnimation.cancel();
            GuiShatterAnimation.cancel();
            this.screenAnim.startOpening();
        }
        this.lastNs = System.nanoTime();
        float toyZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            LogoToy.reopen();
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(toyZoom);
        }
    }

    protected void refreshWidgetPositions() {
        if (this.screenAnim.isClosing() || this.screenAnim.isCloseFinished() || this.screenAnim.alpha() <= 0.01f) {
            this.init();
        }
    }

    public void close() {
        if (!this.screenAnim.isClosing()) {
            GuiCapture.bind(this);
            float handoffAlpha = this.screenAnim.alpha();
            float handoffScale = this.screenAnim.scale();
            this.screenAnim.startClosing();
            if (GuiLayerBlurRenderer.available()) {
                WorldGuiCloseAnimation.begin(handoffAlpha, handoffScale);
                UI.Companion.beginShatter();
            } else {
                WorldGuiCloseAnimation.cancel();
                GuiShatterAnimation.cancel();
            }
            this.releaseAllDrags();
            this.settingsPopup.closeSilent();
            this.headerButtons.closeSilent();
            this.messenger.detachForGuiClose();
            this.search.blur();
            LogoToy.beginShatter();
            Sounds.play("gui_close");
        }
        if (Intrinsics.areEqual((Object)MinecraftClient.getInstance().currentScreen, (Object)this)) {
            MinecraftClient.getInstance().setScreen(null);
        }
    }

    public void removed() {
        if (!this.screenAnim.isClosing()) {
            this.screenAnim.snapClosed();
            GuiShatterAnimation.cancel();
            this.settingsPopup.closeSilent();
            this.headerButtons.closeSilent();
            this.messenger.closeSilent();
            this.search.blur();
        }
        this.releaseAllDrags();
        super.removed();
    }

    private final void releaseAllDrags() {
        PanelDrag.end();
        this.settingsPopup.releaseDrags();
        this.moduleList.scrollbarRelease();
        this.eventsRenderer.scrollbarRelease();
        this.configsRenderer.scrollbarRelease();
    }

    @Override
    public boolean captureActive() {
        return Companion.guiCaptureActive();
    }

    @Override
    public float captureScale() {
        return guiCaptureScale;
    }

    @Override
    public float captureBlurRadius() {
        return guiCaptureBlurMainPx;
    }

    @Override
    public float shatterProgress() {
        return Companion.guiShatterProgress();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Nullable
    public float[] shatterKeepRect() {
        if (!LogoToy.busy()) {
            return null;
        }
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            float[] fArray = LogoToy.keepRectPixels();
            return fArray;
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    private final Decelerate getCategoryAnim(Category category) {
        return this.categoryAnims.computeIfAbsent(category, it -> UI.Companion.createAnim(200));
    }

    public final void profileSelectCategory(@NotNull Category category) {
        Intrinsics.checkNotNullParameter((Object)((Object)category), (String)"category");
        this.selectCategory(category);
    }

    private final void selectCategory(Category category) {
        Category target;
        Category category2 = target = category == this.targetCategory ? null : category;
        if (target == this.targetCategory) {
            return;
        }
        this.search.setText("");
        this.search.blur();
        this.headerButtons.close();
        this.settingsPopup.close();
        this.targetCategory = target;
        this.oldSubText = this.newSubText;
        this.newSubText = target != null && target.getDisplayName() != null ? target.getDisplayName() : I18n.tr("Не выбрано");
        this.subTextAnim.setDirection(Direction.FORWARDS);
        this.subTextAnim.counter.resetCounter();
        this.subTextAnimDone = false;
        for (Category cat : Category.values()) {
            this.getCategoryAnim(cat).setDirection(cat == target ? Direction.FORWARDS : Direction.BACKWARDS);
        }
        this.modulesHeaderAnim.setDirection(UI.Companion.isMainCategory(target) || target == Category.CONFIGS ? Direction.FORWARDS : Direction.BACKWARDS);
        this.eventsHeaderAnim.setDirection(target == Category.EVENTS ? Direction.FORWARDS : Direction.BACKWARDS);
        this.clientHeaderAnim.setDirection(UI.Companion.isOtherCategory(target) ? Direction.FORWARDS : Direction.BACKWARDS);
        if (target == null) {
            this.placeholderAnim.setDirection(Direction.FORWARDS);
            this.placeholderAnim.counter.resetCounter();
        } else {
            this.placeholderAnim.setDirection(Direction.BACKWARDS);
        }
    }

    private final void updateEventsSubAnim(float dt) {
        float r = 1.0f - (float)Math.exp(-dt * 12.0f);
        int n = this.eventsSubT.length;
        for (int i = 0; i < n; ++i) {
            float target = this.contentCategory == Category.EVENTS && this.eventsSub == i ? 1.0f : 0.0f;
            float[] fArray = this.eventsSubT;
            int n2 = i;
            fArray[n2] = fArray[n2] + (target - this.eventsSubT[i]) * r;
        }
    }

    private final void selectEventsSub(int sub) {
        if (this.targetCategory != Category.EVENTS) {
            this.selectCategory(Category.EVENTS);
        }
        this.eventsSub = sub;
        if (sub == 1) {
            this.eventsRenderer.showMines();
        } else {
            this.eventsRenderer.showEvents();
        }
    }

    private final void swapContentCategory(Category next) {
        boolean nextHasHeader;
        this.moduleList.finishTransition();
        this.themesRenderer.finishTransition();
        this.eventsRenderer.finishTransition();
        Category previous = this.contentCategory;
        this.contentCategory = next;
        boolean headerSwap = previous == Category.CONFIGS && UI.Companion.isMainCategory(next) || UI.Companion.isMainCategory(previous) && next == Category.CONFIGS;
        boolean bl = nextHasHeader = next == Category.CONFIGS || UI.Companion.isMainCategory(next);
        if (!headerSwap && nextHasHeader) {
            float f = this.configsHeaderT = next == Category.CONFIGS ? 1.0f : 0.0f;
        }
        if (next == Category.THEMES) {
            this.themesRenderer.open(false);
        }
        if (next == Category.CONFIGS) {
            this.configsRenderer.open();
        }
        if (next == Category.COSMETICS) {
            this.cosmeticsRenderer.open();
        }
        if (next == Category.EVENTS) {
            this.eventsRenderer.open();
            if (this.eventsSub == 1) {
                this.eventsRenderer.showMines();
            } else {
                this.eventsRenderer.showEvents();
            }
        }
        if (next == null) {
            this.placeholderAnim.setDirection(Direction.FORWARDS);
            this.placeholderAnim.counter.resetCounter();
        }
    }

    private final void updateCategoryCrossfade(float dt) {
        if (this.targetCategory == this.contentCategory) {
            if (this.contentCategory != null && this.categoryT < 1.0f) {
                this.categoryT = Math.min(1.0f, this.categoryT + dt / 0.15f);
            }
            return;
        }
        if (this.contentCategory == null) {
            this.swapContentCategory(this.targetCategory);
            this.categoryT = 0.0f;
        } else {
            this.categoryT = Math.max(0.0f, this.categoryT - dt / 0.15f);
            if (this.categoryT <= 0.0f) {
                this.categoryT = 0.0f;
                this.swapContentCategory(this.targetCategory);
            }
        }
    }

    @Override
    protected void renderScreen(@NotNull DrawContext graphics, int mouseX, int mouseY, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.renderPanel(graphics);
    }

    @Override
    protected float uiZoom() {
        return UiScale.zoom();
    }

    private final void updatePanelDrag(float dt) {
        if (!(!PanelDrag.isDragging() || this.screenAnim.canInteract() && Intrinsics.areEqual((Object)MinecraftClient.getInstance().currentScreen, (Object)this))) {
            PanelDrag.end();
        }
        PanelDrag.update(430.0f, 290.0f, dt);
        float dx = PanelDrag.x() - this.lastPanX;
        float dy = PanelDrag.y() - this.lastPanY;
        this.lastPanX = PanelDrag.x();
        this.lastPanY = PanelDrag.y();
        if (!(dx == 0.0f) || !(dy == 0.0f)) {
            this.settingsPopup.shift(dx, dy);
            this.bindPopup.shift(dx, dy);
        }
    }

    private final void updateCameraParallax() {
        ClientPlayerEntity player = IMinecraft.mc.player;
        if (player == null) {
            this.lastCameraYaw = Float.NaN;
            this.parallaxX = 0.0f;
            this.parallaxY = 0.0f;
            return;
        }
        float yaw = player.getYaw();
        float pitch = player.getPitch();
        if (Float.isNaN(this.lastCameraYaw)) {
            this.lastCameraYaw = yaw;
            this.lastCameraPitch = pitch;
        }
        float dYaw = MathHelper.wrapDegrees((float)(yaw - this.lastCameraYaw));
        float dPitch = pitch - this.lastCameraPitch;
        this.lastCameraYaw = yaw;
        this.lastCameraPitch = pitch;
        if (!this.screenAnim.isClosing()) {
            this.parallaxX = 0.0f;
            this.parallaxY = 0.0f;
            return;
        }
        double d = 30.0;
        Object object = IMinecraft.mc.options.getFov().getValue();
        Intrinsics.checkNotNull((Object)object, (String)"null cannot be cast to non-null type kotlin.Number");
        double d2 = ((Number)object).doubleValue();
        double fov = Math.max(d, d2);
        float pxPerDegree = (float)((double)Position.Companion.screenHeight() / fov);
        this.parallaxX -= dYaw * pxPerDegree;
        this.parallaxY -= dPitch * pxPerDegree;
    }

    private final void renderPanel(DrawContext graphics) {
        float configsTarget;
        this.screenAnim.updateFrame();
        this.updateCameraParallax();
        long nowFrame = System.nanoTime();
        float frameDt = Math.min(0.1f, (float)(nowFrame - this.lastNs) / 1.0E9f);
        this.lastNs = nowFrame;
        this.updatePanelDrag(frameDt);
        boolean worldDetached = WorldGuiCloseAnimation.isDetachedRender();
        if (worldDetached) {
            this.parallaxX = 0.0f;
            this.parallaxY = 0.0f;
        }
        if (this.screenAnim.isCloseFinished() && !worldDetached) {
            if (Intrinsics.areEqual((Object)MinecraftClient.getInstance().currentScreen, (Object)this)) {
                this.screenAnim.snapClosed();
                MinecraftClient.getInstance().setScreen(null);
            }
            return;
        }
        float animScale = this.screenAnim.scale();
        float parallaxShift = Math.max(Math.abs(this.parallaxX), Math.abs(this.parallaxY));
        if (parallaxShift > 0.5f) {
            float k = Math.min(1.0f, parallaxShift / 20.0f);
            animScale += (1.0f - animScale) * k;
        }
        guiCaptureScale = animScale;
        float blurGuiPx = worldDetached ? WorldGuiCloseAnimation.blurRadius() : this.screenAnim.blurRadius();
        guiCaptureBlurMainPx = UI.Companion.pixelSize(Math.max(blurGuiPx, GuiShatterAnimation.blurRadius()));
        float dimAlpha = this.screenAnim.alpha();
        float screenAlpha = worldDetached ? 1.0f : dimAlpha;
        float w = 430.0f;
        float h = 290.0f;
        float x = Companion.panelX();
        float y = Companion.panelY();
        float dimZoom = Math.max(0.01f, Render2DCoordinateSpace.uiZoom());
        float dimCenterX = Position.Companion.screenWidth() * 0.5f;
        float dimCenterY = Position.Companion.screenHeight() * 0.5f;
        float dimHalfW = dimCenterX / dimZoom + 8.0f;
        float dimHalfH = dimCenterY / dimZoom + 8.0f;
        float shatterFade = Companion.guiCaptureActive() && GuiShatterAnimation.isActive() ? 1.0f - Math.min(1.0f, Math.max(0.0f, Companion.guiShatterProgress())) : 1.0f;
        float dimDrawAlpha = dimAlpha * shatterFade;
        if (dimDrawAlpha > 0.002f) {
            Render2D.rect(dimCenterX - dimHalfW, dimCenterY - dimHalfH, dimHalfW * 2.0f, dimHalfH * 2.0f, 0.0f, UI.Companion.color(0, 0, 0, 60, dimDrawAlpha));
        }
        if (Companion.guiCaptureActive()) {
            Render2D.flush();
            Render2D.beginFrame(graphics);
            Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
            GuiRenderState rs = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            rs.createNewRootLayer();
            rs.applyBlur();
        }
        this.moduleList.resetCardBlur();
        this.themesRenderer.resetCardBlur();
        this.configsRenderer.resetCardBlur();
        this.eventsRenderer.resetCardBlur();
        this.cosmeticsRenderer.resetCardBlur();
        graphics.getMatrices().pushMatrix();
        if (!(this.parallaxX == 0.0f) || !(this.parallaxY == 0.0f)) {
            graphics.getMatrices().translate(this.parallaxX, this.parallaxY);
        }
        RectUtil.drawClientRect(x, y, w, h, 12.0f, screenAlpha, 6.0f);
        float panelR = 12.0f;
        RenderHelper.drawPanelBg(x + 5.0f, y + 5.0f, 110.0f, h - 10.0f, panelR, 0.0f, 0.0f, panelR, screenAlpha);
        InterfaceModule ifaceModule = InterfaceModule.Companion.getInstance();
        float themesTarget = ifaceModule == null || ifaceModule.isThemeClientColor() ? 1.0f : 0.0f;
        this.themesRowT += (themesTarget - this.themesRowT) * (1.0f - (float)Math.exp(-frameDt * 10.0f));
        this.renderCategoryPanel(graphics, x + 5.0f, y + 2.0f, h, screenAlpha);
        RenderHelper.drawPanelBg(x + 117.0f, y + 5.0f, w - 122.0f, 280.0f, 0.0f, panelR, panelR, 0.0f, screenAlpha);
        this.updateCategoryCrossfade(frameDt);
        this.updateEventsSubAnim(frameDt);
        float catAlpha = screenAlpha * this.categoryT;
        float catSlide = this.screenAnim.isClosing() ? 1.0f : this.categoryT;
        boolean contentComposite = !Companion.guiCaptureActive() && !this.bindPopup.isVisible() && !this.settingsPopup.isVisible() && this.messenger.blurPhase() <= 0.004f;
        this.moduleList.setAppearComposite(contentComposite);
        this.themesRenderer.setAppearComposite(contentComposite);
        this.configsRenderer.setAppearComposite(contentComposite);
        this.eventsRenderer.setAppearComposite(contentComposite);
        this.cosmeticsRenderer.setAppearComposite(contentComposite);
        Category currentCat = this.contentCategory;
        if (catAlpha > 0.01f && currentCat != null) {
            if (currentCat == Category.EVENTS) {
                this.eventsRenderer.render(graphics, x, y, w, catAlpha, frameDt);
            } else if (currentCat == Category.THEMES) {
                this.themesRenderer.render(graphics, x, y, w, catAlpha, catSlide, frameDt);
            } else if (currentCat == Category.CONFIGS) {
                this.configsRenderer.render(graphics, x, y, w, catAlpha, frameDt);
            } else if (currentCat == Category.COSMETICS) {
                this.cosmeticsRenderer.render(graphics, x, y, w, catAlpha, catSlide, frameDt);
            } else {
                List<Module> mods = this.filteredModules(currentCat);
                this.moduleList.render(graphics, x, y, w, catAlpha, catSlide, frameDt, currentCat, mods);
                if (mods.isEmpty() && this.search.hasText()) {
                    this.renderNoResults(x, y, w, catAlpha);
                }
            }
        }
        boolean moduleHeaderView = currentCat != null && currentCat != Category.THEMES && currentCat != Category.EVENTS && currentCat != Category.CONFIGS && currentCat != Category.COSMETICS;
        boolean headerCategory = moduleHeaderView || currentCat == Category.CONFIGS;
        float f = configsTarget = currentCat == Category.CONFIGS ? 1.0f : 0.0f;
        if (headerCategory) {
            if (this.headerVisibleT <= 0.004f) {
                this.configsHeaderT = configsTarget;
            } else {
                this.configsHeaderT += (configsTarget - this.configsHeaderT) * (1.0f - (float)Math.exp(-frameDt * 12.0f));
                if (Math.abs(configsTarget - this.configsHeaderT) < 0.002f) {
                    this.configsHeaderT = configsTarget;
                }
            }
        }
        float headerTarget = headerCategory ? 1.0f : 0.0f;
        this.headerVisibleT += (headerTarget - this.headerVisibleT) * (1.0f - (float)Math.exp(-frameDt * 12.0f));
        if (Math.abs(headerTarget - this.headerVisibleT) < 0.002f) {
            this.headerVisibleT = headerTarget;
        }
        if (screenAlpha > 0.01f && this.headerVisibleT > 0.004f) {
            float stripX = x + 117.0f;
            float stripY = y + 5.0f;
            float stripW = w - 122.0f;
            float stripH = ModuleListRenderer.HEADER_OFFSET - 4.0f;
            float hideOffset = -stripH * (1.0f - this.headerVisibleT);
            float headerAlpha = screenAlpha * this.headerVisibleT;
            Render2D.pushScissor(graphics, stripX, stripY, stripW, stripH);
            RoundedScissor.push(graphics, stripX, stripY - 4.0f, stripW, stripH + 8.0f, 0.0f, panelR, 0.0f, 0.0f);
            float moduleAlpha = headerAlpha * (1.0f - this.configsHeaderT);
            if (moduleAlpha > 0.004f) {
                this.renderModuleHeader(graphics, x, y, w, moduleAlpha, currentCat, frameDt, -stripH * this.configsHeaderT + hideOffset);
            }
            this.configsRenderer.renderHeaderDetails(graphics, headerAlpha * this.configsHeaderT, stripH * (1.0f - this.configsHeaderT) + hideOffset, frameDt);
            RoundedScissor.pop();
            Render2D.popScissor(graphics);
        }
        Double d = this.placeholderAnim.getOutput();
        float placeholderT = (float)(d != null ? d : 0.0);
        if (this.contentCategory == null && this.targetCategory == null && placeholderT > 0.01f) {
            this.renderNoCategoryPlaceholder(graphics, x, y, w, screenAlpha * placeholderT);
        }
        if (this.contentCategory == Category.CONFIGS && this.configsRenderer.hasOverlay()) {
            if (!Companion.guiCaptureActive()) {
                Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
                GuiRenderState rs = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
                rs.createNewRootLayer();
                rs.applyBlur();
                Companion.markPopupStratum(false, false);
            }
            this.configsRenderer.renderOverlay(graphics, screenAlpha, frameDt);
        }
        boolean settingsVisible = this.settingsPopup.isVisible();
        boolean bindVisible = this.bindPopup.isVisible();
        boolean headerModalVisible = this.headerButtons.isVisible();
        boolean messengerVisible = this.messenger.isVisible();
        if (messengerVisible) {
            this.messenger.render(graphics, x, y, w, h, screenAlpha, Position.Companion.mouseX(), Position.Companion.mouseY(), frameDt);
        }
        if (settingsVisible || bindVisible || headerModalVisible) {
            boolean splitAtBind;
            boolean settingsBlur = this.settingsPopup.blurPhase() > 0.004f;
            boolean bindBlur = this.bindPopup.blurPhase() > 0.004f;
            boolean headerBlur = this.headerButtons.blurPhase() > 0.004f;
            boolean anyBlur = settingsBlur || bindBlur || headerBlur;
            boolean bl = splitAtBind = !Companion.guiCaptureActive() && settingsVisible && bindVisible && bindBlur && !settingsBlur;
            if (!Companion.guiCaptureActive()) {
                Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
                GuiRenderState rs = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
                rs.createNewRootLayer();
                rs.applyBlur();
                Companion.markPopupStratum(anyBlur && !splitAtBind, anyBlur);
            }
            if (settingsVisible) {
                this.settingsPopup.render(graphics, screenAlpha);
            }
            if (splitAtBind) {
                Companion.markPopupLayerCapture();
                GuiLayerBlurRenderer.markPopupBoundary(graphics);
            }
            if (bindVisible) {
                this.bindPopup.render(graphics, screenAlpha);
            }
            if (headerModalVisible) {
                if (!Companion.guiCaptureActive() && (settingsVisible || bindVisible)) {
                    Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
                    ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().createNewRootLayer();
                }
                this.headerButtons.renderModal(graphics, screenAlpha);
            }
        }
        graphics.getMatrices().popMatrix();
        if (this.screenAnim.isClosing() || worldDetached) {
            LogoToy.beginShatter();
        }
        if (Intrinsics.areEqual((Object)MinecraftClient.getInstance().currentScreen, (Object)this)) {
            LogoToy.update(frameDt, this.screenAnim.canInteract() && !this.screenAnim.isClosing());
            if (LogoToy.busy()) {
                Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
                GuiRenderState toyState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
                toyState.createNewRootLayer();
                LogoToy.render(graphics, screenAlpha);
            }
        }
        if (!Companion.guiCaptureActive()) {
            this.stagePopupBlur();
            this.stageCardBlur(x, y, w);
        }
    }

    private final void renderNoCategoryPlaceholder(DrawContext graphics, float x, float y, float w, float alpha) {
        float areaX = x + 117.0f;
        float areaY = y + 5.0f;
        float areaW = w - 122.0f;
        float areaH = 280.0f;
        float gifSize = 60.0f;
        float gifX = areaX + (areaW - gifSize) * 0.5f;
        float gifY = areaY + (areaH - gifSize) * 0.5f - 10.0f;
        GifRenderer.draw(graphics, gifX, gifY, gifSize, gifSize, 8.0f, "kimiko:gif/kity.gif", alpha);
        String label = I18n.tr("Откройте категорию чтобы начать");
        float labelSize = 6.0f;
        float labelW = Fonts.MEDIUM.width(label, labelSize);
        Fonts.MEDIUM.draw(label, areaX + (areaW - labelW) * 0.5f, gifY + gifSize + 8.0f, labelSize, UI.Companion.color(255, 255, 255, 100, alpha));
    }

    private final boolean isModuleView() {
        return this.contentCategory != null && this.contentCategory != Category.THEMES && this.contentCategory != Category.EVENTS && this.contentCategory != Category.CONFIGS && this.contentCategory != Category.COSMETICS;
    }

    private final List<Module> filteredModules(Category cat) {
        String string = ((Object)StringsKt.trim((CharSequence)this.search.getText())).toString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String q = string2;
        if (((CharSequence)q).length() == 0) {
            return this.moduleList.getModules(cat);
        }
        String norm = UI.Companion.layoutNormalize(q);
        String qCompact = String.valueOf(q).replace(" ", "");
        String normCompact = String.valueOf(norm).replace(" ", "");
        List out = new ArrayList();
        EnumSet<Server> here = ServerRestrictions.current();
        for (Module m : ModuleManager.Companion.get().getAll()) {
            if (m.isHiddenInList() || ServerRestrictions.isHiddenBy(m, here)) continue;
            String name = m.getName().toLowerCase(Locale.ROOT);
            String compact = String.valueOf(name).replace(" ", "");
            if (!String.valueOf(name).contains(q) && !String.valueOf(name).contains(norm) && !String.valueOf(compact).contains(qCompact) && !String.valueOf(compact).contains(normCompact)) continue;
            out.add(m);
        }
        return ModuleListRenderer.Companion.sortPinnedFirst(out);
    }

    private final void renderModuleHeader(DrawContext graphics, float x, float y, float w, float alpha, Category cat, float dt, float dy) {
        float areaX = x + 117.0f;
        float areaY = y + 5.0f + dy;
        float areaW = w - 122.0f;
        float pad = 6.0f;
        float fieldH = 14.0f;
        float panelH = ModuleListRenderer.HEADER_OFFSET - 4.0f;
        float fieldY = areaY + (panelH - fieldH) * 0.5f;
        float avSize = 18.0f;
        float avX = areaX + areaW - pad - avSize;
        float avY = areaY + (panelH - avSize) * 0.5f;
        this.renderDiscordAvatar(graphics, avX, avY, avSize, alpha);
        String name = UI.Companion.profileName();
        String roleLabel = UI.Companion.profileRole();
        float nameSize = 6.5f;
        float roleSize = 5.0f;
        float nameW = Fonts.MEDIUM.width(name, nameSize);
        float roleW = Fonts.MEDIUM.width(roleLabel, roleSize);
        float textRight = avX - 5.0f;
        float nameTop = avY + (avSize - (nameSize + 1.0f + roleSize)) * 0.5f;
        Fonts.MEDIUM.draw(name, textRight - nameW, nameTop, nameSize, UI.Companion.color(255, 255, 255, 230, alpha));
        Fonts.MEDIUM.draw(roleLabel, textRight - roleW, nameTop + nameSize + 1.0f, roleSize, ClientAccent.accentSoftAt(195.0f * alpha, textRight - roleW, nameTop + nameSize + 1.0f));
        float profLeft = textRight - Math.max(nameW, roleW);
        float fieldX = areaX + pad;
        float btnW = this.headerButtons.width(fieldH);
        float btnX = profLeft - pad - btnW;
        float fieldW = Math.max(60.0f, btnX - 5.0f - fieldX);
        this.search.render(graphics, fieldX, fieldY, fieldW, fieldH, alpha, Position.Companion.mouseX(), Position.Companion.mouseY(), dt);
        this.headerButtons.setActiveA(this.messenger.isOpen());
        this.headerButtons.render(graphics, btnX, fieldY, fieldH, alpha, Position.Companion.mouseX(), Position.Companion.mouseY(), dt);
    }

    private final void renderDiscordAvatar(DrawContext graphics, float ax, float ay, float size, float alpha) {
        float radius = size * 0.5f;
        int white = RangesKt.coerceIn((int)MathKt.roundToInt((float)(alpha * 255.0f)), (int)0, (int)255) << 24 | 0xFFFFFF;
        String tex = DiscordAvatar.texture();
        if (tex != null && Render2D.imageReady(tex)) {
            Render2D.image(tex, ax, ay, size, size, radius, white);
            return;
        }
        Render2D.rect(ax, ay, size, size, radius, UI.Companion.color(0, 0, 0, 90, alpha));
        Render2D.outline(ax, ay, size, size, radius, 0.8f, UI.Companion.color(255, 255, 255, 30, alpha));
    }

    private final void renderNoResults(float x, float y, float w, float alpha) {
        float areaX = x + 117.0f;
        float areaW = w - 122.0f;
        float listTop = y + 5.0f + ModuleListRenderer.HEADER_OFFSET;
        float listH = 280.0f - ModuleListRenderer.HEADER_OFFSET;
        String msg = I18n.tr("Ничего не найдено");
        float size = 6.5f;
        float mw = Fonts.MEDIUM.width(msg, size);
        Fonts.MEDIUM.draw(msg, areaX + (areaW - mw) * 0.5f, listTop + listH * 0.5f - 3.0f, size, UI.Companion.color(255, 255, 255, 110, alpha));
    }

    private final void renderCategoryPanel(DrawContext graphics, float x, float y, float h, float alpha) {
        float panelX = x;
        float panelW = 110.0f;
        float left = panelX + 9.0f;
        int catCount = MAIN_CATEGORIES.length + OTHER_CATEGORIES.length;
        float brandCardTop = y + 3.0f;
        float brandCardH = 26.0f;
        RenderHelper.drawPanelBg(panelX, brandCardTop, panelW, brandCardH, 12.0f, 0.0f, 0.0f, 0.0f, alpha);
        float brandCY = brandCardTop + brandCardH * 0.5f;
        String bIcon = "x";
        float bIconSize = 11.7f;
        float brandSize = 14.299999f;
        float brandGap = 7.7999997f;
        float bIconW = Fonts.KIMIKO.msdfWidth(bIcon, bIconSize);
        float brandTextW = Fonts.SMALL_PIXEL.msdfWidth(MainWindow.CLIENT_NAME_UPPER, brandSize);
        float brandStartX = panelX + (panelW - (bIconW + brandGap + brandTextW)) * 0.5f;
        LogoToy.renderSocket(graphics, brandStartX, brandCY - bIconSize * 0.5f + 0.5f, bIconSize, alpha);
        Fonts.SMALL_PIXEL.msdf(MainWindow.CLIENT_NAME_UPPER, brandStartX + bIconW + brandGap, brandCY - brandSize * 0.5f + 0.5f, brandSize, UI.Companion.color(255, 255, 255, 255, alpha));
        float headerTop = y + 34.0f;
        float hcY = headerTop + 10.0f;
        Double d = this.modulesHeaderAnim.getOutput();
        float modulesAnim = (float)(d != null ? d : 0.0);
        if (modulesAnim > 0.004f) {
            Render2D.rect(panelX + 4.0f, headerTop + 4.5f, panelW - 8.0f, 13.333333f, 5.0f, UI.Companion.color(255, 255, 255, MathKt.roundToInt((float)((float)16 * modulesAnim)), alpha));
        }
        String mIcon = "h";
        float mIconW = Fonts.KIMIKO.msdfWidth(mIcon, 8.0f);
        AccentGradient.msdfIcon(Fonts.KIMIKO, mIcon, left, hcY - 4.0f + 1.5f, 8.0f, (200.0f + 55.0f * modulesAnim) * alpha, 0.15f);
        Fonts.MEDIUM.draw("Modules", left + mIconW + 6.0f, hcY - 4.0f + 0.5f, 8.0f, UI.Companion.color(255, 255, 255, MathKt.roundToInt((float)((float)215 + (float)40 * modulesAnim)), alpha));
        float subStartY = headerTop + 20.0f + 4.0f;
        float lineX = left + 3.0f;
        float subIconX = lineX + 9.0f;
        float firstC = subStartY + 9.0f;
        float lastC = subStartY + (float)(MAIN_CATEGORIES.length - 1) * 18.0f + 9.0f;
        Render2D.rect(lineX, firstC, 1.0f, lastC - firstC, 0.0f, UI.Companion.color(255, 255, 255, 36, alpha));
        float rowLift = 2.0f;
        int n = MAIN_CATEGORIES.length;
        for (int i = 0; i < n; ++i) {
            Category cat = MAIN_CATEGORIES[i];
            float rowTop = subStartY + (float)i * 18.0f;
            float cy = rowTop + 9.0f;
            Double d2 = this.getCategoryAnim(cat).getOutput();
            float p = (float)(d2 != null ? d2 : 0.0);
            int a = Math.min(255, 140 + MathKt.roundToInt((float)(p * (float)115)));
            int col = UI.Companion.color(255, 255, 255, a, alpha);
            String icon = String.valueOf(UI.Companion.iconChar(cat));
            float iconW = Fonts.KIMIKO.msdfWidth(icon, 7.0f);
            if (p > 0.01f) {
                float underW = (iconW + 5.0f + Fonts.MEDIUM.width(cat.getDisplayName(), 7.0f)) * p;
                AccentGradient.fillHorizontal(subIconX, cy + 8.5f - rowLift, underW, 0.75f, 0.625f, 88.0f * p * alpha);
            }
            float indexT = catCount > 1 ? (float)i / (float)(catCount - 1) : 0.5f;
            AccentGradient.msdfIcon(Fonts.KIMIKO, icon, subIconX, cy - 3.5f + 1.5f - rowLift, 7.0f, (float)a * alpha, indexT);
            Fonts.MEDIUM.draw(cat.getDisplayName(), subIconX + iconW + 5.0f, cy - 3.5f + 0.5f - rowLift, 7.0f, col);
            String count = String.valueOf(this.moduleList.getModules(cat).size());
            float countW = Fonts.MEDIUM.width(count, 5.5f);
            Fonts.MEDIUM.draw(count, panelX + panelW - 10.0f - countW, cy - 2.75f + 0.5f - rowLift, 5.5f, UI.Companion.color(255, 255, 255, MathKt.roundToInt((float)((float)80 + (float)80 * p)), alpha));
        }
        float eventsHeaderTop = subStartY + (float)MAIN_CATEGORIES.length * 18.0f + 3.0f;
        float ehcY = eventsHeaderTop + 10.0f;
        Double d3 = this.eventsHeaderAnim.getOutput();
        float eventsAnim = (float)(d3 != null ? d3 : 0.0);
        if (eventsAnim > 0.004f) {
            Render2D.rect(panelX + 4.0f, eventsHeaderTop + 4.5f, panelW - 8.0f, 13.333333f, 5.0f, UI.Companion.color(255, 255, 255, MathKt.roundToInt((float)((float)16 * eventsAnim)), alpha));
        }
        String eIcon = "e";
        float eIconW = Fonts.KIMIKO.msdfWidth(eIcon, 8.0f);
        AccentGradient.msdfIcon(Fonts.KIMIKO, eIcon, left, ehcY - 4.0f + 1.5f, 8.0f, (200.0f + 55.0f * eventsAnim) * alpha, 0.6f);
        Fonts.MEDIUM.draw("Server", left + eIconW + 6.0f, ehcY - 4.0f + 0.5f, 8.0f, UI.Companion.color(255, 255, 255, MathKt.roundToInt((float)((float)215 + (float)40 * eventsAnim)), alpha));
        float eSubStartY = eventsHeaderTop + 20.0f + 4.0f;
        float eFirstC = eSubStartY + 9.0f;
        float eLastC = eSubStartY + (float)(EVENT_SUBS.length - 1) * 18.0f + 9.0f;
        Render2D.rect(lineX, eFirstC, 1.0f, eLastC - eFirstC, 0.0f, UI.Companion.color(255, 255, 255, 36, alpha));
        int iconW = EVENT_SUBS.length;
        for (int i = 0; i < iconW; ++i) {
            float rowTop = eSubStartY + (float)i * 18.0f;
            float cy = rowTop + 9.0f;
            float p = this.eventsSubT[i];
            int a = Math.min(255, 140 + MathKt.roundToInt((float)(p * (float)115)));
            int col = UI.Companion.color(255, 255, 255, a, alpha);
            String icon = EVENT_SUB_ICONS[i];
            float iconW2 = Fonts.KIMIKO.msdfWidth(icon, 7.0f);
            if (p > 0.01f) {
                float underW = (iconW2 + 5.0f + Fonts.MEDIUM.width(EVENT_SUBS[i], 7.0f)) * p;
                AccentGradient.fillHorizontal(subIconX, cy + 8.5f - rowLift, underW, 0.75f, 0.625f, 88.0f * p * alpha);
            }
            AccentGradient.msdfIcon(Fonts.KIMIKO, icon, subIconX, cy - 3.5f + 1.5f - rowLift, 7.0f, (float)a * alpha, 0.6f);
            Fonts.MEDIUM.draw(EVENT_SUBS[i], subIconX + iconW2 + 5.0f, cy - 3.5f + 0.5f - rowLift, 7.0f, col);
        }
        float clientHeaderTop = eSubStartY + (float)EVENT_SUBS.length * 18.0f + 3.0f;
        float chcY = clientHeaderTop + 10.0f;
        Double d4 = this.clientHeaderAnim.getOutput();
        float clientAnim = (float)(d4 != null ? d4 : 0.0);
        if (clientAnim > 0.004f) {
            Render2D.rect(panelX + 4.0f, clientHeaderTop + 4.5f, panelW - 8.0f, 13.333333f, 5.0f, UI.Companion.color(255, 255, 255, MathKt.roundToInt((float)((float)16 * clientAnim)), alpha));
        }
        String cIcon = "л";
        float cIconSize = 8.5f;
        float cIconW = Fonts.I2.msdfWidth(cIcon, cIconSize);
        AccentGradient.msdfIcon(Fonts.I2, cIcon, left, chcY - cIconSize * 0.5f + 1.5f, cIconSize, (200.0f + 55.0f * clientAnim) * alpha, 0.85f);
        Fonts.MEDIUM.draw("Client", left + cIconW + 6.0f, chcY - 4.0f + 0.5f, 8.0f, UI.Companion.color(255, 255, 255, MathKt.roundToInt((float)((float)215 + (float)40 * clientAnim)), alpha));
        float cSubStartY = clientHeaderTop + 20.0f + 4.0f;
        int firstVisible = -1;
        int lastVisible = -1;
        int n2 = OTHER_CATEGORIES.length;
        for (int i = 0; i < n2; ++i) {
            if (!UI.Companion.otherRowVisible(UI.OTHER_CATEGORIES[i])) continue;
            if (firstVisible < 0) {
                firstVisible = i;
            }
            lastVisible = i;
        }
        if (firstVisible >= 0 && lastVisible > firstVisible) {
            float cFirstC = cSubStartY + (float)firstVisible * 18.0f + 9.0f;
            float cLastC = cSubStartY + (float)lastVisible * 18.0f + 9.0f;
            Render2D.rect(lineX, cFirstC, 1.0f, cLastC - cFirstC, 0.0f, UI.Companion.color(255, 255, 255, 36, alpha));
        }
        n2 = OTHER_CATEGORIES.length;
        for (int i = 0; i < n2; ++i) {
            float rowT;
            Category cat = OTHER_CATEGORIES[i];
            float f = rowT = cat == Category.THEMES ? this.themesRowT : 1.0f;
            if (rowT <= 0.01f) continue;
            float rowTop = cSubStartY + (float)i * 18.0f;
            float cy = rowTop + 9.0f;
            Double d5 = this.getCategoryAnim(cat).getOutput();
            float p = (float)(d5 != null ? d5 : 0.0);
            int a = MathKt.roundToInt((float)((float)Math.min(255, 140 + MathKt.roundToInt((float)(p * (float)115))) * rowT));
            int col = UI.Companion.color(255, 255, 255, a, alpha);
            String icon = String.valueOf(UI.Companion.iconChar(cat));
            float iconW3 = Fonts.KIMIKO.msdfWidth(icon, 7.0f);
            if (p > 0.01f) {
                float underW = (iconW3 + 5.0f + Fonts.MEDIUM.width(cat.getDisplayName(), 7.0f)) * p;
                AccentGradient.fillHorizontal(subIconX, cy + 8.5f - rowLift, underW, 0.75f, 0.625f, 88.0f * p * rowT * alpha);
            }
            AccentGradient.msdfIcon(Fonts.KIMIKO, icon, subIconX, cy - 3.5f + 1.5f - rowLift, 7.0f, (float)a * alpha, 0.85f);
            Fonts.MEDIUM.draw(cat.getDisplayName(), subIconX + iconW3 + 5.0f, cy - 3.5f + 0.5f - rowLift, 7.0f, col);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean mouseClicked(@NotNull Click event, boolean doubleClick) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            boolean bl = this.mouseClickedScaled(event, doubleClick);
            return bl;
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    private final boolean mouseClickedScaled(Click event, boolean doubleClick) {
        if (!this.screenAnim.canInteract()) {
            return true;
        }
        float w = 430.0f;
        float h = 290.0f;
        float x = Companion.panelX();
        float y = Companion.panelY();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        for (Setting s : this.settingsPopup.widgets()) {
            if (!(s instanceof BindSetting) || !((BindSetting)s).isListening()) continue;
            ((BindSetting)s).setKey(KeyBind.Companion.mouse(event.button()).getCode());
            ((BindSetting)s).setListening(false);
            return true;
        }
        if (this.bindPopup.isOpen() && this.bindPopup.mouseBind(event.button())) {
            return true;
        }
        if (this.bindPopup.isOpen() && event.button() == 0) {
            this.bindPopup.click(mx, my);
            return true;
        }
        if (this.settingsPopup.isOpen() && event.button() == 0) {
            this.settingsPopup.click(mx, my);
            return true;
        }
        if (this.settingsPopup.isOpen() && event.button() == 1 && this.settingsPopup.contains(mx, my)) {
            return true;
        }
        if (this.isModuleView() && event.button() == 0) {
            int headerButton = this.headerButtons.buttonAt(mx, my);
            if (headerButton == 0) {
                this.headerButtons.close();
                this.messenger.toggle();
                return true;
            }
            if (headerButton == 1) {
                this.headerButtons.toggleStub(1);
                return true;
            }
        }
        if (this.headerButtons.isOpen() && event.button() == 0) {
            this.headerButtons.clickModal(mx, my);
            return true;
        }
        if (this.messenger.isOpen() && this.messenger.mouseClicked(mx, my, event.button())) {
            return true;
        }
        if (LogoToy.grab(mx, my, event.button(), doubleClick)) {
            return true;
        }
        if (this.isModuleView() && this.search.mouseClicked(mx, my, event.button())) {
            return true;
        }
        if (this.contentCategory == Category.EVENTS && event.button() == 0) {
            if (this.eventsRenderer.scrollbarGrab(mx, my)) {
                return true;
            }
            if (this.eventsRenderer.click(mx, my)) {
                return true;
            }
        }
        if (this.contentCategory == Category.THEMES && event.button() == 0 && this.themesRenderer.click(x, y, w, mx, my)) {
            return true;
        }
        if (this.contentCategory == Category.COSMETICS && event.button() == 0 && this.cosmeticsRenderer.click(x, y, w, mx, my)) {
            return true;
        }
        if (this.contentCategory == Category.CONFIGS) {
            if (event.button() == 0 && this.configsRenderer.scrollbarGrab(mx, my)) {
                return true;
            }
            if (event.button() == 1 && this.configsRenderer.rightClick(mx, my)) {
                return true;
            }
            if (this.configsRenderer.click(mx, my, event.button())) {
                return true;
            }
            if (this.configsRenderer.isModalOpen()) {
                return true;
            }
        }
        if (event.button() == 0 || event.button() == 1) {
            boolean moduleView = this.isModuleView();
            if (moduleView && this.moduleList.scrollbarGrab(mx, my)) {
                return true;
            }
            float listX = x + 117.0f;
            float listY = y + 5.0f + ModuleListRenderer.HEADER_OFFSET;
            float listW = w - 122.0f;
            float listH = 280.0f - ModuleListRenderer.HEADER_OFFSET;
            if (moduleView) {
                List<Module> modules = this.filteredModules(this.contentCategory);
                if (mx >= listX && mx <= listX + listW && my >= listY && my <= listY + listH) {
                    int n = ((Collection)modules).size();
                    for (int idx = 0; idx < n; ++idx) {
                        Module mod = modules.get(idx);
                        float[] rect = this.moduleList.cardRect(modules, idx, listX, listY, listW);
                        if (rect == null) continue;
                        float cx = rect[0];
                        float cy = rect[1];
                        float colW = rect[2];
                        float cardH = rect[3];
                        if (!(mx >= cx) || !(mx <= cx + colW) || !(my >= cy) || !(my <= cy + cardH)) continue;
                        if (event.button() == 1) {
                            if (!((Collection)mod.getSettings().all()).isEmpty() && this.settingsPopup.toggle(mod, mx, my, listX, listY, listW, listH)) {
                                Sounds.play("module_settings_open");
                            }
                            return true;
                        }
                        if (this.search.hasText() && UI.Companion.ctrlHeld()) {
                            Category target = mod.getCategory();
                            this.moduleList.focusModule(mod.getName());
                            if (this.targetCategory != target) {
                                Sounds.play("select_category");
                                this.selectCategory(target);
                            } else {
                                this.search.setText("");
                                this.search.blur();
                            }
                            return true;
                        }
                        if (this.moduleList.hitSettingsIcon(mx, my, cx, cy, colW, mod)) {
                            if (this.settingsPopup.toggle(mod, mx, my, listX, listY, listW, listH)) {
                                Sounds.play("module_settings_open");
                            }
                            return true;
                        }
                        if (!ModuleListRenderer.Companion.isPinned(mod)) {
                            mod.toggle();
                        }
                        return true;
                    }
                }
            }
            if (event.button() == 1) {
                return true;
            }
        }
        if (event.button() != 0) {
            return super.mouseClicked(event, doubleClick);
        }
        int eventsSubClicked = this.eventsSubAt(x, y, mx, my);
        if (eventsSubClicked >= 0) {
            if (this.targetCategory != Category.EVENTS || this.eventsSub != eventsSubClicked) {
                Sounds.play("select_category");
            }
            this.selectEventsSub(eventsSubClicked);
            return true;
        }
        Category clicked = this.categoryButtonAt(x, y, mx, my);
        if (clicked != null) {
            if (clicked != this.targetCategory) {
                Sounds.play("select_category");
            }
            this.selectCategory(clicked);
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    private final Category categoryButtonAt(float x, float y, float mx, float my) {
        float panelX = x + 5.0f;
        float panelW = 110.0f;
        float hitX0 = panelX + 4.0f;
        float hitX1 = panelX + panelW - 4.0f;
        float colY = y + 2.0f;
        float subStartY = colY + 34.0f + 20.0f + 4.0f;
        int n = MAIN_CATEGORIES.length;
        for (int i = 0; i < n; ++i) {
            float top = subStartY + (float)i * 18.0f;
            if (!(mx >= hitX0) || !(mx <= hitX1) || !(my >= top) || !(my <= top + 18.0f)) continue;
            return MAIN_CATEGORIES[i];
        }
        float eventsHeaderTop = subStartY + (float)MAIN_CATEGORIES.length * 18.0f + 3.0f;
        float eSubStartY = eventsHeaderTop + 20.0f + 4.0f;
        float clientHeaderTop = eSubStartY + (float)EVENT_SUBS.length * 18.0f + 3.0f;
        float cSubStartY = clientHeaderTop + 20.0f + 4.0f;
        int n2 = OTHER_CATEGORIES.length;
        for (int i = 0; i < n2; ++i) {
            float top = cSubStartY + (float)i * 18.0f;
            if (!(mx >= hitX0) || !(mx <= hitX1) || !(my >= top) || !(my <= top + 18.0f)) continue;
            Category cat = OTHER_CATEGORIES[i];
            if (cat == Category.THEMES && this.themesRowT < 0.5f) {
                return null;
            }
            return cat;
        }
        return null;
    }

    private final int eventsSubAt(float x, float y, float mx, float my) {
        float panelX = x + 5.0f;
        float panelW = 110.0f;
        float hitX0 = panelX + 4.0f;
        float hitX1 = panelX + panelW - 4.0f;
        float colY = y + 2.0f;
        float subStartY = colY + 34.0f + 20.0f + 4.0f;
        float eventsHeaderTop = subStartY + (float)MAIN_CATEGORIES.length * 18.0f + 3.0f;
        float eSubStartY = eventsHeaderTop + 20.0f + 4.0f;
        int n = EVENT_SUBS.length;
        for (int i = 0; i < n; ++i) {
            float top = eSubStartY + (float)i * 18.0f;
            if (!(mx >= hitX0) || !(mx <= hitX1) || !(my >= top) || !(my <= top + 18.0f)) continue;
            return i;
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean mouseReleased(@NotNull Click event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            boolean bl = this.mouseReleasedScaled(event);
            return bl;
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    private final boolean mouseReleasedScaled(Click event) {
        if (this.screenAnim.isClosing()) {
            return true;
        }
        if (event.button() == 0) {
            LogoToy.release();
            this.settingsPopup.releaseDrags();
            this.bindPopup.releaseDrag();
        }
        this.moduleList.scrollbarRelease();
        this.eventsRenderer.scrollbarRelease();
        this.configsRenderer.scrollbarRelease();
        this.configsRenderer.mouseReleased(event.button());
        this.search.mouseReleased(event.button());
        this.messenger.mouseReleased(event.button());
        return super.mouseReleased(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontal, double vertical) {
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            boolean bl = this.mouseScrolledScaled(mouseX, mouseY, horizontal, vertical);
            return bl;
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    private final boolean mouseScrolledScaled(double mouseX, double mouseY, double horizontal, double vertical) {
        if (!this.screenAnim.canInteract()) {
            return true;
        }
        if (UI.Companion.ctrlHeld() && Math.abs(vertical) > 0.0) {
            UiScale.shift(vertical > 0.0 ? 1 : -1);
            return true;
        }
        if (this.bindPopup.isOpen()) {
            this.bindPopup.close();
            return true;
        }
        double mx = Position.Companion.mouseX();
        double my = Position.Companion.mouseY();
        if (this.settingsPopup.isOpen() && this.settingsPopup.scroll((float)mx, (float)my, vertical)) {
            return true;
        }
        if (this.messenger.isOpen() && this.messenger.contains(Position.Companion.mouseX(), Position.Companion.mouseY())) {
            this.messenger.scroll(vertical);
            return true;
        }
        float w = 430.0f;
        float x = Companion.panelX();
        float y = Companion.panelY();
        float listX = x + 117.0f;
        float listY = y + 5.0f;
        float listW = w - 122.0f;
        float listH = 280.0f;
        if (mx >= (double)listX && mx <= (double)(listX + listW) && my >= (double)listY && my <= (double)(listY + listH)) {
            if (this.contentCategory == Category.EVENTS) {
                this.eventsRenderer.scroll(vertical, listH);
            } else if (this.contentCategory == Category.THEMES) {
                this.themesRenderer.scroll(vertical, listH);
            } else if (this.contentCategory == Category.CONFIGS) {
                this.configsRenderer.scroll(vertical, listH);
            } else if (this.contentCategory == Category.COSMETICS) {
                this.cosmeticsRenderer.scroll(vertical, listH);
            } else {
                this.settingsPopup.close();
                this.moduleList.scroll(vertical, listH - ModuleListRenderer.HEADER_OFFSET);
            }
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontal, vertical);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private final void onRawMouse(MouseButtonEvent event) {
        if (!Intrinsics.areEqual((Object)MinecraftClient.getInstance().currentScreen, (Object)this)) {
            return;
        }
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            this.onRawMouseScaled(event);
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    private final void onRawMouseScaled(MouseButtonEvent event) {
        if (event.button != 2) {
            return;
        }
        if (event.action == MouseButtonEvent.Action.RELEASE) {
            if (PanelDrag.isDragging()) {
                PanelDrag.end();
                event.cancel();
            }
            return;
        }
        if (!this.screenAnim.canInteract()) {
            return;
        }
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        if (this.settingsPopup.isOpen() && this.settingsPopup.middleClick(mx, my)) {
            event.cancel();
            return;
        }
        Module mod = this.moduleAtCursor();
        if (mod != null) {
            this.bindPopup.open(mod, mx, my);
            event.cancel();
            return;
        }
        if (this.canGrabPanel(mx, my)) {
            PanelDrag.begin(mx, my);
            event.cancel();
        }
    }

    private final boolean canGrabPanel(float mx, float my) {
        if (this.bindPopup.isOpen() || this.settingsPopup.contains(mx, my)) {
            return false;
        }
        if (this.contentCategory == Category.CONFIGS && this.configsRenderer.isModalOpen()) {
            return false;
        }
        if (this.messenger.isVisible() && this.messenger.contains(mx, my)) {
            return false;
        }
        float x = Companion.panelX();
        float y = Companion.panelY();
        return mx >= x && mx <= x + 430.0f && my >= y && my <= y + 290.0f;
    }

    private final Module moduleAtCursor() {
        if (!this.isModuleView()) {
            return null;
        }
        float w = 430.0f;
        float x = Companion.panelX();
        float y = Companion.panelY();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float listX = x + 117.0f;
        float listY = y + 5.0f + ModuleListRenderer.HEADER_OFFSET;
        float listW = w - 122.0f;
        float listH = 280.0f - ModuleListRenderer.HEADER_OFFSET;
        if (mx < listX || mx > listX + listW || my < listY || my > listY + listH) {
            return null;
        }
        List<Module> modules = this.filteredModules(this.contentCategory);
        int n = ((Collection)modules).size();
        for (int idx = 0; idx < n; ++idx) {
            float[] rect = this.moduleList.cardRect(modules, idx, listX, listY, listW);
            if (rect == null || !(mx >= rect[0]) || !(mx <= rect[0] + rect[2]) || !(my >= rect[1]) || !(my <= rect[1] + rect[3])) continue;
            return modules.get(idx);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean keyPressed(@NotNull KeyInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            boolean bl = this.keyPressedScaled(event);
            return bl;
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    private final boolean keyPressedScaled(KeyInput event) {
        if (this.screenAnim.isClosing()) {
            ClickGui reopenGui = ModuleManager.Companion.get().get(ClickGui.class);
            int reopenKey = reopenGui != null && reopenGui.getBind() != null ? reopenGui.getBind().getCode() : 344;
            if (event.key() == reopenKey) {
                pendingAfterClose = null;
                WorldGuiCloseAnimation.reverse();
                GuiShatterAnimation.gather(WorldGuiCloseAnimation.isReversing() ? WorldGuiCloseAnimation.remainingNanos() : 0L);
                this.screenAnim.resumeOpening();
                Sounds.play("gui_open");
            }
            return true;
        }
        if (this.bindPopup.isOpen()) {
            if (this.bindPopup.keyPressed(event.key())) {
                return true;
            }
            if (event.key() == 256) {
                this.bindPopup.close();
            }
            return true;
        }
        for (Setting s : this.settingsPopup.widgets()) {
            if (!(s instanceof BindSetting) || !((BindSetting)s).isListening()) continue;
            int k = event.key();
            boolean clear = k == 256 || k == 261;
            ((BindSetting)s).setKey(clear ? -1 : k);
            ((BindSetting)s).setListening(false);
            return true;
        }
        for (Setting s : this.settingsPopup.widgets()) {
            if (!(s instanceof TextSetting) || !((TextSetting)s).isFocused() || !((TextSetting)s).typeKey(event.key())) continue;
            return true;
        }
        if (UI.Companion.isPanelResetCombo(event.key()) && PanelDrag.isMoved()) {
            PanelDrag.reset();
            return true;
        }
        if (UI.Companion.ctrlHeld() && UI.Companion.scaleHotkey(event.key())) {
            return true;
        }
        if (event.key() == 256 && this.settingsPopup.hasOpenOverlay()) {
            this.settingsPopup.closeOverlays();
            return true;
        }
        if (event.key() == 256 && this.settingsPopup.isOpen()) {
            this.settingsPopup.close();
            return true;
        }
        if (this.contentCategory == Category.CONFIGS && this.configsRenderer.keyPressed(event)) {
            return true;
        }
        if (this.messenger.isTyping() && this.messenger.keyPressed(event)) {
            return true;
        }
        if (this.search.isTyping() && this.search.keyPressed(event)) {
            return true;
        }
        ClickGui clickGui = ModuleManager.Companion.get().get(ClickGui.class);
        int toggleKey = clickGui != null && clickGui.getBind() != null ? clickGui.getBind().getCode() : 344;
        if (event.key() == 256 || event.key() == toggleKey) {
            this.close();
            return true;
        }
        return super.keyPressed(event);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean charTyped(@NotNull CharInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
        try {
            boolean bl = this.charTypedScaled(event);
            return bl;
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    private final boolean charTypedScaled(CharInput event) {
        if (this.screenAnim.isClosing()) {
            return true;
        }
        if (this.bindPopup.isOpen()) {
            return true;
        }
        for (Setting s : this.settingsPopup.widgets()) {
            if (!(s instanceof TextSetting) || !((TextSetting)s).isFocused()) continue;
            ((TextSetting)s).typeChar(event.codepoint());
            return true;
        }
        if (this.contentCategory == Category.CONFIGS && this.configsRenderer.charTyped(event)) {
            return true;
        }
        if (this.messenger.charTyped(event)) {
            return true;
        }
        if (this.search.isTyping() && this.search.charTyped(event)) {
            return true;
        }
        return super.charTyped(event);
    }

    private final void stagePopupBlur() {
        if (!popupBlurStaged) {
            return;
        }
        popupBlurStaged = false;
        int count = 1;
        if (this.settingsPopup.writeBlurRect(popupBlurMask, count * 6) && popupBlurMask[count * 6 + 5] > 0.004f) {
            ++count;
        }
        if (this.bindPopup.writeBlurRect(popupBlurMask, count * 6) && popupBlurMask[count * 6 + 5] > 0.004f) {
            ++count;
        }
        if (this.headerButtons.writeBlurRect(popupBlurMask, count * 6) && popupBlurMask[count * 6 + 5] > 0.004f) {
            ++count;
        }
        if (count <= 1) {
            return;
        }
        float maxPhase = 0.0f;
        int n = count;
        for (int i = 1; i < n; ++i) {
            maxPhase = Math.max(maxPhase, popupBlurMask[i * 6 + 5]);
        }
        if (maxPhase <= 0.004f) {
            return;
        }
        float radius = Math.max(0.5f, UI.Companion.pixelSize(9.0f) * maxPhase);
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -3.4028235E38f;
        float maxY = -3.4028235E38f;
        int n2 = count;
        for (int i = 1; i < n2; ++i) {
            int o = i * 6;
            float rx = UI.Companion.pixelX(UI.popupBlurMask[o] + this.parallaxX);
            float ry = UI.Companion.pixelY(UI.popupBlurMask[o + 1] + this.parallaxY);
            float rw = UI.Companion.pixelSize(UI.popupBlurMask[o + 2]);
            float rh = UI.Companion.pixelSize(UI.popupBlurMask[o + 3]);
            UI.popupBlurMask[o] = rx;
            UI.popupBlurMask[o + 1] = ry;
            UI.popupBlurMask[o + 2] = rw;
            UI.popupBlurMask[o + 3] = rh;
            minX = Math.min(minX, rx);
            minY = Math.min(minY, ry);
            maxX = Math.max(maxX, rx + rw);
            maxY = Math.max(maxY, ry + rh);
        }
        float boundsPad = 24.0f + radius;
        UI.popupBlurMask[0] = minX - boundsPad;
        UI.popupBlurMask[1] = minY - boundsPad;
        UI.popupBlurMask[2] = maxX - minX + boundsPad * 2.0f;
        UI.popupBlurMask[3] = maxY - minY + boundsPad * 2.0f;
        UI.popupBlurMask[4] = -1.0f;
        UI.popupBlurMask[5] = 0.0f;
        popupBlurMaskCount = count;
        float scissorPad = boundsPad + radius * 2.0f + 8.0f;
        popupBlurRadius = radius;
        popupBlurX = MathKt.roundToInt((float)(minX - scissorPad));
        popupBlurY = MathKt.roundToInt((float)(minY - scissorPad));
        popupBlurW = MathKt.roundToInt((float)(maxX - minX + scissorPad * 2.0f));
        popupBlurH = MathKt.roundToInt((float)(maxY - minY + scissorPad * 2.0f));
        popupBlurOriginX = (minX + maxX) * 0.5f;
        popupBlurOriginY = (minY + maxY) * 0.5f;
        popupBlurPending = true;
    }

    private final void stageCardBlur(float x, float y, float w) {
        if (motionBlurPending || !cardCaptureStaged) {
            return;
        }
        int cards = 0;
        float maxPhase = 0.0f;
        float[] rects = null;
        float contentX = x + 117.0f;
        float contentY = 0.0f;
        float contentW = w - 122.0f;
        float contentH = 0.0f;
        if (this.moduleList.cardBlurCount() > 0) {
            cards = this.moduleList.cardBlurCount();
            maxPhase = this.moduleList.cardBlurMaxPhase();
            rects = this.moduleList.cardBlurRects();
            contentY = y + 5.0f + ModuleListRenderer.HEADER_OFFSET;
            contentH = 280.0f - ModuleListRenderer.HEADER_OFFSET;
        } else if (this.eventsRenderer.cardBlurCount() > 0) {
            cards = this.eventsRenderer.cardBlurCount();
            maxPhase = this.eventsRenderer.cardBlurMaxPhase();
            rects = this.eventsRenderer.cardBlurRects();
            contentY = y + 5.0f;
            contentH = 280.0f;
        } else if (this.configsRenderer.cardBlurCount() > 0) {
            cards = this.configsRenderer.cardBlurCount();
            maxPhase = this.configsRenderer.cardBlurMaxPhase();
            rects = this.configsRenderer.cardBlurRects();
            contentY = y + 5.0f + ModuleListRenderer.HEADER_OFFSET;
            contentH = 280.0f - ModuleListRenderer.HEADER_OFFSET;
        } else {
            cards = this.themesRenderer.cardBlurCount();
            maxPhase = this.themesRenderer.cardBlurMaxPhase();
            rects = this.themesRenderer.cardBlurRects();
            contentY = y + 5.0f;
            contentH = 280.0f;
        }
        if (cards <= 0 || maxPhase <= 0.003f) {
            return;
        }
        float zoneX = UI.Companion.pixelX(contentX + this.parallaxX);
        float zoneY = UI.Companion.pixelY(contentY + this.parallaxY);
        float zoneW = UI.Companion.pixelSize(contentW);
        float zoneH = UI.Companion.pixelSize(contentH);
        float boundsPad = 48.0f;
        UI.cardBlurMask[0] = zoneX - boundsPad;
        UI.cardBlurMask[1] = zoneY - boundsPad;
        UI.cardBlurMask[2] = zoneW + boundsPad * 2.0f;
        UI.cardBlurMask[3] = zoneH + boundsPad * 2.0f;
        UI.cardBlurMask[4] = -1.0f;
        UI.cardBlurMask[5] = 0.0f;
        int count = 1;
        for (int i = 0; i < cards; ++i) {
            int src = i * 6;
            int dst = count * 6;
            UI.cardBlurMask[dst] = UI.Companion.pixelX(rects[src] + this.parallaxX);
            UI.cardBlurMask[dst + 1] = UI.Companion.pixelY(rects[src + 1] + this.parallaxY);
            UI.cardBlurMask[dst + 2] = UI.Companion.pixelSize(rects[src + 2]);
            UI.cardBlurMask[dst + 3] = UI.Companion.pixelSize(rects[src + 3]);
            UI.cardBlurMask[dst + 4] = rects[src + 4];
            UI.cardBlurMask[dst + 5] = rects[src + 5] / maxPhase;
            ++count;
        }
        cardBlurMaskCount = count;
        motionBlurOpacity = 1.0f;
        motionBlurRadius = Math.max(0.5f, 22.0f * maxPhase);
        float scissorPad = boundsPad + motionBlurRadius * 2.0f + 8.0f;
        motionBlurX = MathKt.roundToInt((float)(zoneX - scissorPad));
        motionBlurY = MathKt.roundToInt((float)(zoneY - scissorPad));
        motionBlurW = MathKt.roundToInt((float)(zoneW + scissorPad * 2.0f));
        motionBlurH = MathKt.roundToInt((float)(zoneH + scissorPad * 2.0f));
        motionBlurScale = 1.0f;
        motionBlurOriginX = UI.Companion.pixelX(contentX + this.parallaxX + contentW * 0.5f);
        motionBlurOriginY = UI.Companion.pixelY(contentY + this.parallaxY + contentH * 0.5f);
        cardBlurPending = true;
    }


    @JvmStatic
    public static final boolean isOpen() {
        return Companion.isOpen();
    }

    @JvmStatic
    public static final float panelX() {
        return Companion.panelX();
    }

    @JvmStatic
    public static final float panelY() {
        return Companion.panelY();
    }

    @JvmStatic
    public static final boolean isSettingsOpenFor(@NotNull String moduleName) {
        return Companion.isSettingsOpenFor(moduleName);
    }

    @JvmStatic
    public static final boolean isSettingsPopupVisible() {
        return Companion.isSettingsPopupVisible();
    }

    @JvmStatic
    public static final void closeInto(@Nullable Screen next) {
        Companion.closeInto(next);
    }

    @JvmStatic
    public static final boolean isSearchTyping() {
        return Companion.isSearchTyping();
    }

    @JvmStatic
    @NotNull
    public static final GuiShareCloseState shareCloseState() {
        return Companion.shareCloseState();
    }

    @JvmStatic
    @Nullable
    public static final GuiShareLocalSnapshot shareSnapshot() {
        return Companion.shareSnapshot();
    }

    @JvmStatic
    public static final void markCardStratum() {
        Companion.markCardStratum();
    }

    @JvmStatic
    public static final boolean consumeCardStratumMark() {
        return Companion.consumeCardStratumMark();
    }

    @JvmStatic
    public static final void markPopupStratum(boolean wantsBlur, boolean staged) {
        Companion.markPopupStratum(wantsBlur, staged);
    }

    @JvmStatic
    public static final void markPopupLayerCapture() {
        Companion.markPopupLayerCapture();
    }

    @JvmStatic
    public static final boolean popupLayerCapturePending() {
        return Companion.popupLayerCapturePending();
    }

    @JvmStatic
    public static final boolean consumePopupStratumMark() {
        return Companion.consumePopupStratumMark();
    }

    @JvmStatic
    public static final boolean consumePopupBlurCapture() {
        return Companion.consumePopupBlurCapture();
    }

    @JvmStatic
    public static final boolean consumePopupLayerCapture() {
        return Companion.consumePopupLayerCapture();
    }

    @JvmStatic
    public static final void dropPendingBlurs() {
        Companion.dropPendingBlurs();
    }

    @JvmStatic
    public static final boolean consumePanelSplitMark() {
        return Companion.consumePanelSplitMark();
    }

    @JvmStatic
    public static final void requestVanillaBlurAtSplit() {
        Companion.requestVanillaBlurAtSplit();
    }

    @JvmStatic
    public static final boolean consumeVanillaBlurRequest() {
        return Companion.consumeVanillaBlurRequest();
    }

    @JvmStatic
    public static final void applyMainCompositeAtSplit() {
        Companion.applyMainCompositeAtSplit();
    }

    @JvmStatic
    public static final boolean motionBlurCapturePending() {
        return Companion.motionBlurCapturePending();
    }

    @JvmStatic
    public static final float motionBlurCaptureRadius() {
        return Companion.motionBlurCaptureRadius();
    }

    @JvmStatic
    public static final boolean guiCaptureActive() {
        return Companion.guiCaptureActive();
    }

    @JvmStatic
    public static final float guiCaptureScale() {
        return Companion.guiCaptureScale();
    }

    @JvmStatic
    public static final float guiCaptureBlurRadius() {
        return Companion.guiCaptureBlurRadius();
    }

    @JvmStatic
    public static final float guiShatterProgress() {
        return Companion.guiShatterProgress();
    }

    @JvmStatic
    public static final void flushMotionBlur() {
        Companion.flushMotionBlur();
    }

    @JvmStatic
    public static final void renderClosingPanelOverHud(@NotNull DrawContext g) {
        Companion.renderClosingPanelOverHud(g);
    }

    static {
        EVENT_SUBS = new String[]{"Events", "Mines"};
        EVENT_SUB_ICONS = new String[]{"b", "m"};
        INSTANCE = new UI();
        MAIN_CATEGORIES = new Category[]{Category.VISUALS, Category.DISPLAY, Category.UTILS};
        OTHER_CATEGORIES = new Category[]{Category.CONFIGS, Category.THEMES, Category.COSMETICS};
        motionBlurMask = new float[12];
        cardBlurMask = new float[396];
        popupBlurMask = new float[24];
        guiCaptureScale = 1.0f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\f\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u0014\n\u0002\b \b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\nJ\u001b\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0007J\u0019\u0010\u0013\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0014J\u001d\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u0007J\u0013\u0010\u001f\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\u0004\u0018\u00010!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u0004\u0018\u00010!H\u0002\u00a2\u0006\u0004\b$\u0010#J\u0019\u0010'\u001a\u00020\f2\b\u0010&\u001a\u0004\u0018\u00010%H\u0002\u00a2\u0006\u0004\b'\u0010(J\u0013\u0010)\u001a\u00020\u001aH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b)\u0010\u0003J\u0013\u0010*\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b*\u0010\u0007J#\u0010-\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b-\u0010.J\u0013\u0010/\u001a\u00020\u001aH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b/\u0010\u0003J\u0013\u00100\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b0\u0010\u0007J\u0013\u00101\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b1\u0010\u0007J\u0013\u00102\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b2\u0010\u0007J\u0013\u00103\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b3\u0010\u0007J\u0013\u00104\u001a\u00020\u001aH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b4\u0010\u0003J\u0013\u00105\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b5\u0010\u0007J\u0013\u00106\u001a\u00020\u001aH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b6\u0010\u0003J\u0013\u00107\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b7\u0010\u0007J\u0013\u00108\u001a\u00020\u001aH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b8\u0010\u0003J\u0013\u00109\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b9\u0010\u0007J\u0013\u0010:\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b:\u0010\nJ\u0013\u0010;\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b;\u0010\u0007J\u0013\u0010<\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b<\u0010\nJ\u0013\u0010=\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b=\u0010\nJ\u000f\u0010>\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b>\u0010\u0003J\u0017\u0010@\u001a\u00020\b2\u0006\u0010?\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0017\u0010C\u001a\u00020\b2\u0006\u0010B\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bC\u0010AJ\u0017\u0010E\u001a\u00020\b2\u0006\u0010D\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bE\u0010AJ\u000f\u0010F\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bF\u0010\nJ\u0013\u0010G\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bG\u0010\nJ\u0013\u0010H\u001a\u00020\u001aH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bH\u0010\u0003J\u001b\u0010K\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020IH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bK\u0010LJ\u0017\u0010P\u001a\u00020O2\u0006\u0010N\u001a\u00020MH\u0002\u00a2\u0006\u0004\bP\u0010QJ\u0017\u0010S\u001a\u00020\f2\u0006\u0010R\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bU\u0010VJ\u000f\u0010W\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bW\u0010VJ\u000f\u0010X\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bX\u0010\u0007J\u000f\u0010Y\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bY\u0010\u0007J\u0017\u0010[\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020MH\u0002\u00a2\u0006\u0004\b[\u0010\\J\u0017\u0010_\u001a\u00020^2\u0006\u0010]\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b_\u0010`J\u0017\u0010a\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020MH\u0002\u00a2\u0006\u0004\ba\u0010\\J7\u0010g\u001a\u00020M2\u0006\u0010b\u001a\u00020M2\u0006\u0010c\u001a\u00020M2\u0006\u0010d\u001a\u00020M2\u0006\u0010e\u001a\u00020M2\u0006\u0010f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bg\u0010hR\u001a\u0010j\u001a\b\u0012\u0004\u0012\u00020\f0i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u001a\u0010l\u001a\b\u0012\u0004\u0012\u00020\f0i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010kR\u0019\u0010o\u001a\u00020m8\u0006X\u0087\u0004\u0092\u0002\u0002\bn\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0014\u0010q\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0014\u0010s\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bs\u0010rR\u0014\u0010t\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bt\u0010rR\u0014\u0010u\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bu\u0010rR\u0014\u0010v\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bv\u0010rR\u0014\u0010w\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bw\u0010rR\u0014\u0010x\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bx\u0010rR\u001a\u0010y\u001a\b\u0012\u0004\u0012\u00020\u00110i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010zR\u001a\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00110i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0014\u0010|\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010rR\u0014\u0010}\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010rR\u0014\u0010~\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b~\u0010rR\u0014\u0010\u007f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u007f\u0010rR\u0016\u0010\u0080\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010rR\u0016\u0010\u0081\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010rR\u001b\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0019\u0010\u0084\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0018\u0010\u0086\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010rR\u0018\u0010\u0087\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010rR\u0018\u0010\u0088\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0088\u0001\u0010rR\u0018\u0010\u0089\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010rR\u0018\u0010\u008a\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008a\u0001\u0010rR\u0019\u0010\u008b\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0019\u0010\u008d\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008c\u0001R\u0019\u0010\u008e\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008c\u0001R\u0019\u0010\u008f\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u008c\u0001R\u0019\u0010\u0090\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u008c\u0001R\u0019\u0010\u0091\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u008c\u0001R\u0019\u0010\u0092\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u008c\u0001R\u0019\u0010\u0093\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u008c\u0001R\u0018\u0010\u0095\u0001\u001a\u00030\u0094\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0016\u0010\u0097\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010rR\u0019\u0010\u0098\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0085\u0001R\u0019\u0010\u0099\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0085\u0001R\u0018\u0010\u009a\u0001\u001a\u00030\u0094\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0096\u0001R\u0019\u0010\u009b\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u008c\u0001R\u0019\u0010\u009c\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0085\u0001R\u0019\u0010\u009d\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u0085\u0001R\u0019\u0010\u009e\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u0085\u0001R\u0019\u0010\u009f\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u0085\u0001R\u0019\u0010\u00a0\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u0085\u0001R\u0016\u0010\u00a1\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a1\u0001\u0010rR\u0018\u0010\u00a2\u0001\u001a\u00030\u0094\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u0096\u0001R\u0019\u0010\u00a3\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u008c\u0001R\u0019\u0010\u00a4\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u0085\u0001R\u0019\u0010\u00a5\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u0085\u0001R\u0019\u0010\u00a6\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u0085\u0001R\u0019\u0010\u00a7\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a7\u0001\u0010\u0085\u0001R\u0019\u0010\u00a8\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u0085\u0001R\u0018\u0010\u00a9\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a9\u0001\u0010rR\u0019\u0010\u00aa\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00aa\u0001\u0010\u008c\u0001R\u0019\u0010«\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u008c\u0001R\u0019\u0010\u00ac\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u008c\u0001R\u0019\u0010\u00ad\u0001\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u008c\u0001R\u0018\u0010\u00ae\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ae\u0001\u0010rR\u0018\u0010\u00af\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00af\u0001\u0010rR\u0016\u0010<\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010rR\u0018\u0010\u00b0\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b0\u0001\u0010rR\u0017\u0010\u00b1\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00b2\u0001R\u0017\u0010\u00b3\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00b2\u0001\u00a8\u0006\u00b4\u0001"}, d2={"Lrtx/kimiko/api/ui/UI.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isOpen", "()Z", "", "panelX", "()F", "panelY", "", "moduleName", "isSettingsOpenFor", "(Ljava/lang/String;)Z", "isSettingsPopupVisible", "Lrtx/kimiko/api/modules/Category;", "c", "isMainCategory", "(Lrtx/kimiko/api/modules/Category;)Z", "isOtherCategory", "cat", "otherRowVisible", "Lnet/minecraft/Screen;", "next", "", "closeInto", "(Lnet/minecraft/Screen;)V", "isSearchTyping", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "shareCloseState", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareLocalSnapshot;", "shareSnapshot", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareLocalSnapshot;", "shareSnapshotScaled", "Lrtx/kimiko/api/modules/Module;", "module", "shareBadge", "(Lrtx/kimiko/api/modules/Module;)Ljava/lang/String;", "markCardStratum", "consumeCardStratumMark", "wantsBlur", "staged", "markPopupStratum", "(ZZ)V", "markPopupLayerCapture", "popupLayerCapturePending", "consumePopupStratumMark", "consumePopupBlurCapture", "consumePopupLayerCapture", "dropPendingBlurs", "consumePanelSplitMark", "requestVanillaBlurAtSplit", "consumeVanillaBlurRequest", "applyMainCompositeAtSplit", "motionBlurCapturePending", "motionBlurCaptureRadius", "guiCaptureActive", "guiCaptureScale", "guiCaptureBlurRadius", "beginShatter", "designSize", "pixelSize", "(F)F", "designX", "pixelX", "designY", "pixelY", "rawCloseProgress", "guiShatterProgress", "flushMotionBlur", "Lnet/minecraft/DrawContext;", "g", "renderClosingPanelOverHud", "(Lnet/minecraft/DrawContext;)V", "", "ms", "Lrtx/kimiko/utils/animations/Decelerate;", "createAnim", "(I)Lrtx/kimiko/utils/animations/Decelerate;", "s", "layoutNormalize", "(Ljava/lang/String;)Ljava/lang/String;", "profileRole", "()Ljava/lang/String;", "profileName", "ctrlHeld", "altHeld", "key", "isPanelResetCombo", "(I)Z", "category", "", "iconChar", "(Lrtx/kimiko/api/modules/Category;)C", "scaleHotkey", "red", "green", "blue", "alpha", "multiplier", "color", "(IIIIF)I", "", "EVENT_SUBS", "[Ljava/lang/String;", "EVENT_SUB_ICONS", "Lrtx/kimiko/api/ui/UI;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/ui/UI;", "PANEL_W", "F", "PANEL_H", "SIDEBAR_W", "CONTENT_X_OFF", "CONTENT_INSET", "CONTENT_HEIGHT", "CONTENT_Y_OFFSET", "MAIN_CATEGORIES", "[Lrtx/kimiko/api/modules/Category;", "OTHER_CATEGORIES", "CAT_COL_TOP", "CAT_HEADER_H", "CAT_SUB_GAP", "CAT_SUB_ROW_H", "CAT_EVENTS_GAP", "CATEGORY_FADE_SEC", "pendingAfterClose", "Lnet/minecraft/Screen;", "motionBlurPending", "Z", "motionBlurOpacity", "motionBlurRadius", "motionBlurScale", "motionBlurOriginX", "motionBlurOriginY", "motionBlurX", "I", "motionBlurY", "motionBlurW", "motionBlurH", "motionBlurSrcX", "motionBlurSrcY", "motionBlurSrcW", "motionBlurSrcH", "", "motionBlurMask", "[F", "CARD_BLUR_MAX_RADIUS", "cardStratumMarked", "cardBlurPending", "cardBlurMask", "cardBlurMaskCount", "cardCaptureStaged", "cardBlurCaptured", "panelSplitMarked", "vanillaBlurRequested", "popupStratumMarked", "POPUP_BLUR_GUI_RADIUS", "popupBlurMask", "popupBlurMaskCount", "popupLayerBlurWanted", "popupBlurWanted", "popupBlurStaged", "popupBlurCaptured", "popupBlurPending", "popupBlurRadius", "popupBlurX", "popupBlurY", "popupBlurW", "popupBlurH", "popupBlurOriginX", "popupBlurOriginY", "guiCaptureBlurMainPx", "RU_LAYOUT", "Ljava/lang/String;", "EN_LAYOUT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final boolean isOpen() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            return Intrinsics.areEqual((Object)mc.currentScreen, (Object)INSTANCE);
        }

        @JvmStatic
        public final float panelX() {
            return Position.Companion.screenWidth() / 2.0f - 215.0f + PanelDrag.x();
        }

        @JvmStatic
        public final float panelY() {
            return Position.Companion.screenHeight() / 2.0f - 145.0f + PanelDrag.y();
        }

        @JvmStatic
        public final boolean isSettingsOpenFor(@NotNull String moduleName) {
            Intrinsics.checkNotNullParameter((Object)moduleName, (String)"moduleName");
            return this.isOpen() && INSTANCE.settingsPopup.isOpenFor(moduleName);
        }

        @JvmStatic
        public final boolean isSettingsPopupVisible() {
            return this.isOpen() && INSTANCE.settingsPopup.isVisible();
        }

        private final boolean isMainCategory(Category c) {
            if (c == null) {
                return false;
            }
            for (Category m : MAIN_CATEGORIES) {
                if (m != c) continue;
                return true;
            }
            return false;
        }

        private final boolean isOtherCategory(Category c) {
            if (c == null) {
                return false;
            }
            for (Category m : OTHER_CATEGORIES) {
                if (m != c) continue;
                return true;
            }
            return false;
        }

        private final boolean otherRowVisible(Category cat) {
            return cat != Category.THEMES || INSTANCE.themesRowT > 0.01f;
        }

        @JvmStatic
        public final void closeInto(@Nullable Screen next) {
            pendingAfterClose = next;
            UI ui = INSTANCE;
            GuiCapture.bind(ui);
            if (!ui.screenAnim.isClosing()) {
                float handoffAlpha = ui.screenAnim.alpha();
                float handoffScale = ui.screenAnim.scale();
                ui.screenAnim.startClosing();
                if (GuiLayerBlurRenderer.available()) {
                    WorldGuiCloseAnimation.begin(handoffAlpha, handoffScale);
                    this.beginShatter();
                } else {
                    WorldGuiCloseAnimation.cancel();
                    GuiShatterAnimation.cancel();
                }
                ui.releaseAllDrags();
                ui.settingsPopup.closeSilent();
                ui.headerButtons.closeSilent();
                ui.messenger.detachForGuiClose();
                ui.search.blur();
            }
            if (Intrinsics.areEqual((Object)MinecraftClient.getInstance().currentScreen, (Object)ui)) {
                MinecraftClient.getInstance().setScreen(null);
            }
        }

        @JvmStatic
        public final boolean isSearchTyping() {
            return INSTANCE.search.isTyping() || INSTANCE.messenger.isTyping() || INSTANCE.configsRenderer.isModalOpen();
        }

        @JvmStatic
        @NotNull
        public final GuiShareCloseState shareCloseState() {
            if (!WorldGuiCloseAnimation.isActive() || WorldGuiCloseAnimation.isFinished()) {
                return GuiShareCloseState.IDLE;
            }
            float[] rect = GuiShatterAnimation.beginRect();
            return new GuiShareCloseState(true, 1.0f - WorldGuiCloseAnimation.progress(), GuiShatterAnimation.local().lastProgress(), WorldGuiCloseAnimation.screenScale(), GuiShatterAnimation.seed(), rect[0], rect[1], rect[2], rect[3], rect[4], rect[5], rect[6]);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @Nullable
        public final GuiShareLocalSnapshot shareSnapshot() {
            GuiShareLocalSnapshot guiShareLocalSnapshot;
            float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
            try {
                guiShareLocalSnapshot = this.shareSnapshotScaled();
            }
            finally {
                Render2DCoordinateSpace.popUiZoom(previousZoom);
            }
            return guiShareLocalSnapshot;
        }

        private final GuiShareLocalSnapshot shareSnapshotScaled() {
            UI ui = INSTANCE;
            if (!this.isOpen()) {
                return null;
            }
            Category category = ui.targetCategory;
            ArrayList<String> enabled = new ArrayList<String>();
            Map badges = new LinkedHashMap();
            for (Module module : ui.filteredModules(category)) {
                String badge;
                if (module.isEnabled()) {
                    enabled.add(module.getName());
                }
                if (!(!StringsKt.isBlank((CharSequence)(badge = this.shareBadge(module))))) continue;
                badges.put(module.getName(), badge);
            }
            String avatarUrl = null;
            try {
                avatarUrl = DiscordAvatar.currentUrl();
            }
            catch (Throwable module) {
                // empty catch block
            }
            float panelX = this.panelX();
            float panelY = this.panelY();
            String catName = category != null ? category.name() : "";
            int n = ui.eventsSub;
            float f = ui.eventsRenderer.currentScroll();
            float f2 = ui.moduleList.currentScroll();
            float f3 = (Position.Companion.mouseX() - panelX) / 430.0f;
            float f4 = (Position.Companion.mouseY() - panelY) / 290.0f;
            String string = ui.settingsPopup.shareModuleName();
            float f5 = ui.settingsPopup.shareScroll();
            float f6 = (ui.settingsPopup.shareX() - panelX) / 430.0f;
            float f7 = (ui.settingsPopup.shareY() - panelY) / 290.0f;
            List<GuiSharePopupRow> list = GuiSharePopupRow.Companion.capture(ui.settingsPopup.shareModule(), ui.settingsPopup.widgets());
            GuiShareBindPopup guiShareBindPopup = GuiShareBindPopup.Companion.capture(ui.bindPopup, panelX, panelY, 430.0f, 290.0f);
            String string2 = ui.search.getText();
            boolean bl = ui.search.isTyping();
            List list2 = enabled;
            String string3 = this.profileRole();
            String string4 = avatarUrl;
            if (string4 == null) {
                string4 = "";
            }
            return new GuiShareLocalSnapshot(catName, n, f, f2, f3, f4, string, f5, f6, f7, list, guiShareBindPopup, string2, bl, list2, badges, string3, string4, GuiShareThemeState.Companion.capture(), ThemeManager.current().name(), ui.themesRenderer.currentScroll(), new GuiShareChatState(ui.messenger.isOpen(), ui.messenger.shareScroll(), ui.headerButtons.shareModal(), ui.messenger.shareSelectedCount()), category == Category.CONFIGS ? GuiShareConfigRow.Companion.capture() : CollectionsKt.emptyList(), ui.configsRenderer.currentScroll(), Position.Companion.screenHeight());
        }

        private final String shareBadge(Module module) {
            if (module == null) {
                return "";
            }
            if (module.getBindType() == Module.BindType.VOICE) {
                return module.hasVoiceBind() ? I18n.tr("ГОЛОС") : I18n.tr("ЗАПИШИ");
            }
            KeyBind bind = module.getBind();
            if (!bind.isBound()) {
                return "";
            }
            String label = bind.getDisplayName();
            return StringsKt.isBlank((CharSequence)label) ? "?" : label;
        }

        @JvmStatic
        public final void markCardStratum() {
            cardStratumMarked = true;
            cardCaptureStaged = true;
        }

        @JvmStatic
        public final boolean consumeCardStratumMark() {
            boolean marked = cardStratumMarked;
            cardStratumMarked = false;
            if (marked) {
                cardBlurCaptured = true;
            }
            return marked;
        }

        @JvmStatic
        public final void markPopupStratum(boolean wantsBlur, boolean staged) {
            popupStratumMarked = true;
            popupBlurWanted = wantsBlur;
            popupBlurStaged = staged;
            popupBlurCaptured = false;
        }

        @JvmStatic
        public final void markPopupLayerCapture() {
            popupLayerBlurWanted = true;
        }

        @JvmStatic
        public final boolean popupLayerCapturePending() {
            return popupLayerBlurWanted;
        }

        @JvmStatic
        public final boolean consumePopupStratumMark() {
            boolean marked = popupStratumMarked;
            popupStratumMarked = false;
            return marked;
        }

        @JvmStatic
        public final boolean consumePopupBlurCapture() {
            if (!popupBlurWanted) {
                return false;
            }
            popupBlurWanted = false;
            popupBlurCaptured = true;
            return true;
        }

        @JvmStatic
        public final boolean consumePopupLayerCapture() {
            if (!popupLayerBlurWanted) {
                return false;
            }
            popupLayerBlurWanted = false;
            popupBlurCaptured = true;
            return true;
        }

        @JvmStatic
        public final void dropPendingBlurs() {
            cardStratumMarked = false;
            panelSplitMarked = false;
            vanillaBlurRequested = false;
            popupStratumMarked = false;
            popupLayerBlurWanted = false;
            popupBlurWanted = false;
            popupBlurStaged = false;
            popupBlurCaptured = false;
            popupBlurPending = false;
            cardBlurPending = false;
            cardCaptureStaged = false;
            cardBlurCaptured = false;
        }

        @JvmStatic
        public final boolean consumePanelSplitMark() {
            boolean marked = panelSplitMarked;
            panelSplitMarked = false;
            return marked;
        }

        @JvmStatic
        public final void requestVanillaBlurAtSplit() {
            vanillaBlurRequested = true;
        }

        @JvmStatic
        public final boolean consumeVanillaBlurRequest() {
            boolean requested = vanillaBlurRequested;
            vanillaBlurRequested = false;
            return requested;
        }

        @JvmStatic
        public final void applyMainCompositeAtSplit() {
            if (!motionBlurPending) {
                return;
            }
            motionBlurPending = false;
            cardBlurPending = false;
            GuiMotionBlurRenderer.applyWithCopy(motionBlurOpacity, motionBlurRadius, motionBlurX, motionBlurY, motionBlurW, motionBlurH, motionBlurMask, 2, motionBlurSrcX, motionBlurSrcY, motionBlurSrcW, motionBlurSrcH, motionBlurScale, motionBlurOriginX, motionBlurOriginY);
        }

        @JvmStatic
        public final boolean motionBlurCapturePending() {
            return motionBlurPending;
        }

        @JvmStatic
        public final float motionBlurCaptureRadius() {
            return motionBlurRadius;
        }

        @JvmStatic
        public final boolean guiCaptureActive() {
            boolean worldClosing;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (!GuiCapture.isBound(INSTANCE)) {
                return false;
            }
            boolean bl = worldClosing = WorldGuiCloseAnimation.isActive() && !WorldGuiCloseAnimation.isFinished() && (minecraft.currentScreen == null || WorldGuiCloseAnimation.isReversing());
            boolean closingOverlay = INSTANCE.screenAnim.isClosing() && (WorldGuiCloseAnimation.isActive() ? worldClosing : !INSTANCE.screenAnim.isCloseFinished());
            return !(!this.isOpen() && !closingOverlay || !INSTANCE.screenAnim.isAnimating() && !worldClosing);
        }

        @JvmStatic
        public final float guiCaptureScale() {
            return guiCaptureScale;
        }

        @JvmStatic
        public final float guiCaptureBlurRadius() {
            return guiCaptureBlurMainPx;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private final void beginShatter() {
            if (GuiShatterAnimation.resume(this.rawCloseProgress())) {
                return;
            }
            float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
            try {
                InterfaceModule ifaceModule = InterfaceModule.Companion.getInstance();
                float glowPad = ifaceModule != null && ifaceModule.rectGlow.getValue() ? ifaceModule.rectGlowRadius.getFloat() : 0.0f;
                float pad = this.pixelSize(glowPad + 14.0f);
                float w = this.pixelSize(430.0f);
                float h = this.pixelSize(290.0f);
                float screenW = Position.Companion.screenWidth() * Render2DCoordinateSpace.designGuiScale();
                float screenH = Position.Companion.screenHeight() * Render2DCoordinateSpace.designGuiScale();
                float shatterW = INSTANCE.messenger.isVisible() ? w + this.pixelSize(MessengerPanel.Companion.shatterExtraWidth()) : w;
                GuiShatterAnimation.begin((screenW - w) * 0.5f + this.pixelSize(PanelDrag.x()), (screenH - h) * 0.5f + this.pixelSize(PanelDrag.y()), shatterW, h, pad, screenW, screenH);
                GuiShatterAnimation.rebase(this.rawCloseProgress());
            }
            finally {
                Render2DCoordinateSpace.popUiZoom(previousZoom);
            }
        }

        private final float pixelSize(float designSize) {
            return Render2DCoordinateSpace.pixelSize(designSize);
        }

        private final float pixelX(float designX) {
            return Render2DCoordinateSpace.pixelX(designX);
        }

        private final float pixelY(float designY) {
            return Render2DCoordinateSpace.pixelY(designY);
        }

        private final float rawCloseProgress() {
            return WorldGuiCloseAnimation.isActive() ? WorldGuiCloseAnimation.progress() : INSTANCE.screenAnim.closeProgress();
        }

        @JvmStatic
        public final float guiShatterProgress() {
            return GuiShatterAnimation.progress(this.rawCloseProgress());
        }

        @JvmStatic
        public final void flushMotionBlur() {
            cardStratumMarked = false;
            panelSplitMarked = false;
            vanillaBlurRequested = false;
            popupStratumMarked = false;
            popupLayerBlurWanted = false;
            popupBlurWanted = false;
            popupBlurStaged = false;
            cardCaptureStaged = false;
            boolean popupCaptured = popupBlurCaptured;
            popupBlurCaptured = false;
            boolean cardCaptured = cardBlurCaptured;
            cardBlurCaptured = false;
            if (motionBlurPending) {
                motionBlurPending = false;
                cardBlurPending = false;
                popupBlurPending = false;
                GuiMotionBlurRenderer.applyWithCopy(motionBlurOpacity, motionBlurRadius, motionBlurX, motionBlurY, motionBlurW, motionBlurH, motionBlurMask, 2, motionBlurSrcX, motionBlurSrcY, motionBlurSrcW, motionBlurSrcH, motionBlurScale, motionBlurOriginX, motionBlurOriginY);
                return;
            }
            if (popupBlurPending && popupCaptured) {
                popupBlurPending = false;
                cardBlurPending = false;
                GuiMotionBlurRenderer.applyWithCopy(1.0f, popupBlurRadius, popupBlurX, popupBlurY, popupBlurW, popupBlurH, popupBlurMask, popupBlurMaskCount, popupBlurX, popupBlurY, popupBlurW, popupBlurH, 1.0f, popupBlurOriginX, popupBlurOriginY, true);
                return;
            }
            popupBlurPending = false;
            if (cardBlurPending && cardCaptured) {
                cardBlurPending = false;
                GuiMotionBlurRenderer.applyWithCopy(motionBlurOpacity, motionBlurRadius, motionBlurX, motionBlurY, motionBlurW, motionBlurH, cardBlurMask, cardBlurMaskCount, motionBlurX, motionBlurY, motionBlurW, motionBlurH, 1.0f, motionBlurOriginX, motionBlurOriginY);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        public final void renderClosingPanelOverHud(@NotNull DrawContext g) {
            boolean closingOverlay;
            Intrinsics.checkNotNullParameter((Object)g, (String)"g");
            UI ui = INSTANCE;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            WorldGuiCloseAnimation.updateFrame();
            if (GuiCapture.isBound(ui) && WorldGuiCloseAnimation.isReversing() && WorldGuiCloseAnimation.isFinished()) {
                WorldGuiCloseAnimation.cancel();
                GuiShatterAnimation.cancel();
                if (!ui.screenAnim.isClosing()) {
                    ui.screenAnim.snapOpen();
                }
            }
            boolean ownsWorldAnimation = GuiCapture.isBound(ui);
            if (minecraft.currentScreen != null) {
                if (!Intrinsics.areEqual((Object)minecraft.currentScreen, (Object)ui)) {
                    if (ownsWorldAnimation && WorldGuiCloseAnimation.isActive()) {
                        WorldGuiCloseAnimation.cancel();
                    }
                    if (ui.screenAnim.isClosing()) {
                        ui.screenAnim.snapClosed();
                    }
                    pendingAfterClose = null;
                }
                return;
            }
            if (ownsWorldAnimation && WorldGuiCloseAnimation.isActive() && (minecraft.world == null || minecraft.options.hudHidden || WorldGuiCloseAnimation.isFinished() || WorldGuiCloseAnimation.surfaceChanged() || !GuiLayerBlurRenderer.available())) {
                WorldGuiCloseAnimation.cancel();
                if (ui.screenAnim.isClosing()) {
                    ui.screenAnim.snapClosed();
                    if (pendingAfterClose != null) {
                        Screen next = pendingAfterClose;
                        pendingAfterClose = null;
                        minecraft.setScreen(next);
                    }
                }
                return;
            }
            boolean worldClosing = ownsWorldAnimation && WorldGuiCloseAnimation.isActive();
            boolean bl = closingOverlay = ui.screenAnim.isClosing() && (worldClosing || !ui.screenAnim.isCloseFinished());
            if (!closingOverlay) {
                if (ui.screenAnim.isClosing() && pendingAfterClose != null) {
                    Screen next = pendingAfterClose;
                    pendingAfterClose = null;
                    ui.screenAnim.snapClosed();
                    minecraft.setScreen(next);
                }
                return;
            }
            float previousZoom = Render2DCoordinateSpace.pushUiZoom(UiScale.zoom());
            Render2D.beginFrame(g);
            if (worldClosing) {
                BlurFramebuffer.Companion.beginWorldScope();
            }
            try {
                ui.renderPanel(g);
            }
            finally {
                BlurFramebuffer.Companion.endWorldScope();
                Render2D.flush();
                GuiLayerBlurRenderer.markPanelEnd(g);
                Render2DCoordinateSpace.popUiZoom(previousZoom);
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

        private final String layoutNormalize(String s) {
            StringBuilder sb = new StringBuilder(s.length());
            int n = ((CharSequence)s).length();
            for (int i = 0; i < n; ++i) {
                char c = s.charAt(i);
                int idx = String.valueOf(UI.RU_LAYOUT).indexOf((char)c);
                sb.append(idx >= 0 ? UI.EN_LAYOUT.charAt(idx) : c);
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            return string;
        }

        private final String profileRole() {
            Role r = Profile.getRole();
            return r != null && r.name() != null ? r.name() : "";
        }

        private final String profileName() {
            String pn = Profile.getUsername();
            Intrinsics.checkNotNull((Object)pn);
            if (!StringsKt.isBlank((CharSequence)pn)) {
                return pn;
            }
            CharSequence charSequence;
            try {
                if (IMinecraft.mc.getSession() != null && !((charSequence = (CharSequence)IMinecraft.mc.getSession().getUsername()) == null || StringsKt.isBlank((CharSequence)charSequence))) {
                    String string = IMinecraft.mc.getSession().getUsername();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
                    return string;
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return "Player";
        }

        private final boolean ctrlHeld() {
            long handle = IMinecraft.mc.getWindow().getHandle();
            return GLFW.glfwGetKey((long)handle, (int)341) == 1 || GLFW.glfwGetKey((long)handle, (int)345) == 1;
        }

        private final boolean altHeld() {
            long handle = IMinecraft.mc.getWindow().getHandle();
            return GLFW.glfwGetKey((long)handle, (int)342) == 1 || GLFW.glfwGetKey((long)handle, (int)346) == 1;
        }

        private final boolean isPanelResetCombo(int key) {
            boolean alt = key == 342 || key == 346;
            boolean ctrl = key == 341 || key == 345;
            return alt && this.ctrlHeld() || ctrl && this.altHeld();
        }

        private final char iconChar(Category category) {
            return switch (WhenMappings.$EnumSwitchMapping$0[category.ordinal()]) {
                case 1 -> 'p';
                case 2 -> 'j';
                case 3 -> 'r';
                case 4 -> 'i';
                case 5 -> 'w';
                case 6 -> 'B';
                case 7 -> 'c';
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        private final boolean scaleHotkey(int key) {
            switch (key) {
                case 45: 
                case 333: {
                    UiScale.shift(-1);
                    return true;
                }
            }
            switch (key) {
                case 61: 
                case 334: {
                    UiScale.shift(1);
                    return true;
                }
            }
            return false;
        }

        private final int color(int red, int green, int blue, int alpha, float multiplier) {
            int fadedAlpha = RangesKt.coerceIn((int)MathKt.roundToInt((float)((float)alpha * multiplier)), (int)0, (int)255);
            return fadedAlpha <= 0 ? 0 : new Color(red, green, blue, fadedAlpha).getRGB();
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
                try {
                    nArray[Category.COSMETICS.ordinal()] = 7;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }
}

