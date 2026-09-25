/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.ChatScreen
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.utils.animations.DecelerateValue;

@Feature(value={"betterminecraft"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\u0006J\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\u0006J\r\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u0006J\r\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\u0006J\r\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\u0006J\r\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0010R\u0014\u0010\u0014\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0016\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0010\u00ca\u0001\u0010\b\u0018\u0012\f\b\u0019\u0012\b\b\fJ\u0004\b\b(\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/BetterMinecraft;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "shouldAnimateChat", "()Z", "shouldAnimateTab", "shouldAnimateInventory", "shouldAnimateItemMove", "shouldAnimateHotbar", "shouldLiftHotbarOnChat", "shouldDisplaySaturation", "shouldRenderCapeWaves", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "chatAnimations", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "tabAnimation", "inventoryAnimation", "itemMoveAnimation", "hotbarAnimation", "hotbarChatLift", "saturationDisplay", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "betterminecraft", "rtx.kimiko:kimiko"})
public final class BetterMinecraft
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting chatAnimations = (BooleanSetting)this.register((Setting)new BooleanSetting("Анимации чата", "Анимирует новые сообщения и открытие поля ввода чата.", true));
    @NotNull
    private final BooleanSetting tabAnimation = (BooleanSetting)this.register((Setting)new BooleanSetting("Анимация таба", "Плавное появление и закрытие списка игроков.", true));
    @NotNull
    private final BooleanSetting inventoryAnimation = (BooleanSetting)this.register((Setting)new BooleanSetting("Анимация инвентаря", "Плавный выезд инвентаря и контейнеров при открытии.", true));
    @NotNull
    private final BooleanSetting itemMoveAnimation = (BooleanSetting)this.register((Setting)new BooleanSetting("Перетаскивание предметов", "Предметы плавно перелетают в новый слот при перекладывании во всех экранах.", true));
    @NotNull
    private final BooleanSetting hotbarAnimation = (BooleanSetting)this.register((Setting)new BooleanSetting("Анимация хотбара", "Плавно перемещает рамку выбранной ячейки влево и вправо.", true));
    @NotNull
    private final BooleanSetting hotbarChatLift = (BooleanSetting)this.register((Setting)new BooleanSetting("Хотбар при чате", "Плавно поднимает хотбар при открытии чата и опускает при закрытии.", true));
    @NotNull
    private final BooleanSetting saturationDisplay = (BooleanSetting)this.register((Setting)new BooleanSetting("Отображение насыщенности", "Показывает текущее насыщение над ванильной строкой еды.", true));
    @JvmField
    @Nullable
    public static BetterMinecraft INSTANCE;
    @NotNull
    private static final Identifier SATURATION_FULL_SPRITE;
    @NotNull
    private static final Identifier SATURATION_HALF_SPRITE;
    private static final float CHAT_LIFT_PX = 14.0f;
    private static final long HOTBAR_STALE_NANOS = 250000000L;
    @NotNull
    private static final DecelerateValue chatLift;
    @NotNull
    private static final DecelerateValue hotbarSelection;
    private static long hotbarSelectionFrameNanos;
    private static long inventoryOpenTime;

    public BetterMinecraft() {
        super("Better Minecraft", "Небольшие визуальные улучшения ванильного рендера.", Category.VISUALS);
        INSTANCE = this;
    }

    public final boolean shouldAnimateChat() {
        return this.isEnabled() && this.chatAnimations.getValue();
    }

    public final boolean shouldAnimateTab() {
        return this.isEnabled() && this.tabAnimation.getValue();
    }

    public final boolean shouldAnimateInventory() {
        return this.isEnabled() && this.inventoryAnimation.getValue();
    }

    public final boolean shouldAnimateItemMove() {
        return this.isEnabled() && this.itemMoveAnimation.getValue();
    }

    public final boolean shouldAnimateHotbar() {
        return this.isEnabled() && this.hotbarAnimation.getValue();
    }

    public final boolean shouldLiftHotbarOnChat() {
        return this.isEnabled() && this.hotbarChatLift.getValue();
    }

    public final boolean shouldDisplaySaturation() {
        return this.isEnabled() && this.saturationDisplay.getValue();
    }

    public final boolean shouldRenderCapeWaves() {
        return this.isEnabled();
    }

    @JvmStatic
    public static final boolean chatAnimationsEnabled() {
        return Companion.chatAnimationsEnabled();
    }

    @JvmStatic
    public static final boolean capeWavesEnabled() {
        return Companion.capeWavesEnabled();
    }

    @JvmStatic
    public static final boolean tabAnimationEnabled() {
        return Companion.tabAnimationEnabled();
    }

    @JvmStatic
    public static final boolean inventoryAnimationEnabled() {
        return Companion.inventoryAnimationEnabled();
    }

    @JvmStatic
    public static final boolean itemMoveAnimationEnabled() {
        return Companion.itemMoveAnimationEnabled();
    }

    @JvmStatic
    public static final boolean hotbarAnimationEnabled() {
        return Companion.hotbarAnimationEnabled();
    }

    @JvmStatic
    public static final boolean hotbarChatLiftEnabled() {
        return Companion.hotbarChatLiftEnabled();
    }

    @JvmStatic
    public static final void renderSaturation(@NotNull DrawContext graphics, @NotNull PlayerEntity player, int top, int right) {
        Companion.renderSaturation(graphics, player, top, right);
    }

    @JvmStatic
    public static final float chatHotbarLiftOffset() {
        return Companion.chatHotbarLiftOffset();
    }

    @JvmStatic
    public static final int animateHotbarSelectionX(int targetX) {
        return Companion.animateHotbarSelectionX(targetX);
    }

    @JvmStatic
    public static final void markInventoryOpen() {
        Companion.markInventoryOpen();
    }

    @JvmStatic
    public static final float inventorySlideOffset() {
        return Companion.inventorySlideOffset();
    }

    static {
        Identifier identifier2 = Identifier.ofVanilla((String)"hud/food_full");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"withDefaultNamespace(...)");
        SATURATION_FULL_SPRITE = identifier2;
        Identifier identifier3 = Identifier.ofVanilla((String)"hud/food_half");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"withDefaultNamespace(...)");
        SATURATION_HALF_SPRITE = identifier3;
        chatLift = new DecelerateValue(260);
        hotbarSelection = new DecelerateValue(180);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\t\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0013\u0010\f\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\nJ\u0013\u0010\r\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\r\u0010\nJ\u0013\u0010\u000e\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u000e\u0010\nJ\u0013\u0010\u000f\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u000f\u0010\nJ\u0013\u0010\u0010\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0010\u0010\nJ3\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001c\u001a\u00020\u001bH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0015H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\u00020\u0018H\u0007b\u0002\b\b\u00a2\u0006\u0004\b!\u0010\u0003J\u0013\u0010\"\u001a\u00020\u001bH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\"\u0010\u001dR\u001d\u0010$\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010*\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0016\u00103\u001a\u00020,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010.R\u0016\u00104\u001a\u00020,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u0010.\u00a8\u00065"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/BetterMinecraft.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/BetterMinecraft;", "instance", "()Lrtx/kimiko/api/modules/impl/Visuals/BetterMinecraft;", "", "Lkotlin/jvm/JvmStatic;", "chatAnimationsEnabled", "()Z", "capeWavesEnabled", "tabAnimationEnabled", "inventoryAnimationEnabled", "itemMoveAnimationEnabled", "hotbarAnimationEnabled", "hotbarChatLiftEnabled", "Lnet/minecraft/DrawContext;", "graphics", "Lnet/minecraft/PlayerEntity;", "player", "", "top", "right", "", "renderSaturation", "(Lnet/minecraft/DrawContext;Lnet/minecraft/PlayerEntity;II)V", "", "chatHotbarLiftOffset", "()F", "targetX", "animateHotbarSelectionX", "(I)I", "markInventoryOpen", "inventorySlideOffset", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/BetterMinecraft;", "Lnet/minecraft/Identifier;", "SATURATION_FULL_SPRITE", "Lnet/minecraft/Identifier;", "SATURATION_HALF_SPRITE", "CHAT_LIFT_PX", "F", "", "HOTBAR_STALE_NANOS", "J", "Lrtx/kimiko/utils/animations/DecelerateValue;", "chatLift", "Lrtx/kimiko/utils/animations/DecelerateValue;", "hotbarSelection", "hotbarSelectionFrameNanos", "inventoryOpenTime", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final BetterMinecraft instance() {
            BetterMinecraft cached = INSTANCE;
            if (cached != null) {
                return cached;
            }
            return ModuleManager.Companion.get().get(BetterMinecraft.class);
        }

        @JvmStatic
        public final boolean chatAnimationsEnabled() {
            BetterMinecraft module = this.instance();
            return module != null && module.shouldAnimateChat();
        }

        @JvmStatic
        public final boolean capeWavesEnabled() {
            BetterMinecraft module = this.instance();
            return module != null && module.shouldRenderCapeWaves();
        }

        @JvmStatic
        public final boolean tabAnimationEnabled() {
            BetterMinecraft module = this.instance();
            return module != null && module.shouldAnimateTab();
        }

        @JvmStatic
        public final boolean inventoryAnimationEnabled() {
            BetterMinecraft module = this.instance();
            return module != null && module.shouldAnimateInventory();
        }

        @JvmStatic
        public final boolean itemMoveAnimationEnabled() {
            BetterMinecraft module = this.instance();
            return module != null && module.shouldAnimateItemMove();
        }

        @JvmStatic
        public final boolean hotbarAnimationEnabled() {
            BetterMinecraft module = this.instance();
            return module != null && module.shouldAnimateHotbar();
        }

        @JvmStatic
        public final boolean hotbarChatLiftEnabled() {
            BetterMinecraft module = this.instance();
            return module != null && module.shouldLiftHotbarOnChat();
        }

        @JvmStatic
        public final void renderSaturation(@NotNull DrawContext graphics, @NotNull PlayerEntity player, int top, int right) {
            float units;
            Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
            Intrinsics.checkNotNullParameter((Object)player, (String)"player");
            BetterMinecraft module = this.instance();
            if (module == null || !module.shouldDisplaySaturation()) {
                return;
            }
            float saturation = player.getHungerManager().getSaturationLevel();
            int size = 9;
            for (int index = 0; index < 10 && !((units = saturation - (float)index * 2.0f) <= 0.0f); ++index) {
                Identifier sprite = units > 1.0f ? SATURATION_FULL_SPRITE : SATURATION_HALF_SPRITE;
                int x = right - index * 8 - 10 + (10 - size) / 2;
                graphics.drawGuiTexture(RenderPipelines.GUI_TEXTURED, sprite, x, top - size - 1, size, size);
            }
        }

        @JvmStatic
        public final float chatHotbarLiftOffset() {
            boolean lifted = this.hotbarChatLiftEnabled() && MinecraftClient.getInstance().currentScreen instanceof ChatScreen;
            return chatLift.update(lifted ? 14.0f : 0.0f);
        }

        @JvmStatic
        public final int animateHotbarSelectionX(int targetX) {
            long now = System.nanoTime();
            long elapsed = now - hotbarSelectionFrameNanos;
            hotbarSelectionFrameNanos = now;
            if (!this.hotbarAnimationEnabled() || elapsed <= 0L || elapsed > 250000000L) {
                hotbarSelection.snap(targetX);
                return targetX;
            }
            return Math.round(hotbarSelection.update(targetX));
        }

        @JvmStatic
        public final void markInventoryOpen() {
            inventoryOpenTime = System.currentTimeMillis();
        }

        @JvmStatic
        public final float inventorySlideOffset() {
            if (!this.inventoryAnimationEnabled()) {
                return 0.0f;
            }
            if (inventoryOpenTime == 0L) {
                inventoryOpenTime = System.currentTimeMillis();
            }
            float progress = Math.min(1.0f, (float)(System.currentTimeMillis() - inventoryOpenTime) / 350.0f);
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            float t = progress - 1.0f;
            float ease = 1.0f + c3 * t * t * t + c1 * t * t;
            return -50.0f * (1.0f - ease);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

