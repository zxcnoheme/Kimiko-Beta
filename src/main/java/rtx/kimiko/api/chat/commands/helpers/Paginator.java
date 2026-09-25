/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.text.Text
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.commands.helpers;

import java.util.List;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.Text;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.commands.CommandManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B'\b\u0007\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\f\u0010\rJ3\u0010\u0015\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0017R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0018R\u0016\u0010\n\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/chat/commands/helpers/Paginator;", "T", "", "", "items", "", "pageSize", "Lkotlin/jvm/JvmOverloads;", "<init>", "(Ljava/util/List;I)V", "page", "", "setPage", "(I)V", "Ljava/lang/Runnable;", "header", "Ljava/util/function/Function;", "Lnet/minecraft/Text;", "rowFactory", "", "baseCommand", "display", "(Ljava/lang/Runnable;Ljava/util/function/Function;Ljava/lang/String;)V", "Ljava/util/List;", "I", "rtx.kimiko:kimiko"})
public final class Paginator<T> {
    @NotNull
    private final List<T> items;
    private final int pageSize;
    private int page;

    @JvmOverloads
    public Paginator(@Nullable List<? extends T> items, int pageSize) {
        List list = items;
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        this.items = list;
        this.pageSize = Math.max(1, pageSize);
        this.page = 1;
    }

    public /* synthetic */ Paginator(List list, int n, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, ((n2 & 2) != 0 ? 8 : n));
    }

    public final void setPage(int page) {
        this.page = Math.max(1, page);
    }

    public final void display(@Nullable Runnable header, @NotNull Function<T, Text> rowFactory, @NotNull String baseCommand) {
        Intrinsics.checkNotNullParameter(rowFactory, (String)"rowFactory");
        Intrinsics.checkNotNullParameter((Object)baseCommand, (String)"baseCommand");
        Runnable runnable = header;
        if (runnable != null) {
            runnable.run();
        }
        int pages = Math.max(1, (int)Math.ceil((double)this.items.size() / (double)this.pageSize));
        int safePage = Math.min(this.page, pages);
        int start = (safePage - 1) * this.pageSize;
        int end = Math.min(this.items.size(), start + this.pageSize);
        CommandManager manager = CommandManager.Companion.getInstance();
        for (int i = start; i < end; ++i) {
            Text row = rowFactory.apply(this.items.get(i));
            if (manager != null) {
                manager.sendRaw(row);
            }
        }
        if (manager != null && pages > 1) {
            MutableText mutableText2 = Text.literal((String)("\u00a77Page \u00a7f" + safePage + "\u00a77/\u00a7f" + pages + " \u00a78| \u00a77" + baseCommand + " <page>"));
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"literal(...)");
            manager.sendRaw((Text)mutableText2);
        }
    }

    @JvmOverloads
    public Paginator(@Nullable List<? extends T> items) {
        this(items, 0, 2, null);
    }
}

