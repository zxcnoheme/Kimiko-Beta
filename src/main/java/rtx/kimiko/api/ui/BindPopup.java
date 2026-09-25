/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.DragController;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Utils.VoiceControl;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.voice.VoiceBindManager;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.key.KeyHelper;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.voice.VoiceTemplate;
import rtx.kimiko.utils.voice.VoiceTemplateStore;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 Y2\u00020\u0001:\u0001YB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\u0006J\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\f\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u001f\u0010\u0015\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f\u00a2\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u001a\u00a2\u0006\u0004\b!\u0010\u0003J\u001d\u0010%\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\f\u00a2\u0006\u0004\b%\u0010&J?\u0010.\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b0\u0010\u0006J\u000f\u00101\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b1\u00102J\r\u00103\u001a\u00020*\u00a2\u0006\u0004\b3\u00104J\r\u00105\u001a\u00020*\u00a2\u0006\u0004\b5\u00104J\u000f\u00106\u001a\u00020*H\u0002\u00a2\u0006\u0004\b6\u00104J\u001d\u00109\u001a\u00020\u00042\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\f\u00a2\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b;\u0010\u0003J\u0017\u0010=\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020*H\u0002\u00a2\u0006\u0004\b=\u0010>J\r\u0010?\u001a\u00020\u001a\u00a2\u0006\u0004\b?\u0010\u0003J\u0015\u0010@\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u0013\u00a2\u0006\u0004\b@\u0010AJ\u0015\u0010C\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u0013\u00a2\u0006\u0004\bC\u0010AR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010GR\u0016\u0010\u001b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010HR\u0016\u0010I\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010HR\u0016\u0010K\u001a\u00020J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010NR\u0016\u0010P\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010NR\u0016\u0010Q\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010NR\u0016\u0010S\u001a\u00020R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010HR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010X\u00a8\u0006Z"}, d2={"Lrtx/kimiko/api/ui/BindPopup;", "", "<init>", "()V", "", "isOpen", "()Z", "isVisible", "Lrtx/kimiko/api/modules/Module;", "module", "()Lrtx/kimiko/api/modules/Module;", "isListening", "", "x", "()F", "y", "blurPhase", "", "dst", "", "offset", "writeBlurRect", "([FI)Z", "mod", "cursorX", "cursorY", "", "open", "(Lrtx/kimiko/api/modules/Module;FF)V", "dx", "dy", "shift", "(FF)V", "close", "Lnet/minecraft/DrawContext;", "graphics", "alpha", "render", "(Lnet/minecraft/DrawContext;F)V", "bx", "by", "bw", "", "label", "sel", "a", "drawLabel", "(FFFLjava/lang/String;FF)V", "isRecordingThis", "takeCount", "()I", "voiceDisplay", "()Ljava/lang/String;", "voiceActionText", "keyDisplay", "mx", "my", "click", "(FF)Z", "ensureVoiceActive", "key", "startRecording", "(Ljava/lang/String;)V", "releaseDrag", "keyPressed", "(I)Z", "button", "mouseBind", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "Lrtx/kimiko/api/modules/Module;", "Z", "listening", "Lrtx/kimiko/api/modules/Module$BindMode;", "restoreMode", "Lrtx/kimiko/api/modules/Module$BindMode;", "px", "F", "py", "selT", "typeT", "", "lastFrameMs", "J", "dragging", "Lrtx/kimiko/api/drags/DragController;", "drag", "Lrtx/kimiko/api/drags/DragController;", "Companion", "rtx.kimiko:kimiko"})
public final class BindPopup {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Decelerate anim = new Decelerate();
    @Nullable
    private Module module;
    private boolean open;
    private boolean listening;
    @NotNull
    private Module.BindMode restoreMode = Module.BindMode.TOGGLE;
    private float px;
    private float py;
    private float selT;
    private float typeT;
    private long lastFrameMs = System.currentTimeMillis();
    private boolean dragging;
    @NotNull
    private final DragController drag = new DragController(0.0f, 0.0f);
    private static final float WIDTH = 108.0f;
    private static final float PAD = 6.0f;
    private static final float ROW_H = 13.0f;
    private static final float GAP = 5.0f;
    private static final float HEIGHT = 61.0f;

    public BindPopup() {
        this.anim.setMs(220);
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - (long)10000);
    }

    public final boolean isOpen() {
        return this.open;
    }

    public final boolean isVisible() {
        Double out = this.anim.getOutput();
        return this.module != null && out != null && out > 0.01;
    }

    @Nullable
    public final Module module() {
        return this.module;
    }

    public final boolean isListening() {
        return this.listening;
    }

    public final float x() {
        return this.px;
    }

    public final float y() {
        return this.py;
    }

    public final float blurPhase() {
        if (this.module == null) {
            return 0.0f;
        }
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            return 0.0f;
        }
        return RangesKt.coerceIn((float)(1.0f - t), (float)0.0f, (float)1.0f);
    }

    public final boolean writeBlurRect(@Nullable float[] dst, int offset) {
        if (this.module == null || dst == null || offset + 6 > dst.length) {
            return false;
        }
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            return false;
        }
        dst[offset] = this.px;
        dst[offset + 1] = this.py + (1.0f - t) * 4.0f;
        dst[offset + 2] = 108.0f;
        dst[offset + 3] = 61.0f;
        dst[offset + 4] = 1.0f;
        dst[offset + 5] = RangesKt.coerceIn((float)(1.0f - t), (float)0.0f, (float)1.0f);
        return true;
    }

    public final void open(@NotNull Module mod, float cursorX, float cursorY) {
        Intrinsics.checkNotNullParameter((Object)mod, (String)"mod");
        this.module = mod;
        this.open = true;
        this.listening = false;
        this.restoreMode = mod.getBindMode();
        this.px = cursorX;
        this.py = cursorY + 2.0f;
        this.dragging = false;
        this.drag.release();
        this.drag.setTargetX(this.px);
        this.drag.setTargetY(this.py);
        this.drag.syncToTarget();
        this.selT = mod.getBindMode() == Module.BindMode.HOLD ? 1.0f : 0.0f;
        this.typeT = mod.getBindType() == Module.BindType.VOICE ? 1.0f : 0.0f;
        this.anim.setDirection(Direction.FORWARDS);
        this.anim.counter.resetCounter();
    }

    public final void shift(float dx, float dy) {
        if (this.module == null || this.dragging) {
            return;
        }
        this.px += dx;
        this.py += dy;
        this.drag.setTargetX(this.drag.getTargetX() + dx);
        this.drag.setTargetY(this.drag.getTargetY() + dy);
        this.drag.syncToTarget();
    }

    public final void close() {
        if (!this.open) {
            return;
        }
        this.open = false;
        this.listening = false;
        Module mod = this.module;
        if (mod != null && Intrinsics.areEqual((Object)mod.getName(), (Object)VoiceBindManager.INSTANCE.recordingKey())) {
            VoiceBindManager.INSTANCE.cancelRecording();
        }
        if (mod != null && mod.getBindType() == Module.BindType.VOICE && !VoiceTemplateStore.has(mod.getName())) {
            mod.setBindType(Module.BindType.KEY);
            mod.setBindMode(this.restoreMode);
        }
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.resetCounter();
    }

    public final void render(@NotNull DrawContext graphics, float alpha) {
        float fill;
        boolean tilted;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Module module = this.module;
        if (module == null) {
            return;
        }
        Module mod = module;
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            if (!this.open) {
                this.module = null;
            }
            return;
        }
        float a = t * alpha;
        long now = System.currentTimeMillis();
        float dt = Math.min((float)(now - this.lastFrameMs) / 1000.0f, 0.1f);
        this.lastFrameMs = now;
        InterfaceModule iface = InterfaceModule.Companion.getInstance();
        boolean directDrag = iface != null && iface.dragStyle.is("Обычный");
        boolean tiltEnabled = directDrag && iface.dragTilt.getValue();
        float mouseX = Position.Companion.mouseX();
        if (this.dragging) {
            this.drag.tick(mouseX, Position.Companion.mouseY(), 108.0f, 61.0f, directDrag);
        }
        this.drag.updateTilt(mouseX, tiltEnabled);
        this.px = this.drag.getRenderX();
        this.py = this.drag.getRenderY();
        float drawY = this.py + (1.0f - t) * 4.0f;
        float tilt = this.drag.getTiltAngle();
        boolean bl = tilted = Math.abs(tilt) > 0.01f;
        if (tilted) {
            float centerX = this.px + 54.0f;
            float centerY = drawY + 30.5f;
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(centerX, centerY);
            graphics.getMatrices().rotate((float)Math.toRadians(tilt));
            graphics.getMatrices().translate(-centerX, -centerY);
        }
        boolean voice = mod.getBindType() == Module.BindType.VOICE;
        float lerp = Math.min(1.0f, dt * 14.0f);
        this.selT += ((mod.getBindMode() == Module.BindMode.HOLD ? 1.0f : 0.0f) - this.selT) * lerp;
        this.typeT += ((voice ? 1.0f : 0.0f) - this.typeT) * lerp;
        RenderHelper.drawDropBackground(this.px, drawY, 108.0f, 61.0f, a);
        float rowY = drawY + 6.0f;
        float row2Y = rowY + 13.0f + 5.0f;
        float row3Y = row2Y + 13.0f + 5.0f;
        float btnW = 45.5f;
        float leftX = this.px + 6.0f;
        float rightX = this.px + 6.0f + btnW + 5.0f;
        Fonts.MEDIUM.draw(voice ? I18n.tr("Фраза") : I18n.tr("Бинд"), leftX, rowY + 3.0f, 6.0f, ColorEngine.multAlpha(-1, 0.85f * a));
        String pillText = voice ? this.voiceDisplay() : (this.listening ? "..." : this.keyDisplay());
        float pillW = Math.max(40.0f, Fonts.MEDIUM.width(pillText, 6.0f) + 10.0f);
        float pillX = this.px + 108.0f - 6.0f - pillW;
        RenderHelper.drawPanelBg(pillX, rowY, pillW, 13.0f, 3.0f, a);
        if (voice && this.isRecordingThis() && (fill = Math.min(1.0f, VoiceBindManager.INSTANCE.level() * 9.0f)) > 0.02f) {
            Render2D.rect(pillX, rowY, pillW * fill, 13.0f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(150.0f, pillX + pillW * fill * 0.5f, rowY + 6.5f), a));
        }
        Fonts.MEDIUM.draw(pillText, pillX + (pillW - Fonts.MEDIUM.width(pillText, 6.0f)) * 0.5f, rowY + 3.0f, 6.0f, ColorEngine.multAlpha(-1, 0.9f * a));
        RenderHelper.drawPanelBg(leftX, row2Y, btnW, 13.0f, 3.0f, a);
        RenderHelper.drawPanelBg(rightX, row2Y, btnW, 13.0f, 3.0f, a);
        Render2D.rect(leftX + (rightX - leftX) * this.typeT, row2Y, btnW, 13.0f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(210.0f, leftX + (rightX - leftX) * this.typeT + btnW * 0.5f, row2Y + 6.5f), a));
        this.drawLabel(leftX, row2Y, btnW, I18n.tr("Кнопка"), 1.0f - this.typeT, a);
        this.drawLabel(rightX, row2Y, btnW, I18n.tr("Голос"), this.typeT, a);
        if (voice) {
            String action = this.voiceActionText();
            float fullW = 96.0f;
            RenderHelper.drawPanelBg(leftX, row3Y, fullW, 13.0f, 3.0f, a);
            float size = 6.0f;
            float tw = Fonts.MEDIUM.width(action, size);
            float limit = fullW - 6.0f;
            while (tw > limit && size > 4.4f) {
                tw = Fonts.MEDIUM.width(action, size -= 0.2f);
            }
            Fonts.MEDIUM.draw(action, leftX + (fullW - tw) * 0.5f, row3Y + (13.0f - size - 1.0f) * 0.5f + 0.5f, size, ColorEngine.multAlpha(-1, 0.7f * a));
        } else {
            RenderHelper.drawPanelBg(leftX, row3Y, btnW, 13.0f, 3.0f, a);
            RenderHelper.drawPanelBg(rightX, row3Y, btnW, 13.0f, 3.0f, a);
            Render2D.rect(leftX + (rightX - leftX) * this.selT, row3Y, btnW, 13.0f, 3.0f, ColorEngine.multAlpha(ClientAccent.accentSoftAt(210.0f, leftX + (rightX - leftX) * this.selT + btnW * 0.5f, row3Y + 6.5f), a));
            this.drawLabel(leftX, row3Y, btnW, "Toggle", 1.0f - this.selT, a);
            this.drawLabel(rightX, row3Y, btnW, "Hold", this.selT, a);
        }
        if (tilted) {
            graphics.getMatrices().popMatrix();
        }
        this.drag.renderOverlay(graphics, 108.0f, 61.0f, 108.0f, 61.0f);
    }

    private final void drawLabel(float bx, float by, float bw, String label, float sel, float a) {
        int base = ColorEngine.multAlpha(-1, 0.65f * a);
        int selected = ColorEngine.multAlpha(-15856114, a);
        int textColor = ColorEngine.lerpColor(base, selected, sel);
        float tw = Fonts.MEDIUM.width(label, 6.0f);
        Fonts.MEDIUM.draw(label, bx + (bw - tw) * 0.5f, by + 3.0f, 6.0f, textColor);
    }

    private final boolean isRecordingThis() {
        Module module = this.module;
        if (module == null) {
            return false;
        }
        Module mod = module;
        return Intrinsics.areEqual((Object)mod.getName(), (Object)VoiceBindManager.INSTANCE.recordingKey());
    }

    private final int takeCount() {
        VoiceTemplate template;
        Module module = this.module;
        if (module == null) {
            return 0;
        }
        Module mod = module;
        VoiceTemplate voiceTemplate = template = VoiceTemplateStore.get(mod.getName());
        return voiceTemplate != null ? voiceTemplate.takeCount() : 0;
    }

    @NotNull
    public final String voiceDisplay() {
        String string;
        if (this.isRecordingThis()) {
            Object[] objectArray = new Object[]{VoiceBindManager.INSTANCE.recordingRemaining()};
            return I18n.tr("Говорите %s", objectArray);
        }
        int takes = this.takeCount();
        if (takes == 0) {
            string = I18n.tr("Записать");
        } else {
            Object[] objectArray = new Object[]{takes};
            string = I18n.tr("Готово %d", objectArray);
        }
        return string;
    }

    @NotNull
    public final String voiceActionText() {
        if (!VoiceTemplateStore.has("__wake__")) {
            return I18n.tr("Ключевое слово");
        }
        if (this.takeCount() == 0) {
            return I18n.tr("Скажи фразу");
        }
        return I18n.tr("Удалить");
    }

    private final String keyDisplay() {
        Module module = this.module;
        if (module == null) {
            return "None";
        }
        Module mod = module;
        KeyBind bind = mod.getBind();
        return bind != null && bind.isBound() ? KeyHelper.getShortName(bind.getCode()) : "None";
    }

    public final boolean click(float mx, float my) {
        Module module = this.module;
        if (module == null) {
            return false;
        }
        Module mod = module;
        if (!this.open) {
            return false;
        }
        if (mx < this.px || mx > this.px + 108.0f || my < this.py || my > this.py + 61.0f) {
            this.close();
            return false;
        }
        boolean voice = mod.getBindType() == Module.BindType.VOICE;
        float rowY = this.py + 6.0f;
        float row2Y = rowY + 13.0f + 5.0f;
        float row3Y = row2Y + 13.0f + 5.0f;
        float btnW = 45.5f;
        float leftX = this.px + 6.0f;
        float rightX = this.px + 6.0f + btnW + 5.0f;
        String pillText = voice ? this.voiceDisplay() : (this.listening ? "..." : this.keyDisplay());
        float pillW = Math.max(40.0f, Fonts.MEDIUM.width(pillText, 6.0f) + 10.0f);
        float pillX = this.px + 108.0f - 6.0f - pillW;
        if (mx >= pillX && mx <= pillX + pillW && my >= rowY && my <= rowY + 13.0f) {
            if (voice) {
                if (this.isRecordingThis()) {
                    VoiceBindManager.INSTANCE.cancelRecording();
                    Notifications.push(I18n.tr("Голос"), I18n.tr("Запись остановлена"), 1200L);
                } else {
                    this.startRecording(mod.getName());
                }
            } else {
                this.listening = !this.listening;
            }
            return true;
        }
        if (my >= row2Y && my <= row2Y + 13.0f) {
            if (mx >= leftX && mx <= leftX + btnW) {
                mod.setBindType(Module.BindType.KEY);
                mod.setBindMode(this.restoreMode);
                return true;
            }
            if (mx >= rightX && mx <= rightX + btnW) {
                mod.setBindType(Module.BindType.VOICE);
                mod.setBindMode(Module.BindMode.TOGGLE);
                this.listening = false;
                if (VoiceTemplateStore.has(mod.getName())) {
                    this.ensureVoiceActive();
                }
                return true;
            }
        }
        if (my >= row3Y && my <= row3Y + 13.0f) {
            if (voice) {
                if (mx >= leftX && mx <= this.px + 108.0f - 6.0f) {
                    if (!VoiceTemplateStore.has("__wake__")) {
                        this.startRecording("__wake__");
                    } else if (this.takeCount() > 0) {
                        if (this.isRecordingThis()) {
                            VoiceBindManager.INSTANCE.cancelRecording();
                        }
                        VoiceTemplateStore.remove(mod.getName());
                        Notifications.push(I18n.tr("Голос"), I18n.tr("Запись удалена"), 1400L);
                    }
                    return true;
                }
            } else {
                if (mx >= leftX && mx <= leftX + btnW) {
                    mod.setBindMode(Module.BindMode.TOGGLE);
                    return true;
                }
                if (mx >= rightX && mx <= rightX + btnW) {
                    mod.setBindMode(Module.BindMode.HOLD);
                    return true;
                }
            }
        }
        this.drag.setTargetX(this.px);
        this.drag.setTargetY(this.py);
        this.dragging = this.drag.tryGrab(mx, my, 108.0f, 61.0f);
        return true;
    }

    private final void ensureVoiceActive() {
        VoiceControl control = VoiceControl.Companion.getInstance();
        if (control != null && !control.isEnabled()) {
            control.enable();
        }
    }

    private final void startRecording(String key) {
        String string;
        this.ensureVoiceActive();
        VoiceControl control = VoiceControl.Companion.getInstance();
        if (control == null || !control.isEnabled()) {
            Notifications.push(I18n.tr("Голос"), I18n.tr("Не удалось включить VoiceControl"), 1800L);
            return;
        }
        boolean wake = Intrinsics.areEqual((Object)"__wake__", (Object)key);
        VoiceBindManager.INSTANCE.beginRecording(key, (arg_0, arg_1, arg_2) -> BindPopup.startRecording$lambda$0(wake, arg_0, arg_1, arg_2));
        int left = VoiceBindManager.INSTANCE.promptRemaining(key);
        String string2 = I18n.tr("Голос");
        if (wake) {
            Object[] objectArray = new Object[]{left, VoiceBindManager.Companion.times(left)};
            string = I18n.tr("Скажи ключевое слово %d %s", objectArray);
        } else {
            Object[] objectArray = new Object[]{left, VoiceBindManager.Companion.times(left)};
            string = I18n.tr("Скажи фразу %d %s", objectArray);
        }
        Notifications.push(string2, string, 1800L);
    }

    public final void releaseDrag() {
        this.dragging = false;
        this.drag.release();
    }

    public final boolean keyPressed(int key) {
        Module module = this.module;
        if (module == null) {
            return false;
        }
        Module mod = module;
        if (!this.open || !this.listening) {
            return false;
        }
        if (key == 256) {
            mod.setBind(KeyBind.NONE);
        } else {
            mod.setBind(KeyBind.Companion.keyboard(key));
        }
        this.listening = false;
        return true;
    }

    public final boolean mouseBind(int button) {
        Module module = this.module;
        if (module == null) {
            return false;
        }
        Module mod = module;
        if (!this.open || !this.listening) {
            return false;
        }
        mod.setBind(KeyBind.Companion.mouse(button));
        this.listening = false;
        return true;
    }

    private static final void startRecording$lambda$0(boolean $wake, VoiceBindManager.RecordStatus status, String detail, int remaining) {
        Intrinsics.checkNotNullParameter((Object)((Object)status), (String)"status");
        switch (WhenMappings.$EnumSwitchMapping$0[status.ordinal()]) {
            case 1: {
                String string;
                if (remaining > 0) {
                    Object[] objectArray = new Object[]{remaining, VoiceBindManager.Companion.times(remaining), VoiceBindManager.Companion.takeHint(remaining)};
                    Notifications.push(I18n.tr("Голос"), I18n.tr("Отлично! Осталось ещё %d %s%s", objectArray), 2000L);
                    break;
                }
                String string2 = I18n.tr("Голос");
                String string3 = $wake ? I18n.tr("Ключевое слово готово") : I18n.tr("Фраза готова");
                if (detail == null) {
                    string = "";
                } else {
                    Object[] objectArray = new Object[]{detail};
                    string = I18n.tr(", совпадение %s", objectArray);
                }
                Notifications.push(string2, string3 + string, 2000L);
                break;
            }
            case 2: {
                Notifications.push(I18n.tr("Голос"), I18n.tr("Слишком коротко, повтори"), 1600L);
                break;
            }
            case 3: {
                Notifications.push(I18n.tr("Голос"), I18n.tr("Это шум, а не речь — повтори"), 1800L);
                break;
            }
            case 4: {
                Object[] objectArray = new Object[]{detail};
                Notifications.push(I18n.tr("Голос"), I18n.tr("Не услышал фразу, уровень %s", objectArray), 2200L);
                break;
            }
            default: {
                String string;
                String string4 = I18n.tr("Голос");
                if (detail == null) {
                    string = I18n.tr("Не получилось записать");
                } else {
                    Object[] objectArray = new Object[]{detail};
                    string = I18n.tr("Не получилось записать: %s", objectArray);
                }
                Notifications.push(string4, string, 2400L);
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/ui/BindPopup.Companion;", "", "<init>", "()V", "", "WIDTH", "F", "PAD", "ROW_H", "GAP", "HEIGHT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[VoiceBindManager.RecordStatus.values().length];
            try {
                nArray[VoiceBindManager.RecordStatus.OK.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[VoiceBindManager.RecordStatus.TOO_SHORT.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[VoiceBindManager.RecordStatus.TOO_QUIET.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[VoiceBindManager.RecordStatus.TIMEOUT.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

