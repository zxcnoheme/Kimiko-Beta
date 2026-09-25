/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.lang.I18n;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001BB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u001b\u0010\u0012\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0013\u0010\u0013\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\u001b\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0010J\u0013\u0010\u0015\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u000eJ\u001b\u0010\u0016\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0010J\u0013\u0010\u0017\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u000eJ\u001b\u0010\u0018\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0010J\u0013\u0010\u001a\u001a\u00020\u0019H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001c\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0019H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u001f\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u001d\u0010!\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020\u0019H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b#\u0010\u001bJ\u001b\u0010$\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0019H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010\u001dJ\u0013\u0010%\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b%\u0010\u0007J\u001d\u0010&\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b&\u0010\u000bJ\u0013\u0010'\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b'\u0010\u0003J\u000f\u0010(\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b(\u0010\u0003J\u000f\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00102R\u0016\u00104\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00105R\u0016\u00107\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00105R\u0016\u00108\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00105R\u0016\u00109\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00105R\u0016\u0010:\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010;R\u0016\u0010?\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u00102R\u0016\u0010@\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010;R\u0016\u0010A\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010;\u00a8\u0006C"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuSettings;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "backgroundId", "()Ljava/lang/String;", "value", "", "setBackgroundId", "(Ljava/lang/String;)V", "", "blur", "()F", "setBlur", "(F)V", "dim", "setDim", "vignette", "setVignette", "grain", "setGrain", "parallax", "setParallax", "", "drift", "()Z", "setDrift", "(Z)V", "Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;", "atmosphere", "()Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;", "setAtmosphere", "(Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;)V", "intro", "setIntro", "lastTab", "setLastTab", "save", "load", "Ljava/nio/file/Path;", "file", "()Ljava/nio/file/Path;", "clamp", "(F)F", "Lcom/google/gson/Gson;", "GSON", "Lcom/google/gson/Gson;", "FILE", "Ljava/lang/String;", "backgroundIdValue", "blurValue", "F", "dimValue", "vignetteValue", "grainValue", "parallaxValue", "driftValue", "Z", "atmosphereValue", "Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;", "introValue", "lastTabValue", "loaded", "dirty", "Atmosphere", "rtx.kimiko:kimiko"})
public final class MenuSettings {
    @NotNull
    public static final MenuSettings INSTANCE = new MenuSettings();
    @NotNull
    private static final Gson GSON;
    @NotNull
    private static final String FILE = "mainmenu.json";
    @NotNull
    private static String backgroundIdValue;
    private static float blurValue;
    private static float dimValue;
    private static float vignetteValue;
    private static float grainValue;
    private static float parallaxValue;
    private static boolean driftValue;
    @NotNull
    private static Atmosphere atmosphereValue;
    private static boolean introValue;
    @NotNull
    private static String lastTabValue;
    private static boolean loaded;
    private static boolean dirty;

    private MenuSettings() {
    }

    @JvmStatic
    @NotNull
    public static final String backgroundId() {
        INSTANCE.load();
        return backgroundIdValue;
    }

    @JvmStatic
    public static final void setBackgroundId(@Nullable String value) {
        INSTANCE.load();
        if (value == null || Intrinsics.areEqual((Object)value, (Object)backgroundIdValue)) {
            return;
        }
        backgroundIdValue = value;
        dirty = true;
    }

    @JvmStatic
    public static final float blur() {
        INSTANCE.load();
        return blurValue;
    }

    @JvmStatic
    public static final void setBlur(float value) {
        INSTANCE.load();
        blurValue = INSTANCE.clamp(value);
        dirty = true;
    }

    @JvmStatic
    public static final float dim() {
        INSTANCE.load();
        return dimValue;
    }

    @JvmStatic
    public static final void setDim(float value) {
        INSTANCE.load();
        dimValue = INSTANCE.clamp(value);
        dirty = true;
    }

    @JvmStatic
    public static final float vignette() {
        INSTANCE.load();
        return vignetteValue;
    }

    @JvmStatic
    public static final void setVignette(float value) {
        INSTANCE.load();
        vignetteValue = INSTANCE.clamp(value);
        dirty = true;
    }

    @JvmStatic
    public static final float grain() {
        INSTANCE.load();
        return grainValue;
    }

    @JvmStatic
    public static final void setGrain(float value) {
        INSTANCE.load();
        grainValue = INSTANCE.clamp(value);
        dirty = true;
    }

    @JvmStatic
    public static final float parallax() {
        INSTANCE.load();
        return parallaxValue;
    }

    @JvmStatic
    public static final void setParallax(float value) {
        INSTANCE.load();
        parallaxValue = INSTANCE.clamp(value);
        dirty = true;
    }

    @JvmStatic
    public static final boolean drift() {
        INSTANCE.load();
        return driftValue;
    }

    @JvmStatic
    public static final void setDrift(boolean value) {
        INSTANCE.load();
        driftValue = value;
        dirty = true;
    }

    @JvmStatic
    @NotNull
    public static final Atmosphere atmosphere() {
        INSTANCE.load();
        return atmosphereValue;
    }

    @JvmStatic
    public static final void setAtmosphere(@Nullable Atmosphere value) {
        INSTANCE.load();
        if (value == null) {
            return;
        }
        atmosphereValue = value;
        dirty = true;
    }

    @JvmStatic
    public static final boolean intro() {
        INSTANCE.load();
        return introValue;
    }

    @JvmStatic
    public static final void setIntro(boolean value) {
        INSTANCE.load();
        introValue = value;
        dirty = true;
    }

    @JvmStatic
    @NotNull
    public static final String lastTab() {
        INSTANCE.load();
        return lastTabValue;
    }

    @JvmStatic
    public static final void setLastTab(@Nullable String value) {
        INSTANCE.load();
        if (value == null || Intrinsics.areEqual((Object)value, (Object)lastTabValue)) {
            return;
        }
        lastTabValue = value;
        dirty = true;
    }

    @JvmStatic
    public static final void save() {
        if (!dirty) {
            return;
        }
        dirty = false;
        JsonObject root = new JsonObject();
        root.addProperty("background", backgroundIdValue);
        root.addProperty("blur", (Number)Float.valueOf(blurValue));
        root.addProperty("dim", (Number)Float.valueOf(dimValue));
        root.addProperty("vignette", (Number)Float.valueOf(vignetteValue));
        root.addProperty("grain", (Number)Float.valueOf(grainValue));
        root.addProperty("parallax", (Number)Float.valueOf(parallaxValue));
        root.addProperty("drift", Boolean.valueOf(driftValue));
        root.addProperty("atmosphere", atmosphereValue.name());
        root.addProperty("intro", Boolean.valueOf(introValue));
        root.addProperty("lastTab", lastTabValue);
        String json = GSON.toJson((JsonElement)root);
        Thread thread = new Thread(() -> MenuSettings.save$lambda$0(json), "kimiko-menu-settings");
        thread.setDaemon(true);
        thread.start();
    }

    private final void load() {
        if (loaded) {
            return;
        }
        loaded = true;
        try {
            Path path = this.file();
            if (!Files.isRegularFile(path, new LinkOption[0])) {
                return;
            }
            JsonObject root = JsonParser.parseString((String)Files.readString(path, StandardCharsets.UTF_8)).getAsJsonObject();
            if (root.has("background")) {
                String string = root.get("background").getAsString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                backgroundIdValue = string;
            }
            if (root.has("blur")) {
                blurValue = this.clamp(root.get("blur").getAsFloat());
            }
            if (root.has("dim")) {
                dimValue = this.clamp(root.get("dim").getAsFloat());
            }
            if (root.has("vignette")) {
                vignetteValue = this.clamp(root.get("vignette").getAsFloat());
            }
            if (root.has("grain")) {
                grainValue = this.clamp(root.get("grain").getAsFloat());
            }
            if (root.has("parallax")) {
                parallaxValue = this.clamp(root.get("parallax").getAsFloat());
            }
            if (root.has("drift")) {
                driftValue = root.get("drift").getAsBoolean();
            }
            if (root.has("intro")) {
                introValue = root.get("intro").getAsBoolean();
            }
            if (root.has("lastTab")) {
                String string = root.get("lastTab").getAsString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                lastTabValue = string;
            }
            if (root.has("atmosphere")) {
                try {
                    String string = root.get("atmosphere").getAsString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                    atmosphereValue = Atmosphere.valueOf(string);
                }
                catch (Throwable throwable) {}
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private final Path file() {
        Path path = ConfigManager.Companion.systemConfigDirectory().resolve(FILE);
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    private final float clamp(float value) {
        return value < 0.0f ? 0.0f : (value > 1.0f ? 1.0f : value);
    }

    private static final void save$lambda$0(String $json) {
        try {
            Path path = INSTANCE.file();
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            Files.writeString(path, (CharSequence)$json, StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    static {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Intrinsics.checkNotNullExpressionValue((Object)gson, (String)"create(...)");
        GSON = gson;
        backgroundIdValue = "castle_night";
        blurValue = 0.58f;
        dimValue = 0.5f;
        vignetteValue = 0.55f;
        grainValue = 0.28f;
        parallaxValue = 0.5f;
        driftValue = true;
        atmosphereValue = Atmosphere.DUST;
        introValue = true;
        lastTabValue = "PLAY";
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;", "", "", "ru", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "label", "()Ljava/lang/String;", "Ljava/lang/String;", "NONE", "DUST", "RAIN", "SPARKS", "rtx.kimiko:kimiko"})
    public static enum Atmosphere {
        NONE("Нет"),
        DUST("Пыль"),
        RAIN("Дождь"),
        SPARKS("Искры");
@NotNull
        private final String ru;
        
        
        
        
        
        private Atmosphere(String ru) {
            this.ru = ru;
        }

        @NotNull
        public final String label() {
            return I18n.tr(this.ru);
        }

        

        

        @NotNull
        public static EnumEntries<Atmosphere> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

