package rtx.kimiko.api.ui.menu.alts;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class WhiteAccount {
    public enum Type {
        OFFLINE
    }

    public String name;
    public Type type;
    public long created;
    public boolean favorite;

    public WhiteAccount(String name, Type type, long created) {
        this.name = sanitize(name);
        this.type = type;
        this.created = created;
    }

    public WhiteAccount(String name) {
        this(name, Type.OFFLINE, System.currentTimeMillis());
    }

    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        String n = name.trim();
        if (n.length() < 3 || n.length() > 16) {
            return false;
        }
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if (!(c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }
        return true;
    }

    public static String sanitize(String name) {
        if (name == null) {
            return "Player";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : name.trim().toCharArray()) {
            if (c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                sb.append(c);
            }
            if (sb.length() >= 16) {
                break;
            }
        }
        if (sb.length() == 0) {
            return "Player";
        }
        return sb.toString();
    }

    public UUID offlineUuid() {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.name).getBytes(StandardCharsets.UTF_8));
    }
}
