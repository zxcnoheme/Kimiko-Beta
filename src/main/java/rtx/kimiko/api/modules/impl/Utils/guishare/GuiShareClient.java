/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.math.Vec3d
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareBindPopup;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareChatState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareCloseState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareConfigRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiSharePopupRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareRemoteState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareThemeState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00c2\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0002\u0094\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u00dd\u0002\u00107\u001a\u0002062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\b\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\f2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001e2\u0014\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010&2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\b\u0010+\u001a\u0004\u0018\u00010*2\b\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00102\b\u00101\u001a\u0004\u0018\u0001002\u000e\u00103\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010\u001e2\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u0010\u00a2\u0006\u0004\b7\u00108J\u001d\u0010;\u001a\u0002062\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0014\u00a2\u0006\u0004\b;\u0010<J\u0015\u0010>\u001a\u0002062\u0006\u0010=\u001a\u00020\u0004\u00a2\u0006\u0004\b>\u0010?J\r\u0010@\u001a\u00020\f\u00a2\u0006\u0004\b@\u0010AJ\r\u0010B\u001a\u00020\f\u00a2\u0006\u0004\bB\u0010AJ\u0019\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020C0&\u00a2\u0006\u0004\bD\u0010EJ\r\u0010F\u001a\u000206\u00a2\u0006\u0004\bF\u0010\u0003J\u000f\u0010G\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bG\u0010AJ\u000f\u0010H\u001a\u000206H\u0002\u00a2\u0006\u0004\bH\u0010\u0003J\u0017\u0010J\u001a\u0002062\u0006\u0010I\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bJ\u0010?J\u0019\u0010L\u001a\u0002062\b\u0010K\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\bL\u0010?J\u001f\u0010Q\u001a\u00020P2\u0006\u0010N\u001a\u00020M2\u0006\u0010O\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bQ\u0010RJ\u001f\u0010U\u001a\u0002062\u0006\u0010S\u001a\u00020P2\u0006\u0010T\u001a\u00020PH\u0002\u00a2\u0006\u0004\bU\u0010VJ\u001d\u0010X\u001a\b\u0012\u0004\u0012\u0002020\u001e2\u0006\u0010W\u001a\u00020MH\u0002\u00a2\u0006\u0004\bX\u0010YJ\u0017\u0010Z\u001a\u0002002\u0006\u0010W\u001a\u00020MH\u0002\u00a2\u0006\u0004\bZ\u0010[J\u0017\u0010\\\u001a\u00020*2\u0006\u0010W\u001a\u00020MH\u0002\u00a2\u0006\u0004\b\\\u0010]J\u001d\u0010^\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010W\u001a\u00020MH\u0002\u00a2\u0006\u0004\b^\u0010YJ\u0017\u0010_\u001a\u00020!2\u0006\u0010W\u001a\u00020MH\u0002\u00a2\u0006\u0004\b_\u0010`J#\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040&2\u0006\u0010W\u001a\u00020MH\u0002\u00a2\u0006\u0004\ba\u0010bJ\u001f\u0010c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bc\u0010dJ!\u0010g\u001a\u00020\u00042\b\u0010e\u001a\u0004\u0018\u00010\u00042\u0006\u0010f\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bg\u0010hJ\u001f\u0010j\u001a\u00020\u00042\u0006\u0010i\u001a\u00020M2\u0006\u0010O\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bj\u0010kJ\u001f\u0010l\u001a\u00020\f2\u0006\u0010i\u001a\u00020M2\u0006\u0010O\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bl\u0010mJ'\u0010o\u001a\u00020\b2\u0006\u0010i\u001a\u00020M2\u0006\u0010O\u001a\u00020\u00042\u0006\u0010n\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bo\u0010pR\u0014\u0010r\u001a\u00020q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010sR\u001c\u0010v\u001a\n u*\u0004\u0018\u00010t0t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bv\u0010wR\u0014\u0010y\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0014\u0010{\u001a\u00020x8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0018\u0010~\u001a\u00060|j\u0002`}8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b~\u0010\u007fR$\u0010\u0081\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020C0\u0080\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001c\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0083\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0019\u0010\u0086\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0019\u0010\u0088\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0017\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0005\u0010\u0089\u0001R\u0017\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0006\u0010\u0089\u0001R\u0017\u0010\u0007\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0007\u0010\u0089\u0001R\u0017\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\t\u0010\u008a\u0001R\u0017\u0010\n\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\n\u0010\u008a\u0001R\u0017\u0010\u000b\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u000b\u0010\u008a\u0001R\u0017\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\r\u0010\u0087\u0001R\u0017\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u000f\u0010\u008b\u0001R\u0017\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0011\u0010\u008c\u0001R\u0017\u0010\u0012\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0012\u0010\u008c\u0001R\u0017\u0010\u0013\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0013\u0010\u0089\u0001R\u0017\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0015\u0010\u008d\u0001R\u0017\u0010\u0016\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0016\u0010\u008c\u0001R\u0017\u0010\u0017\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0017\u0010\u008c\u0001R\u0017\u0010\u0018\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0018\u0010\u008c\u0001R\u0017\u0010\u0019\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0019\u0010\u008c\u0001R\u0017\u0010\u001a\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u001a\u0010\u0089\u0001R\u0017\u0010\u001b\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u001b\u0010\u008c\u0001R\u0017\u0010\u001c\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u001c\u0010\u008c\u0001R\u0017\u0010\u001d\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u001d\u0010\u008c\u0001R\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b \u0010\u008e\u0001R\u0017\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\"\u0010\u008f\u0001R\u0017\u0010#\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b#\u0010\u0089\u0001R\u0017\u0010$\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b$\u0010\u0087\u0001R\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b%\u0010\u008e\u0001R#\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b'\u0010\u0090\u0001R\u0017\u0010(\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b(\u0010\u0089\u0001R\u0017\u0010)\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b)\u0010\u0089\u0001R\u0017\u0010+\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b+\u0010\u0091\u0001R\u0017\u0010-\u001a\u00020,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b-\u0010\u0092\u0001R\u0017\u0010.\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b.\u0010\u0089\u0001R\u0017\u0010/\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b/\u0010\u008c\u0001R\u0017\u00101\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b1\u0010\u0093\u0001R\u001d\u00103\u001a\b\u0012\u0004\u0012\u0002020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b3\u0010\u008e\u0001R\u0017\u00104\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b4\u0010\u008c\u0001R\u0017\u00105\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b5\u0010\u008c\u0001\u00a8\u0006\u0095\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareClient;", "", "<init>", "()V", "", "profileUsername", "minecraftUsername", "world", "", "x", "y", "z", "", "open", "Lnet/minecraft/Vec3d;", "anchor", "", "yaw", "pitch", "category", "", "eventsSub", "eventsScroll", "listScroll", "mouseX", "mouseY", "popupModule", "popupScroll", "popupX", "popupY", "", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "popupRows", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "bindPopup", "search", "searchFocused", "enabledModules", "", "moduleBadges", "role", "avatarUrl", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "closeState", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "theme", "selectedTheme", "themesScroll", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "chat", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareConfigRow;", "configs", "configsScroll", "screenHeight", "", "setLocalState", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDZLnet/minecraft/Vec3d;FFLjava/lang/String;IFFFFLjava/lang/String;FFFLjava/util/List;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;Ljava/lang/String;ZLjava/util/List;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;Ljava/lang/String;FLrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;Ljava/util/List;FF)V", "host", "port", "connect", "(Ljava/lang/String;I)V", "reason", "disconnect", "(Ljava/lang/String;)V", "isConnected", "()Z", "isConnecting", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "snapshotRemoteStates", "()Ljava/util/Map;", "pushState", "hasLocalIdentity", "sendHello", "type", "sendPacket", "rawPacket", "handlePacket", "Lcom/google/gson/JsonObject;", "packet", "key", "Lcom/google/gson/JsonArray;", "readArray", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonArray;", "statesArray", "removed", "applyDelta", "(Lcom/google/gson/JsonArray;Lcom/google/gson/JsonArray;)V", "state", "readConfigRows", "(Lcom/google/gson/JsonObject;)Ljava/util/List;", "readChatState", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "readCloseState", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "readPopupRows", "readBindPopup", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "readModuleBadges", "(Lcom/google/gson/JsonObject;)Ljava/util/Map;", "buildIdentityKey", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "value", "maxLength", "normalize", "(Ljava/lang/String;I)Ljava/lang/String;", "objectObj", "readString", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "readBoolean", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Z", "fallback", "readDouble", "(Lcom/google/gson/JsonObject;Ljava/lang/String;D)D", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Ljava/net/http/HttpClient;", "kotlin.jvm.PlatformType", "httpClient", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connected", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connecting", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "packetBuffer", "Ljava/lang/StringBuilder;", "Ljava/util/concurrent/ConcurrentHashMap;", "remoteStates", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/net/http/WebSocket;", "socket", "Ljava/net/http/WebSocket;", "manualClose", "Z", "identityKey", "Ljava/lang/String;", "D", "Lnet/minecraft/Vec3d;", "F", "I", "Ljava/util/List;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "Ljava/util/Map;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "SocketListener", "rtx.kimiko:kimiko"})
public final class GuiShareClient {
    @NotNull
    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(6L)).build();
    @NotNull
    private final AtomicBoolean connected = new AtomicBoolean(false);
    @NotNull
    private final AtomicBoolean connecting = new AtomicBoolean(false);
    @NotNull
    private final StringBuilder packetBuffer = new StringBuilder();
    @NotNull
    private final ConcurrentHashMap<String, GuiShareRemoteState> remoteStates = new ConcurrentHashMap();
    @Nullable
    private volatile WebSocket socket;
    private volatile boolean manualClose;
    @NotNull
    private volatile String identityKey = "";
    @NotNull
    private volatile String profileUsername = "";
    @NotNull
    private volatile String minecraftUsername = "";
    @NotNull
    private volatile String world = "";
    private volatile double x;
    private volatile double y;
    private volatile double z;
    private volatile boolean open;
    @NotNull
    private volatile Vec3d anchor;
    private volatile float yaw;
    private volatile float pitch;
    @NotNull
    private volatile String category;
    private volatile int eventsSub;
    private volatile float eventsScroll;
    private volatile float listScroll;
    private volatile float mouseX;
    private volatile float mouseY;
    @NotNull
    private volatile String popupModule;
    private volatile float popupScroll;
    private volatile float popupX;
    private volatile float popupY;
    @NotNull
    private volatile List<GuiSharePopupRow> popupRows;
    @NotNull
    private volatile GuiShareBindPopup bindPopup;
    @NotNull
    private volatile String search;
    private volatile boolean searchFocused;
    @NotNull
    private volatile List<String> enabledModules;
    @NotNull
    private volatile Map<String, String> moduleBadges;
    @NotNull
    private volatile String role;
    @NotNull
    private volatile String avatarUrl;
    @NotNull
    private volatile GuiShareCloseState closeState;
    @NotNull
    private volatile GuiShareThemeState theme;
    @NotNull
    private volatile String selectedTheme;
    private volatile float themesScroll;
    @NotNull
    private volatile GuiShareChatState chat;
    @NotNull
    private volatile List<GuiShareConfigRow> configs;
    private volatile float configsScroll;
    private volatile float screenHeight;

    public GuiShareClient() {
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.anchor = vec3d2;
        this.category = "";
        this.popupModule = "";
        this.popupRows = CollectionsKt.emptyList();
        this.bindPopup = GuiShareBindPopup.HIDDEN;
        this.search = "";
        this.enabledModules = CollectionsKt.emptyList();
        this.moduleBadges = MapsKt.emptyMap();
        this.role = "";
        this.avatarUrl = "";
        this.closeState = GuiShareCloseState.IDLE;
        this.theme = GuiShareThemeState.DEFAULTS;
        this.selectedTheme = "";
        this.chat = GuiShareChatState.HIDDEN;
        this.configs = CollectionsKt.emptyList();
    }

    public final void setLocalState(@NotNull String profileUsername, @NotNull String minecraftUsername, @NotNull String world, double x, double y, double z, boolean open, @Nullable Vec3d anchor, float yaw, float pitch, @NotNull String category, int eventsSub, float eventsScroll, float listScroll, float mouseX, float mouseY, @NotNull String popupModule, float popupScroll, float popupX, float popupY, @Nullable List<GuiSharePopupRow> popupRows, @Nullable GuiShareBindPopup bindPopup, @NotNull String search, boolean searchFocused, @Nullable List<String> enabledModules, @Nullable Map<String, String> moduleBadges, @NotNull String role, @NotNull String avatarUrl, @Nullable GuiShareCloseState closeState, @Nullable GuiShareThemeState theme, @NotNull String selectedTheme, float themesScroll, @Nullable GuiShareChatState chat, @Nullable List<GuiShareConfigRow> configs, float configsScroll, float screenHeight) {
        List list;
        GuiShareThemeState guiShareThemeState;
        GuiShareBindPopup guiShareBindPopup;
        Intrinsics.checkNotNullParameter((Object)profileUsername, (String)"profileUsername");
        Intrinsics.checkNotNullParameter((Object)minecraftUsername, (String)"minecraftUsername");
        Intrinsics.checkNotNullParameter((Object)world, (String)"world");
        Intrinsics.checkNotNullParameter((Object)category, (String)"category");
        Intrinsics.checkNotNullParameter((Object)popupModule, (String)"popupModule");
        Intrinsics.checkNotNullParameter((Object)search, (String)"search");
        Intrinsics.checkNotNullParameter((Object)role, (String)"role");
        Intrinsics.checkNotNullParameter((Object)avatarUrl, (String)"avatarUrl");
        Intrinsics.checkNotNullParameter((Object)selectedTheme, (String)"selectedTheme");
        this.profileUsername = this.normalize(profileUsername, 48);
        this.minecraftUsername = this.normalize(minecraftUsername, 32);
        this.identityKey = this.buildIdentityKey(this.profileUsername, this.minecraftUsername);
        this.world = this.normalize(world, 64);
        this.x = x;
        this.y = y;
        this.z = z;
        this.open = open;
        Vec3d vec3d2 = anchor;
        if (vec3d2 == null) {
            Vec3d vec3d3 = Vec3d.ZERO;
            vec3d2 = vec3d3;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        }
        this.anchor = vec3d2;
        this.yaw = yaw;
        this.pitch = pitch;
        this.category = this.normalize(category, 32);
        this.eventsSub = eventsSub;
        this.eventsScroll = eventsScroll;
        this.listScroll = listScroll;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.popupModule = this.normalize(popupModule, 32);
        this.popupScroll = popupScroll;
        this.popupX = popupX;
        this.popupY = popupY;
        List<GuiSharePopupRow> list2 = popupRows;
        if (list2 == null) {
            list2 = this.popupRows = CollectionsKt.emptyList();
        }
        if ((guiShareBindPopup = bindPopup) == null) {
            guiShareBindPopup = GuiShareBindPopup.HIDDEN;
        }
        this.bindPopup = guiShareBindPopup;
        this.search = this.normalize(search, 32);
        this.searchFocused = searchFocused;
        List list3 = enabledModules;
        if (list3 == null) {
            list3 = CollectionsKt.emptyList();
        }
        this.enabledModules = list3;
        this.moduleBadges = moduleBadges == null ? MapsKt.emptyMap() : (Map)new HashMap<String, String>(moduleBadges);
        this.role = this.normalize(role, 24);
        this.avatarUrl = this.normalize(avatarUrl, 160);
        GuiShareCloseState guiShareCloseState = closeState;
        if (guiShareCloseState == null) {
            guiShareCloseState = this.closeState = GuiShareCloseState.IDLE;
        }
        if ((guiShareThemeState = theme) == null) {
            guiShareThemeState = GuiShareThemeState.DEFAULTS;
        }
        this.theme = guiShareThemeState;
        this.selectedTheme = this.normalize(selectedTheme, 32);
        this.themesScroll = themesScroll;
        GuiShareChatState guiShareChatState = chat;
        if (guiShareChatState == null) {
            guiShareChatState = this.chat = GuiShareChatState.HIDDEN;
        }
        if ((list = configs) == null) {
            list = CollectionsKt.emptyList();
        }
        this.configs = list;
        this.configsScroll = configsScroll;
        this.screenHeight = screenHeight;
    }

    public final synchronized void connect(@NotNull String string, int n) {
    }

    public final synchronized void disconnect(@NotNull String reason) {
        Intrinsics.checkNotNullParameter((Object)reason, (String)"reason");
        this.manualClose = true;
        this.connecting.set(false);
        this.connected.set(false);
        this.remoteStates.clear();
        WebSocket current = this.socket;
        this.socket = null;
        if (current != null) {
            try {
                current.sendClose(1000, this.normalize(reason, 120));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public final boolean isConnected() {
        return this.connected.get();
    }

    public final boolean isConnecting() {
        return this.connecting.get();
    }

    @NotNull
    public final Map<String, GuiShareRemoteState> snapshotRemoteStates() {
        return new HashMap(this.remoteStates);
    }

    public final void pushState() {
        this.sendPacket("state");
    }

    private final boolean hasLocalIdentity() {
        return !StringsKt.isBlank((CharSequence)this.profileUsername) && !StringsKt.isBlank((CharSequence)this.minecraftUsername) && !StringsKt.isBlank((CharSequence)this.identityKey);
    }

    private final void sendHello() {
        this.sendPacket("hello");
    }

    private final void sendPacket(String type) {
        WebSocket current = this.socket;
        if (!this.connected.get() || current == null || !this.hasLocalIdentity()) {
            return;
        }
        JsonObject packet = new JsonObject();
        packet.addProperty("t", Intrinsics.areEqual((Object)"hello", (Object)type) ? "h" : "u");
        packet.addProperty("i", this.identityKey);
        packet.addProperty("p", this.profileUsername);
        packet.addProperty("n", this.minecraftUsername);
        packet.addProperty("w", this.world);
        packet.addProperty("x", (Number)this.x);
        packet.addProperty("y", (Number)this.y);
        packet.addProperty("z", (Number)this.z);
        packet.addProperty("o", Boolean.valueOf(this.open));
        packet.addProperty("ax", (Number)this.anchor.x);
        packet.addProperty("ay", (Number)this.anchor.y);
        packet.addProperty("az", (Number)this.anchor.z);
        packet.addProperty("ry", (Number)Float.valueOf(this.yaw));
        packet.addProperty("rp", (Number)Float.valueOf(this.pitch));
        packet.addProperty("c", this.category);
        packet.addProperty("es", (Number)this.eventsSub);
        packet.addProperty("ec", (Number)Float.valueOf(this.eventsScroll));
        packet.addProperty("sc", (Number)Float.valueOf(this.listScroll));
        packet.addProperty("mx", (Number)Float.valueOf(this.mouseX));
        packet.addProperty("my", (Number)Float.valueOf(this.mouseY));
        packet.addProperty("pm", this.popupModule);
        packet.addProperty("ps", (Number)Float.valueOf(this.popupScroll));
        packet.addProperty("px", (Number)Float.valueOf(this.popupX));
        packet.addProperty("py", (Number)Float.valueOf(this.popupY));
        List<GuiSharePopupRow> rows = this.popupRows;
        if (!((Collection)rows).isEmpty()) {
            JsonArray rowsArray = new JsonArray();
            int limit = Math.min(rows.size(), 28);
            for (int i = 0; i < limit; ++i) {
                GuiSharePopupRow row = rows.get(i);
                JsonObject entry = new JsonObject();
                entry.addProperty("t", row.type);
                entry.addProperty("n", row.name);
                entry.addProperty("v", row.value);
                entry.addProperty("f", (Number)Float.valueOf(row.fraction));
                entry.addProperty("r", (Number)row.rgb);
                if (row.open) {
                    entry.addProperty("o", (Number)1);
                }
                if (row.alpha != 0) {
                    entry.addProperty("a", (Number)row.alpha);
                }
                if (!((Collection)row.options).isEmpty()) {
                    JsonArray jsonArray = new JsonArray();
                    for (String option : row.options) {
                        jsonArray.add(option);
                    }
                    entry.add("op", (JsonElement)jsonArray);
                    entry.addProperty("sm", (Number)row.selectedMask);
                }
                rowsArray.add((JsonElement)entry);
            }
            packet.add("pr", (JsonElement)rowsArray);
        }
        GuiShareBindPopup bind = this.bindPopup;
        if (bind.visible) {
            packet.addProperty("bo", (Number)1);
            packet.addProperty("bk", bind.key);
            packet.addProperty("ba", bind.action);
            packet.addProperty("bt", Boolean.valueOf(bind.voice));
            packet.addProperty("bh", Boolean.valueOf(bind.hold));
            packet.addProperty("bl", Boolean.valueOf(bind.listening));
            packet.addProperty("bx", (Number)Float.valueOf(bind.x));
            packet.addProperty("by", (Number)Float.valueOf(bind.y));
        }
        GuiShareCloseState close = this.closeState;
        if (close.active) {
            packet.addProperty("ka", (Number)1);
            packet.addProperty("kv", (Number)Float.valueOf(close.openness));
            packet.addProperty("kp", (Number)Float.valueOf(close.shatterProgress));
            packet.addProperty("kc", (Number)Float.valueOf(close.screenScale));
            packet.addProperty("kd", String.valueOf(close.seed));
            JsonArray rect = new JsonArray();
            rect.add((Number)Float.valueOf(close.rectX));
            rect.add((Number)Float.valueOf(close.rectY));
            rect.add((Number)Float.valueOf(close.rectW));
            rect.add((Number)Float.valueOf(close.rectH));
            rect.add((Number)Float.valueOf(close.rectPad));
            rect.add((Number)Float.valueOf(close.screenW));
            rect.add((Number)Float.valueOf(close.screenH));
            packet.add("kr", (JsonElement)rect);
        }
        packet.addProperty("q", this.search);
        packet.addProperty("qf", Boolean.valueOf(this.searchFocused));
        JsonObject badges = new JsonObject();
        int badgeCount = 0;
        for (Map.Entry entry : this.moduleBadges.entrySet()) {
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            String module = this.normalize(key, 32);
            String badge = this.normalize(value, 24);
            if (!(!StringsKt.isBlank((CharSequence)module)) || !(!StringsKt.isBlank((CharSequence)badge))) continue;
            badges.addProperty(module, badge);
            if (++badgeCount < 80) continue;
        }
        if (badgeCount > 0) {
            packet.add("mb", (JsonElement)badges);
        }
        packet.addProperty("ro", this.role);
        packet.addProperty("av", this.avatarUrl);
        packet.addProperty("th", this.selectedTheme);
        packet.addProperty("hs", (Number)Float.valueOf(this.themesScroll));
        packet.addProperty("sh", (Number)Float.valueOf(this.screenHeight));
        List<GuiShareConfigRow> configRows = this.configs;
        if (!((Collection)configRows).isEmpty()) {
            JsonArray jsonArray = new JsonArray();
            int written = 0;
            for (GuiShareConfigRow row : configRows) {
                String name;
                if (row == null || StringsKt.isBlank((CharSequence)(name = this.normalize(row.name, 32)))) continue;
                JsonObject entry = new JsonObject();
                entry.addProperty("n", name);
                entry.addProperty("s", this.normalize(row.subtitle, 48));
                if (row.kind != 0) {
                    entry.addProperty("k", (Number)row.kind);
                }
                if (row.premium()) {
                    entry.addProperty("p", (Number)1);
                }
                if (row.cloud) {
                    entry.addProperty("c", (Number)1);
                }
                if (row.active) {
                    entry.addProperty("a", (Number)1);
                }
                jsonArray.add((JsonElement)entry);
                if (++written < 24) continue;
            }
            if (written > 0) {
                packet.add("cl", (JsonElement)jsonArray);
                packet.addProperty("cy", (Number)Float.valueOf(this.configsScroll));
            }
        }
        GuiShareChatState guiShareChatState = this.chat;
        if (guiShareChatState.messengerOpen) {
            packet.addProperty("mg", (Number)1);
            packet.addProperty("mv", (Number)Float.valueOf(guiShareChatState.messengerScroll));
            packet.addProperty("ms", (Number)Math.max(0, Math.min(99, guiShareChatState.selectedCount)));
        }
        if (guiShareChatState.headerModalOpen()) {
            packet.addProperty("hm", (Number)guiShareChatState.headerModal);
        }
        this.theme.writeTo(packet);
        JsonArray enabled = new JsonArray();
        for (String module : this.enabledModules) {
            if (!(!StringsKt.isBlank((CharSequence)module))) continue;
            enabled.add(module);
        }
        packet.add("en", (JsonElement)enabled);
        current.sendText(this.gson.toJson((JsonElement)packet), true);
    }

    private final void handlePacket(String rawPacket) {
        CharSequence charSequence = rawPacket;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return;
        }
        try {
            JsonObject packet = JsonParser.parseString((String)rawPacket).getAsJsonObject();
            Intrinsics.checkNotNull((Object)packet);
            String type = this.readString(packet, "t");
            if (StringsKt.equals((String)"s", (String)type, (boolean)true)) {
                this.remoteStates.clear();
                this.applyDelta(this.readArray(packet, "p"), new JsonArray());
            } else if (StringsKt.equals((String)"d", (String)type, (boolean)true)) {
                this.applyDelta(this.readArray(packet, "p"), this.readArray(packet, "r"));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final JsonArray readArray(JsonObject packet, String key) {
        if (packet.has(key) && packet.get(key).isJsonArray()) {
            JsonArray jsonArray = packet.getAsJsonArray(key);
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            return jsonArray;
        }
        return new JsonArray();
    }

    private final void applyDelta(JsonArray statesArray, JsonArray removed) {
        JsonElement element;
        Iterator iterator = statesArray.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            element = (JsonElement)iterator2.next();
            if (!element.isJsonObject()) continue;
            JsonObject state = element.getAsJsonObject();
            Intrinsics.checkNotNull((Object)state);
            String profileUsername = this.normalize(this.readString(state, "p"), 48);
            String minecraftUsername = this.normalize(this.readString(state, "n"), 32);
            String identityKey = this.normalize(this.readString(state, "i"), 96);
            if (StringsKt.isBlank((CharSequence)identityKey)) {
                identityKey = this.buildIdentityKey(profileUsername, minecraftUsername);
            }
            if (StringsKt.isBlank((CharSequence)identityKey) || StringsKt.isBlank((CharSequence)profileUsername) || StringsKt.isBlank((CharSequence)minecraftUsername)) continue;
            List<GuiSharePopupRow> popupRows = this.readPopupRows(state);
            GuiShareBindPopup bindPopup = this.readBindPopup(state);
            GuiShareCloseState closeState = this.readCloseState(state);
            Map<String, String> moduleBadges = this.readModuleBadges(state);
            ArrayList<String> enabled = new ArrayList<String>();
            if (state.has("en") && state.get("en").isJsonArray()) {
                Iterator iterator3 = (Iterator) (state.getAsJsonArray("en").iterator());
                while (iterator3.hasNext()) {
                    String value;
                    JsonElement moduleName = (JsonElement)iterator3.next();
                    if (!moduleName.isJsonPrimitive() || !(!StringsKt.isBlank((CharSequence)(value = this.normalize(moduleName.getAsString(), 32))))) continue;
                    enabled.add(value);
                }
            }
            ((Map)this.remoteStates).put(identityKey, new GuiShareRemoteState(identityKey, profileUsername, minecraftUsername, this.normalize(this.readString(state, "w"), 64), this.readDouble(state, "x", 0.0), this.readDouble(state, "y", 0.0), this.readDouble(state, "z", 0.0), this.readBoolean(state, "o"), new Vec3d(this.readDouble(state, "ax", 0.0), this.readDouble(state, "ay", 0.0), this.readDouble(state, "az", 0.0)), (float)this.readDouble(state, "ry", 0.0), (float)this.readDouble(state, "rp", 0.0), this.normalize(this.readString(state, "c"), 32), Math.max(0, Math.min(1, (int)this.readDouble(state, "es", 0.0))), (float)this.readDouble(state, "ec", 0.0), (float)this.readDouble(state, "sc", 0.0), (float)this.readDouble(state, "mx", 0.0), (float)this.readDouble(state, "my", 0.0), this.normalize(this.readString(state, "pm"), 32), (float)this.readDouble(state, "ps", 0.0), (float)this.readDouble(state, "px", 0.0), (float)this.readDouble(state, "py", 0.0), popupRows, bindPopup, this.normalize(this.readString(state, "q"), 32), this.readBoolean(state, "qf"), (List<String>)enabled, moduleBadges, this.normalize(this.readString(state, "ro"), 24), this.normalize(this.readString(state, "av"), 160), closeState, GuiShareThemeState.Companion.fromJson(state), this.normalize(this.readString(state, "th"), 32), (float)this.readDouble(state, "hs", 0.0), this.readChatState(state), this.readConfigRows(state), (float)this.readDouble(state, "cy", 0.0), (float)this.readDouble(state, "sh", 0.0)));
        }
        Iterator iterator4 = removed.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator4, (String)"iterator(...)");
        iterator2 = iterator4;
        while (iterator2.hasNext()) {
            String identityKey;
            element = (JsonElement)iterator2.next();
            if (!element.isJsonPrimitive() || !(!StringsKt.isBlank((CharSequence)(identityKey = this.normalize(element.getAsString(), 96))))) continue;
            this.remoteStates.remove(identityKey);
        }
    }

    private final List<GuiShareConfigRow> readConfigRows(JsonObject state) {
        if (!state.has("cl") || !state.get("cl").isJsonArray()) {
            return CollectionsKt.emptyList();
        }
        ArrayList<GuiShareConfigRow> out = new ArrayList<GuiShareConfigRow>();
        Iterator iterator = state.getAsJsonArray("cl").iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonElement element = (JsonElement)iterator2.next();
            if (!element.isJsonObject()) continue;
            JsonObject entry = element.getAsJsonObject();
            Intrinsics.checkNotNull((Object)entry);
            String name = this.normalize(this.readString(entry, "n"), 32);
            if (StringsKt.isBlank((CharSequence)name)) continue;
            boolean premium = this.readDouble(entry, "p", 0.0) > 0.5;
            int kind = GuiShareConfigRow.Companion.clampKind((int)this.readDouble(entry, "k", premium ? 2 : 0));
            out.add(new GuiShareConfigRow(name, this.normalize(this.readString(entry, "s"), 48), kind, this.readDouble(entry, "c", 0.0) > 0.5, this.readDouble(entry, "a", 0.0) > 0.5));
            if (out.size() < 24) continue;
            break;
        }
        return out;
    }

    private final GuiShareChatState readChatState(JsonObject state) {
        int headerModal;
        boolean messengerOpen = this.readDouble(state, "mg", 0.0) > 0.5;
        int n = headerModal = state.has("hm") ? (int)this.readDouble(state, "hm", -1.0) : -1;
        if (headerModal < 0 || headerModal > 1) {
            headerModal = -1;
        }
        if (!messengerOpen && headerModal < 0) {
            return GuiShareChatState.HIDDEN;
        }
        return new GuiShareChatState(messengerOpen, (float)this.readDouble(state, "mv", 0.0), headerModal, Math.max(0, Math.min(99, (int)this.readDouble(state, "ms", 0.0))));
    }

    private final GuiShareCloseState readCloseState(JsonObject state) {
        if (this.readDouble(state, "ka", 0.0) <= 0.5) {
            return GuiShareCloseState.IDLE;
        }
        float[] rect = new float[7];
        if (state.has("kr") && state.get("kr").isJsonArray()) {
            JsonArray array = state.getAsJsonArray("kr");
            int n = rect.length;
            for (int i = 0; i < n; ++i) {
                if (i >= array.size()) continue;
                try {
                    rect[i] = array.get(i).getAsFloat();
                    continue;
                }
                catch (RuntimeException runtimeException) {
                    // empty catch block
                }
            }
        }
        long seed = 0L;
        try {
            seed = Long.parseLong(this.readString(state, "kd"));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
        return new GuiShareCloseState(true, (float)this.readDouble(state, "kv", 1.0), (float)this.readDouble(state, "kp", 0.0), (float)this.readDouble(state, "kc", 1.0), seed, rect[0], rect[1], rect[2], rect[3], rect[4], rect[5], rect[6]);
    }

    private final List<GuiSharePopupRow> readPopupRows(JsonObject state) {
        if (!state.has("pr") || !state.get("pr").isJsonArray()) {
            return CollectionsKt.emptyList();
        }
        ArrayList<GuiSharePopupRow> rows = new ArrayList<GuiSharePopupRow>();
        Iterator iterator = state.getAsJsonArray("pr").iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonElement element = (JsonElement)iterator2.next();
            if (rows.size() >= 28) break;
            if (!element.isJsonObject()) continue;
            JsonObject row = element.getAsJsonObject();
            Intrinsics.checkNotNull((Object)row);
            String type = this.normalize(this.readString(row, "t"), 1);
            if (((CharSequence)type).length() == 0) continue;
            float fraction = (float)this.readDouble(row, "f", 0.0);
            fraction = fraction < 0.0f ? 0.0f : Math.min(fraction, 1.0f);
            int rgb = (int)this.readDouble(row, "r", 0.0);
            rgb = Math.max(0, Math.min(0xFFFFFF, rgb));
            int alpha = (int)this.readDouble(row, "a", 0.0);
            alpha = Math.max(0, Math.min(255, alpha));
            boolean open = this.readDouble(row, "o", 0.0) > 0.5;
            List options = CollectionsKt.emptyList();
            int selectedMask = 0;
            if (row.has("op") && row.get("op").isJsonArray()) {
                ArrayList<String> parsed = new ArrayList<String>();
                for (JsonElement option : row.getAsJsonArray("op")) {
                    if (parsed.size() >= 24) break;
                    if (!option.isJsonPrimitive()) continue;
                    parsed.add(this.normalize(option.getAsString(), 24));
                }
                options = parsed;
                selectedMask = (int)this.readDouble(row, "sm", 0.0);
            }
            rows.add(new GuiSharePopupRow(type, this.normalize(this.readString(row, "n"), 32), this.normalize(this.readString(row, "v"), 24), fraction, rgb, open, options, selectedMask, alpha));
        }
        return rows;
    }

    private final GuiShareBindPopup readBindPopup(JsonObject state) {
        if (!this.readBoolean(state, "bo")) {
            return GuiShareBindPopup.HIDDEN;
        }
        return new GuiShareBindPopup(true, this.normalize(this.readString(state, "bk"), 24), this.normalize(this.readString(state, "ba"), 24), this.readBoolean(state, "bt"), this.readBoolean(state, "bh"), this.readBoolean(state, "bl"), (float)this.readDouble(state, "bx", 0.0), (float)this.readDouble(state, "by", 0.0));
    }

    private final Map<String, String> readModuleBadges(JsonObject state) {
        if (!state.has("mb") || !state.get("mb").isJsonObject()) {
            return MapsKt.emptyMap();
        }
        HashMap badges = new HashMap();
        for (Map.Entry entry : state.getAsJsonObject("mb").entrySet()) {
            Intrinsics.checkNotNull((Object)entry);
            String key = (String)entry.getKey();
            JsonElement value = (JsonElement)entry.getValue();
            if (badges.size() >= 80 || !value.isJsonPrimitive()) continue;
            String module = this.normalize(key, 32);
            String badge = this.normalize(value.getAsString(), 24);
            if (!(!StringsKt.isBlank((CharSequence)module)) || !(!StringsKt.isBlank((CharSequence)badge))) continue;
            ((Map)badges).put(module, badge);
        }
        return badges;
    }

    private final String buildIdentityKey(String profileUsername, String minecraftUsername) {
        if (StringsKt.isBlank((CharSequence)profileUsername) || StringsKt.isBlank((CharSequence)minecraftUsername)) {
            return "";
        }
        return profileUsername + "|" + minecraftUsername;
    }

    private final String normalize(String value, int maxLength) {
        if (value == null) {
            return "";
        }
        String normalized = ((Object)StringsKt.trim((CharSequence)value)).toString();
        if (normalized.length() > maxLength) {
            String string = normalized.substring(0, maxLength);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            return string;
        }
        return normalized;
    }

    private final String readString(JsonObject objectObj, String key) {
        if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
            String string = objectObj.get(key).getAsString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
            return string;
        }
        return "";
    }

    private final boolean readBoolean(JsonObject objectObj, String key) {
        if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
            try {
                return objectObj.get(key).getAsBoolean();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return false;
    }

    private final double readDouble(JsonObject objectObj, String key, double fallback) {
        if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
            try {
                return objectObj.get(key).getAsDouble();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return fallback;
    }

    private static final Unit connect$lambda$0(GuiShareClient this$0, WebSocket webSocket, Throwable throwable) {
        if (throwable != null) {
            this$0.connecting.set(false);
            this$0.connected.set(false);
            this$0.socket = null;
            this$0.remoteStates.clear();
            return Unit.INSTANCE;
        }
        this$0.socket = webSocket;
        return Unit.INSTANCE;
    }

    private static final void connect$lambda$1(Function2 $tmp0, Object p0, Object p1) {
        $tmp0.invoke(p0, p1);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareClient$SocketListener;", "Ljava/net/http/WebSocket$Listener;", "<init>", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareClient;)V", "Ljava/net/http/WebSocket;", "webSocket", "", "onOpen", "(Ljava/net/http/WebSocket;)V", "", "data", "", "last", "Ljava/util/concurrent/CompletionStage;", "onText", "(Ljava/net/http/WebSocket;Ljava/lang/CharSequence;Z)Ljava/util/concurrent/CompletionStage;", "", "statusCode", "", "reason", "onClose", "(Ljava/net/http/WebSocket;ILjava/lang/String;)Ljava/util/concurrent/CompletionStage;", "", "error", "onError", "(Ljava/net/http/WebSocket;Ljava/lang/Throwable;)V", "rtx.kimiko:kimiko"})
    private final class SocketListener
    implements WebSocket.Listener {
        @Override
        public void onOpen(@NotNull WebSocket webSocket) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            GuiShareClient.this.socket = webSocket;
            GuiShareClient.this.connecting.set(false);
            GuiShareClient.this.connected.set(true);
            webSocket.request(1L);
            GuiShareClient.this.sendHello();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Nullable
        public CompletionStage<?> onText(@NotNull WebSocket webSocket, @NotNull CharSequence data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            StringBuilder stringBuilder = GuiShareClient.this.packetBuffer;
            GuiShareClient guiShareClient = GuiShareClient.this;
            StringBuilder stringBuilder2 = stringBuilder;
            synchronized (stringBuilder2) {
                boolean bl = false;
                guiShareClient.packetBuffer.append(data);
                if (last) {
                    String string = guiShareClient.packetBuffer.toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    String packet = string;
                    guiShareClient.packetBuffer.setLength(0);
                    guiShareClient.handlePacket(packet);
                }
                Unit unit = Unit.INSTANCE;
            }
            webSocket.request(1L);
            return null;
        }

        @Override
        @Nullable
        public CompletionStage<?> onClose(@NotNull WebSocket webSocket, int statusCode, @NotNull String reason) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)reason, (String)"reason");
            GuiShareClient.this.connecting.set(false);
            GuiShareClient.this.connected.set(false);
            GuiShareClient.this.socket = null;
            GuiShareClient.this.remoteStates.clear();
            return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
        }

        @Override
        public void onError(@NotNull WebSocket webSocket, @NotNull Throwable error) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            GuiShareClient.this.connecting.set(false);
            GuiShareClient.this.connected.set(false);
            GuiShareClient.this.socket = null;
            if (!GuiShareClient.this.manualClose) {
                GuiShareClient.this.remoteStates.clear();
            }
        }
    }
}

