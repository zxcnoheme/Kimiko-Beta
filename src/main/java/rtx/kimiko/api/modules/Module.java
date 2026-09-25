/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.LazyThreadSafetyMode
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules;

import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.Event;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.module.ModuleToggleEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.ModuleFadeOuts;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.restrict.ServerRestrictions;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.SettingRepository;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.voice.VoiceTemplateStore;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u000e\b&\u0018\u00002\u00020\u0001:\u0002efB!\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000e\u001a\u00020\tH\u0007b\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\t\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\r\u0010\u0015\u001a\u00020\t\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\r\u0010\u0016\u001a\u00020\t\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\u0017\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u000f\u0010\u0019\u001a\u00020\tH\u0000\u00a2\u0006\u0004\b\u0018\u0010\u000fJ\u000f\u0010\u001b\u001a\u00020\u001aH\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u001a\u00a2\u0006\u0004\b\u001f\u0010\u001cJ\r\u0010 \u001a\u00020\u0010\u00a2\u0006\u0004\b \u0010\u001eJ\u001f\u0010!\u001a\u00020\tH\u0015b\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u00a2\u0006\u0004\b!\u0010\u000fJ\u001f\u0010\"\u001a\u00020\tH\u0015b\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u00a2\u0006\u0004\b\"\u0010\u000fJ\u000f\u0010#\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b#\u0010\u001eJ\r\u0010$\u001a\u00020\u0002\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b&\u0010%J\r\u0010'\u001a\u00020\u0002\u00a2\u0006\u0004\b'\u0010%J\u000f\u0010(\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b(\u0010%J\r\u0010)\u001a\u00020\u0005\u00a2\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b+\u0010\u001eJ\u000f\u0010,\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b,\u0010\u001eJ\r\u0010.\u001a\u00020-\u00a2\u0006\u0004\b.\u0010/J\r\u00101\u001a\u000200\u00a2\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u00020\t2\b\u00103\u001a\u0004\u0018\u000100\u00a2\u0006\u0004\b4\u00105J\r\u00107\u001a\u000206\u00a2\u0006\u0004\b7\u00108J\u0017\u0010:\u001a\u00020\t2\b\u00109\u001a\u0004\u0018\u000106\u00a2\u0006\u0004\b:\u0010;J\r\u0010=\u001a\u00020<\u00a2\u0006\u0004\b=\u0010>J\u0017\u0010@\u001a\u00020\t2\b\u0010?\u001a\u0004\u0018\u00010<\u00a2\u0006\u0004\b@\u0010AJ\r\u0010B\u001a\u00020\u0010\u00a2\u0006\u0004\bB\u0010\u001eJ!\u0010F\u001a\u00028\u0000\"\b\b\u0000\u0010D*\u00020C2\u0006\u0010E\u001a\u00028\u0000H\u0004\u00a2\u0006\u0004\bF\u0010GJ3\u0010F\u001a\u00020\t2\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020C0H\"\u00020CH\u0005b\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u00a2\u0006\u0004\bF\u0010JJ\u0011\u0010K\u001a\u00020\u0002H\u0096\u0080\u0004\u00a2\u0006\u0004\bK\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010LR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010LR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010MR\u0019\u0010P\u001a\u00020N8\u0004X\u0085\u0004\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010R\u001a\u00020-8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010TR\u0016\u0010U\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010TR\u0016\u0010V\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010TR\u0016\u0010W\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Z\u001a\u00020Y8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u00103\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010\\R\u0016\u0010]\u001a\u0002068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u001b\u0010d\u001a\u00020\u00028BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\ba\u0010b\u001a\u0004\bc\u0010%\u00a8\u0006g"}, d2={"Lrtx/kimiko/api/modules/Module;", "", "", "name", "description", "Lrtx/kimiko/api/modules/Category;", "category", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/Category;)V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "toggle", "()V", "", "enabled", "setEnabled", "(Z)V", "enable", "disable", "disableInstant", "instant", "finishFadeOut$rtx_kimiko_kimiko", "finishFadeOut", "", "fadeOutSeconds", "()F", "isFadingOut", "()Z", "visualAlpha", "isVisuallyActive", "onEnable", "onDisable", "defaultEnabled", "getName", "()Ljava/lang/String;", "getDisplayName", "getDescription", "getDisplayDescription", "getCategory", "()Lrtx/kimiko/api/modules/Category;", "isHiddenInList", "isEnabled", "Lrtx/kimiko/api/modules/settings/SettingRepository;", "getSettings", "()Lrtx/kimiko/api/modules/settings/SettingRepository;", "Lrtx/kimiko/utils/key/KeyBind;", "getBind", "()Lrtx/kimiko/utils/key/KeyBind;", "bind", "setBind", "(Lrtx/kimiko/utils/key/KeyBind;)V", "Lrtx/kimiko/api/modules/Module$BindMode;", "getBindMode", "()Lrtx/kimiko/api/modules/Module$BindMode;", "mode", "setBindMode", "(Lrtx/kimiko/api/modules/Module$BindMode;)V", "Lrtx/kimiko/api/modules/Module$BindType;", "getBindType", "()Lrtx/kimiko/api/modules/Module$BindType;", "type", "setBindType", "(Lrtx/kimiko/api/modules/Module$BindType;)V", "hasVoiceBind", "Lrtx/kimiko/api/modules/settings/Setting;", "T", "setting", "register", "(Lrtx/kimiko/api/modules/settings/Setting;)Lrtx/kimiko/api/modules/settings/Setting;", "", "s", "([Lrtx/kimiko/api/modules/settings/Setting;)V", "toString", "Ljava/lang/String;", "Lrtx/kimiko/api/modules/Category;", "Lnet/minecraft/MinecraftClient;", "Lkotlin/jvm/JvmField;", "mc", "Lnet/minecraft/MinecraftClient;", "settings", "Lrtx/kimiko/api/modules/settings/SettingRepository;", "Z", "subscribed", "fadingOut", "fadeSeconds", "F", "", "fadeStartNanos", "J", "Lrtx/kimiko/utils/key/KeyBind;", "bindMode", "Lrtx/kimiko/api/modules/Module$BindMode;", "bindType", "Lrtx/kimiko/api/modules/Module$BindType;", "cachedDisplayName$delegate", "Lkotlin/Lazy;", "getCachedDisplayName", "cachedDisplayName", "BindMode", "BindType", "rtx.kimiko:kimiko"})
public abstract class Module {
    @NotNull
    private final String name;
    @NotNull
    private final String description;
    @NotNull
    private final Category category;
    @JvmField
    @NotNull
    protected final MinecraftClient mc;
    @NotNull
    private final SettingRepository settings;
    private boolean enabled;
    private boolean subscribed;
    private boolean fadingOut;
    private float fadeSeconds;
    private long fadeStartNanos;
    @NotNull
    private KeyBind bind;
    @NotNull
    private BindMode bindMode;
    @NotNull
    private BindType bindType;
    @NotNull
    private final Lazy cachedDisplayName$delegate;

    protected Module(@NotNull String name, @NotNull String description, @NotNull Category category) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)description, (String)"description");
        Intrinsics.checkNotNullParameter((Object)((Object)category), (String)"category");
        this.name = name;
        this.description = description;
        this.category = category;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        this.mc = minecraftClient2;
        this.settings = new SettingRepository();
        this.bind = KeyBind.NONE;
        this.bindMode = BindMode.TOGGLE;
        this.bindType = BindType.KEY;
        this.cachedDisplayName$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.NONE, () -> Module.cachedDisplayName_delegate$lambda$0(this));
    }

    @Protect(value=Level.CROWN)
    public final void toggle() {
        if (this.enabled) {
            this.disable();
        } else {
            this.enable();
        }
    }

    public final void setEnabled(boolean enabled) {
        if (enabled) {
            this.enable();
        } else {
            ModuleManager.Companion.get().clearSuspension(this);
            this.disable();
        }
    }

    public final void enable() {
        if (this.enabled) {
            return;
        }
        String blockReason = ServerRestrictions.blockReason(this, ServerRestrictions.current());
        if (blockReason != null) {
            ServerRestrictions.notify(blockReason);
            return;
        }
        this.enabled = true;
        boolean resuming = this.fadingOut;
        this.fadingOut = false;
        if (!this.subscribed) {
            EventBus.Companion.get().subscribe(this);
            this.subscribed = true;
        }
        if (!resuming) {
            this.onEnable();
        }
        EventBus.Companion.get().post((Event)new ModuleToggleEvent(this, true));
    }

    public final void disable() {
        this.disable(false);
    }

    public final void disableInstant() {
        this.disable(true);
    }

    private final void disable(boolean instant) {
        if (!this.enabled) {
            return;
        }
        this.enabled = false;
        float fade = this.fadeOutSeconds();
        if (!instant && fade > 0.0f && this.subscribed && this.mc.world != null) {
            this.fadingOut = true;
            this.fadeSeconds = fade;
            this.fadeStartNanos = System.nanoTime();
            EventBus.Companion.get().post((Event)new ModuleToggleEvent(this, false));
            ModuleFadeOuts.INSTANCE.watch(this);
            return;
        }
        this.fadingOut = false;
        this.onDisable();
        EventBus.Companion.get().post((Event)new ModuleToggleEvent(this, false));
        if (this.subscribed) {
            EventBus.Companion.get().unsubscribe(this);
            this.subscribed = false;
        }
    }

    public final void finishFadeOut$rtx_kimiko_kimiko() {
        if (this.enabled || !this.fadingOut) {
            return;
        }
        this.fadingOut = false;
        this.onDisable();
        if (this.subscribed) {
            EventBus.Companion.get().unsubscribe(this);
            this.subscribed = false;
        }
    }

    public float fadeOutSeconds() {
        return 0.0f;
    }

    public final boolean isFadingOut() {
        return this.fadingOut && !this.enabled;
    }

    public final float visualAlpha() {
        if (this.enabled) {
            return 1.0f;
        }
        if (!this.fadingOut) {
            return 0.0f;
        }
        float elapsed = (float)(System.nanoTime() - this.fadeStartNanos) / 1.0E9f;
        if (elapsed >= this.fadeSeconds) {
            return 0.0f;
        }
        float t = 1.0f - elapsed / this.fadeSeconds;
        return t * t * (3.0f - 2.0f * t);
    }

    public final boolean isVisuallyActive() {
        return this.enabled || this.visualAlpha() > 0.0f;
    }

    @Protect(value=Level.CROWN)
    protected void onEnable() {
    }

    @Protect(value=Level.CROWN)
    protected void onDisable() {
    }

    public boolean defaultEnabled() {
        return false;
    }

    private final String getCachedDisplayName() {
        Lazy lazy = this.cachedDisplayName$delegate;
        Object object = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue((Object)object, (String)"getValue(...)");
        return (String)object;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public String getDisplayName() {
        return I18n.tr(this.getCachedDisplayName());
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public String getDisplayDescription() {
        return I18n.tr(this.description);
    }

    @NotNull
    public final Category getCategory() {
        return this.category;
    }

    public boolean isHiddenInList() {
        return false;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @NotNull
    public final SettingRepository getSettings() {
        return this.settings;
    }

    @NotNull
    public final KeyBind getBind() {
        return this.bind;
    }

    public final void setBind(@Nullable KeyBind bind) {
        KeyBind next = bind != null ? bind : KeyBind.NONE;
        if (this.bind.getCode() != next.getCode()) {
            this.bind = next;
            ConfigManager.Companion.markDirty();
            return;
        }
        this.bind = next;
    }

    @NotNull
    public final BindMode getBindMode() {
        return this.bindMode;
    }

    public final void setBindMode(@Nullable BindMode mode) {
        BindMode next = mode != null ? mode : BindMode.TOGGLE;
        if (this.bindMode != next) {
            this.bindMode = next;
            ConfigManager.Companion.markDirty();
        }
    }

    @NotNull
    public final BindType getBindType() {
        return this.bindType;
    }

    public final void setBindType(@Nullable BindType type) {
        BindType next = type != null ? type : BindType.KEY;
        if (this.bindType != next) {
            this.bindType = next;
            ConfigManager.Companion.markDirty();
        }
    }

    public final boolean hasVoiceBind() {
        return this.bindType == BindType.VOICE && VoiceTemplateStore.has(this.name);
    }

    @NotNull
    protected final <T extends Setting> T register(@NotNull T setting) {
        Intrinsics.checkNotNullParameter(setting, (String)"setting");
        setting.setChangeListener(Module::register$lambda$0);
        Setting[] settingArray = new Setting[]{setting};
        this.settings.add(settingArray);
        return setting;
    }

    @Protect(value=Level.CROWN)
    protected final void register(Setting ... s) {
        Intrinsics.checkNotNullParameter((Object)s, (String)"s");
        int n = s.length;
        for (int i = 0; i < n; ++i) {
            Setting setting = s[i];
            setting.setChangeListener(Module::register$lambda$1);
        }
        this.settings.add(Arrays.copyOf(s, s.length));
    }

    @NotNull
    public String toString() {
        return this.name + " [" + this.category + "] " + (this.enabled ? "ON" : "OFF");
    }

    private static final String cachedDisplayName_delegate$lambda$0(Module this$0) {
        StringBuilder sb = new StringBuilder(this$0.name.length() + 4);
        char[] cArray = this$0.name.toCharArray();
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toCharArray(...)");
        char[] cs = cArray;
        int n = cs.length;
        for (int i = 0; i < n; ++i) {
            char c = cs[i];
            if (i > 0 && Character.isUpperCase(c)) {
                boolean acronymEnd;
                char prev = cs[i - 1];
                char next = i + 1 < cs.length ? cs[i + 1] : (char)' ';
                boolean prevLowerOrDigit = Character.isLowerCase(prev) || Character.isDigit(prev);
                boolean bl = acronymEnd = Character.isUpperCase(prev) && Character.isLowerCase(next);
                if (prev != ' ' && (prevLowerOrDigit || acronymEnd)) {
                    sb.append(' ');
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static final void register$lambda$0() {
        ConfigManager.Companion.markDirty();
    }

    private static final void register$lambda$1() {
        ConfigManager.Companion.markDirty();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/modules/Module$BindMode;", "", "<init>", "(Ljava/lang/String;I)V", "TOGGLE", "HOLD", "rtx.kimiko:kimiko"})
    public static enum BindMode {
        TOGGLE,
        HOLD;

        @NotNull
        public static EnumEntries<BindMode> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/modules/Module$BindType;", "", "<init>", "(Ljava/lang/String;I)V", "KEY", "VOICE", "rtx.kimiko:kimiko"})
    public static enum BindType {
        KEY,
        VOICE;

        @NotNull
        public static EnumEntries<BindType> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        
    }
}

