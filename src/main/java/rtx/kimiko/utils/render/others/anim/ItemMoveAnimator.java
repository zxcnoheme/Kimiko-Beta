/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.slot.Slot
 *  net.minecraft.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others.anim;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u00018B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J;\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\u0012\u0010\u0013JQ\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00152\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00190\u00142\u0006\u0010\u001b\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010!\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010%\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R0\u00102\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001500j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0015`18\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R0\u00104\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001900j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0019`18\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00103R0\u00106\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020500j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u000205`18\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00103R0\u00107\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001100j\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0011`18\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00103\u00a8\u00069"}, d2={"Lrtx/kimiko/utils/render/others/anim/ItemMoveAnimator;", "", "<init>", "()V", "Lnet/minecraft/ScreenHandler;", "menu", "", "mouseX", "mouseY", "leftPos", "topPos", "", "Lkotlin/jvm/JvmStatic;", "beginFrame", "(Lnet/minecraft/ScreenHandler;IIII)V", "Lnet/minecraft/Slot;", "slot", "", "offset", "(Lnet/minecraft/Slot;)[F", "", "Lnet/minecraft/ItemStack;", "cur", "target", "item", "", "pos", "targetPos", "findFrom", "(Ljava/util/Map;Lnet/minecraft/Slot;Lnet/minecraft/ItemStack;Ljava/util/Map;[I)Lnet/minecraft/Slot;", "a", "b", "", "sameKey", "(Lnet/minecraft/ItemStack;Lnet/minecraft/ItemStack;)Z", "", "t", "easeOut", "(F)F", "DURATION", "F", "lastMenu", "Lnet/minecraft/ScreenHandler;", "", "lastNs", "J", "lastCarried", "Lnet/minecraft/ItemStack;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "lastItems", "Ljava/util/HashMap;", "lastPos", "Lrtx/kimiko/utils/render/others/anim/ItemMoveAnimator$Anim;", "anims", "offsets", "Anim", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nItemMoveAnimator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ItemMoveAnimator.kt\nrtx/kimiko/utils/render/others/anim/ItemMoveAnimator\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,153:1\n221#2,2:154\n*S KotlinDebug\n*F\n+ 1 ItemMoveAnimator.kt\nrtx/kimiko/utils/render/others/anim/ItemMoveAnimator\n*L\n99#1:154,2\n*E\n"})
public final class ItemMoveAnimator {
    @NotNull
    public static final ItemMoveAnimator INSTANCE = new ItemMoveAnimator();
    private static final float DURATION = 0.14f;
    @Nullable
    private static ScreenHandler lastMenu;
    private static long lastNs;
    @NotNull
    private static ItemStack lastCarried;
    @NotNull
    private static final HashMap<Slot, ItemStack> lastItems;
    @NotNull
    private static final HashMap<Slot, int[]> lastPos;
    @NotNull
    private static final HashMap<Slot, Anim> anims;
    @NotNull
    private static final HashMap<Slot, float[]> offsets;

    private ItemMoveAnimator() {
    }

    @JvmStatic
    public static final void beginFrame(@NotNull ScreenHandler menu, int mouseX, int mouseY, int leftPos, int topPos) {
        Intrinsics.checkNotNullParameter((Object)menu, (String)"menu");
        offsets.clear();
        if (menu != lastMenu) {
            lastMenu = menu;
            lastItems.clear();
            lastPos.clear();
            anims.clear();
            lastCarried = ItemStack.EMPTY;
            lastNs = 0L;
        }
        long now = System.nanoTime();
        float dt = lastNs == 0L ? 0.016f : Math.min(0.1f, (float)(now - lastNs) / 1.0E9f);
        lastNs = now;
        Map<Slot, ItemStack> cur = new HashMap<>();
        Map<Slot, int[]> pos = new HashMap<>();
        for (Slot slot : menu.slots) {
            cur.put(slot, slot.getStack());
            pos.put(slot, new int[]{slot.x, slot.y});
        }
        ItemStack carried = menu.getCursorStack();
        lastItems.keySet().retainAll(cur.keySet());
        lastPos.keySet().retainAll(cur.keySet());
        anims.keySet().retainAll(cur.keySet());

        for (Map.Entry<Slot, ItemStack> entry : cur.entrySet()) {
            Slot slot = entry.getKey();
            ItemStack c = entry.getValue();
            if (c.isEmpty()) {
                anims.remove(slot);
                continue;
            }
            ItemStack itemStack4 = lastItems.getOrDefault(slot, ItemStack.EMPTY);
            if (INSTANCE.sameKey(c, itemStack4) || anims.containsKey(slot)) continue;
            Anim anim = new Anim();
            int[] tp = pos.get(slot);
            if (tp == null) continue;
            Slot fromSlot = INSTANCE.findFrom(cur, slot, c, pos, tp);
            if (fromSlot != null) {
                int[] fp = lastPos.getOrDefault(fromSlot, pos.get(fromSlot));
                if (fp != null) {
                    anim.fromX = fp[0] - tp[0];
                    anim.fromY = fp[1] - tp[1];
                }
            } else if (INSTANCE.sameKey(c, lastCarried) && !INSTANCE.sameKey(carried, lastCarried)) {
                anim.fromX = (float)(mouseX - leftPos - tp[0]) - 8.0f;
                anim.fromY = (float)(mouseY - topPos - tp[1]) - 8.0f;
            } else {
                anim.pop = true;
            }
            anims.put(slot, anim);
        }

        Iterator<Map.Entry<Slot, Anim>> it = anims.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Slot, Anim> entry = it.next();
            Anim anim = entry.getValue();
            anim.progress += dt / 0.14f;
            if (anim.progress >= 1.0f) {
                it.remove();
                continue;
            }
            float eased = INSTANCE.easeOut(anim.progress);
            if (anim.pop) {
                offsets.put(entry.getKey(), new float[]{0.0f, 0.0f, 0.55f + 0.45f * eased});
                continue;
            }
            float f = 1.0f - eased;
            offsets.put(entry.getKey(), new float[]{anim.fromX * f, anim.fromY * f, 1.0f});
        }

        lastItems.clear();
        for (Map.Entry<Slot, ItemStack> entry : cur.entrySet()) {
            lastItems.put(entry.getKey(), entry.getValue().copy());
        }
        lastPos.clear();
        lastPos.putAll(pos);
        lastCarried = carried.copy();
    }

    @JvmStatic
    @Nullable
    public static final float[] offset(@Nullable Slot slot) {
        return (float[])((Map)offsets).get(slot);
    }

    private final Slot findFrom(Map<Slot, ItemStack> cur, Slot target, ItemStack item, Map<Slot, int[]> pos, int[] targetPos) {
        Slot best = null;
        long bestDist = Long.MAX_VALUE;
        for (Map.Entry<Slot, ItemStack> entry : lastItems.entrySet()) {
            Slot slot = entry.getKey();
            ItemStack value = entry.getValue();
            if (slot == target || !this.sameKey(value, item)) continue;
            ItemStack nowThere = cur.get(slot);
            if (nowThere != null && this.sameKey(nowThere, item)) continue;
            int[] fp = lastPos.getOrDefault(slot, pos.get(slot));
            if (fp == null) continue;
            long dx = (long)(fp[0] - targetPos[0]);
            long dy = (long)(fp[1] - targetPos[1]);
            long dist = dx * dx + dy * dy;
            if (dist >= bestDist) continue;
            bestDist = dist;
            best = slot;
        }
        return best;
    }

    private final boolean sameKey(ItemStack a, ItemStack b) {
        if (a.isEmpty() && b.isEmpty()) {
            return true;
        }
        if (a.isEmpty() || b.isEmpty()) {
            return false;
        }
        return ItemStack.areItemsAndComponentsEqual((ItemStack)a, (ItemStack)b);
    }

    private final float easeOut(float t) {
        float clamped = t < 0.0f ? 0.0f : (t > 1.0f ? 1.0f : t);
        float u = 1.0f - clamped;
        return 1.0f - u * u * u;
    }

    static {
        ItemStack itemStack2 = ItemStack.EMPTY;
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
        lastCarried = itemStack2;
        lastItems = new HashMap();
        lastPos = new HashMap();
        anims = new HashMap();
        offsets = new HashMap();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007R\u001b\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0007R\u001b\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/render/others/anim/ItemMoveAnimator$Anim;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "fromX", "F", "fromY", "progress", "", "pop", "Z", "rtx.kimiko:kimiko"})
    private static final class Anim {
        @JvmField
        public float fromX;
        @JvmField
        public float fromY;
        @JvmField
        public float progress;
        @JvmField
        public boolean pop;
    }
}

