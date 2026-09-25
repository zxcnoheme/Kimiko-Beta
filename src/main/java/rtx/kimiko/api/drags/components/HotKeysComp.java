/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.components.ListHudComp;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.modules.impl.Interface.HotKeysModule;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.math.MathUtils;
import rtx.kimiko.utils.render.fonts.Fonts;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0014\u00a2\u0006\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/drags/components/HotKeysComp;", "Lrtx/kimiko/api/drags/components/ListHudComp;", "<init>", "()V", "", "iconSlotSize", "()F", "", "headerIconGlyph", "()Ljava/lang/String;", "", "Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "collectRows", "()Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
public final class HotKeysComp
extends ListHudComp {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final int ICON_COLOR = -3355444;
    @NotNull
    private static final String EXAMPLE_NAME = "Example bind";
    @NotNull
    private static final String EXAMPLE_KEY = "[B]";

    public HotKeysComp() {
        super("hotkeys", "Hotkeys", HotKeysModule.class, 5.0f, 33.0f);
    }

    @Override
    protected float iconSlotSize() {
        return 6.0f;
    }

    @Override
    @NotNull
    protected String headerIconGlyph() {
        return "u";
    }

    @Override
    @NotNull
    protected List<ListHudComp.Row> collectRows() {
        ArrayList<ListHudComp.Row> rows = new ArrayList<ListHudComp.Row>();
        for (Module module : ModuleManager.Companion.get().getAll()) {
            if (!HotKeysComp.Companion.isActiveBind(module)) continue;
            rows.add(new ListHudComp.Row(module, module.getName(), HotKeysComp.Companion.bindText(module), HotKeysComp.Companion.categoryIcon(module.getCategory()), null, 0, 48, null));
        }
        if (rows.isEmpty() && DragSystem.Companion.get().isDragModeActive()) {
            rows.add(new ListHudComp.Row("preview", EXAMPLE_NAME, EXAMPLE_KEY, HotKeysComp.Companion.categoryIcon(Category.VISUALS), null, 0, 48, null));
        }
        return rows;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/drags/components/HotKeysComp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/Category;", "category", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "categoryIcon", "(Lrtx/kimiko/api/modules/Category;)Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "Lrtx/kimiko/api/modules/Module;", "module", "", "isActiveBind", "(Lrtx/kimiko/api/modules/Module;)Z", "", "bindText", "(Lrtx/kimiko/api/modules/Module;)Ljava/lang/String;", "", "iconChar", "(Lrtx/kimiko/api/modules/Category;)C", "", "ICON_COLOR", "I", "EXAMPLE_NAME", "Ljava/lang/String;", "EXAMPLE_KEY", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final ListHudComp.IconDrawer categoryIcon(Category category) {
            if (category == null) {
                return null;
            }
            String glyph = String.valueOf(this.iconChar(category));
            return (arg_0, arg_1, arg_2, arg_3, arg_4) -> Companion.categoryIcon$lambda$0(glyph, arg_0, arg_1, arg_2, arg_3, arg_4);
        }

        private final boolean isActiveBind(Module module) {
            if (module == null) {
                return false;
            }
            if (module instanceof ClickGui) {
                return false;
            }
            if (!module.isEnabled()) {
                return false;
            }
            if (module.getBindType() == Module.BindType.VOICE) {
                return module.hasVoiceBind();
            }
            KeyBind bind = module.getBind();
            return bind != null && bind.isBound();
        }

        private final String bindText(Module module) {
            if (module.getBindType() == Module.BindType.VOICE) {
                return "[" + I18n.tr("Голос") + "]";
            }
            return "[" + MathUtils.shortBind(module.getBind()) + "]";
        }

        private final char iconChar(Category category) {
            return switch (WhenMappings.$EnumSwitchMapping$0[category.ordinal()]) {
                case 1 -> 'p';
                case 2 -> 'j';
                case 3 -> 'r';
                case 4 -> 'i';
                case 5 -> 'w';
                case 6 -> 'B';
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        private static final void categoryIcon$lambda$0(String $glyph, DrawContext drawContext2, float x, float y, float size, float alpha) {
            float[] bounds;
            Intrinsics.checkNotNullParameter((Object)drawContext2, (String)"<unused var>");
            float a = Math.max(0.0f, Math.min(1.0f, alpha));
            if (a > 0.003921569f && (bounds = Fonts.KIMIKO.msdfBounds($glyph, size)) != null && bounds.length >= 4) {
                float drawX = x + (size - (bounds[2] - bounds[0])) * 0.5f - bounds[0];
                float drawY = y + (size - (bounds[3] - bounds[1])) * 0.5f - bounds[1];
                Fonts.KIMIKO.msdf($glyph, drawX, drawY, size, ColorEngine.multAlpha(-3355444, a));
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[Category.values().length];
                try {
                    nArray[Category.VISUALS.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.DISPLAY.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.UTILS.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.EVENTS.ordinal()] = 4;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.CONFIGS.ordinal()] = 5;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.THEMES.ordinal()] = 6;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }
}

