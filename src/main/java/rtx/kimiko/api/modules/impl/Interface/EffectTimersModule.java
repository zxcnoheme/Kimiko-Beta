package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Effect Timers NEW: карточка над хотбаром со списком активных эффектов —
 * имя, уровень и полоска оставшегося времени (относительно максимальной
 * виденной длительности этого эффекта). До 9 эффектов.
 */
public class EffectTimersModule extends Module {
    private static final int MAX_ROWS = 9;
    private static final long SEEN_TTL_MS = 5L * 60L * 1000L;

    private final BooleanSetting hideInfinite;
    private final Map<RegistryEntry<StatusEffect>, Integer> maxSeen = new HashMap<>();
    private final Map<RegistryEntry<StatusEffect>, Long> seenAt = new HashMap<>();

    public EffectTimersModule() {
        super("Effect Timers NEW", "Карточка эффектов над хотбаром: имя, уровень и полоска оставшегося времени.", Category.DISPLAY);
        this.hideInfinite = (BooleanSetting) this.register((Setting) new BooleanSetting("Скрывать бесконечные", "Не показывать эффекты без длительности.", false));
    }

    @Override
    protected void onDisable() {
        this.maxSeen.clear();
        this.seenAt.clear();
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        if (this.mc.player == null) {
            // вышли из мира — кэш максимумов больше не актуален
            if (this.mc.world == null) {
                this.maxSeen.clear();
                this.seenAt.clear();
            }
            return;
        }
        for (StatusEffectInstance s : this.mc.player.getStatusEffects()) {
            int duration = Math.max(0, s.getDuration());
            Integer seen = this.maxSeen.get(s.getEffectType());
            if (seen == null || duration > seen) {
                this.maxSeen.put(s.getEffectType(), duration);
            }
            this.seenAt.put(s.getEffectType(), System.currentTimeMillis());
        }
        if (this.maxSeen.size() > 64) {
            long now = System.currentTimeMillis();
            this.maxSeen.keySet().removeIf(key -> {
                Long at = this.seenAt.get(key);
                return at == null || now - at > SEEN_TTL_MS;
            });
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.mc.world == null) {
            return;
        }
        List<StatusEffectInstance> effects = new ArrayList<>();
        for (StatusEffectInstance s : this.mc.player.getStatusEffects()) {
            if (s.isInfinite() && this.hideInfinite.getValue()) {
                continue;
            }
            effects.add(s);
            if (effects.size() >= MAX_ROWS) {
                break;
            }
        }
        if (effects.isEmpty()) {
            return;
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        float cardW = 74.0f;
        float rowH = 11.0f;
        float pad = 4.0f;
        float cardH = pad + effects.size() * rowH + 1.0f;
        float x = w * 0.5f - cardW * 0.5f;
        float y = h - 46.0f - cardH;

        Render2D.rect(x, y, cardW, cardH, 7.0f, MenuColors.rgba(9, 11, 17, 165));
        Render2D.rect(x, y, cardW, 0.8f, 0.0f, MenuColors.rgba(255, 255, 255, 26));

        float ry = y + pad;
        for (StatusEffectInstance s : effects) {
            RegistryEntry<StatusEffect> effect = s.getEffectType();
            String name = effect.value().getName().getString();
            int amplifier = s.getAmplifier();
            String label = amplifier > 0 ? name + " " + (amplifier + 1) : name;
            // обрезка длинных названий под ширину строки
            while (label.length() > 3 && Fonts.SEMIBOLD.width(label, 7.0f) > cardW - pad * 2.0f - 12.0f) {
                label = label.substring(0, label.length() - 2);
            }

            int ticksLeft = Math.max(0, s.getDuration());
            String time;
            if (s.isInfinite() || ticksLeft <= 0) {
                time = "∞";
            } else {
                int seconds = (ticksLeft + 19) / 20;
                time = String.format("%d:%02d", seconds / 60, seconds % 60);
            }
            float timeW = Fonts.SEMIBOLD.width(time, 6.5f);
            Fonts.SEMIBOLD.draw(time, x + cardW - pad - timeW, ry + 0.4f, 6.5f,
                    MenuColors.rgba(200, 210, 228, 200));
            Fonts.SEMIBOLD.draw(label, x + pad + 1.0f, ry + 0.4f, 7.0f,
                    MenuColors.rgba(235, 240, 250, 225));

            // полоска оставшегося времени: длина относительно максимальной виденной длительности
            float barY = ry + 8.2f;
            float barW = cardW - pad * 2.0f;
            Render2D.rect(x + pad, barY, barW, 1.2f, 0.6f, MenuColors.rgba(255, 255, 255, 22));
            float frac = 0.0f;
            if (!s.isInfinite()) {
                Integer seen = this.maxSeen.get(effect);
                int maxDur = seen != null ? Math.max(seen, ticksLeft) : ticksLeft;
                if (maxDur > 0) {
                    frac = Math.max(0.0f, Math.min(1.0f, (float) ticksLeft / (float) maxDur));
                }
            }
            if (s.isInfinite()) {
                Render2D.rect(x + pad, barY, barW, 1.2f, 0.6f, MenuColors.rgba(120, 200, 255, 130));
            } else if (frac > 0.0f) {
                int color = frac > 0.35f ? MenuColors.rgba(120, 210, 140, 220)
                        : frac > 0.15f ? MenuColors.rgba(255, 200, 90, 225)
                        : MenuColors.rgba(255, 95, 95, 230);
                Render2D.rect(x + pad, barY, Math.max(1.0f, barW * frac), 1.2f, 0.6f, color);
            }
            ry += rowH;
        }
        Render2D.flush();
    }
}
