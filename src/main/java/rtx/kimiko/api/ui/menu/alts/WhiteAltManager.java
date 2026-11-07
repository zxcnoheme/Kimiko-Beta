package rtx.kimiko.api.ui.menu.alts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.MinecraftClient;
import rtx.kimiko.utils.session.SessionChanger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WhiteAltManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static WhiteAltManager instance;

    private final List<WhiteAccount> accounts = new ArrayList<>();
    private String activeName;
    private boolean booted;

    private WhiteAltManager() {
    }

    public static WhiteAltManager get() {
        if (instance == null) {
            instance = new WhiteAltManager();
            instance.load();
        }
        return instance;
    }

    private static Path resolveFile() {
        try {
            return net.fabricmc.loader.api.FabricLoader.getInstance().getGameDir().resolve("kimiko/alts.json");
        } catch (Throwable ignored) {
            return null;
        }
    }

    public List<WhiteAccount> getAccounts() {
        return this.accounts;
    }

    public boolean exists(String name) {
        if (name == null) {
            return false;
        }
        for (WhiteAccount a : this.accounts) {
            if (a.name.equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public WhiteAccount add(String name) {
        if (name == null) {
            return null;
        }
        String trimmed = name.trim();
        if (trimmed.length() > 15 || !WhiteAccount.isValidName(trimmed) || this.exists(trimmed)) {
            return null;
        }
        WhiteAccount account = new WhiteAccount(trimmed);
        this.accounts.add(account);
        this.save();
        return account;
    }

    public String randomName(String base) {
        if (base == null) {
            base = "";
        }
        StringBuilder cleaned = new StringBuilder();
        for (char c : base.trim().toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '_') {
                cleaned.append(c);
            }
        }
        if (cleaned.length() > 11) {
            cleaned.setLength(11);
        }
        String prefix = cleaned.toString();
        String pool = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        for (int attempt = 0; attempt < 60; attempt++) {
            int maxLen = Math.max(1, Math.min(15 - prefix.length(), 11));
            int minLen = Math.min(4, maxLen);
            int n = minLen + r.nextInt(maxLen - minLen + 1);
            StringBuilder sb = new StringBuilder(prefix);
            for (int i = 0; i < n; i++) {
                sb.append(pool.charAt(r.nextInt(pool.length())));
            }
            String name = sb.toString();
            if (!name.isEmpty() && name.length() <= 15 && !this.exists(name)) {
                return name;
            }
        }
        String fallback = (prefix.isEmpty() ? "Player" : prefix) + r.nextInt(10000);
        return fallback.length() > 15 ? fallback.substring(0, 15) : fallback;
    }

    public void remove(WhiteAccount account) {
        if (this.accounts.remove(account)) {
            this.save();
        }
    }

    public void toggleFavorite(WhiteAccount account) {
        if (account == null) {
            return;
        }
        account.favorite = !account.favorite;
        this.save();
    }

    public List<WhiteAccount> getSorted() {
        List<WhiteAccount> sorted = new ArrayList<>();
        for (WhiteAccount a : this.accounts) {
            if (a.favorite) {
                sorted.add(a);
            }
        }
        for (WhiteAccount a : this.accounts) {
            if (!a.favorite) {
                sorted.add(a);
            }
        }
        return sorted;
    }

    public void login(WhiteAccount account) {
        if (account == null) {
            return;
        }
        SessionChanger.changeUsername(account.name);
        this.activeName = account.name;
        this.save();
    }

    public void bootstrap() {
        if (this.booted) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getSession() == null) {
            return;
        }
        this.booted = true;
        if (this.activeName == null) {
            return;
        }
        for (WhiteAccount a : this.accounts) {
            if (a.name.equalsIgnoreCase(this.activeName)) {
                SessionChanger.changeUsername(a.name);
                return;
            }
        }
    }

    public String currentName() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getSession() == null || mc.getSession().getUsername() == null) {
            return "Player";
        }
        return mc.getSession().getUsername();
    }

    public void save() {
        Path file = resolveFile();
        if (file == null) {
            return;
        }
        JsonObject root = new JsonObject();
        JsonArray arr = new JsonArray();
        for (WhiteAccount a : this.accounts) {
            JsonObject o = new JsonObject();
            o.addProperty("name", a.name);
            o.addProperty("type", a.type.name());
            o.addProperty("created", a.created);
            o.addProperty("favorite", a.favorite);
            arr.add(o);
        }
        root.add("accounts", arr);
        if (this.activeName != null) {
            root.addProperty("active", this.activeName);
        }
        try {
            Files.createDirectories(file.getParent());
            try (Writer writer = new OutputStreamWriter(new FileOutputStream(file.toFile()), StandardCharsets.UTF_8)) {
                GSON.toJson(root, writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        this.accounts.clear();
        Path file = resolveFile();
        if (file == null || !Files.exists(file)) {
            return;
        }
        try (Reader reader = new InputStreamReader(new FileInputStream(file.toFile()), StandardCharsets.UTF_8)) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            if (root.has("active")) {
                String active = root.get("active").getAsString();
                this.activeName = WhiteAccount.isValidName(active) ? active.trim() : null;
            }
            if (!root.has("accounts")) {
                return;
            }
            for (JsonElement el : root.getAsJsonArray("accounts")) {
                JsonObject o = el.getAsJsonObject();
                String name = o.has("name") ? o.get("name").getAsString() : null;
                if (!WhiteAccount.isValidName(name) || this.exists(name)) {
                    continue;
                }
                WhiteAccount.Type type = WhiteAccount.Type.OFFLINE;
                if (o.has("type")) {
                    try {
                        type = WhiteAccount.Type.valueOf(o.get("type").getAsString());
                    } catch (IllegalArgumentException ignored) {
                    }
                }
                long created = o.has("created") ? o.get("created").getAsLong() : System.currentTimeMillis();
                WhiteAccount acc = new WhiteAccount(name.trim(), type, created);
                if (o.has("favorite")) {
                    acc.favorite = o.get("favorite").getAsBoolean();
                }
                this.accounts.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
