/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.world.attribute.EnvironmentAttributeInterpolator
 *  net.minecraft.world.attribute.EnvironmentAttributes
 *  net.minecraft.world.biome.Biome.Precipitation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket
 *  net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket.Reason
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.block.enums.CameraSubmersionType
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.world.ClientWorld.Properties
 *  net.minecraft.client.render.GameRenderer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.world.attribute.EnvironmentAttributeInterpolator;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.render.GameRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.network.PacketEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Visuals.fireflies.FireflyField;
import rtx.kimiko.api.modules.impl.Visuals.rain.RainfallRenderer;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.skyshader.SkyShaderEditorScreen;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.post.ambiencefog.AmbienceFogRenderer;
import rtx.kimiko.utils.render.modules.post.groundreflect.GroundReflectRenderer;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyShaders;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"fullbright", "timechanger", "weather"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00f0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00b7\u00012\u00020\u0001:\u0002\u00b7\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u0003J\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0011\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\tH\u0007b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\r\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0016J\r\u0010\u0018\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0006J\r\u0010\u0019\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0019\u0010\u0016J\r\u0010\u001a\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001a\u0010\u0016J\r\u0010\u001b\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001b\u0010\u0016J\r\u0010\u001c\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001c\u0010\u0006J\r\u0010\u001d\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001d\u0010\u0006J\r\u0010\u001e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001e\u0010\u0006J\u000f\u0010\u001f\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u001f\u0010\u0016J\r\u0010!\u001a\u00020 \u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0014\u00a2\u0006\u0004\b#\u0010\u0016J\r\u0010$\u001a\u00020\u0014\u00a2\u0006\u0004\b$\u0010\u0016J\r\u0010%\u001a\u00020 \u00a2\u0006\u0004\b%\u0010\"J\r\u0010&\u001a\u00020\u0004\u00a2\u0006\u0004\b&\u0010\u0006J\r\u0010'\u001a\u00020 \u00a2\u0006\u0004\b'\u0010\"J\r\u0010(\u001a\u00020 \u00a2\u0006\u0004\b(\u0010\"J\r\u0010)\u001a\u00020 \u00a2\u0006\u0004\b)\u0010\"J\r\u0010*\u001a\u00020\u0014\u00a2\u0006\u0004\b*\u0010\u0016J\r\u0010,\u001a\u00020+\u00a2\u0006\u0004\b,\u0010-J!\u00102\u001a\u00020\u00072\b\u0010/\u001a\u0004\u0018\u00010.2\b\u00101\u001a\u0004\u0018\u000100\u00a2\u0006\u0004\b2\u00103J!\u00106\u001a\u0004\u0018\u000104H\u0007b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f85\u00a2\u0006\u0004\b6\u00107J5\u0010?\u001a\u00020\u00072\b\u00109\u001a\u0004\u0018\u0001082\b\u0010;\u001a\u0004\u0018\u00010:2\b\u0010<\u001a\u0004\u0018\u00010:2\b\u0010>\u001a\u0004\u0018\u00010=\u00a2\u0006\u0004\b?\u0010@J=\u0010B\u001a\u00020\u00072\b\u00109\u001a\u0004\u0018\u0001082\b\u0010;\u001a\u0004\u0018\u00010:2\b\u0010<\u001a\u0004\u0018\u00010:2\b\u0010>\u001a\u0004\u0018\u00010=2\u0006\u0010A\u001a\u00020\u0014\u00a2\u0006\u0004\bB\u0010CJ\u0015\u0010F\u001a\u00020\u00072\u0006\u0010E\u001a\u00020D\u00a2\u0006\u0004\bF\u0010GJ\u0015\u0010H\u001a\u00020\u00072\u0006\u0010E\u001a\u00020D\u00a2\u0006\u0004\bH\u0010GJ\u0017\u0010I\u001a\u00020\u00142\u0006\u0010/\u001a\u00020.H\u0002\u00a2\u0006\u0004\bI\u0010JJ\r\u0010K\u001a\u00020\u0004\u00a2\u0006\u0004\bK\u0010\u0006J\u001f\u0010L\u001a\u00020\u0007H\u0015b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010\u00a2\u0006\u0004\bL\u0010\u0003J\u001b\u0010O\u001a\u00020\u00072\u0006\u0010E\u001a\u00020MH\u0007b\u0002\bN\u00a2\u0006\u0004\bO\u0010PJ\u000f\u0010Q\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bQ\u0010\u0006J\u001f\u0010R\u001a\u00020\u00072\u0006\u0010/\u001a\u00020.2\u0006\u00101\u001a\u000200H\u0002\u00a2\u0006\u0004\bR\u00103J\u000f\u0010S\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bS\u0010\u0003J'\u0010W\u001a\u00020\u00072\u0006\u0010U\u001a\u00020TH\u0003b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8V\u00a2\u0006\u0004\bW\u0010XR\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010`\u001a\u00020_8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0014\u0010b\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010[R\u0014\u0010c\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010^R\u0014\u0010d\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010[R\u0014\u0010e\u001a\u00020_8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010aR\u0014\u0010f\u001a\u00020_8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010aR\u0014\u0010g\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010[R\u0014\u0010i\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0014\u0010k\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010^R\u0014\u0010m\u001a\u00020l8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0014\u0010p\u001a\u00020o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0014\u0010r\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010^R\u0014\u0010s\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010jR\u0014\u0010u\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0014\u0010w\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010vR\u0014\u0010y\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0014\u0010{\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010[R\u0014\u0010|\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b|\u0010jR\u0014\u0010}\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b}\u0010^R\u0014\u0010~\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b~\u0010vR\u0014\u0010\u007f\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u007f\u0010zR\u0016\u0010\u0080\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010zR\u0016\u0010\u0081\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010zR\u0016\u0010\u0082\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010zR\u0016\u0010\u0083\u0001\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010[R\u0016\u0010\u0084\u0001\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010jR\u0016\u0010\u0085\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010zR\u0016\u0010\u0086\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010zR\u0016\u0010\u0087\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010zR\u0016\u0010\u0088\u0001\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0088\u0001\u0010[R\u0016\u0010\u0089\u0001\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010jR\u0016\u0010\u008a\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008a\u0001\u0010zR\u0016\u0010\u008b\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008b\u0001\u0010zR\u0016\u0010\u008c\u0001\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008c\u0001\u0010^R\u0016\u0010\u008d\u0001\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008d\u0001\u0010[R\u0016\u0010\u008e\u0001\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008e\u0001\u0010jR\u0016\u0010\u008f\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008f\u0001\u0010zR\u0016\u0010\u0090\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0090\u0001\u0010zR\u0016\u0010\u0091\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0091\u0001\u0010zR\u0016\u0010\u0092\u0001\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0092\u0001\u0010jR\u0016\u0010\u0093\u0001\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0093\u0001\u0010[R\u0016\u0010\u0094\u0001\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0094\u0001\u0010jR\u0016\u0010\u0095\u0001\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0095\u0001\u0010^R\u0016\u0010\u0096\u0001\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0096\u0001\u0010vR\u0016\u0010\u0097\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010zR\u0016\u0010\u0098\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0098\u0001\u0010zR\u0016\u0010\u0099\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0099\u0001\u0010zR\u0016\u0010\u009a\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u009a\u0001\u0010zR\u0016\u0010\u009b\u0001\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u009b\u0001\u0010zR\u0016\u0010\u009c\u0001\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u009c\u0001\u0010jR\u0018\u0010\u009e\u0001\u001a\u00030\u009d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0018\u0010\u00a1\u0001\u001a\u00030\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u00a2\u0001R\u0019\u0010\u00a3\u0001\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u00a4\u0001R\u0019\u0010\u00a5\u0001\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u00a4\u0001R\u0017\u0010\u00a6\u0001\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u00a7\u0001R\u0018\u0010\u00a9\u0001\u001a\u00030\u00a8\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a9\u0001\u0010\u00aa\u0001R\u0017\u0010«\u0001\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00a7\u0001R\u0017\u0010\u00ac\u0001\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u00a7\u0001R\u0018\u0010\u00ad\u0001\u001a\u00030\u00a8\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u00aa\u0001R\u0019\u0010\u00ae\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00af\u0001R\u0019\u0010\u00b0\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u00af\u0001R\u0019\u0010\u00b1\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00b2\u0001R\u0019\u0010\u00b3\u0001\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00b2\u0001R\u001b\u0010\u00b4\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00b5\u0001R\u0019\u0010\u00b6\u0001\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u00a4\u0001\u00ca\u0001\u001f\b\u00b8\u0001\u0012\u001a\b\u000e\u0012\u0016\b\fJ\u0005\b\b(\u00b9\u0001J\u0005\b\b(\u00ba\u0001J\u0004\b\b(c\u00a8\u0006»\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Ambience;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "userSkyVisible", "()Z", "", "refreshUserShaderOptions", "", "userSkyShaderName", "()Ljava/lang/String;", "name", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "applyUserShader", "(Ljava/lang/String;)V", "openShaderEditor", "", "getBrightnessValue", "()F", "getSaturationFactor", "isWindActive", "getWindGrassStrength", "getWindLeavesStrength", "getWindSpeed", "hasWindGusts", "isCustomSkyActive", "isCustomFogActive", "fadeOutSeconds", "", "fogColorRGB", "()I", "fogDistanceFactor", "fogSkyEndFactor", "skyTypeIndex", "skyUsesClientColor", "skyColorRGB", "skyColor2RGB", "skyGradientMode", "skyBrightness", "", "getInternalTime", "()J", "Lnet/minecraft/ClientWorld;", "level", "Lnet/minecraft/ClientWorld$Properties;", "levelData", "syncWeather", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/ClientWorld$Properties;)V", "Lnet/minecraft/Biome$Precipitation;", "MAX", "getForcedPrecipitation", "()Lnet/minecraft/Biome$Precipitation;", "Lnet/minecraft/Framebuffer;", "renderTarget", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "Lnet/minecraft/Camera;", "camera", "onAfterWorldFog", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Camera;)V", "partialTick", "onAfterWorldWetWorld", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Camera;F)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "renderRainfall", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "renderFireflies", "fireflyPresence", "(Lnet/minecraft/ClientWorld;)F", "shouldHideVanillaWeather", "onDisable", "Lrtx/kimiko/api/events/impl/network/PacketEvent;", "Lrtx/kimiko/api/events/EventHandler;", "onPacket", "(Lrtx/kimiko/api/events/impl/network/PacketEvent;)V", "shouldForcePrecipitation", "restoreWeather", "clearWeatherSnapshot", "Lnet/minecraft/GameStateChangeS2CPacket;", "packet", "STD", "updateCachedWeather", "(Lnet/minecraft/GameStateChangeS2CPacket;)V", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "timeSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "customTime", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "weatherSeparator", "weather", "lightSeparator", "saturation", "brightness", "skySeparator", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "customSky", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "skyType", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "userShader", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "shaderEditor", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "skyColorMode", "skySecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "skyColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "skyColor2", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "skyBrightnessLevel", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "fogSeparator", "customFog", "fogColorMode", "fogColor", "fogDensity", "fogBase", "fogPressed", "fogSkyHaze", "rainFxSeparator", "customRain", "rainRipples", "rainDropDensity", "rainDropSpeed", "wetWorldSeparator", "wetWorld", "wetReflection", "wetAmount", "wetQuality", "windSeparator", "wind", "windGrassStrength", "windLeavesStrength", "windSpeed", "windGusts", "firefliesSeparator", "fireflies", "fireflyColorMode", "fireflyColor", "fireflyCount", "fireflyRadius", "fireflySize", "fireflyGlow", "fireflySpeed", "fireflyNightOnly", "Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer;", "rainfall", "Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer;", "Lrtx/kimiko/api/modules/impl/Visuals/fireflies/FireflyField;", "fireflyField", "Lrtx/kimiko/api/modules/impl/Visuals/fireflies/FireflyField;", "lastFireflyNanos", "J", "lastRainfallNanos", "fogInvViewProj", "Lorg/joml/Matrix4f;", "", "fogUniform", "[F", "wetViewProj", "wetInvViewProj", "wetUniform", "weatherOverrideActive", "Z", "cachedServerRaining", "cachedServerRainLevel", "F", "cachedServerThunderLevel", "weatherSnapshotLevel", "Lnet/minecraft/ClientWorld;", "lastShaderScanMs", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "fullbright", "timechanger", "rtx.kimiko:kimiko"})
public final class Ambience
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting timeSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Время"));
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final NumberSetting customTime;
    @NotNull
    private final SeparatorSetting weatherSeparator;
    @NotNull
    private final ModeSetting weather;
    @NotNull
    private final SeparatorSetting lightSeparator;
    @NotNull
    private final NumberSetting saturation;
    @NotNull
    private final NumberSetting brightness;
    @NotNull
    private final SeparatorSetting skySeparator;
    @NotNull
    private final BooleanSetting customSky;
    @NotNull
    private final ModeSetting skyType;
    @NotNull
    private final SelectSetting userShader;
    @NotNull
    private final ButtonSetting shaderEditor;
    @NotNull
    private final ModeSetting skyColorMode;
    @NotNull
    private final BooleanSetting skySecondColor;
    @NotNull
    private final ColorSetting skyColor;
    @NotNull
    private final ColorSetting skyColor2;
    @NotNull
    private final SliderSetting skyBrightnessLevel;
    @NotNull
    private final SeparatorSetting fogSeparator;
    @NotNull
    private final BooleanSetting customFog;
    @NotNull
    private final ModeSetting fogColorMode;
    @NotNull
    private final ColorSetting fogColor;
    @NotNull
    private final SliderSetting fogDensity;
    @NotNull
    private final SliderSetting fogBase;
    @NotNull
    private final SliderSetting fogPressed;
    @NotNull
    private final SliderSetting fogSkyHaze;
    @NotNull
    private final SeparatorSetting rainFxSeparator;
    @NotNull
    private final BooleanSetting customRain;
    @NotNull
    private final SliderSetting rainRipples;
    @NotNull
    private final SliderSetting rainDropDensity;
    @NotNull
    private final SliderSetting rainDropSpeed;
    @NotNull
    private final SeparatorSetting wetWorldSeparator;
    @NotNull
    private final BooleanSetting wetWorld;
    @NotNull
    private final SliderSetting wetReflection;
    @NotNull
    private final SliderSetting wetAmount;
    @NotNull
    private final ModeSetting wetQuality;
    @NotNull
    private final SeparatorSetting windSeparator;
    @NotNull
    private final BooleanSetting wind;
    @NotNull
    private final SliderSetting windGrassStrength;
    @NotNull
    private final SliderSetting windLeavesStrength;
    @NotNull
    private final SliderSetting windSpeed;
    @NotNull
    private final BooleanSetting windGusts;
    @NotNull
    private final SeparatorSetting firefliesSeparator;
    @NotNull
    private final BooleanSetting fireflies;
    @NotNull
    private final ModeSetting fireflyColorMode;
    @NotNull
    private final ColorSetting fireflyColor;
    @NotNull
    private final SliderSetting fireflyCount;
    @NotNull
    private final SliderSetting fireflyRadius;
    @NotNull
    private final SliderSetting fireflySize;
    @NotNull
    private final SliderSetting fireflyGlow;
    @NotNull
    private final SliderSetting fireflySpeed;
    @NotNull
    private final BooleanSetting fireflyNightOnly;
    @NotNull
    private final RainfallRenderer rainfall;
    @NotNull
    private final FireflyField fireflyField;
    private long lastFireflyNanos;
    private long lastRainfallNanos;
    @NotNull
    private final Matrix4f fogInvViewProj;
    @NotNull
    private final float[] fogUniform;
    @NotNull
    private final Matrix4f wetViewProj;
    @NotNull
    private final Matrix4f wetInvViewProj;
    @NotNull
    private final float[] wetUniform;
    private boolean weatherOverrideActive;
    private volatile boolean cachedServerRaining;
    private volatile float cachedServerRainLevel;
    private volatile float cachedServerThunderLevel;
    @Nullable
    private ClientWorld weatherSnapshotLevel;
    private long lastShaderScanMs;
    private static final float FOG_LAYERS = 1.0f;
    @NotNull
    private static final String WET_QUALITY_LOW = "Низкое";
    @NotNull
    private static final String WET_QUALITY_MEDIUM = "Среднее";
    @NotNull
    private static final String WET_QUALITY_HIGH = "Высокое";
    private static final float WET_GLOSS = 0.45f;
    private static final float WET_HIT_THICKNESS = 0.98f;
    private static final float WET_MAX_DISTANCE = 24.0f;
    private static final float FIREFLY_BASE_SIZE = 0.16f;
    private static final long FIREFLY_DAWN_START = 22500L;
    private static final long FIREFLY_DAWN_END = 23800L;
    private static final long FIREFLY_DUSK_START = 11800L;
    private static final long FIREFLY_DUSK_END = 13200L;
    @NotNull
    private static final String NO_SHADERS = "Нет шейдеров";
    private static final long SHADER_RESCAN_MS = 1200L;

    public Ambience() {
        super("Ambience", "Изменяет время, погоду и атмосферу мира.", Category.VISUALS);
        String[] stringArray = new String[]{"День", "Полдень", "Ночь", "Полночь", "Свой"};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Режим времени в мире.", "Ночь", stringArray));
        this.customTime = (NumberSetting)this.register((Setting)new NumberSetting("Время", "Своё время суток.", 1000.0, 0.0, 24000.0, 100.0).visibleWhen(() -> Ambience.customTime$lambda$0(this)));
        this.weatherSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Погода"));
        stringArray = new String[]{"Ясно", "Дождь", "Гроза", "Снег"};
        this.weather = (ModeSetting)this.register((Setting)new ModeSetting("Погода", "Подмена погоды на клиенте.", "Ясно", stringArray));
        this.lightSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Освещение"));
        this.saturation = (NumberSetting)this.register((Setting)new NumberSetting("Насыщенность", "Смещение множителя насыщенности мира.", 0.6, -1.0, 1.0, 0.05));
        this.brightness = (NumberSetting)this.register((Setting)new NumberSetting("Яркость", "Смещение яркости мира.", 0.15, -1.0, 1.0, 0.05));
        this.skySeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Небо"));
        this.customSky = (BooleanSetting)this.register((Setting)new BooleanSetting("Своё небо", "Полная замена неба кастомным шейдером.", false));
        stringArray = new String[]{"Северное сияние", "Чёрная дыра", "Звездопад", "Кастомный"};
        this.skyType = (ModeSetting)this.register((Setting)new ModeSetting("Тип неба", "Какое небо рисовать.", "Северное сияние", stringArray).visibleWhen(() -> Ambience.skyType$lambda$0(this)));
        this.userShader = (SelectSetting)this.register((Setting)new SelectSetting("Шейдер", "Какой свой шейдер рисовать на небе.").visible(() -> Ambience.userShader$lambda$0(this)));
        this.shaderEditor = (ButtonSetting)this.register((Setting)new ButtonSetting("Редактор шейдеров", "Написать свой GLSL-шейдер неба.").label("Открыть").visible(() -> Ambience.shaderEditor$lambda$0(this)));
        stringArray = new String[]{"Тема", "Свой", "Радуга"};
        this.skyColorMode = (ModeSetting)this.register((Setting)new ModeSetting("Цвет неба", "Откуда брать цвет неба.", "Тема", stringArray).visibleWhen(() -> Ambience.skyColorMode$lambda$0(this)));
        this.skySecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет неба", "Градиент из двух цветов.", false).visibleWhen(() -> Ambience.skySecondColor$lambda$0(this)));
        this.skyColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет неба 1", "Основной цвет неба.", new Color(90, 255, 150, 255)).visibleWhen(() -> Ambience.skyColor$lambda$0(this)));
        this.skyColor2 = (ColorSetting)this.register((Setting)new ColorSetting("Цвет неба 2", "Второй цвет неба.", new Color(120, 90, 255, 255)).visibleWhen(() -> Ambience.skyColor2$lambda$0(this)));
        this.skyBrightnessLevel = (SliderSetting)this.register((Setting)new SliderSetting("Яркость неба", "Насколько ярко светится кастомное небо.").range(10.0f, 200.0f).increment(1.0f).setValue(100.0f).visible(() -> Ambience.skyBrightnessLevel$lambda$0(this)));
        this.fogSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Туман"));
        this.customFog = (BooleanSetting)this.register((Setting)new BooleanSetting("Кастомный туман", "Свой объёмный туман со слоями, не зависящий от освещения мира.", true));
        stringArray = new String[]{"Тема", "Свой"};
        this.fogColorMode = (ModeSetting)this.register((Setting)new ModeSetting("Цвет тумана", "Откуда брать цвет тумана.", "Свой", stringArray).visibleWhen(() -> Ambience.fogColorMode$lambda$0(this)));
        this.fogColor = (ColorSetting)this.register((Setting)new ColorSetting("Свой цвет", "Цвет кастомного тумана.", new Color(150, 120, 255, 255)).visibleWhen(() -> Ambience.fogColor$lambda$0(this)));
        this.fogDensity = (SliderSetting)this.register((Setting)new SliderSetting("Плотность", "Общая густота тумана.").range(0.0f, 100.0f).increment(1.0f).setValue(40.0f).visible(() -> Ambience.fogDensity$lambda$0(this)));
        this.fogBase = (SliderSetting)this.register((Setting)new SliderSetting("Уровень слоя", "Высота, от которой туман начинает редеть кверху.").range(-64.0f, 192.0f).increment(1.0f).setValue(64.0f).visible(() -> Ambience.fogBase$lambda$0(this)));
        this.fogPressed = (SliderSetting)this.register((Setting)new SliderSetting("Прижатость", "Насколько туман прижат к уровню слоя: 0 — равномерный везде.").range(0.0f, 100.0f).increment(1.0f).setValue(35.0f).visible(() -> Ambience.fogPressed$lambda$0(this)));
        this.fogSkyHaze = (SliderSetting)this.register((Setting)new SliderSetting("Дымка неба", "Насколько туман затягивает горизонт и небо.").range(0.0f, 100.0f).increment(1.0f).setValue(50.0f).visible(() -> Ambience.fogSkyHaze$lambda$0(this)));
        this.rainFxSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Дождь"));
        this.customRain = (BooleanSetting)this.register((Setting)new BooleanSetting("Кастомный дождь", "Свой дождь с каплями и рябью вместо ванильного.", true));
        this.rainRipples = (SliderSetting)this.register((Setting)new SliderSetting("Яркость ряби", "Насколько ярко светятся круги и брызги от капель.").range(0.0f, 100.0f).increment(1.0f).setValue(65.0f).visible(() -> Ambience.rainRipples$lambda$0(this)));
        this.rainDropDensity = (SliderSetting)this.register((Setting)new SliderSetting("Плотность капель", "Сколько капель летит вокруг игрока.").range(10.0f, 100.0f).increment(1.0f).setValue(50.0f).visible(() -> Ambience.rainDropDensity$lambda$0(this)));
        this.rainDropSpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость капель", "Скорость падения капель.").range(50.0f, 150.0f).increment(1.0f).setValue(100.0f).visible(() -> Ambience.rainDropSpeed$lambda$0(this)));
        this.wetWorldSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Мокрый мир"));
        this.wetWorld = (BooleanSetting)this.register((Setting)new BooleanSetting("Мокрый мир", "Отражения и мокрый блеск на горизонтальных поверхностях.", false));
        this.wetReflection = (SliderSetting)this.register((Setting)new SliderSetting("Отражение", "Насколько ярко поверхность отражает мир.").range(0.0f, 100.0f).increment(5.0f).setValue(70.0f).visible(() -> Ambience.wetReflection$lambda$0(this)));
        this.wetAmount = (SliderSetting)this.register((Setting)new SliderSetting("Влажность", "Насколько сильно поверхность выглядит промокшей.").range(0.0f, 100.0f).increment(5.0f).setValue(60.0f).visible(() -> Ambience.wetAmount$lambda$0(this)));
        stringArray = new String[]{WET_QUALITY_LOW, WET_QUALITY_MEDIUM, WET_QUALITY_HIGH};
        this.wetQuality = (ModeSetting)this.register((Setting)new ModeSetting("Качество", "Точность трассировки отражения.", WET_QUALITY_MEDIUM, stringArray).visibleWhen(() -> Ambience.wetQuality$lambda$0(this)));
        this.windSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Ветер"));
        this.wind = (BooleanSetting)this.register((Setting)new BooleanSetting("Покачивание растений", "Плавно качает траву и листву ветром, как в шейдерах.", true));
        this.windGrassStrength = (SliderSetting)this.register((Setting)new SliderSetting("Сила травы", "Амплитуда покачивания травы и растений.").range(0.0f, 100.0f).increment(1.0f).setValue(60.0f).visible(() -> Ambience.windGrassStrength$lambda$0(this)));
        this.windLeavesStrength = (SliderSetting)this.register((Setting)new SliderSetting("Сила листвы", "Амплитуда покачивания листвы и лиан.").range(0.0f, 100.0f).increment(1.0f).setValue(45.0f).visible(() -> Ambience.windLeavesStrength$lambda$0(this)));
        this.windSpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость ветра", "Скорость покачивания растительности.").range(0.0f, 100.0f).increment(1.0f).setValue(50.0f).visible(() -> Ambience.windSpeed$lambda$0(this)));
        this.windGusts = (BooleanSetting)this.register((Setting)new BooleanSetting("Порывы ветра", "Волны порывов периодически усиливают покачивание.", true).visibleWhen(() -> Ambience.windGusts$lambda$0(this)));
        this.firefliesSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Светлячки"));
        this.fireflies = (BooleanSetting)this.register((Setting)new BooleanSetting("Светлячки у деревьев", "Светящиеся светлячки летают в кронах деревьев вокруг игрока.", true));
        stringArray = new String[]{"Тема", "Свой"};
        this.fireflyColorMode = (ModeSetting)this.register((Setting)new ModeSetting("Цвет светлячков", "Откуда брать цвет свечения.", "Тема", stringArray).visibleWhen(() -> Ambience.fireflyColorMode$lambda$0(this)));
        this.fireflyColor = (ColorSetting)this.register((Setting)new ColorSetting("Свой цвет свечения", "Цвет свечения светлячков.", new Color(255, 214, 90, 255)).visibleWhen(() -> Ambience.fireflyColor$lambda$0(this)));
        this.fireflyCount = (SliderSetting)this.register((Setting)new SliderSetting("Количество", "Сколько светлячков летает вокруг одновременно.").range(10.0f, 300.0f).increment(5.0f).setValue(90.0f).visible(() -> Ambience.fireflyCount$lambda$0(this)));
        this.fireflyRadius = (SliderSetting)this.register((Setting)new SliderSetting("Радиус", "На каком расстоянии от игрока появляются светлячки.").range(8.0f, 48.0f).increment(1.0f).setValue(28.0f).visible(() -> Ambience.fireflyRadius$lambda$0(this)));
        this.fireflySize = (SliderSetting)this.register((Setting)new SliderSetting("Размер", "Размер огонька светлячка.").range(30.0f, 200.0f).increment(5.0f).setValue(100.0f).visible(() -> Ambience.fireflySize$lambda$0(this)));
        this.fireflyGlow = (SliderSetting)this.register((Setting)new SliderSetting("Яркость свечения", "Насколько сильно светлячки светятся и подсвечивают вокруг себя.").range(10.0f, 200.0f).increment(5.0f).setValue(100.0f).visible(() -> Ambience.fireflyGlow$lambda$0(this)));
        this.fireflySpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость полёта", "Скорость движения и мерцания светлячков.").range(25.0f, 200.0f).increment(5.0f).setValue(100.0f).visible(() -> Ambience.fireflySpeed$lambda$0(this)));
        this.fireflyNightOnly = (BooleanSetting)this.register((Setting)new BooleanSetting("Только ночью", "Светлячки появляются только в тёмное время суток и прячутся в дождь.", true).visibleWhen(() -> Ambience.fireflyNightOnly$lambda$0(this)));
        this.rainfall = new RainfallRenderer();
        this.fireflyField = new FireflyField();
        this.fogInvViewProj = new Matrix4f();
        this.fogUniform = new float[32];
        this.wetViewProj = new Matrix4f();
        this.wetInvViewProj = new Matrix4f();
        this.wetUniform = new float[52];
        this.shaderEditor.onClick(() -> Ambience._init_$lambda$0(this));
        this.refreshUserShaderOptions();
    }

    private final boolean userSkyVisible() {
        if (!this.customSky.getValue() || !this.skyType.is("Кастомный")) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastShaderScanMs >= 1200L) {
            this.refreshUserShaderOptions();
        }
        return true;
    }

    public final void refreshUserShaderOptions() {
        this.lastShaderScanMs = System.currentTimeMillis();
        List<String> names = UserSkyShaders.list();
        if (names.isEmpty()) {
            this.userShader.options(List.of(NO_SHADERS));
        } else {
            this.userShader.options(names);
        }
    }

    @NotNull
    public final String userSkyShaderName() {
        String selected = this.userShader.getSelected();
        CharSequence charSequence = selected;
        return charSequence == null || charSequence.length() == 0 || Intrinsics.areEqual((Object)selected, (Object)NO_SHADERS) ? "" : selected;
    }

    @Protect(value=Level.CROWN)
    public final void applyUserShader(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        this.refreshUserShaderOptions();
        this.customSky.setValue(true);
        this.skyType.setSelected("Кастомный");
        this.userShader.setSelected(name);
        if (!this.isEnabled()) {
            this.enable();
        }
    }

    private final void openShaderEditor() {
        SkyShaderEditorScreen editor = new SkyShaderEditorScreen(UI.INSTANCE);
        if (Intrinsics.areEqual((Object)this.mc.currentScreen, (Object)UI.INSTANCE)) {
            UI.Companion.closeInto(editor);
        } else {
            this.mc.setScreen((Screen)editor);
        }
    }

    public final float getBrightnessValue() {
        return this.brightness.getFloat();
    }

    public final float getSaturationFactor() {
        return Math.clamp(1.0f + this.saturation.getFloat(), 0.0f, 2.0f);
    }

    public final boolean isWindActive() {
        return this.isEnabled() && this.wind.getValue() && (this.windGrassStrength.getFloat() > 0.0f || this.windLeavesStrength.getFloat() > 0.0f);
    }

    public final float getWindGrassStrength() {
        return this.windGrassStrength.getFloat() / 100.0f;
    }

    public final float getWindLeavesStrength() {
        return this.windLeavesStrength.getFloat() / 100.0f;
    }

    public final float getWindSpeed() {
        return this.windSpeed.getFloat() / 100.0f;
    }

    public final boolean hasWindGusts() {
        return this.windGusts.getValue();
    }

    public final boolean isCustomSkyActive() {
        return this.isEnabled() && this.customSky.getValue();
    }

    public final boolean isCustomFogActive() {
        return this.isVisuallyActive() && this.customFog.getValue();
    }

    @Override
    public float fadeOutSeconds() {
        return 1.0f;
    }

    public final int fogColorRGB() {
        if (this.fogColorMode.is("Тема")) {
            try {
                InterfaceModule iface = InterfaceModule.Companion.getInstance();
                if (iface != null) {
                    int primary = iface.clientPrimaryColorOpaque();
                    if (!iface.usesSecondClientColor()) {
                        return primary;
                    }
                    return ColorEngine.lerpColor(primary, iface.clientSecondaryColorOpaque(), 0.5f);
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        return this.fogColor.getColorOpaque();
    }

    public final float fogDistanceFactor() {
        return 512.0f;
    }

    public final float fogSkyEndFactor() {
        float t = this.fogSkyHaze.getFloat() / 100.0f;
        return 1.6f + -1.25f * t;
    }

    public final int skyTypeIndex() {
        if (this.skyType.is("Чёрная дыра")) {
            return 1;
        }
        if (this.skyType.is("Звездопад")) {
            return 2;
        }
        if (this.skyType.is("Кастомный")) {
            return 3;
        }
        return 0;
    }

    public final boolean skyUsesClientColor() {
        return this.skyColorMode.is("Тема");
    }

    public final int skyColorRGB() {
        if (this.skyColorMode.is("Свой")) {
            return this.skyColor.getColorOpaque();
        }
        try {
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                return iface.clientPrimaryColorOpaque();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return -10813546;
    }

    public final int skyColor2RGB() {
        if (this.skyColorMode.is("Свой")) {
            return this.skyColor2.getColorOpaque();
        }
        try {
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                return iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : iface.clientPrimaryColorOpaque();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return -8889601;
    }

    public final int skyGradientMode() {
        if (this.skyColorMode.is("Радуга")) {
            return 2;
        }
        if (!this.skySecondColor.getValue()) {
            return 0;
        }
        if (this.skyColorMode.is("Тема")) {
            try {
                InterfaceModule iface = InterfaceModule.Companion.getInstance();
                if (iface != null && !iface.usesSecondClientColor()) {
                    return 0;
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        return 1;
    }

    public final float skyBrightness() {
        return this.skyBrightnessLevel.getFloat() / 100.0f;
    }

    public final long getInternalTime() {
        if (this.mode.is("День")) {
            return 1000L;
        }
        if (this.mode.is("Полдень")) {
            return 6000L;
        }
        if (this.mode.is("Ночь")) {
            return 13000L;
        }
        if (this.mode.is("Полночь")) {
            return 18000L;
        }
        return (long)this.customTime.getValue();
    }

    public final void syncWeather(@Nullable ClientWorld level, @Nullable ClientWorld.Properties levelData) {
        if (level == null || levelData == null) {
            this.clearWeatherSnapshot();
            return;
        }
        if (!Intrinsics.areEqual((Object)this.weatherSnapshotLevel, (Object)level)) {
            this.clearWeatherSnapshot();
        }
        if (!this.weatherOverrideActive) {
            this.cachedServerRaining = levelData.isRaining();
            this.cachedServerRainLevel = level.getRainGradient(1.0f);
            this.cachedServerThunderLevel = level.getThunderGradient(1.0f);
            this.weatherOverrideActive = true;
            this.weatherSnapshotLevel = level;
        }
        boolean raining = this.shouldForcePrecipitation();
        levelData.setRaining(raining);
        level.setRainGradient(raining ? 1.0f : 0.0f);
        level.setThunderGradient(this.weather.is("Гроза") ? 1.0f : 0.0f);
    }

    @Protect(value=Level.MAX)
    @Nullable
    public final Biome.Precipitation getForcedPrecipitation() {
        if (!this.isEnabled()) {
            return null;
        }
        if (this.weather.is("Снег")) {
            return Biome.Precipitation.SNOW;
        }
        if (this.weather.is("Дождь") || this.weather.is("Гроза")) {
            return Biome.Precipitation.RAIN;
        }
        return Biome.Precipitation.NONE;
    }

    public final void onAfterWorldFog(@Nullable Framebuffer renderTarget, @Nullable Matrix4f positionMatrix, @Nullable Matrix4f projectionMatrix, @Nullable Camera camera) {
        if (renderTarget == null || positionMatrix == null || projectionMatrix == null || camera == null) {
            return;
        }
        if (!this.isVisuallyActive() || !this.customFog.getValue() || this.fogDensity.getFloat() <= 0.5f) {
            return;
        }
        if (camera.getSubmersionType() != CameraSubmersionType.NONE) {
            return;
        }
        if (renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
            return;
        }
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        int color = this.fogColorRGB();
        float d = this.fogDensity.getFloat() / 100.0f * this.visualAlpha();
        float[] data = this.fogUniform;
        data[0] = d * d * 0.05f + d * 0.002f;
        data[1] = (float)(System.currentTimeMillis() % 600000L) / 1000.0f;
        data[2] = this.fogPressed.getFloat() / 100.0f * 0.09f;
        data[3] = this.fogBase.getFloat();
        data[4] = 1.0f;
        data[5] = this.fogSkyHaze.getFloat() / 100.0f;
        int viewDist = (this.mc.options != null && this.mc.options.getViewDistance() != null) ? this.mc.options.getViewDistance().getValue() : 8;
        data[6] = Math.max(128.0f, (float)(viewDist + 1) * 16.0f * 1.25f);
        data[7] = 0.0f;
        data[8] = (float)cam.x;
        data[9] = (float)cam.y;
        data[10] = (float)cam.z;
        data[11] = 0.0f;
        data[12] = (float)(color >> 16 & 0xFF) / 255.0f;
        data[13] = (float)(color >> 8 & 0xFF) / 255.0f;
        data[14] = (float)(color & 0xFF) / 255.0f;
        data[15] = 1.0f;
        this.fogInvViewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix).invert();
        this.fogInvViewProj.get(data, 16);
        AmbienceFogRenderer.apply(renderTarget, data);
    }

    public final void onAfterWorldWetWorld(@Nullable Framebuffer renderTarget, @Nullable Matrix4f positionMatrix, @Nullable Matrix4f projectionMatrix, @Nullable Camera camera, float partialTick) {
        if (renderTarget == null || positionMatrix == null || projectionMatrix == null || camera == null) {
            return;
        }
        if (!this.isVisuallyActive() || !this.wetWorld.getValue() || this.wetReflection.getFloat() <= 0.5f) {
            return;
        }
        if (renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
            return;
        }
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        EnvironmentAttributeInterpolator environmentAttributeInterpolator2 = camera.getEnvironmentAttributeInterpolator();
        Intrinsics.checkNotNullExpressionValue((Object)environmentAttributeInterpolator2, (String)"attributeProbe(...)");
        EnvironmentAttributeInterpolator probe = environmentAttributeInterpolator2;
        Object object = probe.get(EnvironmentAttributes.SKY_COLOR_VISUAL, partialTick);
        Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getValue(...)");
        int skyColor = ((Number)object).intValue();
        float sunAngle = ((Number)probe.get(EnvironmentAttributes.SUN_ANGLE_VISUAL, partialTick)).floatValue() * ((float)Math.PI / 180);
        boolean hasSkyLight = level.getDimension().hasSkyLight();
        float[] data = this.wetUniform;
        data[0] = (float)cam.x;
        data[1] = (float)cam.y;
        data[2] = (float)cam.z;
        data[3] = (float)(System.currentTimeMillis() % 3600000L) / 1000.0f;
        data[4] = (float)(skyColor >> 16 & 0xFF) / 255.0f;
        data[5] = (float)(skyColor >> 8 & 0xFF) / 255.0f;
        data[6] = (float)(skyColor & 0xFF) / 255.0f;
        data[7] = this.wetReflection.getFloat() / 100.0f;
        data[8] = -((float)Math.sin(sunAngle));
        data[9] = (float)Math.cos(sunAngle);
        data[10] = 0.0f;
        data[11] = this.wetAmount.getFloat() / 100.0f;
        data[12] = 0.0f;
        data[13] = hasSkyLight ? 0.45f : 0.0f;
        String string = this.wetQuality.getValue();
        data[14] = Intrinsics.areEqual((Object)string, (Object)WET_QUALITY_LOW) ? 14.0f : (Intrinsics.areEqual((Object)string, (Object)WET_QUALITY_HIGH) ? 40.0f : 24.0f);
        data[15] = 0.98f;
        data[16] = 24.0f;
        data[17] = 1.0f;
        data[18] = this.visualAlpha();
        data[19] = 0.0f;
        this.wetViewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix);
        this.wetInvViewProj.set((Matrix4fc)this.wetViewProj).invert();
        this.wetInvViewProj.get(data, 20);
        this.wetViewProj.get(data, 36);
        GroundReflectRenderer.apply(renderTarget, data);
    }

    public final void renderRainfall(@NotNull WorldRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.isEnabled() || !this.customRain.getValue()) {
            this.lastRainfallNanos = 0L;
            this.rainfall.clear();
            return;
        }
        ClientWorld level = this.mc.world;
        Camera camera = event.getCamera();
        if (camera == null && this.mc.gameRenderer != null) {
            camera = this.mc.gameRenderer.getCamera();
        }
        if (level == null || camera == null || this.weather.is("Снег")) {
            this.lastRainfallNanos = 0L;
            return;
        }
        float rainLevel = level.getRainGradient(1.0f);
        float wetVis = rainLevel;
        if (wetVis <= 0.02f) {
            this.lastRainfallNanos = 0L;
            this.rainfall.clear();
            return;
        }
        long now = System.nanoTime();
        float dt = this.lastRainfallNanos == 0L ? 0.016f : Math.min(0.1f, (float)(now - this.lastRainfallNanos) / 1.0E9f);
        this.lastRainfallNanos = now;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d camPos = vec3d2;
        int count = (int)(this.rainDropDensity.getFloat() * 16.0f * rainLevel);
        this.rainfall.update(level, camPos, dt, count, this.rainDropSpeed.getFloat() / 100.0f, wetVis);
        float rippleBase = Math.min(1.0f, wetVis * 1.5f) * 1.3f;
        MatrixStack matrixStack2 = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        this.rainfall.render(matrixStack2, immediate2, camPos, rainLevel, rippleBase * (this.rainRipples.getFloat() / 100.0f), rippleBase * 0.6f);
    }

    public final void renderFireflies(@NotNull WorldRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.isVisuallyActive() || !this.fireflies.getValue()) {
            this.lastFireflyNanos = 0L;
            this.fireflyField.clear();
            return;
        }
        ClientWorld level = this.mc.world;
        Camera camera = event.getCamera();
        if (camera == null && this.mc.gameRenderer != null) {
            camera = this.mc.gameRenderer.getCamera();
        }
        if (level == null || camera == null) {
            this.lastFireflyNanos = 0L;
            return;
        }
        long now = System.nanoTime();
        float dt = this.lastFireflyNanos == 0L ? 0.016f : Math.min(0.1f, (float)(now - this.lastFireflyNanos) / 1.0E9f);
        this.lastFireflyNanos = now;
        float presence = this.fireflyPresence(level);
        int target = this.isEnabled() && presence > 0.02f ? Math.round(this.fireflyCount.getFloat() * presence) : 0;
        float radius = this.fireflyRadius.getFloat();
        float speed = this.fireflySpeed.getFloat() / 100.0f;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        this.fireflyField.update(level, vec3d2, dt, target, radius, speed);
        int primary = 0;
        int secondary = 0;
        if (this.fireflyColorMode.is("Тема")) {
            InterfaceModule iface;
            InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
            primary = interfaceModule != null ? interfaceModule.clientPrimaryColorOpaque() : this.fireflyColor.getColorOpaque();
            secondary = iface != null && iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : primary;
        } else {
            secondary = primary = this.fireflyColor.getColorOpaque();
        }
        MatrixStack matrixStack2 = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        this.fireflyField.render(matrixStack2, immediate2, camera, primary, secondary, this.fireflySize.getFloat() / 100.0f * 0.16f, this.fireflyGlow.getFloat() / 100.0f, radius, this.visualAlpha());
    }

    private final float fireflyPresence(ClientWorld level) {
        float presence = 1.0f - level.getRainGradient(1.0f) * 0.85f;
        if (!this.fireflyNightOnly.getValue()) {
            return presence;
        }
        long time = (level.getTimeOfDay() % 24000L + 24000L) % 24000L;
        float night = time < 23800L ? 1.0f - MathHelper.clamp((float)((float)(time - 22500L) / 1300.0f), (float)0.0f, (float)1.0f) : (time < 11800L ? 0.0f : (time < 13200L ? (float)(time - 11800L) / 1400.0f : 1.0f));
        return presence *= level.getDimension().hasSkyLight() ? night : 1.0f;
    }

    public final boolean shouldHideVanillaWeather() {
        return this.isEnabled() && this.customRain.getValue() && !this.weather.is("Снег");
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.lastRainfallNanos = 0L;
        this.rainfall.clear();
        this.lastFireflyNanos = 0L;
        this.fireflyField.clear();
        AmbienceFogRenderer.clear();
        GroundReflectRenderer.clear();
        ClientWorld level = this.mc.world;
        if (level != null) {
            ClientWorld.Properties properties2 = level.getLevelProperties();
            Intrinsics.checkNotNullExpressionValue((Object)properties2, (String)"getLevelData(...)");
            this.restoreWeather(level, properties2);
        } else {
            this.clearWeatherSnapshot();
        }
    }

    @EventHandler
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isReceive()) {
            return;
        }
        Packet<?> eventPacket = event.getPacket();
        if (eventPacket instanceof WorldTimeUpdateS2CPacket && this.isEnabled()) {
            event.cancel();
            return;
        }
        if (!(eventPacket instanceof GameStateChangeS2CPacket)) {
            return;
        }
        GameStateChangeS2CPacket.Reason reason2 = ((GameStateChangeS2CPacket)eventPacket).getReason();
        Intrinsics.checkNotNullExpressionValue((Object)reason2, (String)"getEvent(...)");
        GameStateChangeS2CPacket.Reason type = reason2;
        if (!Ambience.Companion.isWeatherPacket(type)) {
            return;
        }
        this.updateCachedWeather((GameStateChangeS2CPacket)eventPacket);
        if (this.isEnabled()) {
            event.cancel();
        }
    }

    private final boolean shouldForcePrecipitation() {
        return this.weather.is("Дождь") || this.weather.is("Гроза") || this.weather.is("Снег");
    }

    private final void restoreWeather(ClientWorld level, ClientWorld.Properties levelData) {
        if (!this.weatherOverrideActive) {
            return;
        }
        levelData.setRaining(this.cachedServerRaining);
        level.setRainGradient(this.cachedServerRainLevel);
        level.setThunderGradient(this.cachedServerThunderLevel);
        this.clearWeatherSnapshot();
    }

    private final void clearWeatherSnapshot() {
        this.weatherOverrideActive = false;
        this.weatherSnapshotLevel = null;
    }

    @Protect(value=Level.STD)
    private final void updateCachedWeather(GameStateChangeS2CPacket packet) {
        GameStateChangeS2CPacket.Reason reason2 = packet.getReason();
        Intrinsics.checkNotNullExpressionValue((Object)reason2, (String)"getEvent(...)");
        GameStateChangeS2CPacket.Reason type = reason2;
        if (Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.RAIN_STARTED)) {
            this.cachedServerRaining = true;
            this.cachedServerRainLevel = Math.max(this.cachedServerRainLevel, 1.0f);
            return;
        }
        if (Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.RAIN_STOPPED)) {
            this.cachedServerRaining = false;
            this.cachedServerRainLevel = 0.0f;
            this.cachedServerThunderLevel = 0.0f;
            return;
        }
        if (Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.RAIN_GRADIENT_CHANGED)) {
            this.cachedServerRainLevel = Math.clamp(packet.getValue(), 0.0f, 1.0f);
            this.cachedServerRaining = this.cachedServerRainLevel > 1.0E-4f;
            return;
        }
        if (Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.THUNDER_GRADIENT_CHANGED)) {
            this.cachedServerThunderLevel = Math.clamp(packet.getValue(), 0.0f, 1.0f);
        }
    }

    private static final Boolean customTime$lambda$0(Ambience this$0) {
        return this$0.mode.is("Свой");
    }

    private static final Boolean skyType$lambda$0(Ambience this$0) {
        return this$0.customSky.getValue();
    }

    private static final Boolean userShader$lambda$0(Ambience this$0) {
        return this$0.userSkyVisible();
    }

    private static final Boolean shaderEditor$lambda$0(Ambience this$0) {
        return this$0.customSky.getValue() && this$0.skyType.is("Кастомный");
    }

    private static final Boolean skyColorMode$lambda$0(Ambience this$0) {
        return this$0.customSky.getValue();
    }

    private static final Boolean skySecondColor$lambda$0(Ambience this$0) {
        return this$0.customSky.getValue() && !this$0.skyColorMode.is("Радуга");
    }

    private static final Boolean skyColor$lambda$0(Ambience this$0) {
        return this$0.customSky.getValue() && this$0.skyColorMode.is("Свой");
    }

    private static final Boolean skyColor2$lambda$0(Ambience this$0) {
        return this$0.customSky.getValue() && this$0.skyColorMode.is("Свой") && this$0.skySecondColor.getValue();
    }

    private static final Boolean skyBrightnessLevel$lambda$0(Ambience this$0) {
        return this$0.customSky.getValue();
    }

    private static final Boolean fogColorMode$lambda$0(Ambience this$0) {
        return this$0.customFog.getValue();
    }

    private static final Boolean fogColor$lambda$0(Ambience this$0) {
        return this$0.customFog.getValue() && this$0.fogColorMode.is("Свой");
    }

    private static final Boolean fogDensity$lambda$0(Ambience this$0) {
        return this$0.customFog.getValue();
    }

    private static final Boolean fogBase$lambda$0(Ambience this$0) {
        return this$0.customFog.getValue();
    }

    private static final Boolean fogPressed$lambda$0(Ambience this$0) {
        return this$0.customFog.getValue();
    }

    private static final Boolean fogSkyHaze$lambda$0(Ambience this$0) {
        return this$0.customFog.getValue();
    }

    private static final Boolean rainRipples$lambda$0(Ambience this$0) {
        return this$0.customRain.getValue();
    }

    private static final Boolean rainDropDensity$lambda$0(Ambience this$0) {
        return this$0.customRain.getValue();
    }

    private static final Boolean rainDropSpeed$lambda$0(Ambience this$0) {
        return this$0.customRain.getValue();
    }

    private static final Boolean wetReflection$lambda$0(Ambience this$0) {
        return this$0.wetWorld.getValue();
    }

    private static final Boolean wetAmount$lambda$0(Ambience this$0) {
        return this$0.wetWorld.getValue();
    }

    private static final Boolean wetQuality$lambda$0(Ambience this$0) {
        return this$0.wetWorld.getValue();
    }

    private static final Boolean windGrassStrength$lambda$0(Ambience this$0) {
        return this$0.wind.getValue();
    }

    private static final Boolean windLeavesStrength$lambda$0(Ambience this$0) {
        return this$0.wind.getValue();
    }

    private static final Boolean windSpeed$lambda$0(Ambience this$0) {
        return this$0.wind.getValue();
    }

    private static final Boolean windGusts$lambda$0(Ambience this$0) {
        return this$0.wind.getValue();
    }

    private static final Boolean fireflyColorMode$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue();
    }

    private static final Boolean fireflyColor$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue() && this$0.fireflyColorMode.is("Свой");
    }

    private static final Boolean fireflyCount$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue();
    }

    private static final Boolean fireflyRadius$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue();
    }

    private static final Boolean fireflySize$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue();
    }

    private static final Boolean fireflyGlow$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue();
    }

    private static final Boolean fireflySpeed$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue();
    }

    private static final Boolean fireflyNightOnly$lambda$0(Ambience this$0) {
        return this$0.fireflies.getValue();
    }

    private static final void _init_$lambda$0(Ambience this$0) {
        this$0.openShaderEditor();
    }

    @JvmStatic
    @Nullable
    public static final Ambience getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u000fR\u0014\u0010\u0017\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u000fR\u0014\u0010\u0018\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u000fR\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0012R\u0014\u0010 \u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001b\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Ambience.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/Ambience;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/Ambience;", "Lnet/minecraft/GameStateChangeS2CPacket$Reason;", "type", "", "isWeatherPacket", "(Lnet/minecraft/GameStateChangeS2CPacket$Reason;)Z", "", "FOG_LAYERS", "F", "", "WET_QUALITY_LOW", "Ljava/lang/String;", "WET_QUALITY_MEDIUM", "WET_QUALITY_HIGH", "WET_GLOSS", "WET_HIT_THICKNESS", "WET_MAX_DISTANCE", "FIREFLY_BASE_SIZE", "", "FIREFLY_DAWN_START", "J", "FIREFLY_DAWN_END", "FIREFLY_DUSK_START", "FIREFLY_DUSK_END", "NO_SHADERS", "SHADER_RESCAN_MS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final Ambience getInstance() {
            return ModuleManager.Companion.get().get(Ambience.class);
        }

        private final boolean isWeatherPacket(GameStateChangeS2CPacket.Reason type) {
            return Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.RAIN_STARTED) || Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.RAIN_STOPPED) || Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.RAIN_GRADIENT_CHANGED) || Intrinsics.areEqual((Object)type, (Object)GameStateChangeS2CPacket.THUNDER_GRADIENT_CHANGED);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

