/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  software.bernie.geckolib.constant.dataticket.DataTicket
 *  software.bernie.geckolib.constant.dataticket.OverridingDataTicket
 *  software.bernie.geckolib.renderer.base.GeoRenderState
 *  software.bernie.geckolib.renderer.base.GeoRenderState$Impl
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.render;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.constant.dataticket.OverridingDataTicket;
import software.bernie.geckolib.renderer.base.GeoRenderState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J/\u0010\u000b\u001a\u00020\n\"\b\b\u0000\u0010\u0006*\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\t\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000e\u001a\u00020\r2\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0010\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0006*\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J3\u0010\u0013\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0006*\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\u00050\u0015H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/CustomPetRenderState;", "Lnet/minecraft/LivingEntityRenderState;", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;", "<init>", "()V", "", "D", "Lsoftware/bernie/geckolib/constant/dataticket/DataTicket;", "dataTicket", "data", "", "addGeckolibData", "(Lsoftware/bernie/geckolib/constant/dataticket/DataTicket;Ljava/lang/Object;)V", "", "hasGeckolibData", "(Lsoftware/bernie/geckolib/constant/dataticket/DataTicket;)Z", "getGeckolibData", "(Lsoftware/bernie/geckolib/constant/dataticket/DataTicket;)Ljava/lang/Object;", "defaultValue", "getOrDefaultGeckolibData", "(Lsoftware/bernie/geckolib/constant/dataticket/DataTicket;Ljava/lang/Object;)Ljava/lang/Object;", "", "getDataMap", "()Ljava/util/Map;", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderState$Impl;", "geckoState", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderState$Impl;", "rtx.kimiko:kimiko"})
public final class CustomPetRenderState
extends LivingEntityRenderState
implements GeoRenderState {
    @NotNull
    private final GeoRenderState.Impl geckoState = new GeoRenderState.Impl();

    public <D> void addGeckolibData(@NotNull DataTicket<D> dataTicket, @NotNull D data) {
        Intrinsics.checkNotNullParameter(dataTicket, (String)"dataTicket");
        Intrinsics.checkNotNullParameter(data, (String)"data");
        this.geckoState.addGeckolibData(dataTicket, data);
    }

    public boolean hasGeckolibData(@NotNull DataTicket<?> dataTicket) {
        Intrinsics.checkNotNullParameter(dataTicket, (String)"dataTicket");
        return this.geckoState.hasGeckolibData(dataTicket);
    }

    @Nullable
    public <D> D getGeckolibData(@NotNull DataTicket<D> dataTicket) {
        OverridingDataTicket overridingTicket;
        Intrinsics.checkNotNullParameter(dataTicket, (String)"dataTicket");
        Object data = this.geckoState.getGeckolibData(dataTicket);
        if (data != null) {
            return (D)data;
        }
        if (dataTicket instanceof OverridingDataTicket && ((OverridingDataTicket)dataTicket).canExtractFrom((GeoRenderState)this) && (overridingTicket = (OverridingDataTicket)dataTicket).getOverriddenClass().isInstance((Object)this)) {
            return (D)overridingTicket.extractFrom(overridingTicket.getOverriddenClass().cast((Object)this));
        }
        return null;
    }

    @Nullable
    public <D> D getOrDefaultGeckolibData(@NotNull DataTicket<D> dataTicket, @Nullable D defaultValue) {
        OverridingDataTicket overridingTicket;
        Intrinsics.checkNotNullParameter(dataTicket, (String)"dataTicket");
        Object data = this.geckoState.getGeckolibData(dataTicket);
        if (data != null) {
            return (D)data;
        }
        if (dataTicket instanceof OverridingDataTicket && ((OverridingDataTicket)dataTicket).canExtractFrom((GeoRenderState)this) && (overridingTicket = (OverridingDataTicket)dataTicket).getOverriddenClass().isInstance((Object)this)) {
            return (D)overridingTicket.extractFrom(overridingTicket.getOverriddenClass().cast((Object)this));
        }
        return defaultValue;
    }

    @NotNull
    public Map<DataTicket<?>, Object> getDataMap() {
        Map map = this.geckoState.getDataMap();
        Intrinsics.checkNotNullExpressionValue((Object)map, (String)"getDataMap(...)");
        return map;
    }
}

