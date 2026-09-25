/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareController;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.utils.net.ClientPresence;

@Feature(value={"globals"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\r\u0010\u0003J\u000f\u0010\u000e\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\u000e\u0010\u0003R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0011\u00ca\u0001\u0010\b\u0016\u0012\f\b\u0017\u0012\b\b\fJ\u0004\b\b(\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Globals;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "defaultEnabled", "()Z", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "onEnable", "onDisable", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "inTags", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "inTab", "shareGui", "remoteGuis", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "globals", "rtx.kimiko:kimiko"})
public final class Globals
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting inTags = (BooleanSetting)this.register((Setting)new BooleanSetting("Показывать в тегах", "Логотип клиента рядом с именем игроков Kimiko в табличках над головой.", true));
    @NotNull
    private final BooleanSetting inTab = (BooleanSetting)this.register((Setting)new BooleanSetting("Показывать в табе", "Логотип клиента рядом с именем игроков Kimiko в списке игроков (Tab).", true));
    @NotNull
    private final BooleanSetting shareGui = (BooleanSetting)this.register((Setting)new BooleanSetting("Транслировать гуй", "Пользователи Kimiko рядом видят ваш открытый гуй в мире перед вами.", true));
    @NotNull
    private final BooleanSetting remoteGuis = (BooleanSetting)this.register((Setting)new BooleanSetting("Гуй игроков в мире", "Показывает открытые гуи других пользователей Kimiko у их лица.", true));

    public Globals() {
        super("Globals", "Показывает метку клиента у других пользователей Kimiko.", Category.UTILS);
    }

    @Override
    public boolean defaultEnabled() {
        return true;
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (event.isPre()) {
            GuiShareController.tick(this);
        }
    }

    @Override
    protected void onEnable() {
        ClientPresence.INSTANCE.start();
    }

    @Override
    protected void onDisable() {
        GuiShareController.reset();
    }

    @JvmStatic
    @Nullable
    public static final Globals getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean tagsBadge() {
        return Companion.tagsBadge();
    }

    @JvmStatic
    public static final boolean tabBadge() {
        return Companion.tabBadge();
    }

    @JvmStatic
    public static final boolean shareGuiEnabled() {
        return Companion.shareGuiEnabled();
    }

    @JvmStatic
    public static final boolean remoteGuisEnabled() {
        return Companion.remoteGuisEnabled();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0013\u0010\f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\nJ\u0013\u0010\r\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\n\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Globals.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/Globals;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Utils/Globals;", "", "tagsBadge", "()Z", "tabBadge", "shareGuiEnabled", "remoteGuisEnabled", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final Globals getInstance() {
            return ModuleManager.Companion.get().get(Globals.class);
        }

        @JvmStatic
        public final boolean tagsBadge() {
            Globals module = this.getInstance();
            return module != null && module.isEnabled() && module.inTags.getValue();
        }

        @JvmStatic
        public final boolean tabBadge() {
            Globals module = this.getInstance();
            return module != null && module.isEnabled() && module.inTab.getValue();
        }

        @JvmStatic
        public final boolean shareGuiEnabled() {
            Globals module = this.getInstance();
            return module != null && module.isEnabled() && module.shareGui.getValue();
        }

        @JvmStatic
        public final boolean remoteGuisEnabled() {
            Globals module = this.getInstance();
            return module != null && module.isEnabled() && module.remoteGuis.getValue();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

