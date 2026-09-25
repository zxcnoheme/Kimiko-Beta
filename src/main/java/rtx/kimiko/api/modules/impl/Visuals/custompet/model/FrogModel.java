/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.base.GeoRenderState
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\n\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/FrogModel;", "Lsoftware/bernie/geckolib/model/GeoModel;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "<init>", "()V", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;", "renderState", "Lnet/minecraft/Identifier;", "getModelResource", "(Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;)Lnet/minecraft/Identifier;", "getTextureResource", "animatable", "getAnimationResource", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;)Lnet/minecraft/Identifier;", "Companion", "rtx.kimiko:kimiko"})
public final class FrogModel
extends GeoModel<CustomPetEntity> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Identifier MODEL;
    @NotNull
    private static final Identifier ANIMATIONS;
    @NotNull
    private static final Identifier TEXTURE;

    @NotNull
    public Identifier getModelResource(@NotNull GeoRenderState renderState) {
        Intrinsics.checkNotNullParameter((Object)renderState, (String)"renderState");
        return MODEL;
    }

    @NotNull
    public Identifier getTextureResource(@NotNull GeoRenderState renderState) {
        Intrinsics.checkNotNullParameter((Object)renderState, (String)"renderState");
        return TEXTURE;
    }

    @NotNull
    public Identifier getAnimationResource(@NotNull CustomPetEntity animatable) {
        Intrinsics.checkNotNullParameter((Object)((Object)animatable), (String)"animatable");
        return ANIMATIONS;
    }

    static {
        Identifier identifier2 = Identifier.of((String)"kimiko", (String)"frog/custom_pet");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        MODEL = identifier2;
        Identifier identifier3 = Identifier.of((String)"kimiko", (String)"frog/custom_pet");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        ANIMATIONS = identifier3;
        Identifier identifier4 = Identifier.of((String)"kimiko", (String)"textures/entity/frog/custom_pet.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier4;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/model/FrogModel.Companion;", "", "<init>", "()V", "Lnet/minecraft/Identifier;", "MODEL", "Lnet/minecraft/Identifier;", "ANIMATIONS", "TEXTURE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

