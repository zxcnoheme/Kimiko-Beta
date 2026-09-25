/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.entity.equipment.EquipmentModelLoader
 *  net.minecraft.client.item.ItemModelManager
 *  net.minecraft.client.texture.AtlasManager
 *  net.minecraft.client.texture.PlayerSkinCache
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.render.MapRenderer
 *  net.minecraft.resource.ResourceManager
 *  net.minecraft.client.render.entity.model.LoadedEntityModels
 *  net.minecraft.client.render.entity.EntityRendererFactory.Context
 *  net.minecraft.client.render.block.BlockRenderManager
 *  net.minecraft.client.render.entity.EntityRenderer
 *  net.minecraft.client.render.entity.EntityRenderManager
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.render;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.equipment.EquipmentModelLoader;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.texture.AtlasManager;
import net.minecraft.client.texture.PlayerSkinCache;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.MapRenderer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRenderManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.CustomPetRenderState;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.CustomPetRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003Ji\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0019H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\u001d\u0010\u0003J3\u0010\"\u001a\u0010\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0002\b\u0003\u0018\u00010!\"\b\b\u0000\u0010\u001f*\u00020\u001e2\u0006\u0010 \u001a\u00028\u0000H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\"\u0010#J3\u0010\"\u001a\u0010\u0012\u0002\b\u0003\u0012\u0006\b\u0000\u0012\u00028\u0000\u0018\u00010!\"\b\b\u0000\u0010%*\u00020$2\u0006\u0010&\u001a\u00028\u0000H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\"\u0010'J\u001b\u0010)\u001a\u000e\u0012\u0006\b\u0000\u0012\u00020(\u0012\u0002\b\u00030!H\u0002\u00a2\u0006\u0004\b)\u0010*R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010+R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010,R\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010-R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010.R\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010/R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u00100R\u001e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u00101R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u00102R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u00103R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u00104R$\u00105\u001a\u0010\u0012\u0006\b\u0000\u0012\u00020(\u0012\u0002\b\u0003\u0018\u00010!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00106\u00a8\u00067"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/CustomPetRendererBridge;", "", "<init>", "()V", "Lnet/minecraft/EntityRenderManager;", "dispatcher", "Lnet/minecraft/MinecraftClient;", "minecraft", "Lnet/minecraft/BlockRenderManager;", "blockRenderDispatcher", "Lnet/minecraft/ItemModelManager;", "itemModelResolver", "Lnet/minecraft/MapRenderer;", "mapRenderer", "Lnet/minecraft/AtlasManager;", "atlasManager", "Lnet/minecraft/TextRenderer;", "font", "Ljava/util/function/Supplier;", "Lnet/minecraft/LoadedEntityModels;", "modelSetSupplier", "Lnet/minecraft/EquipmentModelLoader;", "equipmentAssetManager", "Lnet/minecraft/PlayerSkinCache;", "playerSkinRenderCache", "", "Lkotlin/jvm/JvmStatic;", "bootstrap", "(Lnet/minecraft/EntityRenderManager;Lnet/minecraft/MinecraftClient;Lnet/minecraft/BlockRenderManager;Lnet/minecraft/ItemModelManager;Lnet/minecraft/MapRenderer;Lnet/minecraft/AtlasManager;Lnet/minecraft/TextRenderer;Ljava/util/function/Supplier;Lnet/minecraft/EquipmentModelLoader;Lnet/minecraft/PlayerSkinCache;)V", "reload", "Lnet/minecraft/Entity;", "T", "entity", "Lnet/minecraft/EntityRenderer;", "getCustomRenderer", "(Lnet/minecraft/Entity;)Lnet/minecraft/EntityRenderer;", "Lnet/minecraft/EntityRenderState;", "S", "renderState", "(Lnet/minecraft/EntityRenderState;)Lnet/minecraft/EntityRenderer;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "getOrCreateRenderer", "()Lnet/minecraft/EntityRenderer;", "Lnet/minecraft/MinecraftClient;", "Lnet/minecraft/EntityRenderManager;", "Lnet/minecraft/BlockRenderManager;", "Lnet/minecraft/ItemModelManager;", "Lnet/minecraft/MapRenderer;", "Lnet/minecraft/TextRenderer;", "Ljava/util/function/Supplier;", "Lnet/minecraft/EquipmentModelLoader;", "Lnet/minecraft/AtlasManager;", "Lnet/minecraft/PlayerSkinCache;", "renderer", "Lnet/minecraft/EntityRenderer;", "rtx.kimiko:kimiko"})
public final class CustomPetRendererBridge {
    @NotNull
    public static final CustomPetRendererBridge INSTANCE = new CustomPetRendererBridge();
    @Nullable
    private static MinecraftClient minecraft;
    @Nullable
    private static EntityRenderManager dispatcher;
    @Nullable
    private static BlockRenderManager blockRenderDispatcher;
    @Nullable
    private static ItemModelManager itemModelResolver;
    @Nullable
    private static MapRenderer mapRenderer;
    @Nullable
    private static TextRenderer font;
    @Nullable
    private static Supplier<LoadedEntityModels> modelSetSupplier;
    @Nullable
    private static EquipmentModelLoader equipmentAssetManager;
    @Nullable
    private static AtlasManager atlasManager;
    @Nullable
    private static PlayerSkinCache playerSkinRenderCache;
    @Nullable
    private static EntityRenderer<? super CustomPetEntity, ?> renderer;

    private CustomPetRendererBridge() {
    }

    @JvmStatic
    public static final void bootstrap(@NotNull EntityRenderManager dispatcher, @NotNull MinecraftClient minecraft, @NotNull BlockRenderManager blockRenderDispatcher, @NotNull ItemModelManager itemModelResolver, @NotNull MapRenderer mapRenderer, @NotNull AtlasManager atlasManager, @NotNull TextRenderer font, @NotNull Supplier<LoadedEntityModels> modelSetSupplier, @NotNull EquipmentModelLoader equipmentAssetManager, @NotNull PlayerSkinCache playerSkinRenderCache) {
        Intrinsics.checkNotNullParameter((Object)dispatcher, (String)"dispatcher");
        Intrinsics.checkNotNullParameter((Object)minecraft, (String)"minecraft");
        Intrinsics.checkNotNullParameter((Object)blockRenderDispatcher, (String)"blockRenderDispatcher");
        Intrinsics.checkNotNullParameter((Object)itemModelResolver, (String)"itemModelResolver");
        Intrinsics.checkNotNullParameter((Object)mapRenderer, (String)"mapRenderer");
        Intrinsics.checkNotNullParameter((Object)atlasManager, (String)"atlasManager");
        Intrinsics.checkNotNullParameter((Object)font, (String)"font");
        Intrinsics.checkNotNullParameter(modelSetSupplier, (String)"modelSetSupplier");
        Intrinsics.checkNotNullParameter((Object)equipmentAssetManager, (String)"equipmentAssetManager");
        Intrinsics.checkNotNullParameter((Object)playerSkinRenderCache, (String)"playerSkinRenderCache");
        CustomPetRendererBridge.dispatcher = dispatcher;
        CustomPetRendererBridge.minecraft = minecraft;
        CustomPetRendererBridge.blockRenderDispatcher = blockRenderDispatcher;
        CustomPetRendererBridge.itemModelResolver = itemModelResolver;
        CustomPetRendererBridge.mapRenderer = mapRenderer;
        CustomPetRendererBridge.atlasManager = atlasManager;
        CustomPetRendererBridge.font = font;
        CustomPetRendererBridge.modelSetSupplier = modelSetSupplier;
        CustomPetRendererBridge.equipmentAssetManager = equipmentAssetManager;
        CustomPetRendererBridge.playerSkinRenderCache = playerSkinRenderCache;
        renderer = null;
    }

    @JvmStatic
    public static final void reload() {
        renderer = null;
    }

    @JvmStatic
    @Nullable
    @SuppressWarnings("unchecked")
    public static final <T extends Entity> EntityRenderer<? super T, ?> getCustomRenderer(@NotNull T entity) {
        Intrinsics.checkNotNullParameter(entity, (String)"entity");
        if (dispatcher == null || minecraft == null) {
            return null;
        }
        if (entity instanceof CustomPetEntity) {
            return (EntityRenderer) INSTANCE.getOrCreateRenderer();
        }
        return null;
    }

    @JvmStatic
    @Nullable
    @SuppressWarnings("unchecked")
    public static final <S extends EntityRenderState> EntityRenderer<?, ? super S> getCustomRenderer(@NotNull S renderState) {
        Intrinsics.checkNotNullParameter(renderState, (String)"renderState");
        if (dispatcher == null || minecraft == null) {
            return null;
        }
        if (renderState instanceof CustomPetRenderState) {
            return (EntityRenderer) INSTANCE.getOrCreateRenderer();
        }
        return null;
    }

    private final EntityRenderer<? super CustomPetEntity, ?> getOrCreateRenderer() {
        EntityRenderer currentRenderer = renderer;
        if (currentRenderer == null) {
            MinecraftClient minecraftClient2 = minecraft;
            Intrinsics.checkNotNull((Object)minecraftClient2);
            MinecraftClient mc = minecraftClient2;
            EntityRenderManager entityRenderManager2 = dispatcher;
            Intrinsics.checkNotNull((Object)entityRenderManager2);
            EntityRenderManager disp = entityRenderManager2;
            ItemModelManager itemModelManager2 = itemModelResolver;
            Intrinsics.checkNotNull((Object)itemModelManager2);
            ItemModelManager imr = itemModelManager2;
            MapRenderer mapRenderer2 = mapRenderer;
            Intrinsics.checkNotNull((Object)mapRenderer2);
            BlockRenderManager blockRenderManager2 = blockRenderDispatcher;
            Intrinsics.checkNotNull((Object)blockRenderManager2);
            ResourceManager resourceManager2 = mc.getResourceManager();
            Supplier<LoadedEntityModels> supplier = modelSetSupplier;
            Intrinsics.checkNotNull(supplier);
            EquipmentModelLoader equipmentModelLoader2 = equipmentAssetManager;
            Intrinsics.checkNotNull((Object)equipmentModelLoader2);
            AtlasManager atlasManager2 = atlasManager;
            Intrinsics.checkNotNull((Object)atlasManager2);
            TextRenderer textRenderer2 = font;
            Intrinsics.checkNotNull((Object)textRenderer2);
            PlayerSkinCache playerSkinCache2 = playerSkinRenderCache;
            Intrinsics.checkNotNull((Object)playerSkinCache2);
            EntityRendererFactory.Context context = new EntityRendererFactory.Context(disp, imr, mapRenderer2, blockRenderManager2, resourceManager2, supplier.get(), equipmentModelLoader2, atlasManager2, textRenderer2, playerSkinCache2);
            renderer = currentRenderer = (EntityRenderer)new CustomPetRenderer(context);
        }
        return currentRenderer;
    }
}

