/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.util.Util
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import java.lang.invoke.CallSite;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.ui.mainmenu.MenuBackground;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\t\u0010\bJ\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0011H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0013\u0010\u0018\u001a\u00020\u0011H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0018\u0010\u0003J\u000f\u0010\u0019\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0003J\u000f\u0010\u001a\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u0017\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0013J\u000f\u0010\u001c\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\"\u0010#R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R$\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\u00050-j\b\u0012\u0004\u0012\u00020\u0005`.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010)R\u0016\u00102\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103\u00a8\u00064"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuBackgrounds;", "", "<init>", "()V", "", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "Lkotlin/jvm/JvmStatic;", "all", "()Ljava/util/List;", "user", "selected", "()Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "", "id", "byId", "(Ljava/lang/String;)Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "background", "", "select", "(Lrtx/kimiko/api/ui/mainmenu/MenuBackground;)V", "Ljava/nio/file/Path;", "folder", "()Ljava/nio/file/Path;", "openFolder", "rescan", "ensure", "registerBuiltIn", "add", "scan", "path", "", "supported", "(Ljava/nio/file/Path;)Z", "name", "stripExtension", "(Ljava/lang/String;)Ljava/lang/String;", "", "EXTENSIONS", "[Ljava/lang/String;", "", "RESCAN_INTERVAL_MS", "J", "Ljava/util/LinkedHashMap;", "REGISTRY", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "BUILT_IN", "Ljava/util/ArrayList;", "lastScan", "initialised", "Z", "rtx.kimiko:kimiko"})
public final class MenuBackgrounds {
    @NotNull
    public static final MenuBackgrounds INSTANCE = new MenuBackgrounds();
    @NotNull
    private static final String[] EXTENSIONS;
    private static final long RESCAN_INTERVAL_MS = 2500L;
    @NotNull
    private static final LinkedHashMap<String, MenuBackground> REGISTRY;
    @NotNull
    private static final ArrayList<MenuBackground> BUILT_IN;
    private static long lastScan;
    private static boolean initialised;

    private MenuBackgrounds() {
    }

    @JvmStatic
    @NotNull
    public static final List<MenuBackground> all() {
        INSTANCE.ensure();
        return new ArrayList<MenuBackground>(REGISTRY.values());
    }

    @JvmStatic
    @NotNull
    public static final List<MenuBackground> user() {
        INSTANCE.ensure();
        ArrayList<MenuBackground> list = new ArrayList<MenuBackground>();
        Iterator<MenuBackground> iterator = REGISTRY.values().iterator();
        while (iterator.hasNext()) {
            MenuBackground background = (MenuBackground) (iterator.next());
            if (background.builtIn()) continue;
            list.add(background);
        }
        return list;
    }

    @JvmStatic
    @Nullable
    public static final MenuBackground selected() {
        INSTANCE.ensure();
        MenuBackground background = REGISTRY.get(MenuSettings.backgroundId());
        if (background != null) {
            return background;
        }
        return BUILT_IN.isEmpty() ? null : BUILT_IN.get(0);
    }

    @JvmStatic
    @Nullable
    public static final MenuBackground byId(@Nullable String id) {
        INSTANCE.ensure();
        return (MenuBackground)((Map)REGISTRY).get(id);
    }

    @JvmStatic
    public static final void select(@Nullable MenuBackground background) {
        if (background == null) {
            return;
        }
        MenuSettings.setBackgroundId(background.id());
        MenuSettings.save();
    }

    @JvmStatic
    @NotNull
    public static final Path folder() {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("backgrounds");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    @JvmStatic
    public static final void openFolder() {
        try {
            Path path = MenuBackgrounds.folder();
            Files.createDirectories(path, new FileAttribute[0]);
            Util.getOperatingSystem().open(path);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void rescan() {
        lastScan = 0L;
        INSTANCE.scan();
    }

    private final void ensure() {
        if (!initialised) {
            initialised = true;
            this.registerBuiltIn();
        }
        this.scan();
    }

    private final void registerBuiltIn() {
        this.add(new MenuBackground("castle_night", "Ночной замок", MenuBackground.Kind.IMAGE, "kimiko:images/mainmenu/background.png", true));
        this.add(new MenuBackground("castle_storm", "Гроза над замком", MenuBackground.Kind.IMAGE, "kimiko:images/mainmenu/singleplayer.png", true));
        this.add(new MenuBackground("castle_gates", "Врата", MenuBackground.Kind.IMAGE, "kimiko:images/mainmenu/multiplayer.png", true));
        this.add(new MenuBackground("aurora", "Аврора", MenuBackground.Kind.AURORA, "", true));
        this.add(new MenuBackground("gradient", "Глубина", MenuBackground.Kind.GRADIENT, "", true));
    }

    private final void add(MenuBackground background) {
        ((Map)REGISTRY).put(background.id(), background);
        if (background.builtIn()) {
            BUILT_IN.add(background);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void scan() {
        long now = System.currentTimeMillis();
        if (now - lastScan < 2500L) {
            return;
        }
        lastScan = now;
        Path folder = MenuBackgrounds.folder();
        List<Path> files = new ArrayList<>();
        if (Files.isDirectory(folder, new LinkOption[0])) {
            try (Stream<Path> stream = Files.list(folder)) {
                stream.filter(Files::isRegularFile)
                      .filter(this::supported)
                      .sorted()
                      .forEach(files::add);
            }
            catch (Throwable ignored) {
                return;
            }
        }
        List<String> present = new ArrayList<>();
        for (Path file : files) {
            String id = "user:" + file.getFileName();
            present.add(id);
            if (REGISTRY.containsKey(id)) continue;
            String name = this.stripExtension(file.getFileName().toString());
            REGISTRY.put(id, new MenuBackground(id, name, MenuBackground.Kind.USER, file.toAbsolutePath().toString(), false));
        }
        REGISTRY.entrySet().removeIf(it -> !it.getValue().builtIn() && !present.contains(it.getKey()));
    }

    private final boolean supported(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        for (String extension : EXTENSIONS) {
            if (name.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private final String stripExtension(String name) {
        int dot = name.lastIndexOf('.');
        if (dot <= 0) {
            return name;
        }
        return name.substring(0, dot);
    }

    static {
        String[] stringArray = new String[]{".png", ".jpg", ".jpeg"};
        EXTENSIONS = stringArray;
        REGISTRY = new LinkedHashMap();
        BUILT_IN = new ArrayList();
    }
}

