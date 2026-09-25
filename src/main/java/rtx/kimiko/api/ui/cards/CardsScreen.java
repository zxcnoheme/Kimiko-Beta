package rtx.kimiko.api.ui.cards;

import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class CardsScreen extends Screen {
    public static final String[] EMOTES = new String[]{"heart", "like", "dislike", "fire", "cry", "laugh"};
    @NotNull
    public static final Companion Companion = new Companion(null);

    public CardsScreen() {
        super(Text.literal("Cards"));
    }

    public static final class Companion {
        private Companion() {}
        public /* synthetic */ Companion(DefaultConstructorMarker m) { this(); }

        public void open() {
            MinecraftClient.getInstance().setScreen(new CardsScreen());
        }
    }
}
