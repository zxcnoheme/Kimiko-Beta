/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  net.minecraft.client.input.CharInput
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.client.gui.Click
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.ingame.InventoryScreen
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package rtx.kimiko.api.invmanager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.gui.Click;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.invmanager.InventoryTemplates;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.BaseScreen;
import rtx.kimiko.api.ui.ClientLanguage;
import rtx.kimiko.api.ui.ScrollBar;
import rtx.kimiko.api.ui.module.SearchField;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.window.PanelAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000f\u0018\u0000 \u0082\u00012\u00020\u0001:\u0004\u0083\u0001\u0082\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0003J'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0015\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001aJ\u000f\u0010\u001d\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001aJ\u000f\u0010\u001e\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001aJ\u000f\u0010\u001f\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001f\u0010\u001aJ\u000f\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b \u0010\u0003J/\u0010&\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\u0013H\u0014\u00a2\u0006\u0004\b&\u0010'J)\u0010*\u001a\u0004\u0018\u00010\b2\b\u0010)\u001a\u0004\u0018\u00010(2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0002\u00a2\u0006\u0004\b*\u0010+J)\u0010-\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020!2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010,\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b-\u0010.JG\u00104\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020!2\u0006\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b4\u00105J/\u00107\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00132\u0006\u00106\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b7\u00108JK\u0010<\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020!2\u0006\u0010\t\u001a\u00020\b2\u0006\u00109\u001a\u00020\n2\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\n0:H\u0002\u00a2\u0006\u0004\b<\u0010=J\u001f\u0010A\u001a\u00020\f2\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020\fH\u0016\u00a2\u0006\u0004\bA\u0010BJ\u0017\u0010C\u001a\u00020\f2\u0006\u0010?\u001a\u00020>H\u0016\u00a2\u0006\u0004\bC\u0010DJ/\u0010J\u001a\u00020\f2\u0006\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020E2\u0006\u0010H\u001a\u00020E2\u0006\u0010I\u001a\u00020EH\u0016\u00a2\u0006\u0004\bJ\u0010KJ\u0017\u0010M\u001a\u00020\f2\u0006\u0010?\u001a\u00020LH\u0016\u00a2\u0006\u0004\bM\u0010NJ\u0017\u0010P\u001a\u00020\f2\u0006\u0010?\u001a\u00020OH\u0016\u00a2\u0006\u0004\bP\u0010QJ\u000f\u0010R\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\bR\u0010\u0003J\u000f\u0010S\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bS\u0010\u001aJ\u000f\u0010T\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bT\u0010\u001aJ\u000f\u0010U\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bU\u0010\u001aJ\u000f\u0010V\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bV\u0010\u001aJK\u0010\\\u001a\u00020\u00132\"\u0010Y\u001a\u001e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00130Wj\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0013`X2\u0006\u0010Z\u001a\u00020(2\u0006\u0010[\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\\\u0010]R\u0014\u0010_\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010`R0\u0010a\u001a\u001e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00130Wj\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0013`X8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR0\u0010c\u001a\u001e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00130Wj\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0013`X8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010bR0\u0010d\u001a\u001e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00130Wj\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0013`X8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010bR \u0010f\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u000e0e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0014\u0010i\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0016\u0010k\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0016\u0010m\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010lR\u0014\u0010o\u001a\u00020n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0018\u0010q\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0016\u0010s\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010lR\u0016\u0010t\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010lR\u0016\u0010v\u001a\u00020u8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010lR\u0016\u0010y\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010lR\u0016\u0010z\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010lR\u0016\u0010{\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010lR\u0016\u0010|\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010lR\u0016\u0010}\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010lR\u0016\u0010~\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010lR\u0016\u0010\u007f\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010lR\u0018\u0010\u0080\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010lR\u0018\u0010\u0081\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010l\u00a8\u0006\u0084\u0001"}, d2={"Lrtx/kimiko/api/invmanager/InventoryManagerScreen;", "Lrtx/kimiko/api/ui/BaseScreen;", "<init>", "()V", "", "onClosingOverlayDropped", "init", "primeRows", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "template", "", "index", "", "instant", "Lrtx/kimiko/api/invmanager/InventoryManagerScreen$RowAnim;", "rowAnim", "(Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;IZ)Lrtx/kimiko/api/invmanager/InventoryManagerScreen$RowAnim;", "", "all", "", "dt", "syncRows", "(Ljava/util/List;F)V", "orderedRows", "()Ljava/util/List;", "listX", "()F", "listY", "listW", "listH", "contentHeight", "maxScroll", "layout", "Lnet/minecraft/DrawContext;", "graphics", "vanillaMouseX", "vanillaMouseY", "partialTick", "renderScreen", "(Lnet/minecraft/DrawContext;IIF)V", "", "hoveredRow", "resolvePreview", "(Ljava/lang/String;Ljava/util/List;)Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "a", "renderPreview", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;F)V", "title", "x", "y", "w", "h", "section", "(Lnet/minecraft/DrawContext;Ljava/lang/String;FFFFF)V", "empty", "cell", "(FFFZ)V", "slot", "", "missing", "stack", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;IFFLjava/util/Map;)V", "Lnet/minecraft/Click;", "event", "doubleClick", "mouseClicked", "(Lnet/minecraft/Click;Z)Z", "mouseReleased", "(Lnet/minecraft/Click;)Z", "", "mouseX", "mouseY", "horizontal", "vertical", "mouseScrolled", "(DDDD)Z", "Lnet/minecraft/KeyInput;", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "Lnet/minecraft/CharInput;", "charTyped", "(Lnet/minecraft/CharInput;)Z", "onClose", "delX", "starX", "backX", "backY", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "map", "key", "target", "step", "(Ljava/util/HashMap;Ljava/lang/String;FF)F", "Lrtx/kimiko/api/ui/module/SearchField;", "nameField", "Lrtx/kimiko/api/ui/module/SearchField;", "rowHover", "Ljava/util/HashMap;", "delHover", "starHover", "Ljava/util/LinkedHashMap;", "rowAnims", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/api/ui/ScrollBar;", "scrollBar", "Lrtx/kimiko/api/ui/ScrollBar;", "scroll", "F", "scrollTarget", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "anim", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "selectedName", "Ljava/lang/String;", "hoverSave", "hoverBack", "", "lastNs", "J", "px", "py", "leftX", "leftY", "leftW", "leftH", "rightX", "rightY", "rightW", "rightH", "Companion", "RowAnim", "rtx.kimiko:kimiko"})
public final class InventoryManagerScreen
extends BaseScreen {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SearchField nameField;
    @NotNull
    private final HashMap<String, Float> rowHover;
    @NotNull
    private final HashMap<String, Float> delHover;
    @NotNull
    private final HashMap<String, Float> starHover;
    @NotNull
    private final LinkedHashMap<String, RowAnim> rowAnims;
    @NotNull
    private final ScrollBar scrollBar;
    private float scroll;
    private float scrollTarget;
    @NotNull
    private final PanelAnimation anim;
    @Nullable
    private String selectedName;
    private float hoverSave;
    private float hoverBack;
    private long lastNs;
    private float px;
    private float py;
    private float leftX;
    private float leftY;
    private float leftW;
    private float leftH;
    private float rightX;
    private float rightY;
    private float rightW;
    private float rightH;
    private static final float PANEL_W = 350.0f;
    private static final float PANEL_H = 252.0f;
    private static final float PANEL_R = 12.0f;
    private static final float PAD = 12.0f;
    private static final float HEADER_H = 26.0f;
    private static final float FOOTER_H = 20.0f;
    private static final float LEFT_W = 132.0f;
    private static final float ROW_H = 21.0f;
    private static final float ROW_PITCH = 24.0f;
    private static final float ROWS_Y = 48.0f;
    private static final float SAVE_H = 14.0f;
    private static final float DEL_SIZE = 10.0f;
    private static final float CELL = 16.0f;
    private static final float PITCH = 17.0f;
    private static final float GRID_W = 152.0f;
    private static final float SECTION_CELLS_Y = 22.0f;
    private static final float SECTION_GAP = 5.0f;
    @NotNull
    private static final String ICON_PLUS = "a";
    @NotNull
    private static final String ICON_CUBE = "b";
    @NotNull
    private static final String ICON_MOUSE = "d";
    @NotNull
    private static final String ICON_SWORD = "e";

    public InventoryManagerScreen() {
        super((Text)Text.literal("Инвентарь"));
        this.nameField = new SearchField().placeholder("Название сета...").icon("");
        this.rowHover = new HashMap();
        this.delHover = new HashMap();
        this.starHover = new HashMap();
        this.rowAnims = new LinkedHashMap();
        this.scrollBar = new ScrollBar();
        this.anim = new PanelAnimation(true, true);
    }

    @Override
    protected void onClosingOverlayDropped() {
        this.anim.finish();
    }

    protected void init() {
        this.layout();
        this.primeRows();
        this.anim.setPanelRect(this.px, this.py, 350.0f, 252.0f);
        this.anim.open();
        Sounds.play("gui_open");
    }

    private final void primeRows() {
        this.rowAnims.clear();
        List<InventoryTemplates.Template> all = InventoryTemplates.all();
        int n = ((Collection)all).size();
        for (int i = 0; i < n; ++i) {
            this.rowAnim(all.get(i), i, true);
        }
    }

    private final RowAnim rowAnim(InventoryTemplates.Template template, int index, boolean instant) {
        RowAnim row = this.rowAnims.get(template.name);
        if (row == null) {
            row = new RowAnim();
            row.setY((float)index * 24.0f);
            row.setAppear(instant ? 1.0f : 0.0f);
            ((Map)this.rowAnims).put(template.name, row);
        }
        row.setTemplate(template);
        row.setTargetY((float)index * 24.0f);
        return row;
    }

    private final void syncRows(List<InventoryTemplates.Template> all, float dt) {
        boolean firstFill = this.rowAnims.isEmpty();
        HashSet<String> alive = new HashSet<String>();
        int n = ((Collection)all).size();
        for (int i = 0; i < n; ++i) {
            InventoryTemplates.Template template = all.get(i);
            alive.add(template.name);
            this.rowAnim(template, i, firstFill);
        }
        Iterator<Map.Entry<String, RowAnim>> iterator = this.rowAnims.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, RowAnim> entry = iterator.next();
            RowAnim row = entry.getValue();
            row.setDying(!alive.contains(entry.getKey()));
            row.setY(InventoryManagerScreen.Companion.approach(row.getY(), row.getTargetY(), dt, 13.0f));
            row.setAppear(InventoryManagerScreen.Companion.approach(row.getAppear(), row.getDying() ? 0.0f : 1.0f, dt, 11.0f));
            if (!row.getDying() || !(row.getAppear() < 0.02f)) continue;
            this.rowHover.remove(entry.getKey());
            this.delHover.remove(entry.getKey());
            this.starHover.remove(entry.getKey());
            iterator.remove();
        }
    }

    private final List<RowAnim> orderedRows() {
        ArrayList<RowAnim> ordered = new ArrayList<RowAnim>(this.rowAnims.size());
        for (RowAnim row : this.rowAnims.values()) {
            if (!row.getDying() || row.getTemplate() == null) continue;
            ordered.add(row);
        }
        for (RowAnim row : this.rowAnims.values()) {
            if (row.getDying() || row.getTemplate() == null) continue;
            ordered.add(row);
        }
        return ordered;
    }

    private final float listX() {
        return this.leftX;
    }

    private final float listY() {
        return this.leftY + 48.0f;
    }

    private final float listW() {
        return this.leftW;
    }

    private final float listH() {
        return this.leftH - 48.0f;
    }

    private final float contentHeight() {
        int count = 0;
        Iterator<RowAnim> iterator = this.rowAnims.values().iterator();
        while (iterator.hasNext()) {
            RowAnim row = (RowAnim) (iterator.next());
            if (row.getDying()) continue;
            ++count;
        }
        return count <= 0 ? 0.0f : (float)count * 24.0f - 3.0f;
    }

    private final float maxScroll() {
        return Math.max(0.0f, this.contentHeight() - this.listH());
    }

    private final void layout() {
        this.px = Position.Companion.screenWidth() / 2.0f - 175.0f;
        this.py = Position.Companion.screenHeight() / 2.0f - 126.0f;
        this.leftX = this.px + 12.0f;
        this.leftY = this.py + 26.0f + 6.0f;
        this.leftW = 132.0f;
        this.leftH = 196.0f;
        this.rightX = this.leftX + this.leftW + 12.0f;
        this.rightY = this.leftY;
        this.rightW = this.px + 350.0f - 12.0f - this.rightX;
        this.rightH = this.leftH;
    }

    @Override
    protected void renderScreen(@NotNull DrawContext graphics, int vanillaMouseX, int vanillaMouseY, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.anim.updateFrame();
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (this.anim.isCloseFinished()) {
            this.anim.finish();
            BaseScreen.Companion.cancelClosingOverlay(this);
            if (mc.currentScreen == this || mc.currentScreen == null) {
                ClientPlayerEntity player = mc.player;
                mc.setScreen((Screen)(player != null ? new InventoryScreen((PlayerEntity)player) : null));
            }
            return;
        }
        long now = System.nanoTime();
        float dt = this.lastNs == 0L ? 0.016f : Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        boolean captured = this.anim.captureActive();
        float a = this.anim.contentAlpha();
        float scale = captured ? 1.0f : this.anim.motion().scale();
        this.layout();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        boolean interact = this.anim.canInteract();
        this.hoverBack = InventoryManagerScreen.Companion.approach(this.hoverBack, interact && InventoryManagerScreen.Companion.hover(mx, my, this.backX(), this.backY(), 58.0f, 12.0f) ? 1.0f : 0.0f, dt);
        this.hoverSave = InventoryManagerScreen.Companion.approach(this.hoverSave, interact && InventoryManagerScreen.Companion.hover(mx, my, this.leftX, this.leftY + 17.0f, this.leftW, 14.0f) ? 1.0f : 0.0f, dt);
        Render2D.rect(-5.0f, -5.0f, Position.Companion.screenWidth() + 10.0f, Position.Companion.screenHeight() + 10.0f, 0.0f, InventoryManagerScreen.Companion.col(0, 0, 0, (float)110 * this.anim.dimAlpha()));
        this.anim.beginCaptureStratum(graphics);
        float cx = this.px + 175.0f;
        float cy = this.py + 126.0f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(cx, cy);
        graphics.getMatrices().scale(scale, scale);
        graphics.getMatrices().translate(-cx, -cy);
        RectUtil.drawClientRect(this.px, this.py, 350.0f, 252.0f, 12.0f, a, 6.0f);
        Fonts.INV_ICONS.msdf(ICON_CUBE, this.px + 12.0f + 0.5f, this.py + 10.5f, 8.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)235 * a));
        Fonts.SEMIBOLD.draw(I18n.tr("Менеджер инвентарей"), this.px + 12.0f + 12.0f, this.py + 9.5f, 8.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)240 * a));
        List<InventoryTemplates.Template> all = InventoryTemplates.all();
        int backColor = InventoryManagerScreen.Companion.col(255, 255, 255, ((float)150 + (float)105 * this.hoverBack) * a);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(this.backX() - 10.0f, this.backY() + 6.2f);
        graphics.getMatrices().scale(-1.0f, 1.0f);
        Fonts.I2.msdf("G", -27.0f, -2.5f, 6.0f, backColor);
        graphics.getMatrices().popMatrix();
        Fonts.MEDIUM.draw(I18n.tr("Назад"), this.backX() + 19.0f, this.backY() + 3.2f, 5.6f, backColor);
        float escX = this.backX() + 19.0f + Fonts.MEDIUM.width(I18n.tr("Назад"), 5.5f) + 5.0f;
        Render2D.rect(escX, this.backY() + 2.0f, 16.0f, 10.5f, 3.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)16 * a));
        Render2D.outline(escX, this.backY() + 2.0f, 16.0f, 10.5f, 3.0f, 0.4f, InventoryManagerScreen.Companion.col(255, 255, 255, ((float)30 + (float)40 * this.hoverBack) * a));
        float escW = Fonts.MEDIUM.width("Esc", 4.4f);
        Fonts.MEDIUM.draw("Esc", escX + (16.0f - escW) * 0.5f - 0.5f, this.backY() + 4.5f, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)170 * a));
        Render2D.rect(this.px + 12.0f, this.py + 26.0f, 326.0f, 0.6f, 0.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)16 * a));
        Render2D.rect(this.rightX - 6.0f, this.leftY + 2.0f, 0.6f, this.leftH - 4.0f, 0.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)12 * a));
        this.nameField.render(graphics, this.leftX, this.leftY, this.leftW, 13.0f, a, mx, my, dt);
        float sby = this.leftY + 17.0f;
        float saveAlpha = ((float)115 + (float)55 * this.hoverSave) * a;
        Render2D.rect(this.leftX, sby, this.leftW, 14.0f, 4.0f, ClientAccent.gradientColor(0.0f, saveAlpha), ClientAccent.gradientColor(0.33f, saveAlpha), ClientAccent.gradientColor(0.66f, saveAlpha), ClientAccent.gradientColor(1.0f, saveAlpha));
        Render2D.outlineClient$default(this.leftX, sby, this.leftW, 14.0f, 4.0f, 0.1f, ClientAccent.accentBright(((float)90 + (float)70 * this.hoverSave) * a), 0.0f, 128, null);
        String saveLabel = I18n.tr("Сохранить текущий сет");
        float saveW = Fonts.MEDIUM.width(saveLabel, 5.6f);
        float saveIconW = Fonts.INV_ICONS.msdfWidth(ICON_PLUS, 7.0f);
        float saveGroupW = saveIconW + 3.0f + saveW;
        float saveGroupX = this.leftX + (this.leftW - saveGroupW) * 0.5f;
        Fonts.INV_ICONS.msdf(ICON_PLUS, saveGroupX, sby + 3.5f, 7.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)240 * a));
        Fonts.MEDIUM.draw(saveLabel, saveGroupX + saveIconW + 3.0f, sby + 3.8f, 5.6f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)240 * a));
        Fonts.SEMIBOLD.draw(I18n.tr("Мои сеты"), this.leftX + 1.0f, this.leftY + 37.0f, 6.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)220 * a));
        String countText = String.valueOf(all.size());
        float countW = Fonts.MEDIUM.width(countText, 5.0f);
        float badgeW = countW + 7.0f;
        Render2D.rect(this.leftX + this.leftW - badgeW, this.leftY + 35.4f, badgeW, 9.5f, 3.5f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)18 * a));
        Fonts.MEDIUM.draw(countText, this.leftX + this.leftW - badgeW + 3.5f, this.leftY + 37.4f, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)190 * a));
        String hoveredRow = null;
        String activeName = InventoryTemplates.activeName();
        this.syncRows(all, dt);
        float listX = this.listX();
        float listY = this.listY();
        float listW = this.listW();
        float listH = this.listH() + 4.5f;
        this.scrollTarget = Math.max(0.0f, Math.min(this.scrollTarget, this.maxScroll()));
        this.scroll += (this.scrollTarget - this.scroll) * (1.0f - (float)Math.exp(-dt * 16.0f));
        if (Math.abs(this.scrollTarget - this.scroll) < 0.05f) {
            this.scroll = this.scrollTarget;
        }
        boolean overList = interact && InventoryManagerScreen.Companion.hover(mx, my, listX, listY, listW, listH);
        Render2D.pushScissor(graphics, listX, listY, listW + 8.0f, listH);
        for (RowAnim row : this.orderedRows()) {
            boolean rowHovered;
            InventoryTemplates.Template template = row.getTemplate();
            if (template == null) continue;
            float rowY;
            float ease = row.getAppear() * row.getAppear() * (3.0f - 2.0f * row.getAppear());
            float ra = a * ease;
            if (ease < 0.02f || (rowY = listY + row.getY() - this.scroll) + 21.0f < listY || rowY > listY + listH) continue;
            float slide = InventoryManagerScreen.Companion.rowSlide(row);
            float rowX = listX + slide;
            boolean active = Intrinsics.areEqual((Object)template.name, (Object)activeName);
            boolean bl = rowHovered = overList && !row.getDying() && InventoryManagerScreen.Companion.hover(mx, my, rowX, rowY, listW, 21.0f);
            if (rowHovered) {
                hoveredRow = template.name;
            }
            float h = this.step(this.rowHover, template.name, rowHovered ? 1.0f : 0.0f, dt);
            float rw = listW;
            if (active) {
                float selectedAlpha = ((float)46 + (float)12 * h) * ra;
                Render2D.rect(rowX, rowY, rw, 21.0f, 5.0f, ClientAccent.gradientColor(0.0f, selectedAlpha), ClientAccent.gradientColor(0.33f, selectedAlpha), ClientAccent.gradientColor(0.66f, selectedAlpha), ClientAccent.gradientColor(1.0f, selectedAlpha));
                Render2D.outlineClient$default(rowX, rowY, rw, 21.0f, 5.0f, 0.5f, ClientAccent.accentBright((float)130 * ra), 0.0f, 128, null);
            } else {
                Render2D.rect(rowX, rowY, rw, 21.0f, 5.0f, InventoryManagerScreen.Companion.col(0, 0, 0, (float)40 * ra));
                if (h > 0.02f) {
                    Render2D.rect(rowX, rowY, rw, 21.0f, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)14 * h * ra));
                }
            }
            float tileX = rowX + 3.0f;
            float tileY = rowY + 3.0f;
            Render2D.rect(tileX, tileY, 15.0f, 15.0f, 4.0f, InventoryManagerScreen.Companion.col(0, 0, 0, (float)55 * ra));
            Fonts.INV_ICONS.msdf(ICON_SWORD, tileX + 3.0f, tileY + 3.0f, 9.0f, ClientAccent.accentBright((float)225 * ra));
            float textX = rowX + 22.0f;
            Fonts.MEDIUM.draw(template.name, textX, rowY + 3.4f, 6.5f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)235 * ra));
            String sub = template.itemCount() + " " + InventoryManagerScreen.Companion.itemsWord(template.itemCount());
            int subColor = active ? ClientAccent.accentSoft((float)200 * ra) : InventoryManagerScreen.Companion.col(255, 255, 255, (float)110 * ra);
            Fonts.MEDIUM.draw(sub, textX, rowY + 12.2f, 4.6f, subColor);
            float sx = this.starX() + slide;
            float dx = this.delX() + slide;
            float dy = rowY + 5.5f;
            boolean starHovered = overList && !row.getDying() && InventoryManagerScreen.Companion.hover(mx, my, sx, dy, 10.0f, 10.0f);
            float sh = this.step(this.starHover, template.name, starHovered ? 1.0f : 0.0f, dt);
            boolean starredNow = InventoryTemplates.isFavorite(template.name);
            float sg = Math.max(starredNow ? 1.0f : 0.0f, sh);
            Render2D.rect(sx, dy, 10.0f, 10.0f, 3.0f, InventoryManagerScreen.Companion.col(76 + (int)((float)94 * sg), 80 + (int)((float)55 * sg), 87 - (int)((float)47 * sg), ((float)75 + (float)75 * sg) * ra));
            String starIcon = starredNow ? "M" : "L";
            float starW = Fonts.I2.msdfWidth(starIcon, 6.5f);
            Fonts.I2.msdf(starIcon, sx + (10.0f - starW) * 0.5f, dy + 1.7f, 6.5f, InventoryManagerScreen.Companion.col(175 + (int)((float)80 * sg), 179 + (int)((float)21 * sg), 185 - (int)((float)125 * sg), (float)230 * ra));
            boolean delHovered = overList && !row.getDying() && InventoryManagerScreen.Companion.hover(mx, my, dx, dy, 10.0f, 10.0f);
            float dh = this.step(this.delHover, template.name, delHovered ? 1.0f : 0.0f, dt);
            Render2D.rect(dx, dy, 10.0f, 10.0f, 3.0f, InventoryManagerScreen.Companion.col((int)((float)76 + (float)148 * dh), (int)((float)80 - (float)8 * dh), (int)((float)87 + (float)5 * dh), ((float)75 + (float)75 * dh) * ra));
            float trashW = Fonts.I2.msdfWidth("N", 6.5f);
            Fonts.I2.msdf("N", dx + (10.0f - trashW) * 0.5f, dy + 1.7f, 6.5f, InventoryManagerScreen.Companion.col((int)((float)175 + (float)65 * dh), (int)((float)179 - (float)83 * dh), (int)((float)185 - (float)75 * dh), (float)230 * ra));
        }
        Render2D.popScissor(graphics);
        float contentH = this.contentHeight();
        if (contentH > listH + 0.5f) {
            float newScroll = this.scrollBar.render(this.leftX + this.leftW + 2.5f, listY, listH, listH, contentH, this.scroll, a);
            if (this.scrollBar.isDragging()) {
                this.scroll = newScroll;
                this.scrollTarget = newScroll;
            }
        }
        if (all.isEmpty()) {
            String emptyHint = I18n.tr("Сохрани свой первый сет");
            float emptyHintW = Fonts.MEDIUM.width(emptyHint, 5.5f);
            Fonts.MEDIUM.draw(emptyHint, this.leftX + (this.leftW - emptyHintW) * 0.5f, listY + 40.0f, 5.5f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)90 * a));
        }
        float footLineY = this.py + 252.0f - 20.0f;
        Render2D.rect(this.px + 12.0f, footLineY, 326.0f, 0.6f, 0.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)16 * a));
        float footY = footLineY + 6.5f;
        float dotX = this.leftX + 7.0f + Fonts.MEDIUM.width(I18n.tr("Текущий сет"), 5.0f) + 4.0f;
        if (activeName != null) {
            Render2D.circle(this.leftX + 2.0f, footY + 2.9f, 1.6f, ClientAccent.accent((float)230 * a));
            Fonts.MEDIUM.draw(I18n.tr("Текущий сет"), this.leftX + 7.0f, footY, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)190 * a));
            Render2D.circle(dotX, footY + 3.5f, 1.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)80 * a));
            Fonts.MEDIUM.draw(activeName, dotX + 4.0f, footY, 5.0f, ClientAccent.accentSoft((float)220 * a));
        } else {
            Render2D.circle(this.leftX + 2.0f, footY + 2.9f, 1.6f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)70 * a));
            Fonts.MEDIUM.draw(I18n.tr("Текущий сет"), this.leftX + 7.0f, footY, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)150 * a));
            Render2D.circle(dotX, footY + 2.9f, 1.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)80 * a));
            Fonts.MEDIUM.draw(I18n.tr("не выбран"), dotX + 4.0f, footY, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)110 * a));
        }
        String hint = I18n.tr("Клик по сету — активировать");
        float hintW = Fonts.MEDIUM.width(hint, 4.8f);
        float mouseW = Fonts.INV_ICONS.msdfWidth(ICON_MOUSE, 7.0f);
        float hintX = this.px + 350.0f - 12.0f - hintW - (float)4;
        Fonts.INV_ICONS.msdf(ICON_MOUSE, hintX - mouseW - 3.0f, footY - 1.0f, 7.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)130 * a));
        Fonts.MEDIUM.draw(hint, hintX, footY - 1.0f, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)120 * a));
        this.renderPreview(graphics, this.resolvePreview(hoveredRow, all), a);
        graphics.getMatrices().popMatrix();
    }

    private final InventoryTemplates.Template resolvePreview(String hoveredRow, List<InventoryTemplates.Template> all) {
        InventoryTemplates.Template selected;
        InventoryTemplates.Template hovered;
        if (hoveredRow != null && (hovered = InventoryTemplates.byName(hoveredRow)) != null) {
            return hovered;
        }
        if (this.selectedName != null && (selected = InventoryTemplates.byName(this.selectedName)) != null) {
            return selected;
        }
        InventoryTemplates.Template active = InventoryTemplates.active();
        if (active != null) {
            return active;
        }
        return all.isEmpty() ? null : all.get(0);
    }

    private final void renderPreview(DrawContext graphics, InventoryTemplates.Template template, float a) {
        int colIdx;
        int colIdx2;
        int row;
        int i;
        Render2D.rect(this.rightX, this.rightY + 1.2f, 1.4f, 8.5f, 0.7f, ClientAccent.accent((float)230 * a));
        Fonts.SEMIBOLD.draw(I18n.tr("Превью сета"), this.rightX + 5.5f, this.rightY + 2.0f, 6.5f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)240 * a));
        if (template == null) {
            String hint = I18n.tr("Здесь появится твой сет");
            float hintW = Fonts.MEDIUM.width(hint, 5.5f);
            Fonts.MEDIUM.draw(hint, this.rightX + (this.rightW - hintW) * 0.5f, this.rightY + this.rightH * 0.5f - 3.0f, 5.5f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)85 * a));
            return;
        }
        float previewDotX = this.rightX + 5.5f + Fonts.SEMIBOLD.width(I18n.tr("Превью сета"), 6.5f) + 5.0f;
        Render2D.circle(previewDotX, this.rightY + 6.0f, 1.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)80 * a));
        float previewNameX = previewDotX + 4.0f;
        Fonts.MEDIUM.fade(template.name, previewNameX, this.rightY + 3.0f, 5.0f, ClientAccent.accentSoft((float)210 * a), previewNameX, this.rightX + this.rightW - 46.0f, 8.0f, false, true);
        String count = template.itemCount() + " " + InventoryManagerScreen.Companion.itemsWord(template.itemCount());
        float countW = Fonts.MEDIUM.width(count, 5.0f);
        Fonts.MEDIUM.draw(count, this.rightX + this.rightW - countW, this.rightY + 3.0f, 5.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)130 * a));
        Map missing = InventoryManagerScreen.Companion.missingCounts(template);
        float equipH = 42.0f;
        float invH = 76.0f;
        float hotbarH = 42.0f;
        float totalH = equipH + invH + hotbarH + 10.0f;
        float startY = this.rightY + 14.0f + Math.max(0.0f, (this.rightH - 14.0f - totalH) * 0.5f);
        float gx = this.rightX + (this.rightW - 152.0f) * 0.5f;
        float equipY = startY;
        float invY = equipY + equipH + 5.0f;
        float hotbarY = invY + invH + 5.0f;
        this.section(graphics, I18n.tr("Экипировка"), this.rightX, equipY, this.rightW, equipH, a);
        this.section(graphics, I18n.tr("Инвентарь"), this.rightX, invY, this.rightW, invH, a);
        this.section(graphics, I18n.tr("Хотбар"), this.rightX, hotbarY, this.rightW, hotbarH, a);
        float equipCellsY = equipY + 22.0f;
        float invCellsY = invY + 22.0f;
        float hotbarCellsY = hotbarY + 22.0f;
        for (i = 0; i < 4; ++i) {
            this.cell(gx + (float)i * 17.0f, equipCellsY, a, InventoryManagerScreen.Companion.isEmpty(template, 39 - i));
        }
        this.cell(gx + 68.0f + 4.0f, equipCellsY, a, InventoryManagerScreen.Companion.isEmpty(template, 40));
        for (row = 0; row < 3; ++row) {
            for (colIdx2 = 0; colIdx2 < 9; ++colIdx2) {
                this.cell(gx + (float)colIdx2 * 17.0f, invCellsY + (float)row * 17.0f, a, InventoryManagerScreen.Companion.isEmpty(template, 9 + row * 9 + colIdx2));
            }
        }
        for (colIdx = 0; colIdx < 9; ++colIdx) {
            this.cell(gx + (float)colIdx * 17.0f, hotbarCellsY, a, InventoryManagerScreen.Companion.isEmpty(template, colIdx));
        }
        Render2D.flush();
        for (i = 0; i < 4; ++i) {
            this.stack(graphics, template, 39 - i, gx + (float)i * 17.0f, equipCellsY, missing);
        }
        this.stack(graphics, template, 40, gx + 68.0f + 4.0f, equipCellsY, missing);
        for (row = 0; row < 3; ++row) {
            for (colIdx2 = 0; colIdx2 < 9; ++colIdx2) {
                this.stack(graphics, template, 9 + row * 9 + colIdx2, gx + (float)colIdx2 * 17.0f, invCellsY + (float)row * 17.0f, missing);
            }
        }
        for (colIdx = 0; colIdx < 9; ++colIdx) {
            this.stack(graphics, template, colIdx, gx + (float)colIdx * 17.0f, hotbarCellsY, missing);
        }
        Render2D.beginFrame(graphics);
    }

    private final void section(DrawContext graphics, String title, float x, float y, float w, float h, float a) {
        Render2D.circle(x + 2.0f, y + 6.7f, 1.5f, ClientAccent.accent((float)220 * a));
        Fonts.MEDIUM.draw(title, x + 6.5f, y + 4.0f, 5.4f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)200 * a));
        float lineX = x + 6.5f + Fonts.MEDIUM.width(title, 5.4f) + 6.0f;
        Render2D.rect(lineX, y + 6.4f, x + w - lineX, 0.5f, 0.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)14 * a));
    }

    private final void cell(float x, float y, float a, boolean empty) {
        Render2D.rect(x, y, 16.0f, 16.0f, 2.5f, InventoryManagerScreen.Companion.col(0, 0, 0, (float)46 * a));
        if (empty) {
            float iconW = Fonts.I2.msdfWidth(ICON_PLUS, 6.0f);
            Fonts.I2.msdf(ICON_PLUS, x + (16.0f - iconW) * 0.5f, y + 5.0f, 6.0f, InventoryManagerScreen.Companion.col(255, 255, 255, (float)30 * a));
        }
    }

    private final void stack(DrawContext graphics, InventoryTemplates.Template template, int slot, float x, float y, Map<String, Integer> missing) {
        InventoryTemplates.Entry entry = template.slots.get(slot);
        if (entry == null) {
            return;
        }
        InventoryTemplates.Entry entry2 = entry;
        ItemStack stack = InventoryTemplates.stackFor(entry2);
        if (stack.isEmpty()) {
            return;
        }
        int missingCount = InventoryManagerScreen.Companion.takeMissing(missing, entry2);
        graphics.getMatrices().pushMatrix();
        Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
        Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
        Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
        graphics.getMatrices().translate(x, y);
        graphics.drawItem(stack, 0, 0);
        if (missingCount > 0) {
            InventoryManagerScreen.Companion.drawMissingCount(graphics, missingCount, 0, 0);
        } else {
            graphics.drawStackOverlay(MinecraftClient.getInstance().textRenderer, stack, 0, 0);
        }
        graphics.getMatrices().popMatrix();
    }

    public boolean mouseClicked(@NotNull Click event, boolean doubleClick) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.anim.canInteract()) {
            return true;
        }
        this.layout();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        int button = event.button();
        if (this.nameField.mouseClicked(mx, my, button)) {
            return true;
        }
        if (button != 0) {
            return super.mouseClicked(event, doubleClick);
        }
        if (InventoryManagerScreen.Companion.hover(mx, my, this.backX(), this.backY(), 58.0f, 12.0f)) {
            this.close();
            return true;
        }
        if (InventoryManagerScreen.Companion.hover(mx, my, this.leftX, this.leftY + 17.0f, this.leftW, 14.0f)) {
            InventoryTemplates.Template saved = InventoryTemplates.captureCurrent(this.nameField.getText());
            if (saved != null) {
                this.selectedName = saved.name;
                this.nameField.setText("");
                this.nameField.blur();
                Sounds.play("select_category");
            }
            return true;
        }
        if (this.scrollBar.tryGrab(mx, my)) {
            return true;
        }
        boolean overList = InventoryManagerScreen.Companion.hover(mx, my, this.listX(), this.listY(), this.listW(), this.listH());
        for (RowAnim row : this.orderedRows()) {
            if (row.getDying() || !overList) continue;
            InventoryTemplates.Template template = row.getTemplate();
            if (template == null) continue;
            float rowY = this.listY() + row.getY() - this.scroll;
            float slide = InventoryManagerScreen.Companion.rowSlide(row);
            float rowX = this.listX() + slide;
            float dx = this.delX() + slide;
            float dy = rowY + 5.5f;
            if (InventoryManagerScreen.Companion.hover(mx, my, dx, dy, 10.0f, 10.0f)) {
                InventoryTemplates.delete(template.name);
                if (Intrinsics.areEqual((Object)template.name, (Object)this.selectedName)) {
                    this.selectedName = null;
                }
                return true;
            }
            if (InventoryManagerScreen.Companion.hover(mx, my, this.starX() + slide, dy, 10.0f, 10.0f)) {
                InventoryTemplates.toggleFavorite(template.name);
                Sounds.play("select_category");
                return true;
            }
            if (!InventoryManagerScreen.Companion.hover(mx, my, rowX, rowY, this.listW(), 21.0f)) continue;
            boolean active = Intrinsics.areEqual((Object)template.name, (Object)InventoryTemplates.activeName());
            InventoryTemplates.setActive(active ? null : template.name);
            this.selectedName = template.name;
            Sounds.play("select_category");
            return true;
        }
        if (!InventoryManagerScreen.Companion.hover(mx, my, this.px, this.py, 350.0f, 252.0f)) {
            this.close();
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    public boolean mouseReleased(@NotNull Click event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        this.nameField.mouseReleased(event.button());
        this.scrollBar.release();
        return super.mouseReleased(event);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double horizontal, double vertical) {
        if (!this.anim.canInteract()) {
            return true;
        }
        this.layout();
        if (InventoryManagerScreen.Companion.hover(Position.Companion.mouseX(), Position.Companion.mouseY(), this.listX(), this.listY(), this.listW() + 8.0f, this.listH())) {
            this.scrollTarget = Math.max(0.0f, Math.min(this.scrollTarget - (float)vertical * 14.0f, this.maxScroll()));
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontal, vertical);
    }

    public boolean keyPressed(@NotNull KeyInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.nameField.isTyping() && this.nameField.keyPressed(event)) {
            return true;
        }
        if (event.key() == 256) {
            this.close();
            return true;
        }
        return super.keyPressed(event);
    }

    public boolean charTyped(@NotNull CharInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.nameField.isTyping() && this.nameField.charTyped(event)) {
            return true;
        }
        return super.charTyped(event);
    }

    public void close() {
        if (!this.anim.isClosing()) {
            this.nameField.blur();
            this.layout();
            this.anim.setPanelRect(this.px, this.py, 350.0f, 252.0f);
            this.anim.close();
            Sounds.play("gui_close");
            BaseScreen.Companion.beginClosingOverlay(this);
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (minecraft.currentScreen == this) {
                minecraft.setScreen(null);
            }
        }
    }

    private final float delX() {
        return this.leftX + this.leftW - 4.0f - 10.0f;
    }

    private final float starX() {
        return this.delX() - 10.0f - 2.0f;
    }

    private final float backX() {
        return this.px + 350.0f - 12.0f - 58.0f;
    }

    private final float backY() {
        return this.py + 7.5f;
    }

    private final float step(HashMap<String, Float> map, String key, float target, float dt) {
        Float existing;
        Float f = existing = map.get(key);
        float next = f == null ? 0.0f : InventoryManagerScreen.Companion.approach(f.floatValue(), target, dt);
        ((Map)map).put(key, Float.valueOf(next));
        return next;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b-\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u00142\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J+\u0010\u001a\u001a\u00020\u000b2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u00142\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ/\u0010!\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b!\u0010\"J'\u0010&\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b&\u0010'J/\u0010&\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b&\u0010)J?\u0010.\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b.\u0010/J/\u00104\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b4\u00105R\u0014\u00106\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00107R\u0014\u00109\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u00107R\u0014\u0010:\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00107R\u0014\u0010;\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u00107R\u0014\u0010<\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u00107R\u0014\u0010=\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u00107R\u0014\u0010>\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u00107R\u0014\u0010?\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u00107R\u0014\u0010@\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u00107R\u0014\u0010A\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u00107R\u0014\u0010B\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u00107R\u0014\u0010C\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u00107R\u0014\u0010D\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u00107R\u0014\u0010E\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u00107R\u0014\u0010F\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u00107R\u0014\u0010G\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u00107R\u0014\u0010H\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010IR\u0014\u0010K\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010IR\u0014\u0010L\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010I\u00a8\u0006M"}, d2={"Lrtx/kimiko/api/invmanager/InventoryManagerScreen.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/invmanager/InventoryManagerScreen$RowAnim;", "row", "", "rowSlide", "(Lrtx/kimiko/api/invmanager/InventoryManagerScreen$RowAnim;)F", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "template", "", "slot", "", "isEmpty", "(Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;I)Z", "count", "", "itemsWord", "(I)Ljava/lang/String;", "", "missingCounts", "(Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;)Ljava/util/Map;", "missing", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "entry", "takeMissing", "(Ljava/util/Map;Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;)I", "Lnet/minecraft/DrawContext;", "graphics", "x", "y", "", "drawMissingCount", "(Lnet/minecraft/DrawContext;III)V", "current", "target", "dt", "approach", "(FFF)F", "speed", "(FFFF)F", "mx", "my", "w", "h", "hover", "(FFFFFF)Z", "r", "g", "b", "a", "col", "(IIIF)I", "PANEL_W", "F", "PANEL_H", "PANEL_R", "PAD", "HEADER_H", "FOOTER_H", "LEFT_W", "ROW_H", "ROW_PITCH", "ROWS_Y", "SAVE_H", "DEL_SIZE", "CELL", "PITCH", "GRID_W", "SECTION_CELLS_Y", "SECTION_GAP", "ICON_PLUS", "Ljava/lang/String;", "ICON_CUBE", "ICON_MOUSE", "ICON_SWORD", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nInventoryManagerScreen.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InventoryManagerScreen.kt\nrtx/kimiko/api/invmanager/InventoryManagerScreen.Companion\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,789:1\n221#2,2:790\n*S KotlinDebug\n*F\n+ 1 InventoryManagerScreen.kt\nrtx/kimiko/api/invmanager/InventoryManagerScreen.Companion\n*L\n749#1:790,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        private final float rowSlide(RowAnim row) {
            float ease = row.getAppear() * row.getAppear() * (3.0f - 2.0f * row.getAppear());
            return (1.0f - ease) * 16.0f;
        }

        private final boolean isEmpty(InventoryTemplates.Template template, int slot) {
            InventoryTemplates.Entry entry = template.slots.get(slot);
            return entry == null || InventoryTemplates.stackFor(entry).isEmpty();
        }

        private final String itemsWord(int count) {
            boolean slavic;
            String code = ClientLanguage.code();
            boolean bl = slavic = Intrinsics.areEqual((Object)code, (Object)"ru") || Intrinsics.areEqual((Object)code, (Object)"uk") || Intrinsics.areEqual((Object)code, (Object)"pl");
            if (!slavic) {
                return Math.abs(count) == 1 ? I18n.tr("предмет") : I18n.tr("предметов");
            }
            int mod100 = Math.abs(count) % 100;
            int mod10 = mod100 % 10;
            boolean bl2 = 11 <= mod100 ? mod100 < 15 : false;
            if (bl2) {
                return I18n.tr("предметов");
            }
            if (mod10 == 1) {
                return I18n.tr("предмет");
            }
            boolean bl3 = 2 <= mod10 ? mod10 < 5 : false;
            if (bl3) {
                return I18n.tr("предмета");
            }
            return I18n.tr("предметов");
        }

        /*
         * WARNING - void declaration
         */
        private final Map<String, Integer> missingCounts(InventoryTemplates.Template template) {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            HashMap<String, Integer> result = new HashMap<>();
            ClientPlayerEntity clientPlayerEntity2 = mc.player;
            if (clientPlayerEntity2 == null) {
                return result;
            }
            ClientPlayerEntity player = clientPlayerEntity2;
            PlayerInventory playerInventory2 = player.getInventory();
            Intrinsics.checkNotNullExpressionValue((Object)playerInventory2, (String)"getInventory(...)");
            Map<String, InventoryTemplates.MissingItem> map = InventoryTemplates.missingFor(template, playerInventory2);
            Iterator<Map.Entry<String, InventoryTemplates.MissingItem>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry element$iv;
                Map.Entry entry = element$iv = iterator.next();
                boolean bl = false;
                String key = (String)entry.getKey();
                InventoryTemplates.MissingItem item = (InventoryTemplates.MissingItem)entry.getValue();
                ((Map)result).put(key, item.count);
            }
            return result;
        }

        private final int takeMissing(Map<String, Integer> missing, InventoryTemplates.Entry entry) {
            String key = InventoryTemplates.entryLayoutKey(entry);
            int count = ((Number)missing.getOrDefault(key, 0)).intValue();
            int used = Math.min(count, entry.count());
            if (used > 0) {
                missing.put(key, count - used);
            }
            return used;
        }

        private final void drawMissingCount(DrawContext graphics, int count, int x, int y) {
            if (count <= 1) {
                return;
            }
            String text = String.valueOf(count);
            TextRenderer textRenderer2 = MinecraftClient.getInstance().textRenderer;
            Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
            TextRenderer font = textRenderer2;
            graphics.drawText(font, text, x + 17 - font.getWidth(text), y + 9, -4671304, true);
        }

        private final float approach(float current, float target, float dt) {
            return this.approach(current, target, dt, 16.0f);
        }

        private final float approach(float current, float target, float dt, float speed) {
            return current + (target - current) * (1.0f - (float)Math.exp(-dt * speed));
        }

        private final boolean hover(float mx, float my, float x, float y, float w, float h) {
            return mx >= x && mx <= x + w && my >= y && my <= y + h;
        }

        private final int col(int r, int g, int b, float a) {
            int alpha = Math.max(0, Math.min(255, MathKt.roundToInt((float)a)));
            if (alpha <= 0) {
                return 0;
            }
            return new Color(r, g, b, alpha).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\"\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/invmanager/InventoryManagerScreen$RowAnim;", "", "<init>", "()V", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "template", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "getTemplate", "()Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "setTemplate", "(Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;)V", "", "y", "F", "getY", "()F", "setY", "(F)V", "targetY", "getTargetY", "setTargetY", "appear", "getAppear", "setAppear", "", "dying", "Z", "getDying", "()Z", "setDying", "(Z)V", "rtx.kimiko:kimiko"})
    private static final class RowAnim {
        @Nullable
        private InventoryTemplates.Template template;
        private float y;
        private float targetY;
        private float appear;
        private boolean dying;

        @Nullable
        public final InventoryTemplates.Template getTemplate() {
            return this.template;
        }

        public final void setTemplate(@Nullable InventoryTemplates.Template template) {
            this.template = template;
        }

        public final float getY() {
            return this.y;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public final float getTargetY() {
            return this.targetY;
        }

        public final void setTargetY(float f) {
            this.targetY = f;
        }

        public final float getAppear() {
            return this.appear;
        }

        public final void setAppear(float f) {
            this.appear = f;
        }

        public final boolean getDying() {
            return this.dying;
        }

        public final void setDying(boolean bl) {
            this.dying = bl;
        }
    }
}

