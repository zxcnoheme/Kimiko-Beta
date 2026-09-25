/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  software.bernie.geckolib.constant.dataticket.DataTicket
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.base.GeoRenderState
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.model;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.BigEarGoatModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.ChekushkaModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.FrogModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.NightmareBbModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.OwlModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.RobotModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.UfoModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.NightmareBbRenderer;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 *2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001*B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\n\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/CustomPetModel;", "Lsoftware/bernie/geckolib/model/GeoModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "<init>", "()V", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;", "renderState", "Lnet/minecraft/Identifier;", "getModelResource", "(Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;)Lnet/minecraft/Identifier;", "getTextureResource", "animatable", "getAnimationResource", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;)Lnet/minecraft/Identifier;", "pick", "(Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;)Lsoftware/bernie/geckolib/model/GeoModel;", "", "relatedObject", "", "addAdditionalStateData", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;Ljava/lang/Object;Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;)V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/FrogModel;", "frog", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/FrogModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/RobotModel;", "robot", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/RobotModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/OwlModel;", "owl", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/OwlModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/ChekushkaModel;", "chekushka", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/ChekushkaModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/BigEarGoatModel;", "goat", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/BigEarGoatModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/NightmareBbModel;", "nightmareBb", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/NightmareBbModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/UfoModel;", "ufo", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/UfoModel;", "Companion", "rtx.kimiko:kimiko"})
public class CustomPetModel
extends GeoModel<CustomPetEntity> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final FrogModel frog = new FrogModel();
    @NotNull
    private final RobotModel robot = new RobotModel();
    @NotNull
    private final OwlModel owl = new OwlModel();
    @NotNull
    private final ChekushkaModel chekushka = new ChekushkaModel();
    @NotNull
    private final BigEarGoatModel goat = new BigEarGoatModel();
    @NotNull
    private final NightmareBbModel nightmareBb = new NightmareBbModel();
    @NotNull
    private final UfoModel ufo = new UfoModel();
    @JvmField
    @NotNull
    public static final DataTicket<Boolean> UMBRELLA;
    @JvmField
    @NotNull
    public static final DataTicket<Boolean> AIRBORNE;
    @JvmField
    @NotNull
    public static final DataTicket<String> VARIANT;
    @JvmField
    @NotNull
    public static final DataTicket<Integer> ROBOT_TYPE;
    @JvmField
    @NotNull
    public static final DataTicket<Boolean> OWL;
    @JvmField
    @NotNull
    public static final DataTicket<Boolean> CHEKUSHKA;
    @JvmField
    @NotNull
    public static final DataTicket<Boolean> GOAT;
    @JvmField
    @NotNull
    public static final DataTicket<Boolean> NIGHTMARE_BB;
    @JvmField
    @NotNull
    public static final DataTicket<NightmareBbRenderer.Pose> NIGHTMARE_BB_POSE;
    @JvmField
    @NotNull
    public static final DataTicket<Boolean> UFO;
    @JvmField
    @NotNull
    public static final DataTicket<Float> UFO_BEAM_LEVEL;
    @JvmField
    @NotNull
    public static final DataTicket<Float> UFO_BEAM_STRETCH;
    @JvmField
    @NotNull
    public static final DataTicket<Integer> UFO_BEAM_COLOR;
    @JvmField
    @NotNull
    public static final DataTicket<Float> UFO_LAND_BLEND;

    @NotNull
    public Identifier getModelResource(@NotNull GeoRenderState renderState) {
        Intrinsics.checkNotNullParameter((Object)renderState, (String)"renderState");
        Identifier identifier2 = this.pick(renderState).getModelResource(renderState);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"getModelResource(...)");
        return identifier2;
    }

    @NotNull
    public Identifier getTextureResource(@NotNull GeoRenderState renderState) {
        Intrinsics.checkNotNullParameter((Object)renderState, (String)"renderState");
        Identifier identifier2 = this.pick(renderState).getTextureResource(renderState);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"getTextureResource(...)");
        return identifier2;
    }

    @NotNull
    public Identifier getAnimationResource(@NotNull CustomPetEntity animatable) {
        Intrinsics.checkNotNullParameter((Object)((Object)animatable), (String)"animatable");
        if (animatable.isUfo()) {
            return this.ufo.getAnimationResource(animatable);
        }
        if (animatable.isNightmareBb()) {
            return this.nightmareBb.getAnimationResource(animatable);
        }
        if (animatable.isGoat()) {
            return this.goat.getAnimationResource(animatable);
        }
        if (animatable.isChekushka()) {
            return this.chekushka.getAnimationResource(animatable);
        }
        if (animatable.isOwl()) {
            return this.owl.getAnimationResource(animatable);
        }
        return animatable.getPetVariant().isRobot() ? this.robot.getAnimationResource(animatable) : this.frog.getAnimationResource(animatable);
    }

    private final GeoModel<CustomPetEntity> pick(GeoRenderState renderState) {
        if (Intrinsics.areEqual((Object)renderState.getGeckolibData(UFO), (Object)true)) {
            return this.ufo;
        }
        if (Intrinsics.areEqual((Object)renderState.getGeckolibData(NIGHTMARE_BB), (Object)true)) {
            return this.nightmareBb;
        }
        if (Intrinsics.areEqual((Object)renderState.getGeckolibData(GOAT), (Object)true)) {
            return this.goat;
        }
        if (Intrinsics.areEqual((Object)renderState.getGeckolibData(CHEKUSHKA), (Object)true)) {
            return this.chekushka;
        }
        if (Intrinsics.areEqual((Object)renderState.getGeckolibData(OWL), (Object)true)) {
            return this.owl;
        }
        return Intrinsics.areEqual((Object)"ROBOT", (Object)renderState.getGeckolibData(VARIANT)) ? (GeoModel)this.robot : (GeoModel)this.frog;
    }

    public void addAdditionalStateData(@NotNull CustomPetEntity animatable, @Nullable Object relatedObject, @NotNull GeoRenderState renderState) {
        NightmareBbRenderer.Pose pose;
        Intrinsics.checkNotNullParameter((Object)((Object)animatable), (String)"animatable");
        Intrinsics.checkNotNullParameter((Object)renderState, (String)"renderState");
        renderState.addGeckolibData(UMBRELLA, animatable.shouldUseUmbrella());
        renderState.addGeckolibData(AIRBORNE, animatable.isAirborneMode());
        renderState.addGeckolibData(VARIANT, animatable.getPetVariant().name());
        renderState.addGeckolibData(ROBOT_TYPE, animatable.getRobotType());
        renderState.addGeckolibData(OWL, animatable.isOwl());
        renderState.addGeckolibData(CHEKUSHKA, animatable.isChekushka());
        renderState.addGeckolibData(GOAT, animatable.isGoat());
        renderState.addGeckolibData(NIGHTMARE_BB, animatable.isNightmareBb());
        renderState.addGeckolibData(UFO, animatable.isUfo());
        if (animatable.isNightmareBb() && (pose = NightmareBbRenderer.capture(animatable)) != null) {
            renderState.addGeckolibData(NIGHTMARE_BB_POSE, pose);
        }
    }

    static {
        DataTicket dataTicket = DataTicket.create((String)"kimiko.custom_pet.umbrella", Boolean.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket, (String)"create(...)");
        UMBRELLA = dataTicket;
        DataTicket dataTicket2 = DataTicket.create((String)"kimiko.custom_pet.airborne", Boolean.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket2, (String)"create(...)");
        AIRBORNE = dataTicket2;
        DataTicket dataTicket3 = DataTicket.create((String)"kimiko.custom_pet.variant", String.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket3, (String)"create(...)");
        VARIANT = dataTicket3;
        DataTicket dataTicket4 = DataTicket.create((String)"kimiko.custom_pet.robot_type", Integer.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket4, (String)"create(...)");
        ROBOT_TYPE = dataTicket4;
        DataTicket dataTicket5 = DataTicket.create((String)"kimiko.custom_pet.owl", Boolean.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket5, (String)"create(...)");
        OWL = dataTicket5;
        DataTicket dataTicket6 = DataTicket.create((String)"kimiko.custom_pet.chekushka", Boolean.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket6, (String)"create(...)");
        CHEKUSHKA = dataTicket6;
        DataTicket dataTicket7 = DataTicket.create((String)"kimiko.custom_pet.goat", Boolean.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket7, (String)"create(...)");
        GOAT = dataTicket7;
        DataTicket dataTicket8 = DataTicket.create((String)"kimiko.custom_pet.nightmare_bb", Boolean.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket8, (String)"create(...)");
        NIGHTMARE_BB = dataTicket8;
        DataTicket dataTicket9 = DataTicket.create((String)"kimiko.custom_pet.nightmare_bb_pose", NightmareBbRenderer.Pose.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket9, (String)"create(...)");
        NIGHTMARE_BB_POSE = dataTicket9;
        DataTicket dataTicket10 = DataTicket.create((String)"kimiko.custom_pet.ufo", Boolean.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket10, (String)"create(...)");
        UFO = dataTicket10;
        DataTicket dataTicket11 = DataTicket.create((String)"kimiko.custom_pet.ufo_beam_level", Float.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket11, (String)"create(...)");
        UFO_BEAM_LEVEL = dataTicket11;
        DataTicket dataTicket12 = DataTicket.create((String)"kimiko.custom_pet.ufo_beam_stretch", Float.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket12, (String)"create(...)");
        UFO_BEAM_STRETCH = dataTicket12;
        DataTicket dataTicket13 = DataTicket.create((String)"kimiko.custom_pet.ufo_beam_color", Integer.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket13, (String)"create(...)");
        UFO_BEAM_COLOR = dataTicket13;
        DataTicket dataTicket14 = DataTicket.create((String)"kimiko.custom_pet.ufo_land_blend", Float.class);
        Intrinsics.checkNotNullExpressionValue((Object)dataTicket14, (String)"create(...)");
        UFO_LAND_BLEND = dataTicket14;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\bR\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\t\u0010\bR\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\bR\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\r\u0010\bR\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\bR\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\bR\u001f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\bR\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\bR\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\bR\u001f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\bR\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\bR\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\bR\u001f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\bR\u001f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\b\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/CustomPetModel.Companion;", "", "<init>", "()V", "Lsoftware/bernie/geckolib/constant/dataticket/DataTicket;", "", "Lkotlin/jvm/JvmField;", "UMBRELLA", "Lsoftware/bernie/geckolib/constant/dataticket/DataTicket;", "AIRBORNE", "", "VARIANT", "", "ROBOT_TYPE", "OWL", "CHEKUSHKA", "GOAT", "NIGHTMARE_BB", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/NightmareBbRenderer$Pose;", "NIGHTMARE_BB_POSE", "UFO", "", "UFO_BEAM_LEVEL", "UFO_BEAM_STRETCH", "UFO_BEAM_COLOR", "UFO_LAND_BLEND", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

