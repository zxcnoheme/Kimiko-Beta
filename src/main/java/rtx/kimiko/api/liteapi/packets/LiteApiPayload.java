/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.network.PacketByteBuf
 *  net.minecraft.util.Identifier
 *  net.minecraft.network.packet.CustomPayload
 *  net.minecraft.network.packet.CustomPayload.Id
 *  net.minecraft.network.codec.PacketCodec
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.liteapi.packets;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.codec.PacketCodec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\nR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0003\u0010\n\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/liteapi/packets/LiteApiPayload;", "Lnet/minecraft/CustomPayload;", "", "json", "<init>", "(Ljava/lang/String;)V", "Lnet/minecraft/CustomPayload$Id;", "type", "()Lnet/minecraft/CustomPayload$Id;", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lrtx/kimiko/api/liteapi/packets/LiteApiPayload;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "Companion", "rtx.kimiko:kimiko"})
public final class LiteApiPayload
implements CustomPayload {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String json;
    @JvmField
    @NotNull
    public static final Identifier CHANNEL;
    @JvmField
    @NotNull
    public static final CustomPayload.Id<LiteApiPayload> TYPE;
    private static final int MAX_BYTES = 262144;
    @JvmField
    @NotNull
    public static final PacketCodec<PacketByteBuf, LiteApiPayload> CODEC;

    public LiteApiPayload(@NotNull String json) {
        Intrinsics.checkNotNullParameter((Object)json, (String)"json");
        this.json = json;
    }

    @JvmName(name="json")
    @NotNull
    public final String json() {
        return this.json;
    }

    @NotNull
    public CustomPayload.Id<LiteApiPayload> getId() {
        return TYPE;
    }

    @NotNull
    public final String component1() {
        return this.json;
    }

    @NotNull
    public final LiteApiPayload copy(@NotNull String json) {
        Intrinsics.checkNotNullParameter((Object)json, (String)"json");
        return new LiteApiPayload(json);
    }

    public static /* synthetic */ LiteApiPayload copy$default(LiteApiPayload liteApiPayload, String string, int n, Object object) {
        if ((n & 1) != 0) {
            string = liteApiPayload.json;
        }
        return liteApiPayload.copy(string);
    }

    @NotNull
    public String toString() {
        return "LiteApiPayload(json=" + this.json + ")";
    }

    public int hashCode() {
        return this.json.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiteApiPayload)) {
            return false;
        }
        LiteApiPayload liteApiPayload = (LiteApiPayload)other;
        return Intrinsics.areEqual((Object)this.json, (Object)liteApiPayload.json);
    }

    private static final void CODEC$lambda$0(PacketByteBuf buf, LiteApiPayload payload) {
        Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
        Intrinsics.checkNotNullParameter((Object)payload, (String)"payload");
        String string = payload.json;
        Charset charset = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"UTF_8");
        byte[] byArray = string.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
        byte[] bytes = byArray;
        buf.writeBytes(bytes);
    }

    private static final LiteApiPayload CODEC$lambda$1(PacketByteBuf buf) {
        LiteApiPayload liteApiPayload;
        Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
        try {
            LiteApiPayload liteApiPayload2;
            int len = buf.readableBytes();
            if (len <= 0 || len > 262144) {
                if (len > 0) {
                    buf.skipBytes(len);
                }
                liteApiPayload2 = new LiteApiPayload("");
            } else {
                byte[] bytes = new byte[len];
                buf.readBytes(bytes);
                Charset charset = StandardCharsets.UTF_8;
                Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"UTF_8");
                Charset charset2 = charset;
                LiteApiPayload liteApiPayload3 = new LiteApiPayload(new String(bytes, charset2));
                liteApiPayload2 = liteApiPayload3;
            }
            liteApiPayload = liteApiPayload2;
        }
        catch (RuntimeException ex) {
            if (buf.isReadable()) {
                buf.skipBytes(buf.readableBytes());
            }
            liteApiPayload = new LiteApiPayload("");
        }
        return liteApiPayload;
    }

    static {
        Identifier identifier2 = Identifier.of((String)"liteapi", (String)"feature-control");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        CHANNEL = identifier2;
        TYPE = new CustomPayload.Id(CHANNEL);
        PacketCodec packetCodec2 = PacketCodec.ofStatic(LiteApiPayload::CODEC$lambda$0, LiteApiPayload::CODEC$lambda$1);
        Intrinsics.checkNotNullExpressionValue((Object)packetCodec2, (String)"of(...)");
        CODEC = packetCodec2;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR%\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/liteapi/packets/LiteApiPayload.Companion;", "", "<init>", "()V", "Lnet/minecraft/Identifier;", "Lkotlin/jvm/JvmField;", "CHANNEL", "Lnet/minecraft/Identifier;", "Lnet/minecraft/CustomPayload$Id;", "Lrtx/kimiko/api/liteapi/packets/LiteApiPayload;", "TYPE", "Lnet/minecraft/CustomPayload$Id;", "", "MAX_BYTES", "I", "Lnet/minecraft/PacketCodec;", "Lnet/minecraft/PacketByteBuf;", "CODEC", "Lnet/minecraft/PacketCodec;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

