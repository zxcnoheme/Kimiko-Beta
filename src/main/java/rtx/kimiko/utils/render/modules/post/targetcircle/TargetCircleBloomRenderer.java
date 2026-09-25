package rtx.kimiko.utils.render.modules.post.targetcircle;

import java.util.function.Consumer;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexConsumerProvider;

public class TargetCircleBloomRenderer {
    public static boolean isDisabledAfterError() {
        return false;
    }

    public static void apply(Framebuffer fb, float radius, float glow, boolean throughWalls, Consumer<VertexConsumerProvider.Immediate> drawAction) {
    }

    public static void clear() {
    }
}
