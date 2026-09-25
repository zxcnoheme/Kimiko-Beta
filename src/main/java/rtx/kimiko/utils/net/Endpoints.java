/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.net;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0007J\u0013\u0010\n\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0007J\u0017\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0011\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/net/Endpoints;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "configs", "()Ljava/lang/String;", "cards", "irc", "funtime", "party", "", "data", "d", "([B)Ljava/lang/String;", "K", "[B", "A", "B", "P", "C", "G", "rtx.kimiko:kimiko"})
public final class Endpoints {
    @NotNull
    public static final Endpoints INSTANCE = new Endpoints();
    @NotNull
    private static final byte[] K;
    @NotNull
    private static final byte[] A;
    @NotNull
    private static final byte[] B;
    @NotNull
    private static final byte[] P;
    @NotNull
    private static final byte[] C;
    @NotNull
    private static final byte[] G;

    private Endpoints() {
    }

    @JvmStatic
    @NotNull
    public static final String configs() {
        return INSTANCE.d(C);
    }

    @JvmStatic
    @NotNull
    public static final String cards() {
        return INSTANCE.d(G);
    }

    @JvmStatic
    @NotNull
    public static final String irc() {
        return INSTANCE.d(A);
    }

    @JvmStatic
    @NotNull
    public static final String funtime() {
        return INSTANCE.d(B);
    }

    @JvmStatic
    @NotNull
    public static final String party() {
        return INSTANCE.d(P);
    }

    private final String d(byte[] data) {
        byte[] out = new byte[data.length];
        int n = data.length;
        for (int i = 0; i < n; ++i) {
            out[i] = (byte)(data[i] ^ K[i % K.length]);
        }
        Charset charset = StandardCharsets.US_ASCII;
        Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"US_ASCII");
        Charset charset2 = charset;
        return new String(out, charset2);
    }

    static {
        byte[] byArray = new byte[]{107, 41, 87, 61, 18, 78, 113, -125, 29};
        K = byArray;
        byArray = new byte[]{3, 93, 35, 77, 40, 97, 94, -80, 44, 69, 30, 96, 19, 35, 122, 68, -83, 44, 95, 31, 109, 14, 32, 127, 64, -74};
        A = byArray;
        byArray = new byte[]{3, 93, 35, 77, 40, 97, 94, -80, 44, 69, 30, 96, 19, 35, 122, 68, -83, 44, 95, 31, 109, 14, 32, 127, 67, -73, 50, 6, 76, 35, 85, 125, 42, 94};
        B = byArray;
        byArray = new byte[]{28, 90, 109, 18, 61, 125, 64, -83, 42, 92, 7, 102, 9, 39, 96, 64, -73, 43, 81, 26, 101, 12, 32, 126};
        P = byArray;
        byArray = new byte[]{3, 93, 35, 77, 40, 97, 94, -80, 44, 69, 30, 96, 19, 35, 122, 68, -83, 44, 95, 31, 109, 14, 32, 127, 67, -78};
        C = byArray;
        byArray = new byte[]{28, 90, 109, 18, 61, 125, 64, -83, 42, 92, 7, 102, 9, 39, 96, 64, -73, 43, 81, 26, 101, 12, 32, 124};
        G = byArray;
    }
}

