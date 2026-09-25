/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareBindPopup;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareChatState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareCloseState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareConfigRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiSharePopupRow;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareThemeState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b8\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B\u00cd\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u000f\u0012\u0006\u0010\u0016\u001a\u00020\u000f\u0012\u0006\u0010\u0017\u001a\u00020\u000f\u0012\u0006\u0010\u0018\u001a\u00020\u000f\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u000f\u0012\u0006\u0010\u001b\u001a\u00020\u000f\u0012\u0006\u0010\u001c\u001a\u00020\u000f\u0012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010\"\u001a\u00020\u0002\u0012\u0006\u0010#\u001a\u00020\u000b\u0012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d\u0012\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020%\u0012\u0006\u0010'\u001a\u00020\u0002\u0012\u0006\u0010(\u001a\u00020\u0002\u0012\u0006\u0010*\u001a\u00020)\u0012\u0006\u0010,\u001a\u00020+\u0012\u0006\u0010-\u001a\u00020\u0002\u0012\u0006\u0010.\u001a\u00020\u000f\u0012\u0006\u00100\u001a\u00020/\u0012\f\u00102\u001a\b\u0012\u0004\u0012\u0002010\u001d\u0012\u0006\u00103\u001a\u00020\u000f\u0012\u0006\u00104\u001a\u00020\u000f\u00a2\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b7\u00108J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u00108J\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u00108J\u0010\u0010;\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b;\u00108J\u0010\u0010<\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b<\u0010=J\u0010\u0010>\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b>\u0010=J\u0010\u0010?\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b?\u0010=J\u0010\u0010@\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b@\u0010AJ\u0010\u0010B\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\bB\u0010CJ\u0010\u0010D\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bD\u0010EJ\u0010\u0010F\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bF\u0010EJ\u0010\u0010G\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bG\u00108J\u0010\u0010H\u001a\u00020\u0013H\u00c6\u0003\u00a2\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bJ\u0010EJ\u0010\u0010K\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bK\u0010EJ\u0010\u0010L\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bL\u0010EJ\u0010\u0010M\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bM\u0010EJ\u0010\u0010N\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bN\u00108J\u0010\u0010O\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bO\u0010EJ\u0010\u0010P\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bP\u0010EJ\u0010\u0010Q\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bQ\u0010EJ\u0016\u0010R\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u00c6\u0003\u00a2\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020 H\u00c6\u0003\u00a2\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bV\u00108J\u0010\u0010W\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bW\u0010AJ\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00020\u001dH\u00c6\u0003\u00a2\u0006\u0004\bX\u0010SJ\u001c\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020%H\u00c6\u0003\u00a2\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b[\u00108J\u0010\u0010\\\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\\\u00108J\u0010\u0010]\u001a\u00020)H\u00c6\u0003\u00a2\u0006\u0004\b]\u0010^J\u0010\u0010_\u001a\u00020+H\u00c6\u0003\u00a2\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\ba\u00108J\u0010\u0010b\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bb\u0010EJ\u0010\u0010c\u001a\u00020/H\u00c6\u0003\u00a2\u0006\u0004\bc\u0010dJ\u0016\u0010e\u001a\b\u0012\u0004\u0012\u0002010\u001dH\u00c6\u0003\u00a2\u0006\u0004\be\u0010SJ\u0010\u0010f\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bf\u0010EJ\u0010\u0010g\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\bg\u0010EJ\u00a0\u0003\u0010h\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f2\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020\u00022\b\b\u0002\u0010#\u001a\u00020\u000b2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d2\u0014\b\u0002\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020%2\b\b\u0002\u0010'\u001a\u00020\u00022\b\b\u0002\u0010(\u001a\u00020\u00022\b\b\u0002\u0010*\u001a\u00020)2\b\b\u0002\u0010,\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020\u00022\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020/2\u000e\b\u0002\u00102\u001a\b\u0012\u0004\u0012\u0002010\u001d2\b\b\u0002\u00103\u001a\u00020\u000f2\b\b\u0002\u00104\u001a\u00020\u000fH\u00c6\u0001\u00a2\u0006\u0004\bh\u0010iJ\u001b\u0010l\u001a\u00020\u000b2\b\u0010k\u001a\u0004\u0018\u00010jH\u00d6\u0083\u0004\u00a2\u0006\u0004\bl\u0010mJ\u0011\u0010n\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\bn\u0010IJ\u0011\u0010o\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\bo\u00108R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0003\u0010qR\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0004\u0010qR\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0005\u0010qR\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0006\u0010qR\u0019\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\b\u0010rR\u0019\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\t\u0010rR\u0019\u0010\n\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\n\u0010rR\u0019\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\f\u0010sR\u0019\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u000e\u0010tR\u0019\u0010\u0010\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0010\u0010uR\u0019\u0010\u0011\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0011\u0010uR\u0019\u0010\u0012\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0012\u0010qR\u0019\u0010\u0014\u001a\u00020\u00138\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0014\u0010vR\u0019\u0010\u0015\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0015\u0010uR\u0019\u0010\u0016\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0016\u0010uR\u0019\u0010\u0017\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0017\u0010uR\u0019\u0010\u0018\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0018\u0010uR\u0019\u0010\u0019\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u0019\u0010qR\u0019\u0010\u001a\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u001a\u0010uR\u0019\u0010\u001b\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u001b\u0010uR\u0019\u0010\u001c\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u001c\u0010uR\u001f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\u001f\u0010wR\u0019\u0010!\u001a\u00020 8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b!\u0010xR\u0019\u0010\"\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b\"\u0010qR\u0019\u0010#\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b#\u0010sR\u001f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b$\u0010wR%\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020%8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b&\u0010yR\u0019\u0010'\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b'\u0010qR\u0019\u0010(\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b(\u0010qR\u0019\u0010*\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b*\u0010zR\u0019\u0010,\u001a\u00020+8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b,\u0010{R\u0019\u0010-\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b-\u0010qR\u0019\u0010.\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b.\u0010uR\u0019\u00100\u001a\u00020/8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b0\u0010|R\u001f\u00102\u001a\b\u0012\u0004\u0012\u0002010\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b2\u0010wR\u0019\u00103\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b3\u0010uR\u0019\u00104\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\bp\u00a2\u0006\u0006\n\u0004\b4\u0010u\u00a8\u0006}"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "Ljava/lang/Record;", "", "identityKey", "profileUsername", "minecraftUsername", "world", "", "x", "y", "z", "", "open", "Lnet/minecraft/Vec3d;", "anchor", "", "yaw", "pitch", "category", "", "eventsSub", "eventsScroll", "listScroll", "mouseX", "mouseY", "popupModule", "popupScroll", "popupX", "popupY", "", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "popupRows", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "bindPopup", "search", "searchFocused", "enabledModules", "", "moduleBadges", "role", "avatarUrl", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "closeState", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "theme", "selectedTheme", "themesScroll", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "chat", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareConfigRow;", "configs", "configsScroll", "screenHeight", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDZLnet/minecraft/Vec3d;FFLjava/lang/String;IFFFFLjava/lang/String;FFFLjava/util/List;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;Ljava/lang/String;ZLjava/util/List;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;Ljava/lang/String;FLrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;Ljava/util/List;FF)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "()D", "component6", "component7", "component8", "()Z", "component9", "()Lnet/minecraft/Vec3d;", "component10", "()F", "component11", "component12", "component13", "()I", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "()Ljava/util/List;", "component23", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "component24", "component25", "component26", "component27", "()Ljava/util/Map;", "component28", "component29", "component30", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "component31", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "component32", "component33", "component34", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "component35", "component36", "component37", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDZLnet/minecraft/Vec3d;FFLjava/lang/String;IFFFFLjava/lang/String;FFFLjava/util/List;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;Ljava/lang/String;ZLjava/util/List;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;Ljava/lang/String;FLrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;Ljava/util/List;FF)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmField;", "Ljava/lang/String;", "D", "Z", "Lnet/minecraft/Vec3d;", "F", "I", "Ljava/util/List;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "Ljava/util/Map;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "rtx.kimiko:kimiko"})
public final class GuiShareRemoteState
{
    @JvmField
    @NotNull
    public final String identityKey;
    @JvmField
    @NotNull
    public final String profileUsername;
    @JvmField
    @NotNull
    public final String minecraftUsername;
    @JvmField
    @NotNull
    public final String world;
    @JvmField
    public final double x;
    @JvmField
    public final double y;
    @JvmField
    public final double z;
    @JvmField
    public final boolean open;
    @JvmField
    @NotNull
    public final Vec3d anchor;
    @JvmField
    public final float yaw;
    @JvmField
    public final float pitch;
    @JvmField
    @NotNull
    public final String category;
    @JvmField
    public final int eventsSub;
    @JvmField
    public final float eventsScroll;
    @JvmField
    public final float listScroll;
    @JvmField
    public final float mouseX;
    @JvmField
    public final float mouseY;
    @JvmField
    @NotNull
    public final String popupModule;
    @JvmField
    public final float popupScroll;
    @JvmField
    public final float popupX;
    @JvmField
    public final float popupY;
    @JvmField
    @NotNull
    public final List<GuiSharePopupRow> popupRows;
    @JvmField
    @NotNull
    public final GuiShareBindPopup bindPopup;
    @JvmField
    @NotNull
    public final String search;
    @JvmField
    public final boolean searchFocused;
    @JvmField
    @NotNull
    public final List<String> enabledModules;
    @JvmField
    @NotNull
    public final Map<String, String> moduleBadges;
    @JvmField
    @NotNull
    public final String role;
    @JvmField
    @NotNull
    public final String avatarUrl;
    @JvmField
    @NotNull
    public final GuiShareCloseState closeState;
    @JvmField
    @NotNull
    public final GuiShareThemeState theme;
    @JvmField
    @NotNull
    public final String selectedTheme;
    @JvmField
    public final float themesScroll;
    @JvmField
    @NotNull
    public final GuiShareChatState chat;
    @JvmField
    @NotNull
    public final List<GuiShareConfigRow> configs;
    @JvmField
    public final float configsScroll;
    @JvmField
    public final float screenHeight;

    public GuiShareRemoteState(@NotNull String identityKey, @NotNull String profileUsername, @NotNull String minecraftUsername, @NotNull String world, double x, double y, double z, boolean open, @NotNull Vec3d anchor, float yaw, float pitch, @NotNull String category, int eventsSub, float eventsScroll, float listScroll, float mouseX, float mouseY, @NotNull String popupModule, float popupScroll, float popupX, float popupY, @NotNull List<GuiSharePopupRow> popupRows, @NotNull GuiShareBindPopup bindPopup, @NotNull String search, boolean searchFocused, @NotNull List<String> enabledModules, @NotNull Map<String, String> moduleBadges, @NotNull String role, @NotNull String avatarUrl, @NotNull GuiShareCloseState closeState, @NotNull GuiShareThemeState theme, @NotNull String selectedTheme, float themesScroll, @NotNull GuiShareChatState chat, @NotNull List<GuiShareConfigRow> configs, float configsScroll, float screenHeight) {
        Intrinsics.checkNotNullParameter((Object)identityKey, (String)"identityKey");
        Intrinsics.checkNotNullParameter((Object)profileUsername, (String)"profileUsername");
        Intrinsics.checkNotNullParameter((Object)minecraftUsername, (String)"minecraftUsername");
        Intrinsics.checkNotNullParameter((Object)world, (String)"world");
        Intrinsics.checkNotNullParameter((Object)anchor, (String)"anchor");
        Intrinsics.checkNotNullParameter((Object)category, (String)"category");
        Intrinsics.checkNotNullParameter((Object)popupModule, (String)"popupModule");
        Intrinsics.checkNotNullParameter(popupRows, (String)"popupRows");
        Intrinsics.checkNotNullParameter((Object)bindPopup, (String)"bindPopup");
        Intrinsics.checkNotNullParameter((Object)search, (String)"search");
        Intrinsics.checkNotNullParameter(enabledModules, (String)"enabledModules");
        Intrinsics.checkNotNullParameter(moduleBadges, (String)"moduleBadges");
        Intrinsics.checkNotNullParameter((Object)role, (String)"role");
        Intrinsics.checkNotNullParameter((Object)avatarUrl, (String)"avatarUrl");
        Intrinsics.checkNotNullParameter((Object)closeState, (String)"closeState");
        Intrinsics.checkNotNullParameter((Object)theme, (String)"theme");
        Intrinsics.checkNotNullParameter((Object)selectedTheme, (String)"selectedTheme");
        Intrinsics.checkNotNullParameter((Object)chat, (String)"chat");
        Intrinsics.checkNotNullParameter(configs, (String)"configs");
        this.identityKey = identityKey;
        this.profileUsername = profileUsername;
        this.minecraftUsername = minecraftUsername;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.open = open;
        this.anchor = anchor;
        this.yaw = yaw;
        this.pitch = pitch;
        this.category = category;
        this.eventsSub = eventsSub;
        this.eventsScroll = eventsScroll;
        this.listScroll = listScroll;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.popupModule = popupModule;
        this.popupScroll = popupScroll;
        this.popupX = popupX;
        this.popupY = popupY;
        this.popupRows = popupRows;
        this.bindPopup = bindPopup;
        this.search = search;
        this.searchFocused = searchFocused;
        this.enabledModules = enabledModules;
        this.moduleBadges = moduleBadges;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.closeState = closeState;
        this.theme = theme;
        this.selectedTheme = selectedTheme;
        this.themesScroll = themesScroll;
        this.chat = chat;
        this.configs = configs;
        this.configsScroll = configsScroll;
        this.screenHeight = screenHeight;
    }

    @NotNull
    public final String component1() {
        return this.identityKey;
    }

    @NotNull
    public final String component2() {
        return this.profileUsername;
    }

    @NotNull
    public final String component3() {
        return this.minecraftUsername;
    }

    @NotNull
    public final String component4() {
        return this.world;
    }

    public final double component5() {
        return this.x;
    }

    public final double component6() {
        return this.y;
    }

    public final double component7() {
        return this.z;
    }

    public final boolean component8() {
        return this.open;
    }

    @NotNull
    public final Vec3d component9() {
        return this.anchor;
    }

    public final float component10() {
        return this.yaw;
    }

    public final float component11() {
        return this.pitch;
    }

    @NotNull
    public final String component12() {
        return this.category;
    }

    public final int component13() {
        return this.eventsSub;
    }

    public final float component14() {
        return this.eventsScroll;
    }

    public final float component15() {
        return this.listScroll;
    }

    public final float component16() {
        return this.mouseX;
    }

    public final float component17() {
        return this.mouseY;
    }

    @NotNull
    public final String component18() {
        return this.popupModule;
    }

    public final float component19() {
        return this.popupScroll;
    }

    public final float component20() {
        return this.popupX;
    }

    public final float component21() {
        return this.popupY;
    }

    @NotNull
    public final List<GuiSharePopupRow> component22() {
        return this.popupRows;
    }

    @NotNull
    public final GuiShareBindPopup component23() {
        return this.bindPopup;
    }

    @NotNull
    public final String component24() {
        return this.search;
    }

    public final boolean component25() {
        return this.searchFocused;
    }

    @NotNull
    public final List<String> component26() {
        return this.enabledModules;
    }

    @NotNull
    public final Map<String, String> component27() {
        return this.moduleBadges;
    }

    @NotNull
    public final String component28() {
        return this.role;
    }

    @NotNull
    public final String component29() {
        return this.avatarUrl;
    }

    @NotNull
    public final GuiShareCloseState component30() {
        return this.closeState;
    }

    @NotNull
    public final GuiShareThemeState component31() {
        return this.theme;
    }

    @NotNull
    public final String component32() {
        return this.selectedTheme;
    }

    public final float component33() {
        return this.themesScroll;
    }

    @NotNull
    public final GuiShareChatState component34() {
        return this.chat;
    }

    @NotNull
    public final List<GuiShareConfigRow> component35() {
        return this.configs;
    }

    public final float component36() {
        return this.configsScroll;
    }

    public final float component37() {
        return this.screenHeight;
    }

    @NotNull
    public final GuiShareRemoteState copy(@NotNull String identityKey, @NotNull String profileUsername, @NotNull String minecraftUsername, @NotNull String world, double x, double y, double z, boolean open, @NotNull Vec3d anchor, float yaw, float pitch, @NotNull String category, int eventsSub, float eventsScroll, float listScroll, float mouseX, float mouseY, @NotNull String popupModule, float popupScroll, float popupX, float popupY, @NotNull List<GuiSharePopupRow> popupRows, @NotNull GuiShareBindPopup bindPopup, @NotNull String search, boolean searchFocused, @NotNull List<String> enabledModules, @NotNull Map<String, String> moduleBadges, @NotNull String role, @NotNull String avatarUrl, @NotNull GuiShareCloseState closeState, @NotNull GuiShareThemeState theme, @NotNull String selectedTheme, float themesScroll, @NotNull GuiShareChatState chat, @NotNull List<GuiShareConfigRow> configs, float configsScroll, float screenHeight) {
        Intrinsics.checkNotNullParameter((Object)identityKey, (String)"identityKey");
        Intrinsics.checkNotNullParameter((Object)profileUsername, (String)"profileUsername");
        Intrinsics.checkNotNullParameter((Object)minecraftUsername, (String)"minecraftUsername");
        Intrinsics.checkNotNullParameter((Object)world, (String)"world");
        Intrinsics.checkNotNullParameter((Object)anchor, (String)"anchor");
        Intrinsics.checkNotNullParameter((Object)category, (String)"category");
        Intrinsics.checkNotNullParameter((Object)popupModule, (String)"popupModule");
        Intrinsics.checkNotNullParameter(popupRows, (String)"popupRows");
        Intrinsics.checkNotNullParameter((Object)bindPopup, (String)"bindPopup");
        Intrinsics.checkNotNullParameter((Object)search, (String)"search");
        Intrinsics.checkNotNullParameter(enabledModules, (String)"enabledModules");
        Intrinsics.checkNotNullParameter(moduleBadges, (String)"moduleBadges");
        Intrinsics.checkNotNullParameter((Object)role, (String)"role");
        Intrinsics.checkNotNullParameter((Object)avatarUrl, (String)"avatarUrl");
        Intrinsics.checkNotNullParameter((Object)closeState, (String)"closeState");
        Intrinsics.checkNotNullParameter((Object)theme, (String)"theme");
        Intrinsics.checkNotNullParameter((Object)selectedTheme, (String)"selectedTheme");
        Intrinsics.checkNotNullParameter((Object)chat, (String)"chat");
        Intrinsics.checkNotNullParameter(configs, (String)"configs");
        return new GuiShareRemoteState(identityKey, profileUsername, minecraftUsername, world, x, y, z, open, anchor, yaw, pitch, category, eventsSub, eventsScroll, listScroll, mouseX, mouseY, popupModule, popupScroll, popupX, popupY, popupRows, bindPopup, search, searchFocused, enabledModules, moduleBadges, role, avatarUrl, closeState, theme, selectedTheme, themesScroll, chat, configs, configsScroll, screenHeight);
    }

    public static /* synthetic */ GuiShareRemoteState copy$default(GuiShareRemoteState guiShareRemoteState, String string, String string2, String string3, String string4, double d, double d2, double d3, boolean bl, Vec3d vec3d2, float f, float f2, String string5, int n, float f3, float f4, float f5, float f6, String string6, float f7, float f8, float f9, List list, GuiShareBindPopup guiShareBindPopup, String string7, boolean bl2, List list2, Map map, String string8, String string9, GuiShareCloseState guiShareCloseState, GuiShareThemeState guiShareThemeState, String string10, float f10, GuiShareChatState guiShareChatState, List list3, float f11, float f12, int n2, int n3, Object object) {
        if ((n2 & 1) != 0) {
            string = guiShareRemoteState.identityKey;
        }
        if ((n2 & 2) != 0) {
            string2 = guiShareRemoteState.profileUsername;
        }
        if ((n2 & 4) != 0) {
            string3 = guiShareRemoteState.minecraftUsername;
        }
        if ((n2 & 8) != 0) {
            string4 = guiShareRemoteState.world;
        }
        if ((n2 & 0x10) != 0) {
            d = guiShareRemoteState.x;
        }
        if ((n2 & 0x20) != 0) {
            d2 = guiShareRemoteState.y;
        }
        if ((n2 & 0x40) != 0) {
            d3 = guiShareRemoteState.z;
        }
        if ((n2 & 0x80) != 0) {
            bl = guiShareRemoteState.open;
        }
        if ((n2 & 0x100) != 0) {
            vec3d2 = guiShareRemoteState.anchor;
        }
        if ((n2 & 0x200) != 0) {
            f = guiShareRemoteState.yaw;
        }
        if ((n2 & 0x400) != 0) {
            f2 = guiShareRemoteState.pitch;
        }
        if ((n2 & 0x800) != 0) {
            string5 = guiShareRemoteState.category;
        }
        if ((n2 & 0x1000) != 0) {
            n = guiShareRemoteState.eventsSub;
        }
        if ((n2 & 0x2000) != 0) {
            f3 = guiShareRemoteState.eventsScroll;
        }
        if ((n2 & 0x4000) != 0) {
            f4 = guiShareRemoteState.listScroll;
        }
        if ((n2 & 0x8000) != 0) {
            f5 = guiShareRemoteState.mouseX;
        }
        if ((n2 & 0x10000) != 0) {
            f6 = guiShareRemoteState.mouseY;
        }
        if ((n2 & 0x20000) != 0) {
            string6 = guiShareRemoteState.popupModule;
        }
        if ((n2 & 0x40000) != 0) {
            f7 = guiShareRemoteState.popupScroll;
        }
        if ((n2 & 0x80000) != 0) {
            f8 = guiShareRemoteState.popupX;
        }
        if ((n2 & 0x100000) != 0) {
            f9 = guiShareRemoteState.popupY;
        }
        if ((n2 & 0x200000) != 0) {
            list = guiShareRemoteState.popupRows;
        }
        if ((n2 & 0x400000) != 0) {
            guiShareBindPopup = guiShareRemoteState.bindPopup;
        }
        if ((n2 & 0x800000) != 0) {
            string7 = guiShareRemoteState.search;
        }
        if ((n2 & 0x1000000) != 0) {
            bl2 = guiShareRemoteState.searchFocused;
        }
        if ((n2 & 0x2000000) != 0) {
            list2 = guiShareRemoteState.enabledModules;
        }
        if ((n2 & 0x4000000) != 0) {
            map = guiShareRemoteState.moduleBadges;
        }
        if ((n2 & 0x8000000) != 0) {
            string8 = guiShareRemoteState.role;
        }
        if ((n2 & 0x10000000) != 0) {
            string9 = guiShareRemoteState.avatarUrl;
        }
        if ((n2 & 0x20000000) != 0) {
            guiShareCloseState = guiShareRemoteState.closeState;
        }
        if ((n2 & 0x40000000) != 0) {
            guiShareThemeState = guiShareRemoteState.theme;
        }
        if ((n2 & Integer.MIN_VALUE) != 0) {
            string10 = guiShareRemoteState.selectedTheme;
        }
        if ((n3 & 1) != 0) {
            f10 = guiShareRemoteState.themesScroll;
        }
        if ((n3 & 2) != 0) {
            guiShareChatState = guiShareRemoteState.chat;
        }
        if ((n3 & 4) != 0) {
            list3 = guiShareRemoteState.configs;
        }
        if ((n3 & 8) != 0) {
            f11 = guiShareRemoteState.configsScroll;
        }
        if ((n3 & 0x10) != 0) {
            f12 = guiShareRemoteState.screenHeight;
        }
        return guiShareRemoteState.copy(string, string2, string3, string4, d, d2, d3, bl, vec3d2, f, f2, string5, n, f3, f4, f5, f6, string6, f7, f8, f9, list, guiShareBindPopup, string7, bl2, list2, map, string8, string9, guiShareCloseState, guiShareThemeState, string10, f10, guiShareChatState, list3, f11, f12);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiShareRemoteState(identityKey=" + this.identityKey + ", profileUsername=" + this.profileUsername + ", minecraftUsername=" + this.minecraftUsername + ", world=" + this.world + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", open=" + this.open + ", anchor=" + this.anchor + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", category=" + this.category + ", eventsSub=" + this.eventsSub + ", eventsScroll=" + this.eventsScroll + ", listScroll=" + this.listScroll + ", mouseX=" + this.mouseX + ", mouseY=" + this.mouseY + ", popupModule=" + this.popupModule + ", popupScroll=" + this.popupScroll + ", popupX=" + this.popupX + ", popupY=" + this.popupY + ", popupRows=" + this.popupRows + ", bindPopup=" + this.bindPopup + ", search=" + this.search + ", searchFocused=" + this.searchFocused + ", enabledModules=" + this.enabledModules + ", moduleBadges=" + this.moduleBadges + ", role=" + this.role + ", avatarUrl=" + this.avatarUrl + ", closeState=" + this.closeState + ", theme=" + this.theme + ", selectedTheme=" + this.selectedTheme + ", themesScroll=" + this.themesScroll + ", chat=" + this.chat + ", configs=" + this.configs + ", configsScroll=" + this.configsScroll + ", screenHeight=" + this.screenHeight + ")";
    }

    @Override
    public int hashCode() {
        int result = this.identityKey.hashCode();
        result = result * 31 + this.profileUsername.hashCode();
        result = result * 31 + this.minecraftUsername.hashCode();
        result = result * 31 + this.world.hashCode();
        result = result * 31 + Double.hashCode(this.x);
        result = result * 31 + Double.hashCode(this.y);
        result = result * 31 + Double.hashCode(this.z);
        result = result * 31 + Boolean.hashCode(this.open);
        result = result * 31 + this.anchor.hashCode();
        result = result * 31 + Float.hashCode(this.yaw);
        result = result * 31 + Float.hashCode(this.pitch);
        result = result * 31 + this.category.hashCode();
        result = result * 31 + Integer.hashCode(this.eventsSub);
        result = result * 31 + Float.hashCode(this.eventsScroll);
        result = result * 31 + Float.hashCode(this.listScroll);
        result = result * 31 + Float.hashCode(this.mouseX);
        result = result * 31 + Float.hashCode(this.mouseY);
        result = result * 31 + this.popupModule.hashCode();
        result = result * 31 + Float.hashCode(this.popupScroll);
        result = result * 31 + Float.hashCode(this.popupX);
        result = result * 31 + Float.hashCode(this.popupY);
        result = result * 31 + ((Object)this.popupRows).hashCode();
        result = result * 31 + this.bindPopup.hashCode();
        result = result * 31 + this.search.hashCode();
        result = result * 31 + Boolean.hashCode(this.searchFocused);
        result = result * 31 + ((Object)this.enabledModules).hashCode();
        result = result * 31 + ((Object)this.moduleBadges).hashCode();
        result = result * 31 + this.role.hashCode();
        result = result * 31 + this.avatarUrl.hashCode();
        result = result * 31 + this.closeState.hashCode();
        result = result * 31 + this.theme.hashCode();
        result = result * 31 + this.selectedTheme.hashCode();
        result = result * 31 + Float.hashCode(this.themesScroll);
        result = result * 31 + this.chat.hashCode();
        result = result * 31 + ((Object)this.configs).hashCode();
        result = result * 31 + Float.hashCode(this.configsScroll);
        result = result * 31 + Float.hashCode(this.screenHeight);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuiShareRemoteState)) {
            return false;
        }
        GuiShareRemoteState guiShareRemoteState = (GuiShareRemoteState)other;
        if (!Intrinsics.areEqual((Object)this.identityKey, (Object)guiShareRemoteState.identityKey)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.profileUsername, (Object)guiShareRemoteState.profileUsername)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.minecraftUsername, (Object)guiShareRemoteState.minecraftUsername)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.world, (Object)guiShareRemoteState.world)) {
            return false;
        }
        if (Double.compare(this.x, guiShareRemoteState.x) != 0) {
            return false;
        }
        if (Double.compare(this.y, guiShareRemoteState.y) != 0) {
            return false;
        }
        if (Double.compare(this.z, guiShareRemoteState.z) != 0) {
            return false;
        }
        if (this.open != guiShareRemoteState.open) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.anchor, (Object)guiShareRemoteState.anchor)) {
            return false;
        }
        if (Float.compare(this.yaw, guiShareRemoteState.yaw) != 0) {
            return false;
        }
        if (Float.compare(this.pitch, guiShareRemoteState.pitch) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.category, (Object)guiShareRemoteState.category)) {
            return false;
        }
        if (this.eventsSub != guiShareRemoteState.eventsSub) {
            return false;
        }
        if (Float.compare(this.eventsScroll, guiShareRemoteState.eventsScroll) != 0) {
            return false;
        }
        if (Float.compare(this.listScroll, guiShareRemoteState.listScroll) != 0) {
            return false;
        }
        if (Float.compare(this.mouseX, guiShareRemoteState.mouseX) != 0) {
            return false;
        }
        if (Float.compare(this.mouseY, guiShareRemoteState.mouseY) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.popupModule, (Object)guiShareRemoteState.popupModule)) {
            return false;
        }
        if (Float.compare(this.popupScroll, guiShareRemoteState.popupScroll) != 0) {
            return false;
        }
        if (Float.compare(this.popupX, guiShareRemoteState.popupX) != 0) {
            return false;
        }
        if (Float.compare(this.popupY, guiShareRemoteState.popupY) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual(this.popupRows, guiShareRemoteState.popupRows)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.bindPopup, (Object)guiShareRemoteState.bindPopup)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.search, (Object)guiShareRemoteState.search)) {
            return false;
        }
        if (this.searchFocused != guiShareRemoteState.searchFocused) {
            return false;
        }
        if (!Intrinsics.areEqual(this.enabledModules, guiShareRemoteState.enabledModules)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.moduleBadges, guiShareRemoteState.moduleBadges)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.role, (Object)guiShareRemoteState.role)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.avatarUrl, (Object)guiShareRemoteState.avatarUrl)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.closeState, (Object)guiShareRemoteState.closeState)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.theme, (Object)guiShareRemoteState.theme)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.selectedTheme, (Object)guiShareRemoteState.selectedTheme)) {
            return false;
        }
        if (Float.compare(this.themesScroll, guiShareRemoteState.themesScroll) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.chat, (Object)guiShareRemoteState.chat)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.configs, guiShareRemoteState.configs)) {
            return false;
        }
        if (Float.compare(this.configsScroll, guiShareRemoteState.configsScroll) != 0) {
            return false;
        }
        return Float.compare(this.screenHeight, guiShareRemoteState.screenHeight) == 0;
    }
}

