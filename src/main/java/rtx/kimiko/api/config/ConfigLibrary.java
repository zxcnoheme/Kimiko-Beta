/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.client.Keyboard
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.config.share.ConfigCodec;
import rtx.kimiko.api.config.share.ConfigIdentity;
import rtx.kimiko.api.config.share.ConfigShareClient;
import rtx.kimiko.api.config.share.SharedConfig;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 K2\u00020\u0001:\u0003LMKB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\tJ\r\u0010\u000e\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\fJ\r\u0010\u000f\u001a\u00020\n\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0004\b\u0015\u0010\u0013J\r\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0003J\r\u0010\u0018\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0003J\r\u0010\u0019\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0019\u0010\u0003J\r\u0010\u001a\u001a\u00020\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u0017\u0010\u001c\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u001f\u0010 J\u0019\u0010\"\u001a\u0004\u0018\u00010\u00112\b\u0010!\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b$\u0010\u001dJ!\u0010%\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u00112\b\u0010!\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b'\u0010\u001dJ\u0017\u0010(\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b*\u0010)J\u0017\u0010+\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b+\u0010)J\u0017\u0010,\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b,\u0010)J\u0017\u0010.\u001a\u00020\u00162\b\u0010-\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b.\u0010 J\u001f\u00102\u001a\u0002012\u0006\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b4\u0010\u0003J\u000f\u00105\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b5\u0010\u0003R0\u00108\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n06j\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`78\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R0\u0010;\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020:06j\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020:`78\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u00109R$\u0010>\u001a\u0012\u0012\u0004\u0012\u00020\n0<j\b\u0012\u0004\u0012\u00020\n`=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R$\u0010B\u001a\u0012\u0012\u0004\u0012\u00020\u00110@j\b\u0012\u0004\u0012\u00020\u0011`A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR$\u0010D\u001a\u0012\u0012\u0004\u0012\u00020\u00110@j\b\u0012\u0004\u0012\u00020\u0011`A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010CR$\u0010E\u001a\u0012\u0012\u0004\u0012\u00020\u00110@j\b\u0012\u0004\u0012\u00020\u0011`A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010CR\u0016\u0010\u000f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010FR\u0016\u0010\u000e\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010FR\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010GR\u0016\u0010H\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010GR\u0016\u0010I\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010JR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010F\u00a8\u0006N"}, d2={"Lrtx/kimiko/api/config/ConfigLibrary;", "", "<init>", "()V", "", "version", "()J", "", "remoteLoading", "()Z", "", "status", "()Ljava/lang/String;", "savingBlocked", "lockedCode", "activeKey", "", "Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "localEntries", "()Ljava/util/List;", "premiumEntries", "remoteEntries", "", "open", "refresh", "reloadLocal", "refreshRemote", "entry", "apply", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;)Z", "profileName", "onLocalApplied", "(Ljava/lang/String;)V", "rawName", "createFromCurrent", "(Ljava/lang/String;)Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "saveInto", "rename", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;Ljava/lang/String;)Z", "delete", "publish", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;)V", "unpublish", "download", "copyCode", "rawCode", "importByCode", "name", "modified", "", "countCached", "(Ljava/lang/String;J)I", "ensureStore", "persist", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "links", "Ljava/util/HashMap;", "", "counts", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "cloudNames", "Ljava/util/HashSet;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "local", "Ljava/util/ArrayList;", "premium", "remote", "Ljava/lang/String;", "Z", "storeLoaded", "lastRemoteRefresh", "J", "Companion", "Kind", "Entry", "rtx.kimiko:kimiko"})
public final class ConfigLibrary {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HashMap<String, String> links = new HashMap();
    @NotNull
    private final HashMap<String, long[]> counts = new HashMap();
    @NotNull
    private final HashSet<String> cloudNames = new HashSet();
    @NotNull
    private final ArrayList<Entry> local = new ArrayList();
    @NotNull
    private final ArrayList<Entry> premium = new ArrayList();
    @NotNull
    private final ArrayList<Entry> remote = new ArrayList();
    @NotNull
    private String activeKey = "";
    @NotNull
    private String lockedCode = "";
    private boolean remoteLoading;
    private boolean storeLoaded;
    private long lastRemoteRefresh;
    private long version;
    @NotNull
    private String status = "";
    @NotNull
    private static final ConfigLibrary INSTANCE = new ConfigLibrary();
    @NotNull
    private static final String STORE = "configshare";
    private static final long REFRESH_INTERVAL_MS = 30000L;

    private ConfigLibrary() {
    }

    public final long version() {
        return this.version;
    }

    public final boolean remoteLoading() {
        return this.remoteLoading;
    }

    @NotNull
    public final String status() {
        return this.status;
    }

    public final boolean savingBlocked() {
        return ((CharSequence)this.lockedCode()).length() > 0;
    }

    @NotNull
    public final String lockedCode() {
        this.ensureStore();
        return this.lockedCode;
    }

    @NotNull
    public final String activeKey() {
        this.ensureStore();
        return this.activeKey;
    }

    @NotNull
    public final List<Entry> localEntries() {
        return this.local;
    }

    @NotNull
    public final List<Entry> premiumEntries() {
        return this.premium;
    }

    @NotNull
    public final List<Entry> remoteEntries() {
        return this.remote;
    }

    public final void open() {
        this.reloadLocal();
        if (System.currentTimeMillis() - this.lastRemoteRefresh > 30000L) {
            this.refreshRemote();
        }
    }

    public final void refresh() {
        this.reloadLocal();
        this.refreshRemote();
    }

    public final void reloadLocal() {
        this.ensureStore();
        this.local.clear();
        for (String name : ConfigManager.Companion.listProfiles()) {
            Entry entry = new Entry(Kind.LOCAL);
            entry.setName$rtx_kimiko_kimiko(name);
            entry.setLocalName$rtx_kimiko_kimiko(name);
            String string = name;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
            entry.setCode$rtx_kimiko_kimiko(this.links.get(string2));
            entry.setOwnerName$rtx_kimiko_kimiko(ConfigIdentity.username());
            entry.setOwnerUid$rtx_kimiko_kimiko(ConfigIdentity.uid());
            entry.setUpdatedAt$rtx_kimiko_kimiko(ConfigManager.Companion.profileModified(name));
            entry.setModules$rtx_kimiko_kimiko(this.countCached(name, entry.getUpdatedAt$rtx_kimiko_kimiko()));
            string = name;
            Locale locale2 = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale2, (String)"ROOT");
            String string3 = string.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
            entry.setFromCloud$rtx_kimiko_kimiko(this.cloudNames.contains(string3));
            this.local.add(entry);
        }
        long l = this.version;
        this.version = l + 1L;
    }

    public final void refreshRemote() {
        if (this.remoteLoading) {
            return;
        }
        if (!ConfigShareClient.INSTANCE.identified()) {
            this.status = "";
            this.premium.clear();
            this.remote.clear();
            long l = this.version;
            this.version = l + 1L;
            return;
        }
        this.remoteLoading = true;
        this.lastRemoteRefresh = System.currentTimeMillis();
        ConfigShareClient.INSTANCE.owned((arg_0, arg_1) -> ConfigLibrary.refreshRemote$lambda$0(this, arg_0, arg_1));
        ConfigShareClient.INSTANCE.mine((arg_0, arg_1) -> ConfigLibrary.refreshRemote$lambda$1(this, arg_0, arg_1));
    }

    public final boolean apply(@Nullable Entry entry) {
        if (entry == null) {
            return false;
        }
        if (entry.premium()) {
            JsonObject root = ConfigCodec.decode(entry.getPayload$rtx_kimiko_kimiko());
            if (root == null) {
                Notifications.push(I18n.tr("Конфиги"), I18n.tr("Не удалось распаковать конфиг с сайта"), 2200L);
                return false;
            }
            ConfigManager.Companion.applyRootTransient(root);
            this.ensureStore();
            String string = entry.getCode$rtx_kimiko_kimiko();
            if (string == null) {
                string = "";
            }
            this.lockedCode = string;
            this.activeKey = entry.key();
            this.persist();
            long l = this.version;
            this.version = l + 1L;
            Object[] objectArray = new Object[]{entry.getName$rtx_kimiko_kimiko()};
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Загружен «%s» с сайта. Сохранение отключено", objectArray), 2600L);
            return true;
        }
        String string = entry.getLocalName$rtx_kimiko_kimiko();
        Intrinsics.checkNotNull((Object)string);
        ConfigManager.Companion.loadProfile(string);
        Object[] objectArray = new Object[]{entry.getName$rtx_kimiko_kimiko()};
        Notifications.push(I18n.tr("Конфиги"), I18n.tr("Загружен «%s»", objectArray), 1800L);
        return true;
    }

    public final void onLocalApplied(@Nullable String profileName) {
        this.ensureStore();
        this.lockedCode = "";
        String string = profileName;
        this.activeKey = string == null ? "" : "local:" + string;
        this.persist();
        long l = this.version;
        this.version = l + 1L;
    }

    @Nullable
    public final Entry createFromCurrent(@Nullable String rawName) {
        if (this.savingBlocked()) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Активен конфиг с сайта — сохранение запрещено"), 2400L);
            return null;
        }
        String name = ConfigLibrary.Companion.sanitize(rawName);
        if (((CharSequence)name).length() == 0) {
            name = ConfigManager.Companion.nextProfileName();
        }
        if (ConfigManager.Companion.profileExists(name)) {
            Object[] objectArray = new Object[]{name};
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Конфиг «%s» уже есть", objectArray), 2000L);
            return null;
        }
        String created = name;
        ConfigManager.Companion.writeProfile(created, ConfigManager.Companion.currentRoot(), () -> ConfigLibrary.createFromCurrent$lambda$0(this, created));
        return null;
    }

    public final boolean saveInto(@Nullable Entry entry) {
        if (entry == null || entry.premium()) {
            return false;
        }
        if (this.savingBlocked()) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Активен конфиг с сайта — сохранение запрещено"), 2400L);
            return false;
        }
        JsonObject root = ConfigManager.Companion.currentRoot();
        ConfigManager.Companion.writeProfile(entry.getLocalName$rtx_kimiko_kimiko(), root);
        entry.setUpdatedAt$rtx_kimiko_kimiko(System.currentTimeMillis());
        entry.setModules$rtx_kimiko_kimiko(ConfigManager.Companion.rootEnabledCount(root));
        Map map = this.counts;
        String string = entry.getLocalName$rtx_kimiko_kimiko();
        Intrinsics.checkNotNull((Object)string);
        String string2 = string;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
        string2 = string3;
        long[] lArray = new long[]{entry.getUpdatedAt$rtx_kimiko_kimiko(), entry.getModules$rtx_kimiko_kimiko()};
        map.put(string2, lArray);
        long l = this.version;
        this.version = l + 1L;
        Object[] objectArray = new Object[]{entry.getName$rtx_kimiko_kimiko()};
        Notifications.push(I18n.tr("Конфиги"), I18n.tr("Сохранено в «%s»", objectArray), 1600L);
        return true;
    }

    public final boolean rename(@Nullable Entry entry, @Nullable String rawName) {
        if (entry == null || entry.premium()) {
            return false;
        }
        String name = ConfigLibrary.Companion.sanitize(rawName);
        if (((CharSequence)name).length() == 0 || StringsKt.equals((String)name, (String)entry.getLocalName$rtx_kimiko_kimiko(), (boolean)true)) {
            return false;
        }
        if (!ConfigManager.Companion.renameProfile(entry.getLocalName$rtx_kimiko_kimiko(), name)) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Имя занято"), 1800L);
            return false;
        }
        this.ensureStore();
        String oldName = entry.getLocalName$rtx_kimiko_kimiko();
        if (oldName != null) {
            String oldKey = oldName.toLowerCase(Locale.ROOT);
            String code = this.links.remove(oldKey);
            if (code != null) {
                this.links.put(name.toLowerCase(Locale.ROOT), code);
            }
            if (this.cloudNames.remove(oldKey)) {
                this.cloudNames.add(name.toLowerCase(Locale.ROOT));
            }
        }
        if (Intrinsics.areEqual((Object)this.activeKey, (Object)entry.key())) {
            this.activeKey = "local:" + name;
        }
        this.persist();
        this.reloadLocal();
        return true;
    }

    public final boolean delete(@Nullable Entry entry) {
        if (entry == null || entry.premium()) {
            return false;
        }
        String string = entry.getLocalName$rtx_kimiko_kimiko();
        Intrinsics.checkNotNull((Object)string);
        ConfigManager.Companion.deleteProfile(string);
        this.ensureStore();
        String localName = entry.getLocalName$rtx_kimiko_kimiko();
        String code = null;
        if (localName != null) {
            String key = localName.toLowerCase(Locale.ROOT);
            this.cloudNames.remove(key);
            code = this.links.remove(key);
        }
        if (Intrinsics.areEqual((Object)this.activeKey, (Object)entry.key())) {
            this.activeKey = "";
        }
        this.persist();
        this.reloadLocal();
        if (code != null) {
            int n = ((Collection)this.remote).size() + -1;
            if (0 <= n) {
                do {
                    int i;
                    if (!Intrinsics.areEqual((Object)code, (Object)this.remote.get(i = n--).getCode$rtx_kimiko_kimiko())) continue;
                    this.remote.remove(i);
                } while (0 <= n);
            }
            ConfigShareClient.INSTANCE.delete(code, (arg_0, arg_1) -> ConfigLibrary.delete$lambda$0(this, arg_0, arg_1));
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Удалён «%s» — и из облака тоже", entry.getName$rtx_kimiko_kimiko()), 2200L);
            return true;
        }
        Notifications.push(I18n.tr("Конфиги"), I18n.tr("Удалён «%s»", entry.getName$rtx_kimiko_kimiko()), 1600L);
        return true;
    }

    public final void publish(@Nullable Entry entry) {
        JsonObject root;
        boolean live;
        if (entry == null || entry.premium()) {
            return;
        }
        live = Intrinsics.areEqual((Object)this.activeKey(), (Object)entry.key()) && !this.savingBlocked();
        if (live) {
            root = ConfigManager.Companion.currentRoot();
        } else {
            String string = entry.getLocalName$rtx_kimiko_kimiko();
            Intrinsics.checkNotNull((Object)string);
            root = ConfigManager.Companion.readProfileRoot(string);
        }
        if (root == null) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Конфиг не найден на диске"), 1800L);
            return;
        }
        if (live) {
            ConfigManager.Companion.writeProfile(entry.getLocalName$rtx_kimiko_kimiko(), root);
            entry.setUpdatedAt$rtx_kimiko_kimiko(System.currentTimeMillis());
            Map map = this.counts;
            String string = entry.getLocalName$rtx_kimiko_kimiko();
            Intrinsics.checkNotNull((Object)string);
            String string2 = string;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string3 = string2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
            string2 = string3;
            long[] lArray = new long[]{entry.getUpdatedAt$rtx_kimiko_kimiko(), ConfigManager.Companion.rootEnabledCount(root)};
            map.put(string2, lArray);
        }
        int modules = ConfigManager.Companion.rootEnabledCount(root);
        if (entry.published()) {
            ConfigShareClient.INSTANCE.update(entry.getCode$rtx_kimiko_kimiko(), entry.getName$rtx_kimiko_kimiko(), root, modules, (arg_0, arg_1) -> ConfigLibrary.publish$lambda$0(entry, this, arg_0, arg_1));
            return;
        }
        ConfigShareClient.INSTANCE.publish(entry.getName$rtx_kimiko_kimiko(), root, modules, (arg_0, arg_1) -> ConfigLibrary.publish$lambda$1(entry, this, arg_0, arg_1));
    }

    public final void unpublish(@Nullable Entry entry) {
        if (entry == null || entry.premium() || !entry.published()) {
            return;
        }
        String code = entry.getCode$rtx_kimiko_kimiko();
        ConfigShareClient.INSTANCE.delete(code, (arg_0, arg_1) -> ConfigLibrary.unpublish$lambda$0(entry, this, arg_0, arg_1));
    }

    public final void download(@Nullable Entry entry) {
        if (entry == null || entry.kind() != Kind.REMOTE || !entry.published()) {
            return;
        }
        ConfigShareClient.INSTANCE.fetch(entry.getCode$rtx_kimiko_kimiko(), (arg_0, arg_1) -> ConfigLibrary.download$lambda$0(this, entry, arg_0, arg_1));
    }

    public final void copyCode(@Nullable Entry entry) {
        if (entry == null || !entry.published()) {
            return;
        }
        ConfigLibrary.Companion.copyToClipboard(entry.getCode$rtx_kimiko_kimiko());
        Object[] objectArray = new Object[]{entry.getCode$rtx_kimiko_kimiko()};
        Notifications.push(I18n.tr("Конфиги"), I18n.tr("Код %s скопирован", objectArray), 1800L);
    }

    public final void importByCode(@Nullable String rawCode) {
        String code = ConfigCodec.normalizeCode(rawCode);
        if (((CharSequence)code).length() == 0) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Введите код"), 1600L);
            return;
        }
        ConfigShareClient.INSTANCE.fetch(code, (arg_0, arg_1) -> ConfigLibrary.importByCode$lambda$0(this, arg_0, arg_1));
    }

    private final int countCached(String name, long modified) {
        String string = name;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String key = string2;
        long[] cached = this.counts.get(key);
        if (cached != null && cached[0] == modified) {
            return (int)cached[1];
        }
        int count = ConfigManager.Companion.profileEnabledCount(name);
        Map map = this.counts;
        long[] lArray = new long[]{modified, count};
        map.put(key, lArray);
        return count;
    }

    private final void ensureStore() {
        if (this.storeLoaded) {
            return;
        }
        this.storeLoaded = true;
        try {
            JsonObject root = RepositoryStorage.readObject(STORE);
            if (root.has("links") && root.get("links").isJsonObject()) {
                JsonObject stored = root.getAsJsonObject("links");
                for (String key : stored.keySet()) {
                    if (!stored.get(key).isJsonPrimitive()) continue;
                    ((Map)this.links).put(key, stored.get(key).getAsString());
                }
            }
            if (root.has("cloud") && root.get("cloud").isJsonArray()) {
                Iterator iterator = root.getAsJsonArray("cloud").iterator();
                Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
                Iterator iterator2 = iterator;
                while (iterator2.hasNext()) {
                    JsonElement element = (JsonElement)iterator2.next();
                    if (!element.isJsonPrimitive()) continue;
                    this.cloudNames.add(element.getAsString());
                }
            }
            if (root.has("active") && root.get("active").isJsonPrimitive()) {
                String string = root.get("active").getAsString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                this.activeKey = string;
            }
            if (root.has("locked") && root.get("locked").isJsonPrimitive()) {
                String string = root.get("locked").getAsString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                this.lockedCode = string;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final void persist() {
        try {
            JsonObject root = new JsonObject();
            JsonObject stored = new JsonObject();
            Iterator<Map.Entry<String, String>> iterator = this.links.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> link = iterator.next();
                stored.addProperty(link.getKey(), link.getValue());
            }
            root.add("links", (JsonElement)stored);
            JsonArray cloud = new JsonArray();
            Iterator<String> iterator2 = this.cloudNames.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator2, (String)"iterator(...)");
            Iterator<String> iterator3 = iterator2;
            while (iterator3.hasNext()) {
                String name = (String) (iterator3.next());
                cloud.add(name);
            }
            root.add("cloud", (JsonElement)cloud);
            root.addProperty("active", this.activeKey);
            root.addProperty("locked", this.lockedCode);
            RepositoryStorage.write(STORE, root);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static final void refreshRemote$lambda$0(ConfigLibrary this$0, List<SharedConfig> configs, String error) {
        this$0.remoteLoading = false;
        if (error != null) {
            this$0.status = error;
            this$0.version++;
        } else {
            this$0.status = "";
            this$0.premium.clear();
            if (configs != null) {
                for (SharedConfig config : configs) {
                    Entry entry = new Entry(Kind.PREMIUM);
                    entry.setName$rtx_kimiko_kimiko(config.name());
                    entry.setCode$rtx_kimiko_kimiko(config.code());
                    entry.setOwnerName$rtx_kimiko_kimiko(config.ownerName());
                    entry.setOwnerAvatar$rtx_kimiko_kimiko(config.ownerAvatar());
                    entry.setOwnerUid$rtx_kimiko_kimiko(config.ownerUid());
                    entry.setUpdatedAt$rtx_kimiko_kimiko(config.updatedAt());
                    entry.setModules$rtx_kimiko_kimiko(config.modules());
                    entry.setPayload$rtx_kimiko_kimiko(config.payload());
                    entry.setDownloads$rtx_kimiko_kimiko(config.downloads());
                    this$0.premium.add(entry);
                }
            }
            this$0.version++;
        }
    }

    private static final void refreshRemote$lambda$1(ConfigLibrary this$0, List<SharedConfig> configs, String error) {
        if (error == null && configs != null) {
            Map<String, SharedConfig> byCode = new HashMap<String, SharedConfig>();
            for (SharedConfig cfg : configs) {
                byCode.put(cfg.code(), cfg);
            }
            boolean changed = false;
            for (Map.Entry<String, String> link : new ArrayList<Map.Entry<String, String>>(this$0.links.entrySet())) {
                if (!byCode.containsKey(link.getValue())) {
                    this$0.links.remove(link.getKey());
                    changed = true;
                }
            }
            HashSet<String> linked = new HashSet<String>(this$0.links.values());
            for (Entry entry : this$0.local) {
                SharedConfig config3 = entry.getCode$rtx_kimiko_kimiko() == null ? null : byCode.get(entry.getCode$rtx_kimiko_kimiko());
                if (config3 != null) {
                    entry.setDownloads$rtx_kimiko_kimiko(config3.downloads());
                }
            }
            this$0.remote.clear();
            for (SharedConfig config4 : configs) {
                if (linked.contains(config4.code())) continue;
                Entry entry = new Entry(Kind.REMOTE);
                entry.setName$rtx_kimiko_kimiko(config4.name());
                entry.setCode$rtx_kimiko_kimiko(config4.code());
                entry.setOwnerName$rtx_kimiko_kimiko(config4.ownerName());
                entry.setOwnerAvatar$rtx_kimiko_kimiko(config4.ownerAvatar());
                entry.setOwnerUid$rtx_kimiko_kimiko(config4.ownerUid());
                entry.setUpdatedAt$rtx_kimiko_kimiko(config4.updatedAt());
                entry.setModules$rtx_kimiko_kimiko(config4.modules());
                entry.setDownloads$rtx_kimiko_kimiko(config4.downloads());
                this$0.remote.add(entry);
            }
            if (changed) {
                this$0.persist();
            }
            this$0.version++;
        }
    }

    private static final void createFromCurrent$lambda$0(ConfigLibrary this$0, String $created) {
        this$0.reloadLocal();
        this$0.onLocalApplied($created);
        Object[] objectArray = new Object[]{$created};
        Notifications.push(I18n.tr("Конфиги"), I18n.tr("Создан конфиг «%s»", objectArray), 1800L);
    }

    private static final void delete$lambda$0(ConfigLibrary this$0, Boolean bl, String string) {
        long l = this$0.version;
        this$0.version = l + 1L;
    }

    private static final void publish$lambda$0(Entry $entry, ConfigLibrary this$0, SharedConfig config, String error) {
        if (error != null) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr(error), 2200L);
        } else {
            SharedConfig sharedConfig = config;
            Intrinsics.checkNotNull((Object)sharedConfig);
            $entry.setUpdatedAt$rtx_kimiko_kimiko(sharedConfig.updatedAt());
            $entry.setModules$rtx_kimiko_kimiko(config.modules());
            long l = this$0.version;
            this$0.version = l + 1L;
            Object[] objectArray = new Object[]{$entry.getCode$rtx_kimiko_kimiko()};
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Обновлён на сервере: %s", objectArray), 2000L);
        }
    }

    private static final void publish$lambda$1(Entry $entry, ConfigLibrary this$0, SharedConfig config, String error) {
        if (error != null) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr(error), 2200L);
        } else {
            SharedConfig sharedConfig = config;
            Intrinsics.checkNotNull((Object)sharedConfig);
            $entry.setCode$rtx_kimiko_kimiko(sharedConfig.code());
            $entry.setUpdatedAt$rtx_kimiko_kimiko(config.updatedAt());
            this$0.ensureStore();
            Map map = this$0.links;
            String string = $entry.getLocalName$rtx_kimiko_kimiko();
            Intrinsics.checkNotNull((Object)string);
            String string2 = string;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string3 = string2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
            string2 = string3;
            String string4 = config.code();
            map.put(string2, string4);
            this$0.persist();
            long l = this$0.version;
            this$0.version = l + 1L;
            ConfigLibrary.Companion.copyToClipboard(config.code());
            Object[] objectArray = new Object[]{config.code()};
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Код %s скопирован", objectArray), 2400L);
        }
    }

    private static final void unpublish$lambda$0(Entry $entry, ConfigLibrary this$0, Boolean bl, String error) {
        if (error != null) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr(error), 2200L);
        } else {
            $entry.setCode$rtx_kimiko_kimiko(null);
            this$0.ensureStore();
            if ($entry.getLocalName$rtx_kimiko_kimiko() != null) {
                HashMap<String, String> hashMap = this$0.links;
                String string = $entry.getLocalName$rtx_kimiko_kimiko();
                Intrinsics.checkNotNull((Object)string);
                String string2 = string;
                Locale locale = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                String string3 = string2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
                hashMap.remove(string3);
            }
            this$0.remote.remove($entry);
            this$0.persist();
            long l = this$0.version;
            this$0.version = l + 1L;
            Notifications.push(I18n.tr("Конфиги"), I18n.tr("Удалён из облака"), 1800L);
        }
    }

    private static final void download$lambda$0$0(ConfigLibrary this$0, Entry $entry, String $name) {
        this$0.remote.remove($entry);
        this$0.reloadLocal();
        Object[] objectArray = new Object[]{$name};
        Notifications.push(I18n.tr("Конфиги"), I18n.tr("Загружен «%s» из облака", objectArray), 2200L);
    }

    private static final void download$lambda$0(ConfigLibrary this$0, Entry $entry, SharedConfig config, String error) {
        if (error != null) {
            Notifications.push(I18n.tr("Конфиги"), I18n.tr(error), 2200L);
        } else {
            SharedConfig sharedConfig = config;
            Intrinsics.checkNotNull((Object)sharedConfig);
            JsonObject root = ConfigCodec.decode(sharedConfig.payload());
            if (root == null) {
                Notifications.push(I18n.tr("Конфиги"), I18n.tr("Повреждённый конфиг"), 2000L);
            } else {
                String name = ConfigLibrary.Companion.uniqueName(config.name());
                this$0.ensureStore();
                Map map = this$0.links;
                String string = name;
                Locale locale = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                String string2 = string.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
                string = string2;
                String string3 = config.code();
                map.put(string, string3);
                this$0.persist();
                ConfigManager.Companion.writeProfile(name, root, () -> ConfigLibrary.download$lambda$0$0(this$0, $entry, name));
            }
        }
    }

    private static final void importByCode$lambda$0$0(ConfigLibrary this$0, String $name, SharedConfig $config) {
        this$0.reloadLocal();
        Object[] objectArray = new Object[]{$name, $config.ownerName()};
        Notifications.push(I18n.tr("Конфиги"), I18n.tr("Добавлен «%s» от %s", objectArray), 2400L);
    }

    private static final void importByCode$lambda$0(ConfigLibrary this$0, SharedConfig config, String error) {
        if (error != null) {
            boolean missing = Intrinsics.areEqual((Object)"Конфиг не найден", (Object)error);
            Notifications.push(I18n.tr("Конфиги"), missing ? I18n.tr("Код не найден. Купленные конфиги вводить не нужно — они приходят сами") : I18n.tr(error), missing ? 3200L : 2200L);
        } else {
            SharedConfig sharedConfig = config;
            Intrinsics.checkNotNull((Object)sharedConfig);
            JsonObject root = ConfigCodec.decode(sharedConfig.payload());
            if (root == null) {
                Notifications.push(I18n.tr("Конфиги"), I18n.tr("Повреждённый конфиг"), 2000L);
            } else if (config.premium()) {
                this$0.refreshRemote();
                Notifications.push(I18n.tr("Конфиги"), I18n.tr("Это купленный конфиг — он появится в разделе «Купленные»"), 2600L);
            } else {
                String name = ConfigLibrary.Companion.uniqueName(config.name());
                this$0.ensureStore();
                HashSet<String> hashSet = this$0.cloudNames;
                String string = name;
                Locale locale = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                String string2 = string.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
                hashSet.add(string2);
                this$0.persist();
                ConfigManager.Companion.writeProfile(name, root, () -> ConfigLibrary.importByCode$lambda$0$0(this$0, name, config));
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final ConfigLibrary get() {
        return Companion.get();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\r\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\u000e2\b\u0010\f\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/config/ConfigLibrary.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/config/ConfigLibrary;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/config/ConfigLibrary;", "", "base", "uniqueName", "(Ljava/lang/String;)Ljava/lang/String;", "value", "sanitize", "", "copyToClipboard", "(Ljava/lang/String;)V", "INSTANCE", "Lrtx/kimiko/api/config/ConfigLibrary;", "STORE", "Ljava/lang/String;", "", "REFRESH_INTERVAL_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final ConfigLibrary get() {
            return INSTANCE;
        }

        private final String uniqueName(String base) {
            String name = this.sanitize(base);
            if (((CharSequence)name).length() == 0) {
                name = "Config";
            }
            if (!ConfigManager.Companion.profileExists(name)) {
                return name;
            }
            for (int i = 2; i < 999; ++i) {
                String candidate = name + " " + i;
                if (ConfigManager.Companion.profileExists(candidate)) continue;
                return candidate;
            }
            return name + " " + System.currentTimeMillis();
        }

        private final String sanitize(String value) {
            String string;
            String string2;
            if (value == null) {
                return "";
            }
            Regex regex = new Regex("[\\\\/:*?\"<>|]");
            CharSequence charSequence = ((Object)StringsKt.trim((CharSequence)value)).toString();
            String trimmed = regex.replace(charSequence, string2 = "");
            if (trimmed.length() > 32) {
                String string3 = trimmed.substring(0, 32);
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
                string = ((Object)StringsKt.trim((CharSequence)string3)).toString();
            } else {
                string = trimmed;
            }
            return string;
        }

        private final void copyToClipboard(String value) {
            try {
                MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
                Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
                MinecraftClient minecraft = minecraftClient2;
                if (minecraft.keyboard != null) {
                    Keyboard keyboard2 = minecraft.keyboard;
                    String string = value;
                    if (string == null) {
                        string = "";
                    }
                    keyboard2.setClipboard(string);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b'\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u000f\u0010\rJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\tJ\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0011\u0010\rJ\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0012\u0010\rJ\r\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0019\u0010\u0015J\r\u0010\u001a\u001a\u00020\u0013\u00a2\u0006\u0004\b\u001a\u0010\u0015J\u000f\u0010\u001b\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u001b\u0010\rJ\r\u0010\u001c\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001c\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001dR$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\u001e\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010!R$\u0010\u000e\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u001e\u001a\u0004\b\"\u0010\r\"\u0004\b#\u0010!R$\u0010\u000f\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u001e\u001a\u0004\b$\u0010\r\"\u0004\b%\u0010!R$\u0010\u0011\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u001e\u001a\u0004\b&\u0010\r\"\u0004\b'\u0010!R$\u0010\u0012\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u001e\u001a\u0004\b(\u0010\r\"\u0004\b)\u0010!R\"\u0010\u0014\u001a\u00020\u00138\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010*\u001a\u0004\b+\u0010\u0015\"\u0004\b,\u0010-R\"\u0010\u0017\u001a\u00020\u00168\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010.\u001a\u0004\b/\u0010\u0018\"\u0004\b0\u00101R\"\u0010\u0019\u001a\u00020\u00138\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010*\u001a\u0004\b2\u0010\u0015\"\u0004\b3\u0010-R$\u0010\u001b\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u001e\u001a\u0004\b4\u0010\r\"\u0004\b5\u0010!R\"\u0010\u001a\u001a\u00020\u00138\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010*\u001a\u0004\b6\u0010\u0015\"\u0004\b7\u0010-R\"\u00108\u001a\u00020\u00078\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010\t\"\u0004\b;\u0010<\u00a8\u0006="}, d2={"Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "", "Lrtx/kimiko/api/config/ConfigLibrary$Kind;", "kind", "<init>", "(Lrtx/kimiko/api/config/ConfigLibrary$Kind;)V", "()Lrtx/kimiko/api/config/ConfigLibrary$Kind;", "", "premium", "()Z", "cloud", "", "name", "()Ljava/lang/String;", "localName", "code", "published", "ownerName", "ownerAvatar", "", "ownerUid", "()I", "", "updatedAt", "()J", "modules", "downloads", "payload", "key", "Lrtx/kimiko/api/config/ConfigLibrary$Kind;", "Ljava/lang/String;", "getName$rtx_kimiko_kimiko", "setName$rtx_kimiko_kimiko", "(Ljava/lang/String;)V", "getLocalName$rtx_kimiko_kimiko", "setLocalName$rtx_kimiko_kimiko", "getCode$rtx_kimiko_kimiko", "setCode$rtx_kimiko_kimiko", "getOwnerName$rtx_kimiko_kimiko", "setOwnerName$rtx_kimiko_kimiko", "getOwnerAvatar$rtx_kimiko_kimiko", "setOwnerAvatar$rtx_kimiko_kimiko", "I", "getOwnerUid$rtx_kimiko_kimiko", "setOwnerUid$rtx_kimiko_kimiko", "(I)V", "J", "getUpdatedAt$rtx_kimiko_kimiko", "setUpdatedAt$rtx_kimiko_kimiko", "(J)V", "getModules$rtx_kimiko_kimiko", "setModules$rtx_kimiko_kimiko", "getPayload$rtx_kimiko_kimiko", "setPayload$rtx_kimiko_kimiko", "getDownloads$rtx_kimiko_kimiko", "setDownloads$rtx_kimiko_kimiko", "fromCloud", "Z", "getFromCloud$rtx_kimiko_kimiko", "setFromCloud$rtx_kimiko_kimiko", "(Z)V", "rtx.kimiko:kimiko"})
    public static final class Entry {
        @NotNull
        private final Kind kind;
        @Nullable
        private String name;
        @Nullable
        private String localName;
        @Nullable
        private String code;
        @Nullable
        private String ownerName;
        @Nullable
        private String ownerAvatar;
        private int ownerUid;
        private long updatedAt;
        private int modules;
        @Nullable
        private String payload;
        private int downloads;
        private boolean fromCloud;

        public Entry(@NotNull Kind kind) {
            Intrinsics.checkNotNullParameter((Object)((Object)kind), (String)"kind");
            this.kind = kind;
        }

        @Nullable
        public final String getName$rtx_kimiko_kimiko() {
            return this.name;
        }

        public final void setName$rtx_kimiko_kimiko(@Nullable String string) {
            this.name = string;
        }

        @Nullable
        public final String getLocalName$rtx_kimiko_kimiko() {
            return this.localName;
        }

        public final void setLocalName$rtx_kimiko_kimiko(@Nullable String string) {
            this.localName = string;
        }

        @Nullable
        public final String getCode$rtx_kimiko_kimiko() {
            return this.code;
        }

        public final void setCode$rtx_kimiko_kimiko(@Nullable String string) {
            this.code = string;
        }

        @Nullable
        public final String getOwnerName$rtx_kimiko_kimiko() {
            return this.ownerName;
        }

        public final void setOwnerName$rtx_kimiko_kimiko(@Nullable String string) {
            this.ownerName = string;
        }

        @Nullable
        public final String getOwnerAvatar$rtx_kimiko_kimiko() {
            return this.ownerAvatar;
        }

        public final void setOwnerAvatar$rtx_kimiko_kimiko(@Nullable String string) {
            this.ownerAvatar = string;
        }

        public final int getOwnerUid$rtx_kimiko_kimiko() {
            return this.ownerUid;
        }

        public final void setOwnerUid$rtx_kimiko_kimiko(int n) {
            this.ownerUid = n;
        }

        public final long getUpdatedAt$rtx_kimiko_kimiko() {
            return this.updatedAt;
        }

        public final void setUpdatedAt$rtx_kimiko_kimiko(long l) {
            this.updatedAt = l;
        }

        public final int getModules$rtx_kimiko_kimiko() {
            return this.modules;
        }

        public final void setModules$rtx_kimiko_kimiko(int n) {
            this.modules = n;
        }

        @Nullable
        public final String getPayload$rtx_kimiko_kimiko() {
            return this.payload;
        }

        public final void setPayload$rtx_kimiko_kimiko(@Nullable String string) {
            this.payload = string;
        }

        public final int getDownloads$rtx_kimiko_kimiko() {
            return this.downloads;
        }

        public final void setDownloads$rtx_kimiko_kimiko(int n) {
            this.downloads = n;
        }

        public final boolean getFromCloud$rtx_kimiko_kimiko() {
            return this.fromCloud;
        }

        public final void setFromCloud$rtx_kimiko_kimiko(boolean bl) {
            this.fromCloud = bl;
        }

        @NotNull
        public final Kind kind() {
            return this.kind;
        }

        public final boolean premium() {
            return this.kind == Kind.PREMIUM;
        }

        public final boolean cloud() {
            return this.kind == Kind.PREMIUM || this.fromCloud || this.published();
        }

        @Nullable
        public final String name() {
            return this.name;
        }

        @Nullable
        public final String localName() {
            return this.localName;
        }

        @Nullable
        public final String code() {
            return this.code;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public final boolean published() {
            if (this.code == null) return false;
            String string = this.code;
            Intrinsics.checkNotNull((Object)string);
            if (StringsKt.isBlank((CharSequence)string)) return false;
            return true;
        }

        @Nullable
        public final String ownerName() {
            return this.ownerName;
        }

        @Nullable
        public final String ownerAvatar() {
            return this.ownerAvatar;
        }

        public final int ownerUid() {
            return this.ownerUid;
        }

        public final long updatedAt() {
            return this.updatedAt;
        }

        public final int modules() {
            return this.modules;
        }

        public final int downloads() {
            return this.downloads;
        }

        @Nullable
        public final String payload() {
            return this.payload;
        }

        @NotNull
        public final String key() {
            return switch (WhenMappings.$EnumSwitchMapping$0[this.kind.ordinal()]) {
                case 1 -> "premium:" + this.code;
                case 2 -> "remote:" + this.code;
                case 3 -> "local:" + this.localName;
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[Kind.values().length];
                try {
                    nArray[Kind.PREMIUM.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Kind.REMOTE.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Kind.LOCAL.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/config/ConfigLibrary$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "LOCAL", "PREMIUM", "REMOTE", "rtx.kimiko:kimiko"})
    public static enum Kind {
        LOCAL,
        PREMIUM,
        REMOTE;
@NotNull
        public static EnumEntries<Kind> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

