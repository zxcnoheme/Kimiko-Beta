/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.ArraysKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.screen.Screen
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.voice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import mods.voicechat.natives.Denoiser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.party.voice.MicCapture;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.utils.sounds.Sounds;
import rtx.kimiko.utils.voice.VoiceDsp;
import rtx.kimiko.utils.voice.VoiceLevels;
import rtx.kimiko.utils.voice.VoiceMatcher;
import rtx.kimiko.utils.voice.VoiceTemplate;
import rtx.kimiko.utils.voice.VoiceTemplateStore;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00c8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 \u00a2\u00012\u00020\u0001:\n\u00a3\u0001\u00a4\u0001\u00a5\u0001\u00a6\u0001\u00a2\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\bJ\r\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\nJ\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\bJ\u0015\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000e\u0010\bJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0012\u0010\nJ\r\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0019\u0010\nJ\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0018J!\u0010\u001e\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\u0006\u00a2\u0006\u0004\b \u0010\u0003J\r\u0010!\u001a\u00020\u0006\u00a2\u0006\u0004\b!\u0010\u0003J'\u0010'\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\"H\u0017b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8&\u00a2\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b*\u0010(J\u000f\u0010+\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b+\u0010\u0003J\u000f\u0010,\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b,\u0010\u0003J9\u00101\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b1\u00102J'\u00103\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b3\u00104J1\u00105\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010/\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b5\u00106J/\u0010:\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00132\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b:\u0010;J'\u0010A\u001a\u00020\u00062\u0006\u0010=\u001a\u00020<2\u000e\u0010@\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>H\u0002\u00a2\u0006\u0004\bA\u0010BJ5\u0010H\u001a\u00020\u00062\f\u0010C\u001a\b\u0012\u0004\u0012\u00020?0>2\u0006\u0010D\u001a\u00020?2\u0006\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bH\u0010IJ\u0017\u0010J\u001a\u00020\u00042\u0006\u0010=\u001a\u00020<H\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u001f\u0010M\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00132\u0006\u0010L\u001a\u00020<H\u0002\u00a2\u0006\u0004\bM\u0010NJ'\u0010P\u001a\u00020\u00062\u0006\u0010L\u001a\u00020<H\u0003b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8O\u00a2\u0006\u0004\bP\u0010QJ;\u0010U\u001a\u0004\u0018\u00010T2\u000e\u0010@\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010>2\b\u0010S\u001a\u0004\u0018\u00010RH\u0003b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8O\u00a2\u0006\u0004\bU\u0010VJ9\u0010\\\u001a\u00020\u00062\u0006\u0010X\u001a\u00020W2\b\u0010Y\u001a\u0004\u0018\u00010\u00162\u0006\u0010[\u001a\u00020ZH\u0003b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8O\u00a2\u0006\u0004\b\\\u0010]J;\u0010^\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010X\u001a\u00020W2\b\u0010Y\u001a\u0004\u0018\u00010\u0016H\u0003b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8O\u00a2\u0006\u0004\b^\u0010_J)\u0010`\u001a\u00020Z2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0016H\u0007b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8O\u00a2\u0006\u0004\b`\u0010aJ\u001f\u0010b\u001a\u00020ZH\u0007b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8O\u00a2\u0006\u0004\bb\u0010cJ)\u0010d\u001a\u00020Z2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0016H\u0007b\u000e\b$\u0012\n\b\u0005\u0012\u0006\b\n0%8O\u00a2\u0006\u0004\bd\u0010aR\u001c\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010g\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0014\u0010j\u001a\u00020i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u0014\u0010m\u001a\u00020l8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010nR \u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0016\u0010t\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010sR\u0016\u0010u\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010sR\u0016\u0010v\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010sR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010wR\u0016\u0010x\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010yR\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010yR\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010zR\u0018\u0010{\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0016\u0010|\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010sR\u0018\u0010}\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010~R\u0016\u0010\u007f\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010yR\u0018\u0010\u0080\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010yR\u0018\u0010\u0081\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010wR\u0018\u0010\u0082\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010yR\u0018\u0010\u0083\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010yR\u0018\u0010\u0084\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010yR\u0019\u0010\u0085\u0001\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u001c\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0087\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001c\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008a\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0019\u0010\u008d\u0001\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u0086\u0001R\u0019\u0010\u008e\u0001\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u0086\u0001R\u0018\u0010\u008f\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008f\u0001\u0010sR\u0018\u0010\u0090\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0090\u0001\u0010yR\u0018\u0010\u0091\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0091\u0001\u0010yR\u001a\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0092\u0001\u0010zR\u0018\u0010\u0094\u0001\u001a\u00030\u0093\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R\u001f\u0010\u0098\u0001\u001a\n\u0012\u0005\u0012\u00030\u0097\u00010\u0096\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001R\u0019\u0010\u009a\u0001\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0086\u0001R\u0019\u0010\u009b\u0001\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0086\u0001R\u0019\u0010\u009c\u0001\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0086\u0001R\u0018\u0010\u009d\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009d\u0001\u0010wR\u0018\u0010\u009e\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009e\u0001\u0010wR\u001b\u0010\u009f\u0001\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u00a0\u0001R\u0019\u0010\u00a1\u0001\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u0086\u0001\u00a8\u0006\u00a7\u0001"}, d2={"Lrtx/kimiko/api/voice/VoiceBindManager;", "Lrtx/kimiko/api/party/voice/MicCapture$Listener;", "<init>", "()V", "", "value", "", "setActive", "(Z)V", "isActive", "()Z", "setRequireWake", "requiresWake", "setDebug", "setAnnounce", "", "level", "()F", "isArmed", "", "lastTriggerMs", "()J", "", "lastTriggerName", "()Ljava/lang/String;", "isRecording", "recordingKey", "key", "Lrtx/kimiko/api/voice/VoiceBindManager$RecordCallback;", "callback", "beginRecording", "(Ljava/lang/String;Lrtx/kimiko/api/voice/VoiceBindManager$RecordCallback;)V", "noteInput", "cancelRecording", "", "samples", "Lsigil/protect/Protect;", "Lsigil/protect/Level;", "STD", "onMicFrame", "([S)V", "frame", "append", "resetCapture", "speculate", "pcm", "gen", "startMs", "speculative", "submit", "([SJLjava/lang/String;JZ)V", "speculateWorker", "(JJ[S)V", "process", "(JLjava/lang/String;J[S)V", "Lrtx/kimiko/utils/voice/VoiceDsp$Extracted;", "extracted", "streaming", "evaluate", "(JJLrtx/kimiko/utils/voice/VoiceDsp$Extracted;Z)V", "Lrtx/kimiko/api/modules/Module;", "module", "", "", "segment", "adapt", "(Lrtx/kimiko/api/modules/Module;[[F)V", "raw", "energy", "Lrtx/kimiko/utils/voice/VoiceMatcher$Prefix;", "prefix", "wakeScore", "rememberIfGeneric", "([[F[FLrtx/kimiko/utils/voice/VoiceMatcher$Prefix;F)V", "confirmed", "(Lrtx/kimiko/api/modules/Module;)Z", "target", "trigger", "(JLrtx/kimiko/api/modules/Module;)V", "MAX", "markTriggered", "(Lrtx/kimiko/api/modules/Module;)V", "Lrtx/kimiko/utils/voice/VoiceTemplate;", "wake", "Lrtx/kimiko/api/voice/VoiceBindManager$Candidate;", "bestCommand", "([[FLrtx/kimiko/utils/voice/VoiceTemplate;)Lrtx/kimiko/api/voice/VoiceBindManager$Candidate;", "Lrtx/kimiko/api/voice/VoiceBindManager$RecordStatus;", "status", "detail", "", "remaining", "finishRecording", "(Lrtx/kimiko/api/voice/VoiceBindManager$RecordStatus;Ljava/lang/String;I)V", "keepRecording", "(Ljava/lang/String;Lrtx/kimiko/api/voice/VoiceBindManager$RecordStatus;Ljava/lang/String;)V", "remainingFor", "(Ljava/lang/String;)I", "recordingRemaining", "()I", "promptRemaining", "preroll", "[[S", "utterance", "[S", "Ljava/util/concurrent/atomic/AtomicBoolean;", "busy", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicInteger;", "pendingTakes", "Ljava/util/concurrent/atomic/AtomicInteger;", "", "lastAdaptMs", "Ljava/util/Map;", "active", "Z", "requireWake", "debug", "announce", "F", "armedUntil", "J", "Ljava/lang/String;", "recordKey", "recordFresh", "recordCallback", "Lrtx/kimiko/api/voice/VoiceBindManager$RecordCallback;", "recordDeadline", "recordHardDeadline", "recordPeak", "muteUntil", "captureGen", "firedGen", "session", "I", "Lmods/voicechat/natives/Denoiser;", "denoiser", "Lmods/voicechat/natives/Denoiser;", "Ljava/util/concurrent/ExecutorService;", "worker", "Ljava/util/concurrent/ExecutorService;", "prerollPos", "utterLen", "capturing", "lastVoiceMs", "utterStartMs", "capturedKey", "Lrtx/kimiko/utils/voice/VoiceLevels;", "levels", "Lrtx/kimiko/utils/voice/VoiceLevels;", "Ljava/util/ArrayDeque;", "Lrtx/kimiko/api/voice/VoiceBindManager$BackgroundSample;", "background", "Ljava/util/ArrayDeque;", "voicedRun", "voicedFrames", "totalFrames", "snrPeak", "lastProb", "confirmModule", "Lrtx/kimiko/api/modules/Module;", "confirmCount", "Companion", "RecordStatus", "RecordCallback", "BackgroundSample", "Candidate", "rtx.kimiko:kimiko"})
public final class VoiceBindManager
implements MicCapture.Listener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final short[][] preroll = new short[12][];
    @NotNull
    private final short[] utterance = new short[182400];
    @NotNull
    private final AtomicBoolean busy = new AtomicBoolean();
    @NotNull
    private final AtomicInteger pendingTakes = new AtomicInteger();
    @NotNull
    private final Map<String, Long> lastAdaptMs = new ConcurrentHashMap();
    private volatile boolean active;
    private volatile boolean requireWake = true;
    private volatile boolean debug;
    private volatile boolean announce = true;
    private volatile float level;
    private volatile long armedUntil;
    private volatile long lastTriggerMs;
    @NotNull
    private volatile String lastTriggerName = "";
    @Nullable
    private volatile String recordKey;
    private volatile boolean recordFresh;
    @Nullable
    private volatile RecordCallback recordCallback;
    private volatile long recordDeadline;
    private volatile long recordHardDeadline;
    private volatile float recordPeak;
    private volatile long muteUntil;
    private volatile long captureGen;
    private volatile long firedGen = -1L;
    private volatile int session;
    @Nullable
    private Denoiser denoiser;
    @Nullable
    private ExecutorService worker;
    private int prerollPos;
    private int utterLen;
    private boolean capturing;
    private long lastVoiceMs;
    private long utterStartMs;
    @Nullable
    private String capturedKey;
    @NotNull
    private final VoiceLevels levels = new VoiceLevels();
    @NotNull
    private final ArrayDeque<BackgroundSample> background = new ArrayDeque();
    private int voicedRun;
    private int voicedFrames;
    private int totalFrames;
    private float snrPeak;
    private float lastProb;
    @Nullable
    private volatile Module confirmModule;
    private volatile int confirmCount;
    @JvmField
    public static final int TARGET_TAKES = 5;
    private static final int PREROLL_FRAMES = 12;
    private static final int ATTACK_FRAMES = 5;
    private static final int MIN_VOICED_FRAMES = 9;
    private static final long INPUT_MUTE_MS = 180L;
    private static final float MIN_VOICED_RATIO = 0.33f;
    private static final float MIN_SNR = 7.0f;
    private static final float DEFAULT_LIMIT = 0.45f;
    private static final float SPREAD_FACTOR = 1.15f;
    private static final float SPREAD_MARGIN = 0.06f;
    private static final float LIMIT_MIN = 0.3f;
    private static final float LIMIT_MAX = 0.62f;
    private static final float BACKGROUND_ADD = 1.85f;
    private static final int BACKGROUND_MAX = 8;
    private static final long BACKGROUND_TTL_MS = 120000L;
    private static final long SILENCE_MS = 300L;
    private static final int SPEC_EVERY = 4;
    private static final int SPEC_MIN_FRAMES = 18;
    private static final float SPEC_SCORE = 0.9f;
    private static final float COHORT_MARGIN = 0.82f;
    private static final float SURE_SCORE = 0.7f;
    private static final float WAKE_HARD = 1.18f;
    private static final float WAKE_REMEMBER = 1.45f;
    private static final float CMD_HARD = 1.05f;
    private static final float WAKE_WEIGHT = 0.7f;
    private static final float CMD_WEIGHT = 1.3f;
    private static final float ADAPT_SCORE = 0.58f;
    private static final long ADAPT_COOLDOWN_MS = 180000L;
    private static final float ARMED_WAKE_SCORE = 0.75f;
    private static final int WAKE_MAX_START = 18;
    private static final int CMD_MAX_START = 30;
    private static final int TAIL_BACKOFF = 8;
    private static final int MIN_TAIL_FRAMES = 14;
    private static final int MIN_TAIL_NORM_FRAMES = 24;
    private static final float TAIL_TRIM_GATE = 0.02f;
    private static final int CONFIRMATIONS = 2;
    private static final long MIN_UTTER_MS = 200L;
    private static final long MAX_UTTER_MS = 3400L;
    private static final long ARM_MS = 3000L;
    private static final long COOLDOWN_MS = 900L;
    private static final long RECORD_TIMEOUT_MS = 7000L;
    private static final long RECORD_SESSION_MS = 30000L;
    private static final int MAX_SAMPLES = 182400;
    @JvmField
    @NotNull
    public static final VoiceBindManager INSTANCE = new VoiceBindManager();

    private VoiceBindManager() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void setActive(boolean value) {
        block8: {
            AutoCloseable it;
            block7: {
                if (this.active == value) {
                    return;
                }
                this.active = value;
                int n = this.session;
                this.session = n + 1;
                if (!value) break block7;
                this.denoiser = VoiceBindManager.Companion.createDenoiser();
                this.worker = Executors.newSingleThreadExecutor(VoiceBindManager::setActive$lambda$0);
                this.resetCapture();
                this.levels.reset();
                ArrayDeque<BackgroundSample> arrayDeque = this.background;
                synchronized (arrayDeque) {
                    this.background.clear();
                }
                this.pendingTakes.set(0);
                this.lastProb = 0.0f;
                MicCapture.subscribe(this);
                break block8;
            }
            MicCapture.unsubscribe(this);
            this.cancelRecording();
            this.armedUntil = 0L;
            this.level = 0.0f;
            if (this.worker != null) {
                this.worker.shutdownNow();
                this.worker = null;
            }
            Denoiser denoiser = this.denoiser;
            if (denoiser == null) break block8;
            it = denoiser;
            boolean bl = false;
            try {
                ((Denoiser)it).close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            this.denoiser = null;
        }
    }

    public final boolean isActive() {
        return this.active;
    }

    public final void setRequireWake(boolean value) {
        this.requireWake = value;
    }

    public final boolean requiresWake() {
        return this.requireWake;
    }

    public final void setDebug(boolean value) {
        this.debug = value;
    }

    public final void setAnnounce(boolean value) {
        this.announce = value;
    }

    public final float level() {
        return this.level;
    }

    public final boolean isArmed() {
        return System.currentTimeMillis() < this.armedUntil;
    }

    public final long lastTriggerMs() {
        return this.lastTriggerMs;
    }

    @NotNull
    public final String lastTriggerName() {
        return this.lastTriggerName;
    }

    public final boolean isRecording() {
        return this.recordKey != null;
    }

    @Nullable
    public final String recordingKey() {
        return this.recordKey;
    }

    public final void beginRecording(@Nullable String key, @Nullable RecordCallback callback) {
        if (key == null || callback == null) {
            return;
        }
        if (!this.active) {
            callback.onResult(RecordStatus.ERROR, null, 0);
            return;
        }
        MicCapture.subscribe(this);
        if (!MicCapture.isCapturing() && MicCapture.lastError() != null) {
            callback.onResult(RecordStatus.ERROR, MicCapture.lastError(), 0);
            return;
        }
        long now = System.currentTimeMillis();
        this.recordCallback = callback;
        this.recordKey = key;
        this.recordFresh = true;
        this.recordDeadline = now + 7000L;
        this.recordHardDeadline = now + 30000L;
        this.recordPeak = 0.0f;
        this.pendingTakes.set(0);
        this.armedUntil = 0L;
    }

    public final void noteInput() {
        if (!this.active) {
            return;
        }
        this.muteUntil = System.currentTimeMillis() + 180L;
    }

    public final void cancelRecording() {
        this.recordKey = null;
        this.recordCallback = null;
        this.recordDeadline = 0L;
        this.recordHardDeadline = 0L;
        this.recordPeak = 0.0f;
    }

    @Override
    @Protect(value=Level.STD)
    public void onMicFrame(@NotNull short[] samples) {
        boolean overflow;
        boolean muted;
        Intrinsics.checkNotNullParameter((Object)samples, (String)"samples");
        if (!this.active) {
            return;
        }
        long now = System.currentTimeMillis();
        float raw = VoiceDsp.rms(samples);
        this.levels.update(raw, this.lastProb);
        this.level = this.level * 0.6f + Math.min(1.0f, raw * this.levels.gain()) * 0.4f;
        float prob = 1.0f;
        Denoiser d = this.denoiser;
        if (d != null) {
            try {
                prob = d.denoiseInPlace(samples);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        this.lastProb = prob;
        boolean voiced = this.levels.voiced();
        float snr = this.levels.snr();
        String rKey = this.recordKey;
        if (rKey != null) {
            if (snr > this.recordPeak) {
                this.recordPeak = snr;
            }
            if (now > this.recordHardDeadline) {
                this.finishRecording(RecordStatus.TIMEOUT, I18n.tr("%d дБ", new Object[]{MathKt.roundToInt((float)this.recordPeak)}), this.remainingFor(rKey));
            } else if (now > this.recordDeadline && !this.capturing && this.pendingTakes.get() == 0) {
                this.finishRecording(RecordStatus.TIMEOUT, I18n.tr("%d дБ", new Object[]{MathKt.roundToInt((float)this.recordPeak)}), this.remainingFor(rKey));
            }
        }
        boolean bl = muted = now < this.muteUntil;
        if (!this.capturing) {
            this.preroll[this.prerollPos] = samples;
            this.prerollPos = (this.prerollPos + 1) % 12;
            int n = this.voicedRun = voiced ? this.voicedRun + 1 : 0;
            if (muted || this.voicedRun < 5) {
                return;
            }
            this.capturing = true;
            long l = this.captureGen;
            this.captureGen = l + 1L;
            this.capturedKey = this.recordKey;
            this.utterLen = 0;
            this.utterStartMs = now - 100L;
            for (int i = 0; i < 12; ++i) {
                short[] frame = this.preroll[(this.prerollPos + i) % 12];
                if (frame == null) continue;
                this.append(frame);
            }
            Arrays.fill(this.preroll, null);
            this.lastVoiceMs = now;
            this.voicedFrames = 5;
            this.totalFrames = 5;
            this.snrPeak = snr;
            return;
        }
        this.append(samples);
        int i = this.totalFrames;
        this.totalFrames = i + 1;
        if (snr > this.snrPeak) {
            this.snrPeak = snr;
        }
        if (voiced) {
            i = this.voicedFrames;
            this.voicedFrames = i + 1;
            this.lastVoiceMs = now;
        }
        if (this.capturedKey == null && this.firedGen != this.captureGen && this.totalFrames >= 18 && this.totalFrames % 4 == 0) {
            this.speculate();
        }
        boolean silent = now - this.lastVoiceMs >= 300L;
        boolean bl2 = overflow = now - this.utterStartMs >= 3400L || this.utterLen >= 182400 - MicCapture.FRAME_SAMPLES;
        if (!silent && !overflow) {
            return;
        }
        int length = this.utterLen;
        long duration = now - this.utterStartMs;
        long gen = this.captureGen;
        long startMs = this.utterStartMs;
        String key = this.capturedKey;
        float ratio = this.totalFrames == 0 ? 0.0f : (float)this.voicedFrames / (float)this.totalFrames;
        int voicedCount = this.voicedFrames;
        float peak = this.snrPeak;
        boolean alreadyFired = this.firedGen == gen;
        this.resetCapture();
        if (alreadyFired) {
            return;
        }
        if (duration < 200L || length < 9600) {
            if (key != null) {
                this.keepRecording(key, RecordStatus.TOO_SHORT, null);
            }
            return;
        }
        if (ratio < 0.33f || voicedCount < 9 || peak < 7.0f) {
            Object[] objectArray = new Object[]{voicedCount * 20, MathKt.roundToInt((float)(ratio * 100.0f)), MathKt.roundToInt((float)peak)};
            String why = I18n.tr("шум отброшен: речь %d мс, %d%%, пик %d дБ", objectArray);
            if (key != null) {
                this.keepRecording(key, RecordStatus.TOO_QUIET, null);
            } else if (this.debug) {
                VoiceBindManager.Companion.report(why);
            }
            return;
        }
        short[] pcm = new short[length];
        System.arraycopy(this.utterance, 0, pcm, 0, length);
        this.submit(pcm, gen, key, startMs, false);
    }

    private final void append(short[] frame) {
        int copy = Math.min(frame.length, 182400 - this.utterLen);
        if (copy <= 0) {
            return;
        }
        System.arraycopy(frame, 0, this.utterance, this.utterLen, copy);
        this.utterLen += copy;
    }

    private final void resetCapture() {
        this.capturing = false;
        this.utterLen = 0;
        this.prerollPos = 0;
        this.voicedRun = 0;
        this.voicedFrames = 0;
        this.totalFrames = 0;
        this.snrPeak = 0.0f;
        this.capturedKey = null;
        this.confirmModule = null;
        this.confirmCount = 0;
        Arrays.fill(this.preroll, null);
    }

    private final void speculate() {
        if (this.utterLen < 12000) {
            return;
        }
        short[] pcm = new short[this.utterLen];
        System.arraycopy(this.utterance, 0, pcm, 0, this.utterLen);
        this.submit(pcm, this.captureGen, null, this.utterStartMs, true);
    }

    private final void submit(short[] pcm, long gen, String key, long startMs, boolean speculative) {
        block6: {
            ExecutorService executorService = this.worker;
            if (executorService == null) {
                return;
            }
            ExecutorService pool = executorService;
            if (speculative && !this.busy.compareAndSet(false, true)) {
                return;
            }
            int mySession = this.session;
            if (key != null) {
                this.pendingTakes.incrementAndGet();
            }
            try {
                pool.execute(() -> VoiceBindManager.submit$lambda$0(mySession, this, speculative, gen, startMs, pcm, key));
            }
            catch (Throwable t) {
                if (key != null) {
                    this.pendingTakes.updateAndGet(VoiceBindManager::submit$lambda$1);
                }
                if (!speculative) break block6;
                this.busy.set(false);
            }
        }
    }

    private final void speculateWorker(long gen, long startMs, short[] pcm) {
        if (this.firedGen == gen || this.recordKey != null) {
            return;
        }
        VoiceDsp.Extracted extracted = VoiceDsp.extract(pcm);
        if (extracted != null) {
            this.evaluate(gen, startMs, extracted, true);
        }
    }

    private final void process(long gen, String key, long startMs, short[] pcm) {
        if (key != null) {
            VoiceTemplate template;
            if (!Intrinsics.areEqual((Object)key, (Object)this.recordKey)) {
                return;
            }
            float[][] features = VoiceDsp.features(pcm);
            if (features == null) {
                this.keepRecording(key, RecordStatus.TOO_SHORT, null);
                return;
            }
            VoiceTemplate voiceTemplate = template = this.recordFresh ? null : VoiceTemplateStore.get(key);
            if (template == null) {
                template = new VoiceTemplate();
            }
            this.recordFresh = false;
            float self = template.isEmpty() ? -1.0f : template.distanceTo(features);
            template.addRecorded(features);
            VoiceTemplateStore.put(key, template);
            int remaining = Math.max(0, TARGET_TAKES - template.takeCount());
            if (remaining > 0) {
                this.keepRecording(key, RecordStatus.OK, null);
            } else {
                this.finishRecording(RecordStatus.OK, self < 0.0f ? null : VoiceBindManager.Companion.format(self), 0);
            }
            return;
        }
        VoiceDsp.Extracted extracted = VoiceDsp.extract(pcm);
        if (extracted == null) {
            return;
        }
        VoiceDsp.Extracted extracted2 = extracted;
        this.evaluate(gen, startMs, extracted2, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void evaluate(long gen, long startMs, VoiceDsp.Extracted extracted, boolean streaming) {
        Object info;
        float wakeScore;
        float[][] segment;
        boolean hasWake;
        VoiceTemplate wake;
        boolean loud;
        block16: {
            float[][] raw;
            block17: {
                if (this.firedGen == gen) {
                    return;
                }
                long now = System.currentTimeMillis();
                if (now - this.lastTriggerMs < 900L) {
                    return;
                }
                loud = this.debug && !streaming;
                wake = VoiceTemplateStore.get("__wake__");
                boolean bl = hasWake = wake != null && !wake.isEmpty();
                if (this.requireWake && !hasWake) {
                    if (!loud) return;
                    VoiceBindManager.Companion.report(I18n.tr("ключевое слово не записано"));
                    return;
                }
                raw = extracted.frames;
                float[] energy = extracted.energy;
                segment = null;
                wakeScore = 0.75f;
                info = "";
                if (!this.requireWake) break block17;
                boolean armed = startMs < this.armedUntil || now < this.armedUntil;
                float wakeLimit = VoiceBindManager.Companion.limitFor(wake);
                int n = ((Object[])raw).length;
                VoiceTemplate voiceTemplate = wake;
                Intrinsics.checkNotNull((Object)voiceTemplate);
                int headLen = Math.min(n, (int)((float)voiceTemplate.maxTakeFrames() * 1.8f) + 18);
                float[][] head = VoiceDsp.renormalise(VoiceBindManager.Companion.slice(raw, headLen));
                VoiceMatcher.Prefix prefix = wake.prefixMatch(head, 18);
                float headScore = prefix.distance / wakeLimit;
                if (headScore <= 1.18f) {
                    wakeScore = headScore;
                    Object[] objectArray = new Object[]{VoiceBindManager.Companion.format(wakeScore)};
                    info = I18n.tr("ключ %s", objectArray);
                    int from = VoiceBindManager.Companion.tailStart(energy, prefix.endFrame - 8);
                    float[][] tailRaw = VoiceMatcher.tail(raw, from);
                    if (tailRaw == null || ((Object[])tailRaw).length < 14) {
                        this.armedUntil = now + 3000L;
                        if (streaming) return;
                        if (loud) {
                            Object[] objectArray2 = new Object[]{info};
                            VoiceBindManager.Companion.report(I18n.tr("%s — жду команду", objectArray2));
                            return;
                        }
                        if (!this.announce) return;
                        VoiceBindManager.Companion.report(I18n.tr("Жду команду"));
                        return;
                    }
                    segment = VoiceBindManager.Companion.normaliseTail(raw, tailRaw, from);
                    info = (String)info + " | ";
                    break block16;
                } else if (armed) {
                    wakeScore = 0.75f;
                    segment = VoiceDsp.renormalise(raw);
                    break block16;
                } else {
                    if (!streaming) {
                        Object[] objectArray = new Object[]{VoiceBindManager.Companion.format(headScore)};
                        info = I18n.tr("ключ %s", objectArray);
                        if (loud) {
                            objectArray = new Object[]{info};
                            VoiceBindManager.Companion.report(I18n.tr("%s — мимо", objectArray));
                        }
                        this.rememberIfGeneric(raw, energy, prefix, headScore);
                    }
                    this.confirmModule = null;
                    return;
                }
            }
            segment = VoiceDsp.renormalise(raw);
        }
        Candidate best = this.bestCommand(segment, hasWake ? wake : null);
        if (best == null) {
            if (!loud) return;
            VoiceBindManager.Companion.report((String)info + I18n.tr("нет голосовых биндов"));
            return;
        }
        float combined = (wakeScore * 0.7f + best.getScore() * 1.3f) / 2.0f;
        boolean pass = combined <= (streaming ? 0.9f : 1.0f) && best.getScore() <= 1.05f && (best.getConfident() || best.getScore() <= 0.7f);
        String summary = (String)info + best.describe() + " итог " + VoiceBindManager.Companion.format(combined) + (pass ? " — ДА" : " — нет");
        if (loud) {
            VoiceBindManager.Companion.report(summary);
        }
        if (!pass) {
            this.confirmModule = null;
            return;
        }
        if (streaming && !this.confirmed(best.getModule())) {
            return;
        }
        if (!streaming && best.getScore() <= 0.58f && best.getConfident()) {
            this.adapt(best.getModule(), segment);
        }
        this.trigger(gen, best.getModule());
    }

    private final void adapt(Module module, float[][] segment) {
        long now = System.currentTimeMillis();
        Long last = this.lastAdaptMs.get(module.getName());
        if (last != null && now - last < 180000L) {
            return;
        }
        VoiceTemplate template = VoiceTemplateStore.get(module.getName());
        if (template == null || template.isEmpty() || segment == null) {
            return;
        }
        this.lastAdaptMs.put(module.getName(), now);
        template.addAdapted(segment);
        VoiceTemplateStore.put(module.getName(), template);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void rememberIfGeneric(float[][] raw, float[] energy, VoiceMatcher.Prefix prefix, float wakeScore) {
        Candidate nearTail;
        if (wakeScore <= 1.45f) {
            return;
        }
        float[][] full = VoiceDsp.renormalise(raw);
        if (full == null || ((Object[])full).length < 14) {
            return;
        }
        int from = VoiceBindManager.Companion.tailStart(energy, prefix.endFrame - 8);
        float[][] tailRaw = VoiceMatcher.tail(raw, from);
        if (tailRaw != null && ((Object[])tailRaw).length >= 14 && (nearTail = this.bestCommand(VoiceBindManager.Companion.normaliseTail(raw, tailRaw, from), null)) != null && nearTail.getDistance() <= nearTail.getLimit() * 1.85f) {
            return;
        }
        Candidate nearFull = this.bestCommand(full, null);
        if (nearFull != null && nearFull.getDistance() <= nearFull.getLimit() * 1.85f) {
            return;
        }
        ArrayDeque<BackgroundSample> arrayDeque = this.background;
        synchronized (arrayDeque) {
            boolean bl = false;
            this.background.addLast(new BackgroundSample(full, System.currentTimeMillis()));
            while (this.background.size() > 8) {
                this.background.removeFirst();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final boolean confirmed(Module module) {
        if (this.confirmModule == module) {
            int n = this.confirmCount;
            this.confirmCount = n + 1;
        } else {
            this.confirmModule = module;
            this.confirmCount = 1;
        }
        return this.confirmCount >= 2;
    }

    private final void trigger(long gen, Module target) {
        this.firedGen = gen;
        this.armedUntil = 0L;
        this.confirmModule = null;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        mc.execute(() -> VoiceBindManager.trigger$lambda$0(mc, target, this));
    }

    @Protect(value=Level.MAX)
    private final void markTriggered(Module target) {
        this.lastTriggerMs = System.currentTimeMillis();
        this.lastTriggerName = target.getDisplayName();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Protect(value=Level.MAX)
    private final Candidate bestCommand(float[][] segment, VoiceTemplate wake) {
        ModuleManager moduleManager = ModuleManager.Companion.get();
        if (moduleManager == null) {
            return null;
        }
        ModuleManager manager = moduleManager;
        Module best = null;
        float bestDistance = VoiceMatcher.INF;
        float bestLimit = 0.45f;
        float backgroundBest = wake == null || wake.isEmpty() ? VoiceMatcher.INF : wake.alignedDistance(segment, 30);
        long now = System.currentTimeMillis();
        ArrayList<float[][]> samples = null;
        ArrayDeque<BackgroundSample> arrayDeque = this.background;
        synchronized (arrayDeque) {
            while (!this.background.isEmpty() && now - this.background.peekFirst().getAddedMs() > 120000L) {
                this.background.removeFirst();
            }
            samples = new ArrayList<float[][]>(this.background.size());
            Iterator<BackgroundSample> iterator = this.background.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<BackgroundSample> iterator2 = iterator;
            while (iterator2.hasNext()) {
                BackgroundSample sample = iterator2.next();
                samples.add(sample.getFrames());
            }
        }
        for (float[][] sample : samples) {
            float d = VoiceMatcher.matchPrefix((float[][])sample, (float[][])segment, (int)30).distance;
            if (!(d < backgroundBest)) continue;
            backgroundBest = d;
        }
        for (Module module : manager.getAll()) {
            float d;
            VoiceTemplate template;
            if (module.getBindType() != Module.BindType.VOICE || (template = VoiceTemplateStore.get(module.getName())) == null || template.isEmpty() || (d = template.alignedDistance(segment, 30)) >= VoiceMatcher.INF) continue;
            float limit = VoiceBindManager.Companion.limitFor(template);
            if (best == null || d / limit < bestDistance / bestLimit) {
                if (best != null) {
                    backgroundBest = Math.min(backgroundBest, bestDistance);
                }
                best = module;
                bestDistance = d;
                bestLimit = limit;
                continue;
            }
            backgroundBest = Math.min(backgroundBest, d);
        }
        return best == null ? null : new Candidate(best, bestDistance, bestLimit, backgroundBest);
    }

    @Protect(value=Level.MAX)
    private final void finishRecording(RecordStatus status, String detail, int remaining) {
        RecordCallback callback = this.recordCallback;
        this.cancelRecording();
        if (callback == null) {
            return;
        }
        MinecraftClient.getInstance().execute(() -> VoiceBindManager.finishRecording$lambda$0(callback, status, detail, remaining));
    }

    @Protect(value=Level.MAX)
    private final void keepRecording(String key, RecordStatus status, String detail) {
        RecordCallback callback = this.recordCallback;
        if (callback == null || key == null || !Intrinsics.areEqual((Object)key, (Object)this.recordKey)) {
            return;
        }
        this.recordDeadline = System.currentTimeMillis() + 7000L;
        this.recordPeak = 0.0f;
        int remaining = this.remainingFor(key);
        MinecraftClient.getInstance().execute(() -> VoiceBindManager.keepRecording$lambda$0(callback, status, detail, remaining));
    }

    @Protect(value=Level.MAX)
    public final int remainingFor(@Nullable String key) {
        VoiceTemplate template;
        VoiceTemplate voiceTemplate = template = VoiceTemplateStore.get(key);
        int have = voiceTemplate != null ? voiceTemplate.takeCount() : 0;
        return Math.max(0, TARGET_TAKES - have);
    }

    @Protect(value=Level.MAX)
    public final int recordingRemaining() {
        String key = this.recordKey;
        return key == null ? 0 : Math.max(1, this.remainingFor(key));
    }

    @Protect(value=Level.MAX)
    public final int promptRemaining(@Nullable String key) {
        return Math.max(1, this.remainingFor(key));
    }

    private static final Thread setActive$lambda$0(Runnable r) {
        Thread t = new Thread(r, "kimiko-voice-match");
        t.setDaemon(true);
        return t;
    }

    private static final int submit$lambda$0$0(int v) {
        return Math.max(0, v - 1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void submit$lambda$0(int $mySession, VoiceBindManager this$0, boolean $speculative, long $gen, long $startMs, short[] $pcm, String $key) {
        try {
            if ($mySession == this$0.session && this$0.active) {
                if ($speculative) {
                    this$0.speculateWorker($gen, $startMs, $pcm);
                } else {
                    this$0.process($gen, $key, $startMs, $pcm);
                }
            }
        }
        catch (Throwable throwable) {
        }
        finally {
            if ($key != null) {
                this$0.pendingTakes.updateAndGet(VoiceBindManager::submit$lambda$0$0);
            }
            if ($speculative) {
                this$0.busy.set(false);
            }
        }
    }

    private static final int submit$lambda$1(int v) {
        return Math.max(0, v - 1);
    }

    private static final void trigger$lambda$0(MinecraftClient $mc, Module $target, VoiceBindManager this$0) {
        if ($mc.world != null) {
            if ($target instanceof ClickGui) {
                if ($mc.currentScreen == null) {
                    this$0.markTriggered($target);
                    $mc.setScreen((Screen)UI.INSTANCE);
                    Sounds.play("gui_open");
                    if (this$0.announce) {
                        Notifications.push(I18n.tr("Голос"), I18n.tr("Меню открыто"), 1400L);
                    }
                } else if ($mc.currentScreen == UI.INSTANCE) {
                    this$0.markTriggered($target);
                    UI.INSTANCE.close();
                    if (this$0.announce) {
                        Notifications.push(I18n.tr("Голос"), I18n.tr("Меню закрыто"), 1400L);
                    }
                }
            } else {
                boolean uiOpen;
                boolean bl = uiOpen = $mc.currentScreen == UI.INSTANCE;
                if (!($mc.currentScreen != null && !uiOpen || uiOpen && UI.Companion.isSearchTyping())) {
                    this$0.markTriggered($target);
                    $target.toggle();
                    if (this$0.announce) {
                        Notifications.push(I18n.tr("Голос"), $target.getDisplayName() + ($target.isEnabled() ? I18n.tr(" включён") : I18n.tr(" выключен")), 1400L);
                    }
                }
            }
        }
    }

    private static final void finishRecording$lambda$0(RecordCallback $callback, RecordStatus $status, String $detail, int $remaining) {
        $callback.onResult($status, $detail, $remaining);
    }

    private static final void keepRecording$lambda$0(RecordCallback $callback, RecordStatus $status, String $detail, int $remaining) {
        $callback.onResult($status, $detail, $remaining);
    }

    @JvmStatic
    @NotNull
    public static final String times(int count) {
        return Companion.times(count);
    }

    @JvmStatic
    @NotNull
    public static final String takeHint(int remaining) {
        return Companion.takeHint(remaining);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/voice/VoiceBindManager$BackgroundSample;", "", "", "", "frames", "", "addedMs", "<init>", "([[FJ)V", "[[F", "getFrames", "()[[F", "J", "getAddedMs", "()J", "rtx.kimiko:kimiko"})
    private static final class BackgroundSample {
        @NotNull
        private final float[][] frames;
        private final long addedMs;

        public BackgroundSample(@NotNull float[][] frames, long addedMs) {
            Intrinsics.checkNotNullParameter((Object)frames, (String)"frames");
            this.frames = frames;
            this.addedMs = addedMs;
        }

        @NotNull
        public final float[][] getFrames() {
            return this.frames;
        }

        public final long getAddedMs() {
            return this.addedMs;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0015\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R\u0017\u0010\u0018\u001a\u00020\u00178\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/voice/VoiceBindManager$Candidate;", "", "Lrtx/kimiko/api/modules/Module;", "module", "", "distance", "limit", "background", "<init>", "(Lrtx/kimiko/api/modules/Module;FFF)V", "", "describe", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/Module;", "getModule", "()Lrtx/kimiko/api/modules/Module;", "F", "getDistance", "()F", "getLimit", "getBackground", "score", "getScore", "", "confident", "Z", "getConfident", "()Z", "rtx.kimiko:kimiko"})
    private static final class Candidate {
        @NotNull
        private final Module module;
        private final float distance;
        private final float limit;
        private final float background;
        private final float score;
        private final boolean confident;

        public Candidate(@NotNull Module module, float distance, float limit, float background) {
            Intrinsics.checkNotNullParameter((Object)module, (String)"module");
            this.module = module;
            this.distance = distance;
            this.limit = limit;
            this.background = background;
            this.score = this.distance / this.limit;
            this.confident = this.background >= VoiceMatcher.INF || this.distance <= this.background * 0.82f;
        }

        @NotNull
        public final Module getModule() {
            return this.module;
        }

        public final float getDistance() {
            return this.distance;
        }

        public final float getLimit() {
            return this.limit;
        }

        public final float getBackground() {
            return this.background;
        }

        public final float getScore() {
            return this.score;
        }

        public final boolean getConfident() {
            return this.confident;
        }

        @NotNull
        public final String describe() {
            return this.module.getName() + " " + Companion.format(this.score) + (String)(this.background >= VoiceMatcher.INF ? "" : " фон " + Companion.format(this.background / this.limit)) + (this.confident ? "" : "!");
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\tJ+\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0014\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J;\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b$\u0010%J\u0011\u0010'\u001a\u0004\u0018\u00010&H\u0002\u00a2\u0006\u0004\b'\u0010(R\u0019\u0010*\u001a\u00020\u00048\u0006X\u0087D\u0092\u0002\u0002\b)\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010+R\u0014\u00100\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00103R\u0014\u00106\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00103R\u0014\u00107\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00103R\u0014\u00108\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00103R\u0014\u00109\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u00103R\u0014\u0010:\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00103R\u0014\u0010;\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010+R\u0014\u0010<\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u00101R\u0014\u0010=\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u00101R\u0014\u0010>\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010+R\u0014\u0010?\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010+R\u0014\u0010@\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u00103R\u0014\u0010A\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u00103R\u0014\u0010B\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u00103R\u0014\u0010C\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u00103R\u0014\u0010D\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u00103R\u0014\u0010E\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u00103R\u0014\u0010F\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u00103R\u0014\u0010G\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u00103R\u0014\u0010H\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u00103R\u0014\u0010I\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u00101R\u0014\u0010J\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u00103R\u0014\u0010K\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010+R\u0014\u0010L\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010+R\u0014\u0010M\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010+R\u0014\u0010N\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010+R\u0014\u0010O\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010+R\u0014\u0010P\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u00103R\u0014\u0010Q\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010+R\u0014\u0010R\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u00101R\u0014\u0010S\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u00101R\u0014\u0010T\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u00101R\u0014\u0010U\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u00101R\u0014\u0010V\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u00101R\u0014\u0010W\u001a\u00020/8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u00101R\u0014\u0010X\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010+R\u0019\u0010Z\u001a\u00020Y8\u0006X\u0087\u0004\u0092\u0002\u0002\b)\u00a2\u0006\u0006\n\u0004\bZ\u0010[\u00a8\u0006\\"}, d2={"Lrtx/kimiko/api/voice/VoiceBindManager.Companion;", "", "<init>", "()V", "", "count", "", "Lkotlin/jvm/JvmStatic;", "times", "(I)Ljava/lang/String;", "remaining", "takeHint", "", "", "frames", "length", "slice", "([[FI)[[F", "energy", "from", "tailStart", "([FI)I", "raw", "tailRaw", "normaliseTail", "([[F[[FI)[[F", "Lrtx/kimiko/utils/voice/VoiceTemplate;", "template", "", "limitFor", "(Lrtx/kimiko/utils/voice/VoiceTemplate;)F", "text", "", "report", "(Ljava/lang/String;)V", "value", "format", "(F)Ljava/lang/String;", "Lmods/voicechat/natives/Denoiser;", "createDenoiser", "()Lmods/voicechat/natives/Denoiser;", "Lkotlin/jvm/JvmField;", "TARGET_TAKES", "I", "PREROLL_FRAMES", "ATTACK_FRAMES", "MIN_VOICED_FRAMES", "", "INPUT_MUTE_MS", "J", "MIN_VOICED_RATIO", "F", "MIN_SNR", "DEFAULT_LIMIT", "SPREAD_FACTOR", "SPREAD_MARGIN", "LIMIT_MIN", "LIMIT_MAX", "BACKGROUND_ADD", "BACKGROUND_MAX", "BACKGROUND_TTL_MS", "SILENCE_MS", "SPEC_EVERY", "SPEC_MIN_FRAMES", "SPEC_SCORE", "COHORT_MARGIN", "SURE_SCORE", "WAKE_HARD", "WAKE_REMEMBER", "CMD_HARD", "WAKE_WEIGHT", "CMD_WEIGHT", "ADAPT_SCORE", "ADAPT_COOLDOWN_MS", "ARMED_WAKE_SCORE", "WAKE_MAX_START", "CMD_MAX_START", "TAIL_BACKOFF", "MIN_TAIL_FRAMES", "MIN_TAIL_NORM_FRAMES", "TAIL_TRIM_GATE", "CONFIRMATIONS", "MIN_UTTER_MS", "MAX_UTTER_MS", "ARM_MS", "COOLDOWN_MS", "RECORD_TIMEOUT_MS", "RECORD_SESSION_MS", "MAX_SAMPLES", "Lrtx/kimiko/api/voice/VoiceBindManager;", "INSTANCE", "Lrtx/kimiko/api/voice/VoiceBindManager;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final String times(int count) {
            return count == 1 ? I18n.tr("раз") : I18n.tr("раза");
        }

        @JvmStatic
        @NotNull
        public final String takeHint(int remaining) {
            return switch (remaining) {
                case 4 -> I18n.tr(" — теперь чуть быстрее");
                case 3 -> I18n.tr(" — теперь медленнее");
                case 2 -> I18n.tr(" — теперь тише");
                case 1 -> I18n.tr(" — теперь как обычно");
                default -> "";
            };
        }

        private final float[][] slice(float[][] frames, int length) {
            if (length >= ((Object[])frames).length) {
                return frames;
            }
            return (float[][])ArraysKt.copyOfRange((Object[])((Object[])frames), (int)0, (int)length);
        }

        private final int tailStart(float[] energy, int from) {
            int j;
            int start = Math.max(0, from);
            if (energy == null || start >= energy.length) {
                return start;
            }
            float peak = 0.0f;
            int n = energy.length;
            for (int i = start; i < n; ++i) {
                if (!(energy[i] > peak)) continue;
                peak = energy[i];
            }
            if (peak <= 0.0f) {
                return start;
            }
            float gate = peak * 0.02f;
            for (j = start; j < energy.length - 14 && energy[j] < gate; ++j) {
            }
            return Math.max(start, j - 2);
        }

        private final float[][] normaliseTail(float[][] raw, float[][] tailRaw, int from) {
            if (((Object[])tailRaw).length >= 24) {
                return VoiceDsp.renormalise(tailRaw);
            }
            float[][] full = VoiceDsp.renormalise(raw);
            return VoiceMatcher.tail(full, from);
        }

        private final float limitFor(VoiceTemplate template) {
            if (template == null) {
                return 0.45f;
            }
            float spread = template.spread();
            if (spread <= 0.0f) {
                return 0.45f;
            }
            return Math.max(0.3f, Math.min(0.62f, spread * 1.15f + 0.06f));
        }

        private final void report(String text) {
            MinecraftClient.getInstance().execute(() -> Companion.report$lambda$0(text));
        }

        private final String format(float value) {
            String string;
            if (value >= VoiceMatcher.INF) {
                string = "-";
            } else {
                Locale locale = Locale.ROOT;
                String string2 = "%.2f";
                Object[] objectArray = new Object[]{Float.valueOf(value)};
                String string3 = String.format(locale, string2, Arrays.copyOf(objectArray, objectArray.length));
                string = string3;
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
            }
            return string;
        }

        private final Denoiser createDenoiser() {
            Denoiser denoiser;
            try {
                denoiser = new Denoiser();
            }
            catch (Throwable t) {
                denoiser = null;
            }
            return denoiser;
        }

        private static final void report$lambda$0(String $text) {
            Notifications.push(I18n.tr("Голос"), $text, 2200L);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J)\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&\u00a2\u0006\u0004\b\t\u0010\n\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u000b\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/voice/VoiceBindManager$RecordCallback;", "", "Lrtx/kimiko/api/voice/VoiceBindManager$RecordStatus;", "status", "", "detail", "", "remaining", "", "onResult", "(Lrtx/kimiko/api/voice/VoiceBindManager$RecordStatus;Ljava/lang/String;I)V", "rtx.kimiko:kimiko"})
    public static interface RecordCallback {
        public void onResult(@NotNull RecordStatus var1, @Nullable String var2, int var3);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/voice/VoiceBindManager$RecordStatus;", "", "<init>", "(Ljava/lang/String;I)V", "OK", "TOO_SHORT", "TOO_QUIET", "TIMEOUT", "ERROR", "rtx.kimiko:kimiko"})
    public static enum RecordStatus {
        OK,
        TOO_SHORT,
        TOO_QUIET,
        TIMEOUT,
        ERROR;
@NotNull
        public static EnumEntries<RecordStatus> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

