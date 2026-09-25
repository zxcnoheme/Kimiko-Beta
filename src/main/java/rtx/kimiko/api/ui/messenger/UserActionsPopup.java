/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.Unit
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.functions.Function0
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.messenger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.messenger.MessengerClient;
import rtx.kimiko.api.chat.prefix.ChatPrefixes;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.ui.messenger.ChatPrefixBadge;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u0000 d2\u00020\u0001:\u0003efdB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\f\u0010\nJ-\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0016\u0010\u0003J-\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b!\u0010\u0003J\u000f\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0015\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0%H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b)\u0010*J\u0015\u0010+\u001a\b\u0012\u0004\u0012\u00020\"0%H\u0002\u00a2\u0006\u0004\b+\u0010'J\u0015\u0010,\u001a\b\u0012\u0004\u0012\u00020\"0%H\u0002\u00a2\u0006\u0004\b,\u0010'J\u001f\u0010.\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b.\u0010/J\u001f\u00102\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b2\u00103J-\u00108\u001a\u00020\u00132\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\b\u00a2\u0006\u0004\b8\u0010\u001cJG\u0010<\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\b2\u0006\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u00020\b2\u0006\u0010;\u001a\u00020\b2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b<\u0010=JG\u0010D\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020\b2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\b2\u0006\u0010;\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bD\u0010EJ%\u0010I\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\u001e\u00a2\u0006\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010O\u001a\u00020N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u001c\u0010Q\u001a\b\u0012\u0004\u0012\u00020\"0%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010\u0014\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010SR\u0016\u0010U\u001a\u00020T8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Y\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010ZR\u0016\u0010\\\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010XR\u0016\u0010_\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010XR\u0016\u0010a\u001a\u00020`8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010X\u00a8\u0006g"}, d2={"Lrtx/kimiko/api/ui/messenger/UserActionsPopup;", "", "<init>", "()V", "", "isOpen", "()Z", "isVisible", "", "width", "()F", "height", "rowsHeight", "Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;", "msg", "", "actor", "px", "py", "", "open", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;Ljava/lang/String;FF)V", "close", "panelX", "panelY", "panelW", "panelH", "clampTo", "(FFFF)V", "role", "", "rank", "(Ljava/lang/String;)I", "rebuild", "Lrtx/kimiko/api/ui/messenger/UserActionsPopup$Row;", "back", "()Lrtx/kimiko/api/ui/messenger/UserActionsPopup$Row;", "", "mainRows", "()Ljava/util/List;", "action", "goDuration", "(Ljava/lang/String;)V", "durationRows", "roleRows", "minutes", "punish", "(Ljava/lang/String;I)V", "ok", "message", "report", "(ZLjava/lang/String;)V", "alpha", "mouseX", "mouseY", "rate", "render", "top", "w", "a", "renderPrefixPage", "(FFFFFFF)V", "rx", "ry", "rw", "label", "h", "danger", "drawRow", "(FFFLjava/lang/String;FFZ)V", "mx", "my", "button", "mouseClicked", "(FFI)Z", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "", "hover", "[F", "rows", "Ljava/util/List;", "Z", "Lrtx/kimiko/api/ui/messenger/UserActionsPopup$Page;", "page", "Lrtx/kimiko/api/ui/messenger/UserActionsPopup$Page;", "pending", "Ljava/lang/String;", "x", "F", "y", "targetUid", "I", "targetName", "targetRole", "", "messageId", "J", "actorRole", "Companion", "Page", "Row", "rtx.kimiko:kimiko"})
public final class UserActionsPopup {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @NotNull
    private final Decelerate anim = new Decelerate();
    @NotNull
    private final float[] hover = new float[16];
    @NotNull
    private List<Row> rows = CollectionsKt.emptyList();
    private boolean open;
    @NotNull
    private Page page = Page.MAIN;
    @NotNull
    private String pending = "";
    private float x;
    private float y;
    private int targetUid;
    @NotNull
    private String targetName = "";
    @NotNull
    private String targetRole = "none";
    private long messageId;
    @NotNull
    private String actorRole = "none";
    private static final float WIDTH = 92.0f;
    private static final float PREFIX_W = 118.0f;
    private static final float PAD = 6.0f;
    private static final float GAP = 3.0f;
    private static final float ROW_H = 11.5f;
    private static final float CHIP_ROW_H = 11.0f;
    private static final float ROW_RADIUS = 3.0f;
    private static final float ROW_TEXT_SIZE = 5.8f;
    private static final float TITLE_SIZE = 6.2f;
    private static final float SUB_SIZE = 4.6f;
    private static final float TITLE_H = 13.2f;
    private static final int MAX_ROWS = 16;

    public UserActionsPopup() {
        this.anim.setMs(170);
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - (long)10000);
    }

    public final boolean isOpen() {
        return this.open;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isVisible() {
        if (this.open) return true;
        Double d = this.anim.getOutput();
        double d2 = d != null ? d : 0.0;
        if (!((float)d2 > 0.01f)) return false;
        return true;
    }

    public final float width() {
        return this.page == Page.PREFIX ? 118.0f : 92.0f;
    }

    public final float height() {
        return 19.2f + this.rowsHeight() + 6.0f;
    }

    private final float rowsHeight() {
        if (this.page == Page.PREFIX) {
            int lines = (ChatPrefixes.grantable().size() + 1) / 2;
            return (float)lines * 14.0f + 11.5f + 3.0f;
        }
        return (float)this.rows.size() * 14.5f;
    }

    public final void open(@NotNull MessengerClient.Message msg, @NotNull String actor, float px, float py) {
        Intrinsics.checkNotNullParameter((Object)msg, (String)"msg");
        Intrinsics.checkNotNullParameter((Object)actor, (String)"actor");
        this.targetUid = msg.uid();
        this.targetName = msg.user();
        this.targetRole = msg.role();
        this.messageId = msg.id();
        this.actorRole = actor;
        this.page = Page.MAIN;
        this.pending = "";
        this.rebuild();
        this.x = px;
        this.y = py;
        this.open = true;
        java.util.Arrays.fill(this.hover, 0.0f);
        this.anim.setDirection(Direction.FORWARDS);
        this.anim.counter.resetCounter();
        Sounds.play("module_settings_open");
    }

    public final void close() {
        if (!this.open) {
            return;
        }
        this.open = false;
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.resetCounter();
    }

    public final void clampTo(float panelX, float panelY, float panelW, float panelH) {
        this.x = RangesKt.coerceIn((float)this.x, (float)(panelX + 3.0f), (float)Math.max(panelX + 3.0f, panelX + panelW - this.width() - 3.0f));
        this.y = RangesKt.coerceIn((float)this.y, (float)(panelY + 3.0f), (float)Math.max(panelY + 3.0f, panelY + panelH - this.height() - 3.0f));
    }

    private final int rank(String role) {
        return switch (role) {
            case "owner" -> 4;
            case "admin" -> 3;
            case "moderator" -> 2;
            case "helper" -> 1;
            default -> 0;
        };
    }

    private final void rebuild() {
        this.rows = switch (WhenMappings.$EnumSwitchMapping$0[this.page.ordinal()]) {
            case 1 -> this.mainRows();
            case 2 -> this.durationRows();
            case 3 -> this.roleRows();
            case 4 -> List.of(this.back());
            default -> throw new NoWhenBranchMatchedException();
        };
        java.util.Arrays.fill(this.hover, 0.0f);
    }

    private final Row back() {
        return new Row(I18n.tr("\u2039 Назад"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.back$lambda$0(this)));
    }

    private final List<Row> mainRows() {
        ArrayList<Row> list = new ArrayList<Row>();
        int mine = this.rank(this.actorRole);
        list.add(new Row(I18n.tr("Мут"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$0(this))));
        list.add(new Row(I18n.tr("Снять мут"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$1(this))));
        if (mine >= 2) {
            list.add(new Row(I18n.tr("Запретить голосовые"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$2(this))));
            list.add(new Row(I18n.tr("Вернуть голосовые"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$3(this))));
            list.add(new Row(I18n.tr("Бан"), true, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$4(this))));
            list.add(new Row(I18n.tr("Разбанить"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$5(this))));
        }
        list.add(new Row(I18n.tr("Удалить сообщение"), true, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$6(this))));
        if (mine >= 2) {
            list.add(new Row(I18n.tr("Удалить все сообщения"), true, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$7(this))));
        }
        if (mine >= 3) {
            list.add(new Row(I18n.tr("Роль…"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$8(this))));
            list.add(new Row(I18n.tr("Префикс…"), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.mainRows$lambda$9(this))));
        }
        return list;
    }

    private final void goDuration(String action) {
        this.pending = action;
        this.page = Page.DURATION;
        this.rebuild();
    }

    private final List<Row> durationRows() {
        ArrayList<Row> list2 = new ArrayList<Row>();
        list2.add(this.back());
        String string = this.pending;
        List<Pair<String, Integer>> list;
        if (Intrinsics.areEqual((Object)string, (Object)"ban")) {
            Pair<String, Integer>[] var4_3 = new Pair[]{TuplesKt.to(I18n.tr("1 день"), 1440), TuplesKt.to(I18n.tr("7 дней"), 10080), TuplesKt.to(I18n.tr("30 дней"), 43200), TuplesKt.to(I18n.tr("Навсегда"), 0)};
            list = CollectionsKt.listOf(var4_3);
        } else if (Intrinsics.areEqual((Object)string, (Object)"voicemute")) {
            Pair<String, Integer>[] var4_3 = new Pair[]{TuplesKt.to(I18n.tr("30 минут"), 30), TuplesKt.to(I18n.tr("1 час"), 60), TuplesKt.to(I18n.tr("6 часов"), 360), TuplesKt.to(I18n.tr("1 день"), 1440), TuplesKt.to(I18n.tr("7 дней"), 10080)};
            list = CollectionsKt.listOf(var4_3);
        } else {
            Pair<String, Integer>[] var4_3 = new Pair[]{TuplesKt.to(I18n.tr("5 минут"), 5), TuplesKt.to(I18n.tr("30 минут"), 30), TuplesKt.to(I18n.tr("1 час"), 60), TuplesKt.to(I18n.tr("6 часов"), 360), TuplesKt.to(I18n.tr("1 день"), 1440), TuplesKt.to(I18n.tr("7 дней"), 10080)};
            list = CollectionsKt.listOf(var4_3);
        }
        List<Pair<String, Integer>> options = list;
        String action = this.pending;
        for (Pair<String, Integer> pair : options) {
            String label = (String)pair.component1();
            int minutes = ((Number)pair.component2()).intValue();
            list2.add(new Row(label, Intrinsics.areEqual((Object)action, (Object)"ban"), (Function0<Unit>)((Function0)() -> UserActionsPopup.durationRows$lambda$0(this, action, minutes))));
        }
        return list2;
    }

    private final List<Row> roleRows() {
        ArrayList<Row> list = new ArrayList<Row>();
        list.add(this.back());
        ArrayList<Pair> available = new ArrayList<Pair>();
        available.add(TuplesKt.to((Object)I18n.tr("Игрок"), (Object)"none"));
        available.add(TuplesKt.to((Object)I18n.tr("Помощник"), (Object)"helper"));
        available.add(TuplesKt.to((Object)I18n.tr("Модератор"), (Object)"moderator"));
        if (this.rank(this.actorRole) >= 4) {
            available.add(TuplesKt.to((Object)I18n.tr("Админ"), (Object)"admin"));
        }
        Iterator iterator = available.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            Object e = iterator2.next();
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            Pair pair = (Pair)e;
            String label = (String)pair.component1();
            String role = (String)pair.component2();
            boolean current = Intrinsics.areEqual((Object)this.targetRole, (Object)role);
            list.add(new Row((String)(current ? label + " \u2713" : label), false, (Function0<Unit>)((Function0)() -> UserActionsPopup.roleRows$lambda$0(this, role, label))));
        }
        return list;
    }

    private final void punish(String action, int minutes) {
        this.close();
        MessengerClient.INSTANCE.moderate(action, this.targetUid, this.targetName, minutes, "", (ok, msg) -> {
            this.report(Boolean.TRUE.equals(ok), msg);
            return Unit.INSTANCE;
        });
    }

    private final void report(boolean ok, String message) {
        Notifications.push(ok ? I18n.tr("Модерация") : I18n.tr("Ошибка"), StringsKt.isBlank((CharSequence)message) ? I18n.tr("Готово") : message, 2600L);
    }

    public final void render(float alpha, float mouseX, float mouseY, float rate) {
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f && !this.open) {
            return;
        }
        float a = alpha * t;
        float w = this.width();
        float h = this.height();
        float drawY = this.y + (1.0f - t) * 4.0f;
        RenderHelper.drawDropBackground(this.x, drawY, w, h, a);
        String title = UserActionsPopup.Companion.clip(this.targetName, 6.2f, w - 12.0f - 2.0f);
        Fonts.SEMIBOLD.draw(title, this.x + 6.0f, drawY + 6.0f, 6.2f, ColorEngine.multAlpha(-1, 0.9f * a));
        String roleLabel = ChatPrefixes.roleLabel(this.targetRole);
        float rw = Fonts.MEDIUM.width(roleLabel, 4.6f);
        Fonts.MEDIUM.draw(roleLabel, this.x + w - 6.0f - rw, drawY + 6.0f + 0.6f, 4.6f, ColorEngine.multAlpha(-1, 0.45f * a));
        Render2D.rect(this.x + 6.0f, drawY + 6.0f + 6.2f + 3.0f, w - 12.0f, 0.6f, 0.0f, ColorEngine.multAlpha(0x22FFFFFF, a));
        float top = drawY + 6.0f + 13.2f;
        if (this.page == Page.PREFIX) {
            this.renderPrefixPage(this.x, top, w, a, mouseX, mouseY, rate);
            return;
        }
        int n = ((Collection)this.rows).size();
        for (int i = 0; i < n && i < 16; ++i) {
            Row row = this.rows.get(i);
            float ry = top + (float)i * 14.5f;
            float rx = this.x + 6.0f;
            float rw2 = w - 12.0f;
            boolean hovered = this.open && mouseX >= rx && mouseX <= rx + rw2 && mouseY >= ry && mouseY <= ry + 11.5f;
            float[] fArray = this.hover;
            int n2 = i;
            fArray[n2] = fArray[n2] + ((hovered ? 1.0f : 0.0f) - this.hover[i]) * rate;
            this.drawRow(rx, ry, rw2, row.getLabel(), this.hover[i], a, row.getDanger());
        }
    }

    private final void renderPrefixPage(float px, float top, float w, float a, float mouseX, float mouseY, float rate) {
        Row backRow = this.rows.get(0);
        float rx = px + 6.0f;
        float rw = w - 12.0f;
        boolean hovered = this.open && mouseX >= rx && mouseX <= rx + rw && mouseY >= top && mouseY <= top + 11.5f;
        float[] fArray = this.hover;
        fArray[0] = fArray[0] + ((hovered ? 1.0f : 0.0f) - this.hover[0]) * rate;
        this.drawRow(rx, top, rw, backRow.getLabel(), this.hover[0], a, false);
        String hint = I18n.tr("ЛКМ — выдать, ПКМ — забрать");
        Fonts.MEDIUM.draw(hint, rx, top + 11.5f + 1.0f, 4.2f, ColorEngine.multAlpha(-1, 0.35f * a));
        List<ChatPrefixes.Entry> entries = ChatPrefixes.grantable();
        float cellW = (rw - 3.0f) * 0.5f;
        float gridTop = top + 11.5f + 3.0f + 4.0f;
        int n = ((Collection)entries).size();
        for (int i = 0; i < n; ++i) {
            int col = i % 2;
            int line = i / 2;
            float cx = rx + (float)col * (cellW + 3.0f);
            float cy = gridTop + (float)line * 14.0f;
            boolean over = this.open && mouseX >= cx && mouseX <= cx + cellW && mouseY >= cy && mouseY <= cy + 11.0f;
            RenderHelper.drawPanelBg(cx, cy, cellW, 11.0f, 3.0f, a);
            if (over) {
                Render2D.rect(cx, cy, cellW, 11.0f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(150.0f, cx, cy), a));
            }
            float chipW = ChatPrefixBadge.width(entries.get(i));
            ChatPrefixBadge.draw(entries.get(i), cx + (cellW - chipW) * 0.5f, cy + 2.0f, a);
        }
    }

    private final void drawRow(float rx, float ry, float rw, String label, float h, float a, boolean danger) {
        RenderHelper.drawPanelBg(rx, ry, rw, 11.5f, 3.0f, a);
        if (danger) {
            Render2D.rect(rx, ry, rw, 11.5f, 3.0f, ColorEngine.multAlpha(-4702144, (0.12f + 0.55f * h) * a));
        } else if (h > 0.01f) {
            Render2D.rect(rx, ry, rw, 11.5f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(210.0f * h, rx + rw * 0.5f, ry + 5.75f), a));
        }
        int base = danger ? ColorEngine.multAlpha(-2070424, 0.85f * a) : ColorEngine.multAlpha(-1, 0.68f * a);
        int active = danger ? ColorEngine.multAlpha(-1, a) : ColorEngine.multAlpha(-15856114, a);
        Fonts.MEDIUM.draw(label, rx + 6.0f, ry + 2.25f, 5.8f, ColorEngine.lerpColor(base, active, h));
    }

    public final boolean mouseClicked(float mx, float my, int button) {
        if (!this.open) {
            return false;
        }
        float w = this.width();
        float h = this.height();
        if (mx < this.x || mx > this.x + w || my < this.y || my > this.y + h) {
            this.close();
            return false;
        }
        float top = this.y + 6.0f + 13.2f;
        if (this.page == Page.PREFIX) {
            if (button == 0 && my >= top && my <= top + 11.5f) {
                this.rows.get(0).getRun().invoke();
                return true;
            }
            List<ChatPrefixes.Entry> entries = ChatPrefixes.grantable();
            float rx = this.x + 6.0f;
            float rw = w - 12.0f;
            float cellW = (rw - 3.0f) * 0.5f;
            float gridTop = top + 11.5f + 3.0f + 4.0f;
            int n = ((Collection)entries).size();
            for (int i = 0; i < n; ++i) {
                float cx = rx + (float)(i % 2) * (cellW + 3.0f);
                float cy = gridTop + (float)(i / 2) * 14.0f;
                if (!(mx >= cx) || !(mx <= cx + cellW) || !(my >= cy) || !(my <= cy + 11.0f)) continue;
                boolean revoke = button == 1;
                this.close();
                int targetIndex = i;
                MessengerClient.INSTANCE.assignPrefix(this.targetUid, this.targetName, entries.get(i).id(), revoke, (ok, msg) -> {
                    String string;
                    if (Boolean.TRUE.equals(ok)) {
                        if (revoke) {
                            string = I18n.tr("Префикс забран");
                        } else {
                            string = I18n.tr("Префикс выдан: %s", new Object[]{entries.get(targetIndex).label()});
                        }
                    } else {
                        string = msg;
                    }
                    this.report(Boolean.TRUE.equals(ok), string);
                    return Unit.INSTANCE;
                });
                return true;
            }
            return true;
        }
        if (button != 0) {
            return true;
        }
        int n = ((Collection)this.rows).size();
        for (int i = 0; i < n && i < 16; ++i) {
            float ry = top + (float)i * 14.5f;
            if (!(my >= ry) || !(my <= ry + 11.5f)) continue;
            this.rows.get(i).getRun().invoke();
            return true;
        }
        return true;
    }

    private static final Unit back$lambda$0(UserActionsPopup this$0) {
        this$0.page = Page.MAIN;
        this$0.pending = "";
        this$0.rebuild();
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$0(UserActionsPopup this$0) {
        this$0.goDuration("mute");
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$1(UserActionsPopup this$0) {
        this$0.punish("unmute", 0);
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$2(UserActionsPopup this$0) {
        this$0.goDuration("voicemute");
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$3(UserActionsPopup this$0) {
        this$0.punish("unvoicemute", 0);
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$4(UserActionsPopup this$0) {
        this$0.goDuration("ban");
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$5(UserActionsPopup this$0) {
        this$0.punish("unban", 0);
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$6$0(UserActionsPopup this$0, boolean ok, String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        this$0.report(ok, message);
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$6(UserActionsPopup this$0) {
        this$0.close();
        MessengerClient.INSTANCE.deleteForEveryone(this$0.messageId, (ok, msg) -> {
            this$0.report(Boolean.TRUE.equals(ok), msg);
            return Unit.INSTANCE;
        });
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$7(UserActionsPopup this$0) {
        this$0.close();
        MessengerClient.INSTANCE.purgeUser(this$0.targetUid, this$0.targetName, (ok, msg) -> {
            this$0.report(Boolean.TRUE.equals(ok), msg);
            return Unit.INSTANCE;
        });
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$8(UserActionsPopup this$0) {
        this$0.page = Page.ROLE;
        this$0.rebuild();
        return Unit.INSTANCE;
    }

    private static final Unit mainRows$lambda$9(UserActionsPopup this$0) {
        this$0.page = Page.PREFIX;
        this$0.rebuild();
        return Unit.INSTANCE;
    }

    private static final Unit durationRows$lambda$0(UserActionsPopup this$0, String $action, int $minutes) {
        this$0.punish($action, $minutes);
        return Unit.INSTANCE;
    }

    private static final Unit roleRows$lambda$0$0(UserActionsPopup this$0, String $label, boolean ok, String message) {
        String string;
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        if (ok) {
            Object[] objectArray = new Object[]{$label};
            string = I18n.tr("Роль обновлена: %s", objectArray);
        } else {
            string = message;
        }
        this$0.report(ok, string);
        return Unit.INSTANCE;
    }

    private static final Unit roleRows$lambda$0(UserActionsPopup this$0, String $role, String $label) {
        this$0.close();
        MessengerClient.INSTANCE.assignRole(this$0.targetUid, this$0.targetName, $role, (ok, msg) -> {
            String string = Boolean.TRUE.equals(ok) ? I18n.tr("Роль обновлена: %s", new Object[]{$label}) : msg;
            this$0.report(Boolean.TRUE.equals(ok), string);
            return Unit.INSTANCE;
        });
        return Unit.INSTANCE;
    }

    private static final Unit punish$lambda$0(UserActionsPopup this$0, boolean ok, String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        this$0.report(ok, message);
        return Unit.INSTANCE;
    }

    private static final Unit mouseClicked$lambda$0(UserActionsPopup this$0, boolean $revoke, List $entries, int $i, boolean ok, String message) {
        String string;
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        if (ok) {
            if ($revoke) {
                string = I18n.tr("Префикс забран");
            } else {
                Object[] objectArray = new Object[]{((ChatPrefixes.Entry)$entries.get($i)).label()};
                string = I18n.tr("Префикс выдан: %s", objectArray);
            }
        } else {
            string = message;
        }
        this$0.report(ok, string);
        return Unit.INSTANCE;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\fR\u0014\u0010\u0012\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\fR\u0014\u0010\u0014\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\fR\u0014\u0010\u0015\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\fR\u0014\u0010\u0016\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\fR\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/ui/messenger/UserActionsPopup.Companion;", "", "<init>", "()V", "", "text", "", "size", "maxW", "clip", "(Ljava/lang/String;FF)Ljava/lang/String;", "WIDTH", "F", "PREFIX_W", "PAD", "GAP", "ROW_H", "CHIP_ROW_H", "ROW_RADIUS", "ROW_TEXT_SIZE", "TITLE_SIZE", "SUB_SIZE", "TITLE_H", "", "MAX_ROWS", "I", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        private final String clip(String text, float size, float maxW) {
            int cut;
            if (Fonts.SEMIBOLD.width(text, size) <= maxW) {
                return text;
            }
            for (cut = text.length(); cut > 1; --cut) {
                String string = text.substring(0, cut);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                if (!(Fonts.SEMIBOLD.width(string + "…", size) > maxW)) break;
            }
            String string = text.substring(0, cut);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            return string + "…";
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/ui/messenger/UserActionsPopup$Page;", "", "<init>", "(Ljava/lang/String;I)V", "MAIN", "DURATION", "ROLE", "PREFIX", "rtx.kimiko:kimiko"})
    private static enum Page {
        MAIN,
        DURATION,
        ROLE,
        PREFIX;
@NotNull
        public static EnumEntries<Page> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/ui/messenger/UserActionsPopup$Row;", "", "", "label", "", "danger", "Lkotlin/Function0;", "", "run", "<init>", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function0;)V", "Ljava/lang/String;", "getLabel", "()Ljava/lang/String;", "Z", "getDanger", "()Z", "Lkotlin/jvm/functions/Function0;", "getRun", "()Lkotlin/jvm/functions/Function0;", "rtx.kimiko:kimiko"})
    private static final class Row {
        @NotNull
        private final String label;
        private final boolean danger;
        @NotNull
        private final Function0<Unit> run;

        public Row(@NotNull String label, boolean danger, @NotNull Function0<Unit> run) {
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            Intrinsics.checkNotNullParameter(run, (String)"run");
            this.label = label;
            this.danger = danger;
            this.run = run;
        }

        @NotNull
        public final String getLabel() {
            return this.label;
        }

        public final boolean getDanger() {
            return this.danger;
        }

        @NotNull
        public final Function0<Unit> getRun() {
            return this.run;
        }
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[Page.values().length];
            try {
                nArray[Page.MAIN.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Page.DURATION.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Page.ROLE.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Page.PREFIX.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

