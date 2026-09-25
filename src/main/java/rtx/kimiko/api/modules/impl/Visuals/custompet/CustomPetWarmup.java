/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.ResourceTexture
 *  net.minecraft.client.texture.ReloadableTexture
 *  net.minecraft.client.texture.TextureManager
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  software.bernie.geckolib.cache.GeckoLibResources
 *  software.bernie.geckolib.cache.model.BakedGeoModel
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.ReloadableTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.NightmareBbRenderer;
import software.bernie.geckolib.cache.GeckoLibResources;
import software.bernie.geckolib.cache.model.BakedGeoModel;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\tR\u0014\u0010\u000e\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\tR\u0014\u0010\u000f\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\tR\u0014\u0010\u0010\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\t\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetWarmup;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "warmup", "Lnet/minecraft/Identifier;", "TEXTURE", "Lnet/minecraft/Identifier;", "MODEL", "CHEKUSHKA_TEXTURE", "CHEKUSHKA_MODEL", "GOAT_TEXTURE", "GOAT_MODEL", "NIGHTMARE_BB_TEXTURE", "NIGHTMARE_BB_EYE_TEXTURE", "rtx.kimiko:kimiko"})
public final class CustomPetWarmup {
    @NotNull
    public static final CustomPetWarmup INSTANCE = new CustomPetWarmup();
    @NotNull
    private static final Identifier TEXTURE;
    @NotNull
    private static final Identifier MODEL;
    @NotNull
    private static final Identifier CHEKUSHKA_TEXTURE;
    @NotNull
    private static final Identifier CHEKUSHKA_MODEL;
    @NotNull
    private static final Identifier GOAT_TEXTURE;
    @NotNull
    private static final Identifier GOAT_MODEL;
    @NotNull
    private static final Identifier NIGHTMARE_BB_TEXTURE;
    @NotNull
    private static final Identifier NIGHTMARE_BB_EYE_TEXTURE;

    private CustomPetWarmup() {
    }

    @JvmStatic
    public static final void warmup() {
        BakedGeoModel bakedGeoModel;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return;
        }
        MinecraftClient mc = minecraftClient2;
        try {
            TextureManager textureManager2 = mc.getTextureManager();
            Intrinsics.checkNotNullExpressionValue((Object)textureManager2, (String)"getTextureManager(...)");
            TextureManager textureManager = textureManager2;
            textureManager.registerTexture(TEXTURE, (ReloadableTexture)new ResourceTexture(TEXTURE));
            textureManager.registerTexture(CHEKUSHKA_TEXTURE, (ReloadableTexture)new ResourceTexture(CHEKUSHKA_TEXTURE));
            textureManager.registerTexture(GOAT_TEXTURE, (ReloadableTexture)new ResourceTexture(GOAT_TEXTURE));
            textureManager.registerTexture(NIGHTMARE_BB_TEXTURE, (ReloadableTexture)new ResourceTexture(NIGHTMARE_BB_TEXTURE));
            textureManager.registerTexture(NIGHTMARE_BB_EYE_TEXTURE, (ReloadableTexture)new ResourceTexture(NIGHTMARE_BB_EYE_TEXTURE));
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            bakedGeoModel = GeckoLibResources.getBakedModels().getModel(MODEL);
            Intrinsics.checkNotNull((Object)bakedGeoModel);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            bakedGeoModel = GeckoLibResources.getBakedModels().getModel(CHEKUSHKA_MODEL);
            Intrinsics.checkNotNull((Object)bakedGeoModel);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            bakedGeoModel = GeckoLibResources.getBakedModels().getModel(GOAT_MODEL);
            Intrinsics.checkNotNull((Object)bakedGeoModel);
        }
        catch (Throwable ignored) {
// bakedGeoModel = Unit.INSTANCE;
        }
        try {
            NightmareBbRenderer.preload();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/frog/custom_pet.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"frog/custom_pet");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        MODEL = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/chekushka/chekushka_pet.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        CHEKUSHKA_TEXTURE = identifier4;
        Identifier identifier5 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"chekushka/chekushka_pet");
        Intrinsics.checkNotNullExpressionValue((Object)identifier5, (String)"fromNamespaceAndPath(...)");
        CHEKUSHKA_MODEL = identifier5;
        Identifier identifier6 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/goat/bigear_goat_pet.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier6, (String)"fromNamespaceAndPath(...)");
        GOAT_TEXTURE = identifier6;
        Identifier identifier7 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"goat/bigear_goat_pet");
        Intrinsics.checkNotNullExpressionValue((Object)identifier7, (String)"fromNamespaceAndPath(...)");
        GOAT_MODEL = identifier7;
        Identifier identifier8 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/pet/nightmare_bb_body.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier8, (String)"fromNamespaceAndPath(...)");
        NIGHTMARE_BB_TEXTURE = identifier8;
        Identifier identifier9 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/pet/nightmare_bb_eye.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier9, (String)"fromNamespaceAndPath(...)");
        NIGHTMARE_BB_EYE_TEXTURE = identifier9;
    }
}

