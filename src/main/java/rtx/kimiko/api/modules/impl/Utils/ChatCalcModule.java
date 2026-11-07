package rtx.kimiko.api.modules.impl.Utils;

import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import net.minecraft.text.Text;

/**
 * Chat Calc NEW: клавиша читает арифметическое выражение из буфера обмена,
 * считает его встроенным парсером (рекурсивный спуск) и кладёт обратно
 * в виде "выражение = результат".
 */
public class ChatCalcModule extends Module {
    private final BindSetting bind;
    private final BooleanSetting notifyErrors;
    private String source;
    private int pos;
    private boolean divByZero;
    private boolean lastDown;

    public ChatCalcModule() {
        super("Chat Calc NEW", "Клавиша считает арифметическое выражение из буфера обмена (+ - * / скобки).", Category.UTILS);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша вычисления выражения.").setKey(-1));
        this.notifyErrors = (BooleanSetting) this.register((Setting) new BooleanSetting("Сообщать об ошибке", "Писать в чат, если в буфере не выражение.", true));
        this.source = "";
        this.pos = 0;
        this.divByZero = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.evaluateClipboard();
        }
        this.lastDown = down;
    }

    private void evaluateClipboard() {
        String text;
        try {
            text = this.mc.keyboard.getClipboard();
        } catch (Throwable t) {
            text = "";
        }
        if (text == null) {
            text = "";
        }
        String expr = text.trim();
        boolean looksLikeMath = false;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                looksLikeMath = true;
                break;
            }
        }
        if (expr.isEmpty() || !looksLikeMath) {
            if (this.notifyErrors.getValue()) {
                this.chat("§7В буфере обмена нет выражения.");
            }
            return;
        }
        this.source = expr;
        this.pos = 0;
        this.divByZero = false;
        try {
            double value = this.parseExpression();
            if (this.pos < this.source.length()) {
                throw new IllegalStateException("лишние символы");
            }
            if (this.divByZero) {
                this.chat("§cОшибка: деление на ноль.");
                return;
            }
            String result = this.format(value);
            this.mc.keyboard.setClipboard(expr + " = " + result);
            this.chat("§a" + expr + " §f= §e" + result);
        } catch (Throwable t) {
            if (this.notifyErrors.getValue()) {
                this.chat("§cНе удалось разобрать выражение: §7" + expr);
            }
        }
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }

    private String format(double v) {
        if (Double.isNaN(v) || Double.isInfinite(v)) {
            return String.valueOf(v);
        }
        if (v == Math.rint(v) && Math.abs(v) < 1.0E15) {
            return String.valueOf((long) v);
        }
        String s = String.format(java.util.Locale.US, "%.6f", v);
        s = s.replaceAll("0+$", "").replaceAll("\\.$", "");
        return s;
    }

    // ——— рекурсивный спуск: expr -> term -> factor -> atom

    private double parseExpression() {
        double value = this.parseTerm();
        while (true) {
            this.skipSpaces();
            if (this.pos < this.source.length() && (this.peek() == '+' || this.peek() == '-')) {
                char op = this.next();
                double right = this.parseTerm();
                value = op == '+' ? value + right : value - right;
            } else {
                return value;
            }
        }
    }

    private double parseTerm() {
        double value = this.parseFactor();
        while (true) {
            this.skipSpaces();
            if (this.pos < this.source.length() && (this.peek() == '*' || this.peek() == '/')) {
                char op = this.next();
                double right = this.parseFactor();
                if (op == '*') {
                    value = value * right;
                } else {
                    if (right == 0.0) {
                        this.divByZero = true;
                        right = 1.0;
                    }
                    value = value / right;
                }
            } else {
                return value;
            }
        }
    }

    private double parseFactor() {
        this.skipSpaces();
        if (this.pos < this.source.length() && this.peek() == '-') {
            this.next();
            return -this.parseFactor();
        }
        if (this.pos < this.source.length() && this.peek() == '+') {
            this.next();
            return this.parseFactor();
        }
        return this.parseAtom();
    }

    private double parseAtom() {
        this.skipSpaces();
        if (this.pos >= this.source.length()) {
            throw new IllegalStateException("неожиданный конец");
        }
        if (this.peek() == '(') {
            this.next();
            double value = this.parseExpression();
            this.skipSpaces();
            if (this.pos >= this.source.length() || this.peek() != ')') {
                throw new IllegalStateException("нет закрывающей скобки");
            }
            this.next();
            return value;
        }
        int start = this.pos;
        while (this.pos < this.source.length()) {
            char c = this.peek();
            if ((c >= '0' && c <= '9') || c == '.') {
                this.pos++;
            } else {
                break;
            }
        }
        if (start == this.pos) {
            throw new IllegalStateException("ожидалось число");
        }
        String num = this.source.substring(start, this.pos);
        double v = Double.parseDouble(num);
        if (Double.isNaN(v) || Double.isInfinite(v)) {
            throw new IllegalStateException("плохое число");
        }
        return v;
    }

    private char peek() {
        return this.source.charAt(this.pos);
    }

    private char next() {
        return this.source.charAt(this.pos++);
    }

    private void skipSpaces() {
        while (this.pos < this.source.length() && this.source.charAt(this.pos) == ' ') {
            this.pos++;
        }
    }
}
