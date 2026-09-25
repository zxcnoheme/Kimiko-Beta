/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.storage.friend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\n\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u0003J\u001d\u0010\u000e\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u000e\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u0012J\u001b\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0013\u0010\u0016\u001a\u00020\bH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u0013\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u001a\u0010\u0007J\u0019\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u001b\u0010\u0007R\u0014\u0010\u001c\u001a\u00020\u00058\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001f\u00a8\u0006 "}, d2={"Lrtx/kimiko/utils/storage/friend/FriendUtils;", "", "<init>", "()V", "", "", "get", "()Ljava/util/List;", "", "load", "save", "name", "", "Lkotlin/jvm/JvmStatic;", "isFriend", "(Ljava/lang/String;)Z", "Lnet/minecraft/Entity;", "entity", "(Lnet/minecraft/Entity;)Z", "addFriendAndSave", "(Ljava/lang/String;)V", "removeFriendAndSave", "clearAndSave", "", "size", "()I", "getFriendNames", "friends", "KEY", "Ljava/lang/String;", "cache", "Ljava/util/List;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nFriendUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FriendUtils.kt\nrtx/kimiko/utils/storage/friend/FriendUtils\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,73:1\n1960#2,3:74\n*S KotlinDebug\n*F\n+ 1 FriendUtils.kt\nrtx/kimiko/utils/storage/friend/FriendUtils\n*L\n39#1:74,3\n*E\n"})
public final class FriendUtils {
    @NotNull
    public static final FriendUtils INSTANCE = new FriendUtils();
    @NotNull
    private static final String KEY = "friends";
    @Nullable
    private static List<String> cache;

    private FriendUtils() {
    }

    private final List<String> get() {
        if (cache == null) {
            this.load();
        }
        List<String> list = cache;
        Intrinsics.checkNotNull(list);
        return list;
    }

    private final void load() {
        ArrayList<String> list = new ArrayList<String>();
        cache = list;
        JsonObject obj = RepositoryStorage.readObject(KEY);
        if (obj.has("list")) {
            Iterator iterator = obj.getAsJsonArray("list").iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                JsonElement el = (JsonElement)iterator2.next();
                list.add(el.getAsString());
            }
        }
    }

    private final void save() {
        JsonObject obj = new JsonObject();
        JsonArray arr = new JsonArray();
        for (String f : this.get()) {
            arr.add(f);
        }
        obj.add("list", (JsonElement)arr);
        RepositoryStorage.write(KEY, obj);
    }

    @JvmStatic
    public static final boolean isFriend(@Nullable String name) {
        boolean bl;
        block3: {
            Iterable $this$any$iv = INSTANCE.get();
            boolean $i$f$any = false;
            if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
                bl = false;
            } else {
                for (Object element$iv : $this$any$iv) {
                    String it = (String)element$iv;
                    boolean bl2 = false;
                    if (!StringsKt.equals((String)it, (String)name, (boolean)true)) continue;
                    bl = true;
                    break block3;
                }
                bl = false;
            }
        }
        return bl;
    }

    @JvmStatic
    public static final boolean isFriend(@Nullable Entity entity) {
        return entity instanceof PlayerEntity && FriendUtils.isFriend(((PlayerEntity)entity).getName().getString());
    }

    @JvmStatic
    public static final void addFriendAndSave(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        if (!FriendUtils.isFriend(name)) {
            INSTANCE.get().add(name);
            INSTANCE.save();
        }
    }

    @JvmStatic
    public static final void removeFriendAndSave(@Nullable String name) {
        INSTANCE.get().removeIf(it -> it.equalsIgnoreCase(name));
        INSTANCE.save();
    }

    @JvmStatic
    public static final void clearAndSave() {
        INSTANCE.get().clear();
        INSTANCE.save();
    }

    @JvmStatic
    public static final int size() {
        return INSTANCE.get().size();
    }

    @JvmStatic
    @NotNull
    public static final List<String> getFriendNames() {
        return new ArrayList(INSTANCE.get());
    }

    @JvmStatic
    @NotNull
    public static final List<String> friends() {
        return FriendUtils.getFriendNames();
    }

}

