package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
/**
 * Рендерер спец-элементов «3D-превью косметики» (по образцу EmotionPreviewRenderer):
 * модель рисуется в собственный ортографический фреймбуфер, затем ванильный базовый
 * класс блитит его в GUI с обрезкой по scissorArea. Базовый renderElement не переопределяется.
 */
public final class CosmeticPreviewRenderer extends SpecialGuiElementRenderer<CosmeticPreviewState> {

    public CosmeticPreviewRenderer(@NotNull VertexConsumerProvider.Immediate vertexConsumers) {
        super(vertexConsumers);
    }

    @Override
    @NotNull
    public Class<CosmeticPreviewState> getElementClass() {
        return CosmeticPreviewState.class;
    }

    @Override
    @NotNull
    protected String getName() {
        return "kimiko cosmetic previews";
    }

    @Override
    protected float getYOffset(int height, int guiScale) {
        return (float) height / 2.0f;
    }

    @Override
    public void render(@NotNull CosmeticPreviewState state, @NotNull MatrixStack poseStack) {
        if (state.entries.isEmpty()) {
            return;
        }
        MinecraftClient.getInstance().gameRenderer.getDiffuseLighting().setShaderLights(DiffuseLighting.Type.ENTITY_IN_UI);
        for (CosmeticPreviewState.Entry entry : state.entries) {
            PreviewModel model = CosmeticPreviews.byId(entry.cosmeticId());
            if (model == null) {
                ExternalCosmetic ext = ExternalCosmetic.Library.byId(entry.cosmeticId());
                model = ext != null ? ext.preview() : null;
            }
            if (model == null || model.quads.isEmpty()) {
                continue;
            }
            poseStack.push();
            // смещение к центру плитки (локальные единицы = GUI-пиксели при state.scale()==1)
            poseStack.translate(entry.u(), entry.v(), 0.0f);
            // вращение вокруг центра модели; Rz(180°) разворачивает Y-up модель в Y-down пространство фреймбуфера
            poseStack.multiply(entry.rotation());
            poseStack.scale(entry.scale(), entry.scale(), entry.scale());
            poseStack.translate(-model.centerX(), -model.centerY(), -model.centerZ());
            VertexConsumer buffer = this.vertexConsumers.getBuffer(RenderLayers.entityCutoutNoCull(model.texture));
            model.render(poseStack.peek(), buffer, 0xF000F0, entry.alpha());
            poseStack.pop();
        }
    }
}
