/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.client.input.CharInput
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.client.gui.Click
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.skyshader;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.gui.Click;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.api.ui.BaseScreen;
import rtx.kimiko.api.ui.ScrollBar;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.skyshader.CodeArea;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.window.PanelAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyManager;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyPreview;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyShaders;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.SoundManager;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\t\u0018\u0000 }2\u00020\u0001:\u0001}B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\n\u0010\bJ/\u0010\u0012\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013JO\u0010\u001c\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ/\u0010\u001e\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ?\u0010#\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b#\u0010$J/\u0010(\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b(\u0010)J'\u0010*\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b,\u0010\bJG\u00101\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\u0006\u00100\u001a\u00020/2\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b1\u00102J7\u00103\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b3\u00104J\u001f\u00108\u001a\u00020\u00182\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b8\u00109J'\u0010=\u001a\u00020\u00182\u0006\u00106\u001a\u0002052\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020:H\u0016\u00a2\u0006\u0004\b=\u0010>J\u0017\u0010?\u001a\u00020\u00182\u0006\u00106\u001a\u000205H\u0016\u00a2\u0006\u0004\b?\u0010@J/\u0010E\u001a\u00020\u00182\u0006\u0010A\u001a\u00020:2\u0006\u0010B\u001a\u00020:2\u0006\u0010C\u001a\u00020:2\u0006\u0010D\u001a\u00020:H\u0016\u00a2\u0006\u0004\bE\u0010FJ\u0017\u0010H\u001a\u00020\u00182\u0006\u00106\u001a\u00020GH\u0016\u00a2\u0006\u0004\bH\u0010IJ\u0017\u0010K\u001a\u00020\u00182\u0006\u00106\u001a\u00020JH\u0016\u00a2\u0006\u0004\bK\u0010LJ\u000f\u0010M\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bM\u0010\bJ\u0017\u0010O\u001a\u00020\u00062\u0006\u0010N\u001a\u00020/H\u0002\u00a2\u0006\u0004\bO\u0010PJ\u0017\u0010R\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bT\u0010\bJ\u000f\u0010U\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bU\u0010\bJ\u0017\u0010V\u001a\u00020\u00062\u0006\u0010N\u001a\u00020/H\u0002\u00a2\u0006\u0004\bV\u0010PJ\u000f\u0010W\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bW\u0010XJ\u000f\u0010Y\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bY\u0010XR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010ZR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0014\u0010_\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u001c\u0010b\u001a\b\u0012\u0004\u0012\u00020/0a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0016\u0010d\u001a\u00020/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0016\u0010h\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0016\u0010j\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010iR\u0014\u0010l\u001a\u00020k8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0016\u0010n\u001a\u00020/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010eR\u0016\u0010o\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010gR\u0016\u0010p\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010iR\u0016\u0010q\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010iR\u0016\u0010r\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010iR\u0016\u0010s\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010iR\u0016\u0010t\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010iR\u0016\u0010v\u001a\u00020u8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010eR\u0016\u0010y\u001a\u00020u8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010wR\u0016\u0010z\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010gR\u0016\u0010{\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010iR\u0016\u0010|\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010i\u00a8\u0006~"}, d2={"Lrtx/kimiko/api/ui/skyshader/SkyShaderEditorScreen;", "Lrtx/kimiko/api/ui/BaseScreen;", "Lnet/minecraft/Screen;", "parent", "<init>", "(Lnet/minecraft/Screen;)V", "", "onClosingOverlayDropped", "()V", "init", "onClose", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX0", "mouseY0", "", "partialTick", "renderScreen", "(Lnet/minecraft/DrawContext;IIF)V", "x", "y", "mx", "my", "", "interact", "alpha", "dt", "drawList", "(Lnet/minecraft/DrawContext;FFFFZFF)V", "drawCode", "(Lnet/minecraft/DrawContext;FFF)V", "rightX", "saveY", "applyY", "drawRight", "(Lnet/minecraft/DrawContext;FFFFF)V", "bx", "by", "hover", "drawDocsButton", "(FFFF)V", "drawDocsTooltip", "(FFF)V", "copyDocumentation", "bw", "bh", "", "text", "drawButton", "(FFFFLjava/lang/String;FF)V", "drawBackLink", "(Lnet/minecraft/DrawContext;FFFF)V", "Lnet/minecraft/Click;", "event", "doubleClick", "mouseClicked", "(Lnet/minecraft/Click;Z)Z", "", "dragX", "dragY", "mouseDragged", "(Lnet/minecraft/Click;DD)Z", "mouseReleased", "(Lnet/minecraft/Click;)Z", "mouseX", "mouseY", "scrollX", "scrollY", "mouseScrolled", "(DDDD)Z", "Lnet/minecraft/KeyInput;", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "Lnet/minecraft/CharInput;", "charTyped", "(Lnet/minecraft/CharInput;)Z", "refreshList", "name", "load", "(Ljava/lang/String;)V", "flash", "saveCurrent", "(Z)V", "applyToSky", "createShader", "deleteShader", "winX", "()F", "winY", "Lnet/minecraft/Screen;", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "anim", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "Lrtx/kimiko/api/ui/skyshader/CodeArea;", "code", "Lrtx/kimiko/api/ui/skyshader/CodeArea;", "", "shaders", "Ljava/util/List;", "current", "Ljava/lang/String;", "dirty", "Z", "listScroll", "F", "listScrollTarget", "Lrtx/kimiko/api/ui/ScrollBar;", "listBar", "Lrtx/kimiko/api/ui/ScrollBar;", "newName", "nameFocused", "hoverDocs", "hoverBack", "hoverCreate", "hoverSave", "hoverApply", "", "lastNs", "J", "savedFlashUntil", "savedFlashMs", "previewDrag", "previewLastX", "previewLastY", "Companion", "rtx.kimiko:kimiko"})
public final class SkyShaderEditorScreen
extends BaseScreen {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final Screen parent;
    @NotNull
    private final PanelAnimation anim;
    @NotNull
    private final CodeArea code;
    @NotNull
    private List<String> shaders;
    @NotNull
    private String current;
    private boolean dirty;
    private float listScroll;
    private float listScrollTarget;
    @NotNull
    private final ScrollBar listBar;
    @NotNull
    private String newName;
    private boolean nameFocused;
    private float hoverDocs;
    private float hoverBack;
    private float hoverCreate;
    private float hoverSave;
    private float hoverApply;
    private long lastNs;
    @NotNull
    private String savedFlashUntil;
    private long savedFlashMs;
    private boolean previewDrag;
    private float previewLastX;
    private float previewLastY;
    private static final float PAD = 10.0f;
    private static final float GAP = 8.0f;
    private static final float LIST_W = 88.0f;
    private static final float CODE_W = 182.0f;
    private static final float RIGHT_W = 78.0f;
    private static final float WIN_W = 384.0f;
    private static final float WIN_H = 238.0f;
    private static final float PANEL_TOP = 30.0f;
    private static final float PANEL_H = 198.0f;
    private static final float ROW_H = 12.5f;
    private static final float ROW_RADIUS = 3.0f;
    private static final float LIST_INSET = 2.0f;
    private static final float CREATE_H = 34.0f;
    private static final float PREVIEW_H = 43.875f;
    private static final float BTN_H = 14.0f;
    private static final float DOCS_SIZE = 14.0f;
    @NotNull
    private static final String DOCS_ICON = "k";

    public SkyShaderEditorScreen(@Nullable Screen parent) {
        super((Text)Text.literal("Sky"));
        this.parent = parent;
        this.anim = new PanelAnimation(true, false, 2, null);
        this.code = new CodeArea();
        this.shaders = new ArrayList();
        this.current = "";
        this.listBar = new ScrollBar();
        this.newName = "";
        this.lastNs = System.nanoTime();
        this.savedFlashUntil = "";
        this.code.setOnChange(() -> SkyShaderEditorScreen._init_$lambda$0(this));
    }

    @Override
    protected void onClosingOverlayDropped() {
        this.anim.finish();
    }

    protected void init() {
        UserSkyShaders.ensureFolder();
        this.refreshList();
        if (((CharSequence)this.current).length() == 0 && !((Collection)this.shaders).isEmpty()) {
            this.load(this.shaders.get(0));
        } else if (this.shaders.isEmpty()) {
            String name = UserSkyShaders.uniqueName("example");
            UserSkyShaders.save(name, UserSkyShaders.template());
            this.refreshList();
            this.load(name);
        }
        this.anim.setPanelRect(this.winX(), this.winY(), 384.0f, 238.0f);
        this.anim.open();
        Sounds.play("module_settings_open");
    }

    public void close() {
        if (!this.anim.isClosing()) {
            this.saveCurrent(false);
            this.anim.setPanelRect(this.winX(), this.winY(), 384.0f, 238.0f);
            this.anim.close();
            Sounds.play("module_settings_close");
            BaseScreen.Companion.beginClosingOverlay(this);
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (Intrinsics.areEqual((Object)minecraft.currentScreen, (Object)((Object)this))) {
                minecraft.setScreen(null);
            }
        }
    }

    @Override
    protected void renderScreen(@NotNull DrawContext graphics, int mouseX0, int mouseY0, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.anim.updateFrame();
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (this.anim.isCloseFinished()) {
            this.anim.finish();
            BaseScreen.Companion.cancelClosingOverlay(this);
            if (Intrinsics.areEqual((Object)mc.currentScreen, (Object)((Object)this)) || mc.currentScreen == null) {
                mc.setScreen(this.parent);
            }
            return;
        }
        UserSkyPreview.requestFrame();
        boolean captured = this.anim.captureActive();
        float alpha = this.anim.contentAlpha();
        float scale = captured ? 1.0f : this.anim.motion().scale();
        float x = this.winX();
        float y = this.winY();
        float cx = x + 192.0f;
        float cy = y + 119.0f;
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        boolean interact = this.anim.canInteract();
        float backW = SkyShaderEditorScreen.Companion.backWidth();
        float backX = x + 384.0f - 10.0f - backW;
        float backY = y - 13.0f;
        float rightX = x + 10.0f + 88.0f + 8.0f + 182.0f + 8.0f;
        float saveY = y + 30.0f + 43.875f + 8.0f;
        float applyY = saveY + 14.0f + 6.0f;
        float docsX = SkyShaderEditorScreen.Companion.docsX(x);
        float docsY = SkyShaderEditorScreen.Companion.docsY(y);
        this.hoverBack = SkyShaderEditorScreen.Companion.approach(this.hoverBack, interact && SkyShaderEditorScreen.Companion.hit(mx, my, backX, backY, backW, 10.0f) ? 1.0f : 0.0f, dt);
        this.hoverDocs = SkyShaderEditorScreen.Companion.approach(this.hoverDocs, interact && SkyShaderEditorScreen.Companion.hit(mx, my, docsX, docsY, 14.0f, 14.0f) ? 1.0f : 0.0f, dt);
        this.hoverSave = SkyShaderEditorScreen.Companion.approach(this.hoverSave, interact && SkyShaderEditorScreen.Companion.hit(mx, my, rightX, saveY, 78.0f, 14.0f) ? 1.0f : 0.0f, dt);
        this.hoverApply = SkyShaderEditorScreen.Companion.approach(this.hoverApply, interact && SkyShaderEditorScreen.Companion.hit(mx, my, rightX, applyY, 78.0f, 14.0f) ? 1.0f : 0.0f, dt);
        float createY = y + 30.0f + 198.0f - 34.0f + 17.5f;
        this.hoverCreate = SkyShaderEditorScreen.Companion.approach(this.hoverCreate, interact && SkyShaderEditorScreen.Companion.hit(mx, my, x + 10.0f, createY, 88.0f, 14.0f) ? 1.0f : 0.0f, dt);
        Render2D.rect(-5.0f, -5.0f, mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight(), 0.0f, SkyShaderEditorScreen.Companion.color(0, 0, 0, 50, this.anim.dimAlpha()));
        this.anim.beginCaptureStratum(graphics);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(cx, cy);
        graphics.getMatrices().scale(scale, scale);
        graphics.getMatrices().translate(-cx, -cy);
        RectUtil.drawClientRect(x, y, 384.0f, 238.0f, 12.0f, alpha, 6.0f);
        Fonts.SEMIBOLD.draw(I18n.tr("Шейдеры неба"), x + 10.0f, y + 10.0f, 8.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 240, alpha));
        Fonts.MEDIUM.draw("GLSL", x + 10.0f + Fonts.SEMIBOLD.width(I18n.tr("Шейдеры неба"), 8.0f) + 6.0f, y + 12.5f, 5.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 90, alpha));
        this.drawList(graphics, x, y, mx, my, interact, alpha, dt);
        this.drawCode(graphics, x, y, alpha);
        this.drawRight(graphics, rightX, y, saveY, applyY, alpha);
        this.drawBackLink(graphics, backX, backY, this.hoverBack, alpha * (1.0f - Math.max(0.0f, Math.min(1.0f, this.hoverDocs))));
        this.drawDocsButton(docsX, docsY, this.hoverDocs, alpha);
        this.drawDocsTooltip(docsX, docsY, alpha);
        graphics.getMatrices().popMatrix();
    }

    private final void drawList(DrawContext graphics, float x, float y, float mx, float my, boolean interact, float alpha, float dt) {
        float lx = x + 10.0f;
        float ly = y + 30.0f;
        float listH = 158.0f;
        Fonts.MEDIUM.draw(I18n.tr("Сохранённые"), lx, ly - 7.5f, 5.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 130, alpha));
        RenderHelper.drawPanelBg(lx, ly, 88.0f, listH, 8.0f, alpha);
        float contentH = (float)this.shaders.size() * 12.5f + 4.0f;
        float maxScroll = Math.max(0.0f, contentH - listH);
        this.listScrollTarget = Math.max(0.0f, Math.min(this.listScrollTarget, maxScroll));
        this.listScroll += (this.listScrollTarget - this.listScroll) * (1.0f - (float)Math.exp(-dt * 14.0f));
        if (Math.abs(this.listScrollTarget - this.listScroll) < 0.05f) {
            this.listScroll = this.listScrollTarget;
        }
        this.listScroll = Math.max(0.0f, Math.min(this.listScroll, maxScroll));
        float rowH = 11.5f;
        float panelR = RenderHelper.effectiveCornerRadius(8.0f, 88.0f, listH);
        float edgeR = Math.min(Math.max(3.0f, panelR - 2.0f), rowH * 0.5f);
        Render2D.pushScissor(graphics, lx, ly, 88.0f, listH);
        RoundedScissor.push(graphics, lx, ly, 88.0f, listH, panelR, panelR, panelR, panelR);
        int n = ((Collection)this.shaders).size();
        for (int i = 0; i < n; ++i) {
            float dx;
            float bottomR;
            String name = this.shaders.get(i);
            float rowY = ly + 2.0f + (float)i * 12.5f - this.listScroll;
            if (rowY + 12.5f < ly || rowY > ly + listH) continue;
            boolean selected = Intrinsics.areEqual((Object)name, (Object)this.current);
            boolean hovered = interact && SkyShaderEditorScreen.Companion.hit(mx, my, lx + 2.0f, rowY, 84.0f, 12.5f);
            float topR = rowY - (ly + 2.0f) < 1.5f ? edgeR : 3.0f;
            float f = bottomR = ly + listH - 2.0f - (rowY + rowH) < 1.5f ? edgeR : 3.0f;
            if (selected) {
                AccentGradient.fillHorizontal(lx + 2.0f, rowY, 84.0f, rowH, topR, topR, bottomR, bottomR, 130.0f * alpha);
            } else if (hovered) {
                Render2D.rect(lx + 2.0f, rowY, 84.0f, rowH, topR, topR, bottomR, bottomR, SkyShaderEditorScreen.Companion.color(255, 255, 255, 18, alpha));
            }
            String label = (selected && this.dirty) ? name + " \u2022" : name;
            float maxW = 68.0f;
            String draw = label;
            while (draw.length() > 1 && Fonts.MEDIUM.width(draw, 5.0f) > maxW) {
                draw = draw.substring(0, draw.length() - 1);
            }
            int textAlpha = selected ? 245 : 170;
            Fonts.MEDIUM.draw(draw, lx + 6.0f, rowY + 3.25f - 0.5f, 5.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, textAlpha, alpha));
            if (!hovered) continue;
            boolean delHover = SkyShaderEditorScreen.Companion.hit(mx, my, (dx = lx + 88.0f - 14.0f) - 2.0f, rowY, 11.0f, 12.5f);
            Fonts.MEDIUM.draw("\u00d7", dx, rowY + 2.75f - 0.25f, 6.0f, delHover ? SkyShaderEditorScreen.Companion.color(255, 120, 120, 240, alpha) : SkyShaderEditorScreen.Companion.color(255, 255, 255, 110, alpha));
        }
        RoundedScissor.pop();
        Render2D.popScissor(graphics);
        float barScroll = this.listBar.render(lx + 88.0f - 3.5f, ly + 2.0f, listH - 4.0f, listH, contentH, this.listScroll, alpha);
        if (this.listBar.isDragging()) {
            this.listScroll = barScroll;
            this.listScrollTarget = barScroll;
        }
        float createLabelY = ly + listH + 5.0f;
        Fonts.MEDIUM.draw(I18n.tr("Новый шейдер"), lx, createLabelY, 5.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 130, alpha));
        float fieldY = createLabelY + 7.5f;
        float fieldH = 11.0f;
        RenderHelper.drawPanelBg(lx, fieldY, 88.0f, fieldH, 4.0f, alpha);
        if (this.nameFocused) {
            Render2D.outline(lx, fieldY, 88.0f, fieldH, 4.0f, 0.6f, ClientAccent.accentBrightAt(190.0f * alpha, lx + 44.0f, fieldY + fieldH * 0.5f));
        }
        String shown = ((CharSequence)this.newName).length() == 0 && !this.nameFocused ? I18n.tr("название...") : this.newName;
        int shownColor = ((CharSequence)this.newName).length() == 0 && !this.nameFocused ? SkyShaderEditorScreen.Companion.color(255, 255, 255, 90, alpha) : SkyShaderEditorScreen.Companion.color(255, 255, 255, 220, alpha);
        String drawName = shown;
        while (drawName.length() > 1 && Fonts.MEDIUM.width(drawName, 5.0f) > 78.0f) {
            Intrinsics.checkNotNullExpressionValue((Object)drawName.substring(1), (String)"substring(...)");
        }
        Fonts.MEDIUM.draw(drawName, lx + 4.0f, fieldY + (fieldH - 6.0f) * 0.5f, 5.0f, shownColor);
        if (this.nameFocused && System.currentTimeMillis() / 500L % 2L == 0L) {
            float caretX = lx + 4.0f + Fonts.MEDIUM.width(drawName, 5.0f) + 0.5f;
            Render2D.rect(caretX, fieldY + 2.0f, 0.7f, fieldH - 4.0f, 0.0f, ClientAccent.accentBrightAt(220.0f * alpha, caretX, fieldY));
        }
        float btnY = fieldY + fieldH + 4.0f;
        this.drawButton(lx, btnY, 88.0f, 14.0f, I18n.tr("Создать"), this.hoverCreate, alpha);
    }

    private final void drawCode(DrawContext graphics, float x, float y, float alpha) {
        float cx = x + 10.0f + 88.0f + 8.0f;
        float cy = y + 30.0f;
        Object title = ((CharSequence)this.current).length() == 0 ? I18n.tr("Редактор") : this.current + ".glsl";
        Fonts.MEDIUM.draw((String)title, cx, cy - 7.5f, 5.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 130, alpha));
        RenderHelper.drawPanelBg(cx, cy, 182.0f, 198.0f, 8.0f, alpha);
        Render2D.rect(cx, cy, 182.0f, 198.0f, 8.0f, SkyShaderEditorScreen.Companion.color(0, 0, 0, 70, alpha));
        if (alpha > 0.4f) {
            this.code.render(graphics, cx + 2.0f, cy + 2.0f, 178.0f, 194.0f, alpha);
        }
    }

    private final void drawRight(DrawContext graphics, float rightX, float y, float saveY, float applyY, float alpha) {
        float py = y + 30.0f;
        Fonts.MEDIUM.draw(I18n.tr("Превью"), rightX, py - 7.5f, 5.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 130, alpha));
        String orbitHint = this.previewDrag ? I18n.tr("ПКМ — сброс") : I18n.tr("ЛКМ — вращать");
        float hintW = Fonts.MEDIUM.width(orbitHint, 4.2f);
        Fonts.MEDIUM.draw(orbitHint, rightX + 78.0f - hintW, py - 7.0f, 4.2f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 75, alpha));
        RenderHelper.drawPanelBg(rightX, py, 78.0f, 43.875f, 6.0f, alpha);
        String error = UserSkyPreview.error();
        if (error == null && UserSkyPreview.hasFrame() && Render2D.imageReady("kimiko:usersky_preview")) {
            Render2D.imageUv("kimiko:usersky_preview", rightX + 1.0f, py + 1.0f, 76.0f, 41.875f, 5.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 255, alpha));
        }
        this.drawButton(rightX, saveY, 78.0f, 14.0f, (String)(this.dirty ? I18n.tr("Сохранить") + " \u2022" : I18n.tr("Сохранить")), this.hoverSave, alpha);
        this.drawButton(rightX, applyY, 78.0f, 14.0f, I18n.tr("На небо"), this.hoverApply, alpha);
        long sinceFlash = System.currentTimeMillis() - this.savedFlashMs;
        if (((CharSequence)this.savedFlashUntil).length() > 0 && sinceFlash < 1500L) {
            float fade = sinceFlash > 1100L ? 1.0f - (float)(sinceFlash - (long)1100) / 400.0f : 1.0f;
            Fonts.MEDIUM.draw(this.savedFlashUntil, rightX, applyY + 14.0f + 5.0f, 4.5f, SkyShaderEditorScreen.Companion.color(140, 255, 170, MathKt.roundToInt((float)(210.0f * fade)), alpha));
        }
        float statusY = applyY + 14.0f + 14.0f;
        if (error != null) {
            Fonts.MEDIUM.draw(I18n.tr("Ошибка:"), rightX, statusY, 5.0f, SkyShaderEditorScreen.Companion.color(255, 120, 120, 230, alpha));
            float lineY = statusY + 7.0f;
            for (String line : SkyShaderEditorScreen.Companion.wrap(error, 78.0f, 4.2f, 14)) {
                Fonts.MEDIUM.draw(line, rightX, lineY, 4.2f, SkyShaderEditorScreen.Companion.color(255, 150, 150, 200, alpha));
                lineY += 5.2f;
            }
        } else {
            Fonts.MEDIUM.draw(I18n.tr("Компиляция: OK"), rightX, statusY, 4.5f, SkyShaderEditorScreen.Companion.color(140, 255, 170, 190, alpha));
            float hintY = statusY + 9.0f;
            String[] stringArray = new String[]{"mainImage(color, coord)", I18n.tr("fragCoord — экран (Shadertoy)"), "#define KIMIKO_PANORAMA", I18n.tr("— натянуть на небо"), "skyDirection(), skyPanoramaUv()", "kimikoColor(), kimikoColor2()"};
            for (String hint : stringArray) {
                Fonts.MEDIUM.draw(hint, rightX, hintY, 4.2f, SkyShaderEditorScreen.Companion.color(255, 255, 255, 90, alpha));
                hintY += 5.4f;
            }
        }
    }

    private final void drawDocsButton(float bx, float by, float hover, float alpha) {
        Render2D.rect(bx, by, 14.0f, 14.0f, 3.5f, SkyShaderEditorScreen.Companion.color(255, 255, 255, MathKt.roundToInt((float)(20.0f + 40.0f * hover)), alpha));
        float size = 6.5f;
        float gw = Fonts.KIMIKO.msdfWidth(DOCS_ICON, size);
        Fonts.KIMIKO.msdf(DOCS_ICON, bx + (14.0f - gw) * 0.5f, by + (14.0f - size) * 0.5f + 0.5f, size, SkyShaderEditorScreen.Companion.color(255, 255, 255, MathKt.roundToInt((float)(185.0f + 70.0f * hover)), alpha));
    }

    private final void drawDocsTooltip(float bx, float by, float alpha) {
        float appear = Math.max(0.0f, Math.min(1.0f, this.hoverDocs));
        if (appear <= 0.004f) {
            return;
        }
        float scale = 0.5f + 0.5f * SkyShaderEditorScreen.Companion.easeOutBack(appear);
        float a = alpha * appear;
        float centerX = bx + 7.0f;
        float anchorY = by - 2.0f;
        float size = 5.5f * scale;
        float tw = Fonts.MEDIUM.width(SkyShaderEditorScreen.Companion.getDOCS_TIP(), size);
        float bw = tw + 13.0f * scale;
        float bh = 14.0f * scale;
        float drop = 5.0f * scale;
        float tx = centerX - bw * 0.5f;
        float ty = anchorY - drop - bh;
        RectUtil.drawClientRectWithTail(tx, ty, bw, bh, 5.0f * scale, a, centerX, 6.0f * scale, drop, 1.5f * scale, 3.0f * scale);
        Fonts.MEDIUM.draw(SkyShaderEditorScreen.Companion.getDOCS_TIP(), tx + (bw - tw) * 0.5f, ty + (bh - size) * 0.5f, size, SkyShaderEditorScreen.Companion.color(255, 255, 255, 235, a));
    }

    private final void copyDocumentation() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.keyboard != null) {
            minecraft.keyboard.setClipboard(UserSkyShaders.documentation());
        }
        this.savedFlashUntil = I18n.tr("Документация в буфере");
        this.savedFlashMs = System.currentTimeMillis();
        SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
    }

    private final void drawButton(float bx, float by, float bw, float bh, String text, float hover, float alpha) {
        float radius = RenderHelper.effectiveCornerRadius(4.0f, bw, bh);
        RenderHelper.drawPanelBg(bx, by, bw, bh, 4.0f, alpha);
        if (hover > 0.01f) {
            Render2D.rect(bx, by, bw, bh, radius, ClientAccent.accentFillAt(60.0f * hover * alpha, bx + bw * 0.5f, by + bh * 0.5f));
        }
        int border = ColorEngine.lerpColor(SkyShaderEditorScreen.Companion.color(255, 255, 255, 30, alpha), ClientAccent.accentBrightAt(150.0f * alpha, bx + bw * 0.5f, by + bh * 0.5f), hover);
        Render2D.outline(bx, by, bw, bh, radius, 0.6f, border);
        int textColor = ColorEngine.lerpColor(ClientAccent.accentSoftAt(220.0f * alpha, bx + bw * 0.5f, by + bh * 0.5f), SkyShaderEditorScreen.Companion.color(255, 255, 255, 245, alpha), hover);
        float tw = Fonts.MEDIUM.width(text, 5.5f);
        Fonts.MEDIUM.draw(text, bx + (bw - tw) * 0.5f, by + (bh - 6.5f) * 0.5f, 5.5f, textColor);
    }

    private final void drawBackLink(DrawContext graphics, float bx, float by, float hover, float alpha) {
        int textColor = SkyShaderEditorScreen.Companion.color(255, 255, 255, 150 + MathKt.roundToInt((float)(105.0f * hover)), alpha);
        float iconW = Fonts.I2.msdfWidth("G", 6.0f);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(bx + iconW, by + 5.0f);
        graphics.getMatrices().scale(-1.0f, 1.0f);
        Fonts.I2.msdf("G", -2.0f, -3.0f, 6.0f, textColor);
        graphics.getMatrices().popMatrix();
        Fonts.MEDIUM.draw(I18n.tr("Назад"), bx + iconW + 5.0f, by + 1.7f, 6.0f, textColor);
    }

    public boolean mouseClicked(@NotNull Click event, boolean doubleClick) {
        boolean shift;
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.anim.canInteract()) {
            return true;
        }
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float x = this.winX();
        float y = this.winY();
        float backW = SkyShaderEditorScreen.Companion.backWidth();
        if (SkyShaderEditorScreen.Companion.hit(mx, my, x + 384.0f - 10.0f - backW, y - 13.0f, backW, 10.0f)) {
            this.close();
            return true;
        }
        if (SkyShaderEditorScreen.Companion.hit(mx, my, SkyShaderEditorScreen.Companion.docsX(x), SkyShaderEditorScreen.Companion.docsY(y), 14.0f, 14.0f)) {
            this.copyDocumentation();
            return true;
        }
        float lx = x + 10.0f;
        float ly = y + 30.0f;
        float listH = 158.0f;
        if (this.listBar.tryGrab(mx, my)) {
            this.nameFocused = false;
            this.code.setFocused(false);
            return true;
        }
        if (SkyShaderEditorScreen.Companion.hit(mx, my, lx, ly, 88.0f, listH)) {
            this.nameFocused = false;
            this.code.setFocused(false);
            int index = (int)Math.floor((my - ly - 2.0f + this.listScroll) / 12.5f);
            boolean bl = 0 <= index ? index < ((Collection)this.shaders).size() : false;
            if (bl) {
                String name = this.shaders.get(index);
                float dx = lx + 88.0f - 14.0f;
                if (SkyShaderEditorScreen.Companion.hit(mx, my, dx - 2.0f, ly + 2.0f + (float)index * 12.5f - this.listScroll, 11.0f, 12.5f)) {
                    this.deleteShader(name);
                } else if (!Intrinsics.areEqual((Object)name, (Object)this.current)) {
                    this.saveCurrent(false);
                    this.load(name);
                }
            }
            return true;
        }
        float createLabelY = ly + listH + 5.0f;
        float fieldY = createLabelY + 7.5f;
        if (SkyShaderEditorScreen.Companion.hit(mx, my, lx, fieldY, 88.0f, 11.0f)) {
            this.nameFocused = true;
            this.code.setFocused(false);
            return true;
        }
        float btnY = fieldY + 11.0f + 4.0f;
        if (SkyShaderEditorScreen.Companion.hit(mx, my, lx, btnY, 88.0f, 14.0f)) {
            this.createShader();
            return true;
        }
        float rightX = x + 10.0f + 88.0f + 8.0f + 182.0f + 8.0f;
        float saveY = y + 30.0f + 43.875f + 8.0f;
        float applyY = saveY + 14.0f + 6.0f;
        if (SkyShaderEditorScreen.Companion.hit(mx, my, rightX, y + 30.0f, 78.0f, 43.875f)) {
            this.nameFocused = false;
            if (event.button() == 1) {
                UserSkyPreview.resetView();
            } else {
                this.previewDrag = true;
                this.previewLastX = mx;
                this.previewLastY = my;
            }
            return true;
        }
        if (SkyShaderEditorScreen.Companion.hit(mx, my, rightX, saveY, 78.0f, 14.0f)) {
            this.saveCurrent(true);
            return true;
        }
        if (SkyShaderEditorScreen.Companion.hit(mx, my, rightX, applyY, 78.0f, 14.0f)) {
            this.applyToSky();
            return true;
        }
        this.nameFocused = false;
        boolean bl = shift = (event.modifiers() & 1) != 0;
        if (this.code.mouseClicked(mx, my, shift)) {
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    public boolean mouseDragged(@NotNull Click event, double dragX, double dragY) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        if (this.previewDrag) {
            UserSkyPreview.rotate((mx - this.previewLastX) * 0.014f, (my - this.previewLastY) * 0.014f);
            this.previewLastX = mx;
            this.previewLastY = my;
            return true;
        }
        this.code.mouseDragged(mx, my);
        return super.mouseDragged(event, dragX, dragY);
    }

    public boolean mouseReleased(@NotNull Click event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        this.previewDrag = false;
        this.listBar.release();
        this.code.mouseReleased();
        return super.mouseReleased(event);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        float listH;
        float y;
        float my;
        float mx = Position.Companion.mouseX();
        if (this.code.mouseScrolled(mx, my = Position.Companion.mouseY(), scrollY)) {
            return true;
        }
        float x = this.winX();
        if (SkyShaderEditorScreen.Companion.hit(mx, my, x + 10.0f, (y = this.winY()) + 30.0f, 88.0f, listH = 158.0f)) {
            this.listScrollTarget -= (float)scrollY * 12.5f * 2.0f;
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    public boolean keyPressed(@NotNull KeyInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.nameFocused) {
            int key = event.key();
            if (key == 259) {
                if (((CharSequence)this.newName).length() > 0) {
                    String string = this.newName.substring(0, this.newName.length() - 1);
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                    this.newName = string;
                }
                return true;
            }
            switch (key) {
                case 257: 
                case 335: {
                    this.createShader();
                    return true;
                }
            }
            if (key == 256) {
                this.nameFocused = false;
                return true;
            }
            return true;
        }
        if (this.code.isFocused()) {
            if (event.key() == 83 && ((event.modifiers() & 2) != 0 || (event.modifiers() & 8) != 0)) {
                this.saveCurrent(true);
                return true;
            }
            if (this.code.keyPressed(event.key(), event.modifiers())) {
                return true;
            }
            if (event.key() == 256) {
                return true;
            }
        }
        return super.keyPressed(event);
    }

    public boolean charTyped(@NotNull CharInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.nameFocused) {
            int codepoint = event.codepoint();
            if (!Character.isISOControl(codepoint) && this.newName.length() < 40) {
                char[] cArray = Character.toChars(codepoint);
                Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toChars(...)");
                char[] cArray2 = cArray;
                this.newName = this.newName + new String(cArray2);
            }
            return true;
        }
        if (this.code.isFocused() && this.code.charTyped(event.codepoint())) {
            return true;
        }
        return super.charTyped(event);
    }

    private final void refreshList() {
        this.shaders = UserSkyShaders.list();
    }

    private final void load(String name) {
        this.current = name;
        String content = UserSkyShaders.load(name);
        String string = content;
        if (string == null) {
            string = "";
        }
        this.code.setText(string);
        this.dirty = false;
        UserSkyPreview.compileNow(this.code.getText());
    }

    private final void saveCurrent(boolean flash) {
        if (((CharSequence)this.current).length() == 0) {
            return;
        }
        if (this.dirty || flash) {
            Ambience ambience;
            UserSkyShaders.save(this.current, this.code.getText());
            this.dirty = false;
            UserSkyPreview.compileNow(this.code.getText());
            UserSkyManager.invalidate();
            Ambience ambience2 = ambience = Ambience.Companion.getInstance();
            if (ambience2 != null) {
                ambience2.refreshUserShaderOptions();
            }
            if (flash) {
                this.savedFlashUntil = I18n.tr("Сохранено");
                this.savedFlashMs = System.currentTimeMillis();
                SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
            }
        }
    }

    private final void applyToSky() {
        Ambience ambience;
        this.saveCurrent(false);
        if (((CharSequence)this.current).length() == 0) {
            return;
        }
        Ambience ambience2 = ambience = Ambience.Companion.getInstance();
        if (ambience2 != null) {
            ambience2.applyUserShader(this.current);
        }
        this.savedFlashUntil = I18n.tr("Стоит на небе");
        this.savedFlashMs = System.currentTimeMillis();
        SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
    }

    private final void createShader() {
        Ambience ambience;
        String name = UserSkyShaders.uniqueName(((CharSequence)this.newName).length() == 0 ? "shader" : this.newName);
        this.saveCurrent(false);
        UserSkyShaders.save(name, UserSkyShaders.template());
        this.newName = "";
        this.nameFocused = false;
        this.refreshList();
        this.load(name);
        Ambience ambience2 = ambience = Ambience.Companion.getInstance();
        if (ambience2 != null) {
            ambience2.refreshUserShaderOptions();
        }
        SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
    }

    private final void deleteShader(String name) {
        Ambience ambience;
        UserSkyShaders.delete(name);
        this.refreshList();
        UserSkyManager.invalidate();
        Ambience ambience2 = ambience = Ambience.Companion.getInstance();
        if (ambience2 != null) {
            ambience2.refreshUserShaderOptions();
        }
        if (Intrinsics.areEqual((Object)name, (Object)this.current)) {
            this.current = "";
            this.dirty = false;
            if (!((Collection)this.shaders).isEmpty()) {
                this.load(this.shaders.get(0));
            } else {
                this.code.setText("");
                UserSkyPreview.reset();
            }
        }
    }

    private final float winX() {
        return Position.Companion.screenWidth() * 0.5f - 192.0f;
    }

    private final float winY() {
        return Position.Companion.screenHeight() * 0.5f - 119.0f;
    }

    private static final void _init_$lambda$0(SkyShaderEditorScreen this$0) {
        this$0.dirty = true;
        UserSkyPreview.setCode(this$0.code.getText());
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b \b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J5\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0010J'\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ?\u0010!\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b!\u0010\"J7\u0010(\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010+R\u0014\u0010/\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010+R\u0014\u00100\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010+R\u0014\u00101\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010+R\u0014\u00102\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010+R\u0014\u00103\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u0010+R\u0014\u00104\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010+R\u0014\u00105\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010+R\u0014\u00106\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010+R\u0014\u00107\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010+R\u0014\u00108\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u0010+R\u0014\u00109\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u0010+R\u0014\u0010:\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u0010+R\u0014\u0010;\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010?\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b=\u0010>\u00a8\u0006@"}, d2={"Lrtx/kimiko/api/ui/skyshader/SkyShaderEditorScreen.Companion;", "", "<init>", "()V", "", "text", "", "maxWidth", "size", "", "maxLines", "", "wrap", "(Ljava/lang/String;FFI)Ljava/util/List;", "x", "docsX", "(F)F", "y", "docsY", "backWidth", "()F", "t", "easeOutBack", "current", "target", "dt", "approach", "(FFF)F", "mx", "my", "w", "h", "", "hit", "(FFFFFF)Z", "r", "g", "b", "a", "mult", "color", "(IIIIF)I", "PAD", "F", "GAP", "LIST_W", "CODE_W", "RIGHT_W", "WIN_W", "WIN_H", "PANEL_TOP", "PANEL_H", "ROW_H", "ROW_RADIUS", "LIST_INSET", "CREATE_H", "PREVIEW_H", "BTN_H", "DOCS_SIZE", "DOCS_ICON", "Ljava/lang/String;", "getDOCS_TIP", "()Ljava/lang/String;", "DOCS_TIP", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String getDOCS_TIP() {
            return I18n.tr("Скопировать документацию для нейросети");
        }

        private final List<String> wrap(String text, float maxWidth, float size, int maxLines) {
            List result = new ArrayList();
            CharSequence charSequence = text;
            Regex regex = new Regex("\n");
            int n = 0;
            for (String raw : regex.split(charSequence, n)) {
                String line = ((Object)StringsKt.trim((CharSequence)raw)).toString();
                while (((CharSequence)line).length() > 0 && result.size() < maxLines) {
                    int cut;
                    for (cut = line.length(); cut > 1; --cut) {
                        String string = line.substring(0, cut);
                        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                        if (!(Fonts.MEDIUM.width(string, size) > maxWidth)) break;
                    }
                    String string = line.substring(0, cut);
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                    result.add(string);
                    String string2 = line.substring(cut);
                    Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                    line = ((Object)StringsKt.trim((CharSequence)string2)).toString();
                }
                if (result.size() < maxLines) continue;
                break;
            }
            return result;
        }

        private final float docsX(float x) {
            return x + 384.0f - 10.0f - 14.0f;
        }

        private final float docsY(float y) {
            return y + 7.0f;
        }

        private final float backWidth() {
            return Fonts.MEDIUM.width(I18n.tr("Назад"), 6.0f) + 16.0f;
        }

        private final float easeOutBack(float t) {
            float u = t - 1.0f;
            return 1.0f + 3.0f * u * u * u + 2.0f * u * u;
        }

        private final float approach(float current, float target, float dt) {
            return current + (target - current) * (1.0f - (float)Math.exp(-dt * 16.0f));
        }

        private final boolean hit(float mx, float my, float x, float y, float w, float h) {
            return mx >= x && mx <= x + w && my >= y && my <= y + h;
        }

        private final int color(int r, int g, int b, int a, float mult) {
            int fa = RangesKt.coerceIn((int)MathKt.roundToInt((float)((float)a * mult)), (int)0, (int)255);
            return fa <= 0 ? 0 : new Color(r, g, b, fa).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

