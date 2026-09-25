/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.math.Vec3d
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.sync;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetVariant;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b8\b\u0086\b\u0018\u00002\u00020\u0001B\u009d\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\n\u0012\b\b\u0002\u0010\u0013\u001a\u00020\n\u0012\b\b\u0002\u0010\u0014\u001a\u00020\n\u0012\b\b\u0002\u0010\u0015\u001a\u00020\f\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0019J\r\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0019J\r\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0019J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\u001aJ\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\u001bJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\u001cJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u001dJ\r\u0010\u000e\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u001dJ\r\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u001dJ\r\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u001eJ\r\u0010\u0012\u001a\u00020\n\u00a2\u0006\u0004\b\u0012\u0010\u001cJ\r\u0010\u0013\u001a\u00020\n\u00a2\u0006\u0004\b\u0013\u0010\u001cJ\r\u0010\u0014\u001a\u00020\n\u00a2\u0006\u0004\b\u0014\u0010\u001cJ\r\u0010\u0015\u001a\u00020\f\u00a2\u0006\u0004\b\u0015\u0010\u001dJ\r\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0019J\r\u0010\u001f\u001a\u00020\n\u00a2\u0006\u0004\b\u001f\u0010\u001cJ\r\u0010 \u001a\u00020\n\u00a2\u0006\u0004\b \u0010\u001cJ\r\u0010!\u001a\u00020\n\u00a2\u0006\u0004\b!\u0010\u001cJ\r\u0010\"\u001a\u00020\n\u00a2\u0006\u0004\b\"\u0010\u001cJ\r\u0010#\u001a\u00020\n\u00a2\u0006\u0004\b#\u0010\u001cJ\r\u0010%\u001a\u00020$\u00a2\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\u0019J\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010\u0019J\u0010\u0010)\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010\u0019J\u0010\u0010*\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010\u001aJ\u0010\u0010+\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b+\u0010\u001bJ\u0010\u0010,\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b,\u0010\u001cJ\u0010\u0010-\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b-\u0010\u001dJ\u0010\u0010.\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b.\u0010\u001dJ\u0010\u0010/\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b/\u0010\u001dJ\u0010\u00100\u001a\u00020\u0010H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010\u001eJ\u0010\u00101\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b1\u0010\u001cJ\u0010\u00102\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b2\u0010\u001cJ\u0010\u00103\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b3\u0010\u001cJ\u0010\u00104\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b4\u0010\u001dJ\u0010\u00105\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b5\u0010\u0019J\u00a6\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b6\u00107J\u001b\u00109\u001a\u00020\n2\b\u00108\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b9\u0010:J\u0011\u0010;\u001a\u00020\bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b;\u0010\u001bJ\u0011\u0010<\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b<\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010=\u001a\u0004\b>\u0010\u0019R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010=\u001a\u0004\b?\u0010\u0019R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010=\u001a\u0004\b@\u0010\u0019R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010A\u001a\u0004\bB\u0010\u001aR\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010C\u001a\u0004\bD\u0010\u001bR\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010E\u001a\u0004\bF\u0010\u001cR\u0017\u0010\r\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010G\u001a\u0004\bH\u0010\u001dR\u0017\u0010\u000e\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010G\u001a\u0004\bI\u0010\u001dR\u0017\u0010\u000f\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010G\u001a\u0004\bJ\u0010\u001dR\u0017\u0010\u0011\u001a\u00020\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010K\u001a\u0004\bL\u0010\u001eR\u0017\u0010\u0012\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010E\u001a\u0004\bM\u0010\u001cR\u0017\u0010\u0013\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010E\u001a\u0004\bN\u0010\u001cR\u0017\u0010\u0014\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010E\u001a\u0004\bO\u0010\u001cR\u0017\u0010\u0015\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u0015\u0010G\u001a\u0004\bP\u0010\u001dR\u0017\u0010\u0016\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010=\u001a\u0004\bQ\u0010\u0019R\u0017\u0010R\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\bR\u0010=\u001a\u0004\bS\u0010\u0019R\u0017\u0010T\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\bT\u0010=\u001a\u0004\bU\u0010\u0019R\u0017\u0010V\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\bV\u0010=\u001a\u0004\bW\u0010\u0019R\u0017\u0010X\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\bX\u0010G\u001a\u0004\bY\u0010\u001dR\u0017\u0010Z\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\bZ\u0010=\u001a\u0004\b[\u0010\u0019\u00a8\u0006\\"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetRemoteState;", "", "", "identityKey", "profileUsername", "minecraftUsername", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "variant", "", "robotType", "", "active", "", "x", "y", "z", "", "yaw", "moving", "umbrella", "airborne", "animationSpeed", "petKind", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;IZDDDFZZZDLjava/lang/String;)V", "()Ljava/lang/String;", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "()I", "()Z", "()D", "()F", "isOwl", "isChekushka", "isGoat", "isNightmareBb", "isUfo", "Lnet/minecraft/Vec3d;", "position", "()Lnet/minecraft/Vec3d;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;IZDDDFZZZDLjava/lang/String;)Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetRemoteState;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Ljava/lang/String;", "getIdentityKey", "getProfileUsername", "getMinecraftUsername", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "getVariant", "I", "getRobotType", "Z", "getActive", "D", "getX", "getY", "getZ", "F", "getYaw", "getMoving", "getUmbrella", "getAirborne", "getAnimationSpeed", "getPetKind", "cleanIdentityKey", "getCleanIdentityKey", "cleanProfileUsername", "getCleanProfileUsername", "cleanMinecraftUsername", "getCleanMinecraftUsername", "validAnimationSpeed", "getValidAnimationSpeed", "validPetKind", "getValidPetKind", "rtx.kimiko:kimiko"})
public final class CustomPetRemoteState {
    @NotNull
    private final String identityKey;
    @NotNull
    private final String profileUsername;
    @NotNull
    private final String minecraftUsername;
    @NotNull
    private final CustomPetVariant variant;
    private final int robotType;
    private final boolean active;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final boolean moving;
    private final boolean umbrella;
    private final boolean airborne;
    private final double animationSpeed;
    @NotNull
    private final String petKind;
    @NotNull
    private final String cleanIdentityKey;
    @NotNull
    private final String cleanProfileUsername;
    @NotNull
    private final String cleanMinecraftUsername;
    private final double validAnimationSpeed;
    @NotNull
    private final String validPetKind;

    public CustomPetRemoteState(@NotNull String identityKey, @NotNull String profileUsername, @NotNull String minecraftUsername, @NotNull CustomPetVariant variant, int robotType, boolean active, double x, double y, double z, float yaw, boolean moving, boolean umbrella, boolean airborne, double animationSpeed, @NotNull String petKind) {
        Intrinsics.checkNotNullParameter((Object)identityKey, (String)"identityKey");
        Intrinsics.checkNotNullParameter((Object)profileUsername, (String)"profileUsername");
        Intrinsics.checkNotNullParameter((Object)minecraftUsername, (String)"minecraftUsername");
        Intrinsics.checkNotNullParameter((Object)((Object)variant), (String)"variant");
        Intrinsics.checkNotNullParameter((Object)petKind, (String)"petKind");
        this.identityKey = identityKey;
        this.profileUsername = profileUsername;
        this.minecraftUsername = minecraftUsername;
        this.variant = variant;
        this.robotType = robotType;
        this.active = active;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.moving = moving;
        this.umbrella = umbrella;
        this.airborne = airborne;
        this.animationSpeed = animationSpeed;
        this.petKind = petKind;
        this.cleanIdentityKey = ((Object)StringsKt.trim((CharSequence)this.identityKey)).toString();
        this.cleanProfileUsername = ((Object)StringsKt.trim((CharSequence)this.profileUsername)).toString();
        this.cleanMinecraftUsername = ((Object)StringsKt.trim((CharSequence)this.minecraftUsername)).toString();
        this.validAnimationSpeed = this.animationSpeed <= 0.0 ? 1.0 : this.animationSpeed;
        this.validPetKind = StringsKt.isBlank((CharSequence)this.petKind) ? "frog" : ((Object)StringsKt.trim((CharSequence)this.petKind)).toString();
    }

    public /* synthetic */ CustomPetRemoteState(String string, String string2, String string3, CustomPetVariant customPetVariant, int n, boolean bl, double d, double d2, double d3, float f, boolean bl2, boolean bl3, boolean bl4, double d4, String string4, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        this(((n2 & 1) != 0 ? "" : string), ((n2 & 2) != 0 ? "" : string2), ((n2 & 4) != 0 ? "" : string3), ((n2 & 8) != 0 ? CustomPetVariant.NITWIT : customPetVariant), ((n2 & 0x10) != 0 ? 0 : n), ((n2 & 0x20) != 0 ? false : bl), ((n2 & 0x40) != 0 ? 0.0 : d), ((n2 & 0x80) != 0 ? 0.0 : d2), ((n2 & 0x100) != 0 ? 0.0 : d3), ((n2 & 0x200) != 0 ? 0.0f : f), ((n2 & 0x400) != 0 ? false : bl2), ((n2 & 0x800) != 0 ? false : bl3), ((n2 & 0x1000) != 0 ? false : bl4), ((n2 & 0x2000) != 0 ? 1.0 : d4), ((n2 & 0x4000) != 0 ? "frog" : string4));
    }

    @NotNull
    public final String getIdentityKey() {
        return this.identityKey;
    }

    @NotNull
    public final String getProfileUsername() {
        return this.profileUsername;
    }

    @NotNull
    public final String getMinecraftUsername() {
        return this.minecraftUsername;
    }

    @NotNull
    public final CustomPetVariant getVariant() {
        return this.variant;
    }

    public final int getRobotType() {
        return this.robotType;
    }

    public final boolean getActive() {
        return this.active;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final double getZ() {
        return this.z;
    }

    public final float getYaw() {
        return this.yaw;
    }

    public final boolean getMoving() {
        return this.moving;
    }

    public final boolean getUmbrella() {
        return this.umbrella;
    }

    public final boolean getAirborne() {
        return this.airborne;
    }

    public final double getAnimationSpeed() {
        return this.animationSpeed;
    }

    @NotNull
    public final String getPetKind() {
        return this.petKind;
    }

    @NotNull
    public final String getCleanIdentityKey() {
        return this.cleanIdentityKey;
    }

    @NotNull
    public final String getCleanProfileUsername() {
        return this.cleanProfileUsername;
    }

    @NotNull
    public final String getCleanMinecraftUsername() {
        return this.cleanMinecraftUsername;
    }

    public final double getValidAnimationSpeed() {
        return this.validAnimationSpeed;
    }

    @NotNull
    public final String getValidPetKind() {
        return this.validPetKind;
    }

    @NotNull
    public final String identityKey() {
        return this.identityKey;
    }

    @NotNull
    public final String profileUsername() {
        return this.profileUsername;
    }

    @NotNull
    public final String minecraftUsername() {
        return this.minecraftUsername;
    }

    @NotNull
    public final CustomPetVariant variant() {
        return this.variant;
    }

    public final int robotType() {
        return this.robotType;
    }

    public final boolean active() {
        return this.active;
    }

    public final double x() {
        return this.x;
    }

    public final double y() {
        return this.y;
    }

    public final double z() {
        return this.z;
    }

    public final float yaw() {
        return this.yaw;
    }

    public final boolean moving() {
        return this.moving;
    }

    public final boolean umbrella() {
        return this.umbrella;
    }

    public final boolean airborne() {
        return this.airborne;
    }

    public final double animationSpeed() {
        return this.animationSpeed;
    }

    @NotNull
    public final String petKind() {
        return this.petKind;
    }

    public final boolean isOwl() {
        return Intrinsics.areEqual((Object)"owl", (Object)this.validPetKind);
    }

    public final boolean isChekushka() {
        return Intrinsics.areEqual((Object)"chekushka", (Object)this.validPetKind);
    }

    public final boolean isGoat() {
        return Intrinsics.areEqual((Object)"goat", (Object)this.validPetKind);
    }

    public final boolean isNightmareBb() {
        return Intrinsics.areEqual((Object)"nightmare_bb", (Object)this.validPetKind);
    }

    public final boolean isUfo() {
        return Intrinsics.areEqual((Object)"ufo", (Object)this.validPetKind);
    }

    @NotNull
    public final Vec3d position() {
        return new Vec3d(this.x, this.y, this.z);
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
    public final CustomPetVariant component4() {
        return this.variant;
    }

    public final int component5() {
        return this.robotType;
    }

    public final boolean component6() {
        return this.active;
    }

    public final double component7() {
        return this.x;
    }

    public final double component8() {
        return this.y;
    }

    public final double component9() {
        return this.z;
    }

    public final float component10() {
        return this.yaw;
    }

    public final boolean component11() {
        return this.moving;
    }

    public final boolean component12() {
        return this.umbrella;
    }

    public final boolean component13() {
        return this.airborne;
    }

    public final double component14() {
        return this.animationSpeed;
    }

    @NotNull
    public final String component15() {
        return this.petKind;
    }

    @NotNull
    public final CustomPetRemoteState copy(@NotNull String identityKey, @NotNull String profileUsername, @NotNull String minecraftUsername, @NotNull CustomPetVariant variant, int robotType, boolean active, double x, double y, double z, float yaw, boolean moving, boolean umbrella, boolean airborne, double animationSpeed, @NotNull String petKind) {
        Intrinsics.checkNotNullParameter((Object)identityKey, (String)"identityKey");
        Intrinsics.checkNotNullParameter((Object)profileUsername, (String)"profileUsername");
        Intrinsics.checkNotNullParameter((Object)minecraftUsername, (String)"minecraftUsername");
        Intrinsics.checkNotNullParameter((Object)((Object)variant), (String)"variant");
        Intrinsics.checkNotNullParameter((Object)petKind, (String)"petKind");
        return new CustomPetRemoteState(identityKey, profileUsername, minecraftUsername, variant, robotType, active, x, y, z, yaw, moving, umbrella, airborne, animationSpeed, petKind);
    }

    public static /* synthetic */ CustomPetRemoteState copy$default(CustomPetRemoteState customPetRemoteState, String string, String string2, String string3, CustomPetVariant customPetVariant, int n, boolean bl, double d, double d2, double d3, float f, boolean bl2, boolean bl3, boolean bl4, double d4, String string4, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string = customPetRemoteState.identityKey;
        }
        if ((n2 & 2) != 0) {
            string2 = customPetRemoteState.profileUsername;
        }
        if ((n2 & 4) != 0) {
            string3 = customPetRemoteState.minecraftUsername;
        }
        if ((n2 & 8) != 0) {
            customPetVariant = customPetRemoteState.variant;
        }
        if ((n2 & 0x10) != 0) {
            n = customPetRemoteState.robotType;
        }
        if ((n2 & 0x20) != 0) {
            bl = customPetRemoteState.active;
        }
        if ((n2 & 0x40) != 0) {
            d = customPetRemoteState.x;
        }
        if ((n2 & 0x80) != 0) {
            d2 = customPetRemoteState.y;
        }
        if ((n2 & 0x100) != 0) {
            d3 = customPetRemoteState.z;
        }
        if ((n2 & 0x200) != 0) {
            f = customPetRemoteState.yaw;
        }
        if ((n2 & 0x400) != 0) {
            bl2 = customPetRemoteState.moving;
        }
        if ((n2 & 0x800) != 0) {
            bl3 = customPetRemoteState.umbrella;
        }
        if ((n2 & 0x1000) != 0) {
            bl4 = customPetRemoteState.airborne;
        }
        if ((n2 & 0x2000) != 0) {
            d4 = customPetRemoteState.animationSpeed;
        }
        if ((n2 & 0x4000) != 0) {
            string4 = customPetRemoteState.petKind;
        }
        return customPetRemoteState.copy(string, string2, string3, customPetVariant, n, bl, d, d2, d3, f, bl2, bl3, bl4, d4, string4);
    }

    @NotNull
    public String toString() {
        return "CustomPetRemoteState(identityKey=" + this.identityKey + ", profileUsername=" + this.profileUsername + ", minecraftUsername=" + this.minecraftUsername + ", variant=" + this.variant + ", robotType=" + this.robotType + ", active=" + this.active + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", yaw=" + this.yaw + ", moving=" + this.moving + ", umbrella=" + this.umbrella + ", airborne=" + this.airborne + ", animationSpeed=" + this.animationSpeed + ", petKind=" + this.petKind + ")";
    }

    public int hashCode() {
        int result = this.identityKey.hashCode();
        result = result * 31 + this.profileUsername.hashCode();
        result = result * 31 + this.minecraftUsername.hashCode();
        result = result * 31 + this.variant.hashCode();
        result = result * 31 + Integer.hashCode(this.robotType);
        result = result * 31 + Boolean.hashCode(this.active);
        result = result * 31 + Double.hashCode(this.x);
        result = result * 31 + Double.hashCode(this.y);
        result = result * 31 + Double.hashCode(this.z);
        result = result * 31 + Float.hashCode(this.yaw);
        result = result * 31 + Boolean.hashCode(this.moving);
        result = result * 31 + Boolean.hashCode(this.umbrella);
        result = result * 31 + Boolean.hashCode(this.airborne);
        result = result * 31 + Double.hashCode(this.animationSpeed);
        result = result * 31 + this.petKind.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomPetRemoteState)) {
            return false;
        }
        CustomPetRemoteState customPetRemoteState = (CustomPetRemoteState)other;
        if (!Intrinsics.areEqual((Object)this.identityKey, (Object)customPetRemoteState.identityKey)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.profileUsername, (Object)customPetRemoteState.profileUsername)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.minecraftUsername, (Object)customPetRemoteState.minecraftUsername)) {
            return false;
        }
        if (this.variant != customPetRemoteState.variant) {
            return false;
        }
        if (this.robotType != customPetRemoteState.robotType) {
            return false;
        }
        if (this.active != customPetRemoteState.active) {
            return false;
        }
        if (Double.compare(this.x, customPetRemoteState.x) != 0) {
            return false;
        }
        if (Double.compare(this.y, customPetRemoteState.y) != 0) {
            return false;
        }
        if (Double.compare(this.z, customPetRemoteState.z) != 0) {
            return false;
        }
        if (Float.compare(this.yaw, customPetRemoteState.yaw) != 0) {
            return false;
        }
        if (this.moving != customPetRemoteState.moving) {
            return false;
        }
        if (this.umbrella != customPetRemoteState.umbrella) {
            return false;
        }
        if (this.airborne != customPetRemoteState.airborne) {
            return false;
        }
        if (Double.compare(this.animationSpeed, customPetRemoteState.animationSpeed) != 0) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.petKind, (Object)customPetRemoteState.petKind);
    }

    public CustomPetRemoteState() {
        this(null, null, null, null, 0, false, 0.0, 0.0, 0.0, 0.0f, false, false, false, 0.0, null, Short.MAX_VALUE, null);
    }
}

