/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Formatting
 *  net.minecraft.entity.effect.StatusEffect
 *  net.minecraft.entity.effect.StatusEffectInstance
 *  net.minecraft.entity.effect.StatusEffects
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.entity.effect.StatusEffectCategory
 *  net.minecraft.registry.entry.RegistryEntry
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Formatting;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.components.ListHudComp;
import rtx.kimiko.api.modules.impl.Interface.PotionsModule;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001d\u001cB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\f\u0010\u0003J\u000f\u0010\r\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R0\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015j\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017`\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0011\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/drags/components/PotionsComp;", "Lrtx/kimiko/api/drags/components/ListHudComp;", "<init>", "()V", "", "headerIconGlyph", "()Ljava/lang/String;", "", "Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "collectRows", "()Ljava/util/List;", "", "sweepLabelCache", "previewRow", "()Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "", "previewSwitchMs", "J", "", "previewIndex", "I", "Ljava/util/HashMap;", "", "Lrtx/kimiko/api/drags/components/PotionsComp$EffectLabel;", "Lkotlin/collections/HashMap;", "labelCache", "Ljava/util/HashMap;", "labelCacheAt", "Companion", "EffectLabel", "rtx.kimiko:kimiko"})
public final class PotionsComp
extends ListHudComp {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private long previewSwitchMs;
    private int previewIndex;
    @NotNull
    private final HashMap<Object, EffectLabel> labelCache = new HashMap();
    private long labelCacheAt;
    @NotNull
    private static final RegistryEntry<StatusEffect>[] PREVIEW_EFFECTS;
    private static final int NEGATIVE_COLOR;
    private static final float ICON_SCALE = 0.5f;
    private static final float EFFECT_ICON_SIZE = 8.0f;
    private static final int PULSE_THRESHOLD_TICKS = 200;
    private static final long PULSE_PERIOD_MS = 900L;
    private static final float PULSE_MIN_ALPHA = 0.2f;

    public PotionsComp() {
        super("potions", "Potions", PotionsModule.class, 104.0f, 33.0f);
    }

    @Override
    @NotNull
    protected String headerIconGlyph() {
        return "s";
    }

    @Override
    @NotNull
    protected List<ListHudComp.Row> collectRows() {
        ArrayList<ListHudComp.Row> rows = new ArrayList<ListHudComp.Row>();
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity player = mc.player;
        if (player == null) {
            if (DragSystem.Companion.get().isDragModeActive()) {
                rows.add(this.previewRow());
            }
            return rows;
        }
        this.sweepLabelCache();
        for (StatusEffectInstance instance : player.getStatusEffects()) {
            RegistryEntry<StatusEffect> effect = instance.getEffectType();
            int amplifier = instance.getAmplifier();
            EffectLabel cached = this.labelCache.get(effect);
            if (cached == null || cached.getAmplifier() != amplifier) {
                String built = Formatting.strip(effect.value().getName().getString());
                if (amplifier > 0) {
                    built = built + " " + (amplifier + 1);
                }
                String string = built != null ? built : "";
                cached = new EffectLabel(amplifier, string, (arg_0, arg_1, arg_2, arg_3, arg_4) -> PotionsComp.collectRows$lambda$0(effect, arg_0, arg_1, arg_2, arg_3, arg_4), PotionsComp.Companion.isNegative(effect) ? NEGATIVE_COLOR : 0);
                this.labelCache.put(effect, cached);
            }
            rows.add(new ListHudComp.Row(effect, cached.getText(), PotionsComp.Companion.formatDuration(instance), cached.getIcon(), PotionsComp.Companion.expiryPulse(instance), cached.getTextColor()));
        }
        if (rows.isEmpty() && DragSystem.Companion.get().isDragModeActive()) {
            rows.add(this.previewRow());
        }
        return rows;
    }

    private final void sweepLabelCache() {
        long now = System.currentTimeMillis();
        if (now - this.labelCacheAt > 5000L) {
            this.labelCacheAt = now;
            this.labelCache.clear();
        }
    }

    private final ListHudComp.Row previewRow() {
        long now = System.currentTimeMillis();
        if (now - this.previewSwitchMs >= 1000L) {
            this.previewIndex = (this.previewIndex + 1) % PREVIEW_EFFECTS.length;
            this.previewSwitchMs = now;
        }
        RegistryEntry<StatusEffect> effect = PREVIEW_EFFECTS[this.previewIndex];
        return new ListHudComp.Row("preview", "Example effect", "**:**", (arg_0, arg_1, arg_2, arg_3, arg_4) -> PotionsComp.previewRow$lambda$0(effect, arg_0, arg_1, arg_2, arg_3, arg_4), null, 0, 48, null);
    }

    private static final void collectRows$lambda$0(RegistryEntry $effect, DrawContext drawContext2, float x, float y, float size, float alpha) {
        Intrinsics.checkNotNullParameter((Object)drawContext2, (String)"<unused var>");
        PotionsComp.Companion.drawEffectIcon((RegistryEntry<StatusEffect>)$effect, x, y, size, alpha);
    }

    private static final void previewRow$lambda$0(RegistryEntry $effect, DrawContext drawContext2, float x, float y, float size, float alpha) {
        Intrinsics.checkNotNullParameter((Object)drawContext2, (String)"<unused var>");
        PotionsComp.Companion.drawEffectIcon((RegistryEntry<StatusEffect>)$effect, x, y, size, alpha);
    }

    static {
        RegistryEntry[] class_6880Array = new RegistryEntry[4];
        Intrinsics.checkNotNullExpressionValue((Object)StatusEffects.SPEED, (String)"SPEED");
        Intrinsics.checkNotNullExpressionValue((Object)StatusEffects.JUMP_BOOST, (String)"JUMP_BOOST");
        Intrinsics.checkNotNullExpressionValue((Object)StatusEffects.REGENERATION, (String)"REGENERATION");
        Intrinsics.checkNotNullExpressionValue((Object)StatusEffects.FIRE_RESISTANCE, (String)"FIRE_RESISTANCE");
        PREVIEW_EFFECTS = class_6880Array;
        NEGATIVE_COLOR = ColorEngine.lerpColor(-3355444, -53714, 0.32f);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J=\u0010\r\u001a\u00020\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0015\u001a\u00020\u00142\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cR \u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0014\u0010&\u001a\u00020 8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010\"R\u0014\u0010(\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010$\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/drags/components/PotionsComp.Companion;", "", "<init>", "()V", "Lnet/minecraft/RegistryEntry;", "Lnet/minecraft/StatusEffect;", "effect", "", "x", "y", "size", "alpha", "", "drawEffectIcon", "(Lnet/minecraft/RegistryEntry;FFFF)V", "Lnet/minecraft/StatusEffectInstance;", "instance", "Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "expiryPulse", "(Lnet/minecraft/StatusEffectInstance;)Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "", "isNegative", "(Lnet/minecraft/RegistryEntry;)Z", "value", "smooth", "(F)F", "", "formatDuration", "(Lnet/minecraft/StatusEffectInstance;)Ljava/lang/String;", "", "PREVIEW_EFFECTS", "[Lnet/minecraft/RegistryEntry;", "", "NEGATIVE_COLOR", "I", "ICON_SCALE", "F", "EFFECT_ICON_SIZE", "PULSE_THRESHOLD_TICKS", "", "PULSE_PERIOD_MS", "J", "PULSE_MIN_ALPHA", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final void drawEffectIcon(RegistryEntry<StatusEffect> effect, float x, float y, float size, float alpha) {
            float offset = (size - 8.0f) * 0.5f;
            Render2D.effectIcon(effect, x + offset, y + offset, 8.0f, ColorEngine.multAlpha(-1, alpha));
        }

        private final ListHudComp.AlphaPulse expiryPulse(StatusEffectInstance instance) {
            if (instance.isInfinite() || instance.getDuration() > 200) {
                return null;
            }
            return () -> Companion.expiryPulse$lambda$0(instance);
        }

        private final boolean isNegative(RegistryEntry<StatusEffect> effect) {
            if (((StatusEffect)effect.value()).getCategory() == StatusEffectCategory.HARMFUL) {
                return true;
            }
            return effect.value() == StatusEffects.SLOW_FALLING.value();
        }

        private final float smooth(float value) {
            float t = Math.max(0.0f, Math.min(1.0f, value));
            return t * t * (3.0f - 2.0f * t);
        }

        private final String formatDuration(StatusEffectInstance instance) {
            if (instance.isInfinite()) {
                return "**:**";
            }
            int seconds = Math.max(0, instance.getDuration()) / 20;
            int minutes = seconds / 60;
            Locale locale = Locale.ROOT;
            String string = "%02d";
            Object[] objectArray = new Object[]{seconds % 60};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            return minutes + ":" + string2;
        }

        private static final float expiryPulse$lambda$0(StatusEffectInstance $instance) {
            float f;
            int remaining = Math.max(0, $instance.getDuration());
            if (remaining > 200) {
                f = 1.0f;
            } else {
                float closeness = 1.0f - (float)remaining / 200.0f;
                float depth = Companion.smooth(closeness) * 0.8f;
                float phase = (float)(System.currentTimeMillis() % 900L) / 900.0f;
                float wave = 0.5f - 0.5f * (float)Math.cos((double)phase * 2.0 * Math.PI);
                f = 1.0f - depth * wave;
            }
            return f;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u000b\u001a\u0004\b\u0014\u0010\r\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/drags/components/PotionsComp$EffectLabel;", "", "", "amplifier", "", "text", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "icon", "textColor", "<init>", "(ILjava/lang/String;Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;I)V", "I", "getAmplifier", "()I", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "getIcon", "()Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "getTextColor", "rtx.kimiko:kimiko"})
    private static final class EffectLabel {
        private final int amplifier;
        @NotNull
        private final String text;
        @NotNull
        private final ListHudComp.IconDrawer icon;
        private final int textColor;

        public EffectLabel(int amplifier, @NotNull String text, @NotNull ListHudComp.IconDrawer icon, int textColor) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            Intrinsics.checkNotNullParameter((Object)icon, (String)"icon");
            this.amplifier = amplifier;
            this.text = text;
            this.icon = icon;
            this.textColor = textColor;
        }

        public final int getAmplifier() {
            return this.amplifier;
        }

        @NotNull
        public final String getText() {
            return this.text;
        }

        @NotNull
        public final ListHudComp.IconDrawer getIcon() {
            return this.icon;
        }

        public final int getTextColor() {
            return this.textColor;
        }
    }
}

