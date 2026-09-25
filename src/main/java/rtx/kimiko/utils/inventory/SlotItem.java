/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.effect.StatusEffect
 *  net.minecraft.entity.effect.StatusEffectInstance
 *  net.minecraft.entity.effect.StatusEffects
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.component.type.PotionContentsComponent
 *  net.minecraft.text.Text
 *  net.minecraft.registry.entry.RegistryEntry
 *  net.minecraft.component.type.LoreComponent
 *  net.minecraft.component.DataComponentTypes
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.inventory;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.text.Text;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.component.DataComponentTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0003\u001b\u001c\u001aB'\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0019j\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#\u00a8\u0006$"}, d2={"Lrtx/kimiko/utils/inventory/SlotItem;", "", "", "displayName", "", "defaultColor", "", "Lrtx/kimiko/utils/inventory/SlotItem$EffectSignature;", "effectSignatures", "<init>", "(Ljava/lang/String;ILjava/lang/String;ILjava/util/List;)V", "Lrtx/kimiko/utils/inventory/SlotItem$Group;", "getGroup", "()Lrtx/kimiko/utils/inventory/SlotItem$Group;", "getDisplayName", "()Ljava/lang/String;", "getDefaultColor", "()I", "Lnet/minecraft/ItemStack;", "stack", "", "matchesEffects", "(Lnet/minecraft/ItemStack;)Z", "Ljava/lang/String;", "I", "Ljava/util/List;", "Companion", "Group", "EffectSignature", "POTION_HLOPUSHKA", "POTION_RADIATION", "POTION_SLEEP", "POTION_HOLY_WATER", "POTION_RAGE", "POTION_PALADIN", "POTION_ASSASSIN", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nSlotItem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SlotItem.kt\nrtx/kimiko/utils/inventory/SlotItem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,123:1\n2945#2,3:124\n*S KotlinDebug\n*F\n+ 1 SlotItem.kt\nrtx/kimiko/utils/inventory/SlotItem\n*L\n78#1:124,3\n*E\n"})
public enum SlotItem {
        POTION_HLOPUSHKA("Хлопушка", 0xFF3232, List.of(new EffectSignature(StatusEffects.SLOWNESS, 200, 9), new EffectSignature(StatusEffects.SPEED, 400, 4), new EffectSignature(StatusEffects.BLINDNESS, 100, 9), new EffectSignature(StatusEffects.GLOWING, 3600, 0))),
        POTION_RADIATION("Радиация", 0xFF3232, List.of(new EffectSignature(StatusEffects.POISON, 1200, 1), new EffectSignature(StatusEffects.WITHER, 1200, 1), new EffectSignature(StatusEffects.SLOWNESS, 1800, 2), new EffectSignature(StatusEffects.HUNGER, 1200, 4), new EffectSignature(StatusEffects.GLOWING, 2400, 0))),
        POTION_SLEEP("Снотворное", 0xFF3232, List.of(new EffectSignature(StatusEffects.WEAKNESS, 1800, 1), new EffectSignature(StatusEffects.MINING_FATIGUE, 200, 1), new EffectSignature(StatusEffects.WITHER, 1800, 2), new EffectSignature(StatusEffects.BLINDNESS, 200, 0))),
        POTION_HOLY_WATER("Святая вода", 3329330, List.of(new EffectSignature(StatusEffects.REGENERATION, 900, 1), new EffectSignature(StatusEffects.INVISIBILITY, 12000, 1), new EffectSignature(StatusEffects.INSTANT_HEALTH, 0, 1))),
        POTION_RAGE("Гнева", 3329330, List.of(new EffectSignature(StatusEffects.STRENGTH, 600, 4), new EffectSignature(StatusEffects.SLOWNESS, 600, 3))),
        POTION_PALADIN("Палладина", 3329330, List.of(new EffectSignature(StatusEffects.RESISTANCE, 12000, 0), new EffectSignature(StatusEffects.FIRE_RESISTANCE, 12000, 0), new EffectSignature(StatusEffects.HEALTH_BOOST, 1200, 2), new EffectSignature(StatusEffects.INVISIBILITY, 18000, 0))),
        POTION_ASSASSIN("Ассасина", 3329330, List.of(new EffectSignature(StatusEffects.STRENGTH, 1200, 3), new EffectSignature(StatusEffects.SPEED, 6000, 2), new EffectSignature(StatusEffects.HASTE, 1200, 0), new EffectSignature(StatusEffects.INSTANT_DAMAGE, 0, 1)));
@NotNull
    public static final Companion Companion;
    @NotNull
    private final String displayName;
    private final int defaultColor;
    @NotNull
    private final List<EffectSignature> effectSignatures;
    
    
    
    
    
    
    

    private SlotItem(String displayName, int defaultColor, List<EffectSignature> effectSignatures) {
        this.displayName = displayName;
        this.defaultColor = defaultColor;
        this.effectSignatures = effectSignatures;
    }

    @NotNull
    public final Group getGroup() {
        return Group.POTION;
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    public final int getDefaultColor() {
        return this.defaultColor;
    }

    private final boolean matchesEffects(ItemStack stack) {
        PotionContentsComponent potionContentsComponent2 = (PotionContentsComponent)stack.get(DataComponentTypes.POTION_CONTENTS);
        if (potionContentsComponent2 == null) {
            return false;
        }
        PotionContentsComponent contents = potionContentsComponent2;
        Iterable iterable = contents.getEffects();
        Intrinsics.checkNotNullExpressionValue((Object)iterable, (String)"getAllEffects(...)");
        List effects = CollectionsKt.toList((Iterable)iterable);
        if (effects.size() != this.effectSignatures.size()) {
            return false;
        }
        for (EffectSignature signature : this.effectSignatures) {
            boolean bl;
            block6: {
                Iterable $this$none$iv = effects;
                boolean $i$f$none = false;
                if ($this$none$iv instanceof Collection && ((Collection)$this$none$iv).isEmpty()) {
                    bl = true;
                } else {
                    for (Object element$iv : $this$none$iv) {
                        StatusEffectInstance it = (StatusEffectInstance)element$iv;
                        boolean bl2 = false;
                        Intrinsics.checkNotNull((Object)it);
                        if (!signature.matches(it)) continue;
                        bl = false;
                        break block6;
                    }
                    bl = true;
                }
            }
            if (!bl) continue;
            return false;
        }
        return true;
    }

    

    

    @NotNull
    public static EnumEntries<SlotItem> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    @JvmStatic
    @Nullable
    public static final SlotItem match(@Nullable ItemStack stack, @NotNull Group group) {
        return Companion.match(stack, group);
    }

    static {
        Companion = new Companion(null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/inventory/SlotItem.Companion;", "", "<init>", "()V", "Lnet/minecraft/ItemStack;", "stack", "Lrtx/kimiko/utils/inventory/SlotItem$Group;", "group", "Lrtx/kimiko/utils/inventory/SlotItem;", "Lkotlin/jvm/JvmStatic;", "match", "(Lnet/minecraft/ItemStack;Lrtx/kimiko/utils/inventory/SlotItem$Group;)Lrtx/kimiko/utils/inventory/SlotItem;", "", "isPotionSectionButton", "(Lnet/minecraft/ItemStack;)Z", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nSlotItem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SlotItem.kt\nrtx/kimiko/utils/inventory/SlotItem.Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,123:1\n296#2,2:124\n1960#2,3:126\n*S KotlinDebug\n*F\n+ 1 SlotItem.kt\nrtx/kimiko/utils/inventory/SlotItem.Companion\n*L\n108#1:124,2\n116#1:126,3\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final SlotItem match(@Nullable ItemStack stack, @NotNull Group group) {
            Intrinsics.checkNotNullParameter((Object)group, "group");
            if (group != Group.POTION || stack == null || stack.isEmpty()) {
                return null;
            }
            if (stack.getItem() != Items.SPLASH_POTION && stack.getItem() != Items.LINGERING_POTION) {
                return null;
            }
            if (this.isPotionSectionButton(stack)) {
                return null;
            }
            for (SlotItem item : SlotItem.values()) {
                if (item.matchesEffects(stack)) {
                    return item;
                }
            }
            return null;
        }

        private final boolean isPotionSectionButton(ItemStack stack) {
            boolean bl;
            block5: {
                LoreComponent loreComponent2 = (LoreComponent)stack.get(DataComponentTypes.LORE);
                if (loreComponent2 == null) {
                    return false;
                }
                LoreComponent lore = loreComponent2;
                if (lore.lines().isEmpty()) {
                    return false;
                }
                List list = lore.lines();
                Intrinsics.checkNotNullExpressionValue((Object)list, (String)"lines(...)");
                Iterable $this$any$iv = list;
                boolean $i$f$any = false;
                if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
                    bl = false;
                } else {
                    for (Object element$iv : $this$any$iv) {
                        Text line = (Text)element$iv;
                        String text = line.getString();
                        if (!(text.contains("Нажмите") || text.contains("Раздел открыт"))) continue;
                        bl = true;
                        break block5;
                    }
                    bl = false;
                }
            }
            return bl;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0012J4\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u0005H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u0012J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010\u0012R\u0017\u0010\u0007\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b!\u0010\u0012\u00a8\u0006\""}, d2={"Lrtx/kimiko/utils/inventory/SlotItem$EffectSignature;", "", "Lnet/minecraft/RegistryEntry;", "Lnet/minecraft/StatusEffect;", "effect", "", "duration", "amplifier", "<init>", "(Lnet/minecraft/RegistryEntry;II)V", "Lnet/minecraft/StatusEffectInstance;", "instance", "", "matches", "(Lnet/minecraft/StatusEffectInstance;)Z", "component1", "()Lnet/minecraft/RegistryEntry;", "component2", "()I", "component3", "copy", "(Lnet/minecraft/RegistryEntry;II)Lrtx/kimiko/utils/inventory/SlotItem$EffectSignature;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/RegistryEntry;", "getEffect", "I", "getDuration", "getAmplifier", "rtx.kimiko:kimiko"})
    private static final class EffectSignature {
        @NotNull
        private final RegistryEntry<StatusEffect> effect;
        private final int duration;
        private final int amplifier;

        public EffectSignature(@NotNull RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
            Intrinsics.checkNotNullParameter(effect, (String)"effect");
            this.effect = effect;
            this.duration = duration;
            this.amplifier = amplifier;
        }

        @NotNull
        public final RegistryEntry<StatusEffect> getEffect() {
            return this.effect;
        }

        public final int getDuration() {
            return this.duration;
        }

        public final int getAmplifier() {
            return this.amplifier;
        }

        public final boolean matches(@NotNull StatusEffectInstance instance) {
            Intrinsics.checkNotNullParameter((Object)instance, (String)"instance");
            return Intrinsics.areEqual((Object)instance.getEffectType(), this.effect) && instance.getDuration() == this.duration && instance.getAmplifier() == this.amplifier;
        }

        @NotNull
        public final RegistryEntry<StatusEffect> component1() {
            return this.effect;
        }

        public final int component2() {
            return this.duration;
        }

        public final int component3() {
            return this.amplifier;
        }

        @NotNull
        public final EffectSignature copy(@NotNull RegistryEntry<StatusEffect> effect, int duration, int amplifier) {
            Intrinsics.checkNotNullParameter(effect, (String)"effect");
            return new EffectSignature(effect, duration, amplifier);
        }

        public static /* synthetic */ EffectSignature copy$default(EffectSignature effectSignature, RegistryEntry registryEntry2, int n, int n2, int n3, Object object) {
            if ((n3 & 1) != 0) {
                registryEntry2 = effectSignature.effect;
            }
            if ((n3 & 2) != 0) {
                n = effectSignature.duration;
            }
            if ((n3 & 4) != 0) {
                n2 = effectSignature.amplifier;
            }
            return effectSignature.copy(registryEntry2, n, n2);
        }

        @NotNull
        public String toString() {
            return "EffectSignature(effect=" + this.effect + ", duration=" + this.duration + ", amplifier=" + this.amplifier + ")";
        }

        public int hashCode() {
            int result = this.effect.hashCode();
            result = result * 31 + Integer.hashCode(this.duration);
            result = result * 31 + Integer.hashCode(this.amplifier);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EffectSignature)) {
                return false;
            }
            EffectSignature effectSignature = (EffectSignature)other;
            if (!Intrinsics.areEqual(this.effect, effectSignature.effect)) {
                return false;
            }
            if (this.duration != effectSignature.duration) {
                return false;
            }
            return this.amplifier == effectSignature.amplifier;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2={"Lrtx/kimiko/utils/inventory/SlotItem$Group;", "", "<init>", "(Ljava/lang/String;I)V", "POTION", "rtx.kimiko:kimiko"})
    public static enum Group {
        POTION;

        @NotNull
        public static EnumEntries<Group> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }
    }
}

