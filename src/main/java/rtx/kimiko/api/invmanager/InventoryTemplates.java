/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.item.ItemConvertible
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.registry.RegistryOps
 *  net.minecraft.registry.RegistryWrapper.WrapperLookup
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.registry.Registries
 *  net.minecraft.component.ComponentType
 *  net.minecraft.component.DataComponentTypes
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.invmanager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0003LMNB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000f\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0010J\u001f\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0019\u0010\u0012J\u001b\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001a\u0010\u0010J\u001b\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010!\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001dH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b!\u0010\"J\u001b\u0010#\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b#\u0010$J\u001d\u0010%\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001dH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b%\u0010\"J\u001b\u0010&\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b&\u0010$J7\u0010+\u001a\u00020\u000e\"\b\b\u0000\u0010'*\u00020\u00012\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u001d2\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000)H\u0002\u00a2\u0006\u0004\b+\u0010,J/\u00102\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u000201002\u0006\u0010-\u001a\u00020\u00052\u0006\u0010/\u001a\u00020.H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b2\u00103J\u0017\u00106\u001a\n\u0012\u0004\u0012\u000205\u0018\u000104H\u0002\u00a2\u0006\u0004\b6\u00107J\u0019\u00108\u001a\u0004\u0018\u0001052\u0006\u0010 \u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b8\u00109J\u000f\u0010:\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b:\u0010\u0016J\u000f\u0010;\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b;\u0010\u0003J\u000f\u0010<\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b<\u0010\u0003R\u0014\u0010>\u001a\u00020=8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010AR$\u0010D\u001a\u0012\u0012\u0004\u0012\u00020\u00050Bj\b\u0012\u0004\u0012\u00020\u0005`C8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020\t0F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0018\u0010I\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010AR\u0016\u0010J\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010K\u00a8\u0006O"}, d2={"Lrtx/kimiko/api/invmanager/InventoryTemplates;", "", "<init>", "()V", "", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "Lkotlin/jvm/JvmStatic;", "all", "()Ljava/util/List;", "", "name", "", "isFavorite", "(Ljava/lang/String;)Z", "", "toggleFavorite", "(Ljava/lang/String;)V", "byName", "(Ljava/lang/String;)Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "active", "()Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "activeName", "()Ljava/lang/String;", "setActive", "rawName", "captureCurrent", "delete", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "entry", "Lnet/minecraft/ItemStack;", "stackFor", "(Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;)Lnet/minecraft/ItemStack;", "stack", "stackKey", "(Lnet/minecraft/ItemStack;)Ljava/lang/String;", "entryKey", "(Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;)Ljava/lang/String;", "layoutKey", "entryLayoutKey", "T", "defaults", "Lnet/minecraft/ComponentType;", "component", "resetComponent", "(Lnet/minecraft/ItemStack;Lnet/minecraft/ItemStack;Lnet/minecraft/ComponentType;)V", "template", "Lnet/minecraft/PlayerInventory;", "inventory", "", "Lrtx/kimiko/api/invmanager/InventoryTemplates$MissingItem;", "missingFor", "(Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;Lnet/minecraft/PlayerInventory;)Ljava/util/Map;", "Lnet/minecraft/RegistryOps;", "Lcom/google/gson/JsonElement;", "jsonOps", "()Lnet/minecraft/RegistryOps;", "encode", "(Lnet/minecraft/ItemStack;)Lcom/google/gson/JsonElement;", "nextName", "ensureLoaded", "persist", "", "INVENTORY_SIZE", "I", "STORE", "Ljava/lang/String;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "templates", "Ljava/util/ArrayList;", "Ljava/util/LinkedHashSet;", "favorites", "Ljava/util/LinkedHashSet;", "activeTemplate", "loaded", "Z", "Entry", "Template", "MissingItem", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nInventoryTemplates.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InventoryTemplates.kt\nrtx/kimiko/api/invmanager/InventoryTemplates\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,361:1\n1174#2,2:362\n1#3:364\n*S KotlinDebug\n*F\n+ 1 InventoryTemplates.kt\nrtx/kimiko/api/invmanager/InventoryTemplates\n*L\n87#1:362,2\n*E\n"})
public final class InventoryTemplates {
    @NotNull
    public static final InventoryTemplates INSTANCE = new InventoryTemplates();
    public static final int INVENTORY_SIZE = 41;
    @NotNull
    private static final String STORE = "inventory_manager";
    @NotNull
    private static final ArrayList<Template> templates = new ArrayList();
    @NotNull
    private static final LinkedHashSet<String> favorites = new LinkedHashSet();
    @Nullable
    private static String activeTemplate;
    private static boolean loaded;

    private InventoryTemplates() {
    }

    @JvmStatic
    @NotNull
    public static final synchronized List<Template> all() {
        INSTANCE.ensureLoaded();
        ArrayList<Template> sorted = new ArrayList<>(templates);
        if (sorted.size() > 1) {
            sorted.sort(Comparator.comparingInt(it -> favorites.contains(it.name) ? 0 : 1));
        }
        return sorted;
    }

    @JvmStatic
    public static final synchronized boolean isFavorite(@Nullable String name) {
        INSTANCE.ensureLoaded();
        return name != null && favorites.contains(name);
    }

    @JvmStatic
    public static final synchronized void toggleFavorite(@Nullable String name) {
        INSTANCE.ensureLoaded();
        if (name == null || InventoryTemplates.byName(name) == null) {
            return;
        }
        if (!favorites.remove(name)) {
            favorites.add(name);
        }
        INSTANCE.persist();
    }

    @JvmStatic
    @Nullable
    public static final synchronized Template byName(@Nullable String name) {
        INSTANCE.ensureLoaded();
        for (Template template : templates) {
            if (Intrinsics.areEqual(template.name, name)) {
                return template;
            }
        }
        return null;
    }

    @JvmStatic
    @Nullable
    public static final synchronized Template active() {
        INSTANCE.ensureLoaded();
        return activeTemplate == null ? null : InventoryTemplates.byName(activeTemplate);
    }

    @JvmStatic
    @Nullable
    public static final synchronized String activeName() {
        INSTANCE.ensureLoaded();
        return activeTemplate;
    }

    @JvmStatic
    public static final synchronized void setActive(@Nullable String name) {
        INSTANCE.ensureLoaded();
        activeTemplate = name != null && InventoryTemplates.byName(name) != null ? name : null;
        INSTANCE.persist();
    }

    @JvmStatic
    @Nullable
    public static final synchronized Template captureCurrent(@Nullable String rawName) {
        INSTANCE.ensureLoaded();
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        if (clientPlayerEntity2 == null) {
            return null;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        String name = rawName == null || StringsKt.isBlank((CharSequence)rawName) ? INSTANCE.nextName() : ((Object)StringsKt.trim((CharSequence)rawName)).toString();
        PlayerInventory playerInventory2 = player.getInventory();
        Intrinsics.checkNotNullExpressionValue((Object)playerInventory2, (String)"getInventory(...)");
        PlayerInventory inventory = playerInventory2;
        HashMap<Integer, Entry> slots = new HashMap<>();
        for (int i = 0; i < 41; ++i) {
            ItemStack stack = inventory.getStack(i);
            if (stack == null || stack.isEmpty()) continue;
            String itemId = Registries.ITEM.getId(stack.getItem()).toString();
            slots.put(i, new Entry(itemId, stack.getCount(), INSTANCE.encode(stack)));
        }
        if (slots.isEmpty()) {
            return null;
        }
        templates.removeIf(it -> Objects.equals(it.name, name));
        Template template = new Template(name, slots);
        templates.add(template);
        INSTANCE.persist();
        return template;
    }

    @JvmStatic
    public static final synchronized void delete(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        templates.removeIf(it -> Objects.equals(it.name, name));
        favorites.remove(name);
        if (Intrinsics.areEqual((Object)name, (Object)activeTemplate)) {
            activeTemplate = null;
        }
        INSTANCE.persist();
    }

    @JvmStatic
    @NotNull
    public static final ItemStack stackFor(@NotNull Entry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        return entry.resolve();
    }

    @JvmStatic
    @NotNull
    public static final String stackKey(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "";
        }
        return Registries.ITEM.getId(stack.getItem()).toString() + "#" + ItemStack.hashCode((ItemStack)stack);
    }

    @JvmStatic
    @NotNull
    public static final String entryKey(@NotNull Entry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        return entry.key();
    }

    @JvmStatic
    @NotNull
    public static final String layoutKey(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "";
        }
        if (stack.contains(DataComponentTypes.CUSTOM_NAME)) {
            return Registries.ITEM.getId(stack.getItem()).toString() + "#name:" + stack.getName().getString();
        }
        ItemStack itemStack2 = stack.copy();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"copy(...)");
        ItemStack normalized = itemStack2;
        ItemStack defaults = new ItemStack((ItemConvertible)stack.getItem());
        ComponentType componentType2 = DataComponentTypes.DAMAGE;
        Intrinsics.checkNotNullExpressionValue((Object)componentType2, (String)"DAMAGE");
        INSTANCE.resetComponent(normalized, defaults, componentType2);
        ComponentType componentType3 = DataComponentTypes.REPAIR_COST;
        Intrinsics.checkNotNullExpressionValue((Object)componentType3, (String)"REPAIR_COST");
        INSTANCE.resetComponent(normalized, defaults, componentType3);
        ComponentType componentType4 = DataComponentTypes.CHARGED_PROJECTILES;
        Intrinsics.checkNotNullExpressionValue((Object)componentType4, (String)"CHARGED_PROJECTILES");
        INSTANCE.resetComponent(normalized, defaults, componentType4);
        return InventoryTemplates.stackKey(normalized);
    }

    @JvmStatic
    @NotNull
    public static final String entryLayoutKey(@NotNull Entry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        return entry.layoutKey();
    }

    private final <T> void resetComponent(ItemStack stack, ItemStack defaults, ComponentType<T> component) {
        T value = defaults.get(component);
        if (value == null) {
            stack.remove(component);
        } else {
            stack.set(component, value);
        }
    }

    @JvmStatic
    @NotNull
    public static final Map<String, MissingItem> missingFor(@NotNull Template template, @NotNull PlayerInventory inventory) {
        Intrinsics.checkNotNullParameter((Object)template, (String)"template");
        Intrinsics.checkNotNullParameter((Object)inventory, (String)"inventory");
        LinkedHashMap<String, MissingItem> expected = new LinkedHashMap<>();
        for (Entry entry : template.slots.values()) {
            String key = entry.layoutKey();
            if (((CharSequence)key).length() == 0) continue;
            MissingItem missing = expected.get(key);
            if (missing == null) {
                expected.put(key, new MissingItem(entry.resolve(), entry.count()));
                continue;
            }
            missing.count += entry.count();
        }
        for (int i = 0; i < 41; ++i) {
            ItemStack stack = inventory.getStack(i);
            MissingItem missing;
            if (stack.isEmpty() || (missing = expected.get(InventoryTemplates.layoutKey(stack))) == null) continue;
            missing.count -= stack.getCount();
        }
        expected.values().removeIf(it -> it.count <= 0);
        return expected;
    }

    private final RegistryOps<JsonElement> jsonOps() {
        MinecraftClient mc = MinecraftClient.getInstance();
        RegistryWrapper.WrapperLookup provider = null;
        ClientPlayNetworkHandler connection = mc.getNetworkHandler();
        if (connection != null) {
            provider = (RegistryWrapper.WrapperLookup)connection.getRegistryManager();
        } else if (mc.world != null) {
            provider = (RegistryWrapper.WrapperLookup)mc.world.getRegistryManager();
        }
        return provider != null ? provider.getOps(JsonOps.INSTANCE) : null;
    }

    private final JsonElement encode(ItemStack stack) {
        RegistryOps<JsonElement> ops = this.jsonOps();
        if (ops == null) {
            return null;
        }
        return ItemStack.CODEC.encodeStart(ops, stack).result().orElse(null);
    }

    private final String nextName() {
        int index = 1;
        while (InventoryTemplates.byName("inv" + index) != null) {
            ++index;
        }
        return "inv" + index;
    }

    private final void ensureLoaded() {
        if (loaded) {
            return;
        }
        loaded = true;
        try {
            JsonObject root = RepositoryStorage.readObject(STORE);
            if (root.has("active") && root.get("active").isJsonPrimitive()) {
                activeTemplate = root.get("active").getAsString();
            }
            if (root.has("saves") && root.get("saves").isJsonArray()) {
                for (JsonElement element : root.getAsJsonArray("saves")) {
                    if (!element.isJsonObject()) continue;
                    JsonObject save = element.getAsJsonObject();
                    String name = save.get("name").getAsString();
                    HashMap<Integer, Entry> slots = new HashMap<>();
                    if (save.has("slots") && save.get("slots").isJsonArray()) {
                        for (JsonElement slotElement : save.getAsJsonArray("slots")) {
                            if (!slotElement.isJsonObject()) continue;
                            JsonObject slot = slotElement.getAsJsonObject();
                            JsonElement item = slot.has("item") ? slot.get("item") : null;
                            int slotIndex = slot.get("i").getAsInt();
                            String slotId = slot.has("id") ? slot.get("id").getAsString() : "";
                            int count = slot.has("count") ? slot.get("count").getAsInt() : 1;
                            slots.put(slotIndex, new Entry(slotId, count, item));
                        }
                    }
                    templates.add(new Template(name, slots));
                }
            }
            if (root.has("favorites") && root.get("favorites").isJsonArray()) {
                for (JsonElement element : root.getAsJsonArray("favorites")) {
                    if (!element.isJsonPrimitive()) continue;
                    favorites.add(element.getAsString());
                }
            }
            favorites.removeIf(fav -> InventoryTemplates.byName(fav) == null);
            if (activeTemplate != null && InventoryTemplates.byName(activeTemplate) == null) {
                activeTemplate = null;
            }
        }
        catch (Throwable throwable) {
            templates.clear();
            favorites.clear();
            activeTemplate = null;
        }
    }

    private final void persist() {
        try {
            JsonObject root = new JsonObject();
            String string = activeTemplate;
            if (string != null) {
                String it = string;
                boolean bl = false;
                root.addProperty("active", it);
            }
            JsonArray saves = new JsonArray();
            for (Template template : templates) {
                JsonObject save = new JsonObject();
                save.addProperty("name", template.name);
                JsonArray slots = new JsonArray();
                for (Map.Entry<Integer, Entry> entry : template.slots.entrySet()) {
                    int key = entry.getKey();
                    Entry value = entry.getValue();
                    JsonObject slot = new JsonObject();
                    slot.addProperty("i", key);
                    slot.addProperty("id", value.getItemId$rtx_kimiko_kimiko());
                    slot.addProperty("count", value.count());
                    if (value.getStackJson$rtx_kimiko_kimiko() != null) {
                        slot.add("item", value.getStackJson$rtx_kimiko_kimiko());
                    }
                    slots.add(slot);
                }
                save.add("slots", slots);
                saves.add(save);
            }
            root.add("saves", (JsonElement)saves);
            JsonArray favoriteNames = new JsonArray();
            Iterator iterator3 = favorites.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator3, (String)"iterator(...)");
            Iterator iterator4 = iterator3;
            while (iterator4.hasNext()) {
                Object e = iterator4.next();
                Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
                String name = (String)e;
                favoriteNames.add(name);
            }
            root.add("favorites", (JsonElement)favoriteNames);
            RepositoryStorage.write(STORE, root);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static final boolean captureCurrent$lambda$0(String $name, Template it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return Intrinsics.areEqual((Object)it.name, (Object)$name);
    }

    private static final boolean captureCurrent$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean delete$lambda$0(String $name, Template it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return Intrinsics.areEqual((Object)it.name, (Object)$name);
    }

    private static final boolean delete$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean missingFor$lambda$0(Map.Entry it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return ((MissingItem)it.getValue()).count <= 0 || ((MissingItem)it.getValue()).icon.isEmpty();
    }

    private static final boolean missingFor$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean ensureLoaded$lambda$0(String it) {
        return InventoryTemplates.byName(it) == null;
    }

    private static final boolean ensureLoaded$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    public static final /* synthetic */ LinkedHashSet access$getFavorites$p() {
        return favorites;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u000eR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0005\u0010\u0015R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0010R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0010\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "", "", "itemId", "", "count", "Lcom/google/gson/JsonElement;", "stackJson", "<init>", "(Ljava/lang/String;ILcom/google/gson/JsonElement;)V", "Lnet/minecraft/ItemStack;", "resolve", "()Lnet/minecraft/ItemStack;", "key", "()Ljava/lang/String;", "layoutKey", "Ljava/lang/String;", "getItemId$rtx_kimiko_kimiko", "Lkotlin/jvm/JvmName;", "name", "I", "()I", "Lcom/google/gson/JsonElement;", "getStackJson$rtx_kimiko_kimiko", "()Lcom/google/gson/JsonElement;", "cachedStack", "Lnet/minecraft/ItemStack;", "cachedKey", "cachedLayoutKey", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nInventoryTemplates.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InventoryTemplates.kt\nrtx/kimiko/api/invmanager/InventoryTemplates$Entry\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,361:1\n1#2:362\n*E\n"})
    public static final class Entry {
        @NotNull
        private final String itemId;
        private final int count;
        @Nullable
        private final JsonElement stackJson;
        @Nullable
        private ItemStack cachedStack;
        @Nullable
        private String cachedKey;
        @Nullable
        private String cachedLayoutKey;

        public Entry(@NotNull String itemId, int count, @Nullable JsonElement stackJson) {
            Intrinsics.checkNotNullParameter((Object)itemId, (String)"itemId");
            this.itemId = itemId;
            this.count = count;
            this.stackJson = stackJson;
        }

        @NotNull
        public final String getItemId$rtx_kimiko_kimiko() {
            return this.itemId;
        }

        @JvmName(name="count")
        public final int count() {
            return this.count;
        }

        @Nullable
        public final JsonElement getStackJson$rtx_kimiko_kimiko() {
            return this.stackJson;
        }

        @NotNull
        public final ItemStack resolve() {
            if (this.cachedStack != null) {
                return this.cachedStack;
            }
            ItemStack stack = ItemStack.EMPTY;
            RegistryOps<JsonElement> ops;
            if (this.stackJson != null && (ops = INSTANCE.jsonOps()) != null) {
                stack = ItemStack.CODEC.parse(ops, this.stackJson).result().orElse(ItemStack.EMPTY);
            }
            if (stack.isEmpty()) {
                Item item = Registries.ITEM.getOptionalValue(Identifier.tryParse(this.itemId)).orElse(Items.AIR);
                if (item != Items.AIR) {
                    stack = new ItemStack(item, this.count);
                }
            }
            if (!stack.isEmpty()) {
                this.cachedStack = stack;
            }
            return stack;
        }

        @NotNull
        public final String key() {
            String string = this.cachedKey;
            if (string != null) {
                String it = string;
                boolean bl = false;
                return it;
            }
            String key = InventoryTemplates.stackKey(this.resolve());
            if (((CharSequence)key).length() > 0) {
                this.cachedKey = key;
            }
            return key;
        }

        @NotNull
        public final String layoutKey() {
            if (this.cachedLayoutKey == null) {
                this.cachedLayoutKey = InventoryTemplates.layoutKey(this.resolve());
            }
            String string = this.cachedLayoutKey;
            Intrinsics.checkNotNull((Object)string);
            return string;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\tR\u001b\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/invmanager/InventoryTemplates$MissingItem;", "", "Lnet/minecraft/ItemStack;", "icon", "", "count", "<init>", "(Lnet/minecraft/ItemStack;I)V", "Lkotlin/jvm/JvmField;", "Lnet/minecraft/ItemStack;", "I", "rtx.kimiko:kimiko"})
    public static final class MissingItem {
        @JvmField
        @NotNull
        public final ItemStack icon;
        @JvmField
        public int count;

        public MissingItem(@NotNull ItemStack icon, int count) {
            Intrinsics.checkNotNullParameter((Object)icon, (String)"icon");
            this.icon = icon;
            this.count = count;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\n\u0010\u000bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\f\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\rR%\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\f\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "", "", "name", "", "", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "slots", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "itemCount", "()I", "Lkotlin/jvm/JvmField;", "Ljava/lang/String;", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
    public static final class Template {
        @JvmField
        @NotNull
        public final String name;
        @JvmField
        @NotNull
        public final Map<Integer, Entry> slots;

        public Template(@NotNull String name, @NotNull Map<Integer, Entry> slots) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter(slots, (String)"slots");
            this.name = name;
            this.slots = slots;
        }

        public final int itemCount() {
            return this.slots.size();
        }
    }
}

