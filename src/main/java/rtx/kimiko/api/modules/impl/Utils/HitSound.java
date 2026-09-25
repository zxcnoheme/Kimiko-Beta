/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.Util
 *  net.minecraft.util.hit.HitResult
 *  net.minecraft.util.hit.EntityHitResult
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Util;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.sounds.CustomSounds;
import rtx.kimiko.utils.sounds.SoundManager;

@Feature(value={"hitsound"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 -2\u00020\u0001:\u0001-B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\u0003J\u0011\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0003b\u0002\b\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0015R\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\u001fR\u0014\u0010$\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010+\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,\u00ca\u0001\u0010\b.\u0012\f\b/\u0012\b\b\fJ\u0004\b\b(0\u00a8\u00061"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HitSound;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "isCustom", "()Z", "customVisible", "", "rescan", "Ljava/nio/file/Path;", "pickFile", "()Ljava/nio/file/Path;", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "playSelectedSound", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "soundSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "soundType", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "volume", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "customSeparator", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "open", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "file", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "preview", "folder", "Ljava/nio/file/Path;", "", "", "files", "Ljava/util/List;", "", "lastScanMs", "J", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "hitsound", "rtx.kimiko:kimiko"})
public class HitSound
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting soundSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Звук"));
    @NotNull
    private final ModeSetting soundType;
    @NotNull
    private final NumberSetting volume;
    @NotNull
    private final SeparatorSetting customSeparator;
    @NotNull
    private final ButtonSetting open;
    @NotNull
    private final SelectSetting file;
    @NotNull
    private final ButtonSetting preview;
    @NotNull
    private final Path folder;
    @NotNull
    private List<String> files;
    private long lastScanMs;
    @NotNull
    private static final String RANDOM = "Случайный";
    @NotNull
    private static final String EMPTY = "Нет файлов";
    private static final long RESCAN_INTERVAL_MS = 1200L;

    public HitSound() {
        super("Hit Sound", "Проигрывает звук при попадании.", Category.UTILS);
        String[] stringArray = new String[]{"Стоны", "Металл", "Криминал", "Кастомный"};
        this.soundType = (ModeSetting)this.register((Setting)new ModeSetting("Звук", "Тип звука попадания.", "Стоны", stringArray));
        this.volume = (NumberSetting)this.register((Setting)new NumberSetting("Громкость", "Громкость звука.", 1.0, 0.1, 2.0, 0.1));
        this.customSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Кастомные звуки").visible(() -> HitSound.customSeparator$lambda$0(this)));
        this.open = (ButtonSetting)this.register((Setting)new ButtonSetting("Папка со звуками", "Открыть папку kimiko/hitsounds в проводнике.").label("Открыть").visible(() -> HitSound.open$lambda$0(this)));
        this.file = (SelectSetting)this.register((Setting)new SelectSetting("Файл", "Какой звук проигрывать при попадании.").visible(() -> HitSound.file$lambda$0(this)));
        this.preview = (ButtonSetting)this.register((Setting)new ButtonSetting("Прослушать", "Проиграть выбранный звук.").label("Играть").visible(() -> HitSound.preview$lambda$0(this)));
        this.folder = CustomSounds.folder("hitsounds");
        this.files = CollectionsKt.emptyList();
        stringArray = new String[]{RANDOM};
        this.file.value(stringArray);
        this.open.onClick(() -> HitSound._init_$lambda$0(this));
        this.preview.onClick(() -> HitSound._init_$lambda$1(this));
        CustomSounds.ensureFolder(this.folder);
        this.rescan();
    }

    private final boolean isCustom() {
        return this.soundType.is("Кастомный");
    }

    private final boolean customVisible() {
        if (!this.isCustom()) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastScanMs >= 1200L) {
            this.rescan();
        }
        return true;
    }

    private final void rescan() {
        this.lastScanMs = System.currentTimeMillis();
        List<String> found = CustomSounds.list(this.folder);
        if (Intrinsics.areEqual(found, this.files)) {
            return;
        }
        this.files = found;
        CustomSounds.invalidate();
        ArrayList<String> options = new ArrayList<String>();
        if (this.files.isEmpty()) {
            options.add(EMPTY);
        } else {
            options.add(RANDOM);
            options.addAll(this.files);
        }
        this.file.options((List<String>)options);
    }

    private final Path pickFile() {
        if (this.files.isEmpty()) {
            return null;
        }
        String selected = this.file.getSelected();
        if (Intrinsics.areEqual((Object)selected, (Object)RANDOM) || Intrinsics.areEqual((Object)selected, (Object)EMPTY) || !this.files.contains(selected)) {
            return this.folder.resolve(this.files.get(ThreadLocalRandom.current().nextInt(this.files.size())));
        }
        return this.folder.resolve(selected);
    }

    @EventHandler
    private final void onAttack(AttackEntityEvent event) {
        if (event.isSynthetic() || !(event.getTarget() instanceof LivingEntity)) {
            return;
        }
        HitResult hitResult2 = this.mc.crosshairTarget;
        EntityHitResult entityHitResult2 = hitResult2 instanceof EntityHitResult ? (EntityHitResult)hitResult2 : null;
        if (entityHitResult2 == null) {
            return;
        }
        EntityHitResult hit = entityHitResult2;
        if (!Intrinsics.areEqual((Object)hit.getEntity(), (Object)event.getTarget())) {
            return;
        }
        this.playSelectedSound();
    }

    private final void playSelectedSound() {
        float vol = this.volume.getFloat();
        if (this.isCustom()) {
            Path picked = this.pickFile();
            if (picked != null) {
                CustomSounds.play(picked, vol);
            }
        } else if (this.soundType.is("Металл")) {
            SoundManager.playSound(SoundManager.METALLIC, vol, 1.0f);
        } else if (this.soundType.is("Криминал")) {
            SoundManager.playSound(SoundManager.CRIME, vol, 1.0f);
        } else {
            switch (ThreadLocalRandom.current().nextInt(4)) {
                case 0: {
                    SoundManager.playSound(SoundManager.MOAN1, vol, 1.0f);
                    break;
                }
                case 1: {
                    SoundManager.playSound(SoundManager.MOAN2, vol, 1.0f);
                    break;
                }
                case 2: {
                    SoundManager.playSound(SoundManager.MOAN3, vol, 1.0f);
                    break;
                }
                default: {
                    SoundManager.playSound(SoundManager.MOAN4, vol, 1.0f);
                }
            }
        }
    }

    private static final Boolean customSeparator$lambda$0(HitSound this$0) {
        return this$0.customVisible();
    }

    private static final Boolean open$lambda$0(HitSound this$0) {
        return this$0.customVisible();
    }

    private static final Boolean file$lambda$0(HitSound this$0) {
        return this$0.customVisible();
    }

    private static final Boolean preview$lambda$0(HitSound this$0) {
        return this$0.customVisible();
    }

    private static final void _init_$lambda$0(HitSound this$0) {
        CustomSounds.ensureFolder(this$0.folder);
        Util.getOperatingSystem().open(this$0.folder);
    }

    private static final void _init_$lambda$1(HitSound this$0) {
        this$0.rescan();
        Path picked = this$0.pickFile();
        if (picked != null) {
            CustomSounds.play(picked, this$0.volume.getFloat());
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HitSound.Companion;", "", "<init>", "()V", "", "RANDOM", "Ljava/lang/String;", "EMPTY", "", "RESCAN_INTERVAL_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

