/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags.components;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.HudContextMenu;
import rtx.kimiko.api.drags.components.ScoreboardComp;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.settings.impl.ButtonRowSetting;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u0000 @2\u00020\u0001:\u0001@B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0003J\r\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0014\u0010\bJ\r\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0015\u0010\bJ\u000f\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u000b\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u000b\u0010\bJ\u000f\u0010\f\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\f\u0010\bJ\u000f\u0010\u0019\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u0012J\u000f\u0010\u001a\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0012J\u000f\u0010\u001b\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u001b\u0010\bJ\u000f\u0010\u001c\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u0018J\u000f\u0010\u001d\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u0018J\u0015\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016\u00a2\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b$\u0010%J\u0015\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u001eH\u0014\u00a2\u0006\u0004\b'\u0010!J\u0017\u0010*\u001a\u00020\u00042\u0006\u0010)\u001a\u00020(H\u0016\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\u00042\u0006\u0010)\u001a\u00020(H\u0016\u00a2\u0006\u0004\b,\u0010+J\u0017\u0010/\u001a\u00020\u00042\u0006\u0010.\u001a\u00020-H\u0014\u00a2\u0006\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00107R\u0016\u00109\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00107R\u0016\u0010:\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u00107R\u0016\u0010<\u001a\u00020;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?\u00a8\u0006A"}, d2={"Lrtx/kimiko/api/drags/components/BossBarComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "cycleSize", "", "scale", "()F", "left", "top", "width", "height", "measured", "(FFFF)V", "anchorToVanilla", "", "hasLayout", "()Z", "hasMetrics", "vanillaLeft", "vanillaTop", "", "displayName", "()Ljava/lang/String;", "isVisible", "isInteractive", "screenMargin", "contextTitle", "contextHint", "", "Lrtx/kimiko/api/drags/HudContextMenu$Item;", "contextItems", "()Ljava/util/List;", "", "index", "setSize", "(I)V", "Lrtx/kimiko/api/ui/settings/Setting;", "buildHudSettings", "Lcom/google/gson/JsonObject;", "state", "writeState", "(Lcom/google/gson/JsonObject;)V", "readState", "Lnet/minecraft/DrawContext;", "graphics", "render", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "sizeSetting", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "sizeIndex", "I", "guiLeft", "F", "guiTop", "guiWidth", "guiHeight", "", "measuredAtMs", "J", "anchored", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class BossBarComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ButtonSetting sizeSetting = new ButtonSetting("Размер", "Масштаб полос боссов.");
    private int sizeIndex = 1;
    private float guiLeft;
    private float guiTop;
    private float guiWidth;
    private float guiHeight;
    private long measuredAtMs;
    private boolean anchored;
    @Nullable
    private static BossBarComp instance;
    private static final long STALE_MS = 500L;
    private static final float DEFAULT_X = 130.0f;
    private static final float DEFAULT_Y = 8.0f;

    public BossBarComp() {
        super("bossbar", 130.0f, 8.0f);
        instance = this;
        this.sizeSetting.label(ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko()[this.sizeIndex]).onClick(() -> BossBarComp._init_$lambda$0(this));
    }

    private final void cycleSize() {
        this.sizeIndex = (this.sizeIndex + 1) % ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko().length;
        this.sizeSetting.label(ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko()[this.sizeIndex]);
    }

    public final float scale() {
        return ScoreboardComp.Companion.getSIZE_FACTORS$rtx_kimiko_kimiko()[RangesKt.coerceIn((int)this.sizeIndex, (int)0, (int)(ScoreboardComp.Companion.getSIZE_FACTORS$rtx_kimiko_kimiko().length - 1))];
    }

    public final void measured(float left, float top, float width, float height) {
        this.guiLeft = left;
        this.guiTop = top;
        this.guiWidth = width;
        this.guiHeight = height;
        this.measuredAtMs = System.currentTimeMillis();
        this.anchorToVanilla();
    }

    private final void anchorToVanilla() {
        if (this.anchored) {
            return;
        }
        this.anchored = true;
        if (!(this.getDrag().getTargetX() == 130.0f) || !(this.getDrag().getTargetY() == 8.0f)) {
            return;
        }
        this.getDrag().setTargetX((float)Render2DCoordinateSpace.designXFromGui(this.guiLeft));
        this.getDrag().setTargetY((float)Render2DCoordinateSpace.designYFromGui(this.guiTop));
        this.getDrag().syncToTarget();
    }

    public final boolean hasLayout() {
        return this.guiWidth > 0.0f && this.guiHeight > 0.0f;
    }

    public final boolean hasMetrics() {
        return this.hasLayout() && System.currentTimeMillis() - this.measuredAtMs < 500L;
    }

    public final float vanillaLeft() {
        return this.guiLeft;
    }

    public final float vanillaTop() {
        return this.guiTop;
    }

    @Override
    @NotNull
    public String displayName() {
        return "Боссбар";
    }

    @Override
    public float width() {
        return this.hasLayout() ? ScoreboardComp.Companion.toDesign$rtx_kimiko_kimiko(this.guiWidth) * this.scale() : 91.0f;
    }

    @Override
    public float height() {
        return this.hasLayout() ? ScoreboardComp.Companion.toDesign$rtx_kimiko_kimiko(this.guiHeight) * this.scale() : 14.0f;
    }

    @Override
    public boolean isVisible() {
        return this.hasMetrics();
    }

    @Override
    public boolean isInteractive() {
        return this.hasMetrics();
    }

    @Override
    public float screenMargin() {
        return 0.0f;
    }

    @Override
    @NotNull
    public String contextTitle() {
        return "Размер";
    }

    @Override
    @NotNull
    public String contextHint() {
        return "Выбери размер элемента";
    }

    @Override
    @NotNull
    public List<HudContextMenu.Item> contextItems() {
        ArrayList<HudContextMenu.Item> list = new ArrayList<HudContextMenu.Item>();
        int n = ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko().length;
        for (int i = 0; i < n; ++i) {
            final int idx = i;
            list.add(new HudContextMenu.Item(ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko()[i], () -> BossBarComp.contextItems$lambda$0(this, idx), () -> BossBarComp.contextItems$lambda$1(this, idx)));
        }
        return list;
    }

    private final void setSize(int index) {
        this.sizeIndex = RangesKt.coerceIn((int)index, (int)0, (int)(ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko().length - 1));
        this.sizeSetting.label(ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko()[this.sizeIndex]);
    }

    @Override
    @NotNull
    protected List<Setting> buildHudSettings() {
        ArrayList<Setting> list = new ArrayList<>(super.buildHudSettings());
        list.add(new ButtonRowSetting(this.sizeSetting));
        return list;
    }

    @Override
    public void writeState(@NotNull JsonObject state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        state.addProperty("size", (Number)this.sizeIndex);
    }

    @Override
    public void readState(@NotNull JsonObject state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        if (state.has("size")) {
            this.sizeIndex = RangesKt.coerceIn((int)state.get("size").getAsInt(), (int)0, (int)(ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko().length - 1));
            this.sizeSetting.label(ScoreboardComp.Companion.getSIZE_LABELS$rtx_kimiko_kimiko()[this.sizeIndex]);
        }
    }

    @Override
    protected void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
    }

    private static final void _init_$lambda$0(BossBarComp this$0) {
        this$0.cycleSize();
    }

    private static final boolean contextItems$lambda$0(BossBarComp this$0, int $i) {
        return this$0.sizeIndex == $i;
    }

    private static final void contextItems$lambda$1(BossBarComp this$0, int $i) {
        this$0.setSize($i);
    }

    @JvmStatic
    @Nullable
    public static final BossBarComp get() {
        return Companion.get();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000f\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/drags/components/BossBarComp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/drags/components/BossBarComp;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/drags/components/BossBarComp;", "instance", "Lrtx/kimiko/api/drags/components/BossBarComp;", "", "STALE_MS", "J", "", "DEFAULT_X", "F", "DEFAULT_Y", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final BossBarComp get() {
            return instance;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

