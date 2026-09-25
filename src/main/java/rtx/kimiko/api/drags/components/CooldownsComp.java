/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.item.Item
 *  net.minecraft.entity.player.ItemCooldownManager
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.network.packet.s2c.play.CooldownUpdateS2CPacket
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.CooldownUpdateS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.components.ListHudComp;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.network.PacketEvent;
import rtx.kimiko.api.modules.impl.Interface.CooldownsModule;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0002 \u001fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0003b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0014\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R \u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/drags/components/CooldownsComp;", "Lrtx/kimiko/api/drags/components/ListHudComp;", "<init>", "()V", "", "headerIconGlyph", "()Ljava/lang/String;", "Lrtx/kimiko/api/events/impl/network/PacketEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onPacket", "(Lrtx/kimiko/api/events/impl/network/PacketEvent;)V", "", "Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "collectRows", "()Ljava/util/List;", "", "now", "previewRow", "(J)Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "", "Lnet/minecraft/Identifier;", "Lrtx/kimiko/api/drags/components/CooldownsComp$Cooldown;", "cooldowns", "Ljava/util/Map;", "previewSwitchMs", "J", "", "previewIndex", "I", "Companion", "Cooldown", "rtx.kimiko:kimiko"})
public final class CooldownsComp
extends ListHudComp {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<Identifier, Cooldown> cooldowns = new ConcurrentHashMap();
    private long previewSwitchMs;
    private int previewIndex;
    @NotNull
    private static final Item[] PREVIEW_ITEMS;
    private static final float ITEM_SCALE = 0.5f;
    private static final long STACK_RESOLVE_MS = 250L;
    @NotNull
    private static final Regex REGEX_BRACKETS;
    @NotNull
    private static final Regex REGEX_TAGS;
    @NotNull
    private static final Regex REGEX_BRACES;
    @NotNull
    private static final Regex REGEX_SPACES;

    public CooldownsComp() {
        super("cooldowns", "Cooldowns", CooldownsModule.class, 118.0f, 82.0f);
        EventBus.Companion.get().subscribe(this);
    }

    @Override
    @NotNull
    protected String headerIconGlyph() {
        return "i";
    }

    @EventHandler
    private final void onPacket(PacketEvent event) {
        if (!event.isReceive()) {
            return;
        }
        Packet<?> packet2 = event.getPacket();
        CooldownUpdateS2CPacket cooldownUpdateS2CPacket2 = packet2 instanceof CooldownUpdateS2CPacket ? (CooldownUpdateS2CPacket)packet2 : null;
        if (cooldownUpdateS2CPacket2 == null) {
            return;
        }
        CooldownUpdateS2CPacket packet = cooldownUpdateS2CPacket2;
        Identifier identifier2 = packet.cooldownGroup();
        if (identifier2 == null) {
            return;
        }
        Identifier group = identifier2;
        if (packet.cooldown() <= 0) {
            this.cooldowns.remove(group);
            return;
        }
        long endMs = System.currentTimeMillis() + (long)packet.cooldown() * 50L;
        this.cooldowns.put(group, new Cooldown(group, endMs));
    }

    @Override
    @NotNull
    protected List<ListHudComp.Row> collectRows() {
        long now = System.currentTimeMillis();
        if (MinecraftClient.getInstance().world == null) {
            this.cooldowns.clear();
        }
        ArrayList<ListHudComp.Row> rows = new ArrayList<ListHudComp.Row>();
        Iterator<Cooldown> iterator = this.cooldowns.values().iterator();
        while (iterator.hasNext()) {
            Cooldown cooldown = iterator.next();
            long remaining = cooldown.getEndMs() - now;
            if (remaining <= 0L) {
                iterator.remove();
                continue;
            }
            ItemStack stack = cooldown.getCachedStack();
            if (stack == null || stack.isEmpty() || now - cooldown.getResolvedAtMs() >= 250L) {
                stack = CooldownsComp.Companion.resolveCooldownStack(cooldown.getGroup());
                cooldown.setResolvedAtMs(now);
                if (stack.isEmpty()) {
                    cooldown.setCachedStack(null);
                    cooldown.setCachedName(null);
                    cooldown.setCachedIcon(null);
                    continue;
                }
                if (stack != cooldown.getCachedStack()) {
                    ItemStack resolved = stack;
                    cooldown.setCachedStack(resolved);
                    cooldown.setCachedName(CooldownsComp.Companion.cleanName(resolved.getName().getString()));
                    cooldown.setCachedIcon((arg_0, arg_1, arg_2, arg_3, arg_4) -> CooldownsComp.collectRows$lambda$0(resolved, arg_0, arg_1, arg_2, arg_3, arg_4));
                }
            }
            String name = cooldown.getCachedName();
            ListHudComp.IconDrawer icon = cooldown.getCachedIcon();
            if (name == null || icon == null) continue;
            rows.add(new ListHudComp.Row(cooldown.getGroup(), name, CooldownsComp.Companion.formatRemaining(remaining), icon, null, 0, 48, null));
        }
        if (rows.isEmpty() && DragSystem.Companion.get().isDragModeActive()) {
            rows.add(this.previewRow(now));
        }
        return rows;
    }

    private final ListHudComp.Row previewRow(long now) {
        if (now - this.previewSwitchMs >= 1000L) {
            this.previewIndex = (this.previewIndex + 1) % PREVIEW_ITEMS.length;
            this.previewSwitchMs = now;
        }
        ItemStack itemStack2 = PREVIEW_ITEMS[this.previewIndex].getDefaultStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getDefaultInstance(...)");
        ItemStack stack = itemStack2;
        return new ListHudComp.Row("preview", "Example cooldown", "**:**", (arg_0, arg_1, arg_2, arg_3, arg_4) -> CooldownsComp.previewRow$lambda$0(stack, arg_0, arg_1, arg_2, arg_3, arg_4), null, 0, 48, null);
    }

    private static final void collectRows$lambda$0(ItemStack $resolved, DrawContext graphics, float x, float y, float size, float alpha) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        CooldownsComp.Companion.drawItemIcon(graphics, $resolved, x, y, size, alpha);
    }

    private static final void previewRow$lambda$0(ItemStack $stack, DrawContext graphics, float x, float y, float size, float alpha) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        CooldownsComp.Companion.drawItemIcon(graphics, $stack, x, y, size, alpha);
    }

    static {
        Item[] class_1792Array = new Item[3];
        Intrinsics.checkNotNullExpressionValue((Object)Items.ENDER_PEARL, (String)"ENDER_PEARL");
        Intrinsics.checkNotNullExpressionValue((Object)Items.CHORUS_FRUIT, (String)"CHORUS_FRUIT");
        Intrinsics.checkNotNullExpressionValue((Object)Items.MACE, (String)"MACE");
        PREVIEW_ITEMS = class_1792Array;
        REGEX_BRACKETS = new Regex("\\[[^\\[\\]]*\\]");
        REGEX_TAGS = new Regex("<[^<>]*>");
        REGEX_BRACES = new Regex("\\{[^{}]*\\}");
        REGEX_SPACES = new Regex("\\s+");
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJA\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0016\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010&R\u0014\u0010)\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010&\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/drags/components/CooldownsComp.Companion;", "", "<init>", "()V", "Lnet/minecraft/Identifier;", "group", "Lnet/minecraft/ItemStack;", "resolveCooldownStack", "(Lnet/minecraft/Identifier;)Lnet/minecraft/ItemStack;", "Lnet/minecraft/DrawContext;", "graphics", "stack", "", "x", "y", "size", "alpha", "", "drawItemIcon", "(Lnet/minecraft/DrawContext;Lnet/minecraft/ItemStack;FFFF)V", "", "raw", "cleanName", "(Ljava/lang/String;)Ljava/lang/String;", "", "remainingMs", "formatRemaining", "(J)Ljava/lang/String;", "", "Lnet/minecraft/Item;", "PREVIEW_ITEMS", "[Lnet/minecraft/Item;", "ITEM_SCALE", "F", "STACK_RESOLVE_MS", "J", "Lkotlin/text/Regex;", "REGEX_BRACKETS", "Lkotlin/text/Regex;", "REGEX_TAGS", "REGEX_BRACES", "REGEX_SPACES", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final ItemStack resolveCooldownStack(Identifier group) {
            ClientPlayerEntity clientPlayerEntity2 = MinecraftClient.getInstance().player;
            if (clientPlayerEntity2 == null) {
                ItemStack itemStack2 = ItemStack.EMPTY;
                Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
                return itemStack2;
            }
            ClientPlayerEntity player = clientPlayerEntity2;
            ItemCooldownManager itemCooldownManager2 = player.getItemCooldownManager();
            Intrinsics.checkNotNullExpressionValue((Object)itemCooldownManager2, (String)"getCooldowns(...)");
            ItemCooldownManager cooldowns = itemCooldownManager2;
            PlayerInventory playerInventory2 = player.getInventory();
            Intrinsics.checkNotNullExpressionValue((Object)playerInventory2, (String)"getInventory(...)");
            PlayerInventory inventory = playerInventory2;
            int size = inventory.size();
            for (int i = 0; i < size; ++i) {
                ItemStack stack = (ItemStack) (inventory.getStack(i));
                if (stack.isEmpty() || !Intrinsics.areEqual((Object)group, (Object)cooldowns.getGroup(stack))) continue;
                return stack;
            }
            ItemStack itemStack3 = ItemStack.EMPTY;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"EMPTY");
            return itemStack3;
        }

        private final void drawItemIcon(DrawContext graphics, ItemStack stack, float x, float y, float size, float alpha) {
            float appear = Math.max(0.0f, Math.min(1.0f, alpha));
            if (stack == null || stack.isEmpty() || appear <= 0.01f) {
                return;
            }
            float scale = 0.5f * appear;
            float cx = x + size * 0.5f;
            float cy = y + size * 0.5f - 0.5f;
            graphics.getMatrices().pushMatrix();
            Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
            Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
            Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
            graphics.getMatrices().translate(cx, cy);
            graphics.getMatrices().scale(scale, scale);
            graphics.getMatrices().translate(-8.0f, -8.0f);
            graphics.drawItem(stack, 0, 0);
            graphics.getMatrices().popMatrix();
        }

        private final String cleanName(String raw) {
            if (raw == null) {
                return "";
            }
            CharSequence charSequence = raw;
            Regex regex = REGEX_BRACKETS;
            String string = " ";
            charSequence = regex.replace(charSequence, string);
            regex = REGEX_TAGS;
            string = " ";
            charSequence = regex.replace(charSequence, string);
            regex = REGEX_BRACES;
            string = " ";
            charSequence = regex.replace(charSequence, string);
            regex = REGEX_SPACES;
            string = " ";
            return ((Object)StringsKt.trim((CharSequence)regex.replace(charSequence, string))).toString();
        }

        private final String formatRemaining(long remainingMs) {
            float seconds = (float)remainingMs / 1000.0f;
            if (seconds >= 60.0f) {
                int total = MathKt.roundToInt((float)seconds);
                int n = total / 60;
                Locale locale = Locale.ROOT;
                String string = "%02d";
                Object[] objectArray = new Object[]{total % 60};
                String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
                return n + ":" + string2;
            }
            Locale locale = Locale.ROOT;
            String string = "%.1fs";
            Object[] objectArray = new Object[]{Float.valueOf(seconds)};
            String string3 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
            return string3;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010#\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\r\"\u0004\b%\u0010&\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/drags/components/CooldownsComp$Cooldown;", "", "Lnet/minecraft/Identifier;", "group", "", "endMs", "<init>", "(Lnet/minecraft/Identifier;J)V", "Lnet/minecraft/Identifier;", "getGroup", "()Lnet/minecraft/Identifier;", "J", "getEndMs", "()J", "Lnet/minecraft/ItemStack;", "cachedStack", "Lnet/minecraft/ItemStack;", "getCachedStack", "()Lnet/minecraft/ItemStack;", "setCachedStack", "(Lnet/minecraft/ItemStack;)V", "", "cachedName", "Ljava/lang/String;", "getCachedName", "()Ljava/lang/String;", "setCachedName", "(Ljava/lang/String;)V", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "cachedIcon", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "getCachedIcon", "()Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "setCachedIcon", "(Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;)V", "resolvedAtMs", "getResolvedAtMs", "setResolvedAtMs", "(J)V", "rtx.kimiko:kimiko"})
    private static final class Cooldown {
        @NotNull
        private final Identifier group;
        private final long endMs;
        @Nullable
        private ItemStack cachedStack;
        @Nullable
        private String cachedName;
        @Nullable
        private ListHudComp.IconDrawer cachedIcon;
        private long resolvedAtMs;

        public Cooldown(@NotNull Identifier group, long endMs) {
            Intrinsics.checkNotNullParameter((Object)group, (String)"group");
            this.group = group;
            this.endMs = endMs;
        }

        @NotNull
        public final Identifier getGroup() {
            return this.group;
        }

        public final long getEndMs() {
            return this.endMs;
        }

        @Nullable
        public final ItemStack getCachedStack() {
            return this.cachedStack;
        }

        public final void setCachedStack(@Nullable ItemStack itemStack2) {
            this.cachedStack = itemStack2;
        }

        @Nullable
        public final String getCachedName() {
            return this.cachedName;
        }

        public final void setCachedName(@Nullable String string) {
            this.cachedName = string;
        }

        @Nullable
        public final ListHudComp.IconDrawer getCachedIcon() {
            return this.cachedIcon;
        }

        public final void setCachedIcon(@Nullable ListHudComp.IconDrawer iconDrawer) {
            this.cachedIcon = iconDrawer;
        }

        public final long getResolvedAtMs() {
            return this.resolvedAtMs;
        }

        public final void setResolvedAtMs(long l) {
            this.resolvedAtMs = l;
        }
    }
}

